
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>节点表单对应表明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">节点表单对应表详细信息</span>
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
   <td colspan="6" class="formHead">节点表单对应表</td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">查询条件1:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${nodeForm.condition1}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">查询条件2:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${nodeForm.condition2}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">查询条件3:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${nodeForm.condition3}</span></td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">表单id:</td>
   <td style="width:20%;" class="formInput">${nodeForm.formID}</td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">表单名称:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${nodeForm.formName}</span></td>
   <td style="width:13%;" class="formTitle"></td>
   <td style="width:20%;" class="formInput"></td>
  </tr>
 </tbody>
</table>
</body>
</html>

