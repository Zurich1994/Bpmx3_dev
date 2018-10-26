package com.hotent.platform.model.bpm;


/**
 * 对象功能:任务节点催办预警设置
 * 开发公司:广州宏天软件有限公司
 * 开发人员:miao
 * 创建时间:2015-5-21 09:30:47
 */
public class TaskWarningSet {
	/**到期日之前*/
	public static final String RELATIVE_TYPE_BEFOR = "before";
	/**到期日之后*/
	public static final String RELATIVE_TYPE_AFTER = "after";
	
	private String name;
	
	// 相对于设定的到期时间间隔
	private Integer relativeDueTime ;
	//
	private int reminderDueDay;
	private int reminderDueHour;
	private int reminderDueMinute;
	//相对类型（before/after）
	private String relativeType;
	//预警级别
	private int level;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRelativeDueTime() {
		if(relativeDueTime != null) 
			return relativeDueTime;
		
		return  
			this.reminderDueMinute +
			this.reminderDueHour * 60 +
			this.reminderDueDay *(60*24);
	}
	// 
	/*public void setRelativeDueTime(Integer relativeDueTime) {
		this.relativeDueTime = relativeDueTime;
	}*/

	public int getReminderDueDay() {
		return reminderDueDay;
	}

	public void setReminderDueDay(int reminderDueDay) {
		this.reminderDueDay = reminderDueDay;
	}

	public int getReminderDueHour() {
		return reminderDueHour;
	}

	public void setReminderDueHour(int reminderDueHour) {
		this.reminderDueHour = reminderDueHour;
	}

	public int getReminderDueMinute() {
		return reminderDueMinute;
	}

	public void setReminderDueMinute(Integer reminderDueMinute) {
		this.reminderDueMinute = reminderDueMinute;
	}

	public String getRelativeType() {
		return relativeType;
	}

	public void setRelativeType(String relativeType) {
		this.relativeType = relativeType;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setReminderDueMinute(int reminderDueMinute) {
		this.reminderDueMinute = reminderDueMinute;
	}



	
}
