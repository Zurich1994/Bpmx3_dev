package com.hotent.platform.event.def;

import java.util.Map;

import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.AppUtil;
import com.hotent.platform.model.bpm.BpmNodeSql;
import com.hotent.platform.model.system.SysUser;

/**
 * <pre>
 * 描述：为事件生成的工具类
 * 构建组：bpm33
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2015-3-10-下午5:41:55
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class EventUtil {
	/**
	 * 发布一个NodeSqlEvent
	 * @param actDefId	：流程ID
	 * @param nodeId	:节点ID
	 * @param action	：事件
	 * @param dataMap 	：主表数据Map
	 * void
	 */
	public static void publishNodeSqlEvent(String actDefId, String nodeId, String action, Map<String, Object> dataMap) {
		NodeSqlContext context = new NodeSqlContext();
		context.setActDefId(actDefId);
		context.setNodeId(nodeId);
		context.setAction(action);
		context.setDataMap(dataMap);
		NodeSqlEvent event = new NodeSqlEvent(context);
		AppUtil.publishEvent(event);
	}
	
	/**
	 *  发布用户事件。
	 * @param userId	用户ID
	 * @param action	动作。
	 */
	public static void publishUserEvent(Long userId,int action){
		UserEvent ev=new UserEvent(userId);
		ev.setAction(action);
		AppUtil.publishEvent(ev);
	}
	/**
	 * 发布新流程启动事件
	 * @param action
	 * @param nodeId 
	 * @param processCmd
	 */
	public static void publishTriggerNewFlowEvent(String action, String nodeId, ProcessCmd processCmd) {
		TriggerNewFlowEvent tiNewFlowEvent = new TriggerNewFlowEvent(new TriggerNewFlowModel(action,nodeId, processCmd));
		AppUtil.publishEvent(tiNewFlowEvent);
	}
}
