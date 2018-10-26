/**
 * 当前应用的一些信息
 * 
 * @author zxh
 * @date 2013-06-04
 */

/**
 * 当前用户
 * 
 * @type
 */
var curUserInfo = null;
/**
 * 保存用户信息
 * 
 * @param {}
 *            user
 */
var UserInfo = function(user) {
	this.userId = user.userId;
	this.account = user.account;
	this.fullname = user.fullname;
	this.depId = user.depId;
	this.depName = user.depName;
	this.rights = user.rights;
	this.portalConfig = user.items;
	this.topModules = user.topModules;
};

/**
 * 判断登陆超时 和系统权限
 */
Ext.Ajax.on('requestcomplete', function(conn, resp, options) {
	HT.loadMask();
	if (resp && resp.getResponseHeader) {
		if (resp.getResponseHeader('__timeout')) {
			Ext.Msg.alert(lang.operate.msgTip, lang.response.timeout,
					function() {
						 window.location.reload();
					});

		} else if (resp.getResponseHeader('__403_error')) {
			Ext.Msg.alert(lang.response.accessRight, lang.response.notRight,
					options.url);
		}
	}
	HT.unMask();
}, 'requestexception', function(conn, resp, options) {
	Ext.Msg.alert(lang.operate.msgTip, lang.response.error);
}

);

//if (Ext.device.Connection.isOnline()) {
//	// Ext.Msg.alert('You are currently connected via ' +
//	// Ext.device.Connection.getType());
//} else {
//	Ext.Msg.alert('You are not currently connected', lang.response.error);
//}
