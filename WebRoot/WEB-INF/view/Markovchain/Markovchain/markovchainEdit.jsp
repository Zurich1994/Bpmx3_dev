<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 马尔科夫链表</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/subform.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#markovchainForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				$("#saveData").val(1);
				if(frm.valid()){
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					if(OfficePlugin.officeObjs.length>0){
						OfficePlugin.submit(function(){
							frm.handleFieldName();
							$('#markovchainForm').submit();
						});
					}else{
						frm.handleFieldName();
						$('#markovchainForm').submit();
					}
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = window.location.href;
					}else{
					    window.location.href = window.location.href;
						//window.location.href = "${ctx}/Markovchain/Markovchain/markovchain/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel" style="height:100%;overflow:auto;">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty markovchainItem.id}">
			        <span class="tbar-label"><span></span>编辑马尔科夫链表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加马尔科夫链表</span>
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
	<form id="markovchainForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="4" class="formHead">马尔科夫链表</td>
  </tr>
  <tr>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">马尔科夫链名称:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="m:markovchain:markovchainNAME" lablename="马尔科夫链名称" class="inputText" validate="{maxlength:100}" isflag="tableflag" value="${markovchain.markovchainNAME}" /></span></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">是否参与仿真:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="m:markovchain:isSimulation" lablename="是否参与仿真" controltype="select" validate="{empty:false}" value="${markovchain.isSimulation}"><option value="是">是</option><option value="否">否</option></select></span></td>
  </tr>
  <tr>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">所属流程:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="m:markovchain:belongto" lablename="所属流程" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${markovchain.belongto}" /></span></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">依赖马尔科夫链:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="m:markovchain:dependmark" lablename="依赖马尔科夫链" class="inputText" validate="{maxlength:100}" isflag="tableflag" value="${markovchain.dependmark}" /></span></td>
  </tr>
  <tr>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">马尔科夫链发生次数:</td>
   <td style="width:35%;" class="formInput"><input name="m:markovchain:frequency" type="text" showtype="{"coinValue":"","isShowComdify":0,"decimalValue":0}" validate="{number:true,maxIntLen:9,maxDecimalLen:0}" value="${markovchain.frequency}" /></td>
   <td align="right" style="word-break:break-all;" class="formTitle" nowrap="nowarp">马尔科夫链的概率:</td>
   <td style="width:35%;" class="formInput"><input name="m:markovchain:probability" type="text" showtype="{"coinValue":"","isShowComdify":0,"decimalValue":2}" validate="{number:true,maxIntLen:1,maxDecimalLen:2}" value="${markovchain.probability}" /></td>
  </tr>
  <tr>
   <td align="right" style="width:15%;word-break:break-all;" class="formTitle" nowrap="nowarp"><span style="font-size:12px;text-align:right;background-color:#f0f1f1;">马尔科夫链详细备注:</span></td>
   <td style="width:35%;word-break:break-all;" class="formInput" rowspan="1" colspan="3"><br /><span name="editable-input" style="display:inline-block;" isflag="tableflag"><textarea name="m:markovchain:markovchainDes" lablename="马尔科夫链详细备注" class="l-textarea" rows="5" cols="40" validate="{maxlength:2000}" isflag="tableflag" style="margin:2px;width:1089px;height:75px;">${markovchain.markovchainDes}</textarea></span></td>
  </tr>
  <tr>
   <td align="right" style="width:15%;word-break:break-all;" class="formTitle" nowrap="nowarp"><span style="font-size:12px;text-align:right;background-color:#f0f1f1;">马尔科夫链xml文件:</span></td>
   <td style="width:35%;word-break:break-all;" class="formInput" rowspan="1" colspan="3"><textarea name="m:markovchain:markovchainXML" validate="{empty:false}" style="margin:2px;width:1092px;height:61px;">${markovchain.markovchainXML}</textarea></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${markovchain.id}"/>
		<input type="hidden" id="saveData" name="saveData"/>
		<input type="hidden" name="executeType"  value="start" />
	</form>
</body>
</html>
