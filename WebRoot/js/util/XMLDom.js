var isIe = /msie/i.test(navigator.userAgent);//是否是IE浏览器
var XMLDom =
{
	// 得到xmlDom对象
	getXMLDom:function()
	{
		var axo = null;
		var MS_XML_DOM = [
				"MSXML2.DOMDocument",
				"Microsoft.XMLDOM",
				"MSXML.DOMDocument",
				"MSXML3.DOMDocument"
		];
		if(isIe)
		{
			for( var i = 0;i < 4;i++)
			{
				try
				{
					axo = new ActiveXObject(MS_XML_DOM[i]);
					return axo;
				}
				catch(e)
				{
					return null;
				}
			}
		}
		else
			return document.implementation.createDocument("", "doc", null);
	},
	// 装载一个XMLDom
	loadXML:function(url, async, handle)
	{
		var xmlDom = XMLDom.getXMLDom();
		xmlDom.preserveWhiteSpace = true;//兼容FireFox
		xmlDom.async = (async == true) ? true : false;
		if(async)
		{
			if(isIe)
				xmlDom.onreadystatechange = function()
				{
					if(xmlDom.readyState == 4)
						handle(xmlDom);
				};
			else
				xmlDom.onload = function()
				{
					handle(xmlDom);
				};
		}
		xmlDom.load(url);
		if(!async)
			return xmlDom;
	},
	// 取得XMLDom对象的xml内容
	getXML:function(xmlDom)
	{
		if(isIe)
			return xmlDom.xml;
		else
			return (new xmlSerializer()).serializeToString(xmlDom);
	},
	//得到节点的属性
	getAttribute:function(pNode, pAttribute)
	{
		try
		{
			return pNode.attributes.getNamedItem(pAttribute).nodeValue;
		}
		catch(e)
		{
			return null;
		}
	},
	//根据字符串得到Xml对象
	loadXmlString:function(strXml)
	{
		var xmlDoc;
		try
		//Internet Explorer
		{
			xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
			xmlDoc.async = "false";
			xmlDoc.loadXML(strXml);
		}
		catch(e)
		{
			try
			//Firefox, Mozilla, Opera, etc.
			{
				parser = new DOMParser();
				xmlDoc = parser.parseFromString(strXml, "text/xml");
			}
			catch(e)
			{
				alert(e.message);
			}
		}
		return xmlDoc;
	}
};
