<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>首页栏目管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/sysObjRights/SysObjRightsUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysOrgTacticDialog.js"></script>

<script type="text/javascript">
	$(function(){
		handlerInit();
	});
	function previewTemplate(id,columnName){
		var url = __ctx+"/platform/system/sysIndexColumn/getTemp.ht?id="+id;
		url=url.getNewUrl();
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:500,
			width: 600,
			title : columnName,
			url: url
		});
	}
	function setRights(columnId){
		//var url = __ctx+"/platform/system/sysIndexColumnRights/set.ht?columnId="+columnId;
		//url=url.getNewUrl();
		var url = __ctx+"/platform/system/sysObjRights/edit.ht?objectId="+columnId+"&objType=sic";
		DialogUtil.open({
			height:500,
			width: 600,
			title : "设置授权",
			url: url
		});
	}
	function saveOrg(id,orgId){
		$.ajax({
			type : "POST",
			url : __ctx+"/platform/system/sysIndexColumn/saveOrg.ht",
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
	
	
	//处理初始化模板
    function handlerInit()
    {
    	$("a.link.init").click(function(){
    		var action=$(this).attr("action");
    		if($(this).hasClass('disabled')) return false;
    		
    		$.ligerDialog.confirm('是否确定初始化模版吗？','提示信息',function(rtn){
    			if(rtn){
    				var form=new com.hotent.form.Form();
    				form.creatForm('form', action);
    				form.submit();
    			}
    		});
    		
    	});
    }

</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">首页栏目管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a  class="link init" action="init.ht"><span></span>初始化模板</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a href="javascript:;" class="link reset" onclick="$.clearQueryForm();"><span></span>重置</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">栏目名称:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL'] }" />
						<span class="label">栏目别名:</span><input type="text" name="Q_alias_SL"  class="inputText" value="${param['Q_alias_SL'] }" />
						<span class="label">是否刷新:</span>
								<select name="Q_supportRefesh_S" class="select" ">
									<option value="">--选择--</option>
									<option value="1" <c:if test="${param['Q_supportRefesh_S'] == '1' }">selected</c:if>>是</option>
									<option value="0" <c:if test="${param['Q_supportRefesh_S'] == '0' }">selected</c:if>>否</option>
								</select>
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysIndexColumnList" id="sysIndexColumnItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysIndexColumnItem.id}">
				</display:column>
				<display:column property="name" title="栏目名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
				<display:column property="alias" title="栏目别名" sortable="true" sortName="ALIAS" maxLength="80"></display:column>
				<display:column  title="数据加载方式" sortable="true" sortName="DATA_MODE">
							<c:choose>
							<c:when test="${sysIndexColumnItem.dataMode==0}">
								<span class="red">service方法调用</span>
							</c:when>
							<c:otherwise>
								<span class="green">自定义查询方式</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column property="dataFrom" title="数据来源" sortable="true" sortName="DATA_FROM" maxLength="80"></display:column>
				<display:column  title="公共栏目" sortable="true" sortName="IS_PUBLIC">
						<c:choose>
							<c:when test="${sysIndexColumnItem.isPublic==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column  title="是否刷新" sortable="true" sortName="SUPPORT_REFESH">
						<c:choose>
							<c:when test="${sysIndexColumnItem.supportRefesh==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column property="orgName" title="所属组织" sortable="true" sortName="ORG_ID"></display:column>
				<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">
					<a href="del.ht?id=${sysIndexColumnItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${sysIndexColumnItem.id}" class="link edit">编辑</a>
					<a onclick="previewTemplate('${sysIndexColumnItem.id}','${sysIndexColumnItem.name}');"  class="link detail">预览</a>
					<a onclick="SysObjRightsUtil.setRights('${sysIndexColumnItem.id}','${objType}');"  class="link detail">授权</a>
					<c:if test="${isSuperAdmin&&sysIndexColumnItem.isPublic==0 }">
						<a onclick="selectOrg('${sysIndexColumnItem.id}');"  class="link setting">设置组织</a>
					</c:if>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysIndexColumnItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


