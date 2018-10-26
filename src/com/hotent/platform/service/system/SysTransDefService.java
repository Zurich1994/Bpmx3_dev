package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.SysTransDef;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.dao.system.SysTransDefDao;
import com.hotent.core.service.BaseService;

/**
 * <pre>
 * 对象功能:sys_trans_def Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-04-16 11:15:55
 * </pre>
 */
@Service
public class SysTransDefService extends BaseService<SysTransDef> {
	@Resource
	private SysTransDefDao dao;
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private SysUserService sysUserService;
	@Resource
	GroovyScriptEngine groovyScriptEngine;
	public static String IDS = "{ids}";
	public static String NAMES = "{names}";
	public static String TARGET_PERSONID = "{targetPersonId}";
	public static String TARGET_PERSONNAME = "{targetPersonName}";
	public static String AUTHOR_ID = "{authorId}";
	public static String AUTHOR_NAME = "{authorName}";

	public SysTransDefService() {
	}

	@Override
	protected IEntityDao<SysTransDef, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 保存 sys_trans_def 信息
	 * 
	 * @param sysTransDef
	 */
	public void save(SysTransDef sysTransDef) {
		Long id = sysTransDef.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			sysTransDef.setId(id);

			SysUser sysUser = ContextUtil.getCurrentUser();
			sysTransDef.setCreator(sysUser.getUsername());
			sysTransDef.setCreatorId(sysUser.getUserId());
			sysTransDef.setCreateTime(new Date());

			this.add(sysTransDef);
		} else {
			this.update(sysTransDef);
		}
	}

	/**
	 * 生成ztree格式的json数组
	 * 
	 * @param list
	 * @return List<JSONObject>
	 * @exception
	 * @since 1.0.0
	 */
	public List<JSONObject> treeListJson(List<SysTransDef> list, String authorId) {
		List<JSONObject> result = new ArrayList<JSONObject>();
		JSONObject jo = new JSONObject();
		jo.put("id", "1");
		jo.put("parentId", "0");
		jo.put("name", "所属权限");
		result.add(jo);
		for (SysTransDef std : list) {
			jo = new JSONObject();
			jo.put("id", std.getId());
			jo.put("parentId", "1");
			List l = null;
			try {
				l = excuteSelectSql(std, authorId);
			} catch (Exception e) {
				continue;
			}
			jo.put("count", l.size());
			jo.put("name", std.getName() + "(" + l.size() + ")");
			if (l.size() <= 0) {// 长度为0就不显示了
				continue;
			}
			result.add(jo);
		}
		return result;
	}

	public List excuteSelectSql(SysTransDef sysTransDef, String authorId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = groovyScriptEngine.executeString(sysTransDef
				.getSelectSql().replace(AUTHOR_ID, authorId), map);
		if (StringUtil.isEmpty(sql)) {
			return null;
		}
		List l = jdbcTemplate.queryForList(sql);
		return l;
	}

	public boolean excuteUpdateSql(SysTransDef sysTransDef, SysUser author,
			SysUser targetPerson, String selectedItem) {
		try {

			JSONArray selectedItemJa = JSONArray.fromObject(selectedItem);
			StringBuffer ids = new StringBuffer();
			StringBuffer names = new StringBuffer();
			for (int i = 0; i < selectedItemJa.size(); i++) {
				if (ids.length() != 0) {
					ids.append(",");
					names.append(",");
				}
				JSONObject jo = selectedItemJa.getJSONObject(i);
				ids.append(jo.getString("id"));
				names.append(jo.getString("name"));
			}

			// 替代sql
			String updateSql = sysTransDef
					.getUpdateSql()
					.replace(AUTHOR_ID, author.getUserId().toString())
					.replace(TARGET_PERSONID,
							targetPerson.getUserId().toString())
					.replace(IDS, ids.toString())
					.replace(NAMES, names.toString())
					.replace(AUTHOR_NAME, author.getFullname())
					.replace(TARGET_PERSONNAME, targetPerson.getFullname())
					.replace(AUTHOR_ID, author.getUserId().toString())
					.replace(TARGET_PERSONID,
							targetPerson.getUserId().toString())
					.replace(IDS, ids.toString())
					.replace(AUTHOR_NAME, author.getFullname())
					.replace(TARGET_PERSONNAME, targetPerson.getFullname());

			String sql = groovyScriptEngine.executeString(updateSql,
					new HashMap<String, Object>());
			if (StringUtil.isNotEmpty(sql)) {
				jdbcTemplate.execute(sql);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getLogContent(SysTransDef sysTransDef, String authorId,
			String targetPersonId, String selectedItem) {
		// 开始写日志
		String content = sysTransDef.getLogContent();
		SysUser author = sysUserService.getById(Long.parseLong(authorId));
		SysUser target = sysUserService.getById(Long.parseLong(targetPersonId));

		JSONArray selectedItemJa = JSONArray.fromObject(selectedItem);
		StringBuffer names = new StringBuffer();
		for (int i = 0; i < selectedItemJa.size(); i++) {
			if (names.length() != 0) {
				names.append(",");
			}
			JSONObject jo = selectedItemJa.getJSONObject(i);
			names.append(jo.getString("name"));
		}

		content = content
				.replace(SysTransDefService.NAMES, names.toString())
				.replace(SysTransDefService.AUTHOR_NAME, author.getFullname())
				.replace(SysTransDefService.TARGET_PERSONNAME,
						target.getFullname());

		return content;
	}

}
