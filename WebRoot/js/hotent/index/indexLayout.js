/*******************************************************************************
 * 
 * 系统首页布局设计js
 * 
 * <pre>
 *  
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2015-01-8-上午11:10:52
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 * 
 ******************************************************************************/
var indexLayoutPlugin = null;
;(function($, window, document, undefined) {

	var pluginName = "indexLayout",
	   defaults = {
			templateAliasKey:"template-alias"//模版别名
	   },
		contenthandle = null,
		layouthistory=null,
		layoutClass= ".index-layout",
		STORAGE_KEY ="indexLayoutData",
		downloadLayout="#download-layout",
		currentDocument = null,
		timerSave = 1000, stopsave = 0, startdrag = 0,
		indexHtml = $(layoutClass).html(), currenteditor = null;
	function Plugin(element, options) {
		this.settings = $.extend({}, defaults, options);
		this._defaults = defaults;
		this._name = pluginName;
		this.init();
	}

	Plugin.prototype = {
		init : function() {
			indexLayoutPlugin = this;
			this.initCkeditor();
			this.restoreData();
			this.initData();
			setInterval(function() {
				indexLayoutPlugin.handleSaveLayout();
			}, timerSave);
			
		},
		initData:function(){
			this.initSetting();
			this.initDraggable();
			// 初始化容器
			this.initContainer();
			//初始化操作
			this.initOperation();
			this.removeElm();
			this.gridSystemGenerator();
		},
		//初始化Ckeditor
		initCkeditor : function() {
			CKEDITOR.disableAutoInline = true;
			contenthandle = CKEDITOR.replace('contenteditor', {
				language : 'zh-cn',
				contentsCss : [ __ctx
						+ '/styles/common/css/bootstrap/bootstrap-combined.min.css' ],
				allowedContent : true
			});
		},
		//初始化恢复数据
		restoreData:function(){
//			if (this.supportstorage()) {
//				layouthistory = JSON.parse(localStorage.getItem(STORAGE_KEY));
//				if (!layouthistory)
//					return false;
//				window.indexHtml = layouthistory.list[layouthistory.count - 1];
//				if (window.indexHtml)
//					$(layoutClass).html(window.indexHtml);
//			}
			$(layoutClass).html( $('#html').val());
		},
		initSetting:function(){
			// 设置最小高度
			$("body").css("min-height", $(window).height() - 20);
			// 设置布局最小高度
			$(layoutClass).css("min-height", $(window).height() - 45);
			$('.sidebar-nav').css("height", $(window).height()-10);
			$('.sidebar-scroller').each(function() {
					$(this).slimScroll({
							size : '7px',
							color : '#a1b2bd',
							width:180,
							height :  $(window).height() - 40,
							alwaysVisible : ($(this).attr("data-always-visible") == "1" ? true: false),
							railVisible : ($(this).attr("data-rail-visible") == "1" ? true: false),
							railOpacity : 0.1,
							disableFadeOut : true
						});
				});
		},
		initDraggable:function(){
			var me = this;
			// 可以拖动 允许 添加的布局
			$(".sidebar-nav .lyrow").draggable({
				connectToSortable : layoutClass,
				helper : "clone",
				handle : ".drag",
				start : function(e, t) {
					if (!startdrag)
						stopsave++;
					startdrag = 1;
				},
				drag : function(e, t) {
					t.helper.width(400);
				},
				stop : function(e, t) {
					me.initSortable();
					if (stopsave > 0)
						stopsave--;
					startdrag = 0;
				}
			});
			//允许 添加的栏目
			$(".sidebar-nav .box").draggable({
				connectToSortable : ".column",
				helper : "clone",
				handle : ".drag",
				start : function(e, t) {
					if (!startdrag)
						stopsave++;
					startdrag = 1;
				},
				drag : function(e, t) {
					t.helper.width(400);
				},
				stop : function() {
					me.handleJsIds();
					if (stopsave > 0)
						stopsave--;
					startdrag = 0;
				}
			});
		},
		/**
		 * 初始化排序
		 */
		initSortable:function(){
			$(layoutClass+", "+layoutClass+" .column").sortable({
				connectWith : ".column",
				opacity : .35,
				start : function(e, t) {
					if (!startdrag)
						stopsave++;
					startdrag = 1;
				},
				stop : function(e, t) {
					if (stopsave > 0)
						stopsave--;
					startdrag = 0;
				}
			});
		},
		/**
		 * 初始化容器
		 */
		initContainer :function () {
			this.initSortable();
			this.configurationElm();
		},
		/**
		 * 处理编辑、下拉菜单配置
		 */
		configurationElm :function (e, t) {
			//可以编辑
			$(layoutClass).delegate(".configuration > button", "click", function(e) {
				e.preventDefault();
				var t = $(this).parent().next().next().children();
				$(this).toggleClass("active");
				t.toggleClass($(this).attr("rel"));
			});
			//处理下拉菜单
			$(layoutClass).delegate(".configuration .dropdown-menu a", "click",
					function(e) {
						e.preventDefault();
						var t = $(this).parent().parent();
						var n = t.parent().parent().next().next().children();
						t.find("li").removeClass("active");
						$(this).parent().addClass("active");
						var r = "";
						t.find("a").each(function() {
							r += $(this).attr("rel") + " ";
						});
						t.parent().removeClass("open");
						n.removeClass(r);
						n.addClass($(this).attr("rel"));
					});
		},
		/**
		 * 下载布局html
		 */
		downloadLayoutSrc:function () {
			var formatSrc = this.saveLayoutSrc();
			$("#downloadModal textarea").empty();
			$("#downloadModal textarea").val(formatSrc);
		},
		getLyrow:function (lyrow,len){
			var rtn = "";
			for (var i = 0; i < len; i++) {
				rtn += lyrow+" ";
			}
			return rtn;
		},
		/**
		 * 保存布局的源码
		 * @returns {}
		 */
		saveLayoutSrc:function (){
			var me = this;
			$(downloadLayout).children().html($(layoutClass).html());
			var t = $(downloadLayout).children();
			t.find(".preview, .configuration, .drag, .remove").remove();
			t.find(".lyrow").addClass("removeClean");
			t.find(".box-element").addClass("removeClean");
			var lyrow = ".lyrow";
			for (var i = 6; i >=0; i--) {
				var l = me.getLyrow(lyrow,i);
				t.find(l +".removeClean").each(function() {
					me.cleanHtml(this);
				});
			}
			t.find(".removeClean").remove();
			$(downloadLayout+" .column").removeClass("ui-sortable");
			$(downloadLayout+" .row-fluid").removeClass("clearfix").children()
					.removeClass("column");
			if ($(downloadLayout+" .container").length > 0) {
				me.changeStructure("row-fluid", "row");
			}
			formatSrc = $.htmlClean($(downloadLayout).html(), {
				format : true,
				allowedAttributes : [ [ "id" ], [ "class" ],["data-action"], [ "data-toggle" ],
						[ "data-target" ], [ "data-parent" ], [ "role" ],
						[ "data-dismiss" ], [ "aria-labelledby" ], [ "aria-hidden" ],
						[ "data-slide-to" ], [ "data-slide" ],
						[me._defaults.templateAliasKey]]
			});
			$(downloadLayout).html(formatSrc);
			return formatSrc;
		},
		/**
		 * 处理保存管理布局
		 */
		saveRemoteLayout:function () {
			var  name = $('#name').val();
			if(name == ""){
				BootstrapDialog.alert("请输入名称！");
				return;
			}
			$("#saveRemoteLayout").attr("disabled","disabled");
				
			//需要保存2套，一套是展示的模版，一套是设计的模版
			var formatSrc = this.saveLayoutSrc(),
				html = $(formatSrc).clone(true),
				designHtml =  $(layoutClass).clone(true);
			
			this.changeHtml(html);
			this.changeHtml(designHtml);
			var isDef  =$("input[name='isDef']:checked").val();
			$.ajax({
				type : "POST",
				url : __ctx+"/platform/system/sysIndexLayoutManage/saveLayout.ht",
				data : {
					id:$('#id').val(),
					name:$('#name').val(),
					memo:$('#memo').val(),
					orgId:$('#orgId').val(),
					isDef:isDef,
					html : html.html(),
					designHtml:	designHtml.html()
				},
				success : function(data) {
					var obj = eval('(' + data + ')');
					if (obj.result == 1) {
						BootstrapDialog.confirm("保存成功,是否继续操作？", function(rtn){
							if(rtn){
									window.location.href=__ctx+"/platform/system/sysIndexLayoutManage/design.ht?id="+obj.message;
							}else{
								if(window.opener){
									try{
										window.opener.location.href=window.opener.location.href.getNewUrl();
									}catch(e){}
								}	
								window.close();
							}
						});
					} else {
						BootstrapDialog.error(obj.message,"提示信息");
					}
					
				}
			});
			
		},
		/**
		 * 保存我的布局
		 */
		saveRemoteMyLayout:function(){
			$("#button-my-save-modal").attr("disabled","disabled");
			//需要保存2套，一套是展示的模版，一套是设计的模版
			var formatSrc = this.saveLayoutSrc(),
				html = $(formatSrc).clone(true),
				designHtml =  $(layoutClass).clone(true);
			
			this.changeHtml(html);
			this.changeHtml(designHtml);
			$.ajax({
				type : "POST",
				url : __ctx+"/platform/system/sysIndexMyLayout/saveLayout.ht",
				data : {
					html : html.html(),
					designHtml:	designHtml.html()
				},
				success : function(data) {
					var obj = eval('(' + data + ')');
					if (obj.result == 1) {
						BootstrapDialog.confirm("保存成功,是否继续操作？", function(rtn){
							if(rtn){
									window.location.href=__ctx+"/platform/system/sysIndexMyLayout/design.ht";
							}else{
								if(window.opener){
									try{
										window.opener.location.href=window.opener.location.href.getNewUrl();
									}catch(e){}
								}	
								window.close();
							}
						});
					} else {
						$("#button-my-save-modal").attr("disabled","");
						BootstrapDialog.error(obj.message,"提示信息");
					}
					
				}
			});
		},
		cleanHtml:function (e) {
			$(e).parent().append($(e).children().html());
		},
		changeHtml :function (html){
			var  me = this, templateAliasKey= me._defaults.templateAliasKey;
			html.find("["+templateAliasKey+"]").each(function(){
				var self = $(this),
					alias = self.attr(templateAliasKey),
					div =	$("<div></div>").attr(templateAliasKey,alias);
				self.after(div);
				self.remove();
			});
		},
		/**
		 * 是否支持保存数据 localStorage
		 * 
		 * @returns {Boolean}
		 */
		supportstorage:function () {
			if (typeof window.localStorage == 'object')
				return true;
			else
				return false;
		},

		/**
		 * 处理保存布局
		 */
		handleSaveLayout:function () {
			var e = $(layoutClass).html();
			if (!stopsave && e != window.indexHtml) {
				stopsave++;
				window.indexHtml = e;
				this.saveLayout();
				stopsave--;
			}
		},
		/**
		 * 保存布局
		 */
		saveLayout:function () {
			var data = layouthistory;
			if (!data) {
				data = {};
				data.count = 0;
				data.list = [];
			}
			if (data.list.length > data.count) {
				for (var i = data.count; i < data.list.length; i++)
					data.list[i] = null;
			}
			data.list[data.count] = window.indexHtml;
			data.count++;
			if (this.supportstorage()) {
				localStorage.setItem(STORAGE_KEY, JSON.stringify(data));
			} else {// 不支持本地保存可以考虑保存服务器
				/*
				 * $.ajax({ type: "POST", url: "/build/saveLayout", data: { layout:
				 * $(layoutClass).html() }, success: function(data) {
				 * //updateButtonsVisibility(); } });
				 */
			}
			layouthistory = data;
			//console.info(data);

		},
		/**
		 *  清除布局
		 */
		clearIndex:	function () {
			$(layoutClass).empty();
			layouthistory = null;
			if (this.supportstorage())
				localStorage.removeItem(STORAGE_KEY);
		},
		/**
		 * 删除按钮激活样式
		 */
		removeMenuClasses:function () {
			$("#menu-layoutit li button").removeClass("active");
		},

		changeStructure:function (e, t) {
			$(downloadLayout+" ." + e).removeClass(e).addClass(t);
		},
		/**
		 * 清除布局
		 */
		removeElm :function () {
			var me =this;
			$(layoutClass).delegate(".remove", "click", function(e) {
				e.preventDefault();
				$(this).parent().remove();
				if (!$(layoutClass+" .lyrow").length > 0) {
					me.clearIndex();
				}
			});
		},
		/**
		 * 撤销布局
		 * @returns {Boolean}
		 */
		undoLayout:function () {
			var data = layouthistory;
			// console.log(data);
			if (data) {
				if (data.count < 2)
					return false;
				window.indexHtml = data.list[data.count - 2];
				data.count--;
				$(layoutClass).html(window.indexHtml);
				if (this.supportstorage()) {
					localStorage.setItem(STORAGE_KEY, JSON.stringify(data));
				}
				return true;
			}
			return false;
			/*
			 * $.ajax({ type: "POST", url: "/build/getPreviousLayout", data: { },
			 * success: function(data) { undoOperation(data); } });
			 */
		},
		/**
		 * 重做布局
		 * @returns {Boolean}
		 */
		redoLayout:function () {
			var data = layouthistory;
			if (data) {
				if (data.list[data.count]) {
					window.indexHtml = data.list[data.count];
					data.count++;
					$(layoutClass).html(window.indexHtml);
					if (this.supportstorage()) {
						localStorage.setItem(STORAGE_KEY, JSON.stringify(data));
					}
					return true;
				}
			}
			return false;
			/*
			 * $.ajax({ type: "POST", url: "/build/getPreviousLayout", data: { },
			 * success: function(data) { redoOperation(data); } });
			 */
		},
		/**
		 * 处理js的ID
		 */
		handleJsIds:function () {
			this.handleModalIds();
			this.handleAccordionIds();
			this.handleCarouselIds();
			this.handleTabsIds();
		},
		handleAccordionIds:function () {
			var me = this,
				e = $(layoutClass+" #myAccordion"),
				t = me.randomNumber(),
				n = "accordion-" + t,
				r;
			e.attr("id", n);
			e.find(".accordion-group").each(function(e, t) {
				r = "accordion-element-" +  me.randomNumber();
				$(t).find(".accordion-toggle").each(function(e, t) {
					$(t).attr("data-parent", "#" + n);
					$(t).attr("href", "#" + r);
				});
				$(t).find(".accordion-body").each(function(e, t) {
					$(t).attr("id", r);
				});
			});
		},
		handleCarouselIds:function () {
			var e = $(layoutClass +" #myCarousel"),
				t = this.randomNumber(),
				n = "carousel-" + t;
			e.attr("id", n);
			e.find(".carousel-indicators li").each(function(e, t) {
				$(t).attr("data-target", "#" + n);
			});
			e.find(".left").attr("href", "#" + n);
			e.find(".right").attr("href", "#" + n);
		},
		handleModalIds:function () {
			var me = this,
				e = $(layoutClass+" #myModalLink"),
				t = me.randomNumber(),
				n = "modal-container-" + t,
				r = "modal-" + t;
			e.attr("id", r);
			e.attr("href", "#" + n);
			e.next().attr("id", n);
		},
		handleTabsIds:function () {
			var me = this,
				e = $(layoutClass+" #myTabs"),
				t = me.randomNumber(),
				n = "tabs-" + t;
			e.attr("id", n);
			e.find(".tab-pane").each(function(e, t) {
					var n = $(t).attr("id");
					var r = "panel-" + me.randomNumber();
					$(t).attr("id", r);
					$(t).parent().parent().find("a[href=#" + n + "]").attr("href",
							"#" + r);
				});
		},
		// 随机一个1到
		randomNumber:function () {
			return this.randomFromInterval(1, 1e6);
		},
		randomFromInterval: function (e, t) {
			return Math.floor(Math.random() * (t - e + 1) + e);
		},
		gridSystemGenerator:function () {
			$(".lyrow .preview input").bind("keyup", function() {
				var e = 0, 
					t = "",
					n = $(this).val().split(" ", 12);
				$.each(n, function(n, r) {
					e = e + parseInt(r);
					t += '<div class="span' + r + ' column"></div>';
				});
				if (e == 12) {
					$(this).parent().next().children().html(t);
					$(this).parent().prev().show();
				} else {
					$(this).parent().prev().hide();
				}
			});
		},
		selectTemplate:function(){
			var me = this;
			BootstrapDialog.show({
		        title : '选择模版',
		        size: BootstrapDialog.SIZE_WIDE,
		        url:__ctx+"/platform/system/sysIndexLayoutManage/selectTemplate.ht",
		        //回调函数
		        sucCall:function(rtn){
		        	$(layoutClass).html(rtn);
		        	me.initData();
		        	indexPagePlugin.initUIEvent();
		        }
			});
		},
		//删除布局
		deleteLayout:function(id){
			BootstrapDialog.confirm("该操作是删除自己的布局，首页的布局取默认的布局。是否删除当前设计的布局？", function(rtn){
				if(rtn){
					
					$.ajax({
						type : "POST",
						url : __ctx+"/platform/system/sysIndexMyLayout/deleteLayout.ht",
						data : {
							id : id
						},
						success : function(data) {
							var obj = eval('(' + data + ')');
							if (obj.result == 1) {
								BootstrapDialog.alert("删除成功！", function(){
									window.location.href=__ctx+"/platform/system/sysIndexMyLayout/design.ht";
								});
							} else {
								BootstrapDialog.error(obj.message,"提示信息");
							}
							
						}
					});
					
				}
			})
		},
		
		/**
		 * 初始化相关按钮操作
		 */
		initOperation:function(){
			var me = this;
			//保存编辑器内容
			$("#savecontent").unbind("click").bind("click",function(e) {
				e.preventDefault();
				currenteditor.html(contenthandle.getData());
			});
			
			//下载（预览）
			$("[data-target=#downloadModal]").unbind("click").bind("click",function(e) {
				e.preventDefault();
				me.downloadLayoutSrc();
			});
			$("[data-target=#saveMyModal]").unbind("click").bind("click",function(e) {
				e.preventDefault();
				me.saveRemoteMyLayout();
			});
			
			//保存内容
			$("#saveRemoteLayout").unbind("click").bind("click",function(e) {
				e.preventDefault();
				me.saveRemoteLayout();
			});
			
			//下载
			$("#download").unbind("click").bind("click",function() {
				me.downloadLayout();
				return false;
			});
			
			$("#downloadhtml").unbind("click").bind("click",function() {
				me.downloadHtmlLayout();
				return false;
			});
			
			//编辑
			$("#edit").unbind("click").bind("click",function() {
				$("body").removeClass("devpreview sourcepreview");
				$("body").addClass("edit");
				me.removeMenuClasses();
				$(this).addClass("active");
				return false;
			});
			$("#clear").unbind("click").bind("click",function(e) {
				e.preventDefault();
				BootstrapDialog.confirm("是否清空布局？", function(rtn){
					if(rtn)
						me.clearIndex();
				});
			});
			$("#devpreview").unbind("click").bind("click",function() {
				$("body").removeClass("edit sourcepreview");
				$("body").addClass("devpreview");
				me.removeMenuClasses();
				$(this).addClass("active");
				return false;
			});
			$("#sourcepreview").unbind("click").bind("click",function() {
				$("body").removeClass("edit");
				$("body").addClass("devpreview sourcepreview");
				me.removeMenuClasses();
				$(this).addClass("active");
				return false;
			});
			
			//自适应宽度
			$("#fluidPage").unbind("click").bind("click",function(e) {
				e.preventDefault();
				me.changeStructure("container", "container-fluid");
				$("#fixedPage").removeClass("active");
				$(this).addClass("active");
				me.downloadLayoutSrc();
			});
			//固定宽度
			$("#fixedPage").unbind("click").bind("click",function(e) {
				e.preventDefault();
				me.changeStructure("container-fluid", "container");
				$("#fluidPage").removeClass("active");
				$(this).addClass("active");
				me.downloadLayoutSrc();
			});
			
			$(".nav-header").unbind("click").bind("click",function() {
				$(".sidebar-nav .boxes, .sidebar-nav .rows").hide();
				$(this).next().slideDown();
			});
			//撤销
			$('#undo').unbind("click").bind("click",function() {
				stopsave++;
				if (me.undoLayout())
					me.initContainer();
				stopsave--;
			});
			//重做
			$('#redo').unbind("click").bind("click",function() {
				stopsave++;
				if (me.redoLayout())
					me.initContainer();
				stopsave--;
			});
			
			//设计模式的编辑
			$('body.edit '+layoutClass).unbind("click").bind("click","[data-target=#editorModal]",function(e) {
				e.preventDefault();
				currenteditor = $(this).parent().parent().find('.view');
				var eText = currenteditor.html();
				contenthandle.setData(eText);
			});
			
			$("#template").unbind("click").bind("click",function() {
				me.selectTemplate();
			});
			//删除我设计的布局
			$('#button-delete-modal').unbind("click").bind("click",function() {
				me.deleteLayout($(this).attr('var'));
			});
		}

	};

	$.fn[pluginName] = function(options) {
		return this
				.each(function() {
					if (!$.data(this, "plugin_" + pluginName)) {
						$.data(this, "plugin_" + pluginName, new Plugin(this,
								options));
					}
				});
	};

})(jQuery, window, document);

$(document).ready(function() {
	$('body').indexLayout();
});

/**
 * 调整
 */
$(window).resize(function() {
	indexLayoutPlugin.initSetting();
});

function selectOrg(){
	var conf ={
			isSingle:true,
			callback:function(orgId,orgName){
				$('#orgId').val(orgId);
				$('#orgName').val(orgName);
			}
		};
	
	if(!conf.isSingle)conf.isSingle=false;

	var scope={};
	if(conf.scope) scope = (conf.scope).replace(/\'/g, '"');
	
	var url=__ctx + '/platform/system/sysOrg/dialog.ht?isSingle=' + conf.isSingle;
	url=url.getNewUrl();
	
	var that =this;
	BootstrapDialog.show({
        title : '组织选择器',
        size: BootstrapDialog.SIZE_WIDE,
        url:url,
        scope : scope,
        //回调函数
        sucCall:function(rtn){
        	conf.callback.call(that,rtn.orgId,rtn.orgName);
        }
	});
}
