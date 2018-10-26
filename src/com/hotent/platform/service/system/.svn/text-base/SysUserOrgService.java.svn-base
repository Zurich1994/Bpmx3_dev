package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.platform.dao.system.PositionDao;
import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.dao.system.SysOrgTypeDao;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.dao.system.UserPositionDao;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysOrgType;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SysUserOrg;
import com.hotent.platform.model.system.UserPosition;

/**
 * 对象功能:用户所属组织或部门 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-12-07 18:23:24
 */
@Service
public class SysUserOrgService extends BaseService<UserPosition>
{
//	@Resource
//	private SysUserOrgDao dao;
	
	//删掉了sys_user_org表，实体改用sys_user_pos
	@Resource
	private UserPositionDao dao;
	@Resource
	private SysOrgDao sysOrgDao;
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private PositionDao positionDao;
	@Resource
	private SysOrgTypeDao sysOrgTypeDao;
	protected static Logger logger = LoggerFactory.getLogger(SysUserOrgService.class);
	
	public SysUserOrgService()
	{
	}
	
	@Override
	protected IEntityDao<UserPosition, Long> getEntityDao() 
	{
		return dao;
		
		
	}
	
	/**
	 * 对象功能：查找该条件的用户组织的实体
     * 创建时间:2011-11-08 12:04:22 
	 */
	@Deprecated
	public SysUserOrg getUserOrgModel(Long userId ,Long orgId)	
	{
		return new SysUserOrg();
		//return dao.getUserOrgModel(userId,orgId);
	}
	
	/**
	 * 根据用户查询用户所在的组织。
	 * 同时查询出负责人。
	 * @param userId	用户ID
	 * @return
	 */
	public List<SysUserOrg> getOrgByUserId(Long userId){
		List<SysUserOrg> list=new ArrayList<SysUserOrg>();
		List<UserPosition> upList=dao.getByUserId(userId);
		
		for(SysUserOrg sysUserOrg:upList){
			Long orgId=sysUserOrg.getOrgId();
			SysOrg sysOrg=getChageNameByOrgId(orgId);
			sysUserOrg.setChargeName(sysOrg.getOwnUserName());
		}
		return list;
	}
	
	
	
	/**
	 * 根据组织id获取负责人。
	 * <br>
	 * 返回组织对象，对象有负责人id和负责人名字。<br>
	 * 多个负责人则返回ID串
	 * @param orgId		组织ID
	 * @return
	 */
	public SysOrg getChageNameByOrgId(Long orgId){
		SysOrg sysOrg=new SysOrg();      
		List<UserPosition> chargeList= dao.getChargeByOrgId(orgId);
		
		String chargeId="";
		String chargeName="";
		
		for(SysUserOrg charge:chargeList){
			chargeId+=charge.getUserId() +",";
			chargeName+=charge.getUserName() +",";
		}
		
		if(chargeName.length()>0){
			chargeId=chargeId.substring(0,chargeId.length()-1);
			chargeName=chargeName.substring(0,chargeName.length()-1);
		}
		sysOrg.setOwnUser(chargeId);
		sysOrg.setOwnUserName(chargeName);//多个负责人则返回ID串
		return sysOrg;
	}
	
//	/**
//	 * 存储用户和组织的关系。
//	 * <pre>
//	 * 1.清除用户和组织的关系。
//	 * 2.重新添加用户和组织的关系，根据客户段是否主组织和主管。
//	 * </pre>
//	 * @param userId
//	 * @param aryOrgIds
//	 * @param primaryOrgId
//	 * @param aryOrgCharge
//	 * @throws Exception
//	 */
//	public void  saveUserOrg(Long userId,Long[] aryOrgIds,Long primaryOrgId,Long[] aryOrgCharge ) throws Exception{
//		dao.delByUserId(userId);
//		
//		if(BeanUtils.isEmpty(aryOrgIds)) return ;
//		
//		for(Long orgId :aryOrgIds){
//			SysUserOrg sysUserOrg=new SysUserOrg();
//			sysUserOrg.setUserOrgId(UniqueIdUtil.genId());
//			sysUserOrg.setUserId(userId);
//			sysUserOrg.setOrgId(orgId);
//			if(primaryOrgId!=null && primaryOrgId.equals(orgId)){
//				sysUserOrg.setIsPrimary(SysUserOrg.PRIMARY_YES);
//			}
//			if(BeanUtils.isNotEmpty(aryOrgCharge)) {
//				for(Long tmpOrgId:aryOrgCharge){
//					if(tmpOrgId!=null && orgId.equals(tmpOrgId)){
//						sysUserOrg.setIsCharge(SysUserOrg.CHARRGE_YES);
//					}
//				}
//			}
//			dao.add(sysUserOrg);
//		}
//	}
	

	/**
	 * 根据用户与组织信息，添加sys_user_org关系信息
	 * @param Long orgId,当从组织管理进入新增用户时参数orgId为某个组织的Id、编辑用户时参数orgId设置为0，当从用户管理进入新增或编辑用户时参数orgId都设置为0
	 * @return
	 */	
//	public void addUserOrg(String[] orgIds,String orgIdPrimary,Long userId) throws Exception{	
//		
//		if(BeanUtils.isEmpty(orgIds)) return;
//		for(int i=0;i<orgIds.length;i++){				
//			if(StringUtil.isEmpty(orgIds[i])) continue;
//			addUser(Long.parseLong(orgIds[i]),orgIdPrimary,userId);											   								
//		}
//	}
	
	/**
	 * 根据用户与组织关系，添加用户和主要组织信息
	 * @param 
	 * @return
	 */	
//	@Deprecated
//	public void addUser(Long orgId,String orgIsPrimary,Long userId) throws Exception
//	{
//		SysUserOrg sysUserOrg=getUserOrgModel(userId,orgId);
//		if(sysUserOrg==null){
//			sysUserOrg=new SysUserOrg();
//			sysUserOrg.setUserOrgId(UniqueIdUtil.genId());
//			sysUserOrg.setOrgId(orgId);
//			sysUserOrg.setUserId(userId);
//			if(orgId.toString().equals(orgIsPrimary)){
//				sysUserOrg.setIsPrimary(SysUserOrg.PRIMARY_YES);
//			}
//			else{
//			    sysUserOrg.setIsPrimary(SysUserOrg.PRIMARY_NO);
//			}
//			add(sysUserOrg);
//		}
//		else{
//			String isPrimary="";
//			if(sysUserOrg.getIsPrimary()!=null){
//				isPrimary=sysUserOrg.getIsPrimary().toString();
//			}
//			if(!"1".equals(isPrimary)){//不是主要组织时			
//				if(orgId.toString().equals(orgIsPrimary)){
//					sysUserOrg.setIsPrimary(SysUserOrg.PRIMARY_YES);						    
//				}							
//			}
//			else{
//				if(!orgId.toString().equals(orgIsPrimary)){
//					sysUserOrg.setIsPrimary(SysUserOrg.PRIMARY_NO);
//				}
//				else{
//					sysUserOrg.setIsPrimary(SysUserOrg.PRIMARY_YES);
//				}
//			}
//			update(sysUserOrg);
//		}
//									
//	}
	
	

	
	/**
	 * 根据组织ID返回用户列表。
	 * @param filter
	 */
//	public List<SysUserOrg> getUserByOrgId(QueryFilter filter){
//		
//		return  dao.getUserByOrgId(filter);
//	} 

	/**
	 * 添加人员和组织的关系
	 *  @param Long[] userIds
	 *  @param Long orgId
	 */
	@Deprecated
	public void addOrgUser(Long[] userIds,Long orgId) throws Exception
	{
//		if(BeanUtils.isEmpty(userIds)||StringUtil.isEmpty(orgId.toString())) return;	
//		SysUserOrg sysUserOrg =null;
//		for (Long userId : userIds){
//			SysUserOrg userOrg= dao.getUserOrgModel(userId, orgId);
//			if(userOrg!=null) continue;
//			//更新用户其他的为非主组织。
//			dao.updNotPrimaryByUserId(userId);
//			sysUserOrg =new SysUserOrg();
//			sysUserOrg.setUserOrgId(UniqueIdUtil.genId());
//			sysUserOrg.setOrgId(orgId);
//			sysUserOrg.setUserId(userId);
//			sysUserOrg.setIsPrimary(sysUserOrg.PRIMARY_YES);
//			dao.add(sysUserOrg);
//		}
	}
	
	/**
	 * 设置为非主岗位。
	 * @param userPosId		主岗位ID
	 */
	public void setIsPrimary(Long userPosId){
		UserPosition userPosition =  dao.getById(userPosId);
		if( userPosition.getIsPrimary()==SysUserOrg.PRIMARY_NO){
			userPosition.setIsPrimary(SysUserOrg.PRIMARY_YES);
			dao.updNotPrimaryByUserId(userPosition.getUserId());
		}
		else{
			userPosition.setIsPrimary(UserPosition.PRIMARY_NO);
		}
		dao.update(userPosition);
	}
	
	/**
	 * 设置是否组织负责人。
	 * @param userPosId
	 */
	public void setIsCharge(Long userPosId){
		UserPosition userPosition =  dao.getById(userPosId);
		if( userPosition.getIsCharge()==SysUserOrg.CHARRGE_NO){
			userPosition.setIsCharge(SysUserOrg.CHARRGE_YES);
		}
		else{
			userPosition.setIsCharge(SysUserOrg.CHARRGE_NO);
		}
		dao.update(userPosition);
	}
	
	/**
	 * 设置是否组织管理员。
	 * 这个管理员负责管理此组织中的人员分配，授权等操作。
	 * <pre>
	 * 1.如果原来是管理员，就设置为非管理员。
	 * 2.如果原来不是管理员，就设置为管理员。
	 * </pre>
	 * @param userPosId
	 */
	public void setIsManage(Long userPosId){
		UserPosition userPosition =  dao.getById(userPosId);
		if( userPosition.getIsGradeManage()==SysUserOrg.IS_NOT_GRADE_MANAGE){
			userPosition.setIsGradeManage(SysUserOrg.IS_GRADE_MANAGE);
		}
		else{
			userPosition.setIsGradeManage(SysUserOrg.IS_NOT_GRADE_MANAGE);
		}
		dao.update(userPosition);
	}
	
//	public List<SysUser> getLeaderUserByUserId(Long userId){
//		List<SysUser> sysUsers=new ArrayList<SysUser>();
//		List<SysUserOrg> sysUserOrgs = this.getOrgByUserId(userId);
//		for(SysUserOrg sysUserOrg:sysUserOrgs){
//			List<SysUser> leaders;
//			if(sysUserOrg.getIsCharge()==SysUserOrg.CHARRGE_YES){
//				SysOrg sysOrg=sysOrgDao.getById(sysUserOrg.getOrgId());
//				//调用上级组织查询
//				leaders =  getLeaderUserByOrgId(sysOrg.getOrgSupId(),true);
//			}else{
//				SysOrg sysOrg = sysOrgDao.getById(sysUserOrg.getOrgId());
//				leaders = getLeaderUserByOrgId(sysOrg.getOrgId(),true);
//			}
//			sysUsers.addAll(leaders);
//		}
//		return sysUsers;
//	}
	
	public List<SysUser> getLeaderUserByOrgId(Long orgId,boolean upslope) {
		List<UserPosition> list= dao.getChargeByOrgId(orgId);
		if(BeanUtils.isNotEmpty(list)){
			List<SysUser> users=new ArrayList<SysUser>();
			for(SysUserOrg sysUserOrg:list){
				SysUser user = sysUserDao.getById(sysUserOrg.getUserId());
				users.add(user);
			}
			return users;
		} else {
			SysOrg sysOrg=sysOrgDao.getById(orgId);
			if(sysOrg==null)
				return new ArrayList<SysUser>();
			Long parentOrgId=sysOrg.getOrgSupId();
			SysOrg sysOrgParent=sysOrgDao.getById(parentOrgId);
			if (sysOrgParent == null) {
				return new ArrayList<SysUser>();
			} else {
				if(upslope){
					return getLeaderUserByOrgId(parentOrgId,true);
				}else{
					return new ArrayList<SysUser>();
				}
			}
		}
	}

	/**
	 * 获取用户的组织领导。
	 * <pre>
	 * 1.当前人是普通员工，则获取部门负责人，如果找不到，往上级查询负责人。
	 * 2.当前人员是部门负责人，则获取上级部门负责人，如果找不到则往上级查询负责人。
	 * </pre>
	 * @param userId	用户ID
	 * @return
	 */
	public List<TaskExecutor> getLeaderByUserId(Long userId){
		//获取当前用户ID
		Long startUserId=ContextUtil.getCurrentUserId();
		SysOrg sysOrg=null;
		//判断传送的userID是否发起人ID等同于当前用户ID
		if(userId!=startUserId){
			//根据传入用户ID的获取主组织信息
			sysOrg=sysOrgDao.getPrimaryOrgByUserId(userId);
		}else{
			sysOrg=ContextUtil.getCurrentOrg();
		}
		List<TaskExecutor> list=new ArrayList<TaskExecutor>();
		if(sysOrg==null){
			return list;
		}
		else{
			Long orgId=sysOrg.getOrgId();
			//根据用户获取的主岗位
			UserPosition up=dao.getPrimaryUserPositionByUserId(userId);
			if(up.getIsCharge()==UserPosition.CHARRGE_YES){
				SysOrg sysOrgParent=sysOrgDao.getById(orgId);
				//调用上级组织查询
				return getLeaderByOrgId(sysOrgParent.getOrgSupId());
			}
			else{
				return getLeaderByOrgId(orgId);
			}
		}
	
	}
	
	/**
	 * 获取用户的组织领导岗位。
	 * <pre>
	 * 1.当前人是普通员工，则获取部门负责人，如果找不到，往上级查询负责人岗位。
	 * 2.当前人员是部门负责人，则获取上级部门负责人，如果找不到则往上级查询负责人岗位。
	 * 这个方法写的有些问题，不是太严谨。
	 * </pre>
	 * @param userId	用户ID
	 * @return
	 */
	public String getLeaderPosByUserId(Long userId){
		//根据用户获取的主岗位
		UserPosition up=dao.getPrimaryUserPositionByUserId(userId);
		Long uId=0L;
		if(up==null)
			return null;
		else{
              SysOrg sysOrg=sysOrgDao.getById(up.getOrgId());
			if(up.getIsCharge()==SysUserOrg.CHARRGE_YES){//是负责人
				
				List<TaskExecutor> list=getLeaderByOrgId(sysOrg.getOrgSupId());
				//调用上级组织查询
				if(BeanUtils.isNotEmpty(list)){
					String tmpUserId=list.get(0).getExecuteId();
					uId=Long.parseLong(tmpUserId);
					return positionDao.getPosByUserId(uId).getPosName();
				}
			}
			else{
				List<TaskExecutor> list=getLeaderByOrgId(up.getOrgId());
				if(BeanUtils.isNotEmpty(list)){
					String tmpUserId=list.get(0).getExecuteId();
					uId=Long.parseLong(tmpUserId);
					return positionDao.getPosByUserId(uId).getPosName();
				}	
			}
		}
		return null;
	}
	
	/**
	 * 根据组织ID返回组织负责人。
	 * <pre>
	 * 1.根据组织id获取组织负责人。
	 * 2.如果组织负责人为空。
	 * 		则往上级查询查询领导负责人，知道找到位置。
	 * </pre>
	 * @param orgId
	 * @return
	 */
	public List<TaskExecutor> getLeaderByOrgId(Long orgId){
		List<UserPosition> list=dao.getChargeByOrgId(orgId);
		if(BeanUtils.isNotEmpty(list)){
			List<TaskExecutor> users=new ArrayList<TaskExecutor>();
			for(SysUserOrg sysUserOrg:list){
				TaskExecutor taskExecutor=TaskExecutor.getTaskUser(sysUserOrg.getUserId().toString(),sysUserOrg.getUserName());
				users.add(taskExecutor);
			}
			return users;
		} else {
			SysOrg sysOrg=sysOrgDao.getById(orgId);
			if(sysOrg==null)
				return new ArrayList<TaskExecutor>();
			Long parentOrgId=sysOrg.getOrgSupId();
			SysOrg sysOrgParent=sysOrgDao.getById(parentOrgId);
			if(sysOrgParent==null){
				return new ArrayList<TaskExecutor>();
			}
			else{
				return getLeaderByOrgId(parentOrgId);
			}
		}
	}
	
	/**
	 * 
	 */
	public String getOrgJsonByAuthOrgId(Long orgId){
		//根据授权组ID获取下级组
		SysOrg org = sysOrgDao.getById(orgId);
		if(org == null) return"";
	//	List<SysOrg> list= sysOrgDao.getByOrgPath(org.getPath());
		String json=getJson(org);
		return json;
	}
	
	
	private String getJson(SysOrg sysOrg){
		StringBuffer sb=new StringBuffer();
		List<SysOrg> list= sysOrgDao.getByOrgPath(sysOrg.getPath());
		//设置顶级组织。
		for(SysOrg org:list){
			org.setTopOrgId(sysOrg.getOrgId());
		}
		
		Long orgId=sysOrg.getOrgId();
		Long demId= sysOrg.getDemId();
		sb.append("{orgId:\""+ orgId +"\", orgName:\"" + sysOrg.getOrgName() +"\",demId:\""+ demId +"\",isRoot:\"0\",orgSupId:\""
					+sysOrg.getOrgSupId()+ "\",path:\""+sysOrg.getPath()+"\",topOrgId:" +orgId );
		List<SysOrg> tmpList=getByParentId(orgId,list);
		if(tmpList.size()>0){
			sb.append(",children:[");
			getChildren(sb,tmpList,list);
			
			sb.append("]");
		}
		sb.append("}");
		return sb.toString();
	}
	
	private String getIcon(Long orgType, String ctxPath){
		String nodeIcon="";		
		if(orgType==null) return "";
		SysOrgType  tempSysOrgType= sysOrgTypeDao.getById(orgType);
		if(tempSysOrgType!=null  && tempSysOrgType.getIcon()!=null)		
				nodeIcon=tempSysOrgType.getIcon();
		return ctxPath + nodeIcon;
	}
	
	private void getChildren(StringBuffer sb, List<SysOrg> list,List<SysOrg> allList){
		for(int i=0;i<list.size();i++){
			SysOrg sysOrg=list.get(i);
			Long orgId=sysOrg.getOrgId();
			Long demId= sysOrg.getDemId() ;
			sb.append("{orgId:\""+ orgId +"\",orgName:\"" + sysOrg.getOrgName()  +"\",demId:\""+ demId +"\",isRoot:0,orgSupId:\""
					+sysOrg.getOrgSupId()+ "\",path:\""+sysOrg.getPath()+"\",topOrgId:" +sysOrg.getTopOrgId());
			List<SysOrg> tmpList=getByParentId(orgId,allList);
			if(tmpList.size()>0){
				sb.append(",children:[");
				getChildren(sb,tmpList,allList);
				sb.append("]");
			}
			sb.append("}");
			if(i<list.size()-1){
				sb.append(",");
			}
		}
	}
	/**
	 * 根据父级Id查询下级的组织列表。
	 * @param parentId
	 * @param list
	 * @return
	 */
	private List<SysOrg> getByParentId(Long parentId,List<SysOrg> list){
		List<SysOrg> rtnList=new ArrayList<SysOrg>();
		for(Iterator<SysOrg> it=list.iterator();it.hasNext();){
			SysOrg org=it.next();
			if(parentId.equals(org.getOrgSupId())){
				rtnList.add(org);
				it.remove();
			}
		}
		return rtnList;
	}
	
	public static void main(String[] args) {
		String a="1,111,222,333";
	
		String c="1,111,222,333,445";
		String d="1,112";
		String e="1,112,223";
		
		String f="1,112,225";
		String g="1,113";
		String h="1,114";
		String k="1,115";
		List<String> list=new ArrayList<String>();
		list.add(a);
		
		list.add(c);
		list.add(d);
		list.add(e);
		list.add(f);
		list.add(g);
		list.add(h);
		list.add(k);
			
		List<String> out=new ArrayList<String>();
		String cur=list.get(0);
		out.add(list.get(0));
		for(int i=0;i<list.size();i++){
			String next=list.get(i);
			if(next.indexOf(cur)==-1){
				cur=next;
				out.add(cur);
			}
			
		}
		for(int i=0;i<out.size();i++){
			String str=out.get(i);
			logger.info(str);
		}
	}

	/**
	 *根据组织id，查询用户组织关系列表
	 * @param orgId 组织ID
	 * @return 
	 */
	public List<SysUserOrg> getByOrgId(Long orgId) {
		List<UserPosition> userPositionList = dao.getByOrgId(orgId);
		List<SysUserOrg> sysUserOrgList = new ArrayList<SysUserOrg> ();
		for (UserPosition up : userPositionList) {
			sysUserOrgList.add(up);
		}
		return sysUserOrgList;
	}
	
	public List<SysUserOrg> getUserByOrgIds(String orgIds){
		 List<SysUserOrg> sysUserOrgList=new ArrayList<SysUserOrg>();
		 List<UserPosition> userPositionList =dao.getUserByOrgIds(orgIds);
		 for(UserPosition up:userPositionList){
			 sysUserOrgList.add(up);
		 }
		 return sysUserOrgList;
	}

	/**
	 * 根据组织id获取组织负责人。
	 * @param orgId
	 * @return
	 */
	public List<UserPosition> getChargeByOrgId(Long orgId) {
		List<UserPosition> userPositionList=dao.getChargeByOrgId(orgId);
		return userPositionList;
	}
	
	/**
	 * 获取用户的主组织(没有主组织则返回任一组织)
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 */
	public SysUserOrg getPrimaryByUserId(Long userId) {
		UserPosition userPosition=dao.getPrimaryUserPositionByUserId(userId);
		 return userPosition;
	}
	
	/**
	 * 根据用户ID删除用户组织关系
	 * @param userId
	 *            用户ID
	 * @return
	 */
	public void delByUserId(Long userId){
		dao.delByUserId(userId);
	}
	
/**
 * 根据组织ID删除用户组织关系
 * 实际是删除岗位关系
 * @author hjx
 * @version 创建时间：2013-11-27  下午5:40:52
 * @param orgId
 */
	public void delByOrgId(Long orgId){
		dao.delByOrgId(orgId);
	}
	
	/**
	 * 根据组织路径，删除岗位关系
	 */
	public void delByOrgPath(String path){
		dao.delByOrgPath(path);
	}
	
	
	/**
	 * 根据用户ID获取可以负责的组织列表。
	 * @param userId
	 * @return
	 */
	public List<SysUserOrg> getChargeOrgByUserId(Long userId){
		List<UserPosition> userPositionList=dao.getChargeOrgByUserId(userId);
		List<SysUserOrg> sysUserOrgList=new ArrayList<SysUserOrg>();
		if(userPositionList==null||userPositionList.size()<=0)return null;
		for(UserPosition up:userPositionList){
			sysUserOrgList.add(up);
		}
		return sysUserOrgList;
	}
	
}    

