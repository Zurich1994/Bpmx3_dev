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
			var frm=$('#checkaccount2Form').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#checkaccount2Form').submit();
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
						window.location.href = "${ctx}/banking/banking/checkaccount2/list.ht";
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
			    <c:when test="${not empty checkaccount2Item.id}">
			        <span class="tbar-label"><span></span>编辑加载订单</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加加载订单</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<%String check_design=request.getParameter("check_design");  %>
	<form id="checkaccount2Form" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
			<h2 align="center">您选择的审查 模式是<%=check_design %></h2>
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">加载订单</td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">用户名:</td>
   <td class="formInput" style="width:80%;"><input name="userid" showtype="{'coinValue':'','isShowComdify':1,'decimalValue':0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0,required:true}" type="text" value="${name}" readonly="readonly" /></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">账号:</td>
   <td class="formInput" style="width:80%;"><input name="accountno" showtype="{'coinValue':'','isShowComdify':1,'decimalValue':0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0,required:true}" type="text" value="${account_no}" readonly="readonly"/></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">总价格:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="totalprice" lablename="totalprice" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${number}" readonly="readonly"/></span></td>
  </tr>
 </tbody>
</table>
<a href="${ctx}/Order_check/Order_check/orderCheck/edit.ht?name=<%=request.getParameter("name")%>&number=<%=request.getParameter("number") %>&account_no=<%=request.getParameter("account_no") %>">确认无误 </a>
			</div>
		</div>
		<input type="hidden" name="id" value="${checkaccount2.id}"/>
	</form>
</body>
</html>
