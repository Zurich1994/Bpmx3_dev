package com.hotent.platform.xml.util;

import java.io.File;

import com.hotent.core.util.FileUtil;




/**
 * 导入xml删除文件
 * @author zxh
 *
 */
public class FileXmlUtil {
	
	/**
	 * 写Xml文件
	 * @param fileName
	 * @param content
	 * @param path
	 * @return
	 */
	public static  String writeXmlFile(String fileName,String content,String path){
		String filePath = createFilePath(path , fileName);
		FileUtil.writeFile(filePath,content);
		return filePath;
	}

	/**
	 * 创建文件目录
	 * @param tempPath
	 * @param fileName 文件名称
	 * @return 文件的完整目录
	 */
	public static String createFilePath(String tempPath, String fileName){
		File file = new File(tempPath);
		//文件夹不存在创建
		if(!file.exists())
			file.mkdirs();
		return file.getPath() + File.separator+ fileName;
	}
	
	

}
