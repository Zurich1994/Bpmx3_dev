if (typeof FormDef == 'undefined') {
	FormDef = {};
}
var editor;
FormDef.isSourceMode = false;

/**
 * 
 * @param {} conf
 * {lang }
 */
FormDef.getEditor = function(conf) {
	var h = $(window).height(),
		w = $(window).width(),
		lang = conf.lang?conf.lang:'zh_cn';
	h = conf.height?(h-conf.height):h;
	w = conf.width?(w-conf.width):w;	
	editor = new baidu.editor.ui.Editor({minFrameHeight:h,initialFrameWidth:w,lang:conf.lang});
	editor.addListener("sourceModeChanged",function(t,m){
		FormDef.isSourceMode = m;
	});
};

FormDef.openWin = function(title, width, height, url, buttons, frameId) {
	var left = ($(window).width() - width) / 2;
	var top = ($(window).height() - height) / 2;
	var p = {
		url : url,
		width : width,
		height : height,
		left : left,
		top : top,
		title : title,
		buttons : buttons,
		name : frameId
	};
	$.ligerDialog.open(p);
};

/**
 * 根据表获取字段和子表，构建树。
 * 
 * @param tableId
 */
FormDef.getFieldsByTableId = function(tableId) {
	if($("#colstree").length<=0)return ;//不存在树，则直接返回
		var iconFolder = __ctx + '/styles/tree/';
	$.post('getAllFieldsByTableId.ht?tableId=' + tableId, function(data) {
		var json = eval("("+data+")"),
			treeData = [];
		$('#tableName').val(json.mainname);
		
		for(var i=0,c;c=json.mainfields[i++];){
			if(c.isHidden == 0){
				c.tableId = json.mainid;
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
   					beforeClick : function(treeId, treeNode, clickFlag) {
   						FormDef.insertHtml(editor, treeNode);
   						return false;
   					}
   				}
   			};
		glTypeTree = $.fn.zTree.init($("#colstree"),setting, treeData);
	});
};

/**
 * 重新生成html模版。
 * 
 * @param tableId
 * @param templateAlias
 */
FormDef.genByTemplate = function(tableId, templateAlias) {
	$.post(__ctx+'/platform/form/bpmFormDef/genByTemplate.ht', {
		templateTableId : tableId,
		templateAlias : templateAlias
	}, function(data) {
		editor.setContent(data);
	});
};

/*
 * 编辑器页面
 */
var controls = {};

FormDef.insertHtml = function(editor, node) {
	if (node.fieldType) {
		// 如果是字段
		if (!controls[node.fieldName]) {
				var templatesId = $('#templatesId').val();	
				var	templateAlias = FormDef.parseTemplateAlias(templatesId,node.tableId);
				if(!$.isEmpty(templateAlias)){
						FormDef.insert(editor, node,templateAlias);
				}else{//如果获取当前的模板ID则重新选择模板
					FormDef.selectTemplate(editor,node)
				}
		} else {
			editor.execCommand('inserthtml', controls[node.fieldName],1);
		}
	} else {
		// 如果是子表,选择子表模板
		FormDef.selectSubTemplate(editor,node);
	}
};

/**
 * 插入
 * @param {} editor
 * @param {} node
 * @param {} templateId
 */
FormDef.insert = function (editor, node,templateAlias){
	$.post('getControls.ht', {
		templateAlias : templateAlias,
		tableId : node.tableId
	}, function(data) {
		if( $.isEmptyObject(data)||data.length == 0)
			return FormDef.selectTemplate(editor,node);
		if ($.isEmpty(data) )
			return FormDef.selectTemplate(editor,node);
		controls = data;
		editor.execCommand('inserthtml',controls[node.fieldName],1);
	});
};
/**
 * 选择模板
 */
FormDef.selectTemplate = function(editor,node){
	FormDef.showSelectTemplate('selectTemplate.ht?tableId=' + node.tableId+ '&isSimple=1',
			function(item, dialog) {
				var form = $(document.getElementById('selectTemplate').contentDocument);
				if($.isIE()&&(!form||form.length==0))
					form = $(document.frames['selectTemplate'].document);
				
				var templatesId = FormDef.getTemplatesId(form);
				$('#templatesId').val(templatesId);
				var	templateAlias = FormDef.parseTemplateAlias(templatesId,node.tableId);
				dialog.close();
				FormDef.insert(editor, node,templateAlias);
			});
}


/**
 * 如果是子表,选择子表模板
 */
FormDef.selectSubTemplate = function(editor,node){
	FormDef.showSelectTemplate(
			'selectTemplate.ht?tableId=' + node.tableId
					+ '&isSimple=1',
			function(item, dialog) {
				var form = $(document.getElementById('selectTemplate').contentDocument);
				if($.isIE()&&(!form||form.length==0))
					form = $(document.frames['selectTemplate'].document);
				var templateAlias = $('select[templateId="templateId"]', form).val();
				dialog.close();
				$.post('genByTemplate.ht', {
					templateTableId : node.tableId,
					templateAlias : templateAlias
				}, function(data) {
					editor.execCommand('inserthtml', data,1);
				});
			});
}

/**
 * 解析模板别名
 * @param {} templatesId
 * @param {} tableId
 */
FormDef.parseTemplateAlias = function(templatesId,tableId){
	if($.isEmpty(templatesId)) return '' ;
	var t = templatesId.split(";");
	for(var i=0,c;c=t[i++];){
		var s =c.split(",");
		if(s[0] == tableId){
			return s[1];
		}
	}
};

FormDef.getTemplatesId = function(form){
	var aryTemplateId = [];
	$("select[templateId='templateId']", form).each(function(i) {
		var tableId= $(this).attr("tableid"),templateId =$(this).val();
		aryTemplateId.push(tableId+","+templateId);
	});

	return  aryTemplateId.join(";");
};

// 显示选择模板窗口
FormDef.showSelectTemplate = function(url, callback) {
	if (!callback)
		callback = FormDef.onOk;
	var buttons = [ {
		text : '确定',
		onclick : callback
	} ];
	var newUrl = url+'&templatesId='+$('#templatesId').val();
	FormDef.openWin('选择模板', 550, 350, newUrl, buttons, "selectTemplate");
};

FormDef.onOk = function(item, dialog) {
	var form = $(document.getElementById('selectTemplate').contentDocument);
	if($.isIE()&&(!form||form.length==0))
		form = $(document.frames['selectTemplate'].document);
	var aryTableId = [],aryTemplateId = [],templatesId=[];

	$("select[templateId='templateId']", form).each(function(i) {
		var tableId= $(this).attr("tableid"),templateId =$(this).val();
		aryTableId.push(tableId);
		aryTemplateId.push(templateId);
		templatesId.push(tableId+","+templateId);
	});

	FormDef.genByTemplate(aryTableId.join(","), aryTemplateId.join(","));
	$('#templatesId').val(templatesId.join(";"));
	dialog.close();
};

FormDef.showResponse = function(data) {
	var obj = new com.hrbeu.form.ResultMessage(data);
	if (obj.isSuccess()) {// 成功
		$.ligerDialog.success('保存成功!', '提示', function() {
			window.onbeforeunload = null;
			if(window.opener){
				if(window.opener.parent.reload){
					window.opener.parent.reload();
				}
				else if(window.opener.reload){
					window.opener.reload();
				}
			}
			window.close();
		});
	} else {// 失败
		$.ligerDialog.err('提示', obj.getMessage());
	}
};
