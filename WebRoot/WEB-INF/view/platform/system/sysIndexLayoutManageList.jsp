<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>布局管理管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/sysObjRights/SysObjRightsUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysOrgTacticDialog.js"></script>
<script type="text/javascript">
	function design(id){
		var url = __ctx+"/platform/system/sysIndexLayoutManage/design.ht";
		if(id)
			url = __ctx+"/platform/system/sysIndexLayoutManage/design.ht?id="+id;
		$.openFullWindow(url);
	}
	function saveOrg(id,orgId){
		$.ajax({
			type : "POST",
			url : __ctx+"/platform/system/sysIndexLayoutManage/saveOrg.ht",
			data : {
				id:id,
				orgId:orgId
			},
			success : function(data) {
				var obj = eval('(' + data + ')');
				if (obj.result == 1) {
					$.ligerDialog.success("保存组织成功！","提示信息", function(rtn) {
						if(rtn)
							window.location.reload();
					});
				}
				
			}});
	}
	
	function selectOrg(id){
		var orgTactic = '${orgTactic}';
		SysOrgTacticDialog({
			orgTactic:orgTactic,
			callback:function(orgId, orgName){
				saveOrg(id,orgId);
			}
		});
	}
	
	function orgSelect(){
		var orgTactic = '${orgTactic}';
		SysOrgTacticDialog({
			orgTactic:orgTactic,
			callback:function(orgId, orgName){
				$('#orgId').val(orgId);
				$('#orgName').val(orgName);
			}
		});
	}

</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">布局管理管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="javascript:design();"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a href="javascript:;" class="link reset" onclick="$.clearQueryForm();"><span></span>重置</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li>
							<span class="label">布局名称:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL'] }" />
						</li>
						<li>
							<span class="label">布局描述:</span><input type="text" name="Q_memo_SL"  class="inputText" value="${param['Q_memo_SL'] }" />
						</li>
						<li>
							<span class="label">所属组织:</span>
							<input type="hidden" id="orgId" name="Q_orgId_L"  class="inputText" value="${param['Q_orgId_L'] }" />
							<input type="text" id="orgName" name="Q_orgName_S" class="inputText" value="${param['Q_orgName_S'] }" />
							<a href="javascript:void(0);" onclick="orgSelect()" class="button inputText"><span>...</span></a>

						</li>
						<li>
						<span class="label">是否是默认:</span>
						<select name="Q_isDef_SN" value="${param['Q_isDef_SN']}"  class="inputText">
							<option value="">请选择</option>
							<option value="1" <c:if test="${param['Q_isDef_SN'] == '1'}">selected</c:if>>是</option>	
							<option value="0" <c:if test="${param['Q_isDef_SN'] == '0'}">selected</c:if>>否</option>
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
		    <display:table name="sysIndexLayoutManageList" id="sysIndexLayoutManageItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysIndexLayoutManageItem.id}">
				</display:column>
				<display:column property="name" title="布局名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
				<display:column property="memo" title="布局描述" sortable="true" sortName="MEMO" maxLength="80"></display:column>
				<display:column property="orgName" title="所属组织" sortable="true" sortName="ORG_ID"></display:column>
				<display:column title="是否是默认" sortable="true" sortName="IS_DEF">
						<c:choose>
							<c:when test="${sysIndexLayoutManageItem.isDef==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">
					<a href="del.ht?id=${sysIndexLayoutManageItem.id}" class="link del">删除</a>
					<a  href="javascript:design(${sysIndexLayoutManageItem.id});" class="link edit">编辑</a>
							<a onclick="SysObjRightsUtil.setRights('${sysIndexLayoutManageItem.id}','${objType}');"  class="link detail">授权</a>
					<c:if test="${isSuperAdmin}">
						<a onclick="selectOrg('${sysIndexLayoutManageItem.id}');"  class="link setting">设置组织</a>
					</c:if>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysIndexLayoutManageItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


