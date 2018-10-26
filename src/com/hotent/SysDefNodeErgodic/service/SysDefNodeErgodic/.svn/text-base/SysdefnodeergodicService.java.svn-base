package com.hotent.SysDefNodeErgodic.service.SysDefNodeErgodic;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.engine.impl.util.json.JSONArray;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fr.base.core.json.JSONException;
import com.fr.bi.cube.engine.third.edu.emory.mathcs.backport.java.util.Collections;
import com.fr.report.core.A.H;
import com.hotent.activityDetail.dao.activityDetail.ActivityDetailDao;
import com.hotent.activityDetail.model.activityDetail.ActivityDetail;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.Markovchain.model.Markovchain.Markovchain;
import com.hotent.Markovchain.service.Markovchain.MarkovchainService;
import com.hotent.Subsystemdef.dao.Subsystemdef.SubsystemdefDao;
import com.hotent.Subsystemdef.model.Subsystemdef.Subsystemdef;
import com.hotent.SysDefNodeErgodic.model.SysDefNodeErgodic.Sysdefnodeergodic;
import com.hotent.SysDefNodeErgodic.dao.SysDefNodeErgodic.SysdefnodeergodicDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;
import com.hotent.dbFunc.model.dbFunc.DbFunc;
import com.hotent.dbFunc.service.dbFunc.DbFuncService;
import com.hotent.formParcel.model.formParcel.FormParcel;
import com.hotent.formParcel.service.formParcel.FormParcelService;
import com.hotent.platform.dao.bpm.BpmDefinitionDao;
import com.hotent.platform.dao.bpm.BpmNodeSetDao;
import com.hotent.platform.dao.system.SubSystemDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.system.OperateNodeInfo;
import com.hotent.platform.model.system.TransactionInfo;
import com.hotent.platform.model.system.TransactionNodeInfo;
import com.hotent.platform.model.system.WDefInformation;
import com.hotent.platform.model.system.WNodeInformation;
import com.hotent.platform.model.system.WOperateInfo;
import com.hotent.platform.model.system.WSysdefnodeComparator;
import com.hotent.platform.model.system.WSysdefnodeComparator2;
import com.hotent.platform.model.system.WSysdefnodeComparator3;
import com.hotent.platform.model.system.WSysdefnodeComparator4;
import com.hotent.platform.model.system.WSystemInformation;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmNodeWebServiceService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.tableParcel.model.tableParcel.TableParcel;
import com.hotent.tableParcel.service.tableParcel.TableParcelService;


@Service
public class SysdefnodeergodicService extends BaseService<Sysdefnodeergodic>
{
	@Resource
	private SysdefnodeergodicDao dao;
	@Resource
	private SubSystemDao subSystemDao;
	@Resource	
	private BpmDefinitionDao  bpmDefinitionDao;//流程信息表		
	@Resource
	private SubsystemdefDao subsystemdefDao;//子系统流程名字绑定表
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	@Resource
	private BpmNodeSetDao  bpmNodeSetDao;//流程 节点绑定表
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private ActivityDetailDao activityDetailDao;//节点设置表
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private BpmNodeWebServiceService bpmNodeWebServiceService;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private BpmFormFieldService bpmFormFieldService;
	@Resource
	private FormParcelService formParcelService;
	@Resource
	private TableParcelService tableParcelService;
	@Resource
	private MarkovchainService markovchainService;
	@Resource
	private DbFuncService dbFuncService;
	
	public SysdefnodeergodicService()
	{
	}
	
	@Override
	protected IEntityDao<Sysdefnodeergodic,Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 流程处理器方法 用于处理业务数据
	 * @param cmd
	 * @throws Exception
	 */
	public void processHandler(ProcessCmd cmd)throws Exception{
		Map data=cmd.getFormDataMap();
		if(BeanUtils.isNotEmpty(data)){
			String json=data.get("json").toString();
			Sysdefnodeergodic sysdefnodeergodic=getSysdefnodeergodic(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				sysdefnodeergodic.setId(genId);
				this.add(sysdefnodeergodic);
			}else{
				sysdefnodeergodic.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(sysdefnodeergodic);
			}
			cmd.setBusinessKey(sysdefnodeergodic.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Sysdefnodeergodic对象
	 * @param json
	 * @return
	 */
	public Sysdefnodeergodic getSysdefnodeergodic(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Sysdefnodeergodic sysdefnodeergodic = (Sysdefnodeergodic)JSONObject.toBean(obj, Sysdefnodeergodic.class);
		return sysdefnodeergodic;
	}
	/**
	 * 保存 子系统流程节点遍历 信息
	 * @param sysdefnodeergodic
	 */

	public void save(Sysdefnodeergodic sysdefnodeergodic) throws Exception{
		Long id=sysdefnodeergodic.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			sysdefnodeergodic.setId(id);
		    this.add(sysdefnodeergodic);
		}
		else{
		   this.update(sysdefnodeergodic);
		}
	}
		
		public WSystemInformation getlianjie(WSystemInformation sys_info){	//三维（子系统 流程  节点）链接
			List<Subsystemdef> subsystemdefList = subsystemdefDao.getDefKey(Long.valueOf(sys_info.getSystem_id()));//获得与id[i]匹配的流程名字相关信息
				if(subsystemdefList==null||subsystemdefList.size()==0){//如果没有与id【i】子系统匹配的流程名字
					System.out.println("该子系统"+sys_info.getSystem_id()+"没有绑定流程");
				}else{//找到与该子系统id【i】，相互匹配的流程
					for(Subsystemdef subsys:subsystemdefList){//遍历流程名字
						List<BpmDefinition> def_List=bpmDefinitionDao.getByDefKey(subsys.getSys_defkey());//获得与流程名字匹配的流程链表
						if(def_List==null||def_List.size()==0){//如果没找到与流程subsys.getSys_defkey()相匹配的流程id																		
							System.out.println("子系统："+sys_info.getSystem_id()+"中"+subsys.getSys_defkey()+"该流程已删除");
						}
						else{//在bpmdefinition中找到与该流程名字匹配的流程链表
							Long DefId=def_List.get(def_List.size()-1).getDefId();//得到最新的流程id 即最下方的流程对象
							String actDefId=def_List.get(def_List.size()-1).getActDefId();//得到最新的流程信息  即最下方的流程对象
							WDefInformation def_info=new WDefInformation(DefId);//因为通过了判断说明有流程对象存在，创建流程对象
							def_info.bpmdef=def_List.get(def_List.size()-1);//将最新的该流程的信息，放入流程对象的bpmdef流程基本信息属性
							def_info.typeName="flowChart";
							def_info=defGetlianjie(def_info,sys_info.getSystem_name());	
							sys_info.setNotSetNum(sys_info.getNotSetNum()+def_info.getNotSetNum());							
							sys_info.sys_def_info_list.add(def_info);//将流程对象放入子系统的流程list中				
						}
					}
				}		
		
			return sys_info;
		}
		public WDefInformation defGetlianjie(WDefInformation def_info,String sysName) {//获得没有不全的流程对象
			// TODO Auto-generated method stub
			def_info = setDefMarkovchainId(def_info);
			String actDefId=def_info.bpmdef.getActDefId();
			Long DefId=def_info.bpmdef.getDefId();
			String defXml = def_info.bpmdef.getDefXml();
			JdbcTemplate jdbcTemplate = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
			List<Map<String, Object>> tba_List=jdbcTemplate.queryForList(//获得该流程的成熟度						
				"select f_work_subsys_maturity from w_tba_busaly_subsys where f_actdef_id=\""+actDefId+"\"");									 						
			//System.out.println("获得TBA—List"+tba_List);	
			if(tba_List==null||tba_List.size()==0)System.out.println("在流程统计表中未找到该流程的信息，成熟度为1");
			else{
				def_info.setF_work_subsys_maturity(Double.valueOf(tba_List.get(0).get("f_work_subsys_maturity").toString()));//将流程成熟度存入流程对象中
				//System.out.println("在流程统计表中找到该流程的信息，成熟度为"+def_info.getF_work_subsys_maturity());							
			}
			System.out.println("获得节点ing  actdefid="+actDefId);
			List<BpmNodeSet> bpmNodeIdList=bpmNodeSetDao.getBy_ActDef(actDefId);//获得与流程id匹配的节点相关信息
			
			List<String> nodeList = new ArrayList<String>();
			def_info.defNodeList = new ArrayList<String>();
			nodeList = find(defXml);//调用解析找不是用户节点的节点
			def_info.setDefNodeList(nodeList);
			
			System.out.println("获得该流程节点的list");
			if(bpmNodeIdList.size()==0){//如果没找到与该流程id相互匹配的节点信息						
				System.out.println("流程信息为："+DefId+"该流程无节点");
			}
			else{//找到与该流程匹配的节点list
				for(BpmNodeSet bpmnode:bpmNodeIdList){//遍历节点list							
					 	System.out.println("节点基本信息已经设置");
						System.out.println("创建节点单位");
						WNodeInformation node_info=new WNodeInformation(bpmnode.getActDefId(),bpmnode.getNodeId());//创建节点对象	
						node_info.bpmNodeSet=bpmnode;//将该节点信息写入
						List<ActivityDetail> a=activityDetailDao.getBy_ActDefId_ActivityId(bpmnode.getActDefId(),bpmnode.getNodeId());//获得wactivity中节点设置信息放入设置属性中
						if(a.size()==0)//该节点未设置，wactivity置为空
						{
							node_info.activityDetail=null;
							def_info.setNotSetNum(def_info.getNotSetNum()+1);
						}
						else 
						{   activityDetailDao.updataSysDefNodeName(bpmnode.getActDefId(),bpmnode.getNodeId(), sysName, def_info.bpmdef.getSubject(), bpmnode.getNodeName());	
							node_info.activityDetail=a.get(0);//如果不为空，说明找到了该节点的设置信息，将其复制给节点的设置信息对象中				
						}
			
						String bpmNodeName =bpmnode.getNodeId();//获得当前节点名
						String bpmDefXml = def_info.bpmdef.getDefXml();//得到节点所在的流程图的xml
						//System.out.println("节点名"+bpmNodeName);
						//System.out.println("###Xml###"+bpmDefXml);
						String operateDefKey = findOperateDefKey(bpmDefXml,bpmNodeName);//调用函数解析xml返回绑定的操作图的operateDefKey
						//System.out.println("######"+operateDefKey);
						if(operateDefKey==""){
							System.out.println("该节点没有帮定图");
						}
						else{
							List<BpmDefinition> bpmOperateInfoList =bpmDefinitionService.getByDefKey(operateDefKey);//找到绑定的操作图对象,返回值为List
							if(bpmOperateInfoList.size()==0){
								System.out.println(bpmNodeName+"绑定的操作图"+operateDefKey+"已删除");
								
							}
							else{
							int e =bpmOperateInfoList.size();
							//System.out.println("长度"+e);
							BpmDefinition bpmOperateInfo = bpmOperateInfoList.get(e-1);//得到List中第一个对象
							//System.out.println("11111"+bpmOperateInfo);
							node_info.operateInfo=new WOperateInfo(bpmOperateInfo);
							
							//node_info.operateInfo.bpmDefinition=new BpmDefinition();
							//node_info.operateInfo.setBpmDefinition(bpmOperateInfo);//将操作图对象放入流程节点中的操作图对象
							node_info.operateInfo = setOpeMarkovchainId(node_info.operateInfo);//给操作图对象下的马尔科夫链进行赋值
							//调用函数
							node_info.operateInfo = statisticsOperate(node_info.operateInfo);	
							}
							
						}					
						def_info.def_node_info_list.add(node_info);//将该节点对象放入流程的节点list中										
				}
			}
			def_info = getNodeTimesInMark(def_info);//统计流程节点的次数	
			return def_info;//返回已经补全节点信息的流程对象
		}
		/**
		 *查找操作图下的节点及节点下绑定的事务图或表单
		 * @param operateInfo
		 * @return operateInfo
		 * @author 白晓帆
		 */
		public WOperateInfo statisticsOperate(WOperateInfo operateInfo){//操作图
			
			String operateDefXml = operateInfo.bpmDefinition.getDefXml();//得到bpmDefinition对象 的Xml
			Long operateDefId = operateInfo.bpmDefinition.getDefId();
			List<Map> operateNodeNamelist = findOperateNode(operateDefXml);//解析xml返回节点List
			operateInfo.allBpmFormDefList = new ArrayList<BpmFormDef>();
			operateInfo.allTableList = new ArrayList<String>();
			operateInfo.allMethodList = new ArrayList<String>();
			operateInfo.allMTList = new ArrayList<String>();
			operateInfo.operateNodeInfoList = new ArrayList<OperateNodeInfo>();//实例化操作图下的节点List，准备接收找到的节点List			
			//operateInfo.operateNodeInfoList = new ArrayList<OperateNodeInfo>();//实例化操作图下的节点List，准备接收找到的节点List
			//System.out.println("operateNodeInfoList:+++++++++++++++++=="+operateInfo.operateNodeInfoList.size());
			if(operateNodeNamelist.size()!=0){
				
				//System.out.println("取到的长度:+++++++++++++++++++++++"+operateNodeNamelist.size());
				for(int m=0;m<operateNodeNamelist.size();m++){
					Map nodeMap = operateNodeNamelist.get(m);
					Set<String>keys =nodeMap.keySet();
					 if(keys != null){
							
						 Iterator iterator = keys.iterator();  
				            while(iterator.hasNext()){ 
				            	Object key = iterator.next();
				            String n = key.toString();
				            	Object value = nodeMap.get(key); 
					//System.out.println("m的值  :"+m);
					OperateNodeInfo operateNodeInfo = new OperateNodeInfo();
					//operateInfo.operateNodeInfo =new OperateNodeInfo();
					operateNodeInfo.setNodeId(n);//将节点名存到操作图对象下
					operateNodeInfo.setNodeName(value.toString());
				     
					
					//System.out.println("operateInfo.operateNodeInfo:"+operateNodeInfo);
					//System.out.println("operateNodeName:"+xx.nodeName);
					//operateInfo.operateNodeInfoList.add(m,operateNodeInfo);//将节点操作图节点对象放到节点List下
					
					
					operateInfo.setOperateNodeInfoList(m, operateNodeInfo);
					//System.out.println("添加后长度 ：---------------------"+operateInfo.operateNodeInfoList.size());
				
					//System.out.println("###################次数："+operateInfo.operateNodeInfoList.get(m).getDependExeNum());
				
					String  nodeId =operateInfo.getOperateNOdeInfo(m).getNodeId();//得到操作图节点名，解析它下面的事务图
					
					if(nodeId.contains("ScriptTask")){
						String transactionDefKey = findOperateDefKey(operateDefXml,nodeId);//解析操作图xml找事务图DefKey
						if(transactionDefKey==null||transactionDefKey==""){
							System.out.println("节点："+nodeId+"没有绑定事务图!");
							continue;
						}
						//System.out.println("******事务图的Key："+transactionDefKey);
						List<BpmDefinition> bpmTransactionInfoList = bpmDefinitionService.getByDefKey(transactionDefKey);
						//System.out.println("*I*****事物图个数："+bpmTransactionInfoList.size());
						if(bpmTransactionInfoList.size()==0){
							System.out.println("节点："+nodeId+"绑定的事务图已被删除！");
							continue;
						}
						BpmDefinition bpmTransactionInfo = bpmTransactionInfoList.get(bpmTransactionInfoList.size()-1);//得到事物对象
						operateInfo.getOperateNOdeInfo(m).transactionInfo= new TransactionInfo();
						operateInfo.getOperateNOdeInfo(m).transactionInfo.bpmtransaction = bpmTransactionInfo;//赋值到操作图节点类下
						//调用函数
						operateInfo.getOperateNOdeInfo(m).transactionInfo = statisticsTransaction(operateInfo.getOperateNOdeInfo(m).transactionInfo);
						if(operateInfo.getOperateNOdeInfo(m).transactionInfo.allMethodList.size()!=0){
							operateInfo.allMTList.addAll(operateInfo.getOperateNOdeInfo(m).transactionInfo.allMethodList);
							List<String> tableList = new ArrayList<String>();
							List<String> methodList = new ArrayList<String>();
//							for(int i = 0;i<operateInfo.getOperateNOdeInfo(m).transactionInfo.allMethodList.size();i+=2){
//								String table = operateInfo.getOperateNOdeInfo(m).transactionInfo.allMethodList.get(i);
//								tableList.add(table);
//							}
							for(int i = 0;i<operateInfo.getOperateNOdeInfo(m).transactionInfo.allMethodList.size();i++){
								if(i%2==0){
									String table = operateInfo.getOperateNOdeInfo(m).transactionInfo.allMethodList.get(i);
									tableList.add(table);
								}
								else{
									String method = operateInfo.getOperateNOdeInfo(m).transactionInfo.allMethodList.get(i);
									methodList.add(method);
								}
								
							}
							//System.out.println("####################111111:"+operateInfo.getOperateNOdeInfo(m).transactionInfo.allMethodList.size());
							//System.out.println("####################222222:"+tableList.size());
							operateInfo.allTableList.addAll(tableList);
							operateInfo.allMethodList.addAll(methodList);
						}
						
						
					}
					if(nodeId.contains("userTask")||nodeId.contains("UserTask")){//用户节点下找表单
						//System.out.println();
						BpmNodeSet bpmNodeSet = new BpmNodeSet();
						bpmNodeSet = bpmNodeSetService.getByDefIdNodeId(operateDefId, nodeId);//通过操作图defId和节点名找到节点对象
						String formKey = bpmNodeSet.getFormKey();//得到表单的formKey
						if(formKey.equals(""))continue;
						List<BpmFormDef> bpmFormDefList = new ArrayList<BpmFormDef>();
						bpmFormDefList = bpmFormDefService.getByFormKey(formKey);
						int x = bpmFormDefList.size();
						System.out.println("表单长度"+x);
						if(bpmFormDefList.size()==0) continue;
						BpmFormDef bpmFormDef = bpmFormDefList.get(x-1);
						//String subject = bpmFormDef.getSubject();
						operateInfo.getOperateNOdeInfo(m).bpmFormDef = new BpmFormDef();
						operateInfo.getOperateNOdeInfo(m).bpmFormDef = bpmFormDef;
						operateInfo.allBpmFormDefList.add(bpmFormDef);
						//调用函数
						OperateNodeInfo operateNode = operateInfo.getOperateNOdeInfo(m);
						operateNode = statisticsBpm(operateInfo.getOperateNOdeInfo(m));
					}
				 }         
			}
		
				

				operateInfo = getOperateNodeTimesInMark(operateInfo);//计算马尔科夫链中节点的执行次数
				}


				operateInfo = getOperateNodeTimesInMark(operateInfo);//计算马尔科夫链中节点的执行次数
				}
			

			return operateInfo;
	}
		
		/**
		 *查找事务图下的节点及原子操作的方法和表
		 * @param transactionInfo
		 * @return transactionInfo
		 * @author 白晓帆
		 */
		public TransactionInfo statisticsTransaction(TransactionInfo transactionInfo){
			//System.out.println("******"+transactionInfo.bpmtransaction.getSubject());
			String transactionDefXml =transactionInfo.bpmtransaction.getDefXml();//得到事物图DefXml
			String transactionActDefId = transactionInfo.bpmtransaction.getActDefId();//得到事物图的actDefId
			List<Map> transactionNodelist = findOperateNode(transactionDefXml);//解析事物图Xml的到事务图节点List
			
			transactionInfo.transactionNodeInfoList = new ArrayList<TransactionNodeInfo>();
			transactionInfo.allMethodList = new ArrayList<String>();
			System.out.println("******&&&&&&&&&&&&&&&&&&&&"+transactionNodelist.size());
			if(transactionNodelist.size()!=0){//节点List不为空
				for(int n = 0; n<transactionNodelist.size();n++){//循环节点List
					
					Map<String,String> nodeMap = transactionNodelist.get(n);
					Set<String>keys =nodeMap.keySet();
					 if(keys != null){
							
						 Iterator iterator = keys.iterator();  
				            while(iterator.hasNext()){ 
				            	Object key = iterator.next();
				            	String nodeId = key.toString();
				            	String nodeName = nodeMap.get(key); 
				          
					
				    TransactionNodeInfo transactionNodeInfo = new TransactionNodeInfo();
					//transactionInfo.transactionNodeInfoList= new ArrayList<TransactionNodeInfo>();
					//transactionInfo.transactionNodeInfoList.get(n).nodeId = nodeId;//将节点名字存到事物图节点对象下的nodeName中
				    transactionNodeInfo.setNodeId(nodeId);
				    transactionNodeInfo.setNodeName(nodeName);
				    String service = findScriptService(transactionDefXml,nodeId);
				    System.out.println("-------service---------"+service);
				    transactionNodeInfo.setService(service);
				    transactionInfo.transactionNodeInfoList.add(transactionNodeInfo);
				    //transactionInfo.setTNodeListNodeId(n,nodeId);
					//transactionInfo.transactionNodeInfoList.get(n).nodeName = nameName;
					//transactionInfo.transactionNodeInfoList.add(transactionInfo.transactionNodeInfoList.get(n));//将事务图节点对象存到事务图对象下的节点List中
					List<String> transactionList = new ArrayList<String>();
					String nodeId1 = transactionInfo.transactionNodeInfoList.get(n).nodeId;
					//System.out.println("nodeId1:"+nodeId+"transactionActDefId:"+transactionActDefId);
					//Map<String,String> transactionMap = bpmNodeWebServiceService.nameMethod(nodeId,transactionActDefId);
					transactionList = bpmNodeWebServiceService.nameMethod(nodeId1, transactionActDefId);//调用方法找到原子操作对应的表和方法，返回值为List
					//System.out.println("------------transactionList--------:"+transactionList);
					//transactionInfo.transactionNodeInfo.methodMap1 =new HashMap<String,List<String>>();
					transactionInfo.transactionNodeInfoList.get(n).methodList =new ArrayList<String>();
					//System.out.println("transactionList.size():"+transactionList.size());
					if(transactionList.size()==0){
						//transactionInfo.transactionNodeInfoList.get(n).methodList.add("0");
						System.out.println("没有方法");
					}
					else{
						transactionInfo.transactionNodeInfoList.get(n).setMethodList(transactionList);
						transactionInfo.allMethodList.addAll(transactionList);
						transactionInfo.transactionNodeInfoList.get(n).TableList = new ArrayList<String>();
						transactionInfo.transactionNodeInfoList.get(n).MethodList = new ArrayList<String>();
						List<String> list = transactionInfo.transactionNodeInfoList.get(n).getMethodList();
						List<String> tableList = new ArrayList<String>();
						List<String> methodList = new ArrayList<String>();
						for(int i = 0;i<list.size();i++){
							if(i%2==0){
								String table = list.get(i);
								tableList.add(table);
							}
							else{
								String method = list.get(i);
								methodList.add(method);
							}
							
						}
						transactionInfo.transactionNodeInfoList.get(n).TableList.addAll(tableList);
						transactionInfo.transactionNodeInfoList.get(n).MethodList.addAll(methodList);
						Map<String,Integer>  map = getCount(methodList);
						transactionInfo.transactionNodeInfoList.get(n).setMethodMap(map);
						//List<Long> tableid =markovchainService.findtableId1(tableList);//通过 example aaaa获得tableid
						List<String> dbfuncNamelist = new ArrayList<String>();
						List<DbFunc> dbFuncList = dbFuncService.getByTableName(tableList.get(0));//获得dbfunc对象
						for(DbFunc dbFunc1 : dbFuncList){
							String dbFuncName = dbFunc1.getFunc_name();
							dbfuncNamelist.add(dbFuncName);
						}
						transactionInfo.transactionNodeInfoList.get(n).setDbFuncNameList(dbfuncNamelist);//将dbfunc对象放入
						
						//System.out.println("dbFunc1:"+dbFunc1);
//						StringBuffer str  = str.append(transactionList); 
//	            		String va = valueString.substring(1, valueString.length()-1);
//	            		String [] strarray = cutString(va);
						
					}
					 List<String>  tList = findNodeAndServiceType(transactionDefXml,nodeId);
					 if(tList.size()!=0){
						 transactionInfo.transactionNodeInfoList.get(n).setType(tList.get(0));
						 transactionInfo.transactionNodeInfoList.get(n).setServiceType(tList.get(1));
					 }
					//System.out.println("******长度*********"+transactionInfo.transactionNodeInfo.methodMap.size());
				}
			}
				}
			}
			return transactionInfo;
		}
		
		/**
		 *查找表单以及生成方式和对应的表
		 * @param operateNodeInfo
		 * @return operateNodeInfo
		 * @author 白晓帆
		 */
		public OperateNodeInfo statisticsBpm(OperateNodeInfo operateNodeInfo){
			
			if(operateNodeInfo.bpmFormDef.getDesignType()==0){     //designType==0通过表生成
				Long tableId = operateNodeInfo.bpmFormDef.getTableId(); 
				BpmFormTable bpmFormTable = new BpmFormTable();
				bpmFormTable = bpmFormTableService.getTableById(tableId);//根据表的tableId找到表对象
				operateNodeInfo.bpmFormTable = bpmFormTable;
				List<BpmFormField> bpmFormFieldList = bpmFormFieldService.getByTableId(tableId);//根据表的table
				operateNodeInfo.bpmFormFieldList = bpmFormFieldList;
			}
			if(operateNodeInfo.bpmFormDef.getDesignType()==2){//designType==0通过数据包生成
				String formName = operateNodeInfo.bpmFormDef.getSubject();
				List<FormParcel> formParcelList = new ArrayList<FormParcel>();
				formParcelList = formParcelService.getByFormName(formName);
				operateNodeInfo.formParcelList = formParcelList;
				//if(formParcelList.size()==0) 
				operateNodeInfo.tableParcelList = new ArrayList<TableParcel>();
				for(int y=0;y<operateNodeInfo.formParcelList.size();y++){
					Long parcelId = operateNodeInfo.formParcelList.get(y).getParcelID();
					//node_info.operateInfo.operateNodeInfoList.get(m).tableParcelList = new ArrayList<TableParcel>();
					TableParcel tableParcel = new TableParcel();
					tableParcel = tableParcelService.getById(parcelId);
					//System.out.println("#######parcelId:"+parcelId);
					operateNodeInfo.tableParcelList.add(tableParcel);
					JSONArray ja = new JSONArray(operateNodeInfo.tableParcelList.get(y).getParcel_value());
					List<Map> jojList = new ArrayList<Map>();
					for(int c=0;c<ja.length();c++)
	                 {
	            	   org.activiti.engine.impl.util.json.JSONObject joj = ja.getJSONObject(c);
	            	   
	            	   String ss = joj.getString("field");
	            	    String comment = joj.getString("comment");
	            	    
	            	    Map map = new HashMap();
	            	    map.put(ss, comment);
	            	    jojList.add(map);
	            	   operateNodeInfo.ListMap.add(jojList);
	            	    //System.out.println("														field:"+ss+"  comment:"+comment);
	                 }
					operateNodeInfo.ListMap.add(jojList);
					
					
					//System.out.println("tableParcelList的长度:"+node_info.operateInfo.operateNodeInfoList.get(m).tableParcelList.size());
				}
			}
			return operateNodeInfo;
		}
		
		
		
		
		

		public List<WSystemInformation> sysListBasicStatistics(List<WSystemInformation> sys_information ){//初级统计信息
			System.out.println("基本统计功能");
			for(WSystemInformation sys_informa:sys_information)//遍历子系统链表  一维
			{	
				if(sys_informa.sys_def_info_list.size()==0)
					System.out.println("基本统计功能->子系统"+sys_informa.getSystem_name()+"->流程链表为空");//如果该子系统流程链表属性为空
				else
				{
					sys_informa=sysBasicStatistics(sys_informa);					
				}
			}		
			return sys_information;
		}
		public WSystemInformation sysBasicStatistics(WSystemInformation sys_informa)
		{
			for(WDefInformation def_informa:sys_informa.sys_def_info_list)//遍历流程链表 二维
					{	
						if(def_informa.def_node_info_list.size()==0)
							System.out.println("基本统计功能->子系统 "+sys_informa.getSystem_name()+"->流程"+def_informa.bpmdef.getActDefId()+"->节点链表为空");//如果该流程的节点链表为空
						else
						{
							def_informa=defBasicStatistics(def_informa);
							sys_informa.sysPrimaryStatistics(def_informa);
						}
						
					}
			/*System.out.println("                    子系统exenum为："+sys_informa.getExeNum());
			System.out.println("                    子系统denpandexenum为："+sys_informa.getDependExeNum());
			System.out.println("                    子系统opexenum为："+sys_informa.getOperateExeNum());
			System.out.println("                    子系统opdenpandexenum为："+sys_informa.getOperateDependExeNum());*/
			return sys_informa;
		}
		public WDefInformation defBasicStatistics(WDefInformation def_informa)
		{
			for(WNodeInformation node_informa:def_informa.def_node_info_list)//遍历节点链表 三维
			{
				if(node_informa.operateInfo!=null)
				{
					if(node_informa.operateInfo.operateNodeInfoList.size()!=0)
						{
							node_informa.operateInfo=operateBasicStatistics(node_informa.operateInfo);
							node_informa.nodePrimaryStatistics(node_informa.operateInfo);
						}
				}
			/*	System.out.println("          流程图节点exenum为："+node_informa.getExeNum());
				System.out.println("          流程图节点denpandexenum为："+node_informa.getDependExeNum());
				System.out.println("          流程图节点opexenum为："+node_informa.getOperateExeNum());
				System.out.println("          流程图节点opdenpandexenum为："+node_informa.getOperateDependExeNum());*/
				def_informa.defPrimaryStatistics(node_informa);
			}
			/*System.out.println("               流程图exenum为："+def_informa.getExeNum());
			System.out.println("               流程图denpandexenum为："+def_informa.getDependExeNum());
			System.out.println("               流程图opexenum为："+def_informa.getOperateExeNum());
			System.out.println("               流程图opdenpandexenum为："+def_informa.getOperateDependExeNum());*/
			return def_informa;
		}
		public WOperateInfo operateBasicStatistics(WOperateInfo operateInfo)
		{
			for(OperateNodeInfo operateNode:operateInfo.operateNodeInfoList)//遍历节点链表 三维
			{
				operateInfo.operatePrimaryStatistics(operateNode);
				/*System.out.println("操作图节点exenum为："+operateNode.getExeNum());
				System.out.println("操作图节点denpandexenum为："+operateNode.getDependExeNum());*/
			}
			/*System.out.println("     操作图exenum为："+operateInfo.getExeNum());
			System.out.println("     操作图denpandexenum为："+operateInfo.getDependExeNum());*/
			return operateInfo;
		}
		public List<WSystemInformation> get_jisuan_tongji(List<WSystemInformation> sys_information ){//计算算出更高级的统计
			System.out.println("进入高级统计功能");
			for(WSystemInformation sys_informa:sys_information)//遍历子系统链表  一维
			{
						System.out.println("进入高级统计功能->子系统名字为:"+sys_informa.getSystem_name());
						sys_informa=sys_informa.FinalRankStatistics();		
			}
			return sys_information;
		}
		public void ceshilianbiao(List<WSystemInformation> sys_information){
			for(WSystemInformation a1:sys_information)//遍历子系统链表  一维
			{System.out.println(a1.getSystem_name()+a1.getSystem_id());
				if(a1.sys_def_info_list.size()==0)continue;
				defceshilianbiao(a1.sys_def_info_list);
				System.out.println();
			}
		}
		
		public void defceshilianbiao(List<WDefInformation> sys_def_info_list) {
			// TODO Auto-generated method stub
			for(WDefInformation a2:sys_def_info_list)//遍历流程链表 二维
			{System.out.println("           "+a2.bpmdef.getActDefId());
				if(a2.def_node_info_list.size()==0)continue;
				for(WNodeInformation a3:a2.def_node_info_list){//遍历节点链表 三维
				
					//System.out.println("                         "+a3.bpmNodeSet.getNodeId());
					System.out.println("                         流程节点："+a3.bpmNodeSet.getNodeId()+"依赖次数："+a3.getDependExeNum()+"次数："+a3.getExeNum());
					if(a3.activityDetail==null) continue;
					System.out.println("                                           "+a3.activityDetail.getWork_name());	
					if(a3.operateInfo==null) continue;
					
						//System.out.println("****************"+a3.operateInfo);
						outOperate(a3.operateInfo);
					
				}
					
				List <String> DefNodeNameList =a2.getDefNodeNameList(a2.def_node_info_list);
				
				for(int m=0;m<a2.defNodeList.size();m++){

					a2.defNodeList.removeAll(DefNodeNameList);
					if(a2.defNodeList.size()!=0){
						System.out.println("	wo节点nodeName:"+a2.defNodeList.get(m));	
					}
				}
			}
		}	
		public void outOperate(WOperateInfo operateInfo){
			//System.out.println("****************"+operateInfo);
			
			if(operateInfo.bpmDefinition.getSubject()!=null){
				System.out.println("			       子事务图Id："+operateInfo.bpmDefinition.getDefId()+" 操作图名："+operateInfo.bpmDefinition.getSubject());
				if(operateInfo.oNodeListSize()==0){
					System.out.println("该操作图没有节点");
				}
				else{
					for(int j=0;j<operateInfo.operateNodeInfoList.size();j++){
						
						String name = operateInfo.getOperateNOdeInfo(j).getNodeId();
						String name1=operateInfo.getOperateNOdeInfo(j).getNodeName();
						//System.out.println("				长度："+operateInfo.operateNodeInfoList.size());
						//System.out.println("operateNodeInfoList长度 ："+operateInfo.operateNodeInfoList.size());
						System.out.println("						事物图节点："+name+"汉语名字"+name1+"  依赖次数："+operateInfo.getOperateNOdeInfo(j).getDependExeNum()+"  次数："+operateInfo.getOperateNOdeInfo(j).getExeNum());
						if(name.contains("startSubFlowTask")&&operateInfo.getOperateNOdeInfo(j).transactionInfo!=null){
							outTransaction(operateInfo.getOperateNOdeInfo(j).transactionInfo);
						}
						if(name.contains("userTask")||name.contains("UserTask")){
							outBpm(operateInfo.getOperateNOdeInfo(j));
						}
						else{
							System.out.println("");	
						}
					}
				}
			}
		}
		public void outTransaction(TransactionInfo transactionInfo){
			System.out.println("									 子流程图Id："+transactionInfo.bpmtransaction.getDefId()+" 子流程图名："+transactionInfo.bpmtransaction.getSubject());
			if(transactionInfo.transactionNodeInfoList.size()==0){
				System.out.println("该事物图没有节点");
			}
			else
			{
				Map<String,Integer> methodMap1 = new HashMap<String,Integer>();
				methodMap1 = getCount(transactionInfo.allMethodList);
				System.out.println("*****方法*****："+methodMap1);
				for(int n = 0;n<transactionInfo.getTNodeListsize();n++){
					System.out.println("											子流程节点："+transactionInfo.transactionNodeInfoList.get(n).getNodeName());
//					Set<Object>keys =transactionInfo.transactionNodeInfoList.get(n).methodMap.keySet();
//					 if(keys != null){
//						 Iterator iterator = keys.iterator();  
//				            while(iterator.hasNext()){ 
//				            	Object key = iterator.next();
//				            	//String MKey = key.toString();
//				            	List<String> value = transactionInfo.transactionNodeInfoList.get(n).methodMap.get(key); 
				            	if(transactionInfo.transactionNodeInfoList.get(n).methodList.size()==0){
				            		System.out.println("												没有方法");
				            		
				            	}
				            	{
				            		System.out.println("----------------------------"+transactionInfo.transactionNodeInfoList.get(n).methodList);
				            		//System.out.println("----------------------------"+transactionInfo.transactionNodeInfoList.get(n).methodList);
				            		
//				            		String va = valueString.substring(1, valueString.length()-1);
//				            		String [] strarray = cutString(va);
//				            		for(int a=0;a<strarray.length;a+=2)
//					            	System.out.println("												方法："+strarray[a+1]+" 操作的表："+strarray[a]);
				            	}
				           // }
					 //}
				}
			}
		}
		
		public void outBpm(OperateNodeInfo operateNodeInfo){
			if(operateNodeInfo.bpmFormDef.getSubject()=="")   return;
			System.out.println("									页面："+operateNodeInfo.bpmFormDef.getSubject()+"	依赖访问次数："+operateNodeInfo.getDependExeNum()+"	访问次数："+operateNodeInfo.getExeNum());
			if(operateNodeInfo.bpmFormDef.getDesignType()==0){//输出表
				System.out.println("										由表："+operateNodeInfo.bpmFormTable.getTableName()+"生成");
				for(int z=0;z<operateNodeInfo.bpmFormFieldList.size();z++){
					String fieldName = operateNodeInfo.bpmFormFieldList.get(z).getFieldName();
					String fieldType = operateNodeInfo.bpmFormFieldList.get(z).getFieldType();
					System.out.println("											字段名："+fieldName+" 字段类型："+fieldType);
				}
			}
			else if(operateNodeInfo.bpmFormDef.getDesignType()==2){//输出数据包
				for(int k=0;k<operateNodeInfo.formParcelList.size();k++){
					System.out.println("											由数据包："+operateNodeInfo.formParcelList.get(k).getParcel_name()+"生成");
					
					if(operateNodeInfo.formParcelList.size()==0) continue;
					else{
						//System.out.println("长度:"+wDefInformation.def_node_info_list.get(i).operateInfo.operateNodeInfoList.get(j).tableParcelList.size());
						System.out.println("													由表："+operateNodeInfo.tableParcelList.get(k).getTable_name()+"生成");
						
						System.out.println("++++++++++++++++++++ "+operateNodeInfo.ListMap.get(k));
						JSONArray ja = new JSONArray(operateNodeInfo.tableParcelList.get(k).getParcel_value());
						for(int c=0;c<ja.length();c++)
		                 {
		            	   org.activiti.engine.impl.util.json.JSONObject joj = ja.getJSONObject(c);
		            	   String ss = joj.getString("field");
		            	    String comment = joj.getString("comment");
		            	   
		            	    System.out.println("														field:"+ss+"  comment:"+comment);
		                 }
					}
				}
			}
			else if(operateNodeInfo.bpmFormDef.getDesignType()==1){
				System.out.println("										表单由编辑器生成");
			}
			else{
				System.out.println("										表单由操作图生成");
			}
			
		}
		public static int ergodicId;
		public List<Sysdefnodeergodic> writeInBySysList(List<WSystemInformation> sys_information){//直接输出于List   子系统链表
			//sysdefnodeergodicService.delAll();
			System.out.println("进入子系统链表写入函数");
			List<Sysdefnodeergodic>sysdefnodeergodicList=new ArrayList<Sysdefnodeergodic>();
			ergodicId=0;
			int systemNum=0;
			for(WSystemInformation a1:sys_information)//遍历子系统链表  一维
			{
				System.out.println("遍历子系统链表，进入for 当前子系统名字"+a1.getSystem_name());
				systemNum++;
			
					Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(ergodicId, 
						systemNum,
						a1.getSystem_name(), 
						a1.getSystem_id(),
						a1.getNotSetNum(),
						"",
						"",
						"",
						"",
						null,
						0,
						0,0,0,0,
						"",
						"",
						"",
						"",	
						"",
						"",
						0,0,0,0,
						"",
						"",
						null,
						0,0,
						"",
						"",
						0,0,
						"",
						"",
						null,
						"",
						"",
						"",
						"",
						"",
						"");
					ergodicId++;
					sysdefnodeergodicList.add(sysdefnodeergodic);				
				if(a1.sys_def_info_list.size()!=0)
					{sysdefnodeergodicList=writeInByDefList(
							
							systemNum,
							a1,
							sysdefnodeergodicList);}
				else{
					String n=sysdefnodeergodicList.get(ergodicId-1).getSysName();
					sysdefnodeergodicList.get(ergodicId-1).setSysName(n+"(无流程)");
				}
			}
			
			//sysdefnodeergodicController.list1(sysdefnodeergodicList);
			return  sysdefnodeergodicList;
		}
		public List<Sysdefnodeergodic> writeInByDefList(
				
				int systemNum,
				
				WSystemInformation a1, 
				List<Sysdefnodeergodic> sysdefnodeergodicList) {
			// TODO Auto-generated method stub
			
			System.out.println("进入流程链表写入函数");
			
			
			for(WDefInformation a2:a1.sys_def_info_list)
			{
				System.out.println("遍历l流程链表，进入for 当前流程名字"+a2.bpmdef.getSubject());
				Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
					ergodicId,
					systemNum,
					"", 
					a1.getSystem_id(), 
					a1.getNotSetNum(),
					a2.bpmdef.getSubject()+"("+a2.def_node_info_list.size()+")", 
					Long.toString(a2.bpmdef.getDefId()), 
					a2.bpmdef.getActDefId(),
					a2.typeName,
					a2.bpmdef.getStatus(),
					a2.getNotSetNum(),
					a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),
					"",
					"",
					"",
					"",
					"",
					"",
					0,0,0,0,
					"",
					"",null,
					0,0,
					"",
					"",
					0,0,
					"",
					"",null,
					"",
					"",
					"",
					"",
					"",
					"");
			ergodicId++;
			sysdefnodeergodicList.add(sysdefnodeergodic);
			if(a2.def_node_info_list.size()!=0)
				{
				sysdefnodeergodicList=writeInByNodeList(
						
						systemNum,						
						a1,
						a2,
						sysdefnodeergodicList);
				}
			}
			return sysdefnodeergodicList;
			
		}
		private List<Sysdefnodeergodic> writeInByNodeList(
				
				int systemNum,
				
				WSystemInformation a1, 
				
				WDefInformation a2,
				List<Sysdefnodeergodic> sysdefnodeergodicList) {
			// TODO Auto-generated method stub
			
			System.out.println("进入节点链表写入函数");		
			for(WNodeInformation a3:a2.def_node_info_list)//遍历流程的节点链表 三维
			{
				System.out.println("遍历节点链表，进入for 当前节点名字"+a3.bpmNodeSet.getNodeName());
				if(a3.operateInfo==null)
				{
					if(a3.activityDetail==null)//节点未设定
					{
					Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
							ergodicId,
							systemNum,
							"", 
							a1.getSystem_id(), 
							a1.getNotSetNum(),
							"", 
							Long.toString(a2.bpmdef.getDefId()), 
							a2.bpmdef.getActDefId(),
							a2.bpmdef.getTypeName(),
							a2.bpmdef.getStatus(),
							a2.getNotSetNum(),
							a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),
							Long.toString(a3.bpmNodeSet.getSetId()),
							a3.bpmNodeSet.getNodeName(),
							a3.bpmNodeSet.getNodeId(),
							"未设置",
							"设置",
							"",
							a3.getExeNum(),a3.getDependExeNum(),a3.getOperateExeNum(),a3.getOperateDependExeNum(),
							"",
							"",null,
							0,0,
							"",
							"",
							0,0,
							"",
							"",null,
							"",
							"",
							"",
							"",
							"",
							"");
						sysdefnodeergodicList.add(sysdefnodeergodic);							
						ergodicId++;
					}
					else
					{
						Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
							ergodicId,
							systemNum,
							"", 
							a1.getSystem_id(), 
							a1.getNotSetNum(),
							"", 
							Long.toString(a2.bpmdef.getDefId()), 
							a2.bpmdef.getActDefId(),
							a2.bpmdef.getTypeName(),
							a2.bpmdef.getStatus(),
							a2.getNotSetNum(),
							a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),
							Long.toString(a3.bpmNodeSet.getSetId()),
							a3.bpmNodeSet.getNodeName(),
							a3.bpmNodeSet.getNodeId(),
							"",
							"设置",
							a3.activityDetail.getWork_name(),
							a3.getExeNum(),a3.getDependExeNum(),a3.getOperateExeNum(),a3.getOperateDependExeNum(),
							"",
							"",null,0,0,
							"",
							"",0,0,
							"",
							"",null,
							"",
							"",
							"",
							"",
							"",
							"");
						sysdefnodeergodicList.add(sysdefnodeergodic);							
						ergodicId++;										
					}
				}
				else{	
					if(a3.activityDetail==null)//节点未设定
						{
							Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
								ergodicId,
								systemNum,
								"", 
								a1.getSystem_id(), 
								a1.getNotSetNum(),
								"", 
								Long.toString(a2.bpmdef.getDefId()), 
								a2.bpmdef.getActDefId(),
								a2.bpmdef.getTypeName(),
								a2.bpmdef.getStatus(),
								a2.getNotSetNum(),
								a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),
								Long.toString(a3.bpmNodeSet.getSetId()),
								a3.bpmNodeSet.getNodeName(),
								a3.bpmNodeSet.getNodeId(),
								"未设置",
								"设置",
								"",
								a3.getExeNum(),a3.getDependExeNum(),a3.getOperateExeNum(),a3.getOperateDependExeNum(),
								Long.toString(a3.operateInfo.bpmDefinition.getDefId()),
								a3.operateInfo.bpmDefinition.getSubject()+"("+a3.operateInfo.operateNodeInfoList.size()+")",
								a3.operateInfo.bpmDefinition.getStatus(),
								a3.operateInfo.getExeNum(),a3.operateInfo.getDependExeNum(),
								"",
								"",								
								0,0,
								"",
								"",null,
								"",
								"",
								"",
								"",
								"",
								"");
							sysdefnodeergodicList.add(sysdefnodeergodic);							
							ergodicId++;
						}	
					else//节点已经设定了
						{
							Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
								ergodicId,
								systemNum,
								"", 
								a1.getSystem_id(), 
								a1.getNotSetNum(),
								"", 
								Long.toString(a2.bpmdef.getDefId()), 
								a2.bpmdef.getActDefId(),
								a2.bpmdef.getTypeName(),
								a2.bpmdef.getStatus(),
								a2.getNotSetNum(),
								a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),
								Long.toString(a3.bpmNodeSet.getSetId()),
								a3.bpmNodeSet.getNodeName(),
								a3.bpmNodeSet.getNodeId(),
								"",
								"设置",
								a3.activityDetail.getWork_name(),
								a3.getExeNum(),a3.getDependExeNum(),a3.getOperateExeNum(),a3.getOperateDependExeNum(),
								Long.toString(a3.operateInfo.bpmDefinition.getDefId()),
								a3.operateInfo.bpmDefinition.getSubject()+"("+a3.operateInfo.operateNodeInfoList.size()+")",
								a3.operateInfo.bpmDefinition.getStatus(),
								a3.operateInfo.getExeNum(),a3.operateInfo.getDependExeNum(),
								"",
								"",0,0,
								"",
								"",null,								
								"",
								"",
								"",
								"",
								"",
								"");
							sysdefnodeergodicList.add(sysdefnodeergodic);							
							ergodicId++;								
						}
						if(a3.operateInfo.operateNodeInfoList.size()!=0)
						{
							sysdefnodeergodicList=writeInByOperateNodeList(
								
									systemNum,									
									a1,									
									a2,																	
									a3,
									sysdefnodeergodicList);
						}											
				}											
			}
			return sysdefnodeergodicList;
		}
		private List<Sysdefnodeergodic> writeInByOperateNodeList(
				
				int systemNum,
				WSystemInformation a1, 				
				WDefInformation a2,
				WNodeInformation a3,
				List<Sysdefnodeergodic> sysdefnodeergodicList
				)
			{System.out.println("进入操作图节点list写入函数");
				for(OperateNodeInfo a4:a3.operateInfo.operateNodeInfoList)
				{			
				 if(a4.bpmFormDef!=null){//页面		
					 switch(a4.bpmFormDef.getDesignType())
					 {case 0:{Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
								ergodicId,
								systemNum,
								"", 
								a1.getSystem_id(), 
								a1.getNotSetNum(),
								"", 
								Long.toString(a2.bpmdef.getDefId()), 
								a2.bpmdef.getActDefId(),
								a2.bpmdef.getTypeName(),
								a2.bpmdef.getStatus(),
								a2.getNotSetNum(),
								a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),
								Long.toString(a3.bpmNodeSet.getSetId()),
								"",
								a3.bpmNodeSet.getNodeId(),
								"",
								"",
								"",
								a3.getExeNum(),a3.getDependExeNum(),a3.getOperateExeNum(),a3.getOperateDependExeNum(),
								Long.toString(a3.operateInfo.bpmDefinition.getDefId()),
								"",
								null,
								a3.operateInfo.getExeNum(),a3.operateInfo.getDependExeNum(),
								a4.getNodeId(),
								a4.getNodeName(),
								a4.getExeNum(),a4.getDependExeNum(),
								Long.toString(a4.bpmFormDef.getFormDefId()),
								"(表单页面)"+a4.bpmFormDef.getSubject(),
								null,
								Long.toString(a4.bpmFormTable.getTableId()),
								"（表名）"+a4.bpmFormTable.getTableName()+"("+a4.bpmFormFieldList.size()+")",
								"",
								"",
								"",
								"");
						sysdefnodeergodicList.add(sysdefnodeergodic);
						ergodicId++;
						if(a4.bpmFormFieldList.size()!=0)
						{sysdefnodeergodicList=writeInByFormParcelList//进入数据包链写入函数   进入倒数第二层
							(
									
									systemNum,
									a1,a2,a3,a4,
									sysdefnodeergodicList
							);}
						break;}
					 case 2:{Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
									ergodicId,
									systemNum,
									"", 
									a1.getSystem_id(), 
									a1.getNotSetNum(),
									"", 
									Long.toString(a2.bpmdef.getDefId()), 
									a2.bpmdef.getActDefId(),
									a2.bpmdef.getTypeName(),
									a2.bpmdef.getStatus(),
									a2.getNotSetNum(),
									a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),
									Long.toString(a3.bpmNodeSet.getSetId()),
									"",
									a3.bpmNodeSet.getNodeId(),
									"",
									"",
									"",
									a3.getExeNum(),a3.getDependExeNum(),a3.getOperateExeNum(),a3.getOperateDependExeNum(),
									Long.toString(a3.operateInfo.bpmDefinition.getDefId()),
									"",
									a3.operateInfo.bpmDefinition.getStatus(),
									a3.operateInfo.getExeNum(),a3.operateInfo.getDependExeNum(),
									a4.getNodeId(),
									a4.getNodeName(),
									a4.getExeNum(),a4.getDependExeNum(),
									Long.toString(a4.bpmFormDef.getFormDefId()),
									"(数据包页面)"+a4.bpmFormDef.getSubject()+"("+a4.formParcelList.size()+")",
									null, 
									"",
									"",
									"",
									"",
									"",
									"");
							sysdefnodeergodicList.add(sysdefnodeergodic);
							ergodicId++;
							if(a4.formParcelList.size()!=0)
							{sysdefnodeergodicList=writeInByFormParcelList//进入数据包链写入函数   进入倒数第二层
								(
									
										systemNum,
										a1,a2,a3,a4,
										sysdefnodeergodicList
								);}
							break;}
						default:{Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
							ergodicId,
							systemNum,
							"", 
							a1.getSystem_id(), 
							a1.getNotSetNum(),
							"", 
							Long.toString(a2.bpmdef.getDefId()), 
							a2.bpmdef.getActDefId(),
							a2.bpmdef.getTypeName(),
							a2.bpmdef.getStatus(),
							a2.getNotSetNum(),
							a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),
							Long.toString(a3.bpmNodeSet.getSetId()),
							"",
							a3.bpmNodeSet.getNodeId(),
							"",
							"",
							"",
							a3.getExeNum(),a3.getDependExeNum(),a3.getOperateExeNum(),a3.getOperateDependExeNum(),
							Long.toString(a3.operateInfo.bpmDefinition.getDefId()),
							"",
							a3.operateInfo.bpmDefinition.getStatus(),
							a3.operateInfo.getExeNum(),a3.operateInfo.getDependExeNum(),
							a4.getNodeId(),
							a4.getNodeName(),
							a4.getExeNum(),a4.getDependExeNum(),
							"",
							"",null,
							"",
							"",
							"",
							"",
							"",
							"");
						sysdefnodeergodicList.add(sysdefnodeergodic);		
						ergodicId++;}										 
					 }
	
					}else if(a4.transactionInfo!=null)
					{
						Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
								ergodicId,
								systemNum,
								"", 
								a1.getSystem_id(), 
								a1.getNotSetNum(),
								"", 
								Long.toString(a2.bpmdef.getDefId()), 
								a2.bpmdef.getActDefId(),
								a2.bpmdef.getTypeName(),
								a2.bpmdef.getStatus(),
								a2.getNotSetNum(),
								a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),
								Long.toString(a3.bpmNodeSet.getSetId()),
								"",
								a3.bpmNodeSet.getNodeId(),
								"",
								"",
								"",
								a3.getExeNum(),a3.getDependExeNum(),a3.getOperateExeNum(),a3.getOperateDependExeNum(),
								Long.toString(a3.operateInfo.bpmDefinition.getDefId()),
								"",
								a3.operateInfo.bpmDefinition.getStatus(),
								a3.operateInfo.getExeNum(),a3.operateInfo.getDependExeNum(),
								a4.getNodeId(),
								a4.getNodeName(),
								a4.getExeNum(),a4.getDependExeNum(),
								Long.toString(a4.transactionInfo.bpmtransaction.getDefId()),
								"(子事务图)"+a4.transactionInfo.bpmtransaction.getSubject()+"("+a4.transactionInfo.transactionNodeInfoList.size()+")",
								a4.transactionInfo.bpmtransaction.getStatus(),
								"",
								"",
								"",
								"",
								"",
								"");
						sysdefnodeergodicList.add(sysdefnodeergodic);
						ergodicId++;												
						if(a4.transactionInfo.transactionNodeInfoList.size()!=0)
						{
							sysdefnodeergodicList=writeInByTransactionNodeList//进入事务图节点链 写入函数
							(								
									systemNum,
									a1,a2,a3,a4,
									sysdefnodeergodicList
							);
						}						
					}																		
					else
					{
						Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
								ergodicId,
								systemNum,
								"", 
								a1.getSystem_id(), 
								a1.getNotSetNum(),
								"", 
								Long.toString(a2.bpmdef.getDefId()), 
								a2.bpmdef.getActDefId(),
								a2.bpmdef.getTypeName(),
								a2.bpmdef.getStatus(),
								a2.getNotSetNum(),
								a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),
								Long.toString(a3.bpmNodeSet.getSetId()),
								"",
								a3.bpmNodeSet.getNodeId(),
								
								"",
								"",
								"",
								a3.getExeNum(),a3.getDependExeNum(),a3.getOperateExeNum(),a3.getOperateDependExeNum(),
								Long.toString(a3.operateInfo.bpmDefinition.getDefId()),
								"",
								a3.operateInfo.bpmDefinition.getStatus(),
								a3.operateInfo.getExeNum(),a3.operateInfo.getDependExeNum(),
								a4.getNodeId(),
								a4.getNodeName(),
								a4.getExeNum(),a4.getDependExeNum(),
								"",
								"",
								null,
								"",
								"",
								"",
								"",
								"",
								"");
						sysdefnodeergodicList.add(sysdefnodeergodic);
						ergodicId++;						
				}				
			}				
			return sysdefnodeergodicList;
		}			
		private List<Sysdefnodeergodic> writeInByTransactionNodeList(//事务图节点链  倒数第三层
				int systemNum,
				WSystemInformation a1, 				
				WDefInformation a2,
				WNodeInformation a3,
				OperateNodeInfo a4 ,
				List<Sysdefnodeergodic> sysdefnodeergodicList) {
			// TODO Auto-generated method stub
			for(TransactionNodeInfo a5:a4.transactionInfo.transactionNodeInfoList)
			{
				Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
						ergodicId,
						systemNum,
						"", 
						a1.getSystem_id(), 
						a1.getNotSetNum(),
						"", 
						Long.toString(a2.bpmdef.getDefId()), 
						a2.bpmdef.getActDefId(),
						a2.bpmdef.getTypeName(),
						a2.bpmdef.getStatus(),
						a2.getNotSetNum(),
						a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),
						Long.toString(a3.bpmNodeSet.getSetId()),
						"",
						a3.bpmNodeSet.getNodeId(),
						"",
						"",
						"",
						a3.getExeNum(),a3.getDependExeNum(),a3.getOperateExeNum(),a3.getOperateDependExeNum(),
						Long.toString(a3.operateInfo.bpmDefinition.getDefId()),
						"",
						a3.operateInfo.bpmDefinition.getStatus(),
						a3.operateInfo.getExeNum(),a3.operateInfo.getDependExeNum(),
						a4.getNodeId(),
						"",
						a4.getExeNum(),a4.getDependExeNum(),
						Long.toString(a4.transactionInfo.bpmtransaction.getDefId()),
						"",
						a4.transactionInfo.bpmtransaction.getStatus(),						
						a5.nodeId,
						"(事务图节点)"+a5.getNodeName()+"("+a5.methodList.size()+")",
						"",
						"",
						"",
						"");
				sysdefnodeergodicList.add(sysdefnodeergodic);
				ergodicId++;
				if(a5.methodList.size()!=0)
				{
					sysdefnodeergodicList=writeInByTransactionNodeMethodList//进入事务图节点链 写入函数
					(							
							systemNum,
							a1,a2,a3,a4,a5,
							sysdefnodeergodicList
					);
				}
			}
			return sysdefnodeergodicList;
		}
		private List<Sysdefnodeergodic> writeInByTransactionNodeMethodList(
				int systemNum, WSystemInformation a1,
				WDefInformation a2, WNodeInformation a3, OperateNodeInfo a4,
				TransactionNodeInfo a5,
				List<Sysdefnodeergodic> sysdefnodeergodicList) {
			// TODO Auto-generated method stub
			for(int i=0;i<a5.methodList.size();i+=2)
			{
				Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
						ergodicId,
						systemNum,
						"", 
						a1.getSystem_id(), 
						a1.getNotSetNum(),
						"", 
						Long.toString(a2.bpmdef.getDefId()), 
						a2.bpmdef.getActDefId(),
						a2.bpmdef.getTypeName(),
						a2.bpmdef.getStatus(),
						a2.getNotSetNum(),
						a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),
						Long.toString(a3.bpmNodeSet.getSetId()),
						"",
						a3.bpmNodeSet.getNodeId(),
						"",
						"",
						"",
						a3.getExeNum(),a3.getDependExeNum(),a3.getOperateExeNum(),a3.getOperateDependExeNum(),
						Long.toString(a3.operateInfo.bpmDefinition.getDefId()),
						"",
						a3.operateInfo.bpmDefinition.getStatus(),
						a3.operateInfo.getExeNum(),a3.operateInfo.getDependExeNum(),
						a4.getNodeId(),
						"",
						a4.getExeNum(),a4.getDependExeNum(),
						Long.toString(a4.transactionInfo.bpmtransaction.getDefId()),
						"",
						a4.transactionInfo.bpmtransaction.getStatus(),
						a5.nodeId,
						"",
						"",
						"表名："+a5.methodList.get(i)+"  方法名："+a5.methodList.get(i+1),
						"",
						"");
				sysdefnodeergodicList.add(sysdefnodeergodic);
				ergodicId++;
			}
			return sysdefnodeergodicList;
		}

		private List<Sysdefnodeergodic> writeInByFormParcelList(//数据包链//倒数第二层
				 int systemNum,
				WSystemInformation a1, 				
				WDefInformation a2,
				WNodeInformation a3,
				OperateNodeInfo a4 ,
				List<Sysdefnodeergodic> sysdefnodeergodicList) {
			// TODO Auto-generated method stub
 			if(a4.tableParcelList!=null)//数据包表不为空
			for(int i=0;i<a4.tableParcelList.size();i++)
			{
				Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
						ergodicId,
						systemNum,
						"", 
						a1.getSystem_id(), 
						a1.getNotSetNum(),
						"", 
						Long.toString(a2.bpmdef.getDefId()), 
						a2.bpmdef.getActDefId(),
						a2.bpmdef.getTypeName(),
						a2.bpmdef.getStatus(),
						a2.getNotSetNum(),
						a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),
						Long.toString(a3.bpmNodeSet.getSetId()),
						"",
						a3.bpmNodeSet.getNodeId(),
						"",
						"",
						"",
						a3.getExeNum(),a3.getDependExeNum(),a3.getOperateExeNum(),a3.getOperateDependExeNum(),
						Long.toString(a3.operateInfo.bpmDefinition.getDefId()),
						"",
						a3.operateInfo.bpmDefinition.getStatus(),
						a3.operateInfo.getExeNum(),a3.operateInfo.getDependExeNum(),
						a4.getNodeId(),
						"",
						a4.getExeNum(),a4.getDependExeNum(),
						Long.toString(a4.bpmFormDef.getFormDefId()),
						"",
						null,
						Long.toString(a4.formParcelList.get(i).getParcelID()),
						"(数据包)"+a4.formParcelList.get(i).getParcel_name(),
						Long.toString(a4.tableParcelList.get(i).getId()),
						"(表名)"+a4.tableParcelList.get(i).getTable_name()+"("+a4.ListMap.get(i).size()+")",
						"",
						"");
				sysdefnodeergodicList.add(sysdefnodeergodic);
				ergodicId++;
				if(a4.ListMap.get(i).size()!=0)//如果表的字段不为空
				{
					sysdefnodeergodicList=writeInByTableParcelNodeList//进入字段写入函数   倒数第一层
					(
							
							systemNum,
							a1,a2,a3,a4	,i,				
							sysdefnodeergodicList
					);
				}			
			}
		
		if(a4.bpmFormFieldList!=null)//如果表名内部字段不为空
		{
			for(int i=0;i<a4.bpmFormFieldList.size();i++)
			{
				Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
						ergodicId,
						systemNum,
						"", 
						a1.getSystem_id(), 
						a1.getNotSetNum(),
						"", 
						Long.toString(a2.bpmdef.getDefId()), 
						a2.bpmdef.getActDefId(),
						a2.bpmdef.getTypeName(),
						a2.bpmdef.getStatus(),
						a2.getNotSetNum(),
						a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),
						Long.toString(a3.bpmNodeSet.getSetId()),
						"",
						a3.bpmNodeSet.getNodeId(),
						"",
						"",
						"",
						a3.getExeNum(),a3.getDependExeNum(),a3.getOperateExeNum(),a3.getOperateDependExeNum(),
						Long.toString(a3.operateInfo.bpmDefinition.getDefId()),
						"",
						a3.operateInfo.bpmDefinition.getStatus(),
						a3.operateInfo.getExeNum(),a3.operateInfo.getDependExeNum(),
						a4.getNodeId(),
						"",
						a4.getExeNum(),a4.getDependExeNum(),
						Long.toString(a4.bpmFormDef.getFormDefId()),
						"",
						null,
						Long.toString(a4.bpmFormTable.getTableId()),
						"",
						"",
						"(字段)"+a4.bpmFormFieldList.get(i).getFieldName()+"("+a4.bpmFormFieldList.get(i).getFieldType()+")",						
						"",
						"");
				sysdefnodeergodicList.add(sysdefnodeergodic);
				ergodicId++;
			}
		}
			return sysdefnodeergodicList;
		}

		private List<Sysdefnodeergodic> writeInByTableParcelNodeList(//倒数第一层
				int systemNum,
				WSystemInformation a1, 				
				WDefInformation a2,
				WNodeInformation a3,
				OperateNodeInfo a4 ,
				int j,
				List<Sysdefnodeergodic> sysdefnodeergodicList) {
			// TODO Auto-generated method stub
			for(int i=0;i<a4.ListMap.get(j).size();i++)
			{Sysdefnodeergodic sysdefnodeergodic = new Sysdefnodeergodic(
					ergodicId,
					systemNum,//子系统个数 当前是第几个子系统
					"", //子系统名字  a1.getSystem_name
					a1.getSystem_id(), //当前子系统id
					a1.getNotSetNum(),//子系统中有问题的问题节点个数
					"", //a2.bpmdef.getSubject()当前流程名字
					Long.toString(a2.bpmdef.getDefId()),//当前流程id 
					a2.bpmdef.getActDefId(),//当前流程actdefid
					a2.bpmdef.getTypeName(),//当前流程类型
					a2.bpmdef.getStatus(),//当前流程状态
					a2.getNotSetNum(),//当前流程中有问题节点个数
					a2.getExeNum(),a2.getDependExeNum(),a2.getOperateExeNum(),a2.getOperateDependExeNum(),//四个指标
					Long.toString(a3.bpmNodeSet.getSetId()),//节点在bpmnodeset中的setId
					"",//a3.bpmNodeSet.getNodeName()节点名字
					a3.bpmNodeSet.getNodeId(),//流程节点id
					"",//节点作业名字
					"",//"否"或""
					"",//“设置”
					a3.getExeNum(),a3.getDependExeNum(),a3.getOperateExeNum(),a3.getOperateDependExeNum(),//四个指标
					Long.toString(a3.operateInfo.bpmDefinition.getDefId()),//操作图id
					"",//a3.operateInfo.bpmDefinition.getSubJect()操作图名字
					a3.operateInfo.bpmDefinition.getStatus(),//操作图状态
					a3.operateInfo.getExeNum(),a3.operateInfo.getDependExeNum(),//两个指标
					a4.getNodeId(),//操作图节点id
					"",//a4.getNodeName()操作图节点名字
					a4.getExeNum(),a4.getDependExeNum(),//两个指标
					Long.toString(a4.bpmFormDef.getFormDefId()),//操作图 节点绑定的表单id
					"",//表单名字   事务图名字
					null,   //事务图状态
					Long.toString(a4.formParcelList.get(j).getParcelID()),//数据包id
					"",//数据包名字
					Long.toString(a4.tableParcelList.get(j).getId()),//生成数据包的表id
					"",//名字
					a4.ListMap.get(j).get(i).toString(),//方法
					"(字段)"+a4.ListMap.get(j).get(i).toString());//字段
			
			sysdefnodeergodicList.add(sysdefnodeergodic);
			ergodicId++;
			}
			return sysdefnodeergodicList;
		}
	
		public WDefInformation getNodeTimesInMark(WDefInformation def_info) {    //从**这里开始统计马尔科夫链节点运行次数
			String actDefId=def_info.bpmdef.getActDefId();    
			Long DefId=def_info.bpmdef.getDefId();       //通过流程对象获取DefId
			//List<Markovchain> chainId=markovchainService.getByDefId(DefId);  
			List<Markovchain> chainId = def_info.getMarkovchainList();//得到流程对象的马尔科夫链
			List<String> nodeNameList = null;
			if(chainId.size()!=0){     //取得的链不为空
				System.out.println("进入马尔科夫链的步骤");
				for(int i=0;i<chainId.size();i++)
				     {   if(chainId.get(i).getFrequency()==null)continue;
					    Long Frequency =chainId.get(i).getFrequency();
					   // System.out.println("马尔科夫链的发生次数 为："+Frequency);
				        Long d =chainId.get(i).getDependId();   //从链里取得依赖ID
				        if(d!=null){      //有依赖ID就有依赖关系 
					        		  String xmlStr=markovchainService.getmarkXmlById(d);
					        		  //System.out.println("取得马尔科夫链的文件:"+xmlStr);
					        		  nodeNameList = markovchainService.findNode(xmlStr);
						        	 for(String a:nodeNameList)
							          {//System.out.println("取得马尔科夫链的节点名称为:"+a);	 
								        for(WNodeInformation a1 :def_info.def_node_info_list)
											{
												if(a1.bpmNodeSet.getNodeId().equals(a))   //取节点对象节点Id与节点List的Id比较，记录节点运行次数
												{   
													a1.setDependExeNum(a1.getDependExeNum()+Frequency.intValue());
													//System.out.println("#######流程节点次数########："+a1.getDependExeNum());
												}
										   }	
							          }
						          	 
				        	  
				        }
				        	  //System.out.println("没有依赖关系");
					          String xmlStr=chainId.get(i).getMarkovchainXML();     //没依赖关系的取得是自己的xml文件
				             // System.out.println("取得马尔科夫链的文件:"+xmlStr);
				              nodeNameList = markovchainService.findNode(xmlStr);   //通过解析文件获取节点名称,接收的类型是List型
				         for(String a:nodeNameList)
				          {//System.out.println("取得马尔科夫链的节点名称为:"+a);	 
					        for(WNodeInformation a1 :def_info.def_node_info_list)
								{
									if(a1.bpmNodeSet.getNodeId().equals(a))   //取节点对象节点Id与节点List的Id比较，记录节点运行次数
									{
											a1.setExeNum(a1.getExeNum()+Frequency.intValue());	
										
										
									}
								}	
				            }
				         }//不依赖结束  
				      //for 结束
			}else{      
				  // System.out.println("取得的马尔科夫链为空 ");
				 }   
			return def_info;
		}//**统计马尔科夫链节点运行次数到这里结束 
		/**
		 * 查找节点的Label
		 * @param xml，nodeName
		 * @return label
		 *
		 */
		public List<String> find(String xml) {
			List<String> namelList = new ArrayList<String>();
			try {
				//System.out.println(xmlStr);
				Document document = DocumentHelper.parseText(xml);
				Element root = document.getRootElement();//得到根节点
				//System.out.println("输出根节点"+root);
				List<Element> elementList = root.elements();//得到根节点下的节点列表
					for (Element element : elementList) {  //遍历
						//System.out.println("外层222"+element.attribute("id").getText());
						String name = element.attribute("id").getText();
						if(name.contains("tartEvent")||name.contains("ndEvent")||name.contains("task")||name.contains("serTask")||name.contains("sequenceFlow")){ //判断是不是要找的节点
							
						}
						else{
							namelList.add(name);
						}
					}
					
					
				} catch (DocumentException e) {
				e.printStackTrace();
				}
			return namelList;
		}
		/**
		 * 计算操作图节点在马尔科夫链中走的次数
		 * @param 
		 * @return 
		 * @author 白晓帆
		 */
		
		public WOperateInfo getOperateNodeTimesInMark(WOperateInfo operateInfo) {
			String actDefId=operateInfo.bpmDefinition.getActDefId();
			Long defId=operateInfo.bpmDefinition.getDefId();
			//List<Markovchain> chainId=markovchainService.getByDefId(defId);  //从**这里开始 通过DefId获取马尔科夫链
			List<Markovchain> chainId =operateInfo.getMarkovchainList();//得到流程的马尔科夫链
			List<String> nodeNameList = new ArrayList<String>();
			if(!chainId.isEmpty()){     //取得的链不为空
				//System.out.println("进入马尔科夫链的步骤");
				for(int i=0;i<chainId.size();i++)
				     {   
					    Long Frequency =chainId.get(i).getFrequency();
					    if(Frequency==null){continue;}
					    //System.out.println("马尔科夫链的发生次数 为："+Frequency);
				        Long dependId =chainId.get(i).getDependId();   //从链里取得依赖ID
				        if(dependId!=null){
				        	 //System.out.println("有依赖关系");
				        	// System.out.println("取得马尔科夫链的依赖ID为 :"+dependId);				        	
					        		  String xmlStr=markovchainService.getmarkXmlById(dependId);
					        		  //System.out.println("取得马尔科夫链的文件:"+xmlStr);
					        		  nodeNameList = markovchainService.findNode(xmlStr);
						        	 for(String markNodeName:nodeNameList)
							          {//System.out.println("取得马尔科夫链的节点名称为:"+markNodeName);	 
								        for(OperateNodeInfo operateNodeInfo :operateInfo.operateNodeInfoList)
											{
												if(operateNodeInfo.nodeId.equals(markNodeName))   //取节点对象节点Id与节点List的Id比较，记录节点运行次数
												{   
													
													operateNodeInfo.setDependExeNum(operateNodeInfo.getDependExeNum()+Frequency.intValue());
													System.out.println("#######操作图依赖次数########："+operateNodeInfo.getDependExeNum());
												
												}
											}	
							          }
						        
						         
				        	  
				        }
				        	  //System.out.println("没有依赖关系");
					          String xmlStr=chainId.get(i).getMarkovchainXML();     //没依赖关系的取得是自己的xml文件
				              //System.out.println("取得马尔科夫链的文件:"+xmlStr);
				              nodeNameList = markovchainService.findNode(xmlStr);   //通过解析文件获取节点名称,接收的类型是List型
				         for(String markNodeName:nodeNameList)
				          {//System.out.println("取得马尔科夫链的节点名称为:"+markNodeName);	 
					        for(OperateNodeInfo operateNodeInfo :operateInfo.operateNodeInfoList)
								{
									if(operateNodeInfo.getNodeId().equals(markNodeName))   //取节点对象节点Id与节点List的Id比较，记录节点运行次数
									{
										operateNodeInfo.setExeNum(operateNodeInfo.getExeNum()+Frequency.intValue());
										System.out.println("#######操作图bu依赖次数########："+operateNodeInfo.getDependExeNum());
									}
								}	
				            }
				         //不依赖结束 
				         
				     }  //for 结束
			}else{      
				   System.out.println("取得的马尔科夫链为空 ");

				 }   //**到这里结束 
			
			return operateInfo;
		}
		/**
		 * 查找节点下对应操作图的节点
		 * @param xml，nodeName
		 * @return 
		 *
		 */
		public List<Map> findOperateNode(String xml) {
			//Map <String,Object>nodeMap = new HashMap<String,Object>();
			List<Map> nodeList = new ArrayList<Map>();
			try {
				//System.out.println(xmlStr);
				Document document = DocumentHelper.parseText(xml);
				Element root = document.getRootElement();//得到根节点
				//System.out.println("输出根节点"+root);
				List<Element> elementList = root.elements();//得到根节点下的节点列表
					for (Element element : elementList) 
					{  //遍历
						//System.out.println("外层222"+element.attribute("id").getText());
//						if(element.attribute("id").getText().contains("Task"))
//						{ //判断是不是要找的节点
							String nodeId=element.attribute("id").getText();
							if(nodeId.contains("StartEvent")||nodeId.contains("EndEvent")||nodeId.contains("sequenceFlow")||nodeId.contains("XORGateway")){
								continue ;
							}
							else{
								List<Element>eles = element.elements();
								 for (int i = 0; i<eles.size();i++) 
								 {
									 Map <String,Object>nodeMap = new HashMap<String,Object>();
							    	 if(eles.get(i).getName().equals("label"))
							    	 {
							    		 String NodeName = eles.get(i).getText(); 
							    		 nodeMap.put(nodeId,NodeName);
							    		 nodeList.add(nodeMap);
							    		// System.out.println("_________________________nodeList:"+nodeList.size());
							    		 
							    	 }
								}
							}
							
							
//						}
					
					}
				} 
			catch (DocumentException e)
			{
				e.printStackTrace();
			}
			//System.out.println("changdu++++++++++++++++++++++++++"+nodeList.size());
			return nodeList;
		}
		/**
		 * 查找节点下对应操作图的defKey
		 * @param xml，nodeName
		 * @return defKey
		 *
		 */
		public String findOperateDefKey(String xml,String nodeName) {
			
			String defKey ="";
			//System.out.println("nodeName#####"+nodeName);
			try {
				//System.out.println(xmlStr);
				Document document = DocumentHelper.parseText(xml);
				Element root = document.getRootElement();//得到根节点
				System.out.println("输出根节点"+root);
				List<Element> elementList = root.elements();//得到根节点下的节点列表
					for (Element element : elementList) {  //遍历
						//System.out.println("外层111"+element.getName());
						//System.out.println("外层222"+element.attribute("id").getText());
						if(element.attribute("id").getText().equals(nodeName)){  //判断是不是要找的节点
							List<Element> eles = element.elements();//获得该节点下的下层节点列表
							for (Element ele : eles) {
								//System.out.println("内层########"+ele.getName());
								if(ele.getName().equals("subDefKey")){//判断要找的subDefKey节点
									defKey = ele.getText();//得到节点内容
									//System.out.println("内内222222"+defKey);
								}
							}
						}
					}
					
				} catch (DocumentException e) {
				e.printStackTrace();
				}
			return defKey;
		}
		/**
		 * 分割字符串
		 * @param str
		 * @return str[]
		 * @author 白晓帆
		 */
		public  String [] cutString(String str){
			 String[] strarray=str.split(","); 
			
			return strarray;
		}
	public void delAll() 
	{
		dao.delAll();
	}	
	//将List中的元素出现次数计数
	public Map<String,Integer> getCount(List<String> methodList){
		Map<String,Integer> countMap = new HashMap<String,Integer>();
		int count=0;
		for(int i=0;i<methodList.size();i++){
			methodList.get(i);
			for(int j=0;j<methodList.size();j++){
				
				if(methodList.get(i).equals(methodList.get(j))){
					count++;
				}
			}
			System.out.println(methodList.get(i)+"相同的有"+count);
			countMap.put(methodList.get(i), count);
			count=0;
		} 
		return countMap;
	}
	
	/**
	 * 查找流程图的马尔科夫链并将其存入流程图类下
	 * @param defInfo
	 * @return defInfo
	 *
	 */
	public WDefInformation setDefMarkovchainId (WDefInformation defInfo){
		Long defInfAll= defInfo.bpmdef.getDefId();
		List<Markovchain>markov=markovchainService.getByDefId(defInfAll);
	    defInfo.markovchainList = new ArrayList<Markovchain>();
	    defInfo.setMarkovchainList(markov);
	    return defInfo;
		
	}
	/**
	 * 查找操作图的马尔科夫链并将其存入流程图类下
	 * @param opeInfo
	 * @return opeInfo
	 *
	 */
	public WOperateInfo setOpeMarkovchainId (WOperateInfo opeInfo){
		Long opeInfAll= opeInfo.bpmDefinition.getDefId();
		List<Markovchain>markov=markovchainService.getByDefId(opeInfAll);
	    opeInfo.markovchainList = new ArrayList<Markovchain>();
	    opeInfo.setMarkovchainList(markov);
	    return opeInfo;
	}
	

	public String getNotSetNodeXmlByDef(WDefInformation defInfo) {
		// TODO Auto-generated method stub
		/*
		<flowdata>
		<node id="UserTask3" lable="任务分配与领取" notSetType1="0" notSetType2="1" />
		<node id="UserTask3" lable="任务分配与领取" notSetType="超级管理员" />
		</flowdata>
		 */
		String start = "<flowdata>";
		String end = "</flowdata>";
		String flowset = "";
		String nodeid = "<node id=";
		String label = " lable=";
		String notSetType=" notSetType=";
		if(defInfo.def_node_info_list.size()!=0)
		for(WNodeInformation a1:defInfo.def_node_info_list)
		{
			if(a1.activityDetail==null)
			flowset = flowset + nodeid + "\"" + a1.bpmNodeSet.getNodeId() +"\""+ label + "\"" + a1.bpmNodeSet.getNodeName() +"\""+notSetType+ "\"未设置任务信息\"/>";
			if(a1.operateInfo==null)
			flowset = flowset + nodeid + "\"" + a1.bpmNodeSet.getNodeId() +"\""+ label + "\"" + a1.bpmNodeSet.getNodeName() +"\""+notSetType+ "\"未绑定操作图\"/>";
		}	
		flowset = start + flowset + end;
		return flowset;
	}

	public WDefInformation count(Long id){
		WDefInformation defInfo = new WDefInformation(id);//这里的参数是一个Long的流程Id
		List<BpmDefinition> bpmDefinitionList = bpmDefinitionService.getByDefId(id);
		BpmDefinition bpmDefinition = bpmDefinitionList.get(bpmDefinitionList.size()-1);
		defInfo.setBpmdef(bpmDefinition);
		defInfo = defGetlianjie(defInfo,"");
		
		return defInfo;
	}
	
	/**
	 * 查找节点下对应操作图的defKey
	 * @param xml，nodeName
	 * @return defKey
	 *
	 */
	public String findScriptNodeDefKey(String xml,String nodeName) {
		String defKey ="";
		if(nodeName.contains("ScriptTask")){
			try {
				//System.out.println(xmlStr);
				Document document = DocumentHelper.parseText(xml);
				Element root = document.getRootElement();//得到根节点
				//System.out.println("输出根节点"+root);
				List<Element> elementList = root.elements();//得到根节点下的节点列表
					for (Element element : elementList) {  //遍历
						//System.out.println("外层111"+element.getName());
						//System.out.println("外层222"+element.attribute("id").getText());
						if(element.attribute("id").getText().equals(nodeName)){  //判断是不是要找的节点
							List<Element> eles = element.elements();//获得该节点下的下层节点列表
							for (Element ele : eles) {
								//System.out.println("内层########"+ele.getName());
								if(ele.getName().equals("subDefKey")){//判断要找的subDefKey节点
									defKey = ele.getText();//得到节点内容
									//System.out.println("内内222222"+defKey);
								}
							}
						}
					}
				} catch (DocumentException e) {
				e.printStackTrace();
				}
		}
		System.out.println("defKey:"+defKey);
		return defKey;
	}

	
	//排序的方法  ----Systongji 调的
	public void orderingBysystongji(int sort,List<WSystemInformation> sys_information)
	{
		if (sort!=0){	
		System.out.println("开始排序"+sort);
		
		switch(sort)
		{  
		
		case 0:break;
		case 1:  //按ExeNum排序
			      
			for(WSystemInformation a1:sys_information){
				if(a1.sys_def_info_list.size()!=0){  //流程链不为空
					
					this.defListOrderingByExeNum(a1.sys_def_info_list);	
			
					 for(WDefInformation def_info:a1.sys_def_info_list)
					 {
						 if(def_info.def_node_info_list.size()!=0)
						 {  
							 this.nodeListOrderingByExeNum(def_info.def_node_info_list);	
						}
					 }
				}
			}
			
			break;
	
		case 2://按DependExeNum排序
			
			for(WSystemInformation a1:sys_information){
				if(a1.sys_def_info_list.size()!=0){
					   
					this.defListOrderingByDependExeNum(a1.sys_def_info_list);
					 for(WDefInformation def_info:a1.sys_def_info_list)
					 {
						 if(def_info.def_node_info_list.size()!=0)
						 {
							 this.nodeListOrderingByDependExeNum(def_info.def_node_info_list);
					}
				 }
					
				}
			}break;
			
		case 3: //按OperateExeNum排序
			for(WSystemInformation a1:sys_information){
				if(a1.sys_def_info_list.size()!=0){
								   
					this.defListOrderingByOperateExeNum(a1.sys_def_info_list);
					 for(WDefInformation def_info:a1.sys_def_info_list)
					 {
						 if(def_info.def_node_info_list.size()!=0)
						 {
							 this.nodeListOrderingByOperateExeNum(def_info.def_node_info_list);
						}
					 }
				}
			}
			break;
		case 4:  //按OperateDependExeNum排序
			for(WSystemInformation a1:sys_information){
				if(a1.sys_def_info_list.size()!=0){
								   
					this.defListOrderingByOperateDependExeNum(a1.sys_def_info_list);
					
					 for(WDefInformation def_info:a1.sys_def_info_list)
					 {
						 if(def_info.def_node_info_list.size()!=0)
						 {
							 this.nodeListOrderingByOperateDependExeNum(def_info.def_node_info_list);
						}
					 }
				}
			}
			break;
		
		}
		System.out.println("排序结束");
		}	
	}//结束
	
	//排序的方法-----deftongji调用的
	public void orderingBydeftongji(int sort,List<WDefInformation> def_information)
	{
		if (sort!=0){
			
			System.out.println("开始排序"+sort);
				for(WDefInformation a1:def_information)
				a1=this.defBasicStatistics(a1);
			switch(sort)
			{  
			case 0:break;
			case 1:  //按ExeNum排序
				      
				for(WDefInformation a1:def_information){
					if(a1.def_node_info_list.size()!=0){
									   
						a1.def_node_info_list=this.nodeListOrderingByExeNum(a1.def_node_info_list);	
					}
				}
				
				break;
		
			case 2://按DependExeNum排序
				
				for(WDefInformation a1:def_information){
					if(a1.def_node_info_list.size()!=0){
									   
						a1.def_node_info_list=this.nodeListOrderingByDependExeNum(a1.def_node_info_list);
						
					}
				}break;
				
			case 3: //按OperateExeNum排序
				for(WDefInformation a1:def_information){
					if(a1.def_node_info_list.size()!=0){			   
						a1.def_node_info_list=this.nodeListOrderingByOperateExeNum(a1.def_node_info_list);
						for(WNodeInformation node_info:a1.def_node_info_list)
						 {
							 if(node_info.operateInfo!=null)
							if(node_info.operateInfo.operateNodeInfoList.size()!=0)
							 {
								 this.operateListOrderingByExeNum(node_info.operateInfo.operateNodeInfoList);
							}
						 }
					}
				}
				break;
			case 4:  //按OperateDependExeNum排序
				for(WDefInformation a1:def_information){
					if(a1.def_node_info_list.size()!=0){			   
						a1.def_node_info_list=this.nodeListOrderingByOperateDependExeNum(a1.def_node_info_list);
						for(WNodeInformation node_info:a1.def_node_info_list)
						 {    
							 if(node_info.operateInfo!=null)
							if(node_info.operateInfo.operateNodeInfoList.size()!=0)
							 {
								 this.operateListOrderingByDependExeNum(node_info.operateInfo.operateNodeInfoList);
							}
						 }
					}
				}
				break;
			
			}
			System.out.println("排序结束");
			}
	}//结束
	
	//排序的方法----nodetongji调用的
	public void orderingBynodetongji(int sort,List<WNodeInformation> node_information)
	{
		if (sort!=0){
			System.out.println("开始排序,排序的方式是："+sort);
			
			for(WNodeInformation node_info:node_information)
				if(node_info.operateInfo.operateNodeInfoList.size()!=0)
					node_info.operateInfo=this.operateBasicStatistics(node_info.operateInfo);
			
			switch(sort)
			{  
			case 0:break;
			case 3:  //按操作图节点的ExeNum排序
				      
				for(WNodeInformation node_info:node_information){
					if(node_info.operateInfo.operateNodeInfoList.size()!=0){
									   
						node_info.operateInfo.operateNodeInfoList=this.operateListOrderingByExeNum(node_info.operateInfo.operateNodeInfoList);				
					}
				}
				
				break;
		
			case 4://按操作图节点的DependExeNum排序
				
				for(WNodeInformation node_info:node_information){
					if(node_info.operateInfo.operateNodeInfoList.size()!=0){
									   
						node_info.operateInfo.operateNodeInfoList=this.operateListOrderingByDependExeNum(node_info.operateInfo.operateNodeInfoList);				
					}
				}break;

			}
			System.out.println("排序结束");
			}
	}//结束
	
	
	/**根据ExeNum标准运行次数排序开始
	 * 
	 * **/
	public List<WDefInformation> defListOrderingByExeNum(List<WDefInformation> def_infomation) { //流程链      ExeNum排序方式
		WSysdefnodeComparator comparator=new WSysdefnodeComparator();
		 Collections.sort(def_infomation,comparator);
		 return def_infomation;
	}
	public List<WNodeInformation> nodeListOrderingByExeNum(List<WNodeInformation> node_infomation) {  //节点链  ExeNum排序方式
		WSysdefnodeComparator comparator=new WSysdefnodeComparator();
		 Collections.sort(node_infomation,comparator);
		 return node_infomation;
	}  //结束
	

	/**根据DependExeNum依赖运行次数排序开始
	 * 
	 * **/
	public List<WDefInformation> defListOrderingByDependExeNum(List<WDefInformation> def_infomation) { //流程链     DependExeNum排序方式
		WSysdefnodeComparator2 comparator=new WSysdefnodeComparator2();
		 Collections.sort(def_infomation,comparator);
		 return def_infomation;
	}
	public List<WNodeInformation> nodeListOrderingByDependExeNum(List<WNodeInformation> node_infomation) {   //节点链     DependExeNum排序方式
		WSysdefnodeComparator2 comparator=new WSysdefnodeComparator2();
		 Collections.sort(node_infomation,comparator);
		 return node_infomation;
	} //结束
	
	
	
	/**根据OperateExeNum排序开始
	 * 
	 * **/
	public List<WDefInformation> defListOrderingByOperateExeNum(List<WDefInformation> def_infomation) {  //流程链    OperateExeNum排序方式
		WSysdefnodeComparator3 comparator=new WSysdefnodeComparator3();
		 Collections.sort(def_infomation,comparator);
		 return def_infomation;
	}
	public List<WNodeInformation> nodeListOrderingByOperateExeNum(List<WNodeInformation> node_infomation) { //节点链     OperateExeNum排序方式
		WSysdefnodeComparator3 comparator=new WSysdefnodeComparator3();
		 Collections.sort(node_infomation,comparator);
		 return node_infomation;
	} 
	
	public List<OperateNodeInfo> operateListOrderingByExeNum(List<OperateNodeInfo> operateNodeInfoList) {      //操作图节点链     OperateExeNum排序方式
		WSysdefnodeComparator3 comparator=new WSysdefnodeComparator3();
		 Collections.sort(operateNodeInfoList,comparator);
		return operateNodeInfoList;
	}
	//结束
	
	/**根据OperateDependExeNum排序方式开始
	 * 
	 * **/
	public List<WDefInformation> defListOrderingByOperateDependExeNum(List<WDefInformation> def_infomation) {    //传过来的流程链  OperateDependExeNum排序方式
		WSysdefnodeComparator4 comparator=new WSysdefnodeComparator4();
		 Collections.sort(def_infomation,comparator);
		 return def_infomation;
	}
	public List<WNodeInformation> nodeListOrderingByOperateDependExeNum(List<WNodeInformation> node_infomation) {    //节点链     OperateDependExeNum排序方式 
		WSysdefnodeComparator4 comparator=new WSysdefnodeComparator4();
		 Collections.sort(node_infomation,comparator); 
		 return node_infomation;
	} 

	public List<OperateNodeInfo> operateListOrderingByDependExeNum(List<OperateNodeInfo> operateNodeInfoList) {     //操作图节点链     OperateDependExeNum排序方式
		WSysdefnodeComparator4 comparator=new WSysdefnodeComparator4();
		 Collections.sort(operateNodeInfoList,comparator);
		return operateNodeInfoList;
	}
      //结束

	
	//判断传过来的操作图对象是否有问题---开始
	public String getNotSetNodeXmlByOper(WOperateInfo operInfo) 
	{
		System.out.println("进入操作图节点教研函数");
		String start = "<operationdata>";
		String end = "</operationdata>";
		String operationset = "";
		String nodeid = "<node id=";
		String label = " lable=";
		String notSetType=" notSetType=";
		if(operInfo.operateNodeInfoList.size()!=0)
		{	System.out.println("进入for+++"+operInfo.operateNodeInfoList.size());
		for(OperateNodeInfo a1:operInfo.operateNodeInfoList)
		{
			System.out.println("操作图节点名字"+a1.nodeId+a1.nodeName);		
				if(a1.nodeId.contains("UserTask"))
				{
					System.out.println("usertask"+a1.nodeId+a1.nodeName);
				  
					if(a1.bpmFormDef==null)										
					{operationset = operationset + nodeid + "\"" + a1.getNodeId() +"\""+ label + "\"" + a1.getNodeName() +"\""+notSetType+ "\"未绑定表单\"/>";}
				}  
				else if(a1.nodeId.contains("ScriptTask"))
				{
					System.out.println("scriprtask"+a1.nodeId+a1.nodeName);		
				    if(a1.transactionInfo==null)				
				    {operationset = operationset + nodeid + "\"" + a1.getNodeId() +"\""+ label + "\"" + a1.getNodeName() +"\""+notSetType+ "\"未绑定子事务图\"/>";}
				}
				
		}
		}
		operationset = start + operationset + end;
		return operationset;
	}//结束
	
	
	//传过来的事务图对象判断是否有问题---开始
	public String getNotSetNodeXmlBytrans(TransactionInfo transInfo) 
	{
		String start = "<transactiondata>";
		String end = "</transactiondata>";
		String transactionset = "";
		String nodeid = "<node id=";
		String label = " lable=";
		String notSetType=" notSetType=";
		if(transInfo.transactionNodeInfoList.size()!=0)
		for(TransactionNodeInfo a1:transInfo.transactionNodeInfoList)
		{
			if(a1.methodList.size()==0)   //事务图节点
			transactionset = transactionset + nodeid + "\"" + a1.getNodeId() +"\""+ label + "\"" + a1.getNodeName() +"\""+notSetType+ "\"没有方法\"/>";		
		}	
		transactionset = start + transactionset + end;
		return transactionset;
 }//结束
	
	/**
	 * 查找节点下对应操作图的defKey
	 * @param xml，nodeName
	 * @return defKey
	 *
	 */
	public String findScriptService(String xml,String nodeId) {
		String service = null;
		if(nodeId.contains("ScriptTask")){
			try {
				//System.out.println(xmlStr);
				Document document = DocumentHelper.parseText(xml);
				Element root = document.getRootElement();//得到根节点
				//System.out.println("输出根节点"+root);
				List<Element> elementList = root.elements();//得到根节点下的节点列表
					for (Element element : elementList) {  //遍历
						//System.out.println("外层111"+element.getName());
						//System.out.println("外层222"+element.attribute("id").getText());
						if(element.attribute("id").getText().equals(nodeId)){  //判断是不是要找的节点
							List<Element> eles = element.elements();//获得该节点下的下层节点列表
							for (Element ele : eles) {
								//System.out.println("内层########"+ele.getName());
								if(ele.getName().equals("service")){//判断要找的subDefKey节点
									service = ele.getText();//得到节点内容
									//System.out.println("内内222222"+defKey);
								}
							}
						}
					}
				} catch (DocumentException e) {
				e.printStackTrace();
				}
		}
		System.out.println("service:"+service);
		return service;
	}	
	
	/**
	 * 查找事务图中读写页面节点和读写的表
	 * @param 事务图defkey
	 * @return 
	 *
	 */
	public Map<String,Object> findNodeTypeByDefKey(String defkey){
		List<BpmDefinition>  bpmList = bpmDefinitionService.getByDefKey(defkey);//取到事务图的整体信息
		Map<String,Long> tableid = new HashMap<String, Long>();
		Map<String,Object>result=new HashMap<String, Object>();
		Map<String,Object>Lresult=new HashMap<String, Object>();
		if(bpmList.size()==0)
		{
			Lresult.put(null, null);
		}
		else{
		Long defId=bpmList.get(0).getDefId();
		Long tableId = 0L;
		BpmDefinition bpm = bpmList.get(bpmList.size()-1);
		String xml = bpm.getDefXml();
		String type=findNodeTypeByXml(xml);//根据事务图的XML判断读写页面类型
		List<String> oList = markovchainService.findBpmNode(defId);
		System.out.println("这个："+oList);
		for(String olist : oList)
		{
			List<Long> tList = markovchainService.findtableId(olist,
					defId); 
			if (tList.size() == 0) {
				System.out.println("空");

			} else {
				//Map<Long,String> tableid = new HashMap<Long, String>();//将每个事物图对应的所有原子操作的表id放入Map
				
                for(int i=0;i<tList.size() ;i++){
                	
                	tableid.put(olist, tList.get(i)) ;  	
                
                }
		}
		}
		
		if(tableid!=null)
		{
			Set set = tableid.entrySet();  
			        Map.Entry[] entries = (Map.Entry[])set.toArray(new Map.Entry[set.size()]);  
		       for (int i=0;i<entries.length;i++){  
		    	   String nodeID=entries[i].getKey().toString();
		    	   String tableID=entries[i].getValue().toString();
		    	   Long Id=Long.parseLong(tableID);
		    	   List<BpmFormField>rs=new ArrayList<BpmFormField>();
		    	   rs=bpmFormFieldService.getByTableId(Id);
		    	   result.put("tableId",tableID);
		   		   result.put("information", rs);
		   		if(type==null)
				{
					result.put("type", null);
				}
				else if(type.equals("readpage"))
				{
					result.put("type", "readpage");
				}
				else if(type.equals("writepage"))
				{
					result.put("type", "writepage");
				} 
		            Lresult.put(nodeID, result);
		        
			       }  

		}
		else{
			
			Lresult.put(null, null);
		}
		}
		System.out.println("结果："+Lresult);
		return Lresult;
	}
	public String  findNodeTypeByXml(String defXml){
		String type=null;
		String operate=null;
		try {
			Document document = DocumentHelper.parseText(defXml);
			Element root = document.getRootElement();
			List<Element> elementList = root.elements();
			//System.out.println("元素："+elementList);
			for (Element element : elementList) {
				//System.out.println("***："+element.attributeValue("writepage"));
				if(element.attributeValue("readpage")!=null){
					type= "readpage";
					break;
				}
				else if(element.attributeValue("writepage")!=null){
					type= "writepage";
					break;
				}			
				else{
					
					type= null;
				}
					
					
			}
				
				
			} catch (DocumentException e){
			e.printStackTrace();
		}
		return type;
		
	}
	public List<String>  findNodeAndServiceType(String defXml,String nodeId){
		
		System.out.println("**********************找类型函数*******："+nodeId);
		List<String> typeList = new ArrayList<String>();
		String type=null;
		String serviceType=null;
		try {
			Document document = DocumentHelper.parseText(defXml);
			Element root = document.getRootElement();
			List<Element> elementList = root.elements();
			//System.out.println("元素："+elementList);
			for (Element element : elementList) {
				
				if(element.attributeValue("id").equals(nodeId)){
					System.out.println("***节点名："+element.attributeValue("id"));
					if(element.attributeValue("readpage")!=null){
						type= "readpage";
						serviceType = "www";
						
					}
					else if(element.attributeValue("writepage")!=null){
						type= "writepage";
						serviceType = "www";
					
					}
					else if(element.attributeValue("pageskip")!=null){
						type= "pageskip";
						serviceType = "www";
					
					}
					else if(element.attributeValue("datainquire")!=null){
						type= "datainquire";
						serviceType = "db";
				
					}
					else if(element.attributeValue("dataadd")!=null){
						type= "dataadd";
						serviceType = "db";
					
					}
					else if(element.attributeValue("datachange")!=null){
						type= "datachange";
						serviceType = "db";
					
					}
					else if(element.attributeValue("datadelate")!=null){
						type= "datadelate";
						serviceType = "db";
					
					}
					else{
						
						type= null;
					}
					typeList.add(type);
					typeList.add(serviceType);
				}
				
				//System.out.println("***："+element.attributeValue("writepage"));
				
					
			}
				
			System.out.println("***长度***********："+typeList.size());	
			} catch (DocumentException e){
			e.printStackTrace();
		}
		return typeList;
		
	}
	
	
}

