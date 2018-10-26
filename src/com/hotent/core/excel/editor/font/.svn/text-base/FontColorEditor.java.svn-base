package com.hotent.core.excel.editor.font;

import com.hotent.core.excel.editor.IFontEditor;
import com.hotent.core.excel.style.Color;
import com.hotent.core.excel.style.font.Font;

/**
 * 实现一些常用的字体<br/>
 * 该类用于设置字体颜色
 * @author zxh
 *
 */
public class FontColorEditor implements IFontEditor {

	private Color color = Color.BLACK;

	public void updateFont(Font font) {
		font.color(color);
	}
	
	/**
	 * 获取字体颜色
	 * @return 颜色
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * 设置字体颜色
	 * @param color 颜色
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}
