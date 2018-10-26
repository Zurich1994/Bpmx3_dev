package com.hotent.platform.event.def;

import org.springframework.context.ApplicationEvent;

import com.hotent.core.bpm.model.ProcessCmd;

/**
 * <pre> 
 * 描述：TODO
 * 构建组：bpm33
 * 作者：miaojf
 * 邮箱:miaojf@jee-soft.cn
 * 日期:2015-05-30 22:40:05
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class TriggerNewFlowEvent extends ApplicationEvent {

	
	/**
	 * serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 2591676062449086299L;

	public TriggerNewFlowEvent(TriggerNewFlowModel source) {
		super(source);
	}

}
