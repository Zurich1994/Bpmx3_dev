package com.hotent.core.excel.style.font;

import org.apache.poi.hssf.usermodel.HSSFFont;

/**
 * 字体样式属性，字符集
 *@author zxh
 */
public enum CharSet {
	ANSI(HSSFFont.ANSI_CHARSET), 
	
	DEFAULT(HSSFFont.DEFAULT_CHARSET), 
	
	SYMBOL(HSSFFont.SYMBOL_CHARSET);

	private byte charset;

	private CharSet(byte charset){
		this.charset = charset;
	}

	public byte getCharset() {
		return charset;
	}
	
	/**
	 * 根据值返回对应的枚举值
	 * @param weight
	 * @return
	 */
	public static CharSet instance(byte charset){
		return instance((int)charset);
	}
	
	public static CharSet instance(int charset){
		for(CharSet e : CharSet.values()){
			if(e.getCharset() == charset){
				return e;
			}
		}
		return CharSet.DEFAULT;
	}

}
