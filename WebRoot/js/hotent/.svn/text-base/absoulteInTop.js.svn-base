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
	$.absoulteTop = function ( box, options ){ 
		var scrollTop=$(box).attr('usesclflw');
		if(scrollTop &&  ('false'==scrollTop)) return;
		 var ie6 = ($.browser.msie && ($.browser.version == 6.0) && !$.support.style)?true:false,
		 	 initTop = 0,obj=$(box).closest("[handScroll]");
		 	 
         if(obj.length==0)
        	 obj=$(window);
        	 
         obj.scroll(function () {
        	 scrollTop = obj.scrollTop();
        	 //向下滚动
             if (scrollTop < initTop && scrollTop == 0) {
            	if (ie6)
                	 $(box).css({"position": "relative", "top": "0","margin-top":"10px" });
                 else 
                	 $(box).css({"position": "relative", "top": "0"});
             } else {				
                 if (ie6)
                	 $(box).css({"position": "absolute", "top":options.top+"px", "margin-top":"0","z-index":options.zIndex });
                 else
                	 $(box).css({"position": "fixed", "top": options.top+"px","width":"100%","z-index":options.zIndex});
             }
             setTimeout(function(){initTop = scrollTop;},0);
         });
	};
	
	$.fn.absoulteInTop = function ( options ){
		options = options || {};
		options.top=options.top|| -5;
		options.zIndex =options.zIndex || 100;
		
		this.each(function() {
				new $.absoulteTop( this, options );
		});
		return this;
	};
})( jQuery );

$(function(){
	$.extend({
		initAbsoulteInTop:function(){
		try{
			$('.panel-top').absoulteInTop();
		}catch(e){}
	}});
//	$.initAbsoulteInTop();
});


