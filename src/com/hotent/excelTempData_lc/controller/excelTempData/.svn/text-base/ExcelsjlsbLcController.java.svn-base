
package com.hotent.excelTempData_lc.controller.excelTempData;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.excelTempData_lc.model.excelTempData.ExcelsjlsbLc;
import com.hotent.excelTempData_lc.service.excelTempData.ExcelsjlsbLcService;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.service.system.SysFileService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:excel 数据临时表 控制器类
 */
@Controller
@RequestMapping("/excelTempData_lc/excelTempData/excelsjlsbLc/")
public class ExcelsjlsbLcController extends BaseController
{
	@Resource
	private ExcelsjlsbLcService excelsjlsbLcService;
	@Resource
	private SysFileService sysFileService;
	/**
	 * 添加或更新excel 数据临时表。
	 * @param request
	 * @param response
	 * @param excelsjlsbLc 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新excel 数据临时表")
	public void save(HttpServletRequest request, HttpServletResponse response,ExcelsjlsbLc excelsjlsbLc) throws Exception
	{
		String resultMsg=null;		
		try{
			if(excelsjlsbLc.getId()==null){
				Long id=UniqueIdUtil.genId();
				excelsjlsbLc.setId(id);
				excelsjlsbLcService.add(excelsjlsbLc);
				resultMsg=getText("添加","excel 数据临时表");
			}else{
			    excelsjlsbLcService.update(excelsjlsbLc);
				resultMsg=getText("更新","excel 数据临时表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得excel 数据临时表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看excel 数据临时表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String exp = request.getParameter("exp").trim().replace(" ", "+");
		request.getSession().setAttribute("exp", exp);
		String redirType = request.getParameter("rtnType").trim();
		request.getSession().setAttribute("redirType", redirType);
		System.out.println("curveEndTime:"+redirType);
		List<ExcelsjlsbLc> list=excelsjlsbLcService.getAll(new QueryFilter(request,"excelsjlsbLcItem"));
		ModelAndView mv=this.getAutoView().addObject("excelsjlsbLcList",list).addObject("redirType",redirType);
		
		return mv;
	}
	
	@RequestMapping("listCal1")
	@Action(description="查看excel 数据临时表分页列表")
	public ModelAndView listCal1(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<ExcelsjlsbLc> list=excelsjlsbLcService.getAll(new QueryFilter(request,"excelsjlsbLcItem"));
		ModelAndView mv=this.getAutoView().addObject("excelsjlsbLcList",list);
		
		return mv;
	}
	
	@RequestMapping("listCal2")
	@Action(description="查看excel 数据临时表分页列表")
	public ModelAndView listCal2(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String fileId = String.valueOf(request.getSession().getAttribute("fileId"));
		//System.out.println("id:"+fileId);
		SysFile sysFile = sysFileService.getById(Long.parseLong(fileId));
		
		
		List<ExcelsjlsbLc> list = excelsjlsbLcService.readExcel(sysFile);
//		List<ExcelsjlsbLc> list = excelsjlsbLcService.readExcel();
		ModelAndView mv=this.getAutoView().addObject("excelsjlsbLcList",list);
		
		return mv;
	}
	
	@RequestMapping("listDisplayResult")
	@Action(description="查看excel 数据临时表分页列表")
	public ModelAndView listDisplayResult(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String redirType = String.valueOf(request.getSession().getAttribute("redirType"));
		System.out.println("********redirType***********"+redirType);
		String exp= (String) String.valueOf(request.getSession().getAttribute("exp"));
		System.out.println(exp);
		String sj = request.getParameter("sj").trim();
		System.out.println("sj="+sj);
		System.out.println("***********************************************");
		sj = sj.substring(0, sj.length()-1);
		String row[] = sj.split(",");
		
		double a = 0,b = 0,c = 0,x1=0,y1=0,x2=0,y2=0,x3=0,y3=0;
		String returnExp = "";
		String yearstart="",yearend="";
		if(exp.equals("y=a*x+b")){
				String col0[] = row[0].split(":");
				String col1[] = row[1].split(":");
				if(redirType.equals("year")){
					 x1 = Double.parseDouble(col0[0].substring(0, 4).trim());
					 y1 = Double.parseDouble(col0[1].trim());
					 x2 = Double.parseDouble(col1[0].substring(0, 4).trim());
					 y2 = Double.parseDouble(col1[1].trim());
					 if(x1 <x2){
						 yearstart =col0[0].substring(0, 4).trim();
						 yearend = col1[0].substring(0, 4).trim();
					 }
					 else{
						 yearstart = col1[0].substring(0, 4).trim();
						 yearend = col0[0].substring(0, 4).trim();
					 }
					 System.out.println("???????????????????????????");
					 System.out.println("年起始："+yearstart+"年终止"+yearend);
				}
				else if(redirType.equals("month")){
					 x1 = Double.parseDouble(col0[0].substring(4, 6).trim());
					 y1 = Double.parseDouble(col0[1].trim());
					 x2 = Double.parseDouble(col1[0].substring(4, 6).trim());
					 y2 = Double.parseDouble(col1[1].trim());
				}
				else if(redirType.equals("day")){
					 x1 = Double.parseDouble(col0[0].substring(6, 8).trim());
					 y1 = Double.parseDouble(col0[1].trim());
					 x2 = Double.parseDouble(col1[0].substring(6, 8).trim());
					 y2 = Double.parseDouble(col1[1].trim());
				}else if(redirType.equals("hour")){
					 x1 = Double.parseDouble(col0[0].substring(8, 10).trim());
					 y1 = Double.parseDouble(col0[1].trim());
					 x2 = Double.parseDouble(col1[0].substring(8, 10).trim());
					 y2 = Double.parseDouble(col1[1].trim());
				}
				else if(redirType.equals("minute")){
					 x1 = Double.parseDouble(col0[0].substring(10, 12).trim());
					 y1 = Double.parseDouble(col0[1].trim());
					 x2 = Double.parseDouble(col1[0].substring(10, 12).trim());
					 y2 = Double.parseDouble(col1[1].trim());
				}
				System.out.println("x1 :"+x1+"   y1:"+y1+"   x2:"+x2+"   y2:"+y2);
				a = (y1-y2)/(x1-x2);
				b = (x1*y2-x2*y1)/(x1-x2);
				System.out.println("a:"+a+"   b:"+b);
				if(b<0)
				returnExp = "y="+a+"*x"+b;
				else{
					returnExp = "y="+a+"*x+"+b;
				}
				System.out.println("公式："+returnExp);
		}
		
		if(exp.equals("y=a*(x^2)+b*x+c")){
			Double t1,t2,t3,t4;
			String col0[] = row[0].split(":");
			String col1[] = row[1].split(":");
			String col2[] = row[2].split(":");
			if(redirType.equals("year")){
			 x1 = Double.parseDouble(col0[0].substring(0, 4).trim());
			 y1 = Double.parseDouble(col0[1].trim());
			 x2 = Double.parseDouble(col1[0].substring(0, 4).trim());
			 y2 = Double.parseDouble(col1[1].trim());
			 x3 = Double.parseDouble(col2[0].substring(0, 4).trim());
			 y3 = Double.parseDouble(col2[1].trim());
			 t1 =x1>x2?x1:x2;
			 t2 = t1 >x3?t1:x3;//t2最大的
			 t3 = x1 < x2?x1:x2;
			 t4 = t3<x3?t3:x3;
			 yearstart =t2.toString();
			 yearstart = yearstart.substring(0,yearstart.indexOf("."));
			 yearend = t4.toString();
			 yearend = yearend.substring(0,yearend.indexOf("."));
			 System.out.println("年起始："+yearstart+"年终止"+yearend);
			}
			else if(redirType.equals("month")){
				x1 = Double.parseDouble(col0[0].substring(4, 6).trim());
				 y1 = Double.parseDouble(col0[1].trim());
				 x2 = Double.parseDouble(col1[0].substring(4, 6).trim());
				 y2 = Double.parseDouble(col1[1].trim());
				 x3 = Double.parseDouble(col2[0].substring(4, 6).trim());
				 y3 = Double.parseDouble(col2[1].trim());
			}
			else if(redirType.equals("day")){
				x1 = Double.parseDouble(col0[0].substring(6,8).trim());
				 y1 = Double.parseDouble(col0[1].trim());
				 x2 = Double.parseDouble(col1[0].substring(6,8).trim());
				 y2 = Double.parseDouble(col1[1].trim());
				 x3 = Double.parseDouble(col2[0].substring(6,8).trim());
				 y3 = Double.parseDouble(col2[1].trim());
				
			}else if(redirType.equals("hour")){
				x1 = Double.parseDouble(col0[0].substring(8,10).trim());
				 y1 = Double.parseDouble(col0[1].trim());
				 x2 = Double.parseDouble(col1[0].substring(8,10).trim());
				 y2 = Double.parseDouble(col1[1].trim());
				 x3 = Double.parseDouble(col2[0].substring(8,10).trim());
				 y3 = Double.parseDouble(col2[1].trim());
				
			}else if(redirType.equals("minute")){
				x1 = Double.parseDouble(col0[0].substring(10,12).trim());
				 y1 = Double.parseDouble(col0[1].trim());
				 x2 = Double.parseDouble(col1[0].substring(10,12).trim());
				 y2 = Double.parseDouble(col1[1].trim());
				 x3 = Double.parseDouble(col2[0].substring(10,12).trim());
				 y3 = Double.parseDouble(col2[1].trim());
				
			}
			System.out.println("x1 :"+x1+"   y1:"+y1+"   x2:"+x2+"   y2:"+y2+"  x3:"+x3+"  y3:"+y3);
			a = ((y1-y3)*x2+(x3-x1)*y2)/((x1-x2)*(x1-x3)*(x2-x3));
			b = (y1-y2-a*(x1*x1-x2*x2))/(x1-x2);
			c = y1 - a*x1*x1-b*x1;
			System.out.println("initial:a:"+a+"   b:"+b+"  c:"+c);
			/*a = getRealVaule(a, 2);
			b = getRealVaule(c, 2);
			c = getRealVaule(c, 2);
			System.out.println("a:"+a+"   b:"+b+"  c:"+c);*/
			if(b<0 && c<0)
			returnExp = "y="+a+"*(x^2)"+b+c;
			else if(b<0){
				returnExp = "y="+a+"*(x^2)"+b+"+"+c;
			}
			else if(c<0){
				returnExp = "y="+a+"*(x^2)+"+b+c;
			}else{
				returnExp = "y="+a+"*(x^2)+"+b+"+"+c;
			}

			System.out.println("公式："+returnExp);
	}
		
		String type = request.getSession().getAttribute("passType").toString();
		String pid = request.getSession().getAttribute("passPid").toString();
		List<ExcelsjlsbLc> list=excelsjlsbLcService.getAll(new QueryFilter(request,"excelsjlsbLcItem"));
		ModelAndView mv1 = new ModelAndView("/BusinessCurve/lc/businessCurveEdit.jsp");
		mv1.addObject("returnExp", returnExp).addObject("rtnType",type).addObject("rtnPid",pid).addObject("rtnYearStart",yearstart).addObject("rtnYearEnd",yearend);
		return mv1;
	}
	 public    double getRealVaule(double value,int resLen) {  
	        /*if(resLen==0)  
	            //原理:123.456*10=1234.56+5=1239.56/10=123  
	            //原理:123.556*10=1235.56+5=1240.56/10=124  
	            return Math.round(value*10+5)/10;  
	        double db  = Math.pow(10, resLen);  
	        return Math.round(value*db)/db;  */
		 BigDecimal db = new BigDecimal(value);
		        db = db.setScale(2,BigDecimal.ROUND_HALF_UP);
		       return db.doubleValue();
	    }  
	/**
	 * 删除excel 数据临时表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除excel 数据临时表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			excelsjlsbLcService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除excel 数据临时表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑excel 数据临时表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑excel 数据临时表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		ExcelsjlsbLc excelsjlsbLc=excelsjlsbLcService.getById(id);
		
		return getAutoView().addObject("excelsjlsbLc",excelsjlsbLc)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得excel 数据临时表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看excel 数据临时表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		ExcelsjlsbLc excelsjlsbLc=excelsjlsbLcService.getById(id);
		return getAutoView().addObject("excelsjlsbLc", excelsjlsbLc);
	}
	
}
