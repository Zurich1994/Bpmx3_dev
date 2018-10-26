package com.hotent.core.engine.freemaker;

import java.util.List;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 为freemaker定义indexof的方法。<br>
 * 在freemaker使用如下：<br>
 * <#assign x = \"abcdsomething\">${indexOf(\"met\",x)}
 *
 */
public class IndexOfMethod implements TemplateMethodModel {
	
	public TemplateModel exec(List args) throws TemplateModelException {
		if (args.size() != 2) {
			throw new TemplateModelException("此访问参数必须为两个.");
		}
		String str1=(String) args.get(1);
		String str2=(String) args.get(0);
		
		return new SimpleNumber(str1.indexOf(str2));
	}
}