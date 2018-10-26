package com.hotent.core.engine;

import groovy.lang.GroovyShell;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;


/**
 * 脚本引擎用于执行groovy脚本。
 * <pre>
 * 脚本引擎在web容器启动的时候，spring将当前项目中的所有的service类和实现了IScript接口的类可以并注册到spring容器中管理的类。
 * 注册到脚本引擎中，在编写脚本时可以直接使用。
 * 1.service类。
 *  service的使用方法，我们采用spring注释方式管理service，使用service的方法是：
 *  如BpmService，那么在脚本中使用该service的是否只需 将BpmService的首字母小写就可以使用了。
 *  bpmService.getActiveTasks("任务ID"); 这样就可以了。
 * 
 * 2.实现IScript。
 *  在spring注册类。
 *  &lt;bean id="scriptImpl" class="com.hotent.platform.service.bpm.ScriptImpl">&lt;/bean>
 *  使用类的方法如下：
 *  获取当前用户：
 *  scriptImpl.getCurrentUser() ，返回当前用户的ID。
 *  
 *  
 * </pre>
 * @author ray
 * 
 */
public class GroovyScriptEngine implements BeanPostProcessor {

	private Log logger = LogFactory.getLog(GroovyScriptEngine.class);
	private GroovyBinding binding = new GroovyBinding();
	
	
	



	/**
	 * 执行groovy代码无返回。
	 * 
	 * @param script
	 * @throws Exception 
	 */
	public void execute(String script,Map<String, Object> vars)  {
		executeObject(script,vars);
		
	}

	/**
	 * 设置执行参数。
	 * @param shell
	 * @param vars
	 */
	private void setParameters(GroovyShell shell, Map<String, Object> vars) {
		if(vars==null) return;
		Set<Map.Entry<String, Object>> set = vars.entrySet();
		for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			shell.setVariable(entry.getKey(), entry.getValue());
			//shell.setProperty(property, newValue)
		}
	}
	


	/**
	 * 执行groovy代码返回布尔型。
	 * 
	 * @param script
	 * @return
	 * @throws Exception 
	 */
	public boolean executeBoolean(String script,Map<String, Object> vars) {
		
		Boolean rtn = (Boolean)  executeObject(script,vars);
		return rtn;
	}

	/**
	 * 执行脚本返回字符串数据。
	 * 
	 * @param script
	 * @return
	 * @throws Exception 
	 */
	public String executeString(String script,Map<String, Object> vars) {
		String str = (String) executeObject(script,vars);
		return str;
	}

	/**
	 * 执行脚本返回整形数据。
	 * 
	 * @param script
	 * @return
	 * @throws Exception 
	 */
	public int executeInt(String script,Map<String, Object> vars)  {
		
		Integer rtn = (Integer) executeObject(script,vars);
		return rtn;
	}

	/**
	 * 执行脚本返回浮点型数据。
	 * 
	 * @param script
	 * @return
	 * @throws Exception 
	 */
	public float executeFloat(String script,Map<String, Object> vars) {
		 
		Float rtn =(Float) executeObject(script,vars);
		return rtn;
	}
	
	

	/**
	 * 执行脚本返回对象数据。
	 * 
	 * @param script
	 * @return
	 * @throws Exception 
	 */
	public Object executeObject(String script,Map<String, Object> vars) {
	
		
		logger.debug("执行:" + script);
		GroovyShell shell = new GroovyShell(binding);
		setParameters(shell,vars);
		
		script=script.replace("&apos;", "'")
		.replace("&quot;", "\"")
		.replace("&gt;", ">")
		.replace("&lt;", "<")
		.replace("&nuot;", "\n")
		.replace("&amp;", "&");
		
		Object rtn =  shell.evaluate(script);
		binding.clearVariables();
		return rtn;
		
		
	}


	/**
	 * 获取出错消息
	 * @return
	 */
//	public String getErrorMessage(){
//		if(errors==null) return "";
//		String tmp="";
//		Set<Map.Entry<String, String>> set = errors.entrySet();
//		for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
//			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
//			tmp+=entry.getValue() +"#";
//		}
//		return tmp;
//	}
	
	/**
	 * 判断是否有错误。
	 * @return
	 */
//	public boolean hasErrors()
//	{
//		if(errors!=null )
//			return errors.size()>0;
//		return false;
//	}
	
	public Object getVariable(String key){
		return binding.getVariable(key);
	}

	/**
	 * 给groovy脚本引擎注入 service对象引用和实现IScript对象的引用。
	 */
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		boolean rtn = BeanUtils.isInherit(bean.getClass(), BaseService.class);
		boolean isImplScript = BeanUtils.isInherit(bean.getClass(),IScript.class);
		if (rtn || isImplScript) {
			binding.setProperty(beanName, bean);
			
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

}
