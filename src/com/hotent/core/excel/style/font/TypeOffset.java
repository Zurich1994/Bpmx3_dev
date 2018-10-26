package com.hotent.core.excel.style.font;

import org.apache.poi.hssf.usermodel.HSSFFont;

/**
 * 字体样式属性，上标，下标
 * @author zxh
 */
public enum TypeOffset {
	/**
	 * 正常
	 */
	NONE(HSSFFont.SS_NONE), 
	/**
	 * 上标
	 */
	SUPER(HSSFFont.SS_SUPER),
	/**
	 * 下标
	 */
	SUB(HSSFFont.SS_SUB);

	private short offset;

	private TypeOffset(short offset) {
		this.offset = offset;
	}

	public short getOffset() {
		return offset;
	}

	/**
	 * 根据值返回对应的枚举值
	 * @param weight
	 * @return
	 */
	public static TypeOffset instance(short offset){
		for(TypeOffset e : TypeOffset.values()){
			if(e.getOffset() == offset){
				return e;
			}
		}
		return TypeOffset.NONE;
	}
}
