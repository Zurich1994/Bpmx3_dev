
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>电子商务搜索表明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">电子商务搜索表详细信息</span>
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
   <td colspan="6" class="formHead">电子商务搜索表</td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">地区名:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.REGION}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">客户类型简称:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.CUSTOMER_TYPE_SHORT}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">类别:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.CATEGORY}</span></td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">ITEM ID:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.PRODUCTID}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">产品名称:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.PRODUCTNAME}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">产品特征1:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.FEATURE0}</span></td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">产品特征2:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.FEATURE1}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">产品特征3:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.FEATURE2}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">产品特征4:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.FEATURE3}</span></td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">关键字:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.KEYWORDS}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">链接:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.URL}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">产品描述:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${productModels.DESCRIPTION}</span></td>
  </tr>
 </tbody>
</table>
</body>
</html>

