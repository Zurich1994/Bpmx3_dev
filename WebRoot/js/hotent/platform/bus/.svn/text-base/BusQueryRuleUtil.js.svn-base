// 查询规则的帮助类
if (typeof BusQueryRuleUtil == 'undefined') {
	BusQueryRuleUtil = {};
}

/**
 * 编辑窗口
 * 
 * @param {}
 *            conf
 */
BusQueryRuleUtil.eidtDialog = function(conf) {
	var url = __ctx + '/platform/bus/busQueryRule/edit_' + conf.tableName
			+ '.ht', height = screen.availHeight - 35, width = screen.availWidth
			- 5;
	url = url.getNewUrl();
	$.openFullWindow(url);
}
/**
 * 保存过滤条件
 * 
 * @param {}
 *            conf
 */
BusQueryRuleUtil.saveFilter = function(conf) {
	var filterId = conf.filterFlag, tableName = conf.tableName, filterKey = conf.filterKey;
	var target = "";
	if (!$.isEmpty(filterId)) {
		var url = __ctx + "/platform/bus/busQueryFilter/get.ht";

		$.post(url, {
					id : filterId
				}, function(data) {
					var busQueryFilter = data.busQueryFilter;
					if (!$.isEmpty(busQueryFilter)) {
						target = '<div>'
								+ '<form id="__formSaveFilter__"><table class="table-detail" cellpadding="0" cellspacing="0" border="0">'
								+ '<tr><th style="width:113px;text-align:right;" nowrap="nowrap">过滤名称:</th>'
								+ '<td><input type="text" name="filterName" value="'
								+ busQueryFilter.filterName
								+ '" /></td></tr>'
								+ '<tr><th style="text-align:right;">描述:</th>'
								+ '<td><textarea  name="filterDesc" cols="1" rows="5"  style="width:200px">'
								+ busQueryFilter.filterDesc
								+ '</textarea></td></tr>' + '</table>'
								+ '</form></div>';

						$.ligerDialog.open({
									title : '保存过滤条件',
									target : target,
									width : 400,
									height : 250,
									buttons : [{
												text : '保存',
												onclick : function(item, dialog) {
													saveFilter(item, dialog,
															filterId);
												}
											}, {
												text : '另存为',
												onclick : saveFilter
											}, {
												text : '取消',
												onclick : function(item, dialog) {
													dialog.hide();
												}
											}]
								}).show();
					} else {

						target = '<div>'
								+ '<form id="__formSaveFilter__"><table class="table-detail" cellpadding="0" cellspacing="0" border="0">'
								+ '<tr><th style="width:113px;text-align:right;" nowrap="nowrap">过滤名称:</th>'
								+ '<td><input type="text" name="filterName"  /></td></tr>'
								+ '<tr><th style="text-align:right;">描述:</th>'
								+ '<td><textarea  name="filterDesc" cols="1" rows="5"  style="width:200px"></textarea></td></tr>'
								+ '</table>' + '</form></div>';

						$.ligerDialog.open({
									title : '保存过滤条件',
									target : target,
									width : 400,
									height : 250,
									buttons : [{
												text : '保存',
												onclick : saveFilter
											}, {
												text : '取消',
												onclick : function(item, dialog) {
													dialog.hide();
												}
											}]
								}).show();
					}
				});
	} else {
		target = '<div>'
				+ '<form id="__formSaveFilter__"><table class="table-detail" cellpadding="0" cellspacing="0" border="0">'
				+ '<tr><th style="width:113px;text-align:right;" nowrap="nowrap">过滤名称:</th>'
				+ '<td><input type="text" name="filterName"  /></td></tr>'
				+ '<tr><th style="text-align:right;">描述:</th>'
				+ '<td><textarea  name="filterDesc" cols="1" rows="5"  style="width:200px"></textarea></td></tr>'
				+ '</table>' + '</form></div>';

		$.ligerDialog.open({
					title : '保存过滤条件',
					target : target,
					width : 400,
					height : 250,
					buttons : [{
								text : '保存',
								onclick : saveFilter
							}, {
								text : '取消',
								onclick : function(item, dialog) {
									dialog.hide();
								}
							}]
				}).show();
	}

	/**
	 * 序列化查询参数
	 * 
	 * @param {}
	 *            form
	 * @return {}
	 */
	function serializeObject(form) {
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
	function setUrlParams(params, url) {
		if (url.indexOf("?") != -1) {
			var str = url.substr(1), strs = str.split("&");
			for (var i = 0; i < strs.length; i++) {
				params[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
			}
		}
		return params;
	}

	/**
	 * 保存
	 */
	function saveFilter(item, dialog, filterId) {
		var filterName = $("[name='filterName']", dialog.dialog), filterDesc = $(
				"[name='filterDesc']", dialog.dialog);
		var sorted = $(dialog.dialog).parent().find('.panel-table')
				.find('.sortable.sorted').find("a");
		if (filterName.val() == "") {
			$.ligerDialog.warn("请填写过滤名称!", "提示");
			return;
		}
		var params = serializeObject(searchForm);
		params.filterName = filterName.val();
		params.filterDesc = filterDesc.val();
		params.tableName = tableName;
		params.filterKey = filterKey;
		if (!$.isEmptyObject(sorted)) {
			var h = sorted.attr("href");
			if (!$.isEmpty(h))
				setUrlParams(params, h);
		}
		if (!$.isEmpty(filterId))
			params.filterId = filterId;
		var url = __ctx + "/platform/bus/busQueryFilter/saveFilter.ht";
		$.post(url, params, function(responseText) {
					var obj = new com.hotent.form.ResultMessage(responseText);
					if (obj.isSuccess()) {
						$.ligerDialog.success('保存成功!',
								'提示', function() {
									dialog.hide();
								});
					} else {
						$.ligerDialog.err('提示', '保存失败!',
								obj.getMessage());
					}
				});
	};

};

/**
 * 我的过滤器
 * 
 * @param {}
 *            conf
 */
BusQueryRuleUtil.myFilter = function(conf) {
	var url = __ctx + '/platform/bus/busQueryFilter/list.ht?tableName='
			+ conf.tableName + '&url=' + conf.url;

	url = url.getNewUrl();
	DialogUtil.open({
		height:400,
		width: 500,
		title : '我的过滤器',
		url: url, 
		isResize: true,
		data:{},
		//自定义参数
		sucCall:function(rtn){
			if (rtn != undefined)
				window.location.href = conf.url + rtn;
		}
	});
}

BusQueryRuleUtil.setting = function(conf) {
	var url = __ctx + '/platform/bus/busQuerySetting/edit.ht?tableName='+conf.tableName;

	url = url.getNewUrl();
	DialogUtil.open({
		height: 400,
		width: 500,
		title : '',
		url: url, 
		isResize: true,
		//自定义参数
		data: {},
		sucCall:function(rtn){
			if (rtn != undefined)
				window.location.href = location.href.getNewUrl();
		}
	});
}