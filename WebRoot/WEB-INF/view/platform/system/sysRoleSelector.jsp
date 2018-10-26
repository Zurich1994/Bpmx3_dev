 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>系统角色表管理</title>
	<%@include file="/commons/include/get.jsp" %>
	<style type="text/css">
		html { overflow-x: hidden; }
	</style>
		<script type="text/javascript">
	var isSingle='${isSingle}';
	$(function(){
		var isSingle=${isSingle};
		forbidF5("Chrome");//禁止刷新页面
		$("#sysRoleItem>tbody").find("tr").bind('click', function() {
			if(isSingle=='true'){
				var rad=$(this).find('input:radio');
				rad.attr("checked","checked");
			}else{
				var ch=$(this).find(":checkbox");
				window.parent.selectMulti(ch);
			}
		});
		
		if(!isSingle){
			$("#chkall").click(function(){
				var checkAll=false;
				if($(this).attr("checked")){
					checkAll=true;	
				}
				var checkboxs=$(":checkbox",  $("#sysRoleItem>tbody"));
				checkboxs.each(function(){
					if(checkAll){
						window.parent.selectMulti(this);
					}
				})
			});
		}
	});
	</script>
</head>
<body style="overflow-x:hidden;">
	<div class="panel">
			<div class="panel-search" moreHeight="30">
				<form id="searchForm" method="post" action="selector.ht?isSingle=${isSingle}" target="roleFrame">
					<div class="row">
						<span class="label">角色名称:</span><input type="text" name="Q_roleName_S" value="${param.Q_roleName_S }"  class="inputText" size="20" /> &nbsp;
						<a href="javascript:;" onclick="$('#searchForm').submit()" class='button'><span>查询</span></a>
					</div>
				    <input type ="hidden" id = "all_selected" name="all_selected" value="${all_select}"/>
		            <input type ="hidden" id = "now_selected" name="Q_nowselected_S"/>
		            <input type ="hidden" id = "no_selected" name="Q_noselected_S"/> 
				</form>
			</div>
			<div class="panel-body">
					<div class="panel-data">
					<c:if test="${isSingle==false}">
					<c:set var="checkAll">
						<input type="checkbox" id="chkall" />
					</c:set>
				    </c:if>
				    <display:table name="sysRoleList" id="sysRoleItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
						<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<c:if test="${isSingle==false}">
								<input type="checkbox" class="pk" name="roleId"  value="${sysRoleItem.roleId}#${sysRoleItem.roleName}"/>
							</c:if>
							<c:if test="${isSingle==true}">
								<input type="radio" class="pk" name="roleId" value="${sysRoleItem.roleId}#${sysRoleItem.roleName}">
							</c:if>
						</display:column>
						<display:column property="roleName" title="角色名" sortable="true" sortName="roleName"></display:column>
						<display:column property="subSystem.sysName" title="子系统名称" sortable="true" sortName="sysName">
						</display:column>
						<display:column property="memo" title="备注" ></display:column>
					</display:table>
					<hotent:paging tableId="sysRoleItem" showExplain="false"/>	
				</div>
		</div>				
	</div> 
</body>
</html>