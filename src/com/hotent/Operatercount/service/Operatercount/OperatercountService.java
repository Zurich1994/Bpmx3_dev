package com.hotent.Operatercount.service.Operatercount;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.Atomiccount.service.Atomiccount.AtomiccountService;
import com.hotent.Bpmcount.model.Bpmcount.Bpmcount;
import com.hotent.Markovchain.model.Markovchain.Markovchain;
import com.hotent.Operatercount.model.Operatercount.Operatercount;
import com.hotent.Operatercount.dao.Operatercount.OperatercountDao;
import com.hotent.SysDefNodeErgodic.service.SysDefNodeErgodic.SysdefnodeergodicService;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ehcache.transaction.TransactionID;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.system.OperateNodeInfo;
import com.hotent.platform.model.system.TransactionInfo;
import com.hotent.platform.model.system.WDefInformation;
import com.hotent.platform.model.system.WNodeInformation;
import com.hotent.platform.model.system.WOperateInfo;
import com.hotent.platform.service.bpm.BpmDefinitionService;


@Service
public class OperatercountService extends BaseService<Operatercount>
{
	@Resource
	private OperatercountDao dao;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private SysdefnodeergodicService sysdefnodeergodicService;
	@Resource
	private AtomiccountService atomiccountService;
	
	public OperatercountService()
	{
	}
	
	@Override
	protected IEntityDao<Operatercount,Long> getEntityDao() 
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
			Operatercount operatercount=getOperatercount(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				operatercount.setId(genId);
				this.add(operatercount);
			}else{
				operatercount.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(operatercount);
			}
			cmd.setBusinessKey(operatercount.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Operatercount对象
	 * @param json
	 * @return
	 */
	public Operatercount getOperatercount(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Operatercount operatercount = (Operatercount)JSONObject.toBean(obj, Operatercount.class);
		return operatercount;
	}
	/**
	 * 保存 操作运行次数 信息
	 * @param operatercount
	 */

	public void save(Operatercount operatercount) throws Exception{
		Long id=operatercount.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			operatercount.setId(id);
		    this.add(operatercount);
		}
		else{
		   this.update(operatercount);
		}
	}
	/**
	 * 保存 bpmcount 信息
	 * @param bpmcount
	 */

	
	//获得马尔科夫链各个节点的概率
	 public List<Map<String,Map<String,Long>>> getNodeProbaility(WOperateInfo operateInfo,long number,long time) throws DocumentException
	 {
//		 String defId="10000060520000";
//		 Long defIdLong=Long.valueOf(defId);
//		 defInfo=sysdefnodeergodicService.count(defIdLong);
		
		 List<Markovchain> list=operateInfo.getMarkovchainList();
		
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
				   System.out.println("--------------key= "+ key + " and value= " + map1.get(key));
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
		        
		        //計算每個節點發生的次數
		        List<Map<String,Map<String,Long>>> listMap = new ArrayList<Map<String,Map<String,Long>>>();
		        long addtime = 0l;
		        //計算每個節點發生的次數
		        for(Markovchain a:list)
	        	 {
	        		 long nodeStart = 0l;
	        		 long times = 0l;
	        			 Document document = DocumentHelper.parseText(a.getMarkovchainXML());
	        			 Element root = document.getRootElement(); 
	        			 List<Element> elementList=root.elements();
	        			 Map<String,Map<String,Long>> map=new HashMap<String,Map<String,Long>>();
	        			 for(Element element:elementList)
	        			 {
	        				 Map<String,Long> maps = new HashMap<String,Long>();
	        				 String nodeId=element.attributeValue("id");
	        				 if(nodeId.contains("UserTask")){
	        					 addtime = addtime+1l;
	        					 nodeStart = addtime+time;
	        				 }
	        				 for (Long key : map2.keySet()){
	        					if(a.getId().equals(key))
	        						times = map2.get(key);
	        				 }
	        				 maps.put("time",nodeStart );
	        				 maps.put("times",times);
	        				 //System.out.println("操作图运行次数times:"+times);
	        				 map.put(nodeId, maps);
	        			 }
	        			// System.out.println("map:"+map);
	        			 listMap.add(map);
	        	 }
//		         
		         return listMap;
		 } 
}
