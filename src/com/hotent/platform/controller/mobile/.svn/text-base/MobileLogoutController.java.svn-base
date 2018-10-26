package com.hotent.platform.controller.mobile;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.hotent.core.util.ContextUtil;

/**
 * 退出
 * 
 * @author zxh
 * 
 */
@Controller
@RequestMapping("/mobileLogout.ht")
public class MobileLogoutController extends MobileBaseController {

	@Resource
	private SessionLocaleResolver sessionLocaleResolver;

	/**
	 * 新增加的退出方法
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */

	@RequestMapping("*")
	@ResponseBody
	public Object logout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		try {
			String lang =  ContextUtil.getLocale().toString().toLowerCase();
			Locale curLocale = new Locale(lang);
			HttpSession session = request.getSession();
			session.removeAttribute("_const_cas_assertion_");
			session.setAttribute("_is_logout_", "1");
			session.invalidate();
			sessionLocaleResolver.setLocale(request, response, curLocale);
			return getSuccess("");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return getError("");
		}

	}
}
