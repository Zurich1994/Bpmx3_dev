<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.owner-span{
		font-size: 13px;
		background-color: #EFF2F7;
	    border: 1px solid #CCD5E4;
	    border-radius: 5px 5px 5px 5px;
	    cursor: default;
	    float: left;
	    height: auto !important;
	    margin: 3px;
	    overflow: hidden;
	    padding: 2px 4px;
	    white-space: nowrap;
	}
</style>
<%@include file="/commons/include/get.jsp" %>
<title>流程分组授权管理列表</title>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ImportExportXmlUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/tabOperator.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowRightDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowUtil.js" ></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/htCatCombo.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmDefAuthorize.js"></script>
<script type="text/javascript">

	$(function() {
			 
		//人员查看详情事件
	    openDetailEvent();
		
	    //流和查看详情事件
	    openActDetailEvent();
		    
	});

</script>
</head>
<body>      
	<div class="panel">
	<div class="panel-top">	
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>新增</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>编辑</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
				</div>	
			</div>
			<div class="panel-search">
			       
				<form id="searchForm" method="post" action="list.ht">	    
					<ul class="row">
						
						<li><span class="label">授权人员名称:</span><input type="text" name="Q_ownerName_SL"  class="inputText" value="${param['Q_ownerName_SL']}"/></li>
						
						<li><span class="label">授权流程名称:</span><input type="text" name="Q_defName_SL"  class="inputText" value="${param['Q_defName_SL']}"/></li>
						
						<li><span class="label">权限类型:</span>
								<select name="Q_authorizeType_S" class="select" value="${param['Q_authorizeType_S']}">
									<option value="">所有</option>
									<option value="start" <c:if test="${param['Q_authorizeType_S'] eq 'start'}">selected</c:if>>启动</option>
									<option value="management" <c:if test="${param['Q_authorizeType_S'] eq 'management'}">selected</c:if>>定义</option>
									<option value="task" <c:if test="${param['Q_authorizeType_S'] eq 'task'}">selected</c:if>>任务</option>
								<option value="instance" <c:if test="${param['Q_authorizeType_S'] eq 'instance'}">selected</c:if>>实例</option>
								</select>
						</<li>
						
						<li><span class="label">权限描述:</span><input type="text" name="Q_authorizeDesc_SL"  class="inputText" value="${param['Q_authorizeDesc_SL']}"/></li>
						
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
		    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="bpmDefAuthorizeList" id="bpmDefAuthorizeItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="id"  value="${bpmDefAuthorizeItem.id}" />
					</display:column>
					
					<display:column property="authorizeDesc" title="权限描述" sortable="true" sortName="authorizeDesc" ></display:column>
					
					<display:column title="授权人员名称"  style="width:350px;">
						<c:forEach items="${bpmDefAuthorizeItem.bpmDefUserList}" var="owner" varStatus="status">
					         <span class="owner-span">
					               <c:choose>
										<c:when test="${owner.rightType eq 'all'}">
											所有用户
										</c:when>					
										<c:when test="${owner.rightType eq 'user'}">
											<a class="moreinfo" ownerId="${owner.ownerId}" hrefstr="${ctx}/platform/system/sysUser/get.ht?openType=detail&userId=${owner.ownerId}" href="javascript:;">${owner.ownerName}</a>
										</c:when>
										<c:otherwise>
											${owner.ownerName}
										</c:otherwise>
							       </c:choose> 
							 </span> 
				 		</c:forEach>
				 		<c:if test="${fn:length(bpmDefAuthorizeItem.bpmDefUserList)<1}"><span class="owner-span">暂无授权人员</span></c:if>
				 	</display:column>
					
					<display:column title="流程名称"  style="width:300px;">
						<c:forEach items="${bpmDefAuthorizeItem.bpmDefActList}" var="def" varStatus="status">
							<span class="owner-span">
								<a class="definfo" defKeyId="${def.defKey}" hrefstr="${ctx}/platform/bpm/bpmDefAuthorize/actDetail.ht?defKey=${def.defKey}" href="javascript:;">${def.defName}</a>
							</span>
				 		</c:forEach>  
				 		<c:if test="${fn:length(bpmDefAuthorizeItem.bpmDefActList)<1}"><span class="owner-span">暂无授权流程</span></c:if>    	
				 	</display:column>
					
					<display:column title="授权类型"  style="width:80px;">
							
						<c:forEach items="${bpmDefAuthorizeItem.bpmDefAuthorizeTypeList}" var="defType" varStatus="status">
							<c:if test="${defType.authorizeType eq 'start'}">启动&nbsp;&nbsp;</c:if>
							<c:if test="${defType.authorizeType eq 'management'}">定义&nbsp;&nbsp;</c:if>
						  	<c:if test="${defType.authorizeType eq 'task'}">任务&nbsp;&nbsp;</c:if>
						  	<c:if test="${defType.authorizeType eq 'instance'}">实例&nbsp;&nbsp;</c:if>
				 		</c:forEach>  
				 		<c:if test="${fn:length(bpmDefAuthorizeItem.bpmDefAuthorizeTypeList)<1}"><span class="owner-span">暂无流程授权类型</span></c:if>    	

					</display:column>
					

					<display:column title="管理" media="html" style="width:50px;text-align:center;folat:left"  class="rowOps">
						<a href="edit.ht?id=${bpmDefAuthorizeItem.id}" class="link edit">编辑</a>
						<a href="del.ht?id=${bpmDefAuthorizeItem.id}" class="link del">删除</a>
						<a href="get.ht?id=${bpmDefAuthorizeItem.id}" class="link detail">详情</a>
					</display:column>
				</display:table>
				<hotent:paging tableId="bpmDefAuthorizeItem" />
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
	<div id="dialogCategory" style="width: 380px;display: none;">
	<div class="panel">
			<div class="panel-body">
				<form id="frmDel">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th style="width:150px;">设置分类:</th>
							<td>
								<input class="catComBo" catKey="FLOW_TYPE" valueField="typeId" catValue="" name="typeName" height="150" width="150"/>
							</td>
						</tr>
					</table>
				
				</form>
			</div>
		</div>
</div>
</body>
</html>


