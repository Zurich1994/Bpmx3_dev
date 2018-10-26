package com.hotent.platform.xml.form;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.hotent.platform.model.form.BpmDataTemplate;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormRights;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.xml.table.BpmFormTableXml;

/**
 * <pre>
 * 对象功能:表单定义的XMl配置
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2012-12-29 13:59:56
 * </pre>
 */
@XmlRootElement(name = "formDefs")
@XmlAccessorType(XmlAccessType.FIELD)
public class BpmFormDefXml {
	/**
	 * 自定义表单
	 */
	@XmlElement(name = "bpmFormDef", type = BpmFormDef.class)
	private BpmFormDef bpmFormDef;

	/**
	 * 其它版本的 自定义表单 List
	 */
	@XmlElementWrapper(name = "bpmFormDefList")
	@XmlElements({ @XmlElement(name = "formDefs", type = BpmFormDefXml.class) })
	private List<BpmFormDefXml> bpmFormDefXmlList;

	/**
	 * 表单权限List
	 */
	@XmlElementWrapper(name = "bpmFormRightsList")
	@XmlElements({ @XmlElement(name = "bpmFormRights", type = BpmFormRights.class) })
	private List<BpmFormRights> bpmFormRightsList;


	/**
	 * 对于的表
	 */
	@XmlElement(name = "formTable", type = BpmFormTableXml.class)
	private BpmFormTableXml bpmFormTableXml;
	
	/**
	 * 附件或者帮助文档
	 */
	@XmlElementWrapper(name = "sysFileList")
	@XmlElements({ @XmlElement(name = "sysFile", type = SysFile.class) })
	private List<SysFile> sysFileList;
	
	/**
	 * 数据模版
	 */
	@XmlElement(name = "bpmDataTemplate", type = BpmDataTemplate.class)
	private BpmDataTemplate bpmDataTemplate;
	

	// ==========以下是getting和setting的方法================
	public BpmFormDef getBpmFormDef() {
		
		return bpmFormDef;
	}

	public void setBpmFormDef(BpmFormDef bpmFormDef) {
		this.bpmFormDef = bpmFormDef;
	}

	public List<BpmFormDefXml> getBpmFormDefXmlList() {
		return bpmFormDefXmlList;
	}

	public void setBpmFormDefXmlList(List<BpmFormDefXml> bpmFormDefXmlList) {
		this.bpmFormDefXmlList = bpmFormDefXmlList;
	}

	public List<BpmFormRights> getBpmFormRightsList() {
		return bpmFormRightsList;
	}

	public void setBpmFormRightsList(List<BpmFormRights> bpmFormRightsList) {
		this.bpmFormRightsList = bpmFormRightsList;
	}



	public BpmFormTableXml getBpmFormTableXml() {
		return bpmFormTableXml;
	}

	public void setBpmFormTableXml(BpmFormTableXml bpmFormTableXml) {
		this.bpmFormTableXml = bpmFormTableXml;
	}
	
	/**
	 * @return the sysFileList
	 */
	public List<SysFile> getSysFileList() {
		return sysFileList;
	}

	/**
	 * @param sysFileList the sysFileList to set
	 */
	public void setSysFileList(List<SysFile> sysFileList) {
		this.sysFileList = sysFileList;
	}

	public BpmDataTemplate getBpmDataTemplate() {
		return bpmDataTemplate;
	}

	public void setBpmDataTemplate(BpmDataTemplate bpmDataTemplate) {
		this.bpmDataTemplate = bpmDataTemplate;
	}

	

}
