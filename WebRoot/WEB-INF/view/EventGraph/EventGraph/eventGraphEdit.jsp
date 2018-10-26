<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 事件绑定</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/subform.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#eventGraphForm').form();
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
							$('#eventGraphForm').submit();
						});
					}else{
						frm.handleFieldName();
						$('#eventGraphForm').submit();
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
					   //window.location.href = window.location.href;
						window.location.href = "${ctx}/EventGraph/EventGraph/eventGraph/list.ht";
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
			    <c:when test="${not empty eventGraphItem.id}">
			        <span class="tbar-label"><span></span>编辑事件绑定</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加事件绑定</span>
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
	<form id="eventGraphForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="8" class="formHead">事件绑定</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">元素Id:</td>
   <td style="width:15%;" class="formInput"><input name="m:event_graph:DomId" showtype="{"coinValue":"","isShowComdify":1,"decimalValue":0}" validate="{number:true,maxIntLen:18,maxDecimalLen:0,required:true}" type="text" value="${eventGraph.DomId}" /></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">元素类型:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:event_graph:DomType" lablename="元素类型" class="inputText" validate="{maxlength:1000,required:true}" isflag="tableflag" type="text" value="${eventGraph.DomType}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">FormDefId:</td>
   <td style="width:15%;" class="formInput"><input name="m:event_graph:FormDefId" showtype="{"coinValue":"","isShowComdify":1,"decimalValue":0}" validate="{number:true,maxIntLen:18,maxDecimalLen:0,required:true}" type="text" value="${eventGraph.FormDefId}" /></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">元素事件:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:event_graph:Domevent" lablename="元素事件" class="inputText" validate="{maxlength:1000,required:true}" isflag="tableflag" type="text" value="${eventGraph.Domevent}" /></span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">defId:</td>
   <td style="width:15%;" class="formInput"><input name="m:event_graph:defId" showtype="{"coinValue":"","isShowComdify":1,"decimalValue":0}" validate="{number:true,maxIntLen:18,maxDecimalLen:0,required:true}" type="text" value="${eventGraph.defId}" /></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">读元素:</td>
   <td style="width:15%;" class="formInput"><textarea name="m:event_graph:InputDom" validate="{empty:false,required:true}">${eventGraph.InputDom}</textarea></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">写元素:</td>
   <td style="width:15%;" class="formInput"><textarea name="m:event_graph:OutputDom" validate="{empty:false,required:true}">${eventGraph.OutputDom}</textarea></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">datatemplateId:</td>
   <td style="width:15%;" class="formInput"><input name="m:event_graph:datatemplateId" showtype="{"coinValue":"","isShowComdify":1,"decimalValue":0}" validate="{number:true,maxIntLen:18,maxDecimalLen:0,required:true}" type="text" value="${eventGraph.datatemplateId}" /></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">formKey:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:event_graph:formKey" lablename="formKey" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${eventGraph.formKey}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">xkey:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:event_graph:xkey" lablename="xkey" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${eventGraph.xkey}" /></span></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${eventGraph.id}"/>
		<input type="hidden" id="saveData" name="saveData"/>
		<input type="hidden" name="executeType"  value="start" />
	</form>
</body>
</html>
