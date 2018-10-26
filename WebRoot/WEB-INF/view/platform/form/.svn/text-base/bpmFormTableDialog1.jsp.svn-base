
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>选择自定义表</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript"><!--
	var dialog = parent.frameElement.dialog; //调用页面的dialog对象(ligerui对象)、
	$(function(){
		//var height=$(".panel-top").height();
		$("#centerLayout").height($(window).height()-50);
		//$("#defLayout").ligerLayout({height:'100%', bottomHeight:40,allowTopResize:false,allowBottomResize:false});
		
		$("tr.odd,tr.even").unbind("hover");
		$("tr.odd,tr.even").click(function(){
			$(this).siblings().removeClass("over").end().addClass("over");
		});
	})

	function selectTable(){
	
	/*	var obj=$("#tableParcelItem tr.over");
	
		if(obj.length>0){
	
			var objInput=$("input",obj);
		
			var aryTb=objInput.val().split(",");
			alert("aryTb[0]："+aryTb[0]+"aryTb[1]"+aryTb[1]);
			parent.getTable(aryTb[0],aryTb[1]);
		}*/
	var obj=document.getElementsByName('test'); //选择所有name="'test'"的对象，返回数组
//取到对象数组后，我们来循环检测它是不是被选中
var s='';
var a='';
var name='';
var id='';

for(var i=0; i<obj.length;i++){
if(obj[i].checked)
{
 s+=obj[i].value+',';
//如果选中，将value添加到变量s中
}
}
//那么现在来检测s的值就知道选中的复选框的值了
alert(s);
var a=s.split(",");
for(var i=0;i<a.length;i=i+2)
{

id+=a[i];

}
for(var i=1;i<a.length;i=i+2)
{if(i!=a.length-1)
{
name+=a[i]+',';}
else if(i==a.length-1){
name+=a[i];
}

}

parent.getTable(id,name);

	}
	 
--></script>
<style type="text/css">
	div.bottom{text-align: center;padding-top: 10px;}
	body {overflow: hidden;}
</style>
</head>
<body>
	<div id="defLayout">
		<div id="centerLayout" position="center"  style="overflow: auto;">
			<div class="panel-top" usesclflw="true">
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					</div>	
				</div>
				<div class="panel-search">
					<form id="searchForm" method="post" action="dialog1.ht">
						<ul class="row">
								<li><span class="label">数据包名:</span><input type="text" name="Q_parcel_name_S"  class="inputText" size="15" value="${param['Q_parcel_name_S']}"/></li>
								<li><span class="label">描述:</span><input type="text" name="Q_parcel_alias_S"  class="inputText" size="15" value="${param['Q_parcel_alias_S']}"/></li>
						</ul>		
					</form>
				</div>
			</div>
		
	    	<display:table name="tableParcelList" id="tableParcelItem" requestURI="list.ht" sort="external"  export="false"  class="table-grid">
				<display:column  title="数据包名" style="text-align:left;width:200px;">
				<input  type="hidden" value="${tableParcelItem.id },${tableParcelItem.table_name }">
				<input type="checkbox" class="pk" name="test" value="${tableParcelItem.id },${tableParcelItem.table_name }">
					
					${tableParcelItem.parcel_name}
				</display:column>
				<display:column property="parcel_alias" title="注释" style="text-align:left"></display:column>
			</display:table>
			<hotent:paging tableId="tableParcelItem"/>
		</div>
		<div position="bottom"  class="bottom">
			<a href='#' class='button'  onclick="selectTable()" ><span class="icon ok"></span><span>选择</span></a>
			<a href='#' class='button' style='margin-left:10px;' onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a>
		</div>
	</div> 
</body>
</html>


