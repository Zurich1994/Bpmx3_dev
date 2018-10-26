package com.hotent.platform.service.system;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.SysDataSourceDef;
import com.hotent.platform.dao.system.SysDataSourceDefDao;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.core.util.StringUtil;
import com.hotent.core.bpm.model.ProcessCmd;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

/**
 * <pre>
 * 对象功能:SYS_DATA_SOURCE_DEF Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Aschs
 * 创建时间:2014-08-20 11:10:07
 * </pre>
 */
@Service
public class SysDataSourceDefService extends BaseService<SysDataSourceDef> {
	@Resource
	private SysDataSourceDefDao dao;

	@Resource
	private ProcessRunService processRunService;

	public SysDataSourceDefService() {
	}

	@Override
	protected IEntityDao<SysDataSourceDef, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 重写getAll方法绑定流程runId
	 * 
	 * @param queryFilter
	 */
	public List<SysDataSourceDef> getAll(QueryFilter queryFilter) {
		List<SysDataSourceDef> sysDataSourceDefList = super.getAll(queryFilter);
		List<SysDataSourceDef> sysDataSourceDefs = new ArrayList<SysDataSourceDef>();
		for (SysDataSourceDef sysDataSourceDef : sysDataSourceDefList) {
			ProcessRun processRun = processRunService.getByBusinessKey(sysDataSourceDef.getId().toString());
			if (BeanUtils.isNotEmpty(processRun)) {
				sysDataSourceDef.setRunId(processRun.getRunId());
			}
			sysDataSourceDefs.add(sysDataSourceDef);
		}
		return sysDataSourceDefs;
	}

	/**
	 * 流程处理器方法 用于处理业务数据
	 * 
	 * @param cmd
	 * @throws Exception
	 */
	public void processHandler(ProcessCmd cmd) throws Exception {
		Map data = cmd.getFormDataMap();
		if (BeanUtils.isNotEmpty(data)) {
			String json = data.get("json").toString();
			SysDataSourceDef sysDataSourceDef = getSysDataSourceDef(json);
			if (StringUtil.isEmpty(cmd.getBusinessKey())) {
				Long genId = UniqueIdUtil.genId();
				sysDataSourceDef.setId(genId);
				this.add(sysDataSourceDef);
			} else {
				sysDataSourceDef.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(sysDataSourceDef);
			}
			cmd.setBusinessKey(sysDataSourceDef.getId().toString());
		}
	}

	/**
	 * 根据json字符串获取SysDataSourceDef对象
	 * 
	 * @param json
	 * @return
	 */
	public SysDataSourceDef getSysDataSourceDef(String json) {
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if (StringUtil.isEmpty(json))
			return null;
		JSONObject obj = JSONObject.fromObject(json);
		SysDataSourceDef sysDataSourceDef = (SysDataSourceDef) JSONObject.toBean(obj, SysDataSourceDef.class);
		return sysDataSourceDef;
	}

	/**
	 * TODO方法名称描述
	 * 
	 * @param classPath
	 * @return JSONArray
	 * @exception
	 * @since 1.0.0
	 */
	public JSONArray getHasSetterFieldsJsonArray(String classPath) {
		JSONArray jsonArray = new JSONArray();
		for (Field field : getHasSetterFields(classPath)) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("name", field.getName());
			jsonObject.accumulate("comment", field.getName());
			jsonObject.accumulate("type", field.getType().getName());
			jsonObject.accumulate("baseAttr", "0");
			jsonArray.add(jsonObject);
		}

		// System.out.println(jsonArray.toString());

		return jsonArray;
	}

	/**
	 * 获取类名为classPath的所有有setting的字段
	 * 
	 * @param classPath
	 * @return List&lt;Field>
	 * @exception
	 * @since 1.0.0
	 */
	private List<Field> getHasSetterFields(String classPath) {
		List<Field> fields = new ArrayList<Field>();

		try {
			Class<?> _class = Class.forName(classPath);
			for (Field field : _class.getDeclaredFields()) {

				if (checkHasSetter(_class, field)) {
					// System.out.println(field.getName()+":"+field.getType().getName());
					fields.add(field);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return fields;
	}

	/**
	 * 检查资格字段在_class类中是否有setter
	 * 
	 * @param _class
	 * @param field
	 * @return boolean
	 * @exception
	 * @since 1.0.0
	 */
	private boolean checkHasSetter(Class<?> _class, Field field) {
		boolean b = false;

		for (Method method : _class.getMethods()) {
			if (!method.getName().startsWith("set"))
				continue;

			if (method.getName().replace("set", "").toUpperCase().equals(field.getName().toUpperCase())) {
				b = true;
			}
		}

		return b;
	}
}
