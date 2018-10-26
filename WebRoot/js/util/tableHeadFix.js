(function($) {
	$.chromatable = {
		defaults : {
			width : "900px",
			height : "300px",
			scrolling : "yes"
		}
	};
	$.fn.chromatable = function(options) {
		
		var options = $.extend({}, $.chromatable.defaults, options);
		return this
				.each(function() {
					var $this = $(this);
					var $uniqueID = $(this).attr("ID") + ("wrapper");
					$(this).css('width', options.width).addClass("_scrolling");
					$(this).wrap(
							'<div class="scrolling_outer"><div id="'
									+ $uniqueID
									+ '" class="scrolling_inner"></div></div>');
					$(".scrolling_outer").css({
						'position' : 'relative'
					});
					$("#" + $uniqueID).css({
						'border' : '1px solid #CCCCCC',
						'overflow-x' : 'hidden',
						'overflow-y' : 'auto',
						'padding-right' : '17px'
					});
					$("#" + $uniqueID).css('height', options.height);
					$("#" + $uniqueID).css('width', options.width);
					$(this).before(
							$(this).clone().attr("id", "").addClass("_thead")
									.css({
										'width' : 'auto',
										'display' : 'block',
										'position' : 'absolute',
										'border' : 'none',
										'border-bottom' : '1px solid #CCC',
										'top' : '1px'
									}));
					$('._thead').children('tbody').remove();
					$(this)
							.each(
									function($this) {
										if (options.width == "100%"
												|| options.width == "auto") {
											$("#" + $uniqueID).css({
												'padding-right' : '0px'
											});
										}
										if (options.scrolling == "no") {
											$("#" + $uniqueID)
													.before(
															'<a href="javascript:;" class="expander" style="width:100%;">Expand table</a>');
											$("#" + $uniqueID).css({
												'padding-right' : '0px'
											});
											$(".expander")
													.each(
															function(int) {
																$(this).attr(
																		"ID",
																		int);
																$(this)
																		.bind(
																				"click",
																				function() {
																					$("#"+ $uniqueID).css(
																									{
																										'height' : 'auto'
																									});
																					$("#"+ $uniqueID+ " ._thead").remove();
																					$(this).remove();
																				});
															});
											$("#" + $uniqueID).resizable({
												handles : 's'
											}).css("overflow-y", "hidden");
										}
									});
					$curr = $this.prev();
					$("thead:eq(0)>tr th", this).each(
							function(i) {
								$("thead:eq(0)>tr th:eq(" + i + ")", $curr)
										.width($(this).width());
							});
					if (options.width == "100%" || "auto") {
						$(window).resize(function() {
							resizer($this);
						});
					}
				});
	};
	function resizer($this) {
		$curr = $this.prev();
		$("thead:eq(0)>tr th", $this).each(function(i) {
			$("thead:eq(0)>tr th:eq(" + i + ")", $curr).width($(this).width());
		});
	}
	;
})(jQuery);