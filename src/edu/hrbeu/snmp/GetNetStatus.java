package edu.hrbeu.snmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetNetStatus {
	// 要ping的主机
	private String remoteIpAddress;
	//平均往返时间
	private String averageTime;
	//服务成功率
	private String successRate;
	/**
	 * @author zl
	 * @param remoteIpAddress
	 */
	public GetNetStatus(String remoteIpAddress) {
		super();
		this.remoteIpAddress = remoteIpAddress;
		averageTime = null;
		successRate = null;
	}

	/**
	 * @author zl
	 * 执行ping
	 */
	public void execPing(){
		
		 String line = null;
		 String CMD = "ping " + remoteIpAddress;
		 String returnstr = "";
         try
         {
             Process pro = Runtime.getRuntime().exec(CMD);
             BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream(),"GBK"));
             while ((line = buf.readLine()) != null){
            	 returnstr+=line;
             }
             averageTime = returnstr.substring(returnstr.indexOf("平均") + 5);
             successRate = returnstr.substring(returnstr.indexOf("(") + 1, returnstr.indexOf("丢失)") - 2);
             int surate = 100 - Integer.valueOf(successRate);
             successRate = String.valueOf(surate);
         }
         catch (Exception ex)
         {
             System.out.println(ex.getMessage());
         }
     }
	


	/**
	 * 获取平均往返时间
	 * @author zl
	 * @return
	 */
	public String getAverageTime() {
		if(null==averageTime)
			execPing();
		return averageTime;
	}

	public void setAverageTime(String averageTime) {
		this.averageTime = averageTime;
	}

	/**
	 * 获取成功率
	 * @author zl
	 * @return
	 */
	public String getSuccessRate() {
		if(null==successRate)
			execPing();
		return successRate;
	}

	public void setSuccessRate(String successRate) {
		this.successRate = successRate;
	}

	public static void main(String[] args) {
		//win
		GetNetStatus getNetStatus = new GetNetStatus("192.168.2.171");
		System.err.println("平均往返时间：" + getNetStatus.getAverageTime());
		System.err.println("服务成功率：" + getNetStatus.getSuccessRate());
		//Lin
		GetNetStatus getNetStatuslin = new GetNetStatus("192.168.4.175");
		System.err.println("平均往返时间：" + getNetStatuslin.getAverageTime());
		System.err.println("服务成功率：" + getNetStatuslin.getSuccessRate());
		
}
	}
