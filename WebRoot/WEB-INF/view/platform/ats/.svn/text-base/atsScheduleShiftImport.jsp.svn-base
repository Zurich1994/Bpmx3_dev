<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>排班导入</title>
<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
	<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)	
	$(function(){
		
		$("#btnSave").click(function(){
				var path = $('#file').val();
				var extName = path.split('.').pop().toLowerCase();
				if(extName=='xls' || extName=='xlsx'){
					$.ligerDialog.waitting('正在导入中,请稍候...');
					submitForm();
				}else{
					$.ligerDialog.warn("请选择 excel文件进行导入!");
				}
		
		});
	
	});
	function exportTemp(){
		AtsImport({
			url:__ctx + '/platform/ats/atsScheduleShift/exportTemp.ht',
			title:'导出排班列表模版',
			width:700,
			height:500
		});
	}
	
	function submitForm(){
		var options={};
		if(showResponse){
			options.success=showResponse;
		}
		var frm=$('#importForm').form();
		frm.ajaxForm(options);
		if(frm.valid()){
			frm.submit();
		}
	}
	
	
	function showResponse(responseText){
		var obj=new com.hotent.form.ResultMessage(responseText);
		if(obj.isSuccess()){//成功
			$.ligerDialog.closeWaitting();
			var message = obj.getMessage();
			if(message){
				message =message.replaceAll("###","\'").replaceAll("!!!","<font ").replaceAll("%%%","</font>").replaceAll("&gt;",">").replaceAll("&lt;","<");   
			}
		  	 $.ligerDialog.tipDialog('提示信息',"导入结果如下:",message,null,function(){
		  		dialog.get('sucCall')("/");
				dialog.close();
				});
	    }else{//失败
			$.ligerDialog.closeWaitting();
	    	$.ligerDialog.error(obj.getMessage(),"提示信息");
	    }
	}
	</script>
</head>
<body>
	<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">打卡记录导入</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="btnSave"><span></span>导入</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link export" id="btnExport" onclick="javasrcipt:exportTemp()" ><span></span>导出排班模板</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" onclick="javasrcipt:dialog.close()"><span></span>关闭</a></div>
				</div>	
			</div>
	</div>
	<div class="panel-body">
		<form id="importForm" name="importForm" method="post" target="win" action="importData.ht" enctype="multipart/form-data">
			<div class="row">
			 <table  class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%">选择文件：</th>
					<td width="80%" colspan="3"><input type="file" size="40" name="file" id="file"/></td>						
				</tr>
				<tr>
					<th width="20%">节假日处理</th>
					<td>
						<select id="holidayHandle" name="holidayHandle">
							<option value="2" >不替换</option>
							<option value="1" >替换</option>
						</select>
					</td>
				</tr>
			</table>				
			
			</div>
	    </form>
	</div><!-- end of panel-body -->		
</body>
</html>