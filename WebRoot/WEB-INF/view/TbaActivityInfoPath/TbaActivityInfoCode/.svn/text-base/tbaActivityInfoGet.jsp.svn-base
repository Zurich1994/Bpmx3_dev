
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>活动操作信息表明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">活动操作信息表详细信息</span>
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
   <td class="formHead" colspan="2">活动操作信息表</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">项目ID:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${tbaActivityInfo.item_id}</span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">作业类别:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${tbaActivityInfo.work_type}</span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">作业项:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${tbaActivityInfo.work_item}</span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">作业号:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${tbaActivityInfo.work_id}</span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">作业名称:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${tbaActivityInfo.flow_name}</span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动ID:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${tbaActivityInfo.activity_id}</span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动名称:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${tbaActivityInfo.activity_name}</span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动形态:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><c:if test='${tbaActivityInfo.server_shape==1}'>离线服务</c:if><c:if test='${tbaActivityInfo.server_shape==2}'>在线服务</c:if></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动方式:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><c:if test='${tbaActivityInfo.server_way==1}'>机器作业</c:if><c:if test='${tbaActivityInfo.server_way==2}'>人工作业</c:if><c:if test='${tbaActivityInfo.server_way==3}'>N/A</c:if></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动类型:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><c:if test='${tbaActivityInfo.server_class==1}'>自动检查服务</c:if><c:if test='${tbaActivityInfo.server_class==2}'>自动变换服务</c:if><c:if test='${tbaActivityInfo.server_class==3}'>自动判断服务</c:if><c:if test='${tbaActivityInfo.server_class==4}'>自动计算服务</c:if><c:if test='${tbaActivityInfo.server_class==5}'>N/A</c:if></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动来源:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><c:if test='${tbaActivityInfo.server_source==1}'>本地服务</c:if><c:if test='${tbaActivityInfo.server_source==2}'>利用外部服务</c:if><c:if test='${tbaActivityInfo.server_source==3}'>外部利用服务</c:if><c:if test='${tbaActivityInfo.server_source==4}'>利用公共服务</c:if><c:if test='${tbaActivityInfo.server_source==5}'>公共利用服务</c:if></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动性质:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><c:if test='${tbaActivityInfo.server_nature==1}'>新增服务</c:if><c:if test='${tbaActivityInfo.server_nature==2}'>利旧服务</c:if></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">信息形态:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><c:if test='${tbaActivityInfo.info_shape==1}'>静态信息</c:if><c:if test='${tbaActivityInfo.info_shape==2}'>动态信息</c:if></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">信息标准化:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><c:if test='${tbaActivityInfo.info_stand==1}'>标准信息</c:if><c:if test='${tbaActivityInfo.info_stand==2}'>非标准信息</c:if></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">信息类型:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><c:if test='${tbaActivityInfo.info_type==1}'>本地信息</c:if><c:if test='${tbaActivityInfo.info_type==2}'>利用外部信息</c:if><c:if test='${tbaActivityInfo.info_type==3}'>外部利用信息</c:if><c:if test='${tbaActivityInfo.info_type==4}'>利用公共信息</c:if><c:if test='${tbaActivityInfo.info_type==5}'>公共利用信息</c:if></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动类别:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><c:if test='${tbaActivityInfo.server_type==1}'>事务服务</c:if><c:if test='${tbaActivityInfo.server_type==2}'>计算服务</c:if></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动成熟度:</td>
   <td class="formInput" style="width:80%;">${tbaActivityInfo.op_comp}</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">作业名:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${tbaActivityInfo.work_name}</span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">信息量:</td>
   <td class="formInput" style="width:80%;">${tbaActivityInfo.op_info_sum}</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">A:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${tbaActivityInfo.A}</span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">B:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${tbaActivityInfo.B}</span></td>
  </tr>
 </tbody>
</table>
</body>
</html>

