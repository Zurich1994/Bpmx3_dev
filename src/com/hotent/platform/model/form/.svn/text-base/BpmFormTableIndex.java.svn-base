package com.hotent.platform.model.form;


import java.util.List;
import com.hotent.core.model.BaseModel;

/**
 * 对象功能:bpm_form_field Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:raise
 * 创建时间:2012-02-06 15:49:21
 */
public class BpmFormTableIndex extends BaseModel  implements Cloneable 
{
	private static final long serialVersionUID = 1L;
	//索引类型
	public static String INDEX_TYPE_BITMAP									 = "BITMAP";
	public static String INDEX_TYPE_BTREE									 = "BTREE";
	public static String INDEX_TYPE_FUNCTION 								 = "FUNCTION";
	public static String INDEX_TYPE_HEAP 									 = "HEAP";
	public static String INDEX_TYPE_CLUSTERED								 = "CLUSTERED";
	public static String INDEX_TYPE_NONCLUSTERED							 = "NONCLUSTERED";
	public static String INDEX_TYPE_XML										 = "XML";
	public static String INDEX_TYPE_SPATIAL									 = "SPATIAL";
	public static String INDEX_TYPE_REG								 		 = "REGULAR";
	public static String INDEX_TYPE_DIM							 		 	 = "DIMENSIONBLOCK";
	public static String INDEX_TYPE_BLOK							 		 = "BLOCK";
	//表类型
	public static String TABLE_TYPE_TABLE									 ="TABLE";
	public static String TABLE_TYPE_VIEW									 ="VIEW";
	//索引状态
	public static String INDEX_STATUS_VALIDATE								 ="VALIDATE";
	public static String INDEX_STATUS_INVALIDATE							 ="INVALIDATE";
	
	private String indexTable;
	private String tableType;
	private String indexName;
	private String indexType;
	private String indexStatus;
	private List<String> indexFields;
	private boolean unique;
	private String indexDdl;
	private String indexComment;
	private boolean pkIndex;
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public String getIndexType() {
		return indexType;
	}
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}
	public List<String> getIndexFields() {
		return indexFields;
	}
	public void setIndexFields(List<String> indexFields) {
		this.indexFields = indexFields;
	}
	public String getIndexComment() {
		return indexComment;
	}
	public void setIndexComment(String indexComment) {
		this.indexComment = indexComment;
	}
	public String getIndexTable() {
		return indexTable;
	}
	public void setIndexTable(String indexTable) {
		this.indexTable = indexTable;
	}
	public String getIndexStatus() {
		return indexStatus;
	}
	public void setIndexStatus(String indexStatus) {
		this.indexStatus = indexStatus;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public String getIndexDdl() {
		return indexDdl;
	}
	public void setIndexDdl(String indexDdl) {
		this.indexDdl = indexDdl;
	}
	public boolean isUnique() {
		return unique;
	}
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	public boolean isPkIndex() {
		return pkIndex;
	}
	public void setPkIndex(boolean pkIndex) {
		this.pkIndex = pkIndex;
	}

	@Override
	public String toString() {
		return "BpmFormTableIndex [indexTable=" + indexTable + ", tableType=" + tableType + ", indexName=" + indexName + ", indexType=" + indexType
				+ ", indexStatus=" + indexStatus + ", indexFields=" + indexFields + ", unique=" + unique + ", indexDdl=" + indexDdl + ", indexComment="
				+ indexComment + ", pkIndex=" + pkIndex + "]";
	}
	
	
	
}