/**
 * 描述：TODO
 * 包名：com.hotent.platform.service.util
 * 文件名：SysObjLogUtil.java
 * 作者：User-mailto:liyj@jee-soft.cn
 * 日期2015-4-27-下午2:01:56
 *  2015广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.platform.service.util;

import net.sf.json.JSONObject;

import com.hotent.core.util.AppUtil;
import com.hotent.platform.model.system.SysObjLog;
import com.hotent.platform.service.system.SysObjLogService;

/**
 * <pre>
 * 描述：TODO
 * 构建组：bpm33
 * 作者：aschs
 * 邮箱:liyj@jee-soft.cn
 * 日期:2015-4-27-下午2:01:56
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class SysObjLogUtil {

	/**
	 * 
	 * 添加一条日志
	 * 
	 * @param name
	 *            ：名称
	 * @param content
	 *            ：内容
	 * @param objType
	 *            ：对象类型 关键字
	 * @param param
	 *            ：json格式的字符串 void
	 * @exception
	 * @since 1.0.0
	 */
	private static void add(String name, String content, String objType, String param) {
		SysObjLog sysObjLog = new SysObjLog();
		sysObjLog.setName(name);
		sysObjLog.setContent(content);
		sysObjLog.setObjType(objType);
		sysObjLog.setParam(param);

		SysObjLogService service = AppUtil.getBean(SysObjLogService.class);
		service.save(sysObjLog);
	}

	/**
	 * 
	 * 添加一条日志
	 * 
	 * @param name
	 *            ：名称
	 * @param content
	 *            ：内容
	 * @param objType
	 *            ：对象类型 关键字
	 * @param param
	 *            ：附带参数json
	 * @exception
	 * @since 1.0.0
	 */
	public static void add(String name, String content, String objType, JSONObject param) {
		add(name, content, objType, param.toString());
	}

	/**
	 * 
	 * 添加一条日志
	 * 
	 * @param name
	 *            ：名称
	 * @param content
	 *            ：内容
	 * @param objType
	 *            ：对象类型 关键字
	 * @exception
	 * @since 1.0.0
	 */
	public static void add(String name, String content, String objType) {
		add(name, content, objType, "");
	}
}
