package com.hotent.platform.service.jms.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hotent.core.mail.MailUtil;
import com.hotent.core.mail.model.MailSeting;
import com.hotent.platform.model.mail.OutMailUserSeting;
import com.hotent.platform.service.jms.IJmsHandler;
import com.hotent.platform.service.jms.SysMailModel;
import com.hotent.platform.service.mail.OutMailUserSetingService;

public class SysMailHandler implements IJmsHandler {
	
	@Resource
	private OutMailUserSetingService outMailUserSetingService;
	private final Log logger = LogFactory.getLog(SysMailHandler.class);

	public void handMessage(Object model) {
		try {
			if(!(model instanceof SysMailModel)) return;
			SysMailModel sysMailModel = (SysMailModel)model;
			Long outMailUserSetingId = sysMailModel.getOutMailUserSetingId();
			if(outMailUserSetingId == null || outMailUserSetingId < 0) return;
			OutMailUserSeting outMailUserSeting = outMailUserSetingService.getById(outMailUserSetingId);
			MailSeting mailSetting = OutMailUserSetingService.getByOutMailUserSeting(outMailUserSeting);
			MailUtil util = new MailUtil(mailSetting);
			util.send(sysMailModel.getMail());
			logger.debug("out mail process success.");
		} catch (Exception e) {
			logger.error("out mail process error " + ExceptionUtils.getRootCauseMessage(e));
			e.printStackTrace();
		}
	}

}
