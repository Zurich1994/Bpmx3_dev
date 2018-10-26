
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电子印章管理</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
		/*KILLDIALOG*/
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		$(function(){
			$('#btnSelect').click(function(){
					var sealDivs = $("#sealListFrame").contents().find("div[name=mySealDiv]");
					if(sealDivs.length == 0){
						$.ligerMessageBox.warn('没有可以选择的印章！','提示信息');
						return false;
					}else{
						sealDivs.each(function(){
							 var me=$(this);
							 var myclass = me.attr("class");	
							 if(myclass=="Div_MySeal_click"){
								 var myvalue = me.attr("value");
								 if(typeof myvalue != 'undefined' && myvalue!= null){
									 var retVal = eval('(' + myvalue + ')');
									 //window.returnValue=retVal;
									 dialog.get("sucCall")(retVal);
								 }else{
									 $.ligerMessageBox.warn('印章信息有误！','提示信息');
								 }
								 dialog.close();
							 }else{
								 $.ligerMessageBox.warn('请选择一个印章！','提示信息');
							 }
						});						
					}				
			});
			
			$('#btnClose').click(function(){
				dialog.close();
			});
		});  
	</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">电子印章管理列表</span>
			</div>
		</div>
		<div style="height:500px;">
			<iframe id="sealListFrame" name="sealListFrame" height="90%"
				width="100%" frameborder="0"
				src="${ctx}/platform/system/seal/selector.ht"></iframe>
		</div>
 	<div position="bottom"  class="bottom" style='margin-top:10px'>
			<a class='button' id="btnSelect"><span class="icon ok"></span><span >选择</span></a>
			<a class="button" id="btnClose" style='margin-left:10px;'  onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a>
		</div> 
	</div>
	<!-- end of panel -->
</body>
</html>


