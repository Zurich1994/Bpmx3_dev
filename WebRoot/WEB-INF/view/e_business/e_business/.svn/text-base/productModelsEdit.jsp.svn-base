<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>商品表管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" >
function srch(){
    $.ajax({ 
     	type: 'POST', 
		url: 'search.ht' , 
		data: {
			Q_REGION_S:$("#Q_REGION_S").val(),
            Q_KEYWORDS_S:$("#Q_KEYWORDS_S").val(),
            Q_CATEGORY_S:$("#Q_CATEGORY_S").val()
		} , 
		dataType: 'json' ,
		success: function(d) {
		
			for (var i = 0; i < d.length; i++) {
				var appendUrl = "<a id='"+i+"' href='list.ht'<%=request.getParameter("EMAIL")%>>"+d[i]+"</a><br>";
		        $('#appendDiv').append(appendUrl);
			}
		}
			
		
	});
        
}
</script>
</head>
<body>

	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">商品表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link" href="javascript:srch()">GO</a>
					<a class="link " href="javascript:srch()"><span></span>Search</a>
					 <a href="/mas/e_business/e_business/productModel/list.ht?EMAIL=<%=request.getParameter("EMAIL")%>">查看商品</a>
					
					
					</div>
					<div class="l-bar-separator"></div>
					
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="">
					<div class="row">
					 <p>Select Your Region</p>
						
    <p></p><span class="label">地区名:</span>
       <select name="Q_REGION_S" id="Q_REGION_S" class="inputText">
         <option selected="selected">黑龙江</option>
         <option>北京</option>
         <option>哈尔滨</option>
       </select></p>
     <p>Search</p>
     <p><span class="label">关键字:</span>
       <input name="Q_KEYWORDS_S" class="inputText"  id="Q_KEYWORDS_S" type="text" />
       <span class="label">类别:</span>
       <select name="Q_CATEGORY_S"  id="Q_CATEGORY_S" class="inputText">
         <option selected="selected">Search entire site</option>
         <option>Products</option>
         <option>Services</option>
         <option>Technical Support</option>
         <option>Accessories</option>
       </select>
     </p>
     <p>Browse: Choose customer type:</p>
     <div id="appendDiv"></div>
  
			      </div>
				</form>
			</div>
		</div>
								
	</div> <!-- end of panel -->
</body>
</html>


