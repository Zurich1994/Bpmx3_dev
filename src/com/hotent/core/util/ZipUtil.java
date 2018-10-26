package com.hotent.core.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.springframework.web.multipart.MultipartFile;

/**
 * 压缩解压缩文件 工具类
 * 
 * @author zxh
 * 
 */
public class ZipUtil {
	
	/**
	 * 压缩文件 （压缩后 删除原有的文件）
	 * 
	 * @param path
	 *            压缩文件\文件夹路径
	 */
	public static void zip(String path) {
		zip(path,true);
	}
	
	/**
	 * 压缩文件
	 * 
	 * @param path
	 *            压缩文件\文件夹路径
	 * @param isDelete
	 *            压缩后是否删除原文件\文件夹
	 */
	public static void zip(String path, Boolean isDelete) {
		ZipFile zipFile = null;
		try {
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			File file = new File(path);
			if (file.isDirectory()) {
				zipFile = new ZipFile(new File(path + ".zip"));
				zipFile.setFileNameCharset("utf-8");
				zipFile.addFolder(path, parameters);
			} else {
				zipFile = new ZipFile(new File(path.split(".")[0] + ".zip"));
				zipFile.setFileNameCharset("utf-8");
				zipFile.addFile(file, parameters);
			}
			
			if (isDelete)
				FileUtil.deleteDir(file);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 压缩文件夹(加密)
	 * 
	 * @param path
	 *            压缩文件\文件夹路径
	 * @param isDelete
	 *            压缩后是否删除原文件\文件夹
	 * @param password
	 *            加密密码
	 */
	public static void zipSetPass(String path, Boolean isDelete, String password) {
		ZipFile zipFile = null;
		try {
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			// 设置密码
			parameters.setEncryptFiles(true);
			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
			parameters.setPassword(password);
			File file = new File(path);
			if (file.isDirectory()) {
				zipFile = new ZipFile(new File(path + ".zip"));
				zipFile.setFileNameCharset("utf-8");
				zipFile.addFolder(path, parameters);
			} else {
				zipFile = new ZipFile(new File(path.split(".")[0] + ".zip"));
				zipFile.setFileNameCharset("utf-8");
				zipFile.addFile(file, parameters);
			}
			if (isDelete) {
				FileUtil.deleteDir(new File(path));
			}
		} catch (ZipException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 解压压缩文件
	 * 
	 * @param filePath
	 *            压缩文件路径
	 * @param toPath
	 *            解压到的文件夹路径
	 * @param password
	 *            密码 没有密码设置为""
	 */
	public static void unZip(String filePath, String toPath, String password) {
		try {
			unZipFile(new ZipFile(filePath), toPath, password);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}


	/**
	 *	解压文件(导入导出专用)
	 * @param fileLoad
	 * @param realFilePath 
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@SuppressWarnings("unchecked")
	public static String unZipFile(MultipartFile multipartFile, String toPath) throws Exception {
		//转存文件
		String originalFilename = multipartFile.getOriginalFilename();
		String destPath = toPath + originalFilename;

		createFilePath(destPath, originalFilename);
		File file = new File(destPath);
		if (file.exists())
			file.delete();
		multipartFile.transferTo(file);
		ZipFile zipFile = new ZipFile(file);
		zipFile.setFileNameCharset("GBK");       // 设置文件名编码，在GBK系统中需要设置   
		if (zipFile.isEncrypted())
			zipFile.setPassword("");
		if (!zipFile.isValidZipFile()) 
	            throw new ZipException("压缩文件不合法,可能被损坏.");  
		String fileDir ="";
		zipFile.extractAll(toPath);      // 将文件抽出到解压目录(解压)   
		
		List<FileHeader> fileHeaderList = zipFile.getFileHeaders();
		for (FileHeader fileHeader : fileHeaderList) {
			String dirName =fileHeader.getFileName();	
			if(fileHeader.isDirectory()){	
				if(dirName.endsWith("\\"))//如果是widow下的压缩的
					fileDir = dirName.substring(0, dirName.lastIndexOf("\\"));
				else if(dirName.endsWith("/")) //如果是linux下的压缩的
					fileDir = dirName.substring(0, dirName.lastIndexOf("/"));
				else//如果都不是有问题。用系统替换
					fileDir = dirName.substring(0, dirName.lastIndexOf(File.separator));
			}
		}
		//删除解压文件
		FileUtil.deleteDir(file);
		//返回文件的路径
		return  fileDir;
	}
	
	
	/**
	 * 解压压缩文件
	 * @param multipartFile
	 * @param toPath
	 * @param password
	 */
	public static void unZipFile(MultipartFile multipartFile, String toPath,
			String password) {
		String originalFilename = multipartFile.getOriginalFilename();
		String destPath = toPath + originalFilename;
		try {
			createFilePath(destPath, originalFilename);
			File file = new File(destPath);
			if (file.exists())
				file.delete();
			multipartFile.transferTo(file);
			unZipFile(new ZipFile(file), toPath, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解压压缩文件
	 * @param zipFile
	 * @param toPath
	 * @param password
	 */
	public static void unZipFile(ZipFile zipFile, String toPath, String password) {
		try {
			if (zipFile.isEncrypted())
				zipFile.setPassword(password);
			List<?> fileHeaderList = zipFile.getFileHeaders();
			for (Object o : fileHeaderList) {
				FileHeader fileHeader = (FileHeader) o;
				zipFile.extractFile(fileHeader, toPath);
			}
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建文件目录
	 * 
	 * @param tempPath
	 * @param fileName
	 *            文件名称
	 * @return 文件的完整目录
	 */
	public static String createFilePath(String tempPath, String fileName) {
		File file = new File(tempPath);
		// 文件夹不存在创建
		if (!file.exists())
			file.mkdirs();
		return file.getPath() + File.separator + fileName;
	}
}
