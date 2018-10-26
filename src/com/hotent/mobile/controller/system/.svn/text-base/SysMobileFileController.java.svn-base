package com.hotent.mobile.controller.system;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.mobile.controller.base.BaseMobileController;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.service.system.SysFileService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.util.ServiceUtil;

/**
 * 文件处理
 * @author zxh
 *
 */
@Controller
@RequestMapping("/mobile/system/file/")
public class SysMobileFileController extends BaseMobileController{

	
	@Resource
	private SysFileService sysFileService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private GlobalTypeService globalTypeService;
	
	/**
	 * 如果文件存在则更新，如果文件不存在则新建文件。
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("saveFile")
	@Action(description = "保存文件",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>文件" +
					"<#list sysFiles as item>" +
						"【${item.fileName}】" +
					"</#list>"
	)
	@ResponseBody
	public void saveFile(MultipartHttpServletRequest request,HttpServletResponse response) throws Exception {
		try{
			long userId =  ContextUtil.getCurrentUserId(); // 获取当前用户的id
			long typeId =  RequestUtil.getLong(request,"typeId");
			long fileId=RequestUtil.getLong(request, "fileId");
			SysUser appUser = null;
			if(userId>0){			
				appUser = sysUserService.getById(userId);
			}
			// 获取附件类型
			GlobalType globalType = null;
			if(typeId>0){
				globalType = globalTypeService.getById(typeId);
			}
			Map<String, MultipartFile> files = request.getFileMap();
			Iterator<MultipartFile> it = files.values().iterator();
			
			while (it.hasNext()) {
				boolean isAdd=true;
				SysFile sysFile = null;
				if(fileId==0){
					fileId=UniqueIdUtil.genId();
					sysFile = new SysFile();
					sysFile.setFileId(fileId);
				}
				else{
					sysFile=sysFileService.getById(fileId);
					isAdd=false;
				}
				
				//添加系统日志信息 -B
				try {
					List<SysFile> sysFiles;
					if(SysAuditThreadLocalHolder.getParamerter("sysFiles")==null){
						sysFiles = new ArrayList<SysFile>();
						SysAuditThreadLocalHolder.putParamerter("sysFiles", sysFiles);
					}else{
						sysFiles =(List<SysFile>) SysAuditThreadLocalHolder.getParamerter("sysFiles");
					}
					sysFiles.add(sysFile);
					SysAuditThreadLocalHolder.putParamerter("isAdd", fileId==0);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
				//添加系统日志信息 -E
				
				MultipartFile f = it.next();
				String oriFileName = f.getOriginalFilename();
			    String extName=FileUtil.getFileExt(oriFileName);
			    String fileName= fileId + "." + extName;
			    String filePath= StringUtil.trimSufffix(configproperties.get("file.upload").toString(),File.separator) ;
			    filePath +=File.separator + SysFile.ATTACHMENT_DIRECTORY ;
				//开始写入物理文件
				filePath = createFilePath(filePath , fileName);
			    FileUtil.writeByte(filePath, f.getBytes()) ;
			    
			    //附件名称
			    sysFile.setFileName(oriFileName.substring(0, oriFileName.lastIndexOf('.')));
			    
			    Calendar cal = Calendar.getInstance();
			    Integer year = cal.get(Calendar.YEAR);
			    Integer month = cal.get(Calendar.MONTH) + 1;
			    Integer day = cal.get(Calendar.DATE) ;
			    //附件路径
			    sysFile.setFilePath(SysFile.ATTACHMENT_DIRECTORY + File.separator + year + File.separator + month + File.separator + day +File.separator + fileName);
			    //附件类型
			    if(globalType != null){		
			    	sysFile.setTypeId(globalType.getTypeId());
			    	sysFile.setFileType(globalType.getTypeName());
			    } else {
			    	sysFile.setTypeId(0L);
			    	sysFile.setFileType("-");
			    }
			    // 上传时间
			    sysFile.setCreatetime(new java.util.Date());
			    // 扩展名
			    sysFile.setExt(extName);
			    //字节总数
			    sysFile.setTotalBytes(f.getSize());
			    // 说明
			    sysFile.setNote(FileUtil.getSize(f.getSize()));
			    // 当前用户的信息
			    if(appUser != null){
			    	sysFile.setCreatorId(appUser.getUserId());
			    	sysFile.setCreator(appUser.getUsername());
			    } else {
			    	sysFile.setCreator(SysFile.FILE_UPLOAD_UNKNOWN);
			    }
			    //总的字节数
			    sysFile.setDelFlag(SysFile.FILE_NOT_DEL);
			    if(isAdd){
			    	sysFileService.add(sysFile);
			    }
			    else{
			    	sysFileService.update(sysFile);
			    }
			    Map<String,Object> map = new HashMap<String,Object>();
			    map.put("file", sysFile);
			    // end 向数据库中添加数据
				this.returnCallbackSuccessData(request, response, map);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
			this.returnCallbackErrorData(request, response, e.getMessage());
		}
	} 
	
	
	

	/**
	 * 创建文件目录
	 * @param tempPath
	 * @param fileName 文件名称
	 * @return 文件的完整目录
	 */
	private String createFilePath(String tempPath, String fileName){
		File one = new File(tempPath);
		Calendar cal = Calendar.getInstance(); 
		Integer year = cal.get(Calendar.YEAR); // 当前年份
		Integer month = cal.get(Calendar.MONTH) + 1; // 当前月份
		Integer day=cal.get(Calendar.DATE);
		one = new File(tempPath + File.separator + year + File.separator + month +File.separator + day);
		if(!one.exists()){
			one.mkdirs();
		}
		return one.getPath() + File.separator+ fileName;
	}
	
	/**
	 * 取得附件明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看附件明细",ownermodel=SysAuditModelType.SYSTEM_SETTING,exectype="管理日志")
	@ResponseBody
	public void get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "fileId");
		SysFile sysFile = sysFileService.getById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("file", sysFile);
		this.returnCallbackSuccessData(request, response, map);
	}
}
