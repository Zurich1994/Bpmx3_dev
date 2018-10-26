package com.hotent.platform.controller.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.ImageUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.service.system.SysFileService;
import com.hotent.platform.service.system.SysTypeKeyService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.util.ServiceUtil;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.platform.service.system.SysPropertyService;

/**
 * 对象功能:附件 控制器类 开发公司:广州宏天软件有限公司 开发人员:csx 创建时间:2011-11-26 18:19:16
 */
@Controller
@RequestMapping("/platform/system/sysFile/")
@Action(ownermodel = SysAuditModelType.SYSTEM_SETTING)
public class SysFileController extends BaseController {
	private Log logger = LogFactory.getLog(SysFileController.class);

	@Resource
	private SysFileService sysFileService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private GlobalTypeService globalTypeService;
	@Resource
	private SysTypeKeyService sysTypeKeyService;
	//附件保存路径
	private String attachPath = ServiceUtil.getBasePath().replace("/", "\\");
	//附件保存类型
	private String saveType = ServiceUtil.getSaveType();
	//xinjia
	@Resource
	private SysPropertyService sysPropertyService;
	//xinjia

	/**
	 * 取得附件分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看附件分页列表", exectype = "管理日志", detail = "查看附件分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long typeId = RequestUtil.getLong(request, "typeId");
		QueryFilter filter = new QueryFilter(request, "sysFileItem");
		if (typeId != 0) {
			filter.addFilter("typeId", typeId);
		}
		
		List<SysFile> list = sysFileService.getAll(filter);
		return getAutoView().addObject("sysFileList", list);
	}

	/**
	 * 删除附件
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除附件",
			execOrder = ActionExecOrder.BEFORE, 
			exectype = "管理日志", 
			detail = "删除系统附件" + "<#list StringUtils.split(fileId,\",\") as item>"
			+ "<#assign entity=sysFileService.getById(Long.valueOf(item))/>" 
			+ "【${entity.fileName}】" + "</#list>")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultMessage message = null;
		String returnUrl = RequestUtil.getPrePage(request);
		Long[] lAryId = RequestUtil.getLongAryByStr(request, "fileId");
		//xinjia
		String attachPath = ServiceUtil.getBasePath().replace("/", "\\");
		//xinjia
		try {
			if(!saveType.contains("database")){
				for (Long id : lAryId) {
					SysFile sysFile = sysFileService.getById(id);
					String filePath = sysFile.getFilePath();
					if (StringUtil.isEmpty(attachPath)) {
						filePath = AppUtil.getRealPath(filePath);
					}
					// 删除文件				
					FileUtil.deleteFile(attachPath + File.separator + filePath);
				}
			}
			// 删除数据库中数据 （包括文件在数据库的二进制流）
			sysFileService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除附件成功");
		} catch (Exception e) {
			message = new ResultMessage(ResultMessage.Fail, "删除附件失败");
		}
		addMessage(message, request);
		response.sendRedirect(returnUrl);
	}

	/**
	 * 删除附件。
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("delByFileId")
	@Action(description = "删除附件", execOrder = ActionExecOrder.BEFORE, exectype = "管理日志", 
			detail = "删除系统附件" + "<#list StringUtils.split(ids,\",\") as item>"
			+ "<#assign entity=sysFileService.getById(Long.valueOf(item))/>" + "【${entity.fileName}】" + "</#list>")
	public void delByFileId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//xinjia
		String attachPath = ServiceUtil.getBasePath().replace("/", "\\");
		//xinjia
		PrintWriter out = response.getWriter();
		Long[] lAryId = RequestUtil.getLongAryByStr(request, "ids");
		try {
			if(!saveType.contains("database")){              //保存在服务器时才做文件删除
				for (Long id : lAryId) {
					SysFile sysFile = sysFileService.getById(id);
					String filePath = sysFile.getFilePath();
					filePath = filePath.replace('\\', '/');
					// 删除文件
					if (StringUtil.isEmpty(attachPath)) {
						filePath = AppUtil.getRealPath(filePath);
					}
					FileUtil.deleteFile(attachPath + File.separator + filePath);
				}
			}
			// 删除数据库中数据（包括文件在数据库的二进制流）
			sysFileService.delByIds(lAryId);
			out.print("{\"success\":\"true\"}");
		} catch (Exception e) {
			out.print("{\"success\":\"false\"}");
		}

	}

	@RequestMapping("edit")
	@Action(description = "编辑附件", exectype = "管理日志",
			detail = "<#if isAdd>添加附件<#else>编辑附件" +
			"<#assign entity=sysFileService.getById(Long.valueOf(fileId))/>"+
			"【${entity.fileName}】</#if>")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long fileId = RequestUtil.getLong(request, "fileId");
		String returnUrl = RequestUtil.getPrePage(request);
		SysFile sysFile = null;

		boolean isadd=true;
		if (fileId != 0) {
			sysFile = sysFileService.getById(fileId);
			isadd=false;
		} else {
			sysFile = new SysFile();
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		return getAutoView().addObject("sysFile", sysFile).addObject("returnUrl", returnUrl);
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
	@Action(description = "查看附件明细", exectype = "管理日志", 
			detail = "查看附件" + 
			"<#assign entity=sysFileService.getById(Long.valueOf(fileId))/>"
			+ "【${entity.fileName}】" + "的明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "fileId");
		long canReturn=RequestUtil.getLong(request, "canReturn",0);
		SysFile sysFile = sysFileService.getById(id);
		//返回的Url地址
		String returnUrl = RequestUtil.getPrePage(request);
		return getAutoView().addObject("sysFile", sysFile).addObject("canReturn",canReturn).addObject("returnUrl",returnUrl).addObject("saveType",saveType);
	}

	/**
	 * 附件上传操作
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("fileUpload")
	@Action(description = "文件上传", exectype = "管理日志", detail = "上传文件" + "<#list sysFiles as item>" + "【${item.fileName}】" + "</#list>")
	public void fileUpload(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		try {
			System.out.println("开始准备上传");
			long userId = ContextUtil.getCurrentUserId(); // 获取当前用户的id
			long typeId = RequestUtil.getLong(request, "typeId");
			String uploadType = RequestUtil.getString(request, "uploadType");               //控件的类型
			String fileFormates = RequestUtil.getString(request, "fileFormates");         //格式要求
			boolean mark = true;
			SysUser appUser = null;
			if (userId > 0) {
				appUser = sysUserService.getById(userId);
			}
			// 附件保存路径
			//String attachPath = configproperties.getProperty("file.upload");
			String attachPath=ServiceUtil.getBasePath();
			
			// 获取附件类型
			GlobalType globalType = null;
			if (typeId > 0) {
				globalType = globalTypeService.getById(typeId);
			}
			Map<String, MultipartFile> files = request.getFileMap();
			Iterator<MultipartFile> it = files.values().iterator();

			while (it.hasNext()) {
				Long fileId = UniqueIdUtil.genId();
				System.out.println("fileId:"+fileId);
				request.getSession().setAttribute("fileId", fileId);
				
				MultipartFile f = it.next();
				String oriFileName = f.getOriginalFilename();
				String extName = FileUtil.getFileExt(oriFileName);
				
				if(StringUtil.isNotEmpty(fileFormates)){            //文件格式要求
	                if( !( fileFormates.contains("*."+extName) ) ){       //不符合文件格式要求的就标志为false
	                	mark = false;
	                }
				}
				
				if(mark){
					String fileName = fileId + "." + extName;
					// 开始写入物理文件
					String filePath = "";
					SysFile sysFile = new SysFile();
					if("pictureShow".equals(uploadType)){            //pictureShow控件要的文件路径要有一点不同
						filePath = createFilePath(attachPath  + File.separator + appUser.getAccount()+ File.separator +"pictureShow", fileName);
					}else{
						filePath = createFilePath(attachPath + File.separator + appUser.getAccount(), fileName);
					}	
					
					// 开始写入物理文件
					if(saveType.contains("database")){       //二进制流动保存到数据库中
						sysFile.setFileBlob(f.getBytes());
					}else{
						FileUtil.writeByte(filePath, f.getBytes());
					}

					// end 写入物理文件
					// 向数据库中添加数据
					
					sysFile.setFileId(fileId);
					// 附件名称
					sysFile.setFileName(oriFileName.substring(0, oriFileName.lastIndexOf('.')));

					Calendar cal = Calendar.getInstance();
					Integer year = cal.get(Calendar.YEAR);
					Integer month = cal.get(Calendar.MONTH) + 1;
					
					sysFile.setFilePath(filePath.replace(attachPath + File.separator,""));  //保存相对路径
					// 附件类型
					if (globalType != null) {
						sysFile.setTypeId(globalType.getTypeId());
						sysFile.setFileType(globalType.getTypeName());
					} else {
						sysFile.setTypeId(sysTypeKeyService.getByKey(GlobalType.CAT_FILE).getTypeId());
						sysFile.setFileType("-");
					}
					// 上传时间
					sysFile.setCreatetime(new java.util.Date());
					// 扩展名
					sysFile.setExt(extName);
					// 字节总数
					sysFile.setTotalBytes(f.getSize());
					// 说明
					sysFile.setNote(FileUtil.getSize(f.getSize()));
					// 当前用户的信息
					if (appUser != null) {
						sysFile.setCreatorId(appUser.getUserId());
						sysFile.setCreator(appUser.getUsername());
					} else {
						sysFile.setCreator(SysFile.FILE_UPLOAD_UNKNOWN);
					}
					// 总的字节数
					sysFile.setDelFlag(SysFile.FILE_NOT_DEL);
					sysFileService.add(sysFile);
					
					//暂时保留
					/*if("pictureShow".equals(uploadType)){            //pictureShow控件要做多一个文件压缩图
						//filePath =filePath.replaceAll(fileName, fileId + "_small");
						 压缩后的文件路径      如D:/1234.jpg 变成 D:/1234_small.jpg 
						int width = Integer.parseInt(configproperties.getProperty("compression.width"));
						String filePrex = filePath.substring(0, filePath.lastIndexOf("."));  
						filePath = filePrex + "_small" + filePath.substring(filePrex.length());            
						String reutrnStr = ImageUtil.doCompressByByte(f.getBytes(), filePath, width, 40, 1, true);
					    logger.info("压缩后的文件："+reutrnStr);
					}
					*/
					// end 向数据库中添加数据
					writer.println("{\"success\":\"true\",\"fileId\":\"" + fileId + "\",\"fileName\":\"" + oriFileName + "\"}");
					try {
						List<SysFile> sysFiles;
						if (SysAuditThreadLocalHolder.getParamerter("sysFiles") == null) {
							sysFiles = new ArrayList<SysFile>();
							SysAuditThreadLocalHolder.putParamerter("sysFiles", sysFiles);
						} else {
							sysFiles = (List<SysFile>) SysAuditThreadLocalHolder.getParamerter("sysFiles");
						}
						sysFiles.add(sysFile);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}
				}else{
					logger.error("文件格式不符合要求！");
					writer.println("{\"success\":\"false\"}");
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			writer.println("{\"success\":\"false\"}");
		}
	}

	/**
	 * 如果文件存在则更新，如果文件不存在则新建文件。
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("saveFile")
	@Action(description = "保存文件",
	execOrder=ActionExecOrder.AFTER,
	detail="<#if isAdd>添加<#else>更新</#if>文件" +
			"<#list sysFiles as item>" +
				"【${item.fileName}】" +
			"</#list>"
			)
	public void saveFile(MultipartHttpServletRequest request,HttpServletResponse response) throws Exception {
		//xinjia
		String attachPath = ServiceUtil.getBasePath().replace("/", "\\");
		//xinjia
		System.out.println("准备开始保存文件");
		PrintWriter writer = response.getWriter();
		long userId = ContextUtil.getCurrentUserId(); // 获取当前用户的id
		long typeId = RequestUtil.getLong(request,"typeId");
		long fileId=RequestUtil.getLong(request, "fileId");
		String uploadName = RequestUtil.getString(request, "uploadName", "");
		String ajaxType = RequestUtil.getString(request, "ajaxType", "");
		String inputObjNum = RequestUtil.getString(request, "inputObjNum", "");   //在OFFICE控件对象数组的的序号，用于返回前台时，把相应的值存放在相应的对象里
		try{			
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
				MultipartFile f = it.next();
				String name = f.getName(); 
				String myFilePath = "";
				if(uploadName.equals(name)){
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
						myFilePath = sysFile.getFilePath();
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
					
					String oriFileName = f.getOriginalFilename();
				    String extName=FileUtil.getFileExt(oriFileName);
				    String fileName= fileId + "." + extName;
				   
					//开始写入物理文件
					String filePath = createFilePath(attachPath+File.separator+appUser.getAccount(), fileName);
					File fileInfo = new File(filePath);
					if(fileInfo.exists()){
				    	fileInfo.delete();
				    }
					FileUtil.writeByte(filePath, f.getBytes()) ;
				    
				    //附件名称
				    sysFile.setFileName(oriFileName.substring(0, oriFileName.lastIndexOf('.')));
				    
				    Calendar cal = Calendar.getInstance();
				    Integer year = cal.get(Calendar.YEAR);
				    Integer month = cal.get(Calendar.MONTH) + 1;
				    //附件路径
				    //sysFile.setFilePath(attachPath+'/'+appUser.getAccount() + year + "/" + month + "/" +  fileName);//绝对路劲
				    sysFile.setFilePath(filePath.replace(attachPath + File.separator,""));
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
				    	boolean mark = true;
				    	String newFilePath = sysFile.getFilePath();
				    	if(StringUtil.isNotEmpty(newFilePath)&&StringUtil.isNotEmpty(myFilePath)){
				    		if(newFilePath.trim().equals(myFilePath.trim())){
				    			mark = false;
				    		}
				    	}
				    	if(mark){
				    		if (StringUtil.isEmpty(attachPath)) {
							   myFilePath = AppUtil.getRealPath(myFilePath);
						    }	
					    	//删除原旧的文件
					    	//FileUtil.deleteFile(attachPath + File.separator + myFilePath);
					    	File oldFileInfo = new File(attachPath + File.separator + myFilePath);
					    	if(oldFileInfo.exists()){
					    		oldFileInfo.delete();
						    }
				    	}
				    }
				    // end 向数据库中添加数据
				}
				
				String reutrnStr = Long.toString(fileId);
				if(StringUtil.isNotEmpty(inputObjNum)){   //只有火狐和谷歌浏览器才会加入并有这个参数
					reutrnStr = inputObjNum+"##"+reutrnStr;     //返回到前台（用于火狐和谷歌浏览器）时再解析
				}				
				if(StringUtil.isNotEmpty(ajaxType) && "obj".equals(ajaxType)&& BeanUtils.isNotEmpty(fileId)){
			    	writeResultMessage(writer,reutrnStr,ResultMessage.Success);
			    }else{
					writer.print(reutrnStr);
			    }
			}
		}
		catch (Exception e) {
			logger.warn(e.getMessage());
			if(StringUtil.isNotEmpty(ajaxType) && "obj".equals(ajaxType)&& BeanUtils.isNotEmpty(fileId)){
		    	writeResultMessage(writer,"-1",ResultMessage.Success);
		    }else{
				writer.print(-1);
		    }
		}
	} 

	/**
	 * 取得附件明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getJson")
	@ResponseBody
	@Action(description = "查看附件明细", ownermodel = SysAuditModelType.SYSTEM_SETTING, exectype = "管理日志",detail="查看附件明细")
	public Map<String, Object> getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int status = 1;
		try {
			long id = RequestUtil.getLong(request, "fileId");
			SysFile sysFile = sysFileService.getById(id);
			map.put("sysFile", sysFile);
		} catch (Exception e) {
			status = -1;
		}
		map.put("status", status);
		return map;
	}

	/**
	 * 创建文件目录
	 * 
	 * @param tempPath
	 * @param fileName
	 *            文件名称
	 * @return 文件的完整目录
	 */
	private String createFilePath(String tempPath, String fileName) {
		File one = new File(tempPath);
		Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR); // 当前年份
		Integer month = cal.get(Calendar.MONTH) + 1; // 当前月份
		one = new File(tempPath +  File.separator + year +  File.separator + month);
		if (!one.exists()) {
			one.mkdirs();
		}
		return one.getPath() + File.separator + fileName;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description = "附件查询", exectype = "管理日志", detail = "附件查询")
	public ModelAndView selector(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView result = getAutoView();
		Long userId = ContextUtil.getCurrentUserId();
		Long typeId = RequestUtil.getLong(request, "typeId");
		QueryFilter filter = new QueryFilter(request, "sysFileItem");

		filter.addFilter("userId", userId);
		Long cateTypeId = sysTypeKeyService.getByKey(GlobalType.CAT_FILE).getTypeId();
		if (typeId != 0L && !typeId.equals(cateTypeId)) {
			filter.addFilter("typeId", typeId);
		} else {
			filter.addFilter("typeId", null);
		}
		List<SysFile> list = sysFileService.getFileAttch(filter);
		result.addObject("sysFileList", list);
		int isSingle = RequestUtil.getInt(request, "isSingle", 0);
		result.addObject("isSingle", isSingle);
		return result;
	}

	/**
	 * 附件下载
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("download")
	@Action(description = "附件下载", exectype = "管理日志", 
			detail ="附件【${SysAuditLinkService.getSysFileLink(Long.valueOf(fileId))}】下载")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//xinjjia
		String attachPath = ServiceUtil.getBasePath().replace("/", "\\");
		//xinjia
		// 附件保存路径
		long fileId = RequestUtil.getLong(request, "fileId", 0);
		if (fileId == 0)
			return;
		SysFile sysFile = sysFileService.getById(fileId);
		if (sysFile == null)
			return;
		String fileName = sysFile.getFileName() + "." + sysFile.getExt();
		if(saveType.contains("database")){
			FileUtil.downLoadFileByByte(request, response, sysFile.getFileBlob(), fileName);
		}else{
			String filePath = sysFile.getFilePath();
			if (StringUtil.isEmpty(filePath))
				return;
			// String fullPath=filePath.replace("/", File.separator);//绝对路径下的下载路径
		//	String attachPath = configproperties.getProperty("file.upload");
			if (StringUtil.isEmpty(attachPath)) {
				attachPath = AppUtil.getRealPath("/attachFiles/temp");
			}
			//attachPath = ServiceUtil.getBasePath();
			// String
			// fullPath=StringUtil.trimSufffix(configproperties.getProperty("file.upload").toString(),File.separator)
			// + File.separator +filePath.replace("/", File.separator);
			String fullPath = StringUtil.trimSufffix(attachPath, File.separator) + File.separator + filePath.replace("/", File.separator);
			FileUtil.downLoadFile(request, response, fullPath, fileName);

		}
	}

	/**
	 * 根据文件id取得附件数据。
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getFileById")
	@Action(description = "根据文件id取得附件数据", exectype = "管理日志", detail = "根据文件id取得附件数据")
	public void getFileById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//xinjia
		String attachPath = ServiceUtil.getBasePath().replace("/", "\\");
		//xinjia
		long fileId = RequestUtil.getLong(request, "fileId", 0);
		String type = RequestUtil.getString(request, "type");
		if (fileId == 0)
			return;
		SysFile sysFile = sysFileService.getById(fileId);
		//xinjia
		if(sysFile == null) return;
		//xinjia
		byte[] bytes = null;
		if(saveType.contains("database")){
			bytes = sysFile.getFileBlob();			
		}/*
		if(bytes==null || bytes.length<1 )*/
		else{
			String filePath = sysFile.getFilePath();
			// String fullPath= FileUtil.getRootPath() + filePath.replace("/",
			// File.separator);
			// 附件保存路径		
			String fullPath = StringUtil.trimSufffix(attachPath, File.separator) + File.separator + filePath.replace("/", File.separator);
			/*if("small".equals(type)){
				String filePrex = fullPath.substring(0, fullPath.lastIndexOf("."));  
				fullPath = filePrex + "_small" + fullPath.substring(filePrex.length()); 
			}*/
			bytes = FileUtil.readByte(fullPath);
		}
		response.getOutputStream().write(bytes);
		

	}

	/**
	 * 取得附件明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getFile")
	@Action(description = "查看附件明细", detail = "查看附件明细")
	@ResponseBody
	public SysFile getFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "fileId");
		SysFile sysFile = sysFileService.getById(id);
		return sysFile;
	}

	/**
	 * 根据ID获取附件
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/file_{fileId}")
	public void getById(@PathVariable("fileId") Long fileId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		getFile(request, response, fileId);
	}
	
	/**
	 * 根据ID获取照片(用于用户管理)
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/file_id{fileId}")
	public void getPictureById(@PathVariable("fileId") Long fileId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		getFile(request, response, fileId);
	}

	public void getFile(HttpServletRequest request, HttpServletResponse response, Long fileId) throws IOException {
		//xinjia
		String attachPath = ServiceUtil.getBasePath().replace("/", "\\");
		//xinjia
		response.reset();
		String vers = request.getHeader("USER-AGENT");
		SysFile sysFile = sysFileService.getById(fileId);
		//String filePath = StringUtil.trimSufffix(configproperties.get("file.upload").toString(), File.separator);
		String filePath = StringUtil.trimSufffix(attachPath, File.separator);
		String realPath = filePath + File.separator + sysFile.getFilePath().replace("/", File.separator);
		String fileName = sysFile.getFileName() + "." + sysFile.getExt();
		
		
		OutputStream outp = response.getOutputStream();
		String isDownload = request.getParameter("download");
		String contextType = getContextType(sysFile.getExt(), false);
		response.setContentType(contextType);
		response.setCharacterEncoding("utf-8");
		if (vers.indexOf("Chrome") != -1 && vers.indexOf("Mobile") != -1) {
			fileName = fileName.toString();
		} else {
			fileName = StringUtil.encodingString(fileName, "GB2312", "ISO-8859-1");
		}
		if ("application/octet-stream".equals(contextType) || StringUtils.isNotBlank(isDownload)) {
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		} else {
			response.addHeader("Content-Disposition", "filename=" + fileName);
		}
		response.addHeader("Content-Transfer-Encoding", "binary");
		//判断市场数据库读取还是直接从物理路径下的文件读取
		if(saveType.contains("database")){
			byte[] bytes = null;
			bytes = sysFile.getFileBlob();	
			response.setContentLength( bytes.length);
			outp.write(bytes);
		}else {
			// 读取文件并输出
			File file = new File(realPath);
			if (file.exists()) {
				response.setContentLength((int) file.length());
				FileInputStream in = null;
				try {
					in = new FileInputStream(realPath);
					byte[] b = new byte[1024];
					int i = 0;
					while ((i = in.read(b)) > 0) {
						outp.write(b, 0, i);
					}
					outp.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (in != null) {
						in.close();
						in = null;
					}
					if (outp != null) {
						outp.close();
						outp = null;
						response.flushBuffer();
					}
				}
			} else {
				response.getWriter().print("<font style='font-weight:800;color:#696969;font-size:14px;text-align:center;'>The file is not exists!</font>");
			}
		}
	}

	/**
	 * 获取内容类型。
	 * 
	 * @param extName
	 * @return
	 */
	private String getContextType(String extName, boolean isRead) {
		String contentType = "application/octet-stream";
		if ("jpg".equalsIgnoreCase(extName) || "jpeg".equalsIgnoreCase(extName)) {
			contentType = "image/jpeg";
		} else if ("png".equalsIgnoreCase(extName)) {
			contentType = "image/png";
		} else if ("gif".equalsIgnoreCase(extName)) {
			contentType = "image/gif";
		} else if ("doc".equalsIgnoreCase(extName) || "docx".equalsIgnoreCase(extName)) {
			contentType = "application/msword";
		} else if ("xls".equalsIgnoreCase(extName) || "xlsx".equalsIgnoreCase(extName)) {
			contentType = "application/vnd.ms-excel";
		} else if ("ppt".equalsIgnoreCase(extName) || "pptx".equalsIgnoreCase(extName)) {
			contentType = "application/ms-powerpoint";
		} else if ("rtf".equalsIgnoreCase(extName)) {
			contentType = "application/rtf";
		} else if ("htm".equalsIgnoreCase(extName) || "html".equalsIgnoreCase(extName)) {
			contentType = "text/html";
		} else if ("swf".equalsIgnoreCase(extName)) {
			contentType = "application/x-shockwave-flash";
		} else if ("bmp".equalsIgnoreCase(extName)) {
			contentType = "image/bmp";
		} else if ("mp4".equalsIgnoreCase(extName)) {
			contentType = "video/mp4";
		} else if ("wmv".equalsIgnoreCase(extName)) {
			contentType = "video/x-ms-wmv";
		} else if ("wm".equalsIgnoreCase(extName)) {
			contentType = "video/x-ms-wm";
		} else if ("rv".equalsIgnoreCase(extName)) {
			contentType = "video/vnd.rn-realvideo";
		} else if ("mp3".equalsIgnoreCase(extName)) {
			contentType = "audio/mp3";
		} else if ("wma".equalsIgnoreCase(extName)) {
			contentType = "audio/x-ms-wma";
		} else if ("wav".equalsIgnoreCase(extName)) {
			contentType = "audio/wav";
		}
		if ("pdf".equalsIgnoreCase(extName) && isRead)// txt不下载文件，读取文件内容
		{
			contentType = "application/pdf";
		}
		if (("sql".equalsIgnoreCase(extName) || "txt".equalsIgnoreCase(extName)) && isRead)// pdf不下载文件，读取文件内容
		{
			contentType = "text/plain";
		}
		return contentType;
	}

	/**
	 * 如果PDF文件存在则更新，如果PDF文件不存在则新建文件。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("saveFilePdf")
	@Action(description = "保存PDF文件",detail="保存PDF文件")
	public void saveFilePdf(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		//xinjia
		String attachPath = ServiceUtil.getBasePath().replace("/", "\\");
		//xinjia
		logger.info("开始转换成PDF文件!!!!!!");
		PrintWriter writer = response.getWriter();
		try {
			long userId = ContextUtil.getCurrentUserId(); // 获取当前用户的id
			long typeId = RequestUtil.getLong(request, "typeId");
			long fileId = RequestUtil.getLong(request, "fileId");
			String uploadName = RequestUtil.getString(request, "uploadName", "");
			SysUser appUser = null;
			if (userId > 0) {
				appUser = sysUserService.getById(userId);
			}
		
			// 获取附件类型
			GlobalType globalType = null;
			if (typeId > 0) {
				globalType = globalTypeService.getById(typeId);
			}
			Map<String, MultipartFile> files = request.getFileMap();
			Iterator<MultipartFile> it = files.values().iterator();

			while (it.hasNext()) {
				MultipartFile f = it.next();
				String name = f.getName();
				if (uploadName.equals(name)) {
					boolean isAdd = true;
					SysFile sysFile = null;
					String myFilePath = "";
					if (fileId == 0) {
						fileId = UniqueIdUtil.genId();
						sysFile = new SysFile();
						sysFile.setFileId(fileId);
					} else {
						sysFile = sysFileService.getById(fileId);
						isAdd = false;
						myFilePath = sysFile.getFilePath();
					}

					// 添加系统日志信息 -B
					try {
						List<SysFile> sysFiles;
						if (SysAuditThreadLocalHolder.getParamerter("sysFiles") == null) {
							sysFiles = new ArrayList<SysFile>();
							SysAuditThreadLocalHolder.putParamerter("sysFiles", sysFiles);
						} else {
							sysFiles = (List<SysFile>) SysAuditThreadLocalHolder.getParamerter("sysFiles");
						}
						sysFiles.add(sysFile);
						SysAuditThreadLocalHolder.putParamerter("isAdd", fileId == 0);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}

					String oriFileName = f.getOriginalFilename();
					// String extName=FileUtil.getFileExt(oriFileName);
					String fileName = fileId + ".pdf";

					// 开始写入物理文件
					String filePath = createFilePath(attachPath + File.separator + appUser.getAccount(), fileName);
					File fileInfo = new File(filePath);
					if (fileInfo.exists()) {
						fileInfo.delete();
					}
					FileUtil.writeByte(filePath, f.getBytes());

					// 附件名称
					sysFile.setFileName(oriFileName.substring(0, oriFileName.lastIndexOf('.')));

					/*Calendar cal = Calendar.getInstance();
					Integer year = cal.get(Calendar.YEAR);
					Integer month = cal.get(Calendar.MONTH) + 1;*/
					// 附件路径
					// sysFile.setFilePath(attachPath+'/'+appUser.getAccount() +
					// year + "/" + month + "/" + fileName);//绝对路劲
			//		sysFile.setFilePath(filePath.replace(attachPath + File.separator,""));
					sysFile.setFilePath(filePath.replace(attachPath + File.separator,""));
					// 附件类型
					if (globalType != null) {
						sysFile.setTypeId(globalType.getTypeId());
						sysFile.setFileType(globalType.getTypeName());
					} else {
						sysFile.setTypeId(0L);
						sysFile.setFileType("-");
					}
					// 上传时间
					sysFile.setCreatetime(new java.util.Date());
					// 扩展名
					sysFile.setExt("pdf");
					// 字节总数
					sysFile.setTotalBytes(f.getSize());
					// 说明
					sysFile.setNote(FileUtil.getSize(f.getSize()));
					// 当前用户的信息
					if (appUser != null) {
						sysFile.setCreatorId(appUser.getUserId());
						sysFile.setCreator(appUser.getUsername());
					} else {
						sysFile.setCreator(SysFile.FILE_UPLOAD_UNKNOWN);
					}
					// 总的字节数
					sysFile.setDelFlag(SysFile.FILE_NOT_DEL);
					if (isAdd) {
						sysFileService.add(sysFile);
					} else {
						sysFileService.update(sysFile);
						boolean mark = true;
				    	String newFilePath = sysFile.getFilePath();
				    	if(StringUtil.isNotEmpty(newFilePath)&&StringUtil.isNotEmpty(myFilePath)){
				    		if(newFilePath.trim().equals(myFilePath.trim())){
				    			mark = false;
				    		}
				    	}
				    	if(mark){
				    		if (StringUtil.isEmpty(attachPath)) {
							   myFilePath = AppUtil.getRealPath(myFilePath);
						    }	
					    	//删除原旧的文件
					    	//FileUtil.deleteFile(attachPath + File.separator + myFilePath);
					    	File oldFileInfo = new File(attachPath + File.separator + myFilePath);
					    	if(oldFileInfo.exists()){
					    		oldFileInfo.delete();
						    }
				    	}
					}
					// end 向数据库中添加数据
				}
				writer.print(fileId);
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
			writer.print(-1);
		}
	}

	/**
	 * 根据文件id取得附件数据。
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getFileType")
	public void getFileType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		try {
			long fileId = RequestUtil.getLong(request, "fileId", 0);
			SysFile sysFile = null;
			String type = "doc";
			if (fileId > 0) {
				sysFile = sysFileService.getById(fileId);
				type = sysFile.getExt().toLowerCase();
			}
			writer.print(type);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			writer.print("doc");
		} finally {
			writer.close();
		}
	}

	/**
	 * 如果Info文件存在则更新，如果Info文件不存在则新建文件。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("saveFileInfo")
	@Action(description = "保存Info附件（web签章）",detail = "保存Info附件（web签章）")
	public void saveFileInfo(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		//xinjia
		String attachPath = ServiceUtil.getBasePath().replace("/", "\\");
		//xinjia
		PrintWriter writer = response.getWriter();
		try {
			long userId = ContextUtil.getCurrentUserId(); // 获取当前用户的id
			long typeId = RequestUtil.getLong(request, "typeId");
			long fileId = RequestUtil.getLong(request, "fileId");
			String uploadName = RequestUtil.getString(request, "uploadName", "");
			SysUser appUser = null;
			if (userId > 0) {
				appUser = sysUserService.getById(userId);
			}
		
			// 获取附件类型
			GlobalType globalType = null;
			if (typeId > 0) {
				globalType = globalTypeService.getById(typeId);
			}
			Map<String, MultipartFile> files = request.getFileMap();
			Iterator<MultipartFile> it = files.values().iterator();

			while (it.hasNext()) {
				MultipartFile f = it.next();
				String name = f.getName();
				if (uploadName.equals(name)) {
					boolean isAdd = true;
					SysFile sysFile = null;
					String myFilePath = "";
					if (fileId == 0) {
						fileId = UniqueIdUtil.genId();
						sysFile = new SysFile();
						sysFile.setFileId(fileId);
					} else {
						sysFile = sysFileService.getById(fileId);
						isAdd = false;
						myFilePath = sysFile.getFilePath();
					}

					// 添加系统日志信息 -B
					try {
						List<SysFile> sysFiles;
						if (SysAuditThreadLocalHolder.getParamerter("sysFiles") == null) {
							sysFiles = new ArrayList<SysFile>();
							SysAuditThreadLocalHolder.putParamerter("sysFiles", sysFiles);
						} else {
							sysFiles = (List<SysFile>) SysAuditThreadLocalHolder.getParamerter("sysFiles");
						}
						sysFiles.add(sysFile);
						SysAuditThreadLocalHolder.putParamerter("isAdd", fileId == 0);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}

					String oriFileName = f.getOriginalFilename();
					// String extName=FileUtil.getFileExt(oriFileName);
					String fileName = fileId + ".info";

					// 开始写入物理文件
					String filePath = createFilePath(attachPath + File.separator + appUser.getAccount(), fileName);
					File fileInfo = new File(filePath);
					if (fileInfo.exists()) {
						fileInfo.delete();
					}
					FileUtil.writeByte(filePath, f.getBytes());

					// 附件名称
					sysFile.setFileName(oriFileName.substring(0, oriFileName.lastIndexOf('.')));

					/*Calendar cal = Calendar.getInstance();
					Integer year = cal.get(Calendar.YEAR);
					Integer month = cal.get(Calendar.MONTH) + 1;*/
					// 附件路径
					// sysFile.setFilePath(attachPath+'/'+appUser.getAccount() +
					// year + "/" + month + "/" + fileName);//绝对路劲
					sysFile.setFilePath(filePath.replace(attachPath + File.separator,""));
					// 附件类型
					if (globalType != null) {
						sysFile.setTypeId(globalType.getTypeId());
						sysFile.setFileType(globalType.getTypeName());
					} else {
						sysFile.setTypeId(0L);
						sysFile.setFileType("-");
					}
					// 上传时间
					sysFile.setCreatetime(new java.util.Date());
					// 扩展名
					sysFile.setExt("info");
					// 字节总数
					sysFile.setTotalBytes(f.getSize());
					// 说明
					sysFile.setNote(FileUtil.getSize(f.getSize()));
					// 当前用户的信息
					if (appUser != null) {
						sysFile.setCreatorId(appUser.getUserId());
						sysFile.setCreator(appUser.getUsername());
					} else {
						sysFile.setCreator(SysFile.FILE_UPLOAD_UNKNOWN);
					}
					// 总的字节数
					sysFile.setDelFlag(SysFile.FILE_NOT_DEL);
					if (isAdd) {
						sysFileService.add(sysFile);
					} else {
						sysFileService.update(sysFile);
						boolean mark = true;
				    	String newFilePath = sysFile.getFilePath();
				    	if(StringUtil.isNotEmpty(newFilePath)&&StringUtil.isNotEmpty(myFilePath)){
				    		if(newFilePath.trim().equals(myFilePath.trim())){
				    			mark = false;
				    		}
				    	}
				    	if(mark){
				    		if (StringUtil.isEmpty(attachPath)) {
							   myFilePath = AppUtil.getRealPath(myFilePath);
						    }	
					    	//删除原旧的文件
					    	//FileUtil.deleteFile(attachPath + File.separator + myFilePath);
					    	File oldFileInfo = new File(attachPath + File.separator + myFilePath);
					    	if(oldFileInfo.exists()){
					    		oldFileInfo.delete();
						    }
				    	}
					}
					// end 向数据库中添加数据
				}
				writer.print(fileId);
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
			writer.print(-1);
		}
	}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("pictureShow")
	@Action(description = "图片展示")
	public ModelAndView pictureShow(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView result = getAutoView();
		Long userId = ContextUtil.getCurrentUserId();
		String id = RequestUtil.getString(request, "id");
		String title = RequestUtil.getString(request, "title");
		String type = RequestUtil.getString(request, "type");
		title = URLDecoder.decode(title,"utf-8");
		result.addObject("id", id);
		result.addObject("title", title);
		result.addObject("type", type);
		return result;
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getUserData")
	public void getUserData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		try {
			SysUser user = ContextUtil.getCurrentUser();
		//	JSONObject json = JSONObject.fromObject(user);
			writer.println("{\"success\":\"true\",\"user\":{\"id\":\"" + user.getUserId() + "\",\"name\":\"" + user.getFullname() + "\",\"groupId\":\""+user.getUserOrgId()+"\",\"groupName\":\""+user.getOrgName()+"\" } }");
		} catch (Exception e) {
			logger.warn(e.getMessage());
			writer.println("{\"success\":\"false\",\"user\":\"\"}");
		} finally {
			writer.close();
		}
	}
	/**
	 * 获得附件上传类型
	 */
	@RequestMapping("uploadType")
	@ResponseBody
	public Map<String,String> uploadType(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    Map<String, String> map = new HashMap<String, String>();
		String uploadType = sysPropertyService.getByAlias("uploadType");
	    map.put("uploadType", uploadType);
		return map;
	}
}
