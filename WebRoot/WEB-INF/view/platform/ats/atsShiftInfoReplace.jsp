<%@page pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title>选择班次定义</title>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	$(function(){
		//布局
		$("#listLayout").ligerLayout({
			 topHeight :40,
			 bottomHeight :40,
			 height: '90%',
			 allowBottomResize:false,
			 minLeftWidth:200,
			 allowLeftResize:false
		});
	});
	
	function selectData(){
		var params = dialog.get('params');
		var ids =[],
		 	names =[],
		 	codes = [],
		 	shiftTimes =[],
			chIds = $('#listFrame').contents().find(":input[name='data'][checked]");
		
		$.each(chIds,function(i,ch){
			var aryTmp=$(ch).val().split("#");
			ids.push(aryTmp[0]);
			names.push(aryTmp[1]);
			codes.push(aryTmp[2]);
			shiftTimes.push(aryTmp[3]);
		});
			
		
		var dateType =	$(":input[name='dateType'][checked]").val(),
			backgroundColor= "#337ab7",shiftId ='',title ='';
		if(dateType == 1){
			shiftId = ids.join(",");
			title = names.join(",");
			if(shiftId== ""){
				alert("请选择班次!");
				return 
			}
		}
		else if(dateType == 2){
			shiftId = '';
			title = "休息日";
			backgroundColor = "#000000";
		}else if(dateType == 3){
			shiftId = '';
			backgroundColor = "#257e4a";
			title = $('#dateValue').val();
		}
		
		var obj={start:params.start,
					dateType:dateType,
					shiftId :shiftId,
					title:title,
					backgroundColor:backgroundColor
				};
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
	<div position="top">
		选择日期类型：
		<label><input type="radio" id="dateType1" name="dateType" value="1" checked="checked">工作日</label>
		<label><input type="radio" id="dateType2" name="dateType" value="2">休息日</label>
		<label><input type="radio" id="dateType3" name="dateType" value="3">法定假日</label>
		<input type="text" id="dateValue" value=""/>
	</div>
	<div id="listLayout" style="height:100%;">
		<div position="center">
				<iframe id="listFrame" name="listFrame" height="95%" width="100%" frameborder="0"
					src="${ctx}/platform/ats/atsShiftInfo/selector.ht?isSingle=${isSingle}"
				 ></iframe>
		</div>
	</div>
	<div position="bottom"  class="bottom" style="margin-top:10px;">
		<a href="javascript:;" class="button"  onclick="selectData()" style="margin-right:10px;" ><span class="icon ok"></span><span >选择</span></a>
	
		<a href="javascript:;" class="button" style="margin-left:10px;"  onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a>
	</div>
</body>
</html>


