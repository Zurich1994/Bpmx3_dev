package edu.hrbeu.MDA.DBAccess.core;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.ConfigurableBeanValidator;

/**
 * Controller基类，提供获取国际化，邮件发送等相关服务。
 * @author csx
 */
public class BaseController extends GenericController
{
	public static final String Message="message";
	

	/**
	 * 添加消息
	 * @param message
	 * @param model
	 */
	public void addMessage(ResultMessage message,HttpServletRequest request){
		HttpSession session= request.getSession();
		
		session.setAttribute(Message, message);
	}
	
	@Resource
	protected ConfigurableBeanValidator confValidator;

	/**
	 * Set up a custom property editor for converting form inputs to real
	 * objects
	 * 
	 * @param request
	 *            the current request
	 * @param binder the data binder
	 * @deprecated move to BaseFormController
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) {
		logger.debug("init binder ....");
		binder.registerCustomEditor(Integer.class, null,new CustomNumberEditor(Integer.class, null, true));
		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
		binder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
		SimpleDateFormat dateFormat = new SimpleDateFormat(StringPool.DATE_FORMAT_DATE);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
	}
	
  /**
	 * 服务端验证表单输入内容。<br>
	 * 使用方法：
	 * 
	 * @param form
	 * @param obj
	 * @param result
	 * @param request
	 * @return
	 * @deprecated move to BaseFormController
	 */
	protected  ResultMessage validForm(String form,Object obj,BindingResult result,HttpServletRequest request) {
    	ResultMessage resObj=new ResultMessage(ResultMessage.Success,"");
		confValidator.setFormName(form);
		confValidator.validate(obj, result);
		if(result.hasErrors())
		{
			resObj.setResult(ResultMessage.Fail);
			List<FieldError> list= result.getFieldErrors();
			String errMsg="";
		    for(FieldError err :list)
		    {
		    	String msg=getText(err.getDefaultMessage(),err.getArguments(), request);
		    	errMsg+=msg +"\r\n";
		    }
		    resObj.setMessage(errMsg);
		}
		return resObj;
	}

	/**
	 * 根据分类和视图ID返回视图。
	 * @param category
	 * @param id
	 * @return
	 * @deprecated  移除该方法，所以的视图查找均采用getAutoView()方法
	 */
	public ModelAndView getView(String category,String id)
	{
		String view=ConfigUtil.getVal(category, id);
		return new ModelAndView(view);
	}
}
