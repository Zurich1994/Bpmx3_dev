<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<html>
<head>
<title>业务部署绑定管理</title>

<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
</head>
<<script type="text/javascript">
	function openQuotaDialog() {
		var select = document.getElementById('selectService');
		if(select.value==="-1")
		{
			alert("请先选择服务！");
		}else
		{
			CommonDialog("xzzxt",function(data){
				var param = "";
				var i = 0;
				for(i=0;i<data.length-1;i++) {
					param = param + data[i].SYSTEMID + ",";
					alert(data[i].SYSTEMID);
				}
				param = param + data[i].SYSTEMID;
				
				var nodeId = $("#nodeId").val();
				var service=select.value;
				$.post("${ctx}/Ywbsbd/Ywbsbd/ywbsbd/saveSubsystemRelation.ht",{service:service,defId:${defId},nodeId:nodeId,quotaids:param},function(result){
				window.location.href = "${ctx}/Ywbsbd/Ywbsbd/ywbsbd/list.ht?defId=${defId}&nodeId=${nodeId}";
						
				});
			},"");	
		}					
	}
	
	var win;
	function newFormDef(){
		var url=__ctx + '/platform/system/subSystem/list2.ht?categoryId=${categoryId}';
		win= DialogUtil.open({title:"选择子系统", url: url, height: 800 ,width:950 ,isResize: false,pwin:window });	
	}
	function FormTableDialogA(conf){
	
	var url=__ctx + '/platform/form/bpmFormTable/selector1.ht';
	if(conf.isExternal!=undefined){
		url+="?isExternal=" +conf.isExternal;
	}
	var winArgs="dialogWidth:800px;dialogHeight:500px;help:0;status:1;scroll:1;center:1;resizable:1";
	url=url.getNewUrl();
	
	var that =this;
	
	DialogUtil.open({
		height:500,
		width: 800,
		title : '选择表对话框',
		url: url, 
		isResize: true,
		//自定义参数
		sucCall:function(rtn){
			if(rtn!=undefined){
				if(conf.callBack)
					conf.callBack.call(that,rtn.tableId,rtn.tableName);
			}
		}
	});
}
</script>
<body >
<input id="nodeId" type="hidden" value="${nodeId}"/>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><br>业务部署绑定管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="group"><select type="select" id="selectService">
					 <%List<Map<String,Object>>  serivcetList=(List<Map<String,Object>>)request.getAttribute("serivcetList");%>
					 <option value="-1">请选择服务</option><%
					  for(int i=0;i<serivcetList.size();i++){%>
					  <option value=<%=serivcetList.get(i).get("ID")%>><%=serivcetList.get(i).get("F_servicename")%> </option>
					  <%}%>
					  </select>
					 </div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" onclick="openQuotaDialog()" ><span></span>部署</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">子系统id:</span><input type="text" name="Q_systemid_S"  class="inputText" />
						<span class="label">子系统名称:</span><input type="text" name="Q_sysname_S"  class="inputText" />
						<span class="label">子系统别名:</span><input type="text" name="Q_sysalias_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="ywbsbdList" id="ywbsbdItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${ywbsbdItem.id}">
				</display:column>
				<display:column property="sysname" title="子系统名称" sortable="true" sortName="F_SYSNAME"></display:column>
				<display:column property="sysalias" title="子系统别名" sortable="true" sortName="F_SYSALIAS"></display:column>
				<display:column property="sysmeno" title="备注" sortable="true" sortName="F_SYSMENO"></display:column>
				<display:column property="service" title="服务" sortable="true" sortName="F_SERVICE"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${ywbsbdItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${ywbsbdItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${ywbsbdItem.id}" class="link detail"><span></span>明细</a>
					<a href="getRelation.ht?systemId=${ywbsbdItem.systemId}&nodeId=${nodeId}&defId=${defId}" class="link search"><span></span>查询关联</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="ywbsbdItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


