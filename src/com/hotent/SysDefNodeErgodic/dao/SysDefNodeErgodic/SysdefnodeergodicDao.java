
package com.hotent.SysDefNodeErgodic.dao.SysDefNodeErgodic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;

import com.hotent.SysDefNodeErgodic.model.SysDefNodeErgodic.Sysdefnodeergodic;

@Repository
public class SysdefnodeergodicDao extends BaseDao<Sysdefnodeergodic>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Sysdefnodeergodic.class;
	}
	public void delAll(){
		getBySqlKey("delAll");
	}
	public void add(int id,int zj,String sysName,String sysId,String defName,String defId,String actDefId,int status,String nodeName,String nodeId,String setJudge,String setUp,String nodeWorkName){
		Map  map=new HashMap();
		map.put("id", id);
		map.put("zj", zj);	
		map.put("sysName", sysName);	
		map.put("sysId", sysId);	
		map.put("defName", defName);	
		map.put("defId", defId);	
		map.put("actDefId", actDefId);	
		map.put("status", status);	
		map.put("nodeName", nodeName);
		map.put("nodeId", nodeId);
		map.put("setJudge", setJudge);
		map.put("setUp", setUp);
		map.put("nodeWorkName", nodeWorkName);
		System.out.println("进入已经设置节点写入函数");
		getBySqlKey("addBy",map);
		System.out.println("写入成功");
	}
}