/**
 * 我的请求
 * 
 * @author zxh
 * @date 2013-06-04
 */

Ext.define('mobile.launched.MyRequest', {
	extend : 'HT.PageList',

	name : 'myRequest',

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
					name : 'runId',
					type : 'string'
				}, {
					name : "subject",
					type : "string"
				}, {
					name : "status",
					type : "int"
				}, {
					name : "createtime",
					type : "date",
					convert : this.convertTime
				}, {
					name : 'allowBackToStart',
					type : 'boolean'
				}]
			}
		});

		this.store = Ext.create('HT.Store', {
			model : 'modelItem',
			url : __ctx + '/platform/mobile/mobileTask/myRequest.ht'
		});

		Ext.apply(conf, {
			title : lang.menus.myRequest,
			store : this.store,
			latestfetched : this.latestfetched,
			itemTpl : Ext
					.create(
							'Ext.XTemplate',
							'<div class="x-pagelist-contact">',
							'<div class="top">',
							'<div class="name">{subject}<span>{[this.convertStatus(values.status)]}<span class="time">{createtime}</span></span></div>',
							'</div>', '</div>',
							'<div type="image" class="userImage"  />', {
								convertStatus : function(status) {
									return HTUtil.processStatus(status);
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
	convertTime : function(v, r) {
		return HT.parseLongTime(v);
	},
	/**
	 * 选择记录
	 */
	select : function(view, record) {
		if (!isUserImage) {
			var runId = view.get('runId');
			var processRun = {
				status : view.get('status'),
				allowBackToStart : view.get('allowBackToStart')
			}
			HTUtil.getFolwForm({
				runId : runId,
				scope : this,
				folwType : 2,
				processRun : processRun
			});
		}
		isUserImage = false;
	}

});
