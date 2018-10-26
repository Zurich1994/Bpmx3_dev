package com.hotent.platform.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.model.CurrentUser;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.system.SysIndexLayoutManageDao;
import com.hotent.platform.model.system.SysIndexColumn;
import com.hotent.platform.model.system.SysIndexLayoutManage;
import com.hotent.platform.model.system.SysObjRights;
import com.hotent.platform.service.system.impl.curuser.OrgSubUserService;
import com.hotent.platform.service.util.ServiceUtil;

/**
 * <pre>
 * 对象功能:布局管理 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-18 15:40:13
 * </pre>
 */
@Service
public class SysIndexLayoutManageService extends
		BaseService<SysIndexLayoutManage> {
	@Resource
	private SysIndexLayoutManageDao dao;
	@Resource
	private SysIndexColumnService sysIndexColumnService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private PositionService positionService;
	@Resource
	private CurrentUserService currentUserService;
	@Resource
	private OrgSubUserService orgSubUserService;
	
	public SysIndexLayoutManageService() {
	}

	@Override
	protected IEntityDao<SysIndexLayoutManage, Long> getEntityDao() {
		return dao;
	}

	public SysIndexLayoutManage getLayoutList(Long id,
			List<SysIndexColumn> columnList) {
		SysIndexLayoutManage sysIndexLayoutManage = dao.getById(id);
		if (BeanUtils.isEmpty(sysIndexLayoutManage))
			return getDefaultIndexLayout();
		sysIndexLayoutManage.setDesignHtml(sysIndexColumnService.parserDesignHtml(
				sysIndexLayoutManage.getDesignHtml(), columnList));
		return sysIndexLayoutManage;
	}
	


	public String getDefaultDesignHtml() {
		return   "<div class=\"lyrow ui-draggable\" style=\"display: block;\">"
				+ "<a href=\"#close\" class=\"remove label label-danger\"><i class=\"glyphicon-remove glyphicon\"></i> 删除</a>"
				+ "<span class=\"drag label label-default\"><i class=\"glyphicon glyphglyphicon glyphicon-move\"></i> 拖动</span>"
				+ "<div class=\"preview\"><input type=\"text\" value=\"一列(12)\" readonly=\"readonly\" class=\"form-control\"></div>"
				+ "<div class=\"view\">"
				+ "<div class=\"row clearfix\">"
				+ "<div class=\"col-md-12 column ui-sortable\"></div>"
				+ "</div>"
				+ "</div>"
				+ "</div>";
	}
	
	private SysIndexLayoutManage getDefaultIndexLayout() {
		String designHtml  = getDefaultDesignHtml();
		SysIndexLayoutManage sysIndexLayoutManage  = new SysIndexLayoutManage();
		sysIndexLayoutManage.setDesignHtml(designHtml);
		sysIndexLayoutManage.setIsDef((short) 0);
		return sysIndexLayoutManage;
	}

	/**
	 * 当前组织有权限的布局
	 * @param userId
	 * @return
	 */
	public String getHasRightsLayout() {
		//取得当前的用户的组织的归属组织
		CurrentUser currentUser = ServiceUtil.getCurrentUser();
		List<Long> orgIds = orgSubUserService.getByCurUser(currentUser);
		List<SysIndexLayoutManage> list = dao.getManageLayout(StringUtils.join(orgIds,","),(short)1);
		if(BeanUtils.isNotEmpty(list) && list.size()>0)
			return list.get(0).getTemplateHtml();
		return null;
	}
	
	/**
	 * 管理员的布局
	 * @return
	 */
	public String getManagerLayout() {
		List<SysIndexLayoutManage> list = dao.getManageLayout(null,(short)1);
		if(BeanUtils.isNotEmpty(list) && list.size()>0)
			return list.get(0).getTemplateHtml();
		return null;
	}

	/**
	 * 找自己所属子组织或公司的我有权限的布局 按默认，排序排序
	 * @return
	 */
	public String getMyHasRightsLayout() {
		Map<String, List<Long>> relationMap= currentUserService.getUserRelation(ServiceUtil.getCurrentUser());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("relationMap", relationMap);
		params.put("objType", SysObjRights.RIGHT_TYPE_INDEX_MANAGE);
		List<SysIndexLayoutManage> list = dao.getBySqlKey("getByUserIdFilter",params);
		if(BeanUtils.isNotEmpty(list) && list.size()>0)
			return list.get(0).getTemplateHtml();
		return null;
	}
	
	/**
	 * 取得有权限的列表
	 * @param filter
	 * @return
	 */
	public List<SysIndexLayoutManage> getList(QueryFilter filter) {
		Map<String, List<Long>> params= currentUserService.getUserRelation(ServiceUtil.getCurrentUser());
	
		filter.addFilter("relationMap", params);
		filter.addFilter("objType", SysObjRights.RIGHT_TYPE_INDEX_MANAGE);
		// 根据流程授权获取流程。
		return dao.getByUserIdFilter(filter);
	}

	/**
	 * 保存数据
	 * @param sysIndexLayoutManage
	 * @param type
	 */
	public void save(SysIndexLayoutManage sysIndexLayoutManage, int type) {
		//修改这组织下 或者空下默认
		short isDef = sysIndexLayoutManage.getIsDef();
		if(isDef == 1){
			Long orgId = sysIndexLayoutManage.getOrgId();
			dao.updateIsDef(orgId);
		}
		if(type == 0)
			dao.add(sysIndexLayoutManage);
		else
			dao.update(sysIndexLayoutManage);
		
	}



}
