<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>流程事件脚本编辑</title>
<%@include file="/commons/include/form.jsp" %>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmNodeScript"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerTab.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowVarWindow.js" ></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

	var defId="${defId}";
	function showRequest(formData, jqForm, options) {
		return true;
	}
	$(function() {
        //Tab
		valid(showRequest,function(){});
           $("#tabScript").ligerTab({height:300});
           $("#btnSearch").click(save);
	});
	
	function handFlowVars(obj,txtId){
		var val=$(obj).val();
		InitMirror.editor.insertCode(val);
	}
	
	function slectVars(txtId){
		FlowVarWindow({defId:defId,callback:function(varKey,varName){
			InitMirror.editor.insertCode(varKey);
		}});
		
	}
	
	function slectScript(txtId){
		ScriptDialog({callback:function(script){
			InitMirror.editor.insertCode(script);
		}});
	}
	
	function save() {		
		InitMirror.save();
		var rtn = $("#bpmNodeScriptForm").valid();
		if (!rtn)
			return;

		var url = __ctx + "/platform/bpm/bpmNodeScript/save.ht";
		var para = $('#bpmNodeScriptForm').serialize();

		$.post(url, para, showResult);
	}

	function showResult(responseText) {
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (!obj.isSuccess()) {
			$.ligerDialog.err('出错信息',"流程事件脚本编辑失败",obj.getMessage());
			return;
		} else {
			$.ligerDialog.success('流程事件脚本编辑成功!','提示信息',function() {
				dialog.close();
			});
		}
	}
</script>
	<style type="text/css">
	html { overflow-y: hidden; }
	</style>
</head>

<body>

<c:set var="preModel" value="${map.type1}"></c:set>
<c:set var="afterModel" value="${map.type2}"></c:set>
<c:set var="assignModel" value="${map.type4}"></c:set>
<c:set var="scrptModel" value="${map.type3}"></c:set>
<c:set var="signScriptModel" value="${map.type5}"></c:set>

<c:set var="preBeforeModel" value="${map.type5}"></c:set>
<c:set var="afterBeforeModel" value="${map.type6}"></c:set>

<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">
				  	流程事件脚本编辑
				</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="btnSearch"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  onclick="dialog.close()"><span></span>关闭</a></div>
				
				</div>	
			</div>
		</div>
		<div class="panel-body">
				
				<form id="bpmNodeScriptForm" method="post" action="save.ht">
					<div id="tabScript" >
						<c:if test="${type eq 'startEvent' || type eq 'subProcess' || type eq 'subStartEvent' }">
							<div tabid="startEvent" title="开始事件">
								<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
									<tr>
										<th width="20%">说明:<span class="required">*</span> </th>
										<td>
											该脚本在流程启动时执行，用户可以使用<span class="red">execution</span>做操作。
											例如设置流程变量:execution.setVariable("total", 100);
											<input type="hidden"  name="scriptType" value="1"  class="inputText"/>
											
										</td>
									</tr>
									<tr>
										<th width="20%" >脚本:<span class="required">*</span> </th>
										<td>
											<div>
												<a href="javascript:;"  class="link var" title="常用脚本" onclick="slectScript('startScript')">常用脚本</a>
												表单变量:<f:flowVar defId="${defId}" change="handFlowVars(this,'startScript')" controlName="selFlowVar" parentActDefId="${parentActDefId}"></f:flowVar>
											</div>
											<textarea id="startScript" name="script" codemirror="true" mirrorheight="200px" rows="10" cols="80" >${preModel.script}</textarea>
										</td>
									</tr>
								</table>
							</div>
						</c:if>
						<c:if test="${type eq 'endEvent' || type eq 'subProcess'}">
							<div tabid="endEvent" title="结束事件">
								<table class="table-detail" cellpadding="0" cellspacing="0" border="0"  >
									<tr>
										<th width="20%">脚本描述:<span class="required">*</span> </th>
										<td>
											该脚本在<span class="red">流程结束</span>时执行，用户可以使用<span class="red">execution</span>做操作。
											例如设置流程变量:execution.setVariable("total", 100);
											<input type="hidden"   name="scriptType" value="2"  />
										</td>
									</tr>
									<tr>
										<th width="20%" >脚本:<span class="required">*</span> </th>
										<td>
											<div>
												<a href="javascript:;"  class="link var" title="常用脚本" onclick="slectScript('endScript')">常用脚本</a>
												表单变量:<f:flowVar defId="${defId}" change="handFlowVars(this,'endScript')" controlName="selFlowVar" parentActDefId="${parentActDefId}"></f:flowVar>
											</div>
											<textarea id="endScript" name="script" codemirror="true" mirrorheight="200px" rows="10" cols="80"  >${afterModel.script}</textarea>
										</td>
									</tr>
								</table>
							</div>
						</c:if>
						<c:if test="${type eq 'script' }">
							<div tabid="ScriptNode" title="脚本代码">
								<table class="table-detail" cellpadding="0" cellspacing="0" border="0" style='${type eq "script"?'display:':'display:none'}'>
										<tr>
											<th width="20%">脚本描述:<span class="required">*</span> </th>
											<td>
												这个在脚本任务触发时执行，用户可以使用<span class="red">execution</span>做操作。
														例如设置流程变量:execution.setVariable("total", 100);
												<input type="hidden"  name="scriptType" value="3"  />
											</td>
										</tr>
										<tr>
											<th width="20%" >脚本节点:<span class="required">*</span> </th>
											<td>
												<div>
													<a href="javascript:;"  class="link var" title="常用脚本" onclick="slectScript('scriptScript')">常用脚本</a>
													表单变量:<f:flowVar defId="${defId}" change="handFlowVars(this,'scriptScript')" controlName="selFlowVar" parentActDefId="${parentActDefId}"></f:flowVar>
												</div>
												<textarea id="scriptScript" name="script" codemirror="true" mirrorheight="200px" rows="10" cols="80" >${scrptModel.script}</textarea>
											</td>
										</tr>
								</table>
							</div>
						</c:if>
						<c:if test="${type=='multiUserTask'||type=='userTask' }">
						   
							  <div tabid="startBeforeScript" title="任务创建前置脚本">
							  
							     <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
											<tr>
												<th width="20%">脚本描述:<span class="required">*</span> </th>
												<td>
													该事件在<span class="red">启动该任务</span>时执行，用户可以使用<span class="red">task</span>做操作。
													例如设置流程变量:task.setVariable("total", 100);
													<input type="hidden"  name="scriptType" value="5"  />
												
												</td>
											</tr>
											<tr>
												<th width="20%" >脚本:<span class="required">*</span> </th>
												<td>
													<div>
														<a href="javascript:;"  class="link var" title="常用脚本" onclick="slectScript('preScript')">常用脚本</a>
														表单变量:<f:flowVar defId="${defId}" change="handFlowVars(this,'preScript')" controlName="selFlowVar" parentActDefId="${parentActDefId}"></f:flowVar>
													</div>
													<textarea id="preBeforeScript" name="script" codemirror="true" mirrorheight="200px" rows="10" cols="80"  >${preBeforeModel.script}</textarea>
												</td>
											</tr>
							    </table>
				              </div>
				              <div tabid="startScript" title="任务创建后置脚本">
							  
							     <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
											<tr>
												<th width="20%">脚本描述:<span class="required">*</span> </th>
												<td>
													该事件在<span class="red">启动该任务</span>时执行，用户可以使用<span class="red">task</span>做操作。
													例如设置流程变量:task.setVariable("total", 100);
													<input type="hidden"  name="scriptType" value="1"  />
												
												</td>
											</tr>
											<tr>
												<th width="20%" >脚本:<span class="required">*</span> </th>
												<td>
													<div>
														<a href="javascript:;"  class="link var" title="常用脚本" onclick="slectScript('preScript')">常用脚本</a>
														表单变量:<f:flowVar defId="${defId}" change="handFlowVars(this,'preScript')" controlName="selFlowVar" parentActDefId="${parentActDefId}"></f:flowVar>
													</div>
													<textarea id="preScript" name="script" codemirror="true" mirrorheight="200px" rows="10" cols="80"  >${preModel.script}</textarea>
												</td>
											</tr>
							    </table>
				              </div>
				              <div tabid="endBeforeScript" title="任务结束前置脚本" >
							     <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
											<tr>
												<th width="20%">脚本描述:<span class="required">*</span> </th>
												<td>
													该事件在<span class="red">任务完成</span>时执行，用户可以使用<span class="red">task</span>做操作。
													例如设置流程变量:task.setVariable("total", 100);
													<input type="hidden"   name="scriptType" value="6" />
												
												</td>
											</tr>
											<tr>
												<th width="20%">脚本:<span class="required">*</span>  </th>
												<td>
													<div>
														<a href="javascript:;"  class="link var" title="常用脚本" onclick="slectScript('afterScript')">常用脚本</a>
														表单变量:<f:flowVar defId="${defId}" change="handFlowVars(this,'afterScript')" controlName="selFlowVar" parentActDefId="${parentActDefId}"></f:flowVar>
													</div>
													<textarea id="afterBeforeScript" name="script" codemirror="true" mirrorheight="200px" rows="10" cols="80" >${afterBeforeModel.script}</textarea>
												</td>
											</tr>
							    </table>
				              </div>
				              <div tabid="endScript" title="任务结束后置脚本" >
							     <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
											<tr>
												<th width="20%">脚本描述:<span class="required">*</span> </th>
												<td>
													该事件在<span class="red">任务完成</span>时执行，用户可以使用<span class="red">task</span>做操作。
													例如设置流程变量:task.setVariable("total", 100);
													<input type="hidden"   name="scriptType" value="2" />
												
												</td>
											</tr>
											<tr>
												<th width="20%">脚本:<span class="required">*</span>  </th>
												<td>
													<div>
														<a href="javascript:;"  class="link var" title="常用脚本" onclick="slectScript('afterScript')">常用脚本</a>
														表单变量:<f:flowVar defId="${defId}" change="handFlowVars(this,'afterScript')" controlName="selFlowVar" parentActDefId="${parentActDefId}"></f:flowVar>
													</div>
													<textarea id="afterScript" name="script" codemirror="true" mirrorheight="200px" rows="10" cols="80" >${afterModel.script}</textarea>
												</td>
											</tr>
							    </table>
				              </div>
						 </c:if>
						 <c:if test="${type eq 'signExecute'}">
							<!-- 会签执行事件脚本 -->
							<div id="signExecuteScript" title="会签完成事件脚本">
								<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
									<tr>
										<th width="20%">说明:<span class="required">*</span> </th>
										<td>
											该脚本在判断会签是否结束时执行，用户可以使用<span class="red">execution</span>做操作。
											例如设置流程变量:execution.setVariable("total", 100);
											也可以使用<span class="red">isCompleted</span>判断会签是否结束;<br/>
											<pre>例如：if(isCompleted){</pre>
											<pre>	execution.setVariable("total", 100);</pre>
											<pre>}
											</pre>
											<input type="hidden"  name="scriptType" value="5"  class="inputText"/>
										</td>
									</tr>
									<tr>
										<th width="20%" >脚本:<span class="required">*</span> </th>
										<td>
											<div>
												<a href="javascript:;"  class="link var" title="常用脚本" onclick="slectScript()">常用脚本</a>
												表单变量:<f:flowVar defId="${defId}" change="handFlowVars(this)" controlName="selFlowVar" parentActDefId="${parentActDefId}"></f:flowVar>
											</div>
											<textarea id="signExecuteScript" name="script" codemirror="true" mirrorheight="200px" rows="10" cols="80" >${signScriptModel.script}</textarea>
										</td>
									</tr>
								</table>
							</div>
						</c:if>
						<input type="hidden" id="nodeId" name="nodeId" value="${nodeId}"  class="inputText"/>
						<input type="hidden" id="actDefId" name="actDefId" value="${actDefId}"  class="inputText"/>
						</div>
				</form>
				
				</div>
</div>
</body>
</html>
