
/**
 * 手机登录
 * 
 * @author zxh
 * @date 2013-06-04
 */
Ext.define('mobile.Login', {
	extend : 'Ext.form.Panel',
	name : 'login',
	scrollable : 'vertical',
	fullscreen : true,
	constructor : function(conf) {
		conf = conf || {};
		var userName = Ext.isEmpty(localStorage.getItem("userName"))
				? ''
				: localStorage.getItem("userName");
		Ext.apply(conf, {
			id : 'loginFormPanel',
			items : [{
				docked : 'top',
				title : lang.system.title,
				xtype : 'titlebar',
				items : [{
					align : 'right',
					xtype : 'button',
					name : 'submit',
					text : '设置',
					cls : 'buttonCls',
					hidden:Ext.isEmpty(isSetCtx)?false:isSetCtx,
					handler : this.setConfig
				}]
			}, {
				xtype : 'fieldset',
				defaults : {
					required : true,
					labelAlign : 'left',
					labelWidth : '40%'
				},
				items : [{
					xtype : 'textfield',
					name : 'username',
					label : lang.login.username,
					placeHolder:'请输入账号',
					value : userName
				}, {
					xtype : 'passwordfield',
					name : 'password',
					placeHolder:'请输入密码',
					label : lang.login.password
				}, {
					xtype : 'togglefield',
					name : 'remenberUser',
					label : '记住账号',
					value : Ext.isEmpty(userName) ? 0 : 1
				}
//				, {
//					xtype : 'selectfield',
//					hidden:true,
//					name : 'lang',
//					defaultPhonePickerConfig : defaultPhonePickerConfig,
//					usePicker : false,
//					options : [{
//						text : '中文',
//						value : 'zh_CN'
//					}, {
//						text : '繁體',
//						value : 'zh_TW'
//					}, {
//						text : 'English',
//						value : 'en_US'
//					}],
//					value : __lang,
//					listeners : {
//						scope : this,
//						change : this.onChange
//					}
//				}
				]
			}, {
				xtype : 'panel',
				layout : 'hbox',
				height : 40,
				items : [{
					xtype : 'button',
					text : lang.login.reset,
					ui : 'action',
					flex : 1,
					scope : this,
					handler : this.resetFrom
				}, {
					xtype : 'button',
					text : lang.login.login,
					ui : 'action',
					flex : 1,
					style : 'margin-left:4px;',
					scope : this,
					handler : this.formSubmit
				}]
			}]
		});
		this.callParent([conf]);
	},
	resetFrom : function() {
		Ext.getCmp('loginFormPanel').reset();
	},
	formSubmit : function() {
		var loginFormPanel = Ext.getCmp('loginFormPanel');
//				if (!loginFormPanel.validate(loginFormPanel.items))
//				return;
		
		if (loginFormPanel.getCmpByName('remenberUser').getValue() == 1) 
			localStorage.setItem("userName", loginFormPanel
					.getCmpByName('username').getValue());

		loginFormPanel.submit({
			url : __ctx + '/mobileLogin.ht',
			method : 'POST',
			waitMsg: '登陆中...',
			success : function(form, action, response) {
				var obj = Ext.util.JSON.decode(response);
				if (obj.success) {
					curUserInfo = new UserInfo(obj.user);
					mobileNavi.removeAt(0);
					mobileLogin.destroy();
					mobileView = Ext.create('mobile.View');
					mobileNavi.add(mobileView);
				} else {
					HT.toast(obj.msg);
				}
			},
			failure : function(form, action, response) {
				var obj = Ext.util.JSON.decode(response);
				HT.toast({message:obj.msg});
			}
		});
	},
	onChange : function(field, newValue, oldValue) {
		if (newValue == oldValue)
			return;
		HT.loadMask();
		Ext.Ajax.request({
			url : __ctx + '/platform/mobile/lang/changLang.ht',
			params : {
				lang : newValue
			},
			scope : this,
			success : function(response) {
				window.location.href = __ctx + '/mobileLogin.jsp?jsessionId='
						+ __jsessionId;
				HT.unMask();
			},
			failure : function(response) {
				HT.unMask();
				var result = Ext.util.JSON.decode(response.responseText);
				Ext.Msg.alert(lang.operate.msgTip, result.msg);
			}
		});
	},
	setConfig : function() {
		var setDomain = Ext.create('mobile.SetDomain', {
			domVal : __ctx
		});
		mobileNavi.removeAt(0);
		this.destroy();
		mobileNavi.add(setDomain);
	}

});
