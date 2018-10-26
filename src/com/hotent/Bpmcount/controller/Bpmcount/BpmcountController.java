

package com.hotent.Bpmcount.controller.Bpmcount;

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
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.BpmDefinitionService;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.hotent.Bpmcount.model.Bpmcount.Bpmcount;
import com.hotent.Bpmcount.service.Bpmcount.BpmcountService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:bpmcount 控制器类
 */
@Controller
@RequestMapping("/Bpmcount/Bpmcount/bpmcount/")
public class BpmcountController extends BaseController
{
	@Resource
	private BpmcountService bpmcountService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	/**
	 * 添加或更新bpmcount。
	 * @param request
	 * @param response
	 * @param bpmcount 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新bpmcount")
	public void save(HttpServletRequest request, HttpServletResponse response,Bpmcount bpmcount) throws Exception
	{
		String resultMsg=null;		
		try{
			if(bpmcount.getId()==null){
				bpmcountService.save(bpmcount);
				resultMsg=getText("添加","bpmcount");
			}else{
			    bpmcountService.save(bpmcount);
				resultMsg=getText("更新","bpmcount");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得bpmcount分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看bpmcount分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Bpmcount> list=bpmcountService.getAll(new QueryFilter(request,"bpmcountItem"));
		ModelAndView mv=this.getAutoView().addObject("bpmcountList",list);
		return mv;
	}
	
	/**
	 * 删除bpmcount
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除bpmcount")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			bpmcountService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除bpmcount成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑bpmcount
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑bpmcount")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Bpmcount bpmcount=bpmcountService.getById(id);
		
		return getAutoView().addObject("bpmcount",bpmcount)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得bpmcount明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看bpmcount明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Bpmcount bpmcount=bpmcountService.getById(id);
		return getAutoView().addObject("bpmcount", bpmcount);
	}
	/**
	 * 将计算得到的bpmcount对象写入数据库
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("insertBpmcount")
	public void insertBpmcount(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//Long bpmid=RequestUtil.getLong(request,"bpmid");
		List<Long> bpmidList= new ArrayList<Long>();
		
		for (long  i = 1;i<9;i++) {
			
			Long bpmid=i;
			String nodeid = "userTask"+"i";
			String defId = bpmid.toString();
			//List<BpmDefinition> bpmList = bpmDefinitionService.getByDefId(bpmid);
			//BpmDefinition bpmDefinition = bpmList.get(bpmList.size()-1);
			//String defXml = bpmDefinition.getDefXml();
			//掉文月的掉那晓旭的
			Bpmcount bpmcount = new Bpmcount();
			bpmcount.setDefid(defId);
			bpmcount.setNodeid(nodeid);
			bpmcount.setRuns(1l);
			bpmcountService.save(bpmcount);
		}
		
	}
	
	
}