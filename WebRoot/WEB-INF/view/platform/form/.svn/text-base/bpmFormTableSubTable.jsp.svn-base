
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>查看子表</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerComboBox.js" ></script>

<style type="text/css">
	.panel-data {
		margin-top: 5px ;
		margin-bottom: 5px ;
	}
</style>
<script type="text/javascript">
	var winIframe;
	function selectSubTable() {
		if(!winIframe) {
			$.post('getAllUnassignedSubTable.ht', {
				tableName: '${table.tableName}'
			}, function(tables) {
				var data = [];
				for(var i = 0; i < tables.length; i++) {
					data.push({id: tables[i].tableId, name: tables[i].tableName});
				}
				openWin('选择子表', '#list');
				$("#combo").ligerComboBox( {
					width : '90%',
					isShowCheckBox : true,
					isMultiSelect : true,
					data : data,
					valueFieldID: 'combo1'

				});
			});
		} else {
			winIframe.show();
		}
	}
	
	function openWin(title, target) {
		var width=200;
		var height=150;
		var left=($(window).width()-width)/2;
		var top=($(window).height()-height)/2; 
		var buttons=[{text:"保存",handler:save}];
		var p={width:width,height:height,left:left,top:top,title:title,showMax:true,
					onClose:true,showButton:true,buttons:buttons};
		winIframe = $(target).ligerWindow(p);
	}
	
	function save() {
		var val = $("#combo1").val();
		if(val != '') {
			$.post('linkSubtable.ht', {
				mainTableId: ${table.tableId},
				subTableId: val
			}, function(data) {
				var obj=new com.hotent.form.ResultMessage(data);
				if(obj.isSuccess()){//成功
					parent.needRefresh = true;
					location.reload();
			    }else{//失败
			    	$.ligerDialog.err('出错信息',"保存子表信息失败",obj.getMessage());
			    }
			})
		} else {
			winIframe.close();
		}
	}

</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a href="javascript:;" class=" bar-button" onclick="selectSubTable()"><span></span>添加子表</a></div>
					<div class="l-bar-separator"></div>
				</div>	
			</div>
		</div>
		</div>
		<div class="panel-body">
			<div class="panel-data">
			 	<table class="table-grid">
					<tr>
						<td>${table.tableName}</td>
						<td>${table.tableDesc}</td>
						<td><a href="javascript:;" onclick="parent.showDetail('get.ht?iswin=1&tableId=${table.tableId}')" class="link detail">查看</a></td>
					</tr>
					<tr style="font-style:italic; color:gray">
						<td colspan="3">子表列表</td>
					</tr>
					<c:if test="${fn:length(subTables) == 0}">
						<tr>
							<td colspan="3">无</td>
						</tr>
					</c:if>
					<c:forEach items="${subTables}" var="subTable"  varStatus="status">
						<tr>
							<td>${subTable.tableName}</td>
							<td>${subTable.tableDesc}</td>
							<td>
								<a href="javascript:;" onclick="parent.showDetail('get.ht?iswin=1&tableId=${subTable.tableId}')" class="link detail">查看</a>
								<a href="unlinkSubTable.ht?subTableId=${subTable.tableId}" class="link del" onclick="parent.needRefresh = true">移除</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
	
	<div id="list" style="display:none; padding:5px;">
		<br>
		<input type="text" id="combo" name="combo"/>
	</div>
</body>
</html>
