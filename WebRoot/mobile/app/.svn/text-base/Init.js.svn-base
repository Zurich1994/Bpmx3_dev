/**
 * 手机登录初始化
 * 
 * @author zxh
 * @date 2013-06-04
 */
// <debug>
Ext.Loader.setConfig({
	enabled : true,
	paths : {
		'mobile' : 'mobile/app'
	}
});
// </debug>
Ext.application({
	name : 'mobileLogin',
	id : 'login',
	//这个目前只适用于iOS设备.Android设备的在package.json配置
 	startupImage: {
        '320x460': 'resources/startup/Default.png', // Non-retina iPhone, iPod touch, and all Android devices
        '640x920': 'resources/startup/640x920.png', // Retina iPhone and iPod touch
        '640x1096': 'resources/startup/640x1096.png', // iPhone 5 and iPod touch (fifth generation)
        '768x1004': 'resources/startup/768x1004.png', //  Non-retina iPad (first and second generation) in portrait orientation
        '748x1024': 'resources/startup/748x1024.png', //  Non-retina iPad (first and second generation) in landscape orientation
        '1536x2008': 'resources/startup/1536x2008.png', // : Retina iPad (third generation) in portrait orientation
        '1496x2048': 'resources/startup/1496x2048.png' // : Retina iPad (third generation) in landscape orientation
    },
    isIconPrecomposed: false,
    icon: {
        57: 'resources/icons/icon.png',
        72: 'resources/icons/icon@72.png',
        114: 'resources/icons/icon@2x.png',
        144: 'resources/icons/icon@144.png'
    },
	launch : function() {
		mobileNavi = Ext.Viewport;
		mobileLogin = Ext.create('mobile.Login', {
			fullscreen : true
		});
	}
});