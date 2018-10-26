package com.hotent.platform.xml.util;

import java.util.ArrayList;
import java.util.List;

import com.hotent.core.util.BeanUtils;

/**
 * 消息Util工具类,消息使用list存放。
 * 
 * @author zxh
 * 
 */
public class MsgUtil {

	private static ThreadLocal<List<String>> msglist=new ThreadLocal<List<String>>();
	
	private static ThreadLocal<String> filePathLocal = new ThreadLocal<String>();	

	/**成功 */
	public static final int SUCCESS = 1;
	/**警告*/
	public static final int WARN = 2;
	/**错误*/
	public static final int ERROR = 3;
	/**其它*/
	public static final int OTHER = 0;

	/**
	 * 添加消息。
	 * @param type 消息的类型
	 * @param msg 添加的消息
	 */
	public static void addMsg(int type, String msg) {
		List<String> list = msglist.get();
		if (BeanUtils.isNotEmpty(msg))
			msg = convertMsg(type, msg);
		if (BeanUtils.isEmpty(list)) {
			list = new ArrayList<String>();
			list.add(msg);
			msglist.set(list);
		} else {
			list.add(msg);
		}
	}
	/**
	 * 转换消息
	 * @param type
	 * @param msg
	 * @return
	 */
	private static String convertMsg(int type, String msg) {
		StringBuffer sb = new StringBuffer();
		Boolean flag = true;
		if (BeanUtils.isNotEmpty(type)) {
			if (type == SUCCESS) {
				sb.append("!!! style=###color:green;###>");
			} else if (type == WARN) {
				sb.append("!!!  style=###color:red;###>");
			} else if(type == ERROR) {
				sb.append("!!!  style=###color:orange;###>");
			}else{
				flag = false;
			}
			sb.append(msg);
			if(flag)
				sb.append("%%%");
		}
		return sb.toString();
	}
	/**
	 * 增加换行
	 */
	public static void addSplit(){
		addMsg(OTHER, "------------------------------------------------------------");
	}
	
	/**
	 * 增加空白
	 * @return
	 */
	public static String addSpace() {
		return "&nbsp;&nbsp;&nbsp;&nbsp;";
	}


	/**
	 * 获取消息数据，并直接清除消息中的数据。
	 * 
	 * @return
	 */
	public static List<String> getMsg() {
		return getMsg(true);
	}

	/**
	 * 获取消息数据。
	 * 
	 * @param clean
	 * @return
	 */
	public static List<String> getMsg(boolean clean) {
		List<String> list = msglist.get();
		if (clean)
			clean();
		return list;
	}

	/**
	 * 返回消息。
	 * 
	 * @return
	 */
	public static String getMessage() {
		return getMessage(true);
	}

	/**
	 * 获取消息。
	 * 
	 * @param clean
	 * @return
	 */
	public static String getMessage(boolean clean) {
		List<String> list = getMsg(clean);
		String str = "";
		if (BeanUtils.isEmpty(list)) 
			return str;
		for (String msg : list) {
			str += msg + "</br>";
		}
		return str;
	}
	
	/**
	 * 
	 * @param filePath
	 */
	public static void addFilePath(String filePath){
		filePathLocal.set(filePath);
	}

	/**
	 * 
	 */
	public static void cleanFilePath(){
		filePathLocal.remove();
	}

	/**
	 * 
	 * @return
	 */
	public static String  getFilePath(){
		if( filePathLocal.get()==null)
			return "";
		return filePathLocal.get();
	}

	/**
	 * 清除消息。
	 */
	public static void clean(){
		msglist.remove();
	}

}
