<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<f:js pre="js/lang/view/platform/form" ></f:js>
<title>手机表单设置</title>
<link rel="stylesheet" href="${ctx }/js/tree/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
<script type="text/javascript" src="${ctx}/js/util/SelectOption.js"></script>
<script type="text/javascript">
var tableId = '${bpmFormTable.tableId}',valid;

$(function() {
	$("#teamLayout").ligerLayout({
		leftWidth : 250,
		height : '100%',
		allowLeftResize : false
	});
	loadTree(tableId);
	valid =  $("#formItem").form();
	var height = $("body").height();
	$("#fieldtree").height(height-95);
	
	
	var formItem = 	$("#formItem");
	$("a.add").click(function(){
		addTeam(true);
	});
	$("a.save").click(function(){
		saveData();
	});
	
	$("a.preview").click(function(){
		preview();
	});

	
	initForm();
	
	//绑定分组选择
	formItem.delegate("fieldset", "focus click", function() {
		var me = $(this);
		if(me.attr('zone') == 'team')
			selectTeam (this);
	});

	//绑定上下移动字段
	moveField();
	
	//$("a.moveup,a.movedown").click(move);
	//$("a.movetop,a.movebottom").click(moveTopBottom);
});

//绑定上下移动	
function move(obj){
 	var me=$(obj);
 	var direct=me.hasClass("moveup");
 	var objFieldset=me.closest('fieldset');
	if(direct){
		var prevObj=objFieldset.prev();
		if(prevObj.length>0){
			objFieldset.insertBefore(prevObj);	
		}else{
			alert("已经置顶了!");
		}
	}
	else{
		var nextObj=objFieldset.next();
		if(nextObj.length>0){
			objFieldset.insertAfter(nextObj);
		}else{
			alert("已经置底了!");
		}
	}
};

function moveTopBottom(obj){
 	var me=$(obj);
 	var direct=me.hasClass("movetop");
 	var objFieldset=me.closest('fieldset');
 	if(direct){
		var prevObj=objFieldset.prevAll();
		if(prevObj.length>0){
			objFieldset.insertBefore(prevObj[prevObj.length-1]);
		}else{
			alert("已经置顶了!");
		}
 	}
 	else{
		var nextObj=objFieldset.nextAll();
		if(nextObj.length>0){
			objFieldset.insertAfter(nextObj[nextObj.length-1]);
		}else{
			alert("已经置底了!");
		}
	}
}

//
function moveField(){
	
	$(".moveFieldTop").click(function(){
		var td = $(this).parent().parent().parent();
		var select  = $("select",td).get(0);
		__SelectOption__.moveTop(select);
	});
	$(".moveFieldUp").click(function(){
		var td = 	$(this).parent().parent().parent();
		var select  = $("select",td).get(0);
		__SelectOption__.moveUp(select,'1');
	});
	$(".moveFieldDown").click( function(){
		var td = $(this).parent().parent().parent();
		var select  = $("select",td).get(0);;
		__SelectOption__.moveDown(select,'1');
	});
	$(".moveFieldBottom").click( function(){
		var td = $(this).parent().parent().parent();
		var select  = $("select",td).get(0);
		__SelectOption__.moveBottom(select);
	});	
}

//初始化表单
function initForm(){
	var json = $('#formJson').val();
	if ($.isEmpty(json))
		return;
	var data = $.parseJSON(json);
	if($.isEmpty(json))
		return;
	$("#title").val(data.title);
	//初始化字段
	if(!$.isEmpty(data.team)){
		for(var i=0,c;c=data.team[i++];){
			addTeam(false,c);
		}
	}
	if(!$.isEmpty(data.sub)){
		for(var i=0,c;c=data.sub[i++];){
			addSubTable(c);
		}
	}
	
}

</script>
<script type="text/javascript" src="${ctx}/js/hotent/mobile/form/BpmMobileForm.js"></script>

<body>	
	<div class="panel">
			<div id="teamLayout">
				<div position="left" title="字段列表" style="overflow: auto; float: left; width: 100%; height: 100%;">
					<div class="tree-toolbar">
						<span class="toolBar">
							<div class="group"><a class="link reload" onclick="javascript:loadTree(${bpmFormTable.tableId})"></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link expand" id="treeExpandAll" href="javascript:treeExpandAll(true)" ></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link collapse" id="treeCollapseAll" href="javascript:treeExpandAll(false)" ></a></div>
							
						</span>
					</div>
					<ul id="colstree" class="ztree"></ul>
				</div>
				<div position="center" title="手机表单设置" style="overflow: auto;">
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<a class="link save"  href="javascript:;"><span></span>保存</a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link preview"  href="javascript:;"><span></span>预览</a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link add"  href="javascript:;"><span></span>添加分组</a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link close" href="javascript:window.close()"><span></span>关闭</a>
							</div>
						</div>
					</div>
					<div>
							
						<div>
							<table cellpadding="1" cellspacing="1"  class="table-detail">
								<tr>
									<th  width="10%">标题:</th>
									<td>
										<input type="text" id="title" name="title"  class="inputText"   style="width:250px;">
									</td>
								</tr>
							</table>
						</div>
							
						<div id="formItem">
					
						</div>
						<form  id ="mobileForm" method="post" action="${ctx}/mobile/form/bpmMobileForm/save.ht" >
							<input type="hidden" id="id" name="id" value="${bpmMobileForm.id}">
							<input type="hidden" id="formId" name="formId" value="${bpmMobileForm.formId}">
							<input type="hidden" id="formKey" name="formKey" value="${bpmMobileForm.formKey}">		
							<textarea style="display: none;" id="formJson" name="formJson" >${fn:escapeXml(bpmMobileForm.formJson)}</textarea>						
							<input type="hidden" id="isDefault" name="isDefault" value="${bpmMobileForm.isDefault}">					
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 模板 -->
		<div  id="cloneTemplate"  style="display: none;">
			<fieldset style="margin: 5px 0px 5px 0px;" zone="team" >
				<legend>
					<div>
					<div style="float: left; ">分组设置</div>
					<div class="group" style="float: right; ">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="link del" title="删除"  onclick="delTeam(this);"></a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class='link movetop' title="置顶"  onclick="moveTopBottom(this);"></a>
							<a class='link moveup' title="上移" onclick="move(this);"></a>
							<a class='link movedown' title="下移"  onclick="move(this);"></a>
							<a class='link movebottom' title="置底" onclick="moveTopBottom(this);"></a>
					</div>
					<div style="clear:both;"></div>
					</div>
				</legend>
				<table cellpadding="1" cellspacing="1"  class="table-detail">
					<tr>
						<th width="10%">分组名称</th>
						<td><input type="text" width="70%" name="name" class="inputText"   style="width:250px;" /></td>
					</tr>
					<tr>
						<th>字段</th>
						<td>
								<div style="float: left;">
									<select size="10" style="width:250px;height:200px;display:inline-block;" name="field" ondblclick="removeOpt(this,1)"  ></select>						
								</div>
								<div style="float: left;">
									</br>
									<p>
										<input type="button" value="置顶" class="moveFieldTop">
									</p>
									</br>
									<p>
										<input type="button" value="上移" class="moveFieldUp" >
									</p>
									</br>
									<p>
										<input type="button" value="下移" class="moveFieldDown" >
									</p>
									</br>
									<p>
										<input type="button" value="置底" class="moveFieldBottom">
									</p>
								</div>

							</table>
			</fieldset>
	</div>
	
	<div  id="cloneSubTemplate"  style="display: none;">
			<fieldset style="margin: 5px 0px 5px 0px;" zone="sub" >
				<legend>
					<div>
						<div style="float: left; ">子表设置</div>
						<div class="group" style="float: right; ">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="link del"  title="删除"  onclick="delSubTable(this);"></a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class='link movetop' title="置顶"  onclick="moveTopBottom(this);"></a>
							<a class='link moveup' title="上移"   onclick="move(this);"></a>
							<a class='link movedown' title="下移" onclick="move(this);"></a>
							<a class='link movebottom' title="置底"  onclick="moveTopBottom(this);"></a>
						</div>
						<div style="clear:both;"></div>
					</div>
				</legend>
				<table cellpadding="1" cellspacing="1"  class="table-detail">
					<tr>
						<th width="10%">子表名称</th>
						<td><input type="hidden" name="tableName" /><input type="text" width="70%" name="tableDesc" class="inputText valid"   style="width:250px; "  validate="{required:true}"/></td>
					</tr>
					<tr>
						<th>子表字段</th>
						<td>
								<div style="float: left;">
									<select size="10" style="width:250px;height:200px;display:inline-block;" name="field"   ondblclick="removeOpt(this,3)" ></select>						
								</div>
								<div style="float: left;">
									</br>
									<p>
										<input type="button" value="置顶" class="moveFieldTop">
									</p>
									</br>
									<p>
										<input type="button" value="上移" class="moveFieldUp" >
									</p>
									</br>
									<p>
										<input type="button" value="下移" class="moveFieldDown" >
									</p>
									</br>
									<p>
										<input type="button" value="置底" class="moveFieldBottom">
									</p>
								</div>
							</table>
			</fieldset>
	</div>

</body>
</html>		