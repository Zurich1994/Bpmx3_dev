package com.hotent.core.excel.editor;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.util.CellRangeAddress;

import com.hotent.core.excel.ExcelContext;

/**
 * 行编辑器，可以设置一行的值、样式、数据格式和合并
 * 
 * @author zxh
 * 
 */
public class RowEditor extends AbstractRegionEditor<RowEditor> {
	private HSSFRow row;
	/**开始列*/
	private int startCol = 0;

	public RowEditor(int row, int startCol, ExcelContext context) {
		super(context);
		this.row = this.getRow(row);
		this.startCol = startCol;
	}

	public RowEditor(int row, ExcelContext context) {
		this(row, 0, context);
	}
	

	/**
	 * 设置该行的内容，该方法会覆盖该行已有的内容
	 * 
	 * @param rowData
	 *            内容数组，如果里面有Date类型的元素，则会用默认模式yyyy-MM-dd HH:mm:ss格式化。
	 *            使用Excel.setDefaultDatePattern方法设置默认模式
	 * @return
	 */
	public RowEditor value(Object[] rowData) {
		value(rowData, startCol);
		return this;
	}

	/**
	 * 设置该行的内容，该方法会覆盖该行已有的内容
	 * 
	 * @param rowData
	 *            内容数组，如果里面有Date类型的元素，则会用默认模式yyyy-MM-dd HH:mm:ss格式化。
	 *            使用Excel.setDefaultDatePattern方法设置默认模式
	 * @param startCol
	 *            从指定的列开始写入，从0开始
	 * @return
	 */
	public RowEditor value(Object[] rowData, int startCol) {
		if (startCol < 0) {
			startCol = 0;
		}
		insertData(rowData, row, startCol, true);
		return this;
	}

	/**
	 * 插入一行，原来的内容会自动下移一行
	 * 
	 * @param rowData
	 *            内容数组，如果里面有Date类型的元素，则会用默认模式yyyy-MM-dd HH:mm:ss格式化。
	 *            使用Excel.setDefaultDatePattern方法设置默认模式
	 * @return
	 */
	public RowEditor insert(Object[] rowData) {
		return insert(rowData, startCol);
	}

	/**
	 * 插入一行，原来的内容会自动下移一行
	 * 
	 * @param rowData
	 *            内容数组，如果里面有Date类型的元素，则会用默认模式yyyy-MM-dd HH:mm:ss格式化。
	 *            使用Excel.setDefaultDatePattern方法设置默认模式
	 * @param startCol
	 *            从指定的列开始写入，从0开始
	 * @return
	 */
	public RowEditor insert(Object[] rowData, int startCol) {
		if (startCol < 0) {
			startCol = 0;
		}
		insertData(rowData, row, startCol, false);
		return this;
	}

	/**
	 * 在行末添加内容
	 * 
	 * @param rowData
	 * @return
	 */
	public RowEditor append(Object[] rowData) {
		insertData(rowData, row, row.getLastCellNum(), true);
		return this;
	}

	/**
	 * 设置行高度
	 * 
	 * @param h
	 *            高度，单位像素
	 * @return
	 */
	@Override
	public RowEditor height(float h) {
		row.setHeightInPoints(h);
		return this;
	}

	/**
	 * 获取该行的第col列的单元格
	 * 
	 * @param col
	 *            列，从0开始。可指定多个
	 * @return
	 */
	public CellEditor cell(int col, int... cols) {
		CellEditor cellEditor = new CellEditor(row.getRowNum(), col, ctx);
		for (int c : cols) {
			cellEditor.add(row.getRowNum(), c);
		}
		return cellEditor;
	}

	/**
	 * 转换为POI的对象
	 * 
	 * @return HSSFRow
	 */
	public HSSFRow toHSSFRow() {
		return row;
	}

	/**
	 * 依次设置一行单元格的宽度，如果widths的长度比单元格的数量少，则超出的单元格不设置宽度
	 * 
	 * @param widths
	 * @return
	 */
	public RowEditor width(int[] widths) {
		CellEditor cellEditor = newTopCellEditor();
		cellEditor.width(widths);
		return this;
	}

	/**
	 * 插入数据
	 * 
	 * @param rowData
	 *            待插入的数据
	 * @param row
	 *            行对象
	 * @param startCol
	 *            开始插入的列，从0开始
	 * @param overwrite
	 *            是否覆盖该行数据
	 * @throws Exception
	 */
	private void insertData(Object[] rowData, HSSFRow row, int startCol,
			boolean overwrite) {
		if (!overwrite) {
			workingSheet.shiftRows(row.getRowNum(),
					workingSheet.getLastRowNum(), 1, true, false);
		}
		short i = 0;
		for (Object obj : rowData) {
			CellEditor cellEditor = new CellEditor(row.getRowNum(), startCol
					+ i, ctx);
			cellEditor.value(obj);
			i++;
		}
	}

	@Override
	protected CellEditor newCellEditor() {
		CellEditor cellEditor = new CellEditor(ctx);
		short minColIx = 0;
		short maxColIx = 0;
		minColIx = (short) startCol;// row.getFirstCellNum();
		maxColIx = row.getLastCellNum();
		for (int i = minColIx; i < maxColIx; i++) {
			cellEditor.add(row.getRowNum(), i);
		}
		return cellEditor;
	}

	@Override
	protected CellEditor newBottomCellEditor() {
		return newCellEditor();
	}

	@Override
	protected CellEditor newLeftCellEditor() {
		CellEditor cellEditor = new CellEditor(ctx);
		cellEditor.add(row.getRowNum(), startCol);
		return cellEditor;
	}

	@Override
	protected CellEditor newRightCellEditor() {
		CellEditor cellEditor = new CellEditor(ctx);
		cellEditor.add(row.getRowNum(), row.getLastCellNum());
		return cellEditor;
	}

	@Override
	protected CellEditor newTopCellEditor() {
		return newCellEditor();
	}

	@Override
	protected CellRangeAddress getCellRange() {
		return new CellRangeAddress(row.getRowNum(), row.getRowNum(), startCol,
				row.getLastCellNum() - 1);
	}

	protected HSSFRow getHSSFRow() {
		return row;
	}
}
