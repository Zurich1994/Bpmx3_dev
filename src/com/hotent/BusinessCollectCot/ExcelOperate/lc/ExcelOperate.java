/*package com.hotent.BusinessCollectCot.ExcelOperate.lc;

import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Cell;
import jxl.Workbook;

public class ExcelOperate {
	public static void maim(String [] args){
		try{
			String path = "c:\\发生量计算.xlsx";
			//写入到FileInputStream
			InputStream is = new FileInputStream(path);
			//得到工作薄WorkBook
			jxl.Workbook wb = Workbook.getWorkbook(is);
			//得到工作薄中的一个工作表
			jxl.Sheet st = wb.getSheet(0);
			Cell cell = st.getCell(0,0);
			
			String content = cell.getContents();
			System.out.println(content);
			wb.close();
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
*/