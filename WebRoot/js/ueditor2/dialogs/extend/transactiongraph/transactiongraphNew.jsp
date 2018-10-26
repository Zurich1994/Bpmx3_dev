<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="../input.css">
<link rel="stylesheet" type="text/css" href="${ctx}/styles/default/css/form.css">
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<style type="text/css">
	body{
		overflow:hidden;
	}
	a.extend{
	  display:inline;
	}
</style>
<script type="text/javascript"><!--
	var obj = $(editor.curInput);
		var bodyObj=obj.parents('body');
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
			},
			dragDiv;

		$(function() {
			$(".button-td").bind("mouseenter mouseleave", function() {
				$(this).toggleClass("button-td-hover");
			});
			getDialogs();
			
			$('.fieldCheck','.fieldTr').live("click",function(){
				$(this).siblings('.fieldSelect').each(function(){
					if($(this).css('display')!='none'){
						$(this).hide() ;
					}else{
						$(this).show() ;
					}
				});
				
			});
			
		});		
		//编辑时绑定数据
		function bindData(dialogStr) {
			var dialog = eval("("+dialogStr+")" ),field;
			if(!dialog)return;
			$("#dialog-type").find("option[value='"+dialog.name+"']").each(function(){
					$(this).attr("selected","selected");
					dialogChange();
				}
			);
			while(field=dialog.fields.pop()){
				var src=field.src,
					targets=field.target.split(','),target;
				while(target=targets.pop()){
					var item = $("span.item-span[itemId='"+target+"']").toggleClass("item-span item-span-Disabled");
					if(item.length>0){
						var node = {id:target, name: item.text()};
						addNode(src,node);
					}
				}
			}
			var dialogQueryArr = dialog.query;
			for(var i=0;i<dialogQueryArr.length;i++){
				var isMain = dialogQueryArr[i].isMain ;
				$(":text#"+dialogQueryArr[i].id,".fieldTr").each(function(){
					//字段isMain和属性class==mainfield同时为true或者false时
					if((isMain=="true" ^ $(this).attr("class")=="mainField")==0){
						$(this).siblings(".fieldCheck").click() ;
						$(this).siblings(".fieldSelect").find("option[value='"+dialogQueryArr[i].name+"']").each(function(){
							$(this).attr("selected","selected");
						});
					}
				});
			}
		};
				//初始化字段面板
		function initFieldsDiv(data){
			var mainTable = data.table, data = {};
			data.name = mainTable.tableDesc + '('+editor.getLang("customdialog.main")+')';
			data.id = mainTable.tableName;
			data.desc = mainTable.tableId;
			var formMainField=mainTable.tableName?$(bodyObj).find('[name*="'+mainTable.tableName+'"]'):[];
			
			var items = [];
			for ( var i = 0, c; c = mainTable.fieldList[i++];) {
				if(c.isHidden!=0) continue ;
				//判断当前字段是否在表单上显示，如果存在这显示，
				if(formMainField.length != 0 && removeObj(formMainField,c.fieldName))continue;
				items.push({
					name : c.fieldDesc,
					id : c.fieldName
				});
			}

			for ( var i = 0, c; c = mainTable.subTableList[i++];) {
				var sub = {};
				sub.name = c.tableDesc + '('+editor.getLang("customdialog.sub")+')';
				sub.id = c.tableName;
				sub.desc = c.tableId;
				var currSubField=$(bodyObj).find('[name*="'+c.tableName+'"]');
				var subItems = [];
				for ( var y = 0, t; t = c.fieldList[y++];) {
					//判断当前字段是否在表单上显示，如果存在这显示，不存在这不显示
					if(formMainField.length != 0 && removeObj(currSubField,t.fieldName))continue;
					subItems.push({
						name : t.fieldDesc,
						id : t.fieldName
					});
				}
				sub.items = subItems;
				items.push(sub);
			}
			//生成“绑定参数”行
			var template = $('textarea#templateTr').val() ;
			var templateTrHtml = easyTemplate(template,items).toString();
			$('#fieldTable').append(templateTrHtml);
			
			var parentTableClass = $(editor.curInput).closest('div[type="subtable"]') ;
			if(!parentTableClass || parentTableClass.length<=0){
				//若为空，表示主表，则隐藏子表字段
				$('.subField').each(function(){
					$(this).closest('.fieldTr').hide() ;
				})
			}
			
			data.items = items;
			//初始化字段面板
			dragDiv = $(".domBtnDiv").dragdiv('init',{data : data});
			var dialogStr = $(editor.curInput).get(1).getAttribute("transactiongraphNew");
			if (dialogStr) {
				bindData(dialogStr);
			}
		};
	//zl在线流程设计跳转
       function design(formData, jqForm, options) { 
			//var operatortype=$("#operatortype").val();
				/*if(operatortype=="0"){
					$.ligerDialog.warn("请选择操作类型",'提示信息');
					return false;
				}*/
				var nurl =__ctx + "/platform/bpm/bpmDefinition/designBtn.ht?defId=${defbId}";
						window.open(nurl);
			};
	function openIconDialog() {
		
		
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:conf.dialogHeight,
			width: conf.dialogWidth,
			title : '',
			url: url, 
			isResize: true,
			//自定义参数
			sucCall:function(rtn){
				$("#preview-buttonNew").attr("class",rtn.cla);
			}
		});
		
	};

	

	dialog.onok = function() {
		var html = '<span name="editable-input" style="display:inline-block;">';
		var label = $("#a-labelNew").val(),
		cla = $("#preview-buttonNew").attr("class");
		html += '<a href="javascript:;" ';
		if(cla)html+='class="'+cla+'"';
		html +='>' + label + '</a>';
		html += '</span> ';
		if(_element){
			editor.curInput.outerHTML=html;
		}
		else{
			var child = utils.parseDomByString(html);
			var start = editor.selection.getStart();
			if(!start||!child)return;
			if(start.tagName=='TD'){
				start.appendChild(child);
			}
			else{
				start = domUtils.findEditableInput(start);
				utils.insertAfter(child, start);
			}
		}
	};

--></script>
</head>
<body>
	<div id="inputPanel">
		<fieldset class="base">
			<legend><var id="lang_button_propNew"></var></legend>
			<table>
				<tr>
					<th><var id="lang_button_labelNew"></var>:</th>
					<td><input id="a-labelNew" type="text" /></td>
				</tr>
				<tr>
					<th><var id="lang_button_imgNew"></var>:</th>
					<td>
						<a href="javascript:;" id="preview-buttonNew"></a>				
						<div class="button-td" onclick="design()">
						   	<var id="lang_draw_buttonNew"></var>
						</div>
					</td>
				</tr>				
			</table>
		</fieldset>
	</div>
	
</body>
</html>
