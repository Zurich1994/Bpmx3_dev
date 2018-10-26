var colstree;

/**
 * 根据表获取字段构建树。
 * 
 * @param tableId
 */
function loadTree(tableId) {
	var iconFolder = __ctx + '/styles/tree/';
	$.post(__ctx
			+ '/platform/form/bpmFormTable/getAllFieldsByTableId.ht?tableId='
			+ tableId, function(data) {
		var json = eval("(" + data + ")"), treeData = [];
		
		json.icon = iconFolder + 'table.png';
		json.name =  "(主表)" +json.tableDesc;
		json.id = json.tableId;
		json.pId = 0;
		json.type = 0;
		json.open=true;
		json.collapse = false;
		treeData.push(json);
			
		// 主表字段
		for ( var i = 0, c; c = json.fieldList[i++];) {
			c.icon = iconFolder + c.fieldType + '.png';
			c.name = c.fieldDesc;
			c.id = c.fieldId;
			c.pId = json.tableId;
			c.type = 1;
			treeData.push(c);
		}

		// 子表
		for ( var i = 0, c; c = json.subTableList[i++];) {
			c.icon = iconFolder + 'table.png';
			c.id = c.tableId;
			c.pId = 0;
			c.name = "(子表)" + c.tableDesc;
			c.type = 2;
			c.open=true;
			c.collapse = false;
			treeData.push(c);
			for(var j = 0,m;m=c.fieldList[j++];){
				m.pId = c.tableId;
				m.name = m.fieldDesc;
				m.icon = iconFolder + m.fieldType + '.png';
				m.type = 3;
				m.tableName = c.tableName;
				treeData.push(m);
			}

		}

		var setting = {
			view : {
				fontCss : setFontCss
			},
			data : {
				key : {
					name : "name"
				},
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPId : 0
				}
			},
			callback : {
				onDblClick : function(event, treeId, treeNode) {
					// 增加字段
					if (treeNode.type == 1){
						addField(treeNode);
					}else if (treeNode.type == 2){
						addSubTable(treeNode,true);
					}else if (treeNode.type == 3){
						addSubField(treeNode);
					}
				},
				beforeCollapse: function(treeId, treeNode){
					return (treeNode.collapse !== false);
				}
				
			}
		};
		colstree = $.fn.zTree.init($("#colstree"), setting, treeData);
	});
};

//展开收起
function treeExpandAll(type){
	colstree.expandAll(type);
};

// 添加分组
function addTeam(isAdd, o) {
	var table = $($("#cloneTemplate [zone='team']")[0]).clone(true, true);
	if (!isAdd) {
		$("[name='name']", table).val(o.teamName);
		var option = [];
		for ( var i = 0, c; c = o.teamField[i++];) {
			option.push('<option value="' + c.fieldName + '">' + c.fieldDesc
					+ '</option>');
		}
		$("[name='field']", table).append(option.join(''));
	}
	// 绑定删除的分组
//	$("[var=del]", table).live('click',function() {
//		delTeam(table);
//	});
	$("#formItem").append(table);

	$(table).each(function() {
		selectTeam(this);
	});
}

// 设置选择的分组
function selectTeam(obj) {
	var formItem = $("#formItem");
	$(".validError", formItem).each(function() {
		$(this).removeClass('validError');
	});
	$(obj).closest("fieldset").addClass('validError');
}

/**
 * 增加字段
 */
function addField(treeNode) {
	var fieldName = treeNode.fieldName, name = treeNode.name, selectTeam = $(".validError select"), flag = false;
	if (!selectTeam || selectTeam.length == 0) {
		alert($lang_form.bpmFormTableTeam.selectAddTeam);
		return;
	}

	$("#formItem select[name='field'] option").each(function() {
		if (fieldName == $(this).val())
			flag = true;
	});
	if (flag) {
		alert($lang_form.bpmFormTableTeam.moveAdd);
		return;
	}

	__SelectOption__.add(selectTeam.get(0), name, fieldName);
	setCss(treeNode.tId, "red");
}

function delTeam(obj) {
    var me = $(obj);
    	var objFieldset=me.closest('fieldset');
	$.ligerDialog.confirm($lang_form.bpmFormTableTeam.delTeam, $lang.tip.msg,
			function(rtn) {
				if (rtn) {
					objFieldset.fadeOut(500, function() {
						$("select[name='field'] option", objFieldset).each(
								function() {
									removeColsTree($(this).val(), 1);
								});
						// 查找最近的分组
						$("fieldset[zone='team']", $(objFieldset).parent())
								.siblings().each(function() {
									if ($(this).attr('zone') == 'sub')
										return true;
									if (!$(this).hasClass('validError')) {
										$(this).addClass("validError");
										return false;
									}
								});

						$(this).remove();

					});
				}
			});
}

function getSubFieldset(obj){

    var d = [];

	obj.each(function() {
		var me = $(this), data = {}, fieldList = [];
		data.tableName = $("input[name='tableName']", me).val();
		data.tableDesc = $("input[name='tableDesc']", me).val();
		me.find("select[name='field'] option").each(function() {
			var o = {};
			o.fieldName = $(this).val();
			o.fieldDesc = $(this).text();
			fieldList.push(o);
		});

		data.fieldList = fieldList;
		d.push(data);
	});

    return d ;
}

// 增加子表
function addSubTable(o,isInit) {
	var table = $($("#cloneSubTemplate [zone='sub']")[0]).clone(true, true), tableName = o.tableName, flag = false;
	//设置子表的Option
	setSubOption(table,o);
	// 查找该子表是否增加
	$("#formItem [zone='sub'] [name='tableName'] ").each(function() {
		if (tableName == $(this).val())
			flag = true;
	});
	if (flag) {
		alert('该子表已经添加了，请移出再添加!');
		return;
	}

	// 绑定删除的分组
//	$("[var=del]", table).click(function() {
//		delSubTable(table, o);
//	});
	$("#formItem").append(table);
	// 设置样式
	if(!$.isEmpty(isInit)){
		setCss(o.tId, "red");
	}
}

function setSubOption(table,o){
	if(!$.isEmpty(o)){
		$("[name='tableName']", table).val(o.tableName);
		$("[name='tableDesc']", table).val(o.tableDesc);
		if (!$.isEmpty(o.fieldList)) {
			var option = [];
			for ( var i = 0, c; c = o.fieldList[i++];) {
				option.push('<option value="' + c.fieldName + '">' + c.fieldDesc
						+ '</option>');
			}
			var seletField = $("[name='field']", table);
			seletField.attr("subtable",o.tableName);
			seletField.append(option.join(''));
		}
	}
}


/**
 * 删除子表
 * @param table
 * @param o
 */
function delSubTable(obj) {
    var me = $(obj);
    	var objFieldset=me.closest('fieldset');
	$.ligerDialog.confirm('是否删除该子表？', $lang.tip.msg, function(rtn) {
		if (rtn) {
		var  data = getSubFieldset(objFieldset);
			objFieldset.fadeOut(500, function() {
				// 处理样式
				removeColsTree(data.tableName, 2);
				for ( var i = 0, c; c = data.fieldList[i++];) {
					removeColsTree(c.fieldName, 3);
				}
				// 删除节点
				$(this).remove();
			});
		}
	});
}
function getSubFieldset(obj){
    var me = obj, data = {}, fieldList = [];
    data.tableName = $("input[name='tableName']", me).val();
    data.tableDesc = $("input[name='tableDesc']", me).val();
    me.find("select[name='field'] option").each(function() {
        var o = {};
        o.fieldName = $(this).val();
        o.fieldDesc = $(this).text();
        fieldList.push(o);
    });

    data.fieldList = fieldList;


    return data ;
}
/**
 * 增加子表字段
 * @param treeNode
 */
function addSubField(treeNode){
	var fieldName = treeNode.fieldName,
		name = treeNode.name,
		tableName = treeNode.tableName,
		selectSub = $("select[subtable='"+tableName+"']"),
		flag = false;
	if (!selectSub || selectSub.length == 0) {
		alert("该子表未添加,请添加子表再向该表添加该字段!");
		return;
	}

	$("option",selectSub).each(function() {
		if (fieldName == $(this).val()){
			flag = true;
			return false;
		}
	});
	if (flag) {
		alert($lang_form.bpmFormTableTeam.moveAdd);
		return;
	}

	__SelectOption__.add(selectSub.get(0), name, fieldName);
	setCss(treeNode.tId, "red");
}

/**
 * 初始化设置样式
 */
function setFontCss(treeId, treeNode) {
	var json = $('#formJson').val();
	if ($.isEmpty(json))
		return;
	var data = $.parseJSON(json);
	// 解析字段
	if (treeNode.type == 1) {
		for ( var i = 0, c; c = data.team[i++];) {
			for ( var j = 0, o; o = c.teamField[j++];) {
				if (treeNode.fieldName == o.fieldName)
					return {
						color : "red"
					};
			}
		}
	}
	//子表
	else if (treeNode.type == 2) {
		for ( var i = 0, c; c = data.sub[i++];) {
			if (treeNode.tableName == c.tableName)
				return {
					color : "red"
				};
		}
	}else if(treeNode.type == 3){
		for ( var i = 0, c; c = data.sub[i++];) {
			for ( var j = 0, o; o = c.fieldList[j++];) {
				if (treeNode.fieldName == o.fieldName)
					return {
						color : "red"
					};
			}
		}
	}
	return '';
};

// 移除已选字段
function removeOpt(e,type) {
	var me = $(e);
	var optId = me.val();
	if ($.isEmpty(optId))
		return;

	e.remove(e.selectedIndex);
	// $("option[value='"+optId+"']",me).remove();
	removeColsTree(optId, type);
}

function removeColsTree(optId, type) {
	if ($.isEmpty(optId))
		return;
	if (colstree) {
		var node = colstree.getNodesByFilter(function(node) {
			if (type == 1 || type == 3 ) {
				return (node.fieldName == optId) ? true : false;
			}else if (type == 2) {
				return (node.tableName == optId) ? true : false;
			}
		}, true);
		if(!$.isEmpty(node))
			setCss(node.tId, "");
	}
}

// 设置样式
function setCss(o, t) {
	if ($.isEmpty(o))
		return;
	$('#' + o + '_a').css("color", t);
}

function getJsonData() {
	var rtn = valid.valid();
	if (!rtn) {
		alert('有子表名称未填写！');
		return false;
	}
	var team = [], sub = [], json = {};
	// 分组字段
	$("#formItem [zone='team']").each(function() {
		var me = $(this), data = {}, field = [];
		data.teamName = $("input[name='name']", me).val();
		me.find("select[name='field'] option").each(function() {
			var o = {};
			o.fieldName = $(this).val();
			o.fieldDesc = $(this).text();
			field.push(o);
		});

		if (!field || field.length == 0)
			rtn = false;

		data.teamField = field;
		team.push(data);
	});
	if (!rtn) {
		alert("有分组没有字段");
		return false;
	}
	// 子表字段
	$("#formItem [zone='sub']").each(function() {
		var me = $(this), data = {}, fieldList = [];
		data.tableName = $("input[name='tableName']", me).val();
		data.tableDesc = $("input[name='tableDesc']", me).val();
		me.find("select[name='field'] option").each(function() {
			var o = {};
			o.fieldName = $(this).val();
			o.fieldDesc = $(this).text();
			fieldList.push(o);
		});

		if (!fieldList || fieldList.length == 0)
			rtn = false;

		data.fieldList = fieldList;
		sub.push(data);
	});
	if (!rtn) {
		alert("有子表没有字段");
		return false;
	}

	json.title = $("#title").val();
	json.team = team;
	json.sub = sub;
	json = JSON.stringify(json);
	return json;
}

/**
 * 预览
 */
function preview() {
	if (typeof (Worker) === "undefined") {
		alert("不支持预览的浏览器，请使用支持的浏览器。");
		return;
	}
	var json = getJsonData();
	if (!json)
		return;

	var frm = new com.hotent.form.Form();
	frm.creatForm("mobilePreview", __ctx
			+ '/mobile/form/bpmMobileForm/preview.ht');
	frm.addFormEl("formJson", json);
	frm.addFormEl("formId", $('#formId').val());
	frm.addFormEl("formKey", $('#formKey').val());
	frm.setTarget("_blank");
	frm.setMethod("post");
	frm.submit();
	frm.clearFormEl();

}

/**
 * 保存数据
 */
function saveData() {

	var json = getJsonData();
	if (!json)
		return;
	$('#formJson').val(json);
	var options = {};
	if (showResponse) {
		options.success = showResponse;
	}
	var frm = $('#mobileForm').form();
	frm.ajaxForm(options);
	frm.submit();
}

function showResponse(responseText) {
	$.ligerDialog.closeWaitting();
	var obj = new com.hotent.form.ResultMessage(responseText);
	if (obj.isSuccess()) {
		$.ligerDialog.confirm(obj.getMessage() + ","
				+ $lang.operateTip.continueOp, $lang.tip.msg, function(rtn) {
			if (rtn) {
				window.location.href = location.href.getNewUrl();
			} else {
				window.close();
			}
		});

	} else {
		$.ligerDialog.err($lang.tip.msg, '', obj.getMessage());
	}
}