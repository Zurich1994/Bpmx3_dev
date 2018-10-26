package com.hotent.platform.model.form;

import java.util.ArrayList;
import java.util.List;

public class TeamModel {
	/**
	 * 分组名称
	 */
	protected String teamName = "";
	/**
	 * 分组对应的字段
	 */
	protected List<BpmFormField> teamFields = new ArrayList<BpmFormField>();

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<BpmFormField> getTeamFields() {
		return teamFields;
	}

	public void setTeamFields(List<BpmFormField> teamFields) {
		this.teamFields = teamFields;
	}

}
