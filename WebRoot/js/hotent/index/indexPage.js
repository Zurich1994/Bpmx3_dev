var __es = null;
// Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
require.config({
	paths : {
		echarts : __ctx + '/js/echarts/'
	}
});

// Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
require([ 'echarts',
    'echarts/chart/bar',// 柱形图（纵向），堆积柱形图，条形图（横向），堆积条形图
	'echarts/chart/chord',// 和弦图。常用于展现关系数据，外层为圆环图，可体现数据占比关系，内层为各个扇形间相互连接的弦，可体现关系数据
	'echarts/chart/eventRiver',// 事件河流图。常用于展示具有时间属性的多个事件，以及事件随时间的演化。
	'echarts/chart/force',// 力导布局图。常用于展现复杂关系网络聚类布局。
	'echarts/chart/funnel',// 漏斗图。用于展现数据经过筛选、过滤等流程处理后发生的数据变化，常见于BI类系统。
	'echarts/chart/gauge',// 仪表盘。用于展现关键指标数据，常见于BI类系统。
	'echarts/chart/k',// K线图，蜡烛图。常用于展现股票交易数据。
	'echarts/chart/line', // 折线图，堆积折线图，区域图，堆积区域图。
	'echarts/chart/map',// 地图。内置世界地图、中国及中国34个省市自治区地图数据、可通过标准GeoJson扩展地图类型。支持svg扩展类地图应用，如室内地图、运动场、物件构造等。
	'echarts/chart/pie',// 饼图，圆环图。饼图支持两种（半径、面积）南丁格尔玫瑰图模式。
	'echarts/chart/radar',// 雷达图，填充雷达图。高维度数据展现的常用图表。
	'echarts/chart/scatter' // 散点图，气泡图。散点图至少需要横纵两个数据，更高维度数据加入时可以映射为颜色或大小，当映射到大小时则为气泡图
], function(ec) {
	__es = ec
});

/*******************************************************************************
 * 
 * 系统首页布局js
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
var indexPagePlugin = null;
;(function($, window, document, undefined) {

	var pluginName = "indexPage", isInit__ = false, defaults = {
		templateAliasKey : "template-alias",// 模版别名
		templateParams : "template-params",// 模版参数
		defHeight : 320
	// 默认高度
	}, me;
	function Plugin(element, options) {
		this.settings = $.extend({}, defaults, options);
		this._defaults = defaults;
		this._name = pluginName;
		this.init();
	}

	Plugin.prototype = {
		init : function() {
			indexPagePlugin = this;
			me = this;
			me.initUI();
			me.parser();
		},
		initUIEvent : function(el) {
			this.handleSlimScrolls(el);
			this.handleInfobox(el);
			this.handlePieChar(el);
			this.handleSparkline(el);
		},
		initUI : function() {
			// 处理顶部的按钮
			$(document).on('click','.widget-header a[data-action]',function(ev) {
				ev.preventDefault();
				var $this = $(this), $box = $this
						.closest('.widget-box');
				if ($box.length == 0)
					return;

				var $action = $this.data('action');
				if ($action == 'collapse') {// 展开
					var event_name = $box.hasClass('collapsed') ? 'show'
							: 'hide';
					me.toggle(event_name, $this, $box);
				} else if ($action == 'close') {// 关闭
					me.close($box);
				} else if ($action == 'reload') {// 刷新
					$this.blur();
					me.reload($this);
				} else if ($action == 'fullscreen') {// 全屏
					me.fullscreen($box, $this);
				} else if ($action == 'more') {// 更多
					me.more($this);
				} else if ($action == 'first') {// 首页
					me.firstPage($box);
				} else if ($action == 'prev') {// 上一页
					me.prevPage($box);
				} else if ($action == 'next') {// 下一页
					me.nextPage($box);
				} else if ($action == 'last') {//最后一页
					me.lastPage($box);
				} else if ($action == 'pageSize') {// 分页大小
					me.pageSize($box, $this);
				}
			});
		},
		/**
		 * 获取分页参数
		 */
		getTemplateParams : function($box) {
			var templateParams = me._defaults.templateParams,
				params = $box.attr(templateParams);
			if (!params) {
				alert("请配置参数。");
				return;
			} else {
				return eval("(" + params + ")");
			}
		},
		firstPage : function($box) {
			var params = me.getTemplateParams($box);
			if (!params)
				return;
			me.handerPage($box, 1);
		},
		prevPage : function($box) {
			var params = me.getTemplateParams($box);
			if (!params)
				return;
			var currentPage = params.currentPage;
			if (currentPage <= 1)
				return;
			me.handerPage($box, currentPage - 1);

		},
		nextPage : function($box) {
			var params = me.getTemplateParams($box);
			if (!params)
				return;
			var currentPage = params.currentPage, totalPage = params.totalPage;

			if (currentPage >= totalPage)
				return;
			me.handerPage($box, currentPage + 1);
		},
		lastPage : function($box) {
			var params = me.getTemplateParams($box);
			if (!params)
				return;
			me.handerPage($box, params.totalPage);
		},
		pageSize : function($box, $this) {
			var params = me.getTemplateParams($box);
			if (!params)
				return;
			me.handerPage($box, 1, $this.html());
		},
		handerPage : function($box, currentPage, pageSize) {
			var templateParams = me._defaults.templateParams, params = $box
					.attr(templateParams);
			params = eval("(" + params + ")");
			params.currentPage = currentPage;
			if (pageSize)
				params.pageSize = pageSize;
			$box.attr(templateParams, JSON.stringify(params));
			me.blockUI($box);
			window.setTimeout(function() {
				me.parserTemplate($box);
			}, 100);
		},
		/**
		 * 处理收起、展开
		 */
		toggle : function(type, button, $box) {
			var $body = $box.find('.widget-body').eq(0), $icon = null,
				event_name = typeof type !== 'undefined' ? type: ($box.hasClass('collapsed') ? 'show' : 'hide'),
				event_complete_name = event_name == 'show' ? 'shown': 'hidden';

			if (typeof button === 'undefined') {
				button = $box.find('> .widget-header a[data-action=collapse]')
						.eq(0);
				if (button.length == 0)
					button = null;
			}
			var $match, $icon_down = null, $icon_up = null, expandSpeed = 250, collapseSpeed = 200;
			if (button) {
				$icon = button.find(".ht-icon").eq(0);
				if (($icon_down = $icon.attr('data-icon-show'))) {
					$icon_up = $icon.attr('data-icon-hide')
				} else if ($match = $icon.attr('class').match(
						/fa\-(.*)\-(up|down)/)) {
					$icon_down = 'fa-' + $match[1] + '-down'
					$icon_up = 'fa-' + $match[1] + '-up'
				}
				expandSpeed = button.attr('data-expand-speed') ? button
						.attr('data-expand-speed') : expandSpeed;
				collapseSpeed = button.attr('data-collapse-speed') ? button
						.attr('data-collapse-speed') : collapseSpeed;
			}

			if (event_name == 'show') {
				if ($icon)
					$icon.removeClass($icon_down).addClass($icon_up);

				$body.hide();
				$box.removeClass('collapsed');
				$body.slideDown(expandSpeed);
			} else {
				if ($icon)
					$icon.removeClass($icon_up).addClass($icon_down);
				$body.slideUp(collapseSpeed, function() {
					$box.addClass('collapsed')
					$box.trigger(event_complete_name + '.ht.widget')
				});
			}
		},

		/**
		 * 处理刷新
		 */
		reload : function($this) {
			var el = $this.parents(".widget-box");
			me.blockUI(el);
			var alias = el.parent().find(
					"[" + me._defaults.templateAliasKey + "]");
			if (alias.length > 0) {
				window.setTimeout(function() {
					me.parserTemplate(el);
				}, 100);
			} else {
				me.unblockUI(el);
			}
		},

		/**
		 * 处理更多
		 */
		more : function($this) {
			var url = $this.attr('data-url');
			me.addTab(url);
		},
		/**
		 * 关闭
		 */
		close : function($box) {
			var closeSpeed = 300;
			$box.fadeOut(closeSpeed, function() {
				$box.parent().remove();
			});
		},
		/**
		 * 全屏
		 */
		fullscreen : function($box, $this) {
			var $icon = $this.find(".ht-icon").eq(0),
				$icon_expand = null,
				$icon_compress = null
			if (($icon_expand = $icon.attr('data-icon1'))) {
				$icon_compress = $icon.attr('data-icon2')
			} else {
				$icon_expand = 'fa-expand';
				$icon_compress = 'fa-compress';
			}
			if (!$box.hasClass('fullscreen')) {
				$icon.removeClass($icon_expand).addClass($icon_compress);
				$box.addClass('fullscreen');
			} else {
				$icon.addClass($icon_expand).removeClass($icon_compress);
				$box.removeClass('fullscreen');
			}
		},
		addTab : function(url) {
			if (!url || url == "")
				return;
			$.post(__ctx + "/platform/console/getResourceNode.ht?columnUrl="
					+ url, function(data) {
				if (data == null | data == "") {
					alert("更多路径配置有误", '提示信息');
				} else {
					me.addToTab(data.defaultUrl, data.resName, data.resId,
							data.icon);
				}
			});
		},
		// 添加到tab或者刷新
		addToTab : function(url, txt, id, icon) {
			me.getParent().tab.addTabItem({
				url : __ctx + url,
				text : txt,
				tabid : id,
				icon : __ctx + icon
			});
		},
		// 找到最顶层窗口
		getParent : function(w) {
			var win = w ? w : window;
			if (win.parent != win) // 找到最顶层窗口
				return this.getParent(win.parent);
			return win;
		},
		isEmpty : function(v, allowBlank) {
			return v === null || v === undefined
					|| (!allowBlank ? v === '' : false);
		},
		blockUI : function(el, loaderOnTop) {
			var html = '<div ><i class=" fa fa-spinner fa-spin fa-2x orange "></i>加载中...</div>';

			jQuery(el).block({
				message : html,
				css : {
					top : '10%',
					border : '0',
					padding : '0',
					backgroundColor : 'none'
				},
				overlayCSS : {
					backgroundColor : '#fff',
					opacity : 0.1,
					cursor : 'wait'
				}
			});
		},
		// wrapper function to un-block element(finish loading)
		unblockUI : function(el) {
			jQuery(el).unblock({
				onUnblock : function() {
					jQuery(el).removeAttr("style");
				}
			});
		},
		/**
		 * 走马灯效果
		 */
		handleCarousel : function(conf) {
			if (!jQuery().carouFredSel)
				return;
			var div = conf.div, data = conf.data ? conf.data : [], height = conf.height ? conf.height
					: me._defaults.defHeight, carouFredSel = $('.widget-list',
					div);
			if (carouFredSel.length <= 0)
				return;
			// Basic carousel, no options
			carouFredSel.each(function() {
				var self = $(this), direction = self
						.attr("data-direction") ? self
						.attr("data-direction") : 'up', // 滚动方向 left 、right、down、up
				duration = self.attr("data-duration") ? self.attr("data-duration") : 500, // 持续时间
				easing = self.attr("data-easing") ? self.attr("data-easing") : 'swing', // 展示效果swing（摆动）、linear、 cubic、quadratic
				pauseOnHover = self.attr("data-pauseOnHover") ? self.attr("data-pauseOnHover") : true;// 是否鼠标停止

				self.carouFredSel({
					direction : direction,
					height : height - 20,
					scroll : {
						easing : easing,
						duration : duration,
						pauseOnHover : pauseOnHover
					}
				});
			});

		},
		/**
		 * 处理幻灯片效果
		 */
		handleLayerslider : function(conf) {
			if (!jQuery().layerSlider)
				return;
			var div = conf.div, data = conf.data ? conf.data : [], height = conf.height ? conf.height
					: me._defaults.defHeight, layerslider = $(
					'.widget-layerslider', div);
			if (layerslider.length <= 0)
				return;

			layerslider.each(function() {
						var self = $(this),
							width = self.width() > 0 ? self.width() : me._defaults.defHeight,
							skinsPath = self.attr("data-skins-path") ?self.attr("data-skins-path"): (__ctx + '/styles/common/css/slider-layer-slider/skins/'),
							skin = self.attr("data-skin") ? self.attr("data-skin"): 'fullwidth', 
							thumbnailNavigation = self.attr("data-thumbnailNavigation") ? self
								.attr("data-thumbnailNavigation") : 'hover',
							hoverPrevNext = self.attr("data-hoverPrevNext") ?
									self.attr("data-hoverPrevNext") : false,
							responsive = self.attr("data-responsive") ?
									self.attr("data-responsive") : false,
							responsiveUnder = self.attr("data-responsiveUnder") ? 
									self.attr("data-responsiveUnder") : width,
							layersContainer = self.attr("data-layersContainer") ?
									self.attr("data-layersContainer"): width;
						self.layerSlider({
							skinsPath : skinsPath,
							skin : skin,
							thumbnailNavigation : thumbnailNavigation,
							hoverPrevNext : hoverPrevNext,
							responsive : responsive,
							responsiveUnder : responsiveUnder,
							layersContainer : layersContainer
						});
					});

		},
		/**
		 * 处理列表数据滚动
		 */
		handleSlimScrolls : function(el) {
			if (!jQuery().slimScroll)
				return;

			$('.widget-scroller', el).each(function() {
				var self = $(this),
					height = self.attr("data-height");
					self.slimScroll({size : '7px',
								color : '#a1b2bd',
								height : height ? height
										: (me._defaults.defHeight + 'px'),
								alwaysVisible : (self
										.attr("data-always-visible") == "1" ? true
										: false),
								railVisible : (self
										.attr("data-rail-visible") == "1" ? true
										: false),
								railOpacity : 0.1,
								disableFadeOut : true
							});
				});
		},
		/**
		 * 消息盒子
		 */
		handleInfobox : function(el) {
			$('.infobox[data-url]', el).unbind("click").bind("click",
					function() {
						var self = $(this),
							url = self.attr("data-url");
						me.addTab(url);
					});
		},
		/**
		 * 处理饼图
		 */
		handlePieChar : function(el) {
			if (!jQuery().easyPieChart)
				return;
			$('.easy-pie-chart.percentage', el).each(function() {
				var self = $(this),
					$box = self.closest('.infobox'), 
					barColor = self.data('color')|| (!$box.hasClass('infobox-dark') ? 
								$box.css('color'): 'rgba(255,255,255,0.95)'),
					trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)': '#E2E2E2',
					size = parseInt(self.data('size')) || 50;
				self.easyPieChart({
						barColor : barColor,
						trackColor : trackColor,
						scaleColor : false,
						lineCap : 'butt',
						lineWidth : parseInt(size / 10),
						animate : /msie\s*(8|7|6)/
								.test(navigator.userAgent
										.toLowerCase()) ? false
								: 1000,
						size : size
					});
			});
		},
		/**
		 * 处理信息盒子的图表
		 */
		handleSparkline : function(el) {
			if (!jQuery().sparkline)
				return;
			$('.sparkline', el).each(function() {
				var self = $(this), $box = self
						.closest('.infobox'),
					barColor = !$box.hasClass('infobox-dark') ? $box
						.css('color') : '#FFF',
					type = self.attr("data-type") ? $(this).attr("data-type") : 'bar';
				$(this).sparkline('html', {
					tagValuesAttribute : 'data-values',
					type : type,
					barColor : barColor,
					chartRangeMin : $(this).data('min') || 0
				});
			});
		},
		/**
		 * 处理日历
		 */
		handleCalendar : function(conf) {
			if (!jQuery().fullCalendar)
				return;
			var div = conf.div, data = conf.data ? conf.data : [], height = conf.height ? conf.height
					: me._defaults.defHeight, calendar = $('.widget-calendar',
					div);
			if (calendar.length <= 0)
				return;
			calendar.fullCalendar({
						header : {
							left : 'prev,next today',
							center : 'title',
							right : 'month,agendaWeek,agendaDay'
						},
						height : height,
						selectable : true,
						selectHelper : true,
						editable : true,
						eventLimit : true, // 更多
						views : {
							month : {
								eventLimit : 5,
								eventLimitClick : 'day'
							},
							agendaWeek : {
								eventLimit : 5
							},
							agendaDay : {
								eventLimit : 5
							}
						},
						eventClick : function(calEvent, jsEvent, view) {
							if (calEvent.eventClick) {
								calEvent.eventClick.call(this, calEvent,
										jsEvent, view);
							}
						},
						events : data
					});
		},
		/**
		 * 处理图表
		 */
		handleChart : function(conf) {
			var div = conf.div, data = conf.data, height = conf.height ? conf.height
					: me._defaults.defHeight, chartBody = div
					.find(".widget-chart");// 对图表布局class标签写死
			if (me.isEmpty(chartBody))// 如果没有自己解析图表
				return;
			var w = chartBody.width() > 0 ? chartBody.width()
					: me._defaults.defHeight, h = chartBody.height() > 0 ? chartBody
					.height()
					: height;
			chartBody.width(w);
			chartBody.height(h);
			try {
				// 准备好的dom，初始化echarts图表
				var myChart = __es.init(chartBody.get(0));
				// 为echarts对象加载数据
				myChart.setOption(data);
			} catch (e) {
				// TODO: handle exception
			}

		},
		/**
		 * 刷新页面
		 */
		refreshPage : function($this, delaytime) {
			window.setTimeout(function() {
				me.blockUI($this);
				me.parserTemplate($this);
			}, delaytime);
		},
		/**
		 * 解析模版
		 */
		parserTemplate : function(el) {
			var self = $(el),
				alias = self.attr(me._defaults.templateAliasKey),
				params = self
					.attr(me._defaults.templateParams), div = $("<div></div>");
			me.blockUI(el);
			if (!alias) {
				me.unblockUI(el);
				return;
			}
			$.ajax({
				type : "POST",
				url : __ctx
						+ "/platform/system/sysIndexColumn/getData.ht",
				data : {
					params : params ? params : '',
					alias : alias
				},
				success : function(data) {
					me.unblockUI(el);
					if (me.isEmpty(data))
						return;
					var rtn = eval('(' + data + ')'), templateHtml = rtn.html,
					// 栏目类型
					type = rtn.type,
					// 是否刷新
					isRefresh = rtn.isRefresh,
					// 刷新时间
					refreshTime = rtn.refreshTime, delayTime = refreshTime ? refreshTime: 5,
					// 展示效果。1、默认效果，走马灯
					show = rtn.show, height = rtn.height;// 高度

					// 有儿子就有bug TODO 暂时在它的父类添加div这样解决
					// 判断div的父类是否有元素
					var parent = self.parent();
					if (parent.hasClass('parent-template-class')) {
						div = parent;
					} else {
						div.addClass("parent-template-class");
						self.after(div);
					}
					self.remove();
					div.append(templateHtml);// 把当前页替换成模版的页面

					if (type == 1) {// 图表方式
						me.handleChart({
							div : div,
							data : rtn.option,
							height : height
						});
					} else if (type == 2) {// 日历方式
						me.handleCalendar({
							div : div,
							data : rtn.option,
							height : height
						});
					} else {// 默认方式
						if (show != 1)
							me.initUIEvent(div);
					}

					// 0、是默认；1、是走马灯、2、是幻灯片
					if (show && show == 1) {// 1、是走马灯
						me.handleCarousel({
							div : div,
							data : rtn.option,
							height : height
						});
					} else if (show && show == 2) {// 2、 幻灯片
						me.handleLayerslider({
							div : div,
							data : rtn.option,
							height : height
						});
					}
					// 处理刷新 页面关闭或者隐藏
					if (isRefresh && isRefresh == 1)
						me.refreshPage(div.children().first(),
								delayTime * 1000);
				},
				error : function(data) {
					me.unblockUI(el);
				}
			});

		},

		parser : function() {
			var indexPageTemplate = $('.index-page').find("[" + me._defaults.templateAliasKey + "]");
			if (indexPageTemplate.length > 0) {
				indexPageTemplate.each(function(i, n) {
					me.parserTemplate(n);
				});
			}
		}

	};

	$.fn[pluginName] = function(options) {
		return this.each(function() {
				if (!$.data(this, "plugin_" + pluginName)) {
					$.data(this, "plugin_" + pluginName, new Plugin(this,options));
				}
			});
	};

})(jQuery, window, document);

$(document).ready(function() {
	$('body').indexPage();
});

jQuery(window).load(function() {
	indexPagePlugin.initUIEvent($('body'));
});