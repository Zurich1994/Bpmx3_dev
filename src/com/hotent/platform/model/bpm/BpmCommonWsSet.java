package com.hotent.platform.model.bpm;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * 对象功能:通用webservice调用设置 Model对象
 * 创建时间:2013-10-17 10:09:20
 */
public class BpmCommonWsSet extends BaseModel
{
	// 主键
	protected Long  id;
	// 别名
	protected String  alias;
	// wsdl地址
	protected String  wsdlUrl;
	// webservice设置
	protected String  document;
	//通用webservice调用参数列表
	protected List<BpmCommonWsParams> bpmCommonWsParamsList=new ArrayList<BpmCommonWsParams>();
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setAlias(String alias) 
	{
		this.alias = alias;
	}
	/**
	 * 返回 别名
	 * @return
	 */
	public String getAlias() 
	{
		return this.alias;
	}
	public void setWsdlUrl(String wsdlUrl) 
	{
		this.wsdlUrl = wsdlUrl;
	}
	/**
	 * 返回 wsdl地址
	 * @return
	 */
	public String getWsdlUrl() 
	{
		return this.wsdlUrl;
	}
	public void setDocument(String document) 
	{
		this.document = document;
	}
	/**
	 * 返回 webservice设置
	 * @return
	 */
	public String getDocument() 
	{
		return this.document;
	}
	public void setBpmCommonWsParamsList(List<BpmCommonWsParams> bpmCommonWsParamsList) 
	{
		this.bpmCommonWsParamsList = bpmCommonWsParamsList;
	}
	/**
	 * 返回 通用webservice调用参数列表
	 * @return
	 */
	public List<BpmCommonWsParams> getBpmCommonWsParamsList() 
	{
		return this.bpmCommonWsParamsList;
	}

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmCommonWsSet)) 
		{
			return false;
		}
		BpmCommonWsSet rhs = (BpmCommonWsSet) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.alias, rhs.alias)
		.append(this.wsdlUrl, rhs.wsdlUrl)
		.append(this.document, rhs.document)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.alias) 
		.append(this.wsdlUrl) 
		.append(this.document) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("alias", this.alias) 
		.append("wsdlUrl", this.wsdlUrl) 
		.append("document", this.document) 
		.toString();
	}
   
  

}