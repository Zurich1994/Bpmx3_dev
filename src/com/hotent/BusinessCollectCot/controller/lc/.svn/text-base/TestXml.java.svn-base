package com.hotent.BusinessCollectCot.controller.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class TestXml {
	
	public static List<NodeSet> nodeList = new ArrayList<NodeSet>();
	
	public static void main(String [] args){
		String xmlStr = "<Sequences><Sequence nodeId="+"\"writepageTask1\"" +" probability="+"\"0.7\"" +"/>" +
				"<Sequence nodeId="+"\"readpageTask1\"" +" probability="+"\"0.3\""+" /></Sequences>";
		System.out.println(xmlStr);
		
	}
	public static List<NodeSet> parseXml(String xmlStr){
		System.out.println("xmlStr:"+xmlStr);
		try {
			Document document = DocumentHelper.parseText(xmlStr);
			Element root = document.getRootElement();
			System.out.println("root"+root.getName()); 
			List<Element> childList = root.elements();
			System.out.println("childList"+childList.size());
			for (Iterator iter = root.elementIterator(); iter.hasNext();)
	        {
	            Element e = (Element) iter.next();
	            String nodeId = e.attributeValue("nodeId");
	            String nodeProbability = e.attributeValue("probability");
	            System.out.println(nodeId);
	            System.out.println(nodeProbability);
	            NodeSet ns = new NodeSet();
	            ns.setNodeId(nodeId);
	            ns.setNodeProbability(nodeProbability);
	            nodeList.add(ns);
	        }
			System.out.println(nodeList.size());
		   
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nodeList;
		
	}
	

}
