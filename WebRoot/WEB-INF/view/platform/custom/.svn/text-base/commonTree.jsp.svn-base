<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 自定义显示的树结构</title>
	<%@include file="/commons/include/form.jsp" %>
	<%@include file="/js/msg.jsp"%>
	<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
	<style type="text/css">
		.hide{
			display: none;
		}
		#leftArea{
			float: left;
			width:32%;
			height:550px;
		}
		#centerArea{
			float: left;
			height:550px;
			width:8%;
		}
		#rightArea{
			float: left;
			width:60%;
			height:550px;
		}
		.moveSelect{
			margin:4px auto;
		}
	</style>
	<script type="text/javascript">
		var curField;
		$(function(){
			var inputArgs = window.dialogArguments;
			var isAdd=inputArgs.isAdd;
			var fields=inputArgs.fields;
			var setting=inputArgs.setting;
// 			var conditions=inputArgs.conditions;
			
// 			fields=$.parseJSON(fields);
			initColumns(fields);
			
			if(setting){
// 				setting=$.parseJSON(setting);
				initSetting(setting);	
			}
			$("input.treeField").focus(function(){
				curField=$(this);
			});
			
		});
		
		/*
		*初始化树设置
		*/
		function initSetting(setting){
			if(setting){
				var node=setting.node;
				var parentNode=setting.parent;
				var displayField=setting.display;
				$("#nodeId").val(node.name);
				$("#nodeId").attr("field",JSON2.stringify(node));
				$("#parentNodeId").val(parentNode.name);
				$("#parentNodeId").attr("field",JSON2.stringify(parentNode));
				$("#displayField").val(displayField.name);
				$("#displayField").attr("field",JSON2.stringify(displayField));
			}
		}
		
		/*
		 *初始化数据列的表
		 */
		function initColumns(fields){
			var columnsTbl = $("#columnsTbl tbody").html("");
			for(var i=0;i<fields.length;i++){
				var field=fields[i];
				var tr = constructColumnTr(field);
				columnsTbl.append(tr);
			}
		}
		
		
		
		/*
		 *构造SQL语句选择的列的<tr>///////////////
		 */
		function constructColumnTr(field){
			var index = field.index;
			var type = field.type;
			var name = field.name;
			var qualifiedName=field.qualifiedName;
			var label=field.label;
			var comment = field.comment;
			if(!comment){
				comment=label;
			}
			var display=false;
			if(typeof(field.display)!='undefined'){
				display = field.display;
			}
			//first td checkbox
			var $tdChk = $("<td></td>");
			var $inputChk = $("<input></input>")
			$inputChk.attr("name","fieldCheckBox");
			$inputChk.attr("type","checkbox");
			$tdChk.append($inputChk);
			
			$inputHidden=$("<input type='hidden'/>")
			$inputHidden.val(JSON2.stringify(field));
			$tdChk.append($inputHidden);
			//second td field name
			var $tdName = $("<td></td>");
			$tdName.append(name);
			//third td field comment
			var $tdComment = $("<td></td>");
			var $inputComment=$("<input></input>");
			$inputComment.attr("name","fieldComment");
			$inputComment.val(comment);
			$tdComment.append($inputComment);
			//four td column select checkedbox
// 			var $tdSelect = $("<td></td>");
// 			var $checkbox = $("<input></input>")
// 			$checkbox.attr("name","fieldDisplay");
// 			$checkbox.attr("type","checkbox");
// 			if(display){
// 				$checkbox.attr("checked","checked");
// 			}
// 			$tdSelect.append($checkbox);
			//Tr
			var $tr=$("<tr></tr>");
			$tr.append($tdChk);
			$tr.append($tdName);
			$tr.append($tdComment);
// 			$tr.append($tdSelect);
			return $tr;
		}
		 
		
		/*
		 *设置树的节点，左边字段选择
		 */
		function selectTreeField(){   
			var obj=$("#columnsTbl input:[name='fieldCheckBox']:checked");
			if(curField==null || curField.length==0){
				$.ligerDialog.error("请选择右边的输入框!",'提示信息');
				return;
			}
			if(obj.length==0){
				$.ligerDialog.error("请选择左边的字段!",'提示信息');
				return;
			}
			if(obj.length>1){
				$.ligerDialog.error("只能选择一个左边的字段!",'提示信息');
				return;
			}
			
			var field=obj.next('input[type="hidden"]').val();
			var comment = obj.closest("tr").find("input[name='fieldComment']").val();
			if(!comment){
				field.comment=comment;
			}
			
			var fieldJson=$.parseJSON(field);
			curField.val(fieldJson.name);
			curField.attr("field",field);
		}
		

		/*
		 * 取得参数设置
		 */
		function getAllSetting(){
				var settings={
			};
			
			//取树节点设置
			var node=$("#nodeId").attr("field");
			if(!node){
				$.ligerDialog.error("请选择节点ID！");
				return false;
			}
			var parentNode=$("#parentNodeId").attr("field");
			if(!parentNode){
				$.ligerDialog.error("请选择父节点ID！");
				return false;
			}
			var display=$("#displayField").attr("field");
			if(!display){
				$.ligerDialog.error("请选显示字段！");
				return false;
			}
			var setting = {
				node:$.parseJSON(node),
				parent:$.parseJSON(parentNode),
				display:$.parseJSON(display)
			};
			settings.setting=setting;
			return settings;
		}
		
		function saveForm(){
			var settings =getAllSetting();
			if(!settings){
				return;
			}
			window.returnValue=settings;
			window.close();
		}
		
		
		/*
		 *构造标签
		 */
		function constructTag(name,props,text){
			var tag = $("<"+name+"></"+name+">");
			for(var key in props){
				tag.attr(key,props[key]);
			}
			tag.text(text);
			return tag;
		}
	</script>
</head>
<body>
	<div>
		<div id="leftArea">
		<div style="border: 1px solid rgb(123, 171, 207);height: 548px;overflow: auto;">
			<table id="columnsTbl" class="table-grid">
				<thead>
					<tr>
						<th>选择</th>
						<th>列名</th>
						<th>注释</th>
		<!-- 				<th>显示</th> -->
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		</div>
		<div id="centerArea" >
			<div style="text-align: center;">
			<a href='#' class='button' style="margin-top: 50px"  onclick="selectTreeField()" >
				<span >==></span>
			</a>
			</div>
		</div>
		<div id="rightArea">
			<div style="border: 1px solid rgb(123, 171, 207);height: 548px;overflow: auto;">
			<div id="TreeSettings">
				<table id="treeTbl" class="table-grid">
					<tr>
						<th>名称</th>
						<th>字段</th>
					</tr>
					<tr>
						<td>节点ID</td>
						<td>
							<input id="nodeId" name="nodeId" class="treeField"/>
						</td>
					</tr>
					<tr>
						<td>父节点ID</td>
						<td>
							<input id="parentNodeId" name="parentNodeId" class="treeField"/>
						</td>
					</tr>
					<tr>
						<td>显示字段</td>
						<td>
							<input id="displayField" name="displayField" class="treeField"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		</div>
	</div>
	<div style="clear: both;"></div>
	<div position="bottom" class="bottom" style="padding-top: 15px;">
		<a href='#' class='button'  onclick="saveForm()" ><span class="icon ok"></span><span >确定</span></a>
  		<a href='#' class='button' style='margin-left:10px;' onclick="window.close()"><span class="icon cancel"></span><span >取消</span></a>
	</div>
</body>
</html>