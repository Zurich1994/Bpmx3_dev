package edu.hrbeu.snmp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class MonitorService extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		GetCPUUsage getCPUUsage = new GetCPUUsage("192.168.2.174", "161","1.3.6.1.2.1.25.3.3.1.2", "public");
		GetStorageInfo getStorageInfo = new GetStorageInfo("192.168.2.174", "161", "1.3.6.1.2.1.25.2.3.1", "public");
		String cpuRate = String.valueOf(getCPUUsage.getResult());
		String memory = String.valueOf(getStorageInfo.getPhyUsages());
		//将CPU利用率作为字符串传给请求者
		resp.getWriter().write(memory +"_"+ cpuRate);
	}

}
