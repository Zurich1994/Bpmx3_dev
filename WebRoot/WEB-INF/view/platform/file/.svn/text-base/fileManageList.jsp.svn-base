<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>文件管理</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	// 上传文件对话框
	function uploadFieldDialog() {
		var categoryId = '${categoryId}';
		$.ligerDialog.open({
			height : 600,
			width : 900,
			title : '上传文件',
			url : "uploadFieldDialog.ht?categoryId=" + categoryId,
			showMax : false,
			showMin : false,
			isResize : true,
			slide : true		
		});
	}

	// 共享编辑框
	function showEdit(fileId) {
		$.ligerDialog.open({
			height : 600,
			width : 900,
			title : '共享编辑框',
			url : "edit.ht?fileId=" + fileId,
			showMax : false,
			showMin : false,
			isResize : true,
			slide : true		
		});
	}
	// 文件预览
	function previewFile(fileId,fileName,ext) {
		var winArgs = "toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no,z-look=yes";
		var iWidth=800; //弹出窗口的宽度;
		var iHeight=600; //弹出窗口的高度;
		var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
		winArgs = winArgs + "height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft;
		var url = "${ctx}/platform/file/fileManage/previewFileDialog.ht?fileId=" + fileId+"&ext="+ext;
		url = url.getNewUrl();
		window.open (url,'newwindow',winArgs); 
	}
	// 刷新
	function reload() {
		window.location.href = location.href.getNewUrl();
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">文件管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link search" id="btnSearch"><span></span>查询</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link upload" href="javascript:uploadFieldDialog();"><span></span>上传文件</a>
					</div>
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label">文件名:</span><input type="text"
							name="Q_fileName_SL" class="inputText"
							value="${param['Q_fileName_SL']}" /></li>
						<li><span class="label">上传者:</span><input type="text"
							name="Q_creator_SL" class="inputText"
							value="${param['Q_creator_SL']}" />
						</li>
						<li><span class="label">扩展名:</span><input type="text"
							name="Q_ext_SL" class="inputText" value="${param['Q_ext_SL']}" />
						</li>
						<div class="row_date">
							<li><span class="label">创建时间 从:</span><input type="text"
								name="Q_begincreatetime_DL" class="inputText date"
								value="${param['Q_begincreatetime_DL']}" />
							</li>
							<li><span class="label">至: </span><input type="text"
								name="Q_endcreatetime_DG" class="inputText date"
								value="${param['Q_endcreatetime_DG']}" />
							</li>
						</div>
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
			<display:table name="fileManageList" id="fileManageItem"
				requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"
				class="table-grid">
				<display:column property="categoryName" title="分类"></display:column>
				<display:column property="fileName" title="文件名" sortable="true"
					sortName="fileName"></display:column>
				<display:column title="创建时间" sortable="true" sortName="createTime">
					<fmt:formatDate value="${fileManageItem.createTime}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</display:column>
				<display:column property="ext" title="扩展名" sortName="ext"
					sortable="true"></display:column>
				<display:column property="fileSize" title="大小" sortable="true"
					sortName="fileSize" maxLength="80"></display:column>
				<display:column property="creator" title="上传者"></display:column>
				<display:column title="管理" media="html"
					style="width:220px;text-align:center">
					<c:if test="${fileManageItem.creatorId eq loginerId}">
						<a href="del.ht?fileId=${fileManageItem.fileId}" class="link del">删除</a>
						<a href="javascript:showEdit('${fileManageItem.fileId }')"
							class="link edit">共享</a>
					</c:if>
					<c:if test="${fileManageItem.ext eq 'doc' or fileManageItem.ext eq 'docx' 
								or fileManageItem.ext eq 'xlsx'  or fileManageItem.ext eq 'xls' 
								or fileManageItem.ext eq 'pptx'  or fileManageItem.ext eq 'ppt'}">
						<a href="javascript:previewFile('${fileManageItem.fileId }','${fileManageItem.fileName }','${fileManageItem.ext }')"
							class="link preview">预览</a>
					</c:if>
					<a href="downLoadFile.ht?fileId=${fileManageItem.fileId }"
						target="_blank" class="link download">下载</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="fileManageItem" />
		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of panel -->
</body>
</html>


