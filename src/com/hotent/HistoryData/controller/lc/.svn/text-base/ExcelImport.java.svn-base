package com.hotent.HistoryData.controller.lc;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class ExcelImport {
	Map<String,String> map = new HashMap<String,String>();
	List<String> list = new ArrayList<String>();
	public HashMap<String,String> excelOperateToMap(String path){
		//path = "e:\\����������.xls";
		System.out.println(path);
		File file = new File(path);
		try {
			InputStream is = new FileInputStream(file);
			jxl.Workbook wb = Workbook.getWorkbook(is);
			jxl.Sheet st = wb.getSheet(0);
			int row = st.getRows();
			//System.out.println("row:"+row);
			int col = st.getColumns();
			//System.out.println("col:"+col);
			for(int r=1;r<row;r++){
				String[] strs = new String[10];
				for(int c=0;c<col;c++){
					Cell cell = st.getCell(c, r);
					strs[c] = cell.getContents();
					System.out.println(strs[c]);
					
				}
				list.add(strs[0]);
				map.put(strs[0], strs[1]);
				//System.out.println("-----------------------");
			}
			
			wb.close();
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return (HashMap<String, String>) map;
	}
	
	public ArrayList<String> excelOperateToList(String path){
		//path = "e:\\����������.xls";
//		System.out.println(path);
		File file = new File(path);
		try {
			InputStream is = new FileInputStream(file);
			jxl.Workbook wb = Workbook.getWorkbook(is);
			jxl.Sheet st = wb.getSheet(0);
			int row = st.getRows();
			//System.out.println("row:"+row);
			int col = st.getColumns();
			//System.out.println("col:"+col);
			for(int r=1;r<row;r++){
				String[] strs = new String[10];
				for(int c=0;c<col;c++){
					Cell cell = st.getCell(c, r);
					strs[c] = cell.getContents();
					System.out.println(strs[c]);
					
				}
				list.add(strs[0]);
				
				//System.out.println("-----------------------");
			}
			
			wb.close();
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return (ArrayList<String>) list;
	}
	
	public static void main(String [] args){
		HashMap<String,String> map = 	new ExcelImport().excelOperateToMap("e:\\����������.xls");
		ArrayList<String> list = new ExcelImport().excelOperateToList("e:\\����������.xls");
		System.out.println(map.size());
		for(int i=0;i<list.size();i++){
			String time = list.get(i);
			System.out.println(time);
			String count = map.get(time);
			System.out.println(count);
		}
		
	}
}
