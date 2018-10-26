package com.hotent.core.bpm.cache;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.impl.persistence.deploy.DeploymentCache;

import com.hotent.core.cache.ICache;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.FileUtil;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;



/**
 * 流程引擎的自定义缓存接口，可以配置到activiti的配置文件中。
 * 支持集群部署，如果集群部署的情况可以使用到memcached缓存。
 * <pre> 
 * &lt;property name="processDefinitionCache">
 *	&lt;bean class="com.hotent.core.bpm.cache.ActivitiDefCache">&lt;/bean>
 * &lt;/property>
 * 构建组：x5-bpmx-activiti
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2013-12-20-上午10:26:09
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class ActivitiDefCache implements DeploymentCache<ProcessDefinitionEntity> {
	
	/**
	 * 清除线程变量缓存，这个在每次请求前进行调用。
	 */
	public static void clearLocal(){
		ActivitiDefCache cache=(ActivitiDefCache)AppUtil.getBean(ActivitiDefCache.class);
		cache.clearProcessCache();
	}
	
	/**
	 * 根据流程定义ID清除缓存，这个在流程定义xml发生变更时进行调用。
	 * @param actDefId
	 */
	public static void clearByDefId(String actDefId){
		ActivitiDefCache cache=(ActivitiDefCache)AppUtil.getBean(ActivitiDefCache.class);
		cache.clearProcessDefinitionEntity(actDefId);
		cache.clearProcessCache();
	}
	
	private ThreadLocal<Map<String,ProcessDefinitionEntity>> processDefinitionCacheLocal = new ThreadLocal<Map<String,ProcessDefinitionEntity>>();

	 private void clearProcessDefinitionEntity(String defId){
		 remove(defId);
		 processDefinitionCacheLocal.remove();
	 }
	 
	 private  void clearProcessCache(){
		 processDefinitionCacheLocal.remove();
	 }
	 
	 private  void setThreadLocalDef(ProcessDefinitionEntity processEnt){
		  if(processDefinitionCacheLocal.get()==null){
			  Map<String, ProcessDefinitionEntity> map=new HashMap<String, ProcessDefinitionEntity>();
			  map.put(processEnt.getId(),processEnt);
			  processDefinitionCacheLocal.set(map);
		  }
		  else{
			  Map<String, ProcessDefinitionEntity> map=processDefinitionCacheLocal.get();
			  map.put(processEnt.getId(),processEnt);
		  }
		
	 }
	 
	 private  ProcessDefinitionEntity getThreadLocalDef(String processDefinitionId){
		  if(processDefinitionCacheLocal.get()==null){
			  return null;
		  }
		  Map<String, ProcessDefinitionEntity> map=processDefinitionCacheLocal.get();
		  if(!map.containsKey(processDefinitionId)){
			  return null;
		  }
		  //LOG.info("get definition from local thread:"+processDefinitionId);
		  return map.get(processDefinitionId);
	 }
	
	
	@Resource
	ICache iCache;

	@Override
	public ProcessDefinitionEntity get(String id) {
		ProcessDefinitionEntity ent=(ProcessDefinitionEntity)iCache.getByKey(id);
		if(ent==null) return null;
		ProcessDefinitionEntity cloneEnt=null;
		try {
			//克隆流程定义。
			cloneEnt=(ProcessDefinitionEntity)FileUtil.cloneObject(ent);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		ProcessDefinitionEntity p=getThreadLocalDef(id);
		if(p==null){
			setThreadLocalDef(cloneEnt);
		}
		p=getThreadLocalDef(id);
		
		return p;
	}

	@Override
	public void add(String id, ProcessDefinitionEntity object) {
		iCache.add(id, object);
		
	}

	@Override
	public void remove(String id) {
		iCache.delByKey(id);
		
	}

	@Override
	public void clear() {
		iCache.clearAll();
	}

}
