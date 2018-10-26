package com.hotent.platform.xml.system;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "res")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResourcesXmlList {
	/**
	 * 流程定义列表
	 */
	@XmlElements({ @XmlElement(name = "resources", type = ResourcesXml.class) })
	private List<ResourcesXml> resourcesXmlList;

	public List<ResourcesXml> getResourcesXmlList() {
		if(resourcesXmlList==null){
			return new ArrayList<ResourcesXml>();
		}
		return resourcesXmlList;
	}

	public void setResourcesXmlList(List<ResourcesXml> resourcesXmlList) {
		this.resourcesXmlList = resourcesXmlList;
	}
	
}
