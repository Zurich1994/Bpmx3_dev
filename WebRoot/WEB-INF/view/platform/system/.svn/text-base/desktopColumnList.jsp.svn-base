<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>桌面栏目管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
function previewTemplate(url){
	var winArgs="dialogWidth=400px;dialogHeight=400px;help=0;status=0;scroll=1;center=1" ;
	url=url.getNewUrl();
	//var rtn=window.showModalDialog(url,null,winArgs);
	/*KILLDIALOG*/
	DialogUtil.open({
		height:400,
		width: 400,
		title : '桌面栏目管理',
		url: url, 
		isResize: true,
	});
}


//处理初始化模板
function handlerInit()
{
	$("a.link.init").click(function(){
		var action=$(this).attr("action");
		if($(this).hasClass('disabled')) return false;
		
		$.ligerDialog.confirm('初始化表单模板将会从文件系统中加载栏目模版，确定初始化吗？','提示信息',function(rtn){
			if(rtn){
				var form=new com.hotent.form.Form();
				form.creatForm('form', action);
				form.submit();
			}
		});
		
	});
}

$(function(){
	handlerInit();
})
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">桌面栏目管理列表</span>
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
					<div class="group"><a  class="link init" id="bntInit" action="init.ht"><span></span>初始化模板</a></div>
				</div>	
			</div>
			<div class="panel-search">
					<form id="searchForm" method="post" action="list.ht">
							<ul class="row">
								<li><span class="label">栏目名称:</span><input type="text" name="Q_columnName_SL"  class="inputText" value="${param['Q_columnName_SL']}"/></li>
							    <li><span class="label">模板名称:</span><input type="text" name="Q_templateName_SL"  class="inputText" value="${param['Q_templateName_SL']}"/></li>
							</ul>
					</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
				<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="desktopColumnList" id="desktopColumnItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
					<input type="checkbox" class="pk" name="id" value="${desktopColumnItem.id}">
					</display:column>
					<display:column property="columnName" title="栏目名称" sortable="true" sortName="name"></display:column>
					<display:column title="数据加载方式" sortable="true" sortName="methodType">
						<c:choose>
							<c:when test="${desktopColumnItem.methodType==0}">
								<span class="red">service方法调用</span>
							</c:when>
							<c:otherwise>
								<span class="green">自定义查询方式</span>
							</c:otherwise>
						</c:choose>
					</display:column>
					<display:column property="columnUrl" title="更多路径" sortable="true" sortName="columnUrl"></display:column>
					<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">
						<a href="del.ht?id=${desktopColumnItem.id}" class="link del">删除</a>
						<a href="edit.ht?id=${desktopColumnItem.id}" class="link edit">编辑</a>
						<a href="get.ht?id=${desktopColumnItem.id}" class="link detail">明细</a>
						<a href="javascript:;" onclick="previewTemplate(__ctx+'/platform/system/desktopColumn/getTemp.ht?id=${desktopColumnItem.id}');" class="link preview">预览</a>
					</display:column>
				</display:table>
				<hotent:paging tableId="desktopColumnItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


