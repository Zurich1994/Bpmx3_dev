package com.hotent.platform.controller.system;

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
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.util.StringUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import com.hotent.platform.model.system.SysUser;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.jsonobject.JSONObjectUtil;

import com.hotent.platform.model.system.SysObjLog;
import com.hotent.platform.service.system.SysObjLogService;
import com.hotent.core.web.ResultMessage;

/**
 * <pre>
 * 对象功能:SYS_OBJ_LOG 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-04-27 11:09:44
 * </pre>
 */
@Controller
@RequestMapping("/platform/system/sysObjLog/")
public class SysObjLogController extends BaseController {
	public static String OBJ_TYPE_SYSTRANSDEF="transDef";
	
	@Resource
	private SysObjLogService sysObjLogService;

	/**
	 * 添加或更新SYS_OBJ_LOG。
	 * 
	 * @param request
	 * @param response
	 * @param sysObjLog
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新SYS_OBJ_LOG")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = FileUtil.inputStream2String(request.getInputStream());
		SysObjLog sysObjLog = JSONObjectUtil.toBean(json, SysObjLog.class);
		String resultMsg = null;
		try {
			if (sysObjLog.getId() == null || sysObjLog.getId() == 0) {
				resultMsg = getText("添加成功", "SYS_OBJ_LOG");
			} else {
				resultMsg = getText("更新成功", "SYS_OBJ_LOG");
			}
			sysObjLogService.save(sysObjLog);
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得SYS_OBJ_LOG分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看SYS_OBJ_LOG分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysObjLog> list = sysObjLogService.getAll(new QueryFilter(request, "sysObjLogItem"));
		ModelAndView mv = this.getAutoView().addObject("sysObjLogList", list);
		return mv;
	}

	/**
	 * 删除SYS_OBJ_LOG
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除SYS_OBJ_LOG")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			sysObjLogService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除SYS_OBJ_LOG成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑SYS_OBJ_LOG
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑SYS_OBJ_LOG")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		SysObjLog sysObjLog = sysObjLogService.getById(id);

		return getAutoView().addObject("sysObjLog", sysObjLog).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得SYS_OBJ_LOG明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看SYS_OBJ_LOG明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		SysObjLog sysObjLog = sysObjLogService.getById(id);
		return getAutoView().addObject("sysObjLog", sysObjLog);
	}

}
