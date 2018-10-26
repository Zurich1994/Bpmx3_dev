/**
 * 描述：TODO
 * 包名：com.hotent.platform.webservice.impl.util
 * 文件名：BpmNodeUtil.java
 * 作者：User-mailto:chensx@jee-soft.cn
 * 日期2015-1-13-下午4:49:58
 *  2015广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.platform.webservice.impl.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.hotent.core.bpm.model.FlowNode;

/**
 * <pre>
 * 描述：流程设置节点工具类
 * 构建组：bpm33
 * 作者：Aschs
 * 邮箱:chensx@jee-soft.cn
 * 日期:2015-1-13-下午4:49:58
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class BpmNodeUtil {
	/**
	 * 删除FlowNode中的flow数组 因为那些flow有递归的可能会导致json报错
	 * 
	 * @param flowNode
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	public static void removeListInFlowNode(FlowNode flowNode) {
		flowNode.setNextFlowNodes(new ArrayList<FlowNode>());
		flowNode.setPreFlowNodes(new ArrayList<FlowNode>());
		flowNode.setSubFlowNodes(new ArrayList<FlowNode>());
		flowNode.setSubProcessNodes(new HashMap<String, FlowNode>());
	}
}
