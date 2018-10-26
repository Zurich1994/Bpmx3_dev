<%--
	time:2015-04-17 15:19:16
	desc:edit the 流程批量审批定义设置
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 流程批量审批定义设置</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/BpmDefinitionDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#bpmBatchApprovalForm").attr("action","save.ht");
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#bpmBatchApprovalForm').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				var fieldJson = $('#fieldJson').val();
				if($.isEmpty(fieldJson)){
					$.ligerDialog.alert("请设置字段!","提示信息");
					return ;
				}
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = "${ctx}/platform/bpm/bpmBatchApproval/list.ht";
						//window.location.href = window.location.href;
					}else{
						window.location.href = "${ctx}/platform/bpm/bpmBatchApproval/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		/**
		* 选择流程
		*/
		function selectFlow(){
			BpmDefinitionDialog({isSingle:true,returnDefKey:true,callback:function(defIds,subjects,defKeys){
				$("#defKey").val(defKeys);
				$("#subject").val(subjects);
				$('#nodeId').val("");
				$('#nodeName1').val("");
			}});
		}
		function selectNode(){
			var defKey = $("#defKey").val();
			if($.isEmpty(defKey)){
				$.ligerDialog.alert("请先选择流程定义！","提示信息");
				return;
			}
			var url=__ctx + "/platform/bpm/bpmDefinition/selectNodes.ht?defKey="+defKey;
		//	url=url.getNewUrl();
			DialogUtil.open({
				height:500,
				width: 650,
				title : '选择节点',
				url: url, 
				//自定义参数
				sucCall:function(rtn){
					$('#nodeId').val(rtn.nodeId);
					$('#nodeName1').val(rtn.nodeName);
				}
			});
		}
		
		//设置字段
		function setFields(){
			var defKey = $("#defKey").val(),nodeId=$('#nodeId').val(),fieldJson=$('#fieldJson').val();
			if($.isEmpty(defKey)){
				$.ligerDialog.alert("请先选择流程定义！","提示信息");
				return;
			}
			if($.isEmpty(nodeId)){
				$.ligerDialog.alert("请先选择节点！","提示信息");
				return;
			}
			var url=__ctx + "/platform/bpm/bpmBatchApproval/fieldDialog.ht?defKey="+defKey+"&nodeId="+nodeId+"&fieldJson="+fieldJson;
			//url=url.getNewUrl();
			DialogUtil.open({
				height:500,
				width: 650,
				title : '列表字段设置',
				url: url, 
				//自定义参数
				sucCall:function(rtn){
					$('#tableId').val(rtn.tableId);
					$('#fieldJson').val(rtn.fieldJson);
				}
			});
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${bpmBatchApproval.id !=null}">
			        <span class="tbar-label"><span></span>编辑流程批量审批定义设置</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加流程批量审批定义设置</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:void(0);"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="bpmBatchApprovalForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">流程定义: </th>
					<td>
					<input type="hidden" id="defKey" name="defKey" value="${bpmBatchApproval.defKey}"/>
					<input type="text" id="subject" name="subject" readonly="readonly" value="${bpmBatchApproval.subject}"  class="inputText"  style="width:210px;" validate="{required:true}"/>
					
					<a onclick="selectFlow()" class="button" href="javascript:void(0);"><span class="icon ok"></span><span>选择</span></a>
					<a onclick="cancelFlow()" class="button" href="javascript:;"><span class="icon cancel"></span><span>重置</span></a>
					</td>
				</tr>
				<tr>
					<th width="20%">节点名称: </th>
					<td>
						<input type="hidden" id="nodeId" name="nodeId" value="${bpmBatchApproval.nodeId}"/>
						<input type="text" id="nodeName1" name="nodeName1" readonly="readonly" value="${bpmBatchApproval.nodeName}"  class="inputText"  style="width:210px;"  validate="{required:true}"/>
						
						<a onclick="selectNode()" class="button" href="javascript:void(0);"><span class="icon ok"></span><span>选择</span></a>
						<a onclick="cancelNode()" class="button" href="javascript:;"><span class="icon cancel"></span><span>重置</span></a>
						
					</td>
				</tr>
				<tr>
					<th width="20%">列表字段设置: </th>
					<td>
						<textarea id="fieldJson" name="fieldJson"  rows="1" cols="1" style="display: none">${fn:escapeXml(bpmBatchApproval.fieldJson)}</textarea>
						<a onclick="setFields()" class="link setting" href="javascript:void(0);"><span>设置</span></a>
					
					</td>
				</tr>
			</table>
			<input type="hidden" id="tableId" name="tableId" value="${bpmBatchApproval.tableId}"/>
			<input type="hidden" id="id"  name="id" value="${bpmBatchApproval.id}" />
		</form>
		
	</div>
</div>
</body>
</html>
