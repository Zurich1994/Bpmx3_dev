package com.hotent.core.web.controller;

 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springmodules.validation.commons.ConfigurableBeanValidator;

import com.hotent.core.json.SmartDateEditor;
import com.hotent.core.util.StringPool;
import com.hotent.core.web.ResultMessage;

/**
 * 表单的操作类基础工具类。
 * @author csx
 *
 */
public class BaseFormController extends GenericController
{
	public Logger logger=LoggerFactory.getLogger(BaseFormController.class);
	
	@Resource
	protected ConfigurableBeanValidator confValidator;

	/**
	 * Set up a custom property editor for converting form inputs to real
	 * objects
	 * 
	 * @param request
	 *            the current request
	 * @param binder
	 *            the data binder
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) {
		logger.debug("init binder ....");
		binder.registerCustomEditor(Integer.class, null,new CustomNumberEditor(Integer.class, null, true));
		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
		binder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
		SimpleDateFormat dateFormat = new SimpleDateFormat(StringPool.DATE_FORMAT_DATE);
		dateFormat.setLenient(false);
	//	binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
	binder.registerCustomEditor(Date.class, new SmartDateEditor());
	}
	
  /**
	 * 服务端验证表单输入内容。<br>
	 * <pre>
	 * 使用方法：
	 * ResultMessage msg;
	 * msg= validForm ("表单名","被验证对象",BindingResult ,request);
	 * </pre>
	 * @param form	表单名称
	 * @param obj	验证的对象
	 * @param result	验证后将结果放到结果对象。
	 * @param request	
	 * @return
	 */
	protected  ResultMessage validForm(String form,Object obj,BindingResult result,HttpServletRequest request) {
    	ResultMessage resObj=new ResultMessage(ResultMessage.Success,"");
//		confValidator.setFormName(form);
//		confValidator.validate(obj, result);
//		if(result.hasErrors())
//		{
//			resObj.setResult(ResultMessage.Fail);
//			List<FieldError> list= result.getFieldErrors();
//			String errMsg="";
//		    for(FieldError err :list)
//		    {
//		    	String msg=getText(err.getDefaultMessage(),err.getArguments(), request);
//		    	errMsg+=msg +"\r\n";
//		    }
//		    resObj.setMessage(errMsg);
//		}
		return resObj;
	}
	
	
}
