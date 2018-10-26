package com.hotent.mobile.controller.system;

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
import com.hotent.mobile.controller.base.BaseMobileController;

/**
 * 退出
 * 
 * @author zxh
 * 
 */
@Controller
@RequestMapping("/mobile/system/mobileLogout.ht")
public class SysMobileLogoutController extends BaseMobileController {

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
	public void logout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String lang =  ContextUtil.getLocale().toString().toLowerCase();
			Locale curLocale = new Locale(lang);
			HttpSession session = request.getSession();
			session.removeAttribute("_const_cas_assertion_");
			session.setAttribute("_is_logout_", "1");
			session.invalidate();
			sessionLocaleResolver.setLocale(request, response, curLocale);
			this.returnCallbackSuccessData(request, response, "");
		} catch (Exception e) {
			e.printStackTrace();
			this.returnCallbackErrorData(request, response, "错误");
		}

	}
}
