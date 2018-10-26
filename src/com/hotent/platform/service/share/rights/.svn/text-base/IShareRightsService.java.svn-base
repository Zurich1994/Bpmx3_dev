package com.hotent.platform.service.share.rights;

import com.hotent.platform.model.share.SysShareRights;

/**
 * @author as xianggang
 * 
 */
public interface IShareRightsService {

	/**
	 *  添加一个共享数据
	 * @param json  包含（ 原类型，原ID ，目标ID	，共享条目 ）
	 */
	public void addShare(SysShareRights sysShareRights);
	
	/**
	 * 获取共享的类型
	 */
	public String getShareType();
	
	/**
	 * 获取共享的描述
	 */
	public String getShareDesc();
	
	
	public DataTemplateVO getDataObject(String id);
	
	public void removeShareRights(SysShareRights sysShareRights);
}
