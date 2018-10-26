package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import com.hotent.platform.event.def.EventUtil;
import com.hotent.platform.event.def.UserEvent;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.encrypt.EncryptUtil;
import com.hotent.core.model.OnlineUser;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;


import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.bpm.BpmNodeUserUplow;
import com.hotent.platform.model.system.Demension;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.BpmNodeUserService;

/**
 * 对象功能:用户表 Service类 
 * 开发公司:广州宏天软件有限公司
 * 开发人员:heyifan 
 * 创建时间:2012-12-17 16:02:46
 */
@Service
public class SysUserService extends  BaseService<SysUser>{
	@Resource
	private SysUserDao dao;
	@Resource
	private BpmNodeUserService bpmNodeUserService;
	@Resource
	private PositionService positionService;
	@Resource
	private SysOrgDao sysOrgDao;
	@Resource
	private UserPositionService userPositionService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private SysUserOrgService sysUserOrgService;
	@Resource
	private UserUnderService userUnderService;
	
	@Override
	protected IEntityDao<SysUser, Long> getEntityDao() {
		return dao;
	}

	public SysUserService() {
		
	}
	

	


	public SysUser getByAccount(String account) {
		return dao.getByAccount(account);
	}

	/**
	 * 对象功能：根据查询条件查询用户(用于人员选择器)
	 */
	public List<SysUser> getUserByQuery(QueryFilter queryFilter) {
		return dao.getUserByQuery(queryFilter);
	}
	
	/**
	 * 对象功能：根据查询条件查询用户(用于用户管理)
	 */
	public List<SysUser> getUsersByQuery(QueryFilter queryFilter) {
		return dao.getUsersByQuery(queryFilter);
	}

	/**
	 * 返回某个角色的所有用户Id
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Long> getUserIdsByRoleId(Long roleId) {
		List<Long> ids = new ArrayList<Long>();
		List<SysUser> users = dao.getByRoleId(roleId);
		for(SysUser user:users){
			ids.add(user.getUserId());
		}
		return ids;
	}

	/**
	 * 对象功能：根据角色id查询员工
	 */
	public List<SysUser> getByRoleId(Long roleId) {
		return dao.getByRoleId(roleId);
	}

	/**
	 * 获取没有分配组织的用户
	 * 
	 * @return
	 */
	public List<SysUser> getUserNoOrg(QueryFilter queryFilter) {
		return dao.getUserNoOrg(queryFilter);
	}
	
	/**
	 * 根据多个工号字符串获取对应用户列表，
	 * 注：该方法不适用于读取大量用户
	 * @param accounts 用户工号，以逗号隔开
	 * @return 用户例表
	 * @author 202625
	 */
	public List<SysUser> getByAccounts(String accounts) {
		List<SysUser> users = new ArrayList<SysUser>();
		if(accounts!=null) {
			String[] aAccount = accounts.split(",");
			SysUser u;
			for(String a : aAccount) {
				u = getByAccount(a);
				if(u!=null) {
					users.add(u);
				}
			}
		}
		return users;
	}
	/**
	 * 对象功能：根据岗位路径查询用户
	 */
	public List<SysUser> getDistinctUserByPosPath(QueryFilter queryFilter) {
		return dao.getDistinctUserByPosPath(queryFilter);
	}

	/**
	 * 对象功能：根据组织path查询用户
	 */
	public List<SysUser> getDistinctUserByOrgPath(QueryFilter queryFilter) {
		//如果不含组织path 传递orgId 亦可
		String path = (String) queryFilter.getFilters().get("path");
		String orgId = (String) queryFilter.getFilters().get("orgId");
		if(StringUtil.isEmpty(path) && StringUtil.isNotEmpty(orgId)){
			SysOrg org  = sysOrgDao.getById(Long.parseLong(orgId));
			if(org != null) queryFilter.addFilter("path", org.getPath());
		}
		
		return dao.getDistinctUserByOrgPath(queryFilter);
	}

	/**
	 * 对象功能：判断是否存在该账号
	 */
	public boolean isAccountExist(String account) {
		return dao.isAccountExist(account);
	}

	/**
	 * 判定帐号是否存在，在更新时使用。
	 * 
	 * @param userId
	 * @param account
	 * @return
	 */
	public boolean isAccountExistForUpd(Long userId, String account) {
		return dao.isAccountExistForUpd(userId, account);
	}

	/**
	 * 查询用户属性
	 * 
	 * @param userParam
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> getByUserParam(String userParam) throws Exception {
		ParamSearch search = new ParamSearch<SysUser>() {
			@Override
			/**
			 * 实现查找数据的接口。
			 */
			public List<SysUser> getFromDataBase(Map<String, String> property) {
				return dao.getByUserOrParam(property);
			}
		};
		return search.getByParamCollect(userParam);
	}

	/**
	 * 查询组织属性
	 * 
	 * @param userParam
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> getByOrgParam(String userParam) throws Exception {
		ParamSearch search = new ParamSearch<SysUser>() {
			@Override
			/**
			 * 实现查找数据的接口。
			 */
			public List<SysUser> getFromDataBase(Map<String, String> property) {
				return dao.getByOrgOrParam(property);
			}
		};
		return search.getByParamCollect(userParam);
	}

	/**
	 * 通过用户属性、组织属性获取用户
	 * @param nodeUserId
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> getByParam(long nodeUserId) throws Exception {
		List<SysUser> list = null;
		BpmNodeUser bpmNodeUser = bpmNodeUserService.getById(nodeUserId);
		String assignType = bpmNodeUser.getAssignType();
		String param = bpmNodeUser.getCmpIds();
		if ( BpmNodeUser.ASSIGN_TYPE_USER_ATTR.equals(assignType)) {
			list = getByUserParam(param);
		} else if (BpmNodeUser.ASSIGN_TYPE_ORG_ATTR.equals(assignType) ) {
			list = getByOrgParam(param);
		}
		return list;
	}

	/**
	 * 根据当前登陆用户与上下级关系取得当前登陆用户的上下级用户
	 * 
	 * @param userId
	 * @param nodeUserId
	 * @return
	 */
	public List<SysUser> getByUserIdAndUplow(long userId, BpmNodeUser bpmNodeUser) {
		String cmpIds= bpmNodeUser.getCmpIds();
		JSONArray ja= JSONArray.fromObject(cmpIds);
		List<BpmNodeUserUplow> uplowList= (List) JSONArray.toCollection(ja, BpmNodeUserUplow.class);
		return getByUserIdAndUplow(userId, uplowList);
	}

	/**
	 * 根据 当前登陆用户与上下级关系取得上下级用户
	 * @param userId
	 * @param uplowList
	 * @return
	 */
	public List<SysUser> getByUserIdAndUplow(long userId,List<BpmNodeUserUplow> uplowList) {
		if (uplowList == null)
			return null;
		List<SysUser> list = new ArrayList<SysUser>();
		// 当前登陆用户的主岗位
		Position pl = null;
		// 当前登陆用户的主组织
		SysOrg ol = null;
		for (BpmNodeUserUplow uplow : uplowList) {
			short upLowType = uplow.getUpLowType();
			int upLowLevel = uplow.getUpLowLevel();
			int isCharge = uplow.getIsCharge();
			Long demensionId = uplow.getDemensionId();
			if (uplow.getDemensionId().longValue() == Demension.positionDem.getDemId().longValue()) {
				// 目前岗位没有上下级
				return list;

			} else {
				// 组织
				if (ol == null)
					// 获取主组织
					ol = sysOrgDao.getPrimaryOrgByUserId(userId);
				// 此处需要再次判断主组织是否为空，因为当前用户可能没有设置主组织，例如：管理员
				if (ol == null || !demensionId.equals(ol.getDemId()))
					return list;
				String currentPath = ol.getPath();
				Map<String, Object> param = handlerCondition(currentPath,upLowType, upLowLevel, isCharge);
				List<SysUser> l = dao.getUpLowOrg(param);
				// list去重
				for (SysUser user : l) {
					if (!list.contains(user)) {
						list.add(user);
					}
				}

			}
		}
		return list;
	}
	
	/**
	 * 根据 发起人与上下级关系取得上下级用户
	 * 
	 * @param userId
	 * @param bpmNodeUser
	 * @return
	 */
	public List<SysUser> getByUserIdAndUplow(long userId) {
		List<SysUser> list = new ArrayList<SysUser>();
		// 当前登陆用户的主岗位
		Position pl = null;
		// 当前登陆用户的组织
		SysOrg ol = null;
		//岗位
		pl = positionService.getPrimaryPositionByUserId(userId);

		if (pl != null) {
//			String currentPath = pl.getNodePath();
//			int currentDepth = currentPath.split("\\.").length;
//			//查找上下级
//			for(int depth=currentDepth; depth > 1; depth--) {
//				Map<String, Object> param = handlerCondition(currentPath, depth);
//				List<SysUser> l = dao.getUpLowPost(param);
//				for(SysUser user:l) {
//					if(!list.contains(user)){
//						list.add(user);
//					}
//				}
//			}
		}
		//组织
		ol = sysOrgDao.getPrimaryOrgByUserId(userId);
		if (ol != null) {
			String currentPath = ol.getPath();
			int currentDepth = currentPath.split("\\.").length;
			//查找上下级
			for(int depth=currentDepth; depth > 1; depth--) {
				Map<String, Object> param = handlerCondition(currentPath, depth);
				List<SysUser> l = dao.getUpLowOrg(param);
				for(SysUser user:l) {//list去重
					if(!list.contains(user)){
						list.add(user);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 根据当前用户所在路径组织查询条件 //此段代码的前提是upLowType:1为上级,0为平级,-1为下级;如此方能计算正确
	 * 
	 * @param currentPath
	 * @param upLowType
	 *            1为上级,0为平级,-1为下级
	 * @param upLowLevel
	 * @param isCharge
	 *            判断是否为组织负责人  1为是,0为否
	 * @return
	 */
	private static Map<String, Object> handlerCondition(String currentPath, short upLowType, int upLowLevel, int isCharge) {

		String pathArr[] = currentPath.split("\\.");
		int currentDepth = pathArr.length;
		String path = null;
		Integer depth = null;
		String pathCondition = null;
		int depthCondition = 0;
		
		switch (upLowType) {
		case 1://上级
			depth = currentDepth-upLowLevel;
			pathCondition = "=";
			path = coverArray2Str(pathArr, depth);
			path += ".";
			break;
		case -1://下级
			depth = currentDepth+upLowLevel;
			pathCondition = "like";
			depthCondition = depth; 
			path = currentPath + "%";
			break;
		case 0://同级
			depth = currentDepth;
			pathCondition = "like";
			path = coverArray2Str(pathArr, depth-1) + "._%";
			depthCondition = depth;
			break;
		}

		Map<String, Object> returnMap = new HashMap<String, Object>();
		//查找路径
		returnMap.put("path", path);
		//depthCondition用于判断是否要按组织、岗位的层次进行具体层次的查找，主要用于上几级或下几级的查找，0为不进行层次查找
		returnMap.put("depthCondition", depthCondition);
		//isCharge用于判断是否要查找负责人，0为不查找，1为查找
		returnMap.put("isCharge", isCharge);
		//路径条件，或为‘=’，或为‘like’
		returnMap.put("pathCondition", pathCondition);
		return returnMap;
	}
	
	/**
	 * 根据当前用户所在路径组织查询条件 
	 * @param currentPath
	 * @param depth
	 * @return
	 */
	private static Map<String, Object> handlerCondition(String currentPath, int depth) {

		String pathArr[] = currentPath.split("\\.");
		String path = null;
		String pathCondition = "=";
		if(depth==pathArr.length){
			path = coverArray2Str(pathArr, depth-1) + "._%";
			pathCondition = "like";
		}
		else {
			path = coverArray2Str(pathArr, depth);
			path += ".";
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("path", path);
		//isCharge用于判断是否要查找负责人，0为不查找，1为查找
		returnMap.put("isCharge", 0);
		returnMap.put("pathCondition", pathCondition);
		//depthCondition用于判断是否要按组织、岗位的层次进行具体层次的查找，主要用于上几级或下几级的查找，0为不进行层次查找
		returnMap.put("depthCondition", 0);
		return returnMap;
	}

	/**
	 * 将数组路径转化为字符串
	 * 
	 * @param pathArr
	 * @param len
	 * @return
	 */
	private static String coverArray2Str(String pathArr[], int len) {
		if (len < 0)
			return pathArr[0];
		if (len > pathArr.length)
			len = pathArr.length;

		StringBuilder sb = new StringBuilder();
		if (pathArr.length > 1) {
			int i = 0;
			do {
				sb.append(pathArr[i]);
				sb.append(".");
				i++;
			} while (i < len);

			sb = sb.delete(sb.length() - 1, sb.length());
		} else if (pathArr.length > 0)
			sb = sb.append(pathArr[0]);
		return sb.toString();
	}

	/**
	 * 获取在线用户
	 * @param list
	 * @return
	 */
	public List<SysUser> getOnlineUser(List<SysUser> list) {
		List<SysUser> listOnl = new ArrayList<SysUser>();
		Map<Long, OnlineUser> onlineUsers = AppUtil.getOnlineUsers();
		List<OnlineUser> onlineList = new ArrayList<OnlineUser>();
		for (Long sessionId : onlineUsers.keySet()) {
			OnlineUser onlineUser = onlineUsers.get(sessionId);
			onlineList.add(onlineUser);
		}
		for (SysUser sysUser : list) {
			for (OnlineUser onlineUser : onlineList) {
				Long sysUserId = sysUser.getUserId();
				Long onlineUserId = onlineUser.getUserId();
				if (sysUserId.toString().equals(onlineUserId.toString())) {
					listOnl.add(sysUser);
				}
			}
		}
		list = listOnl;
		return list;
	}

	/**
	 * 按用户Id组取到该用户列表
	 * 
	 * @param uIds
	 * @return
	 */
	public List<SysUser> getByIdSet(Set uIds) {
		return dao.getByIdSet(uIds);
	}

	public SysUser getByMail(String address) {
		return dao.getByMail(address);
	}
	
	/**
	 * 获取所有用户（包含用户的组织ID）
	 * @return
	 */
	public List<SysUser> getAllIncludeOrg(){
		return dao.getAll();
	}

	/**
	 * 更新用户密码。
	 * 
	 * @param userId
	 *            用户id
	 * @param pwd
	 *            明文密码。
	 */
	public void updPwd(Long userId, String pwd) {
		String enPassword = EncryptUtil.encryptSha256(pwd);
		dao.updPwd(userId, enPassword);
	}

	/**
	 * 更新用户的状态。
	 * 
	 * @param userId
	 *            用户id
	 * @param status
	 *            1，激活，0，禁用，-1，删除
	 * @param isLock
	 *            0，未锁定，1，锁定
	 */
	public void updStatus(Long userId, Short status, Short isLock) {
		dao.updStatus(userId, status, isLock);
	}

	/**
	 * 保存用户对象
	 * 
	 * @param sysUser
	 *            用户对象
	 * @param orgIdCharge
	 *            负责的组织。
	 * @param orgIds
	 *            组织ID
	 * @param orgIdPrimary
	 *            主组织
	 * @param posIds
	 *            岗位ID
	 * @param posIdPrimary
	 *            主岗位
	 * @param roleIds
	 *            用户角色
	 * @throws Exception
	 */
	public void saveUser(Integer bySelf, SysUser sysUser, Long[] posIdCharge, Long[] posIds, Long posIdPrimary, Long[] roleIds)
			throws Exception {
		if (sysUser.getUserId() == null) {
			sysUser.setUserId(UniqueIdUtil.genId());
			dao.add(sysUser);  
			System.out.println(sysUser.getRoles());
		} else {
			dao.update(sysUser);
			EventUtil.publishUserEvent(sysUser.getUserId(), UserEvent.ACTION_UPD);
			System.out.println(sysUser.getRoles());
		}
		if (bySelf == 0) {
			Long userId = sysUser.getUserId();
//			// 添加用户和组织关系。
//			sysUserOrgService.saveUserOrg(userId, aryOrgIds, orgIdPrimary, orgIdCharge);
//			// 处理岗位信息。
//			userPositionService.saveUserPos(userId, posIds, posIdPrimary);
			
			//TODO 保存用户 和岗位的对应关系
			userPositionService.saveUserPos(userId, posIds, posIdPrimary,posIdCharge);
			// 保存与角色的映射关系。
			userRoleService.saveUserRole(userId, roleIds);
			//保存上级数据
			userUnderService.saveSuperior(userId, sysUser.getSuperiorIds());
			
		}
		
	}
	
	/**
	 * 通过用户来源类型获取用户列表
	 * @param type
	 * @return
	 */
	public List<SysUser> getByFromType(Short type){
		return dao.getByFromType(type);
	}
	
	/**
	 * 根据角色id查询员
	 */
	public List<SysUser> getUserByRoleId(QueryFilter queryFilter) {
		return dao.getBySqlKey("getUserByRoleId", queryFilter);
	}

	/**
	 * 手机用户的查询
	 * @param filter
	 * @return
	 */
	public List<SysUser> getAllMobile(QueryFilter filter) {
		return dao.getAllMobile(filter);
	}
	
	/**
	 * 取得某个部门下有某个角色的人员列表
	 * @param roleId
	 * @param orgId
	 * @return
	 */
	public List<SysUser> getUserByRoleIdOrgId(Long roleId,Long orgId){
		return dao.getUserByRoleIdOrgId(roleId,orgId);
	}
	
	/**
	 * 取得某个部门下有某个岗位的人员列表 
	 * @param orgId
	 * @param posId
	 * @return
	 */
	public List<SysUser> getByOrgIdPosId(Long orgId,Long posId){
		return dao.getByOrgIdPosId(orgId,posId);
	}

	public List<SysUser> getDistinctUserByOrgId(QueryFilter queryFilter) {
		// TODO Auto-generated method stub
		return dao.getDistinctUserByOrgId(queryFilter);
	}

	public List<SysUser> getDistinctUserByPosId(QueryFilter queryFilter) {
		// TODO Auto-generated method stub
		return dao.getDistinctUserByPosId(queryFilter);
	}
	
	/**
	 * 根据职务列表获取人员。
	 * @param list
	 * @return
	 */
	public List<SysUser> getUserListByJobId(Long jobId){
		List<Long> list=new ArrayList<Long>();
		list.add(jobId);
		return dao.getByJobIds(list);
	}
	
	/**
	 * 根据职务列表获取人员。
	 * @param list
	 * @return
	 */
	public List<SysUser> getUserListByPosId(Long posId){
		return dao.getByPosId(posId);
	}

}
