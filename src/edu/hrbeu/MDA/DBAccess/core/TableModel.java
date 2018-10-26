package edu.hrbeu.MDA.DBAccess.core;

import java.util.ArrayList;
import java.util.List;


/**
 * 表对象。
 *
 */
public class TableModel {
	/**
	 * 自定义表的主键(ID)
	 */
	public static final String PK_COLUMN_NAME = "ID";
	/**
	 * 自定义表的外键(REFID)
	 */
	public static final String FK_COLUMN_NAME = "REFID";
	/**
	 * 自定义字段的字段前缀(F_)
	 */
	public static  String CUSTOMER_COLUMN_PREFIX = "F_";
	/**
	 * 自定义表的表前缀(W_)
	 */
	public static  String CUSTOMER_TABLE_PREFIX = "W_";
	/**
	 * 自定义表的索引前缀(IDX_)
	 */
	public static final String CUSTOMER_INDEX_PREFIX = "IDX_";
	
	/**
	 * 历史业务数据表名的后缀(_HISTORY)
	 */
	public static final String CUSTOMER_TABLE_HIS_SUFFIX="_HISTORY";

	/**
	 * 新添加的表通用前缀 (TT_)
	 */
	public static final String CUSTOMER_TABLE_COMM_PREFIX="TT_";
	
	/**
	 * 在主表表中默认添加用户字段。(curentUserId_)
	 */
	public static final String CUSTOMER_COLUMN_CURRENTUSERID="curentUserId_";
	/**
	 * 在主表和从表 表中默认添加组织字段。
	 */
	public static final String CUSTOMER_COLUMN_CURRENTORGID="curentOrgId_";
	/**
	 * 流程运行ID
	 */
	public static final String CUSTOMER_COLUMN_FLOWRUNID="flowRunId_";
	/**
	 * 流程定义ID
	 */
	public static final String CUSTOMER_COLUMN_DEFID="defId_";
	
	
	/**
	 * 默认创建时间
	 */
	public static final String CUSTOMER_COLUMN_CREATETIME="CREATETIME";
	
	//表名
	private String name="";
	//表注释
	private String comment="";
	//列列表
	private List<ColumnModel> columnList=new ArrayList<ColumnModel>();
	
	static {
		String customerTablePrefix = AppConfigUtil.get("CUSTOMER_TABLE_PREFIX");
		CUSTOMER_TABLE_PREFIX = StringUtil.isEmpty(customerTablePrefix)?CUSTOMER_TABLE_PREFIX: customerTablePrefix;
		
		String customerColumnPrefix = AppConfigUtil.get("CUSTOMER_COLUMN_PREFIX");
		CUSTOMER_COLUMN_PREFIX = StringUtil.isEmpty(customerColumnPrefix)? CUSTOMER_COLUMN_PREFIX:customerColumnPrefix ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		if(StringUtil.isNotEmpty(comment)){
			comment=comment.replace("'", "''");
		}
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 添加列对象。
	 * @param model
	 */
	public void addColumnModel(ColumnModel model){
		this.columnList.add(model);
	}
	
	public List<ColumnModel> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<ColumnModel> columnList) {
		this.columnList = columnList;
	}
	
	
	public List<ColumnModel> getPrimayKey(){
		List<ColumnModel> pks=new ArrayList<ColumnModel>();
		for(ColumnModel column:columnList){
			if(column.getIsPk()){
				pks.add(column);
			}
		}
		return pks;
	}
	@Override
	public String toString() {
		return "TableModel [name=" + name + ", comment=" + comment + ", columnList=" + columnList + "]";
	}
}
