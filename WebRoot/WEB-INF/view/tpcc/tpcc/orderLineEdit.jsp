<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 order_line</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#orderLineForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#orderLineForm').submit();
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
						window.location.href = "${ctx}/tpcc/tpcc/orderLine/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		function duihua()
		{
		var bin=window.confirm("确认发货");
		if(bin)
		{
			window.location='list.ht';		
		}
		
		}
		
		
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty orderLineItem.id}">
			        <span class="tbar-label"><span></span>编辑order_line</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加order_line</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				<!--<div class="group"><a class="link back" href="/mas/customer/customer/customer/query.ht">
				<span></span>查询客户</a></div>		
			-->
			
			<div class="group"><a class="link back" onclick="duihua()">
				<span></span>确认发货</a></div>		
			
			
			
			</div>
		</div>
	</div>
	<form id="orderLineForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">order_line</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">地区号:</td>
   <td class="formInput" style="width:80%;"><input name="ol_d_id" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0,required:true}'" type="text" value="${orderLine.ol_d_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">仓库号:</td>
   <td class="formInput" style="width:80%;"><input name="ol_w_id" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0,required:true}'" type="text" value="${orderLine.ol_w_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">订单行号:</td>
   <td class="formInput" style="width:80%;"><input name="ol_number" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0,required:true}'" type="text" value="${orderLine.ol_number}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">商品号:</td>
   <td class="formInput" style="width:80%;"><input name="ol_i_id" showtype="validate='{number:true,maxIntLen:10,maxDecimalLen:0}'" type="text" value="${orderLine.ol_i_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ol_supply_w_id:</td>
   <td class="formInput" style="width:80%;"><input name="ol_supply_w_id" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0}'" type="text" value="${orderLine.ol_supply_w_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">发货日期或时间:</td>
   <td class="formInput" style="width:80%;"><input name="ol_delivery_d" class="Wdate" displaydate="0" validate="{empty:false}" type="text" value="<fmt:formatDate value='${orderLine.ol_delivery_d}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">件数:</td>
   <td class="formInput" style="width:80%;"><input name="ol_quantity" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0}'" type="text" value="${orderLine.ol_quantity}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">总额:</td>
   <td class="formInput" style="width:80%;"><input name="ol_amount" showtype="validate='{number:true,maxIntLen:4,maxDecimalLen:2}'" type="text" value="${orderLine.ol_amount}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">地区编码:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ol_dist_info" lablename="ol_dist_info" class="inputText" validate="{maxlength:24}" isflag="tableflag" type="text" value="${orderLine.ol_dist_info}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="ol_o_id" value="${orderLine.ol_o_id}"/>
	</form>
</body>
</html>
