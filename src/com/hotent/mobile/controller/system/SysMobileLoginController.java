package com.hotent.mobile.controller.system;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jasig.cas.client.validation.AssertionImpl;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.hotent.core.encrypt.EncryptUtil;
import com.hotent.core.ldap.dao.LdapUserDao;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.LocaleUtil;
import com.hotent.core.web.util.CookieUitl;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.mobile.controller.base.BaseMobileController;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysUserService;

/**
 * 登录
 * 
 * @author zxh
 * 
 */
@Controller
@RequestMapping("/mobile/system/mobileLogin.ht")
public class SysMobileLoginController extends BaseMobileController {

	@Resource
	SysUserService sysUserService;

	@Resource
	private LdapUserDao ldapUserDao;

	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager = null;
	@Resource
	private SessionLocaleResolver sessionLocaleResolver;

	@Resource
	private SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();

	@RequestMapping("*")
	@ResponseBody
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = RequestUtil.getString(request, "username");
		String password= RequestUtil.getString(request, "password");
		String lang= RequestUtil.getString(request, "lang");
		Locale curLocale = LocaleUtil.getLocale(lang);
		// String
		// validCodeEnabled=configproperties.getProperty("validCodeEnabled");
		username = StringUtils.trimToEmpty(username);
		boolean error = false;
		String json = "";
		Object[] args = { username };
		try {
			// if(validCodeEnabled!=null && "true".equals(validCodeEnabled)){
			// String
			// validCode=(String)request.getSession().getAttribute(ValidCode.SessionName_Randcode);
			// String code=request.getParameter("validCode");
			// if(validCode==null || StringUtils.isEmpty(code) ||
			// !validCode.equals(code)){
			// request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			// "验证码不正确！");
			// error=true;
			// return map();
			// }
			// }
			if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
				request.getSession().setAttribute(
						WebAttributes.AUTHENTICATION_EXCEPTION,
						getText("mobile.login.isEmpty"));
				error = true;
				json = getCallbackData(request,
						getError("mobile.login.isEmpty", curLocale));
				return;
			}

			SysUser sysUser = sysUserService.getByAccount(username);
			String encrptPassword = EncryptUtil.encryptSha256(password);
			// ad 用户登录
			if (sysUser != null && sysUser.getFromType() == 1) {
				boolean authenticated = false;
				try {
					authenticated = ldapUserAuthentication(username, password);
				} catch (Exception e) {
					e.printStackTrace();
					json = getCallbackData(request,
							getError("mobile.login.verifyAD", curLocale));
					return;
				}

				if (!authenticated) {
					error = true;
					json = getCallbackData(request,
							getError("mobile.login.error", curLocale));
					return;
				}
			}
			// 通过sys_user 验证。
			else {
				if (sysUser == null
						|| !encrptPassword.equals(sysUser.getPassword())) {
					error = true;
					json = getCallbackData(request,
							getError("mobile.login.error", curLocale));
					return;
				} else if (BeanUtils.isNotEmpty(sysUser.getIsExpired())
						&& sysUser.getIsExpired() == 1) {
					if (BeanUtils.isNotEmpty(sysUser.getStatus())
							&& (sysUser.getStatus() == 5 || sysUser.getStatus() == 7)) {
						json = getCallbackData(request,
								getError("mobile.login.resignation", curLocale));
						return;
					} else {
						json = getCallbackData(
								request,
								getError("mobile.login.expired", args,
										curLocale));
						return;
					}
				}
			}

			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					username, password);
			authRequest.setDetails(new WebAuthenticationDetails(request));
			SecurityContext securityContext = SecurityContextHolder
					.getContext();
			Authentication auth = authenticationManager
					.authenticate(authRequest);

			// 设置多语言支持
			sessionLocaleResolver.setLocale(request, response, curLocale);

			/**
			 * 增加cas令牌验证
			 */
			HttpSession session = request.getSession(true);
			session.setAttribute("_const_cas_assertion_", new AssertionImpl(""));

			securityContext.setAuthentication(auth);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("msg", getText("mobile.login.success", curLocale));
			map.put("user", sysUser);

			json = this.getCallbackData(request,  this.getSuccess(map));

			CookieUitl.addCookie("loginAction", "mobile", request, response);

			sessionStrategy.onAuthentication(auth, request, response);

		} catch (LockedException e) {
			request.getSession().setAttribute(
					WebAttributes.AUTHENTICATION_EXCEPTION,
					username + ":用户被锁定!");
			error = true;
			// username + ":用户被锁定!
			json = getCallbackData(request,
					getError("mobile.login.lock", args, curLocale));
			return;
		} catch (DisabledException e) {
			request.getSession().setAttribute(
					WebAttributes.AUTHENTICATION_EXCEPTION,
					username + ":用户被禁用!");
			error = true;
			// return getError(username + ":用户被禁用!");
			json = getCallbackData(request,
					getError("mobile.login.disabled", args, curLocale));
			return;
		} catch (AccountExpiredException e) {
			request.getSession().setAttribute(
					WebAttributes.AUTHENTICATION_EXCEPTION,
					username + ":用户已过期!");
			error = true;
			json = getCallbackData(request,
					getError("mobile.login.disabled", args, curLocale));
			return;
		} catch (Exception e) {
			e.printStackTrace();
			json = getCallbackData(request,
					getError("mobile.login.fail", args, curLocale));
			return;
		} finally {
			if (error == true) {
				// javax.servlet.http.HttpSession session=request.getSession();
				// Integer tryCount=(Integer)
				// session.getAttribute(TRY_MAX_COUNT);
				// if(tryCount==null){
				// session.setAttribute(TRY_MAX_COUNT, 1);
				// }else{
				// if(tryCount>maxTryCount-1){
				// //锁定账户 TODO
				// //session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
				// new
				// MaxTryLoginException("超过最大登录尝试次数"+maxTryCount+",用户已被锁定"));
				// }
				// session.setAttribute("tryMaxCount", tryCount+1);
				// }
			}

			returnData(response, json);

		}
	}
	

	/**
	 * 判断域是否正确
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	private boolean ldapUserAuthentication(String userId, String password) {
		return ldapUserDao.authenticate(userId, password);
	}

}
