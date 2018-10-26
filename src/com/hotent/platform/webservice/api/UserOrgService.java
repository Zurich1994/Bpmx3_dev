package com.hotent.platform.webservice.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;

/**
 * 用户组织数据交互接口
 *
 */
@SOAPBinding(style=Style.DOCUMENT,parameterStyle=ParameterStyle.WRAPPED)
@WebService(endpointInterface="com.hotent.platform.webservice.api.UserOrgService",
			targetNamespace="http://impl.webservice.platform.hotent.com/")
public interface UserOrgService {
	/**
	 * 添加或者更新用户
	 * <pre>
	 * 如果通过传入的用户账号(account)查找到了用户，则做更新操作，否则做添加操作
	 * </pre>
	 * @param userInfo 用户信息
	 * @return
	 */
	@WebMethod(operationName="addOrUpdateUser")
	String addOrUpdateUser(@WebParam(name="userInfo")String userInfo);
	
	/**
	 * 删除用户
	 * @param userAccount 用户账号
	 * @return
	 */
	@WebMethod(operationName="deleteUser")
	String deleteUser(@WebParam(name="account")String account);
	
	/**
	 * 添加或者更新角色
	 * <pre>
	 * 如果通过传入的角色别名(alias)查找到了角色，则做更新操作，否则做添加操作
	 * </pre>
	 * @param roleInfo 角色信息
	 * @return
	 */
	@WebMethod(operationName="addOrUpdateRole")
	String addOrUpdateRole(@WebParam(name="roleInfo")String roleInfo);
	
	/**
	 * 删除角色
	 * @param alias 角色别名
	 * @return
	 */
	@WebMethod(operationName="deleteRole")
	String deleteRole(@WebParam(name="alias")String alias);
	
	/**
	 * 添加或者更新组织
	 * <pre>
	 * 如果通过传入的组织代码(code)查找到了组织，则做更新操作，否则做添加操作
	 * </pre>
	 * @param orgInfo 组织信息
	 * @return
	 */
	@WebMethod(operationName="addOrUpdateOrg")
	String addOrUpdateOrg(@WebParam(name="orgInfo")String orgInfo);
	
	/**
	 * 删除组织
	 * @param code 组织代码
	 * @return
	 */
	@WebMethod(operationName="deleteOrg")
	String deleteOrg(@WebParam(name="code")String code);
	
	/**
	 * 添加或者更新岗位
	 * <pre>
	 * 如果通过传入的岗位代码(posCode)查找到了岗位，则做更新操作，否则做添加操作
	 * </pre>
	 * @param posInfo 岗位信息（包括岗位所属组织代码和职务代码）
	 * @return
	 */
	@WebMethod(operationName="addOrUpdatePos")
	String addOrUpdatePos(@WebParam(name="posInfo")String posInfo);
	
	/**
	 * 删除岗位
	 * @param posCode 岗位代码
	 * @return
	 */
	@WebMethod(operationName="deletePos")
	String deletePos(@WebParam(name="posCode")String posCode);
	
	/**
	 * 添加或者更新职务
	 * <pre>
	 * 如果通过传入的职务代码(jobcode)查找到了职务，则做更新操作，否则做添加操作
	 * </pre>
	 * @param jobInfo 职务信息
	 * @return
	 */
	@WebMethod(operationName="addOrUpdateJob")
	String addOrUpdateJob(@WebParam(name="jobInfo")String jobInfo);
	
	/**
	 * 删除职务
	 * @param jobcode 职务代码
	 * @return
	 */
	@WebMethod(operationName="deleteJob")
	String deleteJob(@WebParam(name="jobcode")String jobcode);
	
	/**
	 * 添加或者更新用户组织参数
	 * <pre>
	 * 如果通过传入的参数代码(paramKey)查找到了参数，则做更新操作，否则做添加操作
	 * </pre>
	 * @param paramInfo 参数信息
	 * @return
	 */
	@WebMethod(operationName="addOrUpdateParam")
	String addOrUpdateParam(@WebParam(name="paramInfo")String paramInfo);
	
	/**
	 * 删除参数
	 * @param paramKey 参数代码
	 * @return
	 */
	@WebMethod(operationName="deleteParam")
	String deleteParam(@WebParam(name="paramKey")String paramKey);
	
	/**
	 * 获取参数列表
	 * @param paramCode
	 * @return
	 */
	@WebMethod(operationName="getParam")
	String getParam();
	
	/**
	 * 编辑用户、组织属性值
	 * @param type 类型 user：用户属性，org：组织属性
	 * @param key 类型为用户时传入用户账号(account),类型为组织时传入组织代码(code)
	 * @param paramValue 属性值
	 * @return
	 */
	@WebMethod(operationName="editParamValue")
	String editParamValue(@WebParam(name="type")String type,@WebParam(name="key")String key,@WebParam(name="paramValue")String paramValue);
	
	/**
	 * 添加用户上下级关系
	 * @param upUserAccount 上级用户账号
	 * @param downUserAccount 下级用户账号(可以传入多个下级用户账号，逗号分隔)
	 * @return
	 */
	@WebMethod(operationName="addUserRel")
	String addUserRel(@WebParam(name="upUserAccount")String upUserAccount,@WebParam(name="downUserAccount")String downUserAccount);
	
	/**
	 * 移除用户上下级关系
	 * @param upUserAccount 上级用户账号
	 * @param downUserAccount 下级用户账号(可以传入多个下级用户账号，逗号分隔。传入空字符串则表示移除该上级用户的所有下级)
	 * @return
	 */
	@WebMethod(operationName="removeUserRel")
	String removeUserRel(@WebParam(name="upUserAccount")String upUserAccount,@WebParam(name="downUserAccount")String downUserAccount);
	
	/**
	 * 添加用户角色关系(从属关系)
	 * @param userAccount 用户账号(可以传入多个用户账号，逗号分隔。不能为空)
	 * @param roleAlias   角色别名(可以传入多个角色别名，逗号分隔。不能为空)
	 * @return
	 */
	@WebMethod(operationName="addUserRoleRel")
	String addUserRoleRel(@WebParam(name="userAccount")String userAccount,@WebParam(name="roleAlias")String roleAlias);
	
	/**
	 * 移除用户角色关系(从属关系)
	 * @param userAccount(可以传入多个角色别名，逗号分隔。为空则表示移除该角色下所有的用户)
	 * @param roleAlias(可以传入多个角色别名，逗号分隔。为空则表示移除该用户下所有的角色)
	 * @return
	 */
	@WebMethod(operationName="removeUserRoleRel")
	String removeUserRoleRel(@WebParam(name="userAccount")String userAccount,@WebParam(name="roleAlias")String roleAlias);
	
	/**
	 * 添加用户和组织以及角色的关系
	 * @param userAccount 用户账号
	 * @param orgCode 组织代码
	 * @param userPerms 用户权限(add,delete,edit 逗号分隔，多选，可以为空)
	 * @param orgPerms 子组织权限(add,delete,edit 逗号分隔，多选，可以为空)
	 * @param roleAlias 角色别名(可以传入多个角色别名，逗号分隔。可以为空)
	 * gradeManage:分级管理员
	 * @return
	 */
	@WebMethod(operationName="addUserOrgRel")
	String addUserOrgRel(@WebParam(name="userAccount")String userAccount,@WebParam(name="orgCode")String orgCode,@WebParam(name="userPerms")String userPerms,@WebParam(name="orgPerms")String orgPerms,@WebParam(name="roleAlias")String roleAlias);
	
	/**
	 * 移除用户和组织以及角色的关系
	 * @param userAccount 用户账号
	 * @param orgCode 组织代码
	 * @return
	 */
	@WebMethod(operationName="removeUserOrgRel")
	String removeUserOrgRel(@WebParam(name="userAccount")String userAccount,@WebParam(name="orgCode")String orgCode);
	
	/**
	 * 添加用户和岗位的关系
	 * @param userAccount 用户账号
	 * @param posCode 岗位代码
	 * @param orgType 关系类型 belong：从属,main：负责人   为空则默认为从属
	 * @param posType 关系类型 belong：从属,main：主岗位   为空则默认为从属
	 * @return
	 */
	@WebMethod(operationName="addUserPosRel")
	String addUserPosRel(@WebParam(name="userAccount")String userAccount,@WebParam(name="posCode")String posCode,@WebParam(name="orgType")String orgType,@WebParam(name="posType")String posType);
	
	/**
	 * 移除用户和岗位的关系
	 * @param userAccount 用户账号
	 * @param posCode 岗位代码(可以传入多个角色别名，逗号分隔。可以为空)
	 * @return
	 */
	@WebMethod(operationName="removeUserPosRel")
	String removeUserPosRel(@WebParam(name="userAccount")String userAccount,@WebParam(name="posCode")String posCode);
	
	/**
	 * 添加组织和角色的关系
	 * @param orgCode 组织代码  (可以传入多个组织代码，逗号分隔。不能为空)
	 * @param roleCode 角色代码(可以传入多个角色别名，逗号分隔。不能为空)
	 * @return
	 */
	@WebMethod(operationName="addOrgRoleRel")
	String addOrgRoleRel(@WebParam(name="orgCode")String orgCode,@WebParam(name="roleAlias")String roleAlias);
	
	/**
	 * 移除组织和角色的关系
	 * @param orgCode 组织代码    (可以传入多个组织代码，逗号分隔。为空则表示移除该角色下所有的组织)
	 * @param roleCode 角色代码   (可以传入多个角色别名，逗号分隔。为空则表示移除该组织下所有的角色)
	 * @return
	 */
	@WebMethod(operationName="removeOrgRoleRel")
	String removeOrgRoleRel(@WebParam(name="orgCode")String orgCode,@WebParam(name="roleAlias")String roleAlias);
}
