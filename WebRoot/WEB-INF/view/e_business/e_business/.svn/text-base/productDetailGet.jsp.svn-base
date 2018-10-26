
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<html>
<head>
<title>商品详细表明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">商品细节</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back"  href="/mas/e_business/e_business/productModel/list.ht?EMAIL=<%=request.getParameter("EMAIL")%>" class="link detail"" ><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="8" class="formHead">商品细节</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">ITEM ID:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productDetail.PRODUCTID}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">客户类型简称:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productDetail.CUSTOMER_TYPE_SHORT}</span></td>
   </tr><tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">产品细节:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productDetail.DETAIL}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">地区名:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productDetail.REGION}</span></td>
 
  </tr>
 </tbody>
</table>
</body>
</html>

