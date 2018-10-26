package com.hotent.core.bpm.util;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.interceptor.CommandExecutor;

import com.hotent.core.util.AppUtil;

/**
 * 获取命令执行器。
 * @author ray
 */
public class ActivitiUtil {

	/**
	 * 获取流程引擎命令执行对象，用于执行CMD对象。
	 * @return 
	 * CommandExecutor
	 */
	public static CommandExecutor getCommandExecutor(){
		ProcessEngineImpl engine = (ProcessEngineImpl)AppUtil.getBean(ProcessEngine.class) ;
		CommandExecutor cmdExecutor=engine.getProcessEngineConfiguration().getCommandExecutor();
		return cmdExecutor;
	}

}
