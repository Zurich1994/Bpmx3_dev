<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ page import="com.hotent.core.util.*" %>
<html>
<head>
	<title>编辑 业务发生量采集与计算</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var processId = $("#processId").val();
			//alert("processId1:"+processId);
			if($("#processId").val().trim() == "null"){
				//alert("进入");
				var processId = $("#processId").attr("processId");
				//alert("processId2:"+processId);
				$("#processId").val(processId);
			}
			
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#businessCollectCotForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#businessCollectCotForm').submit();
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
						window.location.href = "${ctx}/BusinessCollectCot/lc/businessCollectCot/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		function linkDateImpot(){
			var processId = $("#processId").val();
			window.location.href = "${ctx}/ImportData/lc/importData/edit.ht?processId="+processId;
		}
		
		function showPicture(){
			var processId = $("#processId").val();
			var startTime =$("#startTime").val();
			var endTime = $("#endTime").val();
			if(startTime==""){
				alert("请设置开始时间");
				return;
			}else if(endTime==""){
				alert("请设置结束时间");
				return;
			}
			window.location.href = "${ctx}/BusinessCollectCot/lc/businessCollectCot/showRule.ht?processId="+processId+"&startTime="+startTime+"&endTime="+endTime;
		}
		
		function goBack(){
			var processId = $("#processId").val();
			window.location.href = "${ctx}/BusinessCollectCot/lc/businessCollectCot/edit.ht?processId="+processId;
		}
	</script>
	<style type="text/css">
	.am-btn {
    display: inline-block;
    margin-bottom: 0px;
    padding: 0.625em 1em;
    font-size: 0.8rem;
    font-weight: 300;
    line-height: 0.6;
    text-align: center;
    white-space: nowrap;
    background-image: none;
    border: 1px solid transparent;
    cursor: pointer;
    outline: 0px none;
    transition: background-color 300ms ease-out 0s, border-color 300ms ease-out 0s;
	}	
	.am-btn-primary {
    color: #FFF;
    background-color: #0E90D2;
    border-color: #0E90D2;
}
	</style>
	

	 

</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty businessCollectCotItem.id}">
			        <span class="tbar-label"><span></span>编辑业务发生量采集与计算</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加业务发生量采集与计算</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a style="display: none;" class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a  class="link back" href="javascript:;" onclick="goBack()"><span></span>返回</a></div>
			
			</div>
		</div>
	</div>
	<form id="businessCollectCotForm" method="post" action="save.ht">
		
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">业务发生量</td>
  </tr>
  <tr>
   <td style="width:40%;" class="formTitle" align="right" nowrap="nowarp">流程定义id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input readonly="readonly" id="processId" processId="<%=String.valueOf(UniqueIdUtil.genId()) %>" name="processId" lablename="流程定义id" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="<%=request.getParameter("processId")%>"  readonly="readonly" /></span></td>
  </tr>
  <tr>
   <td style="width:40%;" class="formTitle" align="right" nowrap="nowarp">业务发生量时间段:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input readonly="readonly" id="startTime" name="startTime" lablename="业务发生开始时间" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="<%=request.getParameter("startTime") %>"  />
   -<input readonly="readonly" id="endTime" name="endTime" lablename="业务发生结束时间" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="<%=request.getParameter("endTime") %>" /></span></td>
  </tr>
  <tr>
   <td style="width:40%;" class="formTitle" align="right" nowrap="nowarp">业务发生量 :</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input readonly="readonly" id="businessResult" name="businessResult" lablename="业务发生量" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${result} "  /></span></td>
  </tr>
 </tbody>
</table>
	<div>
		<table border="0" cellpadding="2" cellspacing="0" width="100%" >
		<tbody>
		<tr>
		<%-- 
			<td style="width:33%;" align="right"><button class="am-btn am-btn-primary btn-loading-example" type="button" onclick="linkDateImpot()">参数导入</button></td>
			<td style="width:33%;" align="center"><button class="am-btn am-btn-primary btn-loading-example" type="button"  onclick="showPicture()">发生规律图</button></td>
			<td style="width:33%;" align="left"><button class="am-btn am-btn-primary btn-loading-example" type="button">发生量计算</button></td>
		--%>
		</tr>
		</tbody>
		</table>
	</div>	
	
			</div>
		</div>
		<input type="hidden" name="id" value="${businessCollectCot.id}"/>
	</form>
</body>
</html>
