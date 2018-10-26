package com.hotent.platform.service.bpm.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import org.activiti.engine.impl.persistence.deploy.DeploymentCache;
import org.activiti.engine.task.Task;

import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.BeanUtils;
/**
 * 流程任务执行线程绑定服务类
 * @author csx
 *
 */
public class TaskThreadService {
	//任务令牌服务类，一般是用于任务分发时，传递分发的令牌
	private static ThreadLocal<String> forkTaskTokenLocal=new ThreadLocal<String>();
	//用于任务完成后，收集产生的新任务
	private static ThreadLocal<List<Task>> newTasksLocal= new ThreadLocal<List<Task>>();
	//前面节点的执行用户。
	private static ThreadLocal<String> preUserLocal=new ThreadLocal<String>();
	
	private static ThreadLocal<ProcessCmd> processCmdLocal=new ThreadLocal<ProcessCmd>();
	//外部子流程调用,存放子流程实例id列表。
	private static ThreadLocal<List<String>> extSubProcess=new ThreadLocal<List<String>>();
	
	private static ThreadLocal<Object> objLocal=new ThreadLocal<Object>();
	//是否是流程启动时跳过的第一个节点
	private static ThreadLocal<Short> toFirstNode=new ThreadLocal<Short>();
	
	private static ThreadLocal<Map<String,Object>> varsLocal=new ThreadLocal<Map<String,Object>>();
	//临时线程变量
	private static ThreadLocal<Map<String,Object>> tempLocal=new ThreadLocal<Map<String,Object>>();
	
	public static void addTask(Task taskEntity){
		List<Task> taskList=newTasksLocal.get();
		if(taskList==null){
			taskList=new ArrayList<Task>();
			newTasksLocal.set(taskList);
		}
		taskList.add(taskEntity);
	}
	
	public static List<Task> getNewTasks(){
		List<Task> list=newTasksLocal.get();
		return list;
}
	
	/**
	 * 根据任务ID从任务列表中删除任务。
	 * @param taskId
	 */
	public static void removeTask(String taskId){
		List<Task> list=newTasksLocal.get();
		if(BeanUtils.isEmpty(list)) return;
		for(Iterator<Task> it=list.iterator();it.hasNext();){
			Task task=it.next();
			if(taskId.equals(task.getId())){
				it.remove();
			}
		}
	}
	
	
	public static String getToken(){
		return forkTaskTokenLocal.get();
	}
	
	public static void setToken(String token){
		forkTaskTokenLocal.set(token);
	}
	
	public static void clearToken(){
		forkTaskTokenLocal.remove();
	}
	
	public static void clearNewTasks(){
		newTasksLocal.remove();
	}
	
	public static void cleanTaskUser(){
		preUserLocal.remove();
	}
	
	/**
	 * 获取上下文的ProcessCmd对象。
	 * 
	 * @return
	 */
	public static ProcessCmd getProcessCmd(){
		return processCmdLocal.get();
	}
	
	/**
	 * 设置ProcessCmd对象到线程变量中。
	 * @param processCmd
	 */
	public static void setProcessCmd(ProcessCmd processCmd){
		processCmdLocal.set(processCmd);
	}
	
	public static void cleanProcessCmd(){
		processCmdLocal.remove();
	}

	public static List<String> getExtSubProcess() {
		return extSubProcess.get();
	}

	public static void setExtSubProcess(List<String> extSubProcessList) {
		extSubProcess.set(extSubProcessList);
	}
	
	/**
	 * 添加变量。
	 * @param key
	 * @param value
	 */
	public static void addExtSubProcess(String instanceId) {
		List<String> list=null;
		if(extSubProcess.get()==null){
			list=new ArrayList<String>();
			list.add(instanceId);
			extSubProcess.set(list);
		}
		else{
			extSubProcess.get().add(instanceId);
		}
	}
	
	public static void cleanExtSubProcess() {
		extSubProcess.remove();
	}
	
	public static void setObject(Object obj){
		objLocal.set(obj);
	}
	
	public static Object getObject(){
		return objLocal.get();
	}
	
	public static void setToFirstNode(Short obj){
		toFirstNode.set(obj);
	}
	
	public static Object getToFirstNode(){
		return toFirstNode.get();
	}
	public static void removeToFirstNode(){
		toFirstNode.remove();
	}
	
	public static void removeObject(){
		 objLocal.remove();
	}
	
	public static void setVariables(Map<String,Object> vars_){
		varsLocal.set(vars_);
	}
	
	public static void putVariables(Map<String,Object> vars_){
		if(varsLocal.get()==null) return;
		varsLocal.get().putAll(vars_);
	}
	
	public static Map<String,Object> getVariables(){
		return varsLocal.get();
	}
	
	public static void clearVariables(){
		varsLocal.remove();
	}
	
	
	/**
	 * 设置临时线程变量
	 * @param actInstId		线程变量ID
	 */
	public static void setTempLocal(String actInstId){
		if(tempLocal.get()!=null ){
			Map<String,Object> map=tempLocal.get();
			if(map.get(actInstId)!=null){
				return ;
			}
			else{
				setTempLocal(actInstId,map);
			}
		}
		else{
			Map<String,Object> map=new HashMap<String, Object>();
			setTempLocal(actInstId,map);
			tempLocal.set(map);
		}
	}
	
	/**
	 * 设置流程实例临时变量。
	 * @param actInstId
	 * @param map
	 */
	private static void setTempLocal(String actInstId,Map<String,Object> map ){
		map.put("forkTaskTokenLocal"+actInstId, forkTaskTokenLocal.get());
		map.put("newTasksLocal"+actInstId, newTasksLocal.get());
		map.put("preUserLocal"+actInstId, preUserLocal.get());
		map.put("processCmdLocal"+actInstId, processCmdLocal.get());
		map.put("extSubProcess"+actInstId, extSubProcess.get());
		map.put("objLocal"+actInstId, objLocal.get());
		map.put("varsLocal"+actInstId, varsLocal.get());
	}
	
	/**
	 * 恢复临时线程变量
	 * @param actInstId
	 */
	@SuppressWarnings("unchecked")
	public static void resetTempLocal(String actInstId){
		Map<String,Object> map=tempLocal.get();
		forkTaskTokenLocal.set((String)map.get("forkTaskTokenLocal" +actInstId));
		newTasksLocal.set((List<Task>)map.get("newTasksLocal" + actInstId));
		preUserLocal.set((String)map.get("preUserLocal" + actInstId));
		processCmdLocal.set((ProcessCmd)map.get("processCmdLocal" + actInstId));
		extSubProcess.set((List<String>)map.get("extSubProcess" + actInstId));
		objLocal.set((Object)map.get("objLocal" + actInstId));
		varsLocal.set((Map<String,Object>)map.get("varsLocal" + actInstId));
	}
	
	

	
	/**
	 * 清除所有的流程变量。
	 */
	public static void clearAll(){
		forkTaskTokenLocal.remove();
		newTasksLocal.remove();
		preUserLocal.remove();
		processCmdLocal.remove();
		extSubProcess.remove();
		objLocal.remove();
		varsLocal.remove();
		
		//DeploymentCache.clearProcessDefinitionEntity();
		
		tempLocal.remove();
	}
}
