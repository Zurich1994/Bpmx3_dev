package com.hotent.platform.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.eclipse.jdt.core.dom.ThisExpression;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fr.report.core.A.f;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.StringUtil;
import com.ibm.db2.jcc.am.re;

public class DataObtainTag extends BodyTagSupport {

	private static ThreadLocal<Map<String,Map<String, String>>> dataLocal=new ThreadLocal<Map<String,Map<String,String>>>();
	
	public static void cleanData(){
		dataLocal.remove();
	}
	
	/**
	 * 别名
	 */
	private String alias="";
	
	//表或视图名称
	private String dbObjName="";
	//查询条件字段
	private String queryField="";
	//查询条件
	private String fieldValue="";
	//查询条件字段类型
	private String dataType="string|number";
	
	
	/**
	 * 显示格式
	 * 输入格式：[firstName],[lastName]性别[sex]
	 * 输出格式：firstName,lastName性别sex
	 */
	private   String displayFormat="";
	
	
	
	
	
	public void setDbObjName(String dbObjName) {
		this.dbObjName = dbObjName;
	}

	public void setQueryField(String queryField) {
		this.queryField = queryField;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public void setDisplayFormat(String displayFormat) {
		this.displayFormat = displayFormat;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	private String getData(){
		//根据displayFormat解析需要查询的字段 firstName,lastName,sex
		String sql=getSql();
		JdbcTemplate template= (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		Object param=fieldValue;
		if("number".equals(dataType)){
			param=Long.parseLong(fieldValue);
		}
		Map<String, Object> map= template.queryForMap(sql,param);
		
		return getDisplay(map);
		
	}
	
	public String getResult(){
		String rtnData="";
		Map<String,Map<String, String>> map=dataLocal.get();
		if(map==null){
			String rtn=getData();
			Map<String,Map<String, String>> temp=new HashMap<String, Map<String,String>>();
			
			Map<String, String> tempMap=new HashMap<String, String>();
			tempMap.put(fieldValue, rtn);
			temp.put(alias, tempMap);
			
			dataLocal.set(temp);
			
			rtnData=rtn;
		}
		else{
			Map<String,String> tempMap= map.get(alias);
			if(tempMap==null){
				String rtn=getData();
				tempMap=new HashMap<String, String>();
				tempMap.put(fieldValue, rtn);
				map.put(alias, tempMap);
				rtnData=rtn;
			}
			else{
				if(!tempMap.containsKey(fieldValue)){
					String rtn=getData();
					tempMap.put(fieldValue, rtn);
					rtnData=rtn;
				}
				else{
					rtnData=tempMap.get(fieldValue);
				}
			}
		}
		
		return rtnData;
	}
	
	
	private  String getDisplay(Map<String, Object> map){
		//获取map 替换displayFormat
	
		List<String> queryFieldList=getQueryField();
		
		String tmp=displayFormat;
		
		for (String field : queryFieldList) {
			tmp=tmp.replace("["+ field+"]", (String) map.get(field));
		}
		return tmp;
	}
	
	private String getSql(){
		List<String> queryFieldList=getQueryField();
		String queryFieldTemp="";
		String fields="";
		for (String tempField:queryFieldList) {
			queryFieldTemp+=tempField+",";
		}
		if (StringUtil.isNotEmpty(queryFieldTemp)) {
			fields=queryFieldTemp.substring(0,queryFieldTemp.length()-1);
		}
		
		return  "select " + fields+ " from " + dbObjName + " where "+queryField+"=?";
	}
	
	private   List<String> getQueryField(){
		List<String> result=new ArrayList<String>();
		
		Pattern regex = Pattern.compile("\\[(\\w+)\\]");
		Matcher regexMatcher = regex.matcher(displayFormat);
		while (regexMatcher.find()) {
			result.add(regexMatcher.group(1));
		} 
		 
		return result;
	}
	
	
	
	
	
	
	
	public int doStartTag() throws JspTagException {
		return EVAL_BODY_BUFFERED;
	}
	
	public int doEndTag() throws JspTagException {

		try {
			String str = getResult();
			pageContext.getOut().print(str);
		} catch (Exception e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}
	
	
	public static void main(String[] args) {
		/*String[] ResultString=null;
		String subjectString="[firstName],[lastName]性别[sex]";
		Pattern regex = Pattern.compile("\\[(\\w*)\\]");
		Matcher regexMatcher = regex.matcher(subjectString);
		while (regexMatcher.find()) {
			for (int i = 1; i <= regexMatcher.groupCount(); i++) {
//				ResultString=regexMatcher.group(i);
//				System.out.println(ResultString);
			} 
		} */
	/*	displayFormat="[firstName],[lastName]性别[sex]";
	 * 
		String ResultString=getQueryField();
		System.out.println(ResultString);*/
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("firstName", "李");
		map.put("lastName", "四");
		map.put("sex", "男");
//		
//		displayFormat="[firstName],[lastName]性别[sex]";
//		System.out.println(getDisplay(map));
		String string="[firstName],[lastName]性别[sex]";
		DataObtainTag tag=new DataObtainTag();
		tag.setDisplayFormat(string);
		List<String> list= tag.getQueryField();
		
		String string2= tag.getDisplay(map);
		
		
		
		System.out.println(string2);
	}
	
	
	
}
