package org.displaytag.export;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 扩展导出的一些帮助类
 * @author zxh
 *
 */
public class ExportUtil {


	/**
	 * 匹配input、font、span和a 四个标签,如果表格还会有其他标签再增就可以了
	 * @param stringValue 去掉HTML标签
	 * @return
	 */
	public static String removeHtml(String stringValue){
		if(StringUtils.isEmpty(stringValue)) return "";
        //匹配input、font、span和a 四个标签,如果表格还会有其他标签再增就可以了
        Pattern p = Pattern.compile("</?[input|font|a|span][^>]*>", Pattern.CASE_INSENSITIVE); 
        Matcher m = p.matcher(stringValue); 
        return m.replaceAll("");
	}
}
