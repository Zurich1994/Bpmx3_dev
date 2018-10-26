package com.hotent.platform.service.form;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;




import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.cxf.common.util.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.hotent.Markovchain.model.Markovchain.SequenceFlow;
import com.hotent.Markovchain.service.Markovchain.MarkovchainService;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.XmlBeanUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.formParcel.model.formParcel.FormParcel;
import com.hotent.formParcel.service.formParcel.FormParcelService;
import com.hotent.mobile.model.form.PlatformType;
import com.hotent.platform.dao.bpm.BpmNodeSetDao;
import com.hotent.platform.dao.form.BpmDataTemplateDao;
import com.hotent.platform.dao.form.BpmFormDefDao;
import com.hotent.platform.dao.form.BpmFormRightsDao;
import com.hotent.platform.dao.form.BpmFormTableDao;
import com.hotent.platform.dao.system.GlobalTypeDao;
import com.hotent.platform.dao.system.PositionDao;
import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.dao.system.SysRoleDao;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmFormRun;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.FormModel;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.form.BpmDataTemplate;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormDefHi;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormRights;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.Identity;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmFormRunService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.FormDefTreeService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.platform.xml.form.BpmFormDefXml;
import com.hotent.platform.xml.form.BpmFormDefXmlList;
import com.hotent.platform.xml.table.BpmFormTableXml;
import com.hotent.platform.xml.util.MsgUtil;
import com.hotent.tableParcel.model.tableParcel.TableParcel;
import com.hotent.tableParcel.service.tableParcel.TableParcelService;

/**
 * 对象功能:BPM_FORM_DEF Service类 开发公司:广州宏天软件有限公司 开发人员:xwy 创建时间:2011-12-22 11:07:56
 */
@Service
public class BpmFormDefService extends BaseService<BpmFormDef> {
	@Resource
	private BpmFormDefDao dao;
	@Resource
	private BpmFormRightsService bpmFormRightsService;

	@Resource
	private BpmFormHandlerService bpmFormHandlerService;

	@Resource
	private BpmFormRunService bpmFormRunService;



	@Resource
	private BpmFormTableService bpmFormTableService;
	
	@Resource
	private FormParcelService  formParcelService;
	
	@Resource
	private TableParcelService  tableParcelService;

	@Resource
	private BpmFormRightsDao bpmFormRightsDao;

	@Resource
	private BpmNodeSetService bpmNodeSetService;
	
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	
	@Resource
	private BpmFormDefHiService bpmFormDefHiService;

	@Resource
	private BpmNodeSetDao bpmNodeSetDao;
	
	@Resource
	private GlobalTypeDao globalTypeDao;
	
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private SysRoleDao sysRoleDao;
	@Resource
	private SysOrgDao sysOrgDao;
	@Resource
	private PositionDao positionDao;
	@Resource
	private SubSystemService  subsystemservice;

	@Resource
	private BpmDataTemplateDao bpmDataTemplateDao;
	
	@Resource
	private BpmFormTableDao bpmFormTableDao;
	@Resource
	private ProcessRunService processRunService;
	
		@Resource
	private BpmFormFieldService bpmFormFieldService;
	
		
		@Resource
		private MarkovchainService markovchainService;
	
		@Resource
		private BpmService bpmservice;
		
	public BpmFormDefService() {
		
	}

	@Override
	protected IEntityDao<BpmFormDef, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 获得已发布版本数量
	 * 
	 * @param formKey
	 *            在表单版本中使用
	 * @return
	 */
	 public Integer getCountByFormKey(String formKey) {
//	public Integer getCountByFormKey(Long formKey) {
		return dao.getCountByFormKey(formKey);
	}

	/**
	 * 获得默认版本
	 * 
	 * @param formKey
	 *            在表单版本中使用
	 * @return
	 */
//	public BpmFormDef getDefaultVersionByFormKey(Long formKey) {
		public BpmFormDef getDefaultVersionByFormKey(String formKey) {
		return dao.getDefaultVersionByFormKey(formKey);
	}
		/**
		 * 通过tableid获取对象
		 */
		public List<BpmFormDef> gettableId(Long tableId) {
			return dao.gettableId(tableId);
		}
	
	/**
	 * 处理权限的人员
	 * @param permission
	 * @param flag 标记是导出（true）还是导入(false)
	 * @return
	 */
	public   String parsePermission(String permission,boolean flag) {
		JSONObject json = JSONObject.fromObject(permission);
		Object read =json.get("read"); 
		Object write =json.get("write"); 	
		if(read==null) read = "{}";
		if(write==null) write = "{}";
		json.element("read", parseUseInfo(read.toString(),flag));
		json.element("write", parseUseInfo(write.toString(),flag));
		return json.toString();
	}
	
	/**
	 * 处理权限的人员
	 * @param mode 处理json
	 * @param flag  标记是导出（true）还是导入(false)
	 * @return
	 */
	private  String parseUseInfo(String mode, boolean flag ) {
		JSONObject node = JSONObject.fromObject(mode);
		if(JSONUtils.isNull(node)) return "";
		if(JSONUtils.isNull(node.get("type")))return mode;
		if(JSONUtils.isNull(node.get("id")))return mode;
		String type =  node.get("type").toString();
		String id =  (String) node.get("id");
		String tempFullname =  (String) node.get("fullname");
		if (BpmFormRights.TYPE_NONE.equals(type) ||BpmFormRights.TYPE_EVERYONE.equals(type)) 
			return mode;
		if(flag){//导出只对人员处理
			if(BpmFormRights.TYPE_USER.equals(type)){
				SysUser sysUser  =(SysUser)sysUserDao.getById(Long.parseLong(id));
				if(BeanUtils.isNotEmpty(sysUser))
					node.element("fullname", sysUser.getAccount());		
			}
		}
		//导入
		else{
			if(StringUtil.isEmpty(tempFullname))
				return cleanPermission(node);
			//因传入的fullname可能为“用户1,用户2”，因此将传入的fullname进行分割再处理
			String[] fullnameArr = tempFullname.split(",");
			int count = 0 ;//用于统计找不到记录的次数
			StringBuffer ids = new StringBuffer();//存放过滤后的用户ID
			StringBuffer names = new StringBuffer();//存放过滤后的用户工号
			for(String fullname:fullnameArr){
				//用户
				if(BpmFormRights.TYPE_USER.equals(type)){
					SysUser sysUser  =(SysUser)sysUserDao.getByAccount(fullname);
					if(BeanUtils.isEmpty(sysUser)){
						MsgUtil.addMsg(MsgUtil.ERROR, "表单权限的中的用户工号："+fullname+",不存在!请检查!");	
						count++;
					}else{
						ids.append(sysUser.getUserId()).append(",");
						names.append(sysUser.getFullname()).append(",");
						
					}
				}
				//角色
				else if(BpmFormRights.TYPE_ROLE.equals(type)){			
					List<SysRole> sysRoleList  = 	sysRoleDao.getByRoleName(fullname);
					if(BeanUtils.isEmpty(sysRoleList) ){	
						MsgUtil.addMsg(MsgUtil.ERROR, "表单权限的中的角色名称："+fullname+",不存在!请检查!");	
						count++;
					}else if(sysRoleList.size() > 1){
						MsgUtil.addMsg(MsgUtil.ERROR, "表单权限的中的角色名称："+fullname+",多于一条记录!请检查!");
						count++;
					}else{
						SysRole sysRole = (SysRole)sysRoleList.get(0);
						ids.append(sysRole.getRoleId()).append(",");
						names.append(sysRole.getRoleName()).append(",");
					}			
				}
				//组织或组织负责人
				else if(BpmFormRights.TYPE_ORG.equals(type) || BpmFormRights.TYPE_ORGMGR.equals(type)){
					List<SysOrg> sysOrgList  =sysOrgDao.getByOrgName(fullname);
					if(BeanUtils.isEmpty(sysOrgList) ){
						MsgUtil.addMsg(MsgUtil.ERROR, "表单权限的中的组织名称："+fullname+",不存在!请检查!");	
						count++;
					}else if(sysOrgList.size() > 1){
						MsgUtil.addMsg(MsgUtil.ERROR, "表单权限的中的组织名称："+fullname+",多于一条记录!请检查!");
						count++;
					}else{
						SysOrg sysOrg = (SysOrg)sysOrgList.get(0);
						ids.append(sysOrg.getOrgId()).append(",");
						names.append(sysOrg.getOrgName()).append(",");
					}
				}
				//岗位
				else if(BpmFormRights.TYPE_POS.equals(type)){
					List<Position> positionList  =  positionDao.getByPosName(fullname);
					if(BeanUtils.isEmpty(positionList) ){
						MsgUtil.addMsg(MsgUtil.ERROR, "表单权限的中的岗位名称："+fullname+",不存在!请检查!");	
						count++;
					}else if(positionList.size() > 1){
						MsgUtil.addMsg(MsgUtil.ERROR, "表单权限的中的岗位名称："+fullname+",多于一条记录!请检查!");
						count++;
					}else{
						Position position = positionList.get(0);
						ids.append(position.getPosId()).append(",");
						names.append(position.getPosName()).append(",");
					}
				}
			}
			//若数组中所有的名称在数据库中都无对应记录
			if(count == fullnameArr.length){
				return cleanPermission(node);
			}else{
				node.element("id", ids.deleteCharAt(ids.length()-1).toString());
				node.element("fullname", names.deleteCharAt(names.length()-1).toString());
			}
		}
		return node.toString();

	}
	
	/**
	 * 清理权限
	 * @param node 权限
	 * @return
	 */
	private String cleanPermission(JSONObject permission) {	
		permission.element("id", "");
		permission.element("fullname", "");
		return permission.toString();
	}

	/**
	 * 根据formkey查询所有的表单定义版本。
	 * 
	 * @param formKey
	 *            在表单版本中使用
	 * @return
	 */
	 	public List<BpmFormDef> getByFormKey(String formKey) {
	//public List<BpmFormDef> getByFormKey(Long formKey) {
		return dao.getByFormKey(formKey);
	}
	 	public List<BpmFormDef> getBySubject(String Subject) {
	 			return dao.getAllPublished(Subject);
	 		}

	/**
	 * 增加表单定义。
	 * 
	 * @param bpmFormDef
	 *            自定义表单对象
	 * @throws Exception
	 */
	public void addForm(BpmFormDef bpmFormDef)
			throws Exception {
		long id = UniqueIdUtil.genId();
		bpmFormDef.setFormDefId(id);
	//	bpmFormDef.setFormKey(id);
		bpmFormDef.setVersionNo(1);
		bpmFormDef.setIsDefault((short) 1);
		bpmFormDef.setIsPublished((short) 0);
		dao.add(bpmFormDef);
		
		BpmFormDefHi bpmFormDefHi = new BpmFormDefHi(bpmFormDef); // 保持表单的操作记录
		bpmFormDefHiService.addHisRecord(bpmFormDefHi);
	}

	/**
	 * 更新表单及权限。
	 * 
	 * @param bpmFormDef
	 *            自定义表单对象
	 * @throws Exception
	 */
	public void updateForm(BpmFormDef bpmFormDef)
			throws Exception {
		
		// 更新table
		dao.update(bpmFormDef);
		
		BpmFormDefHi bpmFormDefHi = new BpmFormDefHi(bpmFormDef); // 保持表单的操作记录
		bpmFormDefHiService.addHisRecord(bpmFormDefHi);
	}

	/**
	 * 发布
	 * 
	 * @param formDefId
	 *            自定义表单Id
	 * @param operator
	 *            发布人
	 * @throws Exception
	 */
	public void publish(Long formDefId, String operator) throws Exception {
		// 设为已发布
		BpmFormDef formDef = dao.getById(formDefId);

		formDef.setIsPublished((short) 1);
		formDef.setPublishedBy(operator);
		formDef.setPublishTime(new Date());
		dao.update(formDef);

	}

	/**
	 * 设为默认版本。
	 * 
	 * @param formDefId
	 *            自定义表单Id
	 * @param formKey
	 *            在表单版本使用
	 */
	 	public void setDefaultVersion(Long formDefId, String formKey) {
//	public void setDefaultVersion(Long formDefId, Long formKey) {
		dao.setDefaultVersion(formKey, formDefId);
	}

	/**
	 * 根据表单定义id创建新的表单版本。 表单定义ID
	 * 
	 * @param formDefId
	 *            自定义表单Id
	 * @throws Exception
	 */
	public void newVersion(Long formDefId) throws Exception {
		BpmFormDef formDef = dao.getById(formDefId);
		Integer rtn = dao.getMaxVersionByFormKey(formDef.getFormKey());
		Long newFormDefId = UniqueIdUtil.genId();
		// 创建新的版本
		BpmFormDef newVersion = (BpmFormDef) formDef.clone();
		newVersion.setFormDefId(newFormDefId);
		newVersion.setIsDefault((short) 0);
		newVersion.setIsPublished((short) 0);
		newVersion.setPublishedBy("");

		newVersion.setVersionNo(rtn + 1);
		dao.add(newVersion);
		// 拷贝表单权限

	}

	/**
	 * 添加复制的表单，包括表单权限信息
	 * 
	 * @param bpmFormDef
	 * @param oldFormkey
	 */
	public void copyForm(BpmFormDef bpmFormDef, String oldFormkey) {
		dao.add(bpmFormDef);
	//	Long formKey = bpmFormDef.getFormKey();
			String formKey = bpmFormDef.getFormKey();
		if (bpmFormDef.getDesignType() == 0) {
			List<BpmFormRights> list = bpmFormRightsDao
					.getByFormKeyNodeEmpty(oldFormkey,PlatformType.PC);
			for (BpmFormRights bpmFormRights : list) {
				Long newId = UniqueIdUtil.genId();
				bpmFormRights.setId(newId);
				//bpmFormRights.setFormDefId(formKey);
					bpmFormRights.setFormKey(formKey);
				bpmFormRightsDao.add(bpmFormRights);
			}
		}
	}

	/**
	 * 根据BpmFormRun取得表单。 表单分为： 1.在线表单。 2.url表单。
	 * <pre>
	 * 1.首先去bpmformrun中获取表单数据。
	 * 2.没有获取到则获取当前节点的表单设置。
	 * 3.获取全局表单设置。
	 * </pre>
	 * @param processRun
	 * @param nodeId
	 * @param userId
	 * @param ctxPath
	 * @param variables
	 * @return
	 * @throws Exception
	 */
	public FormModel getForm(ProcessRun processRun, String nodeId, Long userId,
			String ctxPath, Map<String, Object> variables) throws Exception {
		String instanceId = processRun.getActInstId();
		String actDefId = processRun.getActDefId();
		String businessKey =processRun.getBusinessKey() ;
		
		
		BpmFormRun bpmFormRun = bpmFormRunService.getByInstanceAndNode(instanceId, nodeId);

		BpmNodeSet bpmNodeSet = null;
		String parentActDefId = "";
		if(variables.containsKey(BpmConst.FLOW_PARENT_ACTDEFID)){//判断当前是否属于子流程任务
			parentActDefId = (String)variables.get(BpmConst.FLOW_PARENT_ACTDEFID);
			bpmNodeSet = bpmNodeSetService.getByActDefIdNodeId(actDefId, nodeId, parentActDefId);
		}else{
			bpmNodeSet = bpmNodeSetService.getByActDefIdNodeId(actDefId, nodeId);
		}
		
		FormModel formModel = new FormModel();
		// 运行时存在。
		if (bpmFormRun != null) {
			Long formDefId = bpmFormRun.getFormdefId();
			BpmFormDef bpmFormDef = dao.getById(formDefId);
		//	Long formKey = bpmNodeSet.getFormKey();
				String formKey = bpmNodeSet.getFormKey();
			//判断当前是否 更换了表单
			if (bpmFormDef != null && bpmFormDef.getFormKey().equals(formKey)) {
				String tablename = "";
				Long tableId = bpmFormDef.getTableId();
				if(tableId > 0){
					BpmFormTable bpmFormTable = bpmFormTableService.getById(tableId);
					if(BeanUtils.isNotEmpty(bpmFormTable)){
						tablename = bpmFormTable.getTableName();
						bpmFormDef.setTableName(tablename);
					}
				}
				//判断数据是否有效。
				isDataValid(formModel, businessKey, tablename);
				String formHtml = bpmFormHandlerService.obtainHtml(bpmFormDef, userId, businessKey, instanceId, actDefId, nodeId, ctxPath, parentActDefId, false);
				
				formModel.setFormHtml(formHtml);
				return formModel;
			}
		}
	
		if (bpmNodeSet == null) return formModel;
		// 获取在线表单
		if (BpmConst.OnLineForm.equals(bpmNodeSet.getFormType())) {
			BpmFormDef bpmFormDef = dao.getDefaultPublishedByFormKey(bpmNodeSet.getFormKey());
			String bussinessKey = processRun.getBusinessKey();
			String tablename = "";
			BpmFormTable bpmFormTable = bpmFormTableDao.getById(bpmFormDef.getTableId());
			if(BeanUtils.isNotEmpty(bpmFormTable)){
				tablename= bpmFormTable.getTableName();
			}
			//验证表单数据是否有效。
			isDataValid(formModel, bussinessKey, tablename);
			String formHtml = bpmFormHandlerService.obtainHtml(bpmFormDef,  0L, businessKey, processRun.getActInstId(), processRun.getActDefId(), "", ctxPath, parentActDefId, false);
//			String formHtml = bpmFormHandlerService.obtainHtml(bpmFormDef,processRun, userId, nodeId, ctxPath, parentActDefId);
			formModel.setFormHtml(formHtml);
		} else {
			// 获取流程实例ID
			String bussinessKey = processRun.getBusinessKey();
			String formUrl = bpmNodeSet.getFormUrl();
			String detailUrl = bpmNodeSet.getDetailUrl();
			if (StringUtil.isNotEmpty(formUrl)) {
				
				formUrl = getFormUrl(formUrl, bussinessKey, variables, ctxPath);
				formModel.setFormUrl(formUrl);
				formModel.setFormType(BpmConst.UrlForm);
			}
			if (StringUtil.isNotEmpty(detailUrl) ) {
				detailUrl = getFormUrl(detailUrl, bussinessKey, variables, ctxPath);
				formModel.setDetailUrl(detailUrl);
			}
		}
		return formModel;
	}

	/**
	 * 获取表单URL。
	 * @param formUrl
	 * @param bussinessKey
	 * @param variables
	 * @param ctxPath
	 * @return
	 */
	private String getFormUrl(String formUrl, String bussinessKey,
			Map<String, Object> variables, String ctxPath) {
		String url=formUrl;
		if(StringUtil.isNotEmpty(bussinessKey)){
			url = formUrl.replaceFirst(BpmConst.FORM_PK_REGEX, bussinessKey);
		}
		
		if (variables != null)
			url = getUrlByVariables(url, variables);
		if (!formUrl.startsWith("http")) {
			url = ctxPath + url;
		}
		return url;
	}

	

	/**
	 * 根据任务id 获取API调用的跳转URL 。
	 * 
	 * @param taskId
	 * @param defId
	 * @param nodeId
	 * @param businessKey
	 * @param ctxPath
	 * @return
	 */
	public String getFormUrl(String taskId, Long defId, String nodeId,
			String businessKey, String ctxPath) {
		String formUrl = "";

		BpmNodeSet nodeSet = bpmNodeSetService.getByDefIdNodeId(defId, nodeId);
		if (nodeSet != null) {
			formUrl = nodeSet.getFormUrl();
		}
		if (StringUtil.isEmpty(formUrl)) {
			BpmNodeSet node = bpmNodeSetService.getBySetType(defId,
					BpmNodeSet.SetType_GloabalForm);
			formUrl = node.getFormUrl();
		}
		if(StringUtils.isEmpty(formUrl))
				return formUrl;
		formUrl = formUrl.replaceFirst(BpmConst.FORM_PK_REGEX, businessKey)
				.replaceFirst("\\{taskId\\}", taskId);
		if (!formUrl.startsWith("http")) {
			formUrl = ctxPath + formUrl;
		}
		return formUrl;

	}

	/**
	 * 替换地址
	 * orderNo={orderNo}
	 * @param url
	 * @param variables
	 * @return
	 */
	private String getUrlByVariables(String url, Map<String, Object> variables) {
		Pattern regex = Pattern.compile("\\{(.*?)\\}");
		Matcher regexMatcher = regex.matcher(url);
		while (regexMatcher.find()) {
			String toreplace = regexMatcher.group(0);
			String varName = regexMatcher.group(1);
			if (!variables.containsKey(varName))
				continue;
			url = url.replace(toreplace, variables.get(varName).toString());
		}
		return url;
	}

	/**
	 * 取得发布的表单。
	 * 
	 * @param queryFilter
	 * @return
	 */
	public List<BpmFormDef> getPublished(QueryFilter queryFilter) {
		return dao.getPublished(queryFilter);
	}
	
	public List<BpmFormDef> getAllPublished(String formDesc){
		return dao.getAllPublished(formDesc);
	}

	/**
	 * 根据表单key获取默认的表单定义。
	 * 
	 * @param formKey
	 * @return
	 */
	 	public BpmFormDef getDefaultPublishedByFormKey(String formKey) {
//	public BpmFormDef getDefaultPublishedByFormKey(Long formKey) {
		return dao.getDefaultPublishedByFormKey(formKey);
	}

	/**
	 * 判断表单是否已经被使用。
	 * 
	 * @param formKey
	 *            表单key
	 * @return
	 */
	 	public int getFlowUsed(String formKey) {
//	public int getFlowUsed(Long formKey) {
		int rtn = dao.getFlowUsed(formKey);
		return rtn;
	}
	
	/**
	 * 根据formKey获取其关联的流程定义，结果返回 表单名，关联的流程定义（List<String>）
	 * @param formKey
	 * @return
	 */
//	public List getBpmFormRelatedDef(Long formKey) {
		public List getBpmFormRelatedDef(String formKey) {
		List result = new ArrayList() ;
		List<String> actDefName = new ArrayList<String>() ;
		List<BpmNodeSet> bpmNodeSetList = bpmNodeSetDao.getByFormKey(formKey);
		BpmDefinition bpmDefinition = null ;
		for(BpmNodeSet bpmNodeSet:bpmNodeSetList){
			bpmDefinition = bpmDefinitionService.getByActDefId(bpmNodeSet.getActDefId());
			actDefName.add(bpmDefinition.getSubject()) ;
		}
		if(BeanUtils.isNotEmpty(bpmNodeSetList)){
			result.add(bpmNodeSetList.get(0).getFormDefName());
			result.add(actDefName);
		}
		return result ;
	}

	/**
	 * 根据formkey删除数据。
	 * 
	 * <pre>
	 * 	如果表已经生成并且表单是通过设计器进行设计的那么将删除所创建的表。
	 * </pre>
	 * 
	 * @param formKey
	 * @throws SQLException
	 */
	//public void delByFormKey(Long formKey) throws SQLException {
		public void delByFormKey(String formKey) throws SQLException {
		BpmFormDef bpmFormDef = dao.getDefaultVersionByFormKey(formKey);
		Long tableId = bpmFormDef.getTableId();
		// 删除表单权限
		bpmFormRightsService.deleteByTableId(tableId,PlatformType.PC);
		// 先删除表单，后判断是否还有表单使用该表
		dao.delByFormKey(formKey);
		//删除数据模版。
		bpmDataTemplateDao.delByFormKey(formKey);
		// tableId大于零并且有表单生成。
		if (tableId > 0 && bpmFormDef.getDesignType() == 1) {
			BpmFormTable bpmFormTable = bpmFormTableService
					.getTableById(tableId);
			// 是否还有表单使用该表
			boolean tableHasForm = dao.isTableHasFormDef(tableId);
			if (bpmFormTable != null && !tableHasForm) {
				bpmFormTableService.dropTable(bpmFormTable);
				bpmFormTableService.delTable(bpmFormTable);
			}
		}
	// 删除树结构设置
		AppUtil.getBean(FormDefTreeService.class).delByFormDefId(bpmFormDef.getFormDefId());
	}

	/**
	 * 保存表单。
	 * 
	 * <pre>
	 * 	1.表单输入新创建的表单。
	 * 		1.保存表单。
	 * 		
	 *  2.表单未发布。
	 *  	1.保存表单。
	 *  	
	 *  3.表单已经发布的情况，表单已经发布，数据库表已经创建。
	 *  	1.保存表单。
	 *  	2.表单是否有其他的表单定义情况。
	 *  		1.相同的表不止对应一个表单的情况，对表做更新处理。
	 *  		2.没有数据的情况，表删除重建。
	 * </pre>
	 * 
	 * @param bpmFormdef
	 * @param bpmFormTable
	 * @throws Exception
	 */
	public void saveForm(BpmFormDef bpmFormdef, BpmFormTable bpmFormTable,
			boolean isPublish) throws Exception {
		if (bpmFormdef.getFormDefId() == 0) {
			Long formDefId = UniqueIdUtil.genId();
			bpmFormdef.setFormDefId(formDefId);
		//	bpmFormdef.setFormKey(formDefId);
			bpmFormdef.setDesignType(BpmFormDef.DesignType_CustomDesign);
			bpmFormdef.setIsDefault((short) 1);
			bpmFormdef.setVersionNo(1);
			Long tableId = 0L;
			if (isPublish) {
				tableId = bpmFormTableService.saveTable(bpmFormTable);
				bpmFormdef.setIsPublished((short) 1);
				bpmFormdef.setPublishTime(new Date());
			} else {
				bpmFormdef.setIsPublished((short) 0);
				bpmFormdef.setPublishedBy("");
			}
			bpmFormdef.setTableId(tableId);
			dao.add(bpmFormdef);
		} else {
			// 当前为发布或者表单已经分布。
			if (isPublish || bpmFormdef.getIsPublished() == 1) {
				Long tableId = bpmFormdef.getTableId();
				bpmFormTable.setTableId(tableId);
				tableId = bpmFormTableService.saveTable(bpmFormTable);
				bpmFormdef.setTableId(tableId);
				bpmFormdef.setIsPublished((short) 1);
				bpmFormdef.setPublishTime(new Date());
			}
			dao.update(bpmFormdef);
		}
		BpmFormDefHi bpmFormDefHi = new BpmFormDefHi(bpmFormdef); // 保持表单的操作记录
		bpmFormDefHiService.addHisRecord(bpmFormDefHi);
	}

	/**
	 * 获取现有表单Id函数
	 * 
	 * @param nodeSet
	 * @return
	 */
	public Long getCurrentTableId(BpmNodeSet nodeSet) {
		Long formId = 0L;
		BpmFormDef bpmFormDef;
		// 节点挂钩表单不为空时取节点表单
		if (nodeSet.getFormType().equals(Short.parseShort("0"))) {
			bpmFormDef = dao.getDefaultVersionByFormKey(nodeSet.getFormKey());
			if (bpmFormDef != null) {
				formId = bpmFormDef.getFormDefId();
			}
		} else { // 节点表单为空时取全局表单
			BpmNodeSet globalForm = bpmNodeSetDao.getBySetType(
					nodeSet.getDefId(), BpmNodeSet.SetType_GloabalForm);
			if (globalForm != null) {
				bpmFormDef = dao.getDefaultVersionByFormKey(globalForm
						.getFormKey());
				if (bpmFormDef != null) {
					formId = bpmFormDef.getFormDefId();
				}
			}
		}
		return formId;
	}

	/**
	 * 
	 * 导出表单XML
	 * 
	 * <pre>
	 * 1.导出流程定义
	 * 2.导出流程定义权限
	 * 3.导出数据模板
	 * </pre>
	 * 
	 * @param formDefIds
	 * @param map 是否导出的Map列表
	 * @return
	 * @throws Exception
	 */
	public String exportXml(Long[] formDefIds, Map<String, Boolean> map) throws Exception {
		BpmFormDefXmlList bpmFormDefXmls = new BpmFormDefXmlList();
		List<BpmFormDefXml> list = new ArrayList<BpmFormDefXml>();
		for (int i = 0; i < formDefIds.length; i++) {
			BpmFormDef bpmFormDef = dao.getById(formDefIds[i]);
			BpmFormDefXml bpmFormDefXml = exportBpmFormDef(bpmFormDef,
					BpmFormDef.IS_DEFAULT,map);
			list.add(bpmFormDefXml);
		}
		bpmFormDefXmls.setBpmFormDefXmlList(list);
		return XmlBeanUtil.marshall(bpmFormDefXmls, BpmFormDefXmlList.class);
	}

	public Map<String, Boolean> getDefaultExportMap(Map<String, Boolean> map){
		if(BeanUtils.isEmpty(map)){
			 map = new HashMap<String, Boolean>();
			map.put("bpmFormDef", true);
			map.put("bpmFormTable", false);
			map.put("bpmFormDefOther", true);
			map.put("bpmFormRights",true);
			map.put("bpmTableTemplate", true);
		}
		return map;
	}
	
	/**
	 * 导出表单的信息
	 * 
	 * @param bpmFormDef
	 *            表单
	 * @param isDefault
	 *            是否是默认 默认则要导出其它表单和模板
	 * @param map  是否导出的Map列表
	 * @return
	 */
	public BpmFormDefXml exportBpmFormDef(BpmFormDef bpmFormDef, Short isDefault, Map<String, Boolean> map) {
		BpmFormDefXml bpmFormDefXml = new BpmFormDefXml();
		// 表单
		bpmFormDefXml.setBpmFormDef(bpmFormDef);
		Long formDefId = bpmFormDef.getFormDefId();
			String formKey = bpmFormDef.getFormKey();
	//	Long formKey = bpmFormDef.getFormKey();
		
		if (isDefault.shortValue() == BpmFormDef.IS_DEFAULT.shortValue()) {
			//导出对应的表
			if(map.get("bpmFormTable"))
				exportBpmFormTableXml(bpmFormDef,bpmFormDefXml);
			
			if (BeanUtils.isNotEmpty(formKey)) {
				// 导出自定义表单 非默认版本
				if(map.get("bpmFormDefOther"))
					 exportBpmFormDefOther(formKey,map,bpmFormDefXml);
				// 数据模板
				if(map.get("bpmTableTemplate"))
						 exportBpmDataTemplate(formKey,bpmFormDefXml);
				
			}
		}

	//	if (BeanUtils.isNotEmpty(formDefId)) {
			if (BeanUtils.isNotEmpty(formKey)) {
			// 表单权限
			 if(map.get("bpmFormRights"))
			 	exportBpmFormRights(formKey, bpmFormDefXml);
			//	 exportBpmFormRights(formDefId,bpmFormDefXml);
		
		}
		return bpmFormDefXml;
	}
	
	/**
	 * 导出对于的表
	 * @param bpmFormDef
	 * @param bpmFormDefXml
	 */
	private void exportBpmFormTableXml(BpmFormDef bpmFormDef,
			BpmFormDefXml bpmFormDefXml) {
		if(BeanUtils.isEmpty(bpmFormDef.getTableId()))
			return;
		if (bpmFormDef.getTableId()==0  && bpmFormDef.getDesignType()==BpmFormDef.DesignType_CustomDesign)
			return;
		
		BpmFormTable formTable = bpmFormTableService.getById(bpmFormDef.getTableId());
		BpmFormTableXml bpmFormTableXml = bpmFormTableService.exportTable(formTable,null);
		bpmFormDefXml.setBpmFormTableXml(bpmFormTableXml);
	}


	/**
	 * 导出其它版本的自定义表单
	 * @param formKey
	 * @param map
	 * @param bpmFormDefXml
	 */
	private void exportBpmFormDefOther(String formKey, Map<String, Boolean> map, BpmFormDefXml bpmFormDefXml) {
		List<BpmFormDef> formDefList = dao.getByFormKeyIsDefault(
				formKey,BpmFormDef.IS_NOT_DEFAULT);
		if (BeanUtils.isEmpty(formDefList)) return ;
		
		List<BpmFormDefXml> list = new ArrayList<BpmFormDefXml>();
		for (BpmFormDef formDef : formDefList) {
			BpmFormDefXml formDefXml = exportBpmFormDef(formDef,
					BpmFormDef.IS_NOT_DEFAULT,map);
			list.add(formDefXml);
		}
		bpmFormDefXml.setBpmFormDefXmlList(list);
	}
	
	/**
	 * 导出表单权限
	 * @param formDefId
	 * @param bpmFormDefXml
	 */
	private void exportBpmFormRights(String formKey, BpmFormDefXml bpmFormDefXml) {
		List<BpmFormRights> bpmFormRightsList = bpmFormRightsDao			
		.getByFormKeyNodeEmpty(formKey,PlatformType.PC);
		//.getByFormDefId(formDefId,PlatformType.PC);
		if (BeanUtils.isNotEmpty(bpmFormRightsList))
			bpmFormDefXml.setBpmFormRightsList(bpmFormRightsList);
	}
	/**
	 * 处理人员
	 * @param bpmFormRightsList
	 * @return
	 */
	public List<BpmFormRights> exportBpmFormRightsUser(List<BpmFormRights> bpmFormRightsList){	
		List<BpmFormRights> formRightsList =  new ArrayList<BpmFormRights>();
		//处理人员
		for (BpmFormRights bpmFormRights : bpmFormRightsList) {
			String permission = bpmFormRights.getPermission();
			bpmFormRights.setPermission(parsePermission(permission,true));
			formRightsList.add(bpmFormRights);	
		}
		return formRightsList;
	}
	
	/**
	 * 导出数据模板
	 * @param formKey
	 * @param bpmFormDefXml
	 */
//	private void exportBpmDataTemplate(Long formKey,
//			BpmFormDefXml bpmFormDefXml) {
				private void exportBpmDataTemplate(String formKey, BpmFormDefXml bpmFormDefXml) {
		BpmDataTemplate bpmDataTemplate = bpmDataTemplateDao.getByFormKey(formKey);
		if (bpmDataTemplate!=null) 
			bpmFormDefXml.setBpmDataTemplate(bpmDataTemplate);
		
	}


	/**
	 * 导入xml
	 * <pre>
	 * 1.导入流程定义
	 * 2.导入流程定义权限
	 * 3.导入数据模板
	 * </pre>
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public void importXml(InputStream inputStream) throws Exception {
		Document doc = Dom4jUtil.loadXml(inputStream);
		Element root = doc.getRootElement();
		// 验证格式是否正确
		this.checkXMLFormat(root);

		String xmlStr = root.asXML();
		BpmFormDefXmlList bpmFormDefXmlList = (BpmFormDefXmlList) XmlBeanUtil
				.unmarshall(xmlStr, BpmFormDefXmlList.class);
		List<BpmFormDefXml> list = bpmFormDefXmlList.getBpmFormDefXmlList();
		for (BpmFormDefXml bpmFormDefXml : list) {
			this.importBpmFormDef(bpmFormDefXml);
			MsgUtil.addSplit();
		}
	}

	/**
	 * 检查XML格式是否正确
	 * 
	 * @param root
	 * @param msg
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	private void checkXMLFormat(Element root) throws Exception {
		String msg = "导入文件格式不对";
		if (!root.getName().equals("form"))
			throw new Exception(msg);
		List<Element> itemLists = root.elements();
		for (Element elm : itemLists) {
			if (!elm.getName().equals("formDefs"))
				throw new Exception(msg);
		}
	}

	/**
	 * 导入表单信息
	 * 
	 * @param bpmFormDefXml
	 * @param formKey
	 * @param formKey
	 * @param isDefault
	 * @return
	 * @throws Exception
	 */
	public Map<String, Map> importBpmFormDef(BpmFormDefXml bpmFormDefXml) throws Exception {
		Set<Identity> identitySet = new HashSet<Identity>();
		// 导入自定义表
		Map map = this.importBpmFormTable(bpmFormDefXml.getBpmFormTableXml(), identitySet);
		// 流水号
		bpmFormTableService.importIdentity(identitySet);

		BpmFormDef bpmFormDef = bpmFormDefXml.getBpmFormDef();
		String origFormKey = bpmFormDef.getFormKey();
		Long origFormDefId = bpmFormDef.getFormDefId();
		// 导入表单信息
		bpmFormDef = this.importBpmFormDef(bpmFormDef, map);
		String formKey = bpmFormDef.getFormKey();
		Long formDefId = bpmFormDef.getFormDefId();

		// // 导入 表单模板
		BpmDataTemplate bpmDataTemplate = bpmFormDefXml.getBpmDataTemplate();
		if (BeanUtils.isNotEmpty(bpmDataTemplate)) {
			this.importBpmDataTemplate(bpmDataTemplate, formKey);
		}
		// 表单权限
		List<BpmFormRights> bpmFormRightsList = bpmFormDefXml.getBpmFormRightsList();
		if (BeanUtils.isNotEmpty(bpmFormRightsList)) {
			for (BpmFormRights bpmFormRights : bpmFormRightsList) {
				this.importBpmFormRights(bpmFormRights, formKey);
			}
		}
		Map<String, Map> defMap = new HashMap<String, Map>();
		Map<String, String> keyMap = new HashMap<String, String>();
		Map idMap = new HashMap();

		idMap.put(origFormDefId, formDefId);
		keyMap.put(origFormKey, formKey);
		defMap.put("id", idMap);
		defMap.put("key", keyMap);
		return defMap;

	}
	
	
	/**
	 * 导入自定义表
	 * @param bpmFormTableXml
	 * @param identitySet
	 * @throws Exception
	 */
	private Map<Long, Long>  importBpmFormTable(BpmFormTableXml bpmFormTableXml, Set<Identity> identitySet) throws Exception {
	//	Map<Long, Long> map = new HashMap<Long, Long>();
			Map map = new HashMap();
		if (BeanUtils.isNotEmpty(bpmFormTableXml)) {		
			map = bpmFormTableService.importBpmFormTableXml(bpmFormTableXml);
			bpmFormTableService.setIdentity(bpmFormTableXml.getIdentityList(), identitySet);
		}
		return map;
	}

	/**
	 * 导入的表单信息保存
	 * 
	 * @param bpmFormDef
	 * @param map 
	 * @param tableMap
	 * @param msg
	 * @return
	 */
	private BpmFormDef importBpmFormDef(BpmFormDef bpmFormDef, Map<Long, Long> map) throws Exception {
		String formKey = bpmFormDef.getFormKey();
	//	Long formKey =	bpmFormDef.getFormKey();
		Integer maxVersion = dao.getMaxVersionByFormKey(formKey);
		SysUser sysUser = (SysUser)ContextUtil.getCurrentUser();
		bpmFormDef.setHtml(StringUtil.convertScriptLine(bpmFormDef.getHtml(), false));
		bpmFormDef.setTemplate(StringUtil.convertScriptLine(bpmFormDef.getTemplate(), false));
		// 设置tableId
		this.setTableId(bpmFormDef,map);	

		// 设置分类
		this.setCategoryId(bpmFormDef);
		
		bpmFormDef.setPublishTime(new Date());
		bpmFormDef.setCreatetime(new Date());
		bpmFormDef.setPublishedBy(sysUser.getFullname());
		bpmFormDef.setCreateBy(sysUser.getUserId());
		
		if(BeanUtils.isEmpty(maxVersion) || maxVersion.intValue() == 0){
			BpmFormDef def = dao.getById(bpmFormDef.getFormDefId());
			if(BeanUtils.isNotEmpty(def))
				bpmFormDef.setFormDefId(UniqueIdUtil.genId());		

			bpmFormDef.setVersionNo(1);		
			//bpmFormDef.setIsPublished(BpmFormDef.IS_PUBLISHED);
			bpmFormDef.setPublishedBy(sysUser.getFullname());
			bpmFormDef.setPublishTime(new Date());
			bpmFormDef.setCreatetime(new Date());
			bpmFormDef.setCreateBy(sysUser.getUserId());
			dao.add(bpmFormDef);
			MsgUtil.addMsg(MsgUtil.SUCCESS, "自定义表单:‘"+bpmFormDef.getSubject()+"’,该记录成功导入!");
		}else{
			bpmFormDef.setFormDefId(UniqueIdUtil.genId());
			bpmFormDef.setVersionNo(maxVersion+1);
			bpmFormDef.setIsDefault(BpmFormDef.IS_NOT_DEFAULT);
			//bpmFormDef.setIsPublished(BpmFormDef.IS_PUBLISHED);
			
			dao.add(bpmFormDef);
			MsgUtil.addMsg(MsgUtil.WARN, "自定义表单:‘"+bpmFormDef.getSubject()+"’,已经存在,重新发布新版本！");
		}
		return bpmFormDef;
			
	
		
	
	}
	
	/**
	 * 设置表ID
	 * 
	 * @param bpmFormDef
	 * @param map 
	 * @param tableMap
	 * @return
	 */
	private void setTableId(BpmFormDef bpmFormDef, Map<Long, Long> map) {
		if (BeanUtils.isEmpty(bpmFormDef.getTableId()))
			return ;
		Long tableId = bpmFormDef.getTableId();
		if (BeanUtils.isNotEmpty(map)){
			Long origTableId = map.get(tableId);
			if(BeanUtils.isNotEmpty(origTableId)){
				tableId = origTableId;
			}			
		}
		
		BpmFormTable bpmFormTable = bpmFormTableService.getById(tableId);
		if (BeanUtils.isEmpty(bpmFormTable))
			bpmFormDef.setTableId(null);
		
	}
	

	/**
	 * 导入的表单信息保存
	 * 
	 * @param bpmFormDef
	 * @param tableMap
	 * @param msg
	 * @return
	 */
	@SuppressWarnings("unused")
	private void importBpmFormDef(BpmFormDef bpmFormDef) throws Exception {
		// 设置分类
		this.setCategoryId(bpmFormDef);
		// 设置tableId
		this.setTableId(bpmFormDef);
		bpmFormDef.setIsPublished(BpmFormDef.IS_NOT_PUBLISHED);
		bpmFormDef.setPublishedBy(null);
		bpmFormDef.setPublishTime(null);
		dao.add(bpmFormDef);
		MsgUtil.addMsg(MsgUtil.SUCCESS, "自定义表单:"+bpmFormDef.getSubject()+",该记录成功导入!");
	}

	/**
	 * 设置表ID
	 * 
	 * @param bpmFormDef
	 * @param tableMap
	 * @return
	 */
	private void setTableId(BpmFormDef bpmFormDef) {
		if (BeanUtils.isEmpty(bpmFormDef.getTableId()))
			return ;

		BpmFormTable bpmFormTable = bpmFormTableService.getById(bpmFormDef.getTableId());
		if (BeanUtils.isEmpty(bpmFormTable))
			bpmFormDef.setTableId(null);
		
	}

	/**
	 * 设置分类
	 * 
	 * @param bpmFormDef
	 * @return
	 */
	private void setCategoryId(BpmFormDef bpmFormDef) {
		if (BeanUtils.isEmpty(bpmFormDef.getCategoryId()))
			return ;
		GlobalType globalType = globalTypeDao.getById(bpmFormDef
				.getCategoryId());
		if (BeanUtils.isEmpty(globalType))
			bpmFormDef.setCategoryId(null);
		
	}

	/**
	 * 保存 表单权限
	 * 
	 * @param bpmFormRights
	 * @param formDefId
	 * @param msg
	 * @return
	 */
//	private void importBpmFormRights(BpmFormRights bpmFormRights, Long formDefId)
//			throws Exception {
	private void importBpmFormRights(BpmFormRights bpmFormRights, String formKey) throws Exception {
		BpmFormRights formRights = bpmFormRightsDao.getById(bpmFormRights.getId());
		if (BeanUtils.isNotEmpty(formRights)) {
			bpmFormRightsDao.update(bpmFormRights);
			MsgUtil.addMsg(MsgUtil.SUCCESS, " 表单权限已经存在:"+bpmFormRights.getName()+",该记录更新!");
			//MsgUtil.addMsg(MsgUtil.WARN, "表单权限已经存在,表单权限ID："+bpmFormRights.getId()+",该记录终止导入!");
			return;
		}
	//	bpmFormRights.setFormDefId(formDefId);
		bpmFormRights.setFormKey(formKey);
		bpmFormRightsDao.add(bpmFormRights);
		MsgUtil.addMsg(MsgUtil.SUCCESS, " 表单权限:"+bpmFormRights.getName()+",该记录成功导入!");
	}

	/**
	 * 保存 数据模板
	 * 
	 * @param bpmTableTemplate
	 * @param long1
	 * @param msg
	 * @return
	 */
//	private void importBpmDataTemplate(BpmDataTemplate bpmTableTemplate,
//			Long formKey) throws Exception {
				private void importBpmDataTemplate(BpmDataTemplate bpmTableTemplate, String formKey) throws Exception {
		BpmDataTemplate tableTemplate = bpmDataTemplateDao.getById(bpmTableTemplate.getId());
		if (BeanUtils.isNotEmpty(tableTemplate)) {
			bpmDataTemplateDao.update(bpmTableTemplate);
			MsgUtil.addMsg(MsgUtil.WARN, " 数据模板已经存在,数据模板ID："+tableTemplate.getId()+",已经存在，该数据模板进行更新!");
			return;
		}
		bpmTableTemplate.setFormKey(formKey);
		bpmDataTemplateDao.add(bpmTableTemplate);
		MsgUtil.addMsg(MsgUtil.SUCCESS, " 数据模板成功导入!");
	}
	
		public void updCategory(Long categoryId, List<String> formKeyList) {
//	public void updCategory(Long categoryId,List<Long> formKeyList){
		dao.updCategory(categoryId, formKeyList);
	}
	
	/**
	 * 根据流程定义ID，取得Table ID
	 * @param defId 流程定义id
	 * @return
	 */
	public Long getTableIdByDefId(Long defId){
		List<BpmFormDef> bpmFormDefs = dao.getByDefId(defId);
		if(BeanUtils.isNotEmpty(bpmFormDefs)){
			return bpmFormDefs.get(0).getTableId();
		}else{
			return null;
		}
	}
	
	/**
	 * 根据流程定义ID，节点ID，取得流程开始表单定义。
	 * 在节点没有设置表单时，如果cascade为true，则会查询全局表单和开始表单
	 * @param actDefId
	 * @param nodeId
	 * @param cascade 是否向上查找标志
	 * @return
	 */
	public BpmFormDef getNodeFormDef(String actDefId,String nodeId,boolean cascade){
		List<BpmFormDef> defs = dao.getByActDefIdAndNodeId(actDefId, nodeId);
		if(BeanUtils.isNotEmpty(defs)){
			return defs.get(0);
		}
		
		if(!cascade){
			return null;
		}
		
		BpmFormDef def = this.getGlobalFormDef(actDefId);
		if(def!=null){
			return def;
		}
		
	
		
		return def;
		
	}
	
	
	/**
	 * 根据流程定义ID，取得流程全局表单定义
	 * @param actDefId
	 * @return
	 */
	public BpmFormDef getGlobalFormDef(String actDefId){
		List<BpmFormDef> defs = dao.getByActDefIdAndSetType(actDefId, BpmNodeSet.SetType_GloabalForm);
		if(BeanUtils.isNotEmpty(defs)){
			return defs.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 查看明细时获取表单
	 * @param processRun
	 * @param userId
	 * @param ctxPath
	 * @param variables
	 * @return
	 * @throws Exception
	 */
	public FormModel getForm(ProcessRun processRun, Long userId,String ctxPath, Map<String, Object> variables) throws Exception {
		Long defId = processRun.getDefId();
		Short formType = processRun.getFormType();
		String formKeyUrl = processRun.getFormKeyUrl();
		FormModel formModel = new FormModel();
		String parentActDefId = (String)variables.get(BpmConst.FLOW_PARENT_ACTDEFID);
		String pkValue=processRun.getBusinessKey();
		
		//processRun中保存了流程启动时的表单key或者明细表单url
		if(BeanUtils.isNotEmpty(formType)){
			if(BpmConst.OnLineForm.equals(formType)){
			//	Long formKey = Long.parseLong(formKeyUrl);
			//	BpmFormDef bpmFormDef = dao.getDefaultPublishedByFormKey(formKey);
					Long formDefId = processRun.getFormDefId();
				BpmFormDef bpmFormDef = dao.getById(formDefId);
				if(BeanUtils.isEmpty(bpmFormDef))
					throw new Exception("未能获取到显示明细的表单，可能已被删除!");
//				String pkValue = processRun.getBusinessKey();
				BpmFormTable bpmFormTable = bpmFormTableDao.getById(bpmFormDef.getTableId());
				String tableName= bpmFormTable.getFactTableName();
				bpmFormDef.setTableName(tableName);
				isDataValid(formModel, pkValue, tableName);
				if(formModel.isValid()){
					//String formHtml = bpmFormHandlerService.obtainHtml(bpmFormDef, processRun, 0L, "", ctxPath, parentActDefId);
					String formHtml = bpmFormHandlerService.obtainHtml(bpmFormDef,  0L, pkValue, processRun.getActInstId(), processRun.getActDefId(), "", ctxPath, parentActDefId, false);
					//obtainHtml(BpmFormDef bpmFormDef, ProcessRun processRun, Long userId, String nodeId,String ctxPath, String parentActDefId,boolean isCopyFlow)
//					String formHtml = bpmFormHandlerService.obtainHtml(bpmFormDef, processRun, 0L, "", ctxPath, parentActDefId);
					formModel.setFormHtml(formHtml);
				}
			}
			else{
//				String bussinessKey = processRun.getBusinessKey();
				if (StringUtil.isNotEmpty(formKeyUrl) && StringUtil.isNotEmpty(pkValue)) {
					formKeyUrl = getFormUrl(formKeyUrl, pkValue, variables,ctxPath);
					formModel.setDetailUrl(formKeyUrl);
				}
			}
		}
		//processRun中没有保存表单key时 获取流程的全局表单或开始节点表单来显示表单数据
		else{
			BpmNodeSet bpmNodeSet = null;
			if(StringUtil.isEmpty(parentActDefId)){//判断当前是否属于子流程任务
				bpmNodeSet = bpmNodeSetService.getBySetType(defId,BpmNodeSet.SetType_GloabalForm);
			}else{
				bpmNodeSet = bpmNodeSetService.getBySetType(defId,BpmNodeSet.SetType_GloabalForm, parentActDefId);
			}
			if(bpmNodeSet==null)
				logger.info("temp");
			if(bpmNodeSet!=null){
				if (BpmConst.OnLineForm.equals(bpmNodeSet.getFormType())) {
					String bussinessKey = processRun.getBusinessKey();
					BpmFormDef bpmFormDef = dao.getDefaultPublishedByFormKey(bpmNodeSet.getFormKey());
					BpmFormTable bpmFormTable = bpmFormTableDao.getById(bpmFormDef.getTableId());
					String tableName= bpmFormTable.getTableName();
					isDataValid(formModel, bussinessKey, tableName);
					if(formModel.isValid()){
						String formHtml = bpmFormHandlerService.obtainHtml(bpmFormDef,  0L, pkValue, processRun.getActInstId(), processRun.getActDefId(), "", ctxPath, parentActDefId, false);
//						String formHtml = bpmFormHandlerService.obtainHtml(bpmFormDef, processRun, 0L, "", ctxPath, parentActDefId);
						formModel.setFormHtml(formHtml);
					}
				}
				//URL表单情况
				else {
//					String bussinessKey = processRun.getBusinessKey();
					String formUrl = bpmNodeSet.getFormUrl();
					String detailUrl = bpmNodeSet.getDetailUrl();
					if (StringUtil.isNotEmpty(formUrl) && StringUtil.isNotEmpty(pkValue)) {
						formUrl = getFormUrl(formUrl, pkValue, variables, ctxPath);
						formModel.setFormUrl(formUrl);
						formModel.setFormType(BpmConst.UrlForm);
					}
					if (StringUtil.isNotEmpty(detailUrl) && StringUtil.isNotEmpty(pkValue)) {
						detailUrl = getFormUrl(detailUrl, pkValue, variables,ctxPath);
						formModel.setDetailUrl(detailUrl);
					}
				}
			}
		}
		return formModel;
	}

	/**
	 * 提交的主键和表名确定数据表单是否有效。
	 * <pre>
	 * 1.获取当前节点的表单数据对应的表。
	 * 2.如果主键存在的情况。
	 * 3.那么根据主键去这个表中获取数据。
	 * 如果能够获取到数据说明表单没有更换。否则说明表单已经变更。
	 * </pre>
	 * @param formModel
	 * @param pkValue		主键
	 * @param tableName		表名
	 */
	private void isDataValid(FormModel formModel, String pkValue,String tableName) {
		//判断业务主键是否为空。
		if(StringUtil.isEmpty(pkValue)||StringUtil.isEmpty(tableName)) return ;
		long pk=Long.parseLong(pkValue);
		boolean rtn=bpmFormHandlerService.isHasDataByPk(TableModel.CUSTOMER_TABLE_PREFIX + tableName, pk);
		formModel.setValid(rtn);
		
	}
	
	/**
	 * 判断自定义表是否绑定了表单
	 * @param tableId 自定义表ID
	 * @return 
	 * 	<code>true</code> 绑定了表单<br/>
	 *  <code>false</code> 末绑定表单
	 */ 
	public boolean isTableHasFormDef(Long tableId){
		return dao.isTableHasFormDef(tableId);
	}
	/**
	 * 根据formkey获取formdefid
	 * @param formkey
	 *            
	 * @return
	 * */
	public Long getFormDefIdByFormKey(String formkey) {
		return dao.getFormDefIdByFormKey(formkey);
	}
	/**
	 * 根据formDefId获取formKey
	 * @param formdefid
	 *            
	 * @return
	 * */
	public String getFormKeyByFormDefId(Long formdefid) {
		return dao.getFormKeyByFormDefId(formdefid);
	}
	
	/**
	 * 根据formDefId获取TableId
	 * @param formdefid
	 *            
	 * @return
	 * */
	public Long getTableIdByFormDefId(Long formdefid) {
		return dao.getTableIdByFormDefId(formdefid);
	}
	
	/**
	 * 获得html
	 * 
	 * @param formDefId
	 *            
	 * @return
	 */
	public String getHTMLByformDefId(Long formDefId) {
		return dao.getHTMLByformDefId(formDefId);
	}
	public void addjspnameandvalue(Long formKey,ArrayList<String> names ,ArrayList<String> values )
	{
		dao.addjspnameandvalue(formKey, names, values);
	}
		/**
	 * 根据tableId构建数据，键值如下：
	 * 
	 * <pre>
	 * mainname：主表描述，若描述为空，则取表名
	 * mainid：主表tableId
	 * tablename：表名
	 * mainfields：主表字段列表，List<BpmFormField> mainTableFields = bpmFormFieldService.getByTableId(tableId);
	 * 
	 * subtables：子表列表
	 * 	name：子表描述，若描述为空，则取表名
	 * 	id：子表tableId
	 * 	tablename：子表表名
	 * 	subfields：子表字段列表，List<BpmFormField> subFields = bpmFormFieldService.getByTableId(subTableId);
	 * </pre>
	 * 
	 * @param tableId
	 * @return String
	 * @see BpmFormField
	 */
	public Map<String, Object> getAllFieldsByTableId(Long tableId) {
		BpmFormTable mainTable = bpmFormTableService.getById(tableId);
		List<BpmFormField> mainTableFields = bpmFormFieldService.getByTableId(tableId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("mainname", StringUtil.isEmpty(mainTable.getTableDesc()) ? mainTable.getTableName() : mainTable.getTableDesc());
		result.put("mainid", tableId);
		result.put("tablename", mainTable.getTableName());
		result.put("mainfields", mainTableFields);

		List<BpmFormTable> subTables = bpmFormTableService.getSubTableByMainTableId(tableId);
		List<Map<String, Object>> subTableList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < subTables.size(); i++) {
			BpmFormTable subTable = subTables.get(i);
			Long subTableId = subTable.getTableId();
			List<BpmFormField> subFields = bpmFormFieldService.getByTableId(subTableId);
			Map<String, Object> subMap = new HashMap<String, Object>();
			subMap.put("name", StringUtil.isEmpty(subTable.getTableDesc()) ? subTable.getTableName() : subTable.getTableDesc());
			subMap.put("id", subTableId);
			subMap.put("tablename", subTable.getTableName());
			subMap.put("subfields", subFields);
			subTableList.add(subMap);
		}

		result.put("subtables", subTableList);
		return result;
	}
	/**
	 * 根据formDefId搜索数据包数据，返回字段的List结果集：
	 * @param formDefId
	 * @return List 
	 * @author 王钊
	 */
	@SuppressWarnings("null")
	public List<BpmFormField> getAllFieldsByformDefId(Long formDefId){
	//	System.out.println("根据formDefId搜索数据包数据，返回字段的List结果集!!!!!");
		String F_FORM_NAME = dao.getSubjectByFormDefId(formDefId);
		
        List<FormParcel> formparcels = formParcelService.getByFormName(F_FORM_NAME);
      //  System.out.println("formparcels::::::::"+formparcels.size());
        List<BpmFormField> bpmFormFields = new ArrayList<BpmFormField>();
        if(formparcels.size()!=0){
	    for(int i=0;i<formparcels.size();i++){
	    	 TableParcel tableparcel = tableParcelService.getParcelByParcelId(formparcels.get(i).getParcelID());
	    	 BpmFormTable bpmFormTable = bpmFormTableService.getByTableName(tableparcel.getTable_name());
	    	 JSONArray  parcelarray  = JSONArray.fromObject(tableparcel.getParcel_value());
	    	 for(int j=0;j<parcelarray.size();j++){
	    		 JSONObject parcelobject = parcelarray.getJSONObject(j);
	    		 String s = parcelobject.getString("field");
	    		 
	    		 BpmFormField bpmformfield = bpmFormFieldService.getFieldByTidFna(bpmFormTable.getTableId(), s);
	    		 System.out.println("bpmformfield："+bpmformfield.getFieldName());
	    		 bpmFormFields.add(bpmformfield);
	    	 } 	
	     }
        }
        else{
        	Long tableId  =  this.getTableIdByFormDefId(formDefId);
        	BpmFormTable bpmFormTable = bpmFormTableService.getTableById(tableId);
        	List<TableParcel>  tableParcels =  tableParcelService.getParcelsbyTableName(bpmFormTable.getTableName());
        	//System.out.println("tableParcels::::::::"+tableParcels.size());
        	for(int i=0;i<tableParcels.size();i++){
        		JSONArray  parcelarray  = JSONArray.fromObject(tableParcels.get(i).getParcel_value());
        		//System.out.println("parcelarray::::::::"+parcelarray.size());
        		for(int j=0;j<parcelarray.size();j++){
   	    		 JSONObject parcelobject = parcelarray.getJSONObject(j);
   	    		 String s = parcelobject.getString("field");
   	    		// System.out.println("s::::"+s);
   	    		 BpmFormField bpmformfield = bpmFormFieldService.getFieldByTidFna(bpmFormTable.getTableId(), s);
   	    		// System.out.println("bpmformfield："+bpmformfield.getFieldName());
   	    		//System.out.println("bpmFormFields："+bpmFormFields.size());
   	    		 bpmFormFields.add(bpmformfield);
   	    		//System.out.println("bpmFormFields最终："+bpmFormFields.size());
   	    	 } 	
        	}
        }
		return bpmFormFields;
	}
	
	
	//袁松
	//操作图建表单
	public void addFormDirect(BpmFormDef bpmFormDef)
	throws Exception {
		long id = UniqueIdUtil.genId();
		bpmFormDef.setFormDefId(id);
		String Id=String.valueOf(id);
		bpmFormDef.setFormKey(Id);
		bpmFormDef.setDesignType(3);
		bpmFormDef.setVersionNo(1);//设置版本
		bpmFormDef.setIsDefault((short) 1);//设置默认
		bpmFormDef.setIsPublished((short) 0);//设置发表
		
		dao.add(bpmFormDef);
		
		BpmFormDefHi bpmFormDefHi = new BpmFormDefHi(bpmFormDef); // 保持表单的操作记录
		bpmFormDefHiService.addHisRecord(bpmFormDefHi);
		}
	/**
	 * 操作图代码生成业务逻辑处理
	 * 将操作图转换为表id
	 */
	public Long xString (Long DefId){
		Long tableId = 1L;
		//调用解析方法获得操作图对应的节点
		List<String> oList = markovchainService.findBpmNode(DefId);
		for(String olist : oList){
			System.out.println("olist: "+olist);
			String nodeId = olist; 
			System.out.println("DefId:"+DefId);
			//调用解析方法解析对应的事物图节点
			List<String> cList =  markovchainService.searchOperate(nodeId, DefId);
			
			if(cList==null){
				System.out.println("null");
			}else {
				for(String clist :cList){
					Long defid = markovchainService.searchTransactionDefId(nodeId,DefId);
					
					
					//得到acedefid
					System.out.println("actDefId++++++++++:"+defid);
					BpmDefinition bpm = bpmDefinitionService.getById(defid);
					String actDefId = bpm.getActDefId();
					
					System.out.println("actDefId++++++++++:"+actDefId);
					
					
					System.out.println("clist:"+clist+"defid"+defid);
					//调用解析方法 获得 tableid 从而生成dao层和mapper层代码
					List<Long> tList = markovchainService.findtableId(clist,defid);
					System.out.println("tList:"+tList.size());
					if(tList.size()==0){
						System.out.println("空");

					}else {
						
						tableId =  tList.get(0);
						System.out.println("tableId:"+tableId);
					}
				}
				
			}
		}
		return tableId;
	}
	/**
	 * 分支前节点存入Map，返回第一个节点有分支节点下所有后续节点
	 * @param DefId
	 * @return Map
	 * @author 彭鹏
	 */
	
	public Map<String,String> displayFun (Long DefId){
		//long deId = 10000062800053L;
		//获得deId用户的相关Xml信息
		//将xml信息转换为文档
		//获取节点
		BpmDefinition bpm = bpmDefinitionService.getById(DefId);
		Long deployId = bpm.getActDeployId();//return actDeployId;
		String actxml = bpmservice.getDefXmlByDeployId(deployId.toString());//return xml;
		String actDefid = bpm.getActDefId();
		Map<String,String> flownodes = new HashMap<String, String>();
		//System.out.println(actxml);
		System.out.println("111111111111"+actDefid);
		
		try {
			
			
			List<SequenceFlow> sequenceflow = new ArrayList<SequenceFlow>();
			Document document = DocumentHelper.parseText(actxml);//将xml转换为文档
			Element root = document.getRootElement();
			List<Element> elementList=root.elements();
			Map<String,String> nodes = new HashMap<String, String>();
			//System.out.println("111111111111"+elementList.size());2
			for(Element element : elementList){//根节点下循环遍历，根节点下的第一层
																											
				
				List<Element> eles = element.elements();
				    //int k=0;
					for(Element ele : eles){
						 //System.out.println("******"+ele.getName());
						 
						 //System.out.println("***开始节点*** "+eles);
						 if(ele.getName().contains("sequenceFlow")){
							                                                                            //System.out.println("***开始节点***");
							    String nodeid = ele.attributeValue("sourceRef");
								//String targetRef = ele.attributeValue("targetRef");
								//String nodeid = ele.attributeValue("id");
																										//System.out.println("******"+sourceRef);
								if(nodeid.contains("StartEvent"))//||nodeid.contains("StartEvent")
									{
									System.out.println("***开始节点*** ");
								    nodes.put(nodeid,actDefid);
								    //System.out.println("!!!!!!!!!!!!!!!1  "+sourceRef);
								    System.out.println("nodeid:"+nodeid+"actDefid:"+actDefid);
								     flownodes = NodeCache.getNextNodes(nodeid, actDefid); //获得nextnode
								     //System.out.println("!!!!!!!!!!!!!!!1  "+flownodes);//++flownodes.get
								     
								     if (flownodes.size()<2){
								    	 //nodes.add(flownodes.get(nodeid));//将节点sourceRef放入
								    	 //System.out.println("nodeid:"+flownodes);
								    	 nodes.put(nodeid,actDefid);
								    	 System.out.println("cccnodeid:  "+nodeid);
								    	 flownodes = NodeCache.getNextNodes(nodeid, actDefid);//获得下一节点
								    	 nodeid = flownodes.keySet().toString();								    	 								    	 
								        }
								     else 
								    	 System.out.println("有分支:"+flownodes.size());continue;
									}								
								else{
									 if (flownodes.size()<2){
								    	 //nodes.add(flownodes.get(nodeid));//将节点sourceRef放入
								    	 //System.out.println("nodeid:"+flownodes);
										 nodes.put(nodeid,actDefid);
										 System.out.println("!!!nodeid:  "+nodeid);
								    	 flownodes = NodeCache.getNextNodes(nodeid, actDefid);//获得下一节点
								    	 nodeid = flownodes.keySet().toString();								    	 								    	 
								    	 //System.out.println("!%%%%nodeid:  "+nodeid);
								        }
								     else 
								    	{								    	
								    	 System.out.println(NodeCache.getpreNodes(nodeid, actDefid)+"后有分支:"+flownodes.size());
								    	 //flownodes = NodeCache.getPreNodes(nodeid, actDefid);
								    	 System.out.println("nodeid:"+flownodes);
								    	 break;
								    	}
									}								
									 								   
						
						}
					}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return  flownodes;
	}
	public List<String> xddString(Long DefId) {
		// 调用解析方法获得操作图对应的节点
		Map<Long,String> tableid = new HashMap<Long, String>();//将每个事物图对应的所有原子操作的表id放入Map
		List<String> oList = markovchainService.findBpmNode(DefId);
		//List<String> ddefids = new ArrayList<String>();
		for (String olist : oList) {//操作图中所有节点
			Map<Long,String> newtableid = new HashMap<Long, String>();
			System.out.println("olist: " + olist);
			String nodeId = olist;
			System.out.println("DefId:" + DefId);
			// 调用解析方法解析对应的事物图节点
			List<String> cList = markovchainService
					.searchOperate(nodeId, DefId);//事物图节点=（事物图id，操作图id）

			if (cList == null) {
				System.out.println("null");
			} else {
				for (String clist : cList) {
					//Long defid = markovchainService.searchTransactionDefId(
					List<String> defkeyss = markovchainService.ssearchTransactionDefId(nodeId,DefId);
							/*		nodeId, DefId);//通过操作图节点Id和操作图DefId找到节点下绑定的事务图Id
					BpmDefinition bpm = bpmDefinitionService.getById(defid);
					String actDefId = bpm.getActDefId();
					String ddefid = bpm.getDefKey();
					System.out.println("clist33333333333333:" + clist + "defid" + defid);*/
					//List<String> ddefids = new ArrayList<String>();
					//ddefids.add(ddefid);
					//return ddefids;
					//System.out.println(ddefid);
					return defkeyss;
				}
				//List<Long> xddName = markovchainService.findtableId(clist,
						//defid);
				
			}
			
		}
		//List<String> ddefids = null;
		//return ddefids;
		return null;
	}
	//7将全部事物与用户对存入list
	public List<Map<String, String>> sAndUser(Long DefId) {
		//long DefId = 10000062800053L;
		BpmDefinition bpm = bpmDefinitionService.getById(DefId);
		Long deployId = bpm.getActDeployId();
		String actxml = bpmservice.getDefXmlByDeployId(deployId.toString());// return		
		String actDefid = bpm.getActDefId();
		List<Map<String, String>> nodes = new ArrayList<Map<String, String>>();


		try {
			Document document = document = DocumentHelper.parseText(actxml);// 将xml转换为文档
			Element root = document.getRootElement();
			List<Element> elementList = root.elements();

			for (Element element : elementList) {// 根节点下循环遍历，根节点下的第一层
				List<Element> eles = element.elements();

				for (Element ele : eles) {
                     
					 System.out.println("ele的内容"+ele.getName());
					if (ele.getName().contains("sequenceFlow")) {
						//System.out.println("******sequenceFlow"+ele.getName());
						String nodeidnext = ele.attributeValue("targetRef");
						//System.out.println("******nodeidnext"+nodeidnext);
						String nodeidpre = ele.attributeValue("sourceRef");
						//System.out.println("******nodeidpre"+nodeidpre);
						if (nodeidnext.contains("UserTask")) {
							//System.out.println("******nodeidnext");
							if(nodeidpre.contains("ScriptTask")){
								//System.out.println("******");
								Map<String,String> node = new HashMap<String, String>();
								System.out.println("******"+nodeidpre+"******"+nodeidnext);
								String subString = subsystemservice.findScriptNodeDefKey11(actDefid,nodeidpre);
								node.put(nodeidpre, subString);
								node.put(nodeidnext, subString);
								nodes.add(node);
								System.out.println(node);
							}
							
						}
					}
				}				
			}

		} catch (DocumentException e) {
			e.printStackTrace();// try
		}
		return nodes;
	}
//	
	//返回多个表id
	//
		public Map<Long,String> xdString(Long DefId) {
			// 调用解析方法获得操作图对应的节点
			Map<Long,String> tableid = new HashMap<Long, String>();//将每个事物图对应的所有原子操作的表id放入Map
			List<String> oList = markovchainService.findBpmNode(DefId);
			for (String olist : oList) {//操作图中所有节点
				//Map<Long,String> newtableid = new HashMap<Long, String>();
				System.out.println("olist: " + olist);
				String nodeId = olist;
				System.out.println("DefId:" + DefId);
				// 调用解析方法解析对应的事物图节点
				List<String> cList = markovchainService
						.searchOperate(nodeId, DefId);//事物图=（事物图节点，操作图id）

				if (cList == null) {
					System.out.println("null");
				} else {
					for (String clist : cList) {
						Long defid = markovchainService.searchTransactionDefId(
								nodeId, DefId);//通过事物图节点节点Id和操作图DefId找到节点下绑定的事务图Id
						BpmDefinition bpm = bpmDefinitionService.getById(defid);
						String actDefId = bpm.getActDefId();					
						System.out.println("actDefId++++++++++:" + actDefId);
						System.out.println("clist:" + clist + "defid" + defid);
						
						// 调用解析方法 获得 tableid 从而生成dao层和mapper层代码
						List<Long> tList = markovchainService.findtableId(clist,
								defid); //解析事物图原子操作操作的表的tableId = (单个事物图节点，事务图Id)
						System.out.println("tList:" + tList.size());
						if (tList.size() == 0) {
							System.out.println("空");

						} else {
							//Map<Long,String> tableid = new HashMap<Long, String>();//将每个事物图对应的所有原子操作的表id放入Map
							
	                        for(int i=0;i<tList.size() ;i++){
	                        	
	                        	tableid.put(tList.get(i), clist) ;  					 						
	                        }
	                    
						}
						
					}

				}
				
			}
			System.out.println("  tableId+++++++++++++++++++++++:"+tableid);
			return tableid;
		}
		  //
		//将事物图及其子事物图
		//所有绑定多表单返回
		//
		
		public Map<Long,String> xdbdtString(Long DefId) {
			Stack<Map<Long,String>> stackall = stackNode(DefId) ;
			Map<Long,String> tableid = new HashMap<Long, String>();
			while(!stackall.empty()){
				String clist = "";	
				Long defid = 1L;
				Map<Long,String> idandj = stackall.pop();
				for(Map.Entry<Long,String> entry : idandj.entrySet()) {
					  clist = entry.getValue();  //取出事物图id
					  defid = entry.getKey();
					  	
					List<Long> tList = markovchainService.findtableId(clist,
							defid); //解析事物图原子操作操作的表的tableId = (单个事物图节点，事务图Id)
					System.out.println("tList:" + tList.size());
					if (tList.size() == 0) {
						System.out.println("空");
		
					} else {
						
		                for(int i=0;i<tList.size() ;i++){
		                	
		                	tableid.put(tList.get(i), clist) ;  					 						
		                }           
				    }
				}
			}
			System.out.println("tableid  : "+tableid);
			return tableid;
		}
	////子事物图递归
		////获取所有事物图（事务图Id，事物图节点）
		////
		////

		
		public Stack<Map<Long,String>> stackNode (Long DefId){
			Stack<Map<Long,String>> stackall = new Stack<Map<Long,String>>();
			Stack<Map<Long,String>> stackson = new Stack<Map<Long,String>>();
			
			List<String> oList = markovchainService.findBpmNode(DefId);
			for (String olist : oList) {//操作图中所有节点
				String nodeId = markovchainService.searchNewOperate(olist, DefId);//事物图=（事物图节点，操作图id）
				if(nodeId!= null){//将所有事务节点及其id放入栈
					Map<Long,String> scriptnodeson = new HashMap<Long, String>();
					Long defid = markovchainService.searchTransactionDefId(nodeId, DefId);//得到事务图Id
					scriptnodeson.put(defid, nodeId);//事务图Id，事物图
					stackall.push(scriptnodeson);
					stackson.push(scriptnodeson);
					System.out.println("stackson    :"+stackson);
				}
			}
			System.out.println("stackall    :"+stackall);
			while(!stackson.empty()){
				String clist = "";	
				Map<Long,String> idandj = stackson.pop();
				for(Map.Entry<Long,String> entry : idandj.entrySet()) {
					  clist = entry.getValue();  //取出事物图id
					  }	
				BpmDefinition bpm = bpmDefinitionService.getById(DefId);
				String actDefId = bpm.getActDefId();					
				String subString = subsystemservice.findScriptNodeDefKey12(actDefId,clist);
				if(!("ADD".equals(subString))&&!("DEL".equals(subString))&&!("UPD".equals(subString))&&!("QUI".equals(subString))&&!("".equals(subString))&&!("Write".equals(subString))&&!("Read".equals(subString))){
					oList = markovchainService.searchOperate(clist, DefId);
					for (String olist : oList) {//事物图内层中所有节点
						String nodeId = markovchainService.searchNewOperate(olist, DefId);//事物图节点=（事物图id，操作图id）
						if(nodeId!= null){//将所有事务节点及其id放入栈
							Map<Long,String> scriptnodeson = new HashMap<Long, String>();
							Long defid = markovchainService.searchTransactionDefId(nodeId, DefId);//得到事务图Id
							scriptnodeson.put(defid, nodeId);
							stackall.push(scriptnodeson);
							stackson.push(scriptnodeson);
							
						}
					}
				}
			}
			System.out.println(stackall);
			return stackall;
			
		}
		
		public List<BpmFormDef> getByDesc(String formKey){
			return dao.getByDesc(formKey);
		}
}
