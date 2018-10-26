var Namespace = new Object();

Namespace.register = function(path) {
	var arr = path.split(".");
	var ns = "";
	for ( var i = 0; i < arr.length; i++) {
		if (i > 0)
			ns += ".";
		ns += arr[i];
		eval("if(typeof(" + ns + ") == 'undefined') " + ns + " = new Object();");
	}
};

jQuery.extend({
			/**
			 * 根据名称全选或反选复选框。
			 * 
			 * @param name
			 * @param checked
			 */
			checkAll : function(name, checked) {

				$("input[name='" + name + "']").attr("checked", checked);
			},
			/**
			 * 根据复选框的名称获取选中值，使用逗号分隔。
			 * 
			 * @param name
			 * @returns {String}
			 */
			getChkValue : function(name) {
				var str = "";
				$('input[type="checkbox"][name=' + name + ']').each(function() {
					if ($(this).attr('checked')) {
						str += $(this).val() + ",";
					}
				});
				if (str != "")
					str = str.substring(0, str.length - 1);
				return str;
			},
			/**
			 * 根据名称获取下拉框的列表的值，使用逗号分隔。
			 * 
			 * @param name
			 * @returns {String}
			 */
			getSelectValue : function(name) {
				var str = "";
				$('select[name=' + name + '] option').each(function() {
					str += $(this).val() + ",";
				});
				if (str != "")
					str = str.substring(0, str.length - 1);
				return str;
			},
			copyToClipboard : function(txt) {
				if (window.clipboardData) {
					window.clipboardData.clearData();
					window.clipboardData.setData("Text", txt);
					return true;
				} else if (navigator.userAgent.indexOf("Opera") != -1) {
					window.location = txt;
					return false;
				} else if (window.netscape) {
					try {
						netscape.security.PrivilegeManager
								.enablePrivilege("UniversalXPConnect");
					} catch (e) {
						alert($lang.tip.msg,$lang_js.util.copyToClipboard.netscape);
						return false;
					}
					var clip = Components.classes['@mozilla.org/widget/clipboard;1']
							.createInstance(Components.interfaces.nsIClipboard);
					if (!clip)
						return false;
					var trans = Components.classes['@mozilla.org/widget/transferable;1']
							.createInstance(Components.interfaces.nsITransferable);
					if (!trans)
						return false;
					trans.addDataFlavor('text/unicode');

					var str = Components.classes["@mozilla.org/supports-string;1"]
							.createInstance(Components.interfaces.nsISupportsString);
					var copytext = txt;
					str.data = copytext;
					trans.setTransferData("text/unicode", str,
							copytext.length * 2);
					var clipid = Components.interfaces.nsIClipboard;
					if (!clip)
						return false;
					clip.setData(trans, null, clipid.kGlobalClipboard);
					return true;
				} else {
					alert($lang.tip.msg,$lang_js.util.copyToClipboard.notCopy);
					return false;
				}
			},
			/**
			 * 拷贝指定文本框的值。
			 * 
			 * @param objId
			 */
			copy : function(objId) {
				var str = $("#" + objId).val();
				var rtn = jQuery.copyToClipboard(str);
				if (rtn) {
					alert($lang_js.util.copy.success);
				}
			},
			/**
			 * 判断是否是IE浏览器
			 * 
			 * @returns {Boolean}
			 */
			isIE : function() {
				 if (!!window.ActiveXObject || "ActiveXObject" in window){
					 return true;  
				 }  
				 else{
					 return false;
				 }  
				          
			},
			/**
			 * 判断是否是IE6浏览器
			 * 
			 * @returns {Boolean}
			 */
			isIE6 : function() {
				if(($.browser.msie && $.browser.version =='6.0') &&!$.support.style)
					return true;
				return false;		
			},
			/**
			 * 序列化xmldom节点为一个xml。 用法: var sb=new StringBuffer(); var
			 * str=jQuery.getChildXml(node,sb);
			 * 
			 * @param node
			 *            xmldom节点。
			 * @param sb
			 * @returns
			 */
			getChildXml : function(node, sb) {
				var nodes = node.childNodes;
				var len = nodes.length;
				for ( var i = 0; i < len; i++) {
					var childNode = nodes[i];
					if (childNode.nodeType != 1)
						continue;
					var childNodeName = childNode.nodeName;
					sb.append("<" + childNodeName + " ");
					var attrs = childNode.attributes;
					for ( var k = 0; k < attrs.length; k++) {
						var attr = attrs[k];
						sb.append(" " + attr.name + "=\"" + attr.value + "\" ");
					}
					sb.append(">");
					$.getChildXml(childNode, sb);
					sb.append("</" + childNodeName + ">");
				}

			},
			/**
			 * 根据xmlnode序列化xml
			 * 
			 * @param node
			 *            xmldom节点。
			 * @returns
			 */
			getChildXmlByNode : function(node) {
				var sb = new StringBuffer();
				jQuery.getChildXml(node, sb);
				return sb.toString();
			},
			/**
			 * 根据xml节点，返回该节点的xml属性。 返回值通过参数ary获取。 用法： var node; var ary=new
			 * Array(); $.getAttrXml(node,ary);
			 * 
			 * @param node
			 * @param ary
			 */
			getAttrXml : function(node, ary) {
				var nodes = node.childNodes;
				var len = nodes.length;
				for ( var i = 0; i < len; i++) {
					var childNode = nodes[i];
					if (childNode.nodeType != 1)
						continue;

					var attrs = childNode.attributes;
					var obj = new Object();
					for ( var k = 0; k < attrs.length; k++) {
						var attr = attrs[k];
						obj[attr.name] = attr.value;
					}
					ary.push(obj);
					$.getAttrXml(childNode, ary);
				}
			},

			/**
			 * <img src="img/logo.png" onload="$.fixPNG(this);"/>
			 * 解决图片在ie中背景透明的问题。
			 * 
			 * @param imgObj
			 */
			fixPNG : function(imgObj) {
				var arVersion = navigator.appVersion.split("MSIE");
				var version = parseFloat(arVersion[1]);
				if ((version >= 5.5) && (version < 7)
						&& (document.body.filters)) {
					var imgID = (imgObj.id) ? "id='" + imgObj.id + "' " : "";
					var imgClass = (imgObj.className) ? "class='"
							+ imgObj.className + "' " : "";
					var imgTitle = (imgObj.title) ? "title='" + imgObj.title
							+ "' " : "title='" + imgObj.alt + "' ";
					var imgStyle = "display:inline-block;"
							+ imgObj.style.cssText;
					var strNewHTML = "<span "
							+ imgID
							+ imgClass
							+ imgTitle
							+ " style=\""
							+ "width:"
							+ imgObj.width
							+ "px; height:"
							+ imgObj.height
							+ "px;"
							+ imgStyle
							+ ";"
							+ "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
							+ "(src=\'" + imgObj.src
							+ "\', sizingMethod='scale');\"></span>";
					imgObj.outerHTML = strNewHTML;
				}
			},
			/**
			 * 获取当前路径中指定键的参数值。
			 * 
			 * @param key
			 * @returns
			 */
			getParameter : function(key) {
				var parameters = unescape(window.location.search.substr(1))
						.split("&");
				for ( var i = 0; i < parameters.length; i++) {
					var paramCell = parameters[i].split("=");
					if (paramCell.length == 2
							&& paramCell[0].toUpperCase() == key.toUpperCase()) {
						return paramCell[1];
					}
				}
				return new String();
			},
			/**
			 * 根据年份和月份获取某个月的天数。
			 * 
			 * @param year
			 * @param month
			 * @returns
			 */
			getMonthDays : function(year, month) {
				if (month < 0 || month > 11) {
					return 30;
				}
				var arrMon = new Array(12);
				arrMon[0] = 31;
				if (year % 4 == 0) {
					arrMon[1] = 29;
				} else {
					arrMon[1] = 28;
				}
				arrMon[2] = 31;
				arrMon[3] = 30;
				arrMon[4] = 31;
				arrMon[5] = 30;
				arrMon[6] = 31;
				arrMon[7] = 31;
				arrMon[8] = 30;
				arrMon[9] = 31;
				arrMon[10] = 30;
				arrMon[11] = 31;
				return arrMon[month];
			},
			/**
			 * 计算日期为当年的第几周
			 * 
			 * @param year
			 * @param month
			 * @param day
			 * @returns
			 */
			weekOfYear : function(year, month, day) {
				// year 年
				// month 月
				// day 日
				// 每周从周日开始
				var date1 = new Date(year, 0, 1);
				var date2 = new Date(year, month - 1, day, 1);
				var dayMS = 24 * 60 * 60 * 1000;
				var firstDay = (7 - date1.getDay()) * dayMS;
				var weekMS = 7 * dayMS;
				date1 = date1.getTime();
				date2 = date2.getTime();
				return Math.ceil((date2 - date1 - firstDay) / weekMS) + 1;
			},
			/**
			 * 添加书签
			 * 
			 * @param title
			 * @param url
			 * @returns {Boolean}
			 */
			addBookmark : function(title, url) {
				if (window.sidebar) {
					window.sidebar.addPanel(title, url, "");
				} else if (document.all) {
					window.external.AddFavorite(url, title);
				} else if (window.opera && window.print) {
					return true;
				}
			},

			/**
			 * 设置cookie
			 * 
			 * @param name
			 * @param value
			 */
			setCookie : function(name, value) {
				var expdate = new Date();
				var argv = arguments;
				var argc = arguments.length;
				var expires = (argc > 2) ? argv[2] : null;
				var path = (argc > 3) ? argv[3] : null;
				var domain = (argc > 4) ? argv[4] : null;
				var secure = (argc > 5) ? argv[5] : false;
				if (expires != null)
					expdate.setTime(expdate.getTime() + (expires * 1000));

				document.cookie = name
						+ "="
						+ escape(value)
						+ ((expires == null) ? "" : (";  expires=" + expdate
								.toGMTString()))
						+ ((path == null) ? "" : (";  path=" + path))
						+ ((domain == null) ? "" : (";  domain=" + domain))
						+ ((secure == true) ? ";  secure" : "");

			},
			/**
			 * 删除cookie
			 * 
			 * @param name
			 */
			delCookie : function(name) {
				var exp = new Date();
				exp.setTime(exp.getTime() - 1);
				var cval = $.getCookie(name);
				document.cookie = name + "=" + cval + ";  expires="
						+ exp.toGMTString();

			},
			/**
			 * 读取cookie
			 * 
			 * @param name
			 * @returns
			 */
			getCookie : function(name) {
				var arg = name + "=";
				var alen = arg.length;
				var clen = document.cookie.length;
				var i = 0;
				while (i < clen) {
					var j = i + alen;
					if (document.cookie.substring(i, j) == arg)
						return $.getCookieVal(j);
					i = document.cookie.indexOf(" ", i) + 1;
					if (i == 0)
						break;
				}
				return null;

			},
			getCookieVal : function(offset)

			{
				var endstr = document.cookie.indexOf(";", offset);
				if (endstr == -1)
					endstr = document.cookie.length;
				return unescape(document.cookie.substring(offset, endstr));
			},
			/**
			 * 通过js设置表单的值。
			 * 
			 * @param data
			 */
			setFormByJson : function(data) {
				var json = data;
				if (typeof (data) == "string") {
					json = jQuery.parseJSON(data);
				}

				for ( var p in json) {

					var value = json[p];
					var frmElments = $("input[name='" + p+ "'],textarea[name='" + p + "']");
					if (frmElments[0]) {
						frmElments.val(value);
					}
				}
			},
			/**
			 * 当鼠标移过表格行时，高亮表格行数据
			 */
			highlightTableRows : function() {
				$("tr.odd,tr.even").hover(function() {
					$(this).addClass("over");
				}, function() {
					$(this).removeClass("over");
				});
			},
			/**
			 * 选中行或反选
			 **/
			selectTr:function(){
				$("tr.odd,tr.even").each(function(){
					$(this).bind("mousedown",function(event){
						if(event.target.tagName=="TD")  
							var strFilter='input:checkbox[class="pk"],input:radio[class="pk"]';
							var obj=$(this).find(strFilter);
							if(obj.length==1){
								var state=obj.attr("checked");
								obj.attr("checked",!state);
								var isHashCheck = $("chkall");
								if(isHashCheck){
									obj.trigger("change");
								}
							}
						}
					);    
				});
			},
			/**
			 * 在数组中指定的位置插入数据。
			 * @param aryData
			 * @param data
			 * @param index
			 */
			insert : function(aryData,data,index){
				if(isNaN(index) || index<0 || index>aryData.length) {
					aryData.push(data);
				}else{
					var temp = aryData.slice(index);
					aryData[index]=data;
					for (var i=0; i<temp.length; i++){
						aryData[index+1+i]=temp[i];
					}
				}
			},
			
			getFirstLower:function(v){
				var value="";
				if(v.indexOf('_')!=-1){
					var ary=v.split('_');
					for(var i=0;i<ary.length;i++){
						var tmp=ary[i];
						if(i==0){
							value+=tmp.toLowerCase();
						}else{
							value+=tmp.substring(0,1).toUpperCase()+tmp.substring(1,tmp.length+1).toLowerCase();
						}
					}
				}else{
					value=v.toLowerCase();
				}
				return value;
			},
			
			getFirstUpper:function(v){
				var value="";
				if(v.indexOf('_')!=-1){
					var ary=v.split('_');
					for(var i=0;i<ary.length;i++){
						var tmp=ary[i];
						value+=tmp.substring(0,1).toUpperCase()+tmp.substring(1,tmp.length+1).toLowerCase();
					}
				}else{
					value=v.substring(0,1).toUpperCase()+v.substring(1,v.length+1).toLowerCase();
				}
				return value;
			},
			/**
			 * 
			 * @param url
			 * @returns
			 */
			openFullWindow:function(url){
				var h=screen.availHeight-35;
				var w=screen.availWidth-5;
				var vars="top=0,left=0,height="+h+",width="+w+",status=no,toolbar=no,menubar=no,location=no,resizable=1,scrollbars=1";
				
				var win=window.open(url,"",vars,true);
				return win;
			},
			/**
			 * 刷新父窗口并关闭当前窗口。
			 */
			closeWindow:function(){
				if(window.opener){
					try{
						window.opener.location.href=window.opener.location.href.getNewUrl();
					}
					catch(e){}
				}	
				window.close();
			},
			/**
	         * 如果传入的值是null、undefined或空字符串，则返回true。（可选的）
	         * @param {Mixed} value 要验证的值。
	         * @param {Boolean} allowBlank （可选的） 如果该值为true，则空字符串不会当作空而返回true。
	         * @return {Boolean}
	         */
	        isEmpty : function(v, allowBlank){
	            return v === null || v === undefined || (!allowBlank ? v === '' : false);
	        },
			/**
			 * 将数字转换成人名币大写。
			 * @param currencyDigits
			 * @returns
			 */
			convertCurrency:function(currencyDigits) { 
				 
			    var MAXIMUM_NUMBER = 99999999999.99;
			    var CN_ZERO = "零"; 
			    var CN_ONE = "壹"; 
			    var CN_TWO = "贰"; 
			    var CN_THREE = "叁"; 
			    var CN_FOUR = "肆"; 
			    var CN_FIVE = "伍"; 
			    var CN_SIX = "陆"; 
			    var CN_SEVEN = "柒"; 
			    var CN_EIGHT = "捌"; 
			    var CN_NINE = "玖"; 
			    var CN_TEN = "拾"; 
			    var CN_HUNDRED = "佰"; 
			    var CN_THOUSAND = "仟"; 
			    var CN_TEN_THOUSAND = "万"; 
			    var CN_HUNDRED_MILLION = "亿"; 
			    var CN_SYMBOL = ""; 
			    var CN_DOLLAR = "元"; 
			    var CN_TEN_CENT = "角"; 
			    var CN_CENT = "分"; 
			    var CN_INTEGER = "整"; 
			    var integral;    
			    var decimal;   
			    var outputCharacters;
			    var parts; 
			    var digits, radices, bigRadices, decimals; 
			    var zeroCount; 
			    var i, p, d; 
			    var quotient, modulus; 
			    currencyDigits = currencyDigits.toString(); 
			    if (currencyDigits == "") { 
			        return ""; 
			    } 
			    if (currencyDigits.match(/[^,.\d]/) != null) { 
			        return ""; 
			    } 
			    if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) { 
			        return ""; 
			    } 
			    currencyDigits = currencyDigits.replace(/,/g, "");    
			    currencyDigits = currencyDigits.replace(/^0+/, "");   
			    
			    if (Number(currencyDigits) > MAXIMUM_NUMBER) { 
			        return ""; 
			    } 

			    parts = currencyDigits.split("."); 
			    if (parts.length > 1) { 
			        integral = parts[0]; 
			        decimal = parts[1]; 
			       
			        decimal = decimal.substr(0, 2); 
			    } 
			    else { 
			        integral = parts[0]; 
			        decimal = ""; 
			    } 
			    
			    digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE); 
			    radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND); 
			    bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION); 
			    decimals = new Array(CN_TEN_CENT, CN_CENT); 
			    
			    outputCharacters = ""; 
			 
			    if (Number(integral) > 0) { 
			        zeroCount = 0; 
			        for (i = 0; i < integral.length; i++) { 
			            p = integral.length - i - 1; 
			            d = integral.substr(i, 1); 
			            quotient = p / 4; 
			            modulus = p % 4; 
			            if (d == "0") { 
			                zeroCount++; 
			            } 
			            else { 
			                if (zeroCount > 0)  { 
			                    outputCharacters += digits[0]; 
			                } 
			                zeroCount = 0; 
			                outputCharacters += digits[Number(d)] + radices[modulus]; 
			            } 
			            if (modulus == 0 && zeroCount < 4) { 
			                outputCharacters += bigRadices[quotient]; 
			            } 
			        } 
			        outputCharacters += CN_DOLLAR; 
			    } 
			    
			    if (decimal != "") { 
			        for (i = 0; i < decimal.length; i++) { 
			            d = decimal.substr(i, 1); 
			            if (d != "0") { 
			                outputCharacters += digits[Number(d)] + decimals[i]; 
			            } 
			        } 
			    } 
			  
			    if (outputCharacters == "") { 
			        outputCharacters = CN_ZERO + CN_DOLLAR; 
			    } 
			    if (decimal == "") { 
			        outputCharacters += CN_INTEGER; 
			    } 
			    outputCharacters = CN_SYMBOL + outputCharacters; 
			    return outputCharacters; 
			},
			/**
			 * 转换节点的tagName
			 * 示例 var me=$(this); me=$.tagName(me,"span");
			 * @param self {object} 要转换的单个节点
			 * @param tag  {string} 转换为tag类型节点
			 * @return {object} 转换后的节点对象
			 */
			tagName:function(self,tag){
				var attrs=self.attributes,
					newTag=document.createElement(tag);
				for(var i=0,c;c=attrs[i++];){
					if(!c.value||c.value=='null')continue;
					$(newTag).attr(c.name,c.value);
				}
				$(self).before($(newTag));
				$(self).remove();
				return $(newTag);
			},
			/**
			 * 在文本框指定的地方插入文本
			 * @param txtarea 文本框对象
			 * @param tag 文本
			 */
			insertText : function(txtarea, tag) {
				// IE
				if (document.selection) {
					var theSelection = document.selection.createRange().text;
					if (!theSelection) {
						theSelection = tag;
					}
					txtarea.focus();
					if (theSelection.charAt(theSelection.length - 1) == " ") {
						theSelection = theSelection.substring(0,
								theSelection.length - 1);
						document.selection.createRange().text = theSelection
								+ " ";
					} else {
						document.selection.createRange().text = theSelection;
					}
					// Mozilla
				} else if (txtarea.selectionStart || txtarea.selectionStart == '0') {
					var startPos = txtarea.selectionStart;
					var endPos = txtarea.selectionEnd;
					var myText = (txtarea.value).substring(startPos, endPos);
					if (!myText) {
						myText = tag;
					}
					if (myText.charAt(myText.length - 1) == " ") { 
						subst = myText.substring(0, (myText.length - 1)) + " ";
					} else {
						subst = myText;
					}
					txtarea.value = txtarea.value.substring(0, startPos)+ subst+ txtarea.value.substring(endPos,txtarea.value.length);
					txtarea.focus();
					var cPos = startPos + (myText.length);
					txtarea.selectionStart = cPos;
					txtarea.selectionEnd = cPos;
					// All others
				} else {
					txtarea.value += tag;
					txtarea.focus();
				}
				if (txtarea.createTextRange)
					txtarea.caretPos = document.selection.createRange().duplicate();
		},
		confirm:function(selector,message,callback){
			$(selector).click(function(){
				if($(this).hasClass('disabled')) return false;
			
				var ele=this;
				$.ligerDialog.confirm(message,$lang.tip.msg,function(rtn) {
					if(rtn){
						if($.browser.msie){
							$.gotoDialogPage(ele.href);
						}
						else{
							location.href=ele.href;
						}
					}
				});
				return false;
			});
		},
		/**
		 * Dialog窗口跳转
		 * @param {} url 地址
		 */
		gotoDialogPage:  function (url) {
	        if($.browser.msie){
	        	var a = document.createElement("a");
		        a.href=url;
		        document.body.appendChild(a);
		        a.click();
			}
			else{
				location.href=url;
			}
		},
		/**
		 * 克隆对象。
		 */
		cloneObject:function(obj){
			var o = obj.constructor === Array ? [] : {};
		    for(var i in obj){
		        if(obj.hasOwnProperty(i)){
		            o[i] = typeof obj[i] === "object" ? cloneObject(obj[i]) : obj[i];
		        }
		    }
			return o;
		},
		/**
		 * 清除表单
		 */
		clearQueryForm:function(){
			$("input[name^='Q_'],select[name^='Q_']").each(function(){
				$(this).val('');
			});
		},
		getFileExtName:function(fileName){
			var pos=fileName.lastIndexOf(".");
			if(pos==-1) return "";
			return  fileName.substring(pos +1);
		},
		//转成千分位。
		comdify:function(v){
			if(v&&v!=''){
				n=v+"";
				var re=/\d{1,3}(?=(\d{3})+$)/g;
				var n1=n.trim().replace(/^(\d+)((\.\d+)?)$/,function(s,s1,s2){
					return s1.replace(re,"$&,")+s2;
				});
				return n1;
			}
			return v;
		},
		toNumber:function(v){
			if(v&&v!=''){
				if(v.indexOf(',')==-1)
					return v;
				var ary=v.split(',');
				var val=ary.join("");
				return val;
			}
			return 0;
		},
		/**
		 * 上下移动
		 * 
		 * @param {}
		 *            obj 上移的对象
		 * @param {}
		 *            isUp 是否上移
		 */
		moveTr : function(obj, isUp) {
			var thisTr = $(obj).parents("tr");
			if (isUp) {
				var prevTr = $(thisTr).prev();
				if (prevTr) {
					thisTr.insertBefore(prevTr);
				}
			} else {
				var nextTr = $(thisTr).next();
				if (nextTr) {
					thisTr.insertAfter(nextTr);
				}
			}
		},
		getCurrentDate:function(){
			
			var myDate=new Date();
			var month=myDate.getMonth()+1;
			month=(month<10)?"0" + month:month;
			return myDate.getFullYear() +"-" + month + "-"+ myDate.getDate();
		},
		getPageSize:function() { 
			var winW, winH; 
			if(window.innerHeight) {// all except IE 
				winW = window.innerWidth; 
				winH = window.innerHeight; 
			} else if (document.documentElement && document.documentElement.clientHeight) {// IE 6 Strict Mode 
				winW = document.documentElement.clientWidth; 
				winH = document.documentElement.clientHeight; 
			} else if (document.body) { // other 
				winW = document.body.clientWidth; 
				winH = document.body.clientHeight; 
			}  // for small pages with total size less then the viewport  
			return {width:winW, height:winH}; 
		} ,
		isArray : function(v){
            return  Object.prototype.toString.call(v) === '[object Array]';
        }
		
});

/**
 * 功能：给url添加一个当前时间日期数值，使页面不会被缓存。
 */
String.prototype.getNewUrl = function() {
	// 如果url中没有参数。
	var time = new Date().getTime();
	var url = this;
	//去除‘#’后边的字符
	if (url.indexOf("#") != -1) {
		var index=url.lastIndexOf("#",url.length-1);
		url=url.substring(0, index);
	}
	
	while(url.endWith("#")){
		url=url.substring(0, url.length-1);
	}
	url=url.replace(/(\?|&)rand=\d*/g, "");
	if (url.indexOf("?") == -1) {
		url += "?rand=" + time;
	} else {
		url += "&rand=" + time;
	}
	return url;
};

/**
 * 功能：给url添加jsessionId 防止session丢失。
 * @returns {String}
 */
String.prototype.getSessionUrl=function(){
	//jsessionid
	var url = this;
	if(url.indexOf(";jsessionid=")!=-1){
		return url;
	}
	if(url.indexOf("?")==-1){
		url+=";jsessionid=" +__jsessionid;
	}
	else{
		var aryUrl=url.split("?");
		url=aryUrl[0] +";jsessionid=" +__jsessionId +"?" + aryUrl[1];
	}
	return url;
};



/**
 * 判断字符串是否为空。
 * 
 * @returns {Boolean}
 */
String.prototype.isEmpty = function() {
	var rtn = (this == null || this == undefined || this.trim() == '');
	return rtn;
};
/**
 * 功能：移除首尾空格
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
/**
 * 功能:移除左边空格
 */
String.prototype.lTrim = function() {
	return this.replace(/(^\s*)/g, "");
};
/**
 * 功能:移除右边空格
 */
String.prototype.rTrim = function() {
	return this.replace(/(\s*$)/g, "");
};



/**
 * 判断结束是否相等
 * 
 * @param str
 * @param isCasesensitive
 * @returns {Boolean}
 */
String.prototype.endWith = function(str, isCasesensitive) {
	if (str == null || str == "" || this.length == 0
			|| str.length > this.length)
		return false;
	var tmp = this.substring(this.length - str.length);
	if (isCasesensitive == undefined || isCasesensitive) {
		return tmp == str;
	} else {
		return tmp.toLowerCase() == str.toLowerCase();
	}

};
/**
 * 判断开始是否相等
 * 
 * @param str
 * @param isCasesensitive
 * @returns {Boolean}
 */
String.prototype.startWith = function(str, isCasesensitive) {
	if (str == null || str == "" || this.length == 0
			|| str.length > this.length)
		return false;
	var tmp = this.substr(0, str.length);
	if (isCasesensitive == undefined || isCasesensitive) {
		return tmp == str;
	} else {
		return tmp.toLowerCase() == str.toLowerCase();
	}
};

/**
 * 在字符串左边补齐指定数量的字符
 * 
 * @param c
 *            指定的字符
 * @param count
 *            补齐的次数 使用方法： var str="999"; str=str.leftPad("0",3); str将输出 "000999"
 * @returns
 */
String.prototype.leftPad = function(c, count) {
	if (!isNaN(count)) {
		var a = "";
		for ( var i = this.length; i < count; i++) {
			a = a.concat(c);
		}
		a = a.concat(this);
		return a;
	}
	return null;
};

/**
 * 在字符串右边补齐指定数量的字符
 * 
 * @param c
 *            指定的字符
 * @param count
 *            补齐的次数 使用方法： var str="999"; str=str.rightPad("0",3); str将输出
 *            "999000"
 * @returns
 */
String.prototype.rightPad = function(c, count) {
	if (!isNaN(count)) {
		var a = this;
		for ( var i = this.length; i < count; i++) {
			a = a.concat(c);
		}
		return a;
	}
	return null;
};

/**
 * 对html字符进行编码 用法： str=str.htmlEncode();
 * 
 * @returns
 */
String.prototype.htmlEncode = function() {
	return this.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g,
			"&gt;").replace(/\"/g, "&#34;").replace(/\'/g, "&#39;");
};

/**
 * 对html字符串解码 用法： str=str.htmlDecode();
 * 
 * @returns
 */
String.prototype.htmlDecode = function() {
	return this.replace(/\&amp\;/g, '\&').replace(/\&gt\;/g, '\>').replace(
			/\&lt\;/g, '\<').replace(/\&quot\;/g, '\'').replace(/\&\#39\;/g,
			'\'');
};

/**
 * 对json中的特殊字符进行转义
 */
String.prototype.jsonEscape = function(){
	return this.replace(/\"/g, "&quot;").replace(/\n/g,"&nuot;");
};

/**
 * 对json中的特殊字符进行转义
 */
String.prototype.jsonUnescape = function(){
	return this.replace(/&quot;/g, "\"").replace(/&nuot;/g,"\n");
};

/**
 * 字符串替换
 * 
 * @param s1
 *            需要替换的字符
 * @param s2
 *            替换的字符。
 * @returns
 */
String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
};

/**
 * 获取url参数
 * 
 * @returns {object}
 */
String.prototype.getArgs = function() {
	var args = {};
	if(this.indexOf("?")>-1){
		var argStr = this.split("?")[1],
			argAry = argStr.split("&");
		
		for(var i=0,c;c=argAry[i++];){
			var pos = c.indexOf("=");
			if(pos==-1)continue;
			var argName = c.substring(0,pos),
				argVal = c.substring(pos+1);
			argVal = decodeURIComponent(argVal);
			args[argName] = argVal;
		}
	}
	return args;
};

/**
 * var str=String.format("姓名:{0},性别:{1}","ray","男");
 * alert(str);
 * @returns
 */
String.format=function(){
	var template=arguments[0];
	var args=arguments;
	var str=template.replace(/\{(\d+)\}/g,function(m,i){
		var k=parseInt(i)+1;
		return args[k];
	});
	return str;
};




/**
 * 字符串操作 使用方法： var sb=new StringBuffer(); sb.append("aa"); sb.append("aa"); var
 * str=sb.toString();
 * 
 * @returns {StringBuffer}
 */
function StringBuffer() {
	this.content = new Array;
}
StringBuffer.prototype.append = function(str) {
	this.content.push(str);
};
StringBuffer.prototype.toString = function() {
	return this.content.join("");
};

/**  
* 日期格式化  
* 格式 YYYY/yyyy/YY/yy 表示年份  
* MM/M 月份  
* W/w 星期  
* dd/DD/d/D 日期  
* hh/HH/h/H 时间  
* mm/m 分钟  
* ss/SS/s/S 秒  
*/
Date.prototype.Format = function(formatStr)  
{  
	var str = formatStr;
	 if(formatStr==undefined || formatStr==null){
		 str="yyyy-MM-dd";
	 }
	   
	 var Week = ['日','一','二','三','四','五','六'];  
	
	 str=str.replace(/yyyy|YYYY/,this.getFullYear());  
	 str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));  
	
	 str=str.replace(/MM/,(this.getMonth()+1)>9?(this.getMonth()+1).toString():'0' + (this.getMonth()+1));  
	 str=str.replace(/M/g,(this.getMonth()+1));  
	
	 str=str.replace(/w|W/g,Week[this.getDay()]);  
	
	 str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());  
	 str=str.replace(/d|D/g,this.getDate());  
	
	 str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());  
	 str=str.replace(/h|H/g,this.getHours());  
	 str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());  
	 str=str.replace(/m/g,this.getMinutes());  
	
	 str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());  
	 str=str.replace(/s|S/g,this.getSeconds());  
	
	 return str;  
};

/** 
* 求两个时间的天数差 日期格式为 yyyy-MM-dd 或 YYYY-MM-dd HH:mm:ss
*/ 
function daysBetween(DateOne,DateTwo)
{
	 var date1 =  new Date(DateOne).getTime();
	 var date2 = new Date(DateTwo).getTime();;
       if(date1>date2){
           return false;
       }else{
           return true;
       }
};

/**
 * 加载多个Script
 * @param resources script file array :['file1.js','file2.js']
 * @param callback function
 * @returns void
 */

jQuery.getMutilScript = function( resources, callback ) {
	var getScript = function(url,callback){
		$.ajax({
			  url: url,
			  dataType: "script",
			  success: callback,
			  async:false
		}).done(function(){
			callback && callback();
		});
	};
    var // reference declaration &amp; localization
    length = resources.length,
    handler = function() { counter++; },
    deferreds = [],
    counter = 0,
    idx = 0;

    for ( ; idx < length; idx++ ) {
        deferreds.push(
        	getScript( resources[ idx ], handler )
        );
    }
    jQuery.when(deferreds ).done(function(){
    	callback && callback();
    });
};

$(function(){
	if($.browser){
		if($.browser.version=='6.0'){
			$("a.tipinfo").each(function(){
				$(this).mouseover(function(){
					$("span",$(this)).show();
				});
				$(this).mouseleave(function(){
					$("span",$(this)).hide();
				});
			});
		}
	}
});



jQuery.getWindowRect = function() {
  var myWidth = 0, myHeight = 0;
  if( typeof( window.innerWidth ) == 'number' ) {
    //Non-IE
    myWidth = window.innerWidth;
    myHeight = window.innerHeight;
  } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
    //IE 6+ in 'standards compliant mode'
    myWidth = document.documentElement.clientWidth;
    myHeight = document.documentElement.clientHeight;
  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
    //IE 4 compatible
    myWidth = document.body.clientWidth;
    myHeight = document.body.clientHeight;
  }
  return {
	height:myHeight,
	width:myWidth
  };
}

//禁用刷新。通过传入浏览器类型 来指定禁用某个浏览器的刷新
function forbidF5(exp) {
	var currentExplorer = window.navigator.userAgent;
	//ie "MSIE"  ,, firefox "Firefox" ,,Chrome "Chrome",,Opera  "Opera",,Safari  "Safari"
	if (currentExplorer.indexOf(exp) >= 0) {
		document.onkeydown = function(e) {
			var ev = window.event || e;
			var code = ev.keyCode || ev.which;
			if (code == 116) {
				ev.keyCode ? ev.keyCode = 0 : ev.which = 0;
				cancelBubble = true;
				return false;
			}
		}
	}
}


Date.prototype.format = function(format)  
{  
    var o = {  
        "M+" : this.getMonth()+1, //month  
        "d+" : this.getDate(),//day  
        "h+" : this.getHours(), //hour  
        "m+" : this.getMinutes(), //minute  
        "s+" : this.getSeconds(), //second  
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter  
        "S" : this.getMilliseconds() //millisecond  
    }  
  
    if(/(y+)/.test(format))   
        format = format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));  
    for(var k in o)  
        if(new RegExp("("+ k +")").test(format))  
            format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] :("00"+ o[k]).substr((""+ o[k]).length));  
    return format;  
}  
/**
 * 调整点击资源菜单时  tab导航栏向上浮动的问题；
 */
function justifyMargin(height,level,time){
	if(window.navigator.userAgent.indexOf("Chrome")>=0){
	    window.setTimeout(function(){
	    	var tabLinks = "";
		    if(level==3){
		    	tabLinks = $("#framecenter > div.l-tab-links",parent.parent.document);
		    	if($(".l-layout-left",parent.document)[0] && $("iframe",parent.document).length !=1) return;
		    }else{
		    	tabLinks = $("#framecenter > div.l-tab-links",parent.document)
		    }
		    tabLinks.css("margin-top",parseInt(tabLinks.css("margin-top"))+ height +"px");
	    },time||0)
	}
}
/**
 * 	修复所有树状结构右键菜单超出屏幕的问题
 * @param e   传入点击事件event
 */
function justifyRightClickPosition(e){
	var offsetHeight=parseInt($(".l-menu").eq(0).height());
	var ePageY=parseInt(e.pageY);
	var bodyOffsetHeight=parseInt(document.body.offsetHeight);
	var h=$(window).height() ;
	if(offsetHeight+ePageY>=bodyOffsetHeight-10){
		e.pageY=ePageY-offsetHeight;
	}
}
/**
 * 修复list页面滚动条的bug ，用法 在对应的页面 的body上面加上 onload事件  如 ： <body style="overflow: hidden;" onload="changeHeight()">
 */
function changeScrollHeight(noNeedFoldHeight,time){
	window.setTimeout(function(){
		$.setCookie("noNeedFoldHeight", noNeedFoldHeight||"");
		if(noNeedFoldHeight) return;
		$("body").css("overflow-y","hidden");
		var currentTable=$(".table-grid.table-list");
		if(! currentTable) return;
		var tableBody=currentTable.parent(),foldHeight=0;
		if(!parent.document.querySelectorAll("#logoImg")[0])foldHeight =  $("#searchForm").height();
		var moreHeight = $('.panel-search').attr("moreHeight")||0;
		tableBody.height($(window).height()-tableBody[0].offsetTop-$(".index_menu",$(parent.document)).height()/2-foldHeight-moreHeight);
		tableBody.css("overflow-y","scroll");
		tableBody.css("border","1px solid #A0BDBB");
		currentTable.css("margin-top","0px"); 
	},time||10)
}

/**
 * 获取元素当前页angular的scope by liyj
 * @param obj
 * @returns
 */
function getScope(obj){
	obj=(obj&&$(obj))||$("[ng-controller]");
	obj=(obj[0]?obj:$("body"));
	return obj.scope();
}

/**
 * 将字符串转为json对象。
 * @param jsonStr
 * @param type  可不填写
 * @returns
 */
function parseToJson(jsonStr,type){
	type=type||1;
	if(jsonStr === "") return;
	try{
		switch(type){
			case 1:
				return eval("("+jsonStr+")");
			break;
			case 2:
				return $.parseJSON(jsonStr);
				break;
			case 3:
				return angular.fromJson(jsonStr);
				break;
			case 4:
				JSON.parse(jsonStr);
				break;
			default:
				console.error("解析json对象错误");
				break;
		}
		
	}catch(e){
		return parseToJson(jsonStr,type+1);
	}
}

//在鼠标停留的地方插入text
function insertText(obj,str) {
    if (document.selection) {
        var sel = document.selection.createRange();
        sel.text = str;
    } else if (typeof obj.selectionStart === 'number' && typeof obj.selectionEnd === 'number') {
        var startPos = obj.selectionStart,
            endPos = obj.selectionEnd,
            cursorPos = startPos,
            tmpStr = obj.value;
        obj.value = tmpStr.substring(0, startPos) + str + tmpStr.substring(endPos, tmpStr.length);
        cursorPos += str.length;
        obj.selectionStart = obj.selectionEnd = cursorPos;
    } else {
        obj.value += str;
    }
}
/**
 * 通过scope中取得ng-model对应的属性值
 * @param obj 控件对象
 * @param exp 表示ngModel （可选） 列如 editingField.option.numberFormat.isShowComdify 
 * @param scope （可选）
 * @returns
 */
function getValByScope(obj,exp,scope){
	if(obj) obj=angular.element(obj);
	if(!scope&&!obj) return;
	scope=scope||obj.scope();
	var ngModel=exp||(obj&&(obj.attr("ht-model-true")||obj.attr("ng-model")));
	if(!ngModel) {
		if(obj&&(obj.attr("type")=="checkbox"||obj.attr("type")=="radio")){
			if(obj.is(":checked")) return obj.val();
			else return "";
		}else return obj.val();
	}
	//判断是否是获取复选框的值
	if(ngModel.indexOf("__tempCheckBoxVal")!=-1){
		ngModel=obj.attr("ht-checkbox");
	}
	try{
		if(scope){
			var str="scope."+ngModel;
			return eval("("+str+")");
		}else{
			return obj.val();
		}
	}catch(e){
		return "";
	}
}
function delSubRow(){
	$("input:checked").closest("[var='templTr']").remove();
}
function checkTr(el){
	var ck = $("input[type='checkbox']",$(el));
	ck.attr("checked",!ck.attr("checked"));
}
/**
 * 给控件的scope赋值
 * @param obj 控件对象 (当传入exp时，obj可以传入空值)
 * @param val 值
 * @param time 延迟时间（可选）
 * @param exp 表示ngModel （可选） 列如 editingField.option.numberFormat.isShowComdify 
 */
function setValToScope(obj,val,time,exp,scope){
	__setValTimes = 0;
	if(time){ // 加上时间延迟 是为了处理在easyUI界面没有加载成功的情况下 数据赋值失败的问题
		window.setTimeout(fun,time);
	}else{
		fun();
	}
	function fun(){
		if(obj&&!scope){
			obj=angular.element(obj);
			scope=obj.scope();
		}
		if(!scope) return;
		ngModel=exp||(obj&&(obj.attr("ht-model-true")||obj.attr("ng-model")));
		if(!ngModel) return;
		var exps=ngModel.split("."),tempModel=ngModel;
		
		if(ngModel.indexOf("data")==0&&ngModel.indexOf(".sub_")>0){
			var sub_model = $.extend([],exps);
			sub_model.splice(exps.length-1,1);
			var subModelVals = getValByScope(null,sub_model.join("."),scope),tempScope = "";
			if(!subModelVals){
				subModelVals = getValByScope(null,sub_model.join("."),scope.$parent);
				tempScope = scope.$parent;
			}else{
				tempScope = scope;
			}
			if(subModelVals){
				if(subModelVals instanceof Array){
					for(var i =0;i<subModelVals.length;i++){
						subModelVals[i][targetParam] = val;
					}
					setValToScope(null,subModelVals,null,sub_model.join("."),tempScope);
					return ;
				}
			}
		}
		setVal();
		__setValTimes = 0;
		function setVal(){
			try{
				eval("("+"scope."+ngModel+"=val"+")");
				obj&&obj.attr("ng-model")&&obj.val(val);
				if(!scope.$$phase){
					scope.$digest();
				}
			}catch(e){
				if(__setValTimes>20) return ;
				setTempVal();
				__setValTimes++;
				tempModel=ngModel;
				exps=ngModel.split(".");
				setVal();
			}
		}
		function setTempVal(){// a.b.c
			try{
				eval("("+"scope."+tempModel+"={}"+")");
			}catch(e){
				tempModel=exps.remove(exps[exps.length-1]).join(".");
				setTempVal();
			}
		}
	}
}

/**
 * 序列化查询参数
 * @param {} form
 * @return {}
 */
function serializeObject(form){
	var o = {};
	var a = $(form).serializeArray();
	$.each(a, function() {
	    if (o[this.name]) {
	        if (!o[this.name].push) {
	            o[this.name] = [o[this.name]];
	        }
	        o[this.name].push(this.value || '');
	    } else {
	        o[this.name] = this.value || '';
	    }
	});
	return o;
}
