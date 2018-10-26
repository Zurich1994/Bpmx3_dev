
/**
 * 数据模板构造方法
 */
var BusQueryRuleEdit = function() {

}
// 属性及函数
BusQueryRuleEdit.prototype = {
	/**
	 * 初始化
	 */
	init : function() {
		var _self = this;
		this.initDisplaySetting();
		this.initSortField();
		this.initFilter();
//		this.initExportField();
		this.selectTr();

		// 绑定选择排序点击事件
		$("#selectSort").click(function() {
			_self.selectSort(_self)
		});
	},
	initDisplaySetting : function() {
		var tbl = '#displayFieldTbl';
		// 处理选择
		this.handChange(tbl);
		this.handClick(tbl);

		var tr = $($("#displayFieldTemplate .table-detail")[0]).clone(true,
				true);
		var displayFieldVal = $("#displayField").val();
		if ($.isEmpty(displayFieldVal))
			return;
		var displayField = $.parseJSON(displayFieldVal);
		var sb = new StringBuffer(),
			fieldRightEl = $("[var='fieldRight']", tr),
			hideFieldRightEl = $('<td var="fieldRight" class="hidden"></td>');
		for (var i = 0, c; c = displayField[i++];) {
			$("[var='index']", tr).html(i);
			$("[var='name']", tr).html(c.name);
			$("input[var='variable']", tr).attr("value", c.variable);
			$("input[var='type']", tr).attr("value", c.type);
			$("input[var='style']", tr).attr("value", c.style);
			$("input[var='desc']", tr).attr("value", c.desc);

			for (var j = 0, k; k = c.right[j++];) {
				var rightHtml  = this.getHtmlTd(k);
				if (k.s == 0) {
					if(!k.srid)
						fieldRightEl.html(rightHtml);
					else{
						hideFieldRightEl.html(rightHtml);
						fieldRightEl.after(hideFieldRightEl);
					}
				} else if (k.s == 1) {
					$("[var='printRight']", tr).html(rightHtml);
				}
			}
			
			sb.append(tr.html());
		}
		$(tbl).append(sb.toString());
	},

	/**
	 * 设置每行的显示的权限
	 * 
	 * @param v
	 *            权限简称
	 * @param full
	 *            权限全称
	 */
	getHtmlTd : function(k) {
		var v = k.type,
			rightId = k.id,
			rightName = k.name,
			rightScript = k.script,
			srid = k.srid||"",
			source = k.source?JSON.stringify(k.source).replaceAll("\"","\'"):"";
			if(rightScript||rightScript=="undefined")
				rightScript = "";
			var aryTd = [
					'<select name="displayFieldRight" class="change_right" var="right"  >',
					'<option value="none" '
							+ (v == 'none' ? 'selected="selected"' : '')
							+ ' >无</option>',
					'<option value="everyone"'
							+ (v == 'everyone' ? 'selected="selected"' : '')
							+ '>所有人</option>',
					'<option value="user"'
							+ (v == 'user' ? 'selected="selected"' : '')
							+ '>用户</option>',
					'<option value="role"'
							+ (v == 'role' ? 'selected="selected"' : '')
							+ '>角色</option>',
					'<option value="org"'
							+ (v == 'org' ? 'selected="selected"' : '')
							+ '>组织</option>',
					'<option value="orgMgr"'
							+ (v == 'orgMgr' ? 'selected="selected"' : '')
							+ '>组织负责人</option>',
					'<option value="pos"'
							+ (v == 'pos' ? 'selected="selected"' : '')
							+ '>岗位</option>',
					'<option value="script"'
							+ (v == 'script' ? 'selected="selected"' : '')
							+ '>脚本</option>',
					'</select>',
					'<span name="displayFieldRight_span"  '
							+ (v == 'script' || v == 'none' || v == 'everyone'
									? 'style="display: none;" '
									: '') + '>',
					'<input type="hidden"  var="rightId"  value="' + rightId + '">',
					'<textarea  readonly="readonly" var="rightName"  cols="40" rows="3">'
							+ rightName + '</textarea>',
					'<a class="link-get" href="javascript:;"><span class="link-btn">选择</span></a>',
					'</span>',
					'<span class="displayFieldRight_script_span" '
							+ (v != 'script' ? 'style="display: none;" ' : '')
							+ '>',
					'<textarea  cols="40" rows="3"  var="rightScript" >'+ rightScript + '</textarea>',
					'<input  type="hidden" var="srid"  value="' + srid + '" />',
					'<input  type="hidden" var="source"  value=\"' + source + '\" />',
					'<a  href="javascript:;" name="btnScript" class="link var" title="常用脚本" onclick="__DataTemplate__.selectScript(this,false)">常用脚本</a>',
					'</span>'];
			return aryTd.join('');
	},
	/**
	 * 选中行或反选
	 * 
	 * @return void
	 */
	selectTr : function() {
		$("tr.odd,tr.even").each(function() {
			$(this).bind("mousedown", function(event) {
				if (event.target.tagName == "TD") {
					var strFilter = 'input:checkbox[class="pk"],input:radio[class="pk"]';
					var obj = $(this).find(strFilter);
					if (obj.length == 1) {
						var state = obj.attr("checked");
						obj.attr("checked", !state);
					}
				}
			});
		});
	},
	/**
	 * 处理选择改变
	 */
	handChange : function(obj) {
		var _self = this;
		$(obj).delegate("select.change_right", "change", function() {
			var me = $(this), spanObj = me.next(), nextSpanObj = spanObj.next();
			_self.showSpan(me.val(), spanObj);
			var txtObj = $("textarea", spanObj), idObj = $("input:hidden",
					spanObj);
			txtObj.val("");
			idObj.val("");
			var nextTxtObj = $("textarea", nextSpanObj);
			nextTxtObj.val("");
		});
	},
	/**
	 * 显示权限的span
	 */
	showSpan : function(permissionType, spanObj) {
		switch (permissionType) {
			case "user" :
			case "role" :
			case "org" :
			case "orgMgr" :
			case "pos" :
				spanObj.show();
				spanObj.next().hide();
				break;
			case "script" :
				spanObj.hide();
				spanObj.next().show();
				break;
			case "everyone" :
			case "none" :
				spanObj.hide();
				spanObj.next().hide();
				break;
		}
	},
	/**
	 * 处理选择
	 */
	handClick : function(obj) {
		$(obj).delegate("a.link-get", "click", function() {
			var me = $(this), txtObj = me.prev(), idObj = txtObj.prev(), selObj = me
					.parent().prev(), selType = selObj.val();

			// 选择回调
			var callback = function(ids, names) {
				txtObj.val(names);
				idObj.val(ids);
			};

			switch (selType) {
				case "user" :
					UserDialog({
						callback : callback
					});
					break;
				case "role" :
					RoleDialog({
						callback : callback
					});
					break;
				case "org" :
				case "orgMgr" :
					OrgDialog({
						callback : callback
					});
					break;
				case "pos" :
					PosDialog({
						callback : callback
					});
					break;
			}
		});
	},
	//====导出字段=====
	initExportField:function(){	
		var tbl = '#exportFieldTbl';
		// 处理选择
		this.handChange(tbl);
		this.handClick(tbl);

		var tr = $($("#exportFieldTemplate .table-detail")[0]).clone(true,
				true);
				
		var tabletr = $($("#exportFieldTemplate .table-list")[0]).clone(true,
				true);		
		var tableVal = $("#exportField").val();
		if ($.isEmpty(tableVal))
			return;
		var table = $.parseJSON(tableVal);
		var sb = new StringBuffer();
		for (var l = 0, t; t = table[l++];) {
			$("input[var='tablename']", tabletr).val(t.tableName);
			$("input[var='tabledesc']", tabletr).val(t.tableDesc);
			$("input[var='ismain']", tabletr).val(t.isMain);
			$("[var='table']", tabletr).html(t.tableDesc+'('+t.tableName+")");
			sb.append(tabletr.html());
			for (var i = 0, c; c = t.fields[i++];) {
					$("[var='index']", tr).html(i);
				$("[var='name']", tr).html(c.name);
				$("input[var='type']", tr).attr("value", c.type);
				$("input[var='style']", tr).attr("value", c.style);
				$("input[var='desc']", tr).attr("value", c.desc);
				$("input[var='tablename']", tr).attr("value", c.tableName);
				$("input[var='ismain']", tr).attr("value", c.isMain);
				for (var j = 0, k; k = c.right[j++];) {
					var rightHtml = this.getHtmlTd(k.type, k.id, k.name, k.script);
					if (k.s == 2) {
						$("[var='exportRight']", tr).html(rightHtml);
					} 
				}
				sb.append(tr.html());
			}
			
		}
		$(tbl).append(sb.toString());
		
	},
	// =====================排序==============================================================================
	/**
	 * 初始化排序
	 */
	initSortField : function() {

		var tr = $($("#sortTemplate .table-detail tr")[0]).clone(true, true);
		var sortFieldVal = $("#sortField").val();
		if ($.isEmpty(sortFieldVal))
			return;
		var sortField = $.parseJSON(sortFieldVal);
		for (var i = 0, c; c = sortField[i++];) {
			try {
				$("[var='name']", tr).html(c.name);
				$("[var='desc']", tr).html(c.desc);
				$("[var='source']", tr).html(c.source);
				$("[var='sort']", tr).val(c.sort);;
			} catch (e) {
			}
			var tr1 = tr.clone(true, true);
			$("#sortTbl tbody").append(tr1);
		}

	},
	/**
	 * 选择排序
	 * 
	 * @param {}
	 *            _self
	 */
	selectSort : function(_self) {
		var tr = $($("#sortTemplate .table-detail tr")[0]).clone(true, true);
		$("#sort-columnsTbl input:[name='select']:checked").each(function() {
			var me = $(this), name = me.attr("fieldname"), desc = me
					.attr("fielddesc");
			var obj = $('#sortTbl');
			// 查找该字段是否存在
			if (_self.isExistName(obj,'name',name)) {
				$("[var='name']", tr).html(name);
				$("[var='desc']", tr).html(desc);
				var tr1 = tr.clone(true, true);
				$("#sortTbl tbody").append(tr1);
			}
		});

	},
	/**
	 * 判断选择是否存在
	 */
	isExistName : function(obj,o,name) {
		var rtn = true;
		obj.find("[var='"+o+"']").each(function() {
			var me = $(this), n = me.html();
			if (n == name) {
				rtn = false;
				return true;
			}
		});
		return rtn;
	},
	// =====================过滤条件==============================================================================
	/**
	 * 初始化过滤条件
	 */
	initFilter : function() {
		this.handChange('#filterTbl');
		this.handClick('#filterTbl');

		var tr = $($("#filterTemplate .table-detail tr")[0]).clone(true, true);
		var filterFieldVal = $("#filterField").val();
		if ($.isEmpty(filterFieldVal))
			return;
		var filterField = $.parseJSON(filterFieldVal);
		var sb = new StringBuffer();
		for (var i = 0, c; c = filterField[i++];) {
			var filter = {};
			filter.name = c.name;
			filter.key = c.key;
			filter.type = c.type;
			filter.condition = c.condition;
			filter.right = c.right;
			this.addFilterTr(tr, filter)
		}
	},
	/**
	 * 增加过滤行的tr
	 * 
	 * @param {}
	 *            tr
	 * @param {}
	 *            conf
	 */
	addFilterTr : function(tr, conf) {
		var name = conf.name, key = conf.key, type = conf.type,condition = conf.condition, right = conf.right;
		if(condition&& typeof condition =="object")
			condition = JSON.stringify(condition);
		$("[var='name']", tr).html(name);
		$("[var='key']", tr).html(key);
		$("[var='type']", tr).val(type);
		$("[var='typeshow']", tr).html(type==2?'SQL':'条件脚本');
		$("[var='condition']", tr).val(condition);
		var filterRightEl = $("[var='filterRight']", tr),
		hideFieldRightEl = $('<td var="filterRight" class="hidden"></td>');
		
		for (var j = 0, k; k = right[j++];) {
			var rightHtml  = this.getHtmlTd(k);
			if (k.s == 3) {
				if(!k.srid)
					filterRightEl.html(rightHtml);
				else{
					hideFieldRightEl.html(rightHtml);
					filterRightEl.after(hideFieldRightEl);
				}
			} 
		}
		var tr1 = tr.clone(true, true);
		$("#filterTbl tbody").append(tr1);
	},
	/**
	 * 增加过滤条件
	 */
	addFilter : function(conf) {
		var _self = this, tableName = $('#tableName').val(),
			tr = $($("#filterTemplate .table-detail tr")[0]).clone(
				true, true);
		var right = {};
		right.type = 'none';
		right.id = '';
		right.name = '';
		right.script = '';
		this.filterDialog({
			tableName : tableName,
			callback : function(rtn) {
				if (rtn) {
					var filter = {},
						type= rtn.type,
					condition = (type==2?rtn.condition:JSON2.stringify(rtn.condition));
					filter.name = rtn.name;
					filter.key = rtn.key;
					filter.type = type;
					filter.condition = condition;
					filter.right = right;
					_self.addFilterTr(tr, filter);
				}
			}
		});
	},
	/**
	 * 过滤的窗口
	 * 
	 * @param {}
	 *            conf
	 */
	filterDialog : function(conf) {
		var dialogWidth = 750;
		var dialogHeight = 500;

		conf = $.extend({}, {
			dialogWidth : dialogWidth,
			dialogHeight : dialogHeight,
			help : 0,
			status : 0,
			scroll : 0,
			center : 1
		}, conf);

		var winArgs = "dialogWidth=" + conf.dialogWidth + "px;dialogHeight="
				+ conf.dialogHeight + "px;help=" + conf.help + ";status="
				+ conf.status + ";scroll=" + conf.scroll + ";center="
				+ conf.center;

		var url = __ctx
				+ "/platform/bus/busQueryRule/filterDialog.ht?tableName="
				+ conf.tableName;
		url = url.getNewUrl();

		/*var rtn = window.showModalDialog(url, conf, winArgs);

		if (rtn && conf.callback) {
			conf.callback.call(this, rtn);
		}*/
		
		var that =this;
		/*KILLDIALOG*/
		DialogUtil.open({
			height:conf.dialogHeight,
			width: conf.dialogWidth,
			title : '',
			url: url, 
			isResize: true,
			//自定义参数
			conf: conf,
			sucCall:function(rtn){
				if (rtn && conf.callback) {
					conf.callback.call(that, rtn);
				}
			}
		});
	},
	/**
	 * 删除过滤条件
	 */
	delFilter : function() {
		this.delSelectTr("filterTbl");
	},
	/**
	 * 编辑过滤条件
	 */
	editFilter : function(obj) {
		var tr = $(obj).parents("tr"), filter = {};
		filter.name = $("[var='name']", tr).html();
		filter.key = $("[var='key']", tr).html();
		filter.type = $("[var='type']", tr).val();
		filter.condition = $("[var='condition']", tr).val();
		this.editFilterDialog(tr, filter)
	},
	/**
	 * 编辑过滤条件窗口
	 * 
	 * @param {}
	 *            tr
	 * @param {}
	 *            filter
	 */
	editFilterDialog : function(tr, filter) {
		var _self = this,
			tableName = $('#tableName').val();
		this.filterDialog({
			tableName : tableName,
			filter : filter,
			callback : function(rtn) {
				if (rtn) {
					var type =rtn.type;
					var condition = (type==2?rtn.condition:JSON2.stringify(rtn.condition));
					$("[var='name']", tr).html(rtn.name);
					$("[var='key']", tr).html(rtn.key);
					$("[var='type']", tr).val(type);
					$("[var='typeshow']", tr).html(type==2?'SQL':'条件脚本');
					$("[var='condition']", tr).val(condition);
				}
			}
		});
	},
	// =====================通用的处理方法==============================================================================
	/**
	 * 切换分页
	 */
	switchNeedPage : function(obj) {
		var me = $(obj), pagSize = $("#spanPageSize");
		var isNeedPage = me.val();
		if (isNeedPage == 1) {
			pagSize.show();
		} else {
			pagSize.hide();
		}
	},
	/**
	 * 删除TR
	 */
	delTr : function(obj) {
		$(obj).closest("tr").remove();
	},
	/**
	 * 删除选择的tr
	 * 
	 * @param {}
	 *            obj 选择的tr的ID
	 */
	delSelectTr : function(obj) {
		var _self = this;
		$("#" + obj + " input:[name='select']:checked").each(function() {
			_self.delTr(this);
		});
	},
	/**
	 * 上下移动
	 * 
	 * @param {}
	 *            obj
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
	/**
	 * 选择脚本
	 * 
	 * @param {}
	 *            obj
	 */
	selectScript : function(obj, isNext) {
		var linkObj = $(obj), txtObj = {};
		if (isNext)
			txtObj = linkObj.next()[0];
		else
			txtObj = linkObj.prev()[0];
		if (txtObj) {
			ScriptDialog({
				callback : function(script) {
					$.insertText(txtObj, script);
				}
			});
		}
	},
	/**
	 * 处理选择字段赋值
	 * 
	 * @param {}
	 *            obj
	 */
	handSelect : function(obj) {
		$(obj).delegate("select[var='name']", "change", function() {
			var me = $(this), text = me.children('option:selected').text();
			tr = me.closest("tr");
			var desc = $("[var='desc']", tr);
			desc.val(text);
		});
	}
};

var __BusQueryRule__ = new BusQueryRuleEdit();// 默认生成一个对象

