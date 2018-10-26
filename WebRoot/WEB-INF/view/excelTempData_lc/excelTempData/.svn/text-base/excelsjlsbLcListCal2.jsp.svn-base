<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@include file="/commons/include/customForm.jsp" %>

<html>
	<head>
		<title>excel 数据临时表管理</title>
		<%@include file="/commons/include/get.jsp"%>

<script type="text/javascript">
 function go() { 
	 var a =document.getElementsByName("sj");
	 var s="";
	 var c = 0;
	 var exp = $("#exp").val();
	 //alert("js exp:"+exp);
	 
	 var finType = $("#finType").val();
	 //alert("js finType:"+finType);
	  for(var i = 0; i<a.length;i++){
			 if(a[i].checked)
			  {
			  //	alert("1a= "+a[i].value); 
			  	s+= a[i].value+",";
			  	c++;
			  //	alert("c:"+c)
			   }
		}
		
		if(exp=="y=a*x+b"){
			if(c < 2){
				alert("选择的项数不够，请选择两项");
				return false;
			}
			
			s = s.substring(0, s.length-1);
			 row= new Array();
			 row = s.split(",");			 
			 col0 = row[0].split(":");
			 col1 = row[1].split(":");
			 //alert("col0:"+col0+"  col1:"+col1);
			 if(finType == "year"){
				var x1 = col0[0].substring(0, 4).trim();
				var x2 = col1[0].substring(0, 4).trim();
				
				if(x1==x2){
				alert("年数相等，请重新选择两项");
					return false;
				}
			}
			else if(finType == "month"){
				 x1 = col0[0].substring(0, 4).trim();
				 x2 = col1[0].substring(0, 4).trim();
				 x11 = col0[0].substring(4, 6).trim();
				 x22 = col1[0].substring(4, 6).trim();
				 
				if(!((x1==x2))){
					alert("年数不相等，请重新选择两项");  
			
					return false;
				}
				if(x11==x22){
					alert("月数相等，请重新选择两项");  
			
					return false;
				}
			}
			else if(finType == "day"){
				 x1 = col0[0].substring(0, 4).trim();
				 x2 = col1[0].substring(0, 4).trim();
				 x11 = col0[0].substring(4, 6).trim();
				 x22 = col1[0].substring(4, 6).trim();
				 x111 = col0[0].substring(6, 8).trim();
				 x222 = col1[0].substring(6, 8).trim();
				if(!((x1==x2)&& (x11==x22))){
					alert("年数或者月份不相等，请重新选择两项");   
					return false;
				}
				if(x111==x222){
					alert("日相等，请重新选择两项");  
			
					return false;
				}
				
			}
			else if(finType == "hour"){
				 x1 = col0[0].substring(0, 4).trim();
				 x2 = col1[0].substring(0, 4).trim();
				 x11 = col0[0].substring(4, 6).trim();
				 x22 = col1[0].substring(4, 6).trim();
				 x111 = col0[0].substring(6, 8).trim();
				 x222 = col1[0].substring(6, 8).trim();
				x1111 = col0[0].substring(8, 10).trim();
				 x2222 = col1[0].substring(8, 10).trim();
				if(!((x1==x2)&& (x11==x22)&&(x111==x222))){
					alert("年数或者月份或者日不相等，请重新选择两项");  
					return false;
				}
				if(x1111==x2222){
					alert("小时相等，请重新选择两项");  
			
					return false;
				}
			}
			else if(finType == "minute"){
				 x1 = col0[0].substring(0, 4).trim();
				 x2 = col1[0].substring(0, 4).trim();
				 x11 = col0[0].substring(4, 6).trim();
				 x22 = col1[0].substring(4, 6).trim();
				 x111 = col0[0].substring(6, 8).trim();
				 x222 = col1[0].substring(6, 8).trim();
				 x1111 = col0[0].substring(8, 10).trim();
				 x2222 = col1[0].substring(8, 10).trim();
				x11111 = col0[0].substring(10, 12).trim();
				 x22222 = col1[0].substring(10, 12).trim();
				if(!((x1==x2)&& (x11==x22)&&(x111==x222)&&(x1111==x2222))){
					alert("年数或者月份或者日或小时不相等，请重新选择两项");  
					return false;
				}	
				if(x11111==x22222){
					alert("分钟数相等，请重新选择两项");  
			
					return false;
				}			
			}
			//alert("s="+s);
		   window.location.href = "listDisplayResult.ht?sj="+s;
		}
		if(exp=="y=a*(x^2)+b*x+c"){
			if(c < 3){
				alert("选择的项数不够，请选择三项");
				return false;
			}
			s = s.substring(0, s.length-1);
			 row = s.split(",");			 
			 col0 = row[0].split(":");
			 col1 = row[1].split(":");
			 col2 = row[2].split(":");
			// alert("col0:"+col0+"  col1:"+col1+"  col2"+col2+"  col3"+col3);
			if(finType == "year"){
				 x1 = col0[0].substring(0, 4).trim();
				 x2 = col1[0].substring(0, 4).trim();
				 x3 = col2[0].substring(0, 4).trim();
				if((x1==x2)||(x1==x3)||(x2==x3)){
				alert("年数相等，请重新选择三项");  
					return false;
				}
			}
			else if(finType == "month"){
				 x1 = col0[0].substring(0, 4).trim();
				 x2 = col1[0].substring(0, 4).trim();
				 x3 = col2[0].substring(0, 4).trim();
				 x11 = col0[0].substring(4, 6).trim();
				 x22 = col1[0].substring(4, 6).trim();
				 x33 = col2[0].substring(4, 6).trim();
				 
				if(!((x1==x2)&&(x1==x3)&&(x2==x3))){
					alert("年数不相等，请重新选择三项");  
			
					return false;
				}
				if((x11==x22)||(x11==x33)||(x22==x33)){
					alert("月份相等，请重新选择三 项");  
			
					return false;
				}
			}
			else if(finType == "day"){
				 x1 = col0[0].substring(0, 4).trim();
				 x2 = col1[0].substring(0, 4).trim();
				 x3 = col2[0].substring(0, 4).trim();
				 x11 = col0[0].substring(4, 6).trim();
				 x22 = col1[0].substring(4, 6).trim();
				 x33 = col2[0].substring(4, 6).trim();
				  x111 = col0[0].substring(6, 8).trim();
				 x222 = col1[0].substring(6, 8).trim();
				 x333 = col2[0].substring(6, 8).trim();
					if(!((x1==x2)&&(x1==x3)&&(x2==x3)&& (x11==x22)&&(x11==x33)&&(x22==x33))){
					alert("年数或者月份不相等，请重新选择三项");   
					return false;
				}
				if((x111==x222)||(x111==x333)||(x222==x333)){
					alert("日相等，请重新选择三项");  
			
					return false;
				}
				
			}
			else if(finType == "hour"){
				 x1 = col0[0].substring(0, 4).trim();
				 x2 = col1[0].substring(0, 4).trim();
				 x3 = col2[0].substring(0, 4).trim();
				 x11 = col0[0].substring(4, 6).trim();
				 x22 = col1[0].substring(4, 6).trim();
				 x33 = col2[0].substring(4, 6).trim();
				  x111 = col0[0].substring(6, 8).trim();
				 x222 = col1[0].substring(6, 8).trim();
				 x333 = col2[0].substring(6, 8).trim();
				 x1111 = col0[0].substring(8, 10).trim();
				 x2222 = col1[0].substring(8, 10).trim();
				 x3333= col2[0].substring(8, 10).trim();
				
				if(!((x1==x2)&&(x1==x3)&&(x2==x3)&& (x11==x22)&&(x11==x33)&&(x22==x33)&&(x111==x222)&&(x111==x333)&&(x222==x333))){
					alert("年数或者月份或者日不相等，请重新选择三项");  
					return false;
				}
				if((x1111==x2222)||(x1111==x3333)||(x2222==x3333)){
					alert("小时相等，请重新选择三项");  
			
					return false;
				}
			}
			else if(finType == "minute"){
				 x1 = col0[0].substring(0, 4).trim();
				 x2 = col1[0].substring(0, 4).trim();
				 x3 = col2[0].substring(0, 4).trim();
				 x11 = col0[0].substring(4, 6).trim();
				 x22 = col1[0].substring(4, 6).trim();
				 x33 = col2[0].substring(4, 6).trim();
				 x111 = col0[0].substring(6, 8).trim();
				 x222 = col1[0].substring(6, 8).trim();
				 x333 = col2[0].substring(6, 8).trim();
				 x1111 = col0[0].substring(8, 10).trim();
				 x2222 = col1[0].substring(8, 10).trim();
				 x3333= col2[0].substring(8, 10).trim();
				 x11111 = col0[0].substring(10, 12).trim();
				 x22222 = col1[0].substring(10, 12).trim();
				 x33333= col2[0].substring(10, 13).trim();
				
				if(!((x1==x2)&&(x1==x3)&&(x2==x3)&& (x11==x22)&&(x11==x33)&&(x22==x33)&&(x111==x222)&&(x111==x333)&&(x222==x333)&&(x1111==x2222)&&(x1111==x3333)&&(x2222==x3333))){
					alert("年数或者月份或者日或小时不相等，请重新选择三项");  
					return false;
				}	
				if((x11111==x22222)||(x11111==x33333)||(x22222==x33333)){
					alert("分钟数相等，请重新选择三项");  
			
					return false;
				}			
			
			}
			alert("s="+s);
		   window.location.href = "listDisplayResult.ht?sj="+s;
			
		}
 }
 </script>
	</head>
	<body>
		<div class="panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">excel 数据临时表管理列表</span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link selectFile"  onclick="go()">显示公式</a>
							
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link back" href="listCal1.ht"><span></span>返回</a>
						</div>
						
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
			<c:set var="checkAll">
				<input type="checkbox" id="chkall" />
			</c:set>
			
			<display:table name="excelsjlsbLcList" id="excelsjlsbLcItem"
				requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"
				class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
					<input type="checkbox" class="pk" name="sj"
						value="${excelsjlsbLcItem.sj}:${excelsjlsbLcItem.fsl}">
				</display:column>
				<display:column property="sj" title="时间" sortable="true"
					sortName="F_SJ"></display:column>
				<display:column property="fsl" title="发生量" sortable="true"
					sortName="F_FSL"></display:column>
					
			</display:table>
			<input type="hidden" id="exp" value="${sessionScope.exp}" />
			<input type="hidden" id="finType" value="${sessionScope.redirType}" />
			<hotent:paging tableId="excelsjlsbLcItem" />
		</div>``
		<!-- end of panel-body -->
		</div>
		<!-- end of panel -->
	</body>
</html>


