<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 原子运行次数</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/subform.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atomiccountForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				$("#saveData").val(1);
				if(frm.valid()){
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					if(OfficePlugin.officeObjs.length>0){
						OfficePlugin.submit(function(){
							frm.handleFieldName();
							$('#atomiccountForm').submit();
						});
					}else{
						frm.handleFieldName();
						$('#atomiccountForm').submit();
					}
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = window.location.href;
					}else{
					    window.location.href = window.location.href;
						//window.location.href = "${ctx}/Atomiccount/Atomiccount/atomiccount/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel" style="height:100%;overflow:auto;">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty atomiccountItem.id}">
			        <span class="tbar-label"><span></span>编辑原子运行次数</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加原子运行次数</span>
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
	<form id="atomiccountForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">原子运行次数</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:atomiccount:bpmid" lablename="流程id" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${atomiccount.bpmid}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">操作图id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:atomiccount:operaterid" lablename="操作图id" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${atomiccount.operaterid}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">事务图id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:atomiccount:transactionid" lablename="事务图id" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${atomiccount.transactionid}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">原子操作id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:atomiccount:atomicid" lablename="原子操作id" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${atomiccount.atomicid}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">运行次数:</td>
   <td class="formInput" style="width:80%;"><input name="m:atomiccount:runs" showtype="{"coinValue":"","isShowComdify":0,"decimalValue":0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" type="text" value="${atomiccount.runs}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">时间:</td>
   <td class="formInput" style="width:80%;"><input name="m:atomiccount:time" class="Wdate" displaydate="0" datefmt="yyyy-MM-dd HH:mm:ss" validate="{empty:false}" type="text" value="${atomiccount.time}" /></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${atomiccount.id}"/>
		<input type="hidden" id="saveData" name="saveData"/>
		<input type="hidden" name="executeType"  value="start" />
	</form>
</body>
</html>
