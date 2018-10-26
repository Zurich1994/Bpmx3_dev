package com.hotent.platform.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.IUserUnderService;

@Service
public class UserUnderServiceImpl implements IUserUnderService {

	@Resource
	private SysUserDao sysUserDao;
	
	public List<SysUser> getMyUnderUser(Long userId) {
		return sysUserDao.getUnderUserByUserId(userId);
	}

}
