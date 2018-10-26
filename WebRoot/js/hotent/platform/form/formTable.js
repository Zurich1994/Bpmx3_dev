/**
 * 
 * @returns {com.hotent.platform.form.TableManager}
 */
Namespace.register("com.hotent.platform.form");

$(function() {

	// 字段类型
	var typeList = [ {
		key : 'varchar',
		value : '文字'
	}, {
		key : 'number',
		value : '数字'
	}, {
		key : 'date',
		value : '日期'
	}, {
		key : 'clob',
		value : '大文本'
	} ];
	// 字段类型map
	var typeTable = {};
	$(typeList).each(function(i, d) {
		typeTable[d.key] = d.value;
	});

	var controlList = [ {
		key : '1',
		value : '单行文本框'
	}, {
		key : '2',
		value : '多行文本框'
	},{
		key : '10',
		value : '富文本框'
	}
	, {
		key : '3',
		value : '数据字典'
	}, {
		key : '4',
		value : '人员选择器(单选)'
	}, {
		key : '8',
		value : '人员选择器(多选)'
	}, {
		key : '5',
		value : '角色选择器'
	}, {
		key : '6',
		value : '组织选择器'
	}, {
		key : '7',
		value : '岗位选择器'
	}, {
		key : '9',
		value : '文件上传'
	} ];

	var valueFromList = [ {
		key : '0',
		value : '表单输入'
	}, {
		key : '1',
		value : '脚本运算(显示)'
	}, {
		key : '2',
		value : '脚本运算(不显示)'
	}, {
		key : '3',
		value : '流水号'
	} ];

	var optiontemplate = '<option value="#value">#text</option>';
	var checkboxtemplate = '<input name="#name" type="checkbox" value="1" #checked/>';

	/*
	 * data: fieldListId, fieldPropertyId, tablePropertyId, fieldList, table, dictList, validRuleList,
	 * allUnpublishedVersionTable, usableMainTables addFieldId, backHistoryId, forwardHistoryId, moveupFieldId,
	 * movedownFieldId, delFieldId
	 * 
	 */
	com.hotent.platform.form.TableManager = function(data, layout) {
		this.ids = data.id;
		this.table=data.data.table;
		this.fieldList = new FieldList(data, layout);
		this.fieldProperty = new FieldProperty(data, this.fieldList);
		this.validate = function() {
			return this.fieldProperty.validateAll();
		},
		this.showAllError = function() {
			this.fieldProperty.showAllError();
		},
		/**
		 * 取得数据
		 */
		this.getData = function() {
			var tableProperty = $('#' + this.ids.tablePropertyId);
			var tableson = {
				tableId : tableProperty.find(':hidden[name=tableId]').val(),
				tableName : tableProperty.find(':text[name=tableName]').val(),
				tableDesc : tableProperty.find(':text[name=tableDesc]').val(),
				versionNo : tableProperty.find(':hidden[name=versionNo]').val(),
				isPublished : tableProperty.find(':hidden[name=isPublished]').val(),
				isMain : tableProperty.find(':radio[name=isMain][checked]').val(),
				mainTableId : tableProperty.find('select[name=mainTableId]').closest('td').is(':visible') ? tableProperty.find('select[name=mainTableId]').val(): '',
				action :$("#action").val()
			};
			
			tableJson=$.extend({},tableJson,this.table);
			var table = JSON2.stringify(tableJson);
			
			var fields = '[';
			for ( var i = 0; i < this.fieldList.fields.length; i++) {
				var f = this.fieldList.fields[i];
				fields += JSON2.stringify(f) + ',';
				//如果是选择器，
				if(f.controlType == '4' || f.controlType == '5' || f.controlType == '6' || f.controlType == '7' || f.controlType == '8') {
					var controlID = f.fieldName + 'ID';
					var controlIDExists = false;
					//查看controlID是否已存在在数据库
					for ( var j = 0; j < this.fieldList.hiddenFields.length; j++) {
						if(this.fieldList.hiddenFields[j].fieldName == controlID) {
							this.fieldList.hiddenFields[j].charLen = f.charLen;
							this.fieldList.hiddenFields[j].hasOwner = true;
							controlIDExists = true;
							break;
						}
					}
					//若不存在，加入
					if(!controlIDExists) {
						var newHidden = $.extend(true, {}, f);
						this.fieldList.hiddenFields.push(newHidden);
						newHidden.fieldName = controlID;
						newHidden.isHidden = 1;
						newHidden.fieldId = null;
						newHidden.hasOwner = true;
						for ( var j = 0; j < this.fieldList.hiddenFields.length; j++) {
							if(f.originalName && this.fieldList.hiddenFields[j].fieldName == f.originalName + 'ID') {
								newHidden.originalName = f.originalName + 'ID';
							}
						}
					}
				}
			}
			for ( var i = 0; i < this.fieldList.hiddenFields.length; i++) {
				if(this.fieldList.hiddenFields[i].hasOwner || this.fieldList.hiddenFields[i].originalName) {
					fields += JSON2.stringify(this.fieldList.hiddenFields[i]) + ',';
				}
			}
			fields += ']';
			return {
				table : table,
				fields : fields
			};
		};
	};
	/**
	 * 字段列表
	 * json 
	 * layout 布局
	 */
	function FieldList(json, layout) {
		{
			var data = json.data;
			this.index = -1;
			this.fields = [];
			this.deletedFields = [];
			this.allFields = [];
			this.hiddenFields = [];
			//字段列表
			if (data && data.fieldList) {
				var fields = data.fieldList;
				for ( var i = 0; i < fields.length; i++) {
					if(fields[i].isHidden == '1') {
						this.hiddenFields.push(fields[i]);
					}else if (fields[i].isDeleted == '0') {
						this.fields.push(fields[i]);
					} else {
						this.deletedFields.push(fields[i]);
					}
					this.allFields.push(jQuery.extend(true, [], fields[i]));
				}
			}
			this.layout = layout;
			this.ids = json.id;

			var fieldList = this;
			setTimeout(function() {
				fieldList.init();
				fieldList.showFieldList();
			}, 1);
		};
		this.setFieldProperty = function(fieldProperty) {
			this.fieldProperty = fieldProperty;
			return this;
		},
		/*
		 * 尝试选中一行
		 */
		this.selectField = function(index) {
			if (index == null) {
				index = -1;
			}
			if (index > -1) {
				if (this.layout.isBottomCollapse) {
					this.layout.setBottomCollapse(false);
				}
			} else {
				this.layout.setBottomCollapse(true);
				this.fieldProperty.resetForm();
			}
			if (this.index != index) {
				if (this.index != -1 && !this.fieldProperty.validate()) {
					this.fieldProperty.showError();
					return false;
				}
				this.index = index;
				// 高亮显示选中行
				$('#' + this.ids.fieldListId + ' tr').removeClass('even');
				if (index > -1) {
					$('#r' + index).addClass('even');
					// 显示详细属性
					this.fieldProperty.showDetail();
				}
			}
			return true;
		},
		/*
		 * 设置编辑框到列表
		 */
		this.setEditorTo = function(obj, index) {
			// 如果是checkbox,或者没有name属性, 或者是不允许修改的属性, 则不需要使用编辑框
			if (!$(obj).attr('name') || $(obj).find(':checkbox').length > 0
					|| (!this.fields[index].isnew && $(obj).is('[name=fieldType]'))) {
				return;
			} else if ($(obj).is(':checkbox')) {
				if ($(obj).attr('checked')) {
					$('#' + this.ids.fieldPropertyId + ' [name=' + $(obj).attr('name') + ']')
							.attr('checked', 'checked').change();
				} else {
					$('#' + this.ids.fieldPropertyId + ' [name=' + $(obj).attr('name') + ']').removeAttr('checked')
							.change();
				}
				return;
			}
			// 显示编辑框
			var name = $(obj).attr('name');
			var input = $('#' + name + 'input');
			var position = $(obj).position();
			var value = this.fields[index][name];
			if (input.is(':text')) {
				value = $(obj).text();
			}
			var center = $('#' + this.ids.centerId);
			
			input.css('left', position.left + center.scrollLeft() + 1)
					.css('top', position.top + center.scrollTop() + 1).css('width', $(obj).width() - 2).css('height',
							$(obj).height() - 2).css('display', '').val(value);
			
			// 焦点设到编辑框
			setTimeout(function() {
				input.focus();
			}, 1);
		},
		/*
		 * 显示列表
		 */
		this.showFieldList = function() {
			//移除所有的行
			$('#' + this.ids.fieldListId + ' tr:gt(0)').remove();
			// 构建TR模板
			var trtemplate = '<tr id="r#id">';
			$('#' + this.ids.fieldListId + ' tr:eq(0) th').each(function() {
				trtemplate += '<td name="' + $(this).attr('name') + '">#' + $(this).attr('name') + '</td>';
			});
			trtemplate += '</tr>';
			
			// 显示数据
			var fieldList = this;
			$(this.fields).each(function(i, d) {
				//替换序号
				var tr = trtemplate.replace('#id', i).replace('#lineNO', i + 1);
				//替换表格中的数据
				$(tr).find('[name]').each(function() {
					var name = $(this).attr('name');
					tr = tr.replace('#' + name, fieldList.getHtml(name, d[name], d));
				});
				//添加行数据
				$('#' + fieldList.ids.fieldListId).append(tr);
			});
			//设置当前选中的索引为-1。
			this.index = -1;
			this.reset();
		},
		/*
		 * 上移
		 */
		this.moveupField = function() {
			if (this.index > 0) {
				var i = this.index - 1;
				this.fieldManager.moveField(this.index, i);
				this.showFieldList();
				$('#' + this.ids.fieldListId + ' #r' + i + ' td:last').click();
			}
		},
		/*
		 * 下移
		 */
		this.movedownField = function() {
			if (this.index > -1 && this.index < this.fields.length - 1) {
				var i = this.index + 1;
				this.fieldManager.moveField(this.index, i);
				this.showFieldList();
				$('#' + this.ids.fieldListId + ' #r' + i + ' td:last').click();
			}
		},
		/*
		 * 在列表中增加一个字段
		 */
		this.addField = function() {
			if (this.index != -1 && !this.fieldProperty.validate()) {
				this.fieldProperty.showError();
				return;
			}
			this.fieldManager.addField();
			this.showFieldList();
			$('#' + this.ids.fieldListId + ' tr:last td[name]:eq(1)').click();
			var center = $('#' + this.ids.centerId);
			center.scrollTop(center.height());
		},
		/*
		 * 删除字段
		 */
		this.delField = function() {
			if (this.index > -1) {
				this.fieldManager.delField(this.index);
				var index = this.index;
				if (!(index < this.fields.length)) {
					index--;
				}
				this.showFieldList();
				this.selectField(index);
			} else {
				$.ligerMsg.info('请先选择字段');
			}
		}, this.backHistory = function() {
			this.historyManager.backHistory();
		}, this.forwardHistory = function() {
			this.historyManager.forwardHistory();
		},
		/*
		 * 更新工具栏显示
		 */
		this.updToolBar = function() {
			if (this.ids.backHistoryId)
				$('#' + this.ids.backHistoryId).html(
						$('#' + this.ids.backHistoryId).html().replace(/\(\d+\)/g,
								'(' + this.historyManager.currHistory + ')'));
			if (this.ids.forwardHistoryId)
				$('#' + this.ids.forwardHistoryId).html(
						$('#' + this.ids.forwardHistoryId).html()
								.replace(
										/\(\d+\)/g,
										'('
												+ (this.historyManager.historys.length
														- this.historyManager.currHistory - 1) + ')'));
		},
		// 显示的html
		this.getHtml = function(name, value, field) {
			var html = '';
			if (name == 'fieldType') {
				html = typeTable[value];
				if (value == 'varchar') {
					html += '(' + field.charLen + ')';
					if (field.valueFrom == 0) {
						if (field.controlType == 1 || field.controlType == 2) {
							html += ',验证:' + (field.validRule == '' ? '无' : field.validRule);
						} else if (field.controlType == 3) {
							html += ',数据字典:' + this.fieldProperty.dictMap[field.dictType];
						}
					} else if (field.valueFrom == 1 || field.valueFrom == 2) {
						html += ',脚本运算';
					} else if (field.valueFrom == 3) {
						html += ',流水号';
					}
				} else if (value == 'number') {
					html += '(' + field.intLen + ',' + field.decimalLen + ')';
				}
			} else if (name == 'isRequired' || name == 'isList' || name == 'isQuery' || name == 'isDeleted' || name == 'isFlowVar') {
				var check = (value == '1' ? 'checked' : '');
				html = checkboxtemplate.replace('#name', name).replace('#checked', check);
			} else {
				html = value;
			}
		
			return html;
		}, 
		this.reset = function() {
			var fieldList = this;
			//字段列表所有的表格去掉click事件绑定
			$('#' + this.ids.fieldListId + ' td').unbind('click');
			$('#' + this.ids.fieldListId + ' td, #' + this.ids.fieldListId + ' :checkbox').click(function() {
				// 当前选中的行
				var index = parseInt($(this).parents('tr').attr('id').replace('r', ''));
				var ret = fieldList.selectField(index);
				if (!ret) {
					return false;
				}
				//设置到绑定界面
				fieldList.setEditorTo(this, index);
			});
		}, 
		/**
		 * 绑定初始按钮事件
		 */
		this.init = function() {
			var fieldList = this;
			if(this.ids.addFieldId){
				$('#' + this.ids.addFieldId).click(function() {
					fieldList.addField();
				});
			}
			if(this.ids.backHistoryId){
				$('#' + this.ids.backHistoryId).click(function() {
					fieldList.backHistory();
				});
			}
			if(this.ids.forwardHistoryId){
				$('#' + this.ids.forwardHistoryId).click(function() {
					fieldList.forwardHistory();
				});
			}
			if(this.ids.moveupFieldId){
				$('#' + this.ids.moveupFieldId).click(function() {
					fieldList.moveupField();
				});
			}
			if(this.ids.movedownFieldId){
				$('#' + this.ids.movedownFieldId).click(function() {
					fieldList.movedownField();
				});
			}
			if(this.ids.delFieldId){
				$('#' + this.ids.delFieldId).click(function() {
					fieldList.delField();
				});
			}
		}
	};

	function FieldProperty(json, fieldList) {
		{
			var data = json.data;
			this.valid = null;
			this.tablevalid = null;
			this.table = data.table;
			this.allUnpublishedVersionTable = data.allUnpublishedVersionTable;
			if (data.usableMainTables) {
				this.usableMainTables = data.usableMainTables;
			} else {
				this.usableMainTables = [];
				for ( var i = 0; i < this.allUnpublishedVersionTable.length; i++) {
					if (this.allUnpublishedVersionTable[i].isMain == 1) {
						this.usableMainTables.push(this.allUnpublishedVersionTable[i]);
					}
				}
			}

			this.dictList = data.dictList;
			this.dictMap = {};
			for ( var i = 0; i < this.dictList.length; i++) {
				this.dictMap[this.dictList[i].nodeKey] = this.dictList[i].typeName;
			}
			this.validRuleList = data.validRuleList;
			this.identityList = data.identityList;
			this.fieldList = fieldList;
			this.ids = json.id;

			this.fieldManager = new FieldManager(fieldList, data);

			fieldList.setFieldProperty(this);

			var fieldProperty = this;
			setTimeout(function() {
				fieldProperty.init();
			}, 1);
		}
		this.setFieldList = function(fieldList) {
			this.fieldList = fieldList;
			return this;
		},
		/*
		 * 验证表单
		 */
		this.validate = function() {
			return this.valid.checkForm();
		},
		/*
		 * 验证表单
		 */
		this.validateAll = function() {
			var ret = true;
			if (!this.valid.checkForm()) {
				ret = false;
			}
			if (!this.tablevalid.checkForm()) {
				ret = false;
			}
			return ret;
		}, this.showError = function() {
			this.valid.showErrors();
			var errors = this.valid.errorList;
			var msg = '请先处理当前的错误：';
			for ( var i = 0; i < errors.length; i++) {
				msg += '<font color="red"><p>' + errors[i].message + '</p></font>';
			}
			$.ligerMsg.info(msg);
		}, this.showAllError = function() {
			this.valid.showErrors();
			this.tablevalid.showErrors();
			var errors = this.tablevalid.errorList;
			errors = errors.concat(this.valid.errorList);
			var msg = '请先处理当前的错误：';
			for ( var i = 0; i < errors.length; i++) {
				msg += '<font color="red"><p>' + errors[i].message + '</p></font>';
			}
			$.ligerMsg.info(msg);
		}, this.resetForm = function() {
			this.valid.resetForm();
		},
		/*
		 * 显示详细属性
		 */
		this.showDetail = function() {
			this.resetForm();
			var property = $('#' + this.ids.fieldPropertyId);
			var field = this.fieldList.fields[this.fieldList.index];
			for (p in field) {
				var box = property.find('[name=' + p + ']');
				if (box.is(':checkbox')) {
					if (box.val() == field[p]) {
						box.attr('checked', 'checked');
					} else {
						box.removeAttr('checked');
					}
				} else {
					box.val(field[p]);
				}
			}
			this.changeDisplay();
		},
		//修改数据类型时，修改数据类型对应的控件类型。
		this.setControlByType=function(fileType){
			var fieldProperty = $('#' + this.ids.fieldPropertyId);
			var objSelect=fieldProperty.find('select[name=controlType]');
			objSelect.empty();
			if(fileType=="varchar"){
				$(controlList).each(function(i, d) {
					var option = optiontemplate.replaceAll('#value', d.key).replace('#text', d.value);
					if(d.key!="10")
						objSelect.append(option);
				});
			}else if(fileType=="clob"){
				$(controlList).each(function(i, d) {
					var option = optiontemplate.replaceAll('#value', d.key).replace('#text', d.value);
					if(d.key=="10" || d.key=="2")
						objSelect.append(option);
				});
			};
		}
		
		/*
		 * 改变控件的显示与隐藏属性
		 */
		this.changeDisplay = function(e) {
			
			
			var fieldProperty = $('#' + this.ids.fieldPropertyId);
			//字段类型
			var fieldType = fieldProperty.find('select[name=fieldType]').val();
			//值来源
			var valueFrom = fieldProperty.find('select[name=valueFrom]').val();
			//控件类型
			var controlType = fieldProperty.find('select[name=controlType]').val();

			// 字段类型一旦保存到数据库就不能在修改。
			if (!this.fieldList.fields[this.fieldList.index].isnew) {
				fieldProperty.find('select[name=fieldType]').attr('disabled', 'disabled');
			} else {
				fieldProperty.find('select[name=fieldType]').removeAttr('disabled');
			}
			// 长度
			if (fieldType == 'varchar') {
				fieldProperty.find('input[name=charLen]').closest('tr').css('display', '');
				fieldProperty.find('input[name=intLen]').closest('tr').css('display', 'none');
			} else if (fieldType == 'number') {
				fieldProperty.find('input[name=charLen]').closest('tr').css('display', 'none');
				fieldProperty.find('input[name=intLen]').closest('tr').css('display', '');
			} else if (fieldType == 'date') {
				fieldProperty.find('input[name=charLen]').closest('tr').css('display', 'none');
				fieldProperty.find('input[name=intLen]').closest('tr').css('display', 'none');
			}else if(fieldType=="clob"){
				fieldProperty.find('input[name=charLen]').closest('tr').css('display', 'none');
				fieldProperty.find('input[name=intLen]').closest('tr').css('display', 'none');
			}
			
			
			if(e!=undefined){
				var name=e.currentTarget.name;
				if(name=="fieldType"){
					this.setControlByType(fieldType);
				}
			}
			//设置控件类型。
			//this.setControlByType(fieldType);
			// 控件类型
			if (fieldType == 'varchar' && valueFrom == '0' || fieldType=="clob") {
				fieldProperty.find('select[name=controlType]').closest('tr').css('display', '');
				
			} else {
				fieldProperty.find('select[name=controlType]').closest('tr').css('display', 'none');
			}
			// 验证规则
			if (fieldType == 'varchar' && valueFrom == '0' && (controlType == '1' || controlType == '2')) {
				fieldProperty.find('select[name=validRule]').closest('tr').css('display', '');
			} else {
				fieldProperty.find('select[name=validRule]').closest('tr').css('display', 'none');
			}
			// 数据字典类型
			if (fieldType == 'varchar' && valueFrom == '0' && controlType == '3') {
				fieldProperty.find('select[name=dictType]').closest('tr').css('display', '');
			} else {
				fieldProperty.find('select[name=dictType]').closest('tr').css('display', 'none');
			}
			// 脚本
			if (valueFrom == '1' || valueFrom == '2') {
				fieldProperty.find('textarea[name=script]').closest('tr').css('display', '');
			} else {
				fieldProperty.find('textarea[name=script]').closest('tr').css('display', 'none');
			}
			//流水号
			if (valueFrom == '3') {
				fieldProperty.find('select[name=identity]').closest('tr').css('display', '');
			} else {
				fieldProperty.find('select[name=identity]').closest('tr').css('display', 'none');
			}

		}, this.init = function() {
			var property = this;
			var fieldList = this.fieldList;
			// 绑定事件
			$(
					'#' + this.ids.fieldPropertyId + ' input, #' + this.ids.fieldPropertyId + ' select, #'
							+ this.ids.fieldPropertyId + ' textarea').change(
					function() {
						var name = $(this).attr('name');
						if (fieldList.index > -1) {
							var td = $('#r' + fieldList.index).find('[name=' + name + ']');
							if ($(this).is(':checkbox')) {
								if ($(this).attr('checked')) {
									td.children(':checkbox').attr('checked', 'checked');
								} else {
									td.children(':checkbox').removeAttr('checked');
								}
							}
							if (property.valid.element(this)) {
								if ($(this).is('[name=fieldName]')) {
									$('#' + property.ids.fieldPropertyId + ' select[name=fieldType]').removeAttr(
											'disabled');
									for ( var i = 0; i < fieldList.allFields.length; i++) {
										if ($(this).val().toUpperCase() == fieldList.allFields[i].fieldName.toUpperCase()) {
											var input = this;
											$.ligerDialog.confirm($(input).val()
													+ '在历史版本中已经存在,系统将会导入进来,并且数据类型不可更改,是否使用' + $(input).val() + '?','提示信息',
													function(r) {
														if (r) {
															td.html($(input).val());
															property.fieldManager.replaceField(fieldList.index,
																	fieldList.allFields[i]);
															fieldList.showFieldList();
															fieldList.selectField(fieldList.fields.length - 1);
														} else {
															$(input).val(fieldList.fields[fieldList.index].fieldName);
														}
													});
											return;
										}
									}
								}
								var value = $(this).val();
								if ($(this).is(':checkbox') && !$(this).attr('checked')) {
									value = '0';
								}
								property.fieldManager.updField(fieldList.fields[fieldList.index], name, value);
							}
							if (!$(this).is(':checkbox')) {
								td.html(fieldList.getHtml(name, $(this).val(), fieldList.fields[fieldList.index]));
								$('#r' + fieldList.index).find('[name=fieldType]').html(
										fieldList.getHtml('fieldType', fieldList.fields[fieldList.index].fieldType,
												fieldList.fields[fieldList.index]));
							}
						}

					});
			//字段类型，值来源，控件类型修改的时候触发property.changeDisplay() 方法。
			$('#' + this.ids.fieldPropertyId + ' select[name=fieldType], #' + this.ids.fieldPropertyId
					+ ' select[name=valueFrom], #' + this.ids.fieldPropertyId + ' select[name=controlType]')
					.change(function(event) {
						property.changeDisplay(event);
					});

			var fieldProperty = $('#' + this.ids.fieldPropertyId);
			// 初始化下拉框
			$(this.dictList).each(function(i, d) {
				var option = optiontemplate.replace('#value', d.nodeKey).replace('#text', d.typeName);
				fieldProperty.find('select[name=dictType]').append(option);
			});
			$(typeList).each(function(i, d) {
				var option = optiontemplate.replaceAll('#value', d.key).replace('#text', d.value);
				fieldProperty.find('select[name=fieldType]').append(option);
			});
			$(this.validRuleList).each(function(i, d) {
				var option = optiontemplate.replaceAll('#value', d.name).replace('#text', d.name);
				fieldProperty.find('select[name=validRule]').append(option);
			});
			$(valueFromList).each(function(i, d) {
				var option = optiontemplate.replaceAll('#value', d.key).replace('#text', d.value);
				fieldProperty.find('select[name=valueFrom]').append(option);
			});
			$(controlList).each(function(i, d) {
				var option = optiontemplate.replaceAll('#value', d.key).replace('#text', d.value);
				fieldProperty.find('select[name=controlType]').append(option);
			});
			$(this.identityList).each(function(i, d) {
				var option = optiontemplate.replaceAll('#value', d.alias).replace('#text', d.name);
				fieldProperty.find('select[name=identity]').append(option);
			});

			// 准备好editor
			$('#' + this.ids.fieldPropertyId + ' :text, #' + this.ids.fieldPropertyId + ' select').each(
					function() {
						var input = $(this).clone().attr('id', $(this).attr('name') + 'input').css('position',
								'absolute').css('display', 'none').css('z-index', '1000');
						$('#' + property.ids.centerId).append(input);
						input.change(function() {
							$('#' + property.ids.fieldPropertyId + ' [name=' + $(this).attr('name') + ']').val(
									$(this).val()).change();
							$(this).css('display', 'none');
						});
						input.blur(function() {
							$(this).css('display', 'none');
						});
					});

			jQuery.validator.addMethod("uniqueName", function(value, element) {
				for ( var i = 0; i < fieldList.fields.length; i++) {
					if (i != fieldList.index && fieldList.fields[i].fieldName.toUpperCase() == value.toUpperCase()) {
						return false;
					}
				}
				return true;
			}, "字段名已经存在");
			jQuery.validator.addMethod("historyUniqueName", function(value, element) {
				for ( var i = 0; i < fieldList.deletedFields.length; i++) {
					if (fieldList.deletedFields[i].fieldName.toUpperCase() == value.toUpperCase()
							&& fieldList.deletedFields[i].fieldId != $('#fieldId').val()) {
						return false;
					}
				}
				return true;
			}, "字段名在历史版本中已经存在");
			jQuery.validator.addMethod("uniqueTableName", function(value, element) {
				for ( var i = 0; i < property.allUnpublishedVersionTable.length; i++) {
					var tableName = property.allUnpublishedVersionTable[i].tableName;
					if (tableName.toUpperCase() == value.toUpperCase() && (!property.table || property.table.tableName.toUpperCase() != tableName.toUpperCase())) {
						return false;
					}
				}
				return true;
			}, "表名已经存在");
			jQuery.validator.addMethod("word", function(value, element) {
				return /^\w*$/gi.test(value);
			}, "只能输入字母数字和下划线");

			this.valid = $('#' + this.ids.fieldPropertyId).validate({
				rules : {
					fieldName : {
						required : true,
						uniqueName : true,
						word: true,
						historyUniqueName : function() {
							if (!fieldList.fields[fieldList.index].isnew)
								return true;
						}
					},
					charLen : {
						required : true,
						digits : true,
						min : function() {
							if (!fieldList.fields[fieldList.index].isnew)
								return fieldList.fields[fieldList.index].charLen;
							else
								return 0;
						}
					},
					intLen : {
						required : true,
						digits : true,
						min : function() {
							if (!fieldList.fields[fieldList.index].isnew)
								return fieldList.fields[fieldList.index].intLen;
							else
								return 0;
						}
					},
					decimalLen : {
						required : true,
						digits : true,
						min : function() {
							if (!fieldList.fields[fieldList.index].isnew)
								return fieldList.fields[fieldList.index].decimalLen;
							else
								return 0;
						}
					}
				},
				messages : {
					fieldName : {
						required : "字段名称必填"
					},
					charLen : {
						required : "文字长度必填",
						min : "文字长度不能小于{0}"
					},
					intLen : {
						required : "整数长度必填",
						min : "整数长度不能小于{0}"
					},
					decimalLen : {
						required : "小数长度必填",
						min : "小数长度不能小于{0}"
					}
				}
			});

			var table = $('#' + this.ids.tablePropertyId);
			this.tablevalid = table.validate({
				rules : {
					tableName : {
						required : true,
						uniqueTableName : true,
						word: true
					}
				},
				messages : {
					tableName : {
						required : "表名必填"
					}
				}
			});

			table.find(':radio[name=isMain]').click(function() {
				if ($(this).val() == '1') {
					table.find('select[name=mainTableId]').closest('td').css('display', 'none');
				} else {
					table.find('select[name=mainTableId]').closest('td').css('display', '');

				}
			});

			if (this.usableMainTables) {
				for ( var i = 0; i < this.usableMainTables.length; i++) {
					if (this.usableMainTables[i].isMain == 1
							&& (!this.table || this.usableMainTables[i].tableName != this.table.tableName)) {
						table.find('select[name=mainTableId]').append(
								optiontemplate.replace('#value', this.usableMainTables[i].tableId).replace('#text',
										this.usableMainTables[i].tableName));
					}
				}
			}

			if (this.table) {
				var table = $('#' + this.ids.tablePropertyId);
				table.find(':hidden[name=tableId]').val(this.table.tableId);
				table.find(':hidden[name=versionNo]').val(this.table.versionNo);
				table.find(':hidden[name=isPublished]').val(this.table.isPublished);
				table.find(':text[name=tableName]').val(this.table.tableName);
				table.find(':text[name=tableDesc]').val(this.table.tableDesc);
				table.find('select[name=mainTableId]').val(this.table.mainTableId);
				table.find(':radio[name=isMain][value=' + this.table.isMain + ']').click();
				if (this.table.versionNo > 1) {
					table.find(':text[name=tableName]').attr('disabled', 'disabled')
					table.find(':radio[name=isMain]').attr('disabled', 'disabled');
				}
			}

		};
	}
	;

	/*
	 * 字段数据操作
	 */
	function FieldManager(fieldList, data) {
		{
			this.newField = {
				fieldName : '',
				fieldDesc : '',
				fieldType : 'varchar',
				isRequired : 1,
				charLen : 20,
				intLen : 20,
				decimalLen : 0,
				dictType : data.dictList[0].nodeKey,
				identity: data.identityList[0].alias,
				isList : 1,
				isQuery : 1,
				isDeleted : 0,
				isHidden: 0,
				isFlowVar: 0,
				validRule : '',
				valueFrom : 0,
				script : '',
				controlType : 1,
				isnew : true
			}, this.fieldList = fieldList;
			this.historyManager = new HistoryManager(fieldList, data);
			fieldList.fieldManager = this;
		}
		/*
		 * 增加字段
		 */
		this.addField = function() {
			this.fieldList.fields.push(jQuery.extend(true, {}, this.newField));
			this.historyManager.addHistory();
		},
		/*
		 * 删除字段
		 */
		this.delField = function(i) {
			this.fieldList.fields.splice(i, 1);
			this.historyManager.addHistory();
		},
		/*
		 * 替换字段
		 */
		this.replaceField = function(i, field) {
			this.fieldList.fields.splice(i, 1, jQuery.extend(true, {}, field));
			this.historyManager.addHistory();
		},
		/*
		 * 移动字段
		 */
		this.moveField = function(start, end) {
			var t = this.fieldList.fields[start];
			this.fieldList.fields[start] = this.fieldList.fields[end];
			this.fieldList.fields[end] = t;
			this.historyManager.addHistory();
		},
		/*
		 * 更新字段
		 */
		this.updField = function(field, name, value) {
			field[name] = value;
			this.historyManager.addHistory();
		}
	}

	/*
	 * 历史数据操作
	 */
	function HistoryManager(fieldList, data) {
		{
			//历史中存放所有字段的数据
			this.historys =[ jQuery.extend(true, [], data.fieldList)];
			
		
			this.currHistory = 0;
			this.historySize = 100;
			this.fieldList = fieldList;
			fieldList.historyManager = this;
		}
		/*
		 * 添加到history
		 */
		this.addHistory = function() {
			//取得当前的字段数据
			var c = jQuery.extend(true, [], this.fieldList.fields);
			this.historys[this.currHistory].index = this.fieldList.index;
			if (this.currHistory == this.historySize - 1) {
				this.historys.shift();
				this.historys.push(c);
			} else if (this.currHistory == this.historys.length - 1) {
				this.historys.push(c);
				this.currHistory++;
			} else {
				this.historys.splice(this.currHistory + 1);
				this.historys.push(c);
				this.currHistory++;
			}
			this.fieldList.updToolBar();
		},
		/*
		 * 撤销
		 */
		this.backHistory = function() {
			if (this.currHistory > 0) {
				this.currHistory--;
				var fields = jQuery.extend(true, [], this.historys[this.currHistory]);
				this.fieldList.fields = fields;
				this.fieldList.showFieldList();
				this.fieldList.selectField(fields.index);
			}
			this.fieldList.updToolBar();
		},
		/*
		 * 重做
		 */
		this.forwardHistory = function() {
			if (this.currHistory < this.historys.length - 1) {
				this.currHistory++;
				var fields = jQuery.extend(true, [], this.historys[this.currHistory]);
				this.fieldList.fields = fields;
				this.fieldList.showFieldList();
				this.fieldList.selectField(fields.index);
			}
			this.fieldList.updToolBar();
		};
	}
	;

});