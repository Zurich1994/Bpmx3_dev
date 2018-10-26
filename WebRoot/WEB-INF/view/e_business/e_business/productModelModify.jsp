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
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="REGION" lablename="地区名" class="inputText" validate="{maxlength:8,required:true}" isflag="tableflag" type="text" value="${productModel.REGION}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">客户类型简称:</td>
   <td style="width:15%;word-break:break-all;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="CUSTOMER_TYPE_SHORT" lablename="客户类型简称" class="inputText" validate="{maxlength:4,required:true}" isflag="tableflag" type="text" value="${productModel.CUSTOMER_TYPE_SHORT}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">类别:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="CATEGORY" lablename="类别" class="inputText" validate="{maxlength:11,required:true}" isflag="tableflag" type="text" value="${productModel.CATEGORY}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品编号:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PRODUCTID" lablename="产品编号" class="inputText" validate="{maxlength:16,required:true}" isflag="tableflag" type="text" value="${productModel.PRODUCTID}" /></span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品名称:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PRODUCTNAME" lablename="产品名称" class="inputText" validate="{maxlength:128,required:true}" isflag="tableflag" type="text" value="${productModel.PRODUCTNAME}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品特征1:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="FEATURE0" lablename="产品特征1" class="inputText" validate="{maxlength:64,required:true}" isflag="tableflag" type="text" value="${productModel.FEATURE0}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品特征2:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="FEATURE1" lablename="产品特征2" class="inputText" validate="{maxlength:64,required:true}" isflag="tableflag" type="text" value="${productModel.FEATURE1}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品特征3:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="FEATURE2" lablename="产品特征3" class="inputText" validate="{maxlength:64,required:true}" isflag="tableflag" type="text" value="${productModel.FEATURE2}" /></span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品特征4:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="FEATURE3" lablename="产品特征4" class="inputText" validate="{maxlength:64,required:true}" isflag="tableflag" type="text" value="${productModel.FEATURE3}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">关键字:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="KEYWORDS" lablename="关键字" class="inputText" validate="{maxlength:30,required:true}" isflag="tableflag" type="text" value="${productModel.KEYWORDS}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">链接:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="URL" lablename="链接" class="inputText" validate="{maxlength:128,required:true}" isflag="tableflag" type="text" value="${productModel.URL}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品描述:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="DESCRIPTION" lablename="产品描述" class="inputText" validate="{maxlength:500,required:true}" isflag="tableflag" type="text" value="${productModel.DESCRIPTION}" /></span></td>
  </tr>
 </tbody>
</table>
