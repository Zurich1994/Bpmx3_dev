package com.hotent.platform.webservice.api;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;

/**
 * 取得适用于Spring-security的用户对像UserDetail。
 *
 */
@SOAPBinding(style=Style.DOCUMENT,parameterStyle=ParameterStyle.WRAPPED)
@WebService(endpointInterface="com.hotent.platform.webservice.api.UserDetailsService",
			targetNamespace="http://impl.webservice.platform.hotent.com/")
public interface UserDetailsService {
	
	/**
	 * 取得适用于Spring-security的用户对像UserDetail。
	 * @param userName	用户名
	 * @return SysUser	适用于Spring-security的用户对像UserDetail。
	 * @throws UsernameNotFoundException	当不存在该用户时会抛出此异常。
	 * @throws DataAccessException	当连接数据库出错时会抛出此异常。
	 */
	@WebMethod(operationName="loadUserByUsername")
	public SysUser loadUserByUsername(@WebParam(name="userAccount") String userName)throws Exception;
	
	/**
	 * 通过用户名获取用户的角色信息列表
	 * @param userName 用户名
	 * @return
	 */
	@WebMethod(operationName="loadRoleByUsername")
	public String loadRoleByUsername(@WebParam(name="userAccount") String userName);
	
	/**
	 * 通过用户账号获取用户的所有组织列表
	 * @param username
	 * @return
	 */
	@WebMethod(operationName="loadOrgsByUsername")
	public List<SysOrg> loadOrgsByUsername(@WebParam(name="userAccount") String username);
	/**
	 * 通过用户账号获取用户的当前组织
	 * @param username
	 * @return
	 */
	@WebMethod(operationName="loadCurOrgByUsername") 
	public SysOrg loadCurOrgByUsername(@WebParam(name="userAccount")String username);
	/**
	 * 通过用户账号和组织ID切换用户的当前组织
	 * @param username
	 * @param orgId
	 * @return
	 */
	@WebMethod(operationName="setCurOrg")
	public void setCurOrg(@WebParam(name="userAccount")String username,@WebParam(name="orgId")Long orgId);
	
	@WebMethod(operationName="getCurrentUserSkin")
	public String getCurrentUserSkin(@WebParam(name="userId")Long userId);
}
