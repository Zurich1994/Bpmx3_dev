package com.hotent.platform.service.form;

/**
 * 根据用户id获取构建获取数据的权限。
 * @author ray
 *
 */
public interface IDataPermission {

	/**
	 * 获取全部。
	 * @param userId
	 * @return
	 */
	public String getSql(Long userId);
	
	/**
	 * 描述
	 * @return
	 */
	public String getDesc();
}
