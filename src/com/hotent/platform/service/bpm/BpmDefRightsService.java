package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.BpmDefRightsDao;
import com.hotent.platform.dao.system.GlobalTypeDao;
import com.hotent.platform.model.bpm.BpmDefRights;
import com.hotent.platform.model.system.GlobalType;

/**
 * 对象功能:流程定义权限明细 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-03-19 09:00:53
 */
@Service
public class BpmDefRightsService extends BaseService<BpmDefRights>
{
	@Resource
	private BpmDefRightsDao dao;
	@Resource
	private GlobalTypeDao globalTypeDao;
	
	public BpmDefRightsService()
	{
	}
	@Override
	protected IEntityDao<BpmDefRights, Long> getEntityDao() 
	{
		return dao;
	}
	/**
	 * 根据流程定义ID和权限类型互获取权限数据。
	 * @param defId
	 * @param rightType
	 * @return
	 */
	public List<BpmDefRights> getDefRight(Long defId,Short rightType){
		return dao.getDefRight(defId,rightType);
	}
	
	/**
	 * 根据分类和权限类型获取权限数据。
	 * @param typeId
	 * @param rightType
	 * @return
	 */
	public List<BpmDefRights> getTypeRight(Long typeId,Short rightType){
		return dao.getTypeRight(typeId,rightType);
	}
	
	/**
	 * 获取流程权限。
	 * @param assignId	流程定义id或流程分类ID,具体由assignType决定
	 * @param assignType	0，流程定义ID,1,流程分类ID。
	 * @return
	 */
	public Map<String,String>  getRights(String assignId,int assignType){
		Map<String,String> rightsMap=new HashMap<String, String>();
		List<BpmDefRights> list=null;
		if(assignType==BpmDefRights.SEARCH_TYPE_DEF){
			list =dao.getByDefKey(assignId);
		}
		else{
			list =dao.getByTypeId(Long.parseLong( assignId));
		}
		List<BpmDefRights> all=new ArrayList<BpmDefRights>();
		List<BpmDefRights> user=new ArrayList<BpmDefRights>();
		List<BpmDefRights> role=new ArrayList<BpmDefRights>();
		List<BpmDefRights> org=new ArrayList<BpmDefRights>();
		List<BpmDefRights> position=new ArrayList<BpmDefRights>();
		List<BpmDefRights> posGroup=new ArrayList<BpmDefRights>();
		List<BpmDefRights> job=new ArrayList<BpmDefRights>();
		List<BpmDefRights> orgGrant=new ArrayList<BpmDefRights>();
		
		for(BpmDefRights rights:list){
			switch (rights.getRightType()) {
				case BpmDefRights.RIGHT_TYPE_ALL:
					all.add(rights);
					break;
				case BpmDefRights.RIGHT_TYPE_USER:
					user.add(rights);
					break;
				case BpmDefRights.RIGHT_TYPE_ROLE:
					role.add(rights);
					break;
				case BpmDefRights.RIGHT_TYPE_ORG:
					org.add(rights);
					break;
				case BpmDefRights.RIGHT_TYPE_POSITION:
					position.add(rights);
					break;
				case BpmDefRights.RIGHT_TYPE_ORG_GRANT:
					orgGrant.add(rights);
					break;
			}
		}
		String allData=coverList2Json(all);
		String userData=coverList2Json(user);
		String roleData=coverList2Json(role);
		String orgData=coverList2Json(org);
		String positionData=coverList2Json(position);
		String posGroupData=coverList2Json(posGroup);
		String jobData=coverList2Json(job);
		String orgGrantData=coverList2Json(orgGrant);
		rightsMap.put("all", allData);
		rightsMap.put("user", userData);
		rightsMap.put("role", roleData);
		rightsMap.put("org", orgData);
		rightsMap.put("position", positionData);
		rightsMap.put("posGroup", posGroupData);
		rightsMap.put("job", jobData);
		rightsMap.put("orgGrant", orgGrantData);
		return rightsMap;
	}
	
	/**
	 * 将流程权限列表转换成为map对象。
	 * 
	 * @param list
	 * @return
	 */
	public String coverList2Json(List<BpmDefRights> list){
		if(BeanUtils.isEmpty(list)) return "";
		JSONArray jarray = new JSONArray();  
		
		for(BpmDefRights r:list){
			JSONObject jobject = new JSONObject();
			jobject.accumulate("id", r.getOwnerId()).accumulate("name", r.getOwnerName());
			jarray.add(jobject);
		}
		return jarray.toString();
	}
	
	/**
	 * 将流程权限列表转换成为map对象。
	 * 
	 * @param list
	 * @return
	 */
	public Map<String,String> coverList2Map(List<BpmDefRights> list){
		Map<String,String> m=new HashMap<String,String>();
		if(BeanUtils.isEmpty(list)) return m;
		
		String ownerId ="";
		String ownerName="";
		for(BpmDefRights r:list){
			ownerId+=r.getOwnerId()+",";
			ownerName+=r.getOwnerName()+",";
		}
		if(ownerId.length()>0)ownerId=ownerId.substring(0,ownerId.length()-1);
		if(ownerName.length()>0)ownerName=ownerName.substring(0,ownerName.length()-1);
		m.put("ownerId", ownerId);
		m.put("ownerName", ownerName);
		
		return m;
	}
	
	public void saveRights(String assignId,int assignType, String[] rightType,String[] ownerId,
			String[] ownerName, int isChange) throws Exception{
		if(assignType==BpmDefRights.SEARCH_TYPE_DEF){
			dao.delByDefKey(assignId);
		}
		/*else{
			if(isChange==1){
				GlobalType gt=globalTypeDao.getById(new Long(assignId));
				String nodePath=gt.getNodePath();
				List<GlobalType> globalTypelist= globalTypeDao.getByNodePath(nodePath);
				for(GlobalType glbtype:globalTypelist){
					dao.delByTypeId(glbtype.getTypeId());
				}
			}else{
				dao.delByTypeId(new Long(assignId));
			}
		}*/
		List<BpmDefRights> rightList=coverTypeRights(assignId,assignType, rightType, ownerId, ownerName, isChange);
		add(rightList);
	}
	
	/**
	 * 添加权限。
	 * @param rightList
	 */
	public void add(List<BpmDefRights> rightList){
		if(rightList==null||rightList.size()==0)return;
		for(BpmDefRights r:rightList){
			dao.add(r);
		}
	}
	
	private List<BpmDefRights> coverTypeRights(String assignId,int assignType, String[] rightType,String[] ownerId,
			String[] ownerName, int isChange) throws Exception{
		
		if(ownerId==null||ownerId.length==0)return null;
	
		List<BpmDefRights> list=new ArrayList<BpmDefRights>();
		//对权限类型进行循环。
		for(int i=0;i<rightType.length;i++){
			String right=rightType[i];
			String[] ids=ownerId[i].split(",");
			String[] names=ownerName[i].split(",");
			if(BeanUtils.isEmpty(ids)) continue;
			
			for(int j=0;j<ids.length;j++){
				String id=ids[j];
				String name=names[j];
				if(StringUtil.isEmpty(id)) continue;

				if(assignType==BpmDefRights.SEARCH_TYPE_DEF){
					BpmDefRights defRight=new BpmDefRights();
					defRight.setId(UniqueIdUtil.genId());
					defRight.setDefKey(assignId);
					defRight.setSearchType(BpmDefRights.SEARCH_TYPE_DEF);
					defRight.setRightType(new Short(right));
					defRight.setOwnerId(new Long(id));
					defRight.setOwnerName(name);
					list.add(defRight);
				}
				/*else{
					if(isChange==1){
						GlobalType gt=globalTypeDao.getById(new Long(assignId));
						String nodePath=gt.getNodePath();
						List<GlobalType> globalTypelist= globalTypeDao.getByNodePath(nodePath);
						for(GlobalType glbtype:globalTypelist){
							BpmDefRights defRight=new BpmDefRights();
							defRight.setId(UniqueIdUtil.genId());
							defRight.setFlowTypeId(glbtype.getTypeId());
							defRight.setSearchType(BpmDefRights.SEARCH_TYPE_GLT);
							defRight.setRightType(new Short(right));
							defRight.setOwnerId(new Long(id));
							defRight.setOwnerName(name);
							list.add(defRight);
						}
					}else{
						BpmDefRights defRight=new BpmDefRights();
						defRight.setId(UniqueIdUtil.genId());
						defRight.setFlowTypeId(new Long(assignId));
						defRight.setSearchType(BpmDefRights.SEARCH_TYPE_GLT);
						defRight.setRightType(new Short(right));
						defRight.setOwnerId(new Long(id));
						defRight.setOwnerName(name);
						list.add(defRight);
					}
				}*/
			}
			
		}
		return list;
	}
	public void saveRights(String assignId,int assignType, String[] rightType,String[] owner, int isChange) throws Exception{
		if(assignType==BpmDefRights.SEARCH_TYPE_DEF){
			String[] assignIds=assignId.split(",");
			for (String defKey : assignIds) {
				dao.delByDefKey(defKey);
			}
		}
	/*	else{
			if(isChange==1){
				GlobalType gt=globalTypeDao.getById(new Long(assignId));
				String nodePath=gt.getNodePath();
				List<GlobalType> globalTypelist= globalTypeDao.getByNodePath(nodePath);
				for(GlobalType glbtype:globalTypelist){
					dao.delByTypeId(glbtype.getTypeId());
				}
			}else{
				dao.delByTypeId(new Long(assignId));
			}
		}*/
		List<BpmDefRights> rightList=coverTypeRights(assignId,assignType, rightType, owner, isChange);
		add(rightList);
	}
	
	private List<BpmDefRights> coverTypeRights(String assignId,int assignType, String[] rightType,String[] owner, int isChange) throws Exception{
		
		if(owner==null||owner.length==0)return null;
	
		String[] assignIds=assignId.split(",");
		List<BpmDefRights> list=new ArrayList<BpmDefRights>();
		//对权限类型进行循环。
		for(int i=0;i<rightType.length;i++){
			String right=rightType[i];
			String ownerObj = owner[i];
			if(StringUtil.isEmpty(ownerObj))continue;
			JSONArray jarray = JSONArray.fromObject(ownerObj);
			int size = jarray.size(); 
			if(size==0) continue;
			
			for(int j=0;j<size;j++){
				JSONObject jObject = (JSONObject)jarray.get(j); 
				String id = jObject.getString("id");
				String name = jObject.getString("name");
				if(StringUtil.isEmpty(id)) continue;

				if(assignType==BpmDefRights.SEARCH_TYPE_DEF){
					for (String assignid:assignIds) {
						BpmDefRights defRight  =setBpmDefRights(assignType, assignid,null, new Short(right), new Long(id), name);
						list.add(defRight);
					}
					
				}
		/*		else{
					if(isChange==1){
						GlobalType gt=globalTypeDao.getById(new Long(assignId));
						String nodePath=gt.getNodePath();
						List<GlobalType> globalTypelist= globalTypeDao.getByNodePath(nodePath);
						for(GlobalType glbtype:globalTypelist){
							BpmDefRights defRight  =setBpmDefRights(assignType,null,glbtype.getTypeId(), new Short(right), new Long(id), name);
							list.add(defRight);
						}
					}else{
						BpmDefRights defRight  =setBpmDefRights(assignType,null,new Long(assignId), new Short(right), new Long(id), name);
						list.add(defRight);
					}
				}*/
			}
			
		}
		return list;
	}

	private BpmDefRights setBpmDefRights(int assignType,String defKey, Long flowTypeId,Short rightType,Long ownerId,String ownerName){
		BpmDefRights defRight=new BpmDefRights();
		defRight.setId(UniqueIdUtil.genId());
	
		if(assignType==BpmDefRights.SEARCH_TYPE_DEF){
			defRight.setSearchType(BpmDefRights.SEARCH_TYPE_DEF);
			defRight.setDefKey(defKey);		
		}else{
			defRight.setSearchType(BpmDefRights.SEARCH_TYPE_GLT);
			defRight.setFlowTypeId(flowTypeId);
		}	
		defRight.setRightType(rightType);
		defRight.setOwnerId(ownerId);
		defRight.setOwnerName(ownerName);
		return defRight;
	}
	
}
