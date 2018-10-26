package com.hotent.platform.xml.form;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <pre>
 * 对象功能:表单定义List的XMl配置
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2012-12-29 13:59:56
 * </pre>
 */
@XmlRootElement(name = "form")
@XmlAccessorType(XmlAccessType.FIELD)
public class BpmFormDefXmlList {
	/**
	 * 表单定义xml List
	 */
	@XmlElements({ @XmlElement(name = "formDefs", type = BpmFormDefXml.class) })
	private List<BpmFormDefXml> bpmFormDefXmlList;

	// ==========以下是getting和setting的方法================

	public List<BpmFormDefXml> getBpmFormDefXmlList() {
		return bpmFormDefXmlList;
	}

	public void setBpmFormDefXmlList(List<BpmFormDefXml> bpmFormDefXmlList) {
		this.bpmFormDefXmlList = bpmFormDefXmlList;
	}

}
