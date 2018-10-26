package com.hotent.core.excel.style.font;

import org.apache.poi.hssf.usermodel.HSSFFont;

import com.hotent.core.excel.style.Color;

/**
 * 封装字体，可以设置或获取字体的属性
 *@author zxh
 */
public class Font {
	private HSSFFont font;

	public Font(HSSFFont font) {
		this.font = font;
	}

	/**
	 * 设置粗体
	 * @param boldweight
	 * @return
	 */
	public Font boldweight(BoldWeight boldweight) {
		font.setBoldweight(boldweight.getWeight());
		return this;
	}
	
	/**
	 * 获取粗体属性
	 * @return
	 */
	public BoldWeight boldweight(){
		return BoldWeight.instance(font.getBoldweight());
	}

	/**
	 * 设置字符集
	 * @param charset
	 * @return
	 */
	public Font charSet(CharSet charset) {
		font.setCharSet(charset.getCharset());
		return this;
	}
	
	/**
	 * 获取字符集属性
	 * @return
	 */
	public CharSet charSet(){
		return CharSet.instance(font.getCharSet());
	}

	/**
	 * 设置字体颜色
	 * @param color
	 * @return
	 */
	public Font color(Color color) {
		if(color.equals(Color.AUTOMATIC)){
			font.setColor(HSSFFont.COLOR_NORMAL);
		}else{
			font.setColor(color.getIndex());
		}
		return this;
	}
	
	/**
	 * 获取字体颜色
	 * @return
	 */
	public Color color(){
		return Color.instance(font.getColor());
	}

	public Font fontHeight(int height) {
		font.setFontHeight((short)height);
		return this;
	}
	
	public short fontHeight(){
		return font.getFontHeight();
	}

	public Font fontHeightInPoints(int height) {
		font.setFontHeightInPoints((short)height);
		return this;
	}
	
	/**
	 * 
	 * @return
	 */
	public short fontHeightInPoints(){
		return font.getFontHeightInPoints();
	}

	/**
	 * 设置字体
	 * @param name 字体名称，如“黑体”
	 * @return
	 */
	public Font fontName(String name) {
		font.setFontName(name);
		return this;
	}
	
	/**
	 * 获取字体名称
	 * @return
	 */
	public String fontName(){
		return font.getFontName();
	}

	/**
	 * 设置斜体
	 * @param italic
	 * @return
	 */
	public Font italic(boolean italic) {
		font.setItalic(italic);
		return this;
	}
	
	/**
	 * 获取是否斜体
	 * @return
	 */
	public boolean italic(){
		return font.getItalic();
	}

	/**
	 * 设置删除线
	 * @param strikeout
	 * @return
	 */
	public Font strikeout(boolean strikeout) {
		font.setStrikeout(strikeout);
		return this;
	}

	/**
	 * 获取删除线
	 * @return
	 */
	public boolean strikeout(){
		return font.getStrikeout();
	}
	
	/**
	 * 设置上标、下标
	 * @param offset
	 * @return
	 */
	public Font typeOffset(TypeOffset offset) {
		font.setTypeOffset(offset.getOffset());
		return this;
	}
	
	/**
	 * 获取上标、下标属性
	 * @return
	 */
	public TypeOffset typeOffset(){
		return TypeOffset.instance(font.getTypeOffset());
	}

	/**
	 * 设置下横线
	 * @param underline
	 * @return
	 */
	public Font underline(Underline underline) {
		font.setUnderline(underline.getLine());
		return this;
	}
	
	/**
	 * 获取下横线属性
	 * @return
	 */
	public Underline underline(){
		return Underline.instance(font.getUnderline());
	}
	
}
