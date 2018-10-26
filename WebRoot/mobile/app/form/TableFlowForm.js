
/**
 * 流程表单
 * 
 * @author zxh
 * @date 2013-06-04
 */
Ext.define('mobile.form.TableFlowForm', {
	extend : 'Ext.tab.Panel',
	name : 'tableFlowForm',
	constructor : function(conf) {

		conf = conf || {};
		this.form = conf.form;
		this.tableFormPanel = Ext.create('Ext.form.Panel', {
			fullscreen : true,
			html:"<p class='no-searches'>"+lang.tip.kaifa+"</p>"
			//items: this.getFormItems()
		});

		
		

		// 按钮
		if (Ext.isEmpty(conf.mapButton)) {
			this.toolbar = Ext.create('Ext.Toolbar', {
				docked : 'top',
				hidden : true
			});
		} else {
			var button = conf.mapButton.button;
			// 配置了按钮
			if (!Ext.isEmpty(button)) {
				this.toolbar = Ext.create('Ext.Toolbar', {
					scrollable : {
						direction : 'horizontal',
						indicators : false
					},
					docked : 'top'
				});
				for (var i = 0; i < button.length; i++) {
					var btn = button[i];
					switch (btn.operatortype) {
						case 1 :// 启动流程
							this.toolbar.add({
								xtype : 'button',
								iconMask : true,
								text : btn.btnname,
								scope : this,
								handler : this.startFlow
							});
							break;
						case 2 :// 流程图

							break;
						case 3 :// 打印
						
							break;

						case 6 :// 驳回
							this.toolbar.add({
								xtype : 'button',
								iconMask : true,
								text : btn.btnname,
								scope : this,
								handler : this.saveForm
							});
							break;

						case 7 :// 驳回到发起人
							this.toolbar.add({
								xtype : 'button',
								iconMask : true,
								text : btn.btnname,
								scope : this,
								handler : this.reference
							});
							break;
						default :
							break;
					}

				}
				// 默认按钮
			} else {
				this.toolbar = Ext.create('Ext.Toolbar', {
					scrollable : {
						direction : 'horizontal',
						indicators : false
					},
					docked : 'top',
					items : [{
						xtype : 'button',
						iconMask : true,
						text : '提交',
						scope : this,
						handler : this.startFlow
					}, {
						xtype : 'button',
						iconMask : true,
						text : '保存草稿',
						scope : this,
						handler : this.saveForm

					}]

				});
			}
		}

		Ext.apply(conf, {
			title : lang.tableForm.tableDetail,
			items : [this.toolbar, {
				title : lang.tableForm.flowTable,
				layout : 'fit',
				items : [this.tableFormPanel]
			}, {
				title : lang.tableForm.userImage,
				xtype : 'panel',
				layout : 'fit',
				flex : 2,
				items : Ext.create('mobile.form.UserImage', {
					moblieImage : conf.moblieImage
				})
			}, Ext.create('mobile.UserInfo')],
			listeners : {
				'activeitemchange' : function() {

				}
			}
		});

		this.callParent([conf]);
	},
	getFormItems:function(){
		var me = this, form = this.form,
		items = []; mainFields = [], subTables = [];
		// 主表字段
		if (form.fields.length != 0)
			mainFields = Ext.util.JSON.decode(form.fields);
		//子表字段
		if (form.subTableList.length != 0)
			subTables = form.subTableList;
		
		for (var i = 0; i < mainFields.length; i++) {
			var item = {},
				field = mainFields[i],
				name =field.key,
				label =field.label,
				controlType = field.controlType;

				switch (controlType) {
					case 1 ://单行文本框
						item =  {
	                        xtype: 'textfield',
	                        name: name,
	                        label: label
	                    };
						break;
					case 2 ://多行文本框
						item =  {
	                        xtype: 'textareafield',
	                        name: name,
	                        label: label
	                    };
						break;
					case 3 ://数据字典
						item =  {
	                        xtype: 'selectfield',
	                        name: name,
		                    label: label,
	                       	options: [
	                                { text: 'master', value: 'Master'},
	                                { text: 'padawan', value: 'Student'},
	                                { text: 'teacher', value: 'Instructor'},
	                                { text: 'aid', value: 'Assistant'}
	                            ]
	                        };
						break;
					 case 4 ://人员单选
					 case 8 ://人员多选
					 	var hiddenF=new Ext.form.Hidden({
								name : name+'ID',
								label : label+'ID',
								value:''
						});
						
						var txtF = new Ext.form.Text({
						      	name:name,
						      	label : label,
						     	readOnly:true,
						   		inputCls : 'user',
						   		userId:hiddenF,
						   		isSingle:controlType==4?true:false,
						   		listeners : {
									element : 'element',
									tap : function(){
										me.selectUser({
											userId:this.config.userId,
											userName:this,
											isSingle:this.config.isSingle
										});
									}
								}
						});
						items.push(hiddenF);
						items.push(txtF);
						break;
					case 17 ://角色单选
					case 5 ://角色多选
					 	var hiddenF=new Ext.form.Hidden({
								name : name+'ID',
								label : label+'ID',
								value:''
						});
						
						var txtF = new Ext.form.Text({
						      	name:name,
						      	label : label,
						     	readOnly:true,
						   		inputCls : 'role',
						   		roleId:hiddenF,
						   		isSingle:controlType==17?true:false,
						   		listeners : {
									element : 'element',
									tap : function(){
										me.selectRole({
											roleId:this.config.roleId,
											roleName:this,
											isSingle:this.config.isSingle
										});
									}
								}
						});
						items.push(hiddenF);
						items.push(txtF);
						break;
					case 18 ://组织单选
					case 6 ://组织多选
					 	var hiddenF=new Ext.form.Hidden({
								name : name+'ID',
								label : label+'ID',
								value:''
						});
						
						var txtF = new Ext.form.Text({
						      	name:name,
						      	label : label,
						     	readOnly:true,
						   		inputCls : 'org',
						   		orgId:hiddenF,
						   		isSingle:controlType==17?true:false,
						   		listeners : {
									element : 'element',
									tap : function(){
										me.selectOrg({
											orgId:this.config.orgId,
											orgName:this,
											isSingle:this.config.isSingle
										});
									}
								}
						});
						items.push(hiddenF);
						items.push(txtF);
						break;
				}
			if(!Ext.isEmpty(item))
             	items.push(item);
		}
		
		return [{
                xtype: 'fieldset',
                title: form.tableName,
                items:items
                }];
	},
	selectUser :function(conf){
		var userId =conf.userId,userName =conf.userName;
		Ext.create('mobile.selector.UserSelector', {
			scope : this,
			isSingle:conf.isSingle,
			userIds : userId.getValue(),
			userNames : userName.getValue(),
			callback : function(ids, names) {
				userId.setValue(ids);
				userName.setValue(names);
			}
		});	
	},
	selectOrg:function(conf){
		var orgId =conf.orgId,orgName =conf.orgName;
		Ext.create('mobile.selector.OrgSelector', {
			scope : this,
			isSingle:conf.isSingle,
			orgIds : orgId.getValue(),
			orgNames : orgName.getValue(),
			callback : function(ids, names) {
				orgId.setValue(ids);
				orgName.setValue(names);
			}
		});	
	},
		
	/**
	 * 启动流程
	 */
	startFlow : function() {
		Ext.Msg.alert('', lang.tip.kaifa);
//		Ext.Msg.confirm("提示信息", "确认启动流程吗?", function(rtn) {
//			if (rtn) {
//
//			}
//		});
	},
	/**
	 * 保存草稿
	 */
	saveForm : function() {
		Ext.Msg.alert('', lang.tip.kaifa);
	},
	/**
	 * 流程引用
	 */
	reference : function() {
		Ext.Msg.alert('', lang.tip.kaifa);
	}
});