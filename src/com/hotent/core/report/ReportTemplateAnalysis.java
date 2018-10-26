package com.hotent.core.report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class ReportTemplateAnalysis {
	
	public static List<Element> getElementList(String filePath){
		List<Element> elementList = new ArrayList<Element>();
		SAXReader saxReader = new SAXReader();  
		List<String> dsNames = new ArrayList<String>();
		try {
			Document document = saxReader.read(new File(filePath));
			Element root = document.getRootElement();
			elementList = root.elements();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return elementList;
	}
	
	public static List<String> dsNameAnalysis(String filePath){
		SAXReader saxReader = new SAXReader();  
		List<String> dsNames = new ArrayList<String>();
		try {
			Document document = saxReader.read(new File(filePath));
			Element root = document.getRootElement();
			List<Element> elementList = root.elements();
			//System.out.println(root.getName());
			for (Element element : elementList) {
				if(element.getName().contains("TableDataMap")){
					List<Element> eles1 = element.elements();
				      //System.out.println("eles1  :"+eles1);
				      for (Element eles2 : eles1) {
				    	  //System.out.println("eles2  :"+eles2);
					      if(eles2.getName().contains("TableData")){
					    	  List<Element> eles3 = eles2.elements();
					    	  String attr = eles2.attributeValue("name");
					    	  System.out.println(attr);
					    	  dsNames.add(attr);
					      }
					    	 
					  }
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsNames;
	}
	
	
	public static String toDsNameJson(String cptTitle,List<String> dsList){
		JSONObject jo1 = new JSONObject();
		jo1.put("name", cptTitle);
		JSONArray ja = new JSONArray();
		JSONObject jo2 = new JSONObject();
		for(int i=0;i<dsList.size();i++){
			jo2.put("name", dsList.get(i));
			ja.add(jo2);
		}
		jo1.put("children", ja);
		return jo1.toString();
	}
	
	public static List<String> getColumnName(String filePath,String dsName){
		List<String> columnNameList  = new ArrayList<String>();
		List<Element> elementList = getElementList(filePath);
		for(Element element : elementList){
			//System.out.println(element.getName());
			if(element.getName().contains("Report")){
				List<Element> reportElements = element.elements();
				for(Element reportElement : reportElements){
					//System.out.println(reportElement.getName());
					if(reportElement.getName().contains("CellElementList")){
						List<Element> CellElements = reportElement.elements();
						
						for(Element cellElement : CellElements){
							//System.out.println("cellElement:"+cellElement.getName());
							if(cellElement.getName().contains("C")){
								List<Element> cElements = cellElement.elements();
						
								for(Element cElement : cElements){
									//System.out.println("cElement:"+cElement.getName());
									if(cElement.getName().contains("O")){
										List<Element> oElements = cElement.elements();
										for(Element oElement : oElements){
											//System.out.println("oElement:"+oElement.getName());
											if(oElement.getName().contains("Attributes")){
												//System.out.println("columnNameListSize:"+columnNameList.size());
												String columnName = oElement.attributeValue("columnName");
							        			String dName = oElement.attributeValue("dsName");
							        			if(dName.equals(dsName)){
							        				columnNameList.add(columnName);
							        			}
							        			//System.out.println("columnName:"+columnName);
							        			//System.out.println("dName:"+dName);
											}
											//System.out.println("columnNameListSize:"+columnNameList.size());
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return columnNameList;
	}
	

	
	public static void main(String [] args){
		String filePath = "E:\\记录数.cpt";
		String dsName = "ds1";
		//List<String> dsNames = dsNameAnalysis(filePath);
		//System.out.println(dsNames.size());
		//String dsJson = toDsNameJson("��¼��",dsNames);
		//System.out.println(dsJson);
		List<String> columnNames = getColumnName(filePath,dsName);
		System.out.println(columnNames.size());
		
	}
}
