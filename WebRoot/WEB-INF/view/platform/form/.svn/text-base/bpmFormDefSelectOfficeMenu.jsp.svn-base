<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<f:js pre="js/lang/view/platform/form" ></f:js>
<title>文档控件菜单权限设置</title>

<script type="text/javascript">
/*KILLDIALOG*/
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

$(function(){	
	
	init();

});

//初始化数据
function init() {
    //var data = window.dialogArguments;
    var data=dialog.get('conf');
    $('input:checkbox').each(function(){
		  var me = $(this);
		  var key = me.val();
		  switch(key){
		  	case "wjRight":
				if(data.wjRight=='y'){
					me.attr("checked",true); 
				}
				break;
		  	case "lhRight":
		  		if(data.lhRight=='y'){
					me.attr("checked",true); 
				}
				break;
		  	case "blhRight":
		  		if(data.blhRight=='y'){
					me.attr("checked",true); 
				}
				break;
		  	case "qchjRight":
		  		if(data.qchjRight=='y'){
					me.attr("checked",true); 
				}
				break;
		  	case "mbthRight":
		  		if(data.mbthRight=='y'){
					me.attr("checked",true); 
				}
				break;
		  	case "xzmbRight":
		  		if(data.xzmbRight=='y'){
					me.attr("checked",true); 
				}
				break;
		  	case "sxqmRight":
		  		if(data.sxqmRight=='y'){
					me.attr("checked",true); 
				}
				break;
		  	case "gzRight":
		  		if(data.gzRight=='y'){
					me.attr("checked",true); 
				}
				break;
		  	case "qpRight":
		  		if(data.qpRight=='y'){
					me.attr("checked",true); 
				}
				break;
		  	case "zcpdfRight":
		  		if(data.zcpdfRight=='y'){
					me.attr("checked",true); 
				}
				break;
		  	case "ekeygzRight":
		  		if(data.ekeygzRight=='y'){
					me.attr("checked",true); 
				}
				break;
		  	case "pdfgzRight":
		  		if(data.pdfgzRight=='y'){
					me.attr("checked",true); 
				}
				break;
		 }
	});
};

//返回数据
function returnData() {
	var data= {};
	$('input:checkbox').each(function(){
		  var me = $(this);
		  var key = me.val();
		  var value = 'n';
		  if (me.is(":checked") == true) {
			  value = 'y';
	      }
		  
		  switch(key){
		  	case "wjRight":
				data.wjRight=value;
				break;
		  	case "lhRight":
				data.lhRight=value;
				break;
		  	case "blhRight":
				data.blhRight=value;
				break;
		  	case "qchjRight":
				data.qchjRight=value;
				break;
		  	case "mbthRight":
				data.mbthRight=value;
				break;
		  	case "xzmbRight":
				data.xzmbRight=value;
				break;
		  	case "sxqmRight":
				data.sxqmRight=value;
				break;
		  	case "gzRight":
				data.gzRight=value;
				break;
		  	case "qpRight":
				data.qpRight=value;
				break;
		  	case "zcpdfRight":
				data.zcpdfRight=value;
				break;
		  	case "ekeygzRight":
				data.ekeygzRight=value;
				break;
		  	case "pdfgzRight":
				data.pdfgzRight=value;
				break;
		 }
	});
	
	//window.returnValue = data;
	dialog.get('sucCall')(data);
    dialog.close(); 

};

//全选
function selectAllRight(){
	$('input:checkbox').attr("checked",true); 
};

//反选
function reverseSelectRight(){
	$('input:checkbox').each(function(index){
		var me = $(this);
		var checked = me.is(":checked");
		if(checked){
			me.removeAttr("checked"); 
		}else{
			me.attr("checked",true); 
		}
	});
};

//放弃选择的
function abandonSelectRight(){
	$('input:checkbox').removeAttr("checked"); 
};

</script>
</head>
<body>
<div class="panel">
		<div class="hide-panel">
			<div class="panel-top">
					<div class="tbar-title">
						文档控件菜单权限设置
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<a class="link save" href="javascript:;" onclick="returnData();"><span></span>确定</a>
							</div>
							<div class="group">
								<a class="link del" href="javascript:;" onclick="dialog.close();"><span></span>关闭</a>
							</div>
						</div>
					</div>
			</div>
		</div>
	<form id="selectOfficeMenu" action="">
		<div class="panel-detail">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td colspan="4">
					    	<a class="link search" href="javascript:;" onclick="selectAllRight()">全选</a>  || <a class="link search" href="javascript:;" onclick="reverseSelectRight()">反选</a> || <a class="link search" href="javascript:;" onclick="abandonSelectRight()">弃选</a>
					</td>
				</tr>
				<tr>
					<th width=100>文件:</td>
					<td>
						<input type="checkbox" id="wjRight" name="menuRight" value="wjRight"/>
					</td>
					
					<th width=100>留痕:</td>
					<td><input type="checkbox" id="lhRight" name="menuRight" value="lhRight"/></td>
				</tr>
				<tr>	
					<th width=100>不留痕:</td>
					<td><input type="checkbox" id="blhRight" name="menuRight" value="blhRight"/></td>
					
					<th width=100>清除痕迹:</td>
					<td><input type="checkbox" id="qchjRight" name="menuRight" value="qchjRight"/></td>
				</tr>
				<tr>	
					<th width=100>模版套红:</td>
					<td><input type="checkbox" id="mbthRight" name="menuRight" value="mbthRight"/></td>
					
					<th width=100>选择模版:</td>
					<td><input type="checkbox" id="xzmbRight" name="menuRight" value="xzmbRight"/></td>
				</tr>
				<tr>	
					<th width=100>手写签名:</td>
					<td><input type="checkbox" id="sxqmRight" name="menuRight" value="sxqmRight"/></td>
					
					<th width=100>盖章:</td>
					<td><input type="checkbox" id="gzRight" name="menuRight" value="gzRight"/></td>
				</tr>
				<tr>	
					<th width=100>全屏:</td>
					<td><input type="checkbox" id="qpRight" name="menuRight" value="qpRight"/></td>
					
					<th width=100>转成PDF:</td>
					<td><input type="checkbox" id="zcpdfRight" name="menuRight" value="zcpdfRight"/></td>
				</tr>
				<tr>	
					<th width=100>Ekey盖章:</td>
					<td><input type="checkbox" id="ekeygzRight" name="menuRight" value="ekeygzRight"/></td>
					
					<th width=100>PDF盖章:</td>
					<td><input type="checkbox" id="pdfgzRight" name="menuRight" value="pdfgzRight"/></td>
				</tr>
				
			</table>
		</div>		
	</form>
</div>
</body>
</html>