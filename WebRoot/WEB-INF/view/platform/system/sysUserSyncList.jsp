<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>AD用户管理</title>
	<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
	<style type="text/css">
		#adError{
			display: block;
			height:100px;
			margin:auto;
			border-radius:0px 0px 5px 0px #FFFFFF;
			background-image: -moz-linear-gradient(top, #AAAAAA 0%,#CCCCCC 50%,#AAAAAA 100%);
			background-image: -o-linear-gradient(top, #AAAAAA 0%,#CCCCCC 50%,#AAAAAA 100%);
			background-image: -webkit-linear-gradient(top, #AAAAAA 0%,#CCCCCC 50%,#AAAAAA 100%);
			background-color: #AAAAAA;
			text-align: center;
			font-style:italic;
			font-size:medium;
			color:red;
		}
	</style>
	<script type="text/javascript">
	function openUserUnder(userid,obj){
		if($(obj).hasClass('disabled')) return false;
		 
		var conf={};				
		var url=__ctx + "/platform/system/userUnder/list.ht?userId="+userid;
		conf.url=url;
		var dialogWidth=550;
		var dialogHeight=450;
		conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);
		var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
			+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;				
		//var rtn=window.showModalDialog(url,"",winArgs);
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:conf.dialogHeight,
			width: conf.dialogWidth,
			title : '管理分页列表',
			url: url, 
			isResize: true,
		});
	}
	
	function syncToLdap(){
		var confirmContent="<font color='red'>与AD服务器进行同步会根据配置的所使用的策略对来自用户数据进行增、删、改，您确定要进行同步吗？</font>";
		$.ligerDialog.confirm(confirmContent,function(data){
			if(data){
				this.sync();
			}else{
				return false;
			}
		});
		this.sync=function(conf){

			var url=__ctx + "/platform/system/sysUser/syncToLdap.ht";


			var win = $.ligerDialog.open({title:'与AD服务器进行用户数据同步结果',
										url: url,
										width:600,
										height:300,
										isResize: true ,
										showMax:true,
										slide:true,
										buttons: [{ text: '确定', onclick: function(){
											window.location.href=window.location.href;								
											} }]
										});
			win.show();
						
		};

	}
	</script>
</head>
<body>
	<c:choose>
		<c:when test="${connectable}">
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">AD用户管理列表</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<f:a alias="searchUser" css="link search" id="btnSearch"><span></span>查询</f:a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<f:a alias="delUser" css="link del" action="del.ht"><span></span>删除</f:a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<f:a alias="syncToLdap" css="link reload" onclick="syncToLdap()"><span></span>同步</f:a>
							</div>
						</div>	
					</div>
				<div class="panel-search">
							<form id="searchForm" method="post" action="syncList.ht">
									<ul class="row">
										<li><span class="label">姓名:</span><input type="text" name="Q_fullname_SL"  class="inputText" style="width:9%" value="${param['Q_fullname_SL']}"/>		</li>			
										<li><span class="label">创建时间从:</span><input type="text" name="Q_begincreatetime_DL"  class="inputText date" style="width:9%" value="${param['Q_begincreatetime_DL']}"/></li>
										<li><span class="label">至</span><input type="text" name="Q_endcreatetime_DG"  class="inputText date" style="width:9%" value="${param['Q_endcreatetime_DG']}"/></li>
										<li><span class="label">是否过期:</span>	
										<select name="Q_isExpired_S" class="select" style="width:8%;">
											<option value="">--选择--</option>
											<option value="<%=SysUser.EXPIRED %>">是</option>
											<option value="<%=SysUser.UN_EXPIRED %>">否</option>
										</select></li>
										<li><span class="label">是否锁定:</span>
										<select name="Q_isLock_S" class="select" style="width:8%;">
											<option value="">--选择--</option>
											<option value="<%=SysUser.LOCKED %>">是</option>
											<option value="<%=SysUser.UN_LOCKED %>">否</option>
										</select></li>
										<li><span class="label">状态:</span>
										<select name="Q_status_S" class="select" style="width:8%;">
											<option value="">--选择--</option>
											<option value="<%=SysUser.STATUS_OK %>">激活</option>
											<option value="<%=SysUser.STATUS_NO %>">禁用</option>
											<option value="<%=SysUser.STATUS_Del %>">删除</option>
										</select></li>
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
			    	<c:set var="checkAll">
						<input type="checkbox" id="chkall"/>
					</c:set>
				    <display:table name="sysUserMapList" id="sysUserMapItem" requestURI="syncList.ht" sort="external" cellpadding="1" cellspacing="1"    class="table-grid">
				    
				    	<c:set value="${sysUserMapItem['sysUser']}" var="sysUserItem"></c:set>
				    	
						<display:column title="${checkAll}" media="html" style="width:30px;text-align:center;">
							  	<input type="checkbox" class="pk" name="userId" value="${sysUserItem.userId}">
						</display:column>
						<display:column  title="姓名" sortable="true" sortName="fullname" style="text-align:left">
							<span>${sysUserItem.fullname}</span>
						</display:column>
						<display:column title="帐号" sortable="true" sortName="account" style="text-align:left">
							<span>${sysUserItem.account}</span>
						</display:column>
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
						<display:column title="同步状态" >
							<c:choose>
								<c:when test="${sysUserMapItem['sync']==0}">
									<span class="green">已同步</span>
							   	</c:when>
							   	<c:when test="${sysUserMapItem['sync']==1}">
							   		<span class="brown">不同步</span>
							   	</c:when>
							   	<c:when test="${sysUserMapItem['sync']==-1}">
							   		<span class="brown">AD已删除</span>
							   	</c:when>
						       	<c:otherwise>
						       		<span class="red">未知</span>
							   	</c:otherwise>
							</c:choose>
						</display:column>		
						<display:column title="管理" media="html" style="width:550px;text-align:center;">
						    <f:a alias="userUnder" css="link primary" href="javascript:;" onclick="openUserUnder('${sysUserItem.userId}',this)">下属管理</f:a>
							<f:a alias="delUser" css="link del" href="del.ht?userId=${sysUserItem.userId}">删除</f:a>
							<f:a alias="updateUserInfo" css="link edit" href="edit.ht?userId=${sysUserItem.userId}">编辑</f:a>
							<f:a alias="userInfo" css="link detail" href="get.ht?userId=${sysUserItem.userId}">明细</f:a>
							<f:a alias="setParams" css="link parameter" href="${ctx}/platform/system/sysUserParam/editByUserId.ht?userId=${sysUserItem.userId}" >参数属性</f:a>
							<f:a alias="resetPwd" css="link resetPwd" href="resetPwdView.ht?userId=${sysUserItem.userId}">重置密码</f:a>
							<f:a alias="setStatus" css="link setting" href="editStatusView.ht?userId=${sysUserItem.userId}">设置状态</f:a>
<%-- 								<f:a alias="ldapComp" css="link detail" href="getLdapComp.ht?userId=${sysUserItem.userId}">AD明细</f:a> --%>
							<f:a alias="syncLdap" css="link reload" href="syncLdap.ht?userId=${sysUserItem.userId}">同步</f:a>
						</display:column>
					</display:table>
					<hotent:paging tableId="sysUserMapItem"/>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div id="adError">
				<BR/>
				<p>连接AD服务器失败</p>
				<p>请检查连接配置参数！</p>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>


