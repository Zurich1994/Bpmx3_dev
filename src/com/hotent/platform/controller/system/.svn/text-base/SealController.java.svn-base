package com.hotent.platform.controller.system;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.Seal;
import com.hotent.platform.model.system.SealRight;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SealRightService;
import com.hotent.platform.service.system.SealService;
import com.hotent.platform.service.system.SysFileService;
import com.hotent.platform.service.util.ServiceUtil;

/**
 * 对象功能:电子印章 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:raise
 * 创建时间:2012-08-29 11:26:00
 */
@Controller
@RequestMapping("/platform/system/seal/")
@Action(ownermodel=SysAuditModelType.SYSTEM_SETTING)
public class SealController extends BaseController
{
	@Resource
	private SealService sealService;
	
	@Resource
	private SealRightService sealRightService;
	
	@Resource 
	private SysFileService sysFileService;

	//附件保存路径
	private String attachPath = ServiceUtil.getBasePath().replace("/", "\\");	
	//附件保存类型
	private String saveType = ServiceUtil.getSaveType();
	/**
	 * 取得电子印章分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看电子印章分页列表",detail="查看电子印章分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Seal> list=sealService.getAll(new QueryFilter(request,"sealItem"));
		ModelAndView mv=this.getAutoView().addObject("sealList",list);
		
		return mv;
	}
	
	/**
	 * 选择一个签章，进行盖章
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dialog")
	@Action(description="选择一个签章，进行盖章",detail="选择一个签章，进行盖章")
	public ModelAndView dialog(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		SysUser currentUser= ContextUtil.getCurrentUser();
		ModelAndView mv=this.getAutoView();
		mv.addObject("user", currentUser);
		return mv;
	}
	
	/**
	 * 与doalog页面一起使用。通过Iframe，在Dialog页面中显示印章，供选择。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description="签章选择器",detail="签章选择器")
	public ModelAndView selector(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String sealName = RequestUtil.getString(request, "sealName");
		Long userId=ContextUtil.getCurrentUserId();
		List<Seal> list=sealService.getSealByUserId(userId,sealName);
//		List<Seal> list=sealService.getAll(new QueryFilter(request,"sealItem"));
		ModelAndView mv=this.getAutoView().addObject("sealList",list).addObject("sealName",sealName);
		
		return mv;
	}
	
	/**
	 * 删除电子印章
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除电子印章",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除电子印章:"+
					"<#list StringUtils.split(sealId,\",\") as item>" +
					"<#assign entity=sealService.getById(Long.valueOf(item))/>" +
					"【${entity.sealName},及其对应的印章权限】"+
				"</#list>")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "sealId");
			if(!saveType.contains("database")){              //保存在服务器时才做文件删除
				for (Long id : lAryId) {
					Seal seal = sealService.getById(id);		
					SysFile sysFile = sysFileService.getById(seal.getAttachmentId());					
					String filePath = sysFile.getFilePath();
					// 删除印章文件
					if (StringUtil.isEmpty(attachPath)) {
						filePath = AppUtil.getRealPath(filePath);
					}
					FileUtil.deleteFile(attachPath + File.separator + filePath);
					
					Long showImageId = seal.getShowImageId();  //删除展示的图片
					if(BeanUtils.isNotEmpty(showImageId)){
						SysFile sysFileIma = sysFileService.getById(showImageId);
						String filePathIma = sysFileIma.getFilePath();
						// 删除文件
						if (StringUtil.isEmpty(attachPath)) {
							filePathIma = AppUtil.getRealPath(filePathIma);
						}
						FileUtil.deleteFile(attachPath + File.separator + filePathIma);
					}
					
					
				}
			}
			sealService.delByIds(lAryId);
			//删除印章时 一并删除 印章权限
			for (Long id : lAryId)
			{
				sealRightService.delBySealId(id,SealRight.CONTROL_TYPE_SEAL);
			}
			message=new ResultMessage(ResultMessage.Success, "删除电子印章成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description="添加或编辑电子印章",
			detail="<#if isAdd>添加电子印章<#else>编辑电子印章" +
					"<#assign entity=sealService.getById(Long.valueOf(sealId))/>" +
					"【${entity.sealName}】</#if>")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long sealId=RequestUtil.getLong(request,"sealId");
		String returnUrl=RequestUtil.getPrePage(request);
		Seal seal=null;
		if(sealId!=0){
			 seal= sealService.getById(sealId);
		}else{
			seal=new Seal();
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", sealId ==0);
		//印章权限类型列表
		List<Map> typeList = sealRightService.getRightType();
		//印章权限
		Map sealRightMap = sealRightService.getSealRight(sealId,SealRight.CONTROL_TYPE_SEAL);
		
		return getAutoView().addObject("seal",seal).addObject("returnUrl", returnUrl)
			   .addObject("typeList",typeList).addObject("sealRightMap", sealRightMap);
	}

	/**
	 * 取得电子印章明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看电子印章明细",detail="查看电子印章明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"sealId");
		long canReturn=RequestUtil.getLong(request, "canReturn",0);
		Seal seal = sealService.getById(id);		
		return getAutoView().addObject("seal", seal).addObject("canReturn",canReturn);
	}

}
