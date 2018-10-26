
/**
 * 手机登录跳转
 * 
 * @author zxh
 * @date 2013-06-04
 */

Ext.define('mobile.View', {
	extend : 'Ext.NavigationView',

	constructor : function(conf) {

		conf = conf || {};
		var me = this;
		Ext.apply(conf, {
			defaultBackButtonText : lang.defaultBackButtonText,
			items : [Ext.create('mobile.Main')],
			navigationBar : {
				ui : 'dark',
				docked : 'top',
				items : [{
					xtype : 'button',
					id : 'logout',
					text : lang.logout.text,
					align : 'right',
					handler : function() {
						HTUtil.logout();
					}
				}]
			}
		});

		this.callParent([conf]);
	}

});
