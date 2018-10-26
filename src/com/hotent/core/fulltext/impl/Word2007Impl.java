package com.hotent.core.fulltext.impl;

import java.io.IOException;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

import com.hotent.core.fulltext.IDocument;



public class Word2007Impl implements IDocument{

	private String fileName="";
	@Override
	public void setFileName(String fileName) {
		this.fileName=fileName;
	}

	@Override
	public String extract() {
		OPCPackage opcPackage;
		String str="";
		try {
			opcPackage = POIXMLDocument.openPackage(fileName);
			POIXMLTextExtractor extractor;
			try {
				extractor = new XWPFWordExtractor(opcPackage);
				str = extractor.getText();
			} catch (XmlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OpenXML4JException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return str;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}

}
