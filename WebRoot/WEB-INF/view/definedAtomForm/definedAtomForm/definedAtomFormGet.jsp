
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>defined_atom_form明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">defined_atom_form详细信息</span>
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
	<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="8">defined_atom_form</td>
  </tr>
  <tr>
   <td style="width:10%" class="formTitle" nowrap="nowrap" align="right">setId:</td>
   <td style="width:15%" class="formInput">${definedAtomForm.setId}</td>
   <td style="width:10%" class="formTitle" nowrap="nowrap" align="right">defId:</td>
   <td style="width:15%" class="formInput">${definedAtomForm.defId}</td>
   <td style="width:10%" class="formTitle" nowrap="nowrap" align="right">nodeId:</td>
   <td style="width:15%" class="formInput">${definedAtomForm.nodeId}</td>
   <td style="width:10%" class="formTitle" nowrap="nowrap" align="right">formKey:</td>
   <td style="width:15%" class="formInput">${definedAtomForm.formKey}</td>
  </tr>
 </tbody>
</table>
</body>
</html>

