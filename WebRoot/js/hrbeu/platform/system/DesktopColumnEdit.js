var wsTree, menu, menu_root, wsHeight, varHeight, varTree, _seq = 1;// 当前页面的ID随机种子

$(function() {

	$("a.getURLbtn").click(function() {
		var paramValueString = "";
		CommonDialog("zcbcx", function(data) {
			$("#wsdlTxt").val(data.URL);
		}, paramValueString);
	});
	$(":radio").click(function() {
		var checked = $(this).attr('checked');
		var val = $(this).val();
		if (checked && val == 1) {
			$("#queryAlias").show();
			$("#serviceMethod").hide();
			$("#webServiceMethod").hide();
		} else if (checked && val == 0) {
			$("#serviceMethod").show();
			$("#queryAlias").hide();
			$("#webServiceMethod").hide();
		} else if (checked && val == 2) {
			$("#webServiceMethod").show();
			$("#serviceMethod").hide();
			$("#queryAlias").hide();
		}
	});

	// 定义常量
	// var defId = "${defId}";
	// var nodeId = "${nodeId}";
	// var actDefId = "${actDefId}";
	// var bpmNodeWebServiceSetId = "${setId}";
	// var bpmNodeWebServiceDocument = '${document}';

	var width = $("#html").width();
	var height = $("#html").height();
	editor = CodeMirror.fromTextArea(document.getElementById("html"), {
		mode : "text/html",
		tabMode : "indent",
		lineNumbers : true
	});

	editor.setSize(width, height);

	function showRequest(formData, jqForm, options) {
		return true;
	}
	var desktopColumnId = '${desktopColumn.id}';
	if (desktopColumnId == null) {
		valid(showRequest, showResponse, 1);
	} else {
		valid(showRequest, showResponse);
	}
	$("a.save").click(function() {

		// if ($.trim( $("[var=wsdl]").html()) != "" &&
		// $.trim($("[var=method]").html())!= "") {
		// var json = saveWebserviceSet();
		// $('#documentTxt').val(json);
		// $("#html").val(editor.getValue());
		// $('#desktopColumnForm').submit();
		// } else {
		// $("#html").val(editor.getValue());
		// $('#desktopColumnForm').submit();
		// }

		var json = saveWebserviceSet();
		$('#documentTxt').val(json);
		$("#html").val(editor.getValue());
		$('#desktopColumnForm').submit();

	});

	// 布局
	$("#webLayout").ligerLayout({
		leftWidth : 350,
		height : '100%',
		allowLeftResize : false
	});

	// wsdl查询框
	$('#wsdlTxt').bind('focus', function(event) {
		var defaultVal = '请输入地址查询';
		$('#wsdlTxt')[0].defaultValue = defaultVal;
		if ($('#wsdlTxt').val() == defaultVal)
			$('#wsdlTxt').val('');
	}).bind('blur', function() {
		var txtValue = $('#wsdlTxt').val();
		if ($.trim(txtValue).length < 1) {
			$('#wsdlTxt').val($('#wsdlTxt')[0].defaultValue);
		}
	});

	// 为选择绑定类型的下拉框设置处理
	$('.table-detail')
			.delegate(
					"select[name=bindingType]",
					"change",
					function() {
						var table = $(this).closest("table");
						$('tr[bingdingType]', table).hide();
						$('tr[bingdingType=' + $(this).val() + ']', table)
								.show('fast');
					});
	// 保存
	$("#dataFormSave").click(function() {
		saveWebserviceSet();
	});
	// 如果数据库中已有该节点的映射记录，则解析到界面上
	if (bpmNodeWebServiceDocument)
		parseNodeWebservice(bpmNodeWebServiceDocument);

	$(":radio").click(function() {
		var checked = $(this).attr('checked');
		var val = $(this).val();
		if (checked && val == 1) {
			$("#queryAlias").show();
			$("#serviceMethod").hide();
			$("#webServiceMethod").hide();
		} else if (checked && val == 0) {
			$("#queryAlias").hide();
			$("#serviceMethod").show();
			$("#webServiceMethod").hide();
		} else if (checked && val == 2) {
			$("#queryAlias").hide();
			$("#serviceMethod").hide();
			$("#webServiceMethod").show();

		}
	});
});

function selectQuery() {
	BpmQueryDialog({
		callback : function(alias) {
			$("#queryAlias").find(':input').val(alias);
		}
	});
}
// 数据库中已经有设置的记录了，此时需要回显到页面
function parseNodeWebservice(v) {
	var v = eval("(" + v + ")");
	for ( var i = 0, c; c = v[i++];) {
		addMethodNode(c, true);
	}
};

// 保存的处理
function saveWebserviceSet() {
	var json = [];
	$("fieldset[zone=method]")
			.each(
					function() {
						var me = $(this), methodInfo = me.data("methodInfo"), inputDiv = $(
								"div[var=inputTreeEdit]", me), inputTable = $(
								".table-detail[zone=binding]", inputDiv)
						obj = {};

						if (methodInfo) {
							obj.inputParams = methodInfo.inputParams.children;

						}
						obj.url = $("input[var=invokeUrl]", me).val();
						if (!obj.url)
							return true;
						obj.serviceName = $("input[var=serviceName]", me).val();
						obj.soapaction = $("input[var=soapaction]", me).val();
						obj.method = $("td[var=method]", me).text();
						obj.namespace = $("td[var=namespace]", me).text();
						obj.inputs = [];
						// 遍历所有的入参设定
						for ( var i = 0, c; c = inputTable[i++];) {
							var param = {};

							param.name = $("span[var=name]", c).text();
							param.fullpath = $("input[var=fullpath]", c).val();
							param.soapType = $("td[var=type]", c).text();
							param.bindingType = $("select[name=bindingType]", c)
									.val();

							var binding = $("[name='defValue"
									+ param.bindingType + "']", c);
							param.bindingVal = binding.val() ? binding.val()
									: binding.text();
							param.bindingVal = param.bindingVal.jsonEscape();
							param.javaType = javaType = $("[name=javaType]", c)
									.text();
							obj.inputs.push(param);
						}

						json.push(obj);
					});
	json = JSON2.stringify(json);
	return json;
	/*
	 * var url = __ctx + "/platform/bpm/bpmNodeWebService/save.ht"; $.post(url, {
	 * setId : bpmNodeWebServiceSetId, json : json, nodeId : nodeId, actDefId :
	 * actDefId }, function(d) { var result = eval("(" + d + ")"); if
	 * (result.success) $.ligerDialog.success(result.msg, $lang.tip.msg,
	 * function() { window.close(); }); else $.ligerDialog.error(result.msg,
	 * $lang.tip.errorMsg); });
	 */
};

// 添加
var addMethodNode = function(t, depParse) {
	var params = {
		wsdlUrl : t.url,
		nodeName : t.method,
		serviceName : t.serviceName
	};
	var setting = {
		view : {
			addDiyDom : function(treeId, treeNode) {
				var type = treeNode.type;
				var aObj = $("#" + treeNode.tId + "_a");
				aObj.append('<span style="margin-left:5px;">(' + type
						+ ')</span>');
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
						$(
								"#" + treeNode.name + "_"
										+ rep_list(treeNode.type) + "-edit",
								$div).remove();

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
				cloneBindingTable(treeNode, $div);
			}
		}
	};

	$
			.ajax({
				type : "POST",
				url : __ctx + "/platform/bpm/bpmNodeWebService/get.ht",
				data : params,
				dataType : "json",
				success : function(result) {
					var json = eval('(' + result + ')');
					if (!json.success) {
						$.ligerDialog.warn(json.msg, $lang.tip.msg);
						return false;
					}
					var operatorField = $("#editField [zone=method]").clone(
							true, true);// 深度克隆
					$(operatorField).data("methodInfo", json);
					$("[var=wsdl]", operatorField).html(json.wsdl);
					$("[var=method]", operatorField).html(json.method);
					$("[var=namespace]", operatorField).html(json.namespace);
					$("[var=soapaction]", operatorField).val(json.soapaction);
					$("[var=invokeUrl]", operatorField).val(json.invokeUrl);
					$("[var=serviceName]", operatorField).val(
							params.serviceName);
					$("[var=del]", operatorField).unbind();// 删除所有事件
					$("[var=del]", operatorField).click(
							function(e) {
								$.ligerDialog.confirm("删除当前方法[" + json.method
										+ "]?", "提示信息", function(rtn) {
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
					$("[var=inputTreeEdit]", operatorField).attr("id",
							$inputTreeUl.attr("id") + "-edit");
					var inputParamsTree = $.fn.zTree.init($inputTreeUl,
							setting, json.inputParams);
					/*
					 * var $outputTreeUl = $("[var=outputTree]", operatorField);
					 * $outputTreeUl.attr("id", "outputtree" + (_seq++));
					 * 对应的编辑处理div $("[var=outputTreeEdit]",
					 * operatorField).attr("id", $outputTreeUl.attr("id") +
					 * "-edit");
					 */
					/*
					 * var outputParamsTree = $.fn.zTree.init($outputTreeUl,
					 * setting, json.outputParams);
					 */

					operatorField.hide();
					$("#webservice").prepend(operatorField);
					operatorField.fadeIn();// 弄点效果提醒一下

					// 展开树
					inputParamsTree.expandAll(true);
					// outputParamsTree.expandAll(true);
					setTimeout(function() {
						// 是否深度解析（当从数据库中取出有映射设置时，解析出来显示在界面上）
						if (depParse) {
							var inputs = t.inputs;
							// outputs = t.outputs
							// 解析输入参数绑定
							for ( var i = 0, c; c = inputs[i++];) {
								cloneBindingTable(c, $("[var=inputTreeEdit]",
										operatorField), 1);
							}
							// 解析输出参数绑定
							/*
							 * for ( var i = 0, c; c = outputs[i++];) {
							 * cloneBindingTable(c, $("[var=outputTreeEdit]",
							 * operatorField), 1); }
							 */
						}
					}, 200);
				},
				error : function(msg) {
					$.ligerDialog.error("出错了！", "提示信息");
					return;
				}
			});
};

// 获取参数名的全路径
function getFullPath(node) {
	if (node.fullpath)
		return node.fullpath;
	var level = node.level, pathAry = [ node.name ];
	while (level > 1) {
		node = node.getParentNode();
		level = node.level;
		pathAry.push(node.name);
	}
	var path, newPathAry = [];
	while (path = pathAry.pop()) {
		newPathAry.push(path);
	}
	return newPathAry.join('.');
};

function rep_list(t) {
	if (!t)
		return t;
	return t.replace(/[\{|\}]/g, '_');
};

// 克隆绑定映射表
function cloneBindingTable(t, d, depParse) {
	if (d.length < 1) {
		return;
	}
	$('table', d).hide();// 所有table隐藏
	if (!t.type && !t.soapType) {
		return;
	}
	var $editTable = $("#" + t.name + "_" + rep_list(t.type) + "-edit", d), divType = d
			.attr("var");

	if ($editTable.length < 1) {
		$editTable = $("#editField [zone=binding]").clone(true, true);// 深度克隆
		$('input[var=fullpath]', $editTable).val(getFullPath(t));
		$('span[var=name]', $editTable).text(t.name);
		// 是否从数据库中取出的数据显示到界面上
		if (depParse) {
			$('[var=type]', $editTable).html(t.soapType);
			$("select[name=bindingType]", $editTable).val(t.bindingType);
			$editTable
					.attr("id", t.name + "_" + rep_list(t.soapType) + "-edit");
		} else {
			$('[var=type]', $editTable).html(t.type);
			$editTable.attr("id", t.name + "_" + rep_list(t.type) + "-edit");
		}
		// 如果是输出参数，则移除固定值的选项
		/*
		 * if ("outputTreeEdit" == divType) { $("a.tipinfo",
		 * $editTable).removeClass("hidden"); $("select[name=bindingType]",
		 * $editTable).find("option").each( function() { if ($(this).val() ==
		 * "1") $(this).remove(); }); }
		 */
		d.append($editTable);
		$editTable.hide();
	}
	$editTable
			.fadeIn(function() {
				if (depParse) {
					$("select[name=bindingType]", $editTable).trigger("change");
					setTimeout(
							function() {
								var bindingVal = $("[name='defValue"
										+ t.bindingType + "']", $editTable);
								if (t.bindingType == 1)
									bindingVal.val(t.bindingVal);
								else
									bindingVal
											.text(t.bindingVal.jsonUnescape());
								$("[name=javaType]", $editTable).text(
										t.javaType);
								var treeId = $("ul", d.siblings()).attr("id"), paramTree = $.fn.zTree
										.getZTreeObj(treeId);

								if (paramTree) {
									var filter = function(node) {
										return (node.name == t.name && node.type == t.soapType);
									};
									var curNode = paramTree.getNodesByFilter(
											filter, true);
									if (curNode)
										paramTree
												.checkNode(curNode, true, true);
								}
							}, 200);
				}
			});
};

// 通过WSDL地址查找数据
function getByWsdlUrl() {
	var wsdlUrl = $('#wsdlTxt').val();
	if ($.trim(wsdlUrl).length < 1 || $('#wsdlTxt')[0].defaultValue == wsdlUrl) {
		$.ligerDialog.warn("还没有填写webService地址!", "提示信息");
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
	// 菜单
	// getMenu();
	// 加载树
	loadWsTree();
	$("#wsTree").height(wsHeight - 65);
}

// 加载webservice树
function loadWsTree() {
	var setting = {
		data : {
			key : {
				name : "name",
				wsdlUrl : "wsdlUrl"
			}
		},
		view : {
			selectedMulti : false
		},
		callback : {
			onDblClick : zTreeOnDblClick
		}
	};
	$.ligerDialog.waitting('正在查询中,请稍候...');
	$.ajax({
		type : "POST",
		url : __ctx + "/platform/bpm/bpmNodeWebService/getTreeData.ht",
		data : {
			'wsdlUrl' : $('#wsdlTxt').val()
		},
		dataType : "json",
		success : function(result) {
			$.ligerDialog.closeWaitting();
			var json = eval('(' + result + ')');
			wsTree = $.fn.zTree.init($("#wsTree"), setting, json);
			wsTree.expandAll(true);
		},
		error : function(msg) {
			$.ligerDialog.closeWaitting();
			$.ligerDialog.error("输入WebService地址是否错误或者链接异常，请检查！", "提示信息");
			return;
		}
	});
};

/**
 * 双击webservice事件
 * 
 * @param {}
 *            e
 * @param {}
 *            treeId
 * @param {}
 *            treeNode
 */
function zTreeOnDblClick(e, treeId, treeNode) {
	wsTree.selectNode(treeNode);
	if (treeNode.level == 0) {
		return;
	} else {
		if (wsTree == null) {
			$.ligerDialog.warn("请先查询出webservice的方法", "提示信息");
			return;
		}
		var treeNode = getSelectNode();
		if (treeNode && treeNode.level == 0) {
			$.ligerDialog.warn("请选择webservice的方法", "提示信息");
			return;
		}
		treeNode.serviceName = treeNode.getParentNode().name;
		treeNode.url = treeNode.getParentNode().wsdlUrl;
		treeNode.method = treeNode.name;
		addMethodNode(treeNode);
	}
}

// 选中的节点
function getSelectNode() {
	wsTree = $.fn.zTree.getZTreeObj("wsTree");
	var nodes = wsTree.getSelectedNodes();
	return nodes[0];
}

//关闭窗口
function closeWin() {
	$.ligerDialog.confirm("是否关闭当前窗口?", "提示信息", function(rtn) {
		if (rtn) {
			window.close();
		}
	});
}
