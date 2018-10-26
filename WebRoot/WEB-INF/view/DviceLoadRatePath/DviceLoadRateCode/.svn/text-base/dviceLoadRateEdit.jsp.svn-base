<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 设备负载值及利用率表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#dviceLoadRateForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#dviceLoadRateForm').submit();
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
						window.location.href = "${ctx}/DviceLoadRatePath/DviceLoadRateCode/dviceLoadRate/list.ht";
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
			    <c:when test="${not empty dviceLoadRateItem.id}">
			        <span class="tbar-label"><span></span>编辑设备负载值及利用率表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加设备负载值及利用率表</span>
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
	<form id="dviceLoadRateForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">设备负载值及利用率表</td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">节点设备id:</td>
   <td style="width:80%" class="formInput"><input name="node_dev_id" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${dviceLoadRate.node_dev_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">时间:</td>
   <td style="width:80%" class="formInput"><input name="active_start" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${dviceLoadRate.active_start}" /></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">负载类型:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="load_type" validate="{maxlength:20}" isflag="tableflag" lablename="负载类型" value="${dviceLoadRate.load_type}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">负载值:</td>
   <td style="width:80%" class="formInput"><input name="load_value" validate="{number:true,maxIntLen:18,maxDecimalLen:3}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':3}" value="${dviceLoadRate.load_value}" /></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">资源类型:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="load_info" validate="{maxlength:10}" isflag="tableflag" lablename="资源类型" value="${dviceLoadRate.load_info}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">利用率:</td>
   <td style="width:80%" class="formInput"><input name="load_use_rate" validate="{number:true,maxIntLen:13,maxDecimalLen:5}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':5}" value="${dviceLoadRate.load_use_rate}" /></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">a:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="a" validate="{maxlength:50}" isflag="tableflag" lablename="a" value="${dviceLoadRate.a}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">b:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="b" validate="{maxlength:100}" isflag="tableflag" lablename="b" value="${dviceLoadRate.b}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">c:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="c" validate="{maxlength:100}" isflag="tableflag" lablename="c" value="${dviceLoadRate.c}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${dviceLoadRate.id}"/>
	</form>
</body>
</html>
