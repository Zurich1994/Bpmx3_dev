<%--
	time:2012-07-25 18:26:13
	desc:edit the 自定义工具条
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 自定义工具条</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmNodeButton"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmNodeButton.js"></script>
	<!--zl引入 FormTableDialog.js -->
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormTableDialog.js"></script>
	<script type="text/javascript"><!--
		var isStartForm=${bpmNodeButton.isstartform};
		var isSign=${bpmNodeButton.nodetype};
		var buttonStr = ${buttonStr};
		var bpmButtonList = eval(buttonStr);
		
		$(function() {
			function showRequest(formData, jqForm, options) { 
				var operatortype=$("#operatortype").val();
				var btnprobability=$("#btnprobability").val();
				if(btnprobability==""){
					$.ligerDialog.warn("请输入发生概率",'提示信息');
					return false;
				}
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#bpmNodeButtonForm').submit(); 
			});
			$("a.back").click(function(){
				var nurl =__ctx + "/platform/bpm/bpmNodeButton/getByNode.ht?defId=${defId}&nodeId=${nodeId}&buttonFlag=${buttonFlag}";
				$.gotoDialogPage(nurl);
			})
			
		});
	
		
		function showResponse(data){
			var obj=new com.hotent.form.ResultMessage(data);
			if(obj.isSuccess()){//成功
				$.ligerDialog.confirm('操作成功,继续操作吗?','提示信息',function(rtn){
					if(rtn){
						location.reload();
					}else{
						var nurl =__ctx + "/platform/bpm/bpmNodeButton/getByNode.ht?defId=${defId}&nodeId=${nodeId}&buttonFlag=${buttonFlag}";
						$.gotoDialogPage(nurl);
					}
				});
		    }else{//失败
		    	$.ligerDialog.err('出错信息',"保存按钮失败",obj.getMessage());
		    }
		};
		
		//zl在线流程设计跳转
       function design(formData, jqForm, options) { 
			//var operatortype=$("#operatortype").val();
				/*if(operatortype=="0"){
					$.ligerDialog.warn("请选择操作类型",'提示信息');
					return false;
				}*/
				var nurl =__ctx + "/platform/bpm/bpmDefinition/designBtn.ht?defId=${defbId}";
						window.open(nurl);
			};
	   //zl限制输入小数
	   function checknum(obj)
		{   
			var reg = /^(0|0\.\d*|1(\.0*))$/;
		if(!reg.test(obj.value)){
    		  obj.value = "";
   			 }
	    };
  

	</script>
</head>
<body>
  <c:if test="${buttonFlag}"> 
	<jsp:include page="incDefinitionHead_new.jsp">
		<jsp:param value="节点操作按钮" name="title"/>
	</jsp:include>
	<f:tab curTab="button" tabName="flow"/>
</c:if>
<div class="panel">
		<div class="panel-top">
			
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					
					<!--zl添加按钮，进入在线流程设计 -->
					<div class="group"><a class="link flowDesign" onclick=design()><span></span>绘图</a></div>	
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="javascript:;"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="bpmNodeButtonForm" method="post" action="save.ht">
					
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						   <tr id="table_tr"></tr>
							<tr>
								<th width="20%">按钮名称: </th>
								<td><input type="text" id="btnname"  name="btnname" value="${bpmNodeButton.btnname}"  class="inputText"/></td>
							</tr>
							
							<tr>
								<th width="20%">发生概率: </th>
								<td><input type="text" id="btnprobability"  name="btnprobability" value="${probability}"  class="inputText" onBlur="checknum(this)" style="ime-mode:disabled" />(以小数表示)</td>
							</tr>
							
						</table>
						<input type="hidden" id="returnUrl" value="getByNode.ht?defId=${bpmNodeButton.defId}&nodeId=${bpmNodeButton.nodeid}" />
						<input type="hidden" name="actdefid" value="${bpmNodeButton.actdefid}" />
						<input type="hidden" name="nodeid" value="${bpmNodeButton.nodeid}" />
						<input type="hidden" name="defId" value="${bpmNodeButton.defId}" />
						<input type="hidden" name="nodetype" value="${bpmNodeButton.nodetype}" />
						<input type="hidden" name="isstartform" value="${bpmNodeButton.isstartform}" />
						<input type="hidden" name="sn" value="${bpmNodeButton.sn}" />
						<input type="hidden" name="id" value="${bpmNodeButton.id}" />
						<input type="hidden" name="btnprobability" value="${btnprobability}" />
					
				</form>
		</div>
</div>
</body>
</html>
