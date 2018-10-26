package com.hotent.platform.service.system;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.ldap.dao.LdapUserDao;
import com.hotent.core.ldap.model.LdapUser;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.system.SysUser;

/**
 * 用户同步功能，只是同步ad的用户。
 * @author Administrator
 *
 */
@Service
public class UserSyncService {
	
	@Resource
	private LdapUserDao ldapUserDao;
	@Resource
	private SysUserDao sysUserDao;
	
	
	/**
	 * 同步AD用户。
	 * <pre>
	 * 只是同步用户。
	 * 电话号码对应规则。
	 * mobile 对应 ad 的常规选项卡的 电话号码
	 * phone 对应 ad 的电话选项卡的家庭电话
	 * </pre>
	 */
	public void syncUsers(){
		
		Map<String,LdapUser> adUserMap=getAdUserMap();
		Map<String,SysUser> sysUserMap=getUserMap();
		
		
		Set<Entry<String,LdapUser>> adSet= adUserMap.entrySet();
		//增加和更新处理。
		for(Iterator<Entry<String,LdapUser>> it=adSet.iterator();it.hasNext();){
			Entry<String,LdapUser> ent=it.next();
			String adCount=ent.getKey();
			
			if(sysUserMap.containsKey(adCount) ){
				SysUser sysUser=sysUserMap.get(adCount);
				//只有通过ad的才修改
				if(!sysUser.getFromType().equals(SysUser.FROMTYPE_DB)){
					sysUser=  getUserByAdUser(ent.getValue(),sysUser);
					sysUserDao.update(sysUser);
				}
			}
			else{
				SysUser sysUser=  getUserByAdUser(ent.getValue());
				sysUserDao.add(sysUser);
			}
		}
		
		//删除处理
		Set<Entry<String,SysUser>> sysUserSet= sysUserMap.entrySet();
		for(Iterator<Entry<String,SysUser>> it=sysUserSet.iterator();it.hasNext();){
			Entry<String,SysUser> ent=it.next();
			//活动目录不包含则删除,只是做删除标记。
			if(!adUserMap.containsKey(ent.getKey())){
				SysUser sysUser=ent.getValue();
				//只有通过id的才设置删除。
				if(!sysUser.getFromType().equals(SysUser.FROMTYPE_DB)){
					sysUser.setStatus(SysUser.STATUS_Del);
					sysUserDao.update(sysUser);
				}
				
			}
		}
	}
	
	/**
	 * 根据ad用户获取sysuser对象。
	 * @param user
	 * @param isAdd
	 * @return
	 */
	private SysUser getUserByAdUser(LdapUser user){
		SysUser sysUser=new SysUser();
		
		sysUser.setUserId(UniqueIdUtil.genId());
		sysUser.setSex("");
		sysUser.setCreatetime(new Date());
		
		sysUser.setFullname(user.getDisplayName()==null ?user.getsAMAccountName():user.getDisplayName());
		sysUser.setAccount(user.getsAMAccountName());
		sysUser.setIsExpired(SysUser.UN_EXPIRED);
		sysUser.setIsLock(SysUser.UN_LOCKED);
		sysUser.setPassword("1");
		sysUser.setStatus(SysUser.STATUS_OK);
		sysUser.setEmail(user.getMail());
		sysUser.setMobile(user.getTelephoneNumber());
		sysUser.setPhone(user.getHomePhone());
		
		sysUser.setFromType(SysUser.FROMTYPE_AD);
		
		return sysUser;
	}
	
	private SysUser getUserByAdUser(LdapUser user,SysUser sysUser){
		sysUser.setFullname(user.getDisplayName()==null ?user.getsAMAccountName():user.getDisplayName());
		sysUser.setAccount(user.getsAMAccountName());
		sysUser.setIsExpired(SysUser.UN_EXPIRED);
		sysUser.setIsLock(SysUser.UN_LOCKED);
		sysUser.setPassword("1");
		sysUser.setStatus(SysUser.STATUS_OK);
		sysUser.setEmail(user.getMail());
		sysUser.setMobile(user.getTelephoneNumber());
		sysUser.setPhone(user.getHomePhone());
		sysUser.setFromType(SysUser.FROMTYPE_AD);
		return sysUser;
	}
	
	
	/**
	 * 获取本地账户
	 * @return
	 */
	private Map<String ,SysUser> getUserMap(){
		List<SysUser> list= sysUserDao.getAll();
		Map<String,SysUser> map=new HashMap<String, SysUser>();
		for(SysUser user:list){
			map.put(user.getAccount(), user);
		}
		return map;
	}
	
	
	/**
	 * 根据用户列表获取map。
	 * @param list
	 * @return
	 */
	private Map<String,LdapUser> getAdUserMap(){
		List<LdapUser> list= ldapUserDao.get();
		Map<String,LdapUser> map=new HashMap<String, LdapUser>();
		for(LdapUser user:list){
			map.put(user.getsAMAccountName(), user);
		}
		return map;
	}

}
