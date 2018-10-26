package com.hotent.platform.service.system;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.UserRole;
import com.hotent.platform.dao.system.UserRoleDao;

/**
 * 对象功能:用户角色映射表 Service类 开发公司:广州宏天软件有限公司 开发人员:csx 创建时间:2011-12-16 15:47:55
 */
@Service
public class UserRoleService extends BaseService<UserRole> {
	@Resource
	private UserRoleDao userRoleDao;

	public UserRoleService() {
	}

	@Override
	protected IEntityDao<UserRole, Long> getEntityDao() {
		return userRoleDao;
	}

	/**
	 * 在角色中添加用户成员。
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void add(Long roleId, Long[] userIds) throws Exception {
		if (roleId == null || roleId == 0 || userIds == null
				|| userIds.length == 0)
			return;
		for (Long userId : userIds) {
			// 首先判断用户是否可以添加。
			UserRole userRole = userRoleDao.getUserRoleModel(userId, roleId);
			if (userRole != null)
				continue;

			long userRoleId = UniqueIdUtil.genId();
			UserRole urro = new UserRole();
			urro.setUserRoleId(userRoleId);
			urro.setRoleId(roleId);
			urro.setUserId(userId);
			userRoleDao.add(urro);
			
		}
	}

	/**
	 * 对象功能：查找该条件的用户角色的实体
	 */
	public UserRole getUserRoleModel(Long userId, Long roleId) {
		return userRoleDao.getUserRoleModel(userId, roleId);
	}

	/**
	 * 根据id数组,删除sys_user_role关系信息
	 * 
	 * @param Long
	 *            [] ids
	 * @return
	 */
	public void delUserRoleByIds(String[] lAryId, Long userId) {
		if (BeanUtils.isEmpty(lAryId))
			return;
		for (String roleId : lAryId) {
			if (StringUtil.isEmpty(roleId))
				continue;
			userRoleDao.delUserRoleByIds(userId, Long.parseLong(roleId));
		}
	}

	/**
	 * 保存用户和角色的映射关系。
	 * 
	 * @param userId
	 *            用户ID
	 * @param roleIds
	 *            角色的数组。
	 * @throws Exception
	 */
	public void saveUserRole(Long userId, Long[] roleIds) throws Exception {
		userRoleDao.delByUserId(userId);

		if (BeanUtils.isEmpty(roleIds))
			return;

		for (int i = 0; i < roleIds.length; i++) {
			Long roleId = roleIds[i];
			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);
			userRole.setUserRoleId(UniqueIdUtil.genId());
			userRoleDao.add(userRole);
		}

	}

	/**
	 * 获取某个角色下的所有用户Id
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Long> getUserIdsByRoleId(Long roleId) {
		return userRoleDao.getUserIdsByRoleId(roleId);
	}

	/**
	 * 根据多个角色获取用户。
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<UserRole> getUserByRoleIds(String roleIds) {
		return userRoleDao.getUserByRoleIds(roleIds);
	}

	/**
	 * 获取某个角色下的所有用户信息
	 * 
	 * @param roleId
	 * @return
	 */
	public List<UserRole> getUserRoleByRoleId(Long roleId) {
		return userRoleDao.getUserRoleByRoleId(roleId);
	}

	/**
	 * 删除角色ID 。
	 * 
	 * @param roleId
	 */
	public void delByRoleId(Long roleId) {
		userRoleDao.delByRoleId(roleId);
		
	}

	/**
	 * 根据用户角色映射id删除关联关系。
	 * 
	 * @param aryUserRoleId
	 */
	public void delByUserRoleId(Long[] aryUserRoleId) {
		for (int i = 0; i < aryUserRoleId.length; i++) {
			Long userRoleId = aryUserRoleId[i];
			UserRole userRole = userRoleDao.getById(userRoleId);
			userRoleDao.delById(userRoleId);
			
		}
	}

	/**
	 * 根据用户id获取对应的角色。
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserRole> getByUserId(Long userId) {
		return userRoleDao.getByUserId(userId);
	}
	
	/**
	 * 根据用户id删除用户角色关系。
	 * @param userId
	 * @return
	 */
	public void delByUserId(Long userId) {
		userRoleDao.delByUserId(userId);
	}
	public void delByUserIdAndRoleId(Long userId, Long roleId) {
		userRoleDao.delUserRoleByIds(userId, roleId);
	}
}
