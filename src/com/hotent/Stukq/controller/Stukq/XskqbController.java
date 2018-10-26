
package com.hotent.Stukq.controller.Stukq;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.Stukq.makeex.POIExcel;
import com.hotent.Stukq.model.Stukq.Xskqb;
import com.hotent.Stukq.service.Stukq.XskqbService;
import com.hotent.Stukq.model.Stuzh.Xskqzhb;
import com.hotent.core.web.ResultMessage;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.service.system.SysFileService;
/**
 * 对象功能:学生考勤表 控制器类
 */
@Controller
@RequestMapping("/Stukq/Stukq/xskqb/")
public class XskqbController extends BaseController
{
	@Resource
	private XskqbService xskqbService;
	@Resource
	private SysFileService sysFileService;
	/**
	 * 添加或更新学生考勤表。
	 * @param request
	 * @param response
	 * @param xskqb 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新学生考勤表")
	public void save(HttpServletRequest request, HttpServletResponse response,Xskqb xskqb) throws Exception
	{
		String resultMsg=null;		
		try{
			if(xskqb.getId()==null){
				Long id=UniqueIdUtil.genId();
				xskqb.setId(id);
				xskqbService.addAll(xskqb);			
				resultMsg=getText("添加","学生考勤表");
			}else{
				
				String kaoqin1=xskqb.getKqby();
				String kaoqin2=xskqb.getKqbe();
				System.out.println(kaoqin1);
				System.out.println(kaoqin2);
				Long ID=xskqb.getId();
				kaoqin1 = kaoqin1.substring(1, kaoqin1.length()-1);
				kaoqin2 = kaoqin2.substring(1, kaoqin2.length()-1);
				
				//把考勤表转换为map类型
				HashMap<String, String> data1= new HashMap<String, String>();
				JSONObject jsonObject1 = JSONObject.fromObject(kaoqin1);
				Iterator it1 = jsonObject1.keys();
				HashMap<String, String> data2= new HashMap<String, String>();
				JSONObject jsonObject2 = JSONObject.fromObject(kaoqin2);
				Iterator it2 = jsonObject2.keys();
				while (it1.hasNext())  
			       {  
			          String key1 =String.valueOf(it1.next());  
			          String value1 = (String) jsonObject1.get(key1);  
			          data1.put(key1, value1);  
			       }
				 Long id1 =Long.parseLong(data1.get("id"));
				 SysFile sysFile1 = sysFileService.getById(id1);
				 String filePath1= sysFile1.getFilePath();
				 filePath1 = filePath1.replace('\\', '/');
		        System.out.println("文件1="+filePath1);
		        while (it2.hasNext())  
			       {  
			          String key2 =String.valueOf(it2.next());  
			          String value2 = (String) jsonObject2.get(key2);  
			          data2.put(key2, value2);  
			       }
				 Long id2 =Long.parseLong(data2.get("id"));
				 SysFile sysFile2 = sysFileService.getById(id2);
				 String filePath2= sysFile2.getFilePath();
				 filePath2 = filePath2.replace('\\', '/');
		        System.out.println("文件2="+filePath2);
		        HSSFWorkbook work1 = new HSSFWorkbook(new FileInputStream(new File(filePath1)));
		        HSSFSheet sheet1 = work1.getSheetAt(0);
		        HSSFWorkbook work2 = new HSSFWorkbook(new FileInputStream(new File(filePath2)));
		        HSSFSheet sheet2 = work2.getSheetAt(0);
		       /* int rows1 = sheet1.getLastRowNum();
			 	int columns1 = sheet1.getRow(0).getLastCellNum();
			 	int rows2 = sheet2.getLastRowNum(); 
			 	int columns2 = sheet2.getRow(0).getLastCellNum();*/
			 	POIExcel p= new POIExcel();
			      //行环列数 
				 	//创建一个Map对像存数据
				 	//TreeMap<Integer,String> map = new TreeMap<Integer,String>();
				 	List<Xskqzhb> list= new ArrayList<Xskqzhb>();
				 	//Iterator ite = map.entrySet().iterator();
			     list=p.getallExcelDataBySheet(sheet1, sheet2);
			     System.out.println(kaoqin1);
			    System.out.println(kaoqin1); 
			    xskqbService.addzhb(list,ID);
			    resultMsg=getText("合并","学生考勤表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得学生考勤表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看学生考勤表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Xskqb> list=xskqbService.getAll(new QueryFilter(request,"xskqbItem"));
		ModelAndView mv=this.getAutoView().addObject("xskqbList",list);
		
		return mv;
	}
	
	/**
	 * 删除学生考勤表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除学生考勤表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			xskqbService.delAll(lAryId);
			message=new ResultMessage(ResultMessage.Success,"删除学生考勤表及其从表成功!");
			xskqbService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除学生考勤表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑学生考勤表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑学生考勤表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Xskqb xskqb=xskqbService.getById(id);
		List<Xskqzhb> xskqzhbList=xskqbService.getXskqzhbList(id);
		
		return getAutoView().addObject("xskqb",xskqb)
							.addObject("xskqzhbList",xskqzhbList)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得学生考勤表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看学生考勤表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Xskqb xskqb=xskqbService.getById(id);
		List<Xskqzhb> xskqzhbList=xskqbService.getXskqzhbList(id);
		return getAutoView().addObject("id", id);
	}
	
}
