package com.hotent.platform.controller.bpm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotent.BpmFormBang.model.bpmFormBang.BpmFormBang;
import com.hotent.BpmFormBang.service.bpmFormBang.BpmFormBangService;
import com.hotent.core.annotion.Action;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.eventgraphrelation.model.eventgraphrelation.Eventgraphrelation;
import com.hotent.eventgraphrelation.service.eventgraphrelation.EventgraphrelationService;

import com.hotent.platform.model.bpm.BpmNodeButton;
import com.hotent.platform.service.bpm.BpmNodeButtonService;
import com.hotent.platform.service.form.FormUtil;

/**
 * 对象功能:自定义工具条 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-07-25 18:26:13
 */
@Controller
@RequestMapping("/platform/bpm/bpmNodeButton/")
public class BpmNodeButtonFormController extends BaseFormController
{
	@Resource
	private BpmNodeButtonService bpmNodeButtonService;
	@Resource
	private EventgraphrelationService eventgraphrelationService;
	@Resource
	private BpmFormBangService bpmformbangservice;
	
	/**
	 * 添加或更新自定义工具条。
	 * @param request
	 * @param response
	 * @param bpmNodeButton 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新自定义工具条")
	public void save(HttpServletRequest request, HttpServletResponse response, BpmNodeButton bpmNodeButton,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("bpmNodeButton", bpmNodeButton, bindResult, request);
		//zl获取发生概率
		String probability = request.getParameter("btnprobability");
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail){
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		//zl判断是否已完成画图
		String actdeffilePath = FormUtil.getDesignButtonPath();
		File file = new File(actdeffilePath+"actdef.conf");
//		 if (!file.exists()) {
//			 writeResultMessage(response.getWriter(),"没有绘制流程图，请先绘制流程图！",ResultMessage.Fail);
//			 return;
//		}
		if(bpmNodeButton.getId()==0){
			boolean rtn=bpmNodeButtonService.isOperatorExist(bpmNodeButton);
			//zl加入处理自定义节点
			if(bpmNodeButton.getOperatortype()==18)
				rtn = false;
			
			if(rtn){
				resultMsg="该操作类型已定义,不能重复添加!";
				writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
				return;
			}
			bpmNodeButton.setId(UniqueIdUtil.genId());
			bpmNodeButton.setSn(bpmNodeButton.getId());
			bpmNodeButtonService.add(bpmNodeButton);
			//zl保存节点与图的关系
			saveRelation(probability,bpmNodeButton,file);
			resultMsg="添加自定义工具条成功";
		}else{
			//zl保存节点与图的关系
			saveRelation(probability,bpmNodeButton,file);
			boolean rtn=bpmNodeButtonService.isOperatorExistForUpd(bpmNodeButton);
			if(rtn){
				resultMsg="该操作类型已定义,不能重复添加!";
				writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
				return;
			}
			bpmNodeButtonService.update(bpmNodeButton);
			resultMsg="更新自定义工具条成功";
		}
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
	}

	//zl保存节点与图的关系
    public void  saveRelation(String probability,BpmNodeButton bpmNodeButton,File file) {
	
    String defID = bpmNodeButton.getDefId().toString();
	String defIDb = "";
	String nodeId = bpmNodeButton.getNodeid();
	//String eventID = request.getParameter("tableId");
	String eventID = bpmNodeButton.getId().toString();
    BufferedReader reader = null;
    try {
    	if(!file.exists())
    		file.createNewFile();
		reader = new BufferedReader(new FileReader(file));
	    defIDb = reader.readLine();
	    if(null == defIDb)
	    	defIDb = "";
	    reader.close();
	    file.delete();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	boolean isAdd = false;
	boolean isAdd1 = false;
	//BpmFormBang bpmformbang = bpmformbangservice.getByDefbId(defID);
	Eventgraphrelation eventgraphrelation =eventgraphrelationService.getByEventId(eventID);
	//if(null==bpmformbang){
	//	bpmformbang = new BpmFormBang();
	//.setId(UniqueIdUtil.genId());
	//}
	//	isAdd1 = true;
	//}
	if(null==eventgraphrelation){
		eventgraphrelation = new Eventgraphrelation();
		eventgraphrelation.setId(UniqueIdUtil.genId());
		}
	if(eventgraphrelation.getEventID() == null) {
		isAdd = true;
	}
	
	//bpmformbang.setDefId(defID);
	//bpmformbang.setDefbId(defIDb);
	//bpmformbang.setDefId(defID);
	//bpmformbang.setNodeId(nodeId);
	//bpmformbang.setBtn_probability(probability);
	
	eventgraphrelation.setDefID(defID);
    if(defIDb != "" &&defIDb != null)
	eventgraphrelation.setDefbID(defIDb);
	eventgraphrelation.setEventID(eventID);
	eventgraphrelation.setNodeID(nodeId);
	eventgraphrelation.setProbability(probability);
	//if(isAdd1)
    //	bpmformbangservice.add(bpmformbang);
	// else
	//	bpmformbangservice.update(bpmformbang);
	if(isAdd)
	    eventgraphrelationService.add(eventgraphrelation);
	 else
		eventgraphrelationService.update(eventgraphrelation);
	
}
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param ID
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected BpmNodeButton getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter BpmNodeButton getFormObject here....");
		BpmNodeButton bpmNodeButton=null;
		if(id!=0){
			bpmNodeButton=bpmNodeButtonService.getById(id);
		}else{
			bpmNodeButton= new BpmNodeButton();
		}
		return bpmNodeButton;
    }

}
