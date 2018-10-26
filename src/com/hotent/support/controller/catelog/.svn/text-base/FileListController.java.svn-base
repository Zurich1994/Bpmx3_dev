
package com.hotent.support.controller.catelog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.Perf.GetPerfAction;

import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.support.model.catelog.FileList;
import com.hotent.support.service.catelog.FileListService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:文件信息 控制器类
 */
@Controller
@RequestMapping("/support/catelog/fileList/")
public class FileListController extends BaseController
{
	@Resource
	private FileListService fileListService;
	/**
	 * 添加或更新文件信息。
	 * @param request
	 * @param response
	 * @param fileList 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新文件信息")
	public void save(HttpServletRequest request, HttpServletResponse response,FileList fileList) throws Exception
	{
		String resultMsg=null;		
		try{
			if(fileList.getId()==null){
				Long id=UniqueIdUtil.genId();
				fileList.setId(id);
				fileListService.add(fileList);
				resultMsg=getText("添加","文件信息");
			}else{
			    fileListService.update(fileList);
				resultMsg=getText("更新","文件信息");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得文件信息分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看文件信息分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		
		List<FileList> list = new ArrayList<FileList>();
		ModelAndView mv = null;
		
		request.setCharacterEncoding("UTF-8");
		String PRODUCTID = request.getParameter("PRODUCTID");
		String FILECATALOG = request.getParameter("FILECATALOG");
		String OS = request.getParameter("OS");
		String LANGUAGE = request.getParameter("LANGUAGE");
		System.out.println(PRODUCTID+" "+FILECATALOG+" "+OS+" "+ LANGUAGE);
  
		HashMap<String, Object> map =new HashMap<String, Object>();
    	map.put("PRODUCTID", PRODUCTID);
		//判断PRODUCTID
		list = fileListService.getp(map);
		if(list.size() == 0)
				{

				}
	
		map.remove("PRODUCTID");
		map.put("FILECATALOG", FILECATALOG);
		map.put("OS", OS);
		map.put("LANGUAGE", LANGUAGE);
		List<FileList> list1 = new ArrayList<FileList>();
		list1 =	fileListService.gety(map);
		if(list1.size() == 0){

		}

		map.put("PRODUCTID", PRODUCTID);
		List<FileList> list2 = new ArrayList<FileList>();
		list2 =	fileListService.getLi(map);
		if(list2.size() == 0){
			
		}else{		
		  mv=this.getAutoView().addObject("fileListList",list2);
		}
		System.out.println("end");
		return mv;
	}
	
	/**
	 * 删除文件信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除文件信息")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			fileListService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除文件信息成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑文件信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑文件信息")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		FileList fileList=fileListService.getById(id);
		
		return getAutoView().addObject("fileList",fileList)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得文件信息明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看文件信息明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		/*String url ="[{\"id\":\"10000021240001\",\"name\":\"myAdministration.txt\"}])";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2005-06-09"); 
		FileList fileList = new FileList();
		fileList.setFileid((long)1234567890);
		fileList.setProductid((long)1234567890);
		fileList.setOs("WinXP");
		fileList.setFilecatalog("Administrator");
		fileList.setLanguage("中文");
		fileList.setFilename("myAdministrator");
		fileList.setFiledate(date);
		fileList.setFilesize((long)12345678);
		fileList.setDescription("这是一个可以下载的文件");
		fileList.setAdditional("此文件无额外信息");
		fileList.setDownloadurl(url);*/
		//long fileID =(long)12345;
		//List<FileList> fileList=fileListService.getByFILEID((long)fileID);
		//request.setAttribute("fileList",fileList);
		//Long fileid =(long)12345;
		Long id=RequestUtil.getLong(request,"id");
		FileList fileList=fileListService.getById(id);
		//List<FileList> fileList=fileListService.getByFILEID((long)fileid);
		return getAutoView().addObject("fileList", fileList);
	}
	/**
	 * 	错误界面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("error")
	@Action(description="错误信息")
	public ModelAndView error(HttpServletRequest request) throws Exception
	{   
		System.out.println(this.getAutoView());
		return this.getAutoView();
	}
	
}
