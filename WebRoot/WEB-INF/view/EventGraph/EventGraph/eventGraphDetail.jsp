<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/scriptMgr.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/subform.js"></script>
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
   <td style="width:15%;" class="formInput">${eventGraph.DomId}</td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">元素类型:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${eventGraph.DomType}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">FormDefId:</td>
   <td style="width:15%;" class="formInput">${eventGraph.FormDefId}</td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">元素事件:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${eventGraph.Domevent}</span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">defId:</td>
   <td style="width:15%;" class="formInput">${eventGraph.defId}</td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">读元素:</td>
   <td style="width:15%;" class="formInput">${eventGraph.InputDom}</td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">写元素:</td>
   <td style="width:15%;" class="formInput">${eventGraph.OutputDom}</td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">datatemplateId:</td>
   <td style="width:15%;" class="formInput">${eventGraph.datatemplateId}</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">formKey:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${eventGraph.formKey}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">xkey:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${eventGraph.xkey}</span></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
  </tr>
 </tbody>
</table>
