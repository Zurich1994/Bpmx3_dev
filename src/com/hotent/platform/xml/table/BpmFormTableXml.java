package com.hotent.platform.xml.table;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.system.Identity;

/**
 * <pre>
 * 对象功能:自定义表的XMl配置
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2012-12-29 13:59:56
 * </pre>
 */
@XmlRootElement(name = "tables")
@XmlAccessorType(XmlAccessType.FIELD)
public class BpmFormTableXml {
	/**
	 * 自定义表
	 */
	@XmlElement(name = "table", type = BpmFormTable.class)
	private BpmFormTable bpmFormTable;

	/**
	 * 自定义表字段列表
	 */
	@XmlElementWrapper(name = "fields")
	@XmlElements({ @XmlElement(name = "field", type = BpmFormField.class) })
	private List<BpmFormField> bpmFormFieldList;
	
	/**
	 * 流水号
	 */
	@XmlElementWrapper(name = "identitys")
	@XmlElements({ @XmlElement(name = "identity", type = Identity.class) })
	private List<Identity> identityList;

	/**
	 * 存在子表则循环
	 */
	@XmlElementWrapper(name = "subTables")
	@XmlElements({ @XmlElement(name = "subTable", type = BpmFormTableXml.class) })
	private List<BpmFormTableXml> bpmFormTableXmlList;

	public BpmFormTable getBpmFormTable() {
		return bpmFormTable;
	}

	public void setBpmFormTable(BpmFormTable bpmFormTable) {
		this.bpmFormTable = bpmFormTable;
	}

	public List<BpmFormField> getBpmFormFieldList() {
		return bpmFormFieldList;
	}

	public void setBpmFormFieldList(List<BpmFormField> bpmFormFieldList) {
		this.bpmFormFieldList = bpmFormFieldList;
	}

	public List<Identity> getIdentityList() {
		return identityList;
	}

	public void setIdentityList(List<Identity> identityList) {
		this.identityList = identityList;
	}

	public List<BpmFormTableXml> getBpmFormTableXmlList() {
		return bpmFormTableXmlList;
	}

	public void setBpmFormTableXmlList(List<BpmFormTableXml> bpmFormTableXmlList) {
		this.bpmFormTableXmlList = bpmFormTableXmlList;
	}

	

}
