
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>数据功能权限分享明细</title>
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
				<span class="tbar-label">数据功能权限分享详细信息</span>
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
   <td colspan="4" class="formHead">数据功能权限分享</td>
  </tr>
  <tr>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">名称:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${sysShareRights.name}</span></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">共享类型:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${sysShareRights.type}</span></td>
  </tr>
  <tr>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">是否启用:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><c:if test="${sysShareRights.enable=='1'}">是</c:if><c:if test="${sysShareRights.enable=='0'}">否</c:if></span></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">是否同步:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><c:if test="${sysShareRights.sync=='1'}">是</c:if><c:if test="${sysShareRights.sync=='0'}">否</c:if></span></td>
  </tr>
  <tr>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">是否全部:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><c:if test="${sysShareRights.isAll=='1'}">是</c:if><c:if test="${sysShareRights.isAll=='0'}">否</c:if></span></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">关联数据的ID:</td>
   <td style="width:35%;" class="formInput">${sysShareRights.pkid}</td>
  </tr>
  <tr>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp"> 被共享的原:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${sysShareRights.source}</span></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">共享的目标:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${sysShareRights.target}</span></td>
  </tr>
  <tr>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">共享条目:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${sysShareRights.shares}</span></td>
   <td style="width:15%;" class="formTitle"></td>
   <td style="width:35%;" class="formInput"></td>
  </tr>
 </tbody>
</table>
</body>
</html>

