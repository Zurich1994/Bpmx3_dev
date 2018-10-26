<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 历史数据信息表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#historyDataLcForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#historyDataLcForm').submit();
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
						window.location.href = "${ctx}/HistoryData/lc/historyDataLc/list.ht";
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
			    <c:when test="${not empty historyDataLcItem.id}">
			        <span class="tbar-label"><span></span>编辑历史数据信息表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加历史数据信息表</span>
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
	<form id="historyDataLcForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">历史数据信息表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">历史数据编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="historyDataId" lablename="历史数据编号" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${historyDataLc.historyDataId}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="processId" lablename="流程编号" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${historyDataLc.processId}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">时间类别:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="timeType" lablename="时间类别" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${historyDataLc.timeType}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">发生量:</td>
   <td class="formInput" style="width:80%;"><input name="occurenceAmount" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" type="text" value="${historyDataLc.occurenceAmount}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">发生时间:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="occurenceTime" lablename="发生时间" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${historyDataLc.occurenceTime}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${historyDataLc.id}"/>
	</form>
</body>
</html>
