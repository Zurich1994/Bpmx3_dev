package com.hotent.sysinfomation.service.sysinfomation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;

import org.exolab.castor.dsml.Importer;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.system.WSystemInformation;
import com.hotent.sysinfomation.model.sysinfomation.Sysinfomation;
import com.hotent.sysinfomation.dao.sysinfomation.SysinfomationDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class SysinfomationService extends BaseService<Sysinfomation>
{
	@Resource
	private SysinfomationDao dao;
	
	public SysinfomationService()
	{
	}
	
	@Override
	protected IEntityDao<Sysinfomation,Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 流程处理器方法 用于处理业务数据
	 * @param cmd
	 * @throws Exception
	 */
	public void processHandler(ProcessCmd cmd)throws Exception{
		Map data=cmd.getFormDataMap();
		if(BeanUtils.isNotEmpty(data)){
			String json=data.get("json").toString();
			Sysinfomation sysinfomation=getSysinfomation(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				sysinfomation.setId(genId);
				this.add(sysinfomation);
			}else{
				sysinfomation.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(sysinfomation);
			}
			cmd.setBusinessKey(sysinfomation.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Sysinfomation对象
	 * @param json
	 * @return
	 */
	public Sysinfomation getSysinfomation(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Sysinfomation sysinfomation = (Sysinfomation)JSONObject.toBean(obj, Sysinfomation.class);
		return sysinfomation;
	}
	/**
	 * 保存 子系统内部信息统计 信息
	 * @param sysinfomation
	 */

	public void save(Sysinfomation sysinfomation) throws Exception{
		Long id=sysinfomation.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			sysinfomation.setId(id);
		    this.add(sysinfomation);
		}
		else{
		   this.update(sysinfomation);
		}
	}


	public void writeToWTbaSubsysList(List<WSystemInformation> sys_information) 
		{
			System.out.println("进入写入service");
			dao.delall();
			int ergodicId=1;
			int p1,p2,p3;
			int systemNum=0;
			List<Sysinfomation> SysinfomatioList;
			for(WSystemInformation a1:sys_information)//遍历子系统链表  一维
			{systemNum++;
			System.out.println("进入写入dao");
		dao.add1(
				Long.valueOf(systemNum),//id
				Integer.toString(systemNum),//子系统数量
				a1.getSystem_name(),//子系统名字
				a1.getSystem_id(),//子系统ID
				Double.toString(a1.getF_nmop_non_elem_num()),//非规范元素
				
				Double.toString(a1.getF_nmop_elem_num()),//元素总个数					
				Double.toString(a1.getF_work_subsys_maturity()),//成熟度
				Double.toString(a1.getF_info_stand_grade()),//规范化程度 
				Double.toString(a1.getF_knowledge_opnum()),//知识操作个数
				Double.toString(a1.getF_knowledge_flag_zidong()),//自动实现的操作数量
				
				Double.toString(a1.getF_knowled_struct_grade()),	//结构化程度比例				
				Double.toString(a1.getF_local()),
				Double.toString(a1.getF_out_gov()),
				Double.toString(a1.getF_in_gov()),
			    Double.toString(a1.getF_out_pub()),
				
				Double.toString(a1.getF_in_pub()),
				Double.toString(a1.getF_info_res_open_grade()),
				Double.toString(a1.getF_flocal()),
			    Double.toString(a1.getF_fout_gov()),
				Double.toString(a1.getF_fin_gov()),
				
				Double.toString(a1.getF_fout_pub()),
				Double.toString(a1.getF_fin_pub()),
				Double.toString(a1.getF_bus_frame_open_grade()),
				Double.toString(a1.defNum),
				Double.toString(a1.activityNum),
				
				Double.toString(a1.markActivityNum),
				Double.toString(a1.getRes_speed()),
				Double.toString(a1.getSer_cycle()),
				Double.toString(a1.getData_incre()),
				Double.toString(a1.getCom_trans()),
				
				
				Double.toString(a1.getAve_freq_occur()),
				Double.toString(a1.getPeak_freq()),
				Double.toString(a1.getCode_line_num())
				//Double.toString(a1.);
				
		        );
	             }
	
		
		}

		private long valueOf(int systemNum) {
		// TODO Auto-generated method stub
		return 0;
	}

		public List<Sysinfomation> getAll1() {
			// TODO Auto-generated method stub
			return dao.getAll1();
		}
	}