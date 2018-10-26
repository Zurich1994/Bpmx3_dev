<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@include file="/commons/include/get.jsp"%>
<html>
<head>
<title>规则表达式</title>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerTab.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript" src="${ctx }/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/expressionMakerDialog.js"></script>
<link rel="stylesheet" href="${ctx }/js/tree/zTreeStyle.css" type="text/css" />
<link href="${ctx}/styles/default/css/expression.css" rel="stylesheet" />
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
	var calTools = [ {exp : 'like',title : 'like',msg : '类似'}, {exp : 'not',title : 'not',	msg : '不是'}, 
	                 {exp : '||',title : 'or',msg : '或'}, {exp : '&&',title : 'and',	msg : '并'}, 
	                 {exp : '!=',title : '!=',msg : '不等于'}, {	exp : '=',title : '=',msg : '等于'}, 
	                 {exp : '<',title:'&lt;',msg:'小于'},{exp:'>',title : '>',msg : '大于'},
	                 {exp : ')',title : ')',msg : '右括号'}, {exp : '(',	title : '(',msg : '左括号'},
	                 {exp : '/',title : '÷',msg : '除'}, {exp : '*',	title : '×',msg : '乘'}, 
	                 {exp : '-',title : '-',msg : '减'}, {exp : '+',	title : '+',msg : '加'} ],
       scriptTree, scriptTypes = [], varTree;
	function clickHandler() {
		var exp = $(this).attr("exp");
		InitMirror.editor.insertCode(exp);
	}
	//初始化运算符工具窗口
	function initTools() {
		var tool;
		while (tool = calTools.pop()) {
			var div = document.createElement("div");
			div.title = tool.msg;
			div.innerHTML = tool.title;
			div.className = "calTool";
			div.setAttribute("exp", tool.exp);
			div.onclick = tool.clickHandler || clickHandler;
			$("#tools_comment").append(div);
		}
		$(".calTool").bind("mouseenter mouseleave", function() {
			$(this).toggleClass("tool-hover");
		});
		$(".toolbar_btn").bind("mouseenter mouseleave", function() {
			$(this).toggleClass("toolbar_btn_hover");
		});
	}
	//脚本数据返回结果，构建脚本树
	function showResult(result) {
		var setting = {
			data : {
				key : {
					name : "name"
				},
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "category",
					rootPId : 0
				}
			},
			view : {
				selectedMulti : false
			},
			callback : {
				onDblClick : zTreeOnDblClick
			}
		};
		for ( var i = 0; i < result.length; i++) {
			var script = result[i];
			script.icon = __ctx + "/styles/default/images/icons/icons_34.png";
			var typeId = hasExist(script.category);
			if (typeId) {
				script.category = typeId;
			} else {
				scriptTypes.push({
					name : script.category,
					id : 0-i - 1
				});
				script.category =0- i - 1;
			}
		}
		var type;
		while (type = scriptTypes.pop()) {
			result.push({
				id : type.id,
				name : type.name,
				category : 0
			});
		}
		
		//alert(obj2str(result));字符串太长，不能全部显示，复制也不方便
		//用div来显示要展示的值
		//$("#resulttostr").html(obj2str(result));
		scriptTree = $.fn.zTree.init($("#scriptTree"), setting, result);
		scriptTree.expandAll(true);
	}
	
	//object 转 stirng ,测试树形数据用
	/*function obj2str(o){
	   var r = [];
	   if(typeof o == "string" || o == null) {
	     return o;
	   }
	   if(typeof o == "object"){
	     if(!o.sort){
	       r[0]="{"
	       for(var i in o){
	         r[r.length]=i;
	         r[r.length]=":";
	         r[r.length]=obj2str(o[i]);
	         r[r.length]=",";
	       }
	       r[r.length-1]="}"
	     }else{
	       r[0]="["
	       for(var i =0;i<o.length;i++){
	         r[r.length]=obj2str(o[i]);
	         r[r.length]=",";
	       }
	       r[r.length-1]="]"
	     }
	     return r.join("");
	   }
	   return o.toString();
	}*/
	//判断是否已添加该脚本类型
	function hasExist(d) {
		for ( var i = 0; i < scriptTypes.length; i++) {
			var type = scriptTypes[i];
			if (type.name == d) {
				return type.id;
			}
		}
		return false;
	}
	//获取脚本数据
	function getScript() {
		var url = __ctx + "/platform/system/script/getScripts.ht";
		$.post(url, null, showResult);
	}
	$(function() {
		$("div.top_left_div").ligerTab();
		initTools();
		//getVar();
		getScript();
	});
	//获取流程变量，构建变量树
	function getVar() {
		var url = __ctx + "/platform/bpm/bpmDefVar/getVars.ht";
		$.post(url, {
			defId : "1343718637626"
		}, function(result) {
			var setting = {
				data : {
					key : {
						name : "varName"
					},
					simpleData : {
						enable : true,
						idKey : "varId"
					}
				},
				view : {
					selectedMulti : false
				},
				callback : {
					onDblClick : zTreeOnDblClick
				}
			};
			varTree = $.fn.zTree.init($("#varTree"), setting, result);
			varTree.expandAll(true);
		});
	}
	//双击树添加内容到规则表达式
	function zTreeOnDblClick(event, treeId, treeNode) {
		var str = treeNode.varKey || treeNode.script;
		InitMirror.editor.insertCode(str);
	};

	function valiExpression() {
		var script = InitMirror.editor.getCode();
		$.ligerDialog.waitting('正在验证,请耐心等候... ');
		var url = __ctx + "/platform/console/getValidateResult.ht";
		$.post(url, {
			script : script
		}, function(obj) {
			$.ligerDialog.closeWaitting();
			if(obj.hasError){
				showResultDialog("error",obj.errorMsg);
			}
			else{
				var html=['<table class="result_table"><tr><th>结果值类型:</th><td>'];
				html.push(obj.resultType);
				html.push('</td></tr><tr><th>结果值:</th><td>');
				html.push(obj.result);
				html.push('</td></tr></table>');
				showResultDialog("",html.join(''));
			}
		});
	};
	
	//显示验证结果
	function showResultDialog(type,html){
		var dialog = $.ligerDialog.open( 
		{
			width:500,
			type: type,
			isDrag:false,
			title:'对话框弹出演示标题', 
			content: html, 
			buttons: [{ text: '确定', onclick: function(){dialog.close();} }] 
		});
	}

	function clickCancel() {
		dialog.close();
	};

	function clickOk() {
		var v = InitMirror.editor.getCode();
		//window.returnValue = v;
		dialog.get("sucCall")(v);
		
		dialog.close();
	};
</script>
</head>
<body>
	<div class="comment_div">
		<div class="top_div">
			<div class="top_left_div">
<!-- 				<div title="流程变量"> -->
<!-- 					<ul id="varTree" class="ztree expre-ul"></ul> -->
<!-- 				</div> -->
				<div title="脚本">
					<ul id="scriptTree" class="ztree expre-ul"></ul>
				</div>
			</div>
			<div class="top_right_div">
				<div class="l-tab-links title-div">&nbsp;&nbsp;表达式运算符</div>
				<div id="tools_comment"></div>
			</div>
		</div>
		<div class="bottom_div">
			<div class="l-tab-links title-div">&nbsp;&nbsp;规则表达式 </div>
			<!--测试用  <div id="resulttostr"></div> -->
			<textarea id="exp_text" codemirror="true" mirrorheight="140px" cols="110" rows="6"></textarea>
			<div class="bottom_toolbar">
				<span class="calTool toolbar_btn" onclick="valiExpression()">验证表达式</span>
				<span class="calTool toolbar_btn" style="float: right;"
					onclick="clickCancel()">取消</span> <span class="calTool toolbar_btn"
					style="float: right;" onclick="clickOk()">确定</span>
			</div>
		</div>
	</div>
</body>
</html>