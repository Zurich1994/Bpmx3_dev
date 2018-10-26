package com.hotent.core.db.helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.hotent.core.annotion.ClassDescription;
import com.hotent.core.annotion.FieldDescription;

/**
 * 根据对象获取增查该删的SQL。
 *
 * @param <T>
 */
public class ObjectHelper<T> {
	
	private T obj;
	
	/**
	 * 设置对象
	 * @param obj
	 */
	public void setModel(T obj)
	{
		this.obj=obj;
	}
	
	/**
	 * 获取对象对应的表名
	 * @param obj
	 * @return
	 */
	public  String getTableName()
	{
		Class cls= obj.getClass();
		ClassDescription clsDesc=(ClassDescription)cls.getAnnotation(ClassDescription.class);
		if(clsDesc==null)
			return cls.getSimpleName();
		return clsDesc.tableName();
	}
	
	/**
	 * 根据对象取得对象的columnmodel。
	 * @return
	 */
	public List<ColumnModel> getColumns()
	{
		List<ColumnModel> list=new ArrayList<ColumnModel>();
		Class cls= obj.getClass();
		Field[] fields=  cls.getDeclaredFields();
		for(int i=0;i<fields.length;i++)
		{
			Field fld=fields[i];
			ColumnModel column=new ColumnModel();
			column.setPropery(fld.getName());
			
			FieldDescription fldDesc=fld.getAnnotation(FieldDescription.class);
			if(fldDesc==null)
			{
				column.setColumnName(fld.getName());
				column.setPk(false);
			}
			else
			{
				column.setColumnName(fldDesc.columnName());
				column.setPk(fldDesc.pk());
				column.setCanUpd(fldDesc.canUpd());
			}
			list.add(column);
		}
		return list;
	}
	
	/**
	 * 取得columnlist的主键对象。
	 * @param list
	 * @return
	 */
	public ColumnModel getPk(List<ColumnModel> list)
	{
		ColumnModel columnModel=null;
		int len=list.size();
		for(int i=0;i<len;i++)
		{
			ColumnModel model=list.get(i);
			if(model.getPk())
				return model;
		}
		return columnModel;
	}
	
	private List<ColumnModel> getCommonCols(List<ColumnModel> list)
	{
		List<ColumnModel> cols=new ArrayList<ColumnModel>();
		int len=list.size();
		for(int i=0;i<len;i++)
		{
			ColumnModel model=list.get(i);
			if(!model.getPk()){
				cols.add(model);
			}
		}
		return cols;
	}
	
	private String[] getInsertColumns()
	{
		 List<ColumnModel> list= getColumns();
		String cols="";
		String vals="";
		int len=list.size();
		String[] aryStr=new String[2];
		for(int i=0;i<len;i++)
		{
			ColumnModel column=list.get(i);
			if(i<len-1)
			{
				cols+=column.getColumnName() +",";
				vals+=":" +column.getPropery() +",";
			}
			else
			{
				cols+=column.getColumnName() ;
				vals+=":" +column.getPropery(); 
			}
		}
		aryStr[0]=cols;
		aryStr[1]=vals;
		return aryStr;
	}
	
	/**
	 * 根据对象取得更新的sql语句。
	 * @return
	 */
	public String  getUpdSql()
	{
		List<ColumnModel> list= getColumns();
		List<ColumnModel> commonList=getCommonCols(list);
		ColumnModel pk=getPk(list);
		String tableName=getTableName();
		String sql="update ";
	 
		sql+=tableName +" set ";
	    
		String tmp="";
	    int len=commonList.size();
	    for(int i=0;i<len;i++)
	    {
	    	ColumnModel model=list.get(i);
	    	if(model.getCanUpd()){
	    		tmp+=model.getColumnName() +"=:" + model.getPropery() +",";
	    	}
	    }
	    if(tmp.length()>0)
	    	tmp=tmp.substring(0,tmp.length()-1);
	    
	    sql+=tmp;
	    
	    sql +=" where " +pk.getColumnName() +"=:" + pk.getPropery();
	    
	    return sql;
	}
	
	/**
	 * 根据对象取得删除的SQL语句。
	 * @return
	 */
	public String getDelSql()
	{
		List<ColumnModel> list= getColumns();
		String tableName=getTableName();
		ColumnModel column=getPk(list);
		String sql="delete from " + tableName +" where " + column.getColumnName() +"=:" + column.getPropery();
		return sql;
	}
	
	/**
	 * 根据对象取得明细的SQL语句。
	 * @return
	 */
	public String getDetailSql()
	{
		List<ColumnModel> list= getColumns();
		String tableName=getTableName();
		ColumnModel column=getPk(list);
		String sql="select a.* from " + tableName +" a where " + column.getColumnName() +"=:" + column.getPropery();
		return sql;
	}
	
	/**
	 * 取得添加的SQL语句。
	 * 
	 * @return 
	 * 
	 */
	public String getAddSql()
	{
		String tableName=getTableName();
		String[] aryCol=getInsertColumns();
		StringBuffer sb=new StringBuffer();
		sb.append("insert into ");
		sb.append(tableName);
		sb.append("(");
		sb.append(aryCol[0]);
		sb.append(")");
		sb.append(" values (");
		sb.append(aryCol[1]);
		sb.append(")");
		return sb.toString();
	}
	
	

}
