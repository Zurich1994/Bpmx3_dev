package com.hotent.core.datakey.impl;

import org.activiti.engine.impl.cfg.IdGenerator;

import com.hotent.core.util.UniqueIdUtil;

/**
 * 使用分布式的方式产生流程id。
 * @author ray
 *
 */
public class ActivitiIdGenerator implements IdGenerator {

	@Override
	public String getNextId() {
		return String.valueOf(UniqueIdUtil.genId());
		
	}

}
