package com.hotent.core.excel.editor;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.util.CellRangeAddressList;

import com.hotent.core.excel.ExcelContext;
import com.hotent.core.excel.editor.listener.CellValueListener;
import com.hotent.core.excel.util.ExcelUtil;

/**
 * 表单编辑器，可设置表单的页眉、页脚、打印格式、表名、获取总行数等
 * 
 * @author zxh
 */
public class SheetEditor extends AbstractEditor {

	private HSSFSheet sheet;
	private int sheetIndex;

	public SheetEditor(HSSFSheet sheet, ExcelContext context) {
		super(context);
		this.sheet = sheet;
		sheetIndex = workBook.getSheetIndex(this.sheet);
	}

	/**
	 * 设置页眉
	 * 
	 * @param left
	 * @param center
	 * @param right
	 * @return
	 */
	public SheetEditor header(String left, String center, String right) {
		HSSFHeader header = sheet.getHeader();
		header.setLeft(left == null ? "" : left);
		header.setCenter(center == null ? "" : center);
		header.setRight(right == null ? "" : right);
		return this;
	}

	/**
	 * 设置页脚
	 * 
	 * @param left
	 * @param center
	 * @param right
	 * @return
	 */
	public SheetEditor footer(String left, String center, String right) {
		HSSFFooter footer = sheet.getFooter();
		footer.setLeft(left == null ? "" : left);
		footer.setCenter(center == null ? "" : center);
		footer.setRight(right == null ? "" : right);
		return this;
	}

	/**
	 * 设置工作表名，如果新设置的表名已存在，则会抛出异常。 该方法相当于sheetName(String,false)
	 * 
	 * @param name
	 *            表名
	 * @return
	 */
	public SheetEditor sheetName(final String name) {
		sheetName(name, false);
		return this;
	}

	/**
	 * 设置工作表名，当auto设置为true时该方法会自动处理重名的异常
	 * 
	 * @param name
	 *            表名
	 * @param autoRename
	 *            如果该参数为true，则当表重名时该方法会自动在name后面加下横线，避免重名
	 * @return
	 */
	public SheetEditor sheetName(final String name, boolean autoRename) {
		if (autoRename) {
			String newName = new String(name);
			HSSFSheet sheet = workBook.getSheet(name);
			while (sheet != null) {
				newName += "_";
				sheet = workBook.getSheet(newName);
			}
			workBook.setSheetName(sheetIndex, newName);
		} else {
			workBook.setSheetName(sheetIndex, name);
		}
		return this;
	}

	/**
	 * 把该表设置为活动状态，用Excel打开后首先显示该表
	 * 
	 * @return
	 */
	public SheetEditor active() {
		workBook.setActiveSheet(sheetIndex);
		return this;
	}

	/**
	 * 冻结行和列
	 * 
	 * @param row
	 *            从指定的行开始冻结，如果传入0则不冻结任何行
	 * @param col
	 *            从指定的列开始冻结，如果传入0则不冻结任何列
	 * @return
	 */
	public SheetEditor freeze(int row, int col) {
		if (row < 0) {
			row = 0;
		}
		if (col < 0) {
			col = 0;
		}
		sheet.createFreezePane(col, row, col, row);
		return this;
	}

	/**
	 * 获取工作表的行数
	 * 
	 * @return 表行数
	 */
	public int getLastRowNum() {
		return ExcelUtil.getLastRowNum(sheet);
	}

	/**
	 * 是否显示表格线
	 * 
	 * @param show
	 * @return
	 */
	public SheetEditor displayGridlines(boolean show) {
		sheet.setDisplayGridlines(show);
		return this;
	}

	/**
	 * 是否打印表格线
	 * 
	 * @param newPrintGridlines
	 * @return
	 */
	public SheetEditor printGridlines(boolean newPrintGridlines) {
		sheet.setPrintGridlines(newPrintGridlines);
		return this;
	}

	/**
	 * 设置是否适合页面大小
	 * 
	 * @param isFit
	 * @return
	 */
	public SheetEditor fitToPage(boolean isFit) {
		sheet.setFitToPage(isFit);
		return this;
	}

	/**
	 * 设置打印时内容是否水平居中
	 * 
	 * @param isCenter
	 * @return
	 */
	public SheetEditor horizontallyCenter(boolean isCenter) {
		sheet.setHorizontallyCenter(isCenter);
		return this;
	}

	/**
	 * 保护工作表
	 * 
	 * @param pw
	 *            密码
	 * @return
	 */
	public SheetEditor password(String pw) {
		sheet.protectSheet(pw);
		return this;
	}

	/**
	 * 详细设置打印属性
	 * 
	 * @param printSetup
	 * @return
	 */
	public SheetEditor printSetup(IPrintSetup printSetup) {
		printSetup.setup(sheet.getPrintSetup());
		return this;
	}

	/**
	 * 只有设置为true，printSetup中的setFitHeight和setFitWidth才会生效
	 * 
	 * @param b
	 * @return
	 */
	public SheetEditor autobreaks(boolean b) {
		sheet.setAutobreaks(b);
		return this;
	}

	/**
	 * 添加单元格监听器
	 * 
	 * @param listener
	 *            监听器，在单元格的值改变时触发
	 */
	public SheetEditor addCellValueListener(CellValueListener listener) {
		ctx.getListenerList(sheetIndex).add(listener);
		return this;
	}

	/**
	 * 移除单元格监听器
	 * 
	 * @param listener
	 *            监听器，在单元格的值改变时触发
	 */
	public SheetEditor removeCellValueListener(CellValueListener listener) {
		ctx.getListenerList(sheetIndex).remove(listener);
		return this;
	}

	/**
	 * 转换为POI的对象
	 * 
	 * @return HSSFSheet
	 */
	public HSSFSheet toHSSFSheet() {
		return sheet;
	}

	/**
	 * 获取表的序号
	 * 
	 * @return 序号，从0开始
	 */
	public int getSheetIndex() {
		return sheetIndex;
	}

	/**
	 * 按行分组
	 * 
	 * @param fromRow
	 *            开始行，从0开始
	 * @param toRow
	 *            结束行，从0开始
	 * @return
	 */
	public SheetEditor groupRow(int fromRow, int toRow) {
		sheet.groupRow(fromRow, toRow);
		return this;
	}

	/**
	 * 按列分组
	 * 
	 * @param fromColumn
	 *            开始列
	 * @param toColumn
	 *            结束列
	 * @return
	 */
	public SheetEditor groupColumn(int fromColumn, int toColumn) {
		sheet.groupColumn(fromColumn, toColumn);
		return this;
	}

	/**
	 * 设置默认的单元格
	 * 
	 * @param column
	 * @param format
	 */
	public SheetEditor setDefaultColumnStyle(int column, String format) {
		CellStyle cellStyle = workBook.createCellStyle();
		DataFormat dataFormat = workBook.createDataFormat();
		cellStyle.setDataFormat(dataFormat.getFormat(format));
		sheet.setDefaultColumnStyle(column, cellStyle);
		return this;
	}
	public SheetEditor addValidationData(int firstRow,int column,  String[] explicitListValues) {
		this.addValidationData(firstRow, 65535, column, column, explicitListValues);
		return this;
	}
	public SheetEditor addValidationData(int firstRow,int lastRow,
			int firstColumn, int lastColumn, String[] explicitListValues) {
		// 生成下拉框内容
		DVConstraint constraint = DVConstraint
				.createExplicitListConstraint(explicitListValues);
		CellRangeAddressList regions = new CellRangeAddressList(firstRow,
				lastRow, firstColumn, lastColumn);

		HSSFDataValidation validation = new HSSFDataValidation(regions,
				constraint);
		// 是否出错警告
		validation.setShowErrorBox(true);
		// 对sheet页生效
		sheet.addValidationData(validation);
		return this;
	}
}
