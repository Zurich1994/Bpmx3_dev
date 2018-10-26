package com.hotent.platform.service.bpm;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import com.hotent.core.bpm.model.ProcessCmd;
import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;
import com.hotent.platform.model.bpm.ProcessRun;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.bpm.BpmNodeSetDao;
import com.hotent.platform.dao.bpm.BpmNodeUserDao;
import com.hotent.platform.dao.bpm.BpmUserConditionDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmFormResult;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.bpm.BpmUserCondition;
import com.hotent.platform.model.bpm.ConditionJsonStruct;
import com.hotent.platform.model.system.SysUser;

/**
 * 对象功能:BpmNOdeUser Service类 开发公司:广州宏天软件有限公司 开发人员:cjj 创建时间:2011-12-05 17:20:40
 */
@Service
public class BpmNodeUserService extends BaseService<BpmNodeUser> {
	@Resource
	private BpmNodeUserDao dao;
	@Resource
	private BpmNodeSetDao bpmNodeSetDao;
	@Resource
	private GroovyScriptEngine groovyScriptEngine;
	@Resource
	private BpmUserConditionDao bpmUserConditionDao;
	@Resource 
	private BpmNodeUserCalculationSelector bpmNodeUserCalculationSelector;
	@Resource
	private BpmNodeUserDao bpmNodeUserDao;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	
	public BpmNodeUserService() {
	
	}

	@Override
	protected IEntityDao<BpmNodeUser, Long> getEntityDao() {
		return dao;
	}

	
	/**
	 * 获取规则运算结果
	 * @param operatorList
	 * @param returnVal
	 * @return
	 */
	private Boolean executeOperator(List<Map<String,Object>> operatorList) {
		if(operatorList.size()==0)return true;
		Boolean returnVal = (Boolean)operatorList.get(0).get("result");
		if(operatorList.size()==1){
			return returnVal;
		}
		int size = operatorList.size();
		for(int k=1;k<size;k++){  
			Map<String,Object> resultMap=operatorList.get(k);
			String operator = resultMap.get("operator").toString();
			if("or".equals(operator)){  //或运算
				returnVal = returnVal || ((Boolean)resultMap.get("result"));
			}
			if("and".equals(operator)){ //与运算
				returnVal = returnVal &&  ((Boolean)resultMap.get("result"));
			}
		}
		return returnVal;
	}
	
	/**
	 * 根据流程定义id获取表单标识。
	 * @param actDefId
	 * @return
	 */
	private String getFormIdentity(String actDefId){
		String formIdentity="";
		BpmDefinition bpmDefinition= bpmDefinitionService.getByActDefId(actDefId);
		BpmFormResult bpmFormResult =bpmDefinitionService.getBpmFormResult(bpmDefinition.getDefId());
		if(bpmFormResult.getResult()==0){
			formIdentity=bpmFormResult.getTableName();
		}
		return formIdentity;
	}
	
	/**
	 * 根据流程定义id获取表单标识。
	 * @param actDefId
	 * @param parentActDefId
	 * @return
	 */
	private String getFormIdentity(String actDefId, String parentActDefId){
		String formIdentity="";
		BpmDefinition bpmDefinition= bpmDefinitionService.getByActDefId(actDefId);
		BpmFormResult bpmFormResult =bpmDefinitionService.getBpmFormResult(bpmDefinition.getDefId(), parentActDefId);
		if(bpmFormResult.getResult()==0){
			formIdentity=bpmFormResult.getTableName();
		}
		return formIdentity;
	}
	
	/**
	 * 计算规则结果
	 * @param conditions 规则列表
	 * @param operatorList 结果集合
	 * @param formVars 流程变量
	 */
	private void getConditionResult(List<ConditionJsonStruct> conditions,List<Map<String,Object>> operatorList,Map<String,Object> formVars){
		for(ConditionJsonStruct cond:conditions){
			//组合规则
			if(cond.getBranch()){
				List<Map<String,Object>> branchResultList = new ArrayList<Map<String,Object>>();
				getConditionResult(cond.getSub(), branchResultList, formVars);
				Boolean branchResult = executeOperator(branchResultList);
				Map<String,Object> resultMap=new HashMap<String, Object>();
				resultMap.put("result", branchResult);
				resultMap.put("operator", cond.getCompType());
				operatorList.add(resultMap);
			}
			else{
				Object formVal = formVars.get(cond.getFlowvarKey());
				if(Integer.valueOf(2).equals(cond.getRuleType())){
					String script = cond.getScript();
					if(StringUtil.isNotEmpty(script)){
						boolean result = groovyScriptEngine.executeBoolean(script, formVars);
						Map<String,Object> resultMap=new HashMap<String, Object>();
						resultMap.put("operator", cond.getCompType());
						resultMap.put("result", result);
						operatorList.add(resultMap);
					}
				}
				else{
					String script = "";
					switch (cond.getOptType()) {
						case 1://数字
							if(BeanUtils.isEmpty(formVal))
								formVal = 0;
							String formNum = formVal.toString();
							if(BeanUtils.isNotEmpty(cond.getJudgeVal1())){
								script = getCompareScript(cond.getJudgeCon1(),formNum,cond.getJudgeVal1(),true);
							}
							if(BeanUtils.isNotEmpty(cond.getJudgeVal2())){
								String moreScript = getCompareScript(cond.getJudgeCon2(),formNum,cond.getJudgeVal2(),true);
								if(StringUtil.isNotEmpty(script))
									script = script + "&&";
								script = script + moreScript;
							}
							break;
						case 2://字符串
							if(BeanUtils.isEmpty(formVal))
								formVal = "\"\"";
							String formStr = formVal.toString();
							script = getCompareScript(cond.getJudgeCon1(),formStr,cond.getJudgeVal1(),false);
							if(StringUtil.isNotEmpty(cond.getJudgeVal2())){
								String moreScript = getCompareScript(cond.getJudgeCon2(),formStr,cond.getJudgeVal2(),false);
								script = script + "&&" + moreScript;
							}
							break;
						case 3://日期
							try {
								String[] formatter = new String[]{"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss","HH:mm:ss","yyyy-MM-dd HH:mm:00"};
								Date formDate = null;
								if(formVal instanceof String){
									formDate = DateUtils.parseDate(formVal.toString(), formatter);
								}
								if(formVal instanceof Date){
									formDate = (Date)formVal;
								}
								if(formDate==null){
									script = "false";
								}
								else{
									if(StringUtil.isNotEmpty(cond.getJudgeVal1())){
										Date varDate1 = DateUtils.parseDate(cond.getJudgeVal1(), formatter);
										int date1 = formDate.compareTo(varDate1);
										Boolean dateResult1 = getDateCompareResult(cond.getJudgeCon1(),date1);
										script += dateResult1 ;
									}
									if(StringUtil.isNotEmpty(cond.getJudgeVal2())){
										Date varDate2 = DateUtils.parseDate(cond.getJudgeVal2(), formatter);
										int date2 = formDate.compareTo(varDate2);
										Boolean dateResult2 = getDateCompareResult(cond.getJudgeCon2(),date2);
										if(StringUtil.isNotEmpty(script))
											script = script + "&&";
										script = script + dateResult2;
									}
								}
							}
							catch (ParseException e) {
								e.printStackTrace();
							}
							break; 
						case 4://字典
							if(BeanUtils.isEmpty(formVal))
								formVal = "";
							String formDic = formVal.toString();
							String[] vals = cond.getJudgeVal1().split("&&");
							for(String val:vals){
								if(StringUtil.isNotEmpty(script))
									script += "&&";
								script += getCompareScript(cond.getJudgeCon1(),formDic,val,false);
							}
							break;
						case 5://角色、组织、岗位选择器
							String[] idName = cond.getJudgeVal1().split("&&");
							if(idName.length==2){
								String idVal;
								if("startUser".equals(cond.getFlowvarKey())||cond.getFlowvarKey().matches("^.*ID$"))//发起人或末尾包含ID的字段可以直接获取变量值
									idVal = (String)formVars.get(cond.getFlowvarKey());
								else{
									idVal = (String)formVars.get(cond.getFlowvarKey() + "ID");
								}
								if(idVal==null) {
									idVal="";
								}
								List<String> list = new ArrayList();
								String[] idVals = idVal.split(",");
								for(String val : idVals){
									list.add(val);
								}
								formVal = list;
								String[] ids = idName[0].split(",");
								for(String id:ids){
									if(StringUtil.isNotEmpty(script))
										script += "||";
									script += getCompareScript(cond.getJudgeCon1(),cond.getFlowvarKey(),id,true);
								}
							}
							break;
						}
						if(StringUtil.isEmpty(script))continue;
						//执行结果记录到operatorList中
						Map<String, Object> scriptvars = new HashMap<String, Object>();	
						scriptvars.put(cond.getFlowvarKey(), formVal);
						Boolean result = groovyScriptEngine.executeBoolean(script, scriptvars);
						Map<String,Object> resultMap=new HashMap<String, Object>();
						resultMap.put("operator", cond.getCompType());//与运算
						resultMap.put("result", result);
						operatorList.add(resultMap);
					}
				}
			}
	}
	
	/**
	 * 计算规则是否成立
	 * @param currentCondition 规则实体
	 * @param formIdentity 表名称
	 * @param formVars 流程变量
	 * @return
	 */
	public Boolean conditionCheck(BpmUserCondition currentCondition,String formIdentity ,Map<String,Object> formVars) {
		Boolean isPassCondition=true;
		String tmpId = currentCondition.getFormIdentity();
		//条件没有表单标识或者当前的标识和之前设置的标识不一致时，则认为规则 成立
		if(StringUtil.isEmpty(tmpId) ||  !tmpId.equals(formIdentity)){
			return true;
		}
		
		if(StringUtil.isEmpty(currentCondition.getCondition())){
			return isPassCondition;
		}
		
		List<Map<String,Object>> operatorList=new ArrayList<Map<String,Object>>();
		List<ConditionJsonStruct> conditions = currentCondition.getConditionJson();
		getConditionResult(conditions, operatorList, formVars);
		//根据表单规则判断当前表单是否符合规则	
		isPassCondition = executeOperator(operatorList);

		return isPassCondition;
	}

	/**
	 * 获取日期的比较结果
	 * @param compare 比较运算符号
	 * @param d 两个日期的比较结果
	 * @return
	 */
	private Boolean getDateCompareResult(String compare,int d){
		if("==".equals(compare))
			return d==0;
		if("!=".equals(compare))
			return d!=0;
		if(">".equals(compare))
			return d==1;
		if("<".equals(compare))
			return d==-1;
		if(">=".equals(compare))
			return d==0||d==1;
		if("<=".equals(compare))
			return d==0||d==-1;
		return true;
	}
	
	/**
	 * 获取比较值的脚本
	 * @param compare 比较符号
	 * @param val1 比较值1
	 * @param val2 比较值2
	 * @return
	 */
	private String getCompareScript(String compare,String val1,String val2,Boolean isObj){
		String script = "";
		if(compare.contains("()")){
			if(compare.contains("notContains")){
				if(isObj)
					script = "!" + val1 + ".contains" + "('"+val2+"')";
				else
					script = "!'" + val1 + "'.contains" + "('"+val2+"')";
			}
			else{
				if(isObj)
					script = val1 + "." + compare.replace("()", "") + "('"+val2+"')";
				else
					script ="'" + val1 + "'." + compare.replace("()", "") + "('"+val2+"')";
			}
		}
		else{
			if(isObj)
				script = val1 +  compare + val2;
			else
				script = "'" + val1 + "'" + compare+"'"+val2+"'";
		}
		return script;
	}
	

	/**
	 * 根据excution获取人员数据。
	 * @param execution
	 * @param bpmUserConditionList
	 * @param preTaskUserId
	 * @return
	 */
	public List<SysUser> getTaskExecutors(DelegateExecution execution,List<BpmUserCondition> bpmUserConditionList,Long preTaskUserId){
		Map<String,Object> processVars = execution.getVariables();
		String actDefId = execution.getProcessDefinitionId();
		String actInstId = execution.getProcessInstanceId();
		String startUserId=(String)processVars.get(BpmConst.StartUser);
		String preTaskExecutorId = "";
		if(preTaskUserId!=null){
			preTaskExecutorId=String.valueOf(preTaskUserId);
		}
		
		List<SysUser> list=getExecutorsByConditions(bpmUserConditionList, actDefId, actInstId, startUserId, preTaskExecutorId, processVars,true);
		return list;
	}
	
	public List<BpmNodeUser> getByConditionId(Long conditionId) {
		return dao.getByConditionId(conditionId);
	}

	/**
	 * 计算发起人启动某个流程后，某个节点应该由哪些用户来执行。
	 * <pre>
	 * 	返回用户数据包括流程参与者和通知人。
	 *  1.参与者键为PARTICIPATION。BpmNodeUser.USER_TYPE_PARTICIPATION
	 *  2.通知人键为NOTIFY。BpmNodeUser.USER_TYPE_NOTIFY
	 * </pre>
	 * @param actDefId
	 * @param actInstId
	 * @param nodeId
	 * @param startUserId
	 * @return 执行的用户ID列表
	 * @throws Exception
	 */
	public  List getExeUserIds(String actDefId, String actInstId, String nodeId, String startUserId,String preTaskUser,Map<String,Object> vars) {
		
		logger.debug("startUserId:" +startUserId +",preTaskUser:" + preTaskUser);
		List list=new ArrayList();
		String parentActDefId = (String)vars.get(BpmConst.FLOW_PARENT_ACTDEFID);;
		BpmNodeSet bpmNodeSet = null;
		if(StringUtil.isEmpty(parentActDefId)){
			bpmNodeSet = bpmNodeSetDao.getByActDefIdNodeId(actDefId, nodeId);
		}else{
			bpmNodeSet = bpmNodeSetDao.getByActDefIdNodeId(actDefId, nodeId, parentActDefId);
		}
		if(bpmNodeSet==null) return list;
		//
		//根据setId获取条件列表。
		List<BpmUserCondition> bpmUserConditionList = bpmUserConditionDao.getBySetId(bpmNodeSet.getSetId());
		if(BeanUtils.isEmpty(bpmUserConditionList)) return list;
		
		list=getExecutorsByConditions(bpmUserConditionList, actDefId, actInstId, startUserId, preTaskUser, vars,false);
		
		return list;
	}
	
	/**
	 * 根据条件列表，流程定义ID,流程实例ID,发起人ID,上个执行人ID,流程变量,是否取得所有的用户。
	 * @param bpmUserConditionList		条件列表
	 * @param actDefId					流程定义id
	 * @param actInstId					流程实例id
	 * @param startUserId				发起人id
	 * @param preTaskUser				上一个任务执行人
	 * @param vars						流程变量
	 * @param isUser					是否直接抽取用户，这个在抄送，消息节点人员获取时使用。
	 * @return
	 */
	private List getExecutorsByConditions(List<BpmUserCondition> bpmUserConditionList,
			String actDefId,String actInstId,String startUserId,String preTaskUser,Map<String,Object> vars,boolean isUser){
			String parentActDefId = (String)vars.get(BpmConst.FLOW_PARENT_ACTDEFID);
			String formIdentity="";
			if(StringUtil.isEmpty(parentActDefId)){
				formIdentity=getFormIdentity(actDefId);
			}else{
				formIdentity=getFormIdentity(actDefId, parentActDefId);
			}
			List list=new ArrayList();
			//排序
			Collections.sort(bpmUserConditionList);
			
			Set userSet=new LinkedHashSet();
			//是否找到了执行人
			boolean hasExecutor=false;
			
			BpmUserCondition prevCondition=null;
			for(int i=0;i<bpmUserConditionList.size();i++){
				BpmUserCondition currentCondition = bpmUserConditionList.get(i);
				Long conditionId=currentCondition.getId();
				//上一个规则不为空 ,并且前面的批次和当前的批次不一样 且之前的规则找到用户了，这个时候跳出循环。
				if(prevCondition!=null && !prevCondition.getGroupNo().equals(currentCondition.getGroupNo()) && hasExecutor ){
					break;
				}
				//检查条件
				boolean isPass = conditionCheck(currentCondition, formIdentity, vars);
				if(!isPass) continue;
				//取得执行人
				List partUsers=getExecutors(conditionId, actDefId, actInstId,  startUserId, preTaskUser,vars,isUser);
				if(partUsers.size()>0){
					userSet.addAll(partUsers);
					hasExecutor=true;
				}
				prevCondition = currentCondition;
			}
			list.addAll(userSet);
			return list;
	}
	
	
	
	
	/**
	 * 取得任务执行人。
	 * @param bpmNodeUser	节点执行人。
	 * @param extractUser	是否抽取用户。
	 * @param startUserId	流程发起人。
	 * @param preTaskUser	上一个节点执行人。
	 * @param actInstId		流程实例ID。
	 * @return
	 */
	private Set<TaskExecutor> getByBpmNodeUser(BpmNodeUser bpmNodeUser,String startUserId,String preTaskUserId,String actInstId,Map<String,Object> vars){		
		
		Long lStartUserId = 0L;
		Long lPrevTaskUserId = 0L;
		if(StringUtil.isNotEmpty(startUserId)){
			lStartUserId=Long.parseLong(startUserId);
		}
		if(StringUtil.isNotEmpty(preTaskUserId)){
			lPrevTaskUserId = Long.parseLong(preTaskUserId);
		}
	
	
		CalcVars params= new CalcVars(lStartUserId,lPrevTaskUserId,actInstId,vars);
		IBpmNodeUserCalculation calculation = bpmNodeUserCalculationSelector.getByKey(bpmNodeUser.getAssignType());
		return calculation.getTaskExecutor(bpmNodeUser, params);
	}
	
	/**
	 * 直接获取用户。
	 * @param bpmNodeUser
	 * @param startUserId
	 * @param preTaskUserId
	 * @param actInstId
	 * @param vars
	 * @return
	 */
	private Set<SysUser> getUsersByBpmNodeUser(BpmNodeUser bpmNodeUser,String startUserId,String preTaskUserId,String actInstId,Map<String,Object> vars){		
		Long lStartUserId = 0L;
		Long lPrevTaskUserId = 0L;
		if(StringUtil.isNotEmpty(startUserId)){
			lStartUserId=Long.parseLong(startUserId);
		}
		if(StringUtil.isNotEmpty(preTaskUserId)){
			lPrevTaskUserId = Long.parseLong(preTaskUserId);
		}
		
	
		CalcVars params= new CalcVars(lStartUserId,lPrevTaskUserId,actInstId,vars);
		IBpmNodeUserCalculation calculation = bpmNodeUserCalculationSelector.getByKey(bpmNodeUser.getAssignType());
		Set<SysUser> set=new HashSet<SysUser>();
		set.addAll(calculation.getExecutor(bpmNodeUser, params));
		return set;
	}
	
	/**
	 * 计算获取用户。
	 * @param nodeUsers
	 * @param actDefId
	 * @param actInstId
	 * @param nodeId
	 * @param startUserId
	 * @param preTaskUser
	 * @return
	 */
	public List getExecutors(Long conditionId,String actDefId, String actInstId,  String startUserId,String preTaskUser,Map<String,Object> vars,boolean isUser){
		List<BpmNodeUser> nodeUsers = bpmNodeUserDao.getByConditionId(conditionId);
		List list=new ArrayList();
		Set userIdSet = new HashSet();
		
		for (BpmNodeUser bpmNodeUser : nodeUsers) {
			Set uIdSet=null;
			if(isUser){
				uIdSet=getUsersByBpmNodeUser( bpmNodeUser, startUserId, preTaskUser, actInstId,vars);
			}
			else{
				uIdSet=getByBpmNodeUser( bpmNodeUser, startUserId, preTaskUser, actInstId,vars);
			}
			
			if (userIdSet.size() == 0) {
				userIdSet = uIdSet;
			}
			else {
				userIdSet = computeUserSet(bpmNodeUser.getCompType(), userIdSet, uIdSet);
			}
		}
		list.addAll(userIdSet);
		return list;
	}
	
		

	
	
	
	/**
	 * 根据对应模板conditionId删除记录
	 * @Methodname: delByTemplateIds
	 * @Discription: 
	 * @param conditionId
	 * @Author HH
	 * @Time 2012-12-19 下午7:33:50
	 */
	public void delByConditionId(Long conditionId){
		dao.delByConditionId(conditionId);
	}

	/**
	 * 修复数据
	 * @return
	 */
	public List<BpmNodeUser> selectNull(){
		return dao.selectNull();
	}
	
	
	/**
	 * 根据流程节点的用户设置，取得相应的用户列表
	 * @param bpmNodeUser 流程节点的用户设置
	 * @param startUserId 流程发起人
	 * @param preTaskUserId 前一个节点的执行人
	 * @param actInstId 流程实例ID
	 * @return 用户列表
	 */
	public List<SysUser> getNodeUser(BpmNodeUser bpmNodeUser,Long startUserId,Long preTaskUserId,
			String actInstId,Map<String,Object> vars){
		CalcVars params= new CalcVars(startUserId,preTaskUserId,actInstId, vars);
		IBpmNodeUserCalculation calculation = bpmNodeUserCalculationSelector.getByKey(bpmNodeUser.getAssignType());
		return calculation.getExecutor(bpmNodeUser, params);
	}
	
	/**
	 * 获取预览用户。
	 * <pre>
	 * 如果算法不支持预览的话，那么返回null。
	 * </pre>
	 * @param bpmNodeUser
	 * @param startUserId
	 * @param preTaskUserId
	 * @param actInstId
	 * @param extractUser
	 * @param vars
	 * @return
	 */
	public List<SysUser> getPreviewNodeUser(BpmNodeUser bpmNodeUser,Long startUserId,Long preTaskUserId,
			String actInstId,Map<String,Object> vars){
		CalcVars params= new CalcVars(startUserId,preTaskUserId,actInstId, vars);
		IBpmNodeUserCalculation calculation = bpmNodeUserCalculationSelector.getByKey(bpmNodeUser.getAssignType());
		if(calculation.supportPreView()){
			return calculation.getExecutor(bpmNodeUser, params);
		}
		return null;
	}
	
	
	
	
	
	/**
	 * 通过actDefId获取流程设置的抄送人员
	 * @param actDefId
	 * @param executionId
	 * @param instanceId
	 * @param startUserId
	 * @param vars
	 * @param preTaskUser
	 * @return
	 */
	public List<SysUser> getCopyUserByActDefId(String actDefId,String instanceId,Long startUserId,Map<String,Object> vars,String preTaskUser){
		List<SysUser> list=new ArrayList<SysUser>();
		String parentActDefId = (String)vars.get(BpmConst.FLOW_PARENT_ACTDEFID);
		List<BpmUserCondition> userConditions = null;
		if(StringUtil.isEmpty(parentActDefId)){
			userConditions = bpmUserConditionDao.getCcByActDefId(actDefId);
		}else{
			userConditions = bpmUserConditionDao.getCcByActDefId(actDefId, parentActDefId);
		}
		if(BeanUtils.isEmpty(userConditions))return list;
		
		list=getExecutorsByConditions(userConditions, actDefId, instanceId, startUserId.toString(), preTaskUser, vars,true);
		
		return list;
		
		
	}
	
	/**
	 * 计算两个集合的交集或合集或排除
	 * 
	 * @param computeType
	 * @param userIdSet
	 *            原集合
	 * @param newUserSet
	 *            新集合
	 * @return
	 */
	public   Set computeUserSet(short computeType, Set userIdSet, Set newUserSet) {
		if (newUserSet == null) return userIdSet;
		if (BpmNodeUser.COMP_TYPE_AND==computeType) {// 交集
			Set orLastSet = new LinkedHashSet();
			Iterator uIt = userIdSet.iterator();
			while (uIt.hasNext()) {
				Object key = uIt.next();
				if (newUserSet.contains(key)) {
					orLastSet.add(key);
				}
			}
			return orLastSet;
		} else if (BpmNodeUser.COMP_TYPE_OR==computeType) {
			userIdSet.addAll(newUserSet);
		} else {// 排除
			for (Object newUserId : newUserSet) {
				userIdSet.remove(newUserId);
			}
		}
		return userIdSet;
	}
	
	/**
	 * 根据ProceCmd userCondition获取人员数据。
	 * @return
	 */
	public List<SysUser> getUserByCondition(ProcessCmd cmd,List<BpmUserCondition> bpmUserConditionList,Long preTaskUserId){
		Map<String,Object> processVars = cmd.getVariables();
		ProcessRun run =cmd.getProcessRun();
		String actDefId = run.getActDefId();
		String actInstId = run.getActInstId();
		String startUserId=(String)processVars.get(BpmConst.StartUser);
		String preTaskExecutorId = "";
		if(preTaskUserId!=null){
			preTaskExecutorId=String.valueOf(preTaskUserId);
		}
		
		List<SysUser> list=getExecutorsByConditions(bpmUserConditionList, actDefId, actInstId, startUserId, preTaskExecutorId, processVars,true);
		return list;
	}
	

}
