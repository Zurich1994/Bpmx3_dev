
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>history2明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">history2详细信息</span>
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
   <td colspan="8" class="formHead">history2</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_c_id:</td>
   <td style="width:15%;" class="formInput">${history2.h_c_id}</td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_c_d_id:</td>
   <td style="width:15%;" class="formInput">${history2.h_c_d_id}</td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_d_id:</td>
   <td style="width:15%;" class="formInput">${history2.h_d_id}</td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_w_id:</td>
   <td style="width:15%;" class="formInput">${history2.h_w_id}</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_date:</td>
   <td style="width:15%;" class="formInput"><fmt:formatDate value='${history2.h_date}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_amount:</td>
   <td style="width:15%;" class="formInput">${history2.h_amount}</td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_data:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${history2.h_data}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_c_w_id:</td>
   <td style="width:15%;" class="formInput">${history2.h_c_w_id}</td>
  </tr>
 </tbody>
</table>
</body>
</html>

