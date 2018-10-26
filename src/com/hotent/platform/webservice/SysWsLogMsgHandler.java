package com.hotent.platform.webservice;

import com.hotent.core.annotion.CxfFuncDesc;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.SysWsLog;
import com.hotent.platform.service.system.SysWsLogService;
import com.hotent.platform.webservice.impl.util.AddressInInterceptor;
import java.lang.reflect.Method;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


/**
 * webService 方法调用拦截器
 * @author cjj
 */
@Aspect
public class SysWsLogMsgHandler {

	@Resource
	private SysWsLogService sysWsLogService;

	@Pointcut("execution(* com.hotent.platform.webservice.api..*Service.*(..))")  
	public void intercept() {}
	
    @Around("intercept()")
    public void around(ProceedingJoinPoint pjp) throws Throwable
    {
    	SysWsLog log = new SysWsLog();
    	log.setLogId(UniqueIdUtil.genId());
    	
    	AddressInInterceptor ad = (AddressInInterceptor)AppUtil.getBean("addressInInterceptor");
        HttpServletRequest request = (HttpServletRequest) ad.getSysWsLogMsg().get(AbstractHTTPDestination.HTTP_REQUEST);
        String ipAddr=request.getRemoteAddr();
        log.setSourceMsg(ipAddr);
		ad.removeSysWsLogMsg();
    	
    	log.setCreatetime(new Date());
    	String methodFull = pjp.getSignature().toString();
    	log.setClsName(methodFull);
    	Object[] objs = pjp.getArgs();
    	Class[] cls = null;
    	if(objs!=null&&objs.length>0){
    		cls = new Class[objs.length];
    		int clsIdx = 0;
    		String param = "";
	    	for(Object obj:objs){
	    		cls[clsIdx] = obj.getClass();
	    		String clsName  = cls[clsIdx].toString();
	    		param+=clsName.substring(clsName.lastIndexOf(".")+1,clsName.length())+": "+obj.toString()+"\r\n";
		    	clsIdx++;
	    	}
	    	log.setImpDesc(param);
    	}
    	
		try {
	    	Object reVal = pjp.proceed();
			String mehodName = methodFull.substring(methodFull.lastIndexOf(".")+1,methodFull.indexOf("("));
			Class targetClass = pjp.getTarget().getClass();
			Method method = targetClass.getMethod(mehodName, cls);
			boolean hasAnnotation = method.isAnnotationPresent(CxfFuncDesc.class);
			if (hasAnnotation)
			{
				CxfFuncDesc annotation = method.getAnnotation(CxfFuncDesc.class);
				log.setCallName(annotation.callName() );
				log.setCallDesc(annotation.callDesc());
			}
			
			log.setExpDesc(reVal!=null?reVal.toString():"null");
			log.setIsSuccess(SysWsLog.SUCCESS);
			sysWsLogService.add(log);
		} catch (Exception e) {
			StackTraceElement[] st = e.getStackTrace();
			StringBuffer bf = new StringBuffer();
			for (StackTraceElement elm : st) {
				bf.append(elm.toString()).append("\r\n");
			}
    		log.setIsSuccess(SysWsLog.FAILTURE);
    		log.setErrDetail(bf.toString());
        	sysWsLogService.add(log);
		}
    }
	
}
