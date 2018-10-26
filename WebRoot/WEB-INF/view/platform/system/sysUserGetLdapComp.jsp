
<%--
	time:2012-09-21 11:37:20
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>用户信息在系统与AD中明细</title>
<%@include file="/commons/include/getById.jsp"%>
<style type="text/css">
	.unset{
		background-color: gray;
	}
</style>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">用户详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="syncList.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%"></th>
				<th width="40%">AD数据</th>
				<th width="40">本地系统数据</th>
			</tr>
			<tr>
				<th >CN:</th>
				<td>${ldapUser.cn}</td>
				<td class="unset"></td>
			</tr>
			<tr>
				<th >RDN:</th>
				<td>${ldapUser.name}</td>
				<td class="unset"></td>
			</tr>
			<tr>
				<th >DN:</th>
				<td>${ldapUser.distinguishedName}</td>
				<td class="unset"></td>
			</tr>
			<tr>
				<th >姓名:</th>
				<td>${ldapUser.sn}${ldapUser.givenName}</td>
				<td>${sysUser.fullname}</td>
			</tr>
			<tr>
				<th >账号:</th>
				<td>${ldapUser.sAMAccountName}</td>
				<td>${sysUser.account}</td>
			</tr>
			<tr>
				<th >登录账号:</th>
				<td>${ldapUser.userPrincipalName}</td>
				<td class="unset"></td>
			</tr>
			<tr>
				<th >电话:</th>
				<td>${ldapUser.homePhone}</td>
				<td>${sysUser.phone}</td>
			</tr>
			<tr>
				<th >英文名:</th>
				<td>${ldapUser.initials}</td>
				<td class="unset"></td>
			</tr>
			<tr>
				<th >传真:</th>
				<td>${ldapUser.facsimileTelephoneNumber}</td>
				<td class="unset"></td>
			</tr>
			<tr>
				<th >邮件:</th>
				<td>${ldapUser.mail}</td>
				<td>${sysUser.email}</td>
			</tr>
			<tr>
				<th >手机:</th>
				<td>${ldapUser.telephoneNumber}</td>
				<td>${sysUser.mobile}</td>
			</tr>
			<tr>
				<th >更新时间:</th>
				<td><fmt:formatDate value="${ldapUser.whenChanged}" pattern="yyyy-MM-dd"/></td>
				<td class="unset"></td>
			</tr>
 
			<tr>
				<th >创建时间:</th>
				<td><fmt:formatDate value="${ldapUser.whenCreated}" pattern="yyyy-MM-dd"/></td>
				<td>${f:shortDate(sysUser.createtime)}</td>
			</tr>
 
			<tr>
				<th >个人网页:</th>
				<td>${ldapUser.wWWHomePage}</td>
				<td class="unset"></td>
			</tr>
 
			<tr>
				<th >账号状态:</th>
				<td>
					<c:choose>
						<c:when test="${!ldapUser.accountDisable}">
							<span class="green">激活</span>
					   	</c:when>
					   	<c:otherwise>
					   		<span class="red">禁用</span>
					   	</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${sysUser.status==1}">
							<span class="green">激活</span>
					   	</c:when>
					   	<c:when test="${sysUser.status==0}">
					   		<span class="red">禁用</span>
					   	</c:when>
				       	<c:otherwise>
				       		<span class="red">删除</span>
					   	</c:otherwise>
					</c:choose>
				</td>
			</tr>
				<tr>
					<th>性别:</th>
					<td class="unset"></td>
					<td><c:choose>
							<c:when test="${sysUser.sex==1}">
								         男
							   	</c:when>
							<c:otherwise>
							                 女       
							</c:otherwise>
						</c:choose></td>
					</tr>
					<tr>
						<th>是否锁定:</th>
						<td class="unset"></td>
						<td>
							<c:choose>
								<c:when test="${sysUser.isLock==1}">
									         已锁定
								 </c:when>
								<c:otherwise>
								                 未锁定       
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th style="height: 48px !important">是否过期:</th>
						<td class="unset"></td>
						<td><c:choose>
								<c:when test="${sysUser.isExpired==1}">
									         已过期
								   	</c:when>
								<c:otherwise>
								                   未过期       
								   	</c:otherwise>
							</c:choose>
						</td>
					</tr>
		</table>
		</div>
		
	</div>
</body>
</html>