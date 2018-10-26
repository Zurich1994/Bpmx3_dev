
<%--
	time:2011-11-16 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>编辑自定义表单	</title>
<%@include file="/commons/include/form.jsp"%>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/ckeditor/custom/preview/plugin.js"></script>
<script type="text/javascript" src="${ctx}/js/ckeditor/custom/opinion/plugin.js"></script>
<script type="text/javascript" src="${ctx}/js/ckeditor/custom/column/plugin.js"></script>
<script type="text/javascript" src="${ctx}/js/ckeditor/custom/template/plugin.js"></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmFormDef"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerTab.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/Permission.js"></script>
<script type="text/javascript">

	var formDefId=${bpmFormDef.formDefId};
	var tableId=${bpmFormDef.tableId};

	/*
	 *   权限页面
	 */
	//权限
	var permission;
	var permissionTr = '<tr name="#name">'
			+ '<td>#desc</td>'
			+ '<td>'
			+ '<table style="width:100%;">'
			+ '<tr>'
			+ '<td style="border:0;padding:0;width:100px;">'
			+ '<select id="selr#type#name" permissonType="#type" name="#name" mode="r" onchange="selChange(this)">'
			+ '<option value="user">用户</option>'
			+ '<option value="role">角色</option>'
			+ '<option value="org">组织</option>'
			+ '<option value="orgMgr">组织负责人</option>'
			+ '<option value="pos">岗位</option>'
			+ '<option value="everyone">所有人</option>'
			+ '<option value="none">无</option>'
			+ '</select>'
			+ '</td>'
			+ '<td style="border:0;padding:0;"><input id="txtr#type#name" type="text" style="width: 100%;" readonly/></td>'
			+ '<td style="border:0;padding:0;width:60px;">&nbsp;<a id="ar#type#name" href="javascript:;" class="link get" onclick="selectCmp(\'#type\', \'r\', \'#name\')">选择</a></td>'
			+ '</tr>'
			+ '</table>'
			+ '</td>'
			+ '<td>'
			+ '<table style="width:100%;">'
			+ '<tr>'
			+ '<td style="border:0;padding:0;width:100px;">'
			+ '<select id="selw#type#name" permissonType="#type" name="#name" mode="w" onchange="selChange(this)">'
			+ '<option value="user">用户</option>'
			+ '<option value="role">角色</option>'
			+ '<option value="org">组织</option>'
			+ '<option value="orgMgr">组织负责人</option>'
			+ '<option value="pos">岗位</option>'
			+ '<option value="everyone">所有人</option>'
			+ '<option value="none">无</option>'
			+ '</select>'
			+ '</td>'
			+ '<td style="border:0;padding:0;"><input id="txtw#type#name" type="text" style="width: 100%;" readonly/></td>'
			+ '<td style="border:0;padding:0;width:60px;">&nbsp;<a id="aw#type#name" href="javascript:;" class="link get" onclick="selectCmp(\'#type\', \'w\', \'#name\')">选择</a></td>'
			+ '</tr>' + '</table>'
			+ '</td>' + '</tr>';

	//选择器
	function selectCmp(type, mode, name) {

		var txt = $('#txt' + mode + type + name);
		var selType = $('#sel' + mode + type + name).val();

		var callback = function(ids, names) {
			permission[type][name][mode] = {
				type : selType,
				id : ids,
				fullname: names
			};
			txt.val(names);
		};

		if (selType == 'user') {
			UserDialog({callback : callback});
		} else if (selType == 'role')//role
		{
			RoleDialog({callback : callback});
		} else if (selType == 'org' || selType == 'orgMgr') {
			OrgDialog({callback : callback});
		} else if (selType == 'pos') {
			PosDialog({callback : callback});
		}
	}
	//选择器类型有改变时执行
	function selChange(obj) {
		var type = $(obj).attr('permissonType');
		var mode = $(obj).attr('mode');
		var name = $(obj).attr('name');
		var selType = $('#sel' + mode + type + name).val();
		var txt = $('#txt' + mode + type + name);
		var a = $('#a' + mode + type + name);
		txt.val('');
		permission[type][name][mode] = {
			type : selType,
			id : '',
			fullname: ''
		};
		if (selType == 'everyone' || selType == 'none') {
			txt.css('display', 'none');
			a.css('display', 'none');
		} else {
			txt.css('display', '');
			a.css('display', '');
		}
	}
	//不需要选择器时隐藏相应控件
	function changeDisplay(type, mode, name) {
		
		var selType = $('#sel' + mode + type + name).val();
		if (selType == 'everyone' || selType == 'none') {
			$('#txt' + mode + type + name).css('display', 'none');
			$('#a' + mode + type + name).css('display', 'none');
		} else {
			$('#txt' + mode + type + name).css('display', '');
			$('#a' + mode + type + name).css('display', '');
		}
	}

	function getPermission() {
		$.post('getPermissionByFormDefId.ht?formDefId=${bpmFormDef.formDefId}', function(data) {
			permission = {};
			$.each(data, function(i, d) {
				permission[i] = {};
				$.each(d, function(i1, d1) {
					
					permission[i][d1['fieldName']||d1['opinionName']||d1['tableName']] = d1.permission;
				});
			});
			genFieldPermissionTable();
			genTablePermissionTable();
			genOpinionPermissionTable();
		});
	}

	//产生权限表格
	function genFieldPermissionTable(data) {
		if(permission) {
			if(!data) {
				data = $('#fieldPermission').data('data');
			}
			var fieldPermission = permission['field'];
			var newFieldPermission = {};
			permission['field'] = newFieldPermission;
			$('#fieldPermission').find('tr:gt(0)').remove();
			$.each(data, function(i, d) {
				newFieldPermission[d.fieldName] = (fieldPermission && fieldPermission[d.fieldName]) || {
					r : {
						type : 'everyone', id: '', fullname: ''
					},
					w : {
						type : 'everyone', id: '', fullname: ''
					}
				};
				$('#fieldPermission').append(
						permissionTr.replaceAll('#name', d.fieldName).replaceAll('#desc', d.fieldDesc)
								.replaceAll('#type', 'field'));
				showPermission('field', d.fieldName, newFieldPermission[d.fieldName]);
			});
		} else {
			$('#fieldPermission').data('data', $.extend([], data));
		}
	}
	//产生权限表格
	function genTablePermissionTable(data) {
		if(permission) {
			if(!data) {
				data = $('#tablePermission').data('data');
			}
			var tablePermission = permission['table'];
			var newTablePermission = {};
			permission['table'] = newTablePermission;
			$('#tablePermission').find('tr:gt(0)').remove();
			$.each(data, function(i, d) {
				newTablePermission[d.tableName] = (tablePermission && tablePermission[d.tableName]) || {
					r : {
						type : 'everyone', id: '', fullname: ''
					},
					w : {
						type : 'everyone', id: '', fullname: ''
					}
				};
				$('#tablePermission')
						.append(
								permissionTr.replaceAll('#name', d.tableName).replaceAll('#desc', d.tableDesc).replaceAll('#type',
										'table'));
				showPermission('table', d.tableName, newTablePermission[d.tableName]);
			});
		} else {
			$('#tablePermission').data('data', $.extend([], data));
		}
	}
	//产生意见权限表格
	function genOpinionPermissionTable() {
		if(permission) {
			var data = [];
			var form = $($('#editor iframe')[0].contentDocument);
			form.find('[name^=opinion]').each(function() {
				var arr = $(this).attr('name').split(':');
				var name = arr[1];
				data.push({
					name : name
				});
			});
			var opinionPermission = permission['opinion'];
			var newOpinionPermission = {};
			permission['opinion'] = newOpinionPermission;
			$('#opinionPermission').find('tr:gt(0)').remove();
			$.each(data, function(i, d) {
				newOpinionPermission[d.name] = (opinionPermission && opinionPermission[d.name]) || {
					r : {
						type : 'everyone', id: '', fullname: ''
					},
					w : {
						type : 'everyone', id: '', fullname: ''
					}
				};
				$('#opinionPermission').append(
						permissionTr.replaceAll('#name', d.name).replaceAll('#desc', d.name).replaceAll('#type', 'opinion'));
				showPermission('opinion', d.name, newOpinionPermission[d.name]);
			});
		}
	}
	//显示数据
	function showPermission(type, name, data) {
		$('#selr' + type + name).val(data.r.type);
		$('#selw' + type + name).val(data.w.type);
		$('#txtr' + type + name).val(data.r.fullname);
		$('#txtw' + type + name).val(data.w.fullname);
		changeDisplay(type, 'r', name);
		changeDisplay(type, 'w', name);
	}
	
	/*
	 *   编辑器页面
	 */
	var controls={};
	//子表模板
	var subTemplates = {};
	
	//显示选择模板窗口
	function showSelectTemplate(url, callback) {
		if (!callback)
			callback = onOk;
		var buttons = [ {
			text : "确定",
			handler : callback
		} ];
		openWin('选择模板', 550, 350, url, buttons, 'select');
	}

	function onOk(win) {
		var form = $(document.getElementById('select').contentDocument);
		var tableId = [];
		var tableTemplateId = [];
		form.find('select[name=tableTemplateId]').each(function() {
			tableId.push($(this).attr('tableId'));
			tableTemplateId.push($(this).val());
		});

		genByTemplate(tableId, tableTemplateId);
		getMacroTemplate(tableTemplateId[0]);
		
		win.close();
	}
	
	function genByTemplate(tableId, tableTemplateId) {
		$.post('genByTemplate.ht', {
			tableId : tableId,
			tableTemplateId : tableTemplateId
		}, function(data) {
			var html = '<div type=custform>';
			$.each(data, function(i, d) {
				var h;
				if (d.isMain == 0) {
					h = '<div type="subtable" tableName="' + d.tableName + '">\n' + d.html + '</div>\n';
					subTemplates[d.tableId] = h;
				} else {
					h = d.html + '\n';
				}
				html += h;
			});
			html += '</div>';
			editor.setData(html);
		});
	}
	
	function getMacroTemplate(templateId) {
		$.post('getMacroTemplate.ht', {
			templateId : templateId
		}, function(data) {
			var patt = new RegExp("<#macro.*? (\\w+)>", "gi");
			var result = patt.exec(data);
			var name = result[1];
			var macroTemplate = data.replace(/<#macro.*?>/gi, '').replace(/<\/#macro>/gi, '');
			macro = {
				macro : macroTemplate,
				name : name
			};
		});
	}

	function openWin(title, width, height, url, buttons, id) {
		var left = ($(window).width() - width) / 2;
		var top = ($(window).height() - height) / 2;
		var p = {
			url : url,width : width,height : height,left : left,top : top,title : title,showMax : false,
			showClose : false,onClose : null,showButton : buttons ? true : false,buttons : buttons,frameid : id
		};
		$("body").ligerWindow(p);
	}

	function insertHtml(editor, node) {
		
		if (node.fieldType) {
			//如果是字段
			if (!controls[node.fieldName]) {
				showSelectTemplate('selectTemplate.ht?tableId=' + node.tableId + '&single=1', function(win) {
					var form = $(document.getElementById('select').contentDocument);
					var templateId = form.find('select[name=tableTemplateId]').val();
					win.close();
					$.post('getControls.ht', {
						templateId : templateId,
						tableId: tableId
					}, function(data) {
						controls = data;
						editor.insertHtml(controls[node.fieldName]);
					});
				});
			} else {
				editor.insertHtml(controls[node.fieldName]);
			}
		} else {
			//如果是子表
			var html = subTemplates[node.tableId];
			if (!html) {
				showSelectTemplate('selectTemplate.ht?tableId=' + node.tableId + '&single=1',
					function(win) {
						var form = $(document.getElementById('select').contentDocument);
						var templateId = form.find('select[name=tableTemplateId]').val();
						win.close();
						$.post('genByTemplate.ht',
							{
								tableId : [ node.tableId ],
								tableTemplateId : [ templateId ]
							},
							function(data) {
								$.each(data,function(i, d) {
									var h = '<div tablename="' + d.tableName + '" type="subtable"">\n'
											+ d.html + '</div>\n';
									if (d.isMain == 0) {
										subTemplates[d.tableId] = h;
									}
								});
								editor.insertHtml(subTemplates[node.tableId]);
							});
					});
			} else {
				editor.insertHtml(html);
			}
		}
	}

	/*
	 *   table数据
	 */
	var iconFolder = '${ctx }/styles/tree/';
	var editor;
	var tableId = '${bpmFormDef.tableId}';

	function getAllFields() {
		$.post('getAllFieldsByTableId.ht?tableId=${bpmFormDef.tableId}', function(data) {
			var mainTable = data.mainTable;
			var mainTableFields = data.mainTableFields;

			for ( var i = 0; i < mainTableFields.length; i++) {
				mainTableFields['tableId'] = mainTable.tableId;
			}
			$('#tableName').val(mainTable.tableName);
			
			var subTables = data.subTables;

			genFieldPermissionTable(mainTableFields);
			genTablePermissionTable(subTables);

			var json = [];
			for ( var i = 0; i < mainTableFields.length; i++) {
				if(mainTableFields[i].isHidden == 0) {
					var field = mainTableFields[i];
					json.push(field);
					field.name = field.fieldDesc;
					field.icon = iconFolder + field.fieldType + '.png';
				}
			}

			for ( var i = 0; i < subTables.length; i++) {
				subTables[i].name = subTables[i].tableDesc;
				subTables[i].icon = iconFolder + 'table.png';
				json.push(subTables[i]);
			}

			glTypeTree = $.fn.zTree.init($("#colstree"), {
				callback : {
					beforeClick : function(treeId, treeNode, clickFlag) {
						insertHtml(editor, treeNode);
						return false;
					}
				}
			}, json);
		});
	}

	var __Permission__;
	var tab;
	$(function() {
		
	
		__Permission__=new Permission();
		__Permission__.loadPermission(tableId,formDefId);
		
		function showRequest(formData, jqForm, options) {
			return true;
		}
		valid(showRequest, showResponse);
		$("a.save").click(function() {
			var data = {};
			var arr = $('#base').serializeArray();
			$.each(arr, function(i, d) {
				data[d.name] = d.value;
			});
			data['html'] = editor.getData();
			
			var permissionData = {};
			$.each(permission, function(i, d) {
				permissionData[i] = [];
				$.each(d, function(i1, d1) {
					permissionData[i].push({name: i1, permission: d1});
				});
			});
			
			$.post('save.ht', {
				data: JSON2.stringify(data),
				permission: JSON2.stringify(permissionData),
				tableName: $('#tableName').val()
			}, function(data) {
				var obj=new com.hotent.form.ResultMessage(data);
				if(obj.isSuccess()){//成功
					$.ligerDialog.confirm('操作成功,继续操作吗?','提示信息',function(rtn){
						if(!rtn){
							var returnUrl=$("a.back").attr("href");
							location.href=returnUrl;
						}
						else{
							location.reload();
						}
					});
			    }else{//失败
			    	$.ligerDialog.err('出错信息',"保存流程自定义表单失败",obj.getMessage());
			    }
			});
		});
		
		
		$("#layout1").ligerLayout({onHeightChanged: heightChanged});
		$("#layout2").ligerLayout({
			leftWidth : 200
		});
		$("#tab").ligerTab({height: $("#layout2 .l-layout-center").height()});
		tab = $("#tab").ligerGetTabManager();
		
		editor = CKEDITOR.replace(
						'html',
						{
							height : $('#editor').height() - 80,
							extraPlugins : 'tableresize,custPreview,opinion,column,template',
							toolbar : [ [ 'Source', '-', 'NewPage', 'CustPreview' ], [ 'Undo', 'Redo' ],
									[ 'Bold', 'Italic', 'Underline', 'Strike' ], [ 'Table' ],
									[ 'TextColor', 'BGColor' ],
									[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ], [ 'FontSize','Styles' ],
									[ 'opinion' ],['Template'],['Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton']
							]
						});
		
		editor.on("dataReady",function(e){
			//getPermission();
		});
		
		
		getAllFields();
		var templateId = '${templateId}';
		var templateTableId = '${templateTableId}';
		
		if(templateId != '') {
			
			var templateIds = templateId.split(',');
			var templateTableIds = templateTableId.split(',');
			genByTemplate(templateTableIds, templateIds);
			getMacroTemplate(templateIds[0]);
		}
		
		//
	});
	
	//布局大小改变的时候通知tab，面板改变大小
    function heightChanged(options){
        if (tab)
            tab.addHeight(options.diff);
    }
	
</script>
</head>
<body style="overflow:hidden">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">
			   <c:choose>
					<c:when test="${bpmFormDef.tableId==null}">
						添加自定义表单
					</c:when>
					<c:otherwise>
						编辑自定义表单
					</c:otherwise>
				</c:choose> 	
			</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link back" href="list.ht"><span></span>返回</a>
				</div>
			</div>
		</div>
	</div>
	<div>
		<div id="layout1" class="panel-body">
			<div position="top">
				<form id="base">
					<input id="formDefId" type="hidden" name="formDefId" value="${bpmFormDef.formDefId}" /> 
					<input id="formKey" type="hidden" name="formKey" value="${bpmFormDef.formKey}" /> 
					<input id="tableId" type="hidden" name="tableId" value="${bpmFormDef.tableId}" /> 
					<input id="tableName" type="hidden" name="tableName" value="" /> 
					<input id="versionNo" type="hidden" name="versionNo" value="${bpmFormDef.versionNo}" /> 
					<input id="isPublished" type="hidden" name="isPublished" value="${bpmFormDef.isPublished}" />
					<table cellpadding="0" cellspacing="0" border="0" style="margin: 4px 30px;">
						<tr>
							<td>表单标题:&nbsp;<input id="subject" type="text" name="subject" value="${bpmFormDef.subject}" class="inputText" /></td>
							<td>&nbsp;&nbsp;&nbsp;表单类型:&nbsp; <select name="formCategory">
									<c:forEach items="${formCategoryList}" var="formCategory">
										<option value="${formCategory.typeName }"
											<c:if test="${bpmFormDef.formCategory == formCategory.typeName }">selected</c:if>>${formCategory.typeName}</option>
									</c:forEach>
							</select>
							</td>
							<td>&nbsp;&nbsp;&nbsp;描述:&nbsp;<input id="formDesc" type="text" name="formDesc"
								value="${bpmFormDef.formDesc}" class="inputText" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div id="tab" position="center">
				
				<div title="表单编辑">
					<div id="layout2">
						<div position="left" title="表字段" style="overflow: auto;">
							<div class="panel-toolbar tree-title">
								<span class="toolBar">
									<div class="group">
										<a class="link reload  onclick="getAllFields()">刷新</a>
									</div>
								</span>
							</div>
							<ul id="colstree" class="ztree"></ul>
						</div>
						<div id="editor" position="center" title="表单编辑" style="overflow: auto;">
							<textarea id="html" name="html">${fn:escapeXml(bpmFormDef.html) }</textarea>
						</div>
					</div>
				</div>
				<div title="权限设置" style="overflow:auto;">
					<div class="panel-body">
						<div class="panel-data">
							<table cellpadding="1" cellspacing="1" class="table-grid">
								<tr>
									<th width="20%">字段</th>
									<th width="40%">只读权限</th>
									<th width="40%">编辑权限</th>
								</tr>
								<tbody id="fieldPermission"></tbody>
							</table>
							<br />
							<table  cellpadding="1" cellspacing="1" class="table-grid">
								<tr>
									<th width="20%">子表</th>
									<th width="40%">只读权限</th>
									<th width="40%">编辑权限</th>
								</tr>
								<tbody id="tablePermission"></tbody>
							</table>
							<br />
							<table  cellpadding="1" cellspacing="1" class="table-grid">
								<tr>
									<th width="20%">意见</th>
									<th width="40%">只读权限</th>
									<th width="40%">编辑权限</th>
								</tr>
								<tbody id="opinionPermission"></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>

