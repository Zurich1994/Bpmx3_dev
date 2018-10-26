package com.hotent.platform.service.mail;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.mail.OutMailAttachmentDao;
import com.hotent.platform.model.mail.OutMailAttachment;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.service.system.SysFileService;
import com.hotent.platform.service.util.ServiceUtil;

/**
 *<pre>
 * 对象功能:OUT_MAIL_ATTACHMENT Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:guojh
 * 创建时间:2014-03-04 13:49:25
 *</pre>
 */
@Service
public class OutMailAttachmentService extends BaseService<OutMailAttachment>
{
	@Resource
	private OutMailAttachmentDao dao;
	@Resource
	private SysFileService sysFileService;
	
	
	public OutMailAttachmentService()
	{
	}
	
	@Override
	protected IEntityDao<OutMailAttachment, Long> getEntityDao() 
	{
		return dao;
	}

	public List<OutMailAttachment> getByMailId(long mailId) {
		return dao.getByMailId(mailId);
	}

	/**
	 * 更新附件文件路径
	 * @param fileName
	 * @param mailId
	 * @param filePath
	 */
	public void updateFilePath(String fileName, Long mailId, String filePath) {
		dao.updateFilePath(fileName, mailId, filePath);
	}

	/**
	 * 根据OutMail实体的fileIds，构建OutMailAttachment列表
	 * @param fileIds
	 * @return
	 */
	public List<OutMailAttachment> getByOutMailFileIds(String fileIds) {
		List<OutMailAttachment> result = new ArrayList<OutMailAttachment>();
		if(fileIds==null) return result;
		fileIds=fileIds.replaceAll("quot;", "\"");
		JSONObject jsonObj=JSONObject.fromObject(fileIds);
		JSONArray jsonArray = JSONArray.fromObject(jsonObj.get("attachs"));
		for(Object obj:jsonArray){
			JSONObject json = (JSONObject)obj;
			long id=Long.parseLong(json.getString("id"));
			SysFile file=sysFileService.getById(id);
			String filePath = ServiceUtil.getBasePath()+File.separator+file.getFilePath();
			OutMailAttachment attachment = new OutMailAttachment();
			attachment.setFileId(id);
			attachment.setFileName(json.getString("name"));
			attachment.setFilePath(filePath);
			result.add(attachment);
		}
		return result;
	}
	
}
