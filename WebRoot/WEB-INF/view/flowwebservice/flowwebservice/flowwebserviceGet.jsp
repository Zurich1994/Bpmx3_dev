
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>flowWebService明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">flowWebService详细信息</span>
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
   <td class="formHead" colspan="2">flowWebService</td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务名称:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${flowwebservice.serviceName}</span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务地址:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${flowwebservice.serviceUrl}</span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务方法名:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${flowwebservice.serviceFuncName}</span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务类型:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${flowwebservice.serviceType}</span></td>
   
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务状态:</td>
    <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${flowwebservice.serviceState}</span></td>
  
 
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">流程ID:</td>
   <td style="width:80%" class="formInput">${flowwebservice.defid}</td>
  </tr>
 </tbody>
</table>
</body>
</html>

