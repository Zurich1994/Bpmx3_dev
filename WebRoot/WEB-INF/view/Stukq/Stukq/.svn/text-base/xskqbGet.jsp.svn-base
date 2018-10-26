
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>学生考勤表明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">学生考勤表详细信息</span>
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
	<tbody>
	<tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp"><h1>请输入此ID:</h1></td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><h2>${id}</h2></span></td>
  </tr>
  <tbody>
<iframe id="report" width="1200" height="500" src="http://192.168.3.58:8080/mas/ReportServer?reportlet=student.cpt"></iframe>
</body>
</html>

