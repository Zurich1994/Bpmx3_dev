<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>附件管理</title>
<style type="text/css">
.file_name{
	text-decoration:none;
	color:black;
}
</style>
<%@include file="/commons/include/get.jsp" %>
<%-- <link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" /> --%>
<f:link href="jquery.qtip.css"></f:link>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ImageQtip.js" ></script>
<script type="text/javascript">
	$(function() {
		$("a.file_name").each(function() {
			var path = $(this).attr("path");			
			//图片类型
			if (/\w+.(png|gif|jpg)/gi.test(path)) {
				ImageQtip.drawImg(this,"${ctx}/"+path,{maxWidth:265});
			}
		});
	});
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">附件管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
				</div>	
			</div>
			<div class="panel-search">
					<form id="searchForm" method="post" action="list.ht">
							<ul class="row">
										<li><span class="label">文件名:</span><input type="text" name="Q_fileName_SL"  class="inputText"  value="${param['Q_fileName_SL']}" />	</li>											
										<li><span class="label">上传者:</span><input type="text" name="Q_creator_SL"  class="inputText"  value="${param['Q_creator_SL']}"/></li>
										<li><span class="label">扩展名:</span><input type="text" name="Q_ext_SL"  class="inputText"   value="${param['Q_ext_SL']}"/></li>									
										<div class="row_date">
										<li><span class="label">创建时间 从:</span><input type="text"  name="Q_begincreatetime_DL"  class="inputText date" value="${param['Q_begincreatetime_DL']}"/></li>
										<li><span class="label">至: </span><input type="text" name="Q_endcreatetime_DG" class="inputText date" value="${param['Q_endcreatetime_DG']}"/></li>
										</div>
							</ul>
					</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
			
			
		    	<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="sysFileList" id="sysFileItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="fileId" value="${sysFileItem.fileId}">
					</display:column>
					<display:column media="html" title="文件名" sortable="true" sortName="fileName">
						<a href="javascript:;" class="file_name" path="${sysFileItem.filePath}">${sysFileItem.fileName}</a>
					</display:column>
					<display:column title="创建时间" sortable="true" sortName="createtime">
						<fmt:formatDate value="${sysFileItem.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</display:column>
					<display:column property="ext" title="扩展名" sortName="ext" sortable="true"></display:column>							
				    <display:column property="note" title="大小" sortable="true" sortName="note" maxLength="80"></display:column>
					<display:column property="creator" title="上传者"  ></display:column>
					<display:column title="管理" media="html" style="width:180px;text-align:center">
						<f:a alias="delFile" href="del.ht?fileId=${sysFileItem.fileId}" css="link del">删除</f:a>
						<a href="get.ht?fileId=${sysFileItem.fileId}" class="link detail">明细</a>
		                <c:choose>
							<c:when test="${sysFile.delFlag eq 1}"><font color="red">已删除</font></c:when>
							<c:otherwise><a href="download.ht?fileId=${sysFileItem.fileId }" target="_blank" class="link download">下载</a></c:otherwise>
						</c:choose>
					</display:column>
				</display:table>
				<hotent:paging tableId="sysFileItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


