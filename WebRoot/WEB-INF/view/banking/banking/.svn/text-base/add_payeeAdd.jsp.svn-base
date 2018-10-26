<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>添加支付者信息表</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
		
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#add_payeeForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#add_payeeForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
		    var payee_id = $("input[name='PAYEEID']").val();
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {			
			var Str = payee_id+" successfully added"
			alert(Str);
						//window.location.href = '${ctx}/add_payee/add_payee/add_payee/result.ht?Str='+Str;				
			} else{
			var Str = "The name: "+payee_id+" already exists "
			alert(Str);
						//window.location.href = '${ctx}/add_payee/add_payee/add_payee/result.ht?Str='+Str;
			}
			
		}

	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty add_payeeItem.id}">
			        <span class="tbar-label"><span></span>编辑支付者信息表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加支付者信息表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>

	</div>
	<form id="add_payeeForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td style="word-break:break-all;" colspan="2" class="formHead">添加支付者信息</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">用户名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="USERID" lablename="用户名" class="inputText" validate="{maxlength:10,required:true,英数字:true}" isflag="tableflag" type="text" value="${name}" readonly="readonly" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">别名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PAYEEID" lablename="别名" class="inputText" validate="{maxlength:20,required:true,英数字:true}" isflag="tableflag" type="text" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">名字:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="NAME" lablename="名字" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text"  /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">街道:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="STREET" lablename="街道" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text"  /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">城市:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="CITY" lablename="城市" class="inputText" validate="{maxlength:20,required:true}" isflag="tableflag" type="text" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">州:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="STATE" lablename="州" class="inputText" validate="{maxlength:2,required:true}" isflag="tableflag" type="text"  /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">区号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ZIP" lablename="区号" class="inputText" validate="{maxlength:5,required:true,正整数:true}" isflag="tableflag" type="text" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">电话:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PHONE" lablename="电话" class="inputText" validate="{maxlength:20,required:true,手机号码:true}" isflag="tableflag" type="text"  /></span></td>
  </tr>
 </tbody>
</table>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>添加</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="/mas/banking/banking/userTab/success.ht?name=<%=request.getParameter("name")%>"><span></span>返回</a></div>
			</div>
		</div>
			</div>
		</div>
		<input type="hidden" name="id" value="${add_payee.id}"/>
	</form>
</body>
</html>
