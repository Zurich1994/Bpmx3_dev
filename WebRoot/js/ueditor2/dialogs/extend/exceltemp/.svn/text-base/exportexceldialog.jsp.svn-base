<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="../input.css">
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>

<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.dragdiv.js"></script>
<link rel="stylesheet" href="${ctx}/js/jquery/plugins/jquery.dragdiv.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/js/tree/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/util/json2.js"></script>


<style type="text/css">
.field-ul {
	width: 95%;
	height: 95%; 
	margin: 0;
	padding: 0;
	overflow-y: auto;
	overflow-x: hidden;
}
.fields-div {
	float: left;
	border: 1px solid #828790;
	width: 160px;
	height: 260px;
	overflow: auto;
}
.domBtnDiv {
	display: block;
	margin-left:5px;
	float:left;
	width:380px;
	height:260px;
	background-color: powderblue;
	overflow-y: auto;
	overflow-x: hidden;
}
</style>
<script type="text/javascript">
	var dragDiv;
	var setting = {
			edit: {
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false,	
				drag:{}
			},				
			data: {
				keep: {
					parent: true,
					leaf: true
				},
				simpleData: {
					enable: true
				}
			},				
			view: {
				selectedMulti: false
			}
		};
	$(function() {
		$(".button-td").bind("mouseenter mouseleave", function() {
			$(this).toggleClass("button-td-hover");
		});
		getAllExcelTemp();
	});	
	
	//编辑时绑定数据
	function bindData(dialogStr) {
		var dialog = eval("("+dialogStr+")");
		var field;
		if(!dialog)return;
		$("#dialog-type").find("option[value='"+dialog.name+"']").each(function(){
				$(this).attr("selected","selected");
				excelTempChange();
			}
		);
		while(field=dialog.fields.pop()){
			var src=field.src;
			var targets=field.target;
			var target;
			while(target=targets.pop()){
				var item = $("span.item-span[itemId='"+target+"']").toggleClass("item-span item-span-Disabled");
				if(item.length>0){
					var node = {id:target, name: item.text()};
					addNode(src,node);
				}
			}
		}
	}; 
	
	//添加树节点
	function addNode(id,node){
		var zTree = $.fn.zTree.getZTreeObj('fields-tree');
		if(!zTree)return;
		var parentNode = zTree.getNodeByParam("id",id,null);
		if(parentNode) zTree.addNodes(parentNode,node);
	};
	
	//获取所有的Excel模板
	function getAllExcelTemp(){
		$.ajax({type : "post",
			url : __ctx + "/platform/system/sysExcelTemp/getAll.ht",
			async : false,
			success : function(data) {
				if (data) {
					for(var i=0,c;c=data[i++];){
						var opt = $('<option value="'+c.tempCode+'" >'+c.tempName+'</option>');
						$("#dialog-type").append(opt);
					}
					getFileds(editor.tableId);
				}
			}
		});
	};	 
	
	//选择不同的查询
	function excelTempChange(){
		var tempCode = $("#dialog-type").val();
		var url = __ctx + '/platform/system/sysExcelTemp/getAllDetailByTempCode.ht?tempCode='+tempCode;
		$.ajax({type : "post",
			url : url,
			async : false,
			success : function(data) {
				if (data) {
					var nodes=[];
					for(var i=0,c;c=data[i++];){
						nodes.push({id:c.showIndex,pid:-1,name:c.columnName,isParent: true, open:true});
					}
					var tree = $.fn.zTree.init($("#fields-tree"), setting, nodes);
					//设置拖拽 树对象
					if(dragDiv)dragDiv.dragdiv('bind','fields-tree');
				}
			}
		});
	} 
	
	// 确定按钮
	dialog.onok = function() {
		var name=$("#dialog-type").val();
		if(!name){
			editor.curInput.removeAttr("exportexcel");
			return;
		}
		
		var zTree = $.fn.zTree.getZTreeObj("fields-tree"),
		nodes=zTree.getNodes(),fields=[];
		
		for(var i=0,c;c=nodes[i++];){
			if(!c.children)continue;
			var target=[],child=null;				
			while(child=c.children.pop()){
				target.push(child.id);
			}
			var sub={
					src:c.id,
					target:target
			};
			fields.push(sub);
		}
		var json={
				name:name,
				fields:fields
		};
		
		var jsonStr = JSON2.stringify(json).replaceAll('"',"'");
		editor.curInput.setAttribute("exportexcel",jsonStr);
	};
	
	//初始化字段面板
	function initFieldsDiv(data){
		var mainTable = data.table, data = {};
		data.name = mainTable.tableDesc + '('+editor.getLang("customdialog.main")+')';
		data.id = mainTable.tableName;
		data.desc = mainTable.tableId;

		var items = [];
		/* for ( var i = 0, c; c = mainTable.fieldList[i++];) {
			items.push({
				name : c.fieldDesc,
				id : c.fieldName
			});
		} */

		for ( var i = 0, c; c = mainTable.subTableList[i++];) {
			var sub = {};
			sub.name = c.tableDesc + '('+editor.getLang("customdialog.sub")+')';
			sub.id = c.tableName;
			sub.desc = c.tableId;

			var subItems = [];
			for ( var y = 0, t; t = c.fieldList[y++];) {
				subItems.push({
					name : t.fieldDesc,
					id : c.tableName+"-"+t.fieldName
				});
			}
			sub.items = subItems;
			items.push(sub);
		}
		data.items = items;
		dragDiv = $(".domBtnDiv").dragdiv('init',{data : data});
		
		var dialogStr = editor.curInput.getAttribute("exportexcel");
		if (dialogStr) {
			bindData(dialogStr);
		}
	};

	//加载字段面板
	function getFileds(tableId) {
		if(tableId){
			var url = __ctx
					+ '/platform/form/bpmFormTable/getTableById.ht?tableId='
					+ tableId+'&incHide=true';
			$.post(url, function(data) {
				initFieldsDiv(data);
			});
		}else{	//编辑器设计表单时获取字段并验证字段
			var html = editor.getContent();		
			var params={};
			params.html=html;
			params.formDefId=editor.formDefId;
			
			$.post(__ctx + '/platform/form/bpmFormDef/validDesign.ht?incHide=true', params, function(data){
				if(data.valid){
					initFieldsDiv(data);
				}
				else{
					alert(data.errorMsg);
				}
			});
		}
	};
</script>
</head>
<body>
	<div id="inputPanel">
		<fieldset class="base">
			<legend><var id="lang_search_setting"></var></legend>
			<table>
				<tr>
					<th>导出excel模板:</th>
					<td>
						<select id="dialog-type" onchange="excelTempChange();">
							<option value="0"></option>
						</select>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="base">
			<legend><var id="lang_return_setting"></var></legend>
				<div class="fields-div">
					<ul id="fields-tree" class="ztree field-ul"></ul>
				</div>
				<div class="domBtnDiv">
				</div>
		</fieldset>
	</div>
</body>
</html>