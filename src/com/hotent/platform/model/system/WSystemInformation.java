package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


import com.hotent.platform.model.system.WDefInformation;
import com.hotent.platform.service.system.SubSystemService;

public class WSystemInformation extends BaseModel
{	@Resource
	private SubSystemService subSystemService;
	//主键

public SubSystem system;
	private String system_id;
	/**
	 *子系统重要信息
	 */
	private String  system_name;
	/**
	 *子系统基本统计信息
	 */
	private double f_local,f_flocal;//本地信息量，本地服务量
	private double f_out_gov,f_fout_gov;//利用外部 信息量，服务量
	private double f_in_gov,f_fin_gov;//外部利用信息量，服务量
	private double f_out_pub,f_fout_pub;//利用公共信息量，服务量
	private double f_in_pub,f_fin_pub;//公共利用信息量，服务量
	
	private double f_knowledge_opnum;//知识操作个数
	
	
	private double f_nmop_elem_num;//新增修改类操作元素个数
	private double f_nmop_non_elem_num;//新增修改类操作非规范元素个数
	private double f_knowledge_flag_shoudong,f_knowledge_flag_zidong;//知识类操作（手动）（自动）
	
	
	
	/**
	 *子系统计算得到的信息
	 */
	private double f_work_subsys_maturity;//子系统成熟度
	private double f_bus_frame_open_grade;//业务架构开放程度
	private double f_info_res_open_grade;//信息资源开放程度
	private double f_info_stand_grade;//信息规范化程度
	private double f_knowled_struct_grade;//知识结构化程度
	private int notSetNum;
	
	private double f_res_speed;   //响应速度
	private double f_ser_cycle;   //服务周期
	private double f_data_incre;//数据增量
	private double f_com_trans;    //通信传输量
	private double f_ave_freq_occur;  //平均发生频度
	private double f_peak_freq;    //上午高峰期发生频度
	private double f_code_line_num; //代码行数
	
	
	private double  f_mac_work;   //机器作业
	private double  f_man_work;   //  人工作业
	private double  f_info_static;  //静态信息
	private double  f_info_dynamic; // 动态信息
	private double  f_n_a;   //N/A
	
	private double exenum;
	private double dependExenum;
	private double  operateExenum;//mark
	private double  operateDependExenum;//依赖mark、
	public double getExeNum() {
		return exenum;
	}
	public void setExeNum(double exenum) {
		this.exenum = exenum;
	}
	public double getDependExeNum() {
		return dependExenum;
	}
	public void setDependExeNum(double dependExenum) {
		this.dependExenum = dependExenum;
	}
	public double getOperateExeNum() {
		return operateExenum;
	}
	public void setOperateExeNum(double operateExenum) {
		this.operateExenum = operateExenum;
	}
	public double getOperateDependExeNum() {
		return operateDependExenum;
	}
	public void setOperateDependExeNum(double operateDependExenum) {
		this.operateDependExenum = operateDependExenum;
	}
	
	
	private double  f_machOpeSta;
	private double  f_workOpeSta;
	/**
	 * 
	 *子系统的流程链表
	 */
	public int defNum,activityNum,markActivityNum,dependMarkActivityNum;//流程数量 节点数量 马尔科夫节点数量 依赖马尔链的节点数量。
	public List<WDefInformation> sys_def_info_list;
	public WSystemInformation(SubSystem subSystem){  
		system=subSystem;
		system_id=String.valueOf(subSystem.getSystemId());
		system_name=subSystem.getSysName();
		f_local=f_flocal=0;
		f_out_gov=f_fout_gov=0;
		f_in_gov=f_fin_gov=0;
		f_out_pub=f_fout_pub=0;
		f_in_pub=f_fin_pub=0;
		f_nmop_elem_num=0;//新增修改类操作元素个数
		f_nmop_non_elem_num=0;//新增修改类操作非规范元素个数
		f_knowledge_flag_shoudong=0;//统计流程下知识类操作的类别（手动的数量）  
		f_knowledge_flag_zidong=0;
		f_work_subsys_maturity=1;
		
		
		f_bus_frame_open_grade=0;//业务架构开放程度
		f_info_res_open_grade=0;//信息资源开放程度
		f_info_stand_grade=0;//信息规范化程度
		f_knowled_struct_grade=0;//知识结构化程度
		
		f_res_speed=0;   //响应速度
		f_ser_cycle=0;   //服务周期
		f_data_incre=0;//数据增量
		f_com_trans=0;    //通信传输量
		f_ave_freq_occur=0;  //平均发生频度
		f_peak_freq=0;    //上午高峰期发生频度
		f_code_line_num=0;  //代码行数
		exenum=0;
		dependExenum=0;
		operateExenum=0;
		operateDependExenum=0;
		f_mac_work=0;   //机器作业
		f_man_work=0;   //  人工作业
		f_info_static=0;  //静态信息
		f_info_dynamic=0; // 动态信息
        f_n_a=0;     //N/A
        
        f_machOpeSta=0;//机器静态
    	f_workOpeSta=0;//人工静态
    	notSetNum=0;
		sys_def_info_list=new ArrayList<WDefInformation>();
		defNum=activityNum=markActivityNum=0;
	} 
	public WSystemInformation(String systemId,String systemName){  
		system_id=systemId;
		system_name=systemName;
		f_local=f_flocal=0;
		f_out_gov=f_fout_gov=0;
		f_in_gov=f_fin_gov=0;
		f_out_pub=f_fout_pub=0;
		f_in_pub=f_fin_pub=0;
		f_nmop_elem_num=0;//新增修改类操作元素个数
		f_nmop_non_elem_num=0;//新增修改类操作非规范元素个数
		f_knowledge_flag_shoudong=0;//统计流程下知识类操作的类别（手动的数量）  
		f_knowledge_flag_zidong=0;
		f_work_subsys_maturity=1;
		
		
		f_bus_frame_open_grade=0;//业务架构开放程度
		f_info_res_open_grade=0;//信息资源开放程度
		f_info_stand_grade=0;//信息规范化程度
		f_knowled_struct_grade=0;//知识结构化程度
		
		f_res_speed=0;   //响应速度
		f_ser_cycle=0;   //服务周期
		f_data_incre=0;//数据增量
		f_com_trans=0;    //通信传输量
		f_ave_freq_occur=0;  //平均发生频度
		f_peak_freq=0;    //上午高峰期发生频度
		f_code_line_num=0;  //代码行数
		exenum=0;
		dependExenum=0;
		operateExenum=0;
		operateDependExenum=0;
		f_mac_work=0;   //机器作业
		f_man_work=0;   //  人工作业
		f_info_static=0;  //静态信息
		f_info_dynamic=0; // 动态信息
        f_n_a=0;     //N/A
        
        f_machOpeSta=0;//机器静态
    	f_workOpeSta=0;//人工静态
    	notSetNum=0;
		sys_def_info_list=new ArrayList<WDefInformation>();
		defNum=activityNum=markActivityNum=0;
	} 
	public WSystemInformation sysPrimaryStatistics(WDefInformation def_informa){//初级统计
		
		this.f_local+=def_informa.getF_local();			this.f_flocal+=def_informa.getF_flocal();
		this.f_out_gov+=def_informa.getF_out_gov();		this.f_fout_gov+=def_informa.getF_fout_gov();
		this.f_in_gov+=def_informa.getF_in_gov();		this.f_fin_gov+=def_informa.getF_fin_gov();
		this.f_out_pub+=def_informa.getF_out_pub();		this.f_fout_pub+=def_informa.getF_fout_pub();
		this.f_in_pub+=def_informa.getF_in_pub();		this.f_fin_pub+=def_informa.getF_fin_pub();
		this.f_nmop_elem_num+=def_informa.getF_ele_sun();	
		this.f_nmop_non_elem_num+=def_informa.getF_non_s_elem_num();	
		this.f_work_subsys_maturity*=def_informa.getF_work_subsys_maturity();//子系统成熟度=流程的成熟度累乘（我猜的！！！！！！！！！）
		
		this.f_res_speed+=def_informa.getRes_speed();      this.f_ser_cycle+=def_informa.getSer_cycle();
		this.f_data_incre+=def_informa.getData_incre();    this.f_com_trans+=def_informa.getCom_trans();
		this.f_ave_freq_occur+=def_informa.getAve_freq_occur();
		this.f_peak_freq+=def_informa.getPeak_freq();       this.f_code_line_num+=def_informa.getCode_line_num();
		this.exenum+=def_informa.getExeNum();
		this.dependExenum+=def_informa.getDependExeNum();
		this.operateExenum+=def_informa.getOperateExeNum();
		this.operateDependExenum+=def_informa.getOperateDependExeNum();   
		this.f_mac_work+=def_informa.getMac_Work();        this.f_man_work+=def_informa.getManWork();
		this.f_info_static+=def_informa.getInfoStatic();       this.f_info_dynamic+=def_informa.getInfoDynamic();
		
		return this;
	}
	public WSystemInformation FinalRankStatistics(){
		double a,b,c;
		if(this.f_flocal==0) {a=0;}
		else
		{	b=(this.f_fout_gov+this.f_fout_pub)/(this.f_flocal+this.f_fout_gov+this.f_fout_pub);
			c=(this.f_fin_gov+this.f_fin_pub)/this.f_flocal;
			a=0.5*(b+c);
		}
		System.out.println("业务架构开发程度"+0.5+"*(("+this.f_fout_gov+"+"+
				this.f_fout_pub+")/("+
				this.f_flocal+"+"+
				this.f_fout_gov+"+"+
				this.f_fout_pub+")+("+
				this.f_fin_gov+"+"+
				this.f_fin_pub+")/"+
				this.f_flocal+")");//服务					
		System.out.println(a);
		this.f_bus_frame_open_grade=a;
		
		if(this.f_local==0) {a=0;}
		else
		{	b=(this.f_out_gov+this.f_out_pub)/(this.f_local+this.f_out_gov+this.f_out_pub);
			c=(this.f_in_gov+this.f_in_pub)/this.f_local;
			a=0.5*(b+c);
		}
		System.out.println("信息资源开发程度"+0.5+"*(("+
				this.f_out_gov+"+"+
				this.f_out_pub+")/("+
				this.f_local+"+"+
				this.f_out_gov+"+"+
				this.f_out_pub+")+("+
				this.f_in_gov+"+"+
				this.f_in_pub+")/"+
				this.f_local+")");//信息					
		System.out.println(a);	
		this.f_info_res_open_grade=a;
		
		
		
		if(this.f_nmop_elem_num==0) {a=0;}
		else{	
			b=1-this.f_nmop_non_elem_num/this.f_nmop_elem_num;
			a=b*this.f_work_subsys_maturity;
		}
		System.out.println("信息规范化程度："+1+"-（"+this.f_nmop_non_elem_num+"/"
				+this.f_nmop_elem_num+")*"
				+this.f_work_subsys_maturity);				
		System.out.println(a);
		this.f_info_stand_grade=a;

		
		
		if(this.f_local==0) {a=0;}
		else{	
			b=(this.f_fout_gov+this.f_fout_pub)/(this.f_local+this.f_fout_gov+this.f_out_pub);
			c=(this.f_fin_pub+this.f_fin_gov)/this.f_local;
			a=0.5*(b+c);
		}
		System.out.println("信息资源开放程度："+0.5+"*(("+this.f_fout_gov+"+"+this.f_fout_gov+")/("
				+this.f_local+"+"
				+this.f_fout_gov+"+"
				+this.f_out_pub+")" 
				+"+("+this.f_fin_pub+"+"+this.f_fin_gov+")/"+this.f_local+")");				
		System.out.println(a);
		this.f_info_res_open_grade=a;
		
		
		
		if(this.f_machOpeSta==0) {a=0;}
		else
		{	b=(this.f_machOpeSta+this.f_workOpeSta);
			a=this.f_machOpeSta/b;
		}
		System.out.println("知识结构化程度"+"*(("+this.f_machOpeSta+"/("
				+this.f_machOpeSta+"+"+this.f_workOpeSta+")");
								
		System.out.println(a);	
		this.f_knowled_struct_grade=a;
		
		
		
		
		
		return this;	
	}
	public String getSystem_id() {
		return system_id;
	}
	public void setSystem_id(String systemId) {
		system_id = systemId;
	}
	public String getSystem_name() {
		return system_name;
	}
	public void setSystem_name(String systemName) {
		system_name = systemName;
	}
	public double getF_local() {
		return f_local;
	}
	public void setF_local(double fLocal) {
		f_local = fLocal;
	}
	public double getF_flocal() {
		return f_flocal;
	}
	public void setF_flocal(double fFlocal) {
		f_flocal = fFlocal;
	}
	public double getF_out_gov() {
		return f_out_gov;
	}
	public void setF_out_gov(double fOutGov) {
		f_out_gov = fOutGov;
	}
	public double getF_fout_gov() {
		return f_fout_gov;
	}
	public void setF_fout_gov(double fFoutGov) {
		f_fout_gov = fFoutGov;
	}
	public double getF_in_gov() {
		return f_in_gov;
	}
	public void setF_in_gov(double fInGov) {
		f_in_gov = fInGov;
	}
	public double getF_fin_gov() {
		return f_fin_gov;
	}
	public void setF_fin_gov(double fFinGov) {
		f_fin_gov = fFinGov;
	}
	public double getF_out_pub() {
		return f_out_pub;
	}
	public void setF_out_pub(double fOutPub) {
		f_out_pub = fOutPub;
	}
	public double getF_fout_pub() {
		return f_fout_pub;
	}
	public void setF_fout_pub(double fFoutPub) {
		f_fout_pub = fFoutPub;
	}
	public double getF_in_pub() {
		return f_in_pub;
	}
	public void setF_in_pub(double fInPub) {
		f_in_pub = fInPub;
	}
	public double getF_fin_pub() {
		return f_fin_pub;
	}
	public void setF_fin_pub(double fFinPub) {
		f_fin_pub = fFinPub;
	}
	
	public double getF_knowledge_opnum() {
		return f_knowledge_opnum;
	}
	public void setF_knowledge_opnum(double fKnowledgeOpnum) {
		f_knowledge_opnum = fKnowledgeOpnum;
	}
	
	
	public double getF_nmop_elem_num() {
		return f_nmop_elem_num;
	}
	public void setF_nmop_elem_num(double fNmopElemNum) {
		f_nmop_elem_num = fNmopElemNum;
	}
	public double getF_nmop_non_elem_num() {
		return f_nmop_non_elem_num;
	}
	public void setF_nmop_non_elem_num(double fNmopNonElemNum) {
		f_nmop_non_elem_num = fNmopNonElemNum;
	}
	public double getF_knowledge_flag_shoudong() {
		return f_knowledge_flag_shoudong;
	}
	public void setF_knowledge_flag_shoudong(double fKnowledgeFlagShoudong) {
		f_knowledge_flag_shoudong = fKnowledgeFlagShoudong;
	}
	public double getF_knowledge_flag_zidong() {
		return f_knowledge_flag_zidong;
	}
	public void setF_knowledge_flag_zidong(double fKnowledgeFlagZidong) {
		f_knowledge_flag_zidong = fKnowledgeFlagZidong;
	}
	public double getF_work_subsys_maturity() {
		return f_work_subsys_maturity;
	}
	public void setF_work_subsys_maturity(double fWorkSubsysMaturity) {
		f_work_subsys_maturity = fWorkSubsysMaturity;
	}
	public double getF_bus_frame_open_grade() {
		return f_bus_frame_open_grade;
	}
	public void setF_bus_frame_open_grade(double fBusFrameOpenGrade) {
		f_bus_frame_open_grade = fBusFrameOpenGrade;
	}
	public double getF_info_res_open_grade() {
		return f_info_res_open_grade;
	}
	public void setF_info_res_open_grade(double fInfoResOpneGrade) {
		f_info_res_open_grade = fInfoResOpneGrade;
	}
	public double getF_info_stand_grade() {
		return f_info_stand_grade;
	}
	public void setF_info_stand_grade(double fInfoStandGrade) {
		f_info_stand_grade = fInfoStandGrade;
	}
	public double getF_knowled_struct_grade() {
		return f_knowled_struct_grade;
	}
	public void setF_knowled_struct_grade(double fKnowledStructGrade) {
		f_knowled_struct_grade = fKnowledStructGrade;
	}
	public List<WDefInformation> getSys_def_info_list() {
		return sys_def_info_list;
	}
	public void setSys_def_info_list(List<WDefInformation> sysDefInfoList) {
		sys_def_info_list = sysDefInfoList;
	}
	public double getRes_speed() {
		return f_res_speed;
	}
	public void setRes_speed(double fResSpeed) {
		f_res_speed = fResSpeed;
	}
	public double getSer_cycle() {
		return f_ser_cycle;
	}
	public void setSer_cycle(double fserCycle) {
		f_ser_cycle = fserCycle;
	}
	public double getData_incre() {
		return f_data_incre;
	}
	public void setData_incre(double dataIncre) {
		f_data_incre = dataIncre;
	}
	public double getCom_trans() {
		return f_com_trans;
	}
	public void setCom_trans(double comTrans) {
		f_com_trans = comTrans;
	}
	
	public double getAve_freq_occur() {
		return f_ave_freq_occur;
	}
	public void setAve_freq_occur(double aveFreqOccur) {
		f_ave_freq_occur = aveFreqOccur;
	}
	public double getPeak_freq() {
		return f_peak_freq;
	}
	public void setPeak_freq(double peakFreq) {
		f_peak_freq = peakFreq;
	}
	public double getCode_line_num() {
		return f_code_line_num;
	}
	public void setCode_line_num(double codeLineNum) {
		f_code_line_num = codeLineNum;
	}
	public int getNotSetNum() {
		return notSetNum;
	}
	public void setNotSetNum(int NotSetNum) {
		notSetNum = NotSetNum;
	}
}
