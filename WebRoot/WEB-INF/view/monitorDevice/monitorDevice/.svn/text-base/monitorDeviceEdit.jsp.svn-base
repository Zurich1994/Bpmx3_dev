<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 监控设备</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#monitorDeviceForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#monitorDeviceForm').submit();
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
						window.location.href = "${ctx}/monitorDevice/monitorDevice/monitorDevice/list_b.ht?typeId=${typeId}";
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
			    <c:when test="${not empty monitorDeviceItem.id}">
			        <span class="tbar-label"><span></span>编辑监控设备</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加监控设备</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list_b.ht?typeId=${typeId}"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="monitorDeviceForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
 <input type="hidden" name="typeid" value="${monitorDevice.typeid}"  />
  <tr>
   <td colspan="2" class="formHead">监控设备</td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" nowrap="nowarp" align="right">设备名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="name" lablename="设备名" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${monitorDevice.name}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">操作系统类型:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="os_type" lablename="操作系统类型" controltype="select" validate="{empty:false}"><option value="windows" <c:if test='${monitorDevice.os_type=="windows"}'>selected='selected'</c:if>>windows</option><option value="linux" <c:if test='${monitorDevice.os_type=="linux"}'>selected='selected'</c:if>>linux</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">主机ip:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ip" lablename="主机ip" class="inputText" validate="{maxlength:100,required:true,ipAddress:true}" isflag="tableflag" type="text" value="${monitorDevice.ip}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">品牌:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="brand" lablename="品牌" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${monitorDevice.brand}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">用户名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="uesrname" lablename="用户名" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${monitorDevice.uesrname}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">密码:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="password" lablename="密码" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${monitorDevice.password}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">SNMP端口号:</td>
   <td class="formInput" style="width:80%;"><input name="port" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0,required:true,isWebSign:true}" type="text" value="${monitorDevice.port}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">团体号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="community" lablename="团体号" class="inputText" validate="{maxlength:100,required:true,isWebSign:true}" isflag="tableflag" type="text" value="${monitorDevice.community}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${monitorDevice.id}"/>
	</form>
</body>
</html>
