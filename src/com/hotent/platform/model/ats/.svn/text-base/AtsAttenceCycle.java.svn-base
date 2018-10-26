package com.hotent.platform.model.ats;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:考勤周期 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 22:03:30
 */
public class AtsAttenceCycle extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1311909062666981385L;
	// 主键
	protected Long id;
	// 编码
	protected String code;
	// 名称
	protected String name;
	// 周期类型
	protected String type;
	// 年
	protected Long year;
	// 月
	protected Long month;
	// 周期区间-开始月
	protected Short startMonth;
	// 周期区间-开始日
	protected Long startDay;
	// 周期区间-结束月
	protected Short endMonth;
	// 周期区间-结束日
	protected Long endDay;
	// 是否默认
	protected Short isDefault =AtsConstant.YES;
	// 描述
	protected String memo;
	
	protected String detailList;

	public void setId(Long id){
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	public void setCode(String code){
		this.code = code;
	}
	/**
	 * 返回 编码
	 * @return
	 */
	public String getCode() {
		return this.code;
	}
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 返回 名称
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	public void setType(String type){
		this.type = type;
	}
	/**
	 * 返回 周期类型
	 * @return
	 */
	public String getType() {
		return this.type;
	}
	public void setYear(Long year){
		this.year = year;
	}
	/**
	 * 返回 年
	 * @return
	 */
	public Long getYear() {
		return this.year;
	}
	public void setMonth(Long month){
		this.month = month;
	}
	/**
	 * 返回 月
	 * @return
	 */
	public Long getMonth() {
		return this.month;
	}
	public void setStartMonth(Short startMonth){
		this.startMonth = startMonth;
	}
	/**
	 * 返回 周期区间-开始月
	 * @return
	 */
	public Short getStartMonth() {
		return this.startMonth;
	}
	public void setStartDay(Long startDay){
		this.startDay = startDay;
	}
	/**
	 * 返回 周期区间-开始日
	 * @return
	 */
	public Long getStartDay() {
		return this.startDay;
	}
	public void setEndMonth(Short endMonth){
		this.endMonth = endMonth;
	}
	/**
	 * 返回 周期区间-结束月
	 * @return
	 */
	public Short getEndMonth() {
		return this.endMonth;
	}
	public void setEndDay(Long endDay){
		this.endDay = endDay;
	}
	/**
	 * 返回 周期区间-结束日
	 * @return
	 */
	public Long getEndDay() {
		return this.endDay;
	}
	public void setIsDefault(Short isDefault){
		this.isDefault = isDefault;
	}
	/**
	 * 返回 是否默认
	 * @return
	 */
	public Short getIsDefault() {
		return this.isDefault;
	}
	public void setMemo(String memo){
		this.memo = memo;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}
   	public String getDetailList() {
		return detailList;
	}
	public void setDetailList(String detailList) {
		this.detailList = detailList;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AtsAttenceCycle)) 
		{
			return false;
		}
		AtsAttenceCycle rhs = (AtsAttenceCycle) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.code, rhs.code)
		.append(this.name, rhs.name)
		.append(this.type, rhs.type)
		.append(this.year, rhs.year)
		.append(this.month, rhs.month)
		.append(this.startMonth, rhs.startMonth)
		.append(this.startDay, rhs.startDay)
		.append(this.endMonth, rhs.endMonth)
		.append(this.endDay, rhs.endDay)
		.append(this.isDefault, rhs.isDefault)
		.append(this.memo, rhs.memo)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.code) 
		.append(this.name) 
		.append(this.type) 
		.append(this.year) 
		.append(this.month) 
		.append(this.startMonth) 
		.append(this.startDay) 
		.append(this.endMonth) 
		.append(this.endDay) 
		.append(this.isDefault) 
		.append(this.memo) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("code", this.code) 
		.append("name", this.name) 
		.append("type", this.type) 
		.append("year", this.year) 
		.append("month", this.month) 
		.append("startMonth", this.startMonth) 
		.append("startDay", this.startDay) 
		.append("endMonth", this.endMonth) 
		.append("endDay", this.endDay) 
		.append("isDefault", this.isDefault) 
		.append("memo", this.memo) 
		.toString();
	}
   
  

}