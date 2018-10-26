//package com.hotent.core.report;
//
//import java.io.File;
//
//import com.fr.base.FRContext;    
//import com.fr.base.ModuleContext;  
//
//import com.fr.io.TemplateWorkBookIO;
//import com.fr.main.TemplateWorkBook;
//import com.fr.report.EngineModule;  
//
//import com.fr.base.Parameter;       
//import com.fr.base.dav.LocalEnv;  
//import com.fr.web.Reportlet;    
//import com.fr.web.ReportletRequest;
//import com.fr.web.WebletException;
//import com.hotent.core.util.FileUtil;
//
//
///**
// * 对象功能:fineReport报表外部参数处理
// * 开发公司:广州宏天软件有限公司
// * 开发人员:cjj
// * 创建时间:2012-04-18 11:56
// */
//public class ReportParamSearch extends Reportlet {
//
//	@Override
//	public TemplateWorkBook createReport(ReportletRequest reportletRequest)
//			throws WebletException {
//        String envPath = FileUtil.getRootPath()+"WEB-INF\\".replace("\\", File.separator);
//        FRContext.setCurrentEnv(new LocalEnv(envPath));
//        ModuleContext.startModule(EngineModule.class.getName());
//        
//       // 获取外部传来的参数    
//       TemplateWorkBook wbTpl = null;  
//       String fileName = reportletRequest.getParameter("fileName").toString();
//       try {
//           wbTpl = TemplateWorkBookIO.readTemplateWorkBook(FRContext.getCurrentEnv(),"\\"+fileName.replace("\\", File.separator));   
//           // 提取报表参数组，由于原模板只有country一个参数，因此直接取index为0的参数，并将外部传入的值赋给该参数    
//           Parameter[] ps = wbTpl.getParameters();
//       } catch (Exception e) {
//           e.printStackTrace();    
//           return null;    
//       }
//       return wbTpl;
//	}
//	
//}
