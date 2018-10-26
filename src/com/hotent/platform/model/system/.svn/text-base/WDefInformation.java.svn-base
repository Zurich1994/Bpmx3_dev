package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hotent.Markovchain.model.Markovchain.Markovchain;
import com.hotent.core.model.BaseModel;
import com.hotent.core.util.AppUtil;
import com.hotent.platform.dao.bpm.BpmDefinitionDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.system.ResourcesService;

public class WDefInformation extends BaseModel
{
	
	
	/**
	 * @return the bpmdef
	 */
	public BpmDefinition getBpmdef() {
		return bpmdef;
	}
	/**
	 * @param bpmdef the bpmdef to set
	 */
	public void setBpmdef(BpmDefinition bpmdef) {
		this.bpmdef = bpmdef;
	}
	public List<Markovchain> getMarkovchainList() {
		return markovchainList;
	}
	public void setMarkovchainList(List<Markovchain> markovchainList) {
		this.markovchainList = markovchainList;
	}

	/**
	 *流程基本信息 流程id
	 */
	private Long  DefId;
	/**
	 *操作个数（总数）
	 */
	
//信息量-》信息资源开发程度      服务量-》业务架构开放程度
	private double f_local,f_flocal;//本地信息量，本地服务量
	private double f_out_gov,f_fout_gov;//利用外部 信息量，服务量
	private double f_in_gov,f_fin_gov;//外部利用信息量，服务量
	private double f_out_pub,f_fout_pub;//利用公共信息量，服务量
	private double f_in_pub,f_fin_pub;//公共利用信息量，服务量
	//private double jiqi_zuoye;
	//信息规范化程度
	private double f_knowledge_opnum;//知识操作个数
	private double f_nmop_elem_num;//新增修改类操作元素个数
	private double f_nmop_non_elem_num;//新增修改类操作非规范元素个数
	private double f_knowledge_flag_shoudong,f_knowledge_flag_zidong;//知识类操作（手动）（自动）
	private double f_work_subsys_maturity;//流程成熟度
	
	private double f_res_speed;   //响应速度
	private double f_ser_cycle;   //服务周期
	private double f_data_incre;//数据增量
	private double f_com_trans;    //通信传输量
	private double f_ave_freq_occur;  //平均发生频度
	private double f_peak_freq;    //上午高峰期发生频度
	private double f_code_line_num; //代码行数
	private double  exenum;//mark
	private double  dependExenum;//depend mark
	private double  operateExenum;//mark
	private double  operateDependExenum;//依赖mark、
	
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

	private double  f_mac_work;   //机器作业
	private double  f_man_work;   //  人工作业
	private double  f_info_static;  //静态信息
	private double  f_info_dynamic; // 动态信息
	private double  f_n_a;   //N/A
	
	private double  f_machOpeSta;
	private double  f_workOpeSta;

	public BpmDefinition bpmdef;
	public String typeName;
	private int notSetNum;
	public List<WNodeInformation> def_node_info_list;
	
	public List<String> defNodeList ;//流程的所有节点名
	public List<Markovchain> markovchainList;//流程的马尔科夫链
	
	public WDefInformation(Long Def_Id){  		
		DefId=Def_Id;
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

		f_knowledge_opnum=0;
		
		
		f_res_speed=0;   //响应速度
		f_ser_cycle=0;   //服务周期
		f_data_incre=0;//数据增量
		f_com_trans=0;    //通信传输量
		f_ave_freq_occur=0;  //平均发生频度
		f_peak_freq=0;    //上午高峰期发生频度
		f_code_line_num=0;  //代码行数
		exenum=0;dependExenum=0;
		operateExenum=0;operateDependExenum=0;
		f_mac_work=0;   //机器作业
		f_man_work=0;   //  人工作业
		f_info_static=0;  //静态信息
		f_info_dynamic=0; // 动态信息
        f_n_a=0;     //N/A
        
        f_machOpeSta=0;
    	f_workOpeSta=0;

		typeName="";

		def_node_info_list=new ArrayList<WNodeInformation>();
		markovchainList=new ArrayList<Markovchain>();
	}
	public WDefInformation defPrimaryStatistics(WNodeInformation node_informa){//初级统计	
		/*
		
		/*
		switch(Integer.parseInt(node_informa.wActivityDetail.getF_server_source()))//服务判断:活动来源
		{
			case 1:this.f_flocal++;		System.out.println("本地服务量"+1);break;
			case 2:this.f_fout_gov++;	System.out.println("利用外部 服务量"+1);break;
			case 3:this.f_fin_gov++;	System.out.println("外部利用服务量"+1);break;
			case 4:this.f_fout_pub++;	System.out.println("利用公共服务量"+1);break;
			case 5:this.f_fin_pub++;	System.out.println("公共利用服务量"+1);break;

		}*/
		
			if(node_informa.activityDetail!=null){
		
		if(node_informa.activityDetail.getServer_source().equals("本地服务")){
			this.f_flocal++;		System.out.println("本地服务量"+1);
		}else if(node_informa.activityDetail.getServer_source().equals("利用外部 服务")){
			this.f_fout_gov++;		System.out.println("利用外部 服务量"+1);
		}else if(node_informa.activityDetail.getServer_source().equals("外部利用服务")){
			this.f_fin_gov++;		System.out.println("外部利用服务量"+1);
		}else if(node_informa.activityDetail.getServer_source().equals("利用公共服务")){
			this.f_fout_pub++;		System.out.println("利用公共服务量"+1);
		}else if(node_informa.activityDetail.getServer_source().equals("公共利用服务量")){
			this.f_fin_pub++;		System.out.println("公共利用服务量"+1);
		} 
		
	
		if(node_informa.activityDetail.getOp_info_sum()!=null)
		{
			Long a=node_informa.activityDetail.getOp_info_sum();
		
			if(node_informa.activityDetail.getInfo_type().equals("本地信息")){
				this.f_local+=Double.parseDouble(a.toString());		System.out.println("本地信息量"+a);
			}else if(node_informa.activityDetail.getInfo_type().equals("利用外部信息")){
				this.f_out_gov+=Double.parseDouble(a.toString());		System.out.println("利用外部 信息量"+a);
			}else if(node_informa.activityDetail.getInfo_type().equals("外部利用信息")){
				this.f_in_gov+=Double.parseDouble(a.toString());		System.out.println("外部利用信息量"+a);
			}else if(node_informa.activityDetail.getInfo_type().equals("利用公共信息")){
				this.f_out_pub+=Double.parseDouble(a.toString());		System.out.println("利用公共信息量"+a);
			}else if(node_informa.activityDetail.getInfo_type().equals("公共利用信息")){
				this.f_in_pub+=Double.parseDouble(a.toString());		System.out.println("公共利用信息量"+a);
				}
			
			
			if(node_informa.activityDetail.getServer_way().equals("机器作业")){
				if(node_informa.activityDetail.getInfo_shape().equals("静态信息")){
					this.f_machOpeSta+=a;    System.out.println("机器作业静态统计值"+a);
				}
				
			}else if(node_informa.activityDetail.getServer_way().equals("人工作业")){
				if(node_informa.activityDetail.getInfo_shape().equals("静态信息")){
					this.f_workOpeSta+=a;      System.out.println("人工作业静态统计值"+a);
				}
			}
		
		
		/*switch(Integer.parseInt(node_informa.wActivityDetail.getF_info_type()))//信息判断:信息类型

		/*switch(Integer.parseInt(node_informa.wActivityDetail.getF_info_type()))//信息判断:信息类型

		{
			case 1:this.f_local+=a;		System.out.println("本地信息量"+a);break;
			case 2:this.f_out_gov+=a;	System.out.println("利用外部 信息量"+a);break;
			case 3:this.f_in_gov+=a;	System.out.println("外部利用信息量"+a);break;
			case 4:this.f_fout_pub+=a;	System.out.println("利用公共信息量"+a);break;
			case 5:this.f_fin_pub+=a;	System.out.println("公共利用信息量"+a);break;

		}*/ 
		JdbcTemplate jdbcTemplate = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");

		
		
		

		List<Map<String, Object>> f_List=jdbcTemplate.queryForList//此处必须用sql语句，因为wac表中没有其信息
		(
				"select f_elem_sum,f_non_s_elem_num " +
				"from w_activity_detail " +
				"where f_actDef_id=\""+node_informa.activityDetail.getActDef_Id()+"\" and " +
					  "f_activity_id=\""+node_informa.activityDetail.getActivity_id()+"\""
		);	
		if(f_nmop_elem_num!=0)
		this.f_nmop_elem_num+=Double.valueOf(f_List.get(0).get("f_elem_sum").toString());////新增修改类操作元素个数
		if(f_nmop_non_elem_num!=0)
		this.f_nmop_non_elem_num+=Double.valueOf(f_List.get(0).get("f_non_s_elem_num").toString());//新增修改类操作非规范元素个数
		System.out.println("公共利用信息量"+a);
		}
		if(!node_informa.activityDetail.getRes_speed().equals(""))
		this.f_res_speed+=Double.valueOf(node_informa.activityDetail.getRes_speed());  
		
		if(!node_informa.activityDetail.getSer_cycle().equals(""))
		this.f_ser_cycle+=Double.valueOf(node_informa.activityDetail.getSer_cycle());
		
		if(!node_informa.activityDetail.getData_incre().equals(""))
		this.f_data_incre+=Double.valueOf(node_informa.activityDetail.getData_incre()); 
		
		if(!node_informa.activityDetail.getCom_trans().equals(""))
		this.f_com_trans+=Double.valueOf(node_informa.activityDetail.getCom_trans());
		
		if(!node_informa.activityDetail.getAve_freq_occur().equals(""))
		this.f_ave_freq_occur+=Double.valueOf(node_informa.activityDetail.getAve_freq_occur());
		
		if(!node_informa.activityDetail.getPeak_freq().equals(""))
		this.f_peak_freq+=Double.valueOf(node_informa.activityDetail.getPeak_freq());  
		if(!node_informa.activityDetail.getCode_line_num().equals(""))
		this.f_code_line_num+=Double.valueOf(node_informa.activityDetail.getCode_line_num());
		}
		
		
		this.exenum+=node_informa.getExeNum();	
		this.dependExenum+=node_informa.getDependExeNum();
		this.operateExenum+=node_informa.getOperateExeNum();		
		this.operateDependExenum+=node_informa.getOperateDependExeNum();
         		
		return this;	         
	}
	
	public Long getDefId() {
		return DefId;
	}
	public void setDefId(Long defId) {
		DefId = defId;
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
	public double getF_ele_sun() {
		return f_nmop_elem_num;
	}
	public void setF_ele_sun(double fEleSun) {
		f_nmop_elem_num = fEleSun;
	}
	public double getF_non_s_elem_num() {
		return f_nmop_non_elem_num;
	}
	public void setF_non_s_elem_num(double fNonSElemNum) {
		f_nmop_non_elem_num = fNonSElemNum;
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
	
	
	
	
	public List<WNodeInformation> getDef_node_info_list() {
		return def_node_info_list;
	}
	public void setDef_node_info_list(List<WNodeInformation> defNodeInfoList) {
		def_node_info_list = defNodeInfoList;
	}
	public double getF_knowledge_opnum() {
		return f_knowledge_opnum;
	}
	public void setF_knowledge_opnum(double fKnowledgeOpnum) {
		f_knowledge_opnum = fKnowledgeOpnum;
		
		
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
	
	public double getMac_Work() {   
		return f_mac_work;
	}
	public void setMac_Work(double macwork) {   
		this.f_mac_work = macwork;
	}
	public double getManWork() {
		return f_man_work;
	}
	public void setManWork(double manwork) {
		this.f_man_work = manwork;
	}
	
	public double getInfoStatic() {
		return f_info_static;
	}
	public void setInfoStatic(double infostatic) {
		this.f_info_static = infostatic;
	}
	public double getInfoDynamic() {
		return f_info_dynamic;
	}
	public void setInfoDynamic(double infodynamic) {
		this.f_info_dynamic = infodynamic;
	}
	public double getNa() {
		return f_n_a;
	}
	public void setNa(double na) {
		this.f_n_a = na;
	}
	public double getMachOpeSta() {
		  return f_machOpeSta;
	}
	public void setMachOpeSta(double machOpeSta) {
	     this.f_machOpeSta = machOpeSta;
	}
    public double getWorkOpeSta() {
		return f_workOpeSta;
	}
    public void setWorkOpeSta(double workOpeSta) {
		this.f_workOpeSta = workOpeSta;
	}
    public int getNotSetNum() {
		return notSetNum;
	}
	public void setNotSetNum(int NotSetNum) {
		notSetNum = NotSetNum;
	}
	public List<String> getDefNodeList() {
		return defNodeList;
	}
	public void setDefNodeList(List<String> defNodeList) {
		this.defNodeList = defNodeList;
	}
	
	public List<String> getDefNodeNameList(List<WNodeInformation> def_node_info_list){//得到流程图节点list中的节点名
		List<String>  DefNodeNameList = new ArrayList<String>();
		for(int i=0;i<def_node_info_list.size();i++){
			String nodeName = def_node_info_list.get(i).bpmNodeSet.getNodeId();
			DefNodeNameList.add(nodeName);
		}
		return DefNodeNameList;
	}
	
}
