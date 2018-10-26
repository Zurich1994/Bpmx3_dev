<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title>短信邮件模板选择</title>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

	window.name="win";
	$(function(){
		jQuery.highlightTableRows();
	});
	function selectTemplate(t){
		
		var me = $(this),
			td = me.parents("td"),
			span = td.find("span[name='"+t+"']");

		//var conf=window.dialogArguments;			
		var conf=dialog.get('conf');
		if(conf.isText){
			//window.returnValue=span.text();
			dialog.get('sucCall')(span.text());
		}
		else{
			alert(span.text());
			//window.returnValue=span.html();
			dialog.get('sucCall')(span.html());
		}
		dialog.close();
	}
</script>
<style type="text/css">
.panel{
	height:100%;width:100%;overflow:auto;
}
</style>
</head>
<body>
<div class="panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">短信邮件模板选择</span>
				</div>
			</div>
		<div class="panel-body">
			   <display:table name="sysTemplateList" id="sysTemplateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">											
				<display:column property="name" titleKey="模板名称" ></display:column>
				<display:column titleKey="使用类型">
					<c:choose>
						<%--
						<c:when test="${sysTemplateItem.useType eq 1}">终止</c:when>
						<c:when test="${sysTemplateItem.useType eq 2}">催办</c:when>
						<c:when test="${sysTemplateItem.useType eq 3}">任务通知</c:when>
						<c:when test="${sysTemplateItem.useType eq 4}">撤销</c:when>
						<c:when test="${sysTemplateItem.useType eq 5}">取消转办</c:when>
						<c:when test="${sysTemplateItem.useType eq 6}">沟通</c:when>
						<c:when test="${sysTemplateItem.useType eq 7}">通知发起人</c:when>
						<c:when test="${sysTemplateItem.useType eq 8}">转办</c:when>
						<c:when test="${sysTemplateItem.useType eq 9}">驳回</c:when>
						<c:when test="${sysTemplateItem.useType eq 10}">驳回到发起人</c:when>
						<c:when test="${sysTemplateItem.useType eq 11}">追回</c:when>
						<c:when test="${sysTemplateItem.useType eq 12}">抄送</c:when>
						<c:when test="${sysTemplateItem.useType eq 13}">流程节点无人员</c:when>
						<c:when test="${sysTemplateItem.useType eq 14}">任务预警</c:when>
						<c:otherwise>其他</c:otherwise>
						 --%>
						<c:when test="${sysTemplateItem.useType eq 1}">终止提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 2}">催办提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 3}">审批提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 4}">撤销提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 5}">取消转办</c:when>
						<c:when test="${sysTemplateItem.useType eq 6}">沟通提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 7}">归档提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 8}">转办提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 9}">退回提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 10}">被沟通人提交</c:when>
						<c:when test="${sysTemplateItem.useType eq 11}">取消代理</c:when>
						<c:when test="${sysTemplateItem.useType eq 12}">抄送提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 13}">流程节点无人员</c:when>
						<c:when test="${sysTemplateItem.useType eq 14}">跟进事项预警</c:when>
						<c:when test="${sysTemplateItem.useType eq 15}">跟进事项到期</c:when>
						<c:when test="${sysTemplateItem.useType eq 16}">跟进事项完成&评价</c:when>
						<c:when test="${sysTemplateItem.useType eq 17}">跟进事项通知</c:when>
						<c:when test="${sysTemplateItem.useType eq 18}">跟进事项已评价知会</c:when>
						<c:when test="${sysTemplateItem.useType eq 19}">逾期提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 22}">代理提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 23}">消息转发</c:when>
						<c:when test="${sysTemplateItem.useType eq 29}">补签提醒</c:when>
						<c:otherwise>其它</c:otherwise>
					</c:choose>
				</display:column>
				<display:column titleKey="选择器">
					<a href="javascript:;" onclick="selectTemplate.call(this,'html')" class="link edit">html富文本</a><br>
					<span name="html" class="text">${sysTemplateItem.htmlContent}</span><br>
					<a href="javascript:;" onclick="selectTemplate.call(this,'plain')" class="link edit">普通文本</a><br>
					<span name="plain" class="text">${sysTemplateItem.plainContent}</span>
				</display:column>
			</display:table>
		</div>			
	</div>
</body>
</html>



<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>信息内容模板选择</title>
	<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript">
		window.name="win";
		$(function(){
			jQuery.highlightTableRows();
		});
		function selectTemplate(obj){		
			var conf=window.dialogArguments;			
			if(conf.isText){
				window.returnValue=$(obj).prev().text();
			}
			else{
				window.returnValue=$(obj).prev().html();
			}
			window.close();
		}
	</script>
</head>
<body>
<div class="panel">
<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">信息内容模板选择</span>
					</div>
				
				</div>
				</div>
			<div class="panel-body">
				<div class="panel-data">
					   <display:table name="sysTemplateList" id="sysTemplateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">											
						<display:column property="name" title="模板名称" sortable="true" sortName="name"></display:column>
						<display:column property="content"  title="模板内容" sortable="true" sortName="content" maxLength="80"></display:column>
						<display:column title="选择">
							<span style="display: none;" >${sysTemplateItem.content}</span>
							<a href="javascript:;" onclick="selectTemplate(this)" class="link edit" target="win">选择</a>
						</display:column>
					</display:table>
				</div>
			</div>			
		</div>
</body>
</html>
 --%>