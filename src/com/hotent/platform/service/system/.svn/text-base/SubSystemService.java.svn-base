package com.hotent.platform.service.system;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONArray;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.hotent.Subsystemdef.dao.Subsystemdef.SubsystemdefDao;//subsystemdef。dao文件  dao
import com.hotent.Subsystemdef.model.Subsystemdef.Subsystemdef;//subsystemdef。java文件  list
import com.hotent.SysDefNodeErgodic.dao.SysDefNodeErgodic.SysdefnodeergodicDao;
import com.hotent.SysDefNodeErgodic.model.SysDefNodeErgodic.Sysdefnodeergodic;
import com.hotent.SysDefNodeErgodic.service.SysDefNodeErgodic.SysdefnodeergodicService;


import com.hotent.platform.dao.bpm.BpmDefinitionDao;//使BpmDefinitionDao  bpmDefinitiondao可用，调用 bpm。dao文件
import com.hotent.platform.model.bpm.BpmDefinition;//使List<BpmDefinition>可用，用list  调用 .java 文件

import com.hotent.platform.dao.bpm.BpmNodeSetDao;//使BpmNodeSetDao  bpmNodeSetDao可用，调用 bpm。dao文件
import com.hotent.platform.model.bpm.BpmNodeSet;//使List<BpmNodeSet>可用，用list  调用 .java 文件
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;

import com.hotent.platform.model.system.OperateNodeInfo;
import com.hotent.platform.model.system.TransactionInfo;
import com.hotent.platform.model.system.TransactionNodeInfo;
import com.hotent.platform.model.system.WDefInformation;
import com.hotent.platform.model.system.WNodeInformation;
import com.hotent.platform.model.system.WOperateInfo;
import com.hotent.platform.model.system.WSystemInformation;

import com.hotent.activityDetail.dao.activityDetail.ActivityDetailDao;
import com.hotent.activityDetail.model.activityDetail.ActivityDetail;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.CookieUitl;
import com.hotent.formParcel.model.formParcel.FormParcel;
import com.hotent.formParcel.service.formParcel.FormParcelService;

import com.hotent.platform.dao.system.ResourcesDao;
import com.hotent.platform.dao.system.SubSystemDao;

import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.Resources;

import com.hotent.platform.model.system.SubSystem;

import com.hotent.platform.model.system.SysTypeKey;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmNodeWebServiceService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.tableParcel.model.tableParcel.TableParcel;
import com.hotent.tableParcel.service.tableParcel.TableParcelService;

import com.alibaba.fastjson.JSON;
import com.fr.base.core.json.JSONException;
import com.fr.base.core.json.JSONObject;
import com.hotent.Markovchain.model.Markovchain.Markovchain;
import com.hotent.Markovchain.service.Markovchain.MarkovchainService;
/**
 * 对象功能:子系统管理 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-11-21 12:22:06
 */
@SuppressWarnings("unused")
@Service
public class SubSystemService extends BaseService<SubSystem>
{	
	@Resource
	private SubSystemDao subSystemDao;
	@Resource
	private ResourcesDao resourcesDao;
	@Resource

	
	private BpmDefinitionDao  bpmDefinitionDao;//流程信息表		
	@Resource
	private SubsystemdefDao subsystemdefDao;//子系统流程名字绑定表
	@Resource
	private BpmNodeSetDao  bpmNodeSetDao;//流程 节点绑定表
	
	@Resource
	private BpmDefinitionService  bpmDefinitionService;//流程名与流程id表
	@Resource
	private BpmNodeSetService  bpmNodeSetService;
	@Resource
	private BpmFormDefService  bpmFormDefService;
	@Resource
	private BpmFormTableService  bpmFormTableService;
	@Resource
	private FormParcelService  formParcelService;
	@Resource
	private TableParcelService  tableParcelService;
	@Resource
	private BpmNodeWebServiceService bpmNodeWebServiceService;
	@Resource 
	private MarkovchainService markovchainService;
	@Resource
	private BpmFormFieldService  bpmFormFieldService;


	@Resource
	private ActivityDetailDao activityDetailDao;//节点设置表
	@Resource
	private SysdefnodeergodicService sysdefnodeergodicService;//我的表

	@Override
	protected IEntityDao<SubSystem, Long> getEntityDao() {
		return subSystemDao;
	}
	
	
	public void setCurrentSystem( Long systemId,HttpServletRequest request, HttpServletResponse response){
		
		SubSystem subSystem=subSystemDao.getById(systemId);
		if(subSystem!=null){
			writeCurrentSystemCookie(String.valueOf(systemId),  request,  response);
			request.getSession().setAttribute(SubSystem.CURRENT_SYSTEM,subSystem);	
		}
		
		
	}
	public void writeCurrentSystemCookie(String systemId, HttpServletRequest request, HttpServletResponse response){
		if (CookieUitl.isExistByName(SubSystem.CURRENT_SYSTEM, request)) {
			CookieUitl.delCookie(SubSystem.CURRENT_SYSTEM, request, response);
		}
		int tokenValiditySeconds = 86400 * 14; // 14 days
		CookieUitl.addCookie(SubSystem.CURRENT_SYSTEM, systemId, tokenValiditySeconds, request, response);
	}
	/**
	 * 取得拥用的系统	
	 * @param user
	 * @return
	 */
	public List<SubSystem> getByUser(SysUser user){
		if(user.getAuthorities().contains(SystemConst.ROLE_GRANT_SUPER)){
			return getAll();
		}else{
			Collection<GrantedAuthority> roles= user.getAuthorities();
			List<SubSystem> sysList=new ArrayList<SubSystem>();
			if(BeanUtils.isEmpty(roles)){
				return sysList;
			}
			String roleNames="";
			for(GrantedAuthority auth:roles){
				if(roleNames.equals("")){
					roleNames+="'" + auth.getAuthority() +"'";
				}
				else{
					roleNames+=",'" + auth.getAuthority() +"'";
				}
			}
			return subSystemDao.getByRoles(roleNames);
		}
	}
	
	/**
	 * 取得本地子系统。
	 * 本地子系统的概念是系统在同一个应用当中。只不过按照功能划分成不同的系统。
	 * @return
	 */
	public List<SubSystem> getLocalSystem(){
		return subSystemDao.getLocalSystem();
	}
	
	/**
	 * 判断系统别名是否已存在。
	 * @param alias
	 * @return
	 */
	public Integer isAliasExist(String alias){
		return subSystemDao.isAliasExist(alias);
	}
	
	/**
	 * 更新时判断别名是否存在。
	 * @param alias		别名
	 * @param systemId	系统ID
	 * @return
	 */
	public Integer isAliasExistForUpd(String alias,Long systemId){
		return subSystemDao.isAliasExistForUpd(alias,systemId);
	}
	
	/**
	 * 获取已经激活的系统。
	 * @return
	 */
	public List<SubSystem> getActiveSystem(){
		return subSystemDao.getActiveSystem();
	}
	
	
	/**
	 * 根据catkey获取数据。
	 * @param catKey
	 * @return
	 */

	
	/**
	 * 导出子系统资源
	 * @param id
	 * @return
	 */
	public String exportXml(long systemId) {
		String strXml;
		Document doc = DocumentHelper.createDocument();	
		Element root = doc.addElement("items");	
		addElement(root,systemId,0L);
		strXml=doc.asXML();
		return strXml;
	}
	
	/**
	 * 递归 加入元素
	 * @param root
	 * @param systemId
	 * @param parentId
	 */
	public void addElement(Element root,long systemId,long parentId){
		List<Resources> list=resourcesDao.getBySystemIdAndParentId(systemId,parentId);
		if(BeanUtils.isNotEmpty(list)){
			for(Resources res:list){
				Element e=root.addElement("item");
				e.addAttribute("name", res.getResName());
				e.addAttribute("icon", res.getIcon());
				String url=res.getDefaultUrl();
				if(url!=null && !url.equals("") && !url.equals("null")){
					e.addAttribute("defaultUrl", url);
				}
				e.addAttribute("isDisplayMenu", res.getIsDisplayInMenu().toString());
				e.addAttribute("isOpen", res.getIsOpen().toString());
				e.addAttribute("isFolder", res.getIsFolder().toString());
				e.addAttribute("sn", res.getSn().toString());
				addElement(e,systemId,res.getResId());
			}
		}
	}
	
	/**
	 * 导入系统资源
	 * @param inputStream
	 * @param typeId
	 */
	public void importXml(InputStream inputStream, long systemId) {
		Document doc=Dom4jUtil.loadXml(inputStream);
		Element root = doc.getRootElement();
		List<Element> list=root.elements();
		
		for(Element element:list){
			addResource(element,0L,systemId);
		}
	}
	
	/**
	 * 递归算法，根据xml文件内容导入资源 添加至数据库中
	 * @param element
	 * @param parentId
	 */
	public void addResource(Element element,long parentId,long systemId){
		Resources res=new Resources();
		long id=UniqueIdUtil.genId();
		res.setResId(id);
		res.setResName(element.attributeValue("name"));
		res.setIcon(element.attributeValue("icon"));
		String url=element.attributeValue("defaultUrl");
		if(url!=null){
			res.setDefaultUrl(url);
		}
		res.setIsOpen(Short.parseShort(element.attributeValue("isOpen")));
		res.setIsDisplayInMenu(Short.parseShort(element.attributeValue("isDisplayMenu")));
		res.setIsFolder(Short.parseShort(element.attributeValue("isFolder")));
		res.setSn(Integer.parseInt(element.attributeValue("sn")));
		res.setSystemId(systemId);
		res.setParentId(parentId);
		resourcesDao.add(res);
		List<Element> list=element.elements();
		if(BeanUtils.isNotEmpty(list)){
			for(Element el:list){
				addResource(el, id,systemId);
			}
		}
	}
	
	public SubSystem getByAlias(String systemName) {
		return subSystemDao.getByAlias(systemName);
	}


	public List<SubSystem> getAll2() {
		List<SubSystem> all=subSystemDao.getAll2();
		return all;
	}




	public void update1(SubSystem subSystemForm) {  //logo连接更改信息
		subSystemDao.update1(subSystemForm);
		
	}
	/**
	 * 对事物图xml进行解析获得数据
	 * @param actDefId
	 * @param nodeName
	 * @return
	 */
	public String findScriptNodeDefKey11(String actDefId,String nodeName) {
		BpmDefinition bpmDefinition = bpmDefinitionService.getByActDefId(actDefId);
		String xml = bpmDefinition.getDefXml();
		String defKey;
		if(nodeName.contains("ScriptTask")){
			try {
				//System.out.println(xmlStr);
				Document document = DocumentHelper.parseText(xml);
				Element root = document.getRootElement();//得到根节点
				System.out.println("输出根节点"+root);
				List<Element> elementList = root.elements();//得到根节点下的节点列表
					for (Element element : elementList) {  //遍历
						//判断节点是什么原子操作
						if(element.attribute("id").getText().equals(nodeName)){
							//判断
							if(element.attribute("childaffair")!=null||element.attribute("affair")!=null){
								
								List<Element> eles = element.elements();//获得该节点下的下层节点列表
								for (Element ele : eles) {
									//System.out.println("内层########"+ele.getName());
									if(ele.getName().equals("subDefKey")){//判断要找的subDefKey节点
										defKey = ele.getText();//得到节点内容
										return defKey;
										
									}
								}
							}//增删改查操作
							
							else if(element.attribute("dataadd")!=null){
							    System.out.println("增加操作");
								defKey = "ADD";
								return defKey;
							}else if (element.attribute("datadelate")!=null) {
								System.out.println("删除操作");
								defKey = "DEL";
								return defKey;
							}else if (element.attribute("datachange")!=null) {
								System.out.println("修改操作");
								defKey = "UPD";
								return defKey;
							}else if (element.attribute("datainquire")!=null) {
								System.out.println("查询操作");
								defKey = "QUI";
								return defKey;
							}
							//读写页面
							else if(element.attribute("writepage")!=null){
							   System.out.println("进入写页面操作分支");
							   defKey = "Write";
							   return defKey;
							}else if (element.attribute("readpage")!=null){
							   System.out.println("进入读页面操作分支");
							   defKey = "Read";
							   return defKey;
							}
							else {
								 System.out.println("进入脚本文件分支");
									defKey = "QUI";
									return defKey;
							}
							
						}
						
					}
				} catch (DocumentException e) {
				e.printStackTrace();
				}
		}
		return null;
	}
	
   /**输出事务图图下的服务信息 -----SubSystemController中verification.ht 来调的*/
  public String countServiceNumber( List<WSystemInformation> sys_information)
  {  
	 System.out.println("进到了输出事务图下的服务信息方法");
	  String[][] sum=new String[50][4];
	  int []sumNum=new int[50];
	  String a ;
	  int m=0;
	  int sun1=0;
	  for (WSystemInformation a1:sys_information)  //子系统遍历
	  {
	    if(a1.sys_def_info_list.size()!=0)         //流程链不为空
	    {
	    	 for(WDefInformation a2:a1.sys_def_info_list)  //流程遍历
	    		 if (a2.def_node_info_list.size()!=0){     //流程下的节点链不为空
	    			 for(WNodeInformation a3:a2.def_node_info_list) //节点遍历
	    			   {
	    				  if(a3.operateInfo!=null)  //节点下有操作图
							 if(a3.operateInfo.operateNodeInfoList.size()!=0){ 
								 for(int i=0;i<a3.operateInfo.operateNodeInfoList.size();i++){ //操作图节点遍历
									 if(a3.operateInfo.operateNodeInfoList.get(i).transactionInfo!=null){
										if(a3.operateInfo.operateNodeInfoList.get(i).transactionInfo.transactionNodeInfoList.size()!=0)
										    {
											for(int j=0;j<a3.operateInfo.operateNodeInfoList.get(i).transactionInfo.transactionNodeInfoList.size();j++)
											   {
												 a=a3.operateInfo.operateNodeInfoList.get(i).transactionInfo.transactionNodeInfoList.get(j).getService();
												 System.out.println("事务图下的服务："+a);
													 if(a!=null)
													{
														 sun1++;
														 int i1;
														 for(i1=0;i1<m;i1++)
														  { 
															 if(sum[i1][0].equals(a)&&sum[i1][3].equals(a1.getSystem_id()))
															 { sumNum[i1]++;break;}
														  }
														if(i1==m)
														 {
															sum[m][0]=a;
															sum[m][2]=a1.getSystem_name();
															sum[m][3]=a1.getSystem_id();
															sumNum[m]=1;
															m++;
														 }
													
													}		 
											   }	  
										    }
									   }
								 }
							 }
	    			    }
	    		 }
	     }	 
	  }
	  for(int i=0;i<m;i++)
	  {
		  //                子系统名字             子系统id  服务名称                            
		  System.out.print(sum[i][2]+","+sum[i][3]+","+sum[i][0]+",");
		  System.out.println(sumNum[i]);
		  sum[i][1]=Integer.toString(sumNum[i]);
	  }
	 
	  System.out.println("总的服务个数为："+sun1);
	  String stringlist="";
	  String sysid="";
	  for(int i=0;i<sum.length;i++)
	  {
		  if(sum[i][0]==null)continue;
		  if(!sysid.equals(sum[i][3]))
		  {
			  if(sysid.equals(""))stringlist+="<folder id='";
			  else stringlist+="</folder> <folder id='";
			  sysid=sum[i][3];			  
			  stringlist+=sum[i][3];
			  stringlist+="' label='";
			  stringlist+=sum[i][2];
			  stringlist+="'>";
		  }
		  stringlist+="<folder label='";
		  stringlist+=sum[i][0];
		  stringlist+="' num='";
		  stringlist+=sumNum[i];
		  stringlist+="'></folder>";
	  }
	  stringlist+="</folder>";
	  return stringlist;
	  /*<folder id='0' label='全部'>
	  <folder id='10000003590002' label='设备管理分类1'></folder>
	  <folder id='10000003590003' label='设备管理分类2'></folder>
	  <folder id='10000003590004' label='设备管理分类3'></folder>
	  </folder>*/

  }//方法结束
 
  /**
   * 把ids转化成子系统对象方法
   * */
  public  List<WSystemInformation> systemIdToSysInfo(Long[] ids){
	  System.out.println("进到了ids转化成子系统对象的方法里");
	  List<WSystemInformation> sys_information = new ArrayList<WSystemInformation>();//创建子系统链表
		for (int i1=0;i1<ids.length;i1++)//遍历传过来的id【】
		{	
			WSystemInformation sys_info = new WSystemInformation(Long.toString(ids[i1]),getById(ids[i1]).getSysName());//因为通过for循环说明有一个子系统，创建一个子系统对象						
			sys_info=sysdefnodeergodicService.getlianjie(sys_info);	
			sys_information.add(sys_info);	//江子系统放入子系统list中	
		}	
		return sys_information;
  }
  public String findScriptNodeDefKey12(String actDefId,String nodeName) {
		BpmDefinition bpmDefinition = bpmDefinitionService.getByActDefId(actDefId);
		String xml = bpmDefinition.getDefXml();
		String defKey;
		if(nodeName.contains("ScriptTask")){
			try {
				//System.out.println(xmlStr);
				Document document = DocumentHelper.parseText(xml);
				Element root = document.getRootElement();//得到根节点
				System.out.println("输出根节点"+root);
				List<Element> elementList = root.elements();//得到根节点下的节点列表
					for (Element element : elementList) {  //遍历
						//判断节点是什么原子操作
						if(element.attribute("id").getText().equals(nodeName)){
							//判断
							if(element.attribute("childaffair")!=null){
								
								List<Element> eles = element.elements();//获得该节点下的下层节点列表
								for (Element ele : eles) {
									//System.out.println("内层########"+ele.getName());
									if(ele.getName().equals("subDefKey")){//判断要找的subDefKey节点
										defKey = ele.getText();//得到节点内容
										return defKey;
										
									}
								}
							}//增删改查操作
							
							else if(element.attribute("dataadd")!=null){
							    System.out.println("增加操作");
								defKey = "ADD";
								return defKey;
							}else if (element.attribute("datadelate")!=null) {
								System.out.println("删除操作");
								defKey = "DEL";
								return defKey;
							}else if (element.attribute("datachange")!=null) {
								System.out.println("修改操作");
								defKey = "UPD";
								return defKey;
							}else if (element.attribute("datainquire")!=null) {
								System.out.println("查询操作");
								defKey = "QUI";
								return defKey;
							}
							//读写页面
							else if(element.attribute("writepage")!=null){
							   System.out.println("进入写页面操作分支");
							   defKey = "Write";
							   return defKey;
							}else if (element.attribute("readpage")!=null){
							   System.out.println("进入读页面操作分支");
							   defKey = "Read";
							   return defKey;
							}
							else {
								    System.out.println("进入脚本文件分支");
									defKey = "QUI";
									return defKey;
							}
							
						}
						
					}
				} catch (DocumentException e) {
				e.printStackTrace();
				}
		}
		return null;
	}
 
}




