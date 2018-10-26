<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
   <td colspan="8" class="formHead">绑定表</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">按钮名称:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${bpmFormBang.btn_name}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">按钮类型:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${bpmFormBang.btn_type}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">按钮概率:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${bpmFormBang.btn_probability}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">在线流程流程定义Id:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${bpmFormBang.defbId}</span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">表单Id:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${bpmFormBang.formId}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">流程定义Id:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${bpmFormBang.defId}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">节点Id:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${bpmFormBang.nodeId}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">自定义按钮Id:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${bpmFormBang.xId}</span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">表单定义Id:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${bpmFormBang.formDefId}</span></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
  </tr>
 </tbody>
</table>
