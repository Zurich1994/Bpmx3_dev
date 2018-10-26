
/**
 * 人员选择器
 * 
 * @author zxh
 * @date 2013-06-04
 */
Ext.define('mobile.selector.RoleSelector', {
	extend : 'Ext.Panel',
	name : 'roleSelector',
	requires : ['Ext.data.Store', 'Ext.List', 'Ext.field.Search',
			'Ext.Toolbar', 'Ext.Panel'],
	constructor : function(conf) {
		this.preCallback = conf.callback;
		this.isSingle = conf.isSingle?true:false;
		this.userIds = conf.userIds;
		this.userNames = conf.userNames;
		this.userDialog = Ext.Viewport.add({
			xtype : 'panel',
			width : '99%',
			height : '99%',
			centered : true,
			modal : true,
			hideOnMaskTap : false,
			layout : 'fit',
			items : [this.getListConfiguration()]
		});
		this.userDialog.show();
	},
	getListConfiguration : function() {
		Ext.define('userItem', {
			extend : 'Ext.data.Model',
			config : {
				fields : [{
					name : 'userId',
					type : 'string'
				}, {
					name : 'fullname',
					type : 'string'
				}, {
					name : 'orgName',
					type : 'string'
				}, {
					name : 'posname',
					type : 'string'
				}]
			}
		});

		this.store = Ext.create('HT.Store', {
			model : 'userItem',
			url : __ctx + '/platform/mobile/mobileUser/list.ht'
		});

		this.toolbar = Ext.create('Ext.Toolbar', {
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
				flex : .5,
				scope : this,
				handler : this.onSearch
			} ]
		});
		this.bottombar = Ext.create('Ext.Toolbar', {
			docked : 'bottom',
			layout : 'vbox',
			items : [{
				xtype : 'hiddenfield',
				name : 'userIds',
				value : this.userIds,
				readOnly : true
			}, {
				xtype : 'textfield',
				name : 'userNames',
				readOnly : true,
				value : this.userNames,
				height:'50px',
				width:'100%'
			}, {
				xtype : 'toolbar',
				layout : {
					type : 'hbox',
					pack : 'center'
			},items : [ {
				xtype : 'button',
				text:lang.operate.button.cancel,
				scope : this,
				handler : this.onClose
			},{
				xtype : 'button',
				text:lang.operate.button.clean,
				scope : this,
				handler : this.onClean
			},{
				xtype : 'button',
				text:lang.operate.button.ok,
				id:'okButton',
				width:80,
				badgeText: this.getBadgeTextCount(this.userIds),
				scope : this,
				handler : this.onOK
			} ]}]
		});

		return {
			xtype : 'list',
			// mode:'multi',
			itemTpl : Ext
					.create(
							'Ext.XTemplate',
							'<div class="x-pagelist-contact">',
							'<div class="top">',
							'<div class="name">{fullname}<span>{orgName}<br/>{posname}</span></div>',
							'</div>', '</div>'),
			disclosure : true,
			// scope: scope,
			onItemDisclosure : {
				scope : this,
				handler : this.onItemDisclosure
			},
			items : [this.toolbar, this.bottombar],
			plugins : [{
				xclass : 'Ext.plugin.ListPaging',
				loadMoreText : lang.tip.loadMoreText,
				noMoreRecordsText : lang.tip.noMoreRecordsText,
				autoPaging : true	// 设置为TRUE将自动触发
			}],
			emptyText : '<p class="no-searches">' + lang.tip.noRecords + '</p>',
			store : this.store
		};
	},
	getBadgeTextCount:function(userIds){
		if (Ext.isEmpty(userIds)) 
			return 0;
		return userIds.split(',').length;
	},
	/**
	 * 选择记录
	 * 
	 * @param {}
	 *            record
	 * @param {}
	 *            item
	 * @param {}
	 *            index
	 * @param {}
	 *            e
	 */
	onItemDisclosure : function(record, item, index, e) {
		e.stopEvent();
		var userId = record.get('userId'),
		fullname = record.get('fullname'),
		bottombar = this.bottombar,
		userIds = bottombar.getCmpByName('userIds'), 
		userNames = bottombar.getCmpByName('userNames'),
		userIdsValue = userIds.getValue(), 
		userNamesValue = userNames.getValue(),
		okButton = Ext.getCmp('okButton');

		if (this.isSingle) {// 单选
			userIds.setValue(userId);
			userNames.setValue(fullname);
		} else {// 多选
			var o = this.parseUser(userId, fullname, userIdsValue,
					userNamesValue);

			userIds.setValue(o.userIds);
			userNames.setValue(o.userNames);
			if(o.count==1){
				var badgeCount = okButton.getBadgeText();
				okButton.setBadgeText(++badgeCount);
			}
		}
	},
	/**
	 * 解析用户
	 * 
	 * @param {}
	 *            ids
	 * @param {}
	 *            names
	 * @param {}
	 *            userIdVal
	 * @param {}
	 *            userNameVal
	 * @return {}
	 */
	parseUser : function(ids, names, userIdVal, userNameVal) {
		var count = 0;
		if (Ext.isEmpty(ids)) {
			userNameVal = '';
			userIdVal = '';
		} else {
			if (Ext.isEmpty(userIdVal)) {
				userNameVal = names;
				userIdVal = ids;
				count =1;
			} else {
				var userIdAry = userIdVal.split(',');
				if (!Ext.Array.contains(userIdAry, ids)) {
					userNameVal = userNameVal + "," + names;
					userIdVal = userIdVal + "," + ids;
					count =1;
				}
			}
		}
		return {
			userIds : userIdVal,
			userNames : userNameVal,
			count:count
		};
	},
	/**
	 * 关闭
	 */
	onClose : function() {
		this.userDialog.removeAll(true);
		this.userDialog.hide();
	},
	/**
	 * 确定
	 */
	onOK : function() {
		var userIds = this.bottombar.getCmpByName('userIds').getValue(), userNames = this.bottombar
				.getCmpByName('userNames').getValue();
		this.onClose();
		this.preCallback.call(this, userIds, userNames);
	},
	/**
	 * 清空
	 */
	onClean : function() {
		this.onClose();
		this.preCallback.call(this, '', '');
	},
	/**
	 * 清除查询记录
	 */
	onSearchClearIconTap : function() {
		// call the clearFilter method on the store instance
		this.getStore().clearFilter();
	},
	onSearch : function() {
		var value = this.toolbar.getCmpByName('searchField').getValue();
		this.store.getProxy().setExtraParams({
			'Q_fullname_SL' : value
		});
		this.store.loadPage(1);
	},
	/**
	 * 查询
	 * 
	 * @param {}
	 *            field
	 */
	onSearchKeyUp : function(field) {
		var value = field.getValue();
		this.store.getProxy().setExtraParams({
			'Q_fullname_S' : value
		});
		this.store.loadPage(1);
	}
});