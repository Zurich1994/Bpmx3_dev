package com.hotent.core.excel.style.font;

import org.apache.poi.hssf.usermodel.HSSFFont;

/**
 * 字体样式属性，字体加粗
 * @author zxh
 */
public enum BoldWeight {
	/**
	 * 正常，不加粗
	 */
	NORMAL(HSSFFont.BOLDWEIGHT_NORMAL), 
	
	/**
	 * 加粗，weight等于700
	 */
	BOLD(HSSFFont.BOLDWEIGHT_BOLD);

	private short weight;

	private BoldWeight(short weight) {
		this.weight = weight;
	}
	
	/**
	 * 返回加粗的等级
	 * @return
	 */
	public short getWeight() {
		return weight;
	}

	/**
	 * 根据值返回对应的枚举值
	 * @param weight
	 * @return
	 */
	public static BoldWeight instance(short weight){
		for(BoldWeight e : BoldWeight.values()){
			if(e.getWeight() == weight){
				return e;
			}
		}
		return BoldWeight.NORMAL;
	}
}
