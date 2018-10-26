package com.hotent.platform.xml.table;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <pre> 
 * 对象功能:自定义表List的XMl配置
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2012-12-29 13:59:56
 * </pre>
 */
@XmlRootElement(name = "bpm")
@XmlAccessorType(XmlAccessType.FIELD)
public class BpmFormTableXmlList {
	
	
	@XmlElements({ @XmlElement(name = "tables", type = BpmFormTableXml.class) })
	private List<BpmFormTableXml> bpmFormTableXmlList;

	public List<BpmFormTableXml> getBpmFormTableXmlList() {
		return bpmFormTableXmlList;
	}

	public void setBpmFormTableXmlList(List<BpmFormTableXml> bpmFormTableXmlList) {
		this.bpmFormTableXmlList = bpmFormTableXmlList;
	}

}
