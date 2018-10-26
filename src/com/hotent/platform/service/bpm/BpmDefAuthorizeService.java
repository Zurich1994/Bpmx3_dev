package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.Collections;
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
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.bpm.BpmDefActDao;
import com.hotent.platform.dao.bpm.BpmDefAuthorizeDao;
import com.hotent.platform.dao.bpm.BpmDefAuthorizeTypeDao;
import com.hotent.platform.dao.bpm.BpmDefUserDao;
import com.hotent.platform.dao.bpm.BpmDefinitionDao;
import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.dao.system.SysRoleDao;
import com.hotent.platform.dao.system.UserPositionDao;
import com.hotent.platform.model.bpm.AuthorizeRight;
import com.hotent.platform.model.bpm.BpmDefAct;
import com.hotent.platform.model.bpm.BpmDefAuthorize;
import com.hotent.platform.model.bpm.BpmDefAuthorizeType;
import com.hotent.platform.model.bpm.BpmDefAuthorizeType.BPMDEFAUTHORIZE_RIGHT_TYPE;
import com.hotent.platform.model.bpm.BpmDefUser;
import com.hotent.platform.model.bpm.BpmDefUser.BPMDEFUSER_RIGHT_TYPE;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysOrgRole;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.UserPosition;
import com.hotent.platform.service.system.PositionService;
import com.hotent.platform.service.system.SysOrgRoleService;


/**
 * 对象功能:流程定义权限明细 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xucx
 * 创建时间:2014-03-05 10:10:50
 */
@Service
public class BpmDefAuthorizeService extends BaseService<BpmDefAuthorize>
{
	@Resource
	private BpmDefUserDao bpmDefUserDao;
	
	@Resource
	private BpmDefActDao bpmDefActDao;
	
	@Resource
	private BpmDefAuthorizeDao bpmDefAuthorizeDao;
	
	@Resource
	private SysRoleDao sysRoleDao;

	@Resource
	private SysOrgDao sysOrgDao;
	
	@Resource
	private UserPositionDao userPositionDao;
	
	@Resource
	private BpmDefinitionDao bpmDefinitionDao;	
	
	@Resource
	private BpmDefAuthorizeTypeDao bpmDefAuthorizeTypeDao;
	
	@Resource
	private SysOrgRoleService sysOrgRoleService;
	
	@Resource
	private PositionService positionService;
	
	
	public BpmDefAuthorizeService(){
	}
	
	@Override
	protected IEntityDao<BpmDefAuthorize, Long> getEntityDao() 
	{
		return bpmDefAuthorizeDao;
	}
	
	
	/**
	 * 获取流程分管授权列表信息
	 * @param queryFilter
	 * @return 
	 * List<BpmDefAuthorize>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmDefAuthorize> getAuthorizeListByFilter(QueryFilter queryFilter){
		List<BpmDefAuthorize> bpmDefAuthorizeList= new ArrayList<BpmDefAuthorize>();
		List<BpmDefAuthorize> list= bpmDefAuthorizeDao.getAll(queryFilter);
		for (BpmDefAuthorize me : list){
			Long id = me.getId();
			BpmDefAuthorize bpmDefAuthorize= getAuthorizeByMe(id,me,false);
			bpmDefAuthorizeList.add(bpmDefAuthorize);
		}
		return bpmDefAuthorizeList;
	}
	
	
	
	/**
	 * 获取流程分管授权所有信息
	 * @param id
	 * @return 
	 * BpmDefAuthorize
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmDefAuthorize getAuthorizeById(Long id){
		BpmDefAuthorize bpmDefAuthorize = getAuthorizeByMe(id,null,true);
		return bpmDefAuthorize;
	}
	

	/**
	 * 根据参数内容获取流程分管授权所有信息
	 * @param id
	 * @param me
	 * @param isNeedjson
	 * @return 
	 * BpmDefAuthorize
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmDefAuthorize getAuthorizeByMe(Long id,BpmDefAuthorize me,boolean isNeedjson){
		BpmDefAuthorize bpmDefAuthorize = null;
		if(id>0){
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("authorizeId", id);
			//获取主表信息
			if(me!=null&&me.getId()==id){
				bpmDefAuthorize = me;
			}else{
				bpmDefAuthorize = bpmDefAuthorizeDao.getById(id);
			}

			//获取授权类型信息
			List<BpmDefAuthorizeType> bpmDefAuthorizeTypeList = bpmDefAuthorizeTypeDao.getAuthorizeTypeByMap(params);
			bpmDefAuthorize.setBpmDefAuthorizeTypeList(bpmDefAuthorizeTypeList);
			
			//获取子表授权用户信息
			List<BpmDefUser> bpmDefUserList = bpmDefUserDao.getUserByMap(params);
			bpmDefAuthorize.setBpmDefUserList(bpmDefUserList);

			//获取子表授权流程信息
			List<BpmDefAct> bpmDefActList = bpmDefActDao.getActByMap(params);
			bpmDefAuthorize.setBpmDefActList(bpmDefActList);
			
			//子表信息需要转JSON数据时
			if(isNeedjson){
				String ownerNameJson = toOwnerNameJson(bpmDefUserList);
				bpmDefAuthorize.setOwnerNameJson(ownerNameJson);
				String  defNameJson = toDefNameJson(bpmDefActList);
				bpmDefAuthorize.setDefNameJson(defNameJson);
			}
		}
		return bpmDefAuthorize;
	}
	
	
	
	/**
	 * 按ID数据删除流程分管授权所有信息
	 * @param bpmDefAuthorize
	 * @return 
	 * Long
	 * @exception 
	 * @since  1.0.0
	 */
	public Long deleteAuthorizeByIds(Long[] lAryId){
		Long delNum = -1L;
		for (Long id : lAryId){
			//删除授权主表信息
			delNum = Long.valueOf(bpmDefAuthorizeDao.delById(id));
			//删除原来的从表信息，包括被授权用户子表及流程权限子表
			bpmDefUserDao.delByAuthorizeId(id);
			bpmDefActDao.delByAuthorizeId(id);
		}
		return delNum;
	}
	
	
	/**
	 * 保存或修改流程分管授权所有信息
	 * @param bpmDefAuthorize
	 * @return 
	 * Long
	 * @exception 
	 * @since  1.0.0
	 */
	public Long saveOrUpdateAuthorize(BpmDefAuthorize bpmDefAuthorize){
		//保存或修改流程分管授权主表信息（如果是修改的话先删除原来的从表信息，包括被授权用户子表及流程权限子表）
		Long authorizeId = bpmDefAuthorize.getId();
		if(authorizeId>0){
			bpmDefAuthorizeDao.update(bpmDefAuthorize);
			//删除原来的从表信息，包括被授权用户子表及流程权限子表
			bpmDefAuthorizeTypeDao.delByAuthorizeId(authorizeId);
			bpmDefUserDao.delByAuthorizeId(authorizeId);
			bpmDefActDao.delByAuthorizeId(authorizeId);
		}else{
			authorizeId = UniqueIdUtil.genId();
			bpmDefAuthorize.setId(authorizeId);
			bpmDefAuthorizeDao.add(bpmDefAuthorize);
		}

		
		//保存分管授权类型表信息
		String authorizeTypes = bpmDefAuthorize.getAuthorizeTypes();
		List<BpmDefAuthorizeType> bpmDefAuthorizeTypeList = toBpmDefAuthorizeTypeList(authorizeTypes, authorizeId);
		for (BpmDefAuthorizeType bpmDefAuthorizeType : bpmDefAuthorizeTypeList){
			bpmDefAuthorizeTypeDao.add(bpmDefAuthorizeType);
		}
		
		
		//保存流程分管授权用户子表信息
		String myOwnerNameJson = bpmDefAuthorize.getOwnerNameJson();
		List<BpmDefUser> bpmDefUserList = toBpmDefUserList(myOwnerNameJson, authorizeId);
		for (BpmDefUser bpmDefUser : bpmDefUserList){
			bpmDefUserDao.add(bpmDefUser);
		}
		
		//保存流程分管授权流程子表信息
		String myDefNameJson = bpmDefAuthorize.getDefNameJson();
		List<BpmDefAct> bpmDefActList = toBpmDefActList(myDefNameJson, authorizeId);
		for (BpmDefAct bpmDefAct : bpmDefActList){
			bpmDefActDao.add(bpmDefAct);
		}
		
		return authorizeId;
	}
	
	/**
	 * 授权类型转成授权类型列表
	 * @param authorizeTypes
	 * @param authorizeId
	 * @return 
	 * List<BpmDefAuthorizeType>
	 * @exception 
	 * @since  1.0.0
	 */
	private List<BpmDefAuthorizeType> toBpmDefAuthorizeTypeList(String authorizeTypes,Long authorizeId){
		List<BpmDefAuthorizeType> bpmDefAuthorizeTypeList = new ArrayList<BpmDefAuthorizeType>();
		if(authorizeId>0&&StringUtil.isNotEmpty(authorizeTypes)){
			String [] types =  authorizeTypes.split("\\,");
			for (String authorizeType : types){
				BpmDefAuthorizeType bpmDefAuthorizeType = new BpmDefAuthorizeType();
				bpmDefAuthorizeType.setId(UniqueIdUtil.genId());
				bpmDefAuthorizeType.setAuthorizeId(authorizeId);
				bpmDefAuthorizeType.setAuthorizeType(authorizeType);
				bpmDefAuthorizeTypeList.add(bpmDefAuthorizeType);
			}
		}		
		return bpmDefAuthorizeTypeList;
	}
	
	
	/**
	 * 授权人员列表转成授权人员JSON
	 * @param myBpmDefUserList
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String toOwnerNameJson( List<BpmDefUser> myBpmDefUserList){
		Map<String, Map<String, Object>> mapObj = new HashMap<String, Map<String, Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		//没有授权内容时，默认一个JSON
		if(myBpmDefUserList==null||myBpmDefUserList.size()<1){
			map.put("allJson", "N");
			map.put("userJson","[]");
			map.put("roleJson", "[]");
			map.put("orgJson", "[]");
			map.put("grantJson", "[]");
			map.put("positionJson", "[]");	
		}else{
			//按用户权限类型分类到LIST
			List<BpmDefUser> allList = new ArrayList<BpmDefUser>();
			List<BpmDefUser> userList = new ArrayList<BpmDefUser>();
			List<BpmDefUser> orgList = new ArrayList<BpmDefUser>();
			List<BpmDefUser> roleList = new ArrayList<BpmDefUser>();
			List<BpmDefUser> grantList = new ArrayList<BpmDefUser>();
			List<BpmDefUser> positionList = new ArrayList<BpmDefUser>();
			for (BpmDefUser bpmDefUser : myBpmDefUserList){
	            String rightType = bpmDefUser.getRightType();
				if(BPMDEFUSER_RIGHT_TYPE.ALL.equals(rightType)){
					allList.add(bpmDefUser);
					break;
				}else if(BPMDEFUSER_RIGHT_TYPE.USER.equals(rightType)){
					userList.add(bpmDefUser);
				}else if(BPMDEFUSER_RIGHT_TYPE.ROLE.equals(rightType)){
					roleList.add(bpmDefUser);
				}else if(BPMDEFUSER_RIGHT_TYPE.ORG.equals(rightType)){
					orgList.add(bpmDefUser);
				}else if(BPMDEFUSER_RIGHT_TYPE.GRANT.equals(rightType)){
					grantList.add(bpmDefUser);
				}else if(BPMDEFUSER_RIGHT_TYPE.POSITION.equals(rightType)){
					positionList.add(bpmDefUser);
				}
			}
			
			//按分类生成对象的MAP集合
			if(allList.size()>0){
				map.put("allJson", "Y");
				map.put("userJson","[]");
				map.put("roleJson", "[]");
				map.put("orgJson", "[]");
				map.put("grantJson", "[]");
				map.put("positionJson", "[]");
			}else{
				map.put("allJson", "N");
				if(userList.size()>0){
					JSONArray jsonarray = JSONArray.fromObject(userList); 
					map.put("userJson",jsonarray);
				}else{
					map.put("userJson","[]");
				}
				
				if(orgList.size()>0){
					JSONArray jsonarray = JSONArray.fromObject(orgList); 
					map.put("orgJson",jsonarray);
				}else{
					map.put("orgJson","[]");
				}
				
				if(roleList.size()>0){
					JSONArray jsonarray = JSONArray.fromObject(roleList); 
					map.put("roleJson",jsonarray);
				}else{
					map.put("roleJson","[]");
				}
				
				if(grantList.size()>0){
					JSONArray jsonarray = JSONArray.fromObject(grantList); 
					map.put("grantJson",jsonarray);
				}else{
					map.put("grantJson","[]");
				}
				
				if(positionList.size()>0){
					JSONArray jsonarray = JSONArray.fromObject(positionList); 
					map.put("positionJson",jsonarray);
				}else{
					map.put("positionJson","[]");
				}
			}
		}
		//转成JSON字符串
		mapObj.put("objJson", map);
		JSONObject obj = JSONObject.fromObject(mapObj);
		System.out.println(obj.toString());
		return obj.toString();
	}
	

	/**
	 * 授权人员JSON转成授权人员列表
	 * @param myOwnerNameJson
	 * @param authorizeId
	 * @return 
	 * List<BpmDefUser>
	 * 以下为JSON格式：
	 * *{ objJson:{
	 *   "allJson":"N",
	 *   "userJson":"[
	 *                  {\"ownerId\":\"10000011230209\",\"ownerName\":\"审查B\"},
	 *                  {\"ownerId\":\"10000011230207\",\"ownerName\":\"审查C\"}
	 *               ]",
	 *   "roleJson":"[
	 *   				{\"ownerId\":\"10000000080083\",\"ownerName\":\"角色管理员\"},
	 *   				{\"ownerId\":\"10000000440185\",\"ownerName\":\"部门负责人\"}
	 *   			 ]",
	 *   "orgJson":"[   
	 *                  {\"ownerId\":\"10000011230194\",\"ownerName\":\"采购部门B\"}
	 *              ]",
	 *   "grantJson":"[
	 *   				{\"ownerId\":\"10000016090060\",\"ownerName\":\"广州分公司\"}
	 *   			]",
	 *   "positionJson":"[
	 *   				{\"ownerId\":\"10000011230203\",\"ownerName\":\"采购部门A_采购专员\"},
	 *   				{\"ownerId\":\"37\",\"ownerName\":\"广州宏天白云区分公司销售部门_宏天软件工程师\"}
	 *   			]"
	 *   }
	 * }
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmDefUser> toBpmDefUserList(String myOwnerNameJson,Long authorizeId){
		List<BpmDefUser> myBpmDefUserList = new ArrayList<BpmDefUser>();
		if(StringUtil.isEmpty(myOwnerNameJson)){
			return myBpmDefUserList;
		}
		
		if(BeanUtils.isEmpty(authorizeId)){
			authorizeId = 0L;
		}
		
		JSONObject obj = JSONObject.fromObject(myOwnerNameJson);
		if(obj.containsKey("objJson")){
			JSONObject objJson = (JSONObject) obj.get("objJson");
			if(objJson.containsKey("allJson")){
				String allJson = objJson.getString("allJson");
				//授权为所有人
				if("Y".equals(allJson)){
					BpmDefUser  objUser = new BpmDefUser();
					objUser.setId(UniqueIdUtil.genId());
					objUser.setAuthorizeId(authorizeId);
					objUser.setOwnerId(0L);
					objUser.setOwnerName(BPMDEFUSER_RIGHT_TYPE.ALL);
					objUser.setRightType(BPMDEFUSER_RIGHT_TYPE.ALL);
					myBpmDefUserList.add(objUser);
				//有指定的对象
				}else{
					String[] strArray = new String[]{"userJson","roleJson","orgJson","grantJson","positionJson"};
					for (String jsonName : strArray) {
						getJsontoList(objJson, myBpmDefUserList, jsonName, authorizeId);
					}
				}
			}
		}
		return myBpmDefUserList;
	}
	
	
	/**
	 * 解析人员的JSON
	 * @param objJson
	 * @param myBpmDefUserList
	 * @param jsonName
	 * @param authorizeId
	 * @return 
	 * List<BpmDefUser>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmDefUser> getJsontoList(JSONObject objJson, List<BpmDefUser> myBpmDefUserList, String jsonName, Long authorizeId){
		if(objJson.containsKey(jsonName)){
			JSONArray myJsonArray = objJson.getJSONArray(jsonName);
			String rightType = "";
			//分配对象的类型
			if("orgJson".equals(jsonName)){
				rightType = BPMDEFUSER_RIGHT_TYPE.ORG;
			}else if("roleJson".equals(jsonName)){
				rightType = BPMDEFUSER_RIGHT_TYPE.ROLE;
			}else if("grantJson".equals(jsonName)){
				rightType = BPMDEFUSER_RIGHT_TYPE.GRANT;
			}else if("positionJson".equals(jsonName)){
				rightType = BPMDEFUSER_RIGHT_TYPE.POSITION;
			}else if("userJson".equals(jsonName)){
				rightType = BPMDEFUSER_RIGHT_TYPE.USER;
			}
			//没有类型的直接返回
			if("".equals(rightType)){
				return myBpmDefUserList;
			}
			//分析JSON,生成对应的BpmDefUser对象
			for(int i = 0; i < myJsonArray.size(); i++){     
	            JSONObject jsonObject = myJsonArray.getJSONObject(i);     
	            BpmDefUser bpmDefUser = new BpmDefUser();
	            if(jsonObject.containsKey("ownerId")){
	            	Long ownerId = jsonObject.getLong("ownerId");
	            	bpmDefUser.setOwnerId(ownerId);
	            }
	            if(jsonObject.containsKey("ownerName")){
	            	String ownerName = jsonObject.getString("ownerName");
	            	bpmDefUser.setOwnerName(ownerName);
	            }
	        //  BpmDefUser bpmDefUser = (BpmDefUser) JSONObject.toBean(jsonObject, BpmDefUser.class);
	            bpmDefUser.setId(UniqueIdUtil.genId());
	            bpmDefUser.setAuthorizeId(authorizeId);
	            bpmDefUser.setRightType(rightType);
				myBpmDefUserList.add(bpmDefUser);
	        } 
		}
		return myBpmDefUserList;
	}
	
	
	/**
	 * 授权流程列表转成授权流程JSON
	 * @param myBpmDefActList
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String toDefNameJson(List<BpmDefAct> myBpmDefActList){
		if(myBpmDefActList==null||myBpmDefActList.size()<1){
			return "{\"defArry\":[]}";
		}
		//反序输出
		Collections.reverse(myBpmDefActList);
		Map<String,Object> map = new HashMap<String,Object>();
		JSONArray jsonArray = JSONArray.fromObject(myBpmDefActList); 
		map.put("defArry", jsonArray);
		JSONObject obj = JSONObject.fromObject(map);
		System.out.println("toDefNameJson === "+obj.toString());
		return obj.toString();
	}
	
	
	
	/**
	 * 授权流程JSON转成授权流程列表
	 * @param myDefNameJson
	 * @param authorizeId
	 * @return 
	 * List<BpmDefAct>
	 * JSON格式：
	 * {"defArry":
	 * 	  [
	 * 	   { "defId":"10000018130014",
	 *         "defKey":"zchz",
	 *         "defName":"周程汇总",
	 *         "rightContent":{"m_edit":"Y","m_del":"N","m_start":"Y","m_set":"N"}
	 *       },
	 *       {"defId":"10000017980009",
	 *        "defKey":"csjdsz",
	 *        "defName":"测试节点设置",
	 *        "rightContent":{"m_edit":"Y","m_del":"N","m_start":"Y","m_set":"N"}
	 *       },
	 *       {"defId":"10000017860008",
	 *        "defKey":"gxzlc",
	 *        "defName":"共享子流程",
	 *        "rightContent":{"m_edit":"Y","m_del":"N","m_start":"Y","m_set":"N"}
	 *        }
	 *      ]
	 *  }
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmDefAct> toBpmDefActList(String myDefNameJson, Long authorizeId){
		List<BpmDefAct> myBpmDefActList = new ArrayList<BpmDefAct>();
		if(StringUtil.isEmpty(myDefNameJson)){
			return myBpmDefActList;
		}
		if(BeanUtils.isEmpty(authorizeId)){
			authorizeId = 0L;
		}
		JSONObject obj = JSONObject.fromObject(myDefNameJson);
		if(obj.containsKey("defArry")){
			JSONArray myJsonArray = obj.getJSONArray("defArry");
			//分析JSON,生成对应的BpmDefAct对象
			for(int i = 0; i < myJsonArray.size(); i++){     
	            JSONObject jsonObject = myJsonArray.getJSONObject(i);     
	            BpmDefAct bpmDefAct = new BpmDefAct();
	            if(jsonObject.containsKey("defKey")){
	            	String defKey = jsonObject.getString("defKey");
	            	bpmDefAct.setDefKey(defKey);
	            }
	            if(jsonObject.containsKey("defName")){
	            	String defName = jsonObject.getString("defName");
		            bpmDefAct.setDefName(defName);
	            }
	            if(jsonObject.containsKey("rightContent")){
	            	String rightContent = jsonObject.getString("rightContent");
	            	bpmDefAct.setRightContent(rightContent);
	            }
	       //   BpmDefAct bpmDefAct = (BpmDefAct) JSONObject.toBean(jsonObject, BpmDefAct.class);
	            bpmDefAct.setId(UniqueIdUtil.genId());
	            bpmDefAct.setAuthorizeId(authorizeId);
	            myBpmDefActList.add(bpmDefAct);
	        } 
		}
		return myBpmDefActList;
	}
	
	
	/**
	 * 查询自己相关的分管授权的流程权限
	 * @param userId
	 * @param authorizeType
	 * @param isRight（是否包括流程的权限）
	 * @param isMyDef（是否包括自己创建的流程的所有权限）
	 * @return 
	 * Map<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	public Map<String,Object> getActRightByUserMap(Long userId,String authorizeType,boolean isRight,boolean isMyDef){
		Map<String,Object> mapRight = new HashMap<String, Object>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("authorizeType", authorizeType);
		String authorizeIds = "";
		//要查询的类型类型
		map.put("all", BPMDEFUSER_RIGHT_TYPE.ALL);
		map.put("user", BPMDEFUSER_RIGHT_TYPE.USER);
		map.put("role", BPMDEFUSER_RIGHT_TYPE.ROLE);
		map.put("org", BPMDEFUSER_RIGHT_TYPE.ORG);
		map.put("position", BPMDEFUSER_RIGHT_TYPE.POSITION);
		map.put("grant", BPMDEFUSER_RIGHT_TYPE.GRANT);
		
		//直接分配到自己ID
		map.put("userId", userId);
		
		//先用户角色查询
		List<SysRole> roles = sysRoleDao.getByUserId(userId);
		List<Long> roleIdList = new ArrayList<Long>();
        if(BeanUtils.isNotEmpty(roles) && roles.size()>0){
        	for (SysRole sysRole : roles){
        		roleIdList.add(sysRole.getRoleId());
			}
		}
        
		//用户组织(自己组织和其上级组织)查询
		List<SysOrg> orgs = sysOrgDao.getOrgsByUserId(userId);
		Map<String,String> mapPath = new HashMap<String,String>();
		if(BeanUtils.isNotEmpty(orgs) && orgs.size()>0){
			String orgIds = "";
			String grantIds = "";
			for (SysOrg sysOrg : orgs){
				orgIds += sysOrg.getOrgId()+",";
				//查询自己组织和上级组织所属于的角色;
				getSysOrgRoleByOrgId(sysOrg.getOrgId(), roleIdList);
				//查询自己上级部分的信息
				String paths = sysOrg.getPath();
				if(StringUtil.isNotEmpty(paths)){
					String[] pathArray = paths.split("\\.");
					for(String path : pathArray){
						if(StringUtil.isNotEmpty(path)){
							String pt = mapPath.get(path);
							if(StringUtil.isEmpty(pt)){
								mapPath.put(path, path);
								grantIds += path+",";
							}
						}
					}
				}
			}
			orgIds =orgIds.substring(0, orgIds.length()-1);
			map.put("orgIds", orgIds);
			if(grantIds.length()>0){
				grantIds =grantIds.substring(0, grantIds.length()-1);
				map.put("grantIds", grantIds);
			}
			
		}
		
		//处理角色的内容
		if(BeanUtils.isNotEmpty(roleIdList) && roleIdList.size()>0){
			String roleIds = "";
			for (Long roleId : roleIdList){
				roleIds += roleId+",";
			}
			roleIds = roleIds.substring(0, roleIds.length()-1);
			map.put("roleIds", roleIds);
		}

		
		 //用户岗位查询
  		List<UserPosition> userPositions=userPositionDao.getByUserId(userId);
  		if(BeanUtils.isNotEmpty(userPositions)&&userPositions.size()>0){
  			String positionIds = "";
			for (UserPosition userPosition : userPositions){
				positionIds += userPosition.getPosId()+",";
			}
			positionIds = positionIds.substring(0, positionIds.length()-1);
			map.put("positionIds", positionIds);
  		}
        
  		
		//转换流程授权的内容
		Map<String,AuthorizeRight> authorizeRightMap = new HashMap<String, AuthorizeRight>();
		Map<String,AuthorizeRight> myRightMap = new HashMap<String, AuthorizeRight>();
		
		//查询自己创建的流程KEY（拥有所有权）
		if(isMyDef){
			List<String> myList = bpmDefinitionDao.getByCreateBy(userId);
			if(myList.size()>0){
				//如果需要所有权限的就直接虚拟一个有处理权限的对象
				if(isRight){
					AuthorizeRight authorizeRight = new AuthorizeRight();
					authorizeRight.setRightByAuthorizeType("Y", BPMDEFAUTHORIZE_RIGHT_TYPE.MANAGEMENT);
					for (String myDefKey : myList){
						authorizeRightMap.put(myDefKey, authorizeRight);
						myRightMap.put(myDefKey, authorizeRight);
						authorizeIds += "'"+myDefKey+"',";
					}
				}else{
				//不需要权限时
					for (String myDefKey : myList){
						authorizeIds += "'"+myDefKey+"',";
					}

				}
				
			}
		}
		
		//获取流程授权的列表内容
		List<BpmDefAct> list = bpmDefActDao.getActRightByUserMap(map);
		if(list.size()>0){
			if(isRight){
				for (BpmDefAct bpmDefAct : list){
					String defKey = bpmDefAct.getDefKey();
					//是属于自己创建的流程是有所有权的，就不用解析了
					if(myRightMap.get(defKey)!=null){
						continue;
					}
					authorizeIds += "'"+defKey+"',";
					String rightContent = bpmDefAct.getRightContent();
					AuthorizeRight authorizeRight = authorizeRightMap.get(defKey);
					if(authorizeRight!=null){
						authorizeRight.setRightByNeed("Y",rightContent,authorizeType);
					}else{
						authorizeRight = new AuthorizeRight();
						authorizeRight.setAuthorizeType(authorizeType);
						authorizeRight.setDefKey(defKey);
						authorizeRight.setRightContent(rightContent);
					}												
					authorizeRightMap.put(defKey, authorizeRight);
				}
			}else{
				for (BpmDefAct bpmDefAct : list){
					authorizeIds += "'"+bpmDefAct.getDefKey()+"',";
				}
			}
			
		}
		
		if(StringUtil.isNotEmpty(authorizeIds)){
			authorizeIds = authorizeIds.substring(0, authorizeIds.length()-1);
		}
		
		mapRight.put("authorizeIds", authorizeIds);
		mapRight.put("authorizeRightMap", authorizeRightMap);
		return mapRight;
	}
	
    //查询自己部门相关的角色
	private List<Long> getSysOrgRoleByOrgId(Long id,List<Long> roleIdList){
			
		if(roleIdList==null){
			roleIdList = new ArrayList<Long>();
		}
		if(BeanUtils.isEmpty(id)||id<0l){
			return roleIdList;
		}

		//查询其部门的或其上级部门的角色
		List<SysOrgRole>  orgRoleList =  sysOrgRoleService.getRolesByOrgId(id);
		if(BeanUtils.isEmpty(orgRoleList)){
			return roleIdList;
		}
		//排除已有的角色，以免重复
		List<Long> oldRoleIdList =  new ArrayList<Long>();
		oldRoleIdList.addAll(roleIdList);
		//之前有角色内容的
		if(oldRoleIdList!=null&&oldRoleIdList.size()>0){
			for (SysOrgRole sysOrgRole : orgRoleList){
				boolean mark = true;
				Long orgRoleId = sysOrgRole.getRoleid();
				for (Long roleId : oldRoleIdList){
					if(roleId==orgRoleId){
						mark = false;
						break;
					}
				}
				if(mark){
					//没有重复的就加进来				
					roleIdList.add(orgRoleId);
				}
			}
		}else{
			for (SysOrgRole sysOrgRole : orgRoleList){
				Long orgRoleId = sysOrgRole.getRoleid();			
				roleIdList.add(orgRoleId);
			}
		}
		
		return roleIdList;
	}
	
	
}
