<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,com.hotent.core.util.AppUtil" %>
<%@page import="com.hotent.platform.model.system.SysObjLog"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String basePath = request.getContextPath();
	String uId = request.getAttribute("uId").toString();
	// 在flex中保存，发布流程的请求地址，isAjaxRequest参数，解决session失效下，【保存】操作会弹出登录页代码提示，具体可看PermissionFilter.java

	String flowKeyGetUrl = basePath+"/platform/bpm/bpmDefinition/getFlowKey.ht";
	String flowListGetUrl = basePath+"/platform/bpm/bpmDefinition/getFlowListByTypeId.ht";
    String typeName=request.getAttribute("typeName").toString();
	
	// 加载数据操作 
	// 获取流程定义id
	String defId = "";
	String loadDateUrl = ""; // flex中获取加载数据的地址
	String markid="";//马尔科夫链ID
	String runId="";
	Object markidtemp=request.getAttribute("markid");
	Object defIdObj = request.getAttribute("defId"); 
	Object runIdObj = request.getAttribute("runId"); 
	if(defIdObj != null && !defIdObj.toString().equals("0")){
		defId = defIdObj.toString(); // 流程id
		loadDateUrl = basePath + "/platform/bpm/bpmDefinition/flexGet.ht?temp=g&defId=" + defId+"&typeName="+typeName;
	}
	if(markidtemp!=null){
		markid=markidtemp.toString();
	}
	if(runIdObj!=null){
		runId=runIdObj.toString();
	}
	String postUrl = basePath + "/platform/bpm/bpmDefinition/flexDefSave.ht?isAjaxRequest=true&defId=" + defId;	
	String postUrl90 = basePath + "/platform/bpm/bpmDefinition/flexDefSave90.ht?isAjaxRequest=true&defId=" + defId;	
	String postUrl2 = basePath + "/platform/bpm/bpmDefinition/flexDefWritePage.ht?isAjaxRequest=true&defId=" + defId;	
	String postUrl10= basePath + "/platform/bpm/bpmDefinition/flexRedirect.ht?isAjaxRequest=true";
   
    String postUrl110= basePath + "/platform/bpm/processRun/flex110.ht?isAjaxRequest=true&defId="+defId+"&runId="+runId+"&typeName="+typeName;
     //保存路径
    String postUrl112 = basePath +  "/platform/bpm/bpmDefinition/flex112.ht?isAjaxRequest=true&defId="+defId+"&markid="+markid;	
    %>

    

  <head>
  	<title>Flex展现
  	<c:choose>
  			<c:when test="${not empty bpmDefinition}">
  				${bpmDefinition.subject} --(版本${bpmDefinition.versionNo})
  			</c:when>
  			<c:otherwise>
  				${subject}
  			</c:otherwise>
  		</c:choose>
  	</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <style type="text/css" media="screen"> 
	html, body	{ height:100%; }
	body { margin:0; padding:0; overflow:auto; text-align:center; 
	       background-color: #ffffff; }   
	#flashContent { display:none; }
  </style>
       <script type="text/javascript" src="<%=basePath%>/media/swf/bpm/swfobject.js"></script>
       <script type="text/javascript"><!--
	       var swfVersionStr = "10.0.0";
	       var xiSwfUrlStr = "<%=basePath%>/media/swf/bpm/playerProductInstall.swf";
	       var flashvars = {};
	       var params = {};
	       params.quality = "high";
	       params.bgcolor = "#ffffff";
	       params.allowscriptaccess = "sameDomain";
	       params.allowfullscreen = "true";
	       params.FlashVars="flowKeyGetUrl=<%=flowKeyGetUrl%>&flowListGetUrl=<%=flowListGetUrl%>";
	       var attributes = {};
	       attributes.id = "bpmeditor";
	       attributes.name = "bpmeditor";
	       attributes.align = "middle";
	       swfobject.embedSWF("<%=basePath%>/media/swf/bpm/bpmeditor.swf", "flashContent", 
	           "100%", "100%", swfVersionStr, xiSwfUrlStr, flashvars, params, attributes);
		swfobject.createCSS("#flashContent", "display:block;text-align:left;");
		
		
	function flexT(){
		//alert("qwert234");
		var test=document.getElementById('typeName').value;
		//alert(test);
	
		return test;
		
		}

	
	//供flex方向调用,获取提交数据的地址
	function getCtxPath(){
     alert("getCtxPath");		
		return document.getElementById("postUrl").value;
	}
	//ys
	function getCtxPath90(){
		alert("getCtxPath90");		
		return document.getElementById("postUrl90").value;
	}
	

//供flex方向调用,获取提交数据的地址,查看原子操作读页面
	function getCtxPath9(){
//alert("getCtxPath9:");
		return document.getElementById("postUrl2").value;
	}
	
	//供flex方向调用,获取提交数据的地址,设置下一跳转页面
	
		function getCtxPath10(){
alert("getCtxPath10");
		return document.getElementById("postUrl10").value;
	}
	
	//供flex方向调用,动态展现
	function getCtxPath13  (){
    //alert("getCtxPath13:");
		return document.getElementById("postUrl3").value;
	}


	// 供flex方向调用,关闭flex页面
	function closeFlexWindow(){
		window.close();
		opener.location.reload();
	}
	
	// 获取flex中加载流程设计图对应的xml数据，请求地址
	function getLoadDataUrl(){
		return document.getElementById("flexLoadDataUrl").value;

	}
	//zdn
	function getCtxPath112(){
		return document.getElementById("postUrl112").value;
	}
	//nxx加载流程历史展现内容
	function getCtxPath110(){
		return document.getElementById("postUrl110").value;
	}
	function getTypeName()
	{
		return document.getElementById("typeName").value;
	}
  --></script>
  <div>
  
  
  
  <textarea id="typeName" style="display:none"><%=typeName%></textarea>
  	<input id="postUrl" type="hidden" value="<%=postUrl%>" />
  	
  	<input id="postUrl2" type="hidden" value="<%=postUrl2%>" />
  	<input id="postUrl90" type="hidden" value="<%=postUrl90%>" /> 
  	<input id="postUrl10" type="hidden" value="<%=postUrl10%>" /> 
  	<input id="postUrl110" type="hidden" value="<%=postUrl110%>" /> 	
  	<input id="postUrl112" type="hidden" value="<%=postUrl112%>" />	
  	<input id="flexLoadDataUrl" type="hidden" value="<%=loadDateUrl%>" />
  </div>
  <div id="flashContent">
     	<p> To view this page ensure that Adobe Flash Player version 10.0.0 or greater is installed. </p>
		<script type="text/javascript"> 
			var pageHost = ((document.location.protocol == "https:") ? "https://" :	"http://"); 
			document.write("<a href='http://www.adobe.com/go/getflashplayer'><img src='" 
			+ pageHost + "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player'/></a>" ); 
		</script> 
   </div>
	   	
<noscript>
    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%" id="bpmeditor">
        <param name="movie" value="<%=basePath%>/media/swf/bpm/bpmeditor.swf" />
        <param name="quality" value="high" />
        <param name="bgcolor" value="#ffffff" />
        <param name="allowScriptAccess" value="sameDomain" />
        <param name="allowFullScreen" value="true" />
        <param name="FlashVars" value="flowKeyGetUrl=<%=flowKeyGetUrl%>&flowListGetUrl=<%=flowListGetUrl%>" />
        <!--[if !IE]>-->
        <object type="application/x-shockwave-flash" data="<%=basePath%>/media/swf/bpm/bpmeditor.swf" width="100%" height="80%">
            <param name="quality" value="high" />
            <param name="bgcolor" value="#ffffff" />
            <param name="allowScriptAccess" value="sameDomain" />
            <param name="allowFullScreen" value="true" />
            <param name="FlashVars" value="flowKeyGetUrl=<%=flowKeyGetUrl%>&flowListGetUrl=<%=flowListGetUrl%>" />
        <!--<![endif]-->
        <!--[if gte IE 6]>-->
        	<p> 
        		Either scripts and active content are not permitted to run or Adobe Flash Player version
        		10.0.0 or greater is not installed.
        	</p>
        <!--<![endif]-->
            <a href="http://www.adobe.com/go/getflashplayer">
                <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash Player" />
            </a>
        <!--[if !IE]>-->
        </object>
        <!--<![endif]-->
   </object>
</noscript>
 
<body>
          		
  		   <!--	<iframe id="def" height="100%" width="100%" frameborder="0" ></iframe>
           <f:a id="def" target="_blank" ></f:a>--> 
</body>
