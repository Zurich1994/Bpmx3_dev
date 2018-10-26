<%--
	time:2012-01-05 12:01:21
	desc:edit the 脚本管理
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>选择视图</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/util/tableHeadFix.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#tbTableList").chromatable({height:"350px",width:"100%"});
			
			$("#btnGenView").click(function(){
				$("#viewForm").submit();
			});
			
			$("input.pk").click(function(){
				$("#pkName").val(this.value);
			})
		});
		
		//规则上移
		function sort(obj,isUp) {
			var thisTr = $(obj).parents("tr");
			if(isUp){
				var prevTr = $(thisTr).prev();
				if(prevTr){
					thisTr.insertBefore(prevTr);
				}
			}
			else{
				var nextTr = $(thisTr).next();
				if(nextTr){
					thisTr.insertAfter(nextTr);
				}
			}
		};
		
		function delRow(obj){
			$(obj).parents("tr").remove();
		}
		
	
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">编辑视图</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<div class="group"><a class="link run" id="btnGenView" href="javascript:;"><span></span>生成</a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link back" onclick="javasrcipt:history.back()"><span></span>返回</a></div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="viewForm" method="post" action="editView.ht">
					<div class="panel-detail">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="10%">视图名称: </th>
								<td width="23%">
									${table.name }
								</td>
								
								<th width="10%">主键字段: </th>
								<td width="23%">
									<input type="text" name="pkName" id="pkName" value="id" />
									
								</td>
								<th width="10%">描述: </th>
								<td width="23%">
									<input type="text" name="viewComment" id="viewComment"  value="${table.comment}"/>
									<input type="hidden" name="viewName" id="viewName"  value="${table.name}"/>
								</td>
							</tr>
						</table>
					</div>
					<br>
					<table id="tbTableList" cellpadding="1" style="width:  200px" class="table-grid table-list" cellspacing="1">
						<thead>
							<tr>
								<th class="sortable">
									主键
								</th>
								<th class="sortable">
									字段名
								</th>
								<th >
									类型
								</th>
								<th class="sortable">
									描述
								</th>
								<th>管理</th>
							</tr>
						</thead>
						<c:forEach items="${table.columnList }" var="col" varStatus="status">
							<tr class="${status.index %2 ==0 ?'odd':'even'}">
								<td style="text-align: center;">
									<input type="radio" name="pk" value="${col.name }"  class="pk"  ${status.index==0?'checked="true"':'' } /></td>
								<td>${col.name }</td>
								<td>
								<c:choose>
									<c:when test="${col.columnType =='NUMBER'}">数字</c:when>
									<c:when test="${col.columnType =='DATE'}">日期</c:when>
									<c:otherwise >
									字符串
									</c:otherwise>
								</c:choose>
								</td>
								<td>
									<input type="hidden" name="column" value="${col.name }">
									<input type="hidden" name="dbtype" value="${col.columnType }">
									<input type="text" name="comment" value="${col.comment }">
								</td>
								<td style="width:180px">
									<a href="javascript:;" class="link del" onclick="delRow(this)">删除</a>	
									<a alt='上移' href='#' class='link moveup' onclick='sort(this,true)'>　</a>	
									<a alt='下移' href='#' class='link movedown' onclick='sort(this,false)'>&nbsp;&nbsp;&nbsp;</a>
								</td>
							</tr>
						</c:forEach>	
					</table>
				</form>
		</div>
</div>
</body>
</html>
