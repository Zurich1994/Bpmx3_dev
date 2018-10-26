package com.hotent.platform.controller.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hotent.core.annotion.Action;
import net.sf.json.JSONObject;
import com.hotent.core.web.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;

import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysQueryField;
import com.hotent.platform.model.system.SysQuerySetting;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.service.system.SysQueryFieldService;
import com.hotent.platform.service.system.SysQuerySettingService;

/**
 * <pre>
 * 对象功能:SYS_QUERY_FIELD 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Aschs
 * 创建时间:2014-05-22 12:47:40
 * </pre>
 */
@Controller
@RequestMapping("/platform/system/sysQueryField/")
public class SysQueryFieldController extends BaseController {
	@Resource
	private SysQueryFieldService sysQueryFieldService;
	@Resource
	private SysQuerySettingService sysQuerySettingService;
	@Resource
	private GlobalTypeService globalTypeService;

	/**
	 * 添加或更新SYS_QUERY_FIELD。
	 * 
	 * @param request
	 * @param response
	 * @param sysQueryField
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新SYS_QUERY_FIELD")
	public void save(HttpServletRequest request, HttpServletResponse response, SysQueryField sysQueryField) throws Exception {
		String resultMsg = null;

		Long sqlId = RequestUtil.getLong(request, "sqlId", 0L);

		try {
			String jsonArrStr = request.getParameter("jsonArrStr");
			List<SysQueryField> sysQueryFie = sysQueryFieldService.getSysQueryFieldArr(jsonArrStr);

			sysQueryFieldService.saveOrUpdate(sysQueryFie);

			if (sysQueryFie != null && !sysQueryFie.isEmpty() && sqlId != 0L) {
				SysQuerySetting sysQuerySetting = sysQuerySettingService.getBySqlId(sqlId);
				if (sysQuerySetting != null) {
					// 同步json
					//sysQuerySettingService.syncSettingAndField(sysQuerySetting, sysQueryFie);
					//xinjia
					sysQuerySettingService.syncSettingAndField(sysQuerySetting, sysQueryFie,null);
					//xinjia
				}
			}

			resultMsg = getText("更新成功", "SYS_QUERY_SQL");
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
		} catch (Exception e) {
			e.printStackTrace();
			writeResultMessage(response.getWriter(), resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得SYS_QUERY_FIELD分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看SYS_QUERY_FIELD分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long sqlId = RequestUtil.getLong(request, "sqlId", 0L);
		// 获取fields
		List<SysQueryField> list = sysQueryFieldService.getListBySqlId(sqlId);
		// getAutoView()有一定的局限性，无法处理jsp:include返回请求
		// return new ModelAndView("/platform/system/sysQueryFieldList.jsp").addObject("sysQueryFields", list);
		return getAutoView().addObject("sysQueryFields", list);
	}

	/**
	 * 设置field的详细信息，控件类型之类的
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editDetail")
	@Action(description = "查看SYS_QUERY_FIELD分页列表")
	public ModelAndView editDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);

		List<GlobalType> globalTypes = globalTypeService.getByCatKey(GlobalType.CAT_DIC, true);

		SysQueryField sysQueryField = sysQueryFieldService.getById(id);

		return getAutoView().addObject("sysQueryField", sysQueryField).addObject("globalTypes", globalTypes);
	}

	@RequestMapping("saveDetail")
	@Action(description = "更新SYS_QUERY_FIELD的控件和格式化")
	public void saveDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String jsonStr = RequestUtil.getString(request, "jsonStr");
			JSONObject json = JSONObject.fromObject(jsonStr);

			Long id = json.getLong("id");
			SysQueryField sysQueryField = sysQueryFieldService.getById(id);

			sysQueryField.setFormat(json.getString("format"));
			sysQueryField.setControlType(new Short(json.getInt("controlType") + ""));
			sysQueryField.setControlContent(json.getString("controlContent"));

			sysQueryFieldService.update(sysQueryField);
			
			//同步setting的json跟field字段
			SysQuerySetting sysQuerySetting=sysQuerySettingService.getBySqlId(sysQueryField.getSqlId());
			sysQueryFieldService.syncConditionControlAndField(sysQueryField, sysQuerySetting);
			
			writeResultMessage(response.getWriter(), "更新成功", ResultMessage.Success);
		} catch (Exception e) {
			e.printStackTrace();
			writeResultMessage(response.getWriter(), "更新失败", ResultMessage.Fail);
		}
	}
}
