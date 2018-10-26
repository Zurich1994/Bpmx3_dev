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
   <td colspan="2" class="formHead">购物车</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">EMAIL地址:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="EMAIL" lablename="EMAIL地址" class="inputText" validate="{maxlength:30,required:true}" isflag="tableflag" type="text" value="${cart.EMAIL}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ITEMID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PRODUCTID" lablename="ITEMID" class="inputText" validate="{maxlength:16,required:true}" isflag="tableflag" type="text" value="${cart.PRODUCTID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ITEM名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ITEMName" lablename="ITEM名称" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${cart.ITEMName}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">组件类型:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="COMPONENTTYPE" lablename="组件类型" controltype="select" validate="{empty:false,required:true}"><option value="CPU" <c:if test='${cart.COMPONENTTYPE=="CPU"}'>selected='selected'</c:if>>CPU</option><option value="Memory" <c:if test='${cart.COMPONENTTYPE=="Memory"}'>selected='selected'</c:if>>Memory</option><option value="OS" <c:if test='${cart.COMPONENTTYPE=="OS"}'>selected='selected'</c:if>>OS</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">组件id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="COMPONENTID" lablename="组件id" class="inputText" validate="{maxlength:16,required:true}" isflag="tableflag" type="text" value="${cart.COMPONENTID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">数量:</td>
   <td class="formInput" style="width:80%;"><input name="QUANTITY" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:10,maxDecimalLen:0,required:true}" type="text" value="${cart.QUANTITY}" /></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">单价:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="UNITPRICE" lablename="单价" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${cart.UNITPRICE}" /></span></td>
  </tr>
 </tbody>
</table>
