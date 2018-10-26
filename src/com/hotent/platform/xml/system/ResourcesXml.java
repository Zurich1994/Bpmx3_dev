package com.hotent.platform.xml.system;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.system.Resources;
@XmlRootElement(name = "resources")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResourcesXml {
	@XmlElement(name = "resouce", type = Resources.class)
	private Resources resources;
	@XmlElement(name = "resourcesList", type = ResourcesXml.class)
	private List<ResourcesXml> resourcesList;
	
	public Resources getResources() {
		return resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}

	public List<ResourcesXml> getResourcesList() {
		if(resourcesList==null){
			return new ArrayList<ResourcesXml>();
		}
		return resourcesList;
	}

	public void setResourcesList(List<ResourcesXml> resourcesList) {
		this.resourcesList = resourcesList;
	}
	
}
