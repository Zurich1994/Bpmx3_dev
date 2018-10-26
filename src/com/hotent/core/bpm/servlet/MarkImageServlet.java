package com.hotent.core.bpm.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.hotent.Markovchain.service.Markovchain.MarkovchainService;
import com.hotent.core.bpm.graph.activiti.ProcessDiagramGenerator;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.dao.bpm.BpmProStatusDao;

import com.hotent.platform.model.bpm.BpmProStatus;
import com.hotent.platform.model.bpm.ProcessRun;

import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.IFlowStatus;
import com.hotent.platform.service.bpm.ProcessRunService;

/**
 * 产生流程图的servlet。<br>
 * 
 * <pre>
 * 要求传入名称为deployId参数。
 * 在web.xml中配置：
 * &lt;servlet>
 *       &lt;servlet-name>bpmImageServlet&lt;/servlet-name>
 *       &lt;servlet-class>com.hotent.core.bpm.servlet.BpmImageServlet&lt;/servlet-class>
 *    &lt;/servlet>
 * &lt;servlet-mapping>
 *   	&lt;servlet-name>bpmImageServlet&lt;/servlet-name>
 *   	&lt;url-pattern>/bpmImage&lt;/url-pattern>
 *   &lt;/servlet-mapping>
 *   
 *   页面使用方法如下：
 *   &lt;img src="${ctx}/bpmImage?deployId=**" />
 *   &lt;img src="${ctx}/bpmImage?taskId=**" />
 *   &lt;img src="${ctx}/bpmImage?processInstanceId=**" />
 *   &lt;img src="${ctx}/bpmImage?definitionId=**" />
 * </pre>
 * 
 * @author csx.
 * 
 */
@SuppressWarnings("serial")
public class MarkImageServlet extends HttpServlet {
	private MarkovchainService markovchainService = (MarkovchainService) AppUtil.getBean("markovchainService");
	private IFlowStatus iFlowStatus = (IFlowStatus) AppUtil.getBean("iFlowStatus");

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		long markid = RequestUtil.getLong(request, "id");
		// 生成图片
		InputStream is = null;
		//根据流程deployId产生图片
		String markXml = markovchainService.getmarkXmlById(markid);
		is = ProcessDiagramGenerator.generatePngDiagram(markXml);
		if (is != null) {
			response.setContentType("image/png");
			OutputStream out = response.getOutputStream();

			try {
				byte[] bs = new byte[1024];
				int n = 0;
				while ((n = is.read(bs)) != -1) {
					out.write(bs, 0, n);
				}
				out.flush();
			} catch (Exception ex) {
			} finally {
				is.close();
				out.close();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
