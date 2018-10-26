package com.hotent.platform.model.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 常用变量
 * @author zxh
 *
 */
public class CommonVar {
	//字段名
	private String name;
	//字段值
	private String value;
	
	public CommonVar() {
		super();
	}

	public CommonVar(String value,String name) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 常用变量
	 * 
	 * @return
	 */
	public static List<CommonVar> geCommonVars() {
		List<CommonVar> list = new ArrayList<CommonVar>();
		list.add(new CommonVar("[CUR_USER]", "当前用户"));
		list.add(new CommonVar("[CUR_ORG]", "当前组织"));
		list.add(new CommonVar("[CUR_COMPANY]","当前公司"));
		return list;
	}
	
	/**
	 * 设置常用变量
	 * 
	 * @param params 参数
	 * @param curUserId 当前用户ID
	 * @param curOrgId 当前组织ID
	 * @param curCompany 当前公司
	 */
	public static void setCommonVar(Map<String, Object> params, Long curUserId,
			Long curOrgId,Long curCompanyId) {
		params.put("[CUR_USER]", curUserId);
		params.put("[CUR_ORG]", curOrgId);
		params.put("[CUR_COMPANY]", curCompanyId);
	}
	
}
