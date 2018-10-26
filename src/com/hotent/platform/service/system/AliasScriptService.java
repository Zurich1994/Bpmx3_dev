package com.hotent.platform.service.system;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.AliasScript;
import com.hotent.platform.dao.system.AliasScriptDao;

/**
 *<pre>
 * 对象功能:自定义别名脚本表 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-12-19 11:26:03
 *</pre>
 */
@Service
public class AliasScriptService extends BaseService<AliasScript>
{
	@Resource
	private AliasScriptDao dao;
	
	
	
	public AliasScriptService(){
	}
	
	@Override
	protected IEntityDao<AliasScript, Long> getEntityDao() 
	{
		return dao;
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
			//只要返回值为boolean的方法
			//if(!"boolean".equals(returnType)&&!"java.lang.Boolean".equals(returnType))continue;
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
	 * 检查别名是否存在
	 * @param userId
	 * @param map
	 * @return
	 */
	public AliasScript getAliasScriptByName(Long userId, Map map){		
		AliasScript as = dao.getAliasScriptByName(userId, map);
		return as;
		
	}
	
	/**
	 * 根据是否有返回值格式配置获取列表
	 * @param returnValue
	 * @return 
	 * AliasScript
	 * @exception 
	 * @since  1.0.0
	 */
	public List<AliasScript> getByReturnValue(Short returnValue){		
		return dao.getByReturnValue(returnValue);
	}
}
