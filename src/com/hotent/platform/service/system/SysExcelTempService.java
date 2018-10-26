package com.hotent.platform.service.system;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.excel.Excel;
import com.hotent.core.excel.editor.IFontEditor;
import com.hotent.core.excel.style.Align;
import com.hotent.core.excel.style.BorderStyle;
import com.hotent.core.excel.style.Color;
import com.hotent.core.excel.style.font.BoldWeight;
import com.hotent.core.excel.style.font.Font;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.SysExcelTempDao;
import com.hotent.platform.dao.system.SysExcelTempDetailDao;
import com.hotent.platform.model.system.SysExcelTemp;
import com.hotent.platform.model.system.SysExcelTempDetail;

/**
 * <pre>
 * 对象功能:Excel模板 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2015-06-13 14:36:35
 * </pre>
 */
@Service
public class SysExcelTempService extends BaseService<SysExcelTemp> {
	@Resource
	private SysExcelTempDao dao;

	@Resource
	private SysExcelTempDetailDao sysExcelTempDetailDao;

	public SysExcelTempService() {
	}

	@Override
	protected IEntityDao<SysExcelTemp, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 根据外键删除子表记录
	 * 
	 * @param id
	 */
	private void delByPk(Long id) {
		sysExcelTempDetailDao.delByMainId(id);
	}

	/**
	 * 删除数据 包含相应子表记录
	 * 
	 * @param lAryId
	 */
	@Override
	public void delByIds(Long[] lAryId) {
		for (Long id : lAryId) {
			delByPk(id);
			dao.delById(id);
		}
	}

	/**
	 * 添加数据
	 * 
	 * @param sysExcelTemp
	 * @throws Exception
	 */
	@Override
	public void add(SysExcelTemp sysExcelTemp) {
		super.add(sysExcelTemp);
		addSubList(sysExcelTemp);
	}

	/**
	 * 更新数据
	 * 
	 * @param sysExcelTemp
	 * @throws Exception
	 */
	@Override
	public void update(SysExcelTemp sysExcelTemp) {
		super.update(sysExcelTemp);
		delByPk(sysExcelTemp.getId());
		addSubList(sysExcelTemp);
	}

	/**
	 * 添加子表记录
	 * 
	 * @param sysExcelTemp
	 * @throws Exception
	 */
	private void addSubList(SysExcelTemp sysExcelTemp) {
		List<SysExcelTempDetail> sysExcelTempDetailList = sysExcelTemp
				.getSysExcelTempDetailList();
		if (BeanUtils.isNotEmpty(sysExcelTempDetailList)) {
			for (SysExcelTempDetail sysExcelTempDetail : sysExcelTempDetailList) {
				sysExcelTempDetail.setTempId(sysExcelTemp.getId());
				sysExcelTempDetail.setId(UniqueIdUtil.genId());
				sysExcelTempDetailDao.add(sysExcelTempDetail);
			}
		}
	}

	/**
	 * 根据外键获得Excel模板明细列表
	 * 
	 * @param id
	 * @return
	 */
	public List<SysExcelTempDetail> getSysExcelTempDetailList(Long id) {
		return sysExcelTempDetailDao.getByMainId(id);
	}

	/**
	 * 保存 Excel模板 信息
	 * 
	 * @param sysExcelTemp
	 */
	public void save(SysExcelTemp sysExcelTemp) {
		Long id = sysExcelTemp.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			sysExcelTemp.setId(id);
			this.add(sysExcelTemp);
		} else {
			this.update(sysExcelTemp);
		}
	}

	/**
	 * 判断别名是否已经存在了
	 * 
	 * @param tempCode
	 * @param id
	 * @return
	 */
	public boolean isTempCodeExist(String tempCode, Long id) {
		return dao.isTempCodeExist(tempCode, id);
	}

	/**
	 * 获取Excel
	 * 
	 * @param excelTemp
	 * @param sysExcelTempDetailList
	 * @param tempData
	 *            [{1:测试数据1,2:测试数据2},{1:测试数据1,2:测试数据2}]
	 * @return
	 * @throws Exception 
	 */
	public HSSFWorkbook getExcel(SysExcelTemp excelTemp,
			List<SysExcelTempDetail> sysExcelTempDetailList, String tempData) throws Exception {
		Excel excel = new Excel();
		
		int titleCols = sysExcelTempDetailList.size();	// 列数
		
		if(titleCols == 0){
			throw new Exception("请设置列！");
		}
		
		// 设置页名
		excel.sheet().sheetName(excelTemp.getTempName());// 重命名当前处于工作状态的表的名称
		
		excel.region(0, 0, 0, titleCols-1).merge(); // 合并第一行的N个单元格
		
		// 设置备注说明，第一行
		excel.cell(0, 0) // 选择第一个单元格
				.value(excelTemp.getTempDes()) // 写入值
				.align(Align.LEFT) // 设置水平对齐方式
				.bgColor(Color.GREEN) // 设置背景色
				.border(BorderStyle.THIN, Color.BLACK)	//设置外边框样式
				.height(excelTemp.getTempDesHeight())// 设置高度
				.fontHeightInPoint(16)
				.warpText(true)		// 自动换行
				.font(new IFontEditor() { // 设置字体
					public void updateFont(Font font) {
						font.boldweight(BoldWeight.BOLD);// 粗体
						font.color(Color.WHITE);// 字体颜色
					}
				});
		
		
		// 设置表头，第二行开始
		for (SysExcelTempDetail detail : sysExcelTempDetailList) { // 表头已经排序过了
			excel.cell(1, detail.getShowIndex()).value(detail.getColumnName()).align(Align.CENTER) // 设置水平对齐方式
					.bgColor(Color.GREY_25_PERCENT) // 设置背景色
					.fontHeightInPoint(14)
					.width(256*detail.getColumnLen())//增加宽度
					.border(BorderStyle.THIN, Color.BLACK)	//设置外边框样式
					.font(new IFontEditor() { // 设置字体
						public void updateFont(Font font) {
							font.boldweight(BoldWeight.BOLD);// 粗体
							font.color(Color.BLACK);// 字体颜色
						}
					});
		}

		// 插入数据，第三行开始
		if (StringUtil.isNotEmpty(tempData)) {
			JSONArray jArray = JSONArray.fromObject(tempData);
			int rows = 2;	// 第三行开始
			for (Object obj : jArray) {	// 有多少行数据
				JSONObject json = JSONObject.fromObject(obj);
				for(int cols = 0; cols < titleCols; cols++){
					excel.cell(rows, cols).value(json.get(cols + "")==null?"":json.get(cols + "")+"")
							.border(BorderStyle.MEDIUM, Color.BLACK)	//设置外边框样式
							.fontHeightInPoint(14)
							.warpText(true)
							.align(Align.LEFT); // 设置水平对齐方式
							
				}
				rows++;
			}
		}
		
		return excel.getWorkBook();
	}
	
	/**
	 * 读取上传的excel文件
	 * @param file
	 * @return	[{1:测试数据1,2:测试数据2},{1:测试数据1,2:测试数据2}]
	 * @throws Exception
	 */
	public List<Map<String,Object>> readExcelContent(MultipartFile file)
			throws Exception {
		if (file == null || file.isEmpty() == true) {
			throw new Exception("文件为空！");
		}
		String fileExt = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf("."));
		if (!fileExt.equalsIgnoreCase(".xls")) {
			throw new Exception("上传文件不是Excel文件类型！");
		}

		//创建相关的文件流对象  
		InputStream is = file.getInputStream();
		
		Workbook wb = null;  
		if(fileExt.equalsIgnoreCase(".xls")){//针对2003版本  
			wb=new HSSFWorkbook(new POIFSFileSystem(is));  
		}else{
			//针对2007版本  
		     wb = new XSSFWorkbook(is);  
		}
		
		// 获得总Sheet数
		Sheet sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		
		if (rowNum < 2) {
			throw new Exception("上传文件没有填写数据！");
		}
		
		Row titleRow = sheet.getRow(1); // 获取第二行，标题行
		int titleRowNum = titleRow.getLastCellNum();
		
		//从第3行开始
		List<Map<String,Object>> listData = new ArrayList<Map<String,Object>>();
		for (int i = 2; i <= rowNum; i++) {
			Map<String,Object> data = new HashMap<String,Object>();
			// 获取第几行
			Row row = sheet.getRow(i);
			for(int j = 0;j < titleRowNum; j++){	
				data.put(j+"", getStringCellValue(row.getCell(j)));
			}
			listData.add(data);
		}
		return listData;
	}
	
	/**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(Cell cell) {
    	if(cell == null) return "";
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
        	//防止数字超过7位数就出现数学计算法
        	DecimalFormat df = new DecimalFormat("#");
            strCell = df.format(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        return strCell;
    }
    
    /**
     * 更新案例数据
     * @param id
     * @param tempDataSample
     */
	public void updateTempDataSample(Long id, String tempDataSample) {
		dao.updateTempDataSample(id,tempDataSample);
	}
	
	/**
	 * 根据别名获取对象
	 * @param tempCode
	 * @return
	 */
	public SysExcelTemp getByTempCode(String tempCode) {
		return dao.getByTempCode(tempCode);
	}
	
	/**
	 * 根据别名获取明细
	 * @param tempCode
	 * @return
	 */
	public List<SysExcelTempDetail> getAllDetailByTempCode(String tempCode) {
		return sysExcelTempDetailDao.getAllByTempCode(tempCode);
	}
	
	

}
