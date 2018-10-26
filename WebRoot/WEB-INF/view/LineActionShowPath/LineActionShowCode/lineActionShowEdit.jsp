<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 线路动作展现表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#lineActionShowForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#lineActionShowForm').submit();
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
						window.location.href = "${ctx}/LineActionShowPath/LineActionShowCode/lineActionShow/list.ht";
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
			    <c:when test="${not empty lineActionShowItem.id}">
			        <span class="tbar-label"><span></span>编辑线路动作展现表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加线路动作展现表</span>
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
	<form id="lineActionShowForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">线路动作展现表</td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">线路id:</td>
   <td style="width:80%" class="formInput"><input name="line_id" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${lineActionShow.line_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">时间:</td>
   <td style="width:80%" class="formInput"><input name="start_time" validate="{number:true,maxIntLen:10,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${lineActionShow.start_time}" /></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">动作名称:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="actionname" validate="{maxlength:20}" isflag="tableflag" lablename="动作名称" value="${lineActionShow.actionname}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">触发动作间隔时间:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="actiontime" validate="{maxlength:10}" isflag="tableflag" lablename="触发动作间隔时间" value="${lineActionShow.actiontime}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">动作循环次数:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="actioncount" validate="{maxlength:10}" isflag="tableflag" lablename="动作循环次数" value="${lineActionShow.actioncount}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">重复次数:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="sameaction_sign" validate="{maxlength:20}" isflag="tableflag" lablename="重复次数" value="${lineActionShow.sameaction_sign}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">重复次数1:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="sameaction_sign1" validate="{maxlength:20}" isflag="tableflag" lablename="重复次数1" value="${lineActionShow.sameaction_sign1}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">线路颜色:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="line_color" validate="{maxlength:20}" isflag="tableflag" lablename="线路颜色" value="${lineActionShow.line_color}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">线路颜色1:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="line_color1" validate="{maxlength:20}" isflag="tableflag" lablename="线路颜色1" value="${lineActionShow.line_color1}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">传输信息:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="line_info" validate="{maxlength:100}" isflag="tableflag" lablename="传输信息" value="${lineActionShow.line_info}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">a:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="a" validate="{maxlength:100}" isflag="tableflag" lablename="a" value="${lineActionShow.a}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">b:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="b" validate="{maxlength:100}" isflag="tableflag" lablename="b" value="${lineActionShow.b}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" nowrap="nowrap" align="right">c:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="c" validate="{maxlength:100}" isflag="tableflag" lablename="c" value="${lineActionShow.c}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${lineActionShow.id}"/>
	</form>
</body>
</html>
