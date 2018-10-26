/**
 * @author sfit0303
 */
(function(){
    window.contact = {
		// 取消常用联系人时弹出菜单
		cancel:function (){
			var contact_footer = angular.element($$("contact_footer"));
			contact_footer.addClass("uhide");
			uexWindow.evaluatePopoverScript("", "contactCommon", "cancelDel()");
		},
		// 删除常用联系人
		del:function (){
			uexWindow.evaluatePopoverScript("", "contactCommon", "contactDel()");
//			var contact_footer = angular.element($$("contact_footer"));
//			contact_footer.addClass("uhide");
		},
		// 常用管理人管理
		commonMge:function(){
			uexWindow.evaluatePopoverScript("", "contactCommon", "longSelector()");
		}
    }
})();