
package com.hotent.sysinfomation.dao.sysinfomation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.util.HashNMap;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;

import com.hotent.sysinfomation.model.sysinfomation.Sysinfomation;

@Repository
public class SysinfomationDao extends BaseDao<Sysinfomation>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Sysinfomation.class;
	}

public void add1(
			
			Long    id,
			String sysNum,
			String sysName,
			String sysId, 
			String nmopNonElemNum,
			String nmopElemTotal,
			
			String workSubsysMaturity,
			String infoStandGrade,
			String knowledgeOpnum,
			String knowledgeAutoOpnum,
			String knowledStrucktGrade,
			
			String local,
			String outGov,
			String inGov,
			String outPub,
			String inPub,
			
			String infoResOpenGrade,
			String flocal,
			String foutGov,
			String finGov,
			String foutPub,
			
			String finPub,
			String busFrameOpenGrade,
			String defNum,
			String activityNum,
			String markActivityNum,
			
			String resSpeed,   //响应速度
			String serCycle ,  //服务周期
			String dataIncre,//数据增量
			String comTrans,    //通信传输量
			String aveFreqOccur,  //平均发生频度
			String peakFreq ,   //上午高峰期发生频度
			String codeLineNum
			){
		System.out.println("进入写入dao");
		Map  map=new HashMap();
	
		map.put("id",id);
		map.put("sysNum", sysNum);
		map.put("sysName", sysName);
		map.put("sysId",sysId);	
		map.put("nmopNonElemNum",nmopNonElemNum);
		
		map.put("nmopElemTotal", nmopElemTotal);	
	    map.put("workSubsysMaturity", workSubsysMaturity);	
		map.put("infoStandGrade", infoStandGrade);	
		map.put("knowledgeOpnum", knowledgeOpnum);	
		map.put("knowledgeAutoOpnum",knowledgeAutoOpnum);
		
		map.put("knowledStrucktGrade",knowledStrucktGrade);
		map.put("local", local);
		map.put("outGov",outGov);
		map.put("inGov",  inGov);
		map.put("outPub",outPub);
		
		map.put("inPub", inPub);
		map.put("infoResOpenGrade",infoResOpenGrade);
		map.put("flocal",flocal);
		map.put("foutGov",foutGov);
		map.put("finGov",finGov);
		
		map.put("foutPub", foutPub);
		map.put("finPub",finPub);
		map.put("busFrameOpenGrade",busFrameOpenGrade);
		map.put("defNum",defNum);
		map.put("activityNum",activityNum);
		
		map.put("markActivityNum",markActivityNum);
		map.put("resSpeed", resSpeed);
		map.put("serCycle",serCycle);
		map.put("dataIncre",dataIncre);
		map.put("comTrans",comTrans);
		
		map.put("aveFreqOccur",aveFreqOccur);
		map.put("peakFreq",peakFreq);
		map.put("codeLineNum",codeLineNum);
		System.out.println("进入系统写入函数");
		
		getBySqlKey("addBy",map);
		System.out.println("写入成功");
	}
	public void delall() {
		// TODO Auto-generated method stub
		getBySqlKey("delall");
	}
	public List<Sysinfomation> getAll1()
	{
		List<Sysinfomation> sysinfomationlist=getBySqlKey("getAll1");
		return sysinfomationlist;
	}
}