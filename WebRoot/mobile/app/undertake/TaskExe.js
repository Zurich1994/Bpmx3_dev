/**
 * 转办（代理）事宜
 * 
 * @author zxh
 * @date 2013-06-04
 */
Ext.define('mobile.undertake.TaskExe', {
	extend : 'HT.PageList',

	name : 'taskExe',

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

		Ext.define('modelItem', {
			extend : 'Ext.data.Model',
			config : {
				fields : [{
					name : 'id',
					type : 'string'
				}, {
					name : 'runId',
					type : 'string'
				}, {
					name : "subject",
					type : "string"
				}, {
					name : "processName",
					type : "string"
				}, {
					name : "status",
					type : "int"
				}, {
					name : "assignType",
					type : "int"
				}, {
					name : "createtime",
					type : "date",
					convert : this.convertCreateTime
				}]
			}
		});

		this.store = Ext.create('HT.Store', {
			model : 'modelItem',
			url : __ctx + '/platform/mobile/mobileTask/taskExe.ht'
		});

		Ext.apply(conf, {
			title : lang.menus.taskExe,
			store : this.store,
			latestfetched : this.latestfetched,
			itemTpl : Ext
					.create(
							'Ext.XTemplate',
							'<div class="x-pagelist-contact">',
							'<div class="top">',
							'<div class="name">{subject}<span>{processName}{[this.convertStatus(values.status,values.assignType)]}{[this.convertAssignType(values.assignType)]}<span class="time">{createtime}</span></span></div>',
							'</div>', '</div>',
							'<div type="image" class="userImage" />', {
								// 转换状态
								convertStatus : function(status, assignType) {

									switch (status) {
										case 0 :
											return "<span class='green'>"
													+ lang.taskExe.status.init
													+ " </span>";
										case 1 :
											return "<span class='green'>"
													+ lang.taskExe.status.completed
													+ " </span>";
										case 2 :
											return "<span class='red'>"
													+ (assignType == 1
															? lang.taskExe.status.cancelAgency
															: lang.taskExe.status.cancelTransmitting)
													+ "</span>";
										case 3 :
											return "<span class='orange'>"
													+ lang.taskExe.status.otherCompleted
													+ " </span>";
										default :
											return '';
									}
								},
								convertAssignType : function(v) {
									return HTUtil.assignType(v);
								}
							}),
			items : [toolbar, Ext.create('mobile.UserInfo')]
		});

		this.callParent([conf]);

	},
	/**
	 * 查询
	 */
	onSearch : function() {
		var searchVal = this.down('toolbar').getCmpByName('searchField');
		this.searchInfo(searchVal.getValue());
	},
	/**
	 * 刷新
	 */
	onRefresh : function() {
		this.down('toolbar').getCmpByName('searchField').setValue('');
		this.searchInfo('');
	},
	/**
	 * 查询信息
	 */
	searchInfo : function(val) {
		this.store.getProxy().setExtraParams({
			'Q_subject_SL' : val
		});
		this.store.loadPage(1);
	},
	latestfetched : function(loaded, arguments) {
		loaded.getList().getStore().getProxy()
				.setExtraParam('Q_subject_SL', '');
		loaded.getList().getStore().loadPage(1);
	},
	/**
	 * 转换时间
	 */
	convertCreateTime : function(v) {
		return HT.parseLongTime(v);
	},
	/**
	 * 选择记录
	 */
	select : function(view, record) {

		if (!isUserImage) {
			var runId = view.get('runId'), status = view.get('status'), assignType = view
					.get('assignType'), taskExeId = view.get('id');
			var folwType = -1
			if (status == 0 && assignType == 1)
				folwType = 0;// 允许取消代理
			else if (status == 0 && assignType == 2)
				folwType = 1;// 允许取消转办
			HTUtil.getFolwForm({
				runId : runId,
				folwType : folwType,
				taskExeId : taskExeId,
				scope : this
			});
		}
		isUserImage = false;
	}

});
