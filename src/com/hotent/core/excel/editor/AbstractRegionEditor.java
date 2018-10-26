package com.hotent.core.excel.editor;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

import com.hotent.core.excel.ExcelContext;
import com.hotent.core.excel.style.Align;
import com.hotent.core.excel.style.BorderStyle;
import com.hotent.core.excel.style.Color;
import com.hotent.core.excel.style.FillPattern;
import com.hotent.core.excel.style.VAlign;

/**
 * 区域编辑器（如行、列、区域）的基类
 * 
 * @author zxh
 * 
 * @param <T>
 */
public abstract class AbstractRegionEditor<T> extends AbstractEditor {

	protected AbstractRegionEditor(ExcelContext context) {
		super(context);
	}

	/**
	 * 新建一个单元格编辑器，包含所有单元格
	 * 
	 * @return
	 */
	abstract protected CellEditor newCellEditor();

	/**
	 * 新建一个单元格编辑器，包含上边单元格
	 * 
	 * @return
	 */
	abstract protected CellEditor newTopCellEditor();

	/**
	 * 新建一个单元格编辑器，包含下边单元格
	 * 
	 * @return
	 */
	abstract protected CellEditor newBottomCellEditor();

	/**
	 * 新建一个单元格编辑器，包含左边单元格
	 * 
	 * @return
	 */
	abstract protected CellEditor newLeftCellEditor();

	/**
	 * 新建一个单元格编辑器，包含右边单元格
	 * 
	 * @return
	 */
	abstract protected CellEditor newRightCellEditor();

	/**
	 * 获取单元格范围，用于合并单元格等操作
	 * 
	 * @return
	 */
	abstract protected CellRangeAddress getCellRange();
	

	/**
	 * 设置外部四条边框样式和颜色
	 * 
	 * @param borderStyle
	 *            样式
	 * @param borderColor
	 *            颜色
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T borderOuter(BorderStyle borderStyle, Color borderColor) {
		this.borderBottom(borderStyle, borderColor);
		this.borderLeft(borderStyle, borderColor);
		this.borderRight(borderStyle, borderColor);
		this.borderTop(borderStyle, borderColor);
		return (T) this;
	}

	/**
	 * 设置全部内外边框样式和颜色
	 * 
	 * @param borderStyle
	 *            样式
	 * @param borderColor
	 *            颜色
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T borderFull(BorderStyle borderStyle, Color borderColor) {
		CellEditor cellEditor = newCellEditor();
		cellEditor.border(borderStyle, borderColor);
		return (T) this;
	}

	/**
	 * 设置外部左边框样式和颜色
	 * 
	 * @param borderStyle
	 *            样式
	 * @param borderColor
	 *            颜色
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T borderLeft(BorderStyle borderStyle, Color borderColor) {
		// 左边框
		CellEditor cellEditorLeft = this.newLeftCellEditor();
		cellEditorLeft.borderLeft(borderStyle, borderColor);
		return (T) this;
	}

	/**
	 * 设置外部右边框样式和颜色
	 * 
	 * @param borderStyle
	 *            样式
	 * @param borderColor
	 *            颜色
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T borderRight(BorderStyle borderStyle, Color borderColor) {
		// 右边框
		CellEditor cellEditorRight = this.newRightCellEditor();
		cellEditorRight.borderRight(borderStyle, borderColor);
		return (T) this;
	}

	/**
	 * 设置外部上边框样式和颜色
	 * 
	 * @param borderStyle
	 *            样式
	 * @param borderColor
	 *            颜色
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T borderTop(BorderStyle borderStyle, Color borderColor) {
		// 上边框
		CellEditor cellEditorTop = this.newTopCellEditor();
		cellEditorTop.borderTop(borderStyle, borderColor);
		return (T) this;
	}

	/**
	 * 设置外部下边框样式和颜色
	 * 
	 * @param borderStyle
	 *            样式
	 * @param borderColor
	 *            颜色
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T borderBottom(BorderStyle borderStyle, Color borderColor) {
		// 下边框
		CellEditor cellEditorBottom = this.newBottomCellEditor();
		cellEditorBottom.borderBottom(borderStyle, borderColor);
		return (T) this;
	}

	/**
	 * 设置字体
	 * 
	 * @param fontEditor
	 *            实现IFontEditor接口
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T font(IFontEditor fontEditor) {
		CellEditor cellEditor = newCellEditor();
		cellEditor.font(fontEditor);
		return (T) this;
	}

	/**
	 * 设置背景色
	 * 
	 * @param bg
	 *            颜色，例如HSSFColor.RED.index
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T bgColor(Color bg) {
		CellEditor cellEditor = newCellEditor();
		cellEditor.bgColor(bg);
		return (T) this;
	}

	/**
	 * 设置背景色，可设置填充的样式
	 * 
	 * @param bg
	 *            颜色，例如HSSFColor.RED.index
	 * @param fillPattern
	 *            填充样式，如HSSFCellStyle.FINE_DOTS。可选值：NO_FILL, SOLID_FOREGROUND,
	 *            FINE_DOTS, ALT_BARS, SPARSE_DOTS, THICK_HORZ_BANDS,
	 *            THICK_VERT_BANDS, THICK_BACKWARD_DIAG, THICK_FORWARD_DIAG,
	 *            BIG_SPOTS, BRICKS, THIN_HORZ_BANDS, THIN_VERT_BANDS,
	 *            THIN_BACKWARD_DIAG, THIN_FORWARD_DIAG, SQUARES, DIAMONDS
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T bgColor(Color bg, FillPattern fillPattern) {
		CellEditor cellEditor = newCellEditor();
		cellEditor.bgColor(bg, fillPattern);
		return (T) this;
	}

	/**
	 * 设置水平对齐方式
	 * 
	 * @param align
	 *            对齐方式，例如HSSFCellStyle.ALIGN_CENTER。可选值：ALIGN_GENERAL,
	 *            ALIGN_LEFT, ALIGN_CENTER, ALIGN_RIGHT, ALIGN_FILL,
	 *            ALIGN_JUSTIFY, ALIGN_CENTER_SELECTION
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T align(Align align) {
		CellEditor cellEditor = newCellEditor();
		cellEditor.align(align);
		return (T) this;
	}

	/**
	 * 设置垂直对齐方式
	 * 
	 * @param align
	 *            对齐方式，例如HSSFCellStyle.VERTICAL_CENTER。可选值：VERTICAL_TOP,
	 *            VERTICAL_CENTER, VERTICAL_BOTTOM, VERTICAL_JUSTIFY
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T vAlign(VAlign align) {
		CellEditor cellEditor = newCellEditor();
		cellEditor.vAlign(align);
		return (T) this;
	}

	/**
	 * 是否自动换行。
	 * 
	 * @param autoWarp
	 *            true自动换行，false不换行
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T warpText(boolean autoWarp) {
		CellEditor cellEditor = newCellEditor();
		cellEditor.warpText(autoWarp);
		return (T) this;
	}

	/**
	 * 合并区间，注意：合并区间可能导致区间内一些单元格的值丢失
	 * 
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T merge() {
		workingSheet.addMergedRegion(getCellRange());
		return (T) this;
	}

	/**
	 * 设置自定义的样式
	 * 
	 * @param style
	 *            样式
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T style(HSSFCellStyle style) {
		CellEditor cellEditor = newCellEditor();
		cellEditor.style(style);
		return (T) this;
	}

	/**
	 * 是否隐藏公式，只有给工作表设置密码后，该设置才生效
	 * 
	 * @param hidden
	 *            true隐藏，false显示
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T hidden(boolean hidden) {
		CellEditor cellEditor = newCellEditor();
		cellEditor.hidden(hidden);
		return (T) this;
	}

	/**
	 * 设置缩进
	 * 
	 * @param indent
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T indent(int indent) {
		CellEditor cellEditor = newCellEditor();
		cellEditor.indent(indent);
		return (T) this;
	}

	/**
	 * 是否锁定，锁定后用户将不能编辑该单元格。只有给工作表设置密码后，该设置才生效
	 * 
	 * @param locked
	 *            true锁定，false解锁
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T lock(boolean locked) {
		CellEditor cellEditor = newCellEditor();
		cellEditor.lock(locked);
		return (T) this;
	}

	/**
	 * 设置文字旋转角度
	 * 
	 * @param rotation
	 *            角度，-90度至90度
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T rotate(int rotation) {
		CellEditor cellEditor = newCellEditor();
		cellEditor.rotate(rotation);
		return (T) this;
	}

	/**
	 * 设置区域内单元格所在列的宽度
	 * 
	 * @param width
	 *            宽，1表示一个字符好宽度的1/256
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T width(int width) {
		CellEditor cellEditor = newTopCellEditor();
		cellEditor.width(width);
		return (T) this;
	}

	/**
	 * 增加区域内单元格所在列的宽度
	 * 
	 * @param width
	 *            要增加的宽度，1表示一个字符好宽度的1/256
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T addWidth(int width) {
		CellEditor cellEditor = newTopCellEditor();
		cellEditor.addWidth(width);
		return (T) this;
	}

	/**
	 * 设置区域内单元格所在行的高度
	 * 
	 * @param height
	 *            高，单位是像素
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T height(float height) {
		CellEditor cellEditor = newLeftCellEditor();
		cellEditor.height(height);
		return (T) this;
	}

	/**
	 * 增加区域单元格所在行的高度
	 * 
	 * @param height
	 *            要增加的高度，单位是像素
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T addHeight(float height) {
		CellEditor cellEditor = newLeftCellEditor();
		cellEditor.addHeight(height);
		return (T) this;
	}

	/**
	 * 设置数据格式
	 * 
	 * @param format
	 *            格式字符串，如0.00%，0.00E+00，#,##0等，详情请查询excel单元格格式
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T dataFormat(String format) {
		CellEditor cellEditor = newLeftCellEditor();
		cellEditor.dataFormat(format);
		return (T) this;
	}

	/**
	 * 获取所有单元格的值
	 * 
	 * @return 包含所有单元格值的数组
	 */
	public Object[] value() {
		CellEditor cellEditor = newCellEditor();
		if (cellEditor.getWorkingCell().size() == 1) {
			return new Object[] { cellEditor.value() };
		} else {
			return (Object[]) cellEditor.value();
		}
	}

	/**
	 * 把所有单元格的值转换成字符串，多个值之间用\t分隔
	 */
	@Override
	public String toString() {
		CellEditor cellEditor = newCellEditor();
		return cellEditor.toString();
	}

	/**
	 * 加粗字体
	 * 
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T bold() {
		CellEditor cellEditor = newCellEditor();
		cellEditor.bold();
		return (T) this;
	}

	/**
	 * 设置字体颜色
	 * 
	 * @param color
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T color(Color color) {
		CellEditor cellEditor = newCellEditor();
		cellEditor.color(color);
		return (T) this;
	}

	/**
	 * 设置斜体
	 * 
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T italic() {
		CellEditor cellEditor = newCellEditor();
		cellEditor.italic();
		return (T) this;
	}

	/**
	 * 添加有效数据
	 * 
	 * @return 返回编辑器，以供链式调用
	 */
	@SuppressWarnings("unchecked")
	public T addValidationData(String[] explicitListValues) {
		CellEditor cellEditor = newCellEditor();
		CellRangeAddress cellRange = getCellRange();
		CellRangeAddressList regions = new CellRangeAddressList(cellRange.getFirstRow(),  cellRange.getLastRow(),cellRange.getFirstColumn(),cellRange.getLastColumn());  
		cellEditor.addValidationData(regions, explicitListValues);
		return (T) this;
	}

}
