<%--
	time:2012-06-25 11:05:09
	desc:edit the 通用表单对话框
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<title>显示通用对话框</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${ctx}/js/util/ObjectUtil.js"></script>
<script type="text/javascript">
	var checkBoxData = new Array();
	var trArray = [];
	var checkBoxArr = [];
	forbidF5("Chrome");//禁止刷新页面
	function onChecked(obj,tr,textarea) {
		var json = eval("(" + $(textarea).val() + ")");
		var checked = $(obj).attr('checked');
		var existsIndex = -1;
		for ( var i = 0; i < checkBoxData.length; i++) {
			if (checkBoxData[i].equals(json)) {
				existsIndex = i;
			}
		}
		if (existsIndex == -1 && (checked == true || checked == 'checked')) {
			//全局数组中不存在并且现在是选中
			checkBoxData.push(json);
			var copyTr = $(tr).clone(true);
			copyTr.removeClass('over odd even');
			$(copyTr.find('td')[0]).html('<a class="link del" href="javascript:;" title="删除"></a>');
			trArray.push(copyTr[0].outerHTML);
			checkBoxArr.push(obj);
		} else if (existsIndex!=-1 && !checked) {
			//全局数组中存在并且现在是反选
			checkBoxData.splice(existsIndex, 1);
			trArray.splice(existsIndex, 1);
			checkBoxArr.splice(existsIndex, 1);
		}
	}
	function viewSelected(){
		$('#dialogFrame').hide();
		$('#viewSelected').html('');
		$('#viewSelected').append(trArray.join(''));
		$('.panel').show();
	}
	function back(){
		$('#dialogFrame').show();
		$('.panel').hide();
	}
	function resetMethod(){
		trArray = [];
		checkBoxArr = [];
		$('#viewSelected').html('');
		checkBoxData.splice(0,checkBoxData.length);
		$('#dialogFrame')[0].contentWindow.unCheckedAll();
	}
	$(function(){
		$('.del').live('click',function(){
			var tr = $(this).parent().parent() ;
			var dialogFrame = $('#dialogFrame')[0].contentWindow;
			for ( var i = 0; i < trArray.length; i++) {
				if (trArray[i].equals(tr[0].outerHTML)) {
					//反选iframe中的复选框反选操作
					dialogFrame.unChecked(checkBoxArr[i]);
					trArray.splice(i, 1);
					checkBoxData.splice(i, 1);
					checkBoxArr.splice(i, 1);
					break ;
				}
			}
			tr.remove();
		});
	});
</script>
</head>
<body style="margin: 0;">
	<iframe src="showFrame.ht?<%=request.getQueryString()%>"
		id="dialogFrame" name="dialogFrame" width="100%"
		frameborder="0"
		onload="this.height=document.body.scrollHeight"></iframe>
	<div class="panel hidden">
		<div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" id="btnBack" onclick="back()"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class='panel-table'>
				<table id="viewSelected" cellpadding="1" class="table-grid table-list" cellspacing="1">
				</table>
			</div>
		</div>
	</div>
</body>
</html>
