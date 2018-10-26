/**
 * 数据模板构造方法
 */
var DataTemplateEdit = function() {

}
// 属性及函数
DataTemplateEdit.prototype = {
	/**
	 * 初始化
	 */
	init : function() {
		var _self = this;
		this.initDisplaySetting();
		this.initConditionField();
		this.initSortField();
		this.initFilter();
		this.initExportField();
		this.initManageField();
		this.selectTr();

		// 绑定选择条件点击事件
		$("#selectCondition").click(function() {
			_self.selectCondition(_self)
		});
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

		var tr = $($("#displayFieldTemplate .table-detail tbody")[0]).clone(true,
				true);
		var displayFieldVal = $("#displayField").val();
		if ($.isEmpty(displayFieldVal))
			return;
		var displayField = $.parseJSON(displayFieldVal),
			sb = new StringBuffer(),
			fieldRightEl = $("[var='fieldRight']", tr),
			hideFieldRightEl = $('<td var="fieldRight" class="hidden"></td>');
		for (var i = 0, c; c = displayField[i++];) {
			$("[var='index']", tr).html(i);
			$("[var='name']", tr).html(c.name);
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
		var sb = new StringBuffer(),
			exportRightEl = $("[var='exportRight']", tr),
			hideFieldRightEl = $('<td var="exportRight" class="hidden"></td>');
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
					var rightHtml = this.getHtmlTd(k);
					if (k.s == 2) {
						if(!k.srid)
							exportRightEl.html(rightHtml);
						else{
							hideFieldRightEl.html(rightHtml);
							exportRightEl.after(hideFieldRightEl);
						}
					} 
				}

	
	
				sb.append(tr.html());
			}
			
		}
		$(tbl).append(sb.toString());
		
	},
	// =====================查询条件==============================================================================
	/**
	 * 初始化条件字段
	 */
	initConditionField : function() {
		var conditionFieldVal = $("#conditionField").val();
		if ($.isEmpty(conditionFieldVal))
			return;
		var conditionField = $.parseJSON(conditionFieldVal);
		for (var i = 0, c; c = conditionField[i++];) {
			var tr = this.constructConditionTr(c);
			$("#conditionTbl tbody").append(tr);
		}
	},
	/**
	 * 选择条件
	 * 
	 */
	selectCondition : function(_self) {

		var conditionType = $("input[name='conditionType']:checked").val();
		$("#condition-columnsTbl input:[name='select']:checked")
				.each(function() {
					var me = $(this), na = me.attr("fieldname"), ty = me
							.attr("fieldtype"), cm = me.attr("fielddesc"), ct = me
							.attr("controltype"),obj= $("#conditionTbl");
				if(_self.isExistName(obj,'name',na)){
					var condition = {
						na : na,
						ty : ty,
						op : 1,
						cm : cm,
						va : "",
						vf : 1,
						ct : ct,
						qt : _self.getQueryType(ty, 1)
					};
					var tr = _self.constructConditionTr(condition);
					$("#conditionTbl tbody").append(tr);
				}
				});

	},
	/**
	 * 构造条件的列
	 * 
	 * @param {}
	 *            condition
	 * @return {}
	 */
	constructConditionTr : function(condition) {

		var ty = condition.ty, na = condition.na, cm = condition.cm, op = condition.op, va = condition.va, vf = condition.vf;
		// 控件类型
		ct = condition.ct;
		// 查询类型
		qt = condition.qt;

		// 联合类型
		//var jtTd = this.constructTag("td");
		//var jtSelect = this.constructTag("select", {
		//	name : "jt"
		//}); // $("<select name='conditionJoinType'></select>");
		//jtSelect.append(this.constructOption(jt, "AND"));
		// jtSelect.append(this.constructOption(jt, "OR"));
		//jtTd.append(jtSelect);


		// 名称
		var naTd = this.constructTag("td", {"var":"name"}, na);

		// 注解
		var cmTd = this.constructTag("td");
		var cmInput = this.constructTag("input", {
			name : "cm",
			value : cm,
			type : "text",
			'class' : "inputText"
		});
		cmTd.append(cmInput);
		var hiddenInput = this.constructTag("input", {
			type : "hidden",
			value : JSON2.stringify(condition)
		});
		cmTd.append(hiddenInput);

		var ctTd = this.constructTag("td");
		var ctSelect = this.constructTag("select", {
			name : "ct",
			style : "width:70px;"
		});
		ctSelect = this.getCtSelect(ctSelect, ct, ty);
		ctTd.append(ctSelect);

		// 条件
		var opTd = this.constructTag("td");
		var opSelect = this.constructTag("select", {
			name : "op",
			style : "width:70px;"
		});
		opSelect = this.getOpSelect(opSelect, op, ty);

		opTd.append(opSelect);
		// 值来源
		var vfTd = this.constructTag("td");
		var vfSelect = this.constructTag("select", {
			name : "vf",
			onchange : "__DataTemplate__.selectValueFrom(this)"
		});
		vfSelect.append(this.constructOption(vf, 1, "表单输入"));
		// vfSelect.append(this.constructOption(vf, 2, "固定值"));
		// vfSelect.append(this.constructOption(vf, 3, "脚本"));
		// vfSelect.append(this.constructOption(vf, 4, "变量"));
		vfSelect.append(this.constructOption(vf, 5, "动态传入"));
		vfTd.append(vfSelect);
		// 值
		var vaTd = this.constructTag("td");
		var a = this.constructTag("a", {
			href : '#',
			name : 'btnScript',
			'class' : 'hide link var',
			title : '常用脚本',
			onclick : "__DataTemplate__.selectScript(this,true)"
		}, "常用脚本");
		var vaInput = {};
		if (vf == 1) {
			vaInput = this.constructTag("input", {
				name : "va",
				type : "text",
				'class' : "hide",
				readonly : "readonly"
			});
		} else if (vf == 2) {
			vaInput = this.constructTag("input", {
				name : 'va',
				type : 'text'
			});
		} else if (vf == 3) {
			vaInput = this.constructTag("textarea", {
				name : 'va'
			});
			a.show();
		}else if (vf == 5) {
			vaInput = this.constructTag("input", {
				name : "va",
				'class' : "hide",
			});
		}else {
			vaInput = this.constructTag("select", {
				name : "va"
			});
			var parameters = this.getParameters();
			for (var i = 0; i < parameters.length; i++) {
				p = parameters[i];
				vaInput.append(this.constructTag("option", {
					value : p.na
				}, p.cm + "(" + p.na + ")"));
			}
		}
		vaInput.val(va);
		vaTd.append(a);
		vaTd.append(vaInput);
		// 管理
		var manageTd = this.constructTag("td");
		var moveupA = this.constructTag("a", {
			href : "#",
			'class' : "link moveup",
			onclick : '__DataTemplate__.moveTr(this,true)'
		}, "");

		var movedownA = this.constructTag("a", {
			href : "#",
			'class' : "link movedown",
			onclick : '__DataTemplate__.moveTr(this,false)'
		}, "");
		var deleteA = this.constructTag("a", {
			href : "#",
			'class' : "link del",
			onclick : '__DataTemplate__.delTr(this)'
		}, "");

		manageTd.append(moveupA).append(movedownA).append(deleteA);

		var tr = this.constructTag("tr");
		tr.append(naTd).append(cmTd).append(ctTd).append(opTd)
				.append(vfTd).append(vaTd).append(manageTd);
		return tr;
	},
	// TODO
	getCtSelect : function(ctSelect, ct, ty) {
		var _self = this;
		if (ty == 'date') {
			ctSelect.append(_self.constructOption(ct, '15', '日期控件'));
		} else {
			$(controlList).each(function(i, d) {
				switch (d.key) {
					case '1' :
					case '2' :
					case '9' :
					case '10' :
					case '16' :
					case '21' :
						ctSelect
								.append(_self.constructOption(ct, '1', d.value));
						break;
					default :
						ctSelect.append(_self.constructOption(ct, d.key,
								d.value));
						break;
				}
			});
		}
		return ctSelect;
	},
	getOpSelect : function(opSelect, op, ty) {
		switch (ty) {
			case 'varchar' :
				opSelect.append(this.constructOption(op, "1", "="));
				opSelect.append(this.constructOption(op, "2", "!="));
				opSelect.append(this.constructOption(op, "3", "like"));
				//opSelect.append(this.constructOption(op, "4", "左like"));
				//opSelect.append(this.constructOption(op, "5", "右like"));
				break;
			case 'number' :
			case 'int' :
				opSelect.append(this.constructOption(op, "1", "="));
				opSelect.append(this.constructOption(op, "2", ">"));
				opSelect.append(this.constructOption(op, "3", "<"));
				opSelect.append(this.constructOption(op, "4", ">="));
				opSelect.append(this.constructOption(op, "5", "<="));
				break;
			case 'date' :
				opSelect.append(this.constructOption(op, "6", "日期范围"));
				opSelect.append(this.constructOption(op, "1", "="));
				opSelect.append(this.constructOption(op, "2", ">"));
				opSelect.append(this.constructOption(op, "3", "<"));
				opSelect.append(this.constructOption(op, "4", ">="));
				opSelect.append(this.constructOption(op, "5", "<="));
				break;
			default :
				opSelect.append(this.constructOption(op, "1", "="));
				opSelect.append(this.constructOption(op, "2", ">"));
				opSelect.append(this.constructOption(op, "3", "<"));
				opSelect.append(this.constructOption(op, "4", ">="));
				opSelect.append(this.constructOption(op, "5", "<="));
		}
		return opSelect;
	},
	/**
	 * 构造选项
	 */
	constructOption : function(cond1, cond2, text) {
		if (!text)
			text = cond2;
		var option = this.constructTag("option", {
			value : cond2
		}, text);
		if (cond1 == cond2)
			option.attr("selected", "selected");
		return option;
	},

	/**
	 * 值来源更改事件处理
	 */
	selectValueFrom : function(obj) {
		var tr = $(obj).closest("tr");
		var a = tr.find("a:[name='btnScript']");
		a.hide();
		var vf = $(obj).val();
		var valueInput;
		if (vf == 1) {
			valueInput = this.constructTag("input", {
				name : 'va',
				type : "text",
				'class' : "hide",
				readonly : "readonly"
			});
		} else if (vf == 2) {
			valueInput = this.constructTag("input", {
				name : 'va',
				type : "text"
			});
		} else if (vf == 3) {
			a.show();
			valueInput = this.constructTag("textarea", {
				name : "va"
			});
		} else {
			valueInput = this.constructTag("select", {
				name : "va"
			});
			var parameters = this.getParameters();
			for (var i = 0; i < parameters.length; i++) {
				var p = parameters[i];
				valueInput.append(this.constructTag("option", {
					value : p.na
				}, p.cm + "(" + p.na + ")"));
			}
		}
		tr.find("[name='va']").replaceWith(valueInput);
	},
	/**
	 * 取得参数设置
	 */
	getParameterSetting : function() {
		var setting = {
			fieldSetting : new Array(),
			conditionField : new Array(),
			parameters : new Array()
		};
		// get field setting
		// setting.fieldSetting= getFields();

		// 取条件字段
		// var conditions=getConditions();
		// setting.conditionField=conditions;
		// //取自定义变量
		// var parameters=getParameters();
		// setting.parameters=parameters;
		return setting;
	},
	/**
	 * 构造标签
	 */
	constructTag : function(name, props, text) {
		var tag = $("<" + name + "></" + name + ">");
		if (props) {
			for (var key in props) {
				tag.attr(key, props[key]);
			}
		}
		if (text) {
			tag.text(text);
		}
		return tag;
	},
	/**
	 * 返回查询条件
	 * 
	 * @param {}
	 *            type
	 * @param {}
	 *            op
	 */
	getQueryType : function(type, op) {
		var qt = "S";
		switch (type) {
			case 'varchar' :
				if (op) {
					switch (op) {
						case 1 :
						case 2 :
							qt = 'S';
							break
						case 3 :
							qt = 'SL';
							break
						case 4 :
							qt = 'SLL';
							break
						case 5 :
							qt = 'SLR';
							break
						default :
							qt = 'S';
							break
					}
				}
				break;
			case 'number' :
				qt = 'L';
				break;
			case 'int' :
				qt = 'N';
				break;
			case 'date' :
				if (op == 6)
					qt = 'DR';
				else 
					qt = 'DL';
				break;
			default :
				qt = 'S';
				break;
		}
		return qt;
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
				$("[var='source']", tr).val(c.source);
				// 修复jquery clone的下拉赋值的bug。

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

	// =====================管理列==============================================================================
	/**
	 * 初始化管理
	 */
	initManageField : function() {
		this.handChange('#manageTbl');
		this.handClick('#manageTbl');
		this.handSelect('#manageTbl');

		var tr = $($("#manageTemplate .table-detail tr")[0]).clone(true, true);
		var manageFieldVal = $("#manageField").val();
		if ($.isEmpty(manageFieldVal))
			return;
		var manageField = $.parseJSON(manageFieldVal);
		var sb = new StringBuffer(),
			manageRight = $("[var='manageRight']", tr),
			hideFieldRightEl = $('<td var="manageRight" class="hidden"></td>');

		for (var i = 0, c; c = manageField[i++];) {
			$("input[var='desc']", tr).val(c.desc);
			$("select[var='name']", tr).val(c.name);

			for (var j = 0, k; k = c.right[j++];) {
				var rightHtml = this.getHtmlTd(k);
				if(!k.srid)
					manageRight.html(rightHtml);
				else{
					hideFieldRightEl.html(rightHtml);
					manageRight.after(hideFieldRightEl);
				}
			}
			var tr1 = tr.clone(true, true);
			$("#manageTbl tbody").append(tr1);
		}

	},
	addManage : function() {
		var tr = $($("#manageTemplate .table-detail tr")[0]).clone(true, true);
		var rightHtml = this.getHtmlTd({type:"none"});
		$("[var='manageRight']", tr).html(rightHtml);
		$("#manageTbl tbody").append(tr);
	},
	delManage : function() {
		this.delSelectTr("manageTbl");
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
		if(typeof right == "object" && !right[0])
			right = [right];
		$("[var='name']", tr).html(name);
		$("[var='key']", tr).html(key);
		$("[var='type']", tr).val(type);
		$("[var='typeshow']", tr).html(type==2?'SQL':'条件脚本');
		$("[var='condition']", tr).val(condition);
		var filterRightEl = $("[var='filterRight']", tr),
			hideFieldRightEl = $('<td var="filterRight" class="hidden"></td>');
		for (var j = 0, k; k = right[j++];) {
			var rightHtml  = this.getHtmlTd(k);
			if(!k.srid)
				filterRightEl.html(rightHtml);
			else{
				hideFieldRightEl.html(rightHtml);
				filterRightEl.after(hideFieldRightEl);
			}
		}
		var tr1 = tr.clone(true, true);
		$("#filterTbl tbody").append(tr1);
	},
	/**
	 * 增加过滤条件
	 */
	addFilter : function(conf) {
		var _self = this, tableId = $('#tableId').val(), source = $('#source')
				.val(), tr = $($("#filterTemplate .table-detail tr")[0]).clone(
				true, true);
		var right = {};
		right.type = 'none';
		right.id = '';
		right.name = '';
		right.script = '';
		this.filterDialog({
			tableId : tableId,
			source : source,
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
				+ "/platform/form/bpmDataTemplate/filterDialog.ht?tableId="
				+ conf.tableId;
		url = url.getNewUrl();

		/*var rtn = window.showModalDialog(url, conf, winArgs);

		if (rtn && conf.callback) {
			conf.callback.call(this, rtn);
		}*/
		/*KILLDIALOG*/
		var that =this;
		DialogUtil.open({
			height:conf.dialogHeight,
			width: conf.dialogWidth,
			title : '过滤的窗口',
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
			tableId = $('#tableId').val(),
			source = $('#source').val();
		this.filterDialog({
			tableId : tableId,
			source : source,
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

var __DataTemplate__ = new DataTemplateEdit();// 默认生成一个对象


/**
 * 控件类型。 16是隐藏域 4 用户单选,8,用户多选, 17,角色单选,5,角色多选, 18,组织单选,6,组织多选 19,岗位单选,7,岗位多选
 * 
 */
var controlList = [{
	key : '1',
	value : '单行文本框'
}, {
	key : '15',
	value : '日期控件'
}, {
	key : '3',
	value : '数据字典'
}, {
	key : '11',
	value : '下拉选项'
}, {
	key : '4',
	value : '人员选择器(单选)'
},  {
	key : '17',
	value : '角色选择器(单选)'
},  {
	key : '18',
	value : '组织选择器(单选)'
},  {
	key : '19',
	value : '岗位选择器(单选)'
}];