package com.hotent.platform.controller.console;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.MessageSend;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.Resources;
import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.DesktopMycolumnService;
import com.hotent.platform.service.system.MessageSendService;
import com.hotent.platform.service.system.PositionService;
import com.hotent.platform.service.system.ResourcesService;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.platform.service.system.SubSystemUtil;
import com.hotent.platform.service.system.SysIndexMyLayoutService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysPropertyService;
import com.hotent.platform.service.system.UserPositionService;

@Controller
@RequestMapping("/platform/console")
public class MainController extends BaseController {
	@Resource
	private SubSystemService subSystemService;
	@Resource
	private ResourcesService resourcesService;
	@Resource
	private DesktopMycolumnService desktopMycolumnService;
	@Resource
	private MessageSendService msgSendService;
	@Resource
	private FreemarkEngine freemarkEngine;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private UserPositionService userPositionService;
	@Resource
	private PositionService positionService;
	@Resource
	private SysIndexMyLayoutService sysIndexMylayoutService;
	/**
	 * 切换组织
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping("turnToMain")
	public ModelAndView turnToMain(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.debug("....................................跳转....................................");
		return this.getView("console", "turnToMain");
	}

	/**
	 * 控制台页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("main")
	public ModelAndView main(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//当前用户
		SysUser curUser = ContextUtil.getCurrentUser();
		Long curUserId = curUser.getUserId();
		//当前有权限的子系统
		List<SubSystem> subSystemList = subSystemService.getByUser(curUser);
		SubSystem currentSystem = SubSystemUtil.getCurrentSystem(request);

		// 得到个人未读信息
		List<MessageSend> list = msgSendService.getNotReadMsg(curUserId);

		//当前用户包含组织
		List<SysOrg> sysOrgs = sysOrgService.getOrgsByUserId(curUserId);
		//当前用户组织
		SysOrg curSysOrg = ContextUtil.getCurrentOrg();
		
		//当前用户包含岗位
		List<Position> positions = positionService.getByUserId(curUserId);
		//当前用户岗位
		Position curPosition = ContextUtil.getCurrentPos();
		

		// 取个性话设置参数并设置logo路径
		String skinStyle = ContextUtil.getCurrentUserSkin(request);
		String uiStyle=SysPropertyService.getByAlias("UI_STYLE", "default");
		// 添加内容
	//	String mainViewName = "extendIndex".equals(configproperties.getProperty("UI_STYLE"))?"mainExtendIndex":"main";
		String mainViewName = "extendIndex".equals(uiStyle)?"mainExtendIndex":"main";
	
	
	
		//前系统有系统
		if(currentSystem!=null ){
			//以前有选择系统,但是现在即不再拥用该系统的权限,重新选择系统
			if(!subSystemList.contains(currentSystem))
				mainViewName = "selectCurrSys";	

		}else{		
			//只有一个系统有权限
			if(subSystemList!=null&&subSystemList.size()==1){
				currentSystem = subSystemList.get(0);
				subSystemService.setCurrentSystem(currentSystem.getSystemId(),request, response);
			}else
				//以前没有选择系统
				mainViewName = "selectCurrSys";
		}
		if(currentSystem != null){
			String logo = StringUtil.formatParamMsg(currentSystem.getLogo(), skinStyle);
			
			currentSystem.setLogo(logo);
		}
		
		return this.getView("console",mainViewName)
			.addObject("skinStyle",skinStyle)
			.addObject("currentSystem", currentSystem)
			.addObject("currentSystemId", currentSystem == null?null:currentSystem.getSystemId())
			.addObject("subSystemList",subSystemList)
			.addObject("readMsg", list.size())
			.addObject("userId",curUserId)
			.addObject("sysOrgs",sysOrgs)
			.addObject("positions",positions)
			.addObject("curSysOrg",curSysOrg)
			.addObject("curPosition",curPosition);

	}

	/**
	 * 控制台页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("home")
	public ModelAndView home(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long userId = ContextUtil.getCurrentUserId();
		String html = sysIndexMylayoutService.getMyIndexData(userId);

		return this.getView("console", "home")
				.addObject("html",html);
	}

	/**
	 * 获取桌面栏目对应模块的更多信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getResourceNode")
	@ResponseBody
	public Resources getResourceNode(HttpServletRequest request,
			HttpServletResponse response) {
		Resources resource = null;
		try {
			String columnUrl = RequestUtil.getString(request, "columnUrl");
			resource = resourcesService.getByUrl(columnUrl);
		} catch (Exception e) {
			return null;
		}
		return resource;
	}

	/**
	 * 设置默认系统
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping("saveCurrSys")
	public void saveCurrSys(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Long systemId = RequestUtil.getLong(request, "systemId");
		subSystemService.setCurrentSystem(systemId, request, response);
		response.sendRedirect(request.getContextPath()
				+ "/platform/console/main.ht");
	}

	/**
	 * 切换岗位
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping("switchCurrentOrg")
	public void switchCurrentPos(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Long posId = RequestUtil.getLong(request, "posId");
		ContextUtil.setCurrentPos(posId);
		response.sendRedirect(request.getContextPath()
				+ "/platform/console/main.ht");
	}

	/**
	 * 切换语言
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	@RequestMapping("switchSysLanguage")
	public void switchSysLanguage(HttpServletRequest request,HttpServletResponse response) throws IOException {	
		String language=RequestUtil.getString(request, "language");
		String[] lan = language.split("_");
		Locale local = new Locale(lan[0],lan[1]);
		ContextUtil.setLocale(local);
        SessionLocaleResolver sessionLocaleResolver = (SessionLocaleResolver) AppUtil.getBean(SessionLocaleResolver.class);
        sessionLocaleResolver.setLocale(request, response, local);
        response.sendRedirect(request.getContextPath()+ "/platform/console/main.ht");
	}

	/**
	 * 取得总分类表用于显示树层次结构的分类可以分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 *             -------------
	 */
	@RequestMapping("getSysRolResTreeData")
	// @Action(description="取得总分类表树形数据")
	@ResponseBody
	public List<Resources> getSysRolResTreeData(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		SubSystem currentSystem=SubSystemUtil.getCurrentSystem(request);
		List<Resources> resourcesList=resourcesService.getSysMenu(currentSystem,ContextUtil.getCurrentUser());
		ResourcesService.addIconCtxPath(resourcesList, request.getContextPath());
		return resourcesList;
	}

	/**
	 * 验证expression中的脚本
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getValidateResult")
	@ResponseBody
	public Object getValidateResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String script = RequestUtil.getString(request, "script");
		GroovyScriptEngine engine = (GroovyScriptEngine) AppUtil
				.getBean(GroovyScriptEngine.class);

		Map<String, Object> reObj = new HashMap<String, Object>();
		try {
			Object result = engine.executeObject(script, null);
			reObj.put("hasError", false);
			reObj.put("errorMsg", "");

			if (result != null) {
				reObj.put("result", result);
				reObj.put("resultType", result.getClass().getName());
			}
		} catch (Exception ex) {
			reObj.put("hasError", true);
			reObj.put("errorMsg", ex.getMessage());
		}
		return reObj;
	}
}
