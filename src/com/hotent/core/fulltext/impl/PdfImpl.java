package com.hotent.core.fulltext.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.hotent.core.fulltext.IDocument;


public class PdfImpl implements IDocument {

	private String fileName = "";

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String extract() {
		String result = null;
		FileInputStream is = null;
		PDDocument document = null;
		try {
			is = new FileInputStream(fileName);
			PDFParser parser = new PDFParser(is);
			parser.parse();
			document = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			result = stripper.getText(document);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (document != null) {
				try {
					document.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
