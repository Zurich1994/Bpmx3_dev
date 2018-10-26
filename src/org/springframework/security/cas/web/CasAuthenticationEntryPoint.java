package org.springframework.security.cas.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.util.CommonUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.Assert;

import com.hotent.core.util.StringUtil;
import com.hotent.core.web.util.RequestUtil;

public class CasAuthenticationEntryPoint
  implements AuthenticationEntryPoint, InitializingBean
{
  private ServiceProperties serviceProperties;
  private String loginUrl;

  @Deprecated
  private boolean encodeServiceUrlWithSessionId = true;

  public void afterPropertiesSet()
    throws Exception
  {
    Assert.hasLength(this.loginUrl, "loginUrl must be specified");
    Assert.notNull(this.serviceProperties, "serviceProperties must be specified");
  }

  public final void commence(HttpServletRequest servletRequest, HttpServletResponse response, AuthenticationException authenticationException)
    throws IOException, ServletException
  {
    String urlEncodedService = createServiceUrl(servletRequest, response);
    
    String rtnUrl=getUrl(servletRequest);
    HttpSession session= servletRequest.getSession();
    session.setAttribute("rtnUrl", rtnUrl);

    String redirectUrl = createRedirectUrl(urlEncodedService);

    preCommence(servletRequest, response);
    
    
    
    

    response.sendRedirect(redirectUrl);
  }
  
  public static String getUrl(HttpServletRequest request) {
		StringBuffer urlThisPage = new StringBuffer();
		urlThisPage.append(request.getRequestURL());
		Enumeration<?> e = request.getParameterNames();
		String para = "";
		String values = "";
		urlThisPage.append("?");
		while (e.hasMoreElements()) {
			para = (String) e.nextElement();
			values = request.getParameter(para);
			urlThisPage.append(para);
			urlThisPage.append("=");
			urlThisPage.append(values);
			urlThisPage.append("&");
		}
		return urlThisPage.substring(0, urlThisPage.length() - 1);
	}

  protected String createServiceUrl(HttpServletRequest request, HttpServletResponse response)
  {
    return CommonUtils.constructServiceUrl(null, response, this.serviceProperties.getService(), null, this.serviceProperties.getArtifactParameter(), this.encodeServiceUrlWithSessionId);
  }

  protected String createRedirectUrl(String serviceUrl)
  {
    return CommonUtils.constructRedirectUrl(this.loginUrl, this.serviceProperties.getServiceParameter(), serviceUrl, this.serviceProperties.isSendRenew(), false);
  }

  protected void preCommence(HttpServletRequest request, HttpServletResponse response)
  {
  }

  public final String getLoginUrl()
  {
    return this.loginUrl;
  }

  public final ServiceProperties getServiceProperties() {
    return this.serviceProperties;
  }

  public final void setLoginUrl(String loginUrl) {
    this.loginUrl = loginUrl;
  }

  public final void setServiceProperties(ServiceProperties serviceProperties) {
    this.serviceProperties = serviceProperties;
  }

  @Deprecated
  public final void setEncodeServiceUrlWithSessionId(boolean encodeServiceUrlWithSessionId)
  {
    this.encodeServiceUrlWithSessionId = encodeServiceUrlWithSessionId;
  }

  @Deprecated
  protected boolean getEncodeServiceUrlWithSessionId()
  {
    return this.encodeServiceUrlWithSessionId;
  }
}