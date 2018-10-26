package com.hotent.platform.controller.system;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.platform.model.system.SysReport;
import com.hotent.platform.service.system.SysReportService;
@Controller
@RequestMapping("/platform/system/sysReport/")
public class SysReportFormController extends BaseController {
	@Resource
	private SysReportService service;
	
	/**
	 * 添加或更新报表模板
	 * @param request
	 * @param response
	 * @param reportTemplate 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新报表",
			execOrder=ActionExecOrder.AFTER,
			detail="添加或更新报表")
	public void save(MultipartHttpServletRequest request, HttpServletResponse response, SysReport sysReport) throws Exception
	{
		Long tempId = sysReport.getReportId();
		String uploadFile = "";
		try{
			//添加
			if(BeanUtils.isEmpty(tempId) || tempId==0){
				Long reportId = UniqueIdUtil.genId();
				Map<String, MultipartFile> files = request.getFileMap();
				Iterator<MultipartFile> it = files.values().iterator();
				if(it.hasNext()) {
					MultipartFile f = it.next();
					String oriFileName = f.getOriginalFilename();
					String uploadDir = service.getUploadDir();
					uploadFile = AppUtil.getRealPath(uploadDir)+File.separator+oriFileName ;
					String compileFilePath = uploadFile.replace(SysReportService.JREPORT_EXT, SysReportService.JREPORT_COMPILED_EXT);
					if(new File(uploadFile).exists() && new File(compileFilePath).exists()){
						writeResultMessage(response.getWriter(),"文件"+oriFileName+"已存在！",ResultMessage.Fail);
						return ;
					}
					FileUtil.createFolderFile(uploadFile);
					//开始写入物理文件
					FileUtil.writeByte(uploadFile, f.getBytes()) ;
					// 向数据库中添加数据 
					sysReport.setReportId(reportId);
					//附件名称
					sysReport.setFileName(oriFileName.substring(0, oriFileName.lastIndexOf('.')));
					//附件路径
					sysReport.setFilePath(uploadDir+oriFileName);
					// 上传时间
					sysReport.setCreatetime(new java.util.Date());
					// 扩展名
					sysReport.setExt(FileUtil.getFileExt(oriFileName));
					service.save(sysReport);
					// end 向数据库中添加数据
				}
				Map<String,Object> result = service.getParamList(sysReport);
				//如果有参数需要进行配置，则进行提示
				if(BeanUtils.isNotEmpty(result.get("paramList"))){
					writeResultMessage(response.getWriter(),reportId.toString(),ResultMessage.Success);
					return ;
				}
			}else{
				sysReport.setStatus(SysReportService.STATUS_REPORT_DONE);
				service.updateReport(sysReport);
			}
			writeResultMessage(response.getWriter(),"",ResultMessage.Success);
		} catch (Exception e) {
			if(e.getMessage()==null){
				writeResultMessage(response.getWriter(),"保存报表出错！",ResultMessage.Fail);
			}else{
				writeResultMessage(response.getWriter(),e.getMessage(),ResultMessage.Fail);
			}
			if(StringUtil.isNotEmpty(uploadFile)){
				FileUtil.deleteFile(uploadFile);
			}
		}
	}
}
