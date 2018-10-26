package com.hotent.core.excel.editor;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.util.CellRangeAddress;

import com.hotent.core.excel.ExcelContext;
import com.hotent.core.excel.util.ExcelUtil;

/**
 * 列编辑器，可以设置一列的值、样式、数据格式和合并
 * 
 * @author zxh
 * 
 */
public class ColumnEditor extends AbstractRegionEditor<ColumnEditor> {

	private int col = 0;
	private int startRow = 0;

	public ColumnEditor(int col, int startRow, ExcelContext context) {
		super(context);
		this.col = col;
		this.startRow = startRow;
	}

	public ColumnEditor(int col, ExcelContext context) {
		this(col, 0, context);
	}

	/**
	 * 设置该行的内容，该方法会覆盖该行已有的内容
	 * 
	 * @param rowData
	 *            内容数组，如果里面有Date类型的元素，则会用默认模式yyyy-MM-dd HH:mm:ss格式化。
	 *            使用Excel.setDefaultDatePattern方法设置默认模式
	 * @return
	 */
	public ColumnEditor value(Object[] rowData) {
		value(rowData, startRow);
		return this;
	}

	/**
	 * 设置该行的内容，该方法会覆盖该行已有的内容
	 * 
	 * @param rowData
	 *            内容数组，如果里面有Date类型的元素，则会用默认模式yyyy/MM/dd HH:mm:ss格式化。
	 *            使用Excel.setDefaultDatePattern方法设置默认模式
	 * @param startCol
	 *            从指定的列开始写入，从0开始
	 * @return
	 */
	public ColumnEditor value(Object[] rowData, int startRow) {
		if (startRow < 0) {
			startRow = 0;
		}
		insertData(rowData, col, startRow);
		return this;
	}

	/**
	 * 根据内容自动设置列宽度。自动计算宽度性能比较低，因此建议在操作完数据后调用一次
	 */
	public ColumnEditor autoWidth() {
		workingSheet.autoSizeColumn((short) col, false);
		workingSheet.setColumnWidth(col,
				workingSheet.getColumnWidth(col) + 1000);
		return this;
	}

	/**
	 * 依次设置一行单元格的高度，如果heights的长度比单元格的数量少，则超出的单元格不设置高度
	 * 
	 * @param heights
	 * @return
	 */
	public ColumnEditor height(float[] heights) {
		CellEditor cellEditorLeft = this.newLeftCellEditor();
		cellEditorLeft.height(heights);
		return this;
	}

	/**
	 * 获取该列的第row行的单元格
	 * 
	 * @param row
	 *            列，从0开始。可指定多个
	 * @return
	 */
	public CellEditor cell(int row, int... rows) {
		CellEditor cellEditor = new CellEditor(row, col, ctx);
		for (int r : rows) {
			cellEditor.add(r, col);
		}
		return cellEditor;
	}

	/**
	 * 插入数据
	 * 
	 * @param rowData
	 *            待插入的数据
	 * @param col
	 *            列序号，从0开始
	 * @param startRow
	 *            开始插入的列，从0开始
	 * @throws Exception
	 */
	private void insertData(Object[] rowData, int col, int startRow) {
		short i = 0;
		for (Object obj : rowData) {
			CellEditor cellEditor = new CellEditor(startRow + i, col, ctx);
			cellEditor.value(obj);
			i++;
		}
	}

	@Override
	protected CellEditor newBottomCellEditor() {
		int lastRowNum = ExcelUtil.getLastRowNum(workingSheet);
		CellEditor cellEditor = new CellEditor(ctx);
		cellEditor.add(lastRowNum, col);
		return cellEditor;
	}

	@Override
	protected CellEditor newCellEditor() {
		CellEditor cellEditor = new CellEditor(ctx);
		int lastRowNum = ExcelUtil.getLastRowNum(workingSheet);
		int firstRowNum = startRow;// workingSheet.getFirstRowNum();
		for (int i = firstRowNum; i <= lastRowNum; i++) {
			HSSFRow row = getRow(i);
			cellEditor.add(row.getRowNum(), col);
		}
		return cellEditor;
	}

	@Override
	protected CellEditor newLeftCellEditor() {
		return newCellEditor();
	}

	@Override
	protected CellEditor newRightCellEditor() {
		return newCellEditor();
	}

	@Override
	protected CellEditor newTopCellEditor() {
		int firstRowNum = startRow;// workingSheet.getFirstRowNum();
		CellEditor cellEditor = new CellEditor(ctx);
		cellEditor.add(firstRowNum, col);
		return cellEditor;
	}

	@Override
	protected CellRangeAddress getCellRange() {
		int firstRowNum = startRow;// workingSheet.getFirstRowNum();
		int lastRowNum = ExcelUtil.getLastRowNum(workingSheet);
		return new CellRangeAddress(firstRowNum, lastRowNum, col, col);
	}

	protected int getCol() {
		return col;
	}
}
