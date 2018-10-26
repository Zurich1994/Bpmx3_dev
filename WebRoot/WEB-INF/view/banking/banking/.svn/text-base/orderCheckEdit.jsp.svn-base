<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 加载订单</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#orderCheckForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#orderCheckForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
		var name=document.getElementById("name").value;
			var obj = new com.hotent.form.ResultMessage(responseText);
			var url = __ctx + "/banking/banking/orderCheck/list.ht?name="+name;
						location.href = url;
	
		}
	</script>
</head>
<body>
<%   
String name=request.getParameter("name"); 

String account_no=request.getParameter("account_no");
String number=request.getParameter("number");

 %>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty orderCheckItem.id}">
			        <span class="tbar-label"><span></span>编辑加载订单</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加加载订单</span>
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
	<form id="orderCheckForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">加载订单</td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">用户名:</td>
   <td class="formInput" style="width:80%;"><input readonly="readonly"  name="userid" showtype="{'coinValue':'','isShowComdify':1,'decimalValue':0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0,required:true}" type="text" value="<%=name%>" id="name"/></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">账号:</td>
   <td class="formInput" style="width:80%;"><input name="accountno" showtype="{'coinValue':'','isShowComdify':1,'decimalValue':0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0,required:true}" type="text" value="<%=account_no%>" /></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">总价格:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="totalprice" lablename="totalprice" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="<%=number %>" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${orderCheck.id}"/>
	</form>
</body>
</html>
