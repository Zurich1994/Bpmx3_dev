<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title></title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx }/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript">
	$(function() {
	
	});
	function dlgCallBack(userIds){
		var orgId="${orgId}";
		var path="${path}";
		var url="addOrgUser.ht";
		para="userIds="+userIds+"&orgId="+orgId;
		$.post(url,para,function(data){
			var obj=new com.hotent.form.ResultMessage(data);
			if(obj.isSuccess()){
				 $.ligerDialog.success(obj.getMessage(),"提示信息",function(rtn){
					  location.href="userList.ht?orgId="+orgId+"&path="+path;
				 });
			}else{
				$.ligerDialog.err('出错信息',"查询组织人员失败",obj.getMessage());
			}
		  
		});
	};
	function addClick(){
		UserDialog({callback:dlgCallBack,isSingle:false});
	};
</script>
</head>
<body>
<div class="panel">
		<c:choose>
		  	<c:when test="${action=='global' }">
		  		<f:tab curTab="user" tabName="sysOrg"/>
		  	</c:when>
		  	<c:otherwise>
		  		<f:tab curTab="user" tabName="sysOrgGrade"/>
		  	</c:otherwise>
	   </c:choose>
		  
      
       <c:choose>
       		<c:when test="${empty sysOrg}">
					<div style="text-align: center;margin-top: 10%;">尚未指定具体组织!</div>
				</c:when>
       		<c:otherwise>
       		<div class="hide-panel">
       		<div class="panel-top">
	       		<div class="panel-toolbar">
					<div class="toolBar">
						<div class="toolBar">
						<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
						<div class="l-bar-separator"></div>
						<c:if test="${action=='global'}">
							<div class="group">
								<a class="link add"  href="${ctx}/platform/system/sysUser/edit.ht?orgId=${orgId}"><span></span>新增人员</a>
							</div>
							<div class="l-bar-separator"></div>
						</c:if>
						<c:if test="${action=='grade'}"> 
							<c:if test="${not empty orgAuth && fn:contains(orgAuth.orgPerms, 'add')}">
								<div class="group"><a class="link add"  href="${ctx}/platform/system/sysUser/editGrade.ht?orgId=${orgId}&topOrgId=${param.topOrgId}"><span></span>新增人员</a></div>
							<div class="l-bar-separator"></div>
							</c:if>
						</c:if>
						<div class="group"><a class="link del" action="${ctx}/platform/system/sysUserOrg/del.ht"><span></span>移除</a></div>
						<div class="l-bar-separator"></div> 
						<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
						<%-- 
						<div class="group"><a class="link add"  href="javascript:;" onclick="addClick()"><span></span>加入人员</a></div>
						--%>
					</div>	
					</div>	
				</div>
				<div class="panel-search">
					<form id="searchForm" method="post" action="userList.ht?orgId=${orgId}">
						<ul class="row">
							<li><span class="label">姓名:</span><input type="text" name="Q_fullname_SL"  class="inputText" style="width:110px !important" value="${param['Q_fullname_SL']}"/></li>
							<li><span class="label">帐号:</span><input type="text" name="Q_account_SL"  class="inputText" style="width:110px !important" value="${param['Q_account_SL']}"/></li>
						</ul>
					</form>
		 		</div>
		 	</div>
		 	</div>
			<div class="panel-body">
			        <c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
					</c:set>
				    <display:table name="userPositionList" id="userPositionItem"  requestURI="userList.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
						<display:column title="${checkAll}" media="html" style="width:30px;text-align:center;">
							  	<input type="checkbox" class="pk" name="userPosId" value="${userPositionItem.userPosId}">
						</display:column>
						<display:column property="orgName" title="所属组织" sortable="true" sortName="orgId"></display:column>
						<display:column property="posName" title="岗位" sortable="true" sortName="posId"></display:column>
							<%-- 
						<display:column property="userName" title="姓名" sortable="true" sortName="userId">
					      --%>
						<display:column  title="姓名" sortable="true" sortName="userId">
							<f:userName userId="${userPositionItem.userId}" />
						</display:column>
						<display:column property="account" title="帐号" sortable="true" sortName="userId"></display:column>
						<display:column   title="主岗位" >
							<c:choose>
								<c:when test="${userPositionItem.isPrimary==1}"><span class="green">是</span></c:when>
								<c:otherwise><span class="red">否</span></c:otherwise>
							</c:choose>
						</display:column>
						<display:column   title="负责人" >
							<c:choose>
								<c:when test="${userPositionItem.isCharge==1}"><span class="green">是</span></c:when>
								<c:otherwise><span class="red">否</span></c:otherwise>
							</c:choose>
						</display:column>
						<%-- 
						<display:column   title="管理员" >
							<c:choose>
								<c:when test="${sysOrgItem.isGradeManage==1}"><span class="green">是</span></c:when>
								<c:otherwise><span class="red">否</span></c:otherwise>
							</c:choose>
						</display:column>
						 --%>
						
 						<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">
							<a href="${ctx}/platform/system/sysUserOrg/del.ht?userPosId=${userPositionItem.userPosId}" class="link del">删除</a>
							
							<a href="${ctx}/platform/system/sysUser/get.ht?userId=${userPositionItem.userId}&canReturn=0" class="link detail">明细</a>
							<c:if test="${not empty orgAuth && fn:contains(orgAuth.orgPerms, 'edit')}">
							<a class="link edit"  href="${ctx}/platform/system/sysUser/editGrade.ht?orgId=${orgId}&userId=${userPositionItem.userId}"><span></span>编辑人员</a>
							</c:if>
							<c:choose>
								<c:when test="${userPositionItem.isPrimary==0}">
									<a class="link primary" href="setIsPrimary.ht?userPosId=${userPositionItem.userPosId}">设为主岗位</a>
								</c:when>
								<c:otherwise>
									<a class="link notPrimary" href="setIsPrimary.ht?userPosId=${userPositionItem.userPosId}">设为非主岗位</a>
								</c:otherwise>
							</c:choose>
							<c:choose>
									<c:when test="${userPositionItem.isCharge==0}">
										<a class="link charge" id="charge" title="设为负责人" href="setIsCharge.ht?userPosId=${userPositionItem.userPosId}">设置为负责人</a>
									</c:when>
									<c:otherwise>
										<a class="link noCharge" id="noCharge" title="设为非负责人" href="setIsCharge.ht?userPosId=${userPositionItem.userPosId}">设置为非负责人</a>
									</c:otherwise>
							</c:choose>
							<%-- 
							<c:choose>
									<c:when test="${sysOrgItem.isGradeManage==0}">
										<a class="link charge" title="设为组织管理者，可取得分级授权" href="setIsManage.ht?userPosId=${sysOrgItem.userOrgId}">设为组织管理者</a>
									</c:when>
									<c:otherwise>
										<a class="link noCharge" title="设为非组织管理者，取消分级授权" href="setIsManage.ht?userPosId=${sysOrgItem.userOrgId}">设为非组织管理者</a>
									</c:otherwise>
							</c:choose>
							 --%>
						</display:column>
					</display:table>
					<hotent:paging tableId="userPositionItem"/>
	  		
	   		</div>
       		</c:otherwise>
       </c:choose>
       
	  </div> 					
</body>
</html>