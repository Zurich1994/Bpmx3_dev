<%--
	time:2012-08-29 11:26:00
	desc:edit the 电子印章
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 电子印章</title>
	<%@include file="/commons/include/form.jsp" %>
	<%@include file="/js/msg.jsp"%>
	
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/ntkosign/NtkoSignManage.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	
	<style type="text/css">
		.displaynone{
			display:none;
		}
	</style>
	<script type="text/javascript">
		/*KILLDIALOG*/
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
		$(function() {
			eventBind();
		});
		
		function showResponse(responseText) {
			var fileObj = document.getElementById('filename');
			var realpath = getSelectText(fileObj);
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				var filename=realpath;
				var signname=$("#signname").val();
				var username=$("#username").val();
				var password=$("#password").val();
				var rtn={
						status:true,
						filename:filename,
						signname:signname,
						username:username,
						password:password,
						showImageId:obj.getMessage()
					};
					//window.returnValue=rtn;
					dialog.get("sucCall")(rtn);
					
					dialog.close();
			} else {
				//window.returnValue=null;
				dialog.get("sucCall")(null);
				dialog.close();
			}
		}
		
		//事件绑定
		function eventBind(){
			//Ok Button
			$("#btnSelect").click(function(){
				try{
					var frm=$('#signForm').form();					
					var options={};
					
					options.success=showResponse;
					frm.ajaxForm(options);
					
					if(frm.valid()){
						frm.submit();
					}
				}catch(ex){
					$.ligerDialog.warn("请把本网站添加到可信站点再进行操作！","提示信息");
				}
			});
			
			$("#filename").bind("change",function(){
				var sUrl = $(this).val();
				if(!sUrl){
					return false;
				}
				var Extlist = ".jpg.jpeg.bmp.png.";	
				if(Extlist.indexOf('.'+getExt(sUrl)+'.')==-1){
					$.ligerDialog.warn("请选择有效图片","提示信息");
					$("#filename").replaceWith( $(this).clone( true ));
				}
			});
		}
		
		function getExt(sUrl)
		{
	        var arrList = sUrl.split(".");
	        return arrList[arrList.length-1];
		}
		
		function getSelectText(obj)    //获取用户选择文本 
		{
			if (obj) { 
				try{
					if(window.navigator.userAgent.indexOf("MSIE") >= 1){ 
						obj.select(); 
						return document.selection.createRange().text;
					}else if(window.navigator.userAgent.indexOf("Firefox") >= 1){
						if(obj.files){
							return window.URL.createObjectURL(obj.files[0]);	  
						}		  
						return obj.value;
					}
				}catch(e){
					return obj.value.split('\\')[obj.value.split('\\').length-1];
				}
			}
		}
		
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top" >
			<div class="tbar-title">
				<span class="tbar-label">编辑电子印章</span>
			</div>
		</div>
		<div class="panel-body">
			<form id="signForm" method="post" action="${ctx}/platform/system/sysFile/saveFile.ht?ajaxType=obj&uploadName=filename">  <!-- ajaxType=obj  表示返回的是一个对象 -->
				<table class="table-detail">
					<tr>
						<th>图片：</th>
						<td>
							<input id="filename" name="filename" type="file" validate="{required:true}"  class="inputText">
							<span class="green">支持图片格式类型：jpg、jpeg、bmp、png</span>
						</td>
					</tr>
					<tr>
						<th>印章名：</th>
						<td><input id="signname" name="signname" type="text" validate="{required:true,maxlength:100}" class="inputText" /></td>
					</tr>
					<tr>
						<th>印章所属单位或个人：</th>
						<td>
							<input id="username" name="username" type="text" validate="{required:true,maxlength:50}" class="inputText" />
						</td>
					</tr>
					<tr>
						<th>密码：</th>
						<td>
							<input id="password" name="password" type="password" validate="{required:true,minlength:6,maxlength:32}"  class="inputText"/>
							<span class="green">长度至少六位</span>
						</td>
					</tr>
					<tr>
						<th>确认密码：</th>
						<td><input id="passwordConfirm" name="passwordConfirm" type="password" validate="{required:true,equalTo:'password'}"   class="inputText"/></td>
					</tr>
				</table>
			</form>
		</div>
		<div position="bottom"  class="bottom" style='margin-top:10px'>
			<a class='button' id="btnSelect"><span class="icon ok"></span><span >确定</span></a>
			<a class="button" id="btnClose" style='margin-left:10px;'  onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a>
		</div>
	</div>
</body>
</html>
