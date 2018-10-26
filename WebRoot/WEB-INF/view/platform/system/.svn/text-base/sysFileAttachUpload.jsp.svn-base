<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html"%>
<html>
  <head>
  	<title>附件上传</title>
  </head>
  <style type="text/css"> 
	html, body	{ height:100%; }
	body { margin:0; padding:0; overflow:auto; text-align:center;  background-color: #ffffff; }   
	#flashContent { display:none; }
  </style>
  	   <%@include file="/commons/include/form.jsp" %>
  	   <script type="text/javascript" src="${ctx}/media/swf/fileUpload2/swfobject.js"></script>
       <script type="text/javascript">
       
       var uploadPath="${ctx}/platform/system/sysFile/fileUpload.ht;jsessionid=<%=session.getId()%>?typeId=0" ;
       var delPath="${ctx}/platform/system/sysFile/delByFileId.ht;jsessionid=<%=session.getId()%>" ;
       var atthMaxSize = "5";
       var sizeUnit = "MB";
       
       function initFlashUpload(){
      	   //设置swfobject对象参数
      	   var swfVersionStr = '10.0.0';
           var xiSwfUrlStr = '${ctx}/media/swf/fileUpload2/playerProductInstall.swf';
           var flashvars = {};
           flashvars.uploadPath=uploadPath;
           flashvars.delPath=delPath;
           flashvars.atthMaxSize = typeof this.opener.atthMaxSize!='undefined'?this.opener.atthMaxSize:atthMaxSize;
           var unit = this.opener.sizeUnit;
           flashvars.sizeUnit = typeof unit!='undefined'&&(unit=="GB"||unit=="MB"||unit=="KB")?unit:sizeUnit;
			
           var params = {};
           params.quality = 'high';
           params.bgcolor = '#ffffff';
           params.allowscriptaccess = 'sameDomain';
           params.allowfullscreen = 'true';
           var attributes = {};
           attributes.id = 'flexupload';
           attributes.name = 'flexupload';
           attributes.align = 'middle';
           swfobject.embedSWF('${ctx}/media/swf/fileUpload2/flexupload.swf', 'flashContent', 
              '100%', '100%', swfVersionStr, xiSwfUrlStr, flashvars, params, attributes);
           swfobject.createCSS('#flashContent', 'display:block;text-align:left;');
       }
    	/**
		 * 关闭Extjs页面同时刷新GridPanel,【在flex中调用】
		 */
		function winClose(obj){
			this.opener.returnValue.call(this, this.opener.curobj, obj.files);
			window.close();
		}
    	
    	$(function(){
    		initFlashUpload();
    	});
	</script>
  	<body>
  		<div id="flashContent" >
			<h1>上传控件找不到</h1>
			<p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" /></a></p>
		</div>
	  
	</body>
</html>