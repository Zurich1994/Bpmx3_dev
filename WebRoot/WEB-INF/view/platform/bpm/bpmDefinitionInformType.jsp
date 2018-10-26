<%
	//某个任务节点的手机访问的设置
%>
<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
    <title>通知方式配置</title>
    <%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript">
		/*KILLDIALOG*/
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
		$(function(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			$('#informTypeForm').ajaxForm(options);
			
			$(".save").click(function(){
				$('#informTypes').val(getMsgTypeList('informType'));
				$('#informTypeForm').submit();
			});
		});
		
		function getMsgTypeList(id){
			var msgTypeList=[];
			$("input[name='"+id+"']").each(function(){
				var me = $(this),
					val = me.val(),
					state = me.attr("checked");
				if(state)
					msgTypeList.push(val);
			});
			return msgTypeList.join(',');
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success(obj.getMessage(),"提示信息", function(rtn) {
					if(rtn){
						dialog.close();
					}
				});
			} else {
				$.ligerDialog.err("出错信息","编辑手机访问设置失败",obj.getMessage());
			}
		}
	</script>
</head>
<body>
<div class="panel-top">
	<div class="tbar-title">
		<span class="tbar-label">
			设置流程任务节点通知方式
	    </span>
	</div>
	<div class="panel-toolbar">
		<div class="toolBar">
			<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
			<div class="l-bar-separator"></div>
			<div class="group"><a class="link del" href="javascript:;" onclick="dialog.close()"><span></span>关闭</a></div>
		</div>
	</div>
</div>
<form id="informTypeForm" method="post" action="saveInformType.ht">
	<div style="padding:8px 8px 8px 8px">
		<b>说明：</b>
		<ul>
			<li>该节点产生任务将下面选择方式发送通知给用户。</li>
		</ul>
	</div>
	<div style="padding:8px 8px 8px 8px" id="nodeTypeDiv">
		<label><b>通知方式 ${bpmNodeSet.nodeName} : </b></label> 
		<c:forEach items="${handlersMap}" var="item">
		<input type="checkbox" name="informType" value="${item.key }"  <c:if test="${fn:contains(bpmNodeSet.informType,item.key)}">checked="checked"</c:if> />
          ${item.value.title }
		</c:forEach>
		
		<br/><label><b>是否发信息给发起人 : </b></label> 
		<input type="checkbox" name="sendMsgToCreator" <c:if test="${bpmNodeSet.sendMsgToCreator==1}">checked="checked"</c:if>/>
		
		<input type="hidden" name="actDefId" value="${bpmNodeSet.actDefId}" />
		<input type="hidden" name="nodeId" value="${bpmNodeSet.nodeId}" />
		<input type="hidden" name="parentActDefId" value="${parentActDefId}" />
		<input type="hidden" id="informTypes" name="informTypes" value=""/>
	</div>
</form>
</body>
</html>