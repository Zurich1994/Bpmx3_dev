
/**
 * 手机用户信息
 * 
 * @author zxh
 * @date 2013-06-04
 */
Ext.define('mobile.UserInfo', {
	extend : 'Ext.Toolbar',

	constructor : function(conf) {
		conf = conf || {};
		Ext.apply(conf, {
			docked : 'bottom',
			items : [
					// {
					// align : 'left',
					// xtype : 'button',
					// hidden : false,
					// text : lang.logout.text,
					// handler : function() {
					// HTUtil.logout();
					// }
					// },
					{
						xtype : 'spacer'
					}, {
						xtype : 'label',
						html : curUserInfo.fullname
					}, {
						xtype : 'spacer'
					}]
		});
		this.callParent([conf]);
	}
});