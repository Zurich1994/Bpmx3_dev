package com.hotent.platform.service.bpm;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.bpm.BpmDefinitionDao;
import com.hotent.platform.dao.bpm.BpmProCopytoDao;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmProCopyto;
import com.hotent.platform.model.bpm.CcMessageType;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysTemplateService;

/**
 *<pre>
 * 对象功能:流程抄送转发 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:heyifan
 * 创建时间:2013-03-27 15:07:58
 *</pre>
 */
@Service
public class BpmProCopytoService extends BaseService<BpmProCopyto>
{
	protected Logger logger = LoggerFactory.getLogger(BaseController.class);
	@Resource
	private BpmProCopytoDao dao;
	@Resource
	private BpmDefinitionDao bpmDefinitionDao;
	@Resource
	private BpmNodeUserService bpmNodeUserService;
	@Resource
	private TaskMessageService taskMessageService;
	
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private SysTemplateService sysTemplateService;
	@Resource
	private ProcessRunService processRunService;
	
	public BpmProCopytoService()
	{
	}
	
	@Override
	protected IEntityDao<BpmProCopyto, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 标记为已读
	 * @param ids
	 */
	public void updateReadStatus(String ids){
		if(StringUtils.isEmpty(ids))return;
		String[] idArr = ids.split(",");
		for(String id : idArr){
			if(StringUtils.isEmpty(id))continue;
			Long copyId = Long.parseLong(id);
			if(copyId>0){
				BpmProCopyto bpmProCopyto = dao.getById(copyId);
				bpmProCopyto.setIsReaded(1L);
				bpmProCopyto.setReadTime(new Date());
				dao.update(bpmProCopyto);
			}
		}
	}
	
	/**
	 * 获取我的抄送列表
	 * @param queryFilter
	 * @return
	 */
	public List<BpmProCopyto> getMyList(QueryFilter queryFilter){
		return dao.getMyList(queryFilter);
	}
	
	/**
	 * 标记为已读
	 * @param ids
	 */
	public void markCopyStatus(String id){
		if(StringUtils.isEmpty(id))return;
		BpmProCopyto bpmProCopyto = dao.getById(Long.parseLong(id));
		bpmProCopyto.setIsReaded(1L);
		bpmProCopyto.setReadTime(new Date());
		dao.update(bpmProCopyto);
	}
	
	/**
	 * 处理抄送任务
	 * @param executionEntity
	 * @param vars
	 */
	public void handlerCopyTask(ProcessRun processRun,Map<String,Object> vars,String preTaskUserId,BpmDefinition bpmDefinition) throws Exception{
		if(BeanUtils.isEmpty(bpmDefinition))return;
		
		String actDefId = processRun.getActDefId();
		Long defTypeId = bpmDefinition.getTypeId();
		
		Integer allowFinishedCc = bpmDefinition.getAllowFinishedCc();
		//流程未设置允许办结抄送时  不处理抄送
		if(allowFinishedCc.intValue()!=1)return;
		String subject = processRun.getSubject();
		if(BeanUtils.isEmpty(processRun))return;
		Long startUserId = processRun.getCreatorId();
		String instanceId = processRun.getActInstId();
		List<SysUser> copyConditionUsers =bpmNodeUserService.getCopyUserByActDefId(actDefId, instanceId, startUserId, vars, preTaskUserId);
		
		
		if(BeanUtils.isEmpty(copyConditionUsers))return;
		//记录抄送日志。
		logger.info("handlerCopyTask:" +actDefId +"," + instanceId + "," + processRun.getRunId() +"," + subject );
		
		
		addCopyTo(copyConditionUsers,processRun,defTypeId);
		handlerCopyMessage(actDefId, copyConditionUsers,vars,subject,processRun.getRunId(), bpmDefinition.getCcMessageType());
	}
	
	private void addCopyTo(List<SysUser> set,ProcessRun processRun,Long defTypeId){
		String instanceId=processRun.getActInstId();
		Long startUserId=processRun.getCreatorId();
		String creator=processRun.getCreator();
		
		for(Iterator<SysUser> it=set.iterator();it.hasNext();){
			SysUser  executor=it.next();
			
			String copyUserId = executor.getUserId().toString();
			String copyUserName = executor.getFullname();
			BpmProCopyto bpmProCopyto = new BpmProCopyto();
			bpmProCopyto.setActInstId(Long.parseLong(instanceId));
			bpmProCopyto.setCcTime(new Date());
			bpmProCopyto.setCcUid(Long.parseLong(copyUserId));
			bpmProCopyto.setCcUname(copyUserName);
			bpmProCopyto.setCopyId(UniqueIdUtil.genId());
			bpmProCopyto.setCpType(BpmProCopyto.CPTYPE_COPY);
			bpmProCopyto.setIsReaded(0L);
			bpmProCopyto.setRunId(processRun.getRunId());
			bpmProCopyto.setSubject(processRun.getSubject());
			bpmProCopyto.setDefTypeId(defTypeId);
			bpmProCopyto.setCreateId(startUserId);
			bpmProCopyto.setCreator(creator);
			dao.add(bpmProCopyto);
		}
	}
	
	/**
	 * 处理抄送提醒消息
	 * @param conditionId
	 * @param copyUsers
	 * @throws Exception 
	 */
	private void handlerCopyMessage(String actDefId,List<SysUser> receiverUserList,Map<String,Object> vars,String subject,Long runId, String ccMessageType) throws Exception{
		if(BeanUtils.isEmpty(receiverUserList))return;
		SysUser curUser=ContextUtil.getCurrentUser();
		String informTypes = "";
		if(StringUtils.isEmpty(ccMessageType)){
			//若抄送类型未设置，则默认为站内消息和邮件
			ccMessageType = "{\"inner\":1,\"mail\":1,\"sms\":0}";
		}
		JSONObject jsonObj=JSONObject.fromObject(ccMessageType);
		CcMessageType cc =(CcMessageType)JSONObject.toBean(jsonObj, CcMessageType.class);
		
		Map<String,String> msgTempMap = sysTemplateService.getTempByFun(SysTemplate.USE_TYPE_COPYTO);
		
		if(cc.getMail()==1){//邮件
			if(!informTypes.contains(BpmConst.MESSAGE_TYPE_MAIL))
				informTypes = informTypes+","+BpmConst.MESSAGE_TYPE_MAIL;
		}
		
		if(cc.getInner()==1){//站内消息
			if(!informTypes.contains(BpmConst.MESSAGE_TYPE_INNER))
				informTypes = informTypes+","+BpmConst.MESSAGE_TYPE_INNER;
		}
		
		if(cc.getSms()==1){//短息
			if(!informTypes.contains(BpmConst.MESSAGE_TYPE_SMS))
				informTypes = informTypes+","+BpmConst.MESSAGE_TYPE_SMS;
		}
		
		//记录抄送信息日志
		logger.info("[发送抄送提醒信息]subject:" +subject +",informTypes:" + informTypes + ",runId:" + runId );
		
	
		taskMessageService.sendMessage(curUser, receiverUserList, informTypes, msgTempMap, subject, "", null, runId,null);

	}
	

	
	/**
	 * 根据运行Id获取用户数据。
	 * @param queryFilter
	 * @return
	 */
	public List<BpmProCopyto> getUserInfoByRunId(QueryFilter queryFilter){
		return dao.getUserInfoByRunId(queryFilter);
	}
	
	/**
	 * 根据流程runId删除抄送转发事宜
	 * @param runId
	 */
	public void delByRunId(Long runId) {
		dao.delByRunId(runId);
	}
}
