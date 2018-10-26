package com.hotent.platform.webservice.impl.util;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.system.SysAcceptIp;
/** 
 * IP地址拦截器 。
 * <pre>
 * 在系统中设置了黑白名单。
 * 如果远程的ip在符合黑白名单的设置，那么远程请求可以通过。
 * 否则抛出异常。
 * 
 * 在app-cxf-service.xml中做配置。
 * &lt;bean id="addressInInterceptor"   class="com.hotent.platform.webservice.impl.util.AddressInInterceptor" />  
 * 
 * &lt;jaxws:endpoint    
 *		id="UserDetailsService"    
 *		implementor="#UserDetailsServiceImpl" 
 *		implementorClass="com.hotent.platform.webservice.impl.UserDetailsServiceImpl"	
 *		address="/UserDetailsService" >
 *		&lt;jaxws:inInterceptors>  
 *			&lt;ref bean="addressInInterceptor" />  
 *		&lt;/jaxws:inInterceptors>
 *	&lt;/jaxws:endpoint> 	
 * </pre>
 */  
public class AddressInInterceptor extends AbstractPhaseInterceptor<Message> {  
	@Resource
	com.hotent.platform.service.system.SysAcceptIpService sysAcceptIpService;
    public AddressInInterceptor() {  
        super(Phase.RECEIVE);  
    }  
    
	private static ThreadLocal<Message> sysWsLogMsg=new ThreadLocal<Message>();
    
    public static Message getSysWsLogMsg() {
		return sysWsLogMsg.get();
	}
    
    public static void setSysWsLogMsg(Message message) {
    	sysWsLogMsg.set(message);
	}
    
    public static void removeSysWsLogMsg() {
    	sysWsLogMsg.remove();
	}

	/**
     *拦截器过滤方法。 
     */
    public void handleMessage(Message message) throws Fault {  
        HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
        String ipAddr=request.getRemoteAddr();
       
        if(!accept(ipAddr)) 
			throw new Fault(new IllegalAccessException("ip地址" + ipAddr + " 拒绝访问"));
        else
        	setSysWsLogMsg(message);
    }  
    
    
    /**
     * 判断IP地址是否在允许的IP地址内
     * @param ipAddr	IP地址。
     * @return	true为放行，false为拦截。
     */
    protected boolean accept(String ipAddr){    	
		try {
			long requestIP = conveIp2Long(ipAddr);
			List<SysAcceptIp> list = sysAcceptIpService.getAll();
			if (BeanUtils.isNotEmpty(list)) {
				for (SysAcceptIp b : list) {
					long start = conveIp2Long(b.getStartIp());
					long end = conveIp2Long(b.getEndIp());
					if (requestIP >= start && requestIP <= end)
						return true;
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}
		return false;
    }
    
    
    /**
     * 转化IP地址为long类型。
     * @param ipAddr	IP地址。
     * @return IP地址的long表现形式。
     * @throws UnknownHostException	当IP地址格式错误是会抛出此异常。
     */
    public static long conveIp2Long(String ipAddr) throws UnknownHostException{
    	//从IP串转成IP地址对象
    	InetAddress ia=InetAddress.getByName(ipAddr);
    	//从IP地址对象获取到IP的四个字节
		byte[] b=ia.getAddress();
		//每字节分加转位并转化为long然后相加
		long iaddr=
				(b[0]<<24)  &  0x00000000ffffffffL 
				|(b[1]<<16) &  0x00000000ffffffffL 
				|(b[2]<<8)  &  0x00000000ffffffffL 
				|(b[3]<<0)  &  0x00000000ffffffffL;  
		return iaddr;
    }
    
   
  
}  