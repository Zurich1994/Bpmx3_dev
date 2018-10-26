package com.hotent.platform.service.form;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.form.BpmFormRuleDao;
import com.hotent.platform.model.form.BpmFormRule;
import com.hotent.platform.model.system.Script;

import freemarker.template.TemplateException;

/**
 * 对象功能:表单验证规则 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2012-01-11 10:53:15
 */
@Service
public class BpmFormRuleService extends BaseService<BpmFormRule>
{
	@Resource
	private BpmFormRuleDao dao;
	
	public BpmFormRuleService()
	{
	}
	
	@Override
	protected IEntityDao<BpmFormRule, Long> getEntityDao() 
	{
		return dao;
	}
	
	@Resource
	private FreemarkEngine freemarkEngine;
	
	/**
	 * 返回规则验证文件。
	 * @return
	 */
	private String getRuleJsPath(){
		return FileUtil.getRootPath() + "js" + File.separator +"hotent" 
				+ File.separator +"platform" + File.separator +"form" +  File.separator +"rule.js" ;
	}
	
	/**
	 * 产生JS
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	public void generateJS() throws IOException, TemplateException {
		List<BpmFormRule> list = dao.getAll();
		Map<String,Object> obj=new HashMap<String, Object>();
		obj.put("ruleList", list);
		String content=freemarkEngine.mergeTemplateIntoString("rulejs.ftl",obj);
		String fileName=getRuleJsPath();
		//写入js文件。
		FileUtil.writeFile(fileName, content);
	}

	/**
	 * 导出表单验证规则XML
	 * @param Long[] tableId
	 * @return
	 */
	public String exportXml(Long[] aryScriptId) throws FileNotFoundException, IOException{		
		String strXml="";
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("List");
		for(int i=0;i<aryScriptId.length;i++){
			BpmFormRule bpmFormRule=dao.getById(aryScriptId[i]);
			if(BeanUtils.isNotEmpty(bpmFormRule)){	
				// 生成主表
				exportTable(bpmFormRule, root, "Script");
			}
		}
		strXml=doc.asXML();
		return strXml;
		
	}
	
	/**
	 * 生成表代码
	 * @param bpmFormTable
	 * @param root
	 * @param nodeName
	 */
	private void exportTable(BpmFormRule bpmFormRule, Element root, String nodeName) 
	{		
		Element elScript = root.addElement(nodeName);
		
		if(StringUtil.isNotEmpty(bpmFormRule.getName())){
			elScript.addElement("name").addText(bpmFormRule.getName());
		}
		if(StringUtil.isNotEmpty(bpmFormRule.getRule())){
			Element elContent=elScript.addElement("rule");
			elContent.addCDATA(bpmFormRule.getRule());
		}
		if(BeanUtils.isNotEmpty(bpmFormRule.getTipInfo())){
			elScript.addElement("tipInfo").addText(bpmFormRule.getTipInfo());
		}
		if(BeanUtils.isNotEmpty(bpmFormRule.getMemo())){
			elScript.addElement("Memo").addText(bpmFormRule.getMemo());
		}
	}

	/**
	 * 导入表单验证规则XML
	 * @param fileStr
	 * @throws Exception
	 */
	public void importXml(InputStream inputStream) throws Exception
	{
		Document doc=Dom4jUtil.loadXml(inputStream);
        Element root = doc.getRootElement();
        List<Element> itemLists = root.elements();
        if(BeanUtils.isNotEmpty(itemLists)){
    		for(Element elm : itemLists) {
    			String itemName=elm.element("name").getText();   			
    				Long bpmFormRuleId = UniqueIdUtil.genId(); //产生id
    				String rule=elm.element("rule").getText();
    				String memoText=elm.element("Memo").getText();
    				String tipInfo=elm.element("tipInfo").getText();
    				BpmFormRule bpmFormRule=new BpmFormRule();
    				bpmFormRule.setId(bpmFormRuleId);
    				bpmFormRule.setName(itemName);
    				bpmFormRule.setRule(rule);
    				bpmFormRule.setMemo(memoText);
    				bpmFormRule.setTipInfo(tipInfo);
    				dao.add(bpmFormRule);		
    		}
        }
	}
}
