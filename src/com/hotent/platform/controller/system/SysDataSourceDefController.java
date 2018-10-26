package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.jsonobject.JSONObjectUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysDataSourceDef;
import com.hotent.platform.service.system.SysDataSourceDefService;

/**
 * <pre>
 * 对象功能:SYS_DATA_SOURCE_DEF 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Aschs
 * 创建时间:2014-08-20 11:10:07
 * </pre>
 */
@Controller
@RequestMapping("/platform/system/sysDataSourceDef/")
public class SysDataSourceDefController extends BaseController {
	@Resource
	private SysDataSourceDefService sysDataSourceDefService;

	/**
	 * 添加或更新SYS_DATA_SOURCE_DEF。
	 * 
	 * @param request
	 * @param response
	 * @param sysDataSourceDef
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新SYS_DATA_SOURCE_DEF")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = FileUtil.inputStream2String(request.getInputStream());
		SysDataSourceDef sysDataSourceDef = JSONObjectUtil.toBean(json, SysDataSourceDef.class);

		try {
			if (sysDataSourceDef.getId() == null) {
				sysDataSourceDef.setId(UniqueIdUtil.genId());
				sysDataSourceDefService.add(sysDataSourceDef);
				writeResultMessage(response.getWriter(), "添加成功", ResultMessage.Success);
			} else {
				sysDataSourceDefService.update(sysDataSourceDef);
				writeResultMessage(response.getWriter(), "更新成功", ResultMessage.Success);
			}
		} catch (Exception e) {
			e.printStackTrace();
			writeResultMessage(response.getWriter(), e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得SYS_DATA_SOURCE_DEF分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看SYS_DATA_SOURCE_DEF分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysDataSourceDef> list = sysDataSourceDefService.getAll(new QueryFilter(request, "sysDataSourceDefItem"));
		ModelAndView mv = this.getAutoView().addObject("sysDataSourceDefList", list);

		return mv;
	}

	/**
	 * 删除SYS_DATA_SOURCE_DEF
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除SYS_DATA_SOURCE_DEF")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			sysDataSourceDefService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除SYS_DATA_SOURCE_DEF成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑SYS_DATA_SOURCE_DEF
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑SYS_DATA_SOURCE_DEF")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		String returnUrl = RequestUtil.getPrePage(request);
		return getAutoView().addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得SYS_DATA_SOURCE_DEF明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看SYS_DATA_SOURCE_DEF明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		SysDataSourceDef sysDataSourceDef = sysDataSourceDefService.getById(id);
		return getAutoView().addObject("sysDataSourceDef", sysDataSourceDef);
	}

	@RequestMapping("getById")
	@ResponseBody
	public SysDataSourceDef getById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Long id = RequestUtil.getLong(request, "id");
		SysDataSourceDef sysDataSourceDef = sysDataSourceDefService.getById(id);
		return sysDataSourceDef;
	}
	
	@RequestMapping("getAll")
	@ResponseBody
	public List<SysDataSourceDef> getAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<SysDataSourceDef> sysDataSourceDefs = sysDataSourceDefService.getAll(); 
		return sysDataSourceDefs;
	}

	/**
	 * 流程url表单 绑定的表单明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	@Action(description = "表单明细")
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "id");
		SysDataSourceDef sysDataSourceDef = sysDataSourceDefService.getById(id);
		return getAutoView().addObject("sysDataSourceDef", sysDataSourceDef);
	}

	/**
	 * 流程url表单 绑定的表单编辑页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modify")
	public ModelAndView modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "id");
		SysDataSourceDef sysDataSourceDef = sysDataSourceDefService.getById(id);
		return getAutoView().addObject("sysDataSourceDef", sysDataSourceDef);
	}

	/**
	 * 
	 * 根据类名获取Setter字段，以json的格式返回,json格式定义如下 [{"name":"属性名称","comment":"描述","type":"值类型","baseAttr":"是否基础属性（是否必填）0：false,1:true"},{...},...]
	 * 
	 * @param request
	 * @param response
	 *            void
	 * @return
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping("getSetterFields")
	@ResponseBody
	public JSONArray getSetterFields(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String classPath = RequestUtil.getString(request, "classPath");

		return sysDataSourceDefService.getHasSetterFieldsJsonArray(classPath);
	}
	
	
}
