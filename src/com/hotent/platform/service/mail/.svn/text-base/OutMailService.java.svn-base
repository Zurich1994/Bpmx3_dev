package com.hotent.platform.service.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.internet.MimeUtility;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.jms.MessageProducer;
import com.hotent.core.mail.MailUtil;
import com.hotent.core.mail.api.AttacheHandler;
import com.hotent.core.mail.model.Mail;
import com.hotent.core.mail.model.MailAttachment;
import com.hotent.core.mail.model.MailSeting;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.mail.OutMailDao;
import com.hotent.platform.dao.system.SysFileDao;
import com.hotent.platform.model.mail.OutMail;
import com.hotent.platform.model.mail.OutMailAttachment;
import com.hotent.platform.model.mail.OutMailUserSeting;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.jms.SysMailModel;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.util.ServiceUtil;
/**
 * 对象功能:外部邮件 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2012-04-09 14:16:18
 * 
 * 注意调用时(activation-1.1.jar和mail-1.4.4.jar)，
 * 其(geronimo-activation_1.1_spec-1.1.jar，
 * geronimo-javamail_1.4_spec-1.7.1.jar)在调用javax.mail.Part接口时有冲突，需要删除后组
 */
@Service
public class OutMailService extends BaseService<OutMail>
{
	static Long MAIL_NO_READ=0L;//未读
	static Long MAIL_IS_READ=1L;//已读
	static Integer MAIL_IS_RECEIVE = 1;// 收件箱
	static Integer MAIL_IS_SEND = 2;// 发件箱
	static Integer MAIL_IS_DRAFT = 3;// 草稿箱
	static Integer MAIL_IS_DELETE = 4;// 垃圾箱
	@Resource
	private OutMailDao dao;
	@Resource
	private OutMailUserSetingService outMailUserSetingService;
	@Resource
	private OutMailLinkmanService outMailLinkmanService;
	@Resource
	private SysFileDao sysFileDao;
	@Resource
	private SysUserService sysUserService;
	@Override
	protected IEntityDao<OutMail, Long> getEntityDao() 
	{return dao;}
	@Resource
	private OutMailAttachmentService outMailAttachmentService;
	@Resource
	private MessageProducer messageProducer;
	public OutMailService()	{}
	
	/**
	 * 保存邮件至垃圾箱
	 * @param request
	 * @param outMailUserSeting
	 * @return
	 */
	public void addDump(Long[] lAryId) {
		for(Long l:lAryId){
			OutMail outMail = dao.getById(l);	
			dao.updateTypes(outMail.getMailId(),MAIL_IS_DELETE);
		}
	}
	
	/**
	 * 浏览邮件
	 * @param outMail
	 * @param outMailUserSeting
	 * @throws NoSuchProviderException
	 * @throws MessagingException
	 */
	public void emailRead(OutMail outMail)throws NoSuchProviderException, MessagingException {
		if(OutMail.Mail_IsNotRead.shortValue() == outMail.getIsRead().shortValue()
				|| OutMail.Mail_InBox.shortValue() != outMail.getTypes().shortValue()) return;
        outMail.setIsRead(OutMail.Mail_IsRead);
        dao.update(outMail);
	}	
	
	/**
	 * 同步远程邮件，进行选择性下载
	 * @param outMailUserSeting
	 * @throws Exception
	 */
	public void emailSync(OutMailUserSeting outMailUserSeting) throws Exception {
		final String mailAddress = outMailUserSeting.getMailAddress();
		MailSeting seting = OutMailUserSetingService.getByOutMailUserSeting(outMailUserSeting);
		MailUtil mailUtil = new MailUtil(seting);
		Long setId = outMailUserSeting.getId();
		String latestEmailId = "";
		List<String> UIDList = dao.getUIDBySetId(setId);
		if(BeanUtils.isNotEmpty(UIDList)){
			latestEmailId = UIDList.get(0);
		}else if(UIDList == null){
			UIDList = new ArrayList<String>();
		}
		final List<String> finalList = UIDList;
		List<Mail> list = mailUtil.receive(new AttacheHandler() {
			
			public Boolean isDownlad(String UID) {
				return !finalList.contains(UID);
			}
			
			public void handle(Part part, Mail mail) {
				try {
					saveAttach(part, mail, mailAddress);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, latestEmailId);
		for(Mail mail:list){
			OutMail bean = getOutMail(mail, setId);
			// 主键
			Long mailId = UniqueIdUtil.genId();
			bean.setMailId(mailId);
			// 邮件标识
			bean.setEmailId(mail.getUID());
			dao.add(bean);
			logger.info("已下载邮件"+bean.getTitle());
			List<MailAttachment> attachments = mail.getMailAttachments();
			if(BeanUtils.isEmpty(attachments)) continue ;
			OutMailAttachment outMailAttachment ;
			for(MailAttachment attachment:attachments){
				String fileName = attachment.getFileName();
				String filePath = attachment.getFilePath();
				String ext = FileUtil.getFileExt(fileName);
				Long fileId = StringUtil.isNotEmpty(filePath)?new Long(new File(filePath).getName().replace("."+ext, "")):UniqueIdUtil.genId();
				outMailAttachment = new OutMailAttachment();
				outMailAttachment.setFileId(fileId);
				outMailAttachment.setFileName(attachment.getFileName());
				outMailAttachment.setFilePath(filePath);
				outMailAttachment.setMailId(mailId);
				outMailAttachmentService.add(outMailAttachment);
			}
		}
	}
	
	/**
	 * 获得OutMail实体
	 * @param message
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private OutMail getOutMail(Mail mail, Long setId)throws Exception{
		OutMail bean =new OutMail();
		Date sentDate = null;
		if (mail.getSendDate() != null) {
			sentDate = mail.getSendDate();
		} else {
			sentDate = new Date();
		}
		//邮件发送时间
		bean.setMailDate(sentDate);
		bean.setSetId(setId);
		bean.setTitle(mail.getSubject());
		bean.setContent(mail.getContent());
		//发件人
		bean.setSenderAddresses(mail.getSenderAddress());
		bean.setSenderName(mail.getSenderName());
		//接受者
		bean.setReceiverAddresses(mail.getReceiverAddresses());
		bean.setReceiverNames(mail.getReceiverName());
		//暗送者
		bean.setBcCAddresses(mail.getBcCAddresses());
		bean.setBcCAnames(mail.getBccName());
		//抄送者
		bean.setCcAddresses(mail.getCopyToAddresses());
		bean.setCcNames(mail.getCopyToName());
		bean.setTypes(MAIL_IS_RECEIVE);
		bean.setIsRead(OutMail.Mail_IsNotRead);
		bean.setUserId(ContextUtil.getCurrentUserId());
		return bean;
	}
	
	/**
     * 将邮件中的附件保存在本地指定目录下
     * @param filename
     * @param in
     * @return
     */
    private void saveAttach(Part message, Mail mail, String userAccount)throws Exception{
    	String filename=MimeUtility.decodeText(message.getFileName());
    	Calendar cal=Calendar.getInstance();//使用日历类
    	int year=cal.get(Calendar.YEAR);//得到年
    	int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
    	SysUser curUser=ContextUtil.getCurrentUser();
    	String curAccount = curUser.getAccount();
    	String relateFilePath = "/emailAttachs/"+curAccount+"/"
				+userAccount+"/"+year+"/"+month+"/"
				+UniqueIdUtil.genId()+"."+FileUtil.getFileExt(filename);
    	String filePath=AppUtil.getRealPath(relateFilePath);
    	FileUtil.createFolderFile(filePath);
		FileUtil.writeFile(filePath, message.getInputStream());
		mail.getMailAttachments().add(new MailAttachment(filename, relateFilePath));
    }
	
    /**
     * 邮箱树形列表的json数据
     * @param request
     * @param list
     * @param listTemp
     * @param listEnd
     * @throws Exception 
     */
	public List<OutMailUserSeting> getMailTreeData(Long userId) throws Exception {
		List<OutMailUserSeting> list=outMailUserSetingService.getMailByUserId(userId);
		List<OutMailUserSeting> temp=new ArrayList<OutMailUserSeting>();
		OutMailUserSeting omus=null;
		for(OutMailUserSeting beanTemp:list){
			beanTemp.setParentId(0L);
			long id=beanTemp.getId();
			temp.add(beanTemp);
		    for(int i=0;i<4;i++){
		    	omus=new OutMailUserSeting();
		    	if(i==0){ 
		    		omus.setUserName("收件箱("+getCount(id,MAIL_IS_RECEIVE)+")");
			    	omus.setTypes(MAIL_IS_RECEIVE);
		    	}else if(i==1){
		    		omus.setUserName("发件箱("+getCount(id,MAIL_IS_SEND)+")");
			    	omus.setTypes(MAIL_IS_SEND);
		    	}else if(i==2){
		    		omus.setUserName("草稿箱("+getCount(id,MAIL_IS_DRAFT)+")");
			    	omus.setTypes(MAIL_IS_DRAFT);
		    	}else {
		    		omus.setUserName("垃圾箱("+getCount(id,MAIL_IS_DELETE)+")");
			    	omus.setTypes(MAIL_IS_DELETE);
			    }
				omus.setId(UniqueIdUtil.genId());
				omus.setParentId(beanTemp.getId());
			    temp.add(omus);
		    }
		}
		return temp;
	}
	
	/**
	 * 获取邮箱的分类邮件，如收件箱，发件箱，草稿箱
	 * @param queryFilter
	 * @return
	 */
	public List<OutMail> getFolderList(QueryFilter queryFilter){
		return dao.getFolderList(queryFilter);
	}
	
	/**
	 * 获取邮箱的分类邮件数
	 * @param address
	 * @param type
	 * @param userId
	 * @return
	 */
	private int getCount(long id,int type){
		return dao.getFolderCount(id, type);
	}
	
	/**
	 * 得到用户默认邮箱中的邮件列表
	 * @param queryFilter
	 * @return
	 */
	public List<OutMail> getDefaultMailList(QueryFilter queryFilter) {
		return dao.getDefaultMailList(queryFilter);
	}
	
	/**
	 * 发送邮件,保存邮件信息至本地,添加/更新最近联系人
	 * @param outMail
	 * @param outMailFiles
	 * @param fileIds
	 * @throws Exception
	 */
	public void sendMail(OutMail outMail,long userId,long mailId,int isReply,String context,String basePath) throws Exception {
		String content=outMail.getContent();
		if(mailId==0||isReply==1){
			outMail.setMailId(UniqueIdUtil.genId());
			add(outMail);
		}else{
			dao.updateTypes(mailId, 2);
		}
		outMail.setContent(content);
		Mail mail=getMail(outMail, basePath);
		sendToMQ(mail, outMail.getSetId());
		//添加 所有收件人为最近联系人
		outMailLinkmanService.addLinkMan(outMail,userId);
	}
	
	/**
	 * 发送邮件到mq队列
	 * @param mail
	 * @param outMailUserSeting 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void sendToMQ(Mail mail, Long outMailUserSetingId){
		SysMailModel model = new SysMailModel();
		model.setMail(mail);
		model.setOutMailUserSetingId(outMailUserSetingId);
		//发送邮件到mq队列
		messageProducer.send(model);
	}
	
	/**
	 * 得到用于回复页面显示信息
	 * @param mailId
	 * @return
	 */
	public OutMail getOutMailReply(Long mailId) {
		OutMail outMail=getById(mailId);
		outMail.setIsReply(OutMail.Mail_IsReplay);
		outMail.setTitle("回复:" + outMail.getTitle());
		return outMail;
	}
	
	/**
	 * 获取Mail 实例
	 * @param outMail
	 * @param outMailUserSeting
	 * @return
	 * @throws Exception 
	 */
	private Mail getMail(OutMail outMail, String basePath) throws Exception{
		Mail mail=new Mail();
		if(BeanUtils.isNotEmpty(outMail)){
			mail.setSenderName(outMail.getSenderName());
			mail.setSenderAddress(outMail.getSenderAddresses());
			mail.setReceiverAddresses(outMail.getReceiverAddresses());
			if(StringUtil.isNotEmpty(outMail.getBcCAddresses())){
				mail.setBcCAddresses(outMail.getBcCAddresses());
			}
			if(StringUtil.isNotEmpty(outMail.getCcAddresses())){
				mail.setCopyToAddresses(outMail.getCcAddresses());
			}
			String fileIds=outMail.getFileIds().replaceAll("quot;", "\"");
			
			JSONObject jsonObj=JSONObject.fromObject(fileIds);
			JSONArray jsonArray = JSONArray.fromObject(jsonObj.get("attachs"));
			if(jsonArray.size()>0){
				SysFile sysFile = null ;
				List<MailAttachment> attachments = mail.getMailAttachments();
				for(Object obj:jsonArray){
					JSONObject json = (JSONObject)obj;
					long id=Long.parseLong(json.getString("id"));
					sysFile=sysFileDao.getById(id);
					String filePath = sysFile.getFilePath();
					String fileName = sysFile.getFileName()+"."+sysFile.getExt();
					if(StringUtil.isEmpty(filePath)){
						attachments.add(new MailAttachment(fileName,sysFile.getFileBlob()));
						continue;
					}
					if(StringUtil.isEmpty(basePath)){
						//路径从配置文件中获取
						basePath=ServiceUtil.getBasePath();
					}
					filePath = basePath+File.separator+filePath;
					attachments.add(new MailAttachment(fileName,filePath));
				}
			}
			mail.setContent(outMail.getContent());
			mail.setSubject(outMail.getTitle());
		}
		return mail;
	}
	
	/**
	 * 发送系统错误报告至公司邮箱
	 * @param content
	 * @param recieveAdress
	 * @param mail
	 * @throws Exception
	 */
	public void sendError(String errorMsg, String recieveAdress, OutMailUserSeting outMailUserSeting)throws Exception {
		MailSeting seting = OutMailUserSetingService.getByOutMailUserSeting(outMailUserSeting);
		MailUtil mailUtil = new MailUtil(seting);
		Mail mail = new Mail();
		mail.setContent(errorMsg);
		mail.setSubject("BPMX3错误报告！");
		mail.setReceiverAddresses(recieveAdress);
		mailUtil.send(mail);
		
	}

	public void delBySetId(Long setId) {
		dao.delBySetId(setId);
	}

	/**
	 * 返回附件下载地址
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public String mailAttachementFilePath(OutMailAttachment entity) throws Exception {
		OutMail outMail = getById(entity.getMailId());
		Long setId = outMail.getSetId(); 
		final String emailId = outMail.getEmailId();
		OutMailUserSeting outMailUserSeting=outMailUserSetingService.getById(setId);
		final String mailAddress = outMailUserSeting.getMailAddress();
		MailSeting seting = OutMailUserSetingService.getByOutMailUserSeting(outMailUserSeting);
		seting.setIsHandleAttach(true);
		MailUtil mailUtil = new MailUtil(seting);
		List<Mail> list = mailUtil.receive(new AttacheHandler() {
			
			@Override
			public Boolean isDownlad(String UID) {
				return UID.equals(emailId);
			}
			
			@Override
			public void handle(Part part, Mail mail) {
				try {
					saveAttach(part, mail, mailAddress);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		if(BeanUtils.isEmpty(list)) throw new Exception("找不到该邮件，可能邮件已被删除！");
		Long mailId = outMail.getMailId();
		String attachFileName = entity.getFileName();
		String resultPath = "";
		Mail mail = list.get(0);
		List<MailAttachment> attachments = mail.getMailAttachments();
		for(MailAttachment attachment:attachments){
			String fileName = attachment.getFileName();
			String filePath = attachment.getFilePath();
			if(fileName.equals(attachFileName)) resultPath = filePath;
			outMailAttachmentService.updateFilePath(fileName, mailId, filePath);
		}
		return resultPath;
	}
}
