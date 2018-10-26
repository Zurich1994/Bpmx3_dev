package com.hotent.core.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.UnsatisfiedDependencyException;

import com.hotent.core.util.AppUtil;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysUserService;

/**
 * 任务执行人
 * @author ray
 *
 */
public class TaskExecutor implements Serializable {
	
	/**
	 * 默认不抽取。
	 */
	public static final int EXACT_NOEXACT=0;
	
	/**
	 * 抽取用户
	 */
	public static final int EXACT_EXACT_USER=1;
	
	/**
	 * 二级抽取,这个适用于会签任务，
	 * 就是角色先不抽取，到具体任务时，在进行人员抽取。
	 */
	public static final int EXACT_EXACT_SECOND=2;
	
	/**
	 * 用户分组，这个适用于会签任务。
	 * 比如某个用户规则选出人员来后，将人员进行分组。
	 * 
	 */
	public static final int EXACT_USER_GROUP=3;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10001L;
	/**
	 * 把任务直接授权给一个或多个用户
	 */
	public static final String USER_TYPE_USER="user";
	/**
	 * 把任务直接授权给一个或多个组织
	 */
	public static final String USER_TYPE_ORG="org";
	/**
	 * 把任务直接授权给一个或多个角色
	 */
	public static final String USER_TYPE_ROLE="role";
	/**
	 * 把任务直接授权给一个或多个岗位
	 */
	public static final String USER_TYPE_POS="pos";
	
	/**
	 * 把任务直接授权给一个或多个职务
	 */
	public static final String USER_TYPE_JOB="job";
	
	
	
	/**
	 * 用户分组。
	 */
	public static final String USER_TYPE_USERGROUP="group";
	
	/**
	 * 执行人的类型为。
	 * type:
	 * user，用户
	 * org,组织
	 * role,角色
	 * pos,岗位
	 */
	private String type="user";
	
	//如果type==user 则需要求出每个用户的主组织
	public String mainOrgName;
	
	/**
	 * 执行者ID
	 */
	private String executeId="";
	
	/**
	 * 执行人name。
	 */
	private String executor="";
	
	/**
	 * 抽取类型。
	 * 0:默认不抽取。
	 * 1:抽取
	 * 2:二级抽取
	 * 3:用户分组
	 */
	private int exactType=0;
	
	public TaskExecutor(){}
	
	/**
	 * 构造函数
	 * @param executeId		用户ID
	 */
	public TaskExecutor(String executeId){
		Long userId = Long.parseLong(executeId);
		SysUserService sysUserService = (SysUserService)AppUtil.getBean(SysUserService.class);
		SysUser sysUser = sysUserService.getById(userId);
		this.executeId = executeId;
		this.executor = sysUser.getFullname();
	}
	
	/**
	 * 构造函数
	 * @param type			人员类型(user,pos,org,role)
	 * @param executeId		对应的id
	 * @param name			对应的名称
	 */
	public TaskExecutor(String type,String executeId,String name){
		this.type=type;
		this.executeId=executeId;
		this.executor=name;
		if(USER_TYPE_USERGROUP.equalsIgnoreCase(type)){
			this.exactType=EXACT_USER_GROUP;
		}
	}
	
	/**
	 * 获取任务用户。
	 * @param executeId
	 * @return
	 */
	public static TaskExecutor getTaskUser(String executeId,String name){
		return new TaskExecutor(USER_TYPE_USER,executeId,name);
	}
	
	/**
	 * 获取组织执行人。
	 * @param executeId
	 * @return
	 */
	public static TaskExecutor getTaskOrg(String executeId,String name){
		return new TaskExecutor(USER_TYPE_ORG,executeId, name);
	}
	
	/**
	 * 获取角色任务。
	 * @param executeId
	 * @return
	 */
	public static TaskExecutor getTaskRole(String executeId,String name){
		return new TaskExecutor(USER_TYPE_ROLE,executeId,name);
	}
	
	
	/**
	 * 获取职务任务。
	 * @param executeId
	 * @return
	 */
	
	public static TaskExecutor getTaskJob(String executeId,String name){
		return new TaskExecutor(USER_TYPE_JOB,executeId,name);
	}
	
	
	/**
	 * 获取岗位。
	 * @param executeId
	 * @return
	 */
	public static TaskExecutor getTaskPos(String executeId,String name){
		return new TaskExecutor(USER_TYPE_POS,executeId,name);
	}
	
	
	/**
	 * 获取用户分组。
	 * @param executeId
	 * @param name
	 * @return
	 */
	public static TaskExecutor getTaskUserGroup(String executeId,String name){
		TaskExecutor ex= new TaskExecutor(USER_TYPE_USERGROUP,executeId,name);
		ex.setExactType(EXACT_USER_GROUP);
		return ex;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExecuteId() {
		return executeId;
	}

	public void setExecuteId(String executeId) {
		this.executeId = executeId;
	}
	
	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}
	
	

	public int getExactType() {
		return exactType;
	}

	public void setExactType(int exactType) {
		this.exactType = exactType;
	}

	@Override
	public boolean equals(Object obj){
		if(! (obj instanceof TaskExecutor)){
			return false;
		}
		TaskExecutor tmp=(TaskExecutor)obj;
		if(this.type.equals(tmp.getType()) && this.executeId.equals(tmp.getExecuteId())){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		String tmp=this.type +this.executeId;
		return tmp.hashCode();
	}

	public Set<SysUser> getSysUser() throws UnsatisfiedDependencyException{
		Set<SysUser> sysUsers = new HashSet<SysUser>();
		if(AppUtil.getContext()==null){
			throw new UnsatisfiedDependencyException("Convert Executor to SysUser dependency ApplicationContext", "applicationContext", "", "Convert Executor to SysUser dependency ApplicationContext");
		}
		if(USER_TYPE_USER.equals(type)){
			SysUserService sysUserService = (SysUserService) AppUtil.getBean(SysUserService.class);
			SysUser sysUser = sysUserService.getById(Long.valueOf(executeId));
			sysUsers.add(sysUser);
		}else if(USER_TYPE_ORG.equals(type)){
			SysUserDao sysUserDao = (SysUserDao) AppUtil.getBean(SysUserDao.class);
			List<SysUser> users = sysUserDao.getByOrgId(Long.valueOf(executeId));
			sysUsers.addAll(users);
		}else if(USER_TYPE_POS.equals(type)){
			SysUserDao sysUserDao = (SysUserDao) AppUtil.getBean(SysUserDao.class);
			List<SysUser> users = sysUserDao.getByPosId(Long.valueOf(executeId));
			sysUsers.addAll(users);
		}else if(USER_TYPE_ROLE.equals(type)){
			SysUserDao sysUserDao = (SysUserDao) AppUtil.getBean(SysUserDao.class);
			List<SysUser> users = sysUserDao.getByRoleId(Long.valueOf(executeId));
			sysUsers.addAll(users);
		}
		return sysUsers;
	}

	public String getMainOrgName() {
		return mainOrgName;
	}

	public void setMainOrgName(String mainOrgName) {
		this.mainOrgName = mainOrgName;
	}

}
