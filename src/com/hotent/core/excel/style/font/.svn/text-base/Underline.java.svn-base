package com.hotent.core.excel.style.font;

import org.apache.poi.hssf.usermodel.HSSFFont;

/**
 * 字体样式属性，字体下横线样式
 *@author zxh
 */
public enum Underline {
	/**
	 * 默认没下划线
	 */
	NONE(HSSFFont.U_NONE), 
	
	/**
	 * 单下横线
	 */
	SINGLE(HSSFFont.U_SINGLE), 
	
	/**
	 * 双下横线
	 */
	DOUBLE(HSSFFont.U_DOUBLE), 
	
	/**
	 * 会计用单下横线
	 */
	SINGLE_ACCOUNTING(HSSFFont.U_SINGLE_ACCOUNTING), 
	
	/**
	 * 会计用双下横线
	 */
	DOUBLE_ACCOUNTING(HSSFFont.U_DOUBLE_ACCOUNTING);
	
	private byte line;

	private Underline(byte line){
		this.line = line;
	}

	public byte getLine() {
		return line;
	}
	
	/**
	 * 根据值返回对应的枚举值
	 * @param weight
	 * @return
	 */
	public static Underline instance(byte line){
		for(Underline e : Underline.values()){
			if(e.getLine() == line){
				return e;
			}
		}
		return Underline.NONE;
	}
	
}
