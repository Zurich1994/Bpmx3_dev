<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>

<%@include file="/commons/include/get.jsp" %>
<!--<script type="text/javascript">
function altRows(id){
	if(document.getElementsByTagName){  
		
		var table = document.getElementById(id);  
		var rows = table.getElementsByTagName("tr"); 
		 
		for(i = 0; i < rows.length; i++){          
			if(i % 2 == 0){
				rows[i].className = "evenrowcolor";
			}else{
				rows[i].className = "oddrowcolor";
			}      
		}
	}
}

window.onload=function(){
	altRows('tableId');
}
--> 
<script type="text/javascript"><!--






 $(document).ready(function(){
    $("#addTable").click(function(){
       var tr="<tr><td><input type=\"checkbox\" name=\"check\"/>"+

              "</td><td>000</td><td>111</td><td>222</td><td>333</td></tr>";                   
　  　$("#markovchainItem").append(tr);　　
    }); 
    //alert( $("#deleteTable").click);
    $("#deleteTable").click(function(){

        var check = document.getElementsByName("check");

        for(var i=0;i<check.length;i++){

            if(check[i].checked){

                 document.getElementById('markovchainItem').deleteRow(i);

                 i--;

            }

        }

    })

})






$(document).ready(function(){
$("tr.odd,tr.even").bind("click",function(){
var num=$(this).find("td").eq(2).html();
var num2=$(this).find("td").eq(2);
//alert("选中:"+num);
var graphchild=window.parent.graph;
graphchild.forEach(function(e){
 //alert("dd:"+e);
    var node = window.parent.getElementById(e);
    if(String(node)==num)
    {
    graphchild.selectionModel.set(node);
    }            
            })
//var node = parent.getElementById("349");
//graphchild.selectionModel.set(node);


//$(this).find("input:radio").attr("checked","checked")

});
});
--></script>


<!-- CSS goes in the document HEAD or added to your external stylesheet -->

</head>
<body>
	<input type="button" value="增加" id="addTable"/>
	<input type="button" value="删除" id="deleteTable"/>
	<div class="panel">
		
		<div class="panel-body">
	    	
		    <display:table name="markovchain" id="markovchainItem" requestURI="loadDetail.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				
				<display:column property="num" title="行号" sortable="true"></display:column>
				<display:column property="nodeId" title="节点ID" sortable="true"></display:column>
				<display:column property="lable" title="标签" sortable="true"></display:column>
				<display:column property="messgae" title="信息" sortable="true"></display:column>
				<display:column property="yellowcolor" title="颜色" sortable="true"></display:column>
				
			</display:table>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel --><!--
	
	
	<table border="1" cellpadding="1" cellspacing="1" id="tableId" class="altrowstable" >
	<tr>
	<td>行号</td>
	<td>节点ID</td>
	<td>标签</td>
	<td>信息</td>
	<td>颜色</td>
	</tr>
	<c:forEach items="${markovchain}" var="rs">
	<tr>
	<td>${rs.num}</td>
	<td>${rs.nodeId}</td>
	<td>${rs.lable}</td>
	<td>${rs.messgae}</td>
	<td>${rs.yellowcolor}</td>
	</tr>
	</c:forEach>
	</table>
	
	
--></body>
</html>


