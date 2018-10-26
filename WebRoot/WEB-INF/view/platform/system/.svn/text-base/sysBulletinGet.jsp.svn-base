
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@include file="/commons/include/getById.jsp"%>
<html>
<head>
<title>公告明细</title>
<%@include file="/commons/include/form.jsp"%>
<f:link href="article.css"></f:link>
<f:link href="form.css" ></f:link>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/AttachMent.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/HtmlUploadDialog.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/FlexUploadDialog.js" ></script>
<script type="text/javascript">
	$().ready(function (){
		AttachMent.init("r");
	});
	
	function FontZoom(size) {
		$("#read-con").css("font-size", size);
	}
	
</script>
</head>
<body>
	<div id="main" align="center">
		<!--网页正文开始-->
		<div class="content">
			<div class="read">
				<h1 class="title">${sysBulletin.subject}</h1>
				<div class="read-info">
					发布时间:
					<fmt:formatDate value="${sysBulletin.createtime}"
						pattern="yyyy-MM-dd" />
					<span>发布人:${sysBulletin.creator}</span> <span>字体:[<a
						href="javascript:FontZoom(18)">&nbsp;大&nbsp;</a> <a
						href="javascript:FontZoom(14)">&nbsp;中&nbsp;</a> <a
						href="javascript:FontZoom(12)">&nbsp;小&nbsp;</a>]</span>
				</div>
				<!--文章块开始-->
				<div id="read-con">
					<div style="padding-bottom: 10px;" align="left">${sysBulletin.content}</div>
				</div>
				<!-- 附件 -->
				<div style="padding-bottom: 10px;" align="left">
					<div name="div_attachment_container">
						<div class="attachement" title="bulletinGet"></div>
						<textarea style="display: none" controltype="attachment"
							name="attachment" lablename="附件" validate="{}">${sysBulletin.attachment}</textarea>
					</div>
				</div>
				<!--文章块结束-->
			</div>
		</div>
		<!--底部版权开始-->
		<div class="footer">
			<p>
				Copyright ?2015 <a href="" title="@@@@@@@@@@">深圳海雅</a> 版权所有
				沪ICP证@@@@@@@号
			</p>
		</div>
		<!--底部版权结束-->
	</div>
</body>
</html>

