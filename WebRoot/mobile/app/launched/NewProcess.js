/**
 * 新建流程
 * 
 * @author zxh
 * @date 2013-06-04
 */

Ext.define('mobile.launched.NewProcess', {
	extend : 'HT.PageList',

	name : 'newProcess',

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
					name : 'defId',
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
			url : __ctx + '/platform/mobile/mobileTask/newProcess.ht'
		});

		Ext.apply(conf, {
			title : lang.menus.newProcess,
			store : this.store,
			latestfetched : this.latestfetched,
			itemTpl : Ext
					.create(
							'Ext.XTemplate',
							'<div class="x-pagelist-contact">',
							'<div class="top">',
							'<div class="name">{subject}<span><span class="time">{createtime}</span></span></div>',
							'</div>', '</div>',
							'<div type="image" class="userImage"  />'),
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
			var me = this, defId = view.get('defId');
			HT.loadMask();
			Ext.Ajax.request({
				url : 'platform/mobile/mobileTask/startFlowForm.ht',
				params : {
					defId : defId
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
					var form = result.form;
					mobileView.push(Ext.create('mobile.form.TableFlowForm', {
						form : form,
						mapButton : result.mapButton,
						moblieImage : result.moblieImage,// 手机流程图
						callback : function() {
							me.store.loadPage(1);
						}
					}));
				},
				failure : function(response) {
					HT.unMask();
					var result = Ext.util.JSON.decode(response.responseText);
					Ext.Msg.alert(lang.operate.msgTip, obj.msg);
					this.store.loadPage(1);
				}
			});
		}
		isUserImage = false;
	}

});
