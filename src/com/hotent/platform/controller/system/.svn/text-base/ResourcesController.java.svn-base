package com.hotent.platform.controller.system;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.ResourceFolder;
import com.hotent.platform.model.system.Resources;
import com.hotent.platform.model.system.ResourcesUrl;
import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.ResourcesService;
import com.hotent.platform.service.system.ResourcesUrlService;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.platform.service.system.SubSystemUtil;
import com.hotent.core.annotion.ActionExecOrder;


/**
 * 对象功能:子系统资源 控制器类 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:csx 
 * 创建时间:2011-12-05 17:00:54
 */
@Controller
@RequestMapping("/platform/system/resources/")
@Action(ownermodel=SysAuditModelType.SYSTEM_SETTING)
public class ResourcesController extends BaseController {
	@Resource
	private ResourcesUrlService resourcesUrlService;
	@Resource
	private ResourcesService resourcesService;
	@Resource
	private SubSystemService subSystemService;

	/**
	 * 取得子系统资源分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("tree")
	// @Action(description="查看子系统资源树")
	public ModelAndView tree(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mv = this.getAutoView();
		List<SubSystem> subSystemList = subSystemService.getAll();
		Long currentSystemId = SubSystemUtil.getCurrentSystemId(request);
		mv.addObject("subSystemList", subSystemList).addObject("currentSystemId", currentSystemId);
		return mv;
	}

	/**
	 * 取得子系统资源分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看子系统资源分页列表",detail="查看子系统资源分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Resources> list = resourcesService.getAll(new QueryFilter(request, "resourcesItem"));
		if (list != null && list.size() > 0) {
			for (Resources res : list) {
				res.setIcon(request.getContextPath() + res.getIcon());
			}
		}
		ModelAndView mv = this.getAutoView().addObject("resourcesList", list);
		return mv;
	}

	/**
	 * 删除子系统资源
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除子系统资源",
			execOrder=ActionExecOrder.BEFORE,
			detail= "删除子系统资源" +
					"<#list StringUtils.split(resId,\",\") as item>" +
						"<#assign entity=resourcesService.getById(Long.valueOf(item))/>" +
						"【${entity.resName}】" +
					"</#list>"
						)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultMessage resultMessage=new ResultMessage(ResultMessage.Success,"删除资源成功!"); 
		Long resId = RequestUtil.getLong(request, "resId");
	    Resources res= this.resourcesService.getById(resId);
		try{
			resourcesService.delById(resId);
			//清除资源缓存
			//iCache.delByKey(SecurityUtil.FunctionRoleMap + res.getSystemId());
//			SecurityUtil.removeCacheBySystemId( res.getSystemId());
		}
		catch(Exception ex){
			resultMessage=new ResultMessage(ResultMessage.Fail,"删除资源失败!"); 
		}
		response.getWriter().print(resultMessage);
		
		
	}

	@RequestMapping("edit")
	@Action(description = "添加或编辑子系统资源",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加子系统资源<#else>编辑子系统资源" +
					"<#assign entity=resourcesService.getById(Long.valueOf(resId))/>" +
					"【${entity.resName}】</#if>"
			)
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		ModelAndView mv = getAutoView();
		long systemId = RequestUtil.getLong(request, "systemId", 0);
		long parentId = RequestUtil.getLong(request, "parentId", 0);
		long resId = RequestUtil.getLong(request, "resId", 0);

		
		Resources resources = null;
		boolean isadd=true;
		if (resId != 0) {
			resources = resourcesService.getById(resId);
			if (resources != null && resources.getIsFolder() != null && resources.getIsFolder().equals(resources.IS_FOLDER_N)) {
				List<ResourcesUrl> resourcesUrlList = resourcesUrlService.getByResId(resources.getResId());
				mv.addObject("resourcesUrlList", resourcesUrlList);
			}
			isadd=false;
		} 
		else {
			Resources parent = resourcesService.getParentResourcesByParentId(systemId, parentId);
			resources = new Resources();

			resources.setIsFolder(Resources.IS_FOLDER_N);
			resources.setIsDisplayInMenu(Resources.IS_DISPLAY_IN_MENU_Y);
			resources.setIsOpen(Resources.IS_OPEN_Y);
			resources.setIsNewOpen(Resources.IS_NEWOPEN_N);

			resources.setSystemId(systemId);
			resources.setParentId(parent.getResId());
			resources.setSn(1);
		}
		resources.setIcon(request.getContextPath() + resources.getIcon());

		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		return mv.addObject("resources", resources);

	}

	/**
	 * 取得子系统资源明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看子系统资源明细",detail="查看子系统资源明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "resId");
		Resources resources = resourcesService.getById(id);
		if (resources != null) {
			resources.setIcon(request.getContextPath() + resources.getIcon());
		}
		List<ResourcesUrl> resourcesUrlList = resourcesUrlService.getByResId(id);
		return getAutoView().addObject("resources", resources).addObject("resourcesUrlList", resourcesUrlList);
	}

	/**
	 * 取得总分类表用于显示树层次结构的分类可以分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getSystemTreeData")
	@ResponseBody
	public List<Resources> getSystemTreeData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long systemId = RequestUtil.getLong(request, "systemId", 0);
		List<Resources> list = resourcesService.getBySystemId(systemId);
		Resources parent = resourcesService.getParentResourcesByParentId(systemId, 0);
		list.add(parent);
		//修改图标路径
		ResourcesService.addIconCtxPath(list,request.getContextPath());
		return list;
	}
	
	

	/**
	 * 取得总分类表用于显示树层次结构的分类可以分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getSysRolResTreeChecked")
	// @Action(description="取得总分类表树形数据")
	@ResponseBody
	public List<Resources> getSysRolResTreeChecked(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long systemId = RequestUtil.getLong(request, "systemId", 0);
		long roleId = RequestUtil.getLong(request, "roleId", 0);

		List<Resources> resourcesList = resourcesService.getBySysRolResChecked(systemId, roleId);

		Resources parent = resourcesService.getParentResourcesByParentId(systemId, 0);
		resourcesList.add(parent);
		//修改图标路径
		ResourcesService.addIconCtxPath(resourcesList,request.getContextPath());
		return resourcesList;
	}
	
	/**
	 * 对资源进行拖动。
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("move")
	@Action(description = "转移子系统资源",
			detail="<#assign sourceEntity=globalTypeService.getById(Long.valueOf(sourceId))/>" +
					   "<#assign targetEntity=globalTypeService.getById(Long.valueOf(targetId))/>" +
					   "分类【${targetEntity.typeName}】转移到" +
					   "分类【${sourceEntity.typeName}】之内"
					   )
	public void move(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultMessage resultObj = null;
		PrintWriter out = response.getWriter();
		long targetId = RequestUtil.getLong(request, "targetId", 0);
		long sourceId = RequestUtil.getLong(request, "sourceId");
		resourcesService.move(sourceId, targetId);
		resultObj = new ResultMessage(ResultMessage.Success, "转移子系统资源完成");
		out.print(resultObj);
	}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/icons")
	public ModelAndView icons(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = getAutoView();
		int iconType = RequestUtil.getInt(request, "iconType");
		String path=RequestUtil.getString(request, "path");
		String iconTypeStr="";
		if(path!=null&&path!=""){
			iconTypeStr=path;
		}else{
			iconTypeStr=Resources.ICON_PATH;
			if (iconType == 1)
				iconTypeStr = Resources.LOGO_PATH;
		}
		String iconPath = request.getSession().getServletContext().getRealPath(iconTypeStr);
		String[] iconList= getIconList(iconPath);
		mv.addObject("iconList", iconList);
		mv.addObject("iconPath",iconTypeStr);
		return mv;
	}
	
	
	@RequestMapping("getFolderData")
	// @Action(description="取得资源图标文件夹")
	@ResponseBody
	public List<ResourceFolder> getFolderData(HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<ResourceFolder> list=new ArrayList<ResourceFolder>();
		String rootPath=Resources.ICON_PATH;
		ResourceFolder folder=new ResourceFolder();
		folder.setFolderId(0L);
		folder.setFolderName("全部");
		folder.setFolderPath(rootPath);
		list.add(folder);
		getFolderList(request,folder, list);
		return list;
	}
	
	
	public void getFolderList(HttpServletRequest request,ResourceFolder resFolder,List<ResourceFolder> list){
		String path=resFolder.getFolderPath();
		String realPath=request.getSession().getServletContext().getRealPath(path);
		File file=new File(realPath);
		File[] files=file.listFiles();
		if(files!=null&& files.length!=0){
			for(File f: files){
				if(f.isDirectory()){
					ResourceFolder folder=new ResourceFolder();
					folder.setFolderId(UniqueIdUtil.genId());
					folder.setFolderName(f.getName());
					folder.setFolderPath(path+"/"+f.getName());
					folder.setParentId(resFolder.getFolderId());
					list.add(folder);
					getFolderList(request,folder, list);
				}
			}
		}
		
	}
	/**
	 * 创建资源图标文件夹
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("createFolder")
	public void createFolder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String iconPath=RequestUtil.getString(request, "path");
		String folderName=RequestUtil.getString(request, "folderName");
		String path = request.getSession().getServletContext().getRealPath(iconPath);
		try {
			FileUtil.createFolder(path, folderName);
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Success, "新建文件夹成功！"));
		} catch (Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"新建文件夹失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	
	/**
	 * 上传图标
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("uploadIcon")
	public void uploadIcon(MultipartHttpServletRequest request,HttpServletResponse response) throws Exception {
		String iconPath=RequestUtil.getString(request,"path");
		Map<String, MultipartFile> files = request.getFileMap();
		Iterator<MultipartFile> it = files.values().iterator();
		if(it.hasNext()) {
			MultipartFile f = it.next();
			String fileName = f.getOriginalFilename();
		    String extName=FileUtil.getFileExt(fileName);
		    if(!extName.equals("gif") && !extName.equals("png") && !extName.equals("jpg")){
		    	writeResultMessage(response.getWriter(),new ResultMessage(ResultMessage.Fail,"上传的文件格式必须为图片类型"));
		    	return;
		    }
		    String filePath = request.getSession().getServletContext().getRealPath(iconPath+"/"+fileName);
		    File file=new File(filePath);
		    file.createNewFile();
		    FileUtil.writeByte(filePath, f.getBytes());
		    writeResultMessage(response.getWriter(),new ResultMessage(ResultMessage.Success,"上传的文件成功！"));
		}
	}
	

	/**
	 * 图标文件列表。
	 * @return
	 */
	private String[] getIconList(String iconPath) {
		File iconFolder = new File(iconPath);
		String[] fileNameList = iconFolder.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String ext = name.substring(name.lastIndexOf(".") + 1).toUpperCase();
				if (Resources.ICON_TYPE.contains(ext)) {
					return true;
				} else {
					return false;
				}
			}
		});
		return fileNameList;
	}
	
	/**
	 * 创建资源图标文件夹
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("delFile")
	public void delFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String folderPath=RequestUtil.getString(request, "path");
		String path = request.getSession().getServletContext().getRealPath(folderPath);
		try {
			FileUtil.deleteDir(new File(path));
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Success, "删除文件夹成功！"));
		} catch (Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"删除文件夹失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	/**
	 * 从样式文件中获取扩展功能图标
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getExtendIcons")
	@Action(description = "从样式文件中获取扩展功能图标",
			detail = "从样式文件中获取扩展功能图标")
	public void getExtendIcons(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultMessage resultObj = null;
		PrintWriter out = response.getWriter();
		try {
			String fileName = FileUtil.getRootPath() + Resources.EXTEND_CSS_PATH;
			String cssStr = FileUtil.readFile(fileName);
			int start = cssStr.indexOf("/*--extend start--*/");
			int end = cssStr.indexOf("/*--extend end--*/");
			String subStr = cssStr.substring(start, end);
			String icons = "";
			Pattern regex = Pattern.compile("a\\.extend\\.([\\w]+)\\s*\\{", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			Matcher regexMatcher = regex.matcher(subStr);
			while (regexMatcher.find()) {
				icons += regexMatcher.group(1) + ",";
			}
			resultObj = new ResultMessage(ResultMessage.Success, icons);
		} catch (Exception ex) {
			resultObj = new ResultMessage(ResultMessage.Fail, ex.getMessage());
		}
		out.print(resultObj);
	}

	private String getNewIconsStr() {
		String iconPath = FileUtil.getRootPath() + Resources.ICON_EXTEND.replace("/", File.separator);
		String[] icons = getIconList(iconPath);
		String returnStr = "/*--extend start--*/";
		for (String str : icons) {
			returnStr += "\n";
			returnStr += "a.extend." + str.substring(0, str.lastIndexOf("."));
			returnStr += "{\n";
			returnStr += "    background:url(../images/extendIcon/" + str + ") 0px -1px no-repeat;\n}\n";
		}
		return returnStr;
	}

	/**
	 * 刷新扩展功能样式文件
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("refreshExtendCss")
	@Action(description = "刷新扩展功能样式文件",
			detail = "刷新扩展功能样式文件")
	public void refreshExtendCss(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		ResultMessage resultMessage = null;
		try {
			String fileName = FileUtil.getRootPath() + Resources.EXTEND_CSS_PATH.replace("/", File.separator);;
			String cssStr = FileUtil.readFile(fileName);
			String newSubStr = getNewIconsStr();
			int length = cssStr.length();
			int start = cssStr.indexOf("/*--extend start--*/");
			int end = cssStr.indexOf("/*--extend end--*/");
			String bottomStr = cssStr.substring(end, length);
			String headStr = cssStr.substring(0, start);
			String newStr = headStr + newSubStr + bottomStr;
			FileUtil.writeFile(fileName, newStr);
			resultMessage = new ResultMessage(ResultMessage.Success, "刷新成功");
		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,"刷新扩展功能样式文件失败:" + str);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
			}
		}
		out.print(resultMessage);
	}
	
	/**
	 * 根据资源Id 导出资源
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportXml")
	@Action(description="导出资源",
			execOrder=ActionExecOrder.AFTER,
			detail="导出资源【${SysAuditLinkService.getResourcesLink(Long.valueOf(resId))}】"
	)
	public void exportXml(HttpServletRequest request,HttpServletResponse response) throws Exception{
		long resId=RequestUtil.getLong(request, "resId");
		if(resId!=0){
			Map<String, Boolean> map = new HashMap<String, Boolean>();
			map.put("resources", true);
			try{
				Resources resources=resourcesService.getById(resId);
				String fileName=resources.getAlias();
				String strXml=resourcesService.exportXml(resId,map);
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(fileName,"UTF-8") + ".xml");
				response.getWriter().write(strXml);
				response.getWriter().flush();
				response.getWriter().close();
				SysAuditThreadLocalHolder.putParamerter("resources", resources);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("importXml")
	@Action(description="导入资源",
			execOrder=ActionExecOrder.AFTER,
			detail="导入子系统资源<#if isAdd>" +
					"【${SysAuditLinkService.getResourcesLink(Long.valueOf(resId))}】成功<#else>失败</#if>"
			)
	public void importXml(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException
	{
		long systemId=RequestUtil.getLong(request, "systemId");
		long resId=RequestUtil.getLong(request, "resId");
		MultipartFile fileLoad = request.getFile("xmlFile");
		ResultMessage resultMessage = null;
		try {
			
			resourcesService.importXml(fileLoad.getInputStream(),resId,systemId);
			
			resultMessage = new ResultMessage(ResultMessage.Success, "导入成功!");
			SysAuditThreadLocalHolder.putParamerter("isAdd", true);
			writeResultMessage(response.getWriter(), resultMessage);
		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			SysAuditThreadLocalHolder.putParamerter("isAdd", false);
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,"导入失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	
	/**
	 * 资源树排序列表。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sortList")
	@Action(description="资源树排序列表",
			detail="资源树排序列表")
	public ModelAndView sortList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Long resId=RequestUtil.getLong(request, "resId",-1);
		Long systemId=RequestUtil.getLong(request, "systemId");
		List<Resources> list = resourcesService.getByParentId(resId);
		if(resId==0&&list.size()>0){
			//Long currentSystemId = SubSystemUtil.getCurrentSystemId(request);
			List<Resources> relist =new ArrayList<Resources>();
			for(Resources resources:list){
				if(resources.getSystemId().equals(systemId)){
					relist.add(resources);
				}
			}
			list.removeAll(list);
			list.addAll(relist);
		}
		
		return getAutoView().addObject("ResourcesList",list);	
	}
	
	/**
	 * 资源树排序。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sort")
	@Action(description="资源树排序",
			detail="资源树排序"
			)
	public void sort(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultMessage resultObj=null;
		PrintWriter out = response.getWriter();
		Long[] lAryId =RequestUtil.getLongAryByStr(request, "resIds");
		if(BeanUtils.isNotEmpty(lAryId)){
			for(int i=0;i<lAryId.length;i++){
				Long resId=lAryId[i];
				long sn=i+1;
				resourcesService.updSn(resId,sn);
				//System.out.println("--------->"+resourcesService.getById(resId).getSn());
			}
		}
		resultObj=new ResultMessage(ResultMessage.Success,"排序分类完成");
		out.print(resultObj);	
	}
	
	@RequestMapping("addResource")
	@Action(description="添加资源",
			detail="添加资源" )
	public ModelAndView addResource(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		System.out.println("haojq");
		ModelAndView mv = getAutoView();
		List<SubSystem> subSystemList = subSystemService.getAll();
		System.out.println(subSystemList);
		Long currentSystemId = SubSystemUtil.getCurrentSystemId(request);
		System.out.println(currentSystemId);
		mv.addObject("subSystemList", subSystemList).addObject("currentSystemId", currentSystemId);
		return mv;
	}
	
	
	@RequestMapping("addResource2")
	@Action(description="添加资源与跳转原子操作",
			detail="添加资源与跳转原子操作" )
	public ModelAndView addResource2(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mv = getAutoView();
		List<SubSystem> subSystemList = subSystemService.getAll();
	
		Long currentSystemId = SubSystemUtil.getCurrentSystemId(request);
		System.out.println("5%%%%%%");
		mv.addObject("subSystemList", subSystemList).addObject("currentSystemId", currentSystemId);
		return mv;
	}
	//xinjia
	/**
	 * 获取TAB列表
	 */
	@RequestMapping("tab_{alias}")
	public ModelAndView tab(HttpServletRequest request,HttpServletResponse response,
			@PathVariable(value="alias") String alias) throws Exception {
		
		SubSystem currentSystem = SubSystemUtil.getCurrentSystem(request);
		Resources res=resourcesService.getByAlias(currentSystem.getSystemId(), alias);
		Long curuserId=ContextUtil.getCurrentUserId();
		
		List<Resources> resList=resourcesService.getByParentUserId(res.getResId(),curuserId);
		
		Map<String,List<Resources>> resrouceMap=new HashMap<String, List<Resources>>();
		for(Resources resource:resList){
			List<Resources> tmpList=resourcesService.getByParentUserId(resource.getResId(), curuserId);
			for(Resources tmp : tmpList){
				String url = tmp.getDefaultUrl();
				if(StringUtil.isNotEmpty(url) && url.indexOf("http") == -1){
					tmp.setDefaultUrl(request.getContextPath()+url);
				}
			}
			resrouceMap.put(resource.getAlias(), tmpList);
		}
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/platform/system/resourcesTab.jsp");
		mv.addObject("resList", resList);
		mv.addObject("resrouceMap", resrouceMap);
		return mv;
	}
}
