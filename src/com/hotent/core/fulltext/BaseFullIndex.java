package com.hotent.core.fulltext;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKQueryParser;
import org.wltea.analyzer.lucene.IKSimilarity;

import com.hotent.core.page.PageBean;



/**
 * 全文索引基类。<br>
 * <br>已实现方法
 * <br>1.添加索引
 * <br>2.分页查询
 * <br>3.删除索引
 * <br>4.更新索引
 */
public  class BaseFullIndex {
	
	private Log log=(Log)LogFactory.getLog(BaseFullIndex.class);

	private String indexDir = "E:/temp/index";
	private String pkName = "id";
	private Analyzer analyzer=new IKAnalyzer();
	
	private IOperator indexOperator;
	
	
	

	private int maxResult = 400;

	/**
	 * 设置查询最大返回记录数
	 * 默认值为400
	 * @param size
	 */
	public void setMaxResult(int size) {
		this.maxResult = size;
	}
	
	/**
	 * 设置全文索引的索引目录
	 * @param dir
	 */
	public void setIndexDir(String dir) {
		this.indexDir = dir;
	}


	/**
	 * 设置主键名称，默认为“id”
	 * @param pkName
	 */
	public void setPkName(String pkName) {
		this.pkName = pkName;
	}
	
	public void setIndexOperator(IOperator indexOperator) {
		this.indexOperator = indexOperator;
	}
	
	

	/**
	 * 全部索引
	 * 具体实现由抽象函数“addDocument”完成。
	 * 
	 * @throws IOException
	 */
	public void indexAll() throws IOException {
		Directory fsDir = FSDirectory.open(new File(this.indexDir));

		// 1.将索引读取到内存中
		Directory ramDir =new RAMDirectory(fsDir);
		//Directory ramDir = new RAMDirectory();
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_33, analyzer);
		// 默认create_or_append
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);// 总是重新创建
		IndexWriter ramWrite = new IndexWriter(ramDir, config);
		
		indexOperator.addDocument(ramWrite);
		ramWrite.close();
		// 3.关闭时,写入到文件
		IndexWriterConfig fsConfig = new IndexWriterConfig(Version.LUCENE_33,analyzer);
		// 默认create_or_append
		fsConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);// 总是重新创建
		IndexWriter fsWriter = new IndexWriter(fsDir, fsConfig);
		// 将内存的索引文件加入到fsiw中
		fsWriter.addIndexes(ramDir);
		fsWriter.commit();
		// 优化索引文件(合并索引文件)
		fsWriter.optimize();
		fsWriter.close();
		fsDir.close();
	}

	/**
	 * 根据ID删除文档
	 * 需要设定pkName，即在索引段中的字段名称 ，默认为"id"
	 * @param id
	 * @throws CorruptIndexException
	 * @throws IOException
	 */
	public void delById(String id) throws CorruptIndexException, IOException {
		File file = new File(this.indexDir);
		Directory fsDir = FSDirectory.open(file);
	
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_33,analyzer);
		config.setOpenMode(IndexWriterConfig.OpenMode.APPEND);
		IndexWriter write = new IndexWriter(fsDir, config);
		Term term = new Term(pkName, id);
		write.deleteDocuments(term);
		write.close();
	}

	/**
	 * 更新索引。
	 * 根据ID更新索引的文档。
	 * 需要指定主键字段的名称，默认为 “id”
	 * @param pkId 主键ID
	 * @param doc 对应的文档
	 * @throws CorruptIndexException
	 * @throws LockObtainFailedException
	 * @throws IOException
	 */
	public void updDoc(String pkId, Document doc) throws CorruptIndexException,
			LockObtainFailedException, IOException {
		File file = new File(this.indexDir);
		Directory fsDir = FSDirectory.open(file);

		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_33,analyzer);
		config.setOpenMode(IndexWriterConfig.OpenMode.APPEND);
		IndexWriter write = new IndexWriter(fsDir, config);
		Term term = new Term(this.pkName, pkId);
		write.updateDocument(term, doc);
		write.close();
	
	}
	
	/**
	 * 添加文档索引。
	 * @param doc
	 * @throws IOException
	 */
	public void addDoc(Document doc) throws IOException {
		Directory fsDir = FSDirectory.open(new File(this.indexDir));
		IndexWriterConfig fsConfig = new IndexWriterConfig(Version.LUCENE_33, analyzer);
		fsConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);// 总是重新创建
		IndexWriter fsWriter = new IndexWriter(fsDir, fsConfig);
		fsWriter.addDocument(doc);
		fsWriter.close();
	}
	


	/**
	 * 高亮字符串。
	 * @param q
	 * @param content
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	public String heightLight(String q,  String content)
			throws ParseException, IOException, InvalidTokenOffsetsException {
		
		 QueryParser parser = new QueryParser(Version.LUCENE_30, "field",this.analyzer);   
				          
         Query query = parser.parse(q);   
         SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color=\"red\">","</font>");   
         Highlighter highlight = new Highlighter(formatter, new QueryScorer(query));   
         TokenStream tokens = analyzer.tokenStream("field", new StringReader(content));   
         String str=highlight.getBestFragment(tokens, content);   
         if(str==null)
         {
        	 if(content.length()>50)
        		 return content.substring(0,50);
        	 return content;
         }
        	 
		 return str;

	}

	/**
	 * 根据关键字查询。
	 * 需要指定查询的字段，需要在子类中进行实现
	 * @param q
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unused")
	public List getPage(String q, int currentPage, int pageSize) {
		try 
		{
			PageBean pageBean=new PageBean(currentPage,pageSize);
			File file = new File(this.indexDir);
			Directory fsdir = FSDirectory.open(file);
			Directory dir =new RAMDirectory(fsdir);
			IndexSearcher search = new IndexSearcher(dir);
			search.setSimilarity(new IKSimilarity());
			String[] fields = indexOperator.getFields();
			Query query =IKQueryParser.parseMultiField(fields, q);
			TopDocs hits = search.search(query, this.maxResult);
			int totalRecord = hits.totalHits;
			int amount= hits.scoreDocs.length;
			

			pageBean.setTotalCount(totalRecord);

			if (hits == null) {
				return null;
			}
			int start = (currentPage - 1) * pageSize;
			int end = currentPage * pageSize;
			List<Document> list = new ArrayList<Document>();
			for (int i = 0; i < totalRecord; i++) {
				if (i >= start && i < end) {
					Document doc = search.doc(hits.scoreDocs[i].doc);
					list.add(doc);
				}
			}
			
			return list;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;

		}

	}
	

}
