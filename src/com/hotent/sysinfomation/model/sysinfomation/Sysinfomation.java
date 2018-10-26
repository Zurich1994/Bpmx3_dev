package com.hotent.sysinfomation.model.sysinfomation;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:子系统内部信息统计 Model对象
 */
public class Sysinfomation extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *子系统数量
	 */
	protected String  sysNum;
	/**
	 *子系统名称
	 */
	protected String  sysName;
	/**
	 *子系统id
	 */
	protected String  sysId;
	/**
	 *新增与修改类操作非规范元素个数
	 */
	protected String  nmopNonElemNum;
	/**
	 *新增与修改类操作元素总个数
	 */
	protected String  nmopElemTotal;
	/**
	 *业务成熟度
	 */
	protected String  workSubsysMaturity;
	/**
	 *信息规范程度
	 */
	protected String  infoStandGrade;
	/**
	 *知识型操作个数
	 */
	protected String  knowledgeOpnum;
	/**
	 *知识型操作自动实现的操作数量
	 */
	protected String  knowledgeAutoOpnum;
	/**
	 *知识结构化比例
	 */
	protected String  knowledStrucktGrade;
	/**
	 *本地信息量
	 */
	protected String  local;
	/**
	 *利用外部信息量
	 */
	protected String  outGov;
	/**
	 *外部利用信息量
	 */
	protected String  inGov;
	/**
	 *利用公共信息量
	 */
	protected String  outPub;
	/**
	 *公共利用信息量
	 */
	protected String  inPub;
	/**
	 *信息资源开放程度
	 */
	protected String infoResOpenGrade ;
	
	/**
	 *本地服务量
	 */
	protected String  flocal;
	/**
	 *利用外部服务量
	 */
	protected String  foutGov;
	/**
	 *外部利用服务量
	 */
	protected String  finGov;
	/**
	 *利用公共服务量
	 */
	protected String  foutPub;
	/**
	 *公共利用服务量
	 */
	protected String  finPub;
	/**
	 *业务架构开放程度
	 */
	protected String  busFrameOpenGrade;
	/**
	 *节点数量
	 */
	protected String  defNum;
	/**
	 *节点数量
	 */
	protected String  activityNum;
	/**
	 *马尔科夫节点数量
	 */
	protected String  markActivityNum;
	protected String resspeed;   //响应速度
	protected String sercycle;   //服务周期
	protected String dataincre;//数据增量
	protected String comtrans;    //通信传输量
	protected String avefreqoccur;  //平均发生频度
	protected String peakfreq;    //上午高峰期发生频度
	protected String codelinenum; //代码行数
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setSysNum(String sysNum) 
	{
		this.sysNum = sysNum;
	}
	/**
	 * 返回 子系统名称
	 * @return
	 */
	public String getSysNum() 
	{
		return this.sysNum;
	}
	
	public void setSysName(String sysName) 
	{
		this.sysName = sysName;
	}
	
	/**
	 * 返回 子系统名称
	 * @return
	 */
	public String getSysName() 
	{
		return this.sysName;
	}
	public void setSysId(String sysId) 
	{
		this.sysId = sysId;
	}
	/**
	 * 返回 子系统id
	 * @return
	 */
	public String getSysId() 
	{
		return this.sysId;
	}
	public void setNmopNonElemNum(String nmopNonElemNum) 
	{
		this.nmopNonElemNum = nmopNonElemNum;
	}
	/**
	 * 返回 新增与修改类操作非规范元素个数
	 * @return
	 */
	public String getNmopNonElemNum() 
	{
		return this.nmopNonElemNum;
	}
	public void setNmopElemTotal(String nmopElemTotal) 
	{
		this.nmopElemTotal = nmopElemTotal;
	}
	/**
	 * 返回 新增与修改类操作元素总个数
	 * @return
	 */
	public String getNmopElemTotal() 
	{
		return this.nmopElemTotal;
	}
	public void setWorkSubsysMaturity(String workSubsysMaturity) 
	{
		this.workSubsysMaturity = workSubsysMaturity;
	}
	/**
	 * 返回 业务成熟度
	 * @return
	 */
	public String getWorkSubsysMaturity() 
	{
		return this.workSubsysMaturity;
	}
	public void setInfoStandGrade(String infoStandGrade) 
	{
		this.infoStandGrade = infoStandGrade;
	}
	/**
	 * 返回 信息规范程度
	 * @return
	 */
	public String getInfoStandGrade() 
	{
		return this.infoStandGrade;
	}
	public void setKnowledgeOpnum(String knowledgeOpnum) 
	{
		this.knowledgeOpnum = knowledgeOpnum;
	}
	/**
	 * 返回 知识型操作个数
	 * @return
	 */
	public String getKnowledgeOpnum() 
	{
		return this.knowledgeOpnum;
	}
	public void setKnowledgeAutoOpnum(String knowledgeAutoOpnum) 
	{
		this.knowledgeAutoOpnum = knowledgeAutoOpnum;
	}
	/**
	 * 返回 知识型操作自动实现的操作数量
	 * @return
	 */
	public String getKnowledgeAutoOpnum() 
	{
		return this.knowledgeAutoOpnum;
	}
	public void setKnowledStrucktGrade(String knowledStrucktGrade) 
	{
		this.knowledStrucktGrade = knowledStrucktGrade;
	}
	/**
	 * 返回 知识结构化比例
	 * @return
	 */
	public String getKnowledStrucktGrade() 
	{
		return this.knowledStrucktGrade;
	}
	public void setLocal(String local) 
	{
		this.local = local;
	}
	/**
	 * 返回 本地信息量
	 * @return
	 */
	public String getLocal() 
	{
		return this.local;
	}
	public void setOutGov(String outGov) 
	{
		this.outGov = outGov;
	}
	/**
	 * 返回 利用外部信息量
	 * @return
	 */
	public String getOutGov() 
	{
		return this.outGov;
	}
	public void setInGov(String inGov) 
	{
		this.inGov = inGov;
	}
	/**
	 * 返回 外部利用信息量
	 * @return
	 */
	public String getInGov() 
	{
		return this.inGov;
	}
	public void setOutPub(String outPub) 
	{
		this.outPub = outPub;
	}
	/**
	 * 返回 利用公共信息量
	 * @return
	 */
	public String getOutPub() 
	{
		return this.outPub;
	}
	public void setInPub(String inPub) 
	{
		this.inPub = inPub;
	}
	/**
	 * 返回 公共利用信息量
	 * @return
	 */
	public String getInPub() 
	{
		return this.inPub;
	
	}
	
	public void setInfoResOpenGrade(String infoResOpenGrade) 
	{
		this.infoResOpenGrade = infoResOpenGrade;
	}
	/**
	 * 返回 信息资源开发程度
	 * @return
	 */
	public String getInfoResOpenGrade() 
	{
		return this.infoResOpenGrade;
	
	}
	
	
	
	public void setFlocal(String flocal) 
	{
		this.flocal = flocal;
	}
	/**
	 * 返回 本地服务量
	 * @return
	 */
	public String getFlocal() 
	{
		return this.flocal;
	}
	public void setFoutGov(String foutGov) 
	{
		this.foutGov = foutGov;
	}
	/**
	 * 返回 利用外部服务量
	 * @return
	 */
	public String getFoutGov() 
	{
		return this.foutGov;
	}
	public void setFinGov(String finGov) 
	{
		this.finGov = finGov;
	}
	/**
	 * 返回 外部利用服务量
	 * @return
	 */
	public String getFinGov() 
	{
		return this.finGov;
	}
	public void setFoutPub(String foutPub) 
	{
		this.foutPub = foutPub;
	}
	/**
	 * 返回 利用公共服务量
	 * @return
	 */
	public String getFoutPub() 
	{
		return this.foutPub;
	}
	public void setFinPub(String finPub) 
	{
		this.finPub = finPub;
	}
	/**
	 * 返回 公共利用服务量
	 * @return
	 */
	public String getFinPub() 
	{
		return this.finPub;
	}
	public void setBusFrameOpenGrade(String busFrameOpenGrade) 
	{
		this.busFrameOpenGrade = busFrameOpenGrade;
	}
	/**
	 * 返回 业务架构开放程度
	 * @return
	 */
	public String getBusFrameOpenGrade() 
	{
		return this.busFrameOpenGrade;
	}
	
	
	
	public void setDefNum(String defNum) 
	{
		this.defNum = defNum;
	}
	/**
	 * 返回流程数量
	 * @return
	 */
	public String getDefNum() 
	{
		return this.defNum;
	}
	
	public void setActivityNum(String activityNum) 
	{
		this.activityNum = activityNum;
	}
	/**
	 * 返回 节点数量
	 * @return
	 */
	public String getActivityNum() 
	{
		return this.activityNum;
	}
	public void setMarkActivityNum(String markActivityNum) 
	{
		this.markActivityNum = markActivityNum;
	}
	/**
	 * 返回 马尔科夫节点数量
	 * @return
	 */
	public String getMarkActivityNum() 
	{
		return this.markActivityNum;
	}

public String getRes_speed() {
	return resspeed;
}
public void setRes_speed(String fResSpeed) {
	resspeed =fResSpeed;
}
public String getSer_cycle() {
	return sercycle;
}
public void setSer_cycle(String fserCycle) {
	sercycle = fserCycle;
}
public String getData_incre() {
	return dataincre;
}
public void setData_incre(String dataIncre) {
	dataincre = dataIncre;
}
public String getCom_trans() {
	return comtrans;
}
public void setCom_trans(String comTrans) {
	comtrans = comTrans;
}

public String getAve_freq_occur() {
	return avefreqoccur;
}
public void setAve_freq_occur(String aveFreqOccur) {
	avefreqoccur = aveFreqOccur;
}
public String getPeak_freq() {
	return peakfreq;
}
public void setPeak_freq(String peakFreq) {
	peakfreq = peakFreq;
}
public String getCode_line_num() {
	return codelinenum;
}
public void setCode_line_num(String codeLineNum) {
	codelinenum = codeLineNum;
}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Sysinfomation)) 
		{
			return false;
		}
		Sysinfomation rhs = (Sysinfomation) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.sysNum, rhs.sysNum)
		.append(this.sysName, rhs.sysName)
		.append(this.sysId, rhs.sysId)
		.append(this.nmopNonElemNum, rhs.nmopNonElemNum)
		.append(this.nmopElemTotal, rhs.nmopElemTotal)
		.append(this.workSubsysMaturity, rhs.workSubsysMaturity)
		.append(this.infoStandGrade, rhs.infoStandGrade)
		.append(this.knowledgeOpnum, rhs.knowledgeOpnum)
		.append(this.knowledgeAutoOpnum, rhs.knowledgeAutoOpnum)
		.append(this.knowledStrucktGrade, rhs.knowledStrucktGrade)
		.append(this.local, rhs.local)
		.append(this.outGov, rhs.outGov)
		.append(this.inGov, rhs.inGov)
		.append(this.outPub, rhs.outPub)
		.append(this.inPub, rhs.inPub)
		.append(this.infoResOpenGrade, rhs.infoResOpenGrade)
		.append(this.flocal, rhs.flocal)
		.append(this.foutGov, rhs.foutGov)
		.append(this.finGov, rhs.finGov)
		.append(this.foutPub, rhs.foutPub)
		.append(this.finPub, rhs.finPub)
		.append(this.busFrameOpenGrade, rhs.busFrameOpenGrade)
		.append(this.defNum, rhs.defNum)
		.append(this.activityNum, rhs.activityNum)
		.append(this.markActivityNum, rhs.markActivityNum)              
		
		.append(this.resspeed, rhs.resspeed)
		.append(this.sercycle, rhs.sercycle)
		.append(this.dataincre, rhs.dataincre)
		.append(this.comtrans, rhs.comtrans)
		.append(this.avefreqoccur, rhs.avefreqoccur)
		.append(this.peakfreq, rhs.peakfreq)
		.append(this.codelinenum, rhs.codelinenum)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.sysNum)
		.append(this.sysName) 
		.append(this.sysId) 
		.append(this.nmopNonElemNum) 
		.append(this.nmopElemTotal) 
		.append(this.workSubsysMaturity) 
		.append(this.infoStandGrade) 
		.append(this.knowledgeOpnum) 
		.append(this.knowledgeAutoOpnum) 
		.append(this.knowledStrucktGrade) 
		.append(this.local) 
		.append(this.outGov) 
		.append(this.inGov) 
		.append(this.outPub)
		.append(this.inPub)
		.append(this.infoResOpenGrade) 
		.append(this.flocal) 
		.append(this.foutGov) 
		.append(this.finGov) 
		.append(this.foutPub) 
		.append(this.finPub) 
		.append(this.busFrameOpenGrade) 
		.append(this.defNum)
		.append(this.activityNum) 
		.append(this.markActivityNum) 
		
		.append(this.resspeed)
		.append(this.sercycle)
		.append(this.dataincre)
		.append(this.comtrans)
		.append(this.avefreqoccur)
		.append(this.peakfreq)
		.append(this.codelinenum)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("sysNum", this.sysNum)
		.append("sysName", this.sysName) 
		.append("sysId", this.sysId) 
		.append("nmopNonElemNum", this.nmopNonElemNum) 
		.append("nmopElemTotal", this.nmopElemTotal) 
		.append("workSubsysMaturity", this.workSubsysMaturity) 
		.append("infoStandGrade", this.infoStandGrade) 
		.append("knowledgeOpnum", this.knowledgeOpnum) 
		.append("knowledgeAutoOpnum", this.knowledgeAutoOpnum) 
		.append("knowledStrucktGrade", this.knowledStrucktGrade) 
		.append("local", this.local) 
		.append("outGov", this.outGov) 
		.append("inGov", this.inGov) 
		.append("outPub", this.outPub) 
		.append("inPub", this.inPub) 
		.append("infoResOpenGrade", this.infoResOpenGrade)
		.append("flocal", this.flocal) 
		.append("foutGov", this.foutGov) 
		.append("finGov", this.finGov) 
		.append("foutPub", this.foutPub) 
		.append("finPub", this.finPub) 
		.append("busFrameOpenGrade", this.busFrameOpenGrade)
		.append("defNum", this.defNum)
		.append("activityNum", this.activityNum) 
		.append("markActivityNum", this.markActivityNum) 
		
		
		.append(this.resspeed)
		.append(this.sercycle)
		.append(this.dataincre)
		.append(this.comtrans)
		.append(this.avefreqoccur)
		.append(this.peakfreq)
		.append(this.codelinenum)
		.toString();
	}

}