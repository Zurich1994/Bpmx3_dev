package com.hotent.Markovchain.service.Markovchain;
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
import com.hotent.Markovchain.model.Markovchain.Markovchain;
import com.hotent.Markovchain.model.Markovchain.NodeSet;
import com.hotent.Markovchain.model.Markovchain.SequenceFlow;
import com.hotent.Markovchain.dao.Markovchain.MarkovchainDao;
import com.hotent.Subsystemdef.dao.Subsystemdef.SubsystemdefDao;
import com.hotent.SysDefNodeErgodic.service.SysDefNodeErgodic.SysdefnodeergodicService;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeWebServiceService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.system.SubSystemService;

@Service
public class MarkovchainService extends BaseService<Markovchain>
{
	@Resource
	private MarkovchainDao dao;
	@Resource
	private BpmDefinitionService definitionservice;
	@Resource
	private BpmService bpmservice;
	@Resource
	private SubSystemService subSystemService;
	@Resource
	private SysdefnodeergodicService sysdefnodeergodicService;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private BpmNodeWebServiceService bpmNodeWebServiceService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	
	public MarkovchainService()
	{
	}
	
	@Override
	protected IEntityDao<Markovchain,Long> getEntityDao() 
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
			Markovchain markovchain=getMarkovchain(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				markovchain.setId(genId);
				this.add(markovchain);
			}else{
				markovchain.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(markovchain);
			}
			cmd.setBusinessKey(markovchain.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Markovchain对象
	 * @param json
	 * @return
	 */
	public Markovchain getMarkovchain(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Markovchain markovchain = (Markovchain)JSONObject.toBean(obj, Markovchain.class);
		return markovchain;
	}
	/**
	 * 保存 马尔科夫链表 信息
	 * @param markovchain
	 */

	public void save(Markovchain markovchain) throws Exception{
		Long id=markovchain.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			markovchain.setId(id);
		    this.add(markovchain);
		}
		else{
		   this.update(markovchain);
		}
	}

	/**
	 * 根据defid获得马尔科夫链
	 * @param json
	 * @return
	 * @author 魏嫚
	 */
	public List<Markovchain> getByDefId(Long defId){
		return dao.getByDefId(defId);
	}

	public String getmarkXmlById(long markid) {
		// TODO Auto-generated method stub
		return dao.getmarkXmlById(markid);
	}

	

	/**
	 * 根据流程defid
	 * 解析actXml文件，记录流程图的节点顺序信息，
	 * @param xmlStr
	 * @param defid
	 * @return nodes
	 * @throws Exception
	 * @author 
	 */
	@SuppressWarnings("unchecked")
	public List<String> findBpmNode(Long defId){
		
		BpmDefinition bpm = definitionservice.getById(defId);
		Long deployId = bpm.getActDeployId();
		String actxml = bpmservice.getDefXmlByDeployId(deployId.toString());
		List<String> nodes = new ArrayList<String>();
		try {
			List<SequenceFlow> sequenceflow = new ArrayList<SequenceFlow>();
			Document document = DocumentHelper.parseText(actxml);
			Element root = document.getRootElement();
			List<Element> elementList=root.elements();
			for(Element element : elementList){	
			 List<Element> eles = element.elements();
					for(Element ele : eles){
						if(ele.getName().contains("sequenceFlow")){
							String sourceRef = ele.attributeValue("sourceRef");
								if(sourceRef.contains("StartEvent")||sourceRef.contains("StartEvent"))
									System.out.println("***开始节点***");
								else{
									System.out.println("sourceRef:"+sourceRef);
									nodes.add(sourceRef);
								}
						
						}
					}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return nodes;
	}
	/**
	 * 通过操作图id返回事务图节点List
	 * @param nodeid(操作图节点id)，defid(操作图id)
	 *@ return nodest
	 */
	public List<String> searchOperate(String nodeId,Long defId ){
		//findOperateDefKey
		System.out.println("nodeId: "+nodeId+" defId: "+defId);
		BpmDefinition bpm = definitionservice.getById(defId);
		String defXml = bpm.getDefXml();

		String defKey = sysdefnodeergodicService.findScriptNodeDefKey(defXml,nodeId);//解析操作图节点下绑定的事务图的defkey
		System.out.println("defKey IIIIIIII:"+defKey);
		if(defKey==""){
			System.out.println("不是事务图节点");
		}
		else{
			List<BpmDefinition> bpmtList = definitionservice.getByDefKey(defKey);//通过defkey得到实物图对象
			BpmDefinition bpmt = bpmtList.get(bpmtList.size()-1);
			Long defIdt = bpmt.getDefId();//得到实物图id
			List<String> nodest = findBpmNode(defIdt);//得到实物图节点list
			return nodest;
		}
		return null;
	}
	
//****************************************************************************自己写的找defKey
	public String ssearchOperate(String nodeId,Long defId ){
		//findOperateDefKey
		System.out.println("nodeId: "+nodeId+" defId: "+defId);
		BpmDefinition bpm = definitionservice.getById(defId);
		String defXml = bpm.getDefXml();

		String defKey = sysdefnodeergodicService.findScriptNodeDefKey(defXml,nodeId);
		
		return  defKey;
	}
	
	
	/**
	 * 解析事物图原子操作操作的表的tableId
	 * @param nodeId，actDefId
	 * @return tableIdList
	 * @author 白晓帆
	 */

	public List<Long> findtableId(String nodeId,Long defId){
		BpmDefinition bpm = definitionservice.getById(defId);
		System.out.println("defid:+"+defId+"nodeId_+"+nodeId);
		String actDefId = bpm.getActDefId();
		List<String> list = new ArrayList<String>();
		List<Long> tableIdList = new ArrayList<Long>();
		list = bpmNodeWebServiceService.nameMethod(nodeId, actDefId);
		System.out.println("*****list******:"+list);
		for(int i=0;i<list.size();i+=2){
			String tableName =list.get(i);
			if(tableName.contains("ACCOUNT")){
				
				tableName = bpmNodeWebServiceService.tableName(tableName);
			}
			System.out.println("*****tableName******:"+tableName);	
			BpmFormTable bpmFormTable =	bpmFormTableService.getByTableName(tableName);
			Long tableId ;
			if(bpmFormTable==null){
				List<BpmFormDef> tableList  = bpmFormDefService.getByFormKey(tableName);
				if(tableList.size()==0){
					List<BpmFormDef> sabojie =	bpmFormDefService.getBySubject(tableName);  //从 前 一层写 一个 getSubject方法*********************get subject
					//int temp = 1;
				//for(BpmFormDef bpmf : xx){
				//int x =	bpmf.getVersionNo();
				  // if(x>=temp){
					 //  temp=x;
			    
				   //} 
					tableList.add(sabojie.get(sabojie.size()-1));
						
				
				}
				BpmFormDef bpmFormDef = tableList.get(0);
				
				tableId = bpmFormDef.getTableId();
				
				System.out.println("table1: "+tableId);
			} else {
				
			   tableId = bpmFormTable.getTableId();
			}
			System.out.println("*****tableName******:"+tableId);
			if(tableIdList.size()==0){
				tableIdList.add(tableId);
			}else{
				for(int j=0;j<tableIdList.size();j++){
					if(tableId.equals(tableIdList.get(j)))
						continue;
					else{
						if(j==tableIdList.size()-1){
							tableIdList.add(tableId);
						}
					}
				}
			}
		}
		return tableIdList;
	}
	/**
	 * 解析事物图原子操作操作的表的tableId
	 * @param nodeId，actDefId
	 * @return tableIdList
	 * @author 白晓帆
	 */

	public List<Long> findtableId1(List<String> list){
		List<Long> tableIdList = new ArrayList<Long>();
		
		System.out.println("*****list******:"+list);
		for(int i=0;i<list.size();i++){
			String tableName =list.get(i);
			if(tableName.contains("ACCOUNT")){
				
				tableName = bpmNodeWebServiceService.tableName(tableName);
			}
			System.out.println("*****tableName******:"+tableName);	
			BpmFormTable bpmFormTable =	bpmFormTableService.getByTableName(tableName);
			Long tableId ;
			if(bpmFormTable==null){
				List<BpmFormDef> tableList  = bpmFormDefService.getByFormKey(tableName);
				if(tableList.size()==0){
					List<BpmFormDef> sabojie =	bpmFormDefService.getBySubject(tableName);  //从 前 一层写 一个 getSubject方法*********************get subject
					//int temp = 1;
				//for(BpmFormDef bpmf : xx){
				//int x =	bpmf.getVersionNo();
				  // if(x>=temp){
					 //  temp=x;
			    
				   //} 
					tableList.add(sabojie.get(sabojie.size()-1));
						
				
				}
				BpmFormDef bpmFormDef = tableList.get(0);
				
				tableId = bpmFormDef.getTableId();
				
				System.out.println("table1: "+tableId);
			} else {
				
			   tableId = bpmFormTable.getTableId();
			}
			System.out.println("*****tableName******:"+tableId);
			if(tableIdList.size()==0){
				tableIdList.add(tableId);
			}else{
				for(int j=0;j<tableIdList.size();j++){
					if(tableId.equals(tableIdList.get(j)))
						continue;
					else{
						if(j==tableIdList.size()-1){
							tableIdList.add(tableId);
						}
					}
				}
			}
		}
		return tableIdList;
	}
	/**
	 * 通过操作图节点Id和操作图DefId找到节点下绑定的事务图Id
	 * @param nodeId,defId
	 * @return defIdt
	 */
	public Long searchTransactionDefId(String nodeId,Long defId ){
		
		BpmDefinition bpm = definitionservice.getById(defId);
		String defXml = bpm.getDefXml();
		String defKey = sysdefnodeergodicService.findOperateDefKey(defXml,nodeId);
		System.out.println("defKey:"+defKey);
		if(defKey==""){
			System.out.println("不是事务图节点!");
		}
		else{
			List<BpmDefinition> bpmtList = definitionservice.getByDefKey(defKey);
			BpmDefinition bpmt = bpmtList.get(bpmtList.size()-1);
			Long defIdt = bpmt.getDefId();
			
			return defIdt;
		}
		return null;
	}
	
	public  List<String> ssearchTransactionDefId(String nodeId,Long defId ){
		
		BpmDefinition bpm = definitionservice.getById(defId);
		String defXml = bpm.getDefXml();
		String defKey = sysdefnodeergodicService.findOperateDefKey(defXml,nodeId);      
		System.out.println("defKey:"+defKey);
		List<String> defkeys = new ArrayList<String>();
		defkeys.add(defKey);           //**找事务图节点defkey
		return defkeys;                    

	}

	/**
	 * 通过xml文件取节点名称时调findNode
	 * @param xmlstr
	 * @return idList
	 */
	public List<String> findNode(String xmlStr) {   //***开始
		List<String> nodeNameList=new ArrayList<String>();
		try {
			
			Document document = DocumentHelper.parseText(xmlStr);
			Element root = document.getRootElement();
			
			List<Element> elementList = root.elements();
			
				for (Element element : elementList) {
					
					String name = element.attribute("id").getText();
					if(name.contains("StartEvent")||name.contains("EndEvent")){
						continue;
					}else{
						nodeNameList.add(name);
					}	
				}
			} catch (DocumentException e) {
			e.printStackTrace();
		}
		return nodeNameList;
		
	}//***结束
    public String searchNewOperate(String nodeId,Long defId ){
		
		System.out.println("nodeId: "+nodeId+" defId: "+defId);
		BpmDefinition bpm = definitionservice.getById(defId);
		String defXml = bpm.getDefXml();

		String defKey = sysdefnodeergodicService.findScriptNodeDefKey(defXml,nodeId);		
		if(defKey==""){
			System.out.println("不是事务图节点");
		}
		else{
			return nodeId;
		}
		return null;
	}



}
