package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.model.bpm.ProcessRun;
import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;

import javax.annotation.Resource;

import org.apache.cxf.binding.corba.wsdl.Array;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.bpm.NodeMsgTemplate;
import com.hotent.platform.model.form.BpmFormData;
import com.hotent.platform.model.form.SubTable;
import com.hotent.platform.dao.bpm.NodeMsgTemplateDao;
import com.hotent.core.service.BaseService;

/**
 * <pre>
 * 对象功能:bpm_nodemsg_template Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-05-27 18:03:14
 * </pre>
 */
@Service
public class NodeMsgTemplateService extends BaseService<NodeMsgTemplate> {
	@Resource
	private NodeMsgTemplateDao dao;
	@Resource
	private FreemarkEngine freemarkEngine;

	public NodeMsgTemplateService() {
	}

	@Override
	protected IEntityDao<NodeMsgTemplate, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 保存 bpm_nodemsg_template 信息
	 * 
	 * @param nodeMsgTemplate
	 */
	public void save(NodeMsgTemplate nodeMsgTemplate) {
		Long id = nodeMsgTemplate.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			nodeMsgTemplate.setId(id);
			this.add(nodeMsgTemplate);
		} else {
			this.update(nodeMsgTemplate);
		}
	}

	public NodeMsgTemplate getObject(String nodeId, Long defId, Long parentDefId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (defId != null) {
			map.put("defId", defId);
		}
		if (nodeId != null) {
			map.put("nodeId", nodeId);
		}
		if (parentDefId != null) {
			map.put("parentDefId", parentDefId);
		}
		return (NodeMsgTemplate) dao.getOne("getObject", map);
	}

	/**
	 * 先找nodeId defId parentDefId限制的一个对象，找不到就扩大范围不限制nodeId来获取整体的对象
	 * 
	 * @param nodeId
	 * @param defId
	 * @param parentDefId
	 * @return NodeMsgTemplate
	 * @exception
	 * @since 1.0.0
	 */
	public NodeMsgTemplate getObjectPossibile(String nodeId, Long defId, Long parentDefId) {
		NodeMsgTemplate nodeMsgTemplate = getObject(nodeId, defId, parentDefId);
		if (nodeMsgTemplate != null)
			return nodeMsgTemplate;
		nodeMsgTemplate = getObject(null, defId, parentDefId);// 找整个流程定义的
		return nodeMsgTemplate;
	}

	/**
	 * 解释
	 * 
	 * @param nodeId
	 * @param defId
	 * @param parentDefId
	 * @param bpmFormData
	 * @return JSONObject	:json.html:是html解释结果 json.text 是text解释结果
	 * @exception
	 * @since 1.0.0
	 */
	public JSONObject parse(String nodeId, Long defId, Long parentDefId, BpmFormData bpmFormData) {
		NodeMsgTemplate nodeMsgTemplate = getObject(nodeId, defId, parentDefId);
		if(nodeMsgTemplate==null){//找不到就找全局
			nodeMsgTemplate=getObject("0", defId, parentDefId);
		}
		return parse(nodeMsgTemplate, bpmFormData);
	}

	/**
	 * 解释
	 * 
	 * @param nodeMsgTemplate
	 * @param bpmFormData
	 * @return JSONObject :json.html:是html解释结果 json.text 是text解释结果
	 * @exception
	 * @since 1.0.0
	 */
	private JSONObject parse(NodeMsgTemplate nodeMsgTemplate, BpmFormData bpmFormData) {
		JSONObject jsonObject = new JSONObject();
		try {

			Map<String, Object> param = new HashMap<String, Object>();

			for (String key : bpmFormData.getMainFields().keySet()) {// 值转字符串
				param.put(key, bpmFormData.getMainFields().get(key) + "");
			}

			for (SubTable subTable : bpmFormData.getSubTableList()) {
				List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
				for (Map<String, Object> d : subTable.getDataList()) {// 值转字符串
					Map<String, String> map = new HashMap<String, String>();
					for (String key : d.keySet()) {
						map.put(key, d.get(key) + "");
					}
					mapList.add(map);
				}
				param.put(subTable.getTableName() + "s", mapList);
			}

			String html = freemarkEngine.parseByStringTemplate(param, nodeMsgTemplate.getHtml());
			jsonObject.put("html", html);
			String text = freemarkEngine.parseByStringTemplate(param, nodeMsgTemplate.getText());
			jsonObject.put("text", text);
		} catch (Exception e) {
			System.out.println(ExceptionUtil.getExceptionMessage(e));
			jsonObject.put("html", "");
			jsonObject.put("text", "");
		}

		return jsonObject;
	}

}
