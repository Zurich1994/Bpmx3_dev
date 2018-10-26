package com.hotent.core.sms.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.GatewayException;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.AGateway.Protocols;
import org.smslib.Message.MessageEncodings;
import org.smslib.modem.SerialModemGateway;
import com.hotent.core.sms.IShortMessage;

/**
 * 通过短信猫发送短信
 * 
 * @author heyifan
 * @date 2012-5-9
 */
public class ModemMessage implements IShortMessage {
	private static ModemMessage instance = null;

	private static Lock lock = new ReentrantLock();

	private boolean serviceHasOpen = false;

	private Service srv = null; // 发送信息的服务

	private SerialModemGateway gateway; // 网关

	private static String smsGroup = "smsgruop";
	protected Logger logger = LoggerFactory.getLogger(ModemMessage.class);
	/**
	 * 获取单例对象
	 * 
	 * @return
	 */
	public static ModemMessage getInstance() {
		if (instance == null) {
			lock.lock();
			try {
				if (instance == null)
					instance = new ModemMessage();
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	/**
	 * 初始化服务和网关
	 * 
	 * @param com
	 *            串口名 (自动扫描可用串口)
	 * @param baudRate
	 *            比特率 (默认为9600)
	 * @param pin
	 *            Pin用户识别码(一般为0000)
	 * @return
	 */
	private boolean initial(String com, int baudRate, String pin) {
		boolean rsbool = true;
		this.srv = new Service();
		this.gateway = new SerialModemGateway("SMSLINK", com, baudRate, "", "");
		this.gateway.setOutbound(true);
		this.gateway.setInbound(true);
		this.gateway.setProtocol(Protocols.PDU);
		this.gateway.setSimPin(pin);
		try {
			this.srv.addGateway(gateway);
		} catch (GatewayException e) {
			rsbool = false;
			e.printStackTrace();
		}
		if (rsbool)
			rsbool = startService();
		return rsbool;
	}

	/**
	 * 发送信息
	 * 
	 * @param phoneList
	 * @param message
	 * @return
	 */
	private boolean sendMessage(List<String> phoneList, String message) {
		boolean rsbool = true;
		// 把手机号码逐个加入到短信发送组中
		for (String phone : phoneList) {
			this.srv.addToGroup(smsGroup, phone);
		}
		OutboundMessage msg = new OutboundMessage(smsGroup, message);
		msg.setEncoding(MessageEncodings.ENCUCS2);
		try {
			this.srv.sendMessage(msg);
			// 发送完短信,把手机号码逐个从短信发送组中移除
			for (String phone : phoneList) {
				this.srv.removeFromGroup(smsGroup, phone);
			}
		} catch (Exception e) {
			rsbool = false;
			e.printStackTrace();
		}
		return rsbool;
	}

	// 启动服务
	private boolean startService() {
		boolean rsbool = true;
		try {
			this.srv.startService();
			this.srv.createGroup(smsGroup);
			// 注册启动短信接收事件 -- 小短信
			// this.srv.setInboundMessageNotification(inbound);
			// 注册启动短信接收事件 -- 大短信
			// this.srv.setOrphanedMessageNotification(Orphaned);
			// ... 还可以注册其他事件
		} catch (Exception e) {
			rsbool = false;
			e.printStackTrace();
		}
		return rsbool;
	}

	/**
	 * 关闭服务
	 * 
	 * @return
	 */
	public boolean stopService() {
		boolean rsbool = true;
		try {
			if (srv != null) {
				this.srv.stopService();
				serviceHasOpen = false;
			}
		} catch (Exception e) {
			rsbool = false;
			e.printStackTrace();
		}
		return rsbool;
	}

	@Override
	public boolean sendSms(List<String> mobiles, String message) {
		if (serviceHasOpen)
			return sendMessage(mobiles, message);
		String comStr = ModemMessageOperator.getInstance().getRightComStr();
		if (comStr == null)
			logger.info("[SMS]未能获取到可以发送短信的串口。");
		logger.info("[SMS]开始使用串口:" + comStr + "发送短信。");
		if (comStr != null) {
			if (initial(comStr, 9600, "0000")) {
				serviceHasOpen = true;
				return sendMessage(mobiles, message);
			} else
				return false;
		} else
			return false;
	}
	
	public static void main(String[] args) {
		//15992494490
		List<String> list=new ArrayList<String>();
		list.add("15992494490");
		ModemMessage msg=ModemMessage.getInstance();
		msg.sendSms(list, "hello 庄晓辉");
	}
}
