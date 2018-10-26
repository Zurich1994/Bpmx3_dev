package com.hotent.platform.service.system;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.dao.system.PersonScriptDao;
import com.hotent.platform.model.system.PersonScript;

/**
 *<pre>
 * 对象功能:系统条件脚本 Service类
 * 开发公司:hotent
 * 开发人员:heyifan
 * 创建时间:2013-04-05 11:34:56
 *</pre>
 */
@Service
public class PersonScriptService extends BaseService<PersonScript>
{
	@Resource
	private PersonScriptDao dao;
	@Resource
	private GroovyScriptEngine groovyScriptEngine;
	
	public PersonScriptService()
	{
	}
	
	@Override
	protected IEntityDao<PersonScript, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 获取所有有效的条件脚本
	 * @return
	 */
	public List<PersonScript> getPersonScript(){
		return dao.getPersonScript();
	}
	
	/**
	 * 通过类名获取类的所有方法
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public JSONArray getMethodsByName(String name) throws Exception{
		JSONArray jarray = new JSONArray(); 
		Class<?> t = Class.forName(name);
		Method[] methods = t.getDeclaredMethods();
		for(Method method : methods){
			String returnType = method.getReturnType().getCanonicalName();
			//只要返回值为Set<String>的方法
			if(!"Set<String>".equals(returnType)&&!"java.util.Set".equals(returnType))continue;
			Integer modifirer = method.getModifiers();
			//只要public方法
			if(modifirer!=1)continue;
			JSONObject jobMethod = new JSONObject();
			JSONArray jaryPara = new JSONArray();
			Class<?>[] paraArr = method.getParameterTypes();
			for(int i=0;i<paraArr.length;i++){
				Class<?> para = paraArr[i];
				String paraName = "arg" + i;
				String paraType = para.getCanonicalName();
				jaryPara.add(new JSONObject().accumulate("paraName", paraName)
											 .accumulate("paraType", paraType)
											 .accumulate("paraDesc", ""));
			}
			jobMethod.accumulate("returnType", returnType)
					 .accumulate("methodName", method.getName())
					 .accumulate("para", jaryPara);
			jarray.add(jobMethod);
		}
		return jarray;
	}
	
	/**
	 * 验证脚本是否有效
	 * @throws Exception
	 */
	public String validScriptIsEnable() throws Exception{
		String message = "";
		Boolean hasUnEnable = false;
		List<PersonScript> list = dao.getAll();
		if(BeanUtils.isEmpty(list)){
			message = "没有脚本记录。";
		}
		else{
			Class<?> t = null;
			for(PersonScript personScript : list){
				try{
					t = Class.forName(personScript.getClassName());
					String paraStr = personScript.getArgument();
					JSONArray jarry = JSONArray.fromObject(paraStr);
					Integer size = jarry.size();
					Class<?>[] paras = new Class<?>[size];
					for(int i=0;i<size;i++){
						JSONObject jobject = jarry.getJSONObject(i);
						paras[i] = Class.forName(jobject.getString("paraType"));
					}
					Method method = t.getMethod(personScript.getMethodName(), paras);
					String returnTypeStr = method.getReturnType().getCanonicalName();
					if(!"Set<String>".equals(returnTypeStr)&&!"java.util.Set".equals(returnTypeStr))
						throw new Exception("返回值不是Set<String>类型");
					if(method.getModifiers()!=1)
						throw new Exception("方法不是public类型");
				}
				catch(Exception ex){
					hasUnEnable = true;
					personScript.setEnable(1L);
					dao.update(personScript);
				}
			}
			if(hasUnEnable)
				message = "验证完成，无效的脚本已被设置为无效。";
			else
				message = "验证完成，未发现无效脚本";
		}
		return message;
	}
	
	
	public String execMethod(Long id,Object[] args){
		PersonScript personScript = getById(id);
		if(personScript==null){
			return "";
		}
		
		String classInstName = personScript.getClassInsName();
		String methodName = personScript.getMethodName();
		String argStr = "";
		Map<String,Object> map = new HashMap<String, Object>();
		for(int i=0;i<args.length;i++){
			String argName = "_arg_"+i;
			map.put(argName, args[i]);
			argStr+=argName+",";
			
		}
		argStr = argStr.substring(0,argStr.length()-1);
		String script = classInstName+"."+methodName+"("+argStr+");";
		return groovyScriptEngine.executeString(script, map);
		
//		return Void.TYPE;
	}
}
