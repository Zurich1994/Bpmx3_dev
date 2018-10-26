package com.hotent.platform.service.system;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.DesktopColumnDao;
import com.hotent.platform.dao.system.DesktopLayoutcolDao;
import com.hotent.platform.dao.system.DesktopMycolumnDao;
import com.hotent.platform.model.system.DesktopColumn;
import com.hotent.platform.service.form.BpmFormTemplateService;

/**
 * 对象功能:桌面栏目 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-03-20 16:39:01
 */
@Service
public class DesktopColumnService extends BaseService<DesktopColumn>
{
	private static Log logger = LogFactory.getLog(DesktopColumnService.class);
	@Resource
	private DesktopColumnDao dao;
	@Resource
	private DesktopLayoutcolDao desktopLayoutcolDao;
	@Resource
	private DesktopMycolumnDao desktopMycolumnDao;
	
	public DesktopColumnService()
	{
	}
	
	@Override
	protected IEntityDao<DesktopColumn, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 获取模板路径。
	 * @return
	 */
	public String getTemplatePath(){
		String templatePath=FileUtil.getClassesPath() +"template" + File.separator;
		return templatePath;
	}
	
	/**
	 * 删除桌面栏目
	 * @param ids
	 */
	public void delDesktopColumn(Long[] ids)
	{
		if(ids==null||ids.length==0)return;
		for (Long id : ids)
		{
			desktopLayoutcolDao.delByLinkLayout(id);
			desktopMycolumnDao.delByLinkMycolumn(id);
		}
		delByIds(ids);
	}
	
	/**
	 * 判断是否存在该栏目
	 * @param deskName
	 * @return
	 */
	public boolean isExistDesktopColumn(String templateId){
		DesktopColumn desktopColumn=dao.getByTemplateId(templateId);
		if(desktopColumn==null){
			//不存在
			return false;
		}else{
			//存在
			return true;
		}
	}

	/**
	 * 获取所有的系统原始桌面栏目模板
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void initAllTemplate(long userId) throws Exception{
		
		//删除系统栏目，包括已设定的用户中的。
		List<DesktopColumn> desktopColumnList= new ArrayList<DesktopColumn>();
	    desktopColumnList=dao.getSysColumn();
		Long[] ids=new Long[desktopColumnList.size()];
		for(int i=0;i<desktopColumnList.size();i++){
			DesktopColumn dc=desktopColumnList.get(i);
			ids[i]=dc.getId();
		}
		delDesktopColumn(ids);
		addDesktopColumn();
	}
	
	/**
	 * 根据桌面栏目配置文件，找到模板文件，
	 * 将系统初始桌面栏目保存在数据库中
	 * @param userId
	 */
	public void addDesktopColumn() throws Exception{
		String templatePath=getTemplatePath();
		String xml= FileUtil.readFile(templatePath+"desktop"+File.separator +"desktop.xml");
		Document document=Dom4jUtil.loadXml(xml);
		Element root=document.getRootElement();
		List<Element> list=root.elements();
		for(Element element:list){
			String name=element.attributeValue("name");
			String serviceMethod=element.attributeValue("serviceMethod");
			String templateName=element.attributeValue("templateName");
			String templateId=element.attributeValue("templateId");
			String columnUrl=element.attributeValue("columnUrl");
			Integer isSys=Integer.parseInt(element.attributeValue("isSys"));
			String fileName=templateId+".ftl";
			String html=FileUtil.readFile(templatePath+"desktop"+File.separator+fileName);
			DesktopColumn desktopColumn=new DesktopColumn();
			desktopColumn.setColumnUrl(columnUrl);
			desktopColumn.setCreatetime(new Date());
			desktopColumn.setHtml(html);
			desktopColumn.setColumnName(name);
			desktopColumn.setServiceMethod(serviceMethod);
			desktopColumn.setTemplateId(templateId);
			desktopColumn.setTemplateName(templateName);
			desktopColumn.setTemplatePath(templatePath);
			desktopColumn.setIsSys(isSys);
			desktopColumn.setId(UniqueIdUtil.genId());
			desktopColumn.setMethodType(0);
			dao.add(desktopColumn);
		}
	}
	
	/**
	 * 根据handler取得数据。
	 * <pre>
	 * handler 为 service +"." + 方法名称。
	 * </pre>
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	public Object getModelByHandler(String handler) throws Exception{
		int rtn=BpmUtil.isHandlerValidNoCmd(handler,null);
		if(rtn==0){
			String [] aryHandler=handler.split("[.]");
			Object model=null;
			if(aryHandler!=null){
				String beanId=aryHandler[0];
				String method=aryHandler[1];
				//触发该Bean下的业务方法
				Object serviceBean=AppUtil.getBean(beanId);
				if(serviceBean!=null){
					Method invokeMethod=serviceBean.getClass().getDeclaredMethod(method, new Class[0]);
					model=invokeMethod.invoke(serviceBean,new Object[0]);
				}
			} 
			return model;
		}
		return null;
		
	}
	
	
	
	
	/**
	 *  初始化桌面栏目，添加桌面栏目到数据库
	 */
	public static void initDesk() {
		DesktopColumnService desktopColumnService=(DesktopColumnService)AppUtil.getBean(DesktopColumnService.class);
		try {
			desktopColumnService.init();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		
	}
	
	/**
	 *初始化桌面栏目,在服务器启动的时候调用
	 * @throws Exception
	 */
	public void init()throws Exception{
		Integer amount=dao.getCounts();
		if(amount==0){
			addDesktopColumn();
		}
	}

	public DesktopColumn getByTemplateId(String templateId) {
		return dao.getByTemplateId(templateId);
	}
}
