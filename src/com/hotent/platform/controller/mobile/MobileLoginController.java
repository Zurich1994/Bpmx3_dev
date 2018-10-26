package com.hotent.platform.controller.mobile;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.hotent.core.encrypt.EncryptUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.LocaleUtil;
import com.hotent.core.web.util.CookieUitl;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.ldap.LdapUserService;
import com.hotent.platform.service.system.SysUserService;

@Controller
@RequestMapping("/mobileLogin.ht")
public class MobileLoginController extends MobileBaseController {

	@Resource
	SysUserService sysUserService;

	@Resource
	private LdapUserService ldapUserService;

	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager = null;
	@Resource
	private SessionLocaleResolver sessionLocaleResolver;

	@Resource
	private SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();

	@RequestMapping("*")
	@ResponseBody
	public Object login(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {

		Locale curLocale = LocaleUtil.getLocale("zh_CN");
		Map<String, Object> map = new HashMap<String, Object>();
		username =StringUtils.trimToEmpty(username);
		boolean error = false;
		Object[] args = {username};
		try {
			if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
				request.getSession().setAttribute(
						WebAttributes.AUTHENTICATION_EXCEPTION, getText("mobile.login.isEmpty"));
				error = true;
				return getError("mobile.login.isEmpty",curLocale);
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
					return getError("mobile.login.verifyAD",curLocale);
				}

				if (!authenticated) {
					error = true;
					return getError("mobile.login.error",curLocale);
				}
			}
			// 通过sys_user 验证。
			else {
				if (sysUser == null
						|| !encrptPassword.equals(sysUser.getPassword())) {
					error = true;
					return getError("mobile.login.error");
				} else if (BeanUtils.isNotEmpty(sysUser.getIsExpired())
						&& sysUser.getIsExpired() == 1) {
					if (BeanUtils.isNotEmpty(sysUser.getStatus())
							&& (sysUser.getStatus() == 5 || sysUser.getStatus() == 7))
						return getError("mobile.login.resignation",curLocale);
					else
						return getError("mobile.login.expired",args,curLocale);
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

			map.put("success", true);
			map.put("msg",  getText("mobile.login.success",curLocale));
			map.put("user", sysUser);

			CookieUitl.addCookie("loginAction", "mobile", request, response);

			sessionStrategy.onAuthentication(auth, request, response);

		} catch (LockedException e) {
			request.getSession().setAttribute(
					WebAttributes.AUTHENTICATION_EXCEPTION,
					username + ":用户被锁定!");
			error = true;
			//username + ":用户被锁定!
			return getError("mobile.login.lock",args);
		} catch (DisabledException e) {
			request.getSession().setAttribute(
					WebAttributes.AUTHENTICATION_EXCEPTION,
					username + ":用户被禁用!");
			error = true;
			//return getError(username + ":用户被禁用!");
			return getError("mobile.login.disabled",args);
		} catch (AccountExpiredException e) {
			request.getSession().setAttribute(
					WebAttributes.AUTHENTICATION_EXCEPTION,
					username + ":用户已过期!");
			error = true;
			return getError("mobile.login.expired",args);
		} catch (Exception e) {
			e.printStackTrace();
			return getError("mobile.login.fail");
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
		}

		return map;
	}


	private boolean ldapUserAuthentication(String userId, String password) {
		return ldapUserService.authenticate(userId, password);
	}

}
