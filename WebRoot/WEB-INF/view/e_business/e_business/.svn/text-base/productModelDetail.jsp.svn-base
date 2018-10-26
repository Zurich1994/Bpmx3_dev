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
   <td colspan="8" class="formHead">产品模型表</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">地区名:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModel.REGION}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">客户类型简称:</td>
   <td style="width:15%;word-break:break-all;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModel.CUSTOMER_TYPE_SHORT}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">类别:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModel.CATEGORY}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品编号:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModel.PRODUCTID}</span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品名称:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModel.PRODUCTNAME}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品特征1:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModel.FEATURE0}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品特征2:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModel.FEATURE1}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品特征3:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModel.FEATURE2}</span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品特征4:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModel.FEATURE3}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">关键字:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModel.KEYWORDS}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">链接:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModel.URL}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品描述:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModel.DESCRIPTION}</span></td>
  </tr>
 </tbody>
</table>
