<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 流程右键信息表</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#activityDetailForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#activityDetailForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.reload();
					}else{
						window.location.href = "${ctx}/activityDetail/activityDetail/activityDetail/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty activityDetailItem.id}">
			        <span class="tbar-label"><span></span>编辑流程右键信息表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加流程右键信息表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="activityDetailForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="6">流程右键信息表</td>
  </tr>
  <tr style="height:23px">
   <td style="width:13%" class="formTitle" nowrap="nowrap">流程ID:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="actDef_Id" isflag="tableflag" validate="{maxlength:50}" lablename="流程ID" value="${activityDetail.actDef_Id}" /></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动ID:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="activity_id" isflag="tableflag" validate="{maxlength:40}" lablename="活动ID" value="${activityDetail.activity_id}" /></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动名称:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="activity_name" isflag="tableflag" validate="{maxlength:200}" lablename="活动名称" value="${activityDetail.activity_name}" /></span></td>
  </tr>
  <tr style="height:23px">
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动形态:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="server_shape" validate="{empty:false}" lablename="活动形态" controltype="select"><option selected="selected" value="1" <c:if test='${activityDetail.server_shape=="1"}'>selected='selected'</c:if>>离线服务</option><option value="2" <c:if test='${activityDetail.server_shape=="2"}'>selected='selected'</c:if>>在线服务</option></select></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动方式:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="server_way" validate="{empty:false}" lablename="活动方式" controltype="select"><option selected="selected" value="1" <c:if test='${activityDetail.server_way=="1"}'>selected='selected'</c:if>>机器作业</option><option value="2" <c:if test='${activityDetail.server_way=="2"}'>selected='selected'</c:if>>人工作业</option><option value="3" <c:if test='${activityDetail.server_way=="3"}'>selected='selected'</c:if>>N/A</option></select></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动类型:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="server_class" validate="{empty:false}" lablename="活动类型" controltype="select"><option selected="selected" value="1" <c:if test='${activityDetail.server_class=="1"}'>selected='selected'</c:if>>自动检查服务</option><option value="2" <c:if test='${activityDetail.server_class=="2"}'>selected='selected'</c:if>>自动变换服务</option><option value="3" <c:if test='${activityDetail.server_class=="3"}'>selected='selected'</c:if>>自动判断服务</option><option value="4" <c:if test='${activityDetail.server_class=="4"}'>selected='selected'</c:if>>自动计算服务</option><option value="5" <c:if test='${activityDetail.server_class=="5"}'>selected='selected'</c:if>>N/A</option></select></span></td>
  </tr>
  <tr style="height:23px">
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动来源:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="server_source" validate="{empty:false}" lablename="活动来源" controltype="select"><option selected="selected" value="1" <c:if test='${activityDetail.server_source=="1"}'>selected='selected'</c:if>>本地服务</option><option value="2" <c:if test='${activityDetail.server_source=="2"}'>selected='selected'</c:if>>利用外部服务</option><option value="3" <c:if test='${activityDetail.server_source=="3"}'>selected='selected'</c:if>>外部利用服务</option><option value="4" <c:if test='${activityDetail.server_source=="4"}'>selected='selected'</c:if>>利用公共服务</option><option value="5" <c:if test='${activityDetail.server_source=="5"}'>selected='selected'</c:if>>公共利用服务</option></select></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动性质:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="server_nature" validate="{empty:false}" lablename="活动性质" controltype="select"><option selected="selected" value="1" <c:if test='${activityDetail.server_nature=="1"}'>selected='selected'</c:if>>新增服务</option><option value="2" <c:if test='${activityDetail.server_nature=="2"}'>selected='selected'</c:if>>利旧服务</option></select></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">信息形态:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="info_shape" validate="{empty:false}" lablename="信息形态" controltype="select"><option selected="selected" value="1" <c:if test='${activityDetail.info_shape=="1"}'>selected='selected'</c:if>>静态信息</option><option value="2" <c:if test='${activityDetail.info_shape=="2"}'>selected='selected'</c:if>>动态信息</option></select></span></td>
  </tr>
  <tr style="height:23px">
   <td style="width:13%" class="formTitle" nowrap="nowrap">信息标准化:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="info_stand" validate="{empty:false}" lablename="信息标准化" controltype="select"><option selected="selected" value="1" <c:if test='${activityDetail.info_stand=="1"}'>selected='selected'</c:if>>标准信息</option><option value="2" <c:if test='${activityDetail.info_stand=="2"}'>selected='selected'</c:if>>非标准信息</option></select></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">信息类型:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="info_type" validate="{empty:false}" lablename="信息类型" controltype="select"><option selected="selected" value="1" <c:if test='${activityDetail.info_type=="1"}'>selected='selected'</c:if>>本地信息</option><option value="2" <c:if test='${activityDetail.info_type=="2"}'>selected='selected'</c:if>>利用外部信息</option><option value="3" <c:if test='${activityDetail.info_type=="3"}'>selected='selected'</c:if>>外部利用信息</option><option value="4" <c:if test='${activityDetail.info_type=="4"}'>selected='selected'</c:if>>利用公共信息</option><option value="5" <c:if test='${activityDetail.info_type=="5"}'>selected='selected'</c:if>>公共利用信息</option></select></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动类别:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="server_type" validate="{empty:false}" lablename="活动类别" controltype="select"><option selected="selected" value="1" <c:if test='${activityDetail.server_type=="1"}'>selected='selected'</c:if>>事务服务</option><option value="2" <c:if test='${activityDetail.server_type=="2"}'>selected='selected'</c:if>>计算服务</option></select></span></td>
  </tr>
  <tr style="height:23px">
   <td style="width:13%" class="formTitle" nowrap="nowrap">活动成熟度:</td>
   <td style="width:20%" class="formInput"><input name="op_comp" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':1,'decimalValue':0}" value="${activityDetail.op_comp}" /></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">作业名:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="work_name" isflag="tableflag" validate="{maxlength:400}" lablename="作业名" value="${activityDetail.work_name}" /></span></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">信息量:</td>
   <td style="width:20%" class="formInput"><input name="op_info_sum" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':1,'decimalValue':0}" value="${activityDetail.op_info_sum}" /></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${activityDetail.id}"/>
	</form>
</body>
</html>
