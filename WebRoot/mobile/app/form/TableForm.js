
/**
 * 手机表单
 * 
 * @author zxh
 * @date 2013-06-04
 */

Ext.define('mobile.form.TableForm', {
	extend : 'Ext.tab.Panel',
	name : 'tableForm',
	constructor : function(conf) {

		conf = conf || {};
		var mainHidden = true;
		this.taskId = conf.taskId,
		this.runId = conf.runId,
		this.parentCallback = conf.callback,
		this.particular = conf.particular,
		this.formData = conf.formData;

		this.tableFormPanel = Ext.create('Ext.form.Panel', {
			fullscreen : true
		});
		// 沟通反馈
		var isTaskNotify = conf.isTaskNotify ? conf.isTaskNotify : false;
		// 是否重启任务
		var isRestartTask = conf.isRestartTask ? conf.isRestartTask : false;
		// 转办、代理
		var folwType = !Ext.isEmpty(conf.folwType) ? conf.folwType : -1;

		conf.notApprove = isTaskNotify ? true : conf.notApprove;
		this.isOption = Ext.isEmpty(conf.isOption)?true:conf.isOption;
		this.isFirstNode = Ext.isEmpty(conf.isFirstNode)?false:conf.isFirstNode;
		
		// 审批意见(不是审批的、有审批意见的，不是第一个节点)
		if (!conf.notApprove && this.isOption && !this.isFirstNode) {
			var voteContentPanel = Ext.create('Ext.Panel', {
				items : [{
					xtype : 'fieldset',
					title : lang.tableForm.option,
					items : [{
						xtype : 'textareafield',
						maxRows : 4,
						name : 'voteContent',
						value : conf.taskOpinion
								? conf.taskOpinion.opinion
								: ""
					}]
				}]
			});
			this.tableFormPanel.add(voteContentPanel);
		}

		// 表单
		if (!conf.extForm) {
			var mainTablePanel = Ext.create('Ext.Panel');
			// 主表字段
			var mainFields = conf.mainFields;
			var subTablePanel = Ext.create('Ext.Panel');
			// 处理子表字段
			var subTables = conf.subTables;

			var isEdit = conf.isEdit ? conf.isEdit : false;

			// 处理主表
			if (mainFields.length > 0) {
				mainHidden = false;
				var html = '<table class="mainTablePanel">'
				for (var idx = 0; idx < mainFields.length; idx++) {
					var field = mainFields[idx];
					html += '<tr>' + '<th width="30%">' + field.label + '</th>'
							+ '<td width="70%">' + field.value + '</td>'
							+ '</tr>';
				}
				mainTablePanel.add({
					xtype : 'panel',
					html : html
				});
			}
			if (subTables.length > 0) {
				// 处理子表
				for (var tbidx = 0; tbidx < subTables.length; tbidx++) {
					var table = subTables[tbidx];
					var fields = table.fields;
					var datas = table.dataList;
					if (datas.length != 0) {
						mainHidden = false;
						var fieldKeys = [];
						var flex = Math.floor(100 / fields.length);
						var html = '<table class="subTablePanel">' + '<tr>';
						for (var idx = 0; idx < fields.length; idx++) {
							var field = fields[idx];
							fieldKeys.push(field.fieldKey);
							html += '<th width="15px" >' + field.fieldVal
									+ '</th>';
						}
						html += '</tr>';

						for (var idx = 0; idx < datas.length; idx++) {
							var data = datas[idx];
							html += '<tr>';
							for (var fidx = 0; fidx < fieldKeys.length; fidx++) {
								html += '<td >' + data[fieldKeys[fidx]]
										+ '</td>';
							}
							html += '</tr>';
						}

						html += '</table>';

						subTablePanel.add({
							xtype : 'panel',
							html : html
						});
					}
				}
			}
			var opinions = conf.form.opinions;
			var opinionPanel = Ext.create('Ext.Panel');
			if (opinions.length > 0) {
				opinions = Ext.util.JSON.decode(opinions);
				var html = '<table class="mainTablePanel">'
				for (var idx = 0; idx < opinions.length; idx++) {
					var field = opinions[idx];
					html += '<tr>' + '<th width="30%">' + field.label + '</th>'
							+ '<td width="70%">' + field.value ;
						if(field.isShow && !this.isFirstNode)
							html +='<textarea id="ext-element-'+field.key+'" class="x-input-el x-input-text x-form-field" type="text" autocapitalize="off" rows="4" name="'+field.name+'"></textarea>' ;
					html +=	'</td></tr>';
				}
				opinionPanel.add({
					xtype : 'panel',
					html : html
				});
			}

			if (mainTablePanel) {
				var tablePanel = Ext.create('Ext.Panel', {
					items : [{
						xtype : 'fieldset',
						title : conf.form.tableName,
						items : [mainTablePanel, subTablePanel,opinionPanel]
					}]
				});
				this.tableFormPanel.add(tablePanel);
			}

		} else {
			// url表单
			mainHidden = false;

			var detail = conf.formDetailUrl;
			var edit = conf.formEditUrl;
			var view = null;
			if (detail != '' || edit != '') {
				if (edit != '') {
					view = edit;
				} else {
					view = detail;
				}

				view = view.substring(5, view.length).replace(
						new RegExp("/", "gm"), ".");
				this.tableFormPanel = Ext.create(view, {
					taskId : conf.taskId != null ? '0' : conf.taskId
				});
			}
		}

		// 按钮处理
		if (isTaskNotify || isRestartTask || folwType != -1) {// 沟通或者取消代理、转办
			if (isTaskNotify) {
				this.toolbar = Ext.create('Ext.Toolbar', {
					docked : 'top',
					items : [{
						xtype : 'button',
						iconMask : true,
						text : lang.tableForm.feedback,
						scope : this,
						handler : this.toTaskNotify
					}]
				});
			}

			if (isRestartTask) {
				this.toolbar = Ext.create('Ext.Toolbar', {
					docked : 'top',
					items : [{
						xtype : 'button',
						iconMask : true,
						text : lang.tableForm.button.agree,
						scope : this,
						handler : this.restartTaskAgree
					}, {
						xtype : 'button',
						iconMask : true,
						text : lang.tableForm.button.notAgree,
						scope : this,
						handler : this.restartTaskNotAgree
					}]
				});
			}

			// 处理流程类型
			if (folwType != -1) {
				this.toolbar = Ext.create('Ext.Toolbar', {
					docked : 'top',
					hidden : (folwType == -1) ? true : false,
					scrollable : {
						direction : 'horizontal',
						indicators : false
					}
				});
				var taskExeText = (folwType == 0
						? lang.tableForm.cancelAgency
						: lang.tableForm.cancelTransmitting);
				switch (folwType) {
					case 0 :// 代理
					case 1 :// 转办
						this.toolbar.add({
							xtype : 'button',
							iconMask : true,
							text : taskExeText,
							scope : this,
							handler : function() {
								this.cancelTaskExe(conf.taskExeId, taskExeText);
							}
						});
						break;
					case 2 :// 我的请求
						var processRun = conf.processRun;
						this.toolbar.add([{
							xtype : 'button',
							iconMask : true,
							text : lang.tableForm.button.recover,// 撤销
							hidden : !(processRun.status == 1 && processRun.allowBackToStart),
							scope : this,
							handler : function() {
								this.recover(conf.runId);
							}
						}, {
							xtype : 'button',
							iconMask : true,
							text : lang.tableForm.button.urge,
							hidden : !(processRun.status == 1 && processRun.allowBackToStart),
							scope : this,
							handler : this.urge
						}, {
							xtype : 'button',
							iconMask : true,
							text : lang.tableForm.button.reSubmit,// 重新提交
							hidden : !(processRun.status == 4 || processRun.status == 5),
							scope : this,
							handler : this.reSubmit
						}, {
							xtype : 'button',
							iconMask : true,
							text : lang.tableForm.button.del,// 删除
							hidden : !(processRun.status == 4 || processRun.status == 5),
							scope : this,
							handler : this.del
						}, {
							xtype : 'button',
							iconMask : true,
							text : lang.tableForm.button.copy,// 复制
							scope : this,
							handler : this.copy
						}]);
						break
					case 3 :// 我的办结
						var processRun = conf.processRun;
						this.toolbar.add([{
							xtype : 'button',
							iconMask : true,
							text : lang.tableForm.button.copy,
							scope : this,
							handler : this.copy
						}, {
							xtype : 'button',
							iconMask : true,
							text : lang.tableForm.button.divert,// 转发
							hidden : !(processRun.status == 2 && processRun.allowFinishedDivert == 1),
							scope : this,
							handler : function() {
								this.divert(conf.runId);
							}
						}, {
							xtype : 'button',
							iconMask : true,
							text : lang.tableForm.button.copyUser,// 抄送人
							scope : this,
							handler : function() {
								this.copyUser(conf.runId);
							}
						}]);
						break
					case 4 :// 办结事宜
						this.toolbar.add({
							xtype : 'button',
							iconMask : true,
							text : lang.tableForm.button.copyUser,// 抄送人
							scope : this,
							handler : function() {
								this.copyUser(conf.runId);
							}
						});
						break
				}

			}
		} else {
			if (Ext.isEmpty(conf.mapButton)) {
				this.toolbar = Ext.create('Ext.Toolbar', {
					docked : 'top',
					hidden : true
				});
			} else {
				var button = conf.mapButton.button;
				if (!Ext.isEmpty(button)) {
					this.toolbar = Ext.create('Ext.Toolbar', {
						scrollable : {
							direction : 'horizontal',
							indicators : false
						},
						docked : 'top',
						hidden : conf.notApprove,
						items : [{
							xtype : 'checkboxfield',
							name : 'isDirectComlete',
							lable : lang.tableForm.button.directComlete,
							cls : 'select',
							checked : false,
							height : '30px',
							width : '20px',
							border : 2,
							hidden : !(conf.isSignTask && conf.isAllowDirectExecute)
						}]
					});
					for (var i = 0; i < button.length; i++) {
						var btn = button[i];
						switch (btn.operatortype) {
							case 1 :// 同意
								this.toolbar.add({
									xtype : 'button',
									iconMask : true,
									text : btn.btnname,
									scope : this,
									handler : this.complete
								});
								break;
							case 2 :// 反对
								this.toolbar.add({
									xtype : 'button',
									iconMask : true,
									text : btn.btnname,
									//hidden : !conf.isSignTask,
									scope : this,
									handler : this.notAgree
								});
								break;
							case 3 :// 弃权
								this.toolbar.add({
									xtype : 'button',
									iconMask : true,
									text : btn.btnname,
									//hidden : !conf.isSignTask,
									scope : this,
									handler : this.abandon
								});
								break;

							case 4 :// 驳回
								this.toolbar.add({
									xtype : 'button',
									iconMask : true,
									text : btn.btnname,
									hidden : !conf.isCanBack,
									scope : this,
									handler : this.reject
								});
								break;

							case 5 :// 驳回到发起人
								this.toolbar.add({
									xtype : 'button',
									iconMask : true,
									text : btn.btnname,
									hidden : !conf.isCanBack,
									scope : this,
									handler : this.rejectToStart
								});
								break;
							case 15 :// 终止
								this.toolbar.add({
									xtype : 'button',
									iconMask : true,
									text : btn.btnname,
									scope : this,
									handler : this.complete
								});
								break;
							case 6 :// 转办
								this.toolbar.add({
									xtype : 'button',
									iconMask : true,
									text : btn.btnname,
									hidden : !conf.isCanAssignee,
									scope : this,
									handler : this.changeAssignee
								});
								break;
							case 7 :// 补签
								this.toolbar.add({
									xtype : 'button',
									iconMask : true,
									text : btn.btnname,
									hidden : !conf.isSignTask,
									scope : this,
									handler : this.flowDesign
								});
								break;
							case 8 :// 保存表单
								this.toolbar.add({
									xtype : 'button',
									iconMask : true,
									text : btn.btnname,
									scope : this,
									handler : this.saveForm
								});
								break;
							case 16 :// 沟通意见
								this.toolbar.add({
									xtype : 'button',
									iconMask : true,
									text : btn.btnname,
									hidden : !conf.isCanBack,
									scope : this,
									handler : this.communication
								});
								break;
							default :
								break;
						}

					}
				} else {
					this.toolbar = Ext.create('Ext.Toolbar', {
						scrollable : {
							direction : 'horizontal',
							indicators : false
						},
						docked : 'top',
						hidden : conf.notApprove,
						items : [{
							xtype : 'checkboxfield',
							name : 'isDirectComlete',
							cls : 'checkbox',
							lable : lang.tableForm.button.directComlete,
							checked : false,
							height : '30px',
							width : '20px',
							hidden : !(conf.isSignTask && conf.isAllowDirectExecute)
						}, {
							xtype : 'button',
							iconMask : true,
							text : lang.tableForm.button.agree,
							scope : this,
							handler : this.complete
						},
								// {
								// xtype: 'button',
								// iconMask: true,
								// text: lang.tableForm.button.notAgree,,
								// hidden:!conf.isSignTask,
								// scope:this,
								// handler: this.notAgree
								// },
								// {
								// xtype: 'button',
								// iconMask: true,
								// text: lang.tableForm.button.abandon,
								// hidden:!conf.isSignTask,
								// scope:this,
								// handler: this.abandon
								// },
								{
									xtype : 'button',
									iconMask : true,
									text : lang.tableForm.button.retoactive,
									hidden : !(conf.isSignTask && conf.isAllowRetoactive),
									scope : this,
									handler : this.retoactive
								}, {
									xtype : 'button',
									iconMask : true,
									text : lang.tableForm.button.saveForm,
									scope : this,
									handler : this.saveForm
								}, {
									xtype : 'button',
									iconMask : true,
									text : lang.tableForm.button.changeAssignee,
									hidden : !conf.isCanAssignee,
									scope : this,
									handler : this.changeAssignee
								}, {
									xtype : 'button',
									iconMask : true,
									text : lang.tableForm.button.rejectToStart,
									hidden : !conf.isCanBack,
									scope : this,
									handler : this.rejectToStart
								}, {
									xtype : 'button',
									iconMask : true,
									text : lang.tableForm.button.communication,
									scope : this,
									handler : this.communication
								}]
					});
				}
			}

		}
		// =============按钮end====================

		Ext.apply(conf, {
			title : lang.tableForm.tableDetail,
			activeItem : mainHidden ? 1 : 0,
			items : [this.toolbar, {
				title : lang.tableForm.flowTable,
				layout : 'fit',
				items : [this.tableFormPanel],
				hidden : mainHidden
			}, {
				title : lang.tableForm.userImage,
				xtype : 'panel',
				layout : 'fit',
				flex : 2,
				items : Ext.create('mobile.form.UserImage', {
					moblieImage : conf.moblieImage
				})
			}, {
				xtype : 'panel',
				title : lang.tableForm.taskOpinions,
				layout : 'fit',
				hidden : conf.taskOpinions ? conf.taskOpinions : false,
				flex : 1,
				items : [Ext.create('mobile.form.TaskOpinions', {
					taskId : conf.taskId ? conf.taskId : '',
					runId : conf.runId ? conf.runId : ''
				})]

			}, Ext.create('mobile.UserInfo')],
			listeners : {
				'activeitemchange' : function() {

				}
			}
		});

		this.callParent([conf]);
	},
	/**
	 * 返回list页面
	 */
	returnBack : function() {
		mobileView.pop();
		this.parentCallback.call();
	},
	/**
	 * 完成任务
	 */
	complete : function() {
		var isDirectComlete = this.toolbar.getCmpByName('isDirectComlete');
		var v = isDirectComlete.isChecked();
		var tmp = 1;
		if (v) {// 直接一票通过
			tmp = 5;
		}
		var param = {
			voteAgree : tmp,
			back : 0,
			msg : lang.tableForm.msg.execute
		};
		this.completeTask(param);
	},
	/**
	 * 完成任务
	 * 
	 * @param {}param.voteAgree
	 *            0，弃权，1，同意，2反对。
	 * @param {}
	 *            param.back 0. 不进行驳回 1，驳回，2，驳回发起人。
	 */
	completeTask : function(param) {
		var formPanel = this.tableFormPanel,
			formData = this.getFormData({
				formData:this.formData,
				formPanel:formPanel,
				isOption:this.isOption
		});
		$postForm({
			formPanel : formPanel,
			scope : this,
			url : __ctx + '/platform/mobile/mobileTask/complete.ht',
			params : {
				'voteContent' : (this.isOption&&!this.isFirstNode)? formPanel.getCmpByName('voteContent')
						.getValue():"",
				'taskId' : this.taskId,
				'voteAgree' : param.voteAgree,
				'back' : param.back,
				'particular' : (this.particular||!this.isOption)?true:false,
				'formData' : formData
			},
			success : function(fp, action) {
				if (action.success) {
					Ext.Msg.alert('', param.msg, this.returnBack, this);
				} else {
					Ext.Msg.alert('', action.msg);
				}
			}
		});

	},
	getFormData:function(conf){
		var formPanel = conf.formPanel,formData =conf.formData;
		if(Ext.isEmpty(conf.formData)){
			//意见
			var opinion = [];
			var op = Ext.query('textarea[name^=opinion]');
			Ext.each(op,function(item) {
				var i =Ext.fly(item);
				opinion.push({
					name: i.getAttribute("name").split(':')[1],
					value: i.getValue()
				});
			});
			var main = {
				fields:{}
			};
			var sub = [];
			var data = {main: main, sub: sub, opinion: opinion};	
			return Ext.util.JSON.encode(data);;
		}
		return formData;
	},
	/**
	 * 驳回
	 */
	reject : function() {
		if(this.isOption){
			var formpanel = this.tableFormPanel,
			voteContent = formpanel.getCmpByName('voteContent'),
			voteContentVal = voteContent
					.getValue();
			if (Ext.isEmpty(voteContentVal)) {
				this.setActiveItem(0);
				Ext.Msg.alert('', lang.tableForm.msg.option, function() {
					voteContent.focus();
				});
				return;
			}
		}
		var param = {
			voteAgree : 3,
			back : 1,
			msg : lang.tableForm.msg.reject
		};
		this.completeTask(param);
	},
	/**
	 * 驳回发起人
	 */
	rejectToStart : function() {
		if(this.isOption){
			var formpanel = this.tableFormPanel,
			voteContent = formpanel.getCmpByName('voteContent'),
			voteContentVal = voteContent
					.getValue();
			if (Ext.isEmpty(voteContentVal)) {
				this.setActiveItem(0);
				Ext.Msg.alert('', lang.tableForm.msg.option, function() {
					voteContent.focus();
				});
				return;
			}
		}
		var param = {
			voteAgree : 3,
			back : 2,
			msg : lang.tableForm.msg.rejectToStart
		};
		this.completeTask(param);
	},
	/**
	 * 反对
	 */
	notAgree : function() {
		var isDirectComlete = this.toolbar.getCmpByName('isDirectComlete');
		var v = isDirectComlete.isChecked();
		var tmp = 2;
		if (v) {// 直接一票通过
			tmp = 6;
		}
		var param = {
			voteAgree : tmp,
			back : 0,
			msg : lang.tableForm.msg.execute
		};
		this.completeTask(param);
	},
	/**
	 * 弃权
	 */
	abandon : function() {
		var param = {
			voteAgree : 0,
			back : 0,
			msg : lang.tableForm.msg.execute
		};
		this.completeTask(param);
	},
	/**
	 * 保存表单
	 */
	saveForm : function() {
		var formPanel = this.tableFormPanel;
		$postForm({
			formPanel : formPanel,
			scope : this,
			url : __ctx + '/platform/mobile/mobileTask/saveData.ht',
			params : {
				'voteContent' : formPanel.getCmpByName('voteContent')
						.getValue(),
				'taskId' : this.taskId
			},
			success : function(fp, action) {
				if (action.success) {
					Ext.Msg.alert('', action.msg, this.returnBack, this);
				} else {
					Ext.Msg.alert('', action.msg);
				}
			}
		});
	},
	/**
	 * 补签
	 */
	retoactive : function() {
		this.retoactiveFormPanel = {
			xtype : 'formpanel',
			modal : true,
			width : '98%',
			height : '200px',
			scrollable : true,
			centered : true,
			items : [{
				docked : 'top',
				xtype : 'toolbar',
				title : lang.tableForm.retoactive.title
			}, {
				xtype : 'hiddenfield',
				name : 'signUserIds',
				label : lang.tableForm.retoactive.signUserIds
			}, {
				xtype : 'textfield',
				inputCls : 'user',
				style : 'border-top:solid 1px #cacaca;border-left:solid 1px #cacaca;border-right:solid 1px #cacaca;',
				iconMask : true,
				label : lang.tableForm.retoactive.signFullnames,
				name : 'signFullnames',
				readOnly : true,
				listeners : {
					scope : this,
					element : 'element',
					tap : this.selectRetUser
				}
			}, {
				docked : 'bottom',
				xtype : 'toolbar',
				layout : {
					type : 'hbox',
					pack : 'middle'
				},
				items : [{
					xtype : 'button',
					iconMask : true,
					text : lang.operate.button.cancel,
					scope : this,
					handler : this.cancelRetoactive
				}, {
					xtype : 'button',
					iconMask : true,
					text : lang.tableForm.retoactive.button,
					scope : this,
					handler : this.saveRetoactive
				}]
			}]
		};

		this.retoactivePanel = Ext.Viewport.add(this.retoactiveFormPanel);
		this.retoactivePanel.show();
	},
	/**
	 * 保存补签
	 */
	saveRetoactive : function() {
		var t = this.retoactivePanel, signUserIds = t
				.getCmpByName('signUserIds').getValue();
		if (Ext.isEmpty(signUserIds)) {
			Ext.Msg.alert('', ang.tableForm.retoactive.msg);
			return;
		}
		Ext.Ajax.request({
			url : __ctx + '/platform/mobile/mobileTask/addSign.ht',
			params : {
				'taskId' : this.taskId,
				'signUserIds' : signUserIds
			},
			scope : this,
			success : function(response) {
				var obj = Ext.util.JSON.decode(response.responseText);
				if (obj.success) {
					Ext.Msg.alert('', obj.msg, function() {
						this.cancelRetoactive();
					}, this);

				} else {
					Ext.Msg.alert('', obj.msg);
				}

			},
			failure : function(response) {
				var obj = Ext.util.JSON.decode(response.responseText);
				Ext.Msg.alert('', obj.msg);
			}
		});
	},
	/**
	 * 关闭补签
	 */
	cancelRetoactive : function() {
		this.retoactivePanel.hide();
	},
	/**
	 * 选择补签人员
	 */
	selectRetUser : function() {
		var me = this, panel = this.retoactivePanel;
		Ext.create('mobile.selector.UserSelector', {
			scope : this,
			callback : function(ids, names) {
				var userId = panel.getCmpByName('signUserIds'), userName = panel
						.getCmpByName('signFullnames');
				var o = me.parseUser(ids, names, userId.getValue(), userName
						.getValue());

				userId.setValue(o.userIds);
				userName.setValue(o.userNames);
			}
		});
	},

	/**
	 * 转办
	 */
	changeAssignee : function() {
		this.changeAssigneeFormPanel = {
			xtype : 'formpanel',
			modal : true,
			width : '98%',
			height : '300px',
			scrollable : true,
			centered : true,
			items : [{
				docked : 'top',
				xtype : 'toolbar',
				title : lang.tableForm.changeAssignee.title
					// '转办'
					}, {
						xtype : 'hiddenfield',
						name : 'assigneeId',
						label : lang.tableForm.changeAssignee.assigneeId
					}, {
						xtype : 'textfield',
						inputCls : 'user',
						style : 'border-top:solid 1px #cacaca;border-left:solid 1px #cacaca;border-right:solid 1px #cacaca;',
						iconMask : true,
						label : lang.tableForm.changeAssignee.assigneeName,// '转办人'
						name : 'assigneeName',
						readOnly : true,
						listeners : {
							scope : this,
							element : 'element',
							tap : this.selectAssigneeUser
						}
					}, {
						xtype : 'textareafield',
						maxRows : 4,
						style : 'border:solid 1px #cacaca;',
						name : 'memo',
						label : lang.tableForm.changeAssignee.memo
					// 转办原因
					}, {
						docked : 'bottom',
						xtype : 'toolbar',
						layout : {
							type : 'hbox',
							pack : 'middle'
						},
						items : [{
							xtype : 'button',
							iconMask : true,
							text : lang.operate.button.cancel,
							scope : this,
							handler : this.cancelTaskChangeAssignee
						}, {
							xtype : 'button',
							iconMask : true,
							text : lang.operate.button.ok,// 确定
							scope : this,
							handler : this.saveAssignee
						}]
					}]
		};

		this.taskChangeAssignee = Ext.Viewport
				.add(this.changeAssigneeFormPanel);
		this.taskChangeAssignee.show();
	},
	/**
	 * 保存
	 * 
	 * @param {}
	 *            text
	 * @param {}
	 *            message
	 */
	saveAssignee : function() {
		var t = this.taskChangeAssignee, assigneeId = t
				.getCmpByName('assigneeId').getValue(), assigneeName = t
				.getCmpByName('assigneeName').getValue(), memo = t
				.getCmpByName('memo').getValue();
		if (Ext.isEmpty(assigneeName)) {
			Ext.Msg.alert('', lang.tableForm.changeAssignee.msg.assigneeName);
			return;
		}
		if (Ext.isEmpty(memo)) {
			Ext.Msg.alert('', lang.tableForm.changeAssignee.msg.memo);
			return;
		}
		HT.loadMask();
		Ext.Ajax.request({
			url : __ctx + '/platform/mobile/mobileTask/assignSave.ht',
			params : {
				'taskId' : this.taskId,
				'memo' : memo,
				'assigneeId' : assigneeId,
				'assigneeName' : assigneeName
			},
			scope : this,
			success : function(response) {
				HT.unMask();
				var obj = Ext.util.JSON.decode(response.responseText);
				if (obj.success) {
					Ext.Msg.alert('', obj.msg, function() {
						this.cancelTaskChangeAssignee();
						this.returnBack();
					}, this);

				} else {
					Ext.Msg.alert('', obj.msg);
				}

			},
			failure : function(response) {
				HT.unMask();
				var obj = Ext.util.JSON.decode(response.responseText);
				Ext.Msg.alert('', obj.msg);
			}
		});
	},

	/**
	 * 沟通意见
	 */
	communication : function() {
		this.communicationFormPanel = {
			xtype : 'formpanel',
			modal : true,
			width : '98%',
			height : '300px',
			scrollable : true,
			centered : true,
			items : [{
				docked : 'top',
				xtype : 'toolbar',
				title : lang.tableForm.communication.title
					// '沟通意见'
					}, {
						xtype : 'hiddenfield',
						name : 'cmpIds',
						label : lang.tableForm.communication.cmpIds
					// '沟通人ID'
					}, {
						xtype : 'textfield',
						inputCls : 'user',
						style : 'border-top:solid 1px #cacaca;border-left:solid 1px #cacaca;border-right:solid 1px #cacaca;',
						label : lang.tableForm.communication.cmpNames,// '沟通人',
						name : 'cmpNames',
						readOnly : true,
						listeners : {
							scope : this,
							element : 'element',
							tap : this.selectCommUser
						}
					}, {
						xtype : 'textareafield',
						maxRows : 4,
						style : 'border:solid 1px #cacaca;',
						name : 'opinion',
						label : lang.tableForm.communication.opinion
					// '内容'
					}, {
						docked : 'bottom',
						xtype : 'toolbar',
						layout : {
							type : 'hbox',
							pack : 'middle'
						},
						items : [{
							xtype : 'button',
							iconMask : true,
							text : lang.operate.button.cancel,
							scope : this,
							handler : this.cancelCommunication
						}, {
							xtype : 'button',
							iconMask : true,
							text : lang.operate.button.ok,
							scope : this,
							handler : this.saveCommunication
						}]
					}]
		};

		this.communicationPanel = Ext.Viewport.add(this.communicationFormPanel);
		this.communicationPanel.show();
	},

	cancelTaskChangeAssignee : function() {
		this.taskChangeAssignee.hide();
	},
	/**
	 * 选择用户
	 * 
	 * @param {}
	 *            conf
	 */
	selectUser : function(conf) {
		Ext.create('mobile.selector.UserSelector', {
			scope : conf.scope,
			isSingle : conf.isSingle,
			userIds : conf.userIds,
			userNames : conf.userNames,
			callback : conf.callback
		});
	},
	/**
	 * 转办 选择用户
	 */
	selectAssigneeUser : function() {
		var userIds = this.taskChangeAssignee.getCmpByName('assigneeId'), userNames = this.taskChangeAssignee
				.getCmpByName('assigneeName');
		this.selectUser({
			scope : this,
			isSingle : true,
			userIds : userIds.getValue(),
			userNames : userNames.getValue(),
			callback : function(ids, names) {
				userIds.setValue(ids);
				userNames.setValue(names);
			}
		});
	},
	/**
	 * 沟通选择用户
	 */
	selectCommUser : function() {
		var userIds = this.communicationPanel.getCmpByName('cmpIds'), userNames = this.communicationPanel
				.getCmpByName('cmpNames');
		this.selectUser({
			scope : this,
			isSingle : false,
			userIds : userIds.getValue(),
			userNames : userNames.getValue(),
			callback : function(ids, names) {
				userIds.setValue(ids);
				userNames.setValue(names);
			}
		});
	},
	/**
	 * 撤销沟通意见窗口
	 */
	cancelCommunication : function() {
		this.communicationPanel.hide();
	},
	/**
	 * 保存沟通意见
	 */
	saveCommunication : function() {
		var t = this.communicationPanel, cmpIds = t.getCmpByName('cmpIds')
				.getValue(), cmpNames = t.getCmpByName('cmpNames').getValue(), opinion = t
				.getCmpByName('opinion').getValue();
		if (Ext.isEmpty(cmpIds)) {
			Ext.Msg.alert('', lang.tableForm.communication.msg.cmpIds);
			return;
		}
		if (Ext.isEmpty(opinion)) {
			Ext.Msg.alert('', lang.tableForm.communication.msg.opinion);
			return;
		}
		Ext.Ajax.request({
			url : __ctx + '/platform/mobile/mobileTask/toStartCommunication.ht',
			params : {
				'taskId' : this.taskId,
				'opinion' : opinion,
				'cmpIds' : cmpIds,
				'cmpNames' : cmpNames
			},
			scope : this,
			success : function(response) {
				var obj = Ext.util.JSON.decode(response.responseText);
				if (obj.success) {
					Ext.Msg.alert('', obj.msg, function() {
						this.cancelCommunication();
						this.returnBack();
					}, this);

				} else {
					Ext.Msg.alert('', obj.msg);
				}

			},
			failure : function(response) {
				var obj = Ext.util.JSON.decode(response.responseText);
				Ext.Msg.alert('', obj.msg);
			}
		});

	},
	/**
	 * 沟通反馈
	 */
	toTaskNotify : function() {
		Ext.Msg.show({
			message : lang.tableForm.toTaskNotify.title,// '沟通反馈'
			buttons : Ext.MessageBox.OKCANCEL,
			multiLine : true,
			prompt : {
				maxlength : 500,
				autocapitalize : true
			},
			scope : this,
			fn : this.saveTaskNotify
		});
	},
	/**
	 * 保存沟通反馈
	 */
	saveTaskNotify : function(text, message) {
		if (text == 'cancel')
			return;
		if (text == 'ok') {
			if (Ext.isEmpty(message)) {
				Ext.Msg.alert('', lang.tableForm.toTaskNotify.msg, function() {
					this.toTaskNotify();
				}, this);
				return;
			}
		}
		Ext.Ajax.request({
			url : __ctx + '/platform/mobile/mobileTask/saveOpinion.ht',
			params : {
				'taskId' : this.taskId,
				'opinion' : message
			},
			scope : this,
			success : function(response) {
				var obj = Ext.util.JSON.decode(response.responseText);
				if (obj.success) {
					Ext.Msg.alert('', obj.msg, function() {
						this.returnBack();
					}, this);

				} else {
					Ext.Msg.alert('', obj.msg);
				}

			},
			failure : function(response) {
				var obj = Ext.util.JSON.decode(response.responseText);
				Ext.Msg.alert('', obj.msg);
			}
		});
	},
	/**
	 * 取消转办、代理
	 */
	cancelTaskExe : function(taskExeId, taskExeText) {
		Ext.Msg.show({
			message : taskExeText,
			buttons : Ext.MessageBox.OKCANCEL,
			multiLine : true,
			prompt : {
				maxlength : 500,
				autocapitalize : true
			},
			scope : this,
			fn : function(text, message) {
				if (text == 'cancel')
					return;
				if (text == 'ok') {
					if (Ext.isEmpty(message)) {
						Ext.Msg.alert('', lang.tableForm.cancelTaskExe.msg,
								function() {
									this.cancelTaskExe(taskExeId, taskExeText);
								}, this);
						return;
					}
				}
				this.saveTaskExe(taskExeId, message, taskExeText);
			}
		});
	},
	/**
	 * 保存取消转办、代理
	 */
	saveTaskExe : function(taskExeId, opinion, taskExeText) {

		Ext.Ajax.request({
			url : __ctx + '/platform/mobile/mobileTask/cancelTaskExe.ht',
			params : {
				'id' : taskExeId,
				'opinion' : opinion
			},
			scope : this,
			success : function(response) {
				var obj = Ext.util.JSON.decode(response.responseText);
				if (obj.success) {
					Ext.Msg.alert('', String.format(obj.msg, taskExeText),
							function() {
								this.returnBack();
							}, this);

				} else {
					Ext.Msg.alert('', lang.response.error);
				}
			},
			failure : function(response) {
				Ext.Msg.alert('', lang.response.error);
			}
		});
	},
	/**
	 * 抄送人
	 */
	copyUser : function(runId) {
		Ext.define('bpmProCopytoItem', {
			extend : 'Ext.data.Model',
			config : {
				fields : [{
					name : "ccUname",
					type : "string"
				}, {
					name : "posName",
					type : "string"
				}, {
					name : "orgName",
					type : "string"
				}, {
					name : "isReaded",
					type : "int",
					convert : function(v) {
						switch (v) {
							case 0 :
								return "<span class='red'>"
										+ lang.tableForm.copyUser.readed// '未读'
										+ "</span>";
							case 1 :
								return "<span class='green'>"
										+ lang.tableForm.copyUser.noReaded// '已读'
										+ "</span>";
						}
					}
				}, {
					name : "ccTime",
					type : 'date',
					convert : function(v) {
						return HT.parseLongTime(v);
					}
				}, {
					name : 'cpType',
					type : "int",
					convert : function(v) {
						switch (v) {
							case 1 :
								return "<span class='red'>"
										+ lang.tableForm.copyUser.copyTo// '抄送'
										+ "</span>";
							case 2 :
								return "<span class='green'>"
										+ lang.tableForm.copyUser.divert// '转发'
										+ "</span>";
						}
					}
				}]
			}
		});

		var store = Ext.create('HT.Store', {
			model : 'bpmProCopytoItem',
			url : __ctx
					+ '/platform/mobile/mobileTask/getCopyUserByInstId.ht?runId='
					+ runId
		});

		var tip = Ext.Viewport.add({
			xtype : 'list',
			modal : true,
			hideOnMaskTap : true,
			width : '99%',
			height : '99%',
			centered : true,
			styleHtmlContent : true,
			scrollable : true,
			store : store,
			itemTpl : Ext
					.create(
							'Ext.XTemplate',
							'<table class="table-task" cellpadding="0" cellspacing="0" border="0">',
							'<tr>', '<th>' + lang.tableForm.copyUser.ccUname
									+ '</th>', '<td>{ccUname}</td>', '</tr>',
							'<tr>', '<th>' + lang.tableForm.copyUser.posName
									+ '</th>', '<td>{posName}</td>', '</tr>',
							'<tr>', '<th>' + lang.tableForm.copyUser.orgName
									+ '</th>', '<td>{orgName}</td>', '</tr>',
							'<tr>', '<th>' + lang.tableForm.copyUser.isReaded
									+ '</th>', '<td>{isReaded}</td>', '</tr>',
							'<tr>', '<th>' + lang.tableForm.copyUser.ccTime
									+ '</th>', '<td>{ccTime}</td>', '</tr>',
							'<tr>', '<th>' + lang.tableForm.copyUser.cpType
									+ '</th>', '<td>{cpType}</td>', '</tr>',
							'</table>'),
			plugins : [{
				xclass : 'Ext.plugin.ListPaging',
				loadMoreText : lang.tip.loadMoreText,
				noMoreRecordsText : lang.tip.noMoreRecordsText,
				autoPaging : true
			// 设置为TRUE将自动触发
			}],
			emptyText : '<p class="no-searches">' + lang.tip.noRecords + '</p>',
			items : [{
				docked : 'top',
				xtype : 'toolbar',
				title : lang.tableForm.copyUser.title,
				scope : this,
				items : [{
					xtype : 'spacer'
				}, {
					ui : 'close',
					iconCls : 'close',
					iconMask : true,
					scope : this,
					handler : function() {
						tip.hide();
					}
				}]
			}]
		});
		tip.show();
	},
	restartTaskAgree : function() {
		var formPanel = this.tableFormPanel;
		$postForm({
			formPanel : formPanel,
			scope : this,
			url : __ctx + '/platform/mobile/mobileTask/saveRestartTask.ht',
			params : {
				'voteContent' : formPanel.getCmpByName('voteContent')
						.getValue(),
				'voteAgree' : 0,
				'taskId' : this.taskId,
				'runId' : this.runId
			},
			success : function(fp, action) {
				if (action.success) {
					Ext.Msg.alert('', action.msg, this.returnBack, this);
				} else {
					Ext.Msg.alert('', action.msg);
				}
			}
		});
	},
	/**
	 * 重启任务反对
	 */
	restartTaskNotAgree : function() {
		var formPanel = this.tableFormPanel;
		$postForm({
			formPanel : formPanel,
			scope : this,
			url : __ctx + '/platform/mobile/mobileTask/saveRestartTask.ht',
			params : {
				'voteContent' : formPanel.getCmpByName('voteContent')
						.getValue(),
				'voteAgree' : 1,
				'taskId' : this.taskId,
				'runId' : this.runId
			},
			success : function(fp, action) {
				if (action.success) {
					Ext.Msg.alert('', action.msg, this.returnBack, this);
				} else {
					Ext.Msg.alert('', action.msg);
				}
			}
		});
	},
	/**
	 * 撤销
	 * 
	 * @param {}
	 *            runId
	 */
	recover : function(runId) {

	},
	/**
	 * 催办
	 * 
	 * @param {}
	 *            runId
	 */
	urge : function(runId) {
	},
	/**
	 * 复制
	 * 
	 * @param {}
	 *            runId
	 */
	copy : function(runId) {
		Ext.Msg.alert('', lang.tip.kaifa);

	},
	/**
	 * 转发
	 */
	divert : function(runId) {
		Ext.Msg.alert('', lang.tip.kaifa);
	}
});
