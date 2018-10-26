var SysQuerySqlEdit = function() {

}
SysQuerySqlEdit.prototype = {
	/**
	 * 初始化
	 */
	init : function() {
		var _self = this;
		this.initUrl();
	},
	// =====================设置url==============================================================================

	initUrl : function() {
		var tr = $($("#urlTemplate .table-detail tr")[0]).clone(true, true);
		var urlParams = $('#urlParams').val();
		if ($.isEmpty(urlParams))
			return;
		var urlParamsJson = $.parseJSON(urlParams);
		for ( var i = 0, c; c = urlParamsJson[i++];) {
			$("input[var='name']", tr).val(c.name);
			$("input[var='desc']", tr).val(c.desc);
			$("input[var='urlPath']", tr).val(c.urlPath);
			$("#setUrlParams", tr).val(c.urlParams);
			var tr1 = tr.clone(true, true);
			$("#urlTbl tbody").append(tr1);
		}
	},

	/**
	 * 添加url
	 */
	addUrl : function() {
		var tr = $($("#urlTemplate .table-detail tr")[0]).clone(true, true);
		$("#urlTbl tbody").append(tr);
	},
	/**
	 * 删除url
	 */
	delUrl : function() {
		this.delSelectTr("urlTbl");
	},
	/**
	 * 新增或编辑url参数
	 */
	addOrEditUrlParams : function(obj) {
		var tr = $(obj).parents("tr");
		var setUrlParams = $("[var='setUrlParams']", tr).val();
		this.addOrEditUrlParamsDialog(tr, setUrlParams)
	},
	/**
	 * 新增或编辑url参数窗口
	 * 
	 * @param {}
	 *            tr
	 * @param {}
	 *            filter
	 */
	addOrEditUrlParamsDialog : function(tr, urlParams) {
		var _self = this;
		this.urlParamsDialog({
			urlParams : urlParams,
			callback : function(rtn) {
				$("[var='setUrlParams']", tr).val(rtn);
			}
		});
	},
	/**
	 * url参数窗口
	 * 
	 * @param {}
	 *            conf
	 */
	urlParamsDialog : function(conf) {
		var dialogWidth = 900;
		var dialogHeight = 500;
		conf = $.extend({}, {
			dialogWidth : dialogWidth,
			dialogHeight : dialogHeight,
			help : 0,
			status : 0,
			scroll : 1,
			center : 1
		}, conf);
		var winArgs = "dialogWidth=" + conf.dialogWidth + "px;dialogHeight="
				+ conf.dialogHeight + "px;help=" + conf.help + ";status="
				+ conf.status + ";scroll=" + conf.scroll + ";center="
				+ conf.center;
		var id = $("#id").val();
		var url = __ctx + "/platform/system/sysQuerySql/urlParamsDialog.ht?id=" + id;
		url = url.getNewUrl();
		var that =this;
		/*KILLDIALOG*/
		DialogUtil.open({
			height:conf.dialogHeight,
			width: conf.dialogWidth,
			title : 'url参数窗口',
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
	// =====================设置url参数==============================================================================
	initUrlParamsData : function(conf) {
		var urlParams = conf.urlParams;
		if ($.isEmpty(urlParams))
			return;
		var urlParamsJson = $.parseJSON(urlParams);
		for ( var i = 0, c; c = urlParamsJson[i++];) {
			var tr = $($("#urlParamsTemplate .table-detail tr")[0]).clone(true,
					true);
			// 1 表示自定义参数，0 表示系统参数
			var isCustomParam = c.isCustomParam;
			$("input[var='key']", tr).attr("value", c.key);
			if (isCustomParam == 1) {
				$("input[var='isCustomParam']", tr).attr("checked", "checked");
				$("input[var='value']", tr).attr("value", c.value);
			} else {
				if (typeof $("input[var='isCustomParam']", tr).attr("checked") != 'undefined') {
					$("input[var='isCustomParam']", tr).removeAttr("checked");
				}
				this.initSelectTd(tr, c.value);
			}
			$("#urlParamsTbl tbody").append(tr);
		}
	},
	/**
	 * 初始化下拉框
	 */
	initSelectTd : function(tr, value) {
		var selectTemplate = $("#selectField").clone(true, true);
		$("td[var='valueTd']", tr).html(selectTemplate);
		$("#selectField", tr).val(value);
	},

	/**
	 * 改value的Td
	 */
	changeTd : function(obj) {
		if (typeof $(obj).attr("checked") == 'undefined') {
			this.addSelectTd(obj);
		} else {
			this.addTextTd(obj);
		}

	},
	/**
	 * 改变为下拉框
	 */
	addSelectTd : function(obj) {
		var selectTemplate = $("#selectField").clone(true, true);
		var $tr = $(obj).closest("tr");
		$("td[var='valueTd']", $tr).html(selectTemplate);
	},
	/**
	 * 改变为文本框
	 */
	addTextTd : function(obj) {
		var valueTemplate = $("#valueTemplate").clone(true, true);
		var $tr = $(obj).closest("tr");
		$("td[var='valueTd']", $tr).html(valueTemplate);
	},

	/**
	 * 添加url参数
	 */
	addUrlParams : function() {
		var tr = $($("#urlParamsTemplate .table-detail tr")[0]).clone(true,
				true);
		$("#urlParamsTbl tbody").append(tr);
	},
	/**
	 * 删除url参数
	 */
	delUrlParams : function() {
		this.delSelectTr("urlParamsTbl");
	},

	// =====================通用的处理方法==============================================================================
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
	}
};

var _SysQuerySqlEdit_ = new SysQuerySqlEdit();// 默认生成一个对象

// =====================定义对象==============================================================================

