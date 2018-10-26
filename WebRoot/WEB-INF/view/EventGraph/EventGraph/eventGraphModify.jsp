<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/codegen/include/customForm.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/js/hotent/scriptMgr.js"></script>
<script type="text/javascript">
	function afterOnload(){
		var afterLoadJs=[
		     			'${ctx}/js/hotent/formdata.js',
		     			'${ctx}/js/hotent/subform.js'
		 ];
		ScriptMgr.load({
			scripts : afterLoadJs
		});
	}
</script>
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
