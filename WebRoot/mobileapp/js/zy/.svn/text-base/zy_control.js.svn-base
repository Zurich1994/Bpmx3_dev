var $support = {
	transform3d : ('WebKitCSSMatrix' in window),
	touch : ('ontouchstart' in window)
};

var $E = {
	start : $support.touch ? 'touchstart' : 'mousedown',
	move : $support.touch ? 'touchmove' : 'mousemove',
	end : $support.touch ? 'touchend' : 'mouseup',
	cancel : $support.touch ? 'touchcancel' : '',
	transEnd : 'webkitTransitionEnd'
};

function setJson(str) {
	var ret = (typeof (str) == 'object') ? JSON.stringify(str) : JSON
			.parse(str);
	return ret;
}
function setHtml(id, html) {
	if ("string" == typeof (id)) {
		var ele = $$(id);
		if (ele != null) {
			ele.innerHTML = html == null ? "" : html;
		}
	} else if (id != null) {
		id.innerHTML = html == null ? "" : html;
	}
}

function getValue(id) {
	var e = $$(id);
	if (e)
		return e.value;
}

function setValue(id, vl) {
	var e = $$(id);
	if (e)
		e.value = vl;
}

function isDefine(para) {
	if (typeof para == 'undefined' || para == "" || para == null
			|| para == undefined)
		return false;
	else
		return true;
}

function fucCheckLength(strTemp) { // 第一种计算字节
	var i, sum;
	sum = 0;
	for (i = 0; i < strTemp.length; i++) {
		if ((strTemp.charCodeAt(i) >= 0) && (strTemp.charCodeAt(i) <= 255))
			sum = sum + 1;
		else
			sum = sum + 2;
	}
	return sum;
}
function strLen(str) { // 第二种计算字节
	return str.replace(/[^\x00-\xff]/g, '__').length;
}
function loadBanner(e, url, json) {
	var img = new Image();
	img.src = url;
	img.onload = function() {
		var banner = $$(e);
		banner.innerHTML = '<img src="'
				+ this.src
				+ '" ontouchstart="zy_touch()" onclick="slideJump(\''
				+ json.type
				+ '\','
				+ json.nid
				+ ')" /><del ontouchstart="zy_touch()" onclick="hideBanner()"></del>';
		banner.style.height = this.height / (this.width / banner.offsetWidth)
				+ 'px';
		banner.style.opacity = 1;
	}
}
function getBanner(id, fun) {
	AJAX.get(serverurl + 'advertising.php?id=' + id, function(json) {
		var src = json.img;
		zy_imgcache('banner', src, src, function(e, url) {
			loadBanner(e, url, json);
		}, function(e) {
			loadBanner(e, src, json);
		});
		if (fun)
			fun(1);
	}, function() {
		if (fun)
			fun(0);
	}, -10);
}

function hideBanner() {
	if (checkActive(event.currentTarget))
		return;
	$$('banner').style.height = '0';
	$$('banner').style.opacity = 0;
}

var rim = (2.5 + 3.125), // 顶部和底部的高度
fontSize = 16, // 字体大小
loadStatus = 0, // 加载状态，默认为可加载状态
maxsize = 0; // 是否最大页
function loadScroll(t) {
	if (loadStatus)
		return;
	var scrollTop = document.body.scrollTop
			|| document.documentElement.scrollTop;
	var downHeight = parseInt(window.screen.availHeight,10) + parseInt(scrollTop,10), // 可见窗口高度+网页被卷去的高
	Height = parseInt(document.body.scrollHeight,10) - parseInt($$("more").offsetHeight * 3,10); // body总高度-加载更多的高度
	if (localStorage['device'] == 'ios')
		downHeight -= rim;
	if (downHeight > Height) {
		Load(1);
		return;
	}
	if (t == 'end') {
		window.setTimeout(function() {
			Scroll('end');
		}, 1000);
	}
}
function bodyTouch() {
	var s = window.getComputedStyle(document.body, null);
	fontSize = parseInt(s.fontSize,10);
	rim *= fontSize;
	document.body.addEventListener($E.move, function(event) {
		loadScroll('move');
	}, false);
	document.body.addEventListener($E.end, function(event) {
		loadScroll('end');
	}, false);
}

var isScroll = 0;
function Scroll() { // 是否禁止拖滚动条
	if (isScroll)
		event.preventDefault();
}

var active = 0;
function checkActive(t) {
	if (active)
		return 1;
	active = 1;
	if (arguments.length == 1) {
		t.className = 'active';
	}
	window.setTimeout(function() {
		t.className = '';
		active = 0;
	}, 1000);
	return 0;
}
function checkActiveNo(t) {
	if (active)
		return 1;
	active = 1;
	window.setTimeout(function() {
		active = 0;
	}, 300);
	return 0;
}

function base64_decode(data) {
	var b64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	var o1, o2, o3, h1, h2, h3, h4, bits, i = 0, ac = 0, dec = "", tmp_arr = [];

	if (!data) {
		return data;
	}

	data += '';

	do { // unpack four hexets into three octets using index points in b64
		h1 = b64.indexOf(data.charAt(i++));
		h2 = b64.indexOf(data.charAt(i++));
		h3 = b64.indexOf(data.charAt(i++));
		h4 = b64.indexOf(data.charAt(i++));

		bits = h1 << 18 | h2 << 12 | h3 << 6 | h4;

		o1 = bits >> 16 & 0xff;
		o2 = bits >> 8 & 0xff;
		o3 = bits & 0xff;

		if (h3 == 64) {
			tmp_arr[ac++] = String.fromCharCode(o1);
		} else if (h4 == 64) {
			tmp_arr[ac++] = String.fromCharCode(o1, o2);
		} else {
			tmp_arr[ac++] = String.fromCharCode(o1, o2, o3);
		}
	} while (i < data.length);

	dec = tmp_arr.join('');

	return dec;
}

function urlParse(url) {
	var params = {};
	var loc = String(url);
	var pieces = loc.substr(loc.indexOf('#') + 1).split('&');
	params.keys = [];
	for ( var i = 0; i < pieces.length; i += 1) {
		var keyVal = pieces[i].split('=');
		params[keyVal[0]] = decodeURIComponent(keyVal[1]);
		params.keys.push(keyVal[0]);
	}
	return params;
}

function uescript(wn, scr) {
	uexWindow.evaluateScript(wn, '0', scr);
}

function ueppscript(wn, pn, scr) {
	uexWindow.evaluatePopoverScript(wn, pn, scr);
}

function openwin(winName, url, anim) {
	uexWindow.open(winName, "0", url, anim, "", "", "4", "275");
}

function closewin(anim) {
	var a = '-1';
	if (anim)
		a = anim;
	uexWindow.close(a);
}

function setstorage(objName, objValue) { // 设置字符串类型的本地缓存
	var sto = window.localStorage;
	if (sto)
		sto.setItem(objName, objValue);
}
function getstorage(objName) { // 读取字符串类型的本地缓存
	var ret = '';
	var sto = window.localStorage;
	if (sto)
		ret = sto.getItem(objName);
	return ret;
}
function clearstorage(objName) { // 清除本地缓存，如没指定名称则为清空所有缓存
	var sto = window.localStorage;
	if (sto) {
		if (objName)
			sto.removeItem(objName);
		else
			sto.clear();
	}
}
function setStorJson(objName, json) { // 设置Json类型的本地缓存
	if (json)
		setstorage(objName, JSON.stringify(json));
}
function getStorJson(objName) { // 读取Json类型的本地缓存
	var ret = {};
	var str = getstorage(objName);
	if (str)
		ret = JSON.parse(str);
	return ret;
}

window.AJAX = {
	callBack : {},
	index : 1,
	dataType : 'json',
	get : function(url, succCall, errCall, opId, dataType, timeout) {
		this.index++;
		var id = (opId || this.index);
		this.callBack[id] = [ succCall, errCall ];
		this.dataType = (dataType || this.dataType);
		uexXmlHttpMgr.open(id, 'get', url, (timeout || 8000));
		this._send(id);
	},
	post : function(url, data, succCall, errCall, opId, dataType, timeout) {
		this.index++;
		var id = (opId || this.index);
		this.callBack[id] = [ succCall, errCall ];
		this.dataType = (dataType || this.dataType);
		uexXmlHttpMgr.open(id, 'post', url, (timeout || 8000));
		var fileData = null;
		if (data.length == 2) {
			fileData = data[1]; // 二进制数据
			data = data[0]; // 文字数据
		}
		if (data) {
			for ( var k in data) {
				uexXmlHttpMgr.setPostData(id, 0, k, data[k]);
			}
		}
		if (fileData) {
			for ( var k in fileData) {
				uexXmlHttpMgr.setPostData(id, 1, k, fileData[k]);
			}
		}
		this._send(id);
	},
	_send : function(id) {
		uexXmlHttpMgr.onData = this.onData;
		uexXmlHttpMgr.send(id);
	},
	onData : function(inOpCode, inStatus, inResult) {
		var that = AJAX, callBack = that.callBack[inOpCode] || [];
		if (inStatus == -1) {
			callBack[1] && callBack[1]();
			delete that.callBack[inOpCode];
			uexXmlHttpMgr.close(inOpCode);
		} else if (inStatus == 1) {
			if (that.dataType == 'json')
				inResult = eval("(" + inResult + ")");
			// inResult= JSON.parse(inResult);
			// inResult= eval("("+inResult+")");
			callBack[0] && callBack[0](inResult);
			delete that.callBack[inOpCode];
			if (that.dataType != 'json')
				window.AJAX.dataType = 'json';
			uexXmlHttpMgr.close(inOpCode);
		}
	}
};

var em_focus = 1;
function zy_Switch(t, i) {
	var Switch = $$('switch'), Em = Switch.getElementsByTagName('em');
	if (typeof (i) != 'undefined') {
		em_focus = i;
	}
	Switch.querySelector('.focus').className = '';
	if (em_focus == Em.length) {
		em_focus = 0;
	}
	Em[em_focus].className = 'focus';
	t.moveToPoint(em_focus);
	em_focus++;
}
function zy_slide() {
	var switchTime;
	$$('slider').slide = new zySlide('slider', 'H', function() {
		window.clearInterval(switchTime);
		zy_Switch(this, this.currentPoint);
		var t = this;
		switchTime = window.setInterval(function() {
			zy_Switch(t);
		}, 5000);
	}, false, function(e) {
	});
	switchTime = window.setInterval(function() {
		zy_Switch($$('slider').slide);
	}, 5000);
}
// 调用外部浏览器
function loadLink(url) {
	var appInfo = '';
	var filter = '';
	var dataInfo = url;
	// var dataInfo = url.toLowerCase();//全部小写
	if (localStorage['device'] == 'android') {
		appInfo = 'android.intent.action.VIEW';
		filter = 'text/html';
	}
	uexWidget.loadApp(appInfo, filter, dataInfo);
}
function zy_selectmenu(sl) {
	if (sl) {
		var sp = sl.parentElement; // <span>
		if (sp) {
			var ch = sp.getElementsByTagName("div")[0];
			var t = sl.options[sl.selectedIndex].text;
			if (ch) {
				ch.innerHTML = t;
			}
		}
	}
}

function zy_for(e, cb) {
	var ch;
	if (e.currentTarget)
		ch = e.currentTarget.previousElementSibling;
	else
		ch = e.previousElementSibling;
	if (ch.nodeName == "INPUT") {
		if (ch.type == "checkbox") {
			ch.checked = !ch.checked;
			angular.element(ch).triggerHandler("click");
		}
		if (ch.type == "radio" && !ch.checked) {
			ch.checked = "checked";
			angular.element(ch).triggerHandler("click");
		}
	}
	if (cb)
		cb(e, ch.checked);
}

function zy_fold(e, col) {
	var a = e.currentTarget.nextElementSibling;
	if (a.nodeName == "DIV") {
		if (col)
			a.className = a.className.replace("col-c", "");
		else
			a.className += ' col-c';
	}
}

function zy_touch(c, f, lf) {
	var t = event.currentTarget;
	if (!t.zTouch) {
		t.zTouch = new zyClick(t, f, c, lf);
		t.zTouch._touchStart(event);
	}
}

function zy_Bounce() {
	var t = event.currentTarget;
	if (!t.zTouch) {
		t.zTouch = new zyBounce(t);
		t.zTouch._touchStart(event);
	}
}

function zy_parse() {
	var params = {};
	var loc = String(document.location);
	if (loc.indexOf("?") > 0)
		loc = loc.substr(loc.indexOf('?') + 1);
	else
		loc = uexWindow.getUrlQuery();
	var pieces = loc.split('&');
	params.keys = [];
	for ( var i = 0; i < pieces.length; i += 1) {
		var keyVal = pieces[i].split('=');
		params[keyVal[0]] = decodeURIComponent(keyVal[1]);
		params.keys.push(keyVal[0]);
	}
	return params;
}

function $$(id) {
	return document.getElementById(id);
}

function zy_con(id, url, x, y) {
	var s = window.getComputedStyle($$(id), null);
	uexWindow.openPopover(id, "0", url, "", parseInt(x,10), parseInt(y,10), parseInt(s.width,10),
			parseInt(s.height,10), parseInt(s.fontSize,10), "4");
}
function zy_resize(id, x, y) {
	var s = window.getComputedStyle($$(id), null);
	uexWindow.setPopoverFrame(id, parseInt(x,10), parseInt(y,10), parseInt(s.width,10), parseInt(s.height,10));
}

function zy_init() {
	
//	if(window.navigator.appVersion.indexOf("Android 4.2.1")!=-1){
//		angular.element(document.getElementsByTagName("body")).css("font-size","48px");
//	}
	if (window.navigator.platform == "Win32")
		document.body.style.fontSize = window.localStorage["defaultfontsize"];
	if (window.navigator.platform == "iPad"){
		document.body.style.fontSize = "24px";
//		document.body.style.fontSize = "12px";
		HT.$($$("footer"))?HT.$($$("footer")).attr({"style":"height:4em !important;"}):"";
	}
}
function zy_cc(t) {
	if (!t.cancelClick) {
		t.cancelClick = true;
		t.addEventListener("click", function() {
			event.stopPropagation();
		}, true);
	}
}
function removeNode(id) {
	var e = $$(id);
	if (e)
		e.parentElement.removeChild(e);
}

function Trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "")
}
function LTrim(str) {
	return str.replace(/(^\s*)/g, "")
}
function RTrim(str) {
	return str.replace(/(\s*$)/g, "")
}
function AllTrim(str) {
	return str.replace(/\s*/g, '')
}

function substr(str, len) {
	if (!str || !len) {
		return '';
	}
	// 预期计数：中文2字节，英文1字节
	var a = 0;
	// 循环计数
	var i = 0;
	// 临时字串
	var temp = '';
	for (i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) > 255) {
			// 按照预期计数增加2
			a += 2;
		} else {
			a++;
		}
		// 如果增加计数后长度大于限定长度，就直接返回临时字符串
		if (a > len) {
			return temp;
		}
		// 将当前内容加到临时字符串
		temp += str.charAt(i);
	}
	// 如果全部是单字节字符，就直接返回源字符串
	return str;
}

function Now() {
	var myDate = new Date()
	return myDate.getFullYear() + '-' + (myDate.getMonth() + 1) + '-'
			+ (myDate.getDate() < 10 ? '0' : '') + myDate.getDate() + ' '
			+ (myDate.getHours() < 10 ? '0' : '') + myDate.getHours() + ':'
			+ (myDate.getMinutes() < 10 ? '0' : '') + myDate.getMinutes() + ':'
			+ (myDate.getSeconds() < 10 ? '0' : '') + myDate.getSeconds();
}

/**
 * 触发该元素下第一个子input的事件(单选、复选、toggle)
 * 
 * @param e
 * @param cb
 */
function zy_for_input(e, cb) {
	var ch = e.currentTarget ? (ch = angular.element(e.currentTarget).find(
			"input")[0]) : (e.previousElementSibling);
	if (ch && ch.nodeName == "INPUT") {
		var elm = angular.element(ch),
				controltype = elm.attr("controltype"),
				scope = elm.scope();
		if (ch.type == "checkbox") {
			ch.checked = !ch.checked;
			if(controltype && controltype== 'toggle'){//toggle
				var	ngCheckedValue = elm.attr("ng-checked-value"),
					ngNocheckedValue = elm.attr("ng-nochecked-value"),
					ngChecked =  elm.attr("ng-checked");
					noUpdateValue = elm.attr("no-update-value");
				if(noUpdateValue)
					return;
				if (ch.checked)
					val = ngCheckedValue?ngCheckedValue:1;
				else
					val = ngNocheckedValue?ngNocheckedValue:0;
				try{
					eval('(scope.' + ngChecked + '='+val +')');
				}catch(e){}
				scope.$digest();
			}else{
				var isParent = elm.attr("is-parent"),
					val = elm.attr("value"),
					ngModel = elm.attr("ng-model"),
					scopeVal = '',
					v = [];
				if(isParent){
					for(var i=0;i<isParent;i++){
						scope = scope.$parent;
					}
				}
				try{
					eval('(scopeVal = scope.' + ngModel + ')');
				}catch(e){}
		
				if (scopeVal)
					v = scopeVal.split(",");
				if (ch.checked)
					v.push(val);
				else
					v.remove(val);
				var s = '(scope.' + ngModel + '="' + v.join(',') + '")';
				try{
					eval(s);
					scope.valid({element:elm});
				}catch(e){}
				scope.$digest();
			}
		} else if(ch.type == "radio"){
			var isParent = elm.attr("is-parent"),
				val = elm.attr("value"),
				ngModel = elm.attr("ng-model");
			if(isParent){
				for(var i=0;i<isParent;i++){
					scope = scope.$parent;
				}
			}
			if (!ch.checked) {
				ch.checked = "checked";
			}else{
				val ='';
			}

			var s = '(scope.' + ngModel + '="' + val + '")';
			try{
				eval(s);
				scope.valid({element:elm});
			}catch(e){}
			scope.$digest();
		}
	}
	if (cb)
		cb(e, ch.checked);
}
