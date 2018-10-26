package com.hotent.excelk.service.excelk;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;


import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.excelk.model.excelk.Excelk;
import com.hotent.excelk.controller.excelk.TimeOperate1;
import com.hotent.excelk.dao.excelk.ExcelkDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class ExcelkService extends BaseService<Excelk>
{
	@Resource
	private ExcelkDao dao;
	
	public ExcelkService()
	{
	}
	
	@Override
	protected IEntityDao<Excelk,Long> getEntityDao() 
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
			Excelk excelk=getExcelk(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				excelk.setId(genId);
				this.add(excelk);
			}else{
				excelk.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(excelk);
			}
			cmd.setBusinessKey(excelk.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Excelk对象
	 * @param json
	 * @return
	 */
	public Excelk getExcelk(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Excelk excelk = (Excelk)JSONObject.toBean(obj, Excelk.class);
		return excelk;
	}
	/**
	 * 保存 excelk 信息
	 * @param excelk
	 */

	public void save(Excelk excelk) throws Exception{
		Long id=excelk.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			excelk.setId(id);
		    this.add(excelk);
		}
		else{
		   this.update(excelk);
		}
	}
	/**
	 * 吕经祥
	 * 流程ID排序
	 * @return
	 */
	public Long[] sort(){
			List<Excelk> excelk=new ArrayList<Excelk>() ;
			excelk =dao.getAll();
			//流程个数
			int m=0;
			Long[] defid=new Long[10000];
			double[] sum=new double[10000];
			for(int i=0;i<excelk.size();i++)
			{
				    int j=0;
					for(j=0;j<m;j++)
					{
							if(excelk.get(i).getProcessId().equals(defid[j]))
							{
								sum[j]+=Double.valueOf(excelk.get(i).getReguval());
								break;
							}
					}
					if(j==m)
					{
							defid[j]=excelk.get(i).getProcessId();
							sum[j]=Double.valueOf(excelk.get(i).getReguval());
						    m++;
				    }
			}
			Long a=0l;
			double b=0.0;
			for(int i=0;i<m-1;i++)
				for(int j=i;j<m;j++)
				{
					if(sum[j]>sum[i])
					{
						a=defid[j];defid[j]=defid[i];defid[i]=a;
						b=sum[j];sum[j]=sum[i];sum[i]=b;
					}
				}
			for(int i=0;i<m;i++)
			{	
					System.out.print(defid[i]+",");
					System.out.println(sum[i]);
			}
			return defid;
		}
	public List<Excelk> getTimeByIdAndTime(String time,Long processId){
		return dao.getTimeByIdAndTime(time,processId);
	}

	public List<Excelk> getPictureData(Long processId){
		return dao.getPictureData(processId);
	}
	public List<Excelk> count(String startTime,String endTime,Long defId){
		String stTime =null,enTime=null;
		String stime[] = new TimeOperate1().strOperate1(startTime);
		List<Excelk> list=new ArrayList<Excelk>();
		for(int i=0,j=1;i<stime.length&j<stime.length;i++,j++){
			   //System.out.println("开始时间数组："+stime[i]+"、"+stime[j]);
		       stTime=String.valueOf(Integer.parseInt(stime[i])*60+Integer.parseInt(stime[j]));
		}
		String etime[] = new TimeOperate1().strOperate1(endTime);
		for(int i=0,j=1;i<etime.length&j<etime.length;i++,j++){
			  //System.out.println("结束时间数组："+etime[i]+"、"+etime[j]);
		      enTime=String.valueOf(Integer.parseInt(etime[i])*60+Integer.parseInt(etime[j]));
		}
		for(int i=Integer.parseInt(stTime);i<Integer.parseInt(enTime);i++)
		{
			Excelk  listn=this.dao.getByIdAndTime(String.valueOf(i), defId);
			list.add(listn);
		}
		return list;
	}
}
