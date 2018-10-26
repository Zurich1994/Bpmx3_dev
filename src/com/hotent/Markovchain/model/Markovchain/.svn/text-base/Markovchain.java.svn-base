package com.hotent.Markovchain.model.Markovchain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:马尔科夫链表 Model对象
 */
public class Markovchain extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *马尔科夫链名称
	 */
	protected String  markovchainNAME;
	/**
	 *是否参与仿真
	 */
	protected String  isSimulation;
	/**
	 *所属流程
	 */
	protected String  belongto;
	/**
	 *依赖马尔科夫链
	 */
	protected String  dependmark;
	/**
	 *马尔科夫链发生次数
	 */
	protected Long  frequency;
	/**
	 *马尔科夫链详细备注
	 */
	protected String  markovchainDes;
	/**
	 *马尔科夫链的概率
	 */
	protected float  probability;
	/**
	 *依赖马尔科夫链id
	 */
	protected Long  dependId;
	/**
	 *所属流程id
	 */
	protected String  defid;
	/**
	 *马尔科夫链xml文件
	 */
	protected String  markovchainXML;
	
	public Markovchain() {
		super();
	}
	public Markovchain(String markovchainNAME, String isSimulation,
			String belongto, String markovchainDes, String defid,
			String markovchainXML) {
		/*markov.setMarkovchainNAME(subject+i);
		markov.setBelongto(subject);
		markov.setMarkovchainXML(markxml);
		markov.setMarkovchainDes("推荐马尔科夫链"+subject);
		markov.setDefid(defid.toString());
		markov.setIsSimulation("是");*/
		super();
		this.markovchainNAME = markovchainNAME;
		this.isSimulation = isSimulation;
		this.belongto = belongto;
		this.markovchainDes = markovchainDes;
		this.defid = defid;
		this.markovchainXML = markovchainXML;
	}

	// activiti流程发布ID
	@XmlAttribute
	protected Long actDeployId;
	
	public Long getActDeployId() {
		return actDeployId;
	}
	public void setActDeployId(Long actDeployId) {
		this.actDeployId = actDeployId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setMarkovchainNAME(String markovchainNAME) 
	{
		this.markovchainNAME = markovchainNAME;
	}
	/**
	 * 返回 马尔科夫链名称
	 * @return
	 */
	public String getMarkovchainNAME() 
	{
		return this.markovchainNAME;
	}
	public void setIsSimulation(String isSimulation) 
	{
		this.isSimulation = isSimulation;
	}
	/**
	 * 返回 是否参与仿真
	 * @return
	 */
	public String getIsSimulation() 
	{
		return this.isSimulation;
	}
	public void setBelongto(String belongto) 
	{
		this.belongto = belongto;
	}
	/**
	 * 返回 所属流程
	 * @return
	 */
	public String getBelongto() 
	{
		return this.belongto;
	}
	public void setDependmark(String dependmark) 
	{
		this.dependmark = dependmark;
	}
	/**
	 * 返回 依赖马尔科夫链
	 * @return
	 */
	public String getDependmark() 
	{
		return this.dependmark;
	}
	public void setFrequency(Long frequency) 
	{
		this.frequency = frequency;
	}
	/**
	 * 返回 马尔科夫链发生次数
	 * @return
	 */
	public Long getFrequency() 
	{
		return this.frequency;
	}
	public void setMarkovchainDes(String markovchainDes) 
	{
		this.markovchainDes = markovchainDes;
	}
	/**
	 * 返回 马尔科夫链详细备注
	 * @return
	 */
	public String getMarkovchainDes() 
	{
		return this.markovchainDes;
	}
	public void setProbability(float probability) 
	{
		this.probability = probability;
	}
	/**
	 * 返回 马尔科夫链的概率
	 * @return
	 */
	public float getProbability() 
	{
		return this.probability;
	}
	public void setDependId(Long dependId) 
	{
		this.dependId = dependId;
	}
	/**
	 * 返回 依赖马尔科夫链id
	 * @return
	 */
	public Long getDependId() 
	{
		return this.dependId;
	}
	public void setDefid(String defid) 
	{
		this.defid = defid;
	}
	/**
	 * 返回 所属流程id
	 * @return
	 */
	public String getDefid() 
	{
		return this.defid;
	}
	public void setMarkovchainXML(String markovchainXML) 
	{
		this.markovchainXML = markovchainXML;
	}
	/**
	 * 返回 马尔科夫链xml文件
	 * @return
	 */
	public String getMarkovchainXML() 
	{
		return this.markovchainXML;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Markovchain)) 
		{
			return false;
		}
		Markovchain rhs = (Markovchain) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.markovchainNAME, rhs.markovchainNAME)
		.append(this.isSimulation, rhs.isSimulation)
		.append(this.belongto, rhs.belongto)
		.append(this.dependmark, rhs.dependmark)
		.append(this.frequency, rhs.frequency)
		.append(this.markovchainDes, rhs.markovchainDes)
		.append(this.probability, rhs.probability)
		.append(this.dependId, rhs.dependId)
		.append(this.defid, rhs.defid)
		.append(this.markovchainXML, rhs.markovchainXML)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.markovchainNAME) 
		.append(this.isSimulation) 
		.append(this.belongto) 
		.append(this.dependmark) 
		.append(this.frequency) 
		.append(this.markovchainDes) 
		.append(this.probability) 
		.append(this.dependId) 
		.append(this.defid) 
		.append(this.markovchainXML) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("markovchainNAME", this.markovchainNAME) 
		.append("isSimulation", this.isSimulation) 
		.append("belongto", this.belongto) 
		.append("dependmark", this.dependmark) 
		.append("frequency", this.frequency) 
		.append("markovchainDes", this.markovchainDes) 
		.append("probability", this.probability) 
		.append("dependId", this.dependId) 
		.append("defid", this.defid) 
		.append("markovchainXML", this.markovchainXML) 
		.toString();
	}

}