<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 公告</title>
	<%@include file="/commons/include/form.jsp"%>
	<f:link href="form.css" ></f:link>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/AttachMent.js" ></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/HtmlUploadDialog.js" ></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/FlexUploadDialog.js" ></script>
	<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor_msg.js"></script>
	<script type="text/javascript">
		$(function() {
		    var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#sysBulletinForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					$('#content').val(editor.getData());
					$('#sysBulletinForm').submit();
				}
			});
			AttachMent.init("w");
			editor=ckeditor("content");
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = window.location.href;
					}else{
						window.location.href = "${returnUrl}";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link back" href="list.ht"><span></span>返回</a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="sysBulletinForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tbody>
					<tr>
						<th width="20%">主题:<span class="required red">*</span></th>
						<td>
							<input type="text" name="subject" value="${sysBulletin.subject}" validate="{required:true,maxlength:128}" class="inputText" style="width: 50%;"  />
						</td>
					</tr>
					<tr>
						<th width="15%">所属栏目: </th>
						<td>
							<select name="columnid" id="columnid" class="inputText" style="width: 250px;">
								<c:forEach items="${columnList}" var="columnList">
									<option value="${columnList.id}" <c:if test="${sysBulletin.columnid==columnList.id}">selected="selected"</c:if> >${columnList.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th width="15%">附件: </th>
						<td>
							<div name="div_attachment_container">
								<div class="attachement"></div>
								<textarea style="display: none" controltype="attachment"
									id="attachment" name="attachment" lablename="主表附件" validate="{}">${sysBulletin.attachment}</textarea>
								<a href="javascript:;" field="attachment" class="link selectFile"
									atype="select" onclick="AttachMent.addFile(this);">选择</a>
							</div> 
						</td>
					</tr>
					<tr>
						<td colspan="2" >
							<textarea  name="content" id="content" >${sysBulletin.content}</textarea>
						</td>
					</tr> 
				</tbody>
			</table>
			<input type="hidden" name="createtime" value="<fmt:formatDate value='${sysBulletin.createtime}' type='date'/>"/>
			<input type="hidden" name="creatorid" value="${sysBulletin.creatorid}"/>
			<input type="hidden" name="creator" value="${sysBulletin.creator}"/>
			<input type="hidden" name="id" value="${sysBulletin.id}"/>
		</form>
	</div>
</body>
</html>
