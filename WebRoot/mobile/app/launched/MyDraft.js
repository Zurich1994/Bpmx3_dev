/**
 * 我的草稿
 * 
 * @author zxh
 * @date 2013-06-04
 */
Ext.define('mobile.launched.MyDraft', {
	extend : 'HT.PageList',

	name : 'myDraft',

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
					name : "subject",
					type : "string"
				}, {
					name : "createtime",
					type : "date",
					convert : this.convertCreateTime
				}]
			}
		});

		this.store = Ext.create('HT.Store', {
			model : 'modelItem',
			url : __ctx + '/platform/mobile/mobileTask/myDraft.ht'
		});

		Ext.apply(conf, {
			title : lang.menus.myDraft,
			store : this.store,
			latestfetched : this.latestfetched,
			itemTpl : Ext
					.create(
							'Ext.XTemplate',
							'<div class="x-pagelist-contact">',
							'<div class="top">',
							'<div class="name">{subject}<span><span class="time">{createtime}</span></span></div>',
							'</div>', '</div>',
							'<a type="image" class="userImage"  />'),
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
	convertCreateTime : function(v, r) {
		return HT.parseLongTime(v);
	},
	/**
	 * 选择记录
	 */
	select : function(view, record) {
		if (!isUserImage) {
			var id = view.get('id');
		}
		isUserImage = false;
	}

});
