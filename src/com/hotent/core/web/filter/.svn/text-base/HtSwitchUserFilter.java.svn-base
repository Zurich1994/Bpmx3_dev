package com.hotent.core.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.util.CookieUitl;

/**
 * 继承 SwitchUserFilter 并重写 doFilter
 * by cjj
 */
public class HtSwitchUserFilter extends  SwitchUserFilter{
	
	public static final String SwitchAccount="origSwitch";
	
	/**
	 * 在切换路径之前把原来用户加入session中
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
		
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
    //    boolean needSetOrg=false;
        if (requiresSwitchUser(request)) {
        	setAccount(ContextUtil.getCurrentUser().getAccount(),request,response);
      //  	needSetOrg=true;
        } 
        //退出删除SwitchAccountcookie。
        else if (requiresExitUser(request)) {
        	removeAccount(request,response);
        //	needSetOrg=true;
        }
        
    
        super.doFilter(req, res, chain);
        
        //重新设置当前组织。
    //    if(needSetOrg){
     //   	ContextUtil.setDefaultPos();
    //    }
        
    }
	
	private void setAccount(String account,HttpServletRequest req,HttpServletResponse res){
		CookieUitl.addCookie(SwitchAccount, account, req, res);
	}
	
	private void removeAccount(HttpServletRequest req,HttpServletResponse res){
		CookieUitl.delCookie(SwitchAccount,  req, res);
	}
	
}
