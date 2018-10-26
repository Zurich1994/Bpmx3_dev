<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>用户表管理</title>
	<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript" src="${ctx}/js/util/form.js"></script>
	<script type="text/javascript">
	function openUserUnder(userid,obj){
		if($(obj).hasClass('disabled')) return false;
		 
		var conf={};				
		var url=__ctx + "/platform/system/userUnder/list.ht?userId="+userid;
		conf.url=url;
		var dialogWidth=800;
		var dialogHeight=600;
		conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);
		var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
			+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;				
		//var rtn=window.showModalDialog(url,"",winArgs);
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:conf.dialogHeight,
			width: conf.dialogWidth,
			title : '下属管理',
			url: url, 
			isResize: true,
		});
	}
	
	function syncToLdap(obj){
		
		var v=$(obj);
		
		if(v.hasClass("disabled")){
			$.ligerDialog.error('没有权限!');
			return;
		}
		var confirmContent="<font color='red'>与AD服务器同步会将AD的用户同步到系统数据库，您确定要进行同步吗？</font>";
		$.ligerDialog.confirm(confirmContent,function(data){
			if(data){
				sync();
			}else{
				return false;
			}
		});
		sync=function(conf){
			var url=__ctx + "/platform/system/sysUser/syncUser.ht";
			$.ligerDialog.waitting('正在同步AD用户，请等待...');
			$.post(url,function(data){
				$.ligerDialog.closeWaitting();
				var obj=new com.hotent.form.ResultMessage(data);
				if(obj.isSuccess()){
					$.ligerDialog.success("同步用户成功!","提示信息",function(){
						location.href=location.href.getNewUrl();
					});
				}
				else{
					$.ligerDialog.err("提示信息","同步用户失败!",obj.getMessage());
				}
			})
		};
	}
	
	</script>
</head>
<body>
<c:set var="SysUser_EXPIRED" value="<%=SysUser.EXPIRED %>" />
<c:set var="SysUser_UN_EXPIRED" value="<%=SysUser.UN_EXPIRED %>"  />

<c:set var="SysUser_LOCKED" value="<%=SysUser.LOCKED %>"/>
<c:set var="SysUser_UN_LOCKED" value="<%=SysUser.UN_LOCKED %>"/>
	<div class="panel">

			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">用户表管理列表</span>
				</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<f:a alias="searchUser" css="link search" id="btnSearch"><span></span>查询</f:a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<f:a alias="addUser" css="link add" href="edit.ht"><span></span>添加</f:a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<f:a alias="delUser" css="link del" action="del.ht"><span></span>删除</f:a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
					    <a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<f:a alias="syncToLdap" css="link reload" showNoRight="false" onclick="syncToLdap(this)"><span></span>同步</f:a>
					</div>
				</div>	
			</div>
			<div class="panel-search">
					<form id="searchForm" method="post" action="list.ht">
						<ul class="row">
							<li><span class="label">姓名:</span><input type="text" name="Q_fullname_SL"  class="inputText"  value="${param['Q_fullname_SL']}"/></li>				
						    <li><span class="label">账号:</span><input type="text" name="Q_account_SL"  class="inputText"  value="${param['Q_account_SL']}"/></li>
							<li><span class="label">来源:</span>
							<select name="Q_fromType_S" class="select" value="${param['Q_fromType_S']}">
								<option value="">--选择--</option>
								<option value="<%=SysUser.FROMTYPE_DB %>">系统添加</option>
								<option value="<%=SysUser.FROMTYPE_AD %>">AD同步</option>
							</select>
							</li>
							<li>
								<span class="label">状态:</span>
								<select name="Q_status_S" class="select"  value="${param['Q_status_S']}">
									<option value="">--选择--</option>
									<option value="<%=SysUser.STATUS_OK %>">激活</option>
									<option value="<%=SysUser.STATUS_NO %>">禁用</option>
									<option value="<%=SysUser.STATUS_Del %>">删除</option>
								</select>
							</li>
						</ul>
						<ul class="row">
							<div class="row_date">
								<li><span class="label">创建时间从:</span><input type="text" id="Q_begincreatetime_DL" name="Q_begincreatetime_DL"  class="inputText Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'Q_endcreatetime_DG\');}'})" value="${param['Q_begincreatetime_DL']}"/></li>
								<li><span class="label">至</span><input type="text" id="Q_endcreatetime_DG" name="Q_endcreatetime_DG"  class="inputText Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'Q_begincreatetime_DL\');}'})"  value="${param['Q_endcreatetime_DG']}"/></li>
							</div>
							<li><span class="label">是否锁定:</span>
								<select name="Q_isLock_S" class="select"  value="${param['Q_isLock_S']}">
									<option value="">--选择--</option>
									<option value="${SysUser_LOCKED}"  <c:if test="${param['Q_isLock_S'] == 'SysUser_LOCKED' }">selected</c:if>>已锁定 </option>
									<option value="${SysUser_UN_LOCKED}" <c:if test="${param['Q_isLock_S'] == 'SysUser_UN_LOCKED' }">selected</c:if>>未锁定 </option>
								</select>
							</li>
							<li><span class="label">是否过期:</span>	
								<select name="Q_isExpired_S" class="select"  value="${param['Q_isExpired_S']}">
									<option value="">--选择--</option>
								<option value="${SysUser_EXPIRED}"  <c:if test="${param['Q_isExpired_S'] == 'SysUser_EXPIRED'}">selected</c:if>>已过期 </option>
								<option value="${SysUser_UN_EXPIRED}" <c:if test="${param['Q_isExpired_S'] == 'SysUser_UN_EXPIRED' }">selected</c:if>>未过期 </option>
								</select>
							</li>
						</ul>
					</form>
			</div>

		</div>
		<div class="panel-body">
		    	<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="sysUserList" id="sysUserItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;text-align:center;">
						  	<input type="checkbox" class="pk" name="userId" value="${sysUserItem.userId}">
					</display:column>
					<display:column property="fullname" title="姓名" sortable="true" sortName="fullname" style="text-align:left"></display:column>
					<display:column property="account" title="帐号" sortable="true" sortName="account" style="text-align:left"></display:column>
					<display:column  title="创建时间" sortable="true" sortName="createtime">
						<fmt:formatDate value="${sysUserItem.createtime}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column title="是否过期" sortable="true" sortName="isExpired">
						<c:choose>
							<c:when test="${sysUserItem.isExpired==1}">
								<span class="red">已过期</span>
						   	</c:when>
					       	<c:otherwise>
						    	<span class="green">未过期</span>
						   	</c:otherwise>
						</c:choose>
					</display:column>
	                <display:column title="是否可用" sortable="true" sortName="isLock">
						<c:choose>
							<c:when test="${sysUserItem.isLock==1}">
								<span class="red">已锁定</span>
						   	</c:when>
					       	<c:otherwise>
					       		<span class="green">未锁定</span>
						   	</c:otherwise>
						</c:choose>
					</display:column>
                	<display:column title="状态" sortable="true" sortName="status">
						<c:choose>
							<c:when test="${sysUserItem.status==1}">
								<span class="green">激活</span>
								
						   	</c:when>
						   	<c:when test="${sysUserItem.status==0}">
						   		<span class="red">禁用</span>
								
						   	</c:when>
					       	<c:otherwise>
					       		<span class="red">删除</span>
						        
						   	</c:otherwise>
						</c:choose>
					</display:column>
					<display:column title="数据来源" sortable="true" sortName="fromType">
						<c:choose>
							<c:when test="${sysUserItem.fromType==1}">
								<span class="brown">AD</span>
								
						   	</c:when>
						   	<c:when test="${sysUserItem.fromType==0}">
						   		<span class="green">系统添加</span>
						   	</c:when>
					       	<c:otherwise>
					       		<span class="red">未知</span>
						   	</c:otherwise>
						</c:choose>
					</display:column>		
					
					<display:column title="管理" media="html" style="text-align:center;width:13%;" class="rowOps">
					    <f:a alias="userUnder" css="link primary" href="javascript:;" onclick="openUserUnder('${sysUserItem.userId}',this)">下属管理</f:a>
						<f:a alias="delUser" css="link del" href="del.ht?userId=${sysUserItem.userId}" >删除 </f:a>
						<f:a alias="updateUserInfo" css="link edit" href="edit.ht?userId=${sysUserItem.userId}" >编辑</f:a>
						<f:a alias="userInfo" css="link detail" href="get.ht?userId=${sysUserItem.userId}">明细</f:a>
						<f:a alias="setParams" css="link parameter" href="${ctx}/platform/system/sysUserParam/editByUserId.ht?userId=${sysUserItem.userId}" >参数属性</f:a>
						<f:a alias="resetPwd" css="link resetPwd" href="resetPwdView.ht?userId=${sysUserItem.userId}">重置密码</f:a>
						<f:a alias="setStatus" css="link setting" href="editStatusView.ht?userId=${sysUserItem.userId}">设置状态</f:a>
						<c:if test="${cookie.origSwitch==null  }">
							<f:a alias="switch" css="link switchuser" target="_top" href="${ctx}/j_spring_security_switch_user?j_username=${sysUserItem.account}" >切换用户</f:a>
						</c:if>
					</display:column>
				</display:table>
				<hotent:paging tableId="sysUserItem"/>
			
		</div>
	</div>
</body>
</html>


