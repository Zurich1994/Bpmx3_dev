
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电子印章管理</title>
<%@include file="/commons/include/get.jsp"%>
<style type="text/css">

.Div_MySeal_click{
    background:#F00;
}

.Div_MySeal_Move{
    background:#FF0;
}

</style>

<script type="text/javascript">
	
	/* function returnSeal(sealId,sealName,fileId){
		var obj = {};
		obj.sealId = sealId;
		obj.sealName = sealName;
		obj.fileId = fileId;
		if (window.opener != undefined) {  
		    window.opener.returnValue = obj;  
		}else if (window.parent != undefined) {  
			parent.window.returnValue = obj;   
		}else {  
		    window.returnValue = obj;  
		}  
	    window.close();
	} */
	
	$(function(){
		var tbodySeal = $('#tbodySeal');
		var seals = $("div[name=mySealDiv]",tbodySeal);
		seals.each(function(){
			divMouseMove($(this));
			changeStyle($(this),seals);
		});
	}); 
	
	//移过事件
	function divMouseMove(div_me){
		div_me.hover(
             function() {
            	 div_me.addClass('Div_MySeal_Move');
             },
             function() {	
            	 div_me.removeClass("Div_MySeal_Move"); 
             }
	    );
	}
	
	//点击 事件 
	function changeStyle(div_me,seals){
		 var sealId = div_me.attr("id");		 
		 div_me.click(function(){
			 var myclass = div_me.attr("class");	
			 if(myclass==""||myclass=="Div_MySeal_Move"){
				// div_me.addClass('Div_MySeal_click');
				 div_me.attr("class","Div_MySeal_click");
			 }else{
				 div_me.attr("class","");
			 }
			 seals.each(function(){
				 var other_div_me=$(this);
				 var otherSealId = other_div_me.attr("id");
				 if(sealId!=otherSealId){
					 other_div_me.attr("class","");
				 }
			 });
		 });
	}
	
</script>
</head>
<body>
	<div class="panel-toolbar">
		<div class="group">
			<a class="link search" id="btnSearch">查询</a>
		</div>
	</div>
	<div class="panel-search">
		 <form id="searchForm" method="post" action="selector.ht">
			<ul class="row">
				<li><span class="label">印章名:</span>
				<input type="text" name="sealName" class="inputText" value="${sealName}"/> </li>				
			</il>
		</form>
	</div>
	<div align="center">
		<table class="table-grid table-list" cellpadding="1" style="margin-top:10px;"  cellspacing="1" id="tableSeal"  width="96%" >
			<tr>
				<th colspan="3">电子印章列表</th>
			</tr>
			<tbody id="tbodySeal">
				
				<c:if test="${fn:length(sealList)==0}">
					<tr>
				       <td colspan="3">暂时没有符合的内容</td>
			       </tr>  
				</c:if>
				
				<!-- 有内容时 -->
				<c:forEach items="${sealList}" var="seal" varStatus="status" >
				    <c:if test="${status.count%3==1}">
					    <tr>
						    <td width="33%" align="center">
						        <div id="${seal.sealId}" name="mySealDiv" class="" value="{'sealId':'${seal.sealId}','sealName':'${seal.sealName}','fileId':'${seal.attachmentId}'}" >
							        <c:if test="${ !empty seal.showImageId && !(seal.showImageId eq '') }">
							           <img alt="${seal.sealName}" src="${ctx}/platform/system/sysFile/getFileById.ht?fileId=${seal.showImageId}" width="100px" height="100px" >
							           </br>
							           ${seal.sealName}
							        </c:if>
                                    <c:if test="${ empty seal.showImageId || (seal.showImageId eq '') }">
							            <div style="width:100px;height:110px" ></br></br></br>${seal.sealName}</div> 
							        </c:if>	
						        </div>
						    </td>
				    </c:if>
				    <c:if test="${status.count%3==2}">
						    <td width="33%" align="center">
						         <div id="${seal.sealId}" name="mySealDiv" class="" value="{'sealId':'${seal.sealId}','sealName':'${seal.sealName}','fileId':'${seal.attachmentId}'}" >
							        <c:if test="${ !empty seal.showImageId && !(seal.showImageId eq '') }">
							           <img alt="${seal.sealName}" src="${ctx}/platform/system/sysFile/getFileById.ht?fileId=${seal.showImageId}" width="100px" height="100px" >
							           </br>
							           ${seal.sealName}
							        </c:if>
							        <c:if test="${ empty seal.showImageId || (seal.showImageId eq '') }">
							            <div style="width:100px;height:110px" ></br></br></br>${seal.sealName}</div> 
							        </c:if>	
						         </div>
						    </td>
				    </c:if>
				    <c:if test="${status.count%3==0}">
						    <td width="34%" align="center">
						         <div id="${seal.sealId}" name="mySealDiv" class="" value="{'sealId':'${seal.sealId}','sealName':'${seal.sealName}','fileId':'${seal.attachmentId}'}" >
							        <c:if test="${ !empty seal.showImageId && !(seal.showImageId eq '') }">
							           <img alt="${seal.sealName}" src="${ctx}/platform/system/sysFile/getFileById.ht?fileId=${seal.showImageId}" width="100px" height="100px" >
							           </br>
							           ${seal.sealName}
							        </c:if>
							        <c:if test="${ empty seal.showImageId || (seal.showImageId eq '') }">
							            <div style="width:100px;height:110px" ></br></br></br>${seal.sealName}</div> 
							        </c:if>						            
						         </div>
						    </td>
					     </tr>
				    </c:if>
				</c:forEach>
				
				<!-- 不够填充表格时，补上并完整表格 --> 
				<c:if test="${fn:length(sealList)%3==1}">
						    <td align="center"  width="33%"></td>
						    <td align="center"  width="33%"></td>
					     </tr>
				</c:if> 
				<c:if test="${fn:length(sealList)%3==2}">
						    <td align="center"  width="34%"></td>
						 </tr>
				</c:if> 				
			</tbody>
	    </table>	
	</div>
	<%-- <div class="panel-search">
		<form id="searchForm" method="post" action="selector.ht">
			<ul class="row">
				<li><span class="label">印章名:</span>
				<input type="text" name="Q_sealName_S" class="inputText" value="${param['Q_sealName_S']}"/> </li>
				<li><span class="label">印章持有者姓名:</span>
				<input type="text" name="Q_belongName_S" class="inputText" value="${param['Q_belongName_S']}"/></li>
			</il>
		</form>
	</div>
	<br />
	<display:table name="sealList" id="sealItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
		<display:column media="html" style="width:30px;">
			<input type="radio" class="pk" name="attachmentId" value="${sealItem.attachmentId}" />
		</display:column>
		<display:column property="sealName" title="印章名" sortable="true" sortName="sealName"></display:column>
		<display:column property="belongName" title="印章持所属单位或个人" sortable="true" sortName="belongName"></display:column>
	</display:table>
	<hotent:paging tableId="sealItem" /> --%>
</body>
</html>


