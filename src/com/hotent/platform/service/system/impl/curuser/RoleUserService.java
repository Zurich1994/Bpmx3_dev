package com.hotent.platform.service.system.impl.curuser;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.hotent.core.model.CurrentUser;
import com.hotent.platform.model.system.UserRole;
import com.hotent.platform.service.system.ICurUserService;
import com.hotent.platform.service.system.UserRoleService;

public class RoleUserService implements ICurUserService {

	@Resource
	UserRoleService userRoleService;
	
	public List<Long> getByCurUser(CurrentUser currentUser) {
		List<UserRole> list= userRoleService.getByUserId(currentUser.getUserId());
		List<Long> roleList=new ArrayList<Long>();
		for(UserRole userRole:list){
			roleList.add(userRole.getRoleId());
		}
		return roleList;
	}

	public String getKey() {
		return "role";
	}

	public String getTitle() {
		return "角色授权";
	}


}
