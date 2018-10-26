
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>设备负载值及利用率表明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">设备负载值及利用率表详细信息</span>
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
   <td class="formHead" colspan="2">设备负载值及利用率表</td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">节点设备id:</td>
   <td style="width:80%" class="formInput">${dviceLoadRate.node_dev_id}</td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">时间:</td>
   <td style="width:80%" class="formInput">${dviceLoadRate.active_start}</td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">负载类型:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${dviceLoadRate.load_type}</span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">负载值:</td>
   <td style="width:80%" class="formInput">${dviceLoadRate.load_value}</td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">资源类型:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${dviceLoadRate.load_info}</span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">利用率:</td>
   <td style="width:80%" class="formInput">${dviceLoadRate.load_use_rate}</td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">a:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${dviceLoadRate.a}</span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">b:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${dviceLoadRate.b}</span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">c:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${dviceLoadRate.c}</span></td>
  </tr>
 </tbody>
</table>
</body>
</html>

