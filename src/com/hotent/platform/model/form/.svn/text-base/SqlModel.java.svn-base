package com.hotent.platform.model.form;

/**
 * sql对象数据。
 * <pre>
 * 	包括SQL数据和对应的参数。
 * </pre>
 * @author ray
 *
 */
public class SqlModel {
	/**
	 * 插入
	 */
	public static final int SQLTYPE_INSERT=0;
	/**
	 * 更新数据
	 */
	public static final int SQLTYPE_UPDATE=1;
	
	/**
	 * 删除数据
	 */
	public static final int SQLTYPE_DEL=2;
	
	
	
	public SqlModel(){}
	
	
	
	public SqlModel(String sql,Object[] values,int sqlType){
		this.sql=sql;
		this.values=values;
		this.sqlType=sqlType;
	
	}
	
	
	/**
	 * sql语句
	 */
	private String sql="";
	
	/**
	 * 对应的表元数据
	 */
	private BpmFormTable bpmFormTable=null;
	
	/**
	 * sql值数组。
	 */
	private Object[] values;
	
	private int sqlType=SQLTYPE_INSERT;

	
	/**
	 * 主键
	 */
	private String pk="";

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}



	public int getSqlType() {
		return sqlType;
	}



	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}






	public String getPk() {
		return pk;
	}



	public void setPk(String pk) {
		this.pk = pk;
	}



	public BpmFormTable getBpmFormTable() {
		return bpmFormTable;
	}



	public void setBpmFormTable(BpmFormTable bpmFormTable) {
		this.bpmFormTable = bpmFormTable;
	}



	
	

}
