package com.hotent.core.fulltext;


import org.apache.lucene.index.IndexWriter;

/**
 * 全文索引接口。
 * @author ray
 *
 */
public interface IOperator {
	
	/**
	 * 添加索引文档。
	 * <pre>
	 * 	抽象方法，需要在子类中实现。
	 * 	Document doc=new Document();
	 *	doc.add(field)
	 *	ramWrite.addDocument(doc);
	 *</pre>
	 * @param writer
	 */
	public void addDocument(IndexWriter writer);
	
	/**
	 * 获取需要查询的字段名称，为数组。
	 * {'Title','Content'}
	 * @return
	 */
	public String[] getFields();

}
