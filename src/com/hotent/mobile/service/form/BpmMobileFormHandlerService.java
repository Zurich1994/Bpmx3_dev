package com.hotent.mobile.service.form;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.mobile.model.form.BpmMobileForm;
import com.hotent.mobile.model.form.PlatformType;
import com.hotent.platform.dao.form.BpmFormDefDao;
import com.hotent.platform.model.bpm.BpmFormRun;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.form.BpmFormData;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.SubTable;
import com.hotent.platform.service.bpm.BpmFormRunService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.form.BpmFormRightsService;

/**
 * 手机表单
 * 
 * @author zxh
 * 
 */
@Service
public class BpmMobileFormHandlerService extends
		BaseService<BpmMobileFormHandlerService> {
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private BpmFormHandlerService bpmFormHandlerService;
	@Resource
	private BpmFormRunService bpmFormRunService;
	@Resource
	private BpmFormDefDao bpmFormDefDao;
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	@Resource
	private BpmMobileFormService bpmMobileFormService;

	@Resource
	private BpmFormRightsService bpmFormRightsService;

	@Override
	protected IEntityDao<BpmMobileFormHandlerService, Long> getEntityDao() {
		return null;
	}

	/**
	 * 获取任务表单
	 * 
	 * @param actInstanceId
	 * @param nodeId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getTaskForm(String actInstanceId, String nodeId)
			throws Exception {
		// ProcessRun processRun = processRunService
		// .getByActInstanceId(instanceId);
		// String actDefId = processRun.getActDefId();

		Map<String, Object> map = new HashMap<String, Object>();

		BpmMobileForm bpmMobileForm = bpmMobileFormService
				.getByProcessInstacne(actInstanceId, nodeId);
		if (BeanUtils.isEmpty(bpmMobileForm))
			throw new Exception("未获取手机表单");
		map.put("mobileFormId", bpmMobileForm.getId());
		if (BeanUtils.isEmpty(bpmMobileForm.getId())) {
			map.put("template", bpmMobileForm.getTemplate());
		}
		// // 取运行的表单
		// BpmFormRun bpmFormRun = bpmFormRunService.getByInstanceAndNode(
		// instanceId, nodeId);
		// // 表单运行时存在。
		// if (bpmFormRun != null) {
		// map.put("formId", bpmFormRun.getFormdefId());
		// map.put("formKey", bpmFormRun.getFormdefKey());
		// return map;
		// } else {
		// // 取不到取全局正在运行的表单
		// bpmFormRun = bpmFormRunService.getByInstanceAndNode(instanceId,
		// null);
		// if (bpmFormRun != null) {
		// map.put("formId", bpmFormRun.getFormdefId());
		// map.put("formKey", bpmFormRun.getFormdefKey());
		// return map;
		// }
		// }
		//
		// // 表单运行时不存在的情况。
		// BpmNodeSet bpmNodeSet =
		// bpmNodeSetService.getByActDefIdNodeId(actDefId,
		// nodeId);
		// if (bpmFormDefService.isFormEmpty(bpmNodeSet)) {
		// Long defId = processRun.getDefId();
		// bpmNodeSet = bpmNodeSetService.getBySetType(defId,
		// BpmNodeSet.SetType_GloabalForm);
		// }
		// if (bpmNodeSet == null)
		// return null;
		// // 获取在线表单
		// if (BpmConst.OnLineForm.shortValue() == bpmNodeSet.getFormType()
		// .shortValue()) {
		// BpmFormDef bpmFormDef = bpmFormDefDao
		// .getDefaultPublishedByFormKey(bpmNodeSet.getFormKey());
		// map.put("formId", bpmFormDef.getFormDefId());
		// map.put("formKey", bpmFormDef.getFormKey());
		// return map;
		// }
		// // URL表单情况。
		// else {
		// System.out.println("不支持的类型");
		// }
		return map;
	}

	/**
	 * 获取表单数据（包含任务、流程实例的表单）
	 * <br/>表单分为： 1.在线表单。 2.url表单。
	 * <pre>
	 * 1.首先去BpmFormRun中获取表单数据。
	 * 2.没有获取到则获取当前节点的表单设置。
	 * 3.获取全局表单设置。
	 * </pre>
	 * @param processRun
	 * @param nodeId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getFormData(ProcessRun processRun, String nodeId, Long userId)
			throws Exception {
		String instanceId = processRun.getActInstId();
		String actDefId = processRun.getActDefId();
		String businessKey = processRun.getBusinessKey();
		Long defId = processRun.getDefId();
		Map<String, Object> map =  new HashMap<String, Object>();

		BpmFormRun bpmFormRun = bpmFormRunService.getByInstanceAndNode(
				instanceId, nodeId);
	
		//子流程先不解决 TODO
//		BpmNodeSet bpmNodeSet = null;
//		String parentActDefId = "";
//		if(variables.containsKey(BpmConst.FLOW_PARENT_ACTDEFID)){//判断当前是否属于子流程任务
//			parentActDefId = (String)variables.get(BpmConst.FLOW_PARENT_ACTDEFID);
//			bpmNodeSet = bpmNodeSetService.getByActDefIdNodeId(actDefId, nodeId, parentActDefId);
//		}else{
//			bpmNodeSet = bpmNodeSetService.getByActDefIdNodeId(actDefId, nodeId);
//		}
		

		// 表单运行时存在。
		if (bpmFormRun != null) {
			return getBpmFormRunData(bpmFormRun, businessKey, instanceId,
					actDefId, nodeId,userId);
		} else {
			// 取全局正在运行的表单
			bpmFormRun = bpmFormRunService.getByInstanceAndNode(
					instanceId, null);
			if (bpmFormRun != null) {
				return getBpmFormRunData(bpmFormRun, businessKey, instanceId,
						actDefId, nodeId,userId);
			}
		}

		// 表单运行时不存在的情况。
		BpmNodeSet bpmNodeSet = bpmNodeSetService.getByActDefIdNodeId(actDefId,
				nodeId);
		if (this.isFormEmpty(bpmNodeSet)) {
			bpmNodeSet = bpmNodeSetService.getBySetType(defId,
					BpmNodeSet.SetType_GloabalForm);
		}
		if (bpmNodeSet == null)
			return map;
		// 获取在线表单
		if (BpmConst.OnLineForm.shortValue() == bpmNodeSet.getFormType()
				.shortValue()) {
			BpmFormDef bpmFormDef = bpmFormDefDao
					.getDefaultPublishedByFormKey(bpmNodeSet.getFormKey());
			return getBpmFormData(bpmFormDef,businessKey,instanceId, actDefId, nodeId,userId,false );
		}
		// URL表单情况。
		else {
			System.out.println("不支持的类型");
		}

		return  map;
	}
	
	public boolean isFormEmpty(BpmNodeSet bpmNodeSet) {
		if (bpmNodeSet == null) {
			return true;
		}
		short formType = bpmNodeSet.getFormType();
		String formKey = bpmNodeSet.getFormKey();
		// 没有设置表单的情况
		if (formType == -1) {
			return true;
		}
		// 在线表单的情况
		if (formType == 0) {
			if (StringUtil.isEmpty(formKey)) {
				return true;
			}
		}
		// url表单的情况。
		else {
			String formUrl = bpmNodeSet.getFormUrl();
			if (StringUtils.isEmpty(formUrl)) {
				return true;
			}
		}
		return false;
	}

	
	
	private Map<String, Object> getBpmFormData(BpmFormDef bpmFormDef, String businessKey, String instanceId,
			String actDefId, String nodeId, Long userId, boolean isCopyFlow) throws Exception {
		Map<String, Object> map =  new HashMap<String, Object>();
		if (BeanUtils.isEmpty(bpmFormDef))
			return map;
		Long tableId = bpmFormDef.getTableId();
		String permission = this.getPermission(bpmFormDef,userId, actDefId, nodeId);
		String  data =  this.getFormDataJson(tableId, businessKey, instanceId, actDefId,
				nodeId, false);
		map.put("data", data);
		map.put("permission", permission);
		return map;
	}

	/**
	 * 表单的JSON数据
	 * 
	 * @param tableId
	 * @param businessKey
	 * @param instanceId
	 * @param actDefId
	 * @param nodeId
	 * @param isCopyFlow
	 * @return
	 * @throws Exception
	 */
	private String getFormDataJson(Long tableId, String businessKey,
			String instanceId, String actDefId, String nodeId,
			boolean isCopyFlow) throws Exception {
		BpmFormData data = bpmFormHandlerService.getBpmFormData(tableId,
				businessKey, instanceId, actDefId, nodeId, isCopyFlow);
		// 主表
		JSONObject mainFields = JSONObject.fromObject(data.getMainFields());

		JSONObject sub = new JSONObject();
		// 子表
		for (SubTable subTable : data.getSubTableList()) {
			JSONArray subFields = new JSONArray();
			if (BeanUtils.isNotEmpty(subTable.getDataList()))
				subFields = JSONArray.fromObject(subTable.getDataList());
			sub.accumulate(subTable.getTableName(), subFields.toString());
		}

		// 意见
		JSONArray opinions = new JSONArray();
		for (Iterator<Entry<String, String>> iterator = data.getOptions()
				.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> opinion = iterator.next();
			JSONObject json = new JSONObject();
			json.element("name", opinion.getKey());
			json.element("value", opinion.getValue());
			opinions.add(json);
		}
		// 拼接json数据
		JSONObject json = new JSONObject();
		json.element("main", mainFields.toString())
				.element("sub", sub.toString())
				.element("opinion", opinions.toString());

		return json.toString();
	}




	
	/**
	 * * field：{"NAME": "w", "SEX": "r"} table：{"TABLE1": "r", "TABLE2": "w"}
	 * opinion：{"领导意见": "w", "部门意见": "r"}
	 * 
	 * @param bpmFormDef
	 * @param userId
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	private String getPermission(BpmFormDef bpmFormDef, Long userId,
			String actDefId, String nodeId) {
		Map<String, Map<String, String>> permission = null;
		if (userId > 0) {
			permission = bpmFormRightsService.getByFormKeyAndUserId(bpmFormDef.getFormKey(),
					userId, actDefId, nodeId,null,1,PlatformType.mobile);
		} else {
			permission = bpmFormRightsService.getByFormKey(bpmFormDef);
		}

		JSONObject p = JSONObject.fromObject(permission);
		JSONObject json = new JSONObject();
		json.accumulate("permission", p);
		return json.toString();
	}

	/**
	 * 获取运行时的数据（私有）
	 * 
	 * @param processRun
	 * @param bpmFormRun
	 * @param instanceId
	 * @param actDefId
	 * @param nodeId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getBpmFormRunData(BpmFormRun bpmFormRun,String businessKey,String instanceId, String actDefId,
			String nodeId, Long userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Long formDefId = bpmFormRun.getFormdefId();
		String formDefKey = bpmFormRun.getFormdefKey();
		BpmFormDef bpmFormDef  = null;
		if(BeanUtils.isNotEmpty(formDefId) && formDefId.longValue() >0L )
			bpmFormDef = bpmFormDefDao.getById(formDefId);
		else if(StringUtil.isNotEmpty(formDefKey) )
			bpmFormDef = bpmFormDefDao.getDefaultVersionByFormKey(formDefKey);
		if(BeanUtils.isEmpty(bpmFormDef))
			return  map;
		return getBpmFormData(bpmFormDef, businessKey, instanceId, actDefId, nodeId, userId, false);

	}

	/**
	 * 获取运行的表单
	 * 
	 * @param runId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getProcessForm(Long runId) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		BpmMobileForm bpmMobileForm = bpmMobileFormService.getByProcessRun(
				runId, null);
		if (BeanUtils.isEmpty(bpmMobileForm))
			throw new Exception("未获取手机表单");
		map.put("mobileFormId", bpmMobileForm.getId());
		if (BeanUtils.isEmpty(bpmMobileForm.getId())) {
			map.put("template", bpmMobileForm.getTemplate());
		}
		// Long instanceId = new Long(processRun.getActInstId());
		// // 取全局正在运行的表单
		// BpmFormRun bpmFormRun = bpmFormRunService.getByInstanceAndNode(
		// instanceId, null);
		// if (bpmFormRun != null) {
		// map.put("formId", bpmFormRun.getFormdefId());
		// map.put("formKey", bpmFormRun.getFormdefKey());
		// return map;
		// }
		//
		// // 表单运行时不存在的情况。
		//
		// Long defId = processRun.getDefId();
		// BpmNodeSet bpmNodeSet = bpmNodeSetService.getBySetType(defId,
		// BpmNodeSet.SetType_GloabalForm);
		//
		// if (bpmNodeSet == null)
		// return null;
		// // 获取在线表单
		// if (BpmConst.OnLineForm.shortValue() == bpmNodeSet.getFormType()
		// .shortValue()) {
		// BpmFormDef bpmFormDef = bpmFormDefDao
		// .getDefaultPublishedByFormKey(bpmNodeSet.getFormKey());
		// map.put("formId", bpmFormDef.getFormDefId());
		// map.put("formKey", bpmFormDef.getFormKey());
		// return map;
		// }
		// // URL表单情况。
		// else {
		// System.out.println("不支持的类型");
		// }
		return map;
	}

	/**
	 * 获取启动的表单
	 * 
	 * @param actDefId
	 * @param toFirstNode
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getStartFlowForm(String actDefId,
			short toFirstNode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		BpmMobileForm bpmMobileForm = bpmMobileFormService
				.getMobileFormForStart(actDefId);
		if (BeanUtils.isEmpty(bpmMobileForm))
			throw new Exception("未获取手机表单");
		map.put("mobileFormId", bpmMobileForm.getId());
		return map;
	}

	/**
	 * 启动流程的数据
	 * 
	 * @param actDefId
	 * @param defId
	 * @param businessKey
	 * @param isCopyFlow
	 * @param userId 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getStartFlowData(String actDefId, Long defId,
			String businessKey, boolean isCopyFlow, Long userId) throws Exception {
		BpmNodeSet bpmNodeSet = bpmFormRunService.getStartBpmNodeSet(defId,
				actDefId);
		Map<String, Object> map = new HashMap<String, Object>();
		if (BeanUtils.isEmpty(bpmNodeSet))
			return map;
		if (bpmNodeSet.getFormType().shortValue() == BpmConst.OnLineForm
				.shortValue()) {
			String formKey = bpmNodeSet.getFormKey();
			if (StringUtil.isEmpty(formKey))
				return map;
			BpmFormDef bpmFormDef = bpmFormDefService
					.getDefaultPublishedByFormKey(formKey);
			return getBpmFormData(bpmFormDef, businessKey, "", actDefId, bpmNodeSet.getNodeId(), userId, isCopyFlow);
		}
		return map;
	}

}
