(function($) {
	$.ligerMsg = {
		_alertButFrag : '<input callback="#callback#" type="button"  value=" #butMsg# "></input>',
		_alertBoxFrag : '<div id="alertMsgBox" class="msg"><div class="msgContent"><div class="#type#">'+
				'<div class="msgInner"><h1>#title#</h1><div class="message">#message#</div></div>'+
				'<div class="toolBar"><ul>#butFragment#</ul></div></div></div></div>',
		_boxId : "#alertMsgBox",
		_closeTimer : null,

		_types : {
			error : "error",
			info : "info",
			warn : "warn",
			correct : "correct",
			confirm : "confirm"
		},

		_title : {
			error : "错误",
			info : "温馨提示",
			warn : "警告",
			correct : "成功",
			confirm : "请确定"
		},
		_butMsg : {
			ok : "确定",
			yes : "是",
			no : "否",
			cancel : "取消"
		},

		_getTitle : function(key) {
			return this._title[key];
		},

		//打开对话框
		_open : function(type, msg, bottom, buttons) {
			var topWindow=self.top;
			
			//不为info和correct的两类型对话框不添加蒙板。
			if (!(this._types.info == type || this._types.correct == type)) {
				$("<div class='l-window-mask' style='display: block;'></div>").appendTo(topWindow.document.body);
			}
			var butsHtml = "";
			if (buttons) {
				for ( var i = 0; i < buttons.length; i++) {
					var sRel = buttons[i].call ? "callback" : "";
					butsHtml += this._alertButFrag.replace("#butMsg#",
							buttons[i].name).replace("#callback#", sRel) + "&nbsp;";
				}
			}
			var boxHtml = this._alertBoxFrag.replace("#type#", type)
					.replace("#title#", this._getTitle(type))
					.replace("#message#", msg)
					.replace("#butFragment#", butsHtml);
			
			if($("div.msg",topWindow.document.body).length>0){
				$("div.msg",topWindow.document.body).remove();
			}
			if($("div.msg",topWindow.document).length>0){
				$("div.msg",topWindow.document).remove();
			}
			var _self=this;
			//确保删除之前的提示框.
			setTimeout(function(){
				$(boxHtml).appendTo(topWindow.document.body);
				var objMsgbox=$(_self._boxId,$(topWindow.document));
				//在顶部冒出来
				if (bottom != true) {
					var top=-objMsgbox.height() +"px" ;
					objMsgbox.css({top : top }).animate({top : "0px"}, 500);
				}
				//从右下角冒出来
				else {
					var height = topWindow.document.documentElement.clientHeight;
					objMsgbox.css({top : height + "px",left : "auto",right : "10px"})
					.animate({top : height - objMsgbox.height()}, 500);
				}
				
				if (_self._closeTimer) {
					clearTimeout(_self._closeTimer);
					this._closeTimer = null;
				}
				//如果类型为info,correct，消息框定时关闭
				if (_self._types.info == type || _self._types.correct == type) {
					_self._closeTimer = setTimeout(function() {
						$.ligerMsg.close();
					}, 3500);
				}
			    //添加按钮事件处理。
				var jCallButs = objMsgbox.find(	"input[callback='callback']");
				for ( var i = 0; i < buttons.length; i++) {
					if (buttons[i].call){
						jCallButs.eq(i).click(buttons[i].call);
					}
				}
			},5);
			
		},
		_alert : function(type, msg, bottom,callback) {
			var op = {
				okName : this._butMsg.ok,
				okCall : callback
			};
			var buttons = [ {
				name : op.okName,
				call : op.okCall
			} ];
			this._open(type, msg, bottom, buttons);
		},
		close : function() {
			var topWindow=self.top;
			var objMsgbox=$("div.msg",$(topWindow.document));
		
			if (objMsgbox.length > 0) {
				objMsgbox.each(function(){
					var obj=$(this);
					//在顶部
					if (obj.position().top <= 0) {
						obj.animate({top : -obj.height()}, 500, function() {
							$(this).remove();
						});
					}
					else {
						var height = topWindow.document.documentElement.clientHeight;
						obj.animate({top : height}, 500, function() {
							$(this).remove();
						});
					}
				});
			}
			//删除蒙板
			$(topWindow.document).find(".l-window-mask").remove();
		},
		error : function(msg, bottom,callback) {
			this._alert(this._types.error, msg, bottom,callback);
		},
		info : function(msg, bottom,callback) {
			this._alert(this._types.info, msg, bottom,callback);
		},
		warn : function(msg, bottom,callback) {
			this._alert(this._types.warn, msg, bottom,callback);
		},
		correct : function(msg, bottom,callback) {
			this._alert(this._types.correct, msg, bottom,callback);
		},
		confirm : function(msg, callback, bottom) {
			var op = {
				okName : this._butMsg.ok,
				okCall : null,
				cancelName : this._butMsg.cancel,
				cancelCall : null
			};
			var buttons = [ {
				name : op.okName,
				call : callback
			}, {
				name : op.cancelName
			} ];
			this._open(this._types.confirm, msg, bottom, buttons);
		}
	};
})(jQuery);