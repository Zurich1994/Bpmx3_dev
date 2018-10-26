/**
 * 描述：TODO
 * 包名：com.hotent.platform.webservice.impl.util
 * 文件名：StopTaskUtil.java
 * 作者：User-mailto:chensx@jee-soft.cn
 * 日期2015-1-14-下午2:21:13
 *  2015广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.platform.webservice.impl.util;

import org.activiti.engine.impl.persistence.entity.TaskEntity;

import com.hotent.core.util.AppUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.platform.service.bpm.BpmService;

/**
 * <pre> 
 * 描述：终止任务的工具类
 * 构建组：bpm33
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2015-1-14-下午2:21:13
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class StopTaskUtil {
	/**
	 * 检查任务是否还在
	 * @param taskId
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isTaskExsit(String taskId){
		BpmService bpmService = (BpmService) AppUtil.getBean("bpmService");
		TaskEntity taskEnt = bpmService.getTask(taskId);
		if(taskEnt==null){
			return false;
		}
		else{
			return true;
		}
	}
}
