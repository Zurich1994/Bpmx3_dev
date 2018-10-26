package com.hotent.platform.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.XmlBeanUtil;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.service.bpm.ProcessRunService;

/**
 * 基于Servlet方式的BPM流程服务类
 * @author csx
 *
 */
public class BpmServiceServlet extends HttpServlet{
	
	private static Log logger=LogFactory.getLog(BpmServiceServlet.class);
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProcessRunService processRunService = (ProcessRunService)AppUtil.getBean(ProcessRunService.class);
		
		String url=req.getRequestURI();
		//invoke the service method
		String invokeMethod=null;
		int index=url.indexOf(req.getContextPath()+"/xmlservice/");
		if(index!=-1){
			invokeMethod=url.substring(index+(req.getContextPath()+"/xmlservice/").length());
		}
		//
		StringBuffer returnXml=new StringBuffer();
		if(invokeMethod!=null){
			 BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));  
	         String line=null;
			 StringBuffer xmlBuffer = new StringBuffer();              
	         while((line = reader.readLine()) != null) {  
	        	 xmlBuffer.append(line);  
	         }
	         logger.debug("post the xml is " + xmlBuffer.toString());
			
	         try {
				ProcessCmd processCmd=(ProcessCmd)XmlBeanUtil.unmarshall(xmlBuffer.toString(), ProcessCmd.class);
				if("start".equals(invokeMethod)){
					ProcessRun processRun=processRunService.startProcess(processCmd);
					returnXml.append(XmlBeanUtil.marshall(processRun, ProcessRun.class));
				}else{
					processRunService.nextProcess(processCmd);
					returnXml.append("<result><success>true</success></result>");
				}
			} catch (JAXBException e) {
				returnXml.append("<result><success>false</success></result>");
			} catch (Exception e) {
				e.printStackTrace();
				returnXml.append("<result><success>false</success></result>");
			}
		}
		
        resp.setContentType("text/xml;charset=UTF-8");
        resp.getWriter().println(returnXml);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
