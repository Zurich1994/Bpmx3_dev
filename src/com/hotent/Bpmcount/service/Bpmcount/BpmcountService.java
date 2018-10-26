package com.hotent.Bpmcount.service.Bpmcount;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.h2.tools.RunScript;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fr.base.StringUtils;
import com.fr.bi.data.BIConstant.DATA;
import com.hotent.Markovchain.model.Markovchain.Markovchain;
import com.hotent.Operatercount.model.Operatercount.Operatercount;
import com.hotent.Operatercount.service.Operatercount.OperatercountService;
import com.hotent.Subsystemdef.model.Subsystemdef.Subsystemdef;
import com.hotent.Subsystemdef.service.Subsystemdef.SubsystemdefService;
import com.hotent.SysDefNodeErgodic.dao.SysDefNodeErgodic.SysdefnodeergodicDao;
import com.hotent.SysDefNodeErgodic.service.SysDefNodeErgodic.SysdefnodeergodicService;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.Atomiccount.service.Atomiccount.AtomiccountService;
import com.hotent.Bpmcount.model.Bpmcount.Bpmcount;
import com.hotent.Bpmcount.dao.Bpmcount.BpmcountDao;

import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;

import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;
import com.hotent.excelk.model.excelk.Excelk;
import com.hotent.excelk.service.excelk.ExcelkService;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.system.OperateNodeInfo;
import com.hotent.platform.model.system.WDefInformation;
import com.hotent.platform.model.system.WNodeInformation;
import com.hotent.platform.model.system.WOperateInfo;
import com.hotent.platform.service.bpm.BpmDefinitionService;


@Service
public class BpmcountService extends BaseService<Bpmcount>
{
	@Resource
	private BpmcountDao dao;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private SysdefnodeergodicService sysdefnodeergodicService;
	@Resource
	private OperatercountService operatercountService;

	@Resource
	private ExcelkService excelkService;
	@Resource
	private AtomiccountService atomiccountService;
	@Resource
	private SubsystemdefService subsystemdefService;

	 BpmcountService()
	{}
	
	@Override
	protected IEntityDao<Bpmcount,Long> getEntityDao() 
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
			Bpmcount bpmcount=getBpmcount(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				bpmcount.setId(genId);
				this.add(bpmcount);
			}else{
				bpmcount.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(bpmcount);
			}
			cmd.setBusinessKey(bpmcount.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Bpmcount对象
	 * @param json
	 * @return
	 */
	public Bpmcount getBpmcount(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Bpmcount bpmcount = (Bpmcount)JSONObject.toBean(obj, Bpmcount.class);
		return bpmcount;
	}
	/**
	 * 保存 bpmcount 信息
	 * @param bpmcount
	 */

	public void save(Bpmcount bpmcount) throws Exception{
		
		Long id=bpmcount.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			bpmcount.setId(id);
		    this.add(bpmcount);
		    System.out.println("--------增加数据库----------------");
		}
		else{
			System.out.println("--------更新数据库----------------");
		   this.update(bpmcount);
		}
	}
	
	/**
	 * 保存 bpmcount 信息
	 * @param bpmcount
	 */

	public static long time;
	
	public void insertBpmcount() throws Exception{
		List<Long> bpmidList= new ArrayList<Long>();
		List<WNodeInformation> nodeList = new ArrayList<WNodeInformation>();
		bpmidList.add(10000062530022l);
		String start = "8:00";
		String end = "10:00";
		System.out.println("************bpm进来了*****");
		Bpmcount bpmcount = new Bpmcount();
 		long time2 = 0l;
		long time1 =0l;
		for (int  i = 0;i<bpmidList.size();i++) {//循环遍历定死的流程id的List
			System.out.println("************获得流程Id： "+bpmidList.get(i));
			Long bpmid=bpmidList.get(i);//得到每个流程id
			WDefInformation defInfo=sysdefnodeergodicService.count(bpmid);//加工成WDefInformation对象			
			List<Excelk>  eList= excelkService.count(start,end,bpmid);
			for(int k=0;k<3;k++){
				long number = eList.get(k).getCount();
				time = Long.valueOf(eList.get(k).getTime());
			
				//long time = Long.valueOf(strarray[0])*60+Long.valueOf(strarray[1]);
				List<Map<String,Long>> listMap = getNodeProbaility(defInfo,(int)number,time);
				String defId = bpmid.toString(); 
				String defKey  = defInfo.getBpmdef().getDefKey();
				System.out.println("syslist： "+defKey);
				List <Subsystemdef> syslist = subsystemdefService.getByDefKey1(defKey);
				System.out.println("syslist： "+syslist.size());
				long sysId = syslist.get(0).getSys_id();
				
				nodeList = defInfo.getDef_node_info_list();//得到流程的节点链
				
				
				
				for(Map<String,Long> map11 : listMap){
				     for (String key1 : map11.keySet()){//流程图节点id
				    	Long t  = map11.get(key1);
				    	 System.out.println("流程图运行次数:"+t);
				    	// for(int m=0;m<eList.size();m++){//刘文月
				       				        	
				        	 for (int j=0;j<nodeList.size();j++) {//遍历节点链  咱们的
				        		 if(key1.equals(nodeList.get(j).bpmNodeSet.getNodeId())){
				        			 WNodeInformation nodeInfo = nodeList.get(j);//流程节点对象
				        			 String nodeid = nodeInfo.bpmNodeSet.getNodeId();//得到流程节点名
									 bpmcount.setNodeid(nodeid);//节点id赋值
									 bpmcount.setDefid(defId);//对bpmcount对象的流程图id赋值
									 WOperateInfo operateInfo = nodeList.get(j).getOperateInfo();//节点下操作图对象
									 Long operateId =operateInfo.bpmDefinition.getDefId();
									 bpmcount.setOperaterid(operateId.toString());
									 
									 
									 bpmcount.setSysId(sysId);
									 bpmcount.setRuns(t);
									 bpmcount.setTime(time);	
									 bpmcount.setId(0l);
									 //save(bpmcount);
									 insertoperatercount(operateInfo,bpmcount); 
									 
									 
									
									 
				        		 }
				        	 }
				        	 long times = map11.get(key1);
				        	// System.out.println("流程图运行次数times:"+times);
				        	 bpmcount.setRuns(times); 
				        // }
					}
				}
			}	
				 
		}		
	}
	public void  insertoperatercount(WOperateInfo operateInfo,Bpmcount bpmcount) throws Exception{
		//Map<String,Integer> operateNodeMap = new HashMap<String, Integer>();//计算流程下节点运行次数
		//getNodeProbaility(operateInfo,100,time);//计算操作图下节点运行次数
		System.out.println("************进来了*****");
		
		List<OperateNodeInfo> operaterNodeList = operateInfo.getOperateNodeInfoList();
		//System.out.println("操作图节点长度："+operaterNodeList.size());
		List<Map<String,Map<String,Long>>> listMap = operatercountService.getNodeProbaility( operateInfo,bpmcount.getRuns(),time);//计算操作图下每个链中的每个节点运行次数和开始时间
		for(Map<String,Map<String,Long>> map11 : listMap){//遍历listMap中每个链
	         for (String key1 : map11.keySet()){
	        	 //System.out.println(key1);//key1为节点id
	        	 for (int j=0;j<operaterNodeList.size();j++) {
	        		 OperateNodeInfo operaterNodeInfo = operaterNodeList.get(j);
	        		 if(key1.equals(operaterNodeInfo.getNodeId())){//key1==操作图类中的节点id
	        			 Operatercount operatercount = new Operatercount();
	 	 				 Long operaterid = operateInfo.bpmDefinition.getDefId();
	 	 				
	 	 				 if(operaterNodeInfo.bpmFormDef!=null){
	 	 					 Long ym = operaterNodeInfo.bpmFormDef.getFormDefId();
		 	 				 operatercount.setYm(ym.toString());
	 	 				 }
	 	 				 if(operaterNodeInfo.transactionInfo!=null){
	 	 					 Long transactionid = operaterNodeInfo.transactionInfo.bpmtransaction.getDefId();
	 	 					 operatercount.setTransactionid(transactionid.toString());
	 	 					
	 	 				 }
	 	 				 
	 	 				 operatercount.setBpmid(bpmcount.getDefid());//给operatercount的流程图id赋值
	 	 				 operatercount.setOperaterid(operaterid.toString());//给operatercount的操作tuid赋值
	 	 				 operatercount.setNodeid(key1);//给operatercount的节点id赋值
	 	 				 Map<String,Long> map22 = map11.get(key1);
	 		        	 
	 		        	 long t = map22.get("time");//时间
	 		        	 long ts = map22.get("times");//次数
	 		        	 operatercount.setTime(t);//给operatercount的开始时间赋值
	 		        	 operatercount.setRuns(ts);//给operatercount的运行次数赋值	 
	 		        	 operatercount.setId(0l);
	 		        	 operatercount.setSysId(bpmcount.getSysId());
	 		        	// operatercountService.save(operatercount);
	 		        	 if(operaterNodeInfo.transactionInfo!=null){
	 		        		atomiccountService.insertatomiccount(operaterNodeInfo.transactionInfo,operatercount);
		 	 				
	 		        	 }
	        			 }
	        		 }
	        	 
	         }
	         //System.out.println("map11："+map11);
	         Map<String,Long> endMap =  map11.get("EndEvent1");
        	//System.out.println("EndEvent1的开始时间："+endMap);
        	 time = endMap.get("time");	//的到每个链的EndEvent1的开始时间也就是流程图的下一个节点的开始时间
        	 System.out.println("time   +++++++++++++++++++："+time);
		}		
	}
	
	
	//获得马尔科夫链各个节点的概率
	 public List<Map<String,Long>> getNodeProbaility(WDefInformation defInfo,int number,long time) throws DocumentException
	 {
//		 String defId="10000060520000";
//		 Long defIdLong=Long.valueOf(defId);
//		 defInfo=sysdefnodeergodicService.count(defIdLong);
	
		 List<Markovchain> list=defInfo.getMarkovchainList();
		
			// System.out.println("----------------------------马尔科夫链的长度:"+list.size());
			 Map<Long,Float> map1=new HashMap<Long,Float>();//存链发生的概率
			 for(Markovchain a:list)//将依赖计算在内，计算马尔科夫链每个的概率
			 {
				 if(a.getDependId()==null)//如果没有依赖，发生概率不变
				 {
					 map1.put(a.getId(), a.getProbability());
				 }
				 else//如果发生依赖
				 {
					 Float probablility=0f;
					 if(map1.containsKey(a.getDependId()))
					 {
						 probablility=map1.get(a.getDependId());//所依赖的马尔科夫链的概率
					 }
					 try{
					 Float temp=probablility+a.getProbability();
					 //System.out.println(temp);
					 map1.put(a.getDependId(),temp);
					 }
					 catch(Exception e)
					 {
						 System.out.println(e.toString());
					 }
				 }
			 }
			 Map<Long,Integer> map2=new HashMap<Long,Integer>();//存链发生的次数
			  for (Long key : map1.keySet()) {
				   map2.put(key, 0);//初始化各个链的发生次数为0
				  // System.out.println("--------------key= "+ key + " and value= " + map1.get(key));
				  }
			  float Max = 1.0f, Min = 0;   
			  //计算每個鏈發生的次數
		      for (int i = 0; i < number; i++) {  
		            BigDecimal db = new BigDecimal(Math.random() * (Max - Min) + Min);  
		           //System.out.println("链的发生次数："+db.setScale(4, BigDecimal.ROUND_HALF_UP).toString());  // 保留4位小数并四舍五入  
		            float random=Float.parseFloat(db.setScale(4, BigDecimal.ROUND_HALF_UP).toString());
		            float tempMin=0;
		            float tempMax=0;
		            for (Long key : map1.keySet()) {
		            	tempMax=map1.get(key)+tempMax;
		 			  if(random>tempMin&&random<=tempMax)
		 			  {
		 				  int temp=map2.get(key);
		 				  temp++;
		 				  map2.put(key, temp);
		 			  }
		 			  tempMin=tempMin+map1.get(key);
		        }
		        }
		        for (Long key : map2.keySet()) {
					   //System.out.println("链的发生次数：key= "+ key + " and value= " + map2.get(key));
					  }
		        List<Map<String,Long>> listMap = new ArrayList<Map<String,Long>>();
		        long addtime = 0l;
		        //計算每個節點發生的次數
		        // for (Long key : map2.keySet()) {
		        	 for(Markovchain a:list)
		        	 {
		        		 long nodeStart = 0l;
		        		 long times = 0l;
		        			 Document document = DocumentHelper.parseText(a.getMarkovchainXML());
		        			 Element root = document.getRootElement(); 
		        			 List<Element> elementList=root.elements();
		        			 Map<String,Long> maps = new HashMap<String,Long>();
		        			 for(Element element:elementList)
		        			 {
		        				 String nodeId=element.attributeValue("id");
		        				 if(nodeId.contains("UserTask")){
		        					 addtime = addtime+1l;
		        					 nodeStart = addtime+time;
		        				 }
		        				 for (Long key : map2.keySet()){
		        					if(a.getId().equals(key))
		        						times = map2.get(key);
		        				 }
		        				 maps.put(nodeId,times );
		        			 }
		        			
		        			 listMap.add(maps);
		        	 }
		        // System.out.println("changdu :"+listMap.size());
		         for(int i=0;i<listMap.size();i++){
		        	 //System.out.println("listmap"+i+":"+listMap.get(i)); 
		         }
		         Map<String,Long> map11 = listMap.get(0); 
		     
		         return listMap;

		 } 
	 public void writedata(Long bpmid,String nodeid,long t){
		 long time = 0l; 
	     BpmDefinition bpmDefinition= bpmDefinitionService.getDefinitionById(bpmid.toString());
	     String actDefId =  bpmDefinition.getActDefId(); 
	     NodeCache nodeCache = new NodeCache();
	     Map<String,String> nextMap = nodeCache.getNextNodes(nodeid, actDefId);
	     Map<String,String> preMap = nodeCache.getPreNodes(nodeid, actDefId);
	     for(String key : nextMap.keySet()){
	    	 if(key.equals("EndEvent1")){
	    		 for(String key1 : preMap.keySet()){
	    			 if(key1.equals("StartEvent1")){
		    			 time = t; 
		    		 } 
	    			 else{
	    				 //去数据库里找结束时间（根据）
	    				 
	    			 }
	    		 }
	    		
	    		 return ;
	    	 }
	    	 else{
	    		 
	    	 }
	     }
	   
	 }
	 
	 
	}
	 

		 
		


