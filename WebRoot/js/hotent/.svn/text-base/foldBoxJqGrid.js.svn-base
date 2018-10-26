/**
 * jquery.scrollFollow.js
 * Copyright (c) 2008 Net Perspective (http://kitchen.net-perspective.com/)
 * Licensed under the MIT License (http://www.opensource.org/licenses/mit-license.php)
 * 
 * @author R.A. Ray
 *
 * @projectDescription	jQuery plugin for allowing an element to animate down as the user scrolls the page.
 * 
 * @version 0.4.0
 * 
 * @requires jquery.js (tested with 1.2.6)
 * @requires ui.core.js (tested with 1.5.2)
 * 
 * @optional jquery.cookie.js (http://www.stilbuero.de/2006/09/17/cookie-plugin-for-jquery/)
 * @optional jquery.easing.js (http://gsgd.co.uk/sandbox/jquery/easing/ - tested with 1.3)
 * 
 * @param speed		int - Duration of animation (in milliseconds)
 * 								default: 500
 * @param offset			int - Number of pixels box should remain from top of viewport
 * 								default: 0
 * @param easing		string - Any one of the easing options from the easing plugin - Requires jQuery Easing Plugin < http://gsgd.co.uk/sandbox/jquery/easing/ >
 * 								default: 'linear'
 * @param container	string - ID of the containing div
 * 								default: box's immediate parent
 * @param killSwitch	string - ID of the On/Off toggle element
 * 								default: 'killSwitch'
 * @param onText		string - killSwitch text to be displayed if sliding is enabled
 * 								default: 'Turn Slide Off'
 * @param offText		string - killSwitch text to be displayed if sliding is disabled
 * 								default: 'Turn Slide On'
 * @param relativeTo	string - Scroll animation can be relative to either the 'top' or 'bottom' of the viewport
 * 								default: 'top'
 * @param delay			int - Time between the end of the scroll and the beginning of the animation in milliseconds
 * 								default: 0
 */

( function( $ ) {
	$.foldBox = function ( box, options ){ 		
		   var $content=$('.content',$(box));
		   var $button=	$('.drop',$(box));
		   var callBack=options.callBack;
			if($(box).hasClass(options.searchBox)){ //搜索框
				$content=$('#searchForm',$(box));
			   var  insertHtml='<div class="title">查询条件</div><div class="drop"><a>展开</a></div>'
				$(insertHtml).insertBefore( $content ) ;
				var afterShowfn=options.afterShow;
				options.afterShow=function(){afterShowfn();
				$('.drop',$(box)).find('a').text('收起');
				$('.drop',$(box)).find('a').addClass('activi')}
				var afterHidefn=options.afterHide;
				options.afterHide=function(){afterHidefn();
				$('.drop',$(box)).find('a').text('展开');
				$('.drop',$(box)).find('a').removeClass('activi')}
				$button=	$('.drop,.title',$(box));
			}
			
			$button.live('click',function(){
				 if( $content.is(':hidden')){
					 options.beforeShow($(box));
					 $content.show();
					 options.afterShow($(box));
					 $.setCookie("isLocked", true);
					 if(callBack){
						 callBack();
					 }
				 }else{
					options.beforeHide($(box));
					$content.hide();
					options.afterHide($(box));
					$.setCookie("isLocked", false);
					if(callBack){
						 callBack();
					}
				 }
			});
			if($.getCookie("isLocked")=="true"){
				 $('.drop',$(box)).find('a').text('收起');
				 $('.drop',$(box)).find('a').addClass('activi');
				 $content.show();	
			}
			
	};
	
	$.fn.foldBox = function ( options ){
		options = options || {};
		options.searchBox = options.searchBox || 'panel-search';
		options.beforeShow = options.beforeShow || function($box){};
		options.afterShow = options.afterShow || function($box){};
		options.beforeHide = options.beforeHide || function($box){};
		options.afterHide = options.afterHide || function($box){};
		this.each( function() 
			{
				new $.foldBox( this, options );
			}
		);
		
		return this;
	};
})( jQuery );

$(function(){
	$.extend({initFoldBox:function(){
		/**
		 * 初始化更新页面
		 */
		try{
			var updateHeight = function(){$("div.hide-panel").height($("div.panel-top").height());};
			$('.panel-search').foldBox({afterShow:updateHeight,afterHide:updateHeight,callBack:callBackFunc});
			$('.foldBox').foldBox();
			//changeScrollHeight(($('.panel-search')[0]&&$('.panel-search').attr("hasScroll")!="true"||$('.foldBox')[0]&&$('.foldBox').attr("hasScroll")!="true")?"":true);
		}catch(e){}
	}});
	$.initFoldBox();
});
