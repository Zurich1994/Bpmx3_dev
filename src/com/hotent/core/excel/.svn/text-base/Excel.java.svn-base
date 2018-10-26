package com.hotent.core.excel;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.CellRangeAddress;

import com.hotent.core.excel.editor.CellEditor;
import com.hotent.core.excel.editor.ColumnEditor;
import com.hotent.core.excel.editor.IFontEditor;
import com.hotent.core.excel.editor.RegionEditor;
import com.hotent.core.excel.editor.RowEditor;
import com.hotent.core.excel.editor.SheetEditor;
import com.hotent.core.excel.style.Align;
import com.hotent.core.excel.style.BorderStyle;
import com.hotent.core.excel.style.Color;
import com.hotent.core.excel.style.font.BoldWeight;
import com.hotent.core.excel.style.font.Font;
import com.hotent.core.excel.util.ExcelUtil;


/**
 * <p>提供了更直观易用的常用方法。主要支持以下的功能：
 * <ul>
 * <li>实现类似jQuery的链式调用方式</li>
 * <li>只支持xls格式，可以加载已存在的xls文件作为模板</li>
 * <li>提供了行编辑器、列编辑器和区域编辑器，可以一次操作一行、一列或一个区域的值或样式</li>
 * <li>可设置的样式包括：边框大小、颜色；背景色；字体大小、颜色、粗体、斜体、删除线、斜体等；数据格式；单元格宽高；对齐方式……等</li>
 * <li>设置打印样式、设置密码、按行或按列分组</li>
 * <li>插入图片、批注、公式</li>
 * </ul>
 * 详情请参考Demo例子和注释
 * 
 * </p>
 *  <p>核心类，能获取所有编辑器的实例。</p>
 * <p>主要有五种编辑器：1、行编辑器；2、列编辑器；3、区域编辑器；4、表编辑器；5、单元格编辑器
 * <p>上述五种编辑器只能通过该类的工厂方法获取，而不能自行创建。</p>
 * 
 */
public class Excel {
	
	private ExcelContext ctx;
	
	/**
	 * 新建一个Excel文件
	 */
	public Excel() {
		this(new DefaultExcelStyle());
	}
	
	/**
	 * 新建一个Excel文件，并设置默认样式
	 * @param defaultStyle 默认样式
	 */
	public Excel(DefaultExcelStyle defaultStyle) {
		this(null, defaultStyle);
	}

	/**
	 * 用一个Excel文件作为模板创建一个Excel，如果模板文件不存在则创建一个空Excel文件
	 * 
	 * @param excelPath
	 *            模板文件路径，可以是文件绝对路径如D：/excel.xls，或classpath里的文件，如/resources/excel/template/excel.xls
	 */
	public Excel(String excelPath) {
		this(excelPath, new DefaultExcelStyle());
	}
	
	/**
	 * 用一个Excel文件作为模板创建一个Excel，如果模板文件不存在则创建一个空Excel文件
	 * 
	 * @param excelPath
	 *            模板文件路径，可以是文件绝对路径如D：/excel.xls，或classpath里的文件，如/resources/excel/template/excel.xls
	 * @param defaultStyle 默认样式
	 */
	public Excel(String excelPath, DefaultExcelStyle defaultStyle) {
		HSSFWorkbook workBook;
		HSSFCellStyle tempCellStyle;// 临时的样式
		HSSFFont tempFont;// 临时的字体
		
		if(excelPath == null || excelPath.trim().equals("")){
			workBook = new HSSFWorkbook();
		}else{
			workBook = readExcel(excelPath);
			if (workBook == null) {
				workBook = new HSSFWorkbook();
			}
		}
		ctx = new ExcelContext(this, workBook);
		ctx.setDefaultStyle(defaultStyle);
		//
		setWorkingSheet(0);
		tempCellStyle = workBook.createCellStyle();
		ctx.setTempCellStyle(tempCellStyle);
		tempFont = workBook.createFont();
		ctx.setTempFont(tempFont);
		//设置默认样式
		HSSFCell cell = ExcelUtil.getHSSFCell(ctx.getWorkingSheet(), 0, 0);
		HSSFCellStyle cellStyle = cell.getCellStyle();
		cellStyle.setFillForegroundColor(defaultStyle.getBackgroundColor().getIndex());
		cellStyle.setFillPattern(defaultStyle.getFillPattern().getFillPattern());
		cellStyle.setAlignment(defaultStyle.getAlign().getAlignment());
		cellStyle.setVerticalAlignment(defaultStyle.getVAlign().getAlignment());
		//设置边框样式
		cellStyle.setBorderBottom(defaultStyle.getBorderStyle().getBorderType());
		cellStyle.setBorderLeft(defaultStyle.getBorderStyle().getBorderType());
		cellStyle.setBorderRight(defaultStyle.getBorderStyle().getBorderType());
		cellStyle.setBorderTop(defaultStyle.getBorderStyle().getBorderType());
		cellStyle.setBottomBorderColor(defaultStyle.getBorderColor().getIndex());
		cellStyle.setTopBorderColor(defaultStyle.getBorderColor().getIndex());
		cellStyle.setLeftBorderColor(defaultStyle.getBorderColor().getIndex());
		cellStyle.setRightBorderColor(defaultStyle.getBorderColor().getIndex());
		//默认字体
		HSSFFont font = cellStyle.getFont(workBook);
		font.setFontHeightInPoints(defaultStyle.getFontSize());
		font.setFontName(defaultStyle.getFontName());
		font.setColor(defaultStyle.getFontColor().getIndex());
	}

	
	/**
	 * 读取模板
	 * 
	 * @param templatePath
	 *            模板文件路径
	 * @return HSSFWorkbook 返回Excel工作簿对象
	 */
	private HSSFWorkbook readExcel(String excelPath) {
		HSSFWorkbook result = null;
		POIFSFileSystem fs;
		try {
			//在文件系统上找
			fs = new POIFSFileSystem(new FileInputStream(excelPath));
			result = new HSSFWorkbook(fs);
		} catch (Exception ex) {
			try {
				//classpath绝对路径
				fs = new POIFSFileSystem(getClass().getResourceAsStream(excelPath));
				result = new HSSFWorkbook(fs);
			} catch (Exception e1) {
				try {
					//调用者的相对路径
					InputStream stream = null;
					StackTraceElement[] st = new Throwable().getStackTrace();
					for(int i=2;i<st.length;i++){
						stream = Class.forName(st[i].getClassName()).getResourceAsStream(excelPath);
						if(stream != null){
							fs = new POIFSFileSystem(stream);
							result = new HSSFWorkbook(fs);
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 保存Excel文件到磁盘
	 * 
	 * @param excelPath
	 *            保存路径
	 * @return true保存成功，false失败
	 * @throws FileNotFoundException 
	 */
	public boolean saveExcel(String excelPath) {
		BufferedOutputStream fileOut;
		try {
			fileOut = new BufferedOutputStream(new FileOutputStream(excelPath));
			return saveExcel(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 保存Excel文件，该方法完成操作后会关闭输出流
	 * 
	 * @param excelPath
	 *            保存路径
	 * @return true保存成功，false失败
	 */
	public boolean saveExcel(OutputStream fileOut) {
		boolean result = false;
		try {
			ctx.getWorkBook().write(fileOut);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileOut.flush();
				fileOut.close();
			} catch (Exception e) {
				result = false;
			}
		}
		return result;
	}
	

	/**
	 * 选择第n个工作簿为工作状态，如果不存在则新建一个
	 * 
	 * @param index
	 *            工作簿序号，从0开始
	 * @return 第n个工作表
	 */
	public SheetEditor setWorkingSheet(int index) {
		if (index < 0) {
			index = 0;
		}
		ctx.setWorkingSheet(ExcelUtil.getHSSFSheet(ctx.getWorkBook(), index));
		return this.sheet(index);
	}
	

	/**
	 * 操作一个单元格
	 * 
	 * @param row 行，从0开始
	 * @param col 列，从0开始
	 * @return 单元格编辑器
	 */
	public CellEditor cell(int row, int col) {
		CellEditor cellEditor = new CellEditor(row, col, ctx);
		return cellEditor;
	}

	/**
	 * <p>操作一行的单元格
	 * <p><b>注意：</b>如果要使用该方法设置一行的样式，请确保需要设置样式的单元格都已写入值，包括空值。否则请使用region方法选取区域
	 * @param row 行，从0开始
	 * @return 行编辑器
	 */
	public RowEditor row(int row){
		return new RowEditor(row, ctx);
	}
	
	/**
	 * <p>操作指定行，从第startCol列开始的单元格
	 * <p><b>注意：</b>如果要使用该方法设置一行的样式，请确保需要设置样式的单元格都已写入值，包括空值。否则请使用region方法选取区域
	 * @param row 行，从0开始
	 * @param startCol 只操作指定的列之后的单元格
	 * @return 行编辑器
	 */
	public RowEditor row(int row, int startCol){
		return new RowEditor(row, startCol, ctx);
	}
	
	/**
	 * <p>该方法始终返回最后一个空白行的编辑器，当需要循环插入n行时特别有用</p>
	 * <p>
	 * <blockquote><pre>
	 * for(int i=0;i&lt;data.size();i++){
	 * 		excel.row().value(data.get(i));
	 * }</pre></blockquote>
	 * <p>
	 * <b>注意：</b>如果要使用该方法设置一行的样式，请确保需要设置样式的单元格都已写入值，包括空值。否则请使用region方法选取区域
	 * @return 行编辑器
	 */
	public RowEditor row(){
		int rowNum = ExcelUtil.getLastRowNum(ctx.getWorkingSheet());
		if(!checkEmptyRow(rowNum)){
			rowNum++;
		}
		return new RowEditor(rowNum, ctx);
	}
	
	/**
	 * 检查指定的行是否空行
	 * @param rowNum
	 * @return
	 */
	private boolean checkEmptyRow(int rowNum){
		HSSFRow row = ctx.getWorkingSheet().getRow(rowNum);
		int lastCell = row != null ? row.getLastCellNum() : 2;
		return (lastCell == 1 || lastCell == -1);
	}
	
	/**
	 * 操作一列的单元格<br/>
	 * <b>注意：如果要使用该方法设置一列的样式，请确保需要设置样式的单元格都已写入值，包括空值。否则请使用region方法选取区域</b>
	 * @param col 列，从0开始
	 * @return 列编辑器
	 */
	public ColumnEditor column(int col){
		ColumnEditor columnEditor = new ColumnEditor(col, ctx);
		return columnEditor;
	}
	
	/**
	 * 操作指定列中，从第startRow行<br/>
	 * <b>注意：如果要使用该方法设置一列的样式，请确保需要设置样式的单元格都已写入值，包括空值。否则请使用region方法选取区域</b>
	 * @param col 列，从0开始
	 * @param startRow 只操作指定的行之后的单元格
	 * @return 列编辑器
	 */
	public ColumnEditor column(int col, int startRow){
		ColumnEditor columnEditor = new ColumnEditor(col, startRow, ctx);
		return columnEditor;
	}
	
	/**
	 * 操作一个区域的单元格，如合并、插入图片，调整样式等
	 * @param beginRow 开始行，从0开始
	 * @param beginCol	开始列，从0开始
	 * @param endRow 结束行，从0开始
	 * @param endCol 结束列，从0开始
	 * @return 区域编辑器
	 */
	public RegionEditor region(int beginRow, int beginCol, int endRow, int endCol){
		RegionEditor regionEditor = new RegionEditor(beginRow, beginCol, endRow, endCol, ctx);
		return regionEditor;
	}
	
	/**
	 * 操作一个区域的单元格，如合并、插入图片，调整样式等
	 * @param ref 区域表达式，例如：$C$1:$H$1
	 * @return 区域编辑器
	 */
	public RegionEditor region(String ref){
		RegionEditor regionEditor = new RegionEditor(CellRangeAddress.valueOf(ref), ctx);
		return regionEditor;
	}
	
	/**
	 * 操作一个工作表，如设置表名、页眉页脚、打印格式、加密等
	 * @param index，从0开始
	 * @return
	 */
	public SheetEditor sheet(int index){
		if(index < 0){
			index = 0;
		}
		SheetEditor sheetEditor = new SheetEditor(ExcelUtil.getHSSFSheet(ctx.getWorkBook(), index), ctx);
		return sheetEditor;
	}
	
	/**
	 * 选择处于工作状态的工作表
	 * @return
	 */
	public SheetEditor sheet() {
		return this.sheet(ctx.getWorkingSheetIndex());
	}
	
	/**
	 * 获取POI的工作簿对象
	 * @return
	 */
	public HSSFWorkbook getWorkBook() {
		return ctx.getWorkBook();
	}
	
	/**
	 * 获取处于工作状态的工作表的序号
	 * @return 工作表序号，从0开始
	 */
	public int getWorkingSheetIndex() {
		return ctx.getWorkingSheetIndex();
	}
	
	/**
	 * 建立一个别名，别名为单元格引用、常量或公式提供了一个更简洁明了的引用名称<br/>
	 * 例如：createName("Interest_Rate","'Loan Calculator'!$E$5");
	 * @param name 别名
	 * @param formulaText 引用、常量或公式
	 * @return 新建的别名对象
	 */
	public Name createName(String name, String formulaText){
		Name refersName = ctx.getWorkBook().createName();
		refersName.setNameName(name);
		refersName.setRefersToFormula(formulaText);
		return refersName;
	}
	
	/**
	 * demo 方法
	 * @param args
	 */
	  public static void main(String[] args) {
          Object[] val = new Object[]{"插入一行数据",123,'A',Math.PI,new Date(), "hello"};
          
          Excel excel = new Excel();
          excel.cell(0, 0) //选择第一个单元格
                  .value("Hello World!")//写入值
                  .align(Align.CENTER)//设置水平对齐方式
                  .bgColor(Color.LIGHT_YELLOW)//设置背景色
                  .height(30)//设置高度
                  .font(new IFontEditor() {
                	//设置字体
					@Override
					public void updateFont(Font font) {
					      font.boldweight(BoldWeight.BOLD);//粗体
                          font.color(Color.BROWN);//字体颜色
					}
				});
          excel.region(0, 0, 0, 10).merge();//合并第一行10个单元格
          excel.region("$A$2:$K$2").merge();//也可以这样选取区域
          
          excel.row(2)//选择第3行
                  .value(val)//写入数据
                  .addWidth(2000)//增加宽度
                  .borderOuter(BorderStyle.DASH_DOT_DOT, Color.CORAL);//设置外边框样式
          
          excel.row(4,1)//选择第5行，但忽略第1个单元格，从第2个单元格开始操作
                  .value(val)
                  .borderFull(BorderStyle.DASH_DOT, Color.RED);//设置全部边框
          
          excel.row(6)//选择第7行
                  .value(val, 2)//从第3个单元格开始写入数据
                  .borderTop(BorderStyle.THIN, Color.BLUE);//只设置上边框
          
          excel.column(11)
                  .value(val)//也可以操作一列
                  .align(Align.CENTER)
                  .borderFull(BorderStyle.THICK, Color.CORNFLOWER_BLUE)//设置全部边框
                  .autoWidth();//根据内容长度，自动调整列宽
          
          excel.cell(7, 0).value("=IF(B3=123,\"等于\",\"不等于\")");//写入Excel函数
          excel.cell(7, 1).value(0.578923).dataFormat("0.00%");//设置数据格式
          excel.cell(7, 2).value(0.578923, "0.00%");//也可以这样设置数据格式
          
          //插入一张图片
          excel.region(8, 0, 10, 1).image("http://www.jee-soft.cn/htsite/template/ht/images/index_r1_c3.jpg");
          
          excel.sheet().freeze(1, 0)//冻结第一行
                  .sheetName("这是第一个表");//重命名当前处于工作状态的表的名称
          
          //设置单元格备注
          excel.cell(8, 5).value("这个单元格设置了备注").comment("这是一条备注");
          
          //操作第二个表
          excel.setWorkingSheet(1).sheetName("第二个表");//把第二个表设置为工作状态，并改名           
          excel.row(0).value(val);//第二个表写入数据
          excel.sheet().groupColumn(0, 3);//按列分组
          
          excel.saveExcel("D:/helloworld.xls");
  }
	
}
