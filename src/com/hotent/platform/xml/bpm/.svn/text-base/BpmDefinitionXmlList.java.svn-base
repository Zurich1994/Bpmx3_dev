package com.hotent.platform.xml.bpm;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <pre>
 * 对象功能:流程定义List的XMl配置
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2012-12-29 13:59:56
 * </pre>
 */
@XmlRootElement(name = "bpm")
@XmlAccessorType(XmlAccessType.FIELD)
public class BpmDefinitionXmlList {
	/**
	 * 流程定义列表
	 */
	@XmlElements({ @XmlElement(name = "bpmDefinitions", type = BpmDefinitionXml.class) })
	private List<BpmDefinitionXml> bpmDefinitionXmlList;

	public List<BpmDefinitionXml> getBpmDefinitionXmlList() {
		return bpmDefinitionXmlList;
	}

	public void setBpmDefinitionXmlList(
			List<BpmDefinitionXml> bpmDefinitionXmlList) {
		this.bpmDefinitionXmlList = bpmDefinitionXmlList;
	}

}
