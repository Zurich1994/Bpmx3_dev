<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 购物车</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#cartForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#cartForm').submit();
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
						window.location.href = "${ctx}/e_business/e_business/cart/list.ht";
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
			    <c:when test="${not empty cartItem.id}">
			        <span class="tbar-label"><span></span>编辑购物车</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加购物车</span>
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
	<form id="cartForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">购物车</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">EMAIL地址:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input  name="EMAIL" lablename="EMAIL地址" class="inputText" validate="{maxlength:30,required:true}" isflag="tableflag" type="text" value="${cart.EMAIL}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ITEMID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input  name="PRODUCTID" lablename="ITEMID" class="inputText" validate="{maxlength:16,required:true}" isflag="tableflag" type="text" value="${cart.PRODUCTID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ITEM名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input   name="ITEMName" lablename="ITEM名称" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${cart.ITEMName}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">组件类型:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="COMPONENTTYPE" lablename="组件类型" controltype="select" validate="{empty:false,required:true}"><option value="CPU" <c:if test='${cart.COMPONENTTYPE=="CPU"}'>selected='selected'</c:if>>CPU</option><option value="Memory" <c:if test='${cart.COMPONENTTYPE=="Memory"}'>selected='selected'</c:if>>Memory</option><option value="OS" <c:if test='${cart.COMPONENTTYPE=="OS"}'>selected='selected'</c:if>>OS</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">组件id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input  name="COMPONENTID" lablename="组件id" class="inputText" validate="{maxlength:16,required:true}" isflag="tableflag" type="text" value="${cart.COMPONENTID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">数量:</td>
   <td class="formInput" style="width:80%;"><input name="QUANTITY" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:10,maxDecimalLen:0,required:true}" type="text" value="${cart.QUANTITY}" /></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">单价:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input  name="UNITPRICE" lablename="单价" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${cart.UNITPRICE}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${cart.id}"/>
	</form>
</body>
</html>
