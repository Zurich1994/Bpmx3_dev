package com.hotent.platform.model.bpm;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
* <pre>
* 对象功能:按钮List的XMl配置
* 开发人员:helh
* 创建时间:2013-11-5 18:20:56
* </pre>
*/

@XmlRootElement(name = "bpm")
@XmlAccessorType(XmlAccessType.FIELD)
public class BpmNodeButtonXml {
	
	@XmlElementWrapper(name = "buttons")
	@XmlElements({ @XmlElement(name = "button", type = BpmButton.class) })
    private List<BpmButton> buttons;

	public List<BpmButton> getButtons() {
		return buttons;
	}

	public void setButtons(List<BpmButton> buttons) {
		this.buttons = buttons;
	}
	
	
}
