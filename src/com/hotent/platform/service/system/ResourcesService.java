package com.hotent.platform.service.system;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.XmlBeanUtil;
import com.hotent.platform.dao.system.ResourcesDao;
import com.hotent.platform.dao.system.ResourcesUrlDao;
import com.hotent.platform.dao.system.RoleResourcesDao;
import com.hotent.platform.dao.system.SubSystemDao;
import com.hotent.platform.model.system.Resources;
import com.hotent.platform.model.system.ResourcesUrl;
import com.hotent.platform.model.system.RoleResources;
import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.xml.system.ResourcesXml;
import com.hotent.platform.xml.system.ResourcesXmlList;
import com.hotent.platform.xml.util.XmlUtil;

/**
 * 对象功能:子系统资源 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-05 17:00:54
 */
@Service
public class ResourcesService extends BaseService<Resources>
{
	@Resource
	private ResourcesDao resourcesDao;
	@Resource
	private ResourcesUrlDao resourcesUrlDao;
	@Resource
	private SubSystemDao subSystemDao;
	@Resource
	private RoleResourcesDao roleResourcesDao;
	
	
	public ResourcesService(){
	}
	
	@Override
	protected IEntityDao<Resources, Long> getEntityDao() 
	{
		return resourcesDao;
	}
	
	/**
	 * 添加资源。
	 * @param resources
	 * @param aryName
	 * @param aryUrl
	 * @throws Exception
	 */
	public Long addRes(Resources resources,String[] aryName,String[] aryUrl) throws Exception{
		Long resId=UniqueIdUtil.genId();
		resources.setResId(resId);
		String path="";
		Long parentId=resources.getParentId();
		Resources parentRes=resourcesDao.getById(parentId);
		if(BeanUtils.isNotEmpty(parentRes)){
			if(StringUtil.isNotEmpty(parentRes.getPath())){
				path=parentRes.getPath()+":"+resId;
			}
		}else{
			path=resId.toString();
		}
		resources.setPath(path);
		resourcesDao.add(resources);
		
		if(BeanUtils.isEmpty(aryName)) return resId;
	
		for(int i=0;i<aryName.length;i++){
			String url=aryUrl[i];
			if(StringUtil.isEmpty(url)) continue;
			ResourcesUrl resouceUrl=new ResourcesUrl();
			resouceUrl.setResId(resId);
			resouceUrl.setResUrlId(UniqueIdUtil.genId());
			resouceUrl.setName(aryName[i]);
			resouceUrl.setUrl(url);
			resourcesUrlDao.add(resouceUrl);
		}
		return resId;
	}
	
	/**
	 * 更新资源。
	 * @param resources
	 * @param aryName
	 * @param aryUrl
	 * @throws Exception
	 */
	public void updRes(Resources resources,String[] aryName,String[] aryUrl) throws Exception{
		Long resId=resources.getResId();
		String path="";
		Long parentId=resources.getParentId();
		Resources parentRes=resourcesDao.getById(parentId);
		if(BeanUtils.isNotEmpty(parentRes)){
			if(StringUtil.isNotEmpty(parentRes.getPath())){
				path=parentRes.getPath()+":"+resId;
			}
		}else{
			path=resId.toString();
		}
		resources.setPath(path);
		resourcesDao.update(resources);
		//删除资源的url。
		resourcesUrlDao.delByResId(resId);
		
		if(BeanUtils.isEmpty(aryName)) return;
		
		for(int i=0;i<aryName.length;i++){
			String url=aryUrl[i];
			if(StringUtil.isEmpty(url))	continue;
			ResourcesUrl resouceUrl=new ResourcesUrl();
			resouceUrl.setResId(resId);
			resouceUrl.setResUrlId(UniqueIdUtil.genId());
			resouceUrl.setName(aryName[i]);
			resouceUrl.setUrl(url);
			resourcesUrlDao.add(resouceUrl);
		}
	}
	
	/**
	 * 根据系统id查询
	 * @param systemId	系统id
	 * @return
	 */
	public List<Resources> getBySystemId(long systemId){
		List<Resources> resourcesList= resourcesDao.getBySystemId(systemId);
		
		return resourcesList;
	}
	

	/**
	 * 根据系统id和父节点id获取资源节点。
	 * <pre>
	 * 1.根据父节点id获取资源获取到即返回。
	 * 2.如果获取不到则根据子系统相关数据构建资源根节点进行返回。
	 * </pre>
	 * @param parentId
	 * @return
	 */
	public Resources getParentResourcesByParentId(long systemId,long parentId) {
		Resources parent = resourcesDao.getById(parentId);
		if(parent!=null) return parent;
	
		SubSystem sys=subSystemDao.getById(systemId);
		
		parent = new Resources();
		parent.setResId(Resources.ROOT_ID);
		parent.setParentId(Resources.ROOT_PID);
		parent.setSn(0);
		parent.setSystemId(systemId);
		
		parent.setAlias(sys.getAlias());
		
		parent.setIsDisplayInMenu(Resources.IS_DISPLAY_IN_MENU_Y);
		parent.setIsFolder(Resources.IS_FOLDER_Y);
		parent.setIsOpen(Resources.IS_OPEN_Y);
		parent.setResName(sys.getSysName());
		
		return parent;
		
	}
	
	/**
	 * 根据资源id查询在列表中他所有的子节点。
	 * @param resId
	 * @param allRes
	 * @return
	 */
	private List<Resources> getChildsByResId(Long resId,List<Resources> allRes){
		List<Resources> rtnList=new ArrayList<Resources>();
		for(Iterator<Resources> it=allRes.iterator();it.hasNext();){
			Resources res=it.next();
			if(!res.getParentId().equals(resId)) continue;
			rtnList.add(res);
			recursiveChilds(res.getResId(),rtnList,allRes);
		}
		return rtnList;
	}
	
	/**
	 * 递归查找节点。
	 * @param resId
	 * @param rtnList
	 * @param allRes
	 */
	private void recursiveChilds(Long resId,List<Resources> rtnList,List<Resources> allRes){
		for(Iterator<Resources> it=allRes.iterator();it.hasNext();){
			Resources res=it.next();
			if(!res.getParentId().equals(resId)) continue;
			rtnList.add(res);
			recursiveChilds(res.getResId(),rtnList,allRes);
		}
	}
	

	/**
	 * 删除资源将删除其下所有的资源节点。
	 */
	public void delById(Long resId){
		//所属系统
		Resources res=resourcesDao.getById(resId);
		List<Resources> allRes=resourcesDao.getBySystemId(res.getSystemId());
		List<Resources> allChilds=getChildsByResId(resId,allRes);
		
		for(Iterator<Resources> it=allChilds.iterator();it.hasNext();){
			Resources resources=it.next();
			Long childId=resources.getResId();
			//删除关联的URL
			resourcesUrlDao.delByResId(childId);
			//删除url关联的角色
			roleResourcesDao.delByResId(childId);
			//删除资源自身
			resourcesDao.delById(childId);
		}
		
		//删除关联的URL
		resourcesUrlDao.delByResId(resId);
		//删除url关联的角色
		roleResourcesDao.delByResId(resId);
		//删除主键
		resourcesDao.delById(resId);
	}
	
	
	/**
	 * 获取系统的资源，并把某角色拥有的资源做一个选择标记。
	 * @param systemId		系统id。
	 * @param roleId		角色id。
	 * @return
	 */
	public List<Resources> getBySysRolResChecked(Long systemId,Long roleId){
		List<Resources>  resourcesList=resourcesDao.getBySystemId(systemId);
		List<RoleResources> roleResourcesList=roleResourcesDao.getBySysAndRole(systemId,roleId);
		
		Set<Long> set=new HashSet<Long> ();
		
		if(BeanUtils.isNotEmpty(roleResourcesList)){
			for(RoleResources rores:roleResourcesList){
				set.add(rores.getResId());
			}
		}
		
		if(BeanUtils.isNotEmpty(resourcesList)){
			for(Resources res:resourcesList){
				if(set.contains(res.getResId())){
					res.setChecked(Resources.IS_CHECKED_Y);
				}else{
					res.setChecked(Resources.IS_CHECKED_N);
				}
			}
		}
		return resourcesList;
	}
	
	/**
	 * 根据用户获取菜单数据。
	 * 
	 * @param sys	子系统
	 * @param user	用户
	 * @return
	 */
	public List<Resources> getSysMenu(SubSystem sys,SysUser user){
		Long systemId=sys.getSystemId();
		String rolealias = user.getRoles();
		if(StringUtil.isNotEmpty(rolealias)){
			String arrys[] = rolealias.split(",");
			rolealias = "";
			if(arrys.length>0){
				for (int i = 0; i < arrys.length; i++){
					rolealias +="'"+arrys[i]+"',";
				}
				rolealias = rolealias.substring(0, rolealias.length()-1);
			}
			
		}
		Collection<GrantedAuthority> auths= (Collection<GrantedAuthority>) ContextUtil.getCurrentUser().getAuthorities();
		List<Resources> resourcesList=new ArrayList<Resources>();
		//是否是超级管理员
		if(auths!=null&&auths.size()>0&&auths.contains(SystemConst.ROLE_GRANT_SUPER)){
			resourcesList=resourcesDao.getSuperMenu(systemId);
		}else{
			if(StringUtil.isNotEmpty(rolealias)){
				resourcesList=resourcesDao.getNormMenuByAllRole(systemId,rolealias);
			}else{
				resourcesList=resourcesDao.getNormMenuByRole(systemId,user.getUserId());
			}
		}
		
		short isLocal=sys.getIsLocal()==null?1:sys.getIsLocal().shortValue();
		//外地系统
		if(isLocal==SubSystem.isLocal_N){
			//前缀+外地址
			for(Resources res:resourcesList){
				res.setDefaultUrl(sys.getDefaultUrl()+res.getDefaultUrl());
			}
		}
		return resourcesList;
	}
	
	
	
	/**
	 * 判断别名在该系统中是否存在。
	 * @param systemId	系统id
	 * @param alias		系统别名
	 * @return
	 */
	public Integer isAliasExists(Resources resources){
		Long systemId=resources.getSystemId();
		String alias=resources.getAlias();
		return resourcesDao.isAliasExists(systemId, alias);
	}
	
	
	/**
	 * 判断别名是否存在。
	 * @param systemId
	 * @param resId
	 * @param alias
	 * @return
	 */
	public Integer isAliasExistsForUpd(Resources resources){
		Long systemId=resources.getSystemId();
		String alias=resources.getAlias();
		Long resId=resources.getResId();
		return resourcesDao.isAliasExistsForUpd(systemId, resId, alias);
	}
	
	/**
	 * 根据栏目更多路径获取相应的资源的功能模块
	 * 如果取得的资源不唯一则随意取一功能模块
	 * @param url  
	 * @return
	 */
	public Resources getByUrl(String url) {
		List<Resources>list=resourcesDao.getByUrl(url);
		if(list.size()!=0){
			return list.get(0);
		}
		return null;
	}
	
	public List<Resources> getByParentId(Long id){
		return resourcesDao.getByParentId(id);
	}
	
	/**
	 * 对资源进树行拖动。
	 * @param sourceId		原节点。
	 * @param targetId		目标节点。
	 */
	public void move(Long sourceId,Long targetId){
		//成为子节点
		Resources source = resourcesDao.getById(sourceId);
		if(targetId==0){
			source.setParentId((long)0);
			source.setPath(sourceId.toString());
		}
		else {
			Resources target = resourcesDao.getById(targetId);
			//将拖动的节点父节点设置为目标节点的ID
			source.setParentId(target.getResId());
			//设置资源移动后的路径
			if(StringUtil.isNotEmpty(target.getPath())){
				source.setPath(target.getPath()+":"+sourceId);
			}else{
				source.setPath(sourceId.toString());
			}
		}
		resourcesDao.update(source);
	}
	
	/**
	 * 给资源的图标添加上下文的路径。
	 * @param list
	 * @param ctxPath
	 */
	public static void addIconCtxPath(List<Resources> list,String ctxPath){
		for(Iterator<Resources> it=list.iterator();it.hasNext();){
			Resources res=it.next();
			String icon=res.getIcon();
			if(StringUtil.isNotEmpty(icon)){
				res.setIcon(ctxPath+ icon);
			}
		}
	}

	/**
	 * 导出资源
	 * @param resId
	 * @return
	 * @throws Exception 
	 */
	public String exportXml(long resId,Map<String,Boolean>map) throws Exception {
		Resources resources=resourcesDao.getById(resId);
		ResourcesXmlList resXmlList=new ResourcesXmlList();
		ResourcesXml resourcesXml=new ResourcesXml();
		if(BeanUtils.isNotEmpty(resources)){
			List<ResourcesXml> resList=new ArrayList<ResourcesXml>();
			resourcesXml=getResourcesXml(resourcesXml,resources);
			resList.add(resourcesXml);
			resXmlList.setResourcesXmlList(resList);
		}
		return XmlBeanUtil.marshall(resXmlList, ResourcesXmlList.class);
	}
	
	/**
	 * 递归查找子资源(为导出资源)
	 * @param resXml
	 * @param res
	 * @return
	 */
	private ResourcesXml getResourcesXml(ResourcesXml resXml,Resources res){
		resXml.setResources(res);
		List<Resources> resList=getByParentId(res.getResId());
		if(BeanUtils.isNotEmpty(resList)){
			List<ResourcesXml> resourcesXmls=resXml.getResourcesList();
			for(Resources resource:resList){
				ResourcesXml  resourcesXml=new ResourcesXml();
				resourcesXml=getResourcesXml(resourcesXml, resource);
				resourcesXmls.add(resourcesXml);
			}
			resXml.setResourcesList(resourcesXmls);
		}
		return resXml;
	}

	/**
	 * 导入资源
	 * @param inputStream
	 * @param resId
	 * @param systemId
	 * @throws Exception 
	 */
	public void importXml(InputStream inputStream, long resId,long systemId) throws Exception {
		String xml=FileUtil.inputStream2String(inputStream);
		Document doc=Dom4jUtil.loadXml(xml);
		Element root = doc.getRootElement();
		XmlUtil.checkXmlFormat(root, "res", "resources");
		ResourcesXmlList resXmlList=(ResourcesXmlList)XmlBeanUtil.unmarshall(xml, ResourcesXmlList.class);
		addResource(resXmlList.getResourcesXmlList().get(0),resId,systemId);
	}
	
	/**
	 * 解析xml递归插入资源
	 * @param resXml
	 * @param parentId
	 * @param systemId
	 */
	private void addResource(ResourcesXml resXml,long parentId,long systemId) {
		Resources res=resXml.getResources();
		Long genId=UniqueIdUtil.genId();
		res.setParentId(parentId);
		res.setSystemId(systemId);
		res.setResId(genId);
		this.add(res);
		List<ResourcesXml> resXmlList=resXml.getResourcesList();
		for(ResourcesXml resourcesXml:resXmlList){
			addResource(resourcesXml,genId,systemId);
		}
	}
	
	//更新sn
	public void updSn(Long resId, long sn) {
		resourcesDao.updSn(resId, sn);
		
	}
	
	/**
	 * 根据资源别名获取资源。
	 * @param systemId
	 * @param alias
	 * @return
	 */
	public Resources getByAlias(Long systemId,String alias){
		return resourcesDao.getByAlias(systemId, alias);
	}
	
	/**
	 * 根据父id和用户id获取下级tab资源。
	 * @param resId
	 * @param userId
	 * @return
	 */
	public List<Resources> getByParentUserId(Long resId,Long userId){
		return resourcesDao.getByParentUserId(resId, userId);
	}
	
}
