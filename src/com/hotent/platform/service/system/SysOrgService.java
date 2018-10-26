package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import com.hotent.platform.model.system.Position;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.dao.system.SysOrgTypeDao;
import com.hotent.platform.dao.system.UserPositionDao;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysOrgType;
import com.hotent.platform.model.system.SysUserOrg;
import com.hotent.platform.model.system.UserPosition;

/**
 * 对象功能:组织架构SYS_ORG Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-11-09 11:20:13
 */
@Service
public class SysOrgService extends BaseService<SysOrg> {
	@Resource
	private SysOrgTacticService sysOrgTacticService;
//	@Resource
//	private SysUserOrgDao sysUserOrgDao;
	@Resource
	private SysUserOrgService sysUserOrgService;
	
	@Resource
	private SysOrgRoleService sysOrgRoleService;
	
	@Resource
	private UserPositionDao userPositionDao;
	
	@Resource
	private SysOrgTypeDao sysOrgTypeDao;

	@Resource
	private SysOrgDao dao; 
	protected static Logger logger = LoggerFactory.getLogger(SysOrgService.class);
	@Override
	protected IEntityDao<SysOrg, Long> getEntityDao() {
		return dao;
	}

	public SysOrgService() {
	}
	


	/* *
	 * 对象功能：根据条件查询组织
	 */
	public List<SysOrg> getOrgByOrgId(QueryFilter queryFilter) {
		return dao.getOrgByOrgId(queryFilter);
	}

	/**
	 * 通过维度取得组织（没有维度ID则获取所有组织）
	 * 
	 * @param demId
	 * @return
	 */
	public List<SysOrg> getOrgsByDemIdOrAll(Long demId) {
		return dao.getOrgsByDemIdOrAll(demId);		
	}
	/**
	 * 根据组织的名称查询组织数据。
	 * @param orgName 组织的名称
	 * @return
	 */
	public List<SysOrg> getByOrgName(String orgName) {
		return dao.getByOrgName(orgName);
	}
	
	/**
	 * 根据维度查找组织节点
	 * @param demId
	 * @return 返回Map<Long,SysOrg>其中key为组织ID
	 */
	public Map getOrgMapByDemId(Long demId) {
		String userNameStr = "";
		String userNameCharge = "";
		Map<Long, SysOrg> orgMap = new HashMap<Long, SysOrg>();
		List<SysOrg> list = dao.getOrgsByDemIdOrAll(demId);
		for (SysOrg sysOrg : list) {
			List<UserPosition> userlist =userPositionDao.getByOrgId(sysOrg.getOrgId());
			for (SysUserOrg userOrg : userlist) {
				String isCharge = "";
				if (BeanUtils.isNotEmpty(userOrg.getIsCharge())) {
					isCharge = userOrg.getIsCharge().toString();
				}
				// 为主要负责人
				if (SysUserOrg.CHARRGE_YES.equals(isCharge)) {
					if (userNameCharge.isEmpty()) {
						userNameCharge = userOrg.getUserName();
					} else {
						userNameCharge = userNameCharge + "," + userOrg.getUserName();
					}
				}
			}

			sysOrg.setOwnUserName(userNameCharge);
			if (sysOrg.getOrgSupId() != 0)
				orgMap.put(sysOrg.getOrgId(), sysOrg);
		}
		return orgMap;
	}
	
	public SysOrg getByCode(String code) {
		List<SysOrg> sysOrgs = dao.getBySqlKey("getByCode", code);
		if(sysOrgs.isEmpty()) return null;
		return  sysOrgs.get(0);
	}
	
	/**
	 * 删除组织，需要把组织下相关的信息 级联删除
	 * 1、标识删除用户岗位和组织的关系
	 * 2、删除组织下绑定的角色
	 * 3、删除组织本身
	 */
	public void delById(Long id) {
		SysOrg sysOrg=dao.getById(id);
		String path = sysOrg.getPath();
		path = StringUtil.isNotEmpty(path)?(path+"%"):"";
		sysUserOrgService.delByOrgPath(path);
		sysOrgRoleService.delByOrgPath(path);
		dao.delByPath(path);
	}

	public List<SysOrg> getOrgsByUserId(Long userId) {
		return dao.getOrgsByUserId(userId);
	}

	/**
	 * 取得某个用户所在的组织ID字符串
	 * 
	 * @param userId
	 * @return 返回格式如1,2
	 */
	public String getOrgIdsByUserId(Long userId) {
		StringBuffer sb = new StringBuffer();
		List<SysOrg> orgList = dao.getOrgsByUserId(userId);
		for (SysOrg org : orgList) {
			sb.append(org.getOrgId() + ",");
		}
		if (orgList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	/**
	 * 
	 * @param userId
	 * @return 返回格式如1,2
	 */
	public Long getOrgIdByUserId(Long userId) {

		PositionService positionService=AppUtil.getBean(PositionService.class);
		SysOrgService sysOrgService=AppUtil.getBean(SysOrgService.class);
		Position position=positionService.getDefaultPosByUserId(userId);
		if(position!=null){
			Long orgId=position.getOrgId();
			SysOrg sysOrg = sysOrgService.getById(orgId);
			if(sysOrg!=null) return sysOrg.getOrgId();
		}
		return null;
	}

	/**
	 * 转达化数据格式,把List<SysOrg>转化为Map<Long,List<SysOrg>>。
	 * 
	 * @param rootId
	 * @param instList
	 * @return
	 */
	private Map<Long, List<SysOrg>> coverTreeData(Long rootId, List<SysOrg> instList) {
		Map<Long, List<SysOrg>> dataMap = new HashMap<Long, List<SysOrg>>();
		dataMap.put(rootId.longValue(), new ArrayList<SysOrg>());
		if (instList != null && instList.size() > 0) {
			for (SysOrg sysOrg : instList) {
				long parentId = sysOrg.getOrgSupId().longValue();
				if (dataMap.get(parentId) == null) {
					dataMap.put(parentId, new ArrayList<SysOrg>());
				}
				dataMap.get(parentId).add(sysOrg);
			}
		}
		return dataMap;
	}

	/**
	 * 转化数所格式,将原如list 重新按树形结构排序
	 * 
	 * @param rootId
	 * @param instList
	 * @return
	 */
	public List<SysOrg> coverTreeList(Long rootId, List<SysOrg> instList) {

		Map<Long, List<SysOrg>> dataMap = coverTreeData(rootId, instList);

		List<SysOrg> list = new ArrayList<SysOrg>();

		list.addAll(getChildList(rootId, dataMap));

		return list;
	}

	/**
	 * 转化数所格式,将原如list 重新按树形结构排序
	 * 
	 * @param parentId
	 * @param dataMap
	 * @return
	 */
	private List<SysOrg> getChildList(Long parentId, Map<Long, List<SysOrg>> dataMap) {
		List<SysOrg> list = new ArrayList<SysOrg>();

		List<SysOrg> orgList = dataMap.get(parentId.longValue());
		if (orgList != null && orgList.size() > 0) {
			for (SysOrg sysOrg : orgList) {
				list.add(sysOrg);
				List<SysOrg> childList = getChildList(sysOrg.getOrgId(), dataMap);
				list.addAll(childList);
			}
		}
		return list;
	}

	public List<SysOrg> getByUserIdAndDemId(Long userId, Long demId) {
		return dao.getByUserIdAndDemId(userId, demId);
	}

	

	/**
	 *拖动组织进行排序。
	 * @param targetId 目标组织ID
	 * @param dragId 	拖动的组织ID
	 * @param moveType 拖动类型 (prev,next,inner);
	 */
	public void move(Long targetId, Long dragId, String moveType) {
		SysOrg target = dao.getById(targetId);
		SysOrg dragged = dao.getById(dragId);
		
		if(!target.getDemId().equals(dragged.getDemId()))
			return;
			
		String nodePath=dragged.getPath();
		//根据拖动节点的路径找到其下所有的子组织。
		List<SysOrg> list=dao.getByOrgPath(nodePath);
		
		for(SysOrg org:list){
			//向目标节点的前后拖动。
			if ("prev".equals(moveType) || "next".equals(moveType)) {
				String targetPath=target.getPath();
				String parentPath=targetPath.endsWith(".")?targetPath.substring(0,targetPath.length()-1):targetPath;
				//这个路径尾部带 "." 。
				parentPath=parentPath.substring(0,parentPath.lastIndexOf(".")+1) ;
				
				if(org.getOrgId().equals(dragId)){
					org.setOrgSupId(target.getOrgSupId());
					org.setPath(parentPath + dragId +".");
				}
				else{
					String path = org.getPath();
					String tmpPath =parentPath + dragId +"." +   path.replaceAll(nodePath, "");
					org.setPath(tmpPath);
				}
				
				if ("prev".equals(moveType)) {
					org.setSn(target.getSn() - 1);
				} else {
					org.setSn(target.getSn() + 1);
				}
			}
			//作为目标节点的子节点。
			else{
				//如果是被拖动的节点。
				////需改父节点
				if(org.getOrgId().equals(dragId)){
					//修改拖动的分类对象
					org.setOrgSupId(targetId);
					// 修改nodepath
					org.setPath(target.getPath() + org.getOrgId() + ".");					
				} else {
					// 带点的路径
					String path = org.getPath();
					// 替换父节点的路径。
					String tmpPath = path.replaceAll(nodePath, "");
					// 新的父节路径
					String targetPath = target.getPath();
					// 新的父节点 +拖动的节点id + 后缀
					String tmp = targetPath + dragged.getOrgId() + "." + tmpPath;
					org.setPath(tmp);					
				}
			}
			dao.update(org);
		}
	}
	
	public static void main(String[] args) {
		String path="1.2.3.";
		path=path.endsWith(".")?path.substring(0,path.length()-1):path;
		String subPath=path.substring(0,path.lastIndexOf(".")+1) ;
		logger.info(subPath);
	}
	
	/**
	 * 添加组织。
	 * @param sysOrg
	 * @throws Exception
	 */
	public void addOrg(SysOrg sysOrg) throws Exception{
		sysOrg.setSn(sysOrg.getOrgId());
		//添加组织
		dao.add(sysOrg);
//组织下直接添加负责人,无法确定用户是哪个岗位下的	
//		String ownerId=sysOrg.getOwnUser();
//		if(StringUtil.isEmpty(ownerId)) return ;
//		//添加组织负责人。
//		String[] aryUserId = ownerId.split(",");
//		for (int i = 0; i < aryUserId.length; i++) {
//			String userId=aryUserId[i];
//			if(StringUtil.isEmpty(userId)) continue;
//			Long lUserId=Long.parseLong(userId);
//			UserPosition userPosition = new UserPosition();
//			userPosition.setUserPosId(UniqueIdUtil.genId());
//			userPosition.setOrgId(sysOrg.getOrgId());
//			userPosition.setIsCharge(SysUserOrg.CHARRGE_YES);
//			userPosition.setUserId(lUserId);
//			//sysUserOrgDao.updNotPrimaryByUserId(lUserId);
//			//一个人只有一个主岗位
//			//一个组织可以有多个组织负责人
//			//userPosition.setIsPrimary(SysUserOrg.PRIMARY_YES);
//			userPositionDao.add(userPosition);
//		}
	}
	
	/**
	 * 更新组织数据。
	 * @param sysOrg
	 * @throws Exception
	 */
	public void updOrg(SysOrg sysOrg) throws Exception{
		Long orgId=sysOrg.getOrgId();
		//添加组织
		dao.update(sysOrg);
		//删除组织负责人。
		/*userPositionDao.delChargeByOrgId(orgId);
		
		String ownerId=sysOrg.getOwnUser();
		if(StringUtil.isEmpty(ownerId)) return ;
		//添加组织负责人。
		String[] aryUserId = ownerId.split(",");
		for (int i = 0; i < aryUserId.length; i++) {
			String userId=aryUserId[i];
			if(StringUtil.isEmpty(userId)) continue;
			Long lUserId=Long.parseLong(userId);
			UserPosition userPosition = new UserPosition();
			userPosition.setUserOrgId(UniqueIdUtil.genId());
			userPosition.setOrgId(orgId);
			userPosition.setIsCharge(SysUserOrg.CHARRGE_YES);
			userPosition.setUserId(lUserId);
			//sysUserOrgDao.updNotPrimaryByUserId(lUserId);
			userPosition.setIsPrimary(SysUserOrg.PRIMARY_YES);
			userPositionDao.add(userPosition);
		}*/
	}
	
	/**
	 * 根据用户取得主组织。
	 * @param userId
	 * @return
	 */
	public SysOrg getPrimaryOrgByUserId(Long userId){
		return dao.getPrimaryOrgByUserId(userId);
	}
	
	/**
	 * 根据路径得到组织集合 
	 * @param path
	 * @return
	 */
	public List<SysOrg> getByOrgPath(String path){	
		return dao.getByOrgPath(path);
	}
	
	/**
	 * 获取组织最近一级带有类型的父节点 
	 * @param path
	 * @return
	 */
	public SysOrg getParentWithType(SysOrg sysOrg){		
		Long parentOrgId=sysOrg.getOrgSupId();
		if(parentOrgId.equals(Long.parseLong("1"))) return null;
		SysOrg parentOrg=dao.getById(parentOrgId);
		if(parentOrg==null ) return null;		
		if(parentOrg.getOrgType()!=null && sysOrgTypeDao.getById(parentOrg.getOrgType())!=null){
			return parentOrg;
		}	else{
			parentOrg=getParentWithType(parentOrg);
		}			
		return parentOrg;
	}
	/**
	 * 根据指定组织和组织类型，往上找到与指定组织类型相同或比指定组织类型更大一级的父组织 
	 * @param sysOrg 当前组织
	 * @return sysOrgType 要查找的组织类型
	 */
	public SysOrg   getParentWithTypeLevel(SysOrg sysOrg,SysOrgType	sysOrgType){	
		 //获取指定部门类型的类型记录
		SysOrg parentOrg=getParentWithType(sysOrg);  //查找所需的父级部门		
		if(parentOrg==null) return parentOrg;
		SysOrgType	currentSysOrgType=sysOrgTypeDao.getById(parentOrg.getOrgType());
		//如果返回的类型比指定类型还小的话，继续查询
		if(currentSysOrgType!=null && sysOrgType.getLevels()<currentSysOrgType.getLevels()){  
			parentOrg=getParentWithTypeLevel(parentOrg,sysOrgType);	
		}
		return parentOrg;
	}
	
	/**
	 * 根据用户ID获取默认的的组织。
	 * @param userId
	 * @return
	 */
	public SysOrg getDefaultOrgByUserId(Long userId){
		
		List<UserPosition>  list= userPositionDao.getByUserId(userId);
		Long orgId=0L;
		//个人不属于任何一个部门。
		if(BeanUtils.isEmpty(list)) return null;
		if(list.size()==1) {
			orgId=list.get(0).getOrgId();
		}else{
			//获取主要的组织。
			for(UserPosition userPosition:list){
				if(userPosition.getIsPrimary()==1){
					orgId=userPosition.getOrgId();
					break;
				}
			}
			//没有获取到主组织，从列表中获取一个组织作为当前组织。
			if(orgId==0L) orgId=list.get(0).getOrgId();
		}
		return  dao.getById(orgId);
	}
	
	//更新sn
	public void updSn(Long orgId, long sn) {
		dao.updSn(orgId,sn);
		
	}
	/**
	 * 根据上级组织ID获取组织列表。
	 * @param userId
	 * @return
	 */
	public List<SysOrg> getOrgByOrgSupId(Long orgSupId){
		List<SysOrg> list= dao.getOrgByOrgSupId(orgSupId);
		return list;
	}
	
	/**
	 * 根据上级组织ID获取此上级组织下level级组织列表，level为配置参数。
	 * @param orgSupId
	 * @param demId
	 * @return 
	 */
	public List<SysOrg> getOrgByOrgSupIdAndLevel(Long orgSupId){
		List<SysOrg> ChildList= dao.getOrgByOrgSupId(orgSupId);
		Properties  prop = (Properties)AppUtil.getBean("configproperties");
		int level =SysPropertyService.getIntByAlias("orgExpandLevel", 0);
		int childSize=ChildList.size();
		if(level==0){
			for(int i=0;i<childSize;i++){
				List<SysOrg> MoreList=getOrgByOrgSupIdAndLevel(ChildList.get(i).getOrgId());
				ChildList.addAll(MoreList);
			}
		} 
		if(level>1){
			level--;
			for(int i=0;i<childSize;i++){
				List<SysOrg> MoreList = getOrgByOrgSupIdAndLevel(ChildList.get(i).getOrgId(),level);
				ChildList.addAll(MoreList)	;
			}
		}
		return ChildList;
	}

	public List<SysOrg> getOrgByOrgSupIdAndLevel(Long orgSupId,int level) {
		List<SysOrg> ChildList = new ArrayList<SysOrg>();
		if(level>0){
			ChildList= dao.getOrgByOrgSupId(orgSupId);
			level--;
			int childSize=ChildList.size();
			for(int i=0;i<childSize;i++){
				List<SysOrg> MoreList=getOrgByOrgSupIdAndLevel(ChildList.get(i).getOrgId(),level);
				ChildList.addAll(MoreList)	;
			}
		}
		return ChildList;
	}
	
	/**
	 * 根据分组获取组织列表。
	 * @param groupId
	 * @return
	 */
	public List<SysOrg> getByOrgMonGroup(Long groupId){
		return dao.getByOrgMonGroup(groupId);
	}

	
	/**
	 * mobile 取组织结构
	 * @param orgid
	 * @param orgName
	 * @return
	 */
	public List<SysOrg> getOrgForMobile(QueryFilter filter){
		return  dao.getBySqlKey("getBySupId4MobileOrg", filter);
	}
	public List<SysOrg> getByOrgType(Long orgType) {
		return  dao.getByOrgType(orgType);
	}
	
	private Map<String,SysOrg> convertToMap(List<SysOrg> companyList){
		Map<String,SysOrg> map=new HashMap<String, SysOrg>();
		for(SysOrg org:companyList){
			map.put(org.getOrgId().toString(), org);
		}
		return map;
	}
	
	/**
	 * 更新组织的分公司字段。
	 */
	public void updCompany(){
		//获取所有的分公司
		List<SysOrg> companyList= sysOrgTacticService.getSysOrgListByOrgTactic();
		Map<String,SysOrg> companyMap=convertToMap(companyList);
		List<SysOrg> list= dao.getAll();
		for(SysOrg sysOrg:list){
			String path=sysOrg.getPath();
			path= StringUtil.trimPrefix(path, SysOrg.BEGIN_ORGID +".");
			path= StringUtil.trimPrefix(path, ".");
			String[] aryPath=path.split("[.]");
			for(int i=aryPath.length-1;i>=0;i--){
				if(companyMap.containsKey(aryPath[i])){
					SysOrg org= dao.getById(new Long(aryPath[i]));
					sysOrg.setCompanyId(org.getOrgId());
					sysOrg.setCompany(org.getOrgName());
					dao.update(sysOrg);
					break;
				}
			}
		}
		
	}
	/**
	 * 获取所有分公司
	 * @return
	 */
	public List<Map<String,Object>> getCompany(){
		return dao.getCompany();
	}
}

