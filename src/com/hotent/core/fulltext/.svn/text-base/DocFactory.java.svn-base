package com.hotent.core.fulltext;

import com.hotent.core.fulltext.impl.PdfImpl;
import com.hotent.core.fulltext.impl.TextImpl;
import com.hotent.core.fulltext.impl.Word2007Impl;
import com.hotent.core.fulltext.impl.WordImpl;

public class DocFactory {
	public IDocument getDoc(String fileName)
	{
		IDocument doc=null;
		fileName=	fileName.toLowerCase();
		if(fileName.endsWith("doc") ){
			doc=new WordImpl();
		}
		else if( fileName.endsWith("docx")){
			doc=new Word2007Impl();
		}
		else if(fileName.endsWith("txt")){
			doc=new TextImpl();
		}
		else if(fileName.endsWith("pdf")){
			doc=new PdfImpl();
		}
		if(doc!=null){
			doc.setFileName(fileName);
		}
		return doc;
	}
}
