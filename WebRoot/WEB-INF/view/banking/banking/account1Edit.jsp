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
			var frm=$('#account1Form').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#account1Form').submit();
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
					 var userid=document.getElementById("userid").value;
					
						window.location.href = "${ctx}/banking/banking/account1/list.ht?name="+userid;
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		function getid(){
		
		 var userid=document.getElementById("userid").value;
					
						window.location.href = "${ctx}/banking/banking/account1/list.ht?name="+userid;
		///bpmx3_dev/ACCOUNT/ACCOUNT/account1/list.ht?name=123
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty account1Item.id}">
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
				<div class="group"><a class="link back" onclick="getid()"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="account1Form" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="8" class="formHead">账户概要</td>
 
  </tr>
  <tr>
   <td style="text-align:center;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp" width="197">用户名:</td>
   <td style="text-align:center;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp" width="135">账号:</td>
   <td style="text-align:center;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp" width="180">类型：</td>
   <td style="text-align:center;" class="formTitle" align="right" nowrap="nowarp" width="342">金额:</td>
   </tr>
  <tr>
   <td style="word-break:break-all;" class="formInput" width="197"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="USERID" id="userid" lablename="用户名" class="inputText" validate="{maxlength:20,required:true}" isflag="tableflag" type="text" value="${currentUserId}" /></span></td>
   <td style="word-break:break-all;" class="formInput" width="135"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ACCOUNTNO" lablename="账号" class="inputText" validate="{maxlength:10,required:true}" isflag="tableflag" type="text" value="${account1.ACCOUNTNO}" /></span></td>
   <td style="word-break:break-all;" class="formInput" width="180"><input name="TYPE" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:2,maxDecimalLen:0,required:true}" type="text" value="${account1.TYPE}" /></td>
   <td class="formInput" width="342"><input name="BALANCE" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" type="text" value="${account1.BALANCE}" /></td>
   </tr>
  <tr>
   <td style="width:10%;text-align:center;" class="formTitle" align="right" nowrap="nowarp">总存款数:</td>
   <td style="width:10%;text-align:center;" class="formTitle" align="right" nowrap="nowarp">平均存款数:</td>
   <td style="width:10%;text-align:center;" class="formTitle" align="right" nowrap="nowarp">总取款数:</td>
   <td style="width:10%;text-align:center;" class="formTitle" align="right" nowrap="nowarp">平均取款数:</td>
  
  </tr>
  <tr>

   <td style="width:15%;" class="formInput"><input name="TOTALDEPOSIT" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" type="text" value="${account1.TOTALDEPOSIT}" /></td>
   <td style="width:15%;" class="formInput"><input name="AVGDEPOSIT" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" type="text" value="${account1.AVGDEPOSIT}" /></td>
   <td style="width:15%;" class="formInput"><input name="TOTALWITHDRAWL" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" type="text" value="${account1.TOTALWITHDRAWL}" /></td>
   <td style="width:15%;" class="formInput"><input name="AVGWITHDRAWAL" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" type="text" value="${account1.AVGWITHDRAWAL}" /></td>
 
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${account1.id}"/>
	</form>
</body>
</html>
