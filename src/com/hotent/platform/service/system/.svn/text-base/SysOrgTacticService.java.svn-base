package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.system.SysOrgTacticDao;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysOrgTactic;

/**
 * <pre>
 * 对象功能:组织策略 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-31 13:43:14
 * </pre>
 */
@Service
public class SysOrgTacticService extends BaseService<SysOrgTactic> {
	@Resource
	private SysOrgTacticDao dao;
	@Resource
	private SysOrgService sysOrgService;

	public SysOrgTacticService() {
	}

	@Override
	protected IEntityDao<SysOrgTactic, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 获取默认的组织策略
	 * @return
	 */
	public SysOrgTactic getOrgTactic() {
		SysOrgTactic sysOrgTactic = dao.getById(SysOrgTactic.DEFAULT_ID);
		if (BeanUtils.isEmpty(sysOrgTactic)){
			sysOrgTactic  = new SysOrgTactic();
			sysOrgTactic.setOrgTactic(SysOrgTactic.ORG_TACTIC_WITHOUT);
		}
		return sysOrgTactic;
	}

	/**
	 * 根据策略获取的组织列表
	 * @return
	 */
	public List<SysOrg> getSysOrgListByOrgTactic() {
		SysOrgTactic sysOrgTactic = getOrgTactic();
		List<SysOrg>  list = new ArrayList<SysOrg>();
		 if (SysOrgTactic.ORG_TACTIC_LEVEL == sysOrgTactic
				.getOrgTactic()) {
			 list = sysOrgService.getByOrgType(sysOrgTactic.getOrgType());
		} else if (SysOrgTactic.ORG_TACTIC_SELECT == sysOrgTactic
				.getOrgTactic()) {
			list  = getOrgLstByOrgSelectId(sysOrgTactic.getOrgSelectId());
		} else if (SysOrgTactic.ORG_TACTIC_COMBINATION == sysOrgTactic
				.getOrgTactic()) {
			list = getCombination(sysOrgTactic.getOrgType(),sysOrgTactic.getOrgSelectId());
		}else{
			return list;
		}
		return list;
	}

	/**
	 * 根据策略获取的组织列表，通过组织名称查询
	 * @return
	 */
	public List<SysOrg> getSysOrgListByOrgName(String orgName) {
		List<SysOrg>  list = getSysOrgListByOrgTactic();
		if(BeanUtils.isEmpty(orgName))
			return list;
		List<SysOrg>  list1 = new ArrayList<SysOrg>();
		for (SysOrg sysOrg : list) {
			if(sysOrg.getOrgName().contains(orgName)){
				list1.add(sysOrg);
			}
		}
		return list1;
	}
	
	/**
	 * 获取组合策略的组织列表
	 * @param orgType
	 * @param orgSelectId
	 * @return
	 */
	private List<SysOrg> getCombination(Long orgType, String orgSelectId) {
		List<SysOrg>   list = sysOrgService.getByOrgType(orgType);
		if(BeanUtils.isEmpty(list))
			list = new ArrayList<SysOrg>();
		List<SysOrg>  list2  = getOrgLstByOrgSelectId(orgSelectId);
		if(BeanUtils.isEmpty(list2))
			list.addAll(list2);
		return list;
	}

	/**
	 * 获取手工选择的组织列表
	 * @param orgSelectId
	 * @return
	 */
	private List<SysOrg> getOrgLstByOrgSelectId(String orgSelectId) {
		List<SysOrg>  list = new ArrayList<SysOrg>();
		if(BeanUtils.isEmpty(orgSelectId))
			return list;
		JSONArray jsonAry =  JSONArray.fromObject(orgSelectId);
		for (Object obj : jsonAry) {
			JSONObject json = (JSONObject) obj;
			String id = (String) json.get("id");
			SysOrg sysOrg = sysOrgService.getById(new Long(id));
			list.add(sysOrg);
		}
		return list;
	}

	/**
	 * 根据当前用户的组织获取有权限的策略组织
	 * @param currentOrg
	 * @return
	 */
	public Long getByCurOrgId(SysOrg currentOrg) {
		List<SysOrg>  list = getSysOrgListByOrgTactic();
		if(BeanUtils.isEmpty(list))
			return currentOrg.getOrgId();
		Set<Long> orgIds = replacePath(currentOrg.getPath());
		for (SysOrg sysOrg : list) {
			Long id = sysOrg.getOrgId();
			for (Long orgId : orgIds) {
				if(orgId.longValue() ==id.longValue() ){
					return id;
				}
			}
		}
		return null;
	}
	
	private Set<Long> replacePath(String path) {
		if(StringUtil.isEmpty(path)) return new HashSet<Long>();
		path = StringUtil.trimSufffix(path, ".");
		String[] aryPath=path.split("\\.");
		Set<Long> list = new HashSet<Long>();
		for(String tmp:aryPath){//要使用多少级
			list.add(new Long(tmp));
		}
		return list;
	}
}
