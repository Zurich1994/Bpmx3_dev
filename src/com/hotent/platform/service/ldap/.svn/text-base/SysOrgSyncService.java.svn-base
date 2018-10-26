package com.hotent.platform.service.ldap;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.ldap.dao.LdapOrganizationalUnitDao;
import com.hotent.core.ldap.model.LdapOrganizationalUnit;
import com.hotent.core.ldap.until.OrgHelper;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SystemConst;

@Service
public class SysOrgSyncService implements LdapDbSync, SysOrgSyncServiceMBean {
	@Resource
	Properties configproperties;
	@Resource
	private LdapOrganizationalUnitDao organizationalUnitDao;
	@Resource
	private SysOrgDao sysOrgDao;
	
	
	// List<LdapOrganizationalUnit> organizationalUnits;
	// protected List<LdapOrganizationalUnit> totalLdapOrgs;
	protected List<SysOrg> totalDbOrgs;

	protected Date lastSyncTime;
	protected Long lastSyncTakeTime;
	protected final List<SysOrg> newFromLdapOrgList = new ArrayList<SysOrg>();
	protected final List<SysOrg> deleteLocalOrgList = new ArrayList<SysOrg>();;
	protected final List<SysOrg> updateLocalOrgList = new ArrayList<SysOrg>();;

	public int syncLodapToDb() {
		reset();
		int result = 0;
		lastSyncTime = new Date();
		long startTime = System.currentTimeMillis();
		int strategy = Integer.valueOf((String) configproperties.get("ldapStrategy"));
		switch (strategy) {
		case 1:
			result = syncLdapAprior();
			break;
		case 2:
			result = syncLdapImport();
			break;
		default:
			throw new UnsupportedClassVersionError("未找到实现同步Ldap用户到本地操作策略");
		}
		lastSyncTakeTime = System.currentTimeMillis() - startTime;
		return result;
	}

	protected int syncLdapAprior() {
		// 取得数据库中所有的组织的路径
		totalDbOrgs = sysOrgDao.getAll();
		List<LdapOrganizationalUnit> organizationalUnits = null;
		try {
			organizationalUnits = organizationalUnitDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (LdapOrganizationalUnit organizationalUnit : organizationalUnits) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", UniqueIdUtil.genId());
			map.put("name", organizationalUnit.getName());
			map.put("desc", organizationalUnit.getDescription());
			map.put("dn", organizationalUnit.getDistinguishedName());
			list.add(map);
		}
		/*
		 * 第一步，AD中已经删除，而数据库中未同步的组织进行删除操作
		 */
		// 取得数据库中所有来自AD的组织的路径
		List<SysOrg> dbAdOrgs = OrgHelper.getSysOrgByFromType(totalDbOrgs, SystemConst.FROMTYPE_AD);
		List<Map<String, Object>> dbAdNamePahts = OrgHelper.getOrgsNamePathsMap(dbAdOrgs, totalDbOrgs);
		// 取得AD中所有的组织的路径
		List<String> adNamePaths = new ArrayList<String>();
		for (Map<String, Object> map : list) {
			String path = OrgHelper.getPathByDn((String) map.get("dn"));
			adNamePaths.add(path);
		}
		// 取得AD中已经删除，而在数据库中未同步组织
		List<Long> delOrgIds = new ArrayList<Long>();
		for (Map<String, Object> map : dbAdNamePahts) {
			String path = (String) map.get("path");
			if (!adNamePaths.contains(path)) {
				delOrgIds.add((Long) map.get("id"));
			}
		}
		// 从数据库中删除，AD中已经删除，而在数据库中未同步组织
		for (Long id : delOrgIds) {
			sysOrgDao.delById(id);
			deleteLocalOrgList.clear();
			deleteLocalOrgList.add(OrgHelper.getSysOrgById(totalDbOrgs, id));
		}
		/*
		 * 第二步，AD中新添加，而数据库中未同步的组织进行插入操作
		 */
		List<Map<String, Object>> namePahtsMaps = OrgHelper.getOrgsNamePathsMap(totalDbOrgs, totalDbOrgs);
		// AD中取得的组织已存在于本地数据库，将组织的ID改变数据库中的组织ID
		for (Map<String, Object> map1 : list) {
			String path = OrgHelper.getPathByDn((String) map1.get("dn"));
			for (Map<String, Object> map2 : namePahtsMaps) {
				if (((String) map2.get("path")).equals(path)) {
					Long id = (Long) map2.get("id");
					SysOrg org = OrgHelper.getSysOrgById(totalDbOrgs, id);
					map1.put("id", id);
					map1.put("path", org.getPath());
					map1.put("sup", org.getOrgSupId());
					map1.put("isExist", true);
				}
			}
		}
		// List<String> namePahts = getOrgsNamePaths(totalDbOrgs);
		// 计算组织的父节点。
		List<SysOrg> addSysOrgs = new ArrayList<SysOrg>();
		for (Map<String, Object> map1 : list) {
			if (map1.get("isExist") != null && (Boolean) map1.get("isExist") == true) {
				continue;
			}
			for (Map<String, Object> map2 : list) {
				String dn1 = (String) map1.get("dn");
				int index = dn1.indexOf(',');
				String dn2 = (String) map2.get("dn");
				if (dn1.substring(index + 1).trim().equals(dn2.trim())) {
					map1.put("sup", map2.get("id"));
					map1.put("supName", map2.get("name"));
				}
			}
			if (map1.get("sup") == null) {
				map1.put("sup", 1L);
			}

			SysOrg sysOrg = new SysOrg();
			try {
				sysOrg.setOrgName((String) map1.get("name"));
				sysOrg.setOrgId((Long) map1.get("id"));
				sysOrg.setOrgDesc((String) map1.get("desc"));
				sysOrg.setOrgSupId((Long) map1.get("sup"));
				sysOrg.setOrgSupName((String) map1.get("supName"));
				sysOrg.setFromType(SystemConst.FROMTYPE_AD);
				sysOrg.setDemId(1L);
				sysOrg.setSn(sysOrg.getOrgId());
				addSysOrgs.add(sysOrg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 设置组织的路径。此处设置的路径是用ID表示(如:1.22112.11134.)。
		OrgHelper.setOrgPath(addSysOrgs);
		for (SysOrg org : addSysOrgs) {
			if (!org.getPath().startsWith("1.")) {
				String path = org.getPath();
				Long ancestorId = Long.parseLong(path.split("\\.")[0]);
				SysOrg ancestroId = OrgHelper.getSysOrgById(totalDbOrgs, ancestorId);
				String ancestorPath = ancestroId.getPath();
				if (!ancestorPath.endsWith(".")) {
					ancestorPath += ".";
				}
				String newPath = ancestorPath + path.substring(path.indexOf('.') + 1);
				org.setPath(newPath);
			}
		}
		// 同步到本地数据库中
		for (SysOrg org : addSysOrgs) {
			try {
				sysOrgDao.add(org);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		newFromLdapOrgList.clear();
		newFromLdapOrgList.addAll(addSysOrgs);
		return deleteLocalOrgList.size() + newFromLdapOrgList.size();
	}

	protected int syncLdapImport() {
		// 取得数据库中所有的组织的路径
		totalDbOrgs = sysOrgDao.getAll();
		List<LdapOrganizationalUnit> organizationalUnits = null;
		try {
			organizationalUnits = organizationalUnitDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (LdapOrganizationalUnit organizationalUnit : organizationalUnits) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", UniqueIdUtil.genId());
			map.put("name", organizationalUnit.getName());
			map.put("desc", organizationalUnit.getDescription());
			map.put("dn", organizationalUnit.getDistinguishedName());
			list.add(map);
		}
		// AD中新添加，而数据库中未同步的组织进行插入操作
		List<Map<String, Object>> namePahtsMaps = OrgHelper.getOrgsNamePathsMap(totalDbOrgs, totalDbOrgs);
		// AD中取得的组织已存在于本地数据库，将组织的ID改变数据库中的组织ID
		for (Map<String, Object> map1 : list) {
			String path = OrgHelper.getPathByDn((String) map1.get("dn"));
			for (Map<String, Object> map2 : namePahtsMaps) {
				if (((String) map2.get("path")).equals(path)) {
					Long id = (Long) map2.get("id");
					SysOrg org = OrgHelper.getSysOrgById(totalDbOrgs, id);
					map1.put("id", id);
					map1.put("path", org.getPath());
					map1.put("sup", org.getOrgSupId());
					map1.put("isExist", true);
				}
			}
		}
		// List<String> namePahts = getOrgsNamePaths(totalDbOrgs);
		// 计算组织的父节点。
		List<SysOrg> addSysOrgs = new ArrayList<SysOrg>();
		for (Map<String, Object> map1 : list) {
			if (map1.get("isExist") != null && (Boolean) map1.get("isExist") == true) {
				continue;
			}
			for (Map<String, Object> map2 : list) {
				String dn1 = (String) map1.get("dn");
				int index = dn1.indexOf(',');
				String dn2 = (String) map2.get("dn");
				if (dn1.substring(index + 1).trim().equals(dn2.trim())) {
					map1.put("sup", map2.get("id"));
					map1.put("supName", map2.get("name"));
				}
			}
			if (map1.get("sup") == null) {
				map1.put("sup", 1L);
			}

			SysOrg sysOrg = new SysOrg();
			try {
				sysOrg.setOrgName((String) map1.get("name"));
				sysOrg.setOrgId((Long) map1.get("id"));
				sysOrg.setOrgDesc((String) map1.get("desc"));
				sysOrg.setOrgSupId((Long) map1.get("sup"));
				sysOrg.setOrgSupName((String) map1.get("supName"));
				sysOrg.setFromType(SystemConst.FROMTYPE_AD);
				sysOrg.setDemId(1L);
				sysOrg.setSn(sysOrg.getOrgId());
				addSysOrgs.add(sysOrg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 设置组织的路径。此处设置的路径是用ID表示(如:1.22112.11134.)。
		OrgHelper.setOrgPath(addSysOrgs);
		for (SysOrg org : addSysOrgs) {
			if (!org.getPath().startsWith("1.")) {
				String path = org.getPath();
				Long ancestorId = Long.parseLong(path.split("\\.")[0]);
				SysOrg ancestroId = OrgHelper.getSysOrgById(totalDbOrgs, ancestorId);
				String ancestorPath = ancestroId.getPath();
				if (!ancestorPath.endsWith(".")) {
					ancestorPath += ".";
				}
				String newPath = ancestorPath + path.substring(path.indexOf('.') + 1);
				org.setPath(newPath);
			}
		}
		// 同步到本地数据库中
		for (SysOrg org : addSysOrgs) {
			try {
				sysOrgDao.add(org);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		newFromLdapOrgList.clear();
		newFromLdapOrgList.addAll(addSysOrgs);
		return newFromLdapOrgList.size();
	}

	public Date getLastSyncTime() {
		return this.lastSyncTime;
	}

	public Long getLastSyncTakeTime() {
		return this.lastSyncTakeTime;
	}

	public List<SysOrg> getNewFromLdapOrgList() {
		return this.newFromLdapOrgList;
	}

	public List<SysOrg> getDeleteLocalOrgList() {
		return this.deleteLocalOrgList;
	}

	public List<SysOrg> getUpdateLocalOrgList() {
		return this.updateLocalOrgList;
	}

	public void reset() {
		this.lastSyncTakeTime = null;
		this.lastSyncTime = null;
		this.deleteLocalOrgList.clear();
		this.updateLocalOrgList.clear();
		this.newFromLdapOrgList.clear();
	}
}