package com.hotent.core.excel;

import com.hotent.core.excel.style.Align;
import com.hotent.core.excel.style.BorderStyle;
import com.hotent.core.excel.style.Color;
import com.hotent.core.excel.style.FillPattern;
import com.hotent.core.excel.style.VAlign;

/**
 * 默认样式。当需要改变Excel文件的默认样式时，使用该类的一个对象来初始化Excel
 * 
 * @author zxh
 * 
 */
public class DefaultExcelStyle {
	/**
	 * 字体大小
	 */
	private short fontSize = 12;

	private String fontName = "宋体";

	private Color backgroundColor = Color.AUTOMATIC;//HSSFColor.AUTOMATIC.index;

	private FillPattern fillPattern = FillPattern.NO_FILL;//HSSFCellStyle.NO_FILL;

	private Align align = Align.GENERAL;//HSSFCellStyle.ALIGN_GENERAL;

	private VAlign vAlign = VAlign.CENTER;//HSSFCellStyle.VERTICAL_CENTER;

	private Color fontColor = Color.AUTOMATIC;//HSSFFont.COLOR_NORMAL;

	private String defaultDatePattern = "yyyy-mm-dd hh:mm：ss";
	
	private Color borderColor = Color.AUTOMATIC;
	
	private BorderStyle borderStyle = BorderStyle.NONE;
	
	/**
	 * 边框颜色
	 * @return
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * 边框颜色
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * 边框样式
	 * @return
	 */
	public BorderStyle getBorderStyle() {
		return borderStyle;
	}

	/**
	 * 边框样式
	 * @param borderStyle
	 */
	public void setBorderStyle(BorderStyle borderStyle) {
		this.borderStyle = borderStyle;
	}

	/**
	 * 字体大小，默认12
	 * @param fontSize
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = (short)fontSize;
	}

	/**
	 * 字体大小，默认12
	 * @param fontSize
	 */
	public short getFontSize() {
		return fontSize;
	}

	/**
	 * 字体名称，默认“宋体”
	 * @param fontName
	 */
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	/**
	 * 字体名称，默认“宋体”
	 */
	public String getFontName() {
		return fontName;
	}

	/**
	 * 背景色，默认HSSFColor.AUTOMATIC.index，即无填充色
	 * @param backgroundColor 颜色，例如HSSFColor.RED.index
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * 背景色，默认HSSFColor.AUTOMATIC.index，即无色
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * 背景色填充样式，默认HSSFCellStyle.NO_FILL，即无填充
	 * 要设置填充色必须把该值设置为NO_FILL以外的值
	 * @param fillPattern 
	 */
	public void setFillPattern(FillPattern fillPattern) {
		this.fillPattern = fillPattern;
	}

	/**
	 * 背景色填充样式，默认HSSFCellStyle.NO_FILL，即无填充
	 * 要设置填充色必须把该值设置为NO_FILL以外的值
	 */
	public FillPattern getFillPattern() {
		return fillPattern;
	}

	/**
	 * 水平对齐方式，默认是HSSFCellStyle.ALIGN_GENERAL
	 * @param align 对齐方式，例如HSSFCellStyle.ALIGN_CENTER。可选值：ALIGN_GENERAL, ALIGN_LEFT, ALIGN_CENTER, ALIGN_RIGHT, ALIGN_FILL, ALIGN_JUSTIFY, ALIGN_CENTER_SELECTION
	 */
	public void setAlign(Align align) {
		this.align = align;
	}

	/**
	 * 水平对齐方式，默认是HSSFCellStyle.ALIGN_GENERAL
	 */
	public Align getAlign() {
		return align;
	}

	/**
	 * 垂直对齐方式，默认HSSFCellStyle.VERTICAL_CENTER，即居中
	 * @param vAlign 对齐方式，例如HSSFCellStyle.VERTICAL_TOP。可选值：VERTICAL_TOP, VERTICAL_CENTER, VERTICAL_BOTTOM, VERTICAL_JUSTIFY
	 */
	public void setVAlign(VAlign vAlign) {
		this.vAlign = vAlign;
	}

	/**
	 * 垂直对齐方式，默认HSSFCellStyle.VERTICAL_CENTER，即居中
	 * @param vAlign
	 */
	public VAlign getVAlign() {
		return vAlign;
	}

	/**
	 * 字体颜色，默认是HSSFFont.COLOR_NORMAL
	 * @param fontColor 颜色，例如HSSFColor.RED.index
	 */
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	/**
	 * 字体颜色，默认是HSSFFont.COLOR_NORMAL
	 */
	public Color getFontColor() {
		return fontColor;
	}
	
	/**
	 * 设置默认的日期格式化模式，默认是yyyy-mm-dd hh:mm：ss，请参考excel的日期格式
	 * @param defaultDatePattern
	 */
	public void setDefaultDatePattern(String defaultDatePattern) {
		this.defaultDatePattern = defaultDatePattern;
	}

	/**
	 * 返回默认的日期格式化模式，默认是yyyy-mm-dd hh:mm：ss，请参考excel的日期格式
	 * @return
	 */
	public String getDefaultDatePattern() {
		return defaultDatePattern;
	}

}
