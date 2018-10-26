package com.hotent.core.db.entity;

public class SQLField {
	public static final String COLUMNTYPE_INT = "int";
	public static final String COLUMNTYPE_VARCHAR = "varchar";
	public static final String COLUMNTYPE_CLOB = "clob";
	public static final String COLUMNTYPE_NUMBER = "number";
	public static final String COLUMNTYPE_DATE = "date";
	
	public static final String COLUMNTYPE_TEXT = "text";
	
	private String name;
	private String qualifiedName;
	private String label;
	private int index;
	private String comment;
	private String type;
	private String table;
	private boolean sortable;
	private boolean display;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQualifiedName() {
		return qualifiedName;
	}
	public void setQualifiedName(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public boolean isSortable() {
		return sortable;
	}
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean display) {
		this.display = display;
	}
	
}