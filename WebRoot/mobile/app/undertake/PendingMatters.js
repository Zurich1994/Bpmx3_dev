/**
 * 待办事宜
 * 
 * @author zxh
 * @date 2013-06-04
 * 
 */

Ext.define('mobile.undertake.PendingMatters', {
	extend : 'HT.PageList',
	name : 'pendingMatters',
	constructor : function(conf) {

		conf = conf || {};
		// 顶部按钮
		var toolbar = Ext.create('Ext.Toolbar', {
			docked : 'top',
			items : [{
				xtype : 'spacer'
			}, {
				xtype : 'searchfield',
				placeHolder : lang.operate.searchPlaceHolder,
				name : 'searchField',
				flex : 3
			}, {
				xtype : 'button',
				iconCls : 'search',
				iconMask : true,
				scope : this,
				flex : .5,
				handler : this.onSearch
			}, {
				xtype : 'button',
				iconCls : 'refresh',
				iconMask : true,
				scope : this,
				flex : .5,
				handler : this.onRefresh
			}, {
				xtype : 'spacer'
			}]
		});

		Ext.define('taskItem', {
			extend : 'Ext.data.Model',
			config : {
				fields : [{
					name : 'id',
					type : 'string'
				}, {
					name : 'subject',
					type : 'string'
				}, {
					name : 'name',
					type : 'string'
				},{
					name : 'description',
					type : 'string',
					convert : this.convertStatus
				}, {
					name : 'createTime',
					type : 'date',
					convert : this.convertCreateTime
				}]
			}
		});

		this.store = Ext.create('HT.Store', {
			model : 'taskItem',
			url : __ctx + '/platform/mobile/mobileTask/pendingMatters.ht'
		});

		Ext.apply(conf, {
			title : lang.menus.pendingMatters,
			store : this.store,
			latestfetched : this.latestfetched,
			itemTpl : Ext
					.create(
							'Ext.XTemplate',
							'<div class="x-pagelist-contact">',
							'<div class="top">',
							'<div class="name">{subject}<span>{name}{description}<span class="time">{createTime}</span></span></div>',
							'</div>', '</div>',
							'<div type="image" class="userImage" />'),
			items : [toolbar, Ext.create('mobile.UserInfo')]
		});

		this.callParent([conf]);

	},
	/**
	 * 查询
	 */
	onSearch : function() {
		var searchVal = this.down('toolbar').getCmpByName('searchField').getValue();
		this.searchInfo(searchVal);
	},
	/**
	 * 刷新
	 */
	onRefresh : function() {
		this.down('toolbar').getCmpByName('searchField').setValue('');
		this.searchInfo();
	},
	/**
	 * 查询信息
	 */
	searchInfo : function(val) { 
		var store =  this.store;
		store.getProxy().setExtraParams(
			{'Q_subject_SL':Ext.isEmpty(val)?'':val});
		store.loadPage(1);
	},
	latestfetched : function(loaded, arguments) {
		var store = loaded.getList().getStore();
		store.getProxy().setExtraParams({'Q_subject_SL':''});
		store.loadPage(1)
	},
	/**
	 * 转换时间
	 */
	convertCreateTime : function(v, r) {
		return HT.parseLongTime(v);
	},
	/**
	 * 转换状态
	 */
	convertStatus : function(v, r) {
		return HTUtil.taskStatus(v);
	},
	/**
	 * 选择记录
	 */
	select : function(view, record) {
		if (!isUserImage) {
			HT.loadMask();
			var me = this, taskId = view.get('id');
			Ext.Ajax.request({
				url : __ctx + '/platform/mobile/mobileTask/getTaskForm.ht',
				params : {
					taskId : taskId
				},
				scope : this,
				success : function(response) {
					HT.unMask();
					var result = Ext.util.JSON.decode(response.responseText);
					if (!result.success) {
						Ext.Msg.alert(lang.operate.msgTip, result.msg);
						me.store.loadPage(1);
						return;
					}
					var form = result.form, formfields = [], subTables = [];
					// 主表字段
					if (form.fields.length != 0)
						formfields = Ext.util.JSON.decode(form.fields);
					if (form.subTableList.length != 0)
						subTables = form.subTableList;
					if (form.extForm) {
						Ext.Msg.alert(lang.operate.msgTip, '暂时不支持url表单！');
						return;
					}

					mobileView.push(Ext.create('mobile.form.TableForm', {
						form : form,
						extForm : form.extForm,
						formDetailUrl : form.formDetailUrl,
						formEditUrl : form.formEditUrl,
						mainFields : formfields,
						subTables : subTables,
						taskId : taskId,
						runId : result.runId,
						notApprove : false,
						isOption:form.option,
						particular : form.particular,// 是否是特殊流程
						formData : form.formData,
						isFirstNode:result.isFirstNode,
						isSignTask : result.isSignTask
								? result.isSignTask
								: false,// 是否会签任务
						isAllowDirectExecute : result.isAllowDirectExecute
								? result.isAllowDirectExecute
								: false,// 是否允许特权
						isAllowRetoactive : result.isAllowRetoactive
								? result.isAllowRetoactive
								: false,// 补签
						isCanBack : result.isCanBack ? result.isCanBack : false,// 是否回退
						isCanAssignee : result.isCanAssignee
								? result.isCanAssignee
								: false,
						mapButton : result.mapButton,// 按钮
						isTaskNotify : result.isTaskNotify,// 是否沟通
						isRestartTask : result.isRestartTask,
						moblieImage : result.moblieImage,// 手机流程图
						taskOpinion : result.taskOpinion,
						callback : function() {
							me.store.loadPage(1);
						}
					}));
				},
				failure : function(response) {
					HT.unMask();
					var result = Ext.util.JSON.decode(response.responseText);
					Ext.Msg.alert(lang.operate.msgTip, result.msg);
					this.store.loadPage(1);
				}
			});
		}
		isUserImage = false;
	}

});
