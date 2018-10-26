package edu.hrbeu.snmp;

import java.io.IOException;
import java.util.List;
/**
 * 获取CPU利用率类
 * @author zl
 *
 */
public class GetCPUUsage {
	
	private DataAcquisition acquisition;

	/**
	 * 
	 * @param IP 目的IP
	 * @param port 端口号
	 * @param oid 对象标识符
	 * @param community 团体名称
	 */
	public GetCPUUsage(String IP ,String port, String oid,String community) {
		super();
		this.acquisition = new DataAcquisition();
		acquisition.setOid(oid);
		acquisition.setTargetAddress(IP,port);
		acquisition.setCommunity(community);
		
	}
	/**
	 * 计算CPU利用率
	 * @return CPU各个核心利用率的平均值，即整个CPU的利用率
	 * @throws IOException
	 */
	public double getResult(){
		
		double rate = 0.0;
		List<String> result;
		try {
			acquisition.init();
			result = acquisition.getResultList();
			int num = result.size();
			for(int i=0 ; i < num ;++i){
				rate+=Double.valueOf(result.get(i));
			}
			rate/=num;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rate;
	}
	
	public static  void main(String[] args){
		//Windows
		GetCPUUsage getCPUUsage = new GetCPUUsage("192.168.2.174", "161","1.3.6.1.2.1.25.3.3.1.2", "public");
		double rate = getCPUUsage.getResult();
		System.err.println("CPU利用率："+rate);
		//Linux
		GetCPUUsage getCPUUsagelin = new GetCPUUsage("192.168.4.175", "161","1.3.6.1.2.1.25.3.3.1.2", "public");
		double ratelin = getCPUUsagelin.getResult();
		System.err.println("CPU利用率："+ratelin);
	} 
}
