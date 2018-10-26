package com.hotent.platform.service.share.rights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShareRightsContainer {
	
	private Map<String,IShareRightsService> shareRightsMap=new HashMap<String, IShareRightsService>();
	
	private List<IShareRightsService> shareRightsList=new ArrayList<IShareRightsService>();
	

	public void setShareRightsList(List<IShareRightsService> IShareRightsServices) {
		for(IShareRightsService shareRights:IShareRightsServices){
			shareRightsMap.put(shareRights.getShareType(), shareRights);
		}
		this.shareRightsList=IShareRightsServices;
	}
	
	
	
	public List<IShareRightsService> getShareRightsList() {
		return shareRightsList;
	}


	public IShareRightsService getShareRightsService(String type){
		return shareRightsMap.get(type);
	}

}
