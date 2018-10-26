package com.hotent.platform.event.def;

import org.springframework.context.ApplicationEvent;

/**
 * 用户事件。
 * @author ray
 *
 */
public class UserEvent extends ApplicationEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2595904294669110938L;

	/**
	 * 更新用户。
	 */
	public static int ACTION_UPD=1;
	
	/**
	 * 删除用户。
	 */
	public static int ACTION_DEL=2;
	
	
	
	
	private Long userId=0L;
	
	/**
	 * 动作。
	 */
	private int action=ACTION_UPD;
	


	public UserEvent(Long source) {
		super(source);
		this.userId=source;
	}



	public int getAction() {
		return action;
	}



	public void setAction(int action) {
		this.action = action;
	}



	public Long getUserId() {
		return userId;
	}
	
	

}
