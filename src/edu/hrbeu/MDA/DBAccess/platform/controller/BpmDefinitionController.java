package edu.hrbeu.MDA.DBAccess.platform.controller;



import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.hrbeu.MDA.DBAccess.core.BaseController;
import edu.hrbeu.MDA.DBAccess.platform.service.BpmDefinitionService;


/**
 * <pre>
 * 对象功能:流程定义 控制器类 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:csx 
 * 创建时间:2011-11-21 22:50:46
 * </pre>
 */
@Controller
@RequestMapping("/platform/bpm/bpmDefinition/")
public class BpmDefinitionController extends BaseController {
	
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	


	

}


