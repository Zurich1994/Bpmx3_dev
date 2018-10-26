<%--
	time:2012-10-23 17:59:41
	desc:edit the 自定义显示
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@page language="java" import="com.hotent.platform.model.system.SysCustomDisplay"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 自定义显示</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
	<style type="text/css">
		.hide{
			display: none;
			overflow: auto;
		}
		.settingdiv{
			height:550px;
			overflow: auto;
		}

	</style>
	<script type="text/javascript">
		$(function() {
			var inputArgs = window.dialogArguments;
			var isAdd=inputArgs.isAdd;
			var fields=inputArgs.fields;
			var setting=inputArgs.setting;
			
// 			fields=$.parseJSON(fields);
			initColumns(fields);
			
			if(setting){
// 				setting=$.parseJSON(setting);
				initSetting(setting);	
			}
		});
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
		 *初始化显示设置
		 */
		function initSetting(setting){
			for(var i=0;i<setting.length;i++){
				$("#columnsTbl input[name='fieldDisplay'][fieldName='"+setting[i].name+"']").attr("checked","checked");
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
			//first td checkbox
			var $tdChk = $("<td class='hidden'></td>");
// 			var $inputChk = $("<input></input>")
// 			$inputChk.attr("name","fieldCheckBox");
// 			$inputChk.attr("type","checkbox");
// 			$tdChk.append($inputChk);
			
			$inputHidden=$("<input type='hidden'/>")
			$inputHidden.val(JSON2.stringify(field));
			$tdChk.append($inputHidden);
			//second td field name
			var $tdName = $("<td></td>");
			$tdName.append(name);
			//third td field comment
			var $tdComment = $("<td ></td>");
			var $inputComment=$("<input></input>");
			$inputComment.attr("name","fieldComment");
			$inputComment.val(comment);
			$tdComment.append($inputComment);
			//four td column select checkedbox
			var $tdSelect = $("<td></td>");
			var $checkbox=$("<input></input>");
			$checkbox.attr("name","fieldDisplay");
			$checkbox.attr("type","checkbox");
			$checkbox.attr("fieldName",name);
			$tdSelect.append($checkbox);
			//Tr
			var $tr=$("<tr></tr>");
			$tr.append($tdChk);
			$tr.append($tdName);
			$tr.append($tdComment);
			$tr.append($tdSelect);
			return $tr;
		}

		/*
		 * 取得参数设置
		 */
		function getAllSetting(){
			var settings={
					setting:new Array()
			};
			//取显示字段
			$("#columnsTbl input:[name='fieldDisplay']:checked").each(function(){
				var field=$.parseJSON($(this).closest("tr").find("input[type='hidden']").val());
				var comment = $(this).closest("tr").find("input[name='fieldComment']").val();
				field.comment=comment;
				settings.setting.push(field);
			});
			if(settings.setting.length<1){
				$.ligerDialog.error("请选择显示字段！");
				return false;
			}
			return settings;
		}
		
		/*
		 *保存设置
		 */
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
			if(text){
				tag.text(text);
			}
			return tag;
		}
	</script>
</head>
<body>
	<div class="panel">
		<div class="settingdiv">
			<table id="columnsTbl" class="table-grid">
				<thead>
					<tr>
<!-- 						<th>选择</th> -->
						<th>列名</th>
						<th>注释</th>
						<th>显示</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	<div position="bottom" class="bottom" style="padding-top: 15px;">
		<a href='#' class='button'  onclick="saveForm()" ><span class="icon ok"></span><span >确定</span></a>
  		<a href='#' class='button' style='margin-left:10px;' onclick="window.close()"><span class="icon cancel"></span><span >取消</span></a>
	</div>
</body>
</html>
