/**
 * 描述：TODO
 * 包名：com.hotent.base.core.util
 * 文件名：JSONObjectUtil.java
 * 作者：User-mailto:chensx@jee-soft.cn
 * 日期2014-7-29-下午3:57:22
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.core.util.jsonobject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.jsonobject.support.BooleanSerializer;
import com.hotent.core.util.jsonobject.support.DateSerializer;
import com.hotent.core.util.jsonobject.support.JsonObjectSerializer;
import com.hotent.core.util.jsonobject.support.SuperclassExclusionStrategy;
import com.hotent.core.web.util.RequestUtil;
  
/**
 * <pre> 
 * 描述：TODO
 * 构建组：x5-base-core
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-7-29-下午3:57:22
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class JSONObjectUtil {
	/**
	 * @param jsonObject
	 * @param cls
	 * @return 
	 * C
	 * @exception 
	 * @since  1.0.0
	 */
	public static <C>  C toBean(JSONObject jsonObject,Class<C> cls){
		return toBean(jsonObject.toString(), cls);
	}
	
	/**
	 * 把jsonStr生成一个cls对象
	 * @param jsonObject
	 * @param cls
	 * @return 
	 * C
	 * @exception 
	 * @since  1.0.0
	 */
	public static <C>  C toBean(String  jsonStr,Class<C> cls){
		GsonBuilder gsonBuilder = new GsonBuilder();  
        gsonBuilder.registerTypeAdapter(String.class, new JsonObjectSerializer())
        		   .registerTypeAdapter(Date.class, new DateSerializer())
        		   .registerTypeAdapter(Boolean.class, new BooleanSerializer())
        		   .addDeserializationExclusionStrategy(new SuperclassExclusionStrategy())
        		   .addSerializationExclusionStrategy(new SuperclassExclusionStrategy());
        Gson gson = gsonBuilder.create();
		C o = (C)gson.fromJson(jsonStr,cls);
		return o;
	}
	
	/**
	 * 模仿requestUitl.java的同名方法
	 * 将参数放到map中。<br>
	 * 
	 * <pre>
	 * 1.如果是需要分页的情况，参数需要做如下配置：
	 * Q_参数名称_参数类型
	 * 
	 * 可以使用的类型如下：
	 * S：字符串
	 * L：长整型
	 * N：整形
	 * DB:double
	 * BD：decimal
	 * FT:浮点型数据
	 * SN：short数据
	 * DL：开始时间
	 * DG:结束时间
	 * SL:字符串 模糊查询
	 * SLL:字符串 左模糊查询
	 * SLR:字符串 右模糊查询
	 * 2.如果参数不需要分页的情况可以不用按照上面的方式传递参数。
	 * 
	 * </pre>
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String,Object> getQueryMap(JSONObject jsonObject) {
		Map map = new HashMap();
		
		for(Object obj:jsonObject.keySet()){
			String key=obj.toString();
			String[] values= new String[1];
			values[0]=jsonObject.getString(key);
			
			if (key.equals("sortField") || key.equals("orderSeq")
					|| key.equals("sortColumns")) {
				String val = values[0].trim();
				if (StringUtil.isNotEmpty(val)) {
					map.put(key, values[0].trim());
				}
			}
			if (values.length > 0 && StringUtils.isNotEmpty(values[0])) {
				if (key.startsWith("Q_")) {
					RequestUtil.addParameter(key, values, map);
				} else {
					if (values.length == 1) {
						String val = values[0].trim();
						if (StringUtil.isNotEmpty(val)) {
							map.put(key, values[0].trim());
						}
					} else {
						map.put(key, values);
					}
				}
			}
		}
		
		return map;
	}
	
	public static void main(String[] args) {
	}
}
