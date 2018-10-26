if (typeof FlowUtil == 'undefined') {
	AutoComplete = {};
}
/**
 * 以别名alias为键，缓存该别名所搜索过的数据
 */
var cache = {};
var matchPattern =new RegExp('\\[[^\\]]+\\]','g');
/**
 * 需要引用的样式及js<br>
 * &lt;link rel="stylesheet" href="${ctx}/js/jquery-ui-portlet/lib/themes/jquery-ui.css"&gt;<br>
 * &lt;script type="text/javascript" src="${ctx}/js/jquery-ui-portlet/lib/jquery-1.8.3.js"&gt;&lt;/script&gt;<br>
 * &lt;script src="${ctx}/js/jquery-ui-portlet/lib/jquery.ui.core.js"&gt;&lt;/script&gt;<br>
 * &lt;script src="${ctx}/js/jquery-ui-portlet/lib/jquery.ui.widget.js"&gt;&lt;/script&gt;<br>
 * &lt;script src="${ctx}/js/jquery-ui-portlet/lib/jquery.ui.position.js"&gt;&lt;/script&gt;<br>
 * &lt;script src="${ctx}/js/jquery-ui-portlet/lib/jquery.ui.menu.js"&gt;&lt;/script&gt;<br>
 * &lt;script src="${ctx}/js/jquery-ui-portlet/lib/jquery.ui.autocomplete.js"&gt;&lt;/script&gt;<br>
 * &lt;script type="text/javascript" src="${ctx}/js/util/json2.js"&gt;&lt;/script&gt;<br>
 * &lt;script src="${ctx}/js/hotent/platform/form/CommonDialog.js"&gt;&lt;/script&gt;<br>
 */
AutoComplete.init = function(){
	$('[autoQuery]').each(function(){
		var self = $(this);
		AutoComplete.handleInit(self);
	});
};

/**
 * 处理【自动完成】控件的初始化工作
 * @param obj	需要初始化的对象
 * <pre>
 * &lt;input id="test" type="text" autoQuery="{'alias':'cxyh', 'filter':'FULLNAME|ACCOUNT', 'format':'姓名：[fullname]，用户ID[userid]', 'render-format':'[account]', 'isCache':true}"&gt;
 * &lt;script&gt;
 * 	var testObj = $('#test');
 * 	autocomplete.handleInit(testObj);
 * &lt;/script&gt;
 * 
 * autoQuery参数介绍：
 * alias：自定义查询的别名（必需）
 * filter：对应自定义查询的条件字段，可传多个，以“|”分隔
 * format：格式化自定义查询返回的结果，格式：[返回字段]（必需）
 * render-format：格式化下拉列表的显示结果，如果为空，则使用上述format属性进行格式化显示
 * isCache：是否使用js缓存返回的结果集
 * minLength：最小输入长度，默认为0（即时搜索）。比如设置minLength:2，则在输入至少2个字符时才会去发起数据请求
 * maxResult：最多返回的记录数，默认为10，即返回前10条记录
 * </pre>
 */
AutoComplete.handleInit = function(obj){
	var self = $(obj);
	var autoQuery = eval("("+self.attr('autoQuery')+")");
	if(!autoQuery) return;
	var opinion = {
		/**
		 * 使用format格式化返回结果
		 * @param item	数据
		 * @param format	格式化格式，默认使用autoQuery里的format属性：员工姓名-[fullname]
		 */
		getFormatItem: function(item, format){
			if(!format) format = autoQuery.format;
			if(!format) return "无格式化format属性";
			//返回的字段貌似都是小写
			format = format.toLowerCase();
			//使用全局定义的正则获取括号及内容
			var result = format.match(matchPattern);
			if(!result || result.length<1) return false;
			for(var i=0;i<result.length;i++) {
				var tmp = result[i].trim();
				var fieldName = tmp.replace(/[\[|\]]/g, '');
				format = format.replace(tmp, item[fieldName]);
			}
			return format;
		},
		source: function(request, response) {
			var alias = autoQuery.alias;
			var filter = autoQuery.filter;
			var filters = [];
			if(filter) {
				filters  = filter.split('|');
			}
			var term = request.term;
			var isCache = autoQuery.isCache;
			if (isCache){
				var aliasCache = cache[alias];
				if(aliasCache && term in aliasCache){
					response(aliasCache[term]);
					return;
				}
			}
			var queryObj = {};
			var filterValue = self.val();
			for(var i=0;i<filters.length;i++){
				queryObj[filters[i]] = filterValue;
			}
			var maxResult = autoQuery.maxResult;
			if(!maxResult) maxResult = 10;
		    var condition = {alias:alias,page:1,pagesize:maxResult,querydata:JSON2.stringify(queryObj)};
			DoQuery(condition,function(data){
				var dataErrors = data.errors;
				if(dataErrors){
					alert(dataErrors);
					return;
				}
				var dataList = data.list;
				if (isCache) {
					if(!cache[alias]) cache[alias] = {};
					cache[alias][term] = dataList;
				}
				response(dataList);
			});
		},
		focus: function(event, ui) {
			self.val(opinion.getFormatItem(ui.item));
			return false;
		},
		select: function(event, ui) {
			self.val(opinion.getFormatItem(ui.item));
			return false;
		}
	};
	var minLength = autoQuery.minLength;
	if(minLength && minLength>0){
		opinion.minLength = minLength;
	}
	self.autocomplete(opinion)
	.data("autocomplete")._renderItem = function(ul, item) {
		var format = autoQuery['render-format'];
		//如果render-format属性为空，则会直接使用format进行显示
		var result = opinion.getFormatItem(item, format);
		return $("<li>")
		.data("item.autocomplete", item)
		.append("<a>" + result + "</a>")
		.appendTo(ul);
	};
};
$(function(){
	AutoComplete.init();
})



