package com.hotent.platform.service.system;

import javax.servlet.http.HttpServletRequest;
import com.hotent.core.web.util.RequestContext;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.util.CookieUitl;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.SysUser;

/**
 * 获取当前系统。
 * @author ray
 *
 */
public class SubSystemUtil {
	/**
	 * 取得当系统
	 * @return
	 * @throws Exception 
	 */
	public static SubSystem getCurrentSystem(HttpServletRequest request) {
		//判断系统是否登录
		SysUser curUser=ContextUtil.getCurrentUser();
		//未登录系统则返回 null。
		if(curUser==null) return null;
		//判断session是否存在子系统
		SubSystem subSystem=(SubSystem) request.getSession().getAttribute(SubSystem.CURRENT_SYSTEM);
		//存在则返回
		if(subSystem!=null) return subSystem;
		//判断cookie是否存在
		boolean isCookieExists=CookieUitl.isExistByName(SubSystem.CURRENT_SYSTEM,request);
		//cookie不存在则返回为空。
		if(!isCookieExists) {
			return null;
		}
		//从cookie中获取systemId，查询子系统并放置到session当中。
		String systemId=CookieUitl.getValueByName(SubSystem.CURRENT_SYSTEM, request);
		SubSystemService subSystemService=(SubSystemService) AppUtil.getBean(SubSystemService.class);
		subSystem=subSystemService.getById(Long.valueOf(systemId));

		request.getSession().setAttribute(SubSystem.CURRENT_SYSTEM,subSystem);	

		return subSystem;
	}
	
	/**
	 * 取得当系统的ID
	 * @return
	 * @throws Exception 
	 */
	public static Long getCurrentSystemId(HttpServletRequest request) {
		SubSystem subSystem=getCurrentSystem(request);
		if(subSystem!=null) return subSystem.getSystemId();
		return null;
	}
	
	/**
	 * 取得当系统的ID
	 * @return
	 * @throws Exception 
	 */
	public static String getCurrentSystemAlias(HttpServletRequest request) {
		SubSystem subSystem=getCurrentSystem(request);
		if(subSystem!=null) return subSystem.getAlias();
		return null;
	}
	
	/**
	 * 删除当前系统。
	 */
	public static void removeSystem(){
		
		RequestContext.getHttpServletRequest().getSession().removeAttribute(SubSystem.CURRENT_SYSTEM);
	}
}
