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
	
	
	function send()
	{
	var name =document.formc.COMPONENTID.value;
	var comptype=document.formc.COMPONENTTYPE.value;
	var compname=document.formc.COMPONENTNAME.value;
	var price=document.formc.PRICE.value;
	var currency=document.formc.CURRENCY.value;
	//String EMAIL=request.getParameter("EMAIL");
	url="${ctx}/e_business/e_business/cart/list.ht?name="+name+"&PRODUCTID="+${PRODUCTID}+"&comptype="+comptype+"&compname="+compname+"&EMAIL="+<%=request.getParameter("EMAIL")%>;
	
	
	
		 window.location.href = url;
    		 }
	function back()
	{ 
	window.location.href="/mas/e_business/e_business/userInfo/edit.ht";
	}
</script>
<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
         <td><%  
String EMAIL=request.getParameter("EMAIL");  
out.println("尊贵的"+EMAIL+"");  
%>
 <form action="" name="formc" method="post">
  <tr>
   <td colspan="2" class="formHead">用户定制</td>
  </tr>
  
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">组件类型:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input1" style="display:inline-block;padding:2px;" class="selectinput"><select name="COMPONENTTYPE" lablename="组件类型" controltype="select" validate="{empty:false,required:true}"><option value="1" <c:if test='${component.COMPONENTTYPE=="1"}'>selected='selected'</c:if>>1</option><option value="2" <c:if test='${component.COMPONENTTYPE=="2"}'>selected='selected'</c:if>>2</option><option value="3" <c:if test='${component.COMPONENTTYPE=="3"}'>selected='selected'</c:if>>3</option></select></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">组件名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input2" style="display:inline-block;padding:2px;" class="selectinput"><select name="COMPONENTNAME" lablename="组件名" controltype="select" validate="{empty:false,required:true}"><option value="1" <c:if test='${component.COMPONENTNAME=="1"}'>selected='selected'</c:if>>1</option><option value="2" <c:if test='${component.COMPONENTNAME=="2"}'>selected='selected'</c:if>>2</option><option value="3" <c:if test='${component.COMPONENTNAME=="3"}'>selected='selected'</c:if>>3</option></select></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">组件ID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input3" style="display:inline-block;padding:2px;" class="selectinput"><select  name="COMPONENTID" lablename="组件ID" controltype="select" validate="{empty:false,required:true}"><option value="1" <c:if test='${component.COMPONENTID=="1"}'>selected='selected'</c:if>>1</option><option value="222" <c:if test='${component.COMPONENTID=="2"}'>selected='selected'</c:if>>2</option><option value="333" <c:if test='${component.COMPONENTID=="3"}'>selected='selected'</c:if>>3</option></select></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">价格:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input4" style="display:inline-block;padding:2px;" class="selectinput"><select name="PRICE" lablename="价格" controltype="select" validate="{empty:false,required:true}"><option value="1" <c:if test='${component.PRICE=="1"}'>selected='selected'</c:if>>1</option><option value="2" <c:if test='${component.PRICE=="2"}'>selected='selected'</c:if>>2</option><option value="3" <c:if test='${component.PRICE=="3"}'>selected='selected'</c:if>>3</option></select></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">货币:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input5" style="display:inline-block;padding:2px;" class="selectinput"><select name="CURRENCY" lablename="货币" controltype="select" validate="{empty:false,required:true}"><option value="1" <c:if test='${component.CURRENCY=="1"}'>selected='selected'</c:if>>1</option><option value="2" <c:if test='${component.CURRENCY=="2"}'>selected='selected'</c:if>>2</option><option value="3" <c:if test='${component.CURRENCY=="3"}'>selected='selected'</c:if>>3</option></select></span></td>
  </tr>
 </tbody>
</table>
 <td>
</td> 
</form>

<input type="button" value="增加到购物车" onclick="send()">

<input type="button" value="返回" onclick="back()">
