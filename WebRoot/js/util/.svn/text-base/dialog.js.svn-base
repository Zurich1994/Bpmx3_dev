/**
 * 窗口文件
 */
if (typeof Dialog == 'undefined') {
	Dialog = {};
}

/**
 * 弹出模态窗口
 * 
 * @param {}
 *            conf
 */
Dialog.showDialog = function(conf) {
	if (!conf)
		conf = {};
	var dialogWidth = 800, dialogHeight = 400;
	conf = $.extend({}, {
				dialogWidth : dialogWidth,
				dialogHeight : dialogHeight,
				help : 0,
				status : 0,
				scroll : 0,
				center : 1
			}, conf);
	// 作用域
	var scope = conf.scope ? conf.scope : this, 
		winArgs = "dialogWidth="
			+ conf.dialogWidth + "px;dialogHeight=" + conf.dialogHeight
			+ "px;help=" + conf.help + ";status=" + conf.status + ";scroll="
			+ conf.scroll + ";center=" + conf.center,
		url = conf.url.getNewUrl();

	/*var rtn = window.showModalDialog(url, window, winArgs);
	if (conf.callback) {
		conf.callback.call(scope, rtn);
	}*/
	/*KILLDIALOG*/
	DialogUtil.open({
		height:conf.dialogHeight,
		width: conf.dialogWidth,
		title : '弹出模态窗口',
		url: url, 
		isResize: true,
		//自定义参数
		window: window,
		sucCall:function(rtn){
			conf.callback.call(scope, rtn);
		}
	});
};

/**
 * 模态窗口高度调整. 根据操作系统及ie不同版本,重新设置窗口高度,避免滚动条出现.
 */
Dialog.resetDialogHeight = function resetDialogHeight(height) {
	var ua = navigator.userAgent;
	if (ua.lastIndexOf("MSIE 6.0") != -1) {
		if (ua.lastIndexOf("Windows NT 5.1") != -1) {
			// alert("xp.ie6.0");
			return (height + 102);
		} else if (ua.lastIndexOf("Windows NT 5.0") != -1) {
			// alert("w2k.ie6.0");
			return (height + 49);
		}
	}
};