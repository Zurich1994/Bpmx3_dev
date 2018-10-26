package com.hotent.platform.service.share.rights;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.hotent.platform.model.share.SysShareRights;


public class ShareRightsCalc {
	@Resource
	ShareRightsContainer container;
	
	public void setContainer(ShareRightsContainer container) {
		this.container = container;
	}

	public void execute(SysShareRights sysShareRights){
		IShareRightsService service= container.getShareRightsService(this.getType(sysShareRights)); 
		service.addShare(sysShareRights);
	}
	public DataTemplateVO getDataTemplateVO(String type,String id){
		IShareRightsService service= container.getShareRightsService(type); 
		return (DataTemplateVO) service.getDataObject(id);
	}
	public void removeShareRights(SysShareRights sysShareRights){
		IShareRightsService service= container.getShareRightsService(this.getType(sysShareRights)); 
		service.removeShareRights(sysShareRights);
	}
	private String getType(SysShareRights sysShareRights){
		JSONObject shareItem = JSONObject.fromObject(sysShareRights.getShareItem());
		return  shareItem.getString("type");
	}
}
