
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>组装表明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/subform.js"></script>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">组装表详细信息</span>
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
   <td colspan="6" class="formHead">组装表</td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">需要表ID:</td>
   <td style="width:20%;" class="formInput">${assemble.needID}</td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">容器表ID:</td>
   <td style="width:20%;" class="formInput">${assemble.containerID}</td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">服务产品ID:</td>
   <td style="width:20%;" class="formInput">${assemble.serviceproductID}</td>
  </tr>
 </tbody>
</table>
</body>
</html>

