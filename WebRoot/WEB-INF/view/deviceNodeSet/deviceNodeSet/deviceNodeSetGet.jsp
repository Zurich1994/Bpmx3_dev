
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>设备节点部署表明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">设备节点部署表详细信息</span>
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
	<table class="formTable" cellspacing="0" cellpadding="2" border="1" uetable="null">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">设备节点部署表</td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">业务IP:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag">${deviceNodeSet.businessIP}</span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">流程定义ID:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag">${deviceNodeSet.actdefid}</span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">流程节点ID:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag">${deviceNodeSet.nodeid}</span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备表:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag">${deviceNodeSet.deviceTable}</span></td>
  </tr>
 </tbody>
</table>
</body>
</html>

