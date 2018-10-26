/**带有连接线的嵌套div插件**/
;(function($){
	var default_options = {			
			updateContent:null,       /**更新div中内容的事件**/
			lineColor:'#696969',	  /**连接线的线条颜色**/
			rule2json:null			  /**生成json数据的事件**/
		};
	
	var methods = {
		//初始化
		init: function(options) {
			var settings = $.extend({}, default_options, options || {});
	        return this.each(function () {
	            $(this).data("linkdivData", new $.LinkDivList(this, settings));
	        });
	    },
	    //添加一个新div
	    addDiv:function(data){
			this.data("linkdivData").addDiv(data);
			return this;
	    },
	    //移除选中的div
	    removeDiv:function(){
	    	this.data("linkdivData").removeDiv();
	    	return this;
	    },
	    //组合选中的div
	    assembleDiv:function(){
	    	this.data("linkdivData").assembleDiv();
	    	return this;
	    },
	    //拆分组合的div
	    splitDiv:function(){
	    	this.data("linkdivData").splitDiv();
	    	return this;
	    },
	    //获取数据
	    getData:function(){
	    	return this.data("linkdivData").getData(); 
	    }
	};
	
	$.LinkDivList = function($div,options){
		$div = $($div);
		//获取线条的span标签
		function getLineHtml(s){
			var span = $('<span class="line-span"></span>').css(s);
			return span;
		};

		//画线
		function drawLine(x0, y0, x1, y1, color) {
			var rs = [];
			if (y0 == y1) // 画横线
			{
				if (x0 > x1) {
					var t = x0;
					x0 = x1;
					x1 = t
				}
				var css = {top:y0,left:x0,width:Math.abs(x1 - x0),background:color,height:2};
				rs.push(getLineHtml(css));				
			} else if (x0 == x1) // 画竖线
			{
				if (y0 > y1) {
					var t = y0;
					y0 = y1;
					y1 = t
				}
				var css = {top:y0,left:x0,height:Math.abs(y1 - y0),background:color,width:2};
				rs.push(getLineHtml(css));
			} else {
				var lx = x1 - x0
				var ly = y1 - y0
				var l = Math.sqrt(lx * lx + ly * ly)				
				for ( var i = 0; i < l; i += 2) {
					var p = i / l;
					var px = parseInt(x0 + lx * p);
					var py = parseInt(y0 + ly * p);
					var css = {top:py,left:px,height:2,background:color,width:2};
					rs.push(getLineHtml(css));
				}				
			}
			return rs
		};

		//添加连接线
		function addLine(item,count,size){			
			var top = item.offset().top,
				left = item.offset().left,
				width = item.width(),
				height = item.height(),
				lineAry = [],
				color = options.lineColor;

			//线段①
			$.merge(lineAry,drawLine(left+width,top+height/2,left+width+40,top+height/2,color));

			if(count+1!=size){
				//线段②
				$.merge(lineAry,drawLine(left+width+40,top+height/2,left+width+40,top+height,color));	
			}
			if(count!=0){
				//线段③			
				$.merge(lineAry,drawLine(left+width+40,top+height/2,left+width+40,top,color));
			}			

			for(var i =0,c;c=lineAry[i++];){
				item.append(c);
			}
		};		

		//添加运算类型
		function addComputing(item){
			var select,	
				computing = item.attr("computing"),
				computingSelect = $("select.computing-select",item);
			if(computingSelect.length>0){
				select = computingSelect;
			}
			if(!computing){
				computing = "and";
			}

			var top = item.offset().top,
				left = item.offset().left,
				width = item.width(),
				height = item.height();

			if(!select){
				select = $('<select class="computing-select"><option value="and">且</option><option value="or">或</option></span>').val(computing);
			}

			select.css({top:top-15,left:left+width+12,width:42});
			item.append(select);
		};

		function showLinkLine(p){
			p.each(function(){
				var assem = $(this),					
					itemAry = assem.children("div.con-item"),
					size = itemAry.length,
					count = 0;
				
				itemAry.each(function(){
					var me = $(this);
					$("span.line-span",me).remove();
					if(size>=1){
						$("select.computing-select",me).remove();
					}
					if(size>1){
						addLine(me,count,size);	
					}
					if(count>0){
						addComputing(me);
					}					
					count++;
					//如果是组合条件，则递归显示 线条
					if(me.hasClass("con-branch")){
						showLinkLine(me);
					}
				});
			});
		};

		function bindEvent(){
			$div.delegate("span.label-span","click",function(e){
				stopBubulle(e);
				var parentDiv = $(this).parents("div.con-item");
				$(parentDiv[0]).toggleClass("con-item-selected");
			});
		};	

		//终止事件冒泡		 
		function stopBubulle(e){
			if(e && e.stopPropagation)
				e.stopPropagation();	
			else
				window.event.cancelBubble = true;
		};

		//添加
		function add_Div(data){
			var div = $('<div class="con-item con-leaf"></div>'),
				selectedBranch = $("div.con-item-selected.con-branch"),
				pdiv = $div;
			if(selectedBranch&&selectedBranch.length>0)
				pdiv = $(selectedBranch[0]);
			pdiv.append(div);
			updateSpan(pdiv);
			if(options.updateContent){
				options.updateContent(div,data);
			}
		};

		//删除
		function remove_Div(){
			$("div.con-item-selected").remove();
			updateSpan();
		};

		//组合
		function assemble_Div(){
			var selected = $("div.con-item-selected").toggleClass("con-item-selected"),				
				branch = $('<div class="con-item con-branch"><span class="label-span">组合规则</span></div>');
			$(selected[0]).before(branch);
			branch.append(selected);
			updateSpan();
		};

		//拆分
		function split_Div(){
			var selectedBranch = $("div.con-item-selected.con-branch").toggleClass("con-item-selected");

			selectedBranch.each(function(){
				var me = $(this);
				me.before(me.children("div.con-item")).remove();
			});
			updateSpan();
		};

		//更新线条和运算类型
		function updateSpan(p){
			if(!p)p = $div;
			showLinkLine(p);
		};
		
		//解析数据为div
		function initData2Div(ary,div){
			for(var i=0,c;c=ary[i++];){
				var itemDiv;
				if(c.branch){
					itemDiv = $('<div class="con-item con-branch"><span class="label-span">组合规则</span></div>');
					div.append(itemDiv);
					initData2Div(c.sub,itemDiv);
				}
				else{
					itemDiv = $('<div class="con-item con-leaf"></div>');
					div.append(itemDiv);
					if(options.updateContent){
						options.updateContent(itemDiv,c);
					}
				}
				if(c.compType){
					itemDiv.attr("computing",c.compType);
				}
			}
		};
		
		//获取数据
		function get_Data(p,json){
			if(!p)return;
			var items = p.children("div.con-item");
			items.each(function(){
				var me = $(this),
					jobject = {};
				if(me.hasClass("con-branch")){//组合规则
					var sub = [],
						compType = me.children("select.computing-select").val();
					jobject.branch = true;
					jobject.sub = sub;
					if(compType){
						jobject.compType = compType;
					}
					get_Data(me,sub);
				}
				else{
					if(options.rule2json){
						jobject =  options.rule2json(me);
					}
				}
				if(jobject)json.push(jobject);
			});
		};

	    this.addDiv = function(data) {
	    	add_Div(data);
	    };
	    
	    this.removeDiv = function() {
	    	remove_Div();
	    };
	    
	    this.assembleDiv = function() {
	    	assemble_Div();
	    };
	    
	    this.splitDiv = function() {
	    	split_Div();
	    };
	    
	    this.getData = function(){
	    	var json = [];
	    	get_Data($div,json);
	    	return json;
	    };
	    
	    bindEvent();
		if(options.data){
			initData2Div(options.data,$div);
		}
		updateSpan();
	};
	
	$.fn.linkdiv = $.fn.linkDiv = function(method){
		if(methods[method]) {
	        return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
	    } else {
	        return methods.init.apply(this, arguments);
	    }
	};
})(jQuery);