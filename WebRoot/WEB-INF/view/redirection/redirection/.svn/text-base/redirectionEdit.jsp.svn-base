<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 redirection</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDialog3.js"></script>
<script type="text/javascript" src="${ctx}/js/hrbeu/extension/bpm/Dialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hrbeu/platform/system/AddResourceDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hrbeu/platform/form/CommonDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#redirectionForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#redirectionForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.reload();
					}else{
						this.close();
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		function selectNodeForm(obj) {
		FormDialog3({
			callback : function(ids, names, tableId) {
				var tdObj=$(obj).parent();				
				$("input.formKey",tdObj).val(ids);
				$("input.formDefName",tdObj).val(names);
				$("input.tableId",tdObj).val(tableId);
				//给表单添加 超链接，使之能看到表单明细
				
				var namesUrl="<a target='_blank' href="+__ctx+"/platform/form/bpmFormHandler/edit.ht?formDefId="+ids+" >"+names+"</a>";
				
				$("span[name='spanForm']",tdObj).html(namesUrl);
				// 是否显示子表授权功能
				$.ajax({
					type : "POST",
					url : __ctx + "/platform/form/bpmFormDef/isSubTable.ht",
					data : {formKey:ids},
					dataType : "json",
					success : function(res) {
						var result= eval('(' + res + ')');
						if(result.success && obj.id == 'subNodeSel'){
							$(obj).siblings("a.grant").show();
						}else{
							$(obj).siblings("a.grant").hide();
						}
					},
					error : function(res) {
						
					}
				});
			},
			callbackwithValue:function(textValue,formKey){
				alert("textValue:"+textValue);
				alert("formKey:"+formKey);
				
				//document.getElementById("inputArea").innelHtml(textValue);
				
				$("#inputArea").val(textValue);
				$("#redirectionurl").val(formKey);
			}
		})
	}
		
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty redirectionItem.id}">
			        <span class="tbar-label"><span></span>编辑redirection</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加redirection</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
		</div>
	</div>
	<form id="redirectionForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">跳转路径</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">输入想要跳转的页面路径:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="redirectionname" class="inputText" type="text" isflag="tableflag" validate="{maxlength:200}" id="inputArea" value="${bpmform.subject}"/></span>
	<a href="javascript:;" class="link get" onclick="selectNodeForm(this)" id="subNodeSel">查询</a>
   </td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="defid" value="${redir.defid}"/>
		<input type="hidden" name="nodeid" value="${redir.nodeid}"/>
		<input type="hidden" name="redirectionurl" id="redirectionurl" value="${redir.redirectionurl}"/>
		
	</form>
</body>
</html>
