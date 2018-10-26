<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 设备指标关联表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#deviceQuotaRelForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#deviceQuotaRelForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "${ctx}/deviceQuotaRel/deviceQuotaRelPac/deviceQuotaRel/listQuotaName.ht?id=${deviceQuotaRel.device_id}";
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
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty deviceQuotaRelItem.id}">
			        <span class="tbar-label"><span></span>编辑设备指标关联表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加设备指标关联表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="deviceQuotaRelForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">设备指标关联表</td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">指标名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="quota_name" lablename="指标名称" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${quota_name}" readonly="readonly"/></span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">监控频率(min):</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="monitor_freq" lablename="监控频率" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${deviceQuotaRel.monitor_freq}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${deviceQuotaRel.id}"/>
	</form>
</body>
</html>
