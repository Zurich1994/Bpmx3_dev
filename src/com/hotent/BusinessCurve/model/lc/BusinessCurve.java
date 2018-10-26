package com.hotent.BusinessCurve.model.lc;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:业务曲线信息表 Model对象
 */
public class BusinessCurve extends BaseModel
{
	public static final String TYPE_0 = "二元一次方程曲线";	
	public static final String TYPE_1 = "二元二次方程曲线";
	public static final String TYPE_2 = "三元曲线方程";
	public static final String STATUS_E0 = "y=a*x+b";	
	public static final String STATUS_E1 = "y=a*(x^2)+b*x+c";
	public static final String STATUS_E2 = "y=a*(x^3)+b*x^2+c*x+d";

	//主键
	protected Long id;
	/**
	 *曲线数据编号
	 */
	protected String  curveDataId;
	/**
	 *曲线编号
	 */
	protected String  curveId;
	/**
	 *曲线类别
	 */
	protected String  curveType;
	/**
	 *基本公式
	 */
	protected String  basicExpression;
	/**
	 *历史数据编号
	 */
	protected String  unknownExp; 
	/**
	 *曲线开始时间
	 */
	protected String  curveStartTime;
	/**
	 *曲线结束时间
	 */
	protected String  curveEndTime;
	
	
	public static String getStatusE0() {
		return STATUS_E0;
	}
	public static String getStatusE1() {
		return STATUS_E1;
	}
	public static String getStatusE2() {
		return STATUS_E2;
	}

	public static String getType0() {
		return TYPE_0;
	}
	public static String getType1() {
		return TYPE_1;
	}
	public static String getType2() {
		return TYPE_2;
	}

	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setCurveDataId(String curveDataId) 
	{
		this.curveDataId = curveDataId;
	}
	/**
	 * 返回 曲线数据编号
	 * @return
	 */
	public String getCurveDataId() 
	{
		return this.curveDataId;
	}
	public void setCurveId(String curveId) 
	{
		this.curveId = curveId;
	}
	/**
	 * 返回 曲线编号
	 * @return
	 */
	public String getCurveId() 
	{
		return this.curveId;
	}
	public void setCurveType(String curveType) 
	{
		this.curveType = curveType;
	}
	/**
	 * 返回 曲线类别
	 * @return
	 */
	public String getCurveType() 
	{
		return this.curveType;
	}
	public void setBasicExpression(String basicExpression) 
	{
		this.basicExpression = basicExpression;
	}
	/**
	 * 返回 基本公式
	 * @return
	 */
	public String getBasicExpression() 
	{
		return this.basicExpression;
	}
	
	/**
	 * 返回未知公式
	 * @return
	 */
	public String getUnknownExp() 
	{
		return this.unknownExp;
	}
	public void setUnknownExp(String unknownExp) {
		this.unknownExp = unknownExp;
	}
	public void setCurveStartTime(String curveStartTime) 
	{
		this.curveStartTime = curveStartTime;
	}
	/**
	 * 返回 曲线开始时间
	 * @return
	 */
	public String getCurveStartTime() 
	{
		return this.curveStartTime;
	}
	public void setCurveEndTime(String curveEndTime) 
	{
		this.curveEndTime = curveEndTime;
	}
	/**
	 * 返回 曲线结束时间
	 * @return
	 */
	public String getCurveEndTime() 
	{
		return this.curveEndTime;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BusinessCurve)) 
		{
			return false;
		}
		BusinessCurve rhs = (BusinessCurve) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.curveDataId, rhs.curveDataId)
		.append(this.curveId, rhs.curveId)
		.append(this.curveType, rhs.curveType)
		.append(this.basicExpression, rhs.basicExpression)
		.append(this.unknownExp, rhs.unknownExp)
		.append(this.curveStartTime, rhs.curveStartTime)
		.append(this.curveEndTime, rhs.curveEndTime)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.curveDataId) 
		.append(this.curveId) 
		.append(this.curveType) 
		.append(this.basicExpression) 
		.append(this.unknownExp) 
		.append(this.curveStartTime) 
		.append(this.curveEndTime) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("curveDataId", this.curveDataId) 
		.append("curveId", this.curveId) 
		.append("curveType", this.curveType) 
		.append("basicExpression", this.basicExpression) 
		.append("unknownExp", this.unknownExp) 
		.append("curveStartTime", this.curveStartTime) 
		.append("curveEndTime", this.curveEndTime) 
		.toString();
	}

}
