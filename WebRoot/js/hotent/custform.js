$(function() {
	var controlID = 0;
	$.fn.custform = {
		init : function() {
			this.tables = {};
			$('div[type=subtable]').each(
				function() {
					var table = $(this);
					//没有show类型的属于表内编辑模式
					var row = $(this).find('[formType=form]');
					//表单
					var form;
					//窗口编辑模式
					if(row.length > 0) {
						form = $(this).find('[formType=window]');
					}
					//页内编辑模式
					else {
						row = $(this).find('[formType=edit]');
					}
					table.data('row', $('<div></div>').append(row.clone()).html());
					row.css('display', 'none').html('');
					//窗口编辑模式
					if(form) {
						table.data('form', '<form>' + $('<div></div>').append(form.clone()).html() + '</form>');
						form.remove();
					}
					
					var tableName = $(this).attr('tableName');
					
					$.fn.custform.tables[tableName] = table;
					//子表的权限
					var canWrite = ($(this).attr('right') == 'w');
					var canRead = canWrite || ($(this).attr('right') == 'r');
					
					$(this).find('[formType="newrow"]').each(function() {
						$(this).find('[name^=s]:visible').each(function() {
							$(this).attr('name', $(this).attr('name') + ':' + controlID++);
						});
						
						if(canWrite) {
							$.fn.custform.addBind($(this), table);
						}
					});
					
					if(canWrite) {
						var menu = $.ligerMenu({
							top : 100,
							left : 100,
							width : 130,
							items : [
								{
									text : '添加',
									click : function() {
										$.fn.custform.add($(menu.target).closest('div[type=subtable]'));
									}
								}
							]
						});
						$(this).find('[formType]:first').prev().bind("contextmenu", function(e) {
							menu.target = e.target;
							menu.show({
								top : e.pageY,
								left : e.pageX
							});
							return false;
						});
					}
					
					if(!canRead) {
						$(this).css('display', 'none');
					}
				});
			this.initUI();
		},
		initUI : function(parent) {
			var filter;
			if (!parent) {
				parent = $('body div[type=custform]');
				
				filter = 'input,textarea';
			} else {
				filter = 'input,textarea,.dicComboTree,.dicComboBox,.dicCombo';
			}

			$.metadata.setType("attr", "validate");
			
			parent.find(filter).each(function() {
				if ($(this).attr('ltype') == 'text') {
					$(this).ligerTextBox({
						width : $(this).width() - 2
					});
				} else if ($(this).attr('ltype') == 'number') {
					$(this).ligerSpinner({
						width : $(this).width() - 2
					});
				} else if ($(this).attr('ltype') == 'date') {
					$(this).ligerDateEditor({
						width : $(this).width() - 2
					});
				} else if ($(this).is('.dicComboTree,.dicComboBox,.dicCombo')) {
					$(this).htDicCombo();
				}
			});
			if (parent.is('form')) {
				parent.data('validate', this.getValidate(parent));
			} else {
				this.valid = this.getValidate(parent.closest('form'));
			}
		},
		add : function(table, beforeElement) {
			if (table.data('form')) {
				this.openWin('添加', $(table.data('form')), table, beforeElement, function(form, table, beforeElement) {
					var v = form.data('validate');
					if (!v.checkForm()) {
						v.showErrors();
						return false;
					} else {
						var record = {};
						var hidden = $('<div type="hidden"></div>');
						form.find('input[name], textarea[name]').each(function() {
							hidden.append($('<input type="hidden"/>').attr('name', $(this).attr('name')).val($(this).val()));
							record[$(this).attr('name')] = $(this).val();
						});
						
						var row = $(table.data('row'));
						row.append(hidden);
						row.find('[fieldName]').each(function() {
							var fieldName = $(this).attr('fieldName');
							var value = record[fieldName];
							if (!value)
								value = '';
							$(this).text(value);
						});
						
						$.fn.custform._add(table, row, beforeElement);
						v.showErrors();
						return true;
					}
				});
			} else {
				var row = $(table.data('row'));
				row.find('[name^=s]').each(function() {
					$(this).attr('name', $(this).attr('name') + ':' + controlID++);
				});
				this._add(table, row, beforeElement);
			}
		},
		_add : function(table, newRow, beforeElement) {
			if (beforeElement) {
				$(beforeElement).before(newRow);
			} else {
				table.find('[formType]:last').after(newRow);
			}
			this.initUI(newRow);
			this.addBind(newRow, table);
		},
		addBind: function(row, table) {
			//是否需要编辑按钮
			var needEdit = ($.fn.custform.tables[table.attr('tableName')].data('form') != null);
			var menu = $.fn.custform.getMenu(needEdit);
			row.bind("contextmenu", function(e) {
				menu.target = e.target;
				menu.show({
					top : e.pageY,
					left : e.pageX
				});
				return false;
			});
		},
		edit : function(table, row) {
			var tableName = table.attr('tableName');
			var form = $(this.tables[tableName].data('form'));
			form.find('[name]').each(function() {
				var value = row.find('[name="' + $(this).attr('name') + '"]').val();
				$(this).val(value);
			});
			form.data('row', row);
			this.openWin('编辑', form, table, null, function(form, table) {
				var v = form.data('validate');
				if (!v.checkForm()) {
					v.showErrors();
				} else {
					var record = {};
					var row = form.data('row');
					var hidden = row.find('div[type=hidden]').empty();
					form.find('input[name], textarea[name]').each(function() {
						hidden.append($('<input type="hidden"/>').attr('name', $(this).attr('name')).val($(this).val()));
						record[$(this).attr('name')] = $(this).val();
					});
					
					$(row).find('[fieldName]').each(function() {
						var fieldName = $(this).attr('fieldName' );
						var value = record[fieldName];
						if (!value)
							value = '';
						$(this).text(value);
					});
					
					v.showErrors();
					return true;
				}
			});
		},
		getValidate : function(target) {
			return target.validate({
				debug : false,
				errorPlacement : function(lable, element) {
					if(element.hasClass("l-text-field")) {
						element = element.parent();
					}
					element.addClass('validError');
					element.mouseover(function() {
						element.ligerTip({
							content : lable.html(),
							appendIdTo : lable
						});
					});
					element.mouseout(function() {
						lable.ligerHideTip();
					});
					
					lable.element = element;
				},
				success : function(lable) {
					lable.element.removeClass('validError');
					lable.element.unbind('mouseover');
					//lable.element.unbind('mouseout');
					lable.ligerHideTip();
				}
				/*,
				submitHandler : function() {
					$("form .l-text,.l-textarea").ligerHideTip();
					//$.fn.custform.submit();
				}
				*/
			});
			
		},
		validate: function() {
			return this.valid.checkForm();
		},
		showError: function() {
			this.valid.showErrors();
		},
		/*
		submit : function() {
			
			$.post('../bpmFormHandler/save.ht', {
				data: this.getData()
			}, function(data) {
				alert(data);
			});
			
		},
		*/
		getData: function() {
			// 主表数据
			var main = {
				tableId: $('#tableId').val(),
				tableName: $('#tableName').val(),
				fields:{}
				};
			$('input[name^=m], textarea[name^=m]').each(function() {
				var name = $(this).attr('name');
				main.fields[name.replace(/.*:/, '')] = $(this).val();
			});
			
			//子表数据
			var sub = [];
			$('div[type=subtable]').each(function() {
				var table = {
					tableName: $(this).attr('tableName'),
					fields: []
				};
				$(this).find('[formtype]:visible').each(function() {
					var row = {};
					$(this).find('input,textarea').each(function() {
						var name = $(this).attr('name').split(':')[2];
						row[name] = $(this).val();
					});
					table.fields.push(row);
				});
				sub.push(table);
			});
			
			//意见
			var opinion = [];
			$('textarea[name^=opinion]').each(function() {
				opinion.push({
					name: $(this).attr('name').split(':')[1],
					value: $(this).val()
				});
			});
			
			var data = {main: main, sub: sub, opinion: opinion};
			
			return JSON2.stringify(data);
		},
		openWin : function(title, form, table, beforeElement, callback) {
			form.data('beforeElement', beforeElement);
			var win = form.ligerWindow({
					width : 400,
					height : 300,
					left : ($(window).width() - 400) / 2,
					top : ($(window).height() - 300) / 2,
					title : title,
					showMax : true,
					onClose : true,
					showButton : true,
					buttons : [ {
						text : "确定",
						handler : function() {
							var result = callback(form, table, form.data('beforeElement'));
							if(result) {
								win.close();
							}
						}
					} ]
				});
			this.initUI(form);
			win.show();
		},
		getMenu : function(needEdit) {
			if ((needEdit && this.menuWithEdit) || (!needEdit && this.menu)) {
				return needEdit ? this.menuWithEdit : this.menu;
			} else {
				var menu;
				var items = [ {
					text : '在前面插入记录',
					click : function() {
						var row = $(menu.target).closest('[formType]');
						var table = row.closest('div[type=subtable]');
						$.fn.custform.add(table, row);
					}
				}, {
					text : '在后面插入记录',
					click : function() {
						var row = $(menu.target).closest('[formType]');
						var table = row.closest('div[type=subtable]');
						row = row.next('[formType]:visible');
						if (row.length == 0) {
							row = null;
						}
						$.fn.custform.add(table, row);
					}
				}, {
					line : true
				}, {
					text : '编辑',
					click : function() {
						var row = $(menu.target).closest('[formType]');
						var table = row.closest('div[type=subtable]');
						$.fn.custform.edit(table, row);
					}
				}, {
					text : '删除此记录',
					click : function() {
						var t = $(menu.target).closest('[formType]');
						t.remove();
					}
				}, {
					line : true
				}, {
					text : '向上移动',
					click : function() {
						var t = $(menu.target).closest('[formType]');
						var prev = t.prev('[formType]:visible');
						if (prev.length > 0) {
							prev.before(t);
						}
					}
				}, {
					text : '向下移动',
					click : function() {
						var t = $(menu.target).closest('[formType]');
						var next = t.next('[formType]:visible');
						if (next.length > 0) {
							next.after(t);
						}
					}
				} ];
				if (!needEdit) {
					items.splice(3, 1);
				}
				menu = $.ligerMenu({
					top : 100,
					left : 100,
					width : 130,
					items : items
				});
				if (needEdit) {
					this.menuWithEdit = menu;
				}
				else
				{
					this.menu = menu;
				}
				return menu;
			}
		}
	};
	$.fn.custform.init();
});