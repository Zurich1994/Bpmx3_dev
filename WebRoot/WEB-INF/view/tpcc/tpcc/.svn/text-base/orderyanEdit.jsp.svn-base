<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 order</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#orderyanForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#orderyanForm').submit();
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
						window.location.href = "${ctx}/tpcc/tpcc/orderyan/list.ht";
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
			    <c:when test="${not empty orderyanItem.id}">
			        <span class="tbar-label"><span></span>编辑order</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加order</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="${ctx}/tpcc/tpcc/customer/Query.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="orderyanForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">order</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">订单号:</td>
   <td class="formInput" style="width:80%;"><input name="o_id" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:8,maxDecimalLen:0,required:true}" value=<%=request.getParameter("d_next_o_id") %> /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">地区号:</td>
   <td class="formInput" style="width:80%;"><input name="o_d_id" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0,required:true}" value=<%=request.getParameter("c_d_id") %> /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">仓库号:</td>
   <td class="formInput" style="width:80%;"><input name="o_w_id" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0,required:true}" value=<%=request.getParameter("c_w_id") %> /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">用户号:</td>
   <td class="formInput" style="width:80%;"><input name="o_c_id" type="text" showtype="{'coinValue':'','isShowComdify':1,'decimalValue':0}" validate="{number:true,maxIntLen:5,maxDecimalLen:0,required:true}" value=<%=request.getParameter("c_id") %> /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">日期和时间:</td>
   <td class="formInput" style="width:80%;"><input name="o_entry_d" type="text" class="Wdate" displaydate="0" validate="{empty:false,required:true}" value="<fmt:formatDate value='${orderyan.o_entry_d}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">承接人ID:</td>
   <td class="formInput" style="width:80%;"><input name="o_carrier_id" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" value="${orderyan.o_carrier_id}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">订单行数量:</td>
   <td class="formInput" style="width:80%;"><input name="o_ol_cnt" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0,required:true}" value="${orderyan.o_ol_cnt}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">是否全是本地商品:</td>
   <td class="formInput" style="width:80%;"> <select name="o_all_local" id="tfeestype" onchange="check(this)"><option value="1">全是本地商品</option><option value="2">不全是本地商品</option> </select>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${orderyan.id}"/>
	</form>
</body>
</html>
