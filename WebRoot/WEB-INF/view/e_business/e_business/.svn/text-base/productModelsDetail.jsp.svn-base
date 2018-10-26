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
   <td colspan="6" class="formHead">电子商务搜索表</td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">地区名:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.REGION}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">客户类型简称:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.CUSTOMER_TYPE_SHORT}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">类别:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.CATEGORY}</span></td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">ITEM ID:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.PRODUCTID}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">产品名称:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.PRODUCTNAME}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">产品特征1:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.FEATURE0}</span></td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">产品特征2:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.FEATURE1}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">产品特征3:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.FEATURE2}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">产品特征4:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.FEATURE3}</span></td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">关键字:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.KEYWORDS}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">链接:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.URL}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">产品描述:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.DESCRIPTION}</span></td>
  </tr>
 </tbody>
</table>
