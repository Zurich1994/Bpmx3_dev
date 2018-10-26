package com.hotent.platform.event.listener;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.util.MapUtil;
import com.hotent.platform.event.def.NodeSqlContext;
import com.hotent.platform.event.def.NodeSqlEvent;
import com.hotent.platform.model.bpm.BpmNodeSql;
import com.hotent.platform.service.bpm.BpmNodeSqlService;

/**
 * 流程结束监听事件。
 * 
 * @author ray
 * 
 */
@Service
public class NodeSqlEventListener implements ApplicationListener<NodeSqlEvent>, Ordered {
	
	@Resource
	BpmNodeSqlService bpmNodeSqlService;
	@Override
	public void onApplicationEvent(NodeSqlEvent event) {
		NodeSqlContext source = (NodeSqlContext) event.getSource();
		Map<String, Object> dataMap = (Map<String, Object>) source.getDataMap();// 主表数据
		String actdefId = source.getActdefId();// 流程id
		String nodeId =  source.getNodeId();// 节点id
		String action = source.getAction();// 流程id

		List<BpmNodeSql> nodeSqls = bpmNodeSqlService.getByNodeIdAndActdefIdAndAction(nodeId, actdefId, action);

		for (BpmNodeSql nodeSql : nodeSqls) {
			String sql = nodeSql.getSql();

			if (dataMap != null && !dataMap.isEmpty()) {
				// 1.先根据主表数据拼装sql
				Pattern pattern = Pattern.compile("<#(.*?)#>");
				Matcher matcher = pattern.matcher(sql);
				while (matcher.find()) {
					String str = matcher.group();
					String key = matcher.group(1);
					String val =MapUtil.getString(dataMap, key);
					sql = sql.replace(str, val);
				}
			}
			// 2.开始执行sql
			try {
				JdbcTemplate jdbcTemplate = JdbcTemplateUtil.getNewJdbcTemplate(nodeSql.getDsAlias());
				jdbcTemplate.execute(sql);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}

		}
	}
	@Override
	public int getOrder() {
		return 2;
	}

}
