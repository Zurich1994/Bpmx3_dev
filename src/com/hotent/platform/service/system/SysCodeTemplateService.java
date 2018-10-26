package com.hotent.platform.service.system;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.form.BpmFormTemplate;
import com.hotent.platform.model.system.SysCodeTemplate;
import com.hotent.platform.dao.system.SysCodeTemplateDao;

/**
 *<pre>
 * 对象功能:自定义表代码模版 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2012-12-19 15:38:01
 *</pre>
 */
@Service
public class SysCodeTemplateService extends BaseService<SysCodeTemplate>
{
	@Resource
	private SysCodeTemplateDao dao;
	
	public SysCodeTemplateService()
	{
	}
	
	@Override
	protected IEntityDao<SysCodeTemplate, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 初始化加载代码生成器模板
	 * @throws Exception
	 */
	public void initAllTemplate() throws Exception {
		delByTemplateType((short)1);
		addSysCodeTemplates();
	}
	
	/**
	 * 根据模板类型删除模板 0 :自定义模板 1：系统模板
	 * @param templateType
	 */
	private void delByTemplateType(Short templateType) {
		dao.delByTemplateType(templateType);
	}
	
	
	
	/**
	 * 初始化添加form下的模版数据到数据库。
	 * @throws Exception
	 */
	private void addSysCodeTemplates()  throws Exception{
		String templatePath=getFormTemplatePath();
		String xml= FileUtil.readFile(templatePath +"codeTemplates.xml");
		Document document=Dom4jUtil.loadXml(xml);
		Element root=document.getRootElement();
		List<Element> list=root.elements();
		for(Element element:list){
			if("templates".equals(element.getName())){
				List<Element> tempList=element.elements();
				for(Element temp:tempList){
					SysCodeTemplate sysCodeTemplate=new SysCodeTemplate();
					String alias=temp.attributeValue("alias");
					String name=temp.attributeValue("name");
					String templateDesc=temp.attributeValue("templateDesc");
					String macroAlias=temp.attributeValue("macroAlias");
					String fileName=temp.attributeValue("fileName");
					String dir=temp.attributeValue("dir");
					String isSub=temp.attributeValue("isSub");
					String formEdit=temp.attributeValue("formEdit");
					String formDetail=temp.attributeValue("formDetail");
					String html= FileUtil.readFile(templatePath +alias+".ftl");
					sysCodeTemplate.setTemplateName(name);
					sysCodeTemplate.setTemplateAlias(alias);
					sysCodeTemplate.setTemplateType((short)1);
					sysCodeTemplate.setMemo(templateDesc);
					sysCodeTemplate.setFileDir(dir);
					sysCodeTemplate.setFileName(fileName);
					sysCodeTemplate.setHtml(html);
					sysCodeTemplate.setId(UniqueIdUtil.genId());
					if(StringUtil.isNotEmpty(isSub)){
						if(("true").equals(isSub)){
							sysCodeTemplate.setIsSub((short)1);
						}
					}
					if(StringUtil.isNotEmpty(formEdit)){
						if("true".equals(formEdit)){
							sysCodeTemplate.setFormEdit((short)1);
						}
					}
					if(StringUtil.isNotEmpty(formDetail)){
						if("true".equals(formDetail)){
							sysCodeTemplate.setFormDetail((short)1);
						}
					}
					dao.add(sysCodeTemplate);
				}
			}
		}
	}
	
	/**
	 * 返回模版物理的路径。
	 * @return
	 */
	private static  String getFormTemplatePath(){
		return FileUtil. getClassesPath() + "template" + File.separator +"code" + File.separator;
	}
	
	/**
	 * 根据模板别名获取模板
	 * @param alias
	 * @return
	 */
	public SysCodeTemplate getByTemplateAlias(String alias) {
		return dao.getByTemplateAlias(alias);
	}
	
	
	/**
	 * 根据路径获取模板
	 * @param alias
	 * @return
	 */
	public static  String getRelateTemplatePath(String alias){
		return "code" + File.separator  + alias +".ftl";
	}

}
