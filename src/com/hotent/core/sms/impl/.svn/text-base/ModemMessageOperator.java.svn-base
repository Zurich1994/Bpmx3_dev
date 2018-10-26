package com.hotent.core.sms.impl;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gnu.io.*;

/**
 * 通过串口发送信息 ①打开端口openPort();②读写信息writeByte()-->readByte();③关闭端口closePort();
 * 
 * @author heyifan
 * @date 2012-5-9
 */
public class ModemMessageOperator {
    private static ModemMessageOperator instance;

    private static ModemMessagePort messagePort;

    private static Lock lock = new ReentrantLock();
    protected static Logger logger = LoggerFactory.getLogger(ModemMessageOperator.class);
    // 需要设置的参数
    int portId; // 串口号：如：com1，则portId为1

    int baudrate; // 波特率

    // 不一定要设定的参数(有默认值)
    int timeOut; // 延迟时间(毫秒数)

    int dataBits; // 数据位

    int stopBits; // 停止位

    int parity; // 奇偶检验

    int funCode; // 功能码

    int dataLen; // 数据长度

    int appendMillsec; // 计算发送间隔用---附加毫秒数

    int bytes; // 计算发送间隔用---发送字节数

    // 自动计算--发送间隔
    int frameInterval; // 根据波特率，数据倍率和数据量，自动设置发送间隔

    // 构造方法
    private ModemMessageOperator() {
	messagePort = ModemMessagePort.getInstance();
	timeOut = 60; // 延迟时间(毫秒数)
	baudrate = 9600;
	dataBits = SerialPort.DATABITS_8; // 数据位
	stopBits = SerialPort.STOPBITS_1; // 停止位
	parity = SerialPort.PARITY_NONE; // 奇偶检验
	funCode = 3; // 读取当前寄存器内一个或多个二进制值
	dataLen = 4; // 假设 需要获取4个数据
	appendMillsec = 38; // 附加毫秒数(需要自己测试调整)
	bytes = 20; // 发送是字节数
    }

    public static ModemMessageOperator getInstance() {
	if (instance == null) {
	    lock.lock();
	    try {
		if (instance == null)
		    instance = new ModemMessageOperator();
	    } catch (Exception ex) {
		logger.info("[sms]error:" + ex.getMessage());
	    } finally {
		lock.unlock();
	    }
	}
	return instance;
    }

    /**
     * @describe: 打开串口
     * @param portStr
     *            串口号. 如: COM3
     * @param baudrate
     *            波特率
     * @param appName
     *            串口占用程序的命名
     * @return: true:打开串口正常 false:打开串口异常
     */
    public boolean openPort(String portStr, int baudrate, String appName) {
	boolean rsBool = false;

	// 初始化串口
	messagePort.initialize(timeOut, baudrate, dataBits, stopBits, parity);
	messagePort.setAppname(appName.toUpperCase());
	// 打开串口
	if (messagePort.openPort(portStr)) {
	    rsBool = true;
	    // 设置帧之间的发送间隔
	    this.frameInterval = getFrameInterval(appendMillsec, bytes, baudrate);
	}
	return rsBool;
    }

    /**
     * @describe: 写串口命令 - 发送AT这个指令
     * @param rs
     *            发送的数据
     */
    public void writeByte(char[] rs) throws Exception {
	messagePort.writePort(rs);
	// 打印发送的串口数据-16进制显示
	// logger.info(bytesToHexString(rs));

	// 等待一段时间, 以保证数据,有足够的时间发送和接收
	// Thread.sleep(frameInterval);
	Thread.sleep(frameInterval);
    }

    /**
     * @describe: .读串口命令 - 对发送AT这个指令,返回OK就是成功
     * @return: true:成功 false:失败
     */
    public boolean readByte(String portStr) throws Exception {
	boolean rsbool = false;
	String rsStr = "";
	// 读取数据
	char[] rsByte = messagePort.readPackData();
	if (rsByte != null) {
	    // 打印收到的串口数据-16进制显示
	    for (char c : rsByte) {
		rsStr += c;
	    }
	    if (rsStr.indexOf("OK") > 0) {
		logger.info("找到" + portStr + ":短信模块串口");
		rsbool = true;
	    }
	} else {
	    logger.info(portStr + ":不是短信模块串口");
	}
	// 处理收到的数据
	return rsbool;
    }

    /**
     * @describe:获取可用的串口
     * @return
     */
    public String getRightComStr() {
	String rightCom = null;
	// 扫描端口数量,并逐个测试
	List<String> portList = messagePort.getAllComPorts();
	if (portList.size() <= 0) {
	} else {
	    // 逐个扫描测试连通性
	    for (String portStr : portList) {
		// 测试串口的是否适合短信模块
		if (testSms(portStr)) {
		    rightCom = portStr;
		    break;
		}
	    }
	}
	return rightCom;
    }

    /**
     * @describe: 测试串口的是否适合短信模块
     * @param portStr
     *            : 串口号. 如:COM3
     * @return: null:失败 其他:成功
     */
    private boolean testSms(String portStr) {
	boolean rsBool = false;
	try {
	    rsBool = instance.openPort(portStr, baudrate, "sms_port");
	    String atCommand = "AT\r"; // 发送AT指令(加换行符号\r)
	    char[] atOrder = atCommand.toCharArray();
	    if (rsBool)
		instance.writeByte(atOrder);
	    if (rsBool) {
		// 串口读(根据得到的数据,判断返回数据的连通性{返回字符包含OK表示成功})
		rsBool = instance.readByte(portStr);
		instance.closePort();
	    }
	} catch (Exception e) {
	    rsBool = false;
	    e.printStackTrace();
	}
	return rsBool;
    }

    /**
     * @describe: 关闭串口，释放资源
     * @date:2012-5-9
     */
    public void closePort() {
	messagePort.closePort();
    }

    // ---------------工具方法---------------//
    /**
     * @describe: 获取需要帧之间需要间隔的时间(毫秒) 功能公式(1*12(位)*数据长度*1000/波特率 +
     *            附加毫秒数)--根据自己的程序动态调整
     * @param appendMillsec
     *            附加毫秒数
     * @param dataLen
     *            数据区数据长度
     * @param baudrate
     *            波特率
     * @return 得到合适的帧发送,间隔毫秒数
     * @date:2012-5-9
     */
    public static int getFrameInterval(int appendMillsec, int dataLen, int baudrate) {
	int rsInt = (int) Math.ceil(1 * 12 * (dataLen + 4) * 1000 / (float) baudrate) + appendMillsec;
	return rsInt;
    }

    /**
     * @describe: 把char类型转换成16进制字符串
     * @param bArray
     *            char类型数组
     * @date:2012-5-9
     */
    public static final String bytesToHexString(char[] bArray) {
	StringBuffer sb = new StringBuffer(bArray.length);
	String sTemp;

	for (int i = 0; i < bArray.length; i++) {
	    sTemp = Integer.toHexString(0xFF & bArray[i]);
	    if (sTemp.length() < 2) {
		sb.append(0);
	    }
	    sb.append(sTemp.toUpperCase() + " ");
	}
	return sb.toString();
    }
}
