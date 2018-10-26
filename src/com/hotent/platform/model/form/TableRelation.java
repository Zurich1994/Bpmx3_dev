package com.hotent.platform.model.form;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 表关联关系。
 * 
 * @author ray
 *
 */
public class TableRelation {
	
	public TableRelation(){
		
	}
	
	public TableRelation(String pk){
		this.pk=pk;
	}
	
	
	/**
	 * 主键
	 */
	private String pk="";
	
	private Map<String, String> relations=new LinkedHashMap<String, String>();

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public Map<String, String> getRelations() {
		return relations;
	}

	public void setRelations(Map<String, String> relations) {
		this.relations = relations;
	}
	
	
	
	public void addRelation(String table,String fk){
		this.relations.put(table, fk);
	}
	
	

}
