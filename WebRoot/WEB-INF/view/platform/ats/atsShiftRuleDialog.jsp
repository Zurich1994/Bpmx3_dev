<%@page pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title>选择轮班规则</title>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	$(function(){
		//布局
		$("#listLayout").ligerLayout({
			 bottomHeight :40,
			 height: '90%',
			 allowBottomResize:false,
			 minLeftWidth:200,
			 allowLeftResize:false
		});
	});
	
	function selectData(){
		var ids =[],
		 	names =[],
			chIds = $('#listFrame').contents().find(":input[name='data'][checked]");
		
		$.each(chIds,function(i,ch){
			var aryTmp=$(ch).val().split("#");
			ids.push(aryTmp[0]);
			names.push(aryTmp[1]);
		});
			
		var obj={id:ids.join(","),name:names.join(",")};
		dialog.get('sucCall')(obj);
		dialog.close();
	}
		
	function clearData(){
		var obj={id:'',name:''};
		dialog.get('sucCall')(obj);
		dialog.close();
	}
</script>
<style type="text/css">
	div.bottom{text-align: center;padding-top: 10px;}
	html,body{width:100%;height:100%;margin: 0 0 0 0;padding: 0 0 0 0 ;overflow: hidden;}
</style>
</head>
<body>
	<div id="listLayout" style="height:100%;">
		<div position="center">
				<iframe id="listFrame" name="listFrame" height="95%" width="100%" frameborder="0"
					src="${ctx}/platform/ats/atsShiftRule/selector.ht"
				 ></iframe>
		</div>
	</div>
	<div position="bottom"  class="bottom" style="margin-top:10px;">
		<a href="javascript:;" class="button"  onclick="selectData()" style="margin-right:10px;" ><span class="icon ok"></span><span >选择</span></a>
		<a href="javascript:;" class="button"  onclick="clearData()"><span class="icon cancel" ></span><span class="chosen" >清空</span></a>
		<a href="javascript:;" class="button" style="margin-left:10px;"  onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a>
	</div>
</body>
</html>


