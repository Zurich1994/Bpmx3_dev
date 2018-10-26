<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>邮件</title>
	<%@include file="/commons/include/form.jsp" %>
    <f:link href="tree/zTreeStyle.css"></f:link>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=outMail"></script>
	<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/mail/MailDef.js"></script>
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
	<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/FlexUploadDialog.js"></script>
	<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
	<link href="${ctx}/js/jquery/plugins/attach.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.attach.js" ></script>
	<style type="text/css">
	.focus {
		background-color: #FFCCFF;
	}
	.tree-title{overflow:hidden;width:8000px;}
	.ztree{overflow: auto;}
	</style>
	<script type="text/javascript">
		$(function() {
			layout();
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			OutMailDef.getEditor();
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				CKEDITOR.config.height = 600;
				$.ligerDialog.waitting("正在发送邮件,请您耐心等待...");
				var editor=CKEDITOR.instances.content;
				var content=editor.getData();
				$('#isReply').val(1);
				$('#types').val(2);
				$("#content").attr('value',content);
				$('#outMailForm').submit();
			});
			$('#dataFormDraft').click(function() {
				var editor=CKEDITOR.instances.content;
				var content=editor.getData();
				$("#content").attr('value',content);
				$('#types').val(3);
				$('#outMailForm').submit(); 
			});
		});
		function showResponse(responseText){
			var type=$('#types').val();
			var obj=new com.hotent.form.ResultMessage(responseText);
			$.ligerDialog.closeWaitting();
			if(obj.isSuccess()){//成功
				$.ligerDialog.success(obj.getMessage(),'成功',function(){
					window.location.href="${ctx}/platform/mail/outMail/list.ht?id=${outMailSetId}&types="+type; 		
				});
		    }else{//失败
		    	$.ligerDialog.error(obj.getMessage());
		    }
		}
		
		//布局
		function layout(){

			$("#layout").ligerLayout( {
				leftWidth : 230,
				onHeightChanged: heightChanged
			});
			//取得layout的高度
	        var height = $(".l-layout-center").height();
	        $("#positionTree").height(height-60);
		};
		//布局大小改变的时候通知tab，面板改变大小
	    function heightChanged(options){
	     	$("#positionTree").height(options.middleHeight - 60);
	    };
	    
		//添加附件
		function addFile(){
			$("#fileIds").val('');
			$("#filePaths").val('');
			FlexUploadDialog({isSingle:true,callback:fileCallback});
		};
		
		function fileCallback(fileIds,fileNames,filePaths)
		{
			var arrPath;
			if(filePaths=="") return ;
				arrPath=filePaths.split(",");
				var sb=new StringBuffer();
				var thisTemp=$("#filePaths").val();
				for(var i=0;i<arrPath.length;i++){
					if(i==arrPath.length-1){
						sb.append(arrPath[i]);
					}else{
						sb.append(arrPath[i]+",");
					}
				}
				$("#filePaths").val(sb.toString());
				$("#fileIds").val(fileIds);
		};
	</script>
</head>
<body>
		<div id="layout">
		<div position="center">
		<div class="panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">回复邮件</span>
				</div>
			<div class="panel-toolbar">
				<div class="toolBar">
				    <div class="group"><a class="link save"><span></span>立即发送</a></div>
					<div class="l-bar-separator"></div>
				    <div class="group"><a class="link init" id="dataFormDraft"><span></span>保存草稿</a></div>
					<div class="l-bar-separator"></div>
				    <div class="group"><a class="link back" href="get.ht?mailId=${outMail.mailId}&outMailSetId=${outMailSetId}"><span></span>返回</a></div>
				</div>
			</div>
			</div>
		<div class="panel-body">
			<form id="outMailForm" method="post" action="send.ht" >
					<div class="panel-detail">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="15%">发件人:</th>
								<td>${outMailUserSeting.userName}</td>
							</tr>
							<tr>
								<th width="15%">收件人:</th>
								<td>${outMail.senderAddresses}</td>
							</tr>
							<tr>
								<th width="15%">主题: </th>
								<td><input type="text" id="title" name="title" value="${outMail.title}"  class="inputText"  style="width: 80%;"/></td>
							</tr>
							<tr>
								<th width="15%">附件: </th>
								<td>
								<input type="hidden" name="fileIds" id="fileIds" class="attach" attachType="2" value="${outMail.fileIds}" />
								</td>
							</tr>
							<tr>
								<td  colspan="2" >
								<textarea  name="content" id="content" cols="60" rows="8">
									<br><br><hr><br>----<strong> 回复邮件</strong>----<br>
									<strong>发件人</strong>:${outMail.senderName}
									<br><strong>发送时间</strong>:<fmt:formatDate value="${outMail.mailDate}" pattern="yyyy年MM月dd日" />
									<br><strong>收件人</strong>:${outMail.receiverNames}
									<c:if test="${not empty outMail.ccNames}">
										<br><strong>抄送人</strong>:${outMail.ccNames}
									</c:if>
									<br><strong>主题</strong>:${outMail.title}
									<br><strong>内容</strong>:<br><br>${outMail.content}
								</textarea>
								</td>
							</tr>
						</table>
					</div>
					<input type="hidden" name="senderAddresses" value="${outMailUserSeting.mailAddress}" />
					<input type="hidden" name="receiverAddresses" value="${outMail.senderAddresses}" />
					<input type="hidden" name="mailId" value="${outMail.mailId}"/>
					<intpu type="hidden" id="outMailSetId" name="outMailSetId" value="${outMailSetId}"/>
					<input name="types" id="types" type="hidden" value="${outMail.types}"/> 
					<input name="isReply" id="isReply" type="hidden" value="${outMail.isReply}"/> 
			</form>
		</div>
		</div>
		</div>
	</div>
</body>
</html>
