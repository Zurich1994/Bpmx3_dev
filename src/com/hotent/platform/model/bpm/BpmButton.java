package com.hotent.platform.model.bpm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


/**
* <pre>
* 对象功能:按钮的XMl配置
* 开发人员:helh
* 创建时间:2013-13-5 17:30:56
* </pre>
*/
@XmlRootElement(name = "button")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmButton {
	//按钮分类 0起始节点，1第一个节点，2会签专有按钮，3普通节点专有按钮，4普通节点
	@XmlAttribute
	protected Integer type;
	//按钮操作类型
	@XmlAttribute
	protected Integer operatortype;
	//是否可显示按钮脚本  1显示，0不显示
	@XmlAttribute
	protected Integer script;
	//按钮名称
	@XmlAttribute
	protected String text;
	//是否默认初始化
	@XmlAttribute
	protected Integer init;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOperatortype() {
		return operatortype;
	}

	public void setOperatortype(Integer operatortype) {
		this.operatortype = operatortype;
	}

	public Integer getScript() {
		return script;
	}

	public void setScript(Integer script) {
		this.script = script;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getInit() {
		return init;
	}

	public void setInit(Integer init) {
		this.init = init;
	}
	
	
}
