package com.hotent.platform.service.mobile;

import javax.annotation.Resource;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.form.BpmFormTableDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.mobile.MobileFormData;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormHandlerService;

/**
 * 处理手机流程业务
 * 
 * @author cjj
 * 
 */
@Service
public class MobileTaskService extends BaseService<ProcessTask> {
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private BpmService bpmService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	@Resource
	private BpmFormTableDao bpmFormTableDao;
	@Resource
	private BpmFormHandlerService bpmFormHandlerService;
	@Resource
	private MobileFormHandlerService moblieFormHandlerService;

	@Override
	protected IEntityDao<ProcessTask, Long> getEntityDao() {
		return null;
	}

	/**
	 * 获取手机需要显示的表单数据
	 * 
	 * @param taskEntity
	 * @param ctxPath
	 * @return { taskId=10000011380027, 任务id form={ 表单 "emptyForm":true, 是否空表单
	 *         "extForm":true, 是否url表单 "fields":"", 主表字段 "formDetailUrl":"",
	 *         url明细表单 "formEditUrl":"", url编辑表单 "options":{}, 审批意见
	 *         "signTask":false, 是否会签任务 "subTableList":[], 子表字段 "tableDesc":"",
	 *         主表描述 "tableId":0, 主表id "tableName":"" 主表名称 } }
	 * @throws Exception
	 */
	public MobileFormData getFormData(TaskEntity taskEntity, String ctxPath)
			throws Exception {

		String taskId = taskEntity.getId();
		String instanceId = taskEntity.getProcessInstanceId();
		String nodeId = taskEntity.getTaskDefinitionKey();
		String actDefId = taskEntity.getProcessDefinitionId();
		Long userId = ContextUtil.getCurrentUserId();

		ProcessRun processRun = processRunService.getByActInstanceId(new Long(
				instanceId));

		String parentActDefId = processRunService.getParentProcessRunActDefId(processRun);
		BpmNodeSet bpmNodeSet = null;
		if(StringUtil.isEmpty(parentActDefId)){
			bpmNodeSet = bpmNodeSetService.getByMyActDefIdNodeId(actDefId,
					nodeId);
			if (BeanUtils.isEmpty(bpmNodeSet) || bpmNodeSet.getFormType() == -1) {
				bpmNodeSet = bpmNodeSetService.getBySetType(processRun.getDefId(),
						BpmNodeSet.SetType_GloabalForm);
			}
		}else{
			bpmNodeSet = bpmNodeSetService.getByMyActDefIdNodeId(actDefId,
					nodeId, parentActDefId);
			if (BeanUtils.isEmpty(bpmNodeSet) || bpmNodeSet.getFormType() == -1) {
				bpmNodeSet = bpmNodeSetService.getBySetType(processRun.getDefId(),
						BpmNodeSet.SetType_GloabalForm, parentActDefId);
			}
		}
		MobileFormData mobileFormData = new MobileFormData();
		BpmDefinition bpmDefinition = bpmDefinitionService
				.getByActDefId(actDefId);
		// 是否是特殊流程
		boolean isParticular = this.isParticular(bpmDefinition);
		if (isParticular) {
			mobileFormData = moblieFormHandlerService.getMobileParticularForm(
					mobileFormData, bpmNodeSet, processRun, taskId, ctxPath,
					nodeId, userId, bpmDefinition);
			mobileFormData.setParticular(isParticular);
		} else {
			mobileFormData = moblieFormHandlerService.getMobileForm(
					mobileFormData, bpmNodeSet, processRun, taskId, ctxPath,
					nodeId, userId);
		}

		mobileFormData.setSignTask(bpmService.isSignTask(taskEntity));// 是否会签任务
		return mobileFormData;
	}

	/**
	 * 是否是特殊流程
	 * 
	 * @param bpmDefinition
	 * @return
	 */
	private boolean isParticular(BpmDefinition bpmDefinition) {

		if (BeanUtils.isEmpty(bpmDefinition))
			return false;
		return false;
	}

	/**
	 * 根据流程运行Id 获取对应的表单数据
	 * 
	 * @param runId
	 *            实例id
	 * @param userId
	 *            用户id
	 * @param ctxPath
	 *            项目根目录
	 * @param taskId
	 *            任务id
	 * @return { taskId=10000011380027, 任务id form={ 表单 "emptyForm":true, 是否空表单
	 *         "extForm":true, 是否url表单 "fields":"", 主表字段 "formDetailUrl":"",
	 *         url明细表单 "formEditUrl":"", url编辑表单 "options":{}, 审批意见
	 *         "signTask":false, 是否会签任务 "subTableList":[], 子表字段 "tableDesc":"",
	 *         主表描述 "tableId":0, 主表id "tableName":"" 主表名称 } }
	 */
	public MobileFormData getProcessData(Long runId, Long userId, String ctxPath)
			throws Exception {

		MobileFormData mobileFormData = new MobileFormData();
		ProcessRun processRun = processRunService.getById(runId);
		if (BeanUtils.isEmpty(processRun))
			throw new Exception("找不到该流程实例");
		BpmDefinition bpmDefinition = bpmDefinitionService
				.getByActDefId(processRun.getActDefId());
		Short formType = processRun.getFormType();
		
		String pkValue = processRun.getBusinessKey();
		Long defId = processRun.getDefId();
		String parentActDefId = processRunService.getParentProcessRunActDefId(processRun);
		// processRun中保存了流程启动时的表单key或者明细表单url
		if (BeanUtils.isNotEmpty(formType)) {
			if (BpmConst.OnLineForm.equals(formType)) {
				Long formDefId = processRun.getFormDefId();
				mobileFormData = getOnLineFormData(mobileFormData,
						bpmDefinition, formDefId, pkValue, processRun, userId,ctxPath);
			} else {
				String formUrl=processRun.getFormKeyUrl();
				mobileFormData = getUrlFormData(mobileFormData, bpmDefinition,
						formUrl, ctxPath, pkValue);
			}
		}
		// processRun中没有保存表单key时 获取流程的全局表单或开始节点表单来显示表单数据
		else {
			BpmNodeSet bpmNodeSet = null;
			if(StringUtil.isEmpty(parentActDefId)){
				bpmNodeSet = bpmNodeSetService.getBySetType(defId, BpmNodeSet.SetType_GloabalForm);
			}else{
				bpmNodeSet = bpmNodeSetService.getBySetType(defId, BpmNodeSet.SetType_GloabalForm, parentActDefId);
			}
			if (bpmNodeSet == null){
				if(StringUtil.isEmpty(parentActDefId)){
					bpmNodeSet = bpmNodeSetService.getBySetType(defId, BpmNodeSet.SetType_StartForm);
				}else{
					bpmNodeSet = bpmNodeSetService.getBySetType(defId, BpmNodeSet.SetType_StartForm, parentActDefId);
				}
			}
			if (bpmNodeSet != null) {
				if (BpmConst.OnLineForm.equals(bpmNodeSet.getFormType())) {
					BpmFormDef bpmFormDef = bpmFormDefService
							.getDefaultPublishedByFormKey(bpmNodeSet
									.getFormKey());
					BpmFormTable bpmFormTable = bpmFormTableDao
							.getById(bpmFormDef.getTableId());
					if (isDataValid(mobileFormData, pkValue,
							bpmFormTable.getTableName())) {
						mobileFormData = moblieFormHandlerService
								.getMobileFormData(bpmFormDef, pkValue,
										processRun, mobileFormData, userId,
										bpmNodeSet.getNodeId(),ctxPath);
					}
				}
				// URL表单情况
				else {
					String formUrl=processRun.getFormKeyUrl();
					mobileFormData = getUrlFormData(mobileFormData,
							bpmDefinition, formUrl, ctxPath, pkValue);
				}
			}
		}
		return mobileFormData;
	}

	/**
	 * 获取URL表单的数据
	 * 
	 * @param mobileFormData
	 * @param bpmDefinition
	 * @param formKeyUrl
	 * @param ctxPath
	 * @param pkValue
	 * @return
	 */
	private MobileFormData getUrlFormData(MobileFormData mobileFormData,
			BpmDefinition bpmDefinition, String formKeyUrl, String ctxPath,
			String pkValue) {
		boolean isExtForm = false;
		boolean isEmptyForm = false;
		String formUrl = "";
		String url = bpmDefinition.getFormDetailUrl();
		if (StringUtil.isNotEmpty(url) && StringUtil.isNotEmpty(pkValue)) {
			isExtForm = true;
			formUrl = url;
			formUrl = formUrl.replaceFirst(BpmConst.FORM_PK_REGEX, pkValue);
			if (!formUrl.startsWith("http")) {
				formUrl = ctxPath + formUrl;
			}
		} else {
			isEmptyForm = true;
		}

		mobileFormData.setEmptyForm(isEmptyForm);
		mobileFormData.setExtForm(isExtForm);
		mobileFormData.setFormDetailUrl(formUrl);

		return mobileFormData;
	}

	/**
	 * 获取在线表单的数据
	 * 
	 * @param mobileFormData
	 * @param bpmDefinition
	 * @param formKeyUrl
	 * @param pkValue
	 * @param actInstId
	 * @return
	 * @throws Exception
	 */
	private MobileFormData getOnLineFormData(MobileFormData mobileFormData,
			BpmDefinition bpmDefinition, Long formDefId, String pkValue,
			ProcessRun processRun, Long userId,String ctxPath) throws Exception {
		
		BpmFormDef bpmFormDef = bpmFormDefService.getById(formDefId)				;
		if (BeanUtils.isEmpty(bpmFormDef))
			throw new Exception("未能获取到显示明细的表单，可能已被删除!");

		BpmFormTable bpmFormTable = bpmFormTableDao.getById(bpmFormDef
				.getTableId());
		String tableName = bpmFormTable.getTableName();
		bpmFormDef.setTableName(tableName);

		if (this.isDataValid(mobileFormData, pkValue, tableName)) {
			// 是否是特殊流程
			boolean isParticular = this.isParticular(bpmDefinition);
			if (isParticular) {
				String actInstId = processRun.getActInstId();
				mobileFormData = moblieFormHandlerService
						.getMobileParticularFormDate(bpmDefinition, bpmFormDef,
								pkValue, actInstId, mobileFormData);
				mobileFormData.setParticular(isParticular);
			} else {
				mobileFormData = moblieFormHandlerService.getMobileFormData(
						bpmFormDef, pkValue, processRun, mobileFormData,
						userId, null,ctxPath);
			}
		}
		return mobileFormData;
	}

	/**
	 * 获取流程起始表单。
	 * 
	 * @param bpmNodeSet
	 *            流程节点设置，可以是全局后者起始节点绑定的表单。
	 * @param pkValue
	 *            流程业务主键
	 * @param actDefId
	 *            流程定义ID
	 * @param ctxPath
	 *            上下文路径。
	 * @return
	 * @throws Exception
	 */
	public MobileFormData getForm(BpmNodeSet bpmNodeSet, String pkValue,
			String actDefId, String ctxPath) throws Exception {
		MobileFormData mobileFormData = new MobileFormData();
		if (BeanUtils.isEmpty(bpmNodeSet))
			return mobileFormData;
		if (bpmNodeSet.getFormType().shortValue() == BpmConst.OnLineForm
				.shortValue()) {
			String formKey = bpmNodeSet.getFormKey();
			if (StringUtil.isEmpty(formKey))
				return mobileFormData;

			BpmFormDef bpmFormDef = bpmFormDefService
					.getDefaultPublishedByFormKey(formKey);

			if (BeanUtils.isEmpty(bpmFormDef))
				return mobileFormData;

			BpmFormTable bpmFormTable = bpmFormTableDao.getById(bpmFormDef
					.getTableId());
			String tableName = bpmFormTable.getTableName();
			bpmFormDef.setTableName(tableName);
			// 表单验证有效。
			if (this.isDataValid(mobileFormData, pkValue, tableName)) {
				mobileFormData = moblieFormHandlerService.getMobileFormData(
						bpmFormDef, pkValue, mobileFormData,ctxPath);
			}
			mobileFormData.setFormKey(formKey);

		}
		// TODO 暂时不处理URL表单
		else {
			String formUrl = bpmNodeSet.getFormUrl();
			// 替换主键。
			formUrl = formUrl.replaceFirst(BpmConst.FORM_PK_REGEX, pkValue);
			if (!formUrl.startsWith("http")) {
				formUrl = ctxPath + formUrl;
			}
			// mobileFormData.setFormType(BpmConst.UrlForm);
			// mobileFormData.setFormUrl(formUrl);
		}

		return mobileFormData;
	}

	/**
	 * 提交的主键和表名确定数据表单是否有效。
	 * 
	 * <pre>
	 * 1.获取当前节点的表单数据对应的表。
	 * 2.如果主键存在的情况。
	 * 3.那么根据主键去这个表中获取数据。
	 * 如果能够获取到数据说明表单没有更换。否则说明表单已经变更。
	 * </pre>
	 * 
	 * @param mobileFormData
	 * @param pkValue
	 *            主键
	 * @param tableName
	 *            表名
	 */
	private Boolean isDataValid(MobileFormData mobileFormData, String pkValue,
			String tableName) {
		// 判断业务主键是否为空。
		if (StringUtil.isEmpty(pkValue))
			return true;
		long pk = Long.parseLong(pkValue);
		boolean rtn = bpmFormHandlerService.isHasDataByPk(
				TableModel.CUSTOMER_TABLE_PREFIX + tableName, pk);
		mobileFormData.setValid(rtn);
		return rtn;
	}

	/**
	 * 完成任务，执行下一步（包含下一步。驳回、驳回发起人等操作） 调用接口
	 * 
	 * @param processCmd
	 * @throws Exception
	 */
	public void complete(ProcessCmd processCmd) throws Exception {
		processRunService.nextProcess(processCmd);
	}

}
