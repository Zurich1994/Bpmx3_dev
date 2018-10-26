package com.hotent.core.aop;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;


import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestContext;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysAudit;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysLogSwitch;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysAuditLinkService;
import com.hotent.platform.service.system.SysAuditService;
import com.hotent.platform.service.system.SysLogSwitchService;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

/**
 * 
 * 使用AOP拦截Controller的方式记录日志。<br>
 * 如果控制器方法需要被拦截，请在方法之前添加注解  {@link Action Action},这样才能记录日志。
 * 
 * 
 * @author csx
 * 
 */
// @Aspect
public class LogAspect
{
	private Log logger = LogFactory.getLog(LogAspect.class);
	@Resource
	private SysAuditService sysAuditService;
	@Resource 
	private SysLogSwitchService sysLogSwitchService;
	@Resource
	public SysAuditLinkService SysAuditLinkService;
	@Resource
	private FreemarkEngine freemarkEngine;
	
	
	
	private  static WorkQueue wq = new WorkQueue(10);
	
	private static boolean isCommonServicesInited = false;
	private static Map<String,Object> commonServices = new HashMap<String, Object>();
	//添加FreeMarker可访问的类静态方法的字段
	static Map<String,TemplateHashModel> STATIC_CLASSES = new HashMap<String, TemplateHashModel>();
	static{
		try {
			BeansWrapper beansWrapper = BeansWrapper.getDefaultInstance();
			TemplateHashModel staticModel = beansWrapper.getStaticModels();
			STATIC_CLASSES.put(Long.class.getSimpleName(), (TemplateHashModel) staticModel.get(java.lang.Long.class.getName()));
			STATIC_CLASSES.put(Integer.class.getSimpleName(), (TemplateHashModel) staticModel.get(java.lang.Integer.class.getName()));
			STATIC_CLASSES.put(java.lang.String.class.getSimpleName(), (TemplateHashModel) staticModel.get(java.lang.String.class.getName()));
			STATIC_CLASSES.put(Short.class.getSimpleName(), (TemplateHashModel) staticModel.get(java.lang.Short.class.getName()));
			STATIC_CLASSES.put(Boolean.class.getSimpleName(), (TemplateHashModel) staticModel.get(java.lang.Boolean.class.getName()));
			STATIC_CLASSES.put(StringUtil.class.getSimpleName(),(TemplateHashModel) staticModel.get(com.hotent.core.util.StringUtil.class.getName()));
			STATIC_CLASSES.put(StringUtils.class.getSimpleName(),(TemplateHashModel) staticModel.get(StringUtils.class.getName()));
		} catch (TemplateModelException e) {
			e.printStackTrace();
		} 
	}
	// @Around("execution(* com.hotent.platform.controller..*.*(..))")
	public Object doAudit(ProceedingJoinPoint point) throws Throwable
	{
		Object returnVal=null;
		
		String methodName = point.getSignature().getName();
		//类
		Class<?> targetClass = point.getTarget().getClass();
		//方法
		Method[] methods = targetClass.getMethods();
		Method method = null;
		for (int i = 0; i < methods.length; i++){
			if (methods[i].getName() == methodName){
				method = methods[i];
				break;
			}
		}
		//如果横切点不是方法，返回
		if (method == null)
			return point.proceed();
		
		//boolean hasAnnotation = method.isAnnotationPresent(Action.class);
		//方法Action
		Action annotation = method.getAnnotation(Action.class);
		//如果方法上没有注解@Action，返回
		if(annotation==null){
			return point.proceed();
		}
		

		if(ActionExecOrder.BEFORE.equals(annotation.execOrder())){
			doLog(point,false);
			returnVal = point.proceed();
		}else if(ActionExecOrder.AFTER.equals(annotation.execOrder())){
			returnVal = point.proceed();
			doLog(point,true);
		}else{
			returnVal = point.proceed();
			doLog(point,true);
		}
		return returnVal;
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void doLog(ProceedingJoinPoint point,boolean async){
		try {
			String methodName = point.getSignature().getName();
			if (StringUtils.isEmpty(methodName)){ 
				return;
			}
			//类
			Class<?> targetClass = point.getTarget().getClass();
			//类Action
			Action classAction = targetClass.getAnnotation(Action.class);
			
			//方法
			Method[] methods = targetClass.getMethods();
			Method method = null;
			for (int i = 0; i < methods.length; i++){
				if (methods[i].getName() == methodName){
					method = methods[i];
					break;
				}
			}
			//如果横切点不是方法，返回
			if (method == null)
				return ;
			
			//boolean hasAnnotation = method.isAnnotationPresent(Action.class);
			//方法Action
			Action annotation = method.getAnnotation(Action.class);
			//如果方法上没有注解@Action，返回
			if(annotation==null){
				return ;
			}
			
			//Action描述
			String methodDescp = annotation.description();
			//增加归属模块
			SysAuditModelType modelType = annotation.ownermodel();
			//日志类型
			String exectype= annotation.exectype();
			
			if(modelType==SysAuditModelType.NULL){
				if(classAction!=null){
					modelType = classAction.ownermodel();
				}
			}
			String ownermodel= modelType.toString();
			
			//日志开关
			SysLogSwitch sysLogSwitch = sysLogSwitchService.getByModel(ownermodel);
			if(sysLogSwitch==null){
				return ;
			}
			short status =sysLogSwitch.getStatus()==null?SysLogSwitch.STATUS_CLOSE:sysLogSwitch.getStatus();
		    if(status!=SysLogSwitch.STATUS_OPEN){
		    	return ;
		    }
			// 取到当前的操作用户
			SysUser curUser = ContextUtil.getCurrentUser();
			SysAudit sysAudit = new SysAudit();
			sysAudit.setAuditId(UniqueIdUtil.genId());
			sysAudit.setOpName(methodDescp);
			//增加归属模块和日志类型 alert by layer
			sysAudit.setOwnermodel(ownermodel);
			sysAudit.setExectype(exectype);
			if (curUser != null)
			{
				sysAudit.setExecutorId(curUser.getUserId());
				sysAudit.setExecutor(curUser.getFullname());
				//增加归属机构 alert by layer
				sysAudit.setOrgid(ContextUtil.getCurrentOrgId());
			}
		    //sysAudit.setExectype("");
			sysAudit.setExeTime(new Date());
			sysAudit.setExeMethod(targetClass.getName() + "." + method.getName());
			HttpServletRequest request =RequestContext .getHttpServletRequest();
			if (request != null)
			{
				String fromIp=RequestUtil.getIpAddr(request);
				sysAudit.setFromIp(fromIp);
				sysAudit.setRequestURI(request.getRequestURI());
				//sysAudit.setReqParams(RequestUtil.getRequestInfo(request).toString());
			}
			//添加明细信息
			String detail = SysAuditThreadLocalHolder.getDetail();
			
			if(async){
				LogHolder logHolder = new LogHolder();

				if(StringUtil.isEmpty(detail)){
					detail = annotation.detail();
					if(StringUtil.isNotEmpty(detail)){
						Map<String, Object> map=new HashMap<String, Object>();
						//添加Request查询参数
						if(request!=null){
							map.putAll(RequestUtil.getQueryMap(request));
						}
						//添加线程相关变量
						map.putAll(SysAuditThreadLocalHolder.getParamerters());
						//添加通用的服务类
						map.put("SysAuditLinkService", SysAuditLinkService);
						initCommonServices();
						map.putAll(commonServices);
						//添加通用静态类
						map.putAll(STATIC_CLASSES);
						
						logHolder.setParseDataModel(map);
						logHolder.setNeedParse(true);
					}
				}
				sysAudit.setDetail(detail);
				logHolder.setSysAudit(sysAudit);
				doLogAsync(logHolder);
			}else{
				if(StringUtil.isEmpty(detail)){
					detail = annotation.detail();
					if(StringUtil.isNotEmpty(detail)){
						try{
							detail = parseDetail(detail,request);
						}catch (Exception ex) {
							logger.error(ex.getMessage());
							ex.printStackTrace();
							detail=null;
						}
					}
				}
				sysAudit.setDetail(detail);
				sysAuditService.add(sysAudit);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}finally{
			SysAuditThreadLocalHolder.clearDetail();
			SysAuditThreadLocalHolder.clearParameters();
			SysAuditThreadLocalHolder.clearResult();
			SysAuditThreadLocalHolder.clearShouldLog();
		}
	}
	 
	
	private void doLogAsync(LogHolder holder){
//		WorkQueue wq = new WorkQueue(10);
		LogExecutor logExecutor = new LogExecutor();
		logExecutor.setLogHolders(holder);
		wq.execute(logExecutor);
		
//		wq.execute
//		LogExecutor logExecutor = new LogExecutor();
	}
	
	
	@SuppressWarnings("unchecked")
	private String parseDetail(String detail,HttpServletRequest request) throws TemplateException, IOException{
		Map<String, Object> map=new HashMap<String, Object>();
		
		//添加Request查询参数
		if(request!=null){
			map.putAll(RequestUtil.getQueryMap(request));
		}
		map.put("request", request);
		//添加线程相关变量
		map.putAll(SysAuditThreadLocalHolder.getParamerters());
		//添加通用的服务类
		map.put("SysAuditLinkService", SysAuditLinkService);
		initCommonServices();
		map.putAll(commonServices);
		//添加通用静态类
		map.putAll(STATIC_CLASSES);
		
		return freemarkEngine.parseByStringTemplate(map , detail);
	}

	/**
	 * 将所有继承自{@link BaseService}的类添加到commonServices中。
	 */
	private void initCommonServices(){
		if(isCommonServicesInited){
			return;
		}
		String[] beanNames = AppUtil.getContext().getBeanDefinitionNames();
		for(String beanName:beanNames){
			Object bean = AppUtil.getBean(beanName);
			if(BeanUtils.isInherit(bean.getClass(), BaseService.class)){
				commonServices.put(beanName, bean);
			}
		}
		isCommonServicesInited = true;
	}
}

/**
 * 日志信息容器
 */
class  LogHolder{
	SysAudit sysAudit;
	boolean needParse=false;
	Map<String, Object> parseDataModel;
	
	public SysAudit getSysAudit() {
		return sysAudit;
	}
	public void setSysAudit(SysAudit sysAudit) {
		this.sysAudit = sysAudit;
	}
	public boolean isNeedParse() {
		return needParse;
	}
	public void setNeedParse(boolean needParse) {
		this.needParse = needParse;
	}
	public Map<String, Object> getParseDataModel() {
		return parseDataModel;
	}
	public void setParseDataModel(Map<String, Object> parseDataModel) {
		this.parseDataModel = parseDataModel;
	}
}

/**
 * 作业队列
 */
class WorkQueue{
	private final int nThreads;
	private final PoolWorker[] threads;
	LinkedList<Runnable> queue;
	
	public WorkQueue(int nThreads){
		this.nThreads=nThreads;
		queue = new LinkedList<Runnable>();
		threads = new PoolWorker[nThreads];
		for(int i=0;i<this.nThreads;i++){
			threads[i] = new PoolWorker();
			threads[i].start();
		}
	}
	
	public void execute(Runnable r){
		synchronized (queue) {
			queue.addLast(r);
			queue.notify();
		}
	}
	
	private class PoolWorker extends Thread{
		private Log logger = LogFactory.getLog(PoolWorker.class);
		public void run(){
			Runnable r;
			while(true){
				synchronized (queue) {
					while(queue.isEmpty()){
						try {
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
							logger.error(e.getMessage());
						}
					}
					r=(Runnable)queue.removeFirst();
				}
				try{
					r.run();
				}catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}
		}
	}
}


/**
 * 执行记录日志的任务作业 
 */
class LogExecutor implements Runnable{
	private Log logger = LogFactory.getLog(LogExecutor.class);
	private LogHolder logHolder;
	private FreemarkEngine freemarkEngine;
	private SysAuditService sysAuditService;


	public void setLogHolders(LogHolder logHolder) {
		this.logHolder = logHolder;
		this.sysAuditService = (SysAuditService) AppUtil.getBean(SysAuditService.class);
		this.freemarkEngine = (FreemarkEngine) AppUtil.getBean(FreemarkEngine.class);
	}

	private void doLog() throws TemplateException, IOException{
		SysAudit sysAudit = logHolder.getSysAudit();
		if(logHolder.isNeedParse()){
			String detail = freemarkEngine.parseByStringTemplate(logHolder.getParseDataModel(),sysAudit.getDetail());
			sysAudit.setDetail(detail);
		}
		sysAuditService.add(sysAudit);
	}
	
	@Override
	public void run() {
		try {
			doLog();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
}