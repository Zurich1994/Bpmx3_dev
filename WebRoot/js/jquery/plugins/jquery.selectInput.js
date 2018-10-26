(function($){
		$.fn.selectInput = function(){
			//用变量idm存储select的id或name
			var idm = $(this).attr("id");

			if($("#" + idm + "div").length <= 0){//判断动态创建的div是否已经存在，如果不存在则创建
				var divHtml = "<div style='display:none' id='" + idm + "div'><input type='text' id='" + idm + "inputText'/></div>";
				$(this).attr("tabindex",-1).after(divHtml);
				$("#" + idm + "div").css({position:"absolute",top:$(this).position().top ,left:$(this).position().left}).show();
				$("#" + idm + "inputText").val($(this).val()).css({width:$(this).width()-18,height:$(this).height() });
				//select注册onchange事件
				$(this).change(function(){
					$("#" + idm + "inputText").val($(this).val());	
				});		
				//inputText注册onchange事件
				$("#" + idm + "inputText").change(function(){
					var inputV=$("#" + idm + "inputText").val();
					var had=false;
					$.each($("#"+idm).children("option"),function(i,opt){
						if($(opt).val()==inputV){
							had=true;
							$("#"+idm).val(inputV);
						}
					});
					if(had==false){
						var opt='<option value="'+inputV+'"  selected="selected">'+inputV+'</option>';
						$("#"+idm).append(opt);
					}
				});		
				
			}
			//解决ie6下select浮在div上面的bug
			$("#" + idm + "div").bgIframe();
			return $("#" + idm + "inputText").val();

		};
})(jQuery);