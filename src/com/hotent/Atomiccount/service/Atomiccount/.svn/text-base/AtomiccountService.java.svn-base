package com.hotent.Atomiccount.service.Atomiccount;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.Atomiccount.model.Atomiccount.Atomiccount;
import com.hotent.Atomiccount.dao.Atomiccount.AtomiccountDao;
import com.hotent.Operatercount.model.Operatercount.Operatercount;
import com.hotent.Subsystemdef.model.Subsystemdef.Subsystemdef;
import com.hotent.Subsystemdef.service.Subsystemdef.SubsystemdefService;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.system.TransactionInfo;
import com.hotent.platform.model.system.TransactionNodeInfo;
import com.hotent.platform.service.bpm.BpmDefinitionService;


@Service
public class AtomiccountService extends BaseService<Atomiccount>
{
	@Resource
	private AtomiccountDao dao;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private SubsystemdefService subsystemdefService;

	public AtomiccountService()
	{
	}
	
	@Override
	protected IEntityDao<Atomiccount,Long> getEntityDao() 
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
			Atomiccount atomiccount=getAtomiccount(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				atomiccount.setId(genId);
				this.add(atomiccount);
			}else{
				atomiccount.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(atomiccount);
			}
			cmd.setBusinessKey(atomiccount.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Atomiccount对象
	 * @param json
	 * @return
	 */
	public Atomiccount getAtomiccount(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Atomiccount atomiccount = (Atomiccount)JSONObject.toBean(obj, Atomiccount.class);
		return atomiccount;
	}
	/**
	 * 保存 原子操作运行次数 信息
	 * @param atomiccount
	 */

	public void save(Atomiccount atomiccount) throws Exception{
		Long id=atomiccount.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atomiccount.setId(id);
		    this.add(atomiccount);
		}
		else{
		   this.update(atomiccount);
		}
	}
	/**
	 * 保存 bpmcount 信息
	 * @param bpmcount
	 */

	public void insertatomiccount(TransactionInfo transactionInfo,Operatercount operatercount) throws Exception{
		System.out.println("************insertatomiccount进来了*****");
		List<TransactionNodeInfo> TransactionNodeInfoList = transactionInfo.getTransactionNodeInfoList();
		//System.out.println("事物图节点长度："+TransactionNodeInfoList.size());
		if(TransactionNodeInfoList.size()!=0){//事物图有原子操作
			for (int j=0;j<TransactionNodeInfoList.size();j++) {//循环遍历事物图节点List
				Atomiccount atomiccount = new Atomiccount();//新建atomiccount对象
				System.out.println("获取bpmcount对象的流程ID："+operatercount.getBpmid());
				atomiccount.setBpmid(operatercount.getBpmid());//对atomiccount对象的流程id赋值
				
				List<BpmDefinition> bpmlist = bpmDefinitionService.getByDefId(Long.valueOf(operatercount.getBpmid()));
				String defKey = bpmlist.get(0).getDefKey();
				System.out.println("syslist： "+defKey);
				List <Subsystemdef> syslist = subsystemdefService.getByDefKey1(defKey);
				System.out.println("syslist： "+syslist.size());
				long sysId = syslist.get(0).getSys_id();
				atomiccount.setOperaterid(operatercount.getOperaterid());//对atomiccount对象的操作图id赋值
				Long transactionid = transactionInfo.bpmtransaction.getDefId();//得到事物图id
				atomiccount.setTransactionid(transactionid.toString());//对atomiccount对象的事物图id赋值
				//System.out.println("********事物图Id："+transactionid);
				TransactionNodeInfo transactionNodeInfo = TransactionNodeInfoList.get(j);
				String nodeid = transactionNodeInfo.getNodeId();//得到事物图节点id
				String type =  transactionNodeInfo.getType();
				String serviceType = transactionNodeInfo.getServiceType();
				Map<String,Integer> methodMap = transactionNodeInfo.getMethodMap();
				//System.out.println("********************************Map******************************：" +methodMap);
				//System.out.println("节点id： "+nodeid);
				atomiccount.setNodeid(nodeid);//对atomiccount对象的节点id赋值
				atomiccount.setAtomictype(type);
				atomiccount.setSysId(sysId);
				atomiccount.setServicetype(serviceType);
				System.out.println("原子操作类型： "+type);
				System.out.println("服务类型： "+serviceType);
				System.out.println("子系统ID： "+sysId);
				if(methodMap!=null&&methodMap.size()==1){
					for (String key : methodMap.keySet()) {
						System.out.println("key： "+key);
						atomiccount.setAtomicid(key);
					}
				}
				Long runs =operatercount.getRuns();
				//  System.out.println("tttttttttttt运行次数："+runs);
				atomiccount.setRuns(runs); //对atomiccount对象的运行次数赋值
				atomiccount.setId(0l);
				atomiccount.setTime(operatercount.getTime());
				
				// System.out.println("---------写tttttttttttttttt");
				save(atomiccount);//保存atomiccount到数据库
			}
		}			
		else{//事物图没有节点
			Atomiccount atomiccount = new Atomiccount();
			//atomiccount.setId(0l);
			atomiccount.setTime(operatercount.getTime());
			atomiccount.setBpmid(operatercount.getBpmid());
			atomiccount.setOperaterid(operatercount.getOperaterid());//对bpmcount对象赋值
			atomiccount.setRuns(operatercount.getRuns());
		
			//System.out.println("--------else写入数据库----------------");
			//save(atomiccount);//保存到数据库
			}
	}
	public Long countAtomiccount(Long id){
		//System.out.println("******:"+id);
		List<Atomiccount> list = new ArrayList<Atomiccount>();
		list = dao.countAtomic(id);
		Long runs = 0l;
		for(int i=0;i<list.size();i++){
			Atomiccount atomiccount = list.get(i);
			runs += atomiccount.getRuns();
		}
		return runs;
	}
}
