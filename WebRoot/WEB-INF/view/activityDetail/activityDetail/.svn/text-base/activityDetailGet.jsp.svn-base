
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>流程右键信息表明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">流程右键信息表详细信息</span>
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
   <td class="formHead" colspan="6">流程右键信息表</td>
  </tr>
  <tr style="height:23px">
   <td style="width:13%" class="formTitle" nowrap="nowrap">流程ID:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${activityDetail.actDef_Id}</span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动ID:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${activityDetail.activity_id}</span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动名称:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${activityDetail.activity_name}</span></td>
  </tr>
  <tr style="height:23px">
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动形态:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><c:if test='${activityDetail.server_shape==1}'>离线服务</c:if><c:if test='${activityDetail.server_shape==2}'>在线服务</c:if></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动方式:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><c:if test='${activityDetail.server_way==1}'>机器作业</c:if><c:if test='${activityDetail.server_way==2}'>人工作业</c:if><c:if test='${activityDetail.server_way==3}'>N/A</c:if></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动类型:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><c:if test='${activityDetail.server_class==1}'>自动检查服务</c:if><c:if test='${activityDetail.server_class==2}'>自动变换服务</c:if><c:if test='${activityDetail.server_class==3}'>自动判断服务</c:if><c:if test='${activityDetail.server_class==4}'>自动计算服务</c:if><c:if test='${activityDetail.server_class==5}'>N/A</c:if></span></td>
  </tr>
  <tr style="height:23px">
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动来源:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><c:if test='${activityDetail.server_source==1}'>本地服务</c:if><c:if test='${activityDetail.server_source==2}'>利用外部服务</c:if><c:if test='${activityDetail.server_source==3}'>外部利用服务</c:if><c:if test='${activityDetail.server_source==4}'>利用公共服务</c:if><c:if test='${activityDetail.server_source==5}'>公共利用服务</c:if></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动性质:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><c:if test='${activityDetail.server_nature==1}'>新增服务</c:if><c:if test='${activityDetail.server_nature==2}'>利旧服务</c:if></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">信息形态:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><c:if test='${activityDetail.info_shape==1}'>静态信息</c:if><c:if test='${activityDetail.info_shape==2}'>动态信息</c:if></span></td>
  </tr>
  <tr style="height:23px">
   <td style="width:13%" class="formTitle" nowrap="nowrap">信息标准化:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><c:if test='${activityDetail.info_stand==1}'>标准信息</c:if><c:if test='${activityDetail.info_stand==2}'>非标准信息</c:if></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">信息类型:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><c:if test='${activityDetail.info_type==1}'>本地信息</c:if><c:if test='${activityDetail.info_type==2}'>利用外部信息</c:if><c:if test='${activityDetail.info_type==3}'>外部利用信息</c:if><c:if test='${activityDetail.info_type==4}'>利用公共信息</c:if><c:if test='${activityDetail.info_type==5}'>公共利用信息</c:if></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动类别:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><c:if test='${activityDetail.server_type==1}'>事务服务</c:if><c:if test='${activityDetail.server_type==2}'>计算服务</c:if></span></td>
  </tr>
  <tr style="height:23px">
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动成熟度:</td>
   <td style="width:20%" class="formInput">${activityDetail.op_comp}</td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">作业名:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${activityDetail.work_name}</span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">信息量:</td>
   <td style="width:20%" class="formInput">${activityDetail.op_info_sum}</td>
  </tr>
 </tbody>
</table>
</body>
</html>

