
Ext.override(Ext.MessageBox, {  
	statics: {
        OK    : {text: '确定',     itemId: 'ok',  ui: 'action'}
	}
//,
//	
//	alert: function(title, message, fn, scope) {
//	    return this.show({
//	        title: title || null,
//	        message: message || null,
//	        buttons: htMsgBox.OK,
//	        promptConfig: false,
//	        fn: function() {
//	            if (fn) {
//	                fn.apply(scope, arguments);
//	            }
//	        },
//	        scope: scope
//	    });
//	}
});  