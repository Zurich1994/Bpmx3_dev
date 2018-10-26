<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>考勤档案管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>

<script>
function selectUser(){
	UserDialog({isSingle:true,callback:function(userId,userName,emails,mobiles,rtn){
		$('#userName').val(userName);
	}});
}


function addSelect(){
	var $aryId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
	
	if($aryId.length == 0){
		$.ligerDialog.warn("请选择记录！");
		return false;
	}
	
	var aryId="",len=$aryId.length;
	$aryId.each(function(i){
		var val=$(this).val();
		if(i<len-1){
			aryId+=val +",";
		}else{
			aryId+=val;
		}
	});
	
	add(aryId);
}

function addAll(){
	add();
}

function add(aryId){
	$.ligerDialog.confirm("是否按默认配置新增？","提示信息",function(rtn) {
		if (rtn) {
			$.ligerDialog.waitting("请稍后……");
			$.ajax({
				type : "POST",
				url : __ctx + "/platform/ats/atsAttendanceFile/saveAdd.ht",
				data : {
					aryId : aryId
				},
				success : function(data) {
					$.ligerDialog.closeWaitting();
					var d = $.parseJSON(data);
					if(d.success){
						$.ligerDialog.alert("新增考勤档案成功！","提示信息",function(rtn) {
							window.location.href = window.location.href;
						});
					}else{
						$.ligerDialog.error(d.msg);
					}
				}
			});
			
		}else{
			var url=__ctx + '/platform/ats/atsAttendanceFile/setting.ht';
			url=url.getNewUrl();
			DialogUtil.open({
				height:400,
				width: 800,
				title : '设置新增配置',
				url: url, 
				isResize: true,
				 //自定义参数
				params: aryId,
				 //回调函数
		        sucCall:function(rtn){
		        	if(rtn)
		        		window.location.href = window.location.href;
		        }
			});
		}
	});
}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" id="add" onclick="addSelect();"><span></span>新增选择</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" id="addAll"  onclick="add();" ><span></span>新增所有</a></div>
					<div class="l-bar-separator"></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="${ctx}/platform/ats/atsAttendanceFile/list.ht"><span></span>返回</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="disUser.ht">
					<ul class="row">
						<li>
							<span class="label">用户:</span>
							<input type="hidden" id="userId" name="Q_userId_L"  value="${param['Q_userId_L']}" />
							<input type="text" id="fullName" name="Q_fullName_SL" value="${param['Q_fullName_SL']}"   readonly="readonly" />
							<a href="javascript:void(0);" onclick="selectUser()" class="button inputText"><span>选 择...</span></a>
						</li>
						<li>			
							<span class="label">组织:</span>
							<input type="hidden" id="orgId" name="Q_orgId_L"  value="${param['Q_orgId_L']}" />
							<input type="text" name="Q_orgName_SL"  class="inputText" value="${param['Q_orgName_SL']}" readonly="readonly"/>
						</li>
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysUserList" id="sysUserItem" requestURI="disUser.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="userId" value="${sysUserItem.userId}">
				</display:column>
				<display:column property="fullname" title="姓名" sortable="true" sortName="USER_ID"></display:column>
				<display:column property="account" title="员工编号" sortable="true" sortName="ACCOUNT"></display:column>
				<display:column property="orgName" title="所属组织" sortable="true" sortName="orgName" maxLength="80"></display:column>
	
			</display:table>
			<hotent:paging tableId="sysUserItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


