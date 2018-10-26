
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>购物车明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">购物车详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">购物车</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">EMAIL地址:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${cart.EMAIL}</span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ITEMID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${cart.PRODUCTID}</span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ITEM名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${cart.ITEMName}</span></td>
  </tr><!--
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">组件类型:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><c:if test='${cart.COMPONENTTYPE==CPU}'>CPU</c:if><c:if test='${cart.COMPONENTTYPE==Memory}'>Memory</c:if><c:if test='${cart.COMPONENTTYPE==OS}'>OS</c:if></span></td>
  </tr>
  --><tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">组件类型:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="COMPONENTTYPE" lablename="组件类型" controltype="select" validate="{empty:false,required:true}" disabled=“disabled”><option value="CPU" <c:if test='${cart.COMPONENTTYPE=="CPU"}'>selected='selected'</c:if>>CPU</option><option value="Memory" <c:if test='${cart.COMPONENTTYPE=="Memory"}'>selected='selected'</c:if>>Memory</option><option value="OS" <c:if test='${cart.COMPONENTTYPE=="OS"}'>selected='selected'</c:if>>OS</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">组件id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${cart.COMPONENTID}</span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">数量:</td>
   <td class="formInput" style="width:80%;">${cart.QUANTITY}</td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">单价:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${cart.UNITPRICE}</span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" align="right" nowrap="nowarp">总价:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${cart.UNITPRICE*cart.QUANTITY}</span></td>
  </tr>
  <tr>
  <td>
 
<a href="/mas/e_business/e_business/orderShipping/edit.ht?name="+name+"&PRODUCTID="+${PRODUCTID}+"&COMPONENTYPE="+${COMPONENTTYPE}+"COMPONENTNAME="+$(COMPONENTNAME);">下一步</a>
<a href="javascript:history.back()">返回</a>


</td>
    </tr>
 </tbody>
</table>
</body>
</html>

