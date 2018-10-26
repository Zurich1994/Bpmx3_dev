/**
 * 描述：TODO
 * 包名：com.hotent.platform.webservice.impl.util
 * 文件名：DefRevocationUtil.java
 * 作者：User-mailto:chensx@jee-soft.cn
 * 日期2015-1-14-上午11:48:31
 *  2015广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.platform.webservice.impl.util;

import com.hotent.core.util.AppUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.platform.service.bpm.ProcessRunService;

/**
 * <pre>
 * 描述：流程撤销的工具类
 * 构建组：bpm33
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2015-1-14-上午11:48:31
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class DefRevocationUtil {
	/**
	 * 检查runId是否可撤销
	 * @param runId
	 * @return 
	 * ResultMessage
	 * @exception 
	 * @since  1.0.0
	 */
	public static ResultMessage checkRecover(Long runId) {
		ResultMessage result =null;
		try {
			result = ((ProcessRunService) AppUtil.getBean("processRunService")).checkRecover(runId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
