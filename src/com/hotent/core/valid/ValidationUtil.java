package com.hotent.core.valid;

import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;


import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.ValidatorResources;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springmodules.validation.commons.ValidatorFactory;


import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ResourceUtil;
import com.hotent.core.util.StringUtil;

import freemarker.template.TemplateException;


/**
 * 验证工具类。
 * @author hotent
 *
 */
public class ValidationUtil {
	
	
	private static Map<String, ValidEnum> map=new HashMap<String, ValidEnum>();
	static {
		 
		 EnumSet<ValidEnum> stateSet = EnumSet.allOf(ValidEnum.class);      
			for (ValidEnum s : stateSet) {      
				map.put(s.name(), s); 
			}  

	}
	
	
	
	
	/**
	 * 根据form的名称取得 ValidForm对象。
	 * @param formName
	 * @param local
	 * @return
	 */
	private static ValidForm getForm(String formName,Locale local)
	{
		ValidForm form=new ValidForm();
		form.setFormName(formName);
		ApplicationContext ctx= AppUtil.getContext();
		ValidatorFactory factory = (ValidatorFactory)BeanFactoryUtils.beanOfTypeIncludingAncestors(ctx, ValidatorFactory.class, true, true);
		ValidatorResources resources= factory.getValidatorResources();
		Form frm= resources.getForm(local, formName);
		if(frm==null) return null;
 		List<Field> list= frm.getFields();
		for(Field fld:list)
		{
			Arg arg=fld.getArg(0);
			String displayName=ResourceUtil.getText(arg.getKey(),null, local);
			ValidField vFld=new ValidField();
			vFld.setDisplayName(displayName);
			vFld.setFormName(fld.getProperty());
			getRuleByField(fld,vFld,local);
			form.addField(vFld);
		}
		return form;
	}
	
	/**
	 * 根据字段取得验证规则。
	 * @param field
	 * @param vFld
	 * @param local
	 */
	private static  void getRuleByField(Field field,ValidField vFld,Locale local)
	{
		List<String> list=field.getDependencyList();
		for(String str:list)
		{
			Rule rule=new Rule();
			rule.setName(str);
			
			vFld.addRule(rule);
			handRule(field,rule, local);
		}
	}
	
	/**
	 * 对规则分别处理。
	 * @param field
	 * @param rule
	 * @param local
	 */
	private static void handRule(Field field,Rule rule,Locale local)
	{
		Arg arg=field.getArg(0);
		String key=arg.getKey();
		String displayName=ResourceUtil.getText(key,null, local);
		String ruleName=rule.getName();
		ValidEnum e= map.get(ruleName);

		String tipInfo="";
		switch(e)
		{
			//必填
			case required:
				
				tipInfo=ResourceUtil.getText("必填",displayName, local);
				String value=field.getVarValue(ruleName);
				if(value!=null){
					rule.setRuleInfo(value);
				}
				else{
					rule.setRuleInfo("true");
				}
				rule.setTipInfo(tipInfo);
				break;
			//信用卡
			case creditcard:
				tipInfo=ResourceUtil.getText("信用卡验证失败",null, local);
				rule.setRuleInfo("true");
				break;
			//日期
			case date:
				String datePattern=field.getVarValue("datePattern");
				Object[] aryObjDate={displayName,datePattern};
				tipInfo=ResourceUtil.getText("errors.date",aryObjDate, local);
				rule.setRuleInfo("true");
				break;
			//等于
			case equalTo:
				Arg argE=field.getArg(1);
				String keyE=argE.getKey();
				String equalName=ResourceUtil.getText(keyE,null, local);
				
				String equalTo=field.getVarValue("equalTo");
				
				if(StringUtil.isEmpty(equalName)) equalName="";
				rule.setRuleInfo(equalTo);
				Object[] aryEqual={displayName,equalName};
				tipInfo=ResourceUtil.getText("不相等",aryEqual, local);
				
				break;
			//整数
			case digits:
				tipInfo=ResourceUtil.getText("请输入整数",displayName, local);
				rule.setRuleInfo("true");
				break;
			//邮件验证
			case email:
				tipInfo=ResourceUtil.getText("请输入正确的邮箱",displayName, local);
				rule.setRuleInfo("true");
				break;
			//最大值
			case max:
				String max=field.getVarValue("max").replace(",", "");
				Object[] aryObj={displayName,max};
				tipInfo=ResourceUtil.getText("超出最大值",aryObj, local);
				rule.setRuleInfo(max);
				break;
			//最大长度
			case maxlength:
				String maxlength=field.getVarValue("maxlength").replace(",", "");
				Object[] aryMaxlength={displayName,maxlength};
				tipInfo=ResourceUtil.getText("内容过长",aryMaxlength, local);
				rule.setRuleInfo(maxlength);
				break;
			//最小值
			case min:
				String min=field.getVarValue("min");
				Object[] aryMin={displayName,min};
				tipInfo=ResourceUtil.getText("值过小",aryMin, local);
				rule.setRuleInfo(min);
				break;
			//最小长度
			case minlength:
				String minlength=field.getVarValue("minlength");
				Object[] aryMinlength={displayName,minlength};
				tipInfo=ResourceUtil.getText("内容过短",aryMinlength, local);
				rule.setRuleInfo(minlength);
				break;
			//数字
			case number:
				tipInfo=ResourceUtil.getText("请输入数字",displayName, local);
				rule.setRuleInfo("true");
				break;
			//数值范围
			case range:
				String rmin = field.getVarValue("min");
				String rmax = field.getVarValue("max").replace(",", "");;
				Object[] aryRange={displayName,rmin,rmax};
				tipInfo=ResourceUtil.getText("不在数值范围内",aryRange, local);
				String ruleInfo="[" + rmin +"," + rmax +"]";
				rule.setRuleInfo(ruleInfo);
				break;
			//长度范围
			case rangelength:
				String rminlength = field.getVarValue("minlength");
				String rmaxlength = field.getVarValue("maxlength").replace(",", "");;
				Object[] aryRangeLength={displayName,rminlength,rmaxlength};
				tipInfo=ResourceUtil.getText("不在长度范围内",aryRangeLength, local);
				rule.setRuleInfo("[" + rminlength +"," + rmaxlength +"]");
				break;
			//正则表达式
			case regex:
				String regex=field.getVarValue("regex");
				tipInfo=ResourceUtil.getText("请输入正确正则表达式",displayName, local);
				rule.setRuleInfo(regex);
				break;
			//url认证
			case url:
				tipInfo=ResourceUtil.getText("请输入正确的UEL",null, local);
				rule.setRuleInfo("true");
				break;
			case mobile:
				tipInfo=ResourceUtil.getText("请输入正确的手机号码",null, local);
				rule.setRuleInfo("true");
				break;
			case phone:
				tipInfo=ResourceUtil.getText("请输入正确的电话号码",null, local);
				rule.setRuleInfo("true");
				break;
			case zip:
				tipInfo=ResourceUtil.getText("请选择zip包",null, local);
				rule.setRuleInfo("true");
				break;
			case qq:
				tipInfo=ResourceUtil.getText("请输入正确的QQ号",null, local);
				rule.setRuleInfo("true");
				break;
			case chinese:
				tipInfo=ResourceUtil.getText("请输入中文",displayName, local);
				rule.setRuleInfo("true");
				break;
			case chrnum:
				tipInfo=ResourceUtil.getText("只能输入数字和字母",displayName, local);
				rule.setRuleInfo("true");
				break;
			case ip:
				tipInfo=ResourceUtil.getText("请输入正确的IP地址",null, local);
				rule.setRuleInfo("true");
				break;
			// 比较时间大小
			case compStartEndTime:
				argE=field.getArg(1);
				keyE=argE.getKey();
				String eTime=ResourceUtil.getText(keyE,null, local);
				String varValue=field.getVarValue("compStartEndTime");
				
				rule.setRuleInfo(varValue);
				Object[] aryEquals={displayName,eTime};
				tipInfo=ResourceUtil.getText("时间不符合逻辑",aryEquals, local);
				break;
			case digitsSum:
				String digitsSumLen=field.getVarValue("digitsSum").replace(",", "");
				Object[] aryDigitsSum={displayName,digitsSumLen};
				tipInfo=ResourceUtil.getText("errors.digitsSum",aryDigitsSum, local);
				rule.setRuleInfo(digitsSumLen);
				break;
		
		}
		rule.setTipInfo(tipInfo);
	}
	
	/**
	 * 调用模版引擎生成验证的JS。
	 * @param roleForm
	 * @param local
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String getJs(String roleForm,Locale local) throws IOException, TemplateException
	{
		FreemarkEngine  freemaker=(FreemarkEngine)AppUtil.getBean(FreemarkEngine.class);
		//获取验证表单对象。
		ValidForm form= getForm(roleForm, local);
		
		Map map=new HashMap();
		map.put("form", form);
		String str=freemaker.mergeTemplateIntoString("validJs.ftl", map);
		return str;
	}

	
	
	
}
