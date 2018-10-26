package com.hotent.core.excel.reader;

import java.util.List;

public class TableEntity {
	
	/** 主表=1 */
	public final static Short IS_MAIN = 1;
	/** 从表=0 */
	public final static Short NOT_MAIN = 0;
	
	/**
	 * 名字
	 */
	private String name;
	
	/**是否是主表*/
	private Short isMain = IS_MAIN;
	
	/**
	 * 主表数据
	 */
	private List<DataEntity> dataEntityList;
	
	/**
	 * 子表数据
	 */
	private List<TableEntity> subTableEntityList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getIsMain() {
		return isMain;
	}

	public void setIsMain(Short isMain) {
		this.isMain = isMain;
	}	
	
	public List<DataEntity> getDataEntityList() {
		return dataEntityList;
	}

	public void setDataEntityList(List<DataEntity> dataEntityList) {
		this.dataEntityList = dataEntityList;
	}

	public List<TableEntity> getSubTableEntityList() {
		return subTableEntityList;
	}

	public void setSubTableEntityList(List<TableEntity> subTableEntityList) {
		this.subTableEntityList = subTableEntityList;
	}
}
