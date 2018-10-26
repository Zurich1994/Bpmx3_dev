<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 orders</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#ordersForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#ordersForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "${ctx}/tpcc/tpcc/orders/list.ht";
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
			    <c:when test="${not empty ordersItem.id}">
			        <span class="tbar-label"><span></span>编辑orders</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加orders</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="/mas/tpcc/tpcc/orderLine/edit.ht?id=${orders.o_id}"><span></span>更新订单明细表</a></div>
			</div>
		</div>
	</div>
	<form id="ordersForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">orders</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">地区号:</td>
   <td class="formInput" style="width:80%;"><input name="o_d_id" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0,required:true}'" type="text" value="${orders.o_d_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">仓库号:</td>
   <td class="formInput" style="width:80%;"><input name="o_w_id" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0,required:true}'" type="text" value="${orders.o_w_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">客户号:</td>
   <td class="formInput" style="width:80%;"><input name="o_c_id" showtype="validate='{number:true,maxIntLen:10,maxDecimalLen:0}'" type="text" value="${orders.o_c_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">日期和时间:</td>
   <td class="formInput" style="width:80%;"><input name="o_entry_d" class="Wdate" displaydate="0" validate="{empty:false}" type="text" value="<fmt:formatDate value='${orders.o_entry_d}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">o_carrier_id:</td>
   <td class="formInput" style="width:80%;"><input name="o_carrier_id" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0}'" type="text" value="${orders.o_carrier_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">定单行数量:</td>
   <td class="formInput" style="width:80%;"><input name="o_ol_cnt" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0}'" type="text" value="${orders.o_ol_cnt}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">产品地:</td>
   <td class="formInput" style="width:80%;"><input name="o_all_local" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0}'" type="text" value="${orders.o_all_local}" /></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="o_id" value="${orders.o_id}"/>
	</form>
</body>
</html>
