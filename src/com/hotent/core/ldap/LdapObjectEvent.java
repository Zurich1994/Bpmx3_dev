package com.hotent.core.ldap;

import org.springframework.context.ApplicationEvent;

public class LdapObjectEvent extends ApplicationEvent {
	
	private boolean isUser=true;

	
	private static final long serialVersionUID = 6370522848066687821L;

	public LdapObjectEvent(Object source) {
		super(source);
	}

	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}
	
	
	
}
