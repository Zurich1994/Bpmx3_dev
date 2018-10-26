package com.hotent.core.excel.editor.listener;

import com.hotent.core.excel.Excel;
import com.hotent.core.excel.editor.CellEditor;

/**
 * 单元格内容设置监听器
 * 
 * @author zxh
 */
public interface CellValueListener {

	/**
	 * 实现该方法，在单元格内容改变时触发<br/>
	 * <b>注意：如果在该方法里调用cell.value(Object)设置单元格的值，不会触发监听器，以避免死循环</b>
	 * 
	 * @param cell
	 *            目标单元格
	 * @param newValue
	 *            新值
	 * @param row
	 *            单元格所在的行
	 * @param col
	 *            单元格所在的列
	 * @param sheet
	 *            单元格所属的sheet
	 * @param excel
	 *            Excel对象
	 */
	public void onValueChange(CellEditor cell, Object newValue, int row,
			int col, Excel excel);
}
