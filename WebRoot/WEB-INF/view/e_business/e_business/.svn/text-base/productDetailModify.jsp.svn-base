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
   <td colspan="8" class="formHead">商品详细表</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">ITEM ID:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PRODUCTID" lablename="ITEM ID" class="inputText" validate="{maxlength:16,required:true}" isflag="tableflag" type="text" value="${productDetail.PRODUCTID}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">客户类型简称:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="CUSTOMER_TYPE_SHORT" lablename="客户类型简称" class="inputText" validate="{maxlength:4}" isflag="tableflag" type="text" value="${productDetail.CUSTOMER_TYPE_SHORT}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品细节:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="DETAIL" lablename="产品细节" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${productDetail.DETAIL}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">地区名:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="REGION" lablename="地区名" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${productDetail.REGION}" /></span></td>
  </tr>
 </tbody>
</table>
