<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>文件上传管理</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<f:link href="web.css" ></f:link>
<f:link href="form.css" ></f:link>
<f:js pre="js/lang/common" ></f:js>
<f:js pre="js/lang/js" ></f:js>
<f:link href="Aqua/css/ligerui-all.css" ></f:link>
<f:link href="Aqua/css/easyui-all.css"></f:link> 
<f:link href="easyui/easyui.css"></f:link> 
<script type="text/javascript" src="${ctx}/js/dynamic.jsp"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/js/util/util.js"></script>
<script type="text/javascript" src="${ctx}/js/util/json2.js"></script>
<script type="text/javascript" src="${ctx}/js/util/form.js"></script>

<script type="text/javascript" src="${ctx}/js/easyui/jquery.easyui.min.js"></script>
<f:js pre="js/lang/easyui" ></f:js>

<script type="text/javascript" src="${ctx}/js/lg/base.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMenu.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMenuBar.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js" ></script>

<script type="text/javascript" src="${ctx}/js/lg/util/DialogUtil.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/OfficeControl.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/OfficePlugin.js"></script>
<c:set var="rights" value="${ param.rights}" />

<c:if test="${rights!='w' }">
	<c:set var="rights" value="r"></c:set>
</c:if>	
<script type="text/javascript">
	$().ready(function (){
		getOffice();
		
		$("#saveOffice").click(function(){
			OfficePlugin.submit();
		})
		
	});
	// 获取Office 控件
	function getOffice(){
		OfficePlugin.init();
		var path= "${ctx}/platform/system/sysFile/file_${param.fileId}.ht";
		try{
			OfficePlugin.officeObjs[0].controlObj.OpenFromURL(path);
			OfficePlugin.officeObjs[0].setFileReadOnly(false);
		}
		catch(err){
			alert(err);
		}
	};
</script>
</head>
<body>
<div class="panel" style="height:100%;">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar" style="height:30px;">
					<c:if test="${ rights=='w'}">
						<div class="group"><a id="saveOffice" class="link2 save" href="#"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					</c:if>
					
					<div class="group"><a id="downLoad" class="link2" href="${ctx}/platform/system/sysFile/file_${param.fileId}.ht"><span></span>下载</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body" style="height:90%;">
			<c:choose>
				<c:when test="${ rights=='w'}">
					<input type="hidden" class="hidden" name="${param.fileId}" value="${param.fileId}" lablename="filePreview" controltype="office" right="w" doctype="${ext }"
			menuRight="{wjRight:'y',lhRight:'y',blhRight:'y',qchjRight:'y',mbthRight:'y',xzmbRight:'y',sxqmRight:'n',gzRight:'n',qpRight:'n',zcpdfRight:'n',ekeygzRight:'n',pdfgzRight:'n'}"
			 />
				</c:when>
				<c:otherwise>
					<input type="hidden" class="hidden" name="${param.fileId}" value="${param.fileId}" lablename="filePreview" controltype="office" right="r" doctype="${ext }"
			menuRight="{wjRight:'y',lhRight:'n',blhRight:'n',qchjRight:'n',mbthRight:'n',xzmbRight:'n',sxqmRight:'n',gzRight:'n',qpRight:'n',zcpdfRight:'n',ekeygzRight:'n',pdfgzRight:'n'}"
			 />	
				</c:otherwise>
				
			</c:choose>
			
		</div>
</div>

	
</body>
</html>
