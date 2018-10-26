
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>projectInfo明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">projectInfo详细信息</span>
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
	<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">projectInfo</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">项目名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${projectinfo.projectName}</span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">description:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${projectinfo.description}</span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">开始时间:</td>
   <td class="formInput" style="width:80%;"><fmt:formatDate value='${projectinfo.stime}' pattern='yyyy-MM-dd'/></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">结束时间:</td>
   <td class="formInput" style="width:80%;"><fmt:formatDate value='${projectinfo.etime}' pattern='yyyy-MM-dd'/></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">实际开始时间:</td>
   <td class="formInput" style="width:80%;"><fmt:formatDate value='${projectinfo.astime}' pattern='yyyy-MM-dd'/></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">实际结束时间:</td>
   <td class="formInput" style="width:80%;"><fmt:formatDate value='${projectinfo.aetime}' pattern='yyyy-MM-dd'/></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">删除开关:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${projectinfo.deleteFlag}</span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">项目报文:</td>
   <td class="formInput" style="width:80%;"><textarea name="projectXml" validate="{empty:false}" readonly="readonly">${projectinfo.projectXml}</textarea></td>
  </tr>
 </tbody>
</table>
</body>
</html>

