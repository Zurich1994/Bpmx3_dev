/**
 * 描述：TODO
 * 包名：com.hotent.platform.webservice.impl.util
 * 文件名：AgentSettingUtil.java
 * 作者：User-mailto:chensx@jee-soft.cn
 * 日期2015-1-13-下午4:53:53
 *  2015广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.platform.webservice.impl.util;

import java.util.Date;

import javax.annotation.Resource;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.util.JSONUtils;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.AgentDef;
import com.hotent.platform.model.bpm.AgentSetting;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.AgentSettingService;
import com.hotent.platform.service.system.SysUserService;

/**
 * <pre>
 * 描述：代理的工具类
 * 构建组：bpm33
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2015-1-13-下午4:53:53
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class AgentSettingUtil {
	/**
	 * 根据json字符串生成AgentSetting对象
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 *             AgentSetting
	 * @exception
	 * @since 1.0.0
	 */
	public static AgentSetting createAgenSettingByJson(String json) throws Exception {
		SysUserService sysUserService = (SysUserService) AppUtil.getBean("sysUserService");

		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		/***
		 * 使用JSON-Lib解析时出错
		 */
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(json);
		int agentType = jsonObject.get("agentType") != null ? jsonObject.getIntValue("agentType") : 0;
		AgentSetting agentSetting = jsonObject.parseObject(json, AgentSetting.class);

		// 验证合法性
		if (agentSetting.getAuthtype() == 0) {
			validateSettingComplictAgainstAll(agentSetting);
		} else {
			validateSettingComplictAgainstGeneral(agentSetting);
			validateSettingComplictByFlow(agentSetting);
		}

		SysUser sysUser = sysUserService.getById(agentSetting.getAuthid());
		if (sysUser == null) {
			throw new Exception("请输入授权人id");
		}

		if (agentType != 1) { // 为普通入口的 受权 （0 受权人为自己） 否则为管理入口（1 受权人为页面所选择的人员）
			agentSetting.setAuthid(sysUser.getUserId());
			agentSetting.setAuthname(sysUser.getFullname());
		}
		if (AgentSetting.AUTHTYPE_GENERAL == agentSetting.getAuthtype()) {
			agentSetting.getAgentDefList().clear();
			agentSetting.getAgentConditionList().clear();
			agentSetting.setFlowkey(null);
			agentSetting.setFlowname(null);
		} else if (AgentSetting.AUTHTYPE_PARTIAL == agentSetting.getAuthtype()) {
			agentSetting.getAgentConditionList().clear();
			agentSetting.setFlowkey(null);
			agentSetting.setFlowname(null);
		} else if (AgentSetting.AUTHTYPE_CONDITION == agentSetting.getAuthtype()) {
			agentSetting.getAgentDefList().clear();
			agentSetting.setAgentid(null);
			agentSetting.setAgent(null);
		} else {
			throw new Exception("无效代理设定");
		}

		return agentSetting;
	}

	/**
	 * 验证是否与存在的有效全权代理代理冲突
	 * 
	 * @param agentSetting
	 *            void
	 * @throws Exception
	 * @exception
	 * @since 1.0.0
	 */
	public static void validateSettingComplictAgainstGeneral(AgentSetting agentSetting) throws Exception {
		AgentSettingService agentSettingService = (AgentSettingService) AppUtil.getBean("agentSettingService");

		String msg = null;
		Long curUserId = agentSetting.getAuthid();// 授权人就是当前用户
		Date startDate = agentSetting.getStartdate();
		Date endDate = agentSetting.getEnddate();
		Long id = agentSetting.getId();
		if (id != null && id != 0L) {
			boolean flag = agentSettingService.isComplictAgainstGeneral(startDate, endDate, curUserId, id);
			if (flag) {
				// 此时间段内与已有的有效全权代理冲突！
				msg = "此时间段内与已有的有效全权代理冲突！";
				throw new Exception(msg);
			}
		} else {
			boolean flag = agentSettingService.isComplictAgainstGeneral(startDate, endDate, curUserId);
			if (flag) {
				// 此时间段内与已有的有效全权代理冲突！
				msg = "此时间段内与已有的有效全权代理冲突！";
				throw new Exception(msg);
			}
		}
	}

	public static void validateSettingComplictByFlow(AgentSetting agentSetting) throws Exception {
		AgentSettingService agentSettingService = (AgentSettingService) AppUtil.getBean("agentSettingService");

		Long curUserId = agentSetting.getAuthid();// 授权人就是当前用户

		Long id = agentSetting.getId();
		Date startDate = agentSetting.getStartdate();
		Date endDate = agentSetting.getEnddate();
		if (id != null && id != 0L) {
			for (AgentDef agentDef : agentSetting.getAgentDefList()) {
				String flowKey = agentDef.getFlowkey();
				boolean flag = agentSettingService.isComplictAgainstPartialOrConditionByFlow(flowKey, startDate, endDate, curUserId, id);
				if (flag) {
					throw new Exception(flowKey + " 流程已经设置代理");
				}
			}
		} else {
			for (AgentDef agentDef : agentSetting.getAgentDefList()) {
				String flowKey = agentDef.getFlowkey();
				boolean flag = agentSettingService.isComplictAgainstPartialOrConditionByFlow(flowKey, startDate, endDate, curUserId);
				if (flag) {
					throw new Exception(flowKey + " 流程已经设置代理");
				}
			}
		}
	}

	public static void validateSettingComplictAgainstAll(AgentSetting agentSetting) throws Exception {
		AgentSettingService agentSettingService = (AgentSettingService) AppUtil.getBean("agentSettingService");

		Long curUserId = agentSetting.getAuthid();
		Date startDate = agentSetting.getStartdate();
		Date endDate = agentSetting.getEnddate();
		Long id = agentSetting.getId() != null ? agentSetting.getId() : 0L;
		if (id != 0L) {
			boolean flag = agentSettingService.validateSettingComplictAgainstAll(startDate, endDate, curUserId, id);
			if (flag) {
				// 此时间段内与已有的有效代理冲突
				throw new Exception("此时间段内与已有的有效代理冲突");
			}
		} else {
			boolean flag = agentSettingService.validateSettingComplictAgainstAll(startDate, endDate, curUserId);
			if (flag) {
				throw new Exception("此时间段内与已有的有效代理冲突");
			}
		}
	}
}
