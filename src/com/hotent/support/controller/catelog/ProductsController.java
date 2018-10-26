
package com.hotent.support.controller.catelog;

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

import com.hotent.support.model.catelog.Products;
import com.hotent.support.service.catelog.ProductsService;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.service.bpm.ProcessRunService;
/**
 * 对象功能:support产品表 控制器类
 */
@Controller
@RequestMapping("/support/catelog/products/")
public class ProductsController extends BaseController
{
	@Resource
	private ProductsService productsService;
	@Resource
	private ProcessRunService processRunService;
	/**
	 * 添加或更新support产品表。
	 * @param request
	 * @param response
	 * @param products 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新support产品表")
	public void save(HttpServletRequest request, HttpServletResponse response,Products products) throws Exception
	{
		String resultMsg=null;		
		try{
			if(products.getId()==null){
				Long id=UniqueIdUtil.genId();
				products.setId(id);
				productsService.add(products);
				resultMsg=getText("添加","support产品表");
			}else{
			    productsService.update(products);
				resultMsg=getText("更新","support产品表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得support产品表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看support产品表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		//List<Products> list=productsService.getAll(new QueryFilter(request,"productsItem"));
		String CATEGORYID = RequestUtil.getString(request, "id");
		List<Products> list = productsService.getByCATEGORYID(CATEGORYID);
		ModelAndView mv=this.getAutoView().addObject("productsList",list).addObject("id", CATEGORYID);
		
		return mv;
	}
	
	/**
	 * 删除support产品表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除support产品表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			productsService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除support产品表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑support产品表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑support产品表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		Long runId=0L;
		ProcessRun processRun=processRunService.getByBusinessKey(id.toString());
		if(BeanUtils.isNotEmpty(processRun)){
			runId=processRun.getRunId();
		}
		String returnUrl=RequestUtil.getPrePage(request);
		Products products=productsService.getById(id);
		
		return getAutoView().addObject("products",products)
							.addObject("runId", runId)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得support产品表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看support产品表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Products products=productsService.getById(id);
		Long runId=0L;
		ProcessRun processRun=processRunService.getByBusinessKey(id.toString());
		if(BeanUtils.isNotEmpty(processRun)){
			runId=processRun.getRunId();
		}
		return getAutoView().addObject("products", products).addObject("runId", runId);
	}
	
	/**
	 * 流程url表单 绑定的表单明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	@Action(description="表单明细")
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Products products = productsService.getById(id);	
		return getAutoView().addObject("products", products);
	}
	
	/**
	 * 流程url表单 绑定的表单编辑页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modify")
	public ModelAndView modify(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Products products = productsService.getById(id);	
		return getAutoView().addObject("products", products);
	}
	
	
	
	/**
	 * 启动流程
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("run")
	@Action(description="启动流程")
	public void run(HttpServletRequest request, HttpServletResponse response,Products products) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		Integer isList=RequestUtil.getInt(request, "isList",0);
		ProcessCmd processCmd=new ProcessCmd();
		processCmd.setFlowKey("undefined");
		processCmd.setUserAccount(ContextUtil.getCurrentUser().getAccount());
		try {
			if(id!=0L){
				processCmd.setBusinessKey(Long.toString(id));
				processRunService.startProcess(processCmd);
				if(isList==0){
					productsService.update(products);
				}
			}else{
				Long genId=UniqueIdUtil.genId();
				processCmd.setBusinessKey(genId.toString());
				products.setId(genId);
				processRunService.startProcess(processCmd);
				productsService.add(products);
			}
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Success, "启动流程成功"));
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Fail, "启动流程失败"));
		}
	}
}
