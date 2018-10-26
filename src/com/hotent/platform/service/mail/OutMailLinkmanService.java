package com.hotent.platform.service.mail;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.mail.OutMailLinkmanDao;
import com.hotent.platform.model.mail.OutMail;
import com.hotent.platform.model.mail.OutMailLinkman;
import com.hotent.platform.model.mail.OutMailUserSeting;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysUserService;

/**
 * 对象功能:最近联系人 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2012-04-13 11:11:56
 */
@Service
public class OutMailLinkmanService extends BaseService<OutMailLinkman>
{
	@Resource
	private OutMailLinkmanDao dao;
	@Resource
	private OutMailUserSetingService outMailUserSetingService;
	@Resource
	private SysUserService sysUserService;

	
	public OutMailLinkmanService()
	{
	}
	
	@Override
	protected IEntityDao<OutMailLinkman, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 添加最近联系人
	 * @param outMail
	 * @throws Exception
	 */
	public void addLinkMan(OutMail outMail,Long userId)throws Exception
	{
		long mailId=outMail.getMailId();
		Date sendTime=outMail.getMailDate();
		String receiveAddresses=outMail.getReceiverAddresses();
		String[] addresses=receiveAddresses.split(",");
		//遍历所有收件人
		for(String address:addresses){
			OutMailLinkman man=findLinkMan(address,userId);
			//该邮箱地址已经保存在联系人列表里
			if(man!=null){
				long id=man.getLinkId();
				dao.delById(id);
			}
			man=new OutMailLinkman();
			OutMailUserSeting outMailUserSeting=outMailUserSetingService.getMailByAddress(address);
			SysUser user=new SysUser();
			if(outMailUserSeting!=null){
				userId=outMailUserSeting.getUserId();
				user=sysUserService.getById(userId);
				if(user!=null){
					man.setLinkName(user.getFullname());
				}
			}else{
				user=sysUserService.getByMail(address);
				if(user!=null){
					man.setLinkName(user.getFullname());
				}
			}
			man.setMailId(mailId);
			man.setLinkAddress(address);
			man.setSendTime(sendTime);
			man.setUserId(ContextUtil.getCurrentUserId());
			man.setLinkId(UniqueIdUtil.genId());
			dao.add(man);
		}
	}
	
	/**
	 * 根据邮箱地址找到联系人
	 * @param address
	 * @return
	 * @throws Exception
	 */
	public OutMailLinkman findLinkMan(String address,long userId)throws Exception
	{
		return dao.findLinkMan(address,userId);
	}
	
	/**
	 * 找到当前用户下的最近联系人
	 * @param userId
	 * @return
	 */
	public List<OutMailLinkman> getAllByUserId(Long userId) {
		return dao.getAllByUserId(userId);
	}
}
