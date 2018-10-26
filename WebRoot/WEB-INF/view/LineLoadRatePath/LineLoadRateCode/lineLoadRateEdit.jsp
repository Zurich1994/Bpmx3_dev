<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 线路负载值及利用率表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#lineLoadRateForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#lineLoadRateForm').submit();
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
						window.location.href = "${ctx}/LineLoadRatePath/LineLoadRateCode/lineLoadRate/list.ht";
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
			    <c:when test="${not empty lineLoadRateItem.id}">
			        <span class="tbar-label"><span></span>编辑线路负载值及利用率表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加线路负载值及利用率表</span>
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
	<form id="lineLoadRateForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">线路负载值及利用率表</td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">线路id:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="line_id" isflag="tableflag" validate="{maxlength:20}" lablename="线路id" value="${lineLoadRate.line_id}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">发起时间:</td>
   <td style="width:80%" class="formInput"><input name="time" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${lineLoadRate.time}" /></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">流量值:</td>
   <td style="width:80%" class="formInput"><input name="flownum" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${lineLoadRate.flownum}" /></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">负载类型:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="load_type" isflag="tableflag" validate="{maxlength:20}" lablename="负载类型" value="${lineLoadRate.load_type}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">利用率:</td>
   <td style="width:80%" class="formInput"><input name="load_use_rate" validate="{number:true,maxIntLen:10,maxDecimalLen:5}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':5}" value="${lineLoadRate.load_use_rate}" /></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">a:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="a" isflag="tableflag" validate="{maxlength:100}" lablename="a" value="${lineLoadRate.a}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">b:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="b" isflag="tableflag" validate="{maxlength:100}" lablename="b" value="${lineLoadRate.b}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">c:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="c" isflag="tableflag" validate="{maxlength:100}" lablename="c" value="${lineLoadRate.c}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${lineLoadRate.id}"/>
	</form>
</body>
</html>
