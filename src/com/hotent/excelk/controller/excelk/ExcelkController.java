

package com.hotent.excelk.controller.excelk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.page.PageList;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysFileService;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.hotent.excelk.model.excelk.Excelk;
import com.hotent.excelk.service.excelk.ExcelkService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:excelk 控制器类
 */
@Controller
@RequestMapping("/excelk/excelk/excelk/")
public class ExcelkController extends BaseController
{
	@Resource
	private ExcelkService excelkService;
	
	@Resource
	private SysFileService sysFileService;
	
	
	/**
	 * 添加或更新excelk。
	 * @param request
	 * @param response
	 * @param excelk 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新excelk")
	public void save(HttpServletRequest request, HttpServletResponse response,Excelk excelk) throws Exception
	{
		String resultMsg=null;
		//总发生量
		Long totalCount=10000l;
		//每一时刻的发生量
		long number=0l;
		String fileId = String.valueOf(request.getSession().getAttribute("fileId"));
		try{
			if(excelk.getId()==null){
                SysFile sysFile = sysFileService.getById(Long.parseLong(fileId));
				String filePath = sysFile.getFilePath();
				//System.out.println("路径!!!:"+filePath);
				String[] filePaths = filePath.split("\\.");
				//System.out.println("路径长度为!!!:"+filePaths.length);
				if(filePaths[1].equals("xls")){
					System.out.println("上传的是xls文件");
					ArrayList<String> list = new ExcelImportn().excelOperateToList(filePath);
					HashMap<String,String> map = new ExcelImportn().excelOperateToMap(filePath);
					for(int i=0;i<list.size();i++){
						Long processId=Long.parseLong(sysFile.getFileName());
						System.out.println("流程ID"+processId);
						String time = list.get(i);
						String reguval = map.get(time);
						Excelk excel1 = new Excelk();
						long id = UniqueIdUtil.genId();
						excel1.setId(id);
					    excel1.setTime(time);
					    excel1.setReguval(reguval);
					    excel1.setProcessId(processId);
					    number=(Long)Math.round(Double.parseDouble(excel1.getReguval())*totalCount);
					    System.out.println("number为："+number);
					    excel1.setCount(number);
						excelkService.add(excel1);
						System.out.println("成功写入数据-------"+time+":"+reguval+":"+processId+":"+number);
						//System.out.println("这是第"+(i+1)+"个");
						//System.out.println("\n\n");	
					}
				}else{
					System.out.println("上传的不是xls文件,请重新上传");
				}
				
				//excelService.save(excelk);
				//resultMsg=getText("添加","excelk");
				resultMsg=getText("添加","excelk");
			}else{
			   // excelService.save(excel);
				//resultMsg=getText("更新","excel");
				excelkService.update(excelk);
				resultMsg=getText("更新","excelk");
			}
			//writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			e.printStackTrace();
			//writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
		response.sendRedirect("list.ht");
		System.out.println("跳转结束");
	}
	
	/**
	 * 取得excelk分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看excelk分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Excelk> list=excelkService.getAll(new QueryFilter(request,"excelkItem"));
		ModelAndView mv=this.getAutoView().addObject("excelkList",list);
		return mv;
	}
	
	/**
	 * 删除excelk
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除excelk")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			excelkService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除excelk成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑excelk
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑excelk")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Excelk excelk=excelkService.getById(id);
		
		return getAutoView().addObject("excelk",excelk)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得excelk明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看excelk明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Excelk excelk=excelkService.getById(id);
		return getAutoView().addObject("excelk", excelk);
	}
	
}

