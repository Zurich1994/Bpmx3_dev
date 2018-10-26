<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>confirmation管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<script type="text/javascript">
	function queren()
	{
		
		if(confirm("  运单号：762345"))
		{
		
		}
		else
		{
		
		}
	}
</script>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">confirmation管理列表</span>
			</div>
			
		
				
		</div>
		<div>
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
			<h1 align="center"><strong>购物车</strong></h1>
			<display:table name="confirmationList" id="confirmationItem" requestURI="list.ht"  cellpadding="1" cellspacing="1"class="table-grid" >
			
				<display:column   property="F_ITEMName" title="条目名" sortable="true" sortName="F_ITEMNAME"></display:column>
				<display:column property="F_COMPONENTTYPE" title="定制" sortable="true" sortName="F_CUSTOMIZATIONS"></display:column>
				<display:column property="castquantity" title="数量" sortable="true" sortName="astquantity"></display:column>
				<display:column property="F_UNITPRICE" title="价格" sortable="true" sortName="F_PRICE"></display:column>
				<display:column  property="totalprice" title="总价" sortable="true" sortName="F_PRICE"></display:column>
				</display:table>
				<h1 align="center"><strong>运输信息</strong></h1>
			<display:table requestURI="list.ht" name="confirmationList" class="table-grid" >
				<display:column property="shippingname"   title="运输姓名" sortable="true" sortName="F_SHIPPINGNAME"></display:column>
				<display:column property="shippingaddress" title="运输地址" sortable="true" sortName="F_SHIPPINGADDRESS"></display:column>
				<display:column property="shippingcity" title="运输城市" sortable="true" sortName="F_SHIPPINGCITY"></display:column>
				<display:column property="shippingstate" title="运输州代码" sortable="true" sortName="F_SHIPPINGSTATE"></display:column>
				<display:column property="shippingzip" title="运输邮编" sortable="true" sortName="F_SHIPPINGZIP"></display:column>
				<display:column property="shippingphone" title="运输电话" sortable="true" sortName="F_SHIPPINGPHONE"></display:column>
				<display:column property="F_SHIPPING" title="运输类型" sortable="true" sortName="F_SHIPPING"></display:column>
				
			</display:table>
			<h1 align="center"><strong>账单信息</strong></h1>
			<display:table  class="table-grid" requestURI="list.ht" name="confirmationList">
				<display:column property="billingname" title="账单姓名" sortable="true" sortName="F_BILLINGNAME"></display:column>
				<display:column property="billingaddress" title="账单地址" sortable="true" sortName="F_BILLINGADDRESS"></display:column>
				<display:column property="billingcity" title="账单城市" sortable="true" sortName="F_BILLINGCITY"></display:column>
				<display:column property="billingstate" title="账单州代码" sortable="true" sortName="F_BILLINGSTATE"></display:column>
				<display:column property="billingzip" title="账单邮编" sortable="true" sortName="F_BILLINGZIP"></display:column>
				<display:column property="billingphone" title="账单电话" sortable="true" sortName="F_BILLINGPHONE"></display:column>
				<display:column property="F_CC_TYPE" title="信用卡" sortable="true" sortName="F_CREDITCARD"></display:column>
			</display:table>
			<input type="button" value="确认" onclick="queren()"/>
			<!--<hotent:paging tableId="confirmationItem"/>
		--></div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


