<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择组织</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

	$(function(){
		$("#defLayout").ligerLayout({
			allowRightResize:false,
			allowLeftResize:false,
			allowTopResize:false,
			allowBottomResize:false,
			height: '100%',
			rightWidth:170
		});
	});
	
	function selectOrg(){

		var	aryOrg = $('#orgFrame').contents().find(":input[name='orgId'][checked]");
			var aryId = [];
			var aryName = [];
			var orgJson = [];
			aryOrg.each(function() {
				var data = $(this).val();
				var aryTmp = data.split("#");
				aryId.push(aryTmp[0]);
				aryName.push(aryTmp[1]);
				orgJson.push({
					id: aryTmp[0],
					name: aryTmp[1]
				});
			});
			var orgIds = aryId.join(",");
			var orgNames = aryName.join(",");
	
			var obj = {};
			obj.orgId = orgIds;
			obj.orgName = orgNames;
			obj.orgJson = orgJson;
		//window.returnValue=obj;
		dialog.get('sucCall')(obj);
		
		dialog.close();
	}
	function clearOrg(){
		var obj = {};
		obj.orgId = '';
		obj.orgName = '';
		obj.orgJson = '';
		dialog.get('sucCall')(obj);
		dialog.close();
	}
	
</script>
<style type="text/css">
	div.bottom{text-align: center;padding-top: 10px;}
	html,body{width:100%;height:100%;margin: 0 0 0 0;padding: 0 0 0 0 ;overflow: hidden;}
</style>
</head>
<body>
	<div id="defLayout">
		<div position="center">
			<div class="l-layout-header">组织列表</div>
			<iframe id="orgFrame" name="orgFrame" height="95%" width="100%"
				frameborder="0"   src="selector.ht"></iframe>
		</div>
		 <div position="bottom"  class="bottom" style='margin-top:10px;'>
			<a href='#' class='button' onclick="selectOrg()" style="margin-right:10px;" ><span class="icon ok"></span><span >选择</span></a>
			<a href="javascript:;" class="button"  onclick="clearOrg()"><span class="icon cancel" ></span><span class="cance" >清空</span></a>
			<a href='#' class='button' style='margin-left:10px;' onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a> 
		 </div>
	 </div>
</body>
</html>


