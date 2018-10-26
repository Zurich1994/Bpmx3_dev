<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 银行订单表</title>
	<%@include file="/commons/include/customForm.jsp" %>
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
			var name=${orderCheck.userid};
			var obj = new com.hotent.form.ResultMessage(responseText);
			var url = __ctx + "/banking/banking/orderCheck/list.ht?name="+name;
						location.href = url;
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty orderCheckItem.id}">
			        <span class="tbar-label"><span></span>编辑银行订单表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加银行订单表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="/bpmx3_dev/UserTab/UserTab/userTab/success.ht?name=${orderCheck.userid}"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="orderCheckForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">银行订单表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">用户id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input readonly="readonly" name="userid" lablename="用户id" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${orderCheck.userid}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">账号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="accountno" lablename="账号" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${orderCheck.accountno}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">总价格:</td>
   <td class="formInput" style="width:80%;"><input name="totalprice" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:13,maxDecimalLen:2,required:true}" type="text" value="${orderCheck.totalprice}" /></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${orderCheck.id}"/>
	</form>
	
</body>
</html>
