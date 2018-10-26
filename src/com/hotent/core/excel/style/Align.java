package com.hotent.core.excel.style;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

/**
 * 水平对齐方式
 * @author zxh
 *
 */
public enum Align {	
	/**
	 * 
	 */
	GENERAL(HSSFCellStyle.ALIGN_GENERAL), 
	
	/**
	 * 
	 */
	LEFT(HSSFCellStyle.ALIGN_LEFT),
	
	/**
	 * 
	 */
	CENTER(HSSFCellStyle.ALIGN_CENTER),
	
	/**
	 * 
	 */
	RIGHT(HSSFCellStyle.ALIGN_RIGHT),
	
	/**
	 * 
	 */
	FILL(HSSFCellStyle.ALIGN_FILL),
	
	/**
	 * 
	 */
	JUSTIFY(HSSFCellStyle.ALIGN_JUSTIFY),
	
	/**
	 * 
	 */
	CENTER_SELECTION(HSSFCellStyle.ALIGN_CENTER_SELECTION);
	
	private short alignment;

	private Align(short alignment) {
		this.alignment = alignment;
	}

	public short getAlignment() {
		return alignment;
	}
}
