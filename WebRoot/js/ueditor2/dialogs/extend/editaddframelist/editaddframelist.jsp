<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="../input.css">
<link rel="stylesheet" type="text/css" href="${ctx}/styles/default/css/form.css">
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDialog2.js"></script>
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<style type="text/css">
	body{
		overflow:hidden;
	}
	a.extend{
	  display:inline;
	}
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
	width:340px;
	height:260px;
	background-color: powderblue;
	overflow-y: auto;
	overflow-x: hidden;
}
#fieldContainer{
	height:200px;
	overflow-y:auto;
	overflow-x:hidden;
}
#fieldTable{
	margin:0;
}
</style>
<script type="text/javascript">
	var _element;
	$(".button-td").bind("mouseenter mouseleave",function(){
		$(this).toggleClass("button-td-hover");
	});
	$(function() {
			var datatemplateStr = $(editor.curInput).get(0).getAttribute("datatemplate");
			var dataTemplate = eval("("+datatemplateStr+")" );
			var id = dataTemplate.name;
			var url= __ctx + "/platform/form/bpmDataTemplate/selectdata.ht" ;
			
	    $.post(url,{id},function(result){	
	    alert("返回 ");
     	});
			
			
			if (datatemplateStr) {
				bindData(datatemplateStr);
		}			
	});
	
	function openIconDialog() {
		var url= __ctx+"/js/ueditor2/dialogs/extend/addframelist/icons.jsp";			
		var dialogWidth=500;
		var dialogHeight=400;
		conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1});

		var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
			+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;			
		/* var rtn=window.showModalDialog(url,"",winArgs);
		if(rtn!=undefined){				
			$("#preview-button").attr("class",rtn.cla);
		} */
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:conf.dialogHeight,
			width: conf.dialogWidth,
			title : '',
			url: url, 
			isResize: true,
			//自定义参数
			sucCall:function(rtn){
				$("#preview-button").attr("class",rtn.cla);
			}
		});
		
	};

	function bindData(datatemplateStr) {
	
			alert("a");
			
			
		
		
		var dataTemplate = eval("("+datatemplateStr+")" );
		alert(dataTemplate.name);
        $("#Bsubject").val(dataTemplate.subject);
	    
			

		_element = editor.curInput;		
		var child = _element.childNodes[0];
		if (child) {
			var cla = $(child).attr("class"), label = $(child).text();
			$("#preview-button").attr("class", cla);
			$("#a-label").val(label);
		}
		
		
		
		

		
		
	};

	function selectNodeForm(obj) {
		FormDialog2({
			callbackwithValue:function(textValue,key){
				$("#inputArea").val(textValue);
				$("#tableId").val(key);
			}
		})
	};
	
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
/*		function bindData(dialogStr) {
			var dialog = eval("("+dialogStr+")" ),field;
			if(!dialog)return;
			$("#dialog-type").find("option[value='"+dialog.name+"']").each(function(){
					$(this).attr("selected","selected");
					dialogChange();
				}
			);
		
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
	*/	
		//获取自定义对话框
		function getDialogs(){
		    var url = __ctx + '/platform/form/bpmDataTemplate/getAllDataTemplates.ht';   //DataTemplates
			$.get(url,function(data){
				if (data) {
					for(var i=0,c;c=data[i++];){
					
						var opt = $('<option value="'+c.id+'" conditionfield="'+c.conditionField
								+'" >'+c.id+'</option>');
						opt.data("fields",c.resultfield);     //DataTemplates					
						opt.data("conditionfield",c.conditionField);    //DataTemplates					    
						$("#dialog-type").append(opt);
	
					}
					getFileds(editor.tableId);
				}
			});
		};		
		//选择不同的对话框
		function dialogChange(){
			var dia=$("#dialog-type").find("option:selected");
			var c = dia.data("conditionfield");
			if(c){
			    alert(c);
				var conditionfield = $.parseJSON(c);
				alert(conditionfield);
				//生成“绑定参数”下拉框选项
				var template = $('textarea#templateOption').val() ;
				var templateOptionHtml = easyTemplate(template,conditionfield).toString();
				$('#inputPanel table').find('.fieldSelect').html(templateOptionHtml);
			}
			
		}
		
		function openQuotaDialog() {
		CommonDialog("ywsjmb",function(data){
			var param = "";
				param = param + data.subject;
			var	ID = param + data.ID;
			var	TABLEID = param + data.TABLEID;
			var	FORMKEY = param + data.FORMKEY;
			var	NAME = param + data.NAME;
			var	ALIAS = param + data.ALIAS;
			var	STYLE = param + data.STYLE;
			var	NEEDPAGE = param + data.NEEDPAGE;
			var	PAGESIZE = param + data.PAGESIZE;
			var	TEMPLATEALIAS = param + data.TEMPLATEALIAS;
			var	TEMPLATEHTML = param + data.TEMPLATEHTML;
			var	DISPLAYFIELD = param + data.DISPLAYFIELD;
			var	EXPORTFIELD = param + data.EXPORTFIELD;
			var	PRINTFIELD = param + data.PRINTFIELD;
			var	CONDITIONFIELD = param + data.CONDITIONFIELD;
			var	SORTFIELD = param + data.SORTFIELD;
			var	MANAGEFIELD = param + data.MANAGEFIELD;
			var	FILTERTYPE = param + data.FILTERTYPE;
			var	FILTERFIELD = param + data.FILTERFIELD;
			var	VARFIELD = param + data.VARFIELD;
			var	SOURCE = param + data.SOURCE;
			var	DEFID = param + data.DEFID;
			var	ISQUERY = param + data.ISQUERY;
			var	ISFILTER = param + data.ISFILTER;
		   
		    $("#Bsubject").val(data.subject);
			$("#Bid").val(data.ID);
			if(data.CONDITIONFIELD){
				var conditionfield = $.parseJSON(data.CONDITIONFIELD);
				alert(conditionfield);
				//生成“绑定参数”下拉框选项
				var template = $('textarea#templateOption').val() ;
				var templateOptionHtml = easyTemplate(template,conditionfield).toString();
				$('#inputPanel table').find('.fieldSelect').html(templateOptionHtml);
			}
				
		    },"");				
	    }

		dialog.onok = function() {
//			var name=$("#dialog-type").val();
//			if(!name){
//				$(editor.curInput).removeAttr("dialog");
//				return;
//			}
			var subject = $("#Bsubject").val();
			var id = $("#Bid").val();
			
			var queryArr = [] ;
			$(':checkbox:checked','table .fieldTr').each(function(){
				var queryObj = {} ;
				queryObj.id = $(this).siblings(':text').attr('id') ;
				queryObj.name = $(this).siblings('select').val() ;
				if($(this).siblings(':text').attr('class')=='mainField'){
					queryObj.isMain = 'true' ;
				}else{
					queryObj.isMain = 'false' ;
				}
				queryArr.push(queryObj);
			});
			var queryString = JSON2.stringify(queryArr).replaceAll("\"","'") ;
		//	var json="{name:'"+name+"',fields:["+fields.join(',')+"],query:"+queryString+"}";
		alert(queryString);
		    var json="{name:'"+id+"',subject:'"+subject+"',fields:[],query:"+queryString+"}";
		    editor.curInput.setAttribute("datatemplate",json);
			editor.curInput = null;
		    
		    
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
			var dialogStr = $(editor.curInput).get(0).getAttribute("dialog");
			if (dialogStr) {
				bindData(dialogStr);
			}
		};

		//加载字段面板
		function getFileds(tableId) {
			if(tableId){
				var url = __ctx
						+ '/platform/form/bpmFormTable/getTableById.ht?tableId='
						+ tableId;
				$.post(url, function(data) {
					initFieldsDiv(data);
				});
			}
			else{	//编辑器设计表单时获取字段并验证字段
				var html = editor.getContent();		
				var params={};
				params.html=html;
				params.formDefId=editor.formDefId;
				
				
				$.post(__ctx + '/platform/form/bpmFormDef/validDesign.ht', params, function(data){
					if(data.valid){
						initFieldsDiv(data);
					}
					else{
						alert(data.errorMsg);
					}
				});
			}
		};
		
		//判断当前字段是否在表单上显示，如果存在这显示，不存在这不显示
		function removeObj(obj,fieldName){
			//当前表单所有字段
			var AllField=$(obj);
			for( var i=0 ;i<AllField.length; i++){
				var formName=$(AllField[i]).attr("name");
				if(!formName)continue;
				var name=formName.split(":");
				if(name[2]==fieldName){
					return false;
				}else{
					continue;
				}
			}
			return true;
		};
		
		
		
</script>
</head>
<body>

	<div id="inputPanel">
		<fieldset class="base">
			<legend><var id="lang_button_prop"></var></legend>
			<table>
				<tr>
					<th>按钮标签</var>:</th>
					<td><input id="a-label" type="text" /></td>
				</tr>
				<tr>
					<th>按钮图标</var>:</th>
					<td>
						<a href="javascript:;" id="preview-button"></a>				
						<div class="button-td" onclick="openIconDialog()">
						   	<var id="lang_choose_img"></var>
						</div>
					</td>
				</tr>
				 
				<tr>
                    <td><var id="lang_sub_table"></var>:<input  type="hidden" id="tableId"  value="0"/></td>
                    <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="formname" lablename="表单名称" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" id="inputArea" value="${pageload.formname}" /></span>
                    <a href="javascript:;" class="link get" onclick="selectNodeForm(this)" id="subNodeSel">选择自定义表单</a></td>
                </tr>
				
			</table>
		</fieldset>
	</div>
	<div id="inputPanel">
		<fieldset class="base">
			<legend><var id="lang_data_setting"></var></legend>
			<table>
				<tr>
				
					<th><var id="lang_choose_data"></var></th>
					<td>关联数据模板耶以
					<input type="text" id="Bsubject" class="inputText" name="Bsubject" value="" readonly="readonly">
							
							<a href='#' class='link search'  onclick="openQuotaDialog()" ></a>
						<input type="hidden" id="Bid"  class="inputText" name="Bid" value="">
					 
					<select id="dialog-type" onchange="dialogChange()">
						<option value="0"></option>
						
					</select>
					 
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="fieldContainer">
						<table id="fieldTable"></table>
						</div>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
	
	<textarea id="templateTr" style="display:none;">
	<#list data as a>
		<#if (!a.items)>
		<tr class="fieldTr">
			<th>
			<#if (a_index == 0)>主表参数</#if>
			</th>
			<td>
				<input type="checkbox" style="width:10px;" class="fieldCheck" />&nbsp;
				 主表字段 
				<input type="text" class="mainField" style="width:60px" value="\${a.name}" readonly id="\${a.id}"/>&nbsp;
				<select class="fieldSelect" style="width:95px;display:none;">
					<option></option>
				</select>
			</td>
		</tr>
		<#else>
			<#list a.items as sub>
			<tr class="fieldTr">
				<th><#if (sub_index == 0)>子表参数</#if></th>
				<td>
					<input type="checkbox" style="width:10px;" class="fieldCheck" />&nbsp;
					<input type="text" class="subField" style="width:60px" value="\${sub.name}" readonly id="\${sub.id}"/>&nbsp;
					<select class="fieldSelect" style="width:95px;display:none;">
						<option></option>
					</select>
				</td>
			</tr>
			</#list>
		</#if>
	</#list>
	</textarea>
	<textarea id="templateOption" style="display:none;">
	<#list data as o>
	 	<option value="\${o.field}">\${o.field}</option>  Dialogs  
	<option value="\${o.na}">\${o.na}</option>
	</#list>
	</textarea>
	
</body>
</html>