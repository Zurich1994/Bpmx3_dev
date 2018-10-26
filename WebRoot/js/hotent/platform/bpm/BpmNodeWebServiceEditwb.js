var wsTree,
menu,
menu_root,
wsHeight, 
varHeight,
varTree,
rMenu ,
zTree,
dialog,
parent,
myparent,
key,
names = [],
_seq = 1;// 当前页面的ID随机种子
$(function() {
	// 布局
	rMenu = $("#rMenu");
	$("#webLayout").ligerLayout({
		leftWidth : 350,
		height : '100%',
		allowLeftResize : false
	});
	$('#wsdlTxt').bind('focus', function(event) {
		var defaultVal = '请输入表名';
		$('#wsdlTxt')[0].defaultValue = defaultVal;
		if($('#wsdlTxt').val()==defaultVal)
			$('#wsdlTxt').val('');
	}).bind('blur', function() {
		var txtValue = $('#wsdlTxt').val();
		if ($.trim(txtValue).length < 1) {
			$('#wsdlTxt').val($('#wsdlTxt')[0].defaultValue);
		}
	});
	/*
	 * 在展示已绑定参数时可能存在问题
	 */
	type=$("#htype").val();
	actDefId=$("#actDefId").val();
	defId=$("#defId").val();
	nodeId=$("#nodeId").val();
	if(type=='select'){
		$('#resulttr').show();
	}
	// 流程变量树
	varHeight = $('#varLayout').height();
	loadVarTree();
	$("#dataFormSave").click(function(){saveBinding();});
	//为选择绑定类型的下拉框设置处理
	$('.table-detail').delegate("select[name=bindingType]","change",function() {
		var table = $(this).closest("table");
		$('tr[bingdingType]',table).hide();
		$('tr[bingdingType=' + $(this).val() + ']',table).show('fast');
	});
	//加载已邦定数据
	loadExist();
	if(existDocument)
		parseNodeTable(existDocument);
	$("#add_custom").click(function(){
		if(dialog==null){
			dialog = $.ligerDialog.open({title:'添加自定义参数',target:$("#custom_param_div"),width:400,height:250,buttons:
				[ {text : '确定',onclick: updateCustomParam},
				  {text : '取消',onclick: function (item, dialog) {
					dialog.hide();
				}
				  }]});
		}
		dialog.show();
	});
});
function updateCustomParam(item, dialog){
	var parent = $("#custom_param_div"),
	id = $("input[name='id']",parent).val(),
	name = $("input[name='name']",parent).val(),
	paramType = $("select[name='paramType']",parent).val(),
	description = $("input[name='description']",parent).val();

	if(!name){
		$.ligerDialog.warn('提示信息','参数名称不能为空');
	}
	var newNode = {id:id,name:name,paramType:paramType,description:description};
	newNode = varStruTree.addNodes(null, newNode);
	dialog.hide();
}
//加载变量树
function loadVarTree(){
	var setting = {
	data: {
		key:{name:"name"}			
	},
	async: {enable: false},
	view: {
		selectedMulti: false
	},
	edit: {
		drag: {
			prev: false,
			inner: false,
			next: false,
			isMove:true
	},
	enable: true,
	showRemoveBtn: false,
	showRenameBtn: false
	}
	};
	function getChildren(names,treeNode){
		names.push(treeNode.name);
		 if (treeNode.isParent){
				for(var obj in treeNode.children){
					getChildren(names,treeNode.children[obj]);
				}
		    }
		 return names;
	};
	var url=__ctx + "/platform/bpm/bpmDefVar/getTree.ht";
	var params={defId:defId,nodeId:nodeId,actDefId:actDefId};
	$.post(url,params,function(result){	
		var json = eval('(' + result + ')');
		varStruTree=$.fn.zTree.init($("#varTree"), setting,json);
		varStruTree.expandAll(true);
		$("#varTree").height(varHeight-20);
	});
	//拖拽变量树上的字段到输入、输出字段上
	$(".drag-span").dragspan({buddy : 'varTree',treeDropHandler:function(t,n){
		t = $(t);
		t.text(n.name);
		t.attr("varId",n.id);
		t.closest("tr").next().find("td").html(n.paramType);
		getChildren(names,n);
		var pNode = n.getParentNode();
		//key = pNode.name;
		while(pNode.getParentNode().name!="前序引擎") {
		    pNode = pNode.getParentNode();
		    myparent = pNode.name;
		}
	}});
};	
//数据库中已经有设置的记录了，此时需要回显到页面
function parseNodeTable(v){
	var v = eval("("+v+")");
	for(var i=0,c;c=v[i++];){
//		addMethodNode(c,true);
		addSelectMethodNode(c,true);
	}
};

//保存的处理
function saveBinding(){
	var json = [];
	$("fieldset[zone=method]").each(function(){
		var me = $(this),
		methodInfo = me.data("methodInfo"),
		inputDiv = $("div[var=inputTreeEdit]",me),
		ouputDIv = $("div[var=outputTreeEdit]",me),
		resultDIv = $("div[var=resultTreeEdit]",me),
		inputTable = $(".table-detail[zone=binding]",inputDiv),
		outputTable = $(".table-detail[zone=binding]",ouputDIv),
		resultTable = $(".table-detail[zone=binding]",resultDIv),
		inputDiv1 = $("div[var=inputTreeEdit1]",me),
		ouputDIv1 = $("div[var=outputTreeEdit1]",me),
		resultDIv1 = $("div[var=resultTreeEdit1]",me),
		inputTable1 = $(".table-detail[zone=binding]",inputDiv1),
		outputTable1 = $(".table-detail[zone=binding]",ouputDIv1),
		resultTable1 = $(".table-detail[zone=binding]",resultDIv1)
		obj = {};

		if(methodInfo){
			obj.inputParams = methodInfo.conditionField.children;
			obj.outputParams = methodInfo.setField.children;
			if(type=="select"){
				obj.resultParams= methodInfo.resultField.children;
			}
		}
		obj.url = $("input[var=invokeUrl]",me).val(); 
		if(!obj.url)return true;
		obj.serviceName = $("input[var=serviceName]",me).val();
		obj.soapaction = $("input[var=soapaction]",me).val();
		obj.method = $("td[var=method]",me).text();
		obj.namespace = $("td[var=namespace]",me).text();
		obj.inputs = [];
		//遍历所有的入参设定
		for(var i=0,c;c=inputTable[i++];){
			var param = {};				
			param.name = $("span[var=name]",c).text();
			param.fullpath = $("input[var=fullpath]",c).val();
			param.soapType = $("td[var=type]",c).text();
			param.bindingType = $("select[name=bindingType]",c).val();
			var binding = $("[name='defValue" + param.bindingType +"']",c);
			param.bindingVal = binding.val()?binding.val():binding.text();
			param.bindingVal = param.bindingVal.jsonEscape();
			param.zdragparents = myparent;
			param.zdragchildren = names;
			param.javaType = javaType = $("[name=javaType]",c).text();
			obj.inputs.push(param);
		}
		for(var i=0,c;c=inputTable1[i++];){
			var param = {};				
			param.name = $("span[var=name]",c).text();
			param.fullpath = $("input[var=fullpath]",c).val();
			param.soapType = $("td[var=type]",c).text();
			param.bindingType = $("select[name=bindingType]",c).val();
			var binding = $("[name='defValue" + param.bindingType +"']",c);
			param.bindingVal = binding.val()?binding.val():binding.text();
			param.bindingVal = param.bindingVal.jsonEscape();
			param.zdragparents = myparent;
			param.zdragchildren = names;
			param.javaType = javaType = $("[name=javaType]",c).text();
			obj.inputs.push(param);
		}
		obj.outputs = [];
		//遍历所有的出参设定
		for(var i=0,c;c=outputTable[i++];){
			var param = {};
			param.name = $("span[var=name]",c).text();
			param.fullpath = $("input[var=fullpath]",c).val();
			param.soapType = $("td[var=type]",c).text();
			param.bindingType = $("select[name=bindingType]",c).val();
			var binding = $("[name='defValue" + param.bindingType +"']",c);
			param.bindingVal = binding.val()?binding.val():binding.text();
			param.bindingVal = param.bindingVal.jsonEscape();
			param.zdragparents = myparent;
			param.zdragchildren = names;
			param.javaType = javaType = $("[name=javaType]",c).text();
			obj.outputs.push(param);
		}
		for(var i=0,c;c=outputTable1[i++];){
			var param = {};
			param.name = $("span[var=name]",c).text();
			param.fullpath = $("input[var=fullpath]",c).val();
			param.soapType = $("td[var=type]",c).text();
			param.bindingType = $("select[name=bindingType]",c).val();
			var binding = $("[name='defValue" + param.bindingType +"']",c);
			param.bindingVal = binding.val()?binding.val():binding.text();
			param.bindingVal = param.bindingVal.jsonEscape();
			param.zdragparents = myparent;
			param.zdragchildren = names;
			param.javaType = javaType = $("[name=javaType]",c).text();
			obj.outputs.push(param);
		}
		//	if(type=="select"){
		obj.results = [];
		//遍历所有的出参设定
		for(var i=0,c;c=resultTable[i++];){
			var param = {};
			param.name = $("span[var=name]",c).text();
			param.fullpath = $("input[var=fullpath]",c).val();
			param.soapType = $("td[var=type]",c).text();
			param.bindingType = $("select[name=bindingType]",c).val();
			var binding = $("[name='defValue" + param.bindingType +"']",c);
			param.bindingVal = binding.val()?binding.val():binding.text();
			param.bindingVal = param.bindingVal.jsonEscape();
			param.zdragparents = myparent;
			param.zdragchildren = names;
			param.javaType = javaType = $("[name=javaType]",c).text();
			obj.results.push(param);
			//	}
		}
		for(var i=0,c;c=resultTable1[i++];){
			var param = {};
			param.name = $("span[var=name]",c).text();
			param.fullpath = $("input[var=fullpath]",c).val();
			param.soapType = $("td[var=type]",c).text();
			param.bindingType = $("select[name=bindingType]",c).val();
			var binding = $("[name='defValue" + param.bindingType +"']",c);
			param.bindingVal = binding.val()?binding.val():binding.text();
			param.bindingVal = param.bindingVal.jsonEscape();
			param.zdragparents = myparent;
			param.zdragchildren = names;
			param.javaType = javaType = $("[name=javaType]",c).text();
			obj.results.push(param);
			//	}
		}
		json.push(obj);
	});
	json = JSON2.stringify(json);
	var url = __ctx + "/platform/bpm/bpmNodeWebService/savewb.ht";
	$.post(url,{setId:'1',json:json,nodeId:nodeId,actDefId:actDefId},function(d){
		var result = eval("("+d+")");		
		if(result.success)
			$.ligerDialog.success(result.msg,"提示信息",function(){
				window.close();	
			});
		else
			$.ligerDialog.error(result.msg,"出错了");
	});
};

//添加
var addMethodNode = function(t,depParse) {
	var params = {
			methodId : t.url,
			func_name : t.method,
			table_name : t.serviceName
	};
	var setting = {
			view : {
		addDiyDom : function(treeId, treeNode) {
		var type = treeNode.type;
		var aObj = $("#" + treeNode.tId + "_a");
		aObj.append('<span style="margin-left:5px;">(' + type+ ')</span>');
	},
	selectedMulti : false
	},
	check : {
		enable : true,
		chkboxType : {
		"Y" : "",
		"N" : ""
	}
	},
	callback : {
		beforeCheck : function(treeId, treeNode) {
		if (!treeNode.type) {
			return true;
		}
		if (treeNode.checked) {
			if (confirm("是否取消当前参数[" + treeNode.name + "]的绑定配置?")) {
				var $div = $('#' + treeId + "-edit");
				if ($div.length < 1) {
					return;
				}						
				$("#" + treeNode.name + "_" + rep_list(treeNode.type) +"-edit", $div).remove();

				return true;
			} else {
				return false;
			}
		}
		return true;
	},
	onCheck : function(event, treeId, treeNode) {// 选中事件
		var $div = $('#' + treeId + "-edit");
		if ($div.length < 1) {
			return;
		}
		if (!treeNode.type) {
			return;
		}
		if (treeNode.checked) {// 选中
			$('#' + treeNode.tId + '_a').click();
		} else {
			var $editTable = $("#" + treeNode.tId + "-edit", $div);
			$editTable.remove();
		}
	},
	onClick : function(event, treeId, treeNode) {// 单击事件
		// 没有选中,则不需要处理
		if (!treeNode.checked) {
			return;
		}
		var $div = $('#' + treeId + "-edit");
		cloneBindingTable(treeNode,$div);
	}
	}
	};
	$.ajax({
		type : "POST",
		url : __ctx + "/platform/bpm/bpmDefVar/getMethodParam.ht",
		//url : __ctx + "/dbFunc/dbFunc/dbFunc/searchforfield.ht",
		data : params,
		//data : methedId,
		dataType : "json",
		success : function(result) {
		var json = eval('(' + result + ')');
		if (!json.success) {
			$.ligerDialog.warn(json.msg,"提示信息");
			return false;
		}
		var operatorField = $("#editField [zone=method]").clone(true, true);// 深度克隆
		$(operatorField).data("methodInfo",json);
		$("[var=wsdl]", operatorField).html(json.wsdl);
		$("[var=method]", operatorField).html(json.method);
		$("[var=namespace]", operatorField).html(json.namespace);
		$("[var=soapaction]", operatorField).val(json.soapaction);
		$("[var=invokeUrl]", operatorField).val(json.invokeUrl);
		$("[var=serviceName]",operatorField).val(params.serviceName);
		$("[var=del]", operatorField).unbind();// 删除所有事件
		$("[var=del]", operatorField).click(function(e) {
			$.ligerDialog.confirm("删除当前方法[" + json.method
					+ "]?","提示信息", function(rtn) {
				if (rtn) {
					operatorField.fadeOut(500, function() {
						$(this).remove();
					});
				}
			});
		});
		var $inputTreeUl = $("[var=inputTree]", operatorField);
		$inputTreeUl.attr("id", "inputtree" + (_seq++));
		// 对应的编辑处理div
		$("[var=inputTreeEdit]", operatorField).attr("id",$inputTreeUl.attr("id") + "-edit");
		var inputParamsTree = $.fn.zTree.init($inputTreeUl, setting,json.conditionField);
		var $outputTreeUl = $("[var=outputTree]", operatorField);
		$outputTreeUl.attr("id", "outputtree" + (_seq++));
		// 对应的编辑处理div
		$("[var=outputTreeEdit]", operatorField).attr("id",$outputTreeUl.attr("id") + "-edit");
		var outputParamsTree = $.fn.zTree.init($outputTreeUl, setting,json.setField);
		operatorField.hide();
		$("#webservice").prepend(operatorField);
		operatorField.fadeIn();// 弄点效果提醒一下
		// 展开树
		inputParamsTree.expandAll(true);
		outputParamsTree.expandAll(true);
		setTimeout(function(){
			//是否深度解析（当从数据库中取出有映射设置时，解析出来显示在界面上）
			if(depParse){
				var inputs = t.inputs,
				outputs = t.outputs;

				//解析输入参数绑定
				for(var i=0,c;c=inputs[i++];){
					cloneBindingTable(c,$("[var=inputTreeEdit]", operatorField),1);
				}
				//解析输出参数绑定
				for(var i=0,c;c=outputs[i++];){
					cloneBindingTable(c,$("[var=outputTreeEdit]", operatorField),1);
				}
			}
		},200);

	},
	error : function(msg) {
		$.ligerDialog.error("出错了！","提示信息");
		return;
	}
	});
	$.ajax({
		type : "POST",
		url : __ctx + "/platform/bpm/bpmDefVar/getMethodParam.ht",
		//url : __ctx + "/dbFunc/dbFunc/dbFunc/searchforfield.ht",
		data : params,
		//data : methedId,
		dataType : "json",
		success : function(result) {
		var json = eval('(' + result + ')');
		if (!json.success) {
			$.ligerDialog.warn(json.msg,"提示信息");
			return false;
		}
		var operatorField = $("#editField [zone=method]").clone(true, true);// 深度克隆
		$(operatorField).data("methodInfo",json);
		$("[var=wsdl]", operatorField).html(json.wsdl);
		$("[var=method]", operatorField).html(json.method);
		$("[var=namespace]", operatorField).html(json.namespace);
		$("[var=soapaction]", operatorField).val(json.soapaction);
		$("[var=invokeUrl]", operatorField).val(json.invokeUrl);
		$("[var=serviceName]",operatorField).val(params.serviceName);
		$("[var=del]", operatorField).unbind();// 删除所有事件
		$("[var=del]", operatorField).click(function(e) {
			$.ligerDialog.confirm("删除当前方法[" + json.method
					+ "]?","提示信息", function(rtn) {
				if (rtn) {
					operatorField.fadeOut(500, function() {
						$(this).remove();
					});
				}
			});
		});
		var $inputTreeUl = $("[var=inputTree1]", operatorField);
		$inputTreeUl.attr("id", "inputtree1" + (_seq++));
		// 对应的编辑处理div
		$("[var=inputTreeEdit1]", operatorField).attr("id",$inputTreeUl.attr("id") + "-edit");
		var inputParamsTree = $.fn.zTree.init($inputTreeUl, setting,json.conditionField);
		var $outputTreeUl = $("[var=outputTree1]", operatorField);
		$outputTreeUl.attr("id", "outputtree1" + (_seq++));
		// 对应的编辑处理div
		$("[var=outputTreeEdit1]", operatorField).attr("id",$outputTreeUl.attr("id") + "-edit");
		var outputParamsTree = $.fn.zTree.init($outputTreeUl, setting,json.setField);
		operatorField.hide();
		$("#webservice").prepend(operatorField);
		operatorField.fadeIn();// 弄点效果提醒一下
		// 展开树
		inputParamsTree.expandAll(false);
		outputParamsTree.expandAll(false);
		setTimeout(function(){
			//是否深度解析（当从数据库中取出有映射设置时，解析出来显示在界面上）
			if(depParse){
				var inputs = t.inputs,
				outputs = t.outputs;

				//解析输入参数绑定
				for(var i=0,c;c=inputs[i++];){
					cloneBindingTable(c,$("[var=inputTreeEdit1]", operatorField),1);
				}
				//解析输出参数绑定
				for(var i=0,c;c=outputs[i++];){
					cloneBindingTable(c,$("[var=outputTreeEdit1]", operatorField),1);
				}
			}
		},200);

	},
	error : function(msg) {
		$.ligerDialog.error("出错了！","提示信息");
		return;
	}
	});
};
var addSelectMethodNode = function(t,depParse) {

	var params = {
			methodId : t.url,
			func_name : t.method,
			table_name : t.serviceName,
			type:type
	};
	var setting = {
			view : {
		addDiyDom : function(treeId, treeNode) {
		var type = treeNode.type;
		var aObj = $("#" + treeNode.tId + "_a");
		aObj.append('<span style="margin-left:5px;">(' + type+ ')</span>');
	},
	selectedMulti : false
	},
	check : {
		enable : true,
		chkboxType : {
		"Y" : "ps",
		"N" : "s"
	}
	},
	callback : {
		beforeCheck : function(treeId, treeNode) {
		if (!treeNode.type) {
			return true;
		}
		if (treeNode.checked) {
			if (confirm("是否取消当前参数[" + treeNode.name + "]的绑定配置?")) {
				var $div = $('#' + treeId + "-edit");
				if ($div.length < 1) {
					return;
				}						
				$("#" + treeNode.name + "_" + rep_list(treeNode.type) +"-edit", $div).remove();

				return true;
			} else {
				return false;
			}
		}
		return true;
	},
	onCheck : function(event, treeId, treeNode) {// 选中事件
		var $div = $('#' + treeId + "-edit");
		if ($div.length < 1) {
			return;
		}
		if (!treeNode.type) {
			return;
		}
		if (treeNode.checked) {// 选中
			$('#' + treeNode.tId + '_a').click();
		} else {
			var $editTable = $("#" + treeNode.tId + "-edit", $div);
			$editTable.remove();
		}
	},
	onClick : function(event, treeId, treeNode) {// 单击事件
		// 没有选中,则不需要处理
		if (!treeNode.checked) {
			return;
		}
		var $div = $('#' + treeId + "-edit");
		cloneBindingTable(treeNode,$div);
	}
	}
	};
	var operatorField = $("#editField [zone=method]").clone(true, true);// 深度克隆
	$.ajax({
		type : "POST",
		//url : __ctx + "/platform/bpm/bpmDefVar/getMethodParam.ht",
		url : __ctx + "/dbFunc/dbFunc/dbFunc/searchforfield.ht",
		data : params,
		//data : methedId,
		dataType : "json",
		success : function(result) {
		var json = eval('(' + result + ')');
		if (!json.success) {
			$.ligerDialog.warn(json.msg,"提示信息");
			return false;
		}
		
		$(operatorField).data("methodInfo",json);
		$("[var=wsdl]", operatorField).html(json.wsdl);
		$("[var=method]", operatorField).html(json.method);
		$("[var=namespace]", operatorField).html(json.namespace);
		$("[var=soapaction]", operatorField).val(json.soapaction);
		$("[var=invokeUrl]", operatorField).val(json.invokeUrl);
		$("[var=serviceName]",operatorField).val(json.serviceName);
		$("[var=del]", operatorField).unbind();// 删除所有事件
		$("[var=del]", operatorField).click(function(e) {
			$.ligerDialog.confirm("删除当前方法[" + json.method
					+ "]?","提示信息", function(rtn) {
				if (rtn) {
					operatorField.fadeOut(500, function() {
						$(this).remove();
					});
				}
			});
		});
		var $inputTreeUl = $("[var=inputTree]", operatorField);
		$inputTreeUl.attr("id", "inputtree" + (_seq++));
		// 对应的编辑处理div
		$("[var=inputTreeEdit]", operatorField).attr("id",$inputTreeUl.attr("id") + "-edit");
		var inputParamsTree = $.fn.zTree.init($inputTreeUl, setting,json.conditionField);
		var $outputTreeUl = $("[var=outputTree]", operatorField);
		$outputTreeUl.attr("id", "outputtree" + (_seq++));
		// 对应的编辑处理div
		$("[var=outputTreeEdit]", operatorField).attr("id",$outputTreeUl.attr("id") + "-edit");
		var outputParamsTree = $.fn.zTree.init($outputTreeUl, setting,json.setField);
		operatorField.hide();
		$("#webservice").prepend(operatorField);
		operatorField.fadeIn();// 弄点效果提醒一下
		// 展开树
		var $resultTreeUl = $("[var=resultTree]", operatorField);
		$resultTreeUl.attr("id", "resulttree" + (_seq++));
		// 对应的编辑处理div
		$("[var=resultTreeEdit]", operatorField).attr("id",$resultTreeUl.attr("id") + "-edit");
		var resultParamsTree = $.fn.zTree.init($resultTreeUl, setting,json.resultField);
		inputParamsTree.expandAll(true);
		outputParamsTree.expandAll(true);
		resultParamsTree.expandAll(true);
		setTimeout(function(){
			//是否深度解析（当从数据库中取出有映射设置时，解析出来显示在界面上）
			if(depParse){
				var inputs = t.inputs,
				outputs = t.outputs,
				results=t.results;

				$('#resulttr').show(); 
				//解析输入参数绑定
				for(var i=0,c;c=inputs[i++];){
					cloneBindingTable(c,$("[var=inputTreeEdit]", operatorField),1);
				}
				//解析输出参数绑定
				for(var i=0,c;c=outputs[i++];){
					cloneBindingTable(c,$("[var=outputTreeEdit]", operatorField),1);
				}
				if(results!=null)
					for(var i=0,c;c=results[i++];){
						cloneBindingTable(c,$("[var=resultTreeEdit]", operatorField),1);
					}
			}
		},200);

	},
	error : function(msg) {
		$.ligerDialog.error("出错了！","提示信息");
		return;
	}
	});
	$.ajax({
		type : "POST",
		//url : __ctx + "/platform/bpm/bpmDefVar/getMethodParam.ht",
		url : __ctx + "/dbFunc/dbFunc/dbFunc/searchforfield1.ht",
		data : params,
		//data : methedId,
		dataType : "json",
		success : function(result) {
		//var resultx = '{"success":true,"msg":"获取成功.","wsdl":"getAll","namespace":"warehouse","invokeUrl":"别名","method":"getAll","soapaction":"database","serviceName":"warehouse","conditionField":{"name":"条件字段","children":[]},"setField":{"name":"设置字段","children":[]},"resultField":{"name":"结果字段","children":[{"name":"W_ID","type":"varChar","nocheck":false},{"name":"W_NAME","type":"varChar","nocheck":false},{"name":"W_STREET_1","type":"varChar","nocheck":false},{"name":"W_STREET_2","type":"varChar","nocheck":false},{"name":"W_CITY","type":"varChar","nocheck":false},{"name":"W_STATE","type":"varChar","nocheck":false},{"name":"W_ZIP","type":"varChar","nocheck":false},{"name":"W_TAX","type":"varChar","nocheck":false},{"name":"W_YTD","type":"varChar","nocheck":false}]}}'
		var json = eval('(' + result + ')');
		if (!json.success) {
			$.ligerDialog.warn(json.msg,"提示信息");
			return false;
		}	
		$(operatorField).data("methodInfo",json);
		$("[var=wsdl]", operatorField).html(json.wsdl);
		$("[var=method]", operatorField).html(json.method);
		$("[var=namespace]", operatorField).html(json.namespace);
		$("[var=soapaction]", operatorField).val(json.soapaction);
		$("[var=invokeUrl]", operatorField).val(json.invokeUrl);
		$("[var=serviceName]",operatorField).val(json.serviceName);
		$("[var=del]", operatorField).unbind();// 删除所有事件
		$("[var=del]", operatorField).click(function(e) {
			$.ligerDialog.confirm("删除当前方法[" + json.method
					+ "]?","提示信息", function(rtn) {
				if (rtn) {
					operatorField.fadeOut(500, function() {
						$(this).remove();
					});
				}
			});
		});
		var $inputTreeUl = $("[var=inputTree1]", operatorField);
		$inputTreeUl.attr("id", "inputtree1" + (_seq++));
		// 对应的编辑处理div
		$("[var=inputTreeEdit]", operatorField).attr("id",$inputTreeUl.attr("id") + "-edit");
		var inputParamsTree = $.fn.zTree.init($inputTreeUl, setting,json.conditionField);
		var $outputTreeUl = $("[var=outputTree1]", operatorField);
		$outputTreeUl.attr("id", "outputtree1" + (_seq++));
		// 对应的编辑处理div
		$("[var=outputTreeEdit1]", operatorField).attr("id",$outputTreeUl.attr("id") + "-edit");
		var outputParamsTree = $.fn.zTree.init($outputTreeUl, setting,json.setField);
		operatorField.hide();
		$("#webservice").prepend(operatorField);
		operatorField.fadeIn();// 弄点效果提醒一下
		// 展开树
		var $resultTreeUl = $("[var=resultTree1]", operatorField);
		$resultTreeUl.attr("id", "resulttree1" + (_seq++));
		// 对应的编辑处理div
		$("[var=resultTreeEdit1]", operatorField).attr("id",$resultTreeUl.attr("id") + "-edit");
		var resultParamsTree = $.fn.zTree.init($resultTreeUl, setting,json.resultField);
		inputParamsTree.expandAll(false);
		outputParamsTree.expandAll(false);
		resultParamsTree.expandAll(false);
		setTimeout(function(){
			//是否深度解析（当从数据库中取出有映射设置时，解析出来显示在界面上）
			if(depParse){
				var inputs = t.inputs,
				outputs = t.outputs,
				results=t.results;

				$('#resulttr').show(); 
				//解析输入参数绑定
				for(var i=0,c;c=inputs[i++];){
					cloneBindingTable(c,$("[var=inputTreeEdit1]", operatorField),1);
				}
				//解析输出参数绑定
				for(var i=0,c;c=outputs[i++];){
					cloneBindingTable(c,$("[var=outputTreeEdit1]", operatorField),1);
				}
				if(results!=null)
					for(var i=0,c;c=results[i++];){
						cloneBindingTable(c,$("[var=resultTreeEdit1]", operatorField),1);

					}
			}
		},200);

	},
	error : function(msg) {
		$.ligerDialog.error("出错了！","提示信息");
		return;
	}
	});
};
//获取参数名的全路径
function getFullPath(node){
	if(node.fullpath)return node.fullpath;
	var level = node.level,
	pathAry = [node.name];
	while(level>1){
		node = node.getParentNode();
		level = node.level;
		pathAry.push(node.name);
	}
	var path,
	newPathAry = [];
	while(path=pathAry.pop()){
		newPathAry.push(path);
	}
	return newPathAry.join('.');
};
function rep_list(t){
	if(!t)return t;
	return t.replace(/[\{|\}]/g,'_');
};
//克隆绑定映射表
function cloneBindingTable(t,d,depParse){
	if (d.length < 1) {
		return;
	}
	$('table', d).hide();// 所有table隐藏
	if (!t.type&&!t.soapType) {
		return;
	}
	var $editTable = $("#" + t.name + "_" + rep_list(t.type) +"-edit", d),
	divType = d.attr("var");

	if ($editTable.length < 1) {
		$editTable = $("#editField [zone=binding]").clone(true,true);// 深度克隆
		$('input[var=fullpath]', $editTable).val(getFullPath(t));
		$('span[var=name]', $editTable).text(t.name);
		//是否从数据库中取出的数据显示到界面上
		if(depParse){
			$('[var=type]', $editTable).html(t.soapType);
			$("select[name=bindingType]",$editTable).val(t.bindingType);
			$editTable.attr("id", t.name + "_" + rep_list(t.soapType) + "-edit");
		}
		else{
			$('[var=type]', $editTable).html(t.type);
			$editTable.attr("id", t.name + "_" + rep_list(t.type) + "-edit");
		}
		//如果是输出参数，则移除固定值的选项
		if("outputTreeEdit"==divType){
			$("a.tipinfo",$editTable).removeClass("hidden");
			$("select[name=bindingType]",$editTable).find("option").each(function(){
				if($(this).val()=="1")
					$(this).remove();
			});
		}
		d.append($editTable);
		$editTable.hide();
	}
	$editTable.fadeIn(function(){
		if(depParse){
			$("select[name=bindingType]",$editTable).trigger("change");
			setTimeout(function(){
				var bindingVal = $("[name='defValue"+t.bindingType+"']",$editTable);				
				if(t.bindingType==1)
					bindingVal.val(t.bindingVal);
				else
					bindingVal.text(t.bindingVal.jsonUnescape());
				$("[name=javaType]",$editTable).text(t.javaType);
				var treeId = $("ul",d.siblings()).attr("id"),
				paramTree = $.fn.zTree.getZTreeObj(treeId);

				if(paramTree){
					var filter = function(node) {
						return (node.name == t.name && node.type == t.soapType);
					};
					var curNode =paramTree.getNodesByFilter(filter, true);
					if(curNode)
						paramTree.checkNode(curNode, true, true);
				}
			},200);
		}
	});
};
//通过tablename查找数据
function getTable() {
	var wsdlUrl = $('#tableName').val();
	var type=$('#selected').val();
	tableName=wsdlUrl;
	var istable=$('#istable').val();
	if ($.trim(wsdlUrl).length < 1 || $('#tableName')[0].defaultValue == wsdlUrl) {
		$.ligerDialog.warn("请输入名称!","提示信息");
		return;
	}
	wsHeight = $('#wsLayout').height();
	$("#treeReFresh").click(function() {
		loadWsTree();
	});

	$("#treeExpand").click(function() {
		wsTree.expandAll(true);
	});
	$("#treeCollapse").click(function() {
		wsTree.expandAll(false);
	});
	loadMethod();
	$("#wsTree").height(wsHeight - 65);
}


/**
 * 双击function事件
 */
function zTreeOnClick(e, treeId, treeNode) {
	wsTree.selectNode(treeNode);
	if (treeNode.level == 0) {
		return;
	} else {
		if (wsTree == null) {
			$.ligerDialog.warn("请先查询出webservice的方法","提示信息");
			return;
		}
		var treeNode = getSelectNode();
		if (treeNode && treeNode.level == 0) {
			$.ligerDialog.warn("请选择webservice的方法","提示信息");
			return;
		}
		treeNode.serviceName = treeNode.getParentNode().name;
		treeNode.url = treeNode.id;
		treeNode.method = treeNode.name;
		addSelectMethodNode(treeNode);
	}
}
/**
 * 加载已绑定参数
 * @return
 */
function loadExist(){
	$.ajax({
		type:"post",
		url:__ctx+"/platform/bpm/bpmNodeWebService/getExist.ht",
		data:{actDefId:actDefId,nodeId:nodeId
	},

	dataType:"json",

	success:function(result){
		parseNodeTable(result);
	}
	});
}

/**
 * 点击方法树右键
 * @param event
 * @param treeId
 * @param treeNode
 * @return
 */
function OnRightClick(event, treeId, treeNode) {

	if(!treeNode.isParent){
		wsTree.selectNode(treeNode);
		showRMenu("node", event.clientX, event.clientY);
	}

}
function showRMenu(type, x, y) {
	$("#rMenu").show();
	if (type=="root") {
		$("#m_del").hide();
		$("#m_check").hide();
		$("#m_unCheck").hide();
	} else {
		$("#m_del").show();
		$("#m_check").show();
		$("#m_unCheck").show();
	}
	var yd=y-30;
	var xd=x+10;
	rMenu.css({"top":yd+"px", "left":xd+"px", "visibility":"visible"});

	$("body").bind("mousedown", onBodyMouseDown);
}
function hideRMenu() {
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}

//选中的节点
function getSelectNode() {
	wsTree = $.fn.zTree.getZTreeObj("wsTree");
	var nodes = wsTree.getSelectedNodes();
	return nodes[0];
}

//关闭窗口
function closeWin() {
	$.ligerDialog.confirm("是否关闭当前窗口?","提示信息", function(rtn) {
		if (rtn) {
			window.close();
		}
	});
}
function detailFun(){
	var node=wsTree.getSelectedNodes();
	var table_name=node[0].getParentNode().name;
	var func_name=node[0].name;
	var id = 0;
	//var url=__ctx+"/platform/bpm/bpmFormQuery/setting.ht?func_name="+func_name+"&table_name="+table_name;
	var url=__ctx+"/dbFunc/dbFunc/dbFunc/detailwb.ht?funcname="+func_name+"&tablename="+table_name+"&id="+id;
	var settingobj=$("#settingobj").val(),
	fields={};
	var displayField=$("#displayField").val(),
	conditionField=$("#conditionField").val(),
	sortField=$("#sortField").val(),
	resultField=$("#resultField").val(),
	settingField=$("#settingField").val();
	if(displayField)
		fields.displayField=displayField;
	if(conditionField)
		fields.conditionField=conditionField;
	if(resultField)
		fields.resultField=resultField;
	if(sortField)
		fields.sortField=sortField;
	if(settingField)
		fields.settingField=settingField;
	url=url.getNewUrl();
	DialogUtil.open({
		height:600,
		width: 800,
		title : '设置列',
		url: url, 
		isResize: true,
		//自定义参数
		fields: fields,
		sucCall:function(rtn){
		$("#settingobj").val(objectname);
		 	$("#displayField").val(rtn[0]);
		 	$("#conditionField").val(rtn[1]);
		 	$("#resultField").val(rtn[2]);
		 	$("#sortField").val(rtn[3]);
		 	$("#settingField").val(rtn[4]);
		 	$("#styletemp").val(rtn[5]);
	}
	});
}
/*加载表 以及对应方法
 * 
 */
function loadMethod() {
	var setting = {
			data : {
		key : {
		methodId:"id",
		name :"name"
	}
	},
	view : {
		selectedMulti : false
	},
	callback : {
		onDblClick : zTreeOnClick,onRightClick: OnRightClick
	}
	};
	$.ajax({
		type : "POST",
		//		url : __ctx + "/platform/bpm/bpmNodeWebService/getMethodList.ht",
		url : __ctx + "/dbFunc/dbFunc/dbFunc/searchformethod.ht",
		data : {
		'name' : $('#tableName').val(),
		//		'type':   $('#stype').val(),
		'type':   type,

		'istable':$('#istable').val()
	},
	dataType : "json",
	success : function(result) {
		var json = eval('(' + result + ')');
		wsTree = $.fn.zTree.init($("#wsTree"), setting, json);
		wsTree.expandAll(true);
	},
	error : function(msg) {
		$.ligerDialog.closeWaitting();
		$.ligerDialog.error("输入WebService地址是否错误或者链接异常，请检查！","提示信息");
		return;
	}
	});

}













