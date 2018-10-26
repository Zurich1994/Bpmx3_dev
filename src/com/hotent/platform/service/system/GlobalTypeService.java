package com.hotent.platform.service.system;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.hotent.Container.model.Container.Container;
import com.hotent.SysDefNodeErgodic.model.SysDefNodeErgodic.Sysdefnodeergodic;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.DictionaryDao;
import com.hotent.platform.dao.system.GlobalTypeDao;
import com.hotent.platform.dao.system.SysTypeKeyDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.system.Dictionary;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.SysTypeKey;
import com.hotent.platform.model.system.WDefInformation;
import com.hotent.platform.model.system.WNodeInformation;
import com.hotent.platform.model.system.WSystemInformation;

/**
 * 对象功能:总分类表 Service类 开发公司:广州宏天软件有限公司 
 * 开发人员:ljf 
 * 创建时间:2011-11-23 11:07:27
 */
@Service
public class GlobalTypeService extends BaseService<GlobalType> {
	@Resource
	private GlobalTypeDao globalTypeDao;
	@Resource
	private DictionaryDao dictionaryDao;
	@Resource
	private SysTypeKeyDao sysTypeKeyDao;
	
	public GlobalTypeService() {
	}

	@Override
	protected IEntityDao<GlobalType, Long> getEntityDao() {
		return globalTypeDao;
	}

	/**
	 * 取得初始分类类型。
	 * @param isRoot	是否根节点。
	 * @param parentId	父节点。
	 * @return
	 * @throws Exception 
	 */
	public GlobalType getInitGlobalType(int isRoot,long parentId) throws Exception{
		GlobalType globalType = new GlobalType();
		Long typeId=UniqueIdUtil.genId();
		//如果是根节点，则从SysTypeKey获取数据构建分类类型
		if(isRoot==1){
			SysTypeKey sysTypeKey=sysTypeKeyDao.getById(parentId);
			globalType.setCatKey(sysTypeKey.getTypeKey());
			globalType.setTypeName(sysTypeKey.getTypeName());
			globalType.setParentId(parentId);
			globalType.setNodePath(parentId +"." + typeId +".");
			globalType.setType(sysTypeKey.getType());
		}
		else{
			//获取父类构建分类类型。
			globalType=globalTypeDao.getById(parentId);
			String nodePath=globalType.getNodePath() ;
			globalType.setNodePath(nodePath +typeId +".");
		}
		globalType.setTypeId(typeId);
		return globalType;
	}
		
	/**
	 * 根据parentId返回GlobalType列表
	 * @param parentId
	 * @return
	 */
	public List<GlobalType> getByParentId(Long parentId)
	{
		return globalTypeDao.getByParentId(parentId);
	}
	
	/**
	 * 根据类型Id删除类型，删除数据字典。
	 * @param typeId
	 */
	public void delByTypeId(Long typeId )
	{
		if(BeanUtils.isEmpty(typeId)) return;
		 
		GlobalType gt=globalTypeDao.getById(typeId);
		//留下路径
		String oldNodePath=gt.getNodePath();
		//取得下级的所有节点
		List<GlobalType> childrenList= globalTypeDao.getByNodePath(oldNodePath);
		
		for(GlobalType globalType:childrenList){
			long Id=globalType.getTypeId();
			globalTypeDao.delById(Id);
			//删除数据字典。
			dictionaryDao.delByTypeId(Id);
		}
	}
		
	/**
	 * 根据nodePath查询
	 * @param nodePath
	 * @return
	 */
	public List<GlobalType> getByNodePath(String nodePath){
		return globalTypeDao.getByNodePath(nodePath);
	}
	
	/**
	 * 根据parentId查询
	 * @param parentId
	 * @return
	 */
	public List<GlobalType> getByParentId(long parentId){
		return globalTypeDao.getByParentId(parentId);
	}
	
	/**
	 * 对象功能：判断是否存在该接节点key的记录
	 */
	public boolean isNodeKeyExists(String catKey,String nodeKey)
	{
		return globalTypeDao.isNodeKeyExists(catKey, nodeKey);
	}	
	
	public boolean isNodeKeyExistsForUpdate(Long typeId,String catKey,String nodeKey){
		return globalTypeDao.isNodeKeyExistsForUpdate(typeId,catKey, nodeKey);
	}
	
	/**
	 *  更新字段的排序。
	 * @param typeId
	 * @param sn
	 */
	public void updSn(Long typeId,Long sn){
		globalTypeDao.updSn(typeId, sn);
	}
	
	/**
	 * 拖动移动节点。
	 * @param targetId		目标id
	 * @param dragId		拖动的节点id
	 * @param moveType		拖动类型 (prev,next,inner);
	 */
	public void move(Long targetId,Long dragId,String moveType){
		GlobalType target=globalTypeDao.getById(targetId);
		GlobalType dragged=globalTypeDao.getById(dragId);
		
		String nodePath=dragged.getNodePath();
		List<GlobalType> list=this.getByNodePath(nodePath);
		
		//修改拖动节点的父亲
		for(GlobalType globalType:list){
			
			//向目标节点的前后拖动。
			if ("prev".equals(moveType) || "next".equals(moveType)) {
				String targetPath=target.getNodePath();
				String parentPath=targetPath.endsWith(".")?targetPath.substring(0,targetPath.length()-1):targetPath;
				//这个路径尾部带 "." 。
				parentPath=parentPath.substring(0,parentPath.lastIndexOf(".")+1) ;
				
				if(globalType.getTypeId().equals(dragId)){
					globalType.setParentId(target.getParentId());
					globalType.setNodePath(parentPath + dragId +".");
				}
				else{
					String path = globalType.getNodePath();
					String tmpPath =parentPath + dragId +"." +   path.replaceAll(nodePath, "");
					globalType.setNodePath(tmpPath);
				}
				globalType.setDepth(target.getDepth());
				
				if ("prev".equals(moveType)) {
					globalType.setSn(target.getSn()-1);
				} else {
					globalType.setSn(target.getSn() + 1);
				}
			}
			else{
				//
				if(globalType.getTypeId().equals(dragId)){
					//修改拖动的分类对象
					//需改父节点
					globalType.setParentId(targetId);
					//修改nodepath
					globalType.setNodePath(target.getNodePath() + globalType.getTypeId() +".");
				}
				else{
					//带点的路径
					String path=globalType.getNodePath();
					//替换父节点的路径。
					String tmpPath=path.replaceAll(nodePath, "");
					//新的父节路径
					String targetPath=target.getNodePath() ;
					//新的父节点 +拖动的节点id + 后缀
					String tmp =targetPath + dragged.getTypeId() + "." + tmpPath;
				
					globalType.setNodePath(tmp);
				}
			}
			
			globalTypeDao.update(globalType);
		}
	
	}
	/**
	 * 根据catkey获取数据。
	 * @param catKey
	 * @return
	 */
	public List<GlobalType> getByCatKey(String catKey,boolean hasRoot ){
		List<GlobalType> list= globalTypeDao.getByCatKey(catKey);
		
		
		if(hasRoot){
			SysTypeKey  sysTypeKey=sysTypeKeyDao.getByKey(catKey);
			GlobalType globalType=new GlobalType();
			globalType.setTypeName(sysTypeKey.getTypeName());
			globalType.setCatKey(sysTypeKey.getTypeKey());
			globalType.setParentId(0L);
			globalType.setIsParent("true");
			globalType.setTypeId(sysTypeKey.getTypeId());
			globalType.setType(sysTypeKey.getType());
			globalType.setNodePath(sysTypeKey.getTypeId() +".");
			list.add(0, globalType);
		}
		
		return list;
	}
	
	/**
	 * 根据catkey获取数据。
	 * @param catKey
	 * @return
	 */
	public List<GlobalType> getByCatKey_f(String catKey,boolean hasRoot,String typeName){
		List<GlobalType> list= globalTypeDao.getByCatKey(catKey);
		List<GlobalType> listresult= new ArrayList<GlobalType>();		
		Long typeId = 0L;
		Long handletypeId=0L;//操作图Id
		Long objecttypeId=0L;//事务图Id
		GlobalType gen=null;
		
		for(GlobalType a:list){
			if("操作图".equals(a.getTypeName()))
			{
				handletypeId=a.getTypeId();
				System.out.println("type"+handletypeId);
			}
			else if("事务图".equals(a.getTypeName()))
			{
				objecttypeId=a.getTypeId();
				gen=a;
				System.out.println("type"+objecttypeId);
			}
			
		}
		if("流程图".equals(typeName))
		{
			
			for(int i=0;i<list.size();i++)
			{
				if(list.get(i).getTypeId().longValue()==handletypeId||list.get(i).getTypeId().longValue()==handletypeId||
						list.get(i).getTypeId().longValue()==objecttypeId||list.get(i).getParentId().longValue()==objecttypeId)//如果是操作图或事务图则加入listresult中
					listresult.add(list.get(i));
				
			}
			listresult=getByTypeId(list,listresult,handletypeId);//listresult中存放操作图和事务图的全部节点包括其子节点
			listresult=getByTypeId(list,listresult,objecttypeId);//listresult中存放操作图和事务图的全部节点包括其子节点
			 for  ( int  i  =   0 ; i  <  listresult.size()  -   1 ; i ++ )   { 
				    for  ( int  j  =  listresult.size()  -   1 ; j  >  i; j -- )   { 
				      if  (listresult.get(j).equals(listresult.get(i)))   { 
				    	  listresult.remove(j); 
				      } 
				    } 
				  } 
			
			 //从list中删除listresult中一样的对象
			 for(int i=0;i<list.size();i++)
			 {
				 if(listresult.contains(list.get(i))){
					 list.remove(i);
				 i--;
				 }
			 }
			 return list;
		}
		else 
		{
			if("操作图".equals(typeName))
			{
				typeId=handletypeId;
			}
			else if("事务图".equals(typeName))
			{
				typeId=objecttypeId;
			}
			for(int i=0;i<list.size();i++)
			{
				if(list.get(i).getTypeName().equals(typeName)||list.get(i).getParentId().longValue()==typeId.longValue())//如果是操作图则跳过
					listresult.add(list.get(i));
				
			}
		}
		listresult=getByTypeId(list,listresult,typeId);
		
		 for  ( int  i  =   0 ; i  <  listresult.size()  -   1 ; i ++ )   { 
			    for  ( int  j  =  listresult.size()  -   1 ; j  >  i; j -- )   { 
			      if  (listresult.get(j).equals(listresult.get(i)))   { 
			    	  listresult.remove(j); 
			      } 
			    } 
			  } 
		return listresult;
	}
	//那晓旭 
	//递归遍历左侧流程分类管理列表树结构数据，得到需要节点下的全部子节点
	public List<GlobalType> getByTypeId(List<GlobalType> list,List<GlobalType> listresult,Long typeId)
	{
		for(int i=0;i<list.size();i++)
		{
			 if(list.get(i).getParentId().longValue()==typeId)//它是typeId的子节点
			{
				if(!listresult.contains(list.get(i)))//同时listresult中没有这个节点，
				{    	
					listresult.add(list.get(i));//把它放入结果list中
				}
				listresult.addAll(getByTypeId(list,listresult,list.get(i).getTypeId().longValue()));//递归添加新的子节点
			}
			
		}
		return listresult;
	}
	//那晓旭end
	public List<GlobalType> getByCatKey1(String catKey,boolean hasRoot){
		List<GlobalType> list= globalTypeDao.getByCatKey1(catKey);

		//是否有根节点。
		if(hasRoot){
			SysTypeKey  sysTypeKey=sysTypeKeyDao.getByKey(catKey);
			GlobalType globalType=new GlobalType();
			globalType.setTypeName(sysTypeKey.getTypeName());
			globalType.setCatKey(sysTypeKey.getTypeKey());
			globalType.setParentId(0L);
			globalType.setIsParent("true");
			globalType.setTypeId(sysTypeKey.getTypeId());
			globalType.setType(sysTypeKey.getType());
			globalType.setNodePath(sysTypeKey.getTypeId() +".");
			list.add(0, globalType);
		}
		return list;
	}
	/**
	 * 根据用户ID,角色Id,组织ID取到用户有权限访问的分类列表
	 * @param userId
	 * @param roleIds 角色Ids 
	 * @param orgIds  组织IDs
	 * @param hasRoot 是否包括根
	 * @return
	 */
	public Set<GlobalType> getByBpmRightCat(Long userId,String roleIds,String orgIds,boolean hasRoot){
		
		Set<GlobalType> globalTypeSet=new LinkedHashSet<GlobalType>();
		
		List<GlobalType> globalTypeList= globalTypeDao.getByBpmRights(GlobalType.CAT_FLOW, userId, roleIds, orgIds);
		globalTypeSet.addAll(globalTypeList);
		//循环查找下面的所有分类列表，若有父类，则把所有的父类实体也加进来，除了根
		for(GlobalType globalType:globalTypeList){
			if(StringUtils.isNotEmpty(globalType.getNodePath())){
				String parentNodePath=globalType.getNodePath();
				int index=parentNodePath.indexOf(globalType.getTypeId().toString());
				if(index!=-1){
					parentNodePath=parentNodePath.substring(0,index);
				}
				String[] nodePaths =parentNodePath.split("[.]");
				//the nodePaths is like 3.1336721342809.1337595123689.
				if(nodePaths.length>=2){
					for(int i=1;i<nodePaths.length;i++){
						GlobalType parentType=globalTypeDao.getById(new Long(nodePaths[i]));
						globalTypeSet.add(parentType);
					}
				}
			}
		}

		if(hasRoot){
			SysTypeKey  sysTypeKey=sysTypeKeyDao.getByKey(GlobalType.CAT_FLOW);
			GlobalType globalType=new GlobalType();
			globalType.setTypeName(sysTypeKey.getTypeName());
			globalType.setCatKey(sysTypeKey.getTypeKey());
			globalType.setParentId(0L);
			globalType.setIsParent("true");
			globalType.setTypeId(sysTypeKey.getTypeId());
			globalType.setType(sysTypeKey.getType());
			globalType.setNodePath(sysTypeKey.getTypeId() +".");
			globalTypeSet.add(globalType);
		}
		return globalTypeSet;
	}
	
	/**
	 * 根据用户ID,角色Id,组织ID取到用户有权限访问的表单分类列表	
	 * @param userId
	 * @param roleIds
	 * @param orgIds
	 * @param hasRoot
	 * @return
	 */
	public Set<GlobalType> getByFormRightCat(Long userId,String roleIds,String orgIds,boolean hasRoot){
		
		Set<GlobalType> globalTypeSet=new LinkedHashSet<GlobalType>();
		
		List<GlobalType> globalTypeList= globalTypeDao.getByFormRights(GlobalType.CAT_FORM, userId, roleIds, orgIds);
		globalTypeSet.addAll(globalTypeList);
		//循环查找下面的所有分类列表，若有父类，则把所有的父类实体也加进来，除了根
		for(GlobalType globalType:globalTypeList){
			if(StringUtils.isNotEmpty(globalType.getNodePath())){
				String parentNodePath=globalType.getNodePath();
				int index=parentNodePath.indexOf(globalType.getTypeId().toString());
				if(index!=-1){
					parentNodePath=parentNodePath.substring(0,index);
				}
				String[] nodePaths =parentNodePath.split("[.]");
				//the nodePaths is like 3.1336721342809.1337595123689.
				if(nodePaths.length>=2){
					for(int i=1;i<nodePaths.length;i++){
						GlobalType parentType=globalTypeDao.getById(new Long(nodePaths[i]));
						globalTypeSet.add(parentType);
					}
				}
			}
		}

		if(hasRoot){
			SysTypeKey  sysTypeKey=sysTypeKeyDao.getByKey(GlobalType.CAT_FORM);
			GlobalType globalType=new GlobalType();
			globalType.setTypeName(sysTypeKey.getTypeName());
			globalType.setCatKey(sysTypeKey.getTypeKey());
			globalType.setParentId(0L);
			globalType.setIsParent("true");
			globalType.setTypeId(sysTypeKey.getTypeId());
			globalType.setType(sysTypeKey.getType());
			globalType.setNodePath(sysTypeKey.getTypeId() +".");
			globalTypeSet.add(globalType);
		}
		return globalTypeSet;
	}
	
	/**
	 * 根据用户id取得类型列表。
	 * @param catKey	分类ID
	 * @param userId	用户ID
	 * @param hasRoot	是否添加根节点
	 * @return
	 */
	public List<GlobalType> getPersonType(String catKey,Long userId,boolean hasRoot){
		List<GlobalType> list= globalTypeDao.getPersonType( catKey,userId);
		//是否有根节点。
		if(hasRoot){
			SysTypeKey  sysTypeKey=sysTypeKeyDao.getByKey(catKey);
			GlobalType globalType=new GlobalType();
			globalType.setTypeName(sysTypeKey.getTypeName());
			globalType.setCatKey(sysTypeKey.getTypeKey());
			globalType.setParentId(0L);
			globalType.setIsParent("true");
			globalType.setTypeId(sysTypeKey.getTypeId());
			globalType.setType(sysTypeKey.getType());
			globalType.setNodePath(sysTypeKey.getTypeId() +".");
			list.add(0, globalType);
		}
		return list;
	}

	
	/**
	 * 根据catKey获取分类的xml数据。
	 * @param catKEY
	 * @return
	 */
	public String getXmlByCatkey(String catKEY){
		
		List<GlobalType> list = globalTypeDao.getByCatKey(catKEY);// 顶级
		SysTypeKey sysTypeKey=sysTypeKeyDao.getByKey(catKEY);
		Long typeId=sysTypeKey.getTypeId();
		
		StringBuffer sb = new StringBuffer("<folder id='0' label='全部'>");
		if(BeanUtils.isNotEmpty(list)){
			for(GlobalType gt : list){
				if(!typeId.equals(gt.getParentId())) continue;
				sb.append("<folder id='" + gt.getTypeId() + "' label='" + gt.getTypeName() + "'>");
				sb.append(getBmpChildList(list,gt.getTypeId()));
				sb.append("</folder>");
			}
		}
		sb.append("</folder>");
		return sb.toString();
	}
	//mz
	public String getXmlByCatkey_f(String catKEY,String typeName){
		List<GlobalType> list = globalTypeDao.getByCatKey(catKEY);// 顶级
		SysTypeKey sysTypeKey=sysTypeKeyDao.getByKey(catKEY);
		Long typeId=sysTypeKey.getTypeId();	
		StringBuffer sb = new StringBuffer("<folder id='0' label='全部'>");
		if(BeanUtils.isNotEmpty(list)){
			for(GlobalType gt : list){
				if(!typeId.equals(gt.getParentId())) continue;
				if (typeName.equals("operationChart")&& !gt.getTypeName().equals("操作图"))  continue;
				else if (typeName.equals("transactionChart")&& !gt.getTypeName().equals("事务图"))  continue;
				else if (typeName.equals("flowChart")&& (gt.getTypeName().equals("操作图")||gt.getTypeName().equals("事务图"))) continue;
				else if (typeName.equals("")) continue;
				sb.append("<folder id='" + gt.getTypeId() + "' label='" + gt.getTypeName() + "'>");
				sb.append(getBmpChildList(list,gt.getTypeId()));
				sb.append("</folder>");
			}
		}
		sb.append("</folder>");
		return sb.toString();
	}

	
	/**
	 * 递归调用获取xml。
	 * @param list
	 * @param parentId
	 * @return
	 */
	private String getBmpChildList(List<GlobalType> list,Long parentId){
		StringBuffer sb = new StringBuffer("");
		if(BeanUtils.isNotEmpty(list)){
			for(GlobalType gt : list){
				if(gt.getParentId().equals(parentId)){
					sb.append("<folder id='" + gt.getTypeId() + "' label='" + gt.getTypeName() + "'>");
					sb.append(getBmpChildList(list,gt.getTypeId()));
					sb.append("</folder>");
				}
			}
		}
		return sb.toString();
	}
	
	
	/**
	 * 根据分类的nodekey获取数据字典项的分类。
	 * @param nodeKey		nodeKey
	 * @return
	 */
	public GlobalType getByDictNodeKey(String nodeKey){
		return globalTypeDao.getByDictNodeKey(nodeKey);
	}
	
	/**
	 * 根据分类的nodekey获取数据字典项的分类。
	 * @param nodeKey		nodeKey
	 * @return
	 */
	public GlobalType getByDictNodeKey1(String nodeKey){
		return globalTypeDao.getByDictNodeKey1(nodeKey);
	}
	

	/**
	 * 按用户权限取得分类列表
	 * @param catKey 分类Key
	 * @param userId 用户ID
	 * @param roleIds 角色IDS 格式如 1,2
	 * @param orgIds  组织IDs 格式如 1,2
	 * @return
	 */
	public List<GlobalType> getByBpmRights(String catKey,Long userId,String roleIds,String orgIds){
		return globalTypeDao.getByBpmRights(catKey, userId, roleIds, orgIds);
	}
	
	
	/**
	 * 根据分类的nodekey获取数据字典项的分类。
	 * @param nodeKey		nodeKey
	 * @return
	 */
	public GlobalType getByCateKeyAndNodeKey(String catKey,String nodeKey){
		return globalTypeDao.getByCateKeyAndNodeKey(catKey,nodeKey);
	}
	
	
	/**
	 * 导出系统分类
	 * @param typeId     
	 * @return
	 */
	public String exportXml(long typeId) {
		String stringXml="";
		String key="";
		String name="";
		String catKey="";
		int type=1;
		boolean isDic=false;
		GlobalType globalType=globalTypeDao.getById(typeId);
		if(globalType==null){
			SysTypeKey sysTypeKey=sysTypeKeyDao.getById(typeId);
			key=sysTypeKey.getTypeKey();
			name=sysTypeKey.getTypeName();
		}else{
			key=globalType.getNodeKey();
			name=globalType.getTypeName();
			catKey=globalType.getCatKey();
			type=globalType.getType();
		}
		if(key.equals("DIC")|| catKey.equals("DIC")){
			isDic=true;
		}
		Document doc = DocumentHelper.createDocument();	
		Element root = doc.addElement("items");	
		Element node=root.addElement("item");
		node.addAttribute("key",key );
		node.addAttribute("name",name);
		node.addAttribute("type", Integer.toString(type));
		if(isDic){
			List<Dictionary> list=dictionaryDao.getByParentId(typeId);
			if(list!=null&&list.size()!=0){
				for(Dictionary dic:list){
					addElementByDic(dic, node);
				}
			}
		}
		List<GlobalType> subs=globalTypeDao.getByParentId(typeId);
		if(subs!=null&&subs.size()!=0){
			for(GlobalType g:subs){
				addElements(g,node,isDic);
			}
		}
		stringXml=doc.asXML();
		return stringXml;
	}
	
	/**
	 * 根据系统分类及其子节点 添加元素
	 * @param g
	 * @param e
	 * @param isDic
	 */
	public void addElements(GlobalType g,Element e,boolean isDic){
		Element nodes=e.addElement("item");
		nodes.addAttribute("key",g.getNodeKey());
		nodes.addAttribute("name",g.getTypeName());
		nodes.addAttribute("type",Integer.toString(g.getType()));
		if(!isDic){
			if(g.getIsLeaf()==0||g.getIsLeaf()==null){return;}
		}else{
			List<Dictionary> list=dictionaryDao.getByTypeId(g.getTypeId());
			if(list!=null&&list.size()!=0){
				for(Dictionary dic:list){
					addElementByDic(dic,nodes);
				}
			}
		}
		List<GlobalType> subs=globalTypeDao.getByParentId(g.getTypeId());
		if(subs!=null&&subs.size()!=0){
			for(GlobalType gt:subs){
				if(gt.getIsLeaf()==1){
					addElements(gt,nodes,isDic);
				}else{
					Element node=nodes.addElement("item");
					node.addAttribute("key", gt.getNodeKey());
					node.addAttribute("name", gt.getTypeName());
					node.addAttribute("type", Integer.toString(g.getType()));
				}
			}
		}
	}
	
	/**
	 * 根据数据字典数据 添加 元素   
	 * @param dic
	 * @param e
	 */
	public void addElementByDic(Dictionary dic,Element e){
		Element data=e.addElement("data");
		data.addAttribute("key", dic.getItemKey());
		data.addAttribute("name", dic.getItemName());
		data.addAttribute("type", Integer.toString(dic.getType()));
		data.setText(dic.getItemValue());
		List<Dictionary> list=dictionaryDao.getByParentId(dic.getDicId());
		if(list!=null&&list.size()!=0){
			for(Dictionary dictionary:list){
				List<Dictionary> subs=dictionaryDao.getByParentId(dictionary.getDicId());
				if(subs!=null&&subs.size()!=0){
					addElementByDic(dictionary,data);
				}else{
					Element sub=data.addElement("data");
					sub.addAttribute("name", dictionary.getItemName());
					sub.addAttribute("key", dictionary.getItemKey());
					sub.addAttribute("type", Integer.toString(dic.getType()));
					sub.setText(dictionary.getItemValue());
				}
			}
		}
	}
	
	/**
	 * 导入系统分类
	 * @param inputStream
	 * @param typeId
	 */
	public void importXml(InputStream inputStream,long typeId) {
		SysTypeKey sysTypeKey=null;
		GlobalType globalType=globalTypeDao.getById(typeId);
		boolean isDic=false;
		String catKey="";
		String basePath="";
		if(globalType==null){
			sysTypeKey=sysTypeKeyDao.getById(typeId);
			catKey=sysTypeKey.getTypeKey();
			basePath=typeId+".";
		}else {
			catKey=globalType.getCatKey();
			basePath=globalType.getNodePath();
		}
		
		Document doc=Dom4jUtil.loadXml(inputStream);
		Element root = doc.getRootElement();
		List<Element> list=root.elements();
		for(Element node: list){
			if(catKey.equals("DIC") && node.getName().equals("data")){
					addDicData(node,typeId+".");
			}else{
				addGlobalType(node,basePath,catKey);
			}
		}
	}
	
	/**
	 * 导入系统分类时解析 xml文件，添加字典项
	 * @param e
	 * @param typeId
	 */
	public void addDicData(Element e,String basePath){
		String name=e.attributeValue("name");
		String key=e.attributeValue("key");
		String type=e.attributeValue("type");
		String value=e.getText();
		long dicId=UniqueIdUtil.genId();
		Dictionary dic=new Dictionary();
		dic.setDicId(dicId);
		String[] paths=basePath.split("\\.");
		String parentId=paths[paths.length-1];
		dic.setParentId(Long.parseLong(parentId));
		dic.setItemKey(key);
		if(type==null){
			dic.setType(1);
		}else{
			dic.setType(Integer.parseInt(type));
		}
		dic.setItemName(name);
		dic.setItemValue(value);
		dic.setSn(0L);
		dic.setNodePath(basePath+dicId+".");
		dic.setTypeId(Long.parseLong(paths[0]));
		dictionaryDao.add(dic);
		List<Element> subs=e.elements();
		if(subs!=null && subs.size()!=0){
			for(Element data: subs){
				addDicData(data,dic.getNodePath());
			}
		}
		
	}
	
	/**
	 * 解析xml 文件 添加GlobalType 实体
	 * @param sysTypeKey
	 * @param e
	 * @return
	 */
	public void addGlobalType(Element e,String typeId,String catKey){
		String name=e.attributeValue("name");
		String key=e.attributeValue("key");
		String type=e.attributeValue("type");
		long id=UniqueIdUtil.genId();
		String [] paths=typeId.split("\\.");
		GlobalType g=new GlobalType();
		g.setTypeId(id);
		g.setCatKey(catKey);
		g.setDepth(paths.length);
		g.setNodePath(typeId+id+".");
		g.setParentId(Long.parseLong(paths[paths.length-1]));
		g.setNodeKey(key);
		g.setTypeName(name);
		if(type==null){
			g.setType(1);
		}else{
			g.setType(Integer.parseInt(type));
		}
		g.setSn(0L);
		if(e.elements()!=null||e.elements().size()!=0){
			g.setIsLeaf(1);
			add(g);
			List<Element>list=e.elements();
			for(Element node:list){
				if(catKey.equals("DIC")&& node.getName().equals("data")){
					addDicData(node,id+".");
				}else{
					addGlobalType(node,g.getNodePath(),catKey);
				}
			}
		}else{
			g.setIsLeaf(0);
			add(g);
		}
	}

	/**
	 * 根据catkey取得根节点
	 * @param catKey
	 * @return
	 */
	public GlobalType getRootByCatKey(String catKey){
		SysTypeKey  sysTypeKey=sysTypeKeyDao.getByKey(catKey);
		GlobalType globalType=new GlobalType();
		globalType.setTypeName(sysTypeKey.getTypeName());
		globalType.setCatKey(sysTypeKey.getTypeKey());
		globalType.setParentId(0L);
		globalType.setIsParent("true");
		globalType.setTypeId(sysTypeKey.getTypeId());
		globalType.setType(sysTypeKey.getType());
		globalType.setNodePath(sysTypeKey.getTypeId() +".");
		return globalType;
	}
	public List<GlobalType> addByWSystemInformationListInfo(
			List<WSystemInformation> sysInformation) {
		// TODO Auto-generated method stub
		List<GlobalType> list=new ArrayList<GlobalType>();
		if(sysInformation.size()!=0)
		for(WSystemInformation a1:sysInformation)
		{
			GlobalType globalType=new GlobalType(Long.valueOf(a1.getSystem_id()),"系统:"+a1.getSystem_name()+"("+a1.getNotSetNum()+")","1000."+a1.getSystem_id(),1,Long.valueOf("1000"));
			list.add(globalType);
			if(a1.sys_def_info_list.size()!=0)
				list=addByWDefInformationListInfo(a1,list);
		}
		return list;
	}
	public List<GlobalType> addByWDefInformationListInfo(WSystemInformation a1,List<GlobalType> list){
		for(WDefInformation a2:a1.sys_def_info_list)
		{
			GlobalType globalType=new GlobalType(a2.getDefId(),"流程:"+a2.bpmdef.getSubject()+"("+a2.getNotSetNum()+")","1000."+a1.getSystem_id()+"."+a2.getDefId(),2,Long.valueOf(a1.getSystem_id()));
			list.add(globalType);
			if(a2.def_node_info_list.size()!=0)
				list=addByWNodeInformationListInfo(a1,a2,list);		
		}
		return list;
	}
	public List<GlobalType> addByWNodeInformationListInfo(WSystemInformation a1,WDefInformation a2,List<GlobalType> list){
		for(WNodeInformation a3:a2.def_node_info_list)
		{
			if(a3.activityDetail!=null&&a3.operateInfo!=null)
			{
				GlobalType globalType=new GlobalType(a3.bpmNodeSet.getSetId(),"节点:"+a3.bpmNodeSet.getNodeName(),"1000."+a1.getSystem_id()+"."+a2.getDefId()+"."+a3.bpmNodeSet.getSetId(),3,a2.getDefId());
				list.add(globalType);
				
			}
			else if(a3.operateInfo==null)
			{
				GlobalType globalType=new GlobalType(a3.bpmNodeSet.getSetId(),"节点:"+a3.bpmNodeSet.getNodeName()+"(未绑定操作图)","1000."+a1.getSystem_id()+"."+a2.getDefId()+"."+a3.bpmNodeSet.getSetId(),3,a2.getDefId());
				list.add(globalType);
				
			}
			else if(a3.activityDetail==null)
			{
			GlobalType globalType=new GlobalType(a3.bpmNodeSet.getSetId(),"节点:"+a3.bpmNodeSet.getNodeName()+"(未设置)","1000."+a1.getSystem_id()+"."+a2.getDefId()+"."+a3.bpmNodeSet.getSetId(),3,a2.getDefId());
			list.add(globalType);
			}
				
		}
		return list;
	}		
	public List<GlobalType> addByDefIds(Long[] defids,String[] subjects) {//未设置流程的补全树状结构数据 成功
		// TODO Auto-generated method stub
		List<GlobalType> List=new ArrayList<GlobalType>();
		//public GlobalType(Long typeId1,String typeName1,String nodePath1,int depth,Long parentId1){
		GlobalType globalType=new GlobalType(Long.valueOf("10001"),"未绑定子系统的流程"+"("+defids.length+")","1000.10001",1,Long.valueOf("1000"));
		List.add(globalType);
		for(int i=0;i<defids.length;i++)
		{
			GlobalType globalType1=new GlobalType(
					defids[i],//typeId
					"流程:"+subjects[i],//typename
					"1000.10001."+Long.toString(defids[i]),//路径
					2,//深度
					Long.valueOf("10001")//父节点
					);
			List.add(globalType1);
		}
		//globalTypeDao.addByDefId(defids.length);
		return List; 
	}

	public 	List<GlobalType> setBySystem(List<SubSystem> subSystem) {//
		// TODO Auto-generated method stub
		List<GlobalType> List=new ArrayList<GlobalType>();
		GlobalType globalType=new GlobalType(Long.valueOf("10002"),"子系统"+"("+subSystem.size()+")","1000.10002",1,Long.valueOf("1000"));
		List.add(globalType);
		for(int i=0;i<subSystem.size();i++)
		{
			GlobalType globalType1=new GlobalType(
					subSystem.get(i).getSystemId(),//typeId
					"子系统:"+subSystem.get(i).getSysName(),//typename
					"1000.10002."+Long.toString(subSystem.get(i).getSystemId()),//路径
					2,//深度
					Long.valueOf("10002")//父节点
					);
			List.add(globalType1);
		}
		return List;
	}

	public List<GlobalType> addByCaozuotu(List<BpmDefinition> caozuotuList) {
		// TODO Auto-generated method stub
		List<GlobalType> List=new ArrayList<GlobalType>();
		GlobalType globalType=new GlobalType(Long.valueOf("10001"),"所有操作图"+"("+caozuotuList.size()+")","1000.10001",1,Long.valueOf("1000"));
		List.add(globalType);
		for(int i=caozuotuList.size()-1;i>=0;i--)
		{
			GlobalType globalType1=new GlobalType(
					caozuotuList.get(i).getDefId(),//typeId
					"操作图:"+caozuotuList.get(i).getSubject(),//typename
					"1000.10001."+Long.toString(caozuotuList.get(i).getDefId()),//路径
					2,//深度
					Long.valueOf("10001")//父节点
					);
			List.add(globalType1);
		}
		return List;
	}

	public 	List<GlobalType> setBySystem7() {    //***
		
		List<GlobalType> List=new ArrayList<GlobalType>();
		String[] str =new String[]{"信息规范化程度","知识结构化程度","业务架构开放程度","信息资源开放程度","作业集中度"};
		GlobalType globalType=new GlobalType(Long.valueOf("10001"),"业务应用发展能力分析"+"("+str.length+")","1000.10001",1,Long.valueOf("1000"));
		List.add(globalType);
	
		for(int i=0;i<str.length;i++)
		{
			//(Long typeId1,String typeName1,String nodePath1,int depth,Long parentId1)
			GlobalType globalType1=new GlobalType(
					Long.valueOf("10001"+Integer.toString(i)),
					str[i],
					"10001.10001"+Integer.toString(i),
					2,
					Long.valueOf("10001")
					);
			List.add(globalType1);
		}
		
		return List;
	}//***
	
	
public 	List<GlobalType> setBySystem8() {    //***
		
		List<GlobalType> List=new ArrayList<GlobalType>();
		
		GlobalType globalType=new GlobalType(Long.valueOf("10002"),"2.业务操作分析表"+"(5)","1000.10002",1,Long.valueOf("1000"));
		List.add(globalType);	
		globalType=new GlobalType(Long.valueOf("10003"),"3.作业项与作业实例对照表","1000.10003",1,Long.valueOf("1000"));
		List.add(globalType);
		globalType=new GlobalType(Long.valueOf("10004"),"4.操作项与操作实例对照表","1000.10004",1,Long.valueOf("1000"));
		List.add(globalType);
		globalType=new GlobalType(Long.valueOf("10005"),"5.操作级负载分析表"+"(3)","1000.10005",1,Long.valueOf("1000"));
		List.add(globalType);
		globalType=new GlobalType(Long.valueOf("10006"),"6.作业级负载分析表"+"(3)","1000.10006",1,Long.valueOf("1000"));
		List.add(globalType);
		globalType=new GlobalType(Long.valueOf("10007"),"7.作业级能力需求计算表"+"(2)","1000.10007",1,Long.valueOf("1000"));
		List.add(globalType);
		globalType=new GlobalType(Long.valueOf("10008"),"8.子系统能力需求统计表","1000.10008",1,Long.valueOf("1000"));
		List.add(globalType);
		globalType=new GlobalType(Long.valueOf("10009"),"9.实体交易能力","1000.10009",1,Long.valueOf("1000"));
		List.add(globalType);
		globalType=new GlobalType(Long.valueOf("10010"),"10.数据交易能力","1000.10010",1,Long.valueOf("1000"));
		List.add(globalType);
		
		
		String[] str =new String[]{"2.1业务操作分析表-1","2.2业务操作分析表-2","2.3业务操作分析表-3","2.4业务操作分析表-4","2.5业务操作分析表-5"};
		for(int i=0;i<str.length;i++)
		{
			//(Long typeId1,String typeName1,String nodePath1,int depth,Long parentId1)
			GlobalType globalType1=new GlobalType(
					Long.valueOf("10002"+Integer.toString(i)),
					str[i],
					"1000.10002.10002"+Integer.toString(i),
					2,
					Long.valueOf("10002")
					);
			List.add(globalType1);
		}
		str =new String[]{"5.1操作级负载分析表-1","5.2操作级负载分析表-2","5.3操作级负载分析表-3"};
		for(int i=0;i<str.length;i++)
		{
			//(Long typeId1,String typeName1,String nodePath1,int depth,Long parentId1)
			GlobalType globalType1=new GlobalType(
					Long.valueOf("10005"+Integer.toString(i)),
					str[i],
					"1000.10005.10005"+Integer.toString(i),
					2,
					Long.valueOf("10005")
					);
			List.add(globalType1);
		}
		str =new String[]{"6.1作业级负载分析表-1","6.2作业级负载分析表-2","6.3作业级负载分析表-3"};
		for(int i=0;i<str.length;i++)
		{
			//(Long typeId1,String typeName1,String nodePath1,int depth,Long parentId1)
			GlobalType globalType1=new GlobalType(
					Long.valueOf("10006"+Integer.toString(i)),
					str[i],
					"1000.10006.10006"+Integer.toString(i),
					2,
					Long.valueOf("10006")
					);
			List.add(globalType1);
		}
		str =new String[]{"7.1作业级能力需求计算表-1","7.2作业级能力需求计算表-2"};
		for(int i=0;i<str.length;i++)
		{
			//(Long typeId1,String typeName1,String nodePath1,int depth,Long parentId1)
			GlobalType globalType1=new GlobalType(
					Long.valueOf("10007"+Integer.toString(i)),
					str[i],
					"1000.10007.10007"+Integer.toString(i),
					2,
					Long.valueOf("10007")
					);
			List.add(globalType1);
		}	
		return List;
	}//***
	
public List<GlobalType> addByWSystemInformationListInfo1(
		List<WSystemInformation> sysInformation) {
	List<GlobalType> list=new ArrayList<GlobalType>();
	if(sysInformation.size()!=0)
	for(WSystemInformation a1:sysInformation)
	{
		GlobalType globalType=new GlobalType(Long.valueOf(a1.getSystem_id()),"系统:"+a1.getSystem_name(),"1000.10001."+a1.getSystem_id(),2,Long.valueOf("10001"));
		list.add(globalType);
		
	}
	GlobalType globalType=new GlobalType(Long.valueOf("10001"),"作业量信息量分析表","1000.10001",1,Long.valueOf("1000"));
	list.add(globalType);
	return list;
}

public List<GlobalType> getByCatKeyService() {
	// TODO Auto-generated method stub
	return globalTypeDao.getByCatKey("SERVICE_TYPE");
}

public List<GlobalType> addByContainerList(List<Container> Containerlist) {
	// TODO Auto-generated method stub
	List<GlobalType> list=new ArrayList<GlobalType>();
	GlobalType globalType=new GlobalType(Long.valueOf("1"),"容器("+Containerlist.size()+")","0.1",1,Long.valueOf("0"));
	list.add(globalType);
	for(Container a1:Containerlist)
	{
		GlobalType globalType1=new GlobalType(a1.getId(),a1.getContainername(),"0.1."+a1.getId(),1,Long.valueOf("1"));
		list.add(globalType1);
	}
	return list;
}
public List<GlobalType> addBySystemList(List<SubSystem> Systemlist) {
	// TODO Auto-generated method stub
	List<GlobalType> list=new ArrayList<GlobalType>();
	GlobalType globalType=new GlobalType(Long.valueOf("10"),"所有子系统("+Systemlist.size()+")","0.10",1,Long.valueOf("0"));
	list.add(globalType);
	for(SubSystem a1:Systemlist)
	{
		GlobalType globalType1=new GlobalType(a1.getSystemId(),a1.getSysName(),"0.10."+a1.getSystemId(),1,Long.valueOf("10"));
		list.add(globalType1);
	}
	return list;
}

	
	
	
}
