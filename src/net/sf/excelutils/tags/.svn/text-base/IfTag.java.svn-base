/*
 * Copyright 2003-2005 try2it.com.
 * Created on 2005-7-7
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package net.sf.excelutils.tags;

import groovy.lang.GroovyShell;
import net.sf.excelutils.ExcelParser;
import net.sf.excelutils.WorkbookUtils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.hotent.core.engine.GroovyBinding;


/**
 * <p>
 * <b>IfTag</b> is a class which parse #if #else #end
 * </p>
 * 
 * @author rainsoft
 * @version $Revision: 1.9 $ $Date: 2005/11/04 08:11:28 $
 */
public class IfTag implements ITag {
	public static final String KEY_IF = "#if";

	public static final String KEY_END = "#end";

	private GroovyBinding binding = new GroovyBinding();
	
	public int[] parseTag(Object context, HSSFSheet sheet, HSSFRow curRow,
			HSSFCell curCell) {
		int ifstart = curRow.getRowNum();
		int ifend = -1;
		int ifCount = 0;
		String ifstr = "";
		boolean bFind = false;
		for (int rownum = ifstart; rownum <= sheet.getLastRowNum(); rownum++) {
			HSSFRow row = sheet.getRow(rownum);
			if (null == row)
				continue;
			int lastCellNum = WorkbookUtils
					.getLastCellNum(row.getLastCellNum());
			for (int colnum = row.getFirstCellNum(); colnum <= lastCellNum; colnum++) {
				HSSFCell cell = row.getCell(colnum);
				if (null == cell)
					continue;
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					String cellstr = cell.getStringCellValue();

					// get the tag instance for the cellstr
					ITag tag = ExcelParser.getTagClass(cellstr);

					if (null != tag) {
						if (tag.hasEndTag()) {
							if (0 == ifCount) {
								ifstart = rownum;
								ifstr = cellstr;
							}
							ifCount++;
							break;
						}
					}
					if (cellstr.startsWith(KEY_END)) {
						ifend = rownum;
						ifCount--;
						if (ifstart >= 0 && ifend >= 0 && ifend > ifstart
								&& ifCount == 0) {
							bFind = true;
						}
						break;
					}
				}
			}
			if (bFind)
				break;
		}

		if (!bFind)
			return new int[] { 0, 0, 1 };

		// test if condition
		boolean bResult = false;
		// remove #if tag and get condition expression
		String expr = ifstr.trim().substring(KEY_IF.length()).trim();

		// parse the condition expression
		expr = (String) ExcelParser.parseStr(context, expr, true);
		// use GroovyShell to evaluate expression value	
		GroovyShell shell = new GroovyShell(binding);
		try {
			Object v =  shell.evaluate(expr);
			bResult = ((Boolean) v).booleanValue();
		} catch (Exception e) {
			bResult = false;
		}

		if (bResult) { // if condition is true
			// remove #if tag and #end tag only
			sheet.removeRow(WorkbookUtils.getRow(ifstart, sheet));
			sheet.removeRow(WorkbookUtils.getRow(ifend, sheet));
			// remove merged region in ifstart & ifend
			for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
				CellRangeAddress r = sheet.getMergedRegion(i);
				if (r.getFirstRow() == ifstart && r.getLastRow() == ifstart
						|| r.getFirstRow() == ifend && r.getLastRow() == ifend) {
					sheet.removeMergedRegion(i);
					// we have to back up now since we removed one
					i = i - 1;
				}
			}
			sheet.shiftRows(ifend + 1, sheet.getLastRowNum(), -1, true, true);
			sheet.shiftRows(ifstart + 1, sheet.getLastRowNum(), -1, true, true);
			return new int[] { 1, -2, 1 };
		} else { // if condition is false
			// remove #if #end block
			for (int rownum = ifstart; rownum <= ifend; rownum++) {
				sheet.removeRow(WorkbookUtils.getRow(rownum, sheet));
			}
			// remove merged region in ifstart & ifend
			for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
				CellRangeAddress r = sheet.getMergedRegion(i);
				if (r.getFirstRow() >= ifstart && r.getLastRow() <= ifend) {
					sheet.removeMergedRegion(i);
					// we have to back up now since we removed one
					i = i - 1;
				}
			}
			sheet.shiftRows(ifend + 1, sheet.getLastRowNum(),
					-(ifend - ifstart + 1), true, true);
			return new int[] { ExcelParser.getSkipNum(ifstart, ifend),
					ExcelParser.getShiftNum(ifend, ifstart), 1 };
		}
	}

	public String getTagName() {
		return KEY_IF;
	}

	public boolean hasEndTag() {
		return true;
	}

}
