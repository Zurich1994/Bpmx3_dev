package com.hotent.mobile.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.annotion.Action;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.mobile.controller.base.BaseMobileController;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.UserPosition;
import com.hotent.platform.model.system.UserRole;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.system.UserPositionService;
import com.hotent.platform.service.system.UserRoleService;

/**
 * 获取用户数据
 * @author zxh
 *
 */
@Controller
@RequestMapping("/mobile/system/user/")
public class SysMobileUserController extends BaseMobileController {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private UserPositionService userPositionService;

	/**
	 * 取得用户表明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看用户表明细",exectype="管理日志")
	@ResponseBody
	public void get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long userId = RequestUtil.getLong(request, "userId");	

		try {
			SysUser sysUser = sysUserService.getById(userId);
//			String pictureLoad = defaultUserImage;
//			if (sysUser != null) {
//				if (StringUtil.isNotEmpty(sysUser.getPicture())) {
//					pictureLoad = sysUser.getPicture();
//				}
//			}
			List<UserRole> roleList = userRoleService.getByUserId(userId);
			List<UserPosition> posList = userPositionService.getByUserId(userId);
			//List<SysUserOrg> orgList = sysUserOrgService.getOrgByUserId(userId);

			//List<UserPosition> userPosList = userPositionService.getByUserId(userId);
			List<UserPosition> userPosList=userPositionService.getOrgListByUserId(userId);
			//List<SysUserParam> userParamList = sysUserParamService.getByUserId(userId);
			
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user", sysUser);

		this.returnCallbackSuccessData(request, response, map);
		} catch (Exception e) {
			e.printStackTrace();
			this.returnCallbackErrorData(request, response, e.getMessage());
		}
	}
	
	/**
	 * 取得组织表明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getOrg")
	@Action(description = "查看用户表明细",exectype="管理日志")
	@ResponseBody
	public void getOrg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.returnCallbackSuccessData(request, response, "");
	}
	
}
