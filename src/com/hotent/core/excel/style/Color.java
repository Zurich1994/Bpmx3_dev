package com.hotent.core.excel.style;

import org.apache.poi.hssf.util.HSSFColor;

/**
 * 颜色
 * @author zxh
 */
public enum Color {
	/**
	 * 默认颜色
	 */
	AUTOMATIC(HSSFColor.AUTOMATIC.index),
	
	/**
	 * 浅绿色
	 */
	AQUA(HSSFColor.AQUA.index), 
	
	/**
	 * 黑色
	 */
	BLACK(HSSFColor.BLACK.index), 
	
	/**
	 * 蓝色
	 */
	BLUE(HSSFColor.BLUE.index), 
	
	/**
	 * 蓝灰色
	 */
	BLUE_GREY(HSSFColor.BLUE_GREY.index), 
	
	/**
	 * 鲜绿色
	 */
	BRIGHT_GREEN(HSSFColor.BRIGHT_GREEN.index), 
	
	/**
	 * 棕色
	 */
	BROWN(HSSFColor.BROWN.index), 
	
	/**
	 * 珊瑚红
	 */
	CORAL(HSSFColor.CORAL.index), 
	
	/**
	 * 浅蓝色,矢车菊蓝
	 */
	CORNFLOWER_BLUE(HSSFColor.CORNFLOWER_BLUE.index), 
	
	/**
	 * 深蓝色
	 */
	DARK_BLUE(HSSFColor.DARK_BLUE.index), 
	
	/**
	 * 深绿色
	 */
	DARK_GREEN(HSSFColor.DARK_GREEN.index), 
	
	/**
	 * 深红色
	 */
	DARK_RED(HSSFColor.DARK_RED.index), 
	
	/**
	 * 深青色
	 */
	DARK_TEAL(HSSFColor.DARK_TEAL.index), 
	
	/**
	 * 深黄色
	 */
	DARK_YELLOW(HSSFColor.DARK_YELLOW.index), 
	
	/**
	 * 金色
	 */
	GOLD(HSSFColor.GOLD.index), 
	
	/**
	 * 绿色
	 */
	GREEN(HSSFColor.GREEN.index), 
	
	/**
	 * 25%浓度灰色
	 */
	GREY_25_PERCENT(HSSFColor.GREY_25_PERCENT.index), 
	
	/**
	 * 40%浓度灰色
	 */
	GREY_40_PERCENT(HSSFColor.GREY_40_PERCENT.index), 
	
	/**
	 * 50%浓度灰色
	 */
	GREY_50_PERCENT(HSSFColor.GREY_50_PERCENT.index), 
	
	/**
	 * 80%浓度灰色
	 */
	GREY_80_PERCENT(HSSFColor.GREY_80_PERCENT.index), 
	
	/**
	 * 靛蓝色
	 */
	INDIGO(HSSFColor.INDIGO.index), 
	
	/**
	 * 浅紫色
	 */
	LAVENDER(HSSFColor.LAVENDER.index), 
	
	/**
	 * 浅柠檬色
	 */
	LEMON_CHIFFON(HSSFColor.LEMON_CHIFFON.index), 
	
	/**
	 * 浅蓝色
	 */
	LIGHT_BLUE(HSSFColor.LIGHT_BLUE.index), 
	
	/**
	 * 浅蓝色，浅矢车菊蓝
	 */
	LIGHT_CORNFLOWER_BLUE(HSSFColor.LIGHT_CORNFLOWER_BLUE.index), 
	
	/**
	 * 浅绿色
	 */
	LIGHT_GREEN(HSSFColor.LIGHT_GREEN.index), 
	
	/**
	 * 浅橙色
	 */
	LIGHT_ORANGE(HSSFColor.LIGHT_ORANGE.index), 
	
	/**
	 * 浅绿蓝
	 */
	LIGHT_TURQUOISE(HSSFColor.LIGHT_TURQUOISE.index), 
	
	/**
	 * 浅黄色
	 */
	LIGHT_YELLOW(HSSFColor.LIGHT_YELLOW.index), 
	
	/**
	 * 绿黄，亮绿色
	 */
	LIME(HSSFColor.LIME.index), 
	
	/**
	 *栗色
	 */
	MAROON(HSSFColor.MAROON.index), 
	
	/**
	 * 橄榄绿
	 */
	OLIVE_GREEN(HSSFColor.OLIVE_GREEN.index), 
	
	/**
	 * 橙色
	 */
	ORANGE(HSSFColor.ORANGE.index), 
	
	/**
	 * 淡紫色
	 */
	ORCHID(HSSFColor.ORCHID.index), 
	
	/**
	 * 淡蓝色，粉青
	 */
	PALE_BLUE(HSSFColor.PALE_BLUE.index),
	
	/**
	 * 粉红色
	 */
	PINK(HSSFColor.PINK.index),
	
	/**
	 * 紫红色
	 */
	PLUM(HSSFColor.PLUM.index),
	
	/**
	 * 红色
	 */
	RED(HSSFColor.RED.index),
	
	/**
	 * 玫瑰红
	 */
	ROSE(HSSFColor.ROSE.index),
	
	/**
	 * 皇家蓝，宝蓝色
	 */
	ROYAL_BLUE(HSSFColor.ROYAL_BLUE.index),
	
	/**
	 * 海藻绿
	 */
	SEA_GREEN(HSSFColor.SEA_GREEN.index),
	
	/**
	 * 天蓝色
	 */
	SKY_BLUE(HSSFColor.SKY_BLUE.index),
	
	/**
	 * 棕褐色
	 */
	TAN(HSSFColor.TAN.index),
	
	/**
	 * 青色
	 */
	TEAL(HSSFColor.TEAL.index),
	
	/**
	 * 宝石绿，蓝绿色
	 */
	TURQUOISE(HSSFColor.TURQUOISE.index),
	
	/**
	 * 紫色，紫罗兰色
	 */
	VIOLET(HSSFColor.VIOLET.index),
	
	/**
	 * 白色
	 */
	WHITE(HSSFColor.WHITE.index),
	
	/**
	 * 黄色
	 */
	YELLOW(HSSFColor.YELLOW.index);
	
	private short index;

	private Color(short index){
		this.index = index;
	}

	public short getIndex() {
		return index;
	}
	
	/**
	 * 根据值返回对应的枚举值
	 * @param weight
	 * @return
	 */
	public static Color instance(short index){
		for(Color e : Color.values()){
			if(e.getIndex() == index){
				return e;
			}
		}
		return Color.AUTOMATIC;
	}
}
