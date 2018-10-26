package com.hotent.core.datakey.impl;

import com.hotent.core.datakey.IKeyGenerator;
import com.hotent.core.util.AppUtil;
import com.hotent.platform.service.system.IdentityService;

/**
 * 根据流水号别名获取KEY。
 * @author ray
 *
 */
public class IdentityGenerator implements IKeyGenerator {
	
	private String alias="";

	@Override
	public Object nextId() throws Exception {
		IdentityService identityService=(IdentityService)AppUtil.getBean(IdentityService.class);
		return identityService.nextId(alias);
		 
	}

	/**
	 * 设置别名。
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	 

}
