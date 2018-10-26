package com.hotent.mobile.controller.system;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.hotent.core.annotion.Action;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.mobile.controller.base.BaseMobileController;

@Controller
@RequestMapping("/mobile/system/lang/")
public class SysMobileLangController extends BaseMobileController{
	@Resource
	private SessionLocaleResolver sessionLocaleResolver;
	/**
	 * 切换语言
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("changLang")
	@Action(description = "切换语言")
	@ResponseBody
	public void changLang(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String lang = RequestUtil.getString(request, "lang", "zh_cn");
		Locale curLocale = new Locale(lang);
		//设置多语言支持
		sessionLocaleResolver.setLocale(request, response, curLocale);

		this.returnCallbackSuccessData(request, response, "");

	}
}
