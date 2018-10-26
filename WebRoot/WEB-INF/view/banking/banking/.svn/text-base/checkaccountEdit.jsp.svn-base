<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 账户概要</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#checkaccountForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#checkaccountForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.reload();
					}else{
						window.location.href = "${ctx}/banking/banking/checkaccount/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty checkaccountItem.id}">
			        <span class="tbar-label"><span></span>编辑账户概要</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加账户概要</span>
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
	<form id="checkaccountForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="4" class="formHead">账户概要</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" width="263">用户名:</td>
   <td style="word-break:break-all;" class="formInput" width="421"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="USERID" lablename="用户名" class="inputText" validate="{maxlength:20,required:true}" isflag="tableflag" value="${checkaccount.USERID}" /></span></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">账号:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="ACCOUNTNO" lablename="账号" class="inputText" validate="{maxlength:10,required:true}" isflag="tableflag" value="${checkaccount.ACCOUNTNO}" /></span></td>
  </tr>
  <tr>
   <td align="right" style="word-break:break-all;" class="formTitle" nowrap="nowarp" width="263">类型:</td>
   <td style="word-break:break-all;" class="formInput" width="421"><input name="TYPE" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:2,maxDecimalLen:0,required:true}" value="${checkaccount.TYPE}" /></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">金额:</td>
   <td style="width:35%;" class="formInput"><input name="BALANCE" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" value="${checkaccount.BALANCE}" /></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" width="263">总存款数:</td>
   <td style="word-break:break-all;" class="formInput" width="421"><input name="TOTALDEPOSIT" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" value="${checkaccount.TOTALDEPOSIT}" /></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">平均存款数:</td>
   <td style="width:35%;" class="formInput"><input name="AVGDEPOSIT" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" value="${checkaccount.AVGDEPOSIT}" /></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" width="263">总取款数:</td>
   <td style="word-break:break-all;" class="formInput" width="421"><input name="TOTALWITHDRAWL" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" value="${checkaccount.TOTALWITHDRAWL}" /></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">平均取款数:</td>
   <td style="width:35%;" class="formInput"><input name="AVGWITHDRAWAL" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" value="${checkaccount.AVGWITHDRAWAL}" /></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${checkaccount.id}"/>
	</form>
</body>
</html>
