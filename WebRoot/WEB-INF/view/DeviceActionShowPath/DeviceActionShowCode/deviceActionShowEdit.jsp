<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 设备动作展现表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#deviceActionShowForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#deviceActionShowForm').submit();
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
						window.location.href = "${ctx}/DeviceActionShowPath/DeviceActionShowCode/deviceActionShow/list.ht";
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
			    <c:when test="${not empty deviceActionShowItem.id}">
			        <span class="tbar-label"><span></span>编辑设备动作展现表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加设备动作展现表</span>
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
	<form id="deviceActionShowForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">设备动作展现表</td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">节点设备id:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="node_dev_id" isflag="tableflag" validate="{maxlength:20}" lablename="节点设备id" value="${deviceActionShow.node_dev_id}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">发起时间:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="start_time" isflag="tableflag" validate="{maxlength:50}" lablename="发起时间" value="${deviceActionShow.start_time}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">动作名称:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="actionname" isflag="tableflag" validate="{maxlength:20}" lablename="动作名称" value="${deviceActionShow.actionname}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">触发动作间隔时间:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="actiontime" isflag="tableflag" validate="{maxlength:10}" lablename="触发动作间隔时间" value="${deviceActionShow.actiontime}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">动作循环次数:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="actioncount" isflag="tableflag" validate="{maxlength:10}" lablename="动作循环次数" value="${deviceActionShow.actioncount}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">负载类型:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="lode_type" isflag="tableflag" validate="{maxlength:15}" lablename="负载类型" value="${deviceActionShow.lode_type}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">利用率值:</td>
   <td style="width:80%" class="formInput"><input name="load_use_rate" validate="{number:true,maxIntLen:18,maxDecimalLen:5}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':5}" value="${deviceActionShow.load_use_rate}" /></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">设备类型:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="dev_type_code" isflag="tableflag" validate="{maxlength:10}" lablename="设备类型" value="${deviceActionShow.dev_type_code}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">重复次数:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="sameaction_sign" isflag="tableflag" validate="{maxlength:20}" lablename="重复次数" value="${deviceActionShow.sameaction_sign}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">重复次数1:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="sameaction_sign1" isflag="tableflag" validate="{maxlength:20}" lablename="重复次数1" value="${deviceActionShow.sameaction_sign1}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">节点颜色:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="node_color" isflag="tableflag" validate="{maxlength:20}" lablename="节点颜色" value="${deviceActionShow.node_color}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">节点动画:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="node_flash" isflag="tableflag" validate="{maxlength:20}" lablename="节点动画" value="${deviceActionShow.node_flash}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">a:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="a" isflag="tableflag" validate="{maxlength:50}" lablename="a" value="${deviceActionShow.a}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">b:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="b" isflag="tableflag" validate="{maxlength:100}" lablename="b" value="${deviceActionShow.b}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">c:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="c" isflag="tableflag" validate="{maxlength:100}" lablename="c" value="${deviceActionShow.c}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">d:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="d" isflag="tableflag" validate="{maxlength:100}" lablename="d" value="${deviceActionShow.d}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${deviceActionShow.id}"/>
	</form>
</body>
</html>
