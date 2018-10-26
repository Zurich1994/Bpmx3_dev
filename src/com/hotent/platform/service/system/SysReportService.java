package com.hotent.platform.service.system;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.MapUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.TimeUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.ZipUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.dao.system.SysReportDao;
import com.hotent.platform.model.system.SysReport;


@Service
public class SysReportService extends BaseService<SysReport> {

	//处理状态
	public final static Long STATUS_REPORT_DONE=1L;
	//待处理状态
	public final static Long STATUS_REPORT_UNDONE=0L;
	//固定值
	public final static String STATIC_VALUE="0";
	//表单输入
	public final static String FROM_INPUT="1";
	//脚本
	public final static String FROM_SCRIPT="2";
	//报表模板后缀名
	public final static String JREPORT_EXT="jrxml"; 
	//报表编译后的后缀名
	public final static String JREPORT_COMPILED_EXT = "jasper" ;
	//向报表模板中添加的分页参数（分页起始位置）
	protected final static String HT_REPORT_PAGE_START_INDEX = "HT_REPORT_PAGE_START_INDEX" ;
	//向报表模板中添加的分页参数（分页大小）
	protected final static String HT_REPORT_PAGE_SIZE = "HT_REPORT_PAGE_SIZE" ;
	
	protected static Properties property = null ;
	
	protected Logger logger = LoggerFactory.getLogger(SysReportService.class);
	
	@Resource
	private SysDataSourceLService sysDataSourceLService ;
	@Resource
	private SysDataSourceService sysDataSourceService ;
	@Resource
	private SysReportDao dao;
	@Resource
	private GroovyScriptEngine groovyScriptEngine;
	
	public SysReportService(){
	}
	
	/**
	 * 根据模版文件得到可以传递的参数列表
	 * @param path *.jrxml模版文件全路径
	 * @return
	 */
	protected Map<String, Object> getNeededParameter(Document doc){
		NodeList nodeList = doc.getElementsByTagName("parameter") ;
		Map<String, Object> result = new HashMap<String, Object>() ;
		if(BeanUtils.isEmpty(nodeList)) return result ;
		for(int i = 0; i < nodeList.getLength(); i++){
			NamedNodeMap map = nodeList.item(i).getAttributes();
			Node isForPrompting = map.getNamedItem("isForPrompting") ;
			if(isForPrompting==null||"true".equalsIgnoreCase(isForPrompting.getNodeValue()))
				result.put(map.getNamedItem("name").getNodeValue(),map.getNamedItem("class").getNodeValue()) ;
		}
		return result ;
	}
	
	/**向模板文件中添加相应的分页参数，并 解析 返回模板中的sql语句，
	 * @param dsName 报表所使用的数据源别名 
	 * @param sourcePath 操作完成之后，将修改后的xml内容写回此文件路径并编译
	 * @param doc 
	 * @param oldSql 如果此参数有值，则只修改分页SQL
	 * @throws Exception 
	 */
	protected String getSql(String dsName,String sourcePath,Document doc,String oldSql) throws Exception{
		NodeList nodeList = doc.getElementsByTagName("queryString") ;
		//用于回滚时写回
		Document tempDoc = (Document) doc.cloneNode(true) ;
		String sql = "" ;
		Element element ;
		CDATASection section ;
		if(nodeList.getLength()>0){
			for(int k=0;k<nodeList.getLength();k++){
				Element queryElement = (Element) nodeList.item(k);
				//子报表中可能也会存在queryString节点，因此需要确定其父节点是否是顶层节点
				if(queryElement.getParentNode().getNodeName()=="jasperReport"){
					sql = queryElement.getTextContent().trim();
					if(StringUtil.isEmpty(sql)){
						break;
					}else if(StringUtil.isNotEmpty(oldSql)){
						//修改了数据源，需要修改分页SQL语句
						sql = oldSql ;
					}else {
						//获取报表SQL语句
						oldSql = sql ;
						/**修改报表模板，添加数据库分页查询参数**/
						element = doc.createElement("parameter") ;
						element.setAttribute("name",HT_REPORT_PAGE_START_INDEX) ;
						element.setAttribute("class","java.lang.Integer") ;
						//表示不需要用户手动进行传参
						element.setAttribute("isForPrompting","false") ;
						doc.getDocumentElement().insertBefore(element, queryElement) ;
						Element defaultValueExpression = doc.createElement("defaultValueExpression") ;
						section = doc.createCDATASection("0") ;
						defaultValueExpression.appendChild(section) ;
						element.appendChild(defaultValueExpression) ;
						
						element = doc.createElement("parameter") ;
						element.setAttribute("name",HT_REPORT_PAGE_SIZE) ;
						element.setAttribute("class","java.lang.Integer") ;
						element.setAttribute("isForPrompting","false") ;
						doc.getDocumentElement().insertBefore(element, queryElement) ;
						defaultValueExpression = doc.createElement("defaultValueExpression") ;
						section = doc.createCDATASection("20") ;
						defaultValueExpression.appendChild(section) ;
						element.appendChild(defaultValueExpression) ;
					}
					if(StringUtil.isNotEmpty(dsName)){
						//从配置文件中获取对应数据库的分页SQL语句
						Object[] objectArr = new Object[]{HT_REPORT_PAGE_START_INDEX,HT_REPORT_PAGE_SIZE,oldSql} ;
						String dbType = sysDataSourceService.getByAlias(dsName).getDbType();
						if(property==null) 
							//读取存放分页sql的配置文件
							getProperty();
						String formatString = property.getProperty(dbType,"");
						if(StringUtil.isNotEmpty(formatString)){
							sql = MessageFormat.format(formatString,objectArr) ;
						}
					}
					section = doc.createCDATASection(sql) ;
					queryElement.setTextContent("") ;
					queryElement.appendChild(section) ;
					writeToXml(doc, sourcePath);
					try{
						//重新编译主报表
						JasperCompileManager.compileReportToFile(sourcePath,sourcePath.replace(JREPORT_EXT, JREPORT_COMPILED_EXT));
					}catch(Exception e){
						//如果编译过程出错,则还原此报表模版
						writeToXml(tempDoc, sourcePath) ;
						throw new Exception("报表编译出错，请检查报表配置！"+e.getMessage()) ;
					}
					break ;
				}
			}
		}
		return oldSql ;
	}
	
	/**
	 * @param conn数据库连接		空数据源则传入null
	 * @param parameters  传入模版的参数，没有可直接传null
	 * @param jasperFilePath ireport模版文件.jasper全路径	
	 * <pre>如 e:\\report\\report1.jasper
	 * 如果文件不存在，则会找该路径下对应的*.jrxml文件并将其编译为*.jasper文件
	 * </pre>
	 * @return  JasperPrint 对象
	 * @throws Exception 
	 */
	protected JasperPrint getJasperPrint(Connection conn,Map<String,Object> parameters,String jasperFilePath) throws Exception{
		if(!new File(jasperFilePath).exists()){
			try{
				//将jrxml编译成jasper:
				String sourceFilePath = jasperFilePath.replace(JREPORT_COMPILED_EXT, JREPORT_EXT) ;
				JasperCompileManager.compileReportToFile(sourceFilePath,jasperFilePath);
			}catch(Exception e){
				throw new Exception("报表编译出错！"+e.getMessage());
			}
		}
		
		//将获得的数据填充到模版文件中
		JasperPrint jasperPrint = null;
		//子报表需注意的java代码：
		String uploadDir = AppUtil.getRealPath(getUploadDir())+File.separator;
		if(parameters == null) parameters = new HashMap<String,Object>();
	    parameters.put("SUBREPORT_DIR", uploadDir);
		try {
			if(conn==null){
				jasperPrint = JasperFillManager.fillReport(jasperFilePath, parameters, new JREmptyDataSource());
			}else
				jasperPrint = JasperFillManager.fillReport(jasperFilePath, parameters, conn);
		} catch (JRException e) {
			throw new Exception("数据填充出错，请检查报表参数或数据源设置！"+e.getMessage());
		}
		return jasperPrint ;
	}
	
	/**
	 * 通过后缀名获取相应的JRAbstractExporter
	 * @param ext 格式，如pdf,html,xls doc
	 * @param jasperPrintList 
	 * @return	JRAbstractExporter
	 */
	protected JRAbstractExporter getExporterByExt(String ext, JasperPrint jasperPrint) {
		JRAbstractExporter exporter = null ;
		if("pdf".equalsIgnoreCase(ext)){
			exporter = new JRPdfExporter() ;
		}else
		if("html".equalsIgnoreCase(ext)){
			exporter = new JRHtmlExporter() ;
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);   
			//exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, preImageFileDir+"html_files/");
			exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
			exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
		}else
		if("xls".equalsIgnoreCase(ext)) {
			exporter = new JRXlsExporter() ;
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,  Boolean.FALSE);
		    exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		    exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		}else{
			exporter = new JRRtfExporter() ;
		}
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		return exporter ;
	}
	
	/**
	  * 获取下载报表所需参数
	  * @param ext	文件名称	例如：pdf。
	  * @param	包含其他所需参数	
	 * @throws Exception 
	  */
	public void export(String exportType,Map<String, Object> params) throws Exception {
		SysReport sysReport = (SysReport) params.get("sysReport");
		Map<String,Object> temp = new HashMap<String,Object>() ;
		JSONArray arr = JSON.parseArray(sysReport.getParams());
		for(int i=0;i<arr.size();i++){
			temp.put((String) arr.getJSONObject(i).get("field"),arr.getJSONObject(i).get("paraType"));
		}
		int totalCount = (Integer) params.get("totalCount");
		String reportFilePath = (String) params.get("filePath") ;
		//过滤传入的参数
		getMap(params,temp) ;
		//保存之后的文件全路径
      	String destFilePath = reportFilePath.replace(JREPORT_EXT, exportType) ;
		Connection conn = null;
		String dsName = sysReport.getDsName();
		if(StringUtil.isNotEmpty(dsName)){
			conn = DataSourceUtil.getDataSourceByAlias(dsName).getConnection();
		}
		params.put(HT_REPORT_PAGE_START_INDEX, 0) ;
		params.put(HT_REPORT_PAGE_SIZE, totalCount) ;
		JasperPrint jasperPrint = getJasperPrint(conn, params, reportFilePath.replace(JREPORT_EXT, JREPORT_COMPILED_EXT)) ;
		JRAbstractExporter exporter = getExporterByExt(exportType, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFilePath) ;
		exporter.exportReport();
		if(conn != null){
			conn.close();
			conn = null;
		}
	}
	
	/**
	  * 分页展示报表
	  * @param map
	  * @return
	  * @throws Exception
	  */
	public Map<String,Object> show(Map<String,Object> map) throws Exception{
		String contextPath = (String) map.get("contextPath");
		Long reportId = (Long) map.get("reportId") ;
		SysReport sr = getById(reportId);
		if(sr==null) throw new Exception("找不到报表文件！");
		String dsName = sr.getDsName();
		String filePath = sr.getFilePath();
		String reportFilePath = AppUtil.getRealPath(filePath) ;
		String jasperFilePath = reportFilePath.replace(JREPORT_EXT, JREPORT_COMPILED_EXT) ;
		String destFilePath = reportFilePath.replace(JREPORT_EXT, "html") ;
		String imagesDir = filePath.replace(JREPORT_EXT, "html_files/") ;
		String imagesUriTmp = contextPath+imagesDir ;
		Map<String,Object> params = new HashMap<String,Object>() ;
		JSONArray arr = JSON.parseArray(sr.getParams());
		for(int i=0;i<arr.size();i++){
			params.put((String) arr.getJSONObject(i).get("field"),arr.getJSONObject(i).get("paraType"));
		}
		Map<String,Object> result = new HashMap<String, Object>() ;
		int totalCount = (Integer) map.get("totalCount");
		if(!new File(reportFilePath).exists() && !new File(jasperFilePath).exists()){
			throw new Exception("找不到报表文件！") ;
		}

		Map<String,Object> tempMap = (Map<String, Object>) map.get("paramMap");
		if(totalCount<=0) tempMap = getParamsValue(sr, tempMap);
		//复制传入的paramData副本，避免修改传入的map
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.putAll(tempMap);
		params = getMap(paramMap,params) ;
		paramMap.clear();
		if(BeanUtils.isNotEmpty(params))
			paramMap.putAll(params);
		int pageSize = (Integer)map.get("pageSize") ;
		int pageIndex = (Integer)map.get("pageIndex") ;
		
		JasperPrint jasperPrint = null ;
		JRAbstractExporter exporter = null ;
		Connection conn = null;
		if(StringUtil.isNotEmpty(dsName)){
			conn=DataSourceUtil.getDataSourceByAlias(dsName).getConnection();
			if(totalCount<=0){//初次预览，totalCount为0
				totalCount = getTotalCount(sr.getDsName(),sr.getSql(),tempMap) ;
				//编译得到html文件，以供客户端进行打印预览使用，可能客户每次设置的参数不同，因此每次预览都需要重新编译HTML文件
				if(totalCount>0){
					paramMap.put(HT_REPORT_PAGE_START_INDEX, 0) ;
					paramMap.put(HT_REPORT_PAGE_SIZE, totalCount) ;
					jasperPrint = getJasperPrint(conn, paramMap, jasperFilePath);
				}else{
					//空数据源
					jasperPrint = getJasperPrint(null, paramMap, jasperFilePath);
				}
				exporter = getExporterByExt("html", jasperPrint);
				exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imagesUriTmp);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,destFilePath);
				exporter.exportReport();
			}
		}
		//空数据源并且还没编译出html页面，则生成html
		else if(!FileUtil.isExistFile(destFilePath)){
			totalCount = 1;
			jasperPrint = getJasperPrint(null, paramMap, jasperFilePath);
			exporter = getExporterByExt("html", jasperPrint);
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imagesUriTmp);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,destFilePath);
			exporter.exportReport();
		}
		int lastPageIndex = totalCount%pageSize==0?totalCount/pageSize-1:totalCount/pageSize ;
		if (pageIndex > 0) {
			pageIndex = pageIndex - 1;
		}
		if (pageIndex < 0) {
			pageIndex = 0;
		}
		if (lastPageIndex>0 && pageIndex > lastPageIndex) {
			pageIndex = lastPageIndex;
		}
		params.put(HT_REPORT_PAGE_START_INDEX, pageIndex*pageSize) ;
		params.put(HT_REPORT_PAGE_SIZE, pageSize) ;

		//预览html报表
		jasperPrint = getJasperPrint(conn, params, jasperFilePath) ;
        StringBuffer tempPreview = new StringBuffer();        
		exporter = getExporterByExt("html", jasperPrint);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imagesUriTmp);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME, AppUtil.getRealPath(imagesDir));
		exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR,Boolean.TRUE);
		exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER,tempPreview);
		exporter.exportReport();
		
		// 用完释放数据库连接
		if(conn != null) {
			conn.close();
			conn = null;
		}
		
		result.put("sourceHtmlFile", filePath.replace(JREPORT_EXT, "html")) ;
		result.put("tempPreview", tempPreview) ;
		result.put("pageIndex", pageIndex+1) ;
		result.put("lastPageIndex", lastPageIndex+1) ;
		result.put("pageSize", pageSize) ;
		result.put("reportId", reportId) ;
		result.put("totalCount", totalCount) ;
		result.put("params", sr.getParams()) ;
		return result ;
	}
	
	/**
	 * 获取报表总记录数
	 * @param reportFilePath 报表路径
	 * @param params	报表参数
	 * @return
	 * @throws Exception
	 */
	protected int getTotalCount(String dsName,String sql,Map<String,Object> params) throws Exception{
		if(StringUtils.isEmpty(sql) || StringUtils.isEmpty(dsName)) return 0;
		//获取以 '$P{' 开头,以 '}' 结尾的内容
		Pattern p = Pattern.compile("(?<=\\$P\\{)[^\\}]*(?=\\})");
		Matcher m = p.matcher(sql);
		Set<String> keys = new HashSet<String>() ;
		while(m.find()){ 
			keys.add(m.group()) ;
		}
		Iterator<String> it = keys.iterator() ;
		String key ;
		//使用传入的参数值替换模版中的参数
		while(it.hasNext()){
			key = it.next() ;
			sql = sql.replaceAll("\\$P\\{"+key+"\\}", "'"+MapUtil.getString(params, key)+"'") ;
		}
		JdbcTemplate jdbcTemplate = JdbcTemplateUtil.getNewJdbcTemplate(dsName);
		int result = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM("+sql+")HT_COUNT");
		//释放连接
		jdbcTemplate.getDataSource().getConnection().close();
		jdbcTemplate=null;
		return result;
	}
	
	/**
	 * 根据tempMap的key以及value（className）过滤sourcesMap的值
	 * @param sourceMap
	 * @param tempMap
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Object> getMap(Map<String, Object> sourceMap, Map<String, Object> tempMap) throws Exception{
		Map<String, Object> result =  new HashMap<String, Object>();
		Map<String, Object> temp =  new HashMap<String, Object>();
		for(String key:tempMap.keySet()){
			if(sourceMap.containsKey(key)){
				//当Jreport需要传入Number类型的参数，若直接传入String类型会报错
				result.put(key, getRealValue((String) tempMap.get(key),(String)sourceMap.get(key)));
				temp.put(key, result.get(key));
			}else{
				temp.put(key, "");
			}
		}
		sourceMap.clear();
		sourceMap.putAll(temp) ;
		return result ;
	}
	
	/**
	 * 根据className返回值，目前只处理Number类型
	 * @param className 类名
	 * @param value	未转换的值
	 * @return	转换后的Object
	 * @throws Exception
	 */
	protected Object getRealValue(String className,String value) throws Exception{
		Constructor<?> con = null ;
		if(StringUtil.isEmpty(className)) return "";
		Class<?> c = Class.forName(className) ;
		if(BeanUtils.isInherit(c, Number.class)){
			if(StringUtil.isNumberic(value)){
				con = Class.forName(className).getConstructor(String.class) ;
				Double max = c.getField("MAX_VALUE").getDouble(null);
				Double val = Double.parseDouble(value);
				//传入的值超过最大值，则返回最大值
				if(val>max){
					return c.getField("MAX_VALUE").get(null) ;
				}
				return con.newInstance(value) ;
			}
			return null ;
		}if(BeanUtils.isInherit(c, Date.class)){
			if(value.indexOf(" ")>0) return TimeUtil.convertString(value);
			return TimeUtil.convertString(value, "yyyy-MM-dd");
		}
		return value ;
	}
	
	public void save(SysReport sysReport) throws Exception{
		if(BeanUtils.isEmpty(sysReport)) return;
		String sourcePath = AppUtil.getRealPath(sysReport.getFilePath());
		String sourceExt = sysReport.getExt();
		//只处理压缩包和.jrxml文件
		if(JREPORT_EXT.equalsIgnoreCase(sourceExt)){
			// 读取报表jxml文件，添加相关内容 
			sysReport = handleReport(sysReport,null);
			add(sysReport);
			return ;
		}
		if(!"zip".equalsIgnoreCase(sourceExt)){
			throw new Exception("请选择 *.zip或*.jrxml文件进行上传");
		}
		//解压到临时目录
		String targetDirPath = AppUtil.getRealPath(getUploadDir()+UniqueIdUtil.genId());
		ZipUtil.unZip(sourcePath, targetDirPath, "");
		File[] files = FileUtil.getFiles(targetDirPath);
		boolean isFound = false ;
		String fileName = "" ;
		for(int i=0;i<files.length;i++){
			//只处理jreport文件类型
			if(!files[i].isFile() || !JREPORT_EXT.equalsIgnoreCase(FileUtil.getFileExt(files[i]))) continue ;
			Long reportId=UniqueIdUtil.genId();
			//附件名称
			fileName = files[i].getName() ;
			SysReport newSysReport = sysReport;
			newSysReport.setReportId(reportId);
		    // 上传时间
			newSysReport.setCreatetime(new java.util.Date());
		    // 扩展名
			newSysReport.setExt(JREPORT_EXT);
			newSysReport.setFileName(fileName.replace("."+JREPORT_EXT, ""));
			// 读取报表jxml文件，添加相关内容
			newSysReport = handleReport(newSysReport,targetDirPath +File.separator+ fileName);
		    //附件路径
		    fileName = getUploadDir() + fileName;
		    newSysReport.setFilePath(fileName);
			add(newSysReport);
			isFound = true ;
		}
		if(!isFound){
			FileUtil.deleteDir(new File(targetDirPath));
			throw new Exception("请检查压缩文件目录结构是否符合要求");
		}
		//删除压缩包
		FileUtil.deleteFile(sourcePath);
		//复制临时解压目录到/jreport/下
		FileUtil.copyDir(targetDirPath, AppUtil.getRealPath(getUploadDir()));
		//删除临时解压目录
		FileUtil.deleteDir(new File(targetDirPath));
	}
	
	/**
	  * 处理上传后的报表
	  * @param fileId
	 * @param typeId 
	 * @return 
	 * @throws Exception 
	  */
	protected SysReport handleReport(SysReport sysReport,String tempPath) throws Exception {
		if(BeanUtils.isEmpty(sysReport)) return sysReport;
		if(StringUtil.isEmpty(tempPath))
			tempPath = AppUtil.getRealPath(sysReport.getFilePath());
		Map<String, Object> params = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Document doc = getDocument(tempPath) ;
		//获取主报表名参数 key是参数名，value是参数对应的java类型
		Map<String, Object> temp = getNeededParameter(doc);
		for(String key:temp.keySet()){
			params = new HashMap<String,Object>();
	    	params.put("field", key);
	    	params.put("paraType", temp.get(key));
	    	list.add(params);
	    }
		sysReport.setParams(JSON.toJSONString(list));
		sysReport.setSql(getSql(sysReport.getDsName(),tempPath,doc,""));
		if(BeanUtils.isEmpty(temp)){
			//没有参数，则不需要进行配置就可直接进行预览查看
			sysReport.setStatus(STATUS_REPORT_DONE);
		}else{
			sysReport.setStatus(STATUS_REPORT_UNDONE);
		}
		return sysReport ;
	}
	
	 /**
	  * 解析SysReport对象数据，返回报表所需的参数列表
	  * <pre>
	  *  返回值为map：
	  *  键：paramList：表示为数据库字段PARAMS转化后的参数列表对象
	  * </pre>
	  */
	public Map<String,Object> getParamList(SysReport sysReport){
		Map<String,Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> paramList = new ArrayList<Map<String,Object>>();
		if(BeanUtils.isEmpty(sysReport)) {
			result.put("paramList", paramList);
			return result;
		}
		JSONArray arry = JSON.parseArray(sysReport.getParams());
		for(int i=0;i<arry.size();i++){
			paramList.add(arry.getJSONObject(i));
		}
		result.put("paramList", paramList);
		return result ;
	}
	
	/**
	 * 更新报表配置
	 * @param sysReport
	 * @throws Exception
	 */
	public void updateReport(SysReport sysReport) throws Exception{
		SysReport old = getById(sysReport.getReportId());
		if(!sysReport.getDsName().equals(old.getDsName())){
			String sourcePath = AppUtil.getRealPath(old.getFilePath());
			//数据源发生了改变，修改分页查询SQL
			getSql(sysReport.getDsName(), sourcePath, getDocument(sourcePath), old.getSql());
		}
		update(sysReport);
	}
	
	/**
	 * 处理参数值类型为固定值以及脚本的，返回实际的参数值
	 * @param sysReport
	 * @param params
	 * @return
	 */
	protected Map<String,Object> getParamsValue(SysReport sysReport,Map<String,Object> params){
		Map<String,Object> map;
		JSONArray arr = JSON.parseArray(sysReport.getParams());
		String defaultType = "";
		String field = "" ;
		Object value = "" ;
		for(int i=0;i<arr.size();i++){
			map = arr.getJSONObject(i);
			defaultType = (String) map.get("defaultType");
			//表单输入，不处理
			if(FROM_INPUT.equals(defaultType)) continue ;
			field = (String) map.get("field");
			//固定值或者脚本代码
			value =  map.get("defaultValue");
			if(FROM_SCRIPT.equals(defaultType)){
				//脚本值
				value = groovyScriptEngine.executeObject((String)value, params);
			}
			params.put(field,value);
		}
		return params ;
	}
	
	/**
	 * 写回函数
	 * @param doc
	 * @param rptdesignPath
	 */
	 protected static void writeToXml(Document doc, String rptdesignPath) {
		try {
		   OutputStream fileoutputStream = new FileOutputStream(rptdesignPath);
		   TransformerFactory tFactory = TransformerFactory.newInstance();
		   Transformer transformer = tFactory.newTransformer();
		   transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		   DOMSource source = new DOMSource(doc);
		   StreamResult result = new StreamResult(fileoutputStream);
		   transformer.transform(source, result);
		   fileoutputStream.close();
		  } catch (Exception e) {
				e.printStackTrace();
		}
	 }
	 
	 /**
	  * 读取指定文档
	  * @param path
	  * @return
	  */
	 protected static  Document getDocument(String path){
		 Document doc = null;
		 try {
			 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			 DocumentBuilder db =  dbf.newDocumentBuilder();
			 doc = db.parse(new File(path));	
		 } catch (Exception e) {
			 e.printStackTrace();
		 } 
		 return doc;
	}
	
	public String getUploadDir(){
		return "/jreport/";
	}
	
	protected void getProperty() throws Exception{
		String classPath = FileUtil.getClassesPath();
		File jreportProp = new File(classPath+"jasperreports.properties");
		property = new Properties();
		try {
			if(!jreportProp.exists()){//如果不存在，则创建一个空文件
				jreportProp.createNewFile();
			}
			InputStream in = SysReportService.class.getClassLoader().getResourceAsStream("jasperreports.properties") ;
			//读取分页SQL配置文件
			property.load(in) ;
		} catch (IOException e) {
			throw new Exception("读取jasperreports.properties配置文件出错！");
		}
	}
	
	@Override
	protected IEntityDao<SysReport,Long> getEntityDao() {
		return dao;
	}
}
