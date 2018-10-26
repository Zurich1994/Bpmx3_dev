<%@page language="java" pageEncoding="UTF-8" import="com.hotent.platform.model.system.Dictionary;"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 flowWebService</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#flowwebserviceForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#flowwebserviceForm').submit();
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
						window.location.href = "${ctx}/flowwebservice/flowwebservice/flowwebservice/list.ht";
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
			    <c:when test="${not empty flowwebserviceItem.id}">
			        <span class="tbar-label"><span></span>编辑flowWebService</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加flowWebService</span>
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
	<form id="flowwebserviceForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">flowWebService</td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务名称:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="serviceName" isflag="tableflag" validate="{maxlength:50}" lablename="serviceName" value="${flowwebservice.serviceName}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务地址:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="serviceUrl" isflag="tableflag" validate="{maxlength:100}" lablename="serviceUrl" value="${flowwebservice.serviceUrl}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务方法名:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="serviceFuncName" isflag="tableflag" validate="{maxlength:100}" lablename="serviceFuncName" value="${flowwebservice.serviceFuncName}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务类型:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input">
   <select name="serviceType" validate="{empty:false}" lablename="serviceType" controltype="select">
   <option>${ServiceType.get(0).getItemName()}</option>
 
                  
                	<%
   		                 
   
                       %>
                  </select>
                  </span></td>
  
  
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务状态:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input2"><select name="serviceState" validate="{empty:false}" lablename="serviceState" controltype="select"><option selected="selected" value="A" <c:if test='${flowwebservice.serviceState=="A"}'>selected='selected'</c:if>>启用</option><option value="B" <c:if test='${flowwebservice.serviceState=="B"}'>selected='selected'</c:if>>未启用</option><option value="C" <c:if test='${flowwebservice.serviceState=="C"}'>selected='selected'</c:if>>禁止</option><option value="D" <c:if test='${flowwebservice.serviceState=="D"}'>selected='selected'</c:if>>暂停</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">流程ID:</td>
   <td style="width:80%" class="formInput"><input name="defid" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${flowwebservice.defid}" /></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${flowwebservice.id}"/>
	</form>
</body>
</html>
