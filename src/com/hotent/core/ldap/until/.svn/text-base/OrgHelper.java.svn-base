package com.hotent.core.ldap.until;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.system.SysOrg;


/**
 * SysUser和LdapUser的用户帮助类
 * @author raise
 *
 */
public class OrgHelper {

	/**
	 * 设置组织的路径
	 * 
	 * @param orgs
	 * @return
	 */
	public static List<SysOrg> setOrgPath(List<SysOrg> orgs) {
		for (SysOrg sysOrg : orgs) {
			sysOrg.setPath(calcPath(sysOrg, orgs) + sysOrg.getOrgId() + ".");
		}
		return orgs;
	}
	/**
	 * 从DN中计算出最具体的组织。如cn=user，ou=org1，o=org2，dc=htdc=com，则返回的组织的路径为/org2/org1
	 * @param dn
	 * @return
	 */
	public static SysOrg getOrgByDn(String dn,List<SysOrg> orgs){
		String orgPath = getPathByDn(dn);
		List<Map<String,Object>> orgMaps = getOrgsNamePathsMap(orgs,null);
		SysOrg sysOrg = null;
		for(Map<String,Object> map:orgMaps){
			String path=(String) map.get("path");
			if(orgPath.equals(path)){
				Long id=(Long) map.get("id");
				sysOrg = getSysOrgById(orgs, id);
				break;
			}
		}
		return sysOrg;
	}
	
	/**
	 * 从Ldap的DN提取表示组织的部分，并转为用斜杠分隔的路径（ou=development,o=hotent,dc=com 得到
	 * /com/hotent/develpment)。
	 * 
	 * @param dn
	 * @return
	 */
	public static String getPathByDn(String dn) {
		String path = "";
		String[] dnAry = dn.split(",");
		for (String str : dnAry) {
			String[] ary = str.split("=");
			String key = ary[0].trim();
			String value = ary[1].trim();
			if (key.equalsIgnoreCase("O") || key.equalsIgnoreCase("OU")) {
				path = "/" + value + path;
			}
		}
		return path;
	}


	/**
	 * 取得组织单元以名字组成的路径（如:/hotent/develpment)。
	 * 
	 * @return
	 */
	public static List<String> getOrgsNamePaths(List<SysOrg> orgs) {
		List<String> namePahts = new ArrayList<String>();
		for (SysOrg org : orgs) {
			String namePath = "";
			String[] ids = org.getPath().split("\\.");
			for (int i = 1; i < ids.length; i++) {
				String id = ids[i];
				if (!StringUtil.isEmpty(id)) {
					SysOrg org2 = getSysOrgById(orgs, Long.parseLong(id));
					if (org2 == null) {
						throw new InternalError("内部数据错误！");
					}
					namePath += "/" + org2.getOrgName();

				}
			}
			namePahts.add(namePath);
		}
		return namePahts;
	}
	
	
	/**
	 * 取得组织单元以ID和名字组成的路径（如:/hotent/develpment)组成的Map的列表。<br/>
	 * key=id，value=组织ID；<br/>
	 * key=path，value=名字组成的路径
	 * @return
	 */
	public static List<Map<String,Object>> getOrgsNamePathsMap(List<SysOrg> orgs,List<SysOrg> searchOrgs) {
		if(searchOrgs==null){
			searchOrgs=orgs;
		}
		List<Map<String,Object>> namePahts = new ArrayList<Map<String,Object>>();
		for (SysOrg org : orgs) {
			String namePath = "";
			String[] ids = org.getPath().split("\\.");
			for (int i = 1; i < ids.length; i++) {
				String id = ids[i];
				if (!StringUtil.isEmpty(id)) {
					SysOrg org2 = getSysOrgById(searchOrgs, Long.parseLong(id));
					if (org2 == null) {
						throw new InternalError("内部数据错误！");
					}
					namePath += "/" + org2.getOrgName();

				}
			}
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", org.getOrgId());
			map.put("path",namePath);
			namePahts.add(map);
		}
		return namePahts;
	}

	/**
	 * 取得组织单元的以名称组成的路径。计算路径时，将在参数searchOrgs所表示的树中搜索。
	 * @param org
	 * @param searchOrgs
	 * @return
	 */
	public static String getOrgsNamePath(SysOrg org,List<SysOrg> searchOrgs) {
		String namePath = "";
		String[] ids = org.getPath().split("\\.");
		for (int i = 1; i < ids.length; i++) {
			String id = ids[i];
			if (!StringUtil.isEmpty(id)) {
				SysOrg org2 = getSysOrgById(searchOrgs, Long.parseLong(id));
				if (org2 == null) {
					throw new InternalError("内部数据错误！");
				}
				namePath += "/" + org2.getOrgName();
			}
		}
		return namePath;
	}
	
	
	/**
	 * 根据id，从SysOrg的List中取得相应的SysOrg。
	 * 
	 * @param orgs
	 * @param id
	 * @return
	 */
	public static SysOrg getSysOrgById(List<SysOrg> orgs, Long id) {
		for (SysOrg org : orgs) {
			if (org.getOrgId().longValue() == id.longValue()) {
				return org;
			}
		}
		return null;
	}
	
	/**
	 * 根据fromType，从SysOrg的List中取得相应的SysOrg列表。
	 * 
	 * @param orgs
	 * @param id
	 * @return
	 */
	public static List<SysOrg> getSysOrgByFromType(List<SysOrg> orgs, short fromType) {
		List<SysOrg> list=new ArrayList<SysOrg>();
		for (SysOrg org : orgs) {
			if (org.getFromType().shortValue() == fromType) {
				list.add(org);
			}
		}
		return list;
	}

	/**
	 * 计算组织的路径
	 * 
	 * @param org
	 * @param orgs
	 * @return
	 */
	private static String calcPath(SysOrg org, List<SysOrg> orgs) {
		if (org.getOrgSupId() == 1) {
			return "1.";
		}
		for (SysOrg org2 : orgs) {
			if (org.getOrgSupId() == org2.getOrgId()) {
				return calcPath(org2, orgs) + org2.getOrgId() + ".";
			} else {
				continue;
			}
		}
		return org.getOrgSupId()+".";
	}
}
