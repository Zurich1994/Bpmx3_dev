<%--
	time:2012-01-05 12:01:21
	desc:edit the 其它参数
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>拓扑图其他参数设置</title>
	<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor_rule.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowVarWindow.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/FlexUploadDialog.js"></script>
	<script type="text/javascript">
	var defId=${bpmnetworkmap.defId };
	
	$(function(){
		editor=ckeditor('taskNameRule');
		handFlowVars();
		//状态改变
		handleStatusChange();
	});
	
	function handleStatusChange(){
		$("#status").change(function(){
			var v=$(this).val();
			if(v=="2" || v=="3"){
				$("#spanMessage").hide();
			}
			else{
				$("#spanMessage").show();
			}
			if(v=="4"){
				$("#testTag").show();
			}else{
				$("#testTag").hide();
			}
		});
	}
	
	function handFlowVars(){
		var objConditionCode=$("#taskNameRule");
		$("select[name='selFlowVar']").change(function(){		
			var val=$(this).val();
			var text=$(this).find("option:selected").text();
			if(val.length==0) return;
			if(text=="发起人(长整型)")
				text=text.replace("(长整型)","");			
			var inStr="{"+text+":"+val+"}";
			InsertText(inStr);
		});
	}
	
	function InsertText(val){
		// Get the editor instance that we want to interact with.
		var oEditor = CKEDITOR.instances.taskNameRule;
		// Check the active editing mode.
		if ( oEditor.mode == 'wysiwyg' ){
			oEditor.insertText( val );
		}else
			alert( '请把编辑器设置为编辑模式' );
	}
	
	function getCheckedValue(id){
		
		var checked=$(id).attr("checked");
		if(checked==undefined){
			return 0;
		}else if(checked){
			return 1;
		}else{
			return 0;
		}
	}
	function getMsgTypeList(id){
		var msgTypeList=[];
		$("input[name='"+id+"']").each(function(){
			var me = $(this),
				val = me.val(),
				state = me.attr("checked");
			if(state)
				msgTypeList.push(val);
		});
		return msgTypeList;
	}
	function saveParam(){
		var taskNameRule=CKEDITOR.instances["taskNameRule"].getData();
		var toFirstNode=getCheckedValue("#toFirstNode");
		var showFirstAssignee=getCheckedValue("#showFirstAssignee");
		var submitConfirm=getCheckedValue("#submitConfirm");
		var allowDivert=getCheckedValue("#allowDivert");
		var allowFinishedDivert=getCheckedValue("#allowFinishedDivert");
		var informStart = getMsgTypeList("informStart");
		var informType = getMsgTypeList("informType");
		var allowFinishedCc=getCheckedValue("#allowFinishedCc");
		var isPrintForm=getCheckedValue("#isPrintForm");
		var formDetailUrl=$('#formDetailUrl').val();
		var attachment=$('#attachment').val();
		var status=$('#status').val();
		var sameExecutorJump=getCheckedValue("#sameExecutorJump");
		var isUseOutForm=getCheckedValue("#isUseOutForm");
		var isUseOutForm=getCheckedValue("#isUseOutForm");
		var allowRefer=getCheckedValue("#allowRefer");
		var instanceAmount=$('#instanceAmount').val();
		var testStatusTag=$('#testStatusTag').val();
		
		var directstart=getCheckedValue("#directstart");
		var ccMessageType = $("input[name='ccMessageType']").val();
		
		var allowMobile=getCheckedValue("#allowMobile");
		
		
		var params={defId:defId,taskNameRule:taskNameRule,toFirstNode:toFirstNode,
				showFirstAssignee:showFirstAssignee,
				submitConfirm:submitConfirm,allowDivert:allowDivert,
				allowFinishedDivert:allowFinishedDivert,informStart:informStart.join(','),
				informType:informType.join(','),allowFinishedCc:allowFinishedCc,
				isPrintForm:isPrintForm,attachment:attachment,
				status:status,sameExecutorJump:sameExecutorJump,
				isUseOutForm:isUseOutForm,allowRefer:allowRefer,instanceAmount:instanceAmount,
				directstart:directstart,ccMessageType:ccMessageType,testStatusTag:testStatusTag,allowMobile:allowMobile};
		
		$.post("saveParam_b.ht",params,function(msg){
			var obj=new com.hotent.form.ResultMessage(msg);
			if(obj.isSuccess()){
				$.ligerDialog.success(obj.getMessage(),"操作成功");
			}else{
				$.ligerDialog.err('出错信息',"流程定义其他参数设置失败",obj.getMessage());
			}
		});
	}
	
	function openCcUserList(){
		var url=__ctx +'/platform/bpm/bpmDefinition/copyUserList.ht?defId=${bpmnetworkmap.defId}&parentActDefId=${parentActDefId}';
	 	var winArgs="height=450,width=750,status=no,toolbar=no,menubar=no,location=no,resizable=1,scrollbars=1";
		url=url.getNewUrl();
	 	window.open(url,"",winArgs);
	 	$("#allowFinishedCc").attr("checked","checked");
	};
	
	//添加附件
	function addFile(){
		FlexUploadDialog({isSingle:true,callback:fileCallback});
	};
	
	function fileCallback(fileIds,fileNames,filePaths){
		if(fileIds==undefined || fileIds=="") return ;
		var url=__ctx+"/platform/system/sysFile/file_"+fileIds +".ht";
		$("#attachment").val(fileIds);
		if($("#file").length>0){
			$("#file").attr("href",url).text(fileNames);
		}else{
			var node='<a href="'+url+'" target="_blank" id="file">'+fileNames+' </a><a  class="link del" onclick="del(this)" style="cursor:pointer;"> 删除</a>';
			$("#attachment").after(node);
		}
	}
	function del(obj){
		$("#attachment").val("");
		$("#file").remove();
		$(obj).remove();
	}
	var referDef;
	function referDefinition(){
		var url=__ctx +'/platform/bpm/bpmDefinition/defReferSelector.ht?defId=${bpmnetworkmap.defId}';
		referDef = $.ligerDialog.open({
			title:'流程引用',
			mask:true,
			isResize:true,
			height: 500,
			url:url,
			width:700,
			buttons:[
			         { text: '确定', onclick: function (item, dialog) {
			        	 var contents=$("iframe",dialog.dialog).contents()
			        	 var chKeys=contents.find("input.pk[name=defKey]:checked");
			        	 var defId=contents.find("#bpmDefId").val();
			        	 var aryDefKey = [];
			        	 
				     		$.each(chKeys,function(i,ch){
				     			aryDefKey.push($(ch).val());
				     		});
				     		var params={defId:defId,refers:aryDefKey.join(",")};
				     		if(aryDefKey.length > 0){
				    			$.post("saveReferDef.ht",params,function(map){
				    				var spanHtml='';
				    				for (var key in map) {  
			    						spanHtml=spanHtml+"<span id='ref_"+key+"'>"+map[key]+"<a href='javascript:void(0);' onclick='delRefer("+key+")'>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>"
				    				} 
				    				$("span#refDefArray").html(spanHtml);
				    				$.ligerDialog.success('流程引用成功！','提示'); 
				    			});
				    		}else{
				    			$.ligerDialog.warn('请选择引用流程!','提示');
				    		}
							dialog.close();
					}
			         },
					{ text: '取消', onclick: function (item, dialog) { dialog.close(); } }
					]});
	};
	
	function clickAllowRefer(obj){
		var $obj=$(obj);
		if($obj.attr("checked")){
			$("#spanInstanceAmount").show();	
		}
		else{
			$("#spanInstanceAmount").hide();
		}
		
	}
	
	function delRefer(refId){
		$.post("delReferDef.ht",{refId:refId},function(msg){
			var obj=new com.hotent.form.ResultMessage(msg);
			if(obj.isSuccess()){
				$.ligerDialog.success(obj.getMessage(),'提示');
				$('#ref_'+refId).remove();
			}else{
				$.ligerDialog.error(obj.getMessage(),'提示');
			}
		});
	}
	
	</script>
</head>
<body> 

            <jsp:include page="incDefinitionHead.jsp">
		   		<jsp:param value="其他参数" name="title"/>
			</jsp:include>
			 <div class="panel-container">
            <f:tab curTab="otherParam" tabName="flow1"/>
            <div class="panel-top">
	            <div class="tbar-title">
					<span class="tbar-label">流程定义其他参数设置</span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link save" onclick="saveParam()"><span></span>保存</a></div>
					</div>	
				</div>

			</div>
            <div class="panel-detail">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="15%">流程标题规则定义</th>
					<td>
						表单变量:<f:flowVar defId="${defId}" controlName="selFlowVar"></f:flowVar>
						<textarea id="taskNameRule" row="6" name="taskNameRule" >${bpmnetworkmap.taskNameRule }</textarea>
					</td>	
				</tr>
				
				<tr>
					<th width="15%">跳过第一个任务:</th>
					<td>
						<input id="toFirstNode" type="checkbox"  name="toFirstNode" value="0"  <c:if test="${bpmnetworkmap.toFirstNode==1 }">checked="checked"</c:if> />
						<div class="tipbox"><a href="javascript:;" class="tipinfo"><span>流程启动后直接完成第一个节点的任务。</span></a></div>
					</td>	
				</tr>
				<c:if test="${!isStartMultipleNode}">
				<tr>
					<th width="15%">直接启动流程:</th>
					<td>
						<input id="directstart" type="checkbox" name="directstart" value="0"  <c:if test="${bpmnetworkmap.directstart==1 }">checked="checked"</c:if> />
						<div class="tipbox"><a href="javascript:;" class="tipinfo"><span>不使用表单直接启动流程，启动流程时不传入主键。</span></a></div>
					</td>	
				</tr>
				</c:if>
				<tr>
					<th width="15%">流程启动选择执行人:</th>
					<td>
						<input id="showFirstAssignee" type="checkbox" name="showFirstAssignee" value="1"  <c:if test="${bpmnetworkmap.showFirstAssignee==1 }">checked="checked"</c:if> />
						<div class="tipbox"><a href="javascript:;" class="tipinfo"><span>如果勾选，那么流程启动时可以改变下一步的执行人，默认不可以。</span></a></div>
					</td>	
				</tr>
				<tr>
					<th width="15%">允许API调用:</th>
					<td>
						<input id="isUseOutForm" type="checkbox" name="isUseOutForm" value="1"  <c:if test="${bpmnetworkmap.isUseOutForm==1 }">checked="checked"</c:if> />
						
						<div class="tipbox"><a href="javascript:;" class="tipinfo"><span>如果勾选，那么流程执行时会转向设置的url业务表单上去。</span></a></div>
					</td>	
				</tr>
				
				<tr>
					<th width="20%">提交是否需要确认:</th>
					<td>
						<input id="submitConfirm" type="checkbox" name="submitConfirm" value="${bpmnetworkmap.submitConfirm}" <c:if test="${bpmnetworkmap.submitConfirm==1 }">checked="checked"</c:if>  />
						<div class="tipbox"><a href="javascript:;" class="tipinfo"><span>如果勾选,在每一次提交提示确认对话框，默认关闭。</span></a></div>
					</td>	
				</tr>
				
				<tr>
					<th width="20%">是否允许转办:</th>
					<td>
						<input id="allowDivert" type="checkbox" name="allowDivert" value="${bpmnetworkmap.allowDivert}" <c:if test="${bpmnetworkmap.allowDivert==1 }">checked="checked"</c:if> />
						<div class="tipbox"><a href="javascript:;" class="tipinfo"><span>如果勾选,则允许转办，默认不允许转办。</span></a></div>
					</td>	
				</tr>
				<tr>
					<th width="20%">是否允许我的办结转发:</th>
					<td>
						<input id="allowFinishedDivert" type="checkbox" name="allowFinishedDivert"   <c:if test="${bpmnetworkmap.allowFinishedDivert==1 }">checked="checked"</c:if>  />
						<div class="tipbox"><a href="javascript:;" class="tipinfo"><span>如果勾选，则允许转发，默认不允许。</span></a></div>
					</td>	
				</tr>
				<tr>
					<th width="20%">归档时发送消息给发起人:</th>
					<td>
							<label><input type="checkbox" name="informStart" value="3" <c:if test="${fn:contains(bpmnetworkmap.informStart,3)}">checked="checked"</c:if>/>站内消息</label>
							<label><input type="checkbox" name="informStart" value="1" <c:if test="${fn:contains(bpmnetworkmap.informStart,1)}">checked="checked"</c:if>/>邮件</label>
							<label><input type="checkbox" name="informStart" value="2" <c:if test="${fn:contains(bpmnetworkmap.informStart,2)}">checked="checked"</c:if>/>手机短信</label>
					</td>	
				</tr>
			
				<tr>
					<th width="20%">是否允许办结抄送:</th>
					<td>
						<input id="allowFinishedCc" type="checkbox" name="allowFinishedCc" value="${bpmnetworkmap.allowFinishedCc}"  <c:if test="${bpmnetworkmap.allowFinishedCc==1 }">checked="checked"</c:if>  />
						<!-- 抄送消息提醒方式 -->
						<input class="send_type" name="ccMessageType" type="hidden" value='${bpmnetworkmap.ccMessageType}' />
						<a href="###" onclick="openCcUserList()">抄送人员设置</a>
						<div class="tipbox"><a class="tipinfo"><span>如果勾选，则允许在流程结束时抄送，默认不抄送。</span></a></div>
					</td>
				</tr>
				<tr>
					<th width="20%">流程实例归档后是否允许打印表单:</th>
					<td>
						<input id="isPrintForm" type="checkbox" name="isPrintForm" value="${bpmnetworkmap.isPrintForm}"   <c:if test="${bpmnetworkmap.isPrintForm==1 }">checked="checked"</c:if> />
						<div class="tipbox"><a href="javascript:;" class="tipinfo"><span>如果勾选，则流程实例归档后将提供打印表单功能。</span></a></div>
					</td>	
				</tr>
				
				<tr>
					<th width="20%">流程帮助:</th>
					<td>
						<input id="attachment" type="hidden" name="attachment" value="${bpmnetworkmap.attachment}"  />
						
						<c:if test="${sysFile !=null }">
							<a href="${ctx}/platform/system/sysFile/file_${sysFile.fileId}.ht" target="_blank" id="file">${sysFile.fileName }</a><a  class="link del" onclick="del(this)" style="cursor:pointer;">删除</span>
						</c:if>
						<a href="javascript:void(0);" class="link selectFile"  onclick="addFile()">添加附件</a>
						<div class="tipbox"><a href="javascript:;" class="tipinfo"><span>流程帮助附件</span></a></div>
					</td>	
				</tr>
				<tr>
					<th width="20%">引用的流程:</th>
					<td>
						<span id="refDefArray">
							<c:forEach items="${referList}" var="refer">
								<span id="ref_${refer.id}"><c:out value="${refer.subject}"></c:out><a href="javascript:void(0);" onclick="delRefer(${refer.id})">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</c:forEach>
						</span><span id="refDefs"></span>
						<a class="link search" href="javascript:void(0);" onclick="referDefinition()">引用</a>
						<div class="tipbox"><a href="javascript:;" class="tipinfo"><span>维护该流程可以进行引用的流程。</span></a></div>
					</td>	
				</tr>
				<tr>
					<th width="20%">流程参考:</th>
					<td>
						<input type="checkbox" value="${bpmnetworkmap.allowRefer}" name="allowRefer" id="allowRefer" onclick="clickAllowRefer(this)"  <c:if test="${bpmnetworkmap.allowRefer==1 }">checked="checked"</c:if> >允许参考
						<span id="spanInstanceAmount" <c:if test="${bpmnetworkmap.allowRefer==0 }">style="display:none;"</c:if> >
							参考条数:<input type="text" value="${bpmnetworkmap.instanceAmount }" name="instanceAmount" id="instanceAmount" style="width:30px;" >
						</span>
					</td>	
				</tr>
				
				<tr>
					<th width="20%">状态:</th>
					<td>
						<select id="status" name="status">
							<option value="1" <c:if test="${bpmnetworkmap.status==1}">selected='selected'</c:if>>启用</option>
							<option value="2" <c:if test="${bpmnetworkmap.status==2}">selected='selected' </c:if>>禁用</option>
							<option value="3" <c:if test="${bpmnetworkmap.status==3}">selected='selected' </c:if>>禁用(实例)</option>
							<option value="4" <c:if test="${bpmnetworkmap.status==4}">selected='selected' </c:if>>测试</option>
						</select>
						<span id="spanMessage" <c:if test="${bpmnetworkmap.status==2||bpmnetworkmap.status==3}">style="display:none;"</c:if>>
							<label><input type="checkbox" name="informType" value="3" <c:if test="${fn:contains(bpmnetworkmap.informType,3)}">checked="checked"</c:if>/>站内消息</label>
							<label><input type="checkbox" name="informType" value="1" <c:if test="${fn:contains(bpmnetworkmap.informType,1)}">checked="checked"</c:if>/>邮件</label>
							<label><input type="checkbox" name="informType" value="2" <c:if test="${fn:contains(bpmnetworkmap.informType,2)}">checked="checked"</c:if>/>手机短信</label>
						</span>
						<span id="testTag"<c:if test="${bpmnetworkmap.status!=4}">style="display:none;"</c:if>>
							&nbsp;&nbsp;测试标签：<input type="text" id="testStatusTag" name="testStatusTag" value="${bpmnetworkmap.testStatusTag}" >
						</span>
					</td>	
				</tr>
				<tr>
					<th width="20%">相邻任务节点人员相同时自动跳过:</th>
					<td>
						<input type="checkbox" name="sameExecutorJump" id="sameExecutorJump" value="${bpmnetworkmap.sameExecutorJump}"  <c:if test="${bpmnetworkmap.sameExecutorJump==1 }">checked="checked"</c:if> />
						<div class="tipbox"><a href="javascript:;" class="tipinfo"><span>如果相邻的任务节点执行人相同时,直接跳过。</span></a></div>
					</td>	
				</tr>
				<tr>
					<th width="20%">是否手机审批:</th>
					<td>
					 	<label><input id="allowMobile" type="checkbox" name="allowMobile" value="1" <c:if test="${bpmnetworkmap.allowMobile==1}">checked="checked"</c:if> /></label>
					</td>	
				</tr>
			</table>
			</div>
			</div>
			<input type="hidden" id="defId" name="defId" value="${bpmnetworkmap.defId }">
</body>
</html>
