package com.hotent.platform.service.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hotent.core.util.AppConfigUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.system.SysPropertyService;

/**
 * <pre>
 * 对象功能:文件上传 service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2014年6月17日21:25:43
 * </pre>
 */
@Service
public class FileUploadService {
	private static final String FILE_PATH_PROPERTIES = "file.upload";

	@Resource
	private SysUserService sysUserService;

	/**
	 * 文件存盘
	 * 
	 * @param mf
	 * @param filePath
	 * @return 返回文件保存的相对路径
	 */
	public String saveFileToDisk(MultipartFile mf) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		String saveFilePath = null;
		// 保存的文件名
		String fileSaveName = this.generateFileName(mf.getOriginalFilename());
		// 保存目录
		String saveFileDirPath = this.getConfigDirPath() + File.separator
				+ this.getRelativeDirPath();
		// 保存绝对路径
		saveFilePath = this.mergeAbsolutePath(saveFileDirPath,fileSaveName);
		try {
			InputStream fileInputStream = mf.getInputStream();
			File saveFileDir = new File(saveFileDirPath);
			if (!saveFileDir.exists()) {
				saveFileDir.mkdirs();
			}
			File file = new File(saveFilePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			out = new BufferedOutputStream(new FileOutputStream(file));
			in = new BufferedInputStream(fileInputStream);
			IOUtils.copy(in, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 忽略异常
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		// 返回文件的相对路径
		return this.getRelativeDirPath() + File.separator + fileSaveName;
	}

	/**
	 * 获取文件相对路径保存目录，路径规则为：用户登录账号/年份/月份
	 * 
	 * 
	 * @return
	 */
	private String getRelativeDirPath() {
		StringBuffer dirPath = new StringBuffer();
		long userId = ContextUtil.getCurrentUserId(); // 获取当前用户的id
		SysUser appUser = null;
		if (userId > 0) {
			appUser = sysUserService.getById(userId);
		}
		Calendar c = Calendar.getInstance();
		dirPath.append(appUser.getAccount()).append(File.separator)
				.append(c.get(Calendar.YEAR)).append(File.separator)
				.append(c.get(Calendar.MONTH)+1);
		return dirPath.toString();
	}

	/**
	 * 获取文件配置目录
	 * 
	 * @return
	 */
	private String getConfigDirPath() {
		String filePath = SysPropertyService.getByAlias(FILE_PATH_PROPERTIES);
		if (StringUtil.isEmpty(filePath)) {
			filePath = AppUtil.getRealPath("/filemanage/temp");
		}
		return filePath;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @param filePath
	 */
	public void deleteFile(String fileRelativePath) {
		// 合并路径
		String filePath =  this.mergeAbsolutePath(this.getConfigDirPath(), fileRelativePath);
		File file = new File(filePath);
		if (file.exists()) {
			if (file.isFile()) {
				try {
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 合并成绝对路径
	 * @param configDirPath
	 * @param fileRelativePath
	 * @return
	 */
	private String mergeAbsolutePath(String configDirPath,String fileRelativePath){
		return configDirPath + File.separator + fileRelativePath;
	}
	/**
	 * 获取唯一文件名
	 * 
	 * @param fileName
	 * @return
	 */
	private String generateFileName(String fileName) {
		Date date = new Date();
		String[] subNames = fileName.split("\\.");
		if (subNames.length <= 1) {
			return "" + date.getTime();
		} else {
			return date.getTime()
					+ "."
					+ org.apache.commons.lang.StringUtils.substringAfterLast(
							fileName, ".");
		}
	}

	/**
	 * 获取文件
	 * 
	 * @param fileSavePath
	 * @return
	 */
	public InputStream getFile(String fileSavePath) {
		// 合并路径
		String filePath =  this.mergeAbsolutePath(this.getConfigDirPath(), fileSavePath);
		File file = new File(filePath);
		InputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return in;
	}

}
