package com.hotent.core.engine;

import java.io.IOException;
import java.io.StringWriter;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * FreemarkEngine解析引擎。
 * @author csx
 */
public class FreemarkEngine {
	//配置来自app-resources.xml
	private Configuration configuration;

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * 把指定的模板生成对应的字符串。
	 * @param templateName  模板名，模板的基础路径为：WEB-INF/template目录。
	 * @param model  传入数据对象。
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public String mergeTemplateIntoString(String templateName,Object model) throws IOException, TemplateException{
		
		Template template=configuration.getTemplate(templateName);
		return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
	}

	
	
	
	/**
	 * 根据字符串模版解析出内容
	 * @param obj 需要解析的对象。
	 * @param templateSource	字符串模版。
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	public  String parseByStringTemplate(Object obj,String templateSource) throws TemplateException, IOException
	{
		Configuration cfg = new Configuration();
		StringTemplateLoader loader = new StringTemplateLoader();
		cfg.setTemplateLoader(loader);
		cfg.setClassicCompatible(true);  
		loader.putTemplate("freemaker", templateSource);
                Template template = cfg.getTemplate("freemaker");   
                StringWriter writer = new StringWriter();   
                template.process(obj, writer);   
		return writer.toString();
		
	}
	
}
