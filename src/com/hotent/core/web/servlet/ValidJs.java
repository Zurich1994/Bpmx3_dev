package com.hotent.core.web.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotent.core.util.StringUtil;
import com.hotent.core.valid.ValidationUtil;
import com.hotent.core.web.util.RequestUtil;

import freemarker.template.TemplateException;

/**
 * 功能:根据表单名称获取客户端表单认证的JS代码。<br>
 * 传入参数为form，代表表单的名称。
 * <pre>
 * 使用方法如下：
 * 1.在web.xml 中配置。
 * 	&lt;servlet>
 * 		&lt;servlet-name>ValidJS&lt;/servlet-name>
 * 		&lt;servlet-class>com.hotent.core.web.servlet.ValidJs&lt;/servlet-class>
 * 	&lt;/servlet>
 * 2.在页面中使用如下。
 * 
 *&lt;script type="text/javascript" src="${ctx}/js/jquery/jquery.js">&lt;/script>
 *&lt;script type="text/javascript" src="${ctx}/js/jquery/jquery.form.js">&lt;/script>
 *&lt;script type="text/javascript" src="${ctx}/js/jquery/jquery.validate.min.js">&lt;/script>
 *&lt;script type="text/javascript" src="${ctx}/js/jquery/additional-methods.min.js">&lt;/script>
 *&lt;script type="text/javascript" src="${ctx}/js/jquery/jquery.validate.ext.js">&lt;/script>
 *&lt;script type="text/javascript" src="${ctx}/servlet/ValidJs?form=roleForm">&lt;/script>
 *</pre>
 */
public class ValidJs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ValidJs() {
        super();
      
    }

	/**
	 * 根据表单名称获取客户段认证的JS代码。
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/javascript;charset=utf-8");
		String form=RequestUtil.getString(request, "form");
		Locale local= RequestUtil.getLocal(request);
		String str="";
		if(StringUtil.isNotEmpty(form))
		{
			try {
				str=ValidationUtil.getJs(form, local);
			} catch (TemplateException e) {
				str= "";
			}
		}
		response.getWriter().print(str);
			
	}

}
