package com.hotent.BusinessCollectCot.ExcelOperate.lc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class FileOperate {
	//public  String createPath(String fileFolder){
		//String projectName = "CloudMaintance";
		//String tomcatPath = Consts.UPLODA_PATH;
		//System.out.println("----"+tomcatPath+"-----------");
		//String tempPath = tomcatPath.replace("bin", "webapps");
		//tempPath += "\\" + projectName;
		//String path = tomcatPath + "\\" + fileFolder;
		//return path;
	//}
	
	public static boolean isFileExist(String filePath){
		File file = new File(filePath);
		boolean flag = file.exists();
		return flag;
	}
	
	public static void createFolder(String filePath){
		boolean flag = new FileOperate().isFileExist(filePath);
		if(flag){
			System.out.println("文件夹已存在");
		}else{
			File file = new File(filePath);
			file.mkdir();
			System.out.println("成功创建文件夹");
		}
	}
	
	public static void upLoad(String filePath,File file,String fileFileName) throws IOException{
		//String upLoadPath = new FileOperate().createPath(tempPath);
		//System.out.println("upLoadPath:"+upLoadPath);
		
		//new FileOperate().createFolder(upLoadPath);
		InputStream is = new FileInputStream(file);
		File toFile = new File(filePath,fileFileName);
		OutputStream os = new FileOutputStream(toFile);
		byte[] buffer = new byte[1024];
		int length = 0;
		while((length = is.read(buffer))  > 0){
			os.write(buffer, 0, length);
		}
		is.close();
		os.close();
	}
	
	public static void main(String [] args){
		//String path = new FileOperate().createPath("upload");
		//System.out.println(path);
		//createFolder(path);
		String filePath = "C:\\Myeclipse8.5Workspace\\bpmx3_dev\\WebRoot\\excel";
		boolean flag = isFileExist("C:\\Myeclipse8.5Workspace\\bpmx3_dev\\WebRoot\\excel");
		System.out.println(flag);
		createFolder("C:\\Myeclipse8.5Workspace\\bpmx3_dev\\WebRoot\\excel");
		File file = new File("c:\\excel.txt");
		try {
			upLoad(filePath,file,"yuda");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
