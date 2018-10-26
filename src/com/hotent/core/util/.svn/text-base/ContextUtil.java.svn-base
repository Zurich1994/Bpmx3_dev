package com.hotent.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hotent.core.bpm.cache.ActivitiDefCache;
import com.hotent.core.cache.ICache;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.web.tag.AnchorTag;
//import com.hotent.core.web.util.CookieUitl;
//import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.util.CookieUitl;
import com.hotent.core.web.util.RequestContext;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.bpm.thread.TaskThreadService;
import com.hotent.platform.service.bpm.thread.TaskUserAssignService;
import com.hotent.platform.service.system.PositionService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysOrgTacticService;
import com.hotent.platform.service.system.SysPaurService;
import com.hotent.platform.service.system.SysUserService;

/**
 * 取得当前用户登录时的上下文环境，一般用于获取当前登录的用户
 * @author csx
 *
 */
public class ContextUtil {
//	private static Logger logger=LoggerFactory.getLogger(ContextUtil.class);
	private static ThreadLocal<String> curUserAccount=new ThreadLocal<String>();
	private static ThreadLocal<SysUser> curUser=new ThreadLocal<SysUser>();
	private static ThreadLocal<Locale> curLocale = new ThreadLocal<Locale>();
	//当前组织。
//	private static ThreadLocal<SysOrg> curOrg=new ThreadLocal<SysOrg>();
	//当前岗位
//	private static ThreadLocal<Position> curPos=new ThreadLocal<Position>();
		
	public static final String CurrentOrg="CurrentOrg_";
	public static final String CurrentCompany ="CurrentCompany_";
	
	public static final String CurrentPos="CurrentPos_";
	
	
	//public static final String SwitcherTag="switcherTag_";
	
	
	
	//单位在行政组织分类中的类别
//	public static final Long CompanyOrgType = 2L;	
	//连接超时时间
	private static Integer _connTimeout = 0;
	//读取超时时间
	private static Integer _readTimeout = 0;
	
	/**
	 * 获取连接超时时间
	 * @return
	 */
	public static Integer getConnectTimeout() {
		if(_connTimeout.intValue()==0){
			_connTimeout = Integer.parseInt(AppConfigUtil.get("webservice.connTimeout"));
			if(_connTimeout.intValue()==0)
				_connTimeout = 3000;
		}
		return _connTimeout;
	}
	/**
	 * 获取读取超时时间
	 * @return
	 */
	public static Integer getReadTimeout() {
		if(_readTimeout.intValue()==0){
			_readTimeout = Integer.parseInt(AppConfigUtil.get("webservice.readTimeout"));
			if(_readTimeout.intValue()==0)
				_readTimeout = 3000;
		}
		return _readTimeout;
	}
	
	/**
	 * 取得当前登录的用户。
	 * <pre>
	 * 1.首先尝试从线程变量中获取获取当前用户，线程变量的是通过setCurrentUserAccount方法进行设置。
	 * 
	 * 2.如果没有获取到则从登录用户中进行获取。
	 * <pre>
	 * @return
	 */
	public static SysUser getCurrentUser(){
		//通过setCurrentUserAccount设置的用户。
		if(curUser.get()!=null){
			SysUser user=curUser.get();
			return user;
		}
		SysUser sysUser=null;
		SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            Authentication auth = securityContext.getAuthentication();
            if (auth != null) {
                Object principal = auth.getPrincipal();
                if (principal instanceof SysUser) {
                	sysUser=(SysUser)principal;
                	setLog4jMDC(sysUser);
                }
            } 
        }
        return sysUser;
	}
	
	/**
	 * 获取当前用户岗位对象。
	 * <pre>
	 * 1.如果设置了主岗位，则取主岗位。
	 * 2.如果没有设置则或其中的一个。
	 * 3.没有岗位则返回为空。
	 * </pre>
	 * @return
	 */
//	public static Position getCurrentPos(){
//		SysUser curUser=getCurrentUser();
//		PositionService positionService=(PositionService)AppUtil.getBean("positionService");
//		
//		//List<Position> positions=positionService.getByUserId(curUser.getUserId());
//		UserPositionService userPositionService=(UserPositionService)AppUtil.getBean("userPositionService");
//		List<UserPosition> userPositions=userPositionService.getByUserId(curUser.getUserId());
//		if(BeanUtils.isNotEmpty(userPositions)){
//			Position posMain=null;
//			for(UserPosition pos:userPositions){
//				if(pos.getIsPrimary().shortValue()==1){
//					posMain=positionService.getById(pos.getPosId());
//					return posMain;
//				}
//			}
//			if(posMain==null){
//				return positionService.getById(userPositions.get(0).getPosId()); 
//			}
//		}
//		return null;
//	}
	
	/**
	 * 获取当前语言环境
	 * @return
	 */
	public static Locale getLocale(){
		if(curLocale.get()!=null){
			return curLocale.get();
		}
		setLocale(new Locale("zh","CN"));
		return curLocale.get();
	}
	
	/**
	 * 设置当前语言环境
	 * @param locale
	 */
	public static void setLocale(Locale locale){
		curLocale.set(locale);
	}
	
	/**
	 * 取得当前用户的ID
	 * @return
	 */
	public static Long getCurrentUserId(){
		SysUser curUser=getCurrentUser();
		if(curUser!=null) return curUser.getUserId();
		return 0L;
	}
	/**
	 * 设置当前用户账号
	 * @param curUserAccount
	 */
	public static void setCurrentUserAccount(String account){
	//	SysUserService sysUserService=(SysUserService)AppUtil.getBean("sysUserService");
		SysUserService sysUserService=(SysUserService)AppUtil.getBean(SysUserService.class);
		SysUser sysUser=sysUserService.getByAccount(account);
		curUser.set(sysUser);
		setLog4jMDC(sysUser);
	}
	/**
	 * 设置当前用户
	 * @param sysUser
	 */
	public static void setCurrentUser(SysUser sysUser){
		curUser.set(sysUser);
		setLog4jMDC(sysUser);
	}
	
	/**
	 * 设置Log4j的MDC
	 * @param user
	 */
	private static void setLog4jMDC(SysUser user){
		if(user!=null){
			MDC.put("current_user_id", user.getUserId());
			MDC.put("current_user_name", user.getFullname());
			MDC.put("current_user_account",user.getAccount());
		}
	}
	/**
	 * 设置当前岗位。
	 * @param orgId
	 */
	public static void setCurrentPos(Long posId){
		SysUser user=ContextUtil.getCurrentUser();
		PositionService positionService=(PositionService)AppUtil.getBean(PositionService.class);
		SysOrgService orgService=(SysOrgService)AppUtil.getBean(SysOrgService.class);
		Position position=positionService.getById(posId);
	//	HttpServletRequest request= RequestUtil.getHttpServletRequest();
	//	HttpServletResponse response= RequestUtil.getHttpServletResponse();
	//	HttpSession session= request.getSession();
	//	saveSessionCookie(position,request,response,session);
//	}
	
	/**
	 * 从当前session中设置当前人的岗位数据。
	 * <pre>
	 * 首先判断session中是否有 岗位 数据。
	 * 1.如果组织获取为空。
	 * 	    1.1从coolie中获取当前 岗位 Id。
	 *      
	 *          判断此id是否为空。
	 *          1.1.1为空的情况。
	 *      	根据当前用户id从数据库获取默认的组织对象.
	 *      
	 *         2.不为空则根据岗位ID获取
	 *      	根据组织ID获取岗位对象
	 * 2.获取不为空。
	 * 
	 * 判定岗位对象是否为空
	 *  不为空则加入到session和cookie中。
	 *  并设置当前session线程变量和缓存。
	 * </pre>
	 */
	 /*
	public static void getCurrentPosFromSession(){
		HttpServletRequest request= RequestUtil.getHttpServletRequest();
		HttpServletResponse response= RequestUtil.getHttpServletResponse();
		Position position = null;
		HttpSession session =  request.getSession();
		//从session中获取。
		if(request!=null){
			position = (Position)session.getAttribute(ContextUtil.CurrentPos);			
		}
		Long userId=getCurrentUserId();
	
		
		if(position==null){
			PositionService positionService=(PositionService)AppUtil.getBean("positionService");
			//从cookie中获取。
			String currentPosId= CookieUitl.getValueByName(ContextUtil.CurrentPos, request);
			if(StringUtil.isEmpty(currentPosId)) {
				position = positionService.getDefaultPosByUserId(userId);
			}
			else{
				String switchTag=(String)session.getAttribute(ContextUtil.SwitcherTag);
				if(switchTag==null){
					//从数据库中获取。
					Long posId=Long.parseLong(currentPosId);
					position= positionService.getById(posId);
				}
				else{
					session.removeAttribute(ContextUtil.SwitcherTag);
				}
			}
			if(position!=null){
				//设置cookie和sesion。
				saveSessionCookie(position,request,response,session);
			}
		}
		if(position!=null){
			ContextUtil.setCurrentPos(position);
			ContextUtil.setCurrentOrg(getOrgByPos(position));
		}
	}
	*/
	/**
	 * 根据岗位得到组织
	 * @author hjx
	 * @version 创建时间：2014-1-21  上午11:42:24
	 * @param position
	 * @return
	 */
	 /*
	private  static SysOrg getOrgByPos(Position position){
		if(position == null) return null;
		
		SysOrgService sysOrgService=(SysOrgService)AppUtil.getBean("sysOrgService");
		SysOrg sysOrg = sysOrgService.getById(position.getOrgId());
		return sysOrg;
	}
	*/
	
		//SysOrg sysOrg = sysOrgService.getById(position.getOrgId());
	SysOrg sysOrg=orgService.getById(position.getOrgId());	
		ICache iCache=AppUtil.getBean(ICache.class);
			Long userId=user.getUserId();
	String posKey=getPositionKey(userId);
	
	
		String orgKey=getOrgKey(userId);
		iCache.add(posKey ,position);
		iCache.add(orgKey ,sysOrg);



	
	/**
	 * 设置默认的岗位。
	 */
	 
	 /*
	public static void setDefaultPos(){
		HttpServletRequest request= RequestUtil.getHttpServletRequest();
		HttpServletResponse response= RequestUtil.getHttpServletResponse();
		
		HttpSession session = null;
		//从session中获取。
		if(request!=null){
			session = request.getSession();
			session.removeAttribute(ContextUtil.CurrentPos);
			session.removeAttribute(ContextUtil.CurrentOrg);
		}
		CookieUitl.delCookie(ContextUtil.CurrentPos, request, response);
		CookieUitl.delCookie(ContextUtil.CurrentOrg, request, response);
		PositionService positionService=(PositionService)AppUtil.getBean("positionService");
		Long userId=getCurrentUserId();
		Position position = positionService.getDefaultPosByUserId(userId);
		
		if(position!=null){
			saveSessionCookie(position,request,response,session);
			ContextUtil.setCurrentPos(position);
			ContextUtil.setCurrentOrg(getOrgByPos(position));
		}
		else{
			
			
			if(request!=null){
				session = request.getSession();
				session.removeAttribute(ContextUtil.CurrentPos);
				session.removeAttribute(ContextUtil.CurrentOrg);
				session.setAttribute(ContextUtil.SwitcherTag, "1");
			}
			
			
			CookieUitl.delCookie(ContextUtil.CurrentPos, request, response);
			CookieUitl.delCookie(ContextUtil.CurrentOrg, request, response);
			
			ICache iCache=(ICache)AppUtil.getBean(ICache.class);
			String userKey=ContextUtil.CurrentOrg + userId;
			iCache.delByKey(userKey);
			
			String posKey=ContextUtil.CurrentPos + userId;
			iCache.delByKey(posKey);
			ContextUtil.cleanCurUser();
			ContextUtil.cleanCurrentPos();
			ContextUtil.cleanCurrentOrg();
			
		}
		
		
	}
	*/
	
		}
	/**
	 * 获取当前组织。
	 * <pre>
	 * 1.从线程中获取当前用户的组织。
	 * 2.如果获取不到则根据当前用户去缓存中获取。
	 * </pre>
	 * @return
	 */
	public static SysOrg getCurrentOrg(){
	//	SysOrg sysOrg = null;
		ICache iCache=(ICache)AppUtil.getBean(ICache.class);
		SysOrgService sysOrgService=AppUtil.getBean(SysOrgService.class);
		Long userId=getCurrentUserId();
	//	if(userId>0){
		//	String userKey=ContextUtil.CurrentOrg + userId;
		//	sysOrg=(SysOrg)iCache.getByKey(userKey);
	//	}
	
		String orgKey=getOrgKey(userId);
		SysOrg sysOrg=(SysOrg)iCache.getByKey(orgKey);
	
		if(sysOrg==null){
		//	sysOrg = curOrg.get();
		//	if(sysOrg == null){
			//现在当前组织为岗位，故转换获取行政级别的公司Id
				Position position = getCurrentPos();   //现在当前组织为岗位，故转换获取行政级别的公司Id
			if(position!=null){
				Long orgId=position.getOrgId();
				sysOrg=sysOrgService.getById(orgId);
			}
		//	if(sysOrg != null && userId>0) {
			//	iCache.add(ContextUtil.CurrentOrg + userId,sysOrg);
			}
		if(sysOrg != null) {
			iCache.add(orgKey,sysOrg);
		}
		return sysOrg;
	}
	
	/**
	 * 获取当前单位。
	 * <pre>
	 * 从缓存中读取当前用户的所属单位。
	 * 从当前组织向上查找，找到组织类别为单位的组织返回。
	 * eg:	 如果当前岗位的组织id为123，组织path 为： 1.2.22.123 。而id为2和22的都为公司类型，那么 会返回22，
	 * </pre>
	 * @return
	 */
	public static SysOrg getCurrentCompany(){
		ICache iCache=(ICache)AppUtil.getBean(ICache.class);
		//SysOrg sysCompany =null;
		
		String orgKey=ContextUtil.CurrentCompany + getCurrentUserId();
	//	sysCompany=(SysOrg)iCache.getByKey(orgKey);
			SysOrg sysCompany=(SysOrg)iCache.getByKey(orgKey);
		if(sysCompany==null){
			SysOrg org = getCurrentOrg();
			if(org == null) return null;
			
			SysOrgTacticService orgTacticService=AppUtil.getBean(SysOrgTacticService.class);
			List<SysOrg> sysOrgList= orgTacticService.getSysOrgListByOrgTactic();
			
			if(BeanUtils.isEmpty(sysOrgList)) {
				return null;
			}
			List<Long> orgIdList=new ArrayList<Long>();
		//	if(org.getOrgType() <= CompanyOrgType ){// 如果当前组织为公司类型，或者为集团类型，返回当前组织
		//		sysCompany = org;
		//	}else{
	for(SysOrg orgTmp:sysOrgList){
				orgIdList.add(orgTmp.getOrgId());
			}
			
				SysOrgService orgService = AppUtil.getBean(SysOrgService.class);
			//	String path = org.getPath();
			//	String[] orgIds = path.split("\\.");
				
			//	if(BeanUtils.isNotEmpty(orgIds))
		//		for (int i=orgIds.length-2; i >= 0; i--) { 
		//			long uperOrgId = Long.parseLong(orgIds[i]);
		//			SysOrg upperOrg= orgService.getById(uperOrgId); 
	//				if(upperOrg!=null)
	//				if(upperOrg.getOrgType() <= CompanyOrgType){
	//					sysCompany = upperOrg;
	//					break;
	//				}
	
			while(!orgIdList.contains(org.getOrgId())){
				Long parentId=org.getOrgSupId();
				if(parentId.equals(SysOrg.BEGIN_ORGID)) break;
				org=orgService.getById(parentId);
				
				}
			if(orgIdList.contains(org.getOrgId())){
				sysCompany=org;		
				
				}
				if(sysCompany != null){
					iCache.add(orgKey, sysCompany);
			}
		}
		return sysCompany;
	}
	
	/**
	 * 获取当前公司的ID。
	 * @return
	 */
	public static Long getCurrentCompanyId(){
		if(SysUser.isSuperAdmin()) return 0L; 
		SysOrg org= getCurrentCompany();
		if(org != null) {
			return org.getOrgId();
		}
		else{
			return 0L;
		} 
	}
	/**
	 * 获取当前组织。
	 * <pre>
	 * 1.从线程中获取当前用户的组织。
	 * 2.如果获取不到则根据当前用户去缓存中获取。
	 * </pre>
	 * @return
	 */
	public static Position getCurrentPos(){
	//	Position position =null;
		Long userId=getCurrentUserId();
	//	if(userId>0){
			ICache iCache=(ICache)AppUtil.getBean(ICache.class);
		//	String userKey=ContextUtil.CurrentPos + userId;
		//	position=(Position)iCache.getByKey(userKey);
	//	}
	
		String positionKey=getPositionKey(userId);
		Position position=(Position)iCache.getByKey(positionKey);
		if(position==null){
			PositionService positionService=AppUtil.getBean(PositionService.class);
			position=positionService.getDefaultPosByUserId(userId);
			if(position!=null){
				iCache.add(positionKey, position);
			}
		}
		return position;
	}
	
	public static String getPositionKey(Long userId){
		String posKey=ContextUtil.CurrentPos + userId;
		return posKey;
	}
	
	public static String getOrgKey(Long userId){
		String orgKey=ContextUtil.CurrentOrg + userId;
		return orgKey;
	}
	
	
	/**
	 *  获取当前岗位ID
	 * @return
	 */
	public static Long getCurrentPosId(){
		Position position = getCurrentPos();
	//	if(position!=null) return position.getOrgId();
		if(position!=null) return position.getPosId();
		return null;
	}
	
	/**
	 *  获取当前部门ID
	 * @return
	 */
	public static Long getCurrentOrgId(){
		SysOrg org = getCurrentOrg();
		if(org!=null) return org.getOrgId();
		return null;
	}
	
	/**
	 * 取当前用户皮肤设置。
	 * @return
	 */
	public static String getCurrentUserSkin(HttpServletRequest request){
		String skinStyle="default";
		
		HttpSession session=request.getSession();
		String skin=(String)session.getAttribute("skinStyle");
		if(StringUtil.isNotEmpty(skin)) return skin;
		
		SysPaurService sysPaurService=(SysPaurService)AppUtil.getBean("sysPaurService");
		Long userId = getCurrentUserId();		
		skinStyle=sysPaurService.getCurrentUserSkin(userId);	
		session.setAttribute("skinStyle", skinStyle);
		return skinStyle;
	}
	
	/**
	 * 设置当前组织。
	 * @param sysOrg
	 */
//	public static void setCurrentOrg(SysOrg sysOrg){
//		if(sysOrg==null) return;
//		curOrg.set(sysOrg);
		
		
//	}
	
	/**
	 * 设置当前岗位。
	 * @param sysOrg
	 */
//	public static void setCurrentPos(Position position){
//		if(position==null) return;
//		curPos.set(position);
//	}
	

	/**
	 * 清除当前组织对象。
	 */
//	public static void cleanCurrentPos(){
//		curPos.remove();
//	}
	
	
//	public static void cleanCurrentOrg(){
//		curOrg.remove();
//	}
	
	
	/**
	 * 清除当前用户。
	 */
	public static void cleanCurUser(){
		curUser.remove();
	}
	
	/**
	 * 将组织数据存放到session，cookie和缓存中。
	 * @param sysOrg
	 * @param request
	 * @param response
	 * @param session
	 */
//	private static void saveSessionCookieWithOrg(Position position,HttpServletRequest request,HttpServletResponse response,HttpSession session){
//		Long orgId=position.getPosId();
//		SysOrg sysOrg=getOrgByPos(position);
//		//放到session。
//		session.setAttribute(CurrentOrg, sysOrg);
//		//获取组织id。
//		//添加cookie。
//		CookieUitl.addCookie(CurrentOrg, orgId.toString(), request, response);
//		
//		Long userId=ContextUtil.getCurrentUserId();
//		
//		//将当前人和组织放到缓存中。
//		ICache iCache=(ICache)AppUtil.getBean(ICache.class);
//		String userKey=ContextUtil.CurrentOrg + userId;
//		iCache.add(userKey, sysOrg);
//		iCache.delByKey(ContextUtil.CurrentCompany + userKey);//清除掉当前公司,等到第一次使用的时候加载。
//	}
	
	/**
	 * 将岗位数据存放到session，cookie和缓存中。
	 * @param sysOrg
	 * @param request
	 * @param response
	 * @param session
	 */
//	private static void saveSessionCookie(Position position,HttpServletRequest request,HttpServletResponse response,HttpSession session){
//		//放到session。
//		session.setAttribute(CurrentPos, position);
//		//获取岗位id。
//		Long posId=position.getPosId();
//		//添加cookie。
//		CookieUitl.addCookie(CurrentPos, posId.toString(), request, response);
//		
//		Long userId=ContextUtil.getCurrentUserId();
//		
//		//将当前人和组织放到缓存中。
//		ICache iCache=(ICache)AppUtil.getBean(ICache.class);
//		String userKey=ContextUtil.CurrentPos + userId;
//		iCache.add(userKey, position);
//		
//		saveSessionCookieWithOrg( position, request, response, session);
//	}
	
	/**
	 * 从session和cookie中清除当前组织。
	 * @param request
	 * @param response
	 */
	public static void removeCurrentOrg(HttpServletRequest request,HttpServletResponse response){
	//	HttpSession session=request.getSession(false);
	//	if(session!=null){
	//		session.removeAttribute(CurrentPos);
	//		session.removeAttribute(CurrentOrg);
	//	}
	//	CookieUitl.delCookie(CurrentPos,  request, response);
	//	CookieUitl.delCookie(CurrentOrg,  request, response);
		Long userId=ContextUtil.getCurrentUserId();
		ICache iCache=(ICache)AppUtil.getBean(ICache.class);
	//	String userKey=ContextUtil.CurrentPos + userId;
	String positionKey=getPositionKey( userId);
		String orgKey=getOrgKey( userId);
	//	iCache.delByKey(userKey);
	//	iCache.delByKey(ContextUtil.CurrentOrg + userId);
		iCache.delByKey(positionKey);
		iCache.delByKey(orgKey);
		iCache.delByKey(ContextUtil.CurrentCompany + userId);
	}
	
	/**
	 * 清除所有的线程变量。
	 */
	public static void clearAll(){
		curUser.remove();
	//	curOrg.remove();
	//	curPos.remove();
		curLocale.remove();
		
	//	RequestUtil.clearHttpReqResponse();
		RequestContext.clearHttpReqResponse();
		TaskThreadService.clearAll();
		TaskUserAssignService.clearAll();
		MessageUtil.clean();
		//清除流程缓存。
		
		SysUser.removeRoleList();
		
		AnchorTag.cleanFuncRights();
		
		
		ActivitiDefCache.clearLocal();
		
		//清空数据源
		DbContextHolder.clearDataSource();
		DbContextHolder.setDefaultDataSource();
		
	}
	
	
	
	/**
	 * 当退出系统时，清除当前用户
	 * add by taoguifang at 2012/1/3
	 */
	public static void removeCurrentUser(){
		curUserAccount.remove();		
	}
	
	
	/**
	 * 通过资源的key获得对于key语言
	 * @param code 资源的key
	 * @return
	 */
	public static String getMessages(String code){
		return getMessages(code, null);
	}
	/**
	 * 通过资源的key获得对于key语言
	 * @param code 资源的key
	 * @return
	 */
	public static String getMessagesL(String code,Locale locale){
		return getMessages(code, null,locale);
	}
	
	/**
	 * 	通过资源的key获得对于key语言
	 * @param code 资源的key
	 * @param args
	 * @param locale
	 * @return
	 */
	public static String getMessages(String code,Object[] args,Locale locale){
		AbstractMessageSource messages = (AbstractMessageSource) AppUtil.getBean("messageSource");
		if(locale == null)
			locale = getLocale();
		return messages.getMessage(code,args,locale);
	}
	
	/**
	 * 	通过资源的key获得对于key语言
	 * @param code 资源的key
	 * @param args
	 * @return
	 */
	public static String getMessages(String code,Object[] args){
		return getMessages(code, args, getLocale());
	}
	
}
