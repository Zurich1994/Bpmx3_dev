<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 活动操作信息表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#tbaActivityInfoForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#tbaActivityInfoForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "${ctx}/TbaActivityInfoPath/TbaActivityInfoCode/tbaActivityInfo/list.ht";
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
			    <c:when test="${not empty tbaActivityInfoItem.id}">
			        <span class="tbar-label"><span></span>编辑活动操作信息表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加活动操作信息表</span>
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
	<form id="tbaActivityInfoForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">活动操作信息表</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">项目ID:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="item_id" class="inputText" type="text" isflag="tableflag" lablename="项目ID" validate="{maxlength:100,required:true}" value="${tbaActivityInfo.item_id}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">作业类别:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="work_type" class="inputText" type="text" isflag="tableflag" lablename="作业类别" validate="{maxlength:40}" value="${tbaActivityInfo.work_type}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">作业项:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="work_item" class="inputText" type="text" isflag="tableflag" lablename="作业项" validate="{maxlength:40}" value="${tbaActivityInfo.work_item}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">作业号:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="work_id" class="inputText" type="text" isflag="tableflag" lablename="作业号" validate="{maxlength:20}" value="${tbaActivityInfo.work_id}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">作业名称:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="flow_name" class="inputText" type="text" isflag="tableflag" lablename="作业名称" validate="{maxlength:400}" value="${tbaActivityInfo.flow_name}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动ID:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="activity_id" class="inputText" type="text" isflag="tableflag" lablename="活动ID" validate="{maxlength:40}" value="${tbaActivityInfo.activity_id}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动名称:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="activity_name" class="inputText" type="text" isflag="tableflag" lablename="活动名称" validate="{maxlength:200}" value="${tbaActivityInfo.activity_name}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动形态:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><select name="server_shape" lablename="活动形态" validate="{empty:false}" controltype="select"><option value="1" <c:if test='${tbaActivityInfo.server_shape=="1"}'>selected='selected'</c:if>>离线服务</option><option value="2" <c:if test='${tbaActivityInfo.server_shape=="2"}'>selected='selected'</c:if>>在线服务</option></select></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动方式:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><select name="server_way" lablename="活动方式" validate="{empty:false}" controltype="select"><option value="1" <c:if test='${tbaActivityInfo.server_way=="1"}'>selected='selected'</c:if>>机器作业</option><option value="2" <c:if test='${tbaActivityInfo.server_way=="2"}'>selected='selected'</c:if>>人工作业</option><option value="3" <c:if test='${tbaActivityInfo.server_way=="3"}'>selected='selected'</c:if>>N/A</option></select></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动类型:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><select name="server_class" lablename="活动类型" validate="{empty:false}" controltype="select"><option value="1" <c:if test='${tbaActivityInfo.server_class=="1"}'>selected='selected'</c:if>>自动检查服务</option><option value="2" <c:if test='${tbaActivityInfo.server_class=="2"}'>selected='selected'</c:if>>自动变换服务</option><option value="3" <c:if test='${tbaActivityInfo.server_class=="3"}'>selected='selected'</c:if>>自动判断服务</option><option value="4" <c:if test='${tbaActivityInfo.server_class=="4"}'>selected='selected'</c:if>>自动计算服务</option><option value="5" <c:if test='${tbaActivityInfo.server_class=="5"}'>selected='selected'</c:if>>N/A</option></select></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动来源:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><select name="server_source" lablename="活动来源" validate="{empty:false}" controltype="select"><option value="1" <c:if test='${tbaActivityInfo.server_source=="1"}'>selected='selected'</c:if>>本地服务</option><option value="2" <c:if test='${tbaActivityInfo.server_source=="2"}'>selected='selected'</c:if>>利用外部服务</option><option value="3" <c:if test='${tbaActivityInfo.server_source=="3"}'>selected='selected'</c:if>>外部利用服务</option><option value="4" <c:if test='${tbaActivityInfo.server_source=="4"}'>selected='selected'</c:if>>利用公共服务</option><option value="5" <c:if test='${tbaActivityInfo.server_source=="5"}'>selected='selected'</c:if>>公共利用服务</option></select></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动性质:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><select name="server_nature" lablename="活动性质" validate="{empty:false}" controltype="select"><option value="1" <c:if test='${tbaActivityInfo.server_nature=="1"}'>selected='selected'</c:if>>新增服务</option><option value="2" <c:if test='${tbaActivityInfo.server_nature=="2"}'>selected='selected'</c:if>>利旧服务</option></select></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">信息形态:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><select name="info_shape" lablename="信息形态" validate="{empty:false}" controltype="select"><option value="1" <c:if test='${tbaActivityInfo.info_shape=="1"}'>selected='selected'</c:if>>静态信息</option><option value="2" <c:if test='${tbaActivityInfo.info_shape=="2"}'>selected='selected'</c:if>>动态信息</option></select></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">信息标准化:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><select name="info_stand" lablename="信息标准化" validate="{empty:false}" controltype="select"><option value="1" <c:if test='${tbaActivityInfo.info_stand=="1"}'>selected='selected'</c:if>>标准信息</option><option value="2" <c:if test='${tbaActivityInfo.info_stand=="2"}'>selected='selected'</c:if>>非标准信息</option></select></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">信息类型:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><select name="info_type" lablename="信息类型" validate="{empty:false}" controltype="select"><option value="1" <c:if test='${tbaActivityInfo.info_type=="1"}'>selected='selected'</c:if>>本地信息</option><option value="2" <c:if test='${tbaActivityInfo.info_type=="2"}'>selected='selected'</c:if>>利用外部信息</option><option value="3" <c:if test='${tbaActivityInfo.info_type=="3"}'>selected='selected'</c:if>>外部利用信息</option><option value="4" <c:if test='${tbaActivityInfo.info_type=="4"}'>selected='selected'</c:if>>利用公共信息</option><option value="5" <c:if test='${tbaActivityInfo.info_type=="5"}'>selected='selected'</c:if>>公共利用信息</option></select></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动类别:</td>
   <td class="formInput" style="width:80%;"><span class="selectinput" style="padding:2px;display:inline-block;" name="editable-input"><select name="server_type" lablename="活动类别" validate="{empty:false}" controltype="select"><option value="1" <c:if test='${tbaActivityInfo.server_type=="1"}'>selected='selected'</c:if>>事务服务</option><option value="2" <c:if test='${tbaActivityInfo.server_type=="2"}'>selected='selected'</c:if>>计算服务</option></select></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">活动成熟度:</td>
   <td class="formInput" style="width:80%;"><input name="op_comp" type="text" validate="{number:true,maxIntLen:13,maxDecimalLen:4}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':4}" value="${tbaActivityInfo.op_comp}" /></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">作业名:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="work_name" class="inputText" type="text" isflag="tableflag" lablename="作业名" validate="{maxlength:400}" value="${tbaActivityInfo.work_name}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">信息量:</td>
   <td class="formInput" style="width:80%;"><input name="op_info_sum" type="text" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${tbaActivityInfo.op_info_sum}" /></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">A:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="A" class="inputText" type="text" isflag="tableflag" lablename="A" validate="{maxlength:50}" value="${tbaActivityInfo.A}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">B:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="B" class="inputText" type="text" isflag="tableflag" lablename="B" validate="{maxlength:100}" value="${tbaActivityInfo.B}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${tbaActivityInfo.id}"/>
	</form>
</body>
</html>
