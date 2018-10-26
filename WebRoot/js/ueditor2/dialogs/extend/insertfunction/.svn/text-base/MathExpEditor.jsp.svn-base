<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/get.jsp"%>
<html>
<head>
<title>数学函数编辑器</title>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerTab.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/MathCaltools.js"></script>
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<link rel="stylesheet" href="${ctx}/js/tree/zTreeStyle.css" type="text/css" />
<link href="${ctx}/styles/default/css/expression.css" rel="stylesheet" />
<script type="text/javascript">
	var scriptTree,
		scriptTypes = [],
		varTree,
		isSingleRecord = false,
		curElement;
	
	$(function() {
		var range = editor.selection.getRange();
		curElement = range.getClosedNode();
		 if(!curElement&&range){
            var startContainer = range.startContainer;
            if(startContainer &&  ('TD' == startContainer.nodeName.toUpperCase() || 'SPAN' == startContainer.nodeName.toUpperCase())){
            	curElement = startContainer.childNodes[range.startOffset];
            }
	      }
		
		if(curElement){
			var funcexp = $(curElement).attr("funcexp");
			if(funcexp)
				$("#exp_text").text(funcexp);
		}
	       
		
		
		$("div.top_left_div").ligerTab();
		initTools(MathCaltools.commonTools,$("#tools_comment"));
		initTools(MathCaltools.subTableTools,$("#sub_tools_comment"));
		$(".calTool").bind("mouseenter mouseleave", function() {
			$(this).toggleClass("tool-hover");
		});
		$(".toolbar_btn").bind("mouseenter mouseleave", function() {
			$(this).toggleClass("toolbar_btn_hover");
		});
		if(editor.tableId){
			getFieldsByTableId({tableId:editor.tableId});
		}
		else{
			if(editor.formDefId){
				getFieldsByTableId({formDefId:editor.formDefId});
			}
		}
	});
	
	dialog.onok = function() {
		if(!curElement)return true;
		var funcexp = InitMirror.editor.getCode();
		if(funcexp)
			$(curElement).attr("funcexp",funcexp);
		else
			$(curElement).removeAttr("funcexp");
	};
	
	function clickHandler() {
		var exp = $(this).attr("exp");
		InitMirror.editor.insertCode(exp);
		if(!$.browser.msie){
			//某些运算符 添加到脚本中以后  需要修改光标所在的位置
			InitMirror.editor.moveCursorPos($(this).attr("step"));
		}
	}
	
	//初始化运算符工具窗口
	function initTools(calTools,t) {
		var tool;
		while (tool = calTools.shift()) {
			var div = document.createElement("div");
			div.title = tool.msg;
			div.innerHTML = tool.title;
			div.className = "calTool";
			div.setAttribute("exp", tool.exp);
			if(tool.step){
				div.setAttribute("step", tool.step);
			}
			div.onclick = tool.clickHandler || clickHandler;
			t.append(div);
		}
	}
	
	//获取表单字段
	function getFieldsByTableId(param) {
		var iconFolder = __ctx + '/styles/tree/';
		$.post(__ctx + '/platform/form/bpmFormDef/getAllFieldsByTableId.ht',param ,function(data) {
			var json = eval("("+data+")"),
				treeData = [];
			$('#tableName').val(json.mainname);
			
			treeData.push({tableId:json.mainid,name:json.mainname,id:0,pId:0,icon:iconFolder + 'table.png',open:true});
			
			for(var i=0,c;c=json.mainfields[i++];){
				if(c.isHidden == 0){
					c.tableId = json.mainid;
					c.tablename = json.tablename;
					c.name = c.fieldDesc;
					c.id = c.fieldId;
					c.pId = 0;
					c.icon = iconFolder + c.fieldType + '.png';
					treeData.push(c);
				}
			}
			
			for(var i=0,c;c=json.subtables[i++];){
				c.icon = iconFolder + 'table.png';
				c.pId = 0;
				c.tableId = c.id;
				treeData.push(c);
				for(var j = 0,m;m=c.subfields[j++];){
					m.tableId = c.id;
					m.tablename = c.tablename;
					m.pId = c.id;
					m.name = m.fieldDesc;
					m.icon = iconFolder + m.fieldType + '.png';
					treeData.push(m);
				}
			}
			
			var setting = {       				    					
	   				data: {
	   					key : {
	   						name: "name"
	   					},
	   					simpleData: {
	   						enable: true,
	   						idKey: "id",
	   						pIdKey: "pId",
							rootPId: 0
	   					}
	   				},
	   				callback : {
	   					onDblClick : zTreeOnDblClick
	   				}
	   			};
			glTypeTree = $.fn.zTree.init($("#colstree"),setting, treeData);
		});
	};
	//双击树添加内容到规则表达式
	function zTreeOnDblClick(event, treeId, treeNode) {
		if(!treeNode)return;
		if(treeNode.fieldType!="number"){
			$.ligerDialog.warn('必须选择数字类型的字段!','提示信息');
			return;
		}
		var str = ['(m:'],
			isMain = treeNode.pId=='0',
			desc;
		
		if(!isMain){
			str = ['(s:'];
		}
		str.push(treeNode.tablename);
		str.push(':');
		str.push(treeNode.fieldName);
		str.push(')');
		
		if(isSingleRecord){
			if(isMain){
				$.ligerDialog.warn('子表中单条记录运算模式下,不能选择主表字段!','提示信息');
				return;
			}
			desc = ['{'];
			desc.push(treeNode.fieldDesc);
			desc.push(str.join(''));
			desc.push('}');
		}
		else{
			desc = ['{'];
			if(!isMain){
				desc = ['['];
			}
			desc.push(treeNode.fieldDesc);
			desc.push(str.join(''));
			if(!isMain){
				desc.push(']');
			}
			else{
				desc.push('}');
			}
		}
		InitMirror.editor.insertCode(desc.join(''));
	};

	//是否子表单条记录运算
	function singleSubRecord(t){
		isSingleRecord = $(t).attr("checked")?true:false;
		if(isSingleRecord){
			$("#sub_tools_comment").hide();
		}
		else{
			$("#sub_tools_comment").show();
		}
	};
</script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
</head>
<body>
	<div class="math_div">
		<div class="top_div">
			<div class="field_left_div" style="overflow:auto;">
				<div title="表字段">
					<ul id="colstree" style="margin:5px;" class="ztree"></ul>
				</div>
			</div>
			<div class="cal_right_div">
				<div style="height:60%;">
					<div class="l-tab-links title-div">
						<span style="margin-left:8px;">通用运算符</span>
					</div>
					<div id="tools_comment"></div>
				</div>
				
				<div style="height:40%;">
					<div class="l-tab-links title-div" style="border-top:1px solid #A7D3F0;">
						<span style="margin-left:8px;">子表字段运算符</span>
					</div>
					<div id="sub_tools_comment"></div>
				</div>
			</div>
		</div>
		<div class="bottom_div">
			<div class="l-tab-links title-div">
				<div style="width:100%;height:100%;font-weight:normal;padding-left:8px;">
					<span style="font-weight:bold;">运算表达式</span>
					<label style="margin-left:8px;"><input type="checkbox" onclick="singleSubRecord(this)"/>子表中单条记录运算</label>
				</div>
			</div>
			<textarea id="exp_text" codemirror="true" mirrorheight="140px" cols="110" rows="6"></textarea>
		</div>
	</div>
</body>
</html>