package com.hotent.platform.controller.mobile;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.hotent.platform.model.mobile.MobileUserInfo;
import com.hotent.platform.service.mobile.MobileUserInfoService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:MOBILE_USER_INFO 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-01-06 11:55:09
 */
@Controller
@RequestMapping("/platform/mobile/mobileUserInfo/")
public class MobileUserInfoController extends BaseController
{
	@Resource
	private MobileUserInfoService mobileUserInfoService;
	
	/**
	 * 添加或更新MOBILE_USER_INFO。
	 * @param request
	 * @param response
	 * @param mobileUserInfo 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新MOBILE_USER_INFO")
	@ResponseBody
	public Object save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Map responseMap = new HashMap();
		String resultMsg=null;		
		MobileUserInfo mobileUserInfo=getFormObject(request);
		try{
			if(mobileUserInfo.getUserid()==null||mobileUserInfo.getUserid()==0){
				mobileUserInfo.setUserid(UniqueIdUtil.genId());
				mobileUserInfoService.add(mobileUserInfo);
				responseMap.put("msg", getText("MOBILE_USER_INFO"));
			}else{
			    mobileUserInfoService.update(mobileUserInfo);
			    responseMap.put("msg", getText("MOBILE_USER_INFO"));
			}
			responseMap.put("success", true);
			return responseMap;
		}catch(Exception e){
			responseMap.put("success", false);
			responseMap.put("msg", resultMsg+","+e.getMessage());
			return responseMap;
		}
	}
	
	/**
	 * 取得 MobileUserInfo 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected MobileUserInfo getFormObject(HttpServletRequest request) throws Exception {
    
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
    
		String json=RequestUtil.getString(request, "json");
		JSONObject obj = JSONObject.fromObject(json);
		
		MobileUserInfo mobileUserInfo = (MobileUserInfo)JSONObject.toBean(obj, MobileUserInfo.class);
		
		return mobileUserInfo;
    }
	
	/**
	 * 取得MOBILE_USER_INFO分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看MOBILE_USER_INFO分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<MobileUserInfo> list=mobileUserInfoService.getAll(new QueryFilter(request,"mobileUserInfoItem"));
		ModelAndView mv=this.getAutoView().addObject("mobileUserInfoList",list);
		
		return mv;
	}
	
	/**
	 * 删除MOBILE_USER_INFO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除MOBILE_USER_INFO")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "userid");
			mobileUserInfoService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除MOBILE_USER_INFO成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑MOBILE_USER_INFO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑MOBILE_USER_INFO")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long userid=RequestUtil.getLong(request,"userid");
		String returnUrl=RequestUtil.getPrePage(request);
		MobileUserInfo mobileUserInfo=mobileUserInfoService.getById(userid);
		
		return getAutoView().addObject("mobileUserInfo",mobileUserInfo).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得MOBILE_USER_INFO明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看MOBILE_USER_INFO明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long userid=RequestUtil.getLong(request,"userid");
		MobileUserInfo mobileUserInfo = mobileUserInfoService.getById(userid);	
		return getAutoView().addObject("mobileUserInfo", mobileUserInfo);
	}
}
