package com.hotent.core.sms.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.hotent.core.sms.IShortMessage;
import com.hotent.platform.service.system.SysPropertyService;

/**
 * IShortMessage的Soap实现类，实现短信的发送。 通过SmsFactoryBean实例化和使用。
 * 
 * @author RaiseDragon
 * 
 */
public class ShortMessageImpl implements IShortMessage {
	private static ShortMessageImpl instance;
	private static Lock lock = new ReentrantLock();

	String url;
	String userID;
	String account;
	String password;
	String content;
	String sendTime;
	int sendType;
	String postFixNumber;
	int sign;

	/**
	 * 初始化url、userId、account、password、content、sendtime、sendtype、postFixNumber、
	 * sign连接soap的参数
	 */
	public void initial() {
		url = SysPropertyService.getByAlias("smsUrl");
		userID = SysPropertyService.getByAlias("smsUserID");
		account = SysPropertyService.getByAlias("smsAccount");
		password = SysPropertyService.getByAlias("smsPassword");
		content = SysPropertyService.getByAlias("smsContent");
		sendTime = SysPropertyService.getByAlias("smsSendTime","");
		sendType = SysPropertyService.getIntByAlias("smssendType",0);
		postFixNumber = SysPropertyService.getByAlias("smsPostFixNumber");
		sign = SysPropertyService.getIntByAlias("smsSign",0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hotent.core.sms.IShortMessage#sendSms(java.util.List,
	 * java.lang.String)
	 */
	@Override
	public boolean sendSms(List<String> mobiles, String message) {
		initial();
		String envelop = generateEnvelop(mobiles, message);

		// Send data
		URL uRL;
		URLConnection conn;
		OutputStreamWriter outputStreamWriter = null;
		BufferedReader bufferedReader = null;
		try {
			uRL = new URL(url);
			conn = uRL.openConnection();
			conn.setDoOutput(true);
			outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
			outputStreamWriter.write(envelop);
			outputStreamWriter.flush();

			// Get the response
			bufferedReader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuffer response = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				response.append(line);
			}

			outputStreamWriter.close();
			bufferedReader.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 生成wsdl格式的消息信封
	 * 
	 * @param mobiles
	 *            接收短信的手机号码
	 * @param content
	 *            短信内容
	 * @return String类型的wsdl格式的消息信封
	 */
	private String generateEnvelop(List<String> mobiles, String content) {
		StringBuffer phones = new StringBuffer();
		for (String mobile : mobiles) {
			phones.append(mobile);
			phones.append(",");
		}
		phones.deleteCharAt(phones.length() - 1);
		String wsdlStr = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.sms.com\">"
				+ "<soapenv:Header/>"
				+ "<soapenv:Body>"
				+ "<soap:directSend>"
				+ "<soap:userID>"
				+ userID
				+ "</soap:userID>"
				+ "<soap:account>"
				+ account
				+ "</soap:account>"
				+ "<soap:password>"
				+ password
				+ "</soap:password>"
				+ "<soap:phones>"
				+ phones
				+ "</soap:phones>"
				+ "<soap:content>"
				+ content
				+ "</soap:content>"
				+ "<soap:sendTime>"
				+ sendTime
				+ "</soap:sendTime>"
				+ "<soap:sendType>"
				+ sendType
				+ "</soap:sendType>"
				+ "<soap:postFixNumber>"
				+ postFixNumber
				+ "</soap:postFixNumber>"
				+ "<soap:sign>"
				+ sign
				+ "</soap:sign>"
				+ "</soap:directSend>"
				+ "</soapenv:Body>"
				+ "</soapenv:Envelope>";
		return wsdlStr;
	}

	/**
	 * 获取单例对象
	 * 
	 * @return
	 */
	public static ShortMessageImpl getInstance() {
		if (instance == null) {
			lock.lock();
			try {
				if (instance == null)
					instance = new ShortMessageImpl();
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	
}
