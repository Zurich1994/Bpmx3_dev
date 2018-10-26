<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<% String basePath = request.getContextPath(); %>
<head>
	<title>编辑 参数依赖</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#relyParamTr").hide();
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#paramRelyForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#paramRelyForm').submit();
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
						window.location.href = "${ctx}/ParamRely/lc/paramRely/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		function selectScript(obj) {
			var txtObj=$(obj).siblings("textarea")[0];
			ScriptDialog({
				callback : function(script) {
					$.insertText(txtObj,script);
				}
			});
		};
		
		
		
		
		function changeDefault(){
			var selectText = $("#paraTypeSelect").find("option:selected").text();
			var selObj = $("#relyItemSelect");
			var value="--------请选择-------";
 			var text="--------请选择-------";
			if(selectText == "流程参数依赖"){
				$("#relyParamTr").hide();
				var selOpt = $("#relyItemSelect option");
				selOpt.remove();
				//alert("流程");
				
 				
 				selObj.append("<option value='"+value+"'>"+text+"</option>");
 				var flowRelyStr = $("#flowRely").val();
 				var processIdArr =[];
 				processIdArr = flowRelyStr.split("\"");
 				//alert(processIdArr);
 				for(var i=0;i<processIdArr.length;i++){
 					//alert(processIdArr[i]);
 					selObj.append("<option value='"+i+"'>"+processIdArr[i]+"</option>");
 				}
 				
 				
				
				
				
			}else if(selectText == "固定指标参数依赖"){
				$("#relyParamTr").show();
				var selOpt = $("#relyItemSelect option");
				selOpt.remove();
				//alert("固定");
				selObj.append("<option value='"+value+"'>"+text+"</option>");
				var fixParamStr = $("#fixParam").val();
				var fixParamArr =[];
				fixParamArr = fixParamStr.split("\"");
				//alert(fixParamArr);
				for(var i=0;i<fixParamArr.length;i+=2){
					selObj.append("<option value='"+i+"'>"+fixParamArr[i]+"</option>");
				}
			}
			
		}
		
		function paramConfig(){
			var selectText = $("#relyItemSelect").find("option:selected").text();
			if(/^\d+$/.test(selectText)){
				//alert("流程");
			}else{
				//alert("固定");
				var fixParamStr = $("#fixParam").val();
				var fixParamArr =[];
				fixParamArr = fixParamStr.split("\"");
				for(var i=0;i<fixParamArr.length;i+=2){
					if(selectText == fixParamArr[i]){
						$("#relyValue").val(fixParamArr[i+1])
					}
				}
				
			}
		}
		
		
		
	
		
		
	</script>
	  <%
  		String newStr =String.valueOf(request.getSession().getAttribute("newStr"));
  		System.out.println("newStr:"+newStr);
  		
  		String paramStr =String.valueOf(request.getSession().getAttribute("paramStr"));
  		System.out.println("paramStr:"+paramStr);
   %>
	
</head>
<body>
 
<param name="movie" value="<%=basePath%>/media/swf/bpm/lineshow.swf" />
<object type="application/x-shockwave-flash" data="<%=basePath%>/media/swf/bpm/lineshow.swf" width="100%" height="80%">

<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty paramRelyItem.id}">
			        <span class="tbar-label"><span></span>编辑参数依赖</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加参数依赖</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				流程定义id:<input type="text" id="processId" name="processId" value="<%=request.getParameter("processId") %>" readonly="readonly" />
			</div>
		</div>
	</div>
	<form id="paramRelyForm" method="post" action="save.ht">
		<input type="hidden" id="flowRely" value=<%=newStr%> />
		<input type="hidden" id="fixParam" value=<%=paramStr%> />
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>

  <tr>
   <td colspan="2" class="formHead">参数依赖</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">依赖方式:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput">
   <select id="paraTypeSelect" name="relyMethod" lablename="依赖方式" controltype="select" validate="{empty:false}" onchange="changeDefault()">
   <option value="0" >--------请选择-------</option>
   <option value="1"  <c:if test='${paramRely.relyMethod=="流程参数依赖"}'>selected='selected'</c:if>>流程参数依赖</option>
   <option value="2" <c:if test='${paramRely.relyMethod=="固定指标参数依赖"}'>selected='selected'</c:if>>固定指标参数依赖</option>
   </select></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">依赖选项:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput">
   <select id="relyItemSelect" name="relyKey" lablename="依赖Key" controltype="select" validate="{empty:false}" onchange="paramConfig()"><option></option>
   		<c:forEach var="historyData" items="${historyDataList.historyData}"> 
   			<option value="${historyData.processId}">${historyData.processId}</option>
  		</c:forEach>
   		<option value="0" >--------依赖项-------</option>
   </select></span></td>
  </tr>
  <tr id="relyParamTr">
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">依赖参数:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input id="relyValue" readonly="readonly" name="relyValue" lablename="依赖参数" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${paramRely.relyValue}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">脚本:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">
   <a href='#' name='btnScript' class='link var' title='常用脚本' onclick='selectScript(this)'>常用脚本</a><br>
   <textarea name="script" lablename="脚本" class="l-textarea" rows="5" cols="40" validate="{maxlength:2000}" isflag="tableflag">${paramRely.script}</textarea></span></td>
  </tr>
  </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${paramRely.id}"/>
	</form>
</body>
</html>
