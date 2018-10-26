package com.hotent.core.excel.style;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

/**
 * 边框样式
 * @author zxh
 *
 */
public enum BorderStyle {
	/**
	 * 无边框
	 */
	NONE(HSSFCellStyle.BORDER_NONE),

	/**
	 * 细实线
	 */
	THIN(HSSFCellStyle.BORDER_THIN),

	/**
	 * 粗实线
	 */
	MEDIUM(HSSFCellStyle.BORDER_MEDIUM),

	/**
	  * 最粗的实线
	  */
	THICK(HSSFCellStyle.BORDER_THICK),
	
	/**
	 * 细虚线
	 */
	DASHED(HSSFCellStyle.BORDER_DASHED),

	/**
	  * 细点线
	  */
	HAIR(HSSFCellStyle.BORDER_HAIR),

	/**
	  * 双实线
	  */
	DOUBLE(HSSFCellStyle.BORDER_DOUBLE),

	/**
	  * 细密点线
	  */
	DOTTED(HSSFCellStyle.BORDER_DOTTED),

	/**
	  * 粗虚线
	  */
	MEDIUM_DASHED(HSSFCellStyle.BORDER_MEDIUM_DASHED),

	/**
	  * 虚线
	  */
	DASH_DOT(HSSFCellStyle.BORDER_DASH_DOT),

	/**
	  * 虚线-点，粗线
	  */
	MEDIUM_DASH_DOT(HSSFCellStyle.BORDER_MEDIUM_DASH_DOT),

	/**
	  * 虚线-点-点，细线
	  */
	DASH_DOT_DOT(HSSFCellStyle.BORDER_DASH_DOT_DOT),

	/**
	  * 虚线-点-点，粗线
	  */
	MEDIUM_DASH_DOT_DOT(HSSFCellStyle.BORDER_MEDIUM_DASH_DOT_DOT),

	/**
	  * 虚线-点，倾斜的粗线
	  */
	SLANTED_DASH_DOT(HSSFCellStyle.BORDER_SLANTED_DASH_DOT);

	private short borderType;

	private BorderStyle(short borderType) {
		this.borderType =  borderType;
	}

	public short getBorderType() {
		return borderType;
	}
}
