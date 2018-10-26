package com.hotent.core.fulltext.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hwpf.extractor.WordExtractor;

import com.hotent.core.fulltext.IDocument;





public class WordImpl implements IDocument {

	private String fileName="";
	@Override
	public void setFileName(String fileName) {
		this.fileName=fileName;
	}
	
	@Override
	public String extract() {
		String str = "";
		FileInputStream in = null;
		try {
			in = new FileInputStream(fileName);
			WordExtractor extractor = new WordExtractor(in);
			str = extractor.getText();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {

		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
		}

		return str;
	}

}
