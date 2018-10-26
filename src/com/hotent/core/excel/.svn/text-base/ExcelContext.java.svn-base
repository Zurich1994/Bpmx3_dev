package com.hotent.core.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hotent.core.excel.editor.listener.CellValueListener;

/**
 * 存放公共变量，内部使用
 * @author zxh
 *
 */
public final class ExcelContext {
	/**
	 * 缓存style对象
	 */
	private Map<Integer, HSSFCellStyle> styleCache = new HashMap<Integer, HSSFCellStyle>();
	private Map<Integer, HSSFFont> fontCache = new HashMap<Integer, HSSFFont>();
	private Map<HSSFSheet, HSSFPatriarch> patriarchCache = new HashMap<HSSFSheet, HSSFPatriarch>();
	private HSSFWorkbook workBook;
	private HSSFCellStyle tempCellStyle;// 临时的样式
	private HSSFFont tempFont;// 临时的字体
	private Excel excel;
	/**
	 * 处于工作状态的工作表
	 */
	private HSSFSheet workingSheet;
	/**
	 * 默认样式
	 */
	private DefaultExcelStyle defaultStyle;
	/**
	 * 当前选择的工作簿
	 */
	private int workingSheetIndex = 0;
	
	/**
	 * 监听器列表
	 */
	private Map<Integer, List<CellValueListener>> cellValueListener;
	
	protected ExcelContext(Excel excel, HSSFWorkbook workBook){
		this.workBook = workBook;
		short numStyle = workBook.getNumCellStyles();
		for(short i=0; i<numStyle;i++){
			HSSFCellStyle style = workBook.getCellStyleAt(i);
			if(style != tempCellStyle){
				styleCache.put(style.hashCode() - style.getIndex(), style);
			}
		}
		short numFont = workBook.getNumberOfFonts();
		for(short i=0; i<numFont;i++){
			HSSFFont font = workBook.getFontAt(i);
			if(font != tempFont){
				fontCache.put(font.hashCode() - font.getIndex(), font);
			}
		}
	};
	
	public HSSFWorkbook getWorkBook() {
		return workBook;
	}

	public void setWorkBook(HSSFWorkbook workBook) {
		this.workBook = workBook;
	}

	public HSSFCellStyle getTempCellStyle() {
		return tempCellStyle;
	}

	public void setTempCellStyle(HSSFCellStyle tempCellStyle) {
		this.tempCellStyle = tempCellStyle;
	}

	public HSSFFont getTempFont() {
		return tempFont;
	}

	public void setTempFont(HSSFFont tempFont) {
		this.tempFont = tempFont;
	}

	public HSSFSheet getWorkingSheet() {
		return workingSheet;
	}

	public void setWorkingSheet(HSSFSheet workingSheet) {
		this.workingSheet = workingSheet;
		workingSheetIndex = workBook.getSheetIndex(workingSheet);
	}

	/**
	 * 返回Patriarch，每个工作表都有一个Patriarch，Patriarch是所有图形的容器
	 * 
	 * @return
	 */
	public HSSFPatriarch getHSSFPatriarch(HSSFSheet sheet) {
		HSSFPatriarch patr = null;
		try {
			patr = patriarchCache.get(sheet);
			if (patr == null) {
				patr = sheet.createDrawingPatriarch();
				patriarchCache.put(sheet, patr);
			}
		} catch (Exception e) {
			patr = sheet.createDrawingPatriarch();
		}
		return patr;
	}

	public void setDefaultStyle(DefaultExcelStyle defaultStyle) {
		this.defaultStyle = defaultStyle;
	}

	public DefaultExcelStyle getDefaultStyle() {
		return defaultStyle;
	}

	public int getWorkingSheetIndex() {
		return workingSheetIndex;
	}

	public void setStyleCache(Map<Integer, HSSFCellStyle> styleCache) {
		this.styleCache = styleCache;
	}

	public Map<Integer, HSSFCellStyle> getStyleCache() {
		return styleCache;
	}

	public void setFontCache(Map<Integer, HSSFFont> fontCache) {
		this.fontCache = fontCache;
	}

	public Map<Integer, HSSFFont> getFontCache() {
		return fontCache;
	}

	private Map<Integer, List<CellValueListener>> getCellValueListener() {
		if(cellValueListener == null){
			cellValueListener = new HashMap<Integer, List<CellValueListener>>();
		}
		return cellValueListener;
	}
	
	/**
	 * 获取指定表单的监听器
	 * @param sheetIndex
	 * @return
	 */
	public List<CellValueListener> getListenerList(int sheetIndex){
		Map<Integer, List<CellValueListener>> map = getCellValueListener();
		List<CellValueListener> listenerList = map.get(sheetIndex);
		if(listenerList == null){
			listenerList = new ArrayList<CellValueListener>();
			map.put(sheetIndex, listenerList);
		}
		return listenerList;
	}

	public Excel getExcel() {
		return excel;
	}
}
