package com.hotent.platform.controller.mail;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.platform.model.mail.OutMailLinkman;
import com.hotent.platform.service.mail.OutMailLinkmanService;
/**
 * 对象功能:最近联系人 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2012-04-13 11:11:56
 */
@Controller
@RequestMapping("/platform/mail/outMailLinkman/")
public class OutMailLinkmanController extends BaseController
{
	@Resource
	private OutMailLinkmanService outMailLinkmanService;
	
	/**
	 * 最近联系人树形列表的json数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getOutMailLinkmanData")
	@ResponseBody
	public List<OutMailLinkman> getOutMailLinkmanData(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		List<OutMailLinkman> list=outMailLinkmanService.getAllByUserId(ContextUtil.getCurrentUserId());
		List<OutMailLinkman> listTemp=new ArrayList<OutMailLinkman>();
		for(OutMailLinkman beanTemp:list){
				OutMailLinkman omus=new OutMailLinkman();
		    	String currentName=ContextUtil.getCurrentUser().getFullname();
		    	String linkName=beanTemp.getLinkName();
		    	if(linkName==null){
		    		omus.setLinkAddress("陌生人("+beanTemp.getLinkAddress()+")");
		    	}else if(linkName.equals(currentName)){
		    		omus.setLinkAddress("自己("+beanTemp.getLinkAddress()+")");
		    	}else{
		    		omus.setLinkAddress(beanTemp.getLinkName()+"("+beanTemp.getLinkAddress()+")");
		    	}
			    listTemp.add(omus);
		}
		return listTemp;
	}
}
