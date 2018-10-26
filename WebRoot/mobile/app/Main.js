
/**
 * 手机功能菜单
 * 
 * @author zxh
 * @date 2013-06-04
 */

Ext.define('mobile.Main', {
	extend : 'Ext.Panel',

	name : 'main',

	constructor : function(conf) {
		conf = conf || {};

		var selfItem = [];

		var rowPanel = null;

		var menuSize = menus.length;
		if (menus.length != 0 && menus.length % 4 != 0) {
			while (menuSize % 4 != 0) {
				menuSize++;
			}
		}

		for (var idx = 0; idx < menuSize; idx++) {

			if (idx == 0 || idx % 4 == 0) {
				rowPanel = Ext.create('Ext.Panel', {
					layout : {
						type : 'hbox',
						align : 'middle'
					}
				});
			}

			var itemPanel = Ext.create('Ext.Panel', {
				layout : {
					type : 'vbox',
					align : 'middle'
				},
				style : {
					'padding-top' : '15px',
					'padding-bottom' : '15px'
				},
				flex : 1,
				height : 100
			});

			if (idx < menus.length) {
				var item = menus[idx];

				itemPanel.add({
					xtype : 'image',
					name : item.view,
					width : 48,
					height : 48,
					cls : item.cls,
					notice:item.notice,
					listeners : {
						tap : function() {
							mobileView.push(Ext.create(this.config.name, {
								title : item.title,
								mask:HT.loadMask()
							}));
					
						},
						initialize:function(obj,b){
							var me = this,notice = me.config.notice;
							
							if(Ext.isEmpty(notice))
								return ;
							Ext.Ajax.request({
							    url: __ctx+notice,
							    socpe:this,
							    success: function(response){
							       var result = Ext.util.JSON.decode(response.responseText);
								    me.setBadgeText(result.totalCounts);
							    }
							});
						}
					}
				});

				itemPanel.add({
					xtype : 'label',
					style : {
						'text-align' : 'center',
						'font-size' : '9pt'
					},
					html : item.title
				});
			}

			rowPanel.add(itemPanel);

			if (idx == 0 || idx % 4 == 0) {
				selfItem.push(rowPanel);
			}
		}

		selfItem.push(Ext.create('mobile.UserInfo'));

		Ext.apply(conf, {
			title : lang.system.menu,
			items : selfItem,
			fullscreen : true,
			scrollable : true
		});

		this.callParent([conf]);
	}

});