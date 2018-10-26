package edu.hrbeu.snmp;

import java.io.IOException;
import java.util.List;

public class GetSysInfo {
	private DataAcquisition acquisition;

	/**
	 * 
	 * @param IP 目的IP
	 * @param port 端口号
	 * @param oid 对象标识符
	 * @param community 团体名称
	 */
	public GetSysInfo(String IP ,String port, String oid,String community) {
		super();
		this.acquisition = new DataAcquisition();
		acquisition.setOid(oid);
		acquisition.setTargetAddress(IP,port);
		acquisition.setCommunity(community);
		
	}
	/**
	 * 获取系统基本信息
	 * @return系统基本信息描述
	 * @throws IOException
	 */
	public String getResult(){
		
		String sysInfo = null;
		String rtnInfo = "";
		String version = "";
		try {
			acquisition.init();
			sysInfo = acquisition.getResultList().get(0);
			if(sysInfo.contains("Win")){
				rtnInfo += sysInfo.substring(sysInfo.indexOf("Win"),sysInfo.indexOf("Win") + 19);
				if(sysInfo.contains("64")){
					rtnInfo += " 64位";
				}
				else {
					rtnInfo += " 32位";
				}
			}
			else {
				rtnInfo += "Linux ";
				version = sysInfo.substring(sysInfo.indexOf(" "));
				version = version.substring(sysInfo.indexOf(" "));
				version = version.substring(version.indexOf(" ") + 1 ,version.indexOf("#") - 1);
				rtnInfo += version;
				if (sysInfo.contains("64")){
					rtnInfo += " 64位";
				}else {
					rtnInfo += " 32位";
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rtnInfo;
	}
	
	public static void main(String[] args){
		//Windows
		GetSysInfo getSysInfo = new GetSysInfo("192.168.2.174", "161","1.3.6.1.2.1.1.1.0", "public");
		System.err.println("系统基本信息：" + getSysInfo.getResult());
		//Linux
		GetSysInfo getSysInfolin = new GetSysInfo("192.168.4.175", "161","1.3.6.1.2.1.1.1.0", "public");
		System.err.println("系统基本信息：" + getSysInfolin.getResult());
	}
}
