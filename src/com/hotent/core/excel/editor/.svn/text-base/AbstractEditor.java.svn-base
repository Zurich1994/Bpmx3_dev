package com.hotent.core.excel.editor;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hotent.core.excel.ExcelContext;
import com.hotent.core.excel.util.ExcelUtil;

/**
 * 编辑器的基类
 * @author zxh
 *
 */
public abstract class AbstractEditor {
	protected HSSFWorkbook workBook;
	protected HSSFCellStyle tempCellStyle;// 临时的样式
	protected HSSFFont tempFont;// 临时的字体
	protected HSSFSheet workingSheet;
	protected ExcelContext ctx;
	protected AbstractEditor(ExcelContext context){
		this.workBook = context.getWorkBook();
		this.workingSheet = context.getWorkingSheet();
		this.tempFont = context.getTempFont();
		this.tempCellStyle = context.getTempCellStyle();
		this.ctx = context;
	}
	
	/**
	 * 获取行对象
	 * 
	 * @param row
	 *            行，从0开始
	 * @return 指定行的对象
	 */
	protected HSSFRow getRow(int row) {
		return ExcelUtil.getHSSFRow(this.workingSheet, row);
	}
	
	/**
	 * 获取单元格对象
	 * 
	 * @param row
	 *            行，从0开始
	 * @param col
	 *            列，从0开始
	 * @return row行col列的单元格对象
	 */
	protected HSSFCell getCell(int row, int col) {
		return ExcelUtil.getHSSFCell(this.workingSheet, row, col);
	}
	
	/**
	 * 获取单元格对象
	 * @param row 行对象
	 * @param col 列，从0开始
	 * @return 在行对象中获取第col列的单元格对象
	 */
	protected HSSFCell getCell(HSSFRow row, int col) {
		return ExcelUtil.getHSSFCell(row, col);		
	}
}
