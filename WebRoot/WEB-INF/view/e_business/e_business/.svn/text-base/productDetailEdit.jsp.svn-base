<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 商品详细表</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#productDetailForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#productDetailForm').submit();
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
						window.location.href = "${ctx}/e_business/e_business/productDetail/list.ht";
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
			    <c:when test="${not empty productDetailItem.id}">
			        <span class="tbar-label"><span></span>编辑商品详细表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加商品详细表</span>
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
	<form id="productDetailForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="8" class="formHead">商品详细表</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">ITEM ID:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PRODUCTID" lablename="ITEM ID" class="inputText" validate="{maxlength:16,required:true}" isflag="tableflag" type="text" value="${productDetail.PRODUCTID}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">客户类型简称:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="CUSTOMER_TYPE_SHORT" lablename="客户类型简称" class="inputText" validate="{maxlength:4}" isflag="tableflag" type="text" value="${productDetail.CUSTOMER_TYPE_SHORT}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品细节:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="DETAIL" lablename="产品细节" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${productDetail.DETAIL}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">地区名:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="REGION" lablename="地区名" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${productDetail.REGION}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${productDetail.id}"/>
	</form>
</body>
</html>
