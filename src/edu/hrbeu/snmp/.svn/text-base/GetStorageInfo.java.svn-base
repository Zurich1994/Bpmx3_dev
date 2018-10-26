package edu.hrbeu.snmp;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.snmp4j.smi.OctetString;

/**
 * 获取存储设备信息类
 * @author zl
 *
 */
public class GetStorageInfo {

	private DataAcquisition dataAcquisition;
	//存储设备oid
	private String oid;
	//设备序号oid
	private  String index;
	//设备描述oid
	private  String descr;
	//设备分配单元oid
	private  String allocUnits;
	//设备总空间大小oid
	private  String size;
	//设备已用空间大小oid
	private  String used;
	//设备分配失败大小oid
	private  String allocFail;
	//存储设备序号列表
	private  List<String> indexList = new ArrayList<String>();
	//存储设备描述列表
	private  List<String> descrList = new ArrayList<String>();
	//存储设备分配单位列表
	private  List<String> unitsList = new ArrayList<String>();
	//存储设备总空间列表
	private  List<String> sizeList = new ArrayList<String>();
	//存储设备总空间映射
	private  Map<String, String> sizeMap = new HashMap<String, String>();
	//存储设备可用空间
	private  Map<String, String> availableMap = new HashMap<String, String>();
	//存储设备利用率大小
	private  Map<String, String> usageMap = new HashMap<String, String>();
	
	public String getOid() {
		return oid;
	}

	/**
	 * @author zl
	 * 获取Linux存储设备利用率
	 * @return
	 */
	public Map<String, String> getLinuxStorageRate(){
		Map<String,String> linuxRateMap = new HashMap<String, String>();
		for(int i = 0; i < sizeList.size(); ++i){
				Integer rate = Integer.valueOf(getUsages().get(i));
				linuxRateMap.put(descrList.get(i), String.valueOf(rate));
		}
		return linuxRateMap;
	}
	/**
	 * @author zl
	 * 获取Linux存储设备可用空间
	 * @return
	 */
	public Map<String, String> getLinuxAvailSize(){
		Map<String,String> linuxAvailSizeMap = new HashMap<String, String>();
		for(int i = 0; i < sizeList.size(); ++i){
				Long availSize = Long.valueOf(getAvailSpace().get(i));
				Long unit = Long.valueOf(unitsList.get(i));
				//换算成以MB为单位
				availSize = availSize * unit / 1024 /1024;
				linuxAvailSizeMap.put(descrList.get(i), String.valueOf(availSize));
		}
		return linuxAvailSizeMap;
	}
	/**
	 * @author zl
	 * 获取Linux硬盘/使用率
	 * @return
	 */
	public int getLinuxhddUsages(){
		for(int i = 0; i < sizeList.size(); ++i){
			if(descrList.get(i).contains("/")){
				int usages = Integer.valueOf(getUsages().get(i));
				return usages;
			}
		}
		return 0;
	}
	/**
	 * @author zl
	 * 获取Linux硬盘/可用空间
	 * @return
	 */
	public long getLinuxhddAvailSize(){
		for(int i = 0; i < sizeList.size(); ++i){
			if(descrList.get(i).contains("/")){
				Long phyAvailsize = Long.valueOf(getAvailSpace().get(i));
				Long unit = Long.valueOf(unitsList.get(i));
				//换算成以MB为单位
				phyAvailsize = phyAvailsize * unit / 1024 /1024;
				return phyAvailsize;
			}
		}
		return 0;
	}
	/**
	 * @author zl
	 * 获取Linux硬盘/总空间
	 * @return
	 */
	public long getLinuxhddSize(){
		Long phySize = 0L;
		for(int i = 0; i < sizeList.size(); ++i){
			if(descrList.get(i).contains("/")){
				Long size = Long.valueOf(sizeList.get(i));
				Long unit = Long.valueOf(unitsList.get(i));
				//换算成以MB为单位
				phySize = size * unit / 1024 /1024;
				break;
			}
		}
		return phySize;
	}
	/**
	 * @author zl
	 * 获取Linux存储设备总空间
	 * @return
	 */
	public Map<String, String> getLinuxStorageSize(){
		Map<String,String> linuxDiskSizeMap = new HashMap<String, String>();
		for(int i = 0; i < sizeList.size(); ++i){
				Long size = Long.valueOf(sizeList.get(i));
				Long unit = Long.valueOf(unitsList.get(i));
				//换算成以MB为单位
				size = size * unit / 1024 /1024;
				linuxDiskSizeMap.put(descrList.get(i), String.valueOf(size));
		}
		return linuxDiskSizeMap;
	}
	/**
	 * @author zl
	 * 获取硬盘各分区利用率
	 * @return
	 */
	public Map<String, String> getHardDiskRate(){
		Map<String,String> hardDiskRateMap = new HashMap<String, String>();
		for(int i = 0; i < sizeList.size(); ++i){
			if(!descrList.get(i).contains("Memory")&&!sizeList.get(i).equals("0")){
				Integer rate = Integer.valueOf(getUsages().get(i));
				hardDiskRateMap.put(descrList.get(i), String.valueOf(rate));
			}
		}
		return hardDiskRateMap;
	}
	/**
	 * @author zl
	 * 获取硬盘各分区可用空间
	 * @return
	 */
	public Map<String, String> getHardDiskAvailSize(){
		Map<String,String> hardDiskAvailSizeMap = new HashMap<String, String>();
		for(int i = 0; i < sizeList.size(); ++i){
			if(!descrList.get(i).contains("Memory")&&!sizeList.get(i).equals("0")){
				Long availSize = Long.valueOf(getAvailSpace().get(i));
				Long unit = Long.valueOf(unitsList.get(i));
				//换算成以MB为单位
				availSize = availSize * unit / 1024 /1024;
				hardDiskAvailSizeMap.put(descrList.get(i), String.valueOf(availSize));
			}
		}
		return hardDiskAvailSizeMap;
	}
	/**
	 * @author zl
	 * 获取硬盘各分区总空间
	 * @return
	 */
	public Map<String, String> getHardDiskSize(){
		Map<String,String> hardDiskSizeMap = new HashMap<String, String>();
		for(int i = 0; i < sizeList.size(); ++i){
			if(!descrList.get(i).contains("Memory")&&!sizeList.get(i).equals("0")){
				Long size = Long.valueOf(sizeList.get(i));
				Long unit = Long.valueOf(unitsList.get(i));
				//换算成以MB为单位
				size = size * unit / 1024 /1024;
				hardDiskSizeMap.put(descrList.get(i), String.valueOf(size));
			}
		}
		return hardDiskSizeMap;
	}
	/**
	 * @author zl
	 * 获取虚拟内存使用率
	 * @return
	 */
	public int getVirUsages(){
		for(int i = 0; i < sizeList.size(); ++i){
			if(descrList.get(i).contains("Virtual")||descrList.get(i).contains("Swap")){
				int usages = Integer.valueOf(getUsages().get(i));
				return usages;
			}
		}
		return 0;
	}
	/**
	 * @author zl
	 * 获取物理内存使用率
	 * @return
	 */
	public int getPhyUsages(){
		for(int i = 0; i < sizeList.size(); ++i){
			if(descrList.get(i).contains("Physical")||descrList.get(i).contains("Real")){
				int usages = Integer.valueOf(getUsages().get(i));
				return usages;
			}
		}
		return 0;
	}
	/**
	 * @author zl
	 * 获取虚拟内存可用空间
	 * @return
	 */
	public long getVirAvailSize(){
		for(int i = 0; i < sizeList.size(); ++i){
			if(descrList.get(i).contains("Virtual")||descrList.get(i).contains("Swap")){
				Long virAvailsize = Long.valueOf(getAvailSpace().get(i));
				Long unit = Long.valueOf(unitsList.get(i));
				//换算成以MB为单位
				virAvailsize = virAvailsize * unit / 1024 /1024;
				return virAvailsize;
			}
		}
		return 0;
	}
	/**
	 * @author zl
	 * 获取物理内存可用空间
	 * @return
	 */
	public long getPhyAvailSize(){
		for(int i = 0; i < sizeList.size(); ++i){
			if(descrList.get(i).contains("Physical")||descrList.get(i).contains("Real")){
				Long phyAvailsize = Long.valueOf(getAvailSpace().get(i));
				Long unit = Long.valueOf(unitsList.get(i));
				//换算成以MB为单位
				phyAvailsize = phyAvailsize * unit / 1024 /1024;
				return phyAvailsize;
			}
		}
		return 0;
	}
	/**
	 * @author zl
	 * 获取虚拟内存总空间
	 * @return
	 */
	public long getVirSize(){
		for(int i = 0; i < sizeList.size(); ++i){
			if(descrList.get(i).contains("Virtual")||descrList.get(i).contains("Swap")){
				Long virsize = Long.valueOf(sizeList.get(i));
				Long unit = Long.valueOf(unitsList.get(i));
				//换算成以MB为单位
				virsize = virsize * unit / 1024 /1024;
				return virsize;
			}
		}
		return 0;
	}
	/**
	 * @author zl
	 * 获取物理内存总空间
	 * @return
	 */
	public long getPhySize(){
		for(int i = 0; i < sizeList.size(); ++i){
			if(descrList.get(i).contains("Physical")||descrList.get(i).contains("Real")){
				Long physize = Long.valueOf(sizeList.get(i));
				Long unit = Long.valueOf(unitsList.get(i));
				//换算成以MB为单位
				physize = physize * unit / 1024 /1024;
				return physize;
			}
		}
		return 0;
	}
	/**
	 * @author zl
	 * 获取存储设备使用率
	 * @return
	 */
	public List<String> getUsages(){
		List<String> rateList = new ArrayList<String>();
		List<String> useList = getUseSpaces();
		double rate;
		int percent;
		for(int i = 0;i < useList.size(); ++i){
			String str1 = useList.get(i);
			String str2 = sizeList.get(i);
			Long devide = Long.valueOf(str1);
			if(devide.equals(0L)){
				rate = 0L;
				percent = 0;
			}else{
				rate = Double.valueOf(str1)/Double.valueOf(str2);
				//保留两位小数
				BigDecimal bigDecimal = new BigDecimal(rate);
				rate = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				percent = (int)(rate * 100);
			}
			rateList.add(String.valueOf(percent));
		}
		return rateList;
		
	}
	/**
	 * @author zl
	 * 获取存储设备已用空间大小
	 * @return
	 */
	public List<String> getUseSpaces(){
		//存储设备已用空间
		List<String> useList = new ArrayList<String>();
		try {
			dataAcquisition.setOid(used);
			dataAcquisition.init();
			useList = dataAcquisition.getResultList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return useList;
	}
	
	/**
	 * @author zl 获取存储设备可用空间
	 * @return
	 */
	public List<String> getAvailSpace() {

		List<String> availList = new ArrayList<String>();
		List<String> useList = getUseSpaces();
		for (int i = 0; i < useList.size(); ++i) {
			String str = useList.get(i);
			String strTotal = sizeList.get(i);
			Long tempLong = Long.valueOf(strTotal) - Long.valueOf(str);
			str = tempLong.toString();
			availList.add(str);
		}
		return availList;
	}
	
	/**
	 *@author zl
	 *初始化设备信息，获取设备序号、类型、描述、分配单元大小
	 */
	public void initStorageInfo(){
		
		try {
			//获取序号列表
			dataAcquisition.setOid(index);
			dataAcquisition.init();
			indexList = dataAcquisition.getResultList();
			//获取描述列表
			dataAcquisition.setOid(descr);
			dataAcquisition.init();
			descrList = dataAcquisition.getResultList();
			for(int i = 0; i< descrList.size();++i){
				String s = descrList.get(i);
				//截取盘符
				if(s.contains(":")){
					s=s.substring(0, 3);
					descrList.set(i, s);
				}
			}
			//获取分配单位列表
			dataAcquisition.setOid(allocUnits);
			dataAcquisition.init();
			unitsList = dataAcquisition.getResultList();
			//获取总空间列表
			dataAcquisition.setOid(size);
			dataAcquisition.init();
			sizeList = dataAcquisition.getResultList();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @author zl
	 * @param oid
	 * 设定存储设备相关oid
	 */
	public void setOid(String oid) {
		this.oid = oid;
		index = oid + ".1";
		descr = oid + ".3";
		allocUnits = oid + ".4";
		size = oid + ".5";
		used = oid + ".6";
		allocFail = oid + ".7";
	}

	/**
	 * 
	 * @param IP 目的IP
	 * @param port 端口号
	 * @param oid 对象标识符
	 * @param community 团体名称
	 */
	public GetStorageInfo(String IP ,String port, String oid,String community) {
		super();
		this.dataAcquisition = new DataAcquisition();
		setOid(oid);
		dataAcquisition.setOid(oid);
		dataAcquisition.setTargetAddress(IP,port);
		dataAcquisition.setCommunity(community);
		initStorageInfo();
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Windows
		GetStorageInfo getStorageInfo = new GetStorageInfo("192.168.2.174", "161", "1.3.6.1.2.1.25.2.3.1", "public");
		System.err.println("物理内存总大小："+getStorageInfo.getPhySize() + "MB");
		System.err.println("物理内存可用空间大小："+getStorageInfo.getPhyAvailSize() + "MB");
		System.err.println("物理内存使用率："+getStorageInfo.getPhyUsages() + "%");
		System.err.println("虚拟内存总大小："+getStorageInfo.getVirSize() + "MB");
		System.err.println("虚拟内存可用空间大小："+getStorageInfo.getVirAvailSize() + "MB");
		System.err.println("虚拟内存使用率："+getStorageInfo.getVirUsages() + "%");
		//各分区总大小
		Map<String, String> hddSize = getStorageInfo.getHardDiskSize();
		System.err.println("各分区总空间：" + hddSize);
		//各分区可用空间大小
		Map<String, String> hddAvailSize = getStorageInfo.getHardDiskAvailSize();
		System.err.println("各分区可用空间：" + hddAvailSize);
		//各分区利用率
		Map<String, String> hddrate = getStorageInfo.getHardDiskRate();
		System.err.println("各分区使用率：" + hddrate);
		
		//Linux
		GetStorageInfo getStorageInfoLin = new GetStorageInfo("192.168.4.175", "161", "1.3.6.1.2.1.25.2.3.1", "public");
		//各分区总大小
		Map<String, String> linuxhddSize = getStorageInfoLin.getLinuxStorageSize();
		System.err.println("各存储总空间：" + linuxhddSize);
		//各分区可用空间大小
		Map<String, String> linuxhddAvailSize = getStorageInfoLin.getLinuxAvailSize();
		System.err.println("各存储可用空间：" + linuxhddAvailSize);
		//各分区利用率
		Map<String, String> linuxhddrate = getStorageInfoLin.getLinuxStorageRate();
		System.err.println("各存储使用率：" + linuxhddrate);
		System.err.println("物理内存总大小："+getStorageInfoLin.getPhySize() + "MB");
		System.err.println("物理内存可用空间大小："+getStorageInfoLin.getPhyAvailSize() + "MB");
		System.err.println("物理内存使用率："+getStorageInfoLin.getPhyUsages() + "%");
		System.err.println("虚拟内存总大小："+getStorageInfoLin.getVirSize() + "MB");
		System.err.println("虚拟内存可用空间大小："+getStorageInfoLin.getVirAvailSize() + "MB");
		System.err.println("虚拟内存使用率："+getStorageInfoLin.getVirUsages() + "%");
		System.err.println("/总大小："+getStorageInfoLin.getLinuxhddSize()+ "MB");
		System.err.println("/可用空间大小："+getStorageInfoLin.getLinuxhddAvailSize() + "MB");
		System.err.println("/使用率："+getStorageInfoLin.getLinuxhddUsages()+ "%");
	}
}
