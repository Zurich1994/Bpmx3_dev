package com.hotent.excelk.controller.excelk;


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


public class ExcelImportn {
	Map<String,String> map = new HashMap<String,String>();
	List<String> list = new ArrayList<String>();
	public HashMap<String,String> excelOperateToMap(String path){
		//path = "e:\\����������.xls";
		File file = new File(path);
		try {
			InputStream is = new FileInputStream(file);
			jxl.Workbook wb = Workbook.getWorkbook(is);
			jxl.Sheet st = wb.getSheet(0);
			int row = st.getRows();
			int col = st.getColumns();
			for(int r=0;r<row;r++){
				String[] strs = new String[10];
				for(int c=0;c<col;c++){
					Cell cell =st.getCell(c, r);
					strs[c] = cell.getContents();
					
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
		File file = new File(path);
		try {
			InputStream is = new FileInputStream(file);
			jxl.Workbook wb = Workbook.getWorkbook(is);
			jxl.Sheet st = wb.getSheet(0);
			int row = st.getRows();
			int col = st.getColumns();
			for(int r=0;r<row;r++){
				String[] strs = new String[10];
				for(int c=0;c<col;c++){
					Cell cell =st.getCell(c, r);
					strs[c] = cell.getContents();
					
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
		HashMap<String,String> map = 	new ExcelImportn().excelOperateToMap("e:\\����������.xls");
		ArrayList<String> list = new ExcelImportn().excelOperateToList("e:\\����������.xls");
		System.out.println(map.size());
		for(int i=0;i<list.size();i++){
			String time = list.get(i);
			System.out.println(time);
			String count = map.get(time);
			System.out.println(count);
		}
		
	}
}
