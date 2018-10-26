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
    String xmlRecord = request.getAttribute("xmlRecord").toString();
    String typeName=request.getAttribute("typeName").toString();
	
	// 加载数据操作 
	// 获取流程定义id
	String defId = "";

	String loadDateUrl = ""; // flex中获取加载数据的地址
	String markid="";//马尔科夫链ID
	String designChart="";//是否设计图
	Object markidtemp=request.getAttribute("markid");
	String checklv="";//是否业务逻辑校验
	Object checklvObj=request.getAttribute("checklv");
	
	Object defIdObj = request.getAttribute("defId"); 
	Object designChartObj = request.getAttribute("designChart"); 
	if(defIdObj != null && !defIdObj.toString().equals("0")){
		defId = defIdObj.toString(); // 流程id
		loadDateUrl = basePath + "/platform/bpm/bpmDefinition/flexGet.ht?temp=g&defId=" + defId;
	}; 
   
	if(markidtemp!=null){
		markid=markidtemp.toString();
	}
	if(checklvObj!=null){
		checklv=checklvObj.toString();
	}
	if(designChartObj!=null){
		designChart=designChartObj.toString();
	}
	String postUrl = basePath + "/platform/bpm/bpmDefinition/flexDefSave.ht?isAjaxRequest=true&defId=" + defId;	
	String postUrl90 = basePath + "/platform/bpm/bpmDefinition/flexDefSave90.ht?isAjaxRequest=true&defId=" + defId;	
	String postUrl0= basePath + "/platform/bpm/bpmDefinition/flexDefSave00.ht?isAjaxRequest=true&defId=" + defId;
	String postUrl2 = basePath + "/platform/bpm/bpmDefinition/flexDefWritePage.ht?isAjaxRequest=true&defId=" + defId;	
	String postUrl10= basePath + "/platform/bpm/bpmDefinition/flexRedirect.ht?isAjaxRequest=true";
	String postUrl8 = basePath + "/platform/bpm/bpmDefinition/flex11.ht?isAjaxRequest=true";
	//zdn
	String postUrl101 = basePath + "/platform/bpm/bpmDefinition/flex101.ht?isAjaxRequest=true";
	String postUrl102 = basePath + "/platform/bpm/bpmDefinition/flex102.ht?isAjaxRequest=true";
	String postUrl103 = basePath + "/platform/bpm/bpmDefinition/flex103.ht?isAjaxRequest=true";
	String postUrl104 = basePath + "/platform/bpm/bpmDefinition/flex104.ht?isAjaxRequest=true";
	String postUrl105 = basePath + "/platform/bpm/bpmDefinition/flex105.ht?isAjaxRequest=true";
    String postUrl106 = basePath + "/platform/bpm/bpmDefinition/flex106.ht?isAjaxRequest=true";
    String postUrl107 = basePath + "/platform/bpm/bpmDefinition/flex107.ht?isAjaxRequest=true";
    String postUrl108 = basePath + "/platform/bpm/bpmDefinition/flex108.ht?isAjaxRequest=true";
    String postUrl109 = basePath + "/platform/bpm/bpmDefinition/flex109.ht?isAjaxRequest=true";
	//马尔科夫链预览
	String postUrl110= basePath + "/platform/bpm/bpmDefinition/flex110.ht?isAjaxRequest=true&markid="+markid; 

	String postUrl21 = basePath + "/platform/bpm/bpmDefinition/flex111.ht?isAjaxRequest=true";
    
	String postUrl17 = basePath + "/platform/bpm/bpmDefinition/amountOccurrence.ht?isAjaxRequest=true";

	String postUrl15= basePath + "/platform/bpm/bpmDefinition/flexDef15.ht?isAjaxRequest=true";
    //zll
    String postUrl18 = basePath +  "/platform/bpm/bpmDefinition/flexNodeTimeAndCount.ht?isAjaxRequest=true&defId=" + defId;	
    //保存路径
    String postUrl112 = basePath +  "/platform/bpm/bpmDefinition/flex112.ht?isAjaxRequest=true&defId="+defId+"&markid="+markid;
    String postUrl113= basePath + "/platform/bpm/bpmDefinition/flex113.ht?isAjaxRequest=true&defId=" + defId;
    String postUrl114 = basePath + "/platform/bpm/bpmDefinition/flex114.ht?isAjaxRequest=true";
    String postUrl115 = basePath + "/platform/bpm/bpmDefinition/flex115.ht?isAjaxRequest=true";
    String postUrl116 = basePath + "/platform/bpm/bpmDefinition/flex116.ht?isAjaxRequest=true";
    %>

    

  <head>
  
  	<title><%if("flowChart".equals(typeName))
  	{	%>
  	在线流程设计--
  	<% 
  	}
  	else if("transactionChart".equals(typeName))
  	{
  %>
  	在线事务图设计--
  <% 
  	}
  	else if("lineChart".equals(typeName))
  	{
  %>
  	发生量曲线图展示--流程图
  <% 
  	}
  	else {
  	%>
  	在线操作图设计--
  	<%} %>
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
     
       <script type="text/javascript">

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
		var test=document.getElementById('typeName').value;
		return test;
		}
	function getDesignChart()
	{
		var test=document.getElementById('designChart').value;
		return test;
	}
	function getcheckLV()
	{
		var test=document.getElementById('checklv').value;
		return test;
	}
	function loadData(){
		var str = document.getElementById('xmlRecord').value;
		return str;
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
	//ys
	//供flex方向调用,获取提交数据的地址,查看拓扑图外部子图
	function getCtxPath0(){
alert("getCtxPath0");
		return document.getElementById("postUrl0").value;
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
	
	function getCtxPath8(){
		return document.getElementById("postUrl8").value;
	}
	
	//zdn
	function getCtxPath101(){
		return document.getElementById("postUrl101").value;
	}
	function getCtxPath102(){
		return document.getElementById("postUrl102").value;
	}
	function getCtxPath110(){
		return document.getElementById("postUrl110").value;
	}
	
	function getCtxPath103(){
		return document.getElementById("postUrl103").value;
	}
	function getCtxPath104(){
		return document.getElementById("postUrl104").value;
	}
	function getCtxPath105(){
		return document.getElementById("postUrl105").value;
	}
	function getCtxPath106(){
		return document.getElementById("postUrl106").value;
	}
	function getCtxPath107(){
	   // alert("出来"+type+","+actDefId+","+activitiId+","+defId+","+parentActDefId);
		//FlowEventWindowx({type:type,actDefId:actDefId,activitiId:activitiId,defId:defId,parentActDefId:parentActDefId});
		return document.getElementById("postUrl107").value;
	}
	function getCtxPath108(){
		return document.getElementById("postUrl108").value;
	}
	function getCtxPath109(){
		return document.getElementById("postUrl109").value;
	}
	function getCtxPath113(){
		return document.getElementById("postUrl113").value;
	}
	function getCtxPath114(){
		return document.getElementById("postUrl114").value;
	}
	function getCtxPath115(){
		return document.getElementById("postUrl115").value;
	}
	function getCtxPath116(){
		return document.getElementById("postUrl116").value;
	}
	//供flex方向调用，webservice原子操作
    function getCtxPath21(){
		return document.getElementById("postUrl21").value;
	}
	
	function getCtxPath17(){
		return document.getElementById("postUrl17").value;
	}
	//zl保存发生量
	function getCtxPath18(){
	alert(document.getElementById("postUrl18").value);
		return document.getElementById("postUrl18").value;
	}
	
	//供flex方向调用,网络模板,wyx
	function getCtxPath15(){  
		return document.getElementById("postUrl15").value;
	}
	 function getproTypeNameValue(){  
		return document.getElementById("typeName").value;
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
  </script>
  <div>
  
  
  
  <textarea id="typeName" style="display:none"><%=typeName%></textarea>
  	<textarea id="xmlRecord" style="display:none"><%=xmlRecord%></textarea>
  	<input id="postUrl" type="hidden" value="<%=postUrl%>" />
  	<input id="postUrl0" type="hidden" value="<%=postUrl0%>" /> 
  	<input id="postUrl2" type="hidden" value="<%=postUrl2%>" />
  		<input id="postUrl90" type="hidden" value="<%=postUrl90%>" /> 
  	<input id="postUrl10" type="hidden" value="<%=postUrl10%>" /> 
  	<input id="postUrl8" type="hidden" value="<%=postUrl8%>" />
  	<input id="postUrl101" type="hidden" value="<%=postUrl101%>" />
  	<input id="postUrl102" type="hidden" value="<%=postUrl102%>" />
	<input id="postUrl110" type="hidden" value="<%=postUrl110%>" />
	
  	<input id="postUrl103" type="hidden" value="<%=postUrl103%>" />
  	<input id="postUrl104" type="hidden" value="<%=postUrl104%>" />
  	<input id="postUrl105" type="hidden" value="<%=postUrl105%>" />
  	<input id="postUrl106" type="hidden" value="<%=postUrl106%>" />
  	<input id="postUrl107" type="hidden" value="<%=postUrl107%>" />
  	<input id="postUrl108" type="hidden" value="<%=postUrl108%>" />
  	<input id="postUrl109" type="hidden" value="<%=postUrl109%>" />
  	<input id="postUrl113" type="hidden" value="<%=postUrl113%>" />
  	<input id="postUrl114" type="hidden" value="<%=postUrl114%>" />
  	<input id="postUrl115" type="hidden" value="<%=postUrl115%>" />
  	<input id="postUrl116" type="hidden" value="<%=postUrl116%>" />
  	
  	<input id="postUrl21" type="hidden" value="<%=postUrl21%>" />
  	<input id="designChart" type="hidden" value="<%=designChart%>" />
  	<input id="checklv" type="hidden" value="<%=checklv%>" />

  	<input id="postUrl17" type="hidden" value="<%=postUrl17%>" />


  	<input id="postUrl17" type="hidden" value="<%=postUrl17%>" />
  	<input id="postUrl18" type="hidden" value="<%=postUrl18%>" />

  	<input id="postUrl15" type="hidden" value="<%=postUrl15%>" />	
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
