package com.hotent.core.datahandler;

import com.hotent.core.util.AppUtil;

/**
 * 发布数据处理事件。
 * @author ray
 *
 */
public class DataPublishUtil {
	
	/**
	 * 发布数据。
	 * @param model
	 */
	public static void publishData(DataModel model){
		AppUtil.publishEvent(new UpdDataEvent(model));
	}
	
	/**
	 * 发布事件。
	 * @param tableName
	 * @param pk
	 */
	public static void publishData(String tableName ,String pk){
		DataModel model=new DataModel();
		model.setPk(pk);
		model.setTableName(tableName);
		AppUtil.publishEvent(new UpdDataEvent(model));
	}

}
