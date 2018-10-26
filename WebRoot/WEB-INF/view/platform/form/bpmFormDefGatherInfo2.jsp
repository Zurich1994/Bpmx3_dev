
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>新建表单</title>
<%@include file="/commons/include/form.jsp" %>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/htCatCombo.js"></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmFormDef"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormTableDialog.js"></script>
<style type="text/css">
	html,body{height:100%;width:100%; overflow: hidden;}
</style>


<script type="text/javascript">	
	function selectTable(){
		var callBack=function(tableId,tableName){		
			$("#tableId").val(tableId);
			$("#tableName").val(tableName);
		}
		FormTableDialog({callBack:callBack});
	}
	function resetTable(){
		$("#tableId").val('');
		$("#tableName").val('');
	};
	
	
</script>

</head>
<body >
	
		<div class="panel-detail">
			<form  id="bpmFormDefForm" method="post" action="selectTemplate.ht" >
				 
				 <table cellpadding="1" cellspacing="1" class="table-detail">															
					<tr id="table_tr">
						<th width="150">表:</th>
						<td style="padding-top: 5px;">
							
							<input type="text" id="tableName" class="inputText" name="tableName" value="" readonly="readonly">
							<input type="hidden" id="tableId" name="tableId" value="">
							<a href='#' class='link search'  onclick="selectTable()" ></a>
							<a href='#' class='link redo' style='margin-left:10px;' onclick="resetTable()"><span>重选</span></a>
						</td>
					</tr>
				</table>
				
			</form>
		</div><!-- end of panel-body -->				

</body>
</html>


