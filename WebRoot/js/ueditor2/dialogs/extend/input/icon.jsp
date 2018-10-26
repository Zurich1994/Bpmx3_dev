<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/default/css/form.css">
<link rel="stylesheet" type="text/css" href="../input.css">
<style type="text/css">
 a.extend{
   display:inline;
 }
</style>
<script type="text/javascript">
	window.name="win";
	
	var selected, cla = "";
	$(function() {
		load();
		$(".button-td").bind("mouseenter mouseleave", function() {
			$(this).toggleClass("button-td-hover");
		});		
	});
	
	function load(){
		$.post(__ctx+"/platform/system/resources/getExtendIcons.ht", function(data) {
			var obj=$.parseJSON(data);
			if(obj.result==0){
				alert("出错了:" + obj.getMessage());
				return;
			}else{				
				icons = obj.message.split(",");
				var html = "";
				for ( var i = 0; i < icons.length; i++) {
					if(icons[i].length>0){
						html += '<li><a class="extend '+icons[i]+'"></a></li>';
					}
				}
				$("#icon-ul").html(html);
				$('#icon-ul li').click(function() {
					if (selected) {
						$(selected).removeClass('selected');
					}
					$(this).addClass('selected');
					selected = this;
					cla = $(this).find("a").attr('class');
				});
			}
		});
	};

	function select() {
		if (cla == "") {
			alert("没有选择图标!");
			return;
		}
		var obj = {
			cla : cla
		};
		window.parent.selectIcon(obj);
	};
	
	function closeWin(){
		window.parent.selectIcon();
	};
	
	function refresh(){
		$.post(__ctx+"/platform/system/resources/refreshExtendCss.ht",function(data){
			var obj=$.parseJSON(data);
			if(obj.result==0){
				alert("出错了:" + obj.getMessage());
				return;
			}
			else{
				location.href=location.href.getNewUrl();
			}
		});
	};
</script>
<base target="_self"></base>
</head>
<body>
	<div id="icon-div">
		<ul id="icon-ul">
		</ul>
	</div>
	<div id="button-div">
		<div class="button-td" onclick="closeWin()">取消</div>
		<div class="button-td" onclick="select()">选择</div>
		<div class="button-td" onclick="refresh()" >刷新</div>
	</div>
</body>
</html>