
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,com.hotent.core.util.AppUtil,com.hotent.platform.model.bpm.BpmDefinition"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title></title>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@include file="/commons/include/form.jsp"%>
		<link rel="stylesheet" type="text/css" href="../input.css">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/default/css/form.css">
		<script type="text/javascript"
			src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
		<script type="text/javascript" src="${ctx}/js/hotent/ajaxgrid.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/system/BpmDefinitionDialog_new.js"></script>

		<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
		<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
		<style>
.new_design,.had_design,.last_design {
	background: #C8C8C8 repeat-x;
	padding-top: 3px;
	border-top: 1px solid #708090;
	border-right: 1px solid #708090;
	border-bottom: 1px solid #708090;
	border-left: 1px solid #708090;
	width: 180px;
	height: auto;
	font-size: 10pt;
	cursor: hand;
}

.ok_btn,.cancle_btn {
	background: #7D7D7D repeat-x;
	width: 280px;
	height: 250px;
}

.free_design {
	background: #FF7575;
	padding-top: 3px;
	border-top: 1px solid #708090;
	border-right: 1px solid #708090;
	border-bottom: 1px solid #708090;
	border-left: 1px solid #708090;
	width: 180px;
	height: auto;
	font-size: 10pt;
	cursor: hand;
}
</style>
		<script type="text/javascript">
var defKey = 0;
var defId  = "0";
var subject = "0";
var dialog = frameElement.dialog;
function design() { 
	var nurl =__ctx + "/platform/bpm/bpmDefinition/designBtn.ht";
    window.open(nurl);
}

function ok_fun(){
   window.close();
}
</script>
		<script type="text/javascript"><!--
	 /**
		* 选择流程
		*/		
		function selectFlow(){
			BpmDefinitionDialog({isSingle:true,showAll:1,validStatus:2,callback:dlgCallBack,returnDefKey:true});
			
		};
		
		/**
		* 选择流程的回调处理
		*/
		function dlgCallBack(defIds,subjects,defKeys){
			if(subjects==null || subjects =="") return;
			$("#flowid").val(defIds);
			
			$("#flowname").val(subjects);
			$("#flowkey").val(defKeys);
		};		
		/**
		* 部分代理 添加流程
		*/
		function addFlow(){
			BpmDefinitionDialog({isSingle:true,showAll:1,returnDefKey:true,validStatus:2,callback:function(defIds,subjects,defKeys){
				if(!subjects) return ;
				$('#firstRow').remove();
				var newSubjects=subjects.split(",");
				var newDefKeys=defKeys.split(",");
				var newDefIds=defIds.split(",");
				
				for(var i=0,len=newDefKeys.length;i<len;i++){
					 defKey=newDefKeys[i];
					 defId=newDefIds[i];
				     subject=newSubjects[i];
					
					var row=$("#def_" + defKey);
					if(row.length>0) continue;
					var tr=getRow(defId,defKey,subject);
					$("#bpmAgentItem").empty();
					$("#bpmAgentItem").append(tr);
				}
			}});
		}		
		/**
		* 部分代理 构造一行流程(用于添加到表中)
		*/
		function getRow(defId,defKey,subject){
			var template=$("#tableRowTemplate").val();
			return template.replaceAll("#defId",defId).replaceAll("#defKey",defKey).replaceAll("#subject",subject);
		}
		
		/**
		* 删除一行
		*/
		function singleDel(obj){
			var tr=$(obj).closest('tr');
			$(tr).remove();
		};
		
		function bangSave(){
		    var gai = $("#mes").val();
		    alert("gai:"+gai);
			var url =__ctx + "/platform/bpm/bpmDefinition/sherlock.ht?";			  
					$.post(url,{defId:defId,formDefId:${formDefId},bangId:${bangId},gai:gai},function(){
				});
		};
		
		function disbang(){
			var url =__ctx + "/platform/bpm/bpmDefinition/disbang.ht?";			  
					$.post(url,{bangId:${bangId}},function(){
					alert("已解綁。");
					window.location.reload();
				});
				
		}
 --></script>
	</head>
	<body>
	<table class="table-detail" cellpadding="0" cellspacing="0"
						border="0">
<tr>	<th width="100%" align="center"><center><h2>${formsubject}:绑定流程<h2></center></th></tr>
</table>
		<div id="inputPanel">
			<form id="agentSettingForm" method="post" action="save.ht">
				<label for="mes"></label>
				<div class="panel-body">
					<table class="table-detail" cellpadding="0" cellspacing="0"
						border="0">



						<tbody id="bpmAgentItem">
							<c:choose>
								<c:when test="${fn:length(agentSetting.agentDefList)>0}">
									<c:forEach items="${agentSetting.agentDefList}"
										var="bpmAgentItem">
										<tr id="def_${bpmAgentItem.flowkey}">
											<td>
												<input type="text" name="flowkey"
													value="${bpmAgentItem.flowkey}" />
												<input type="text" name="flowname"
													value="${bpmAgentItem.flowname}">
												<input type="text" name="defid"
													value="${bpmAgentItem.flowid}">
												
											</td>

										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>

									<tr id="firstRow">
								
										<th width="30%">
											流程名称：
										</th>
										<td>

											<input type="hidden" id="defKey" name="flowkey"
												readonly="readonly" value="${bpmdefinition.defKey }">
											<input type="hidden" id="defId" name="flowId"
												readonly="readonly" value="${bpmdefinition.defId}" />
											<input type="text" id="subject" name="subject"
												readonly="readonly" value="${bpmdefinition.subject}" />
											
										</td>
										<td>
											<div class="panel-toolbar">
												<div class="toolBar">
													<div class="group">
														<a class="link flowDesign"
															onclick="window.open('${ctx}/platform/bpm/bpmDefinition/designBtnx.ht?formDefId=${formDefId}&bangId=${bangId}&flagflex=3')"><span>
														</span>新建事务图</a>
													</div>
													<div class="group">
														<a class="link add" href="javascript:;"
															onclick="addFlow();"><span></span>选择已有流程</a>
													</div>
													<div class="group">
														<a class="link add" href="javascript:;"
															onclick="disbang()"><span></span>解除绑定</a>
													</div>
												</div>
											</div>
										</td>
									</tr>
									<tr><th width="30%">详细信息：</th><td><a
												href="${ctx}/platform/bpm/bpmDefinition/design.ht?defId=${bpmdefinition.defId}&flagflex=3"
												target="_blank">${bpmdefinition.subject}</a></td><td></td></tr>
									
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
				<table class="table-detail" cellpadding="0" cellspacing="0"
						border="0" style="margin-left:3px;margin-right:3px;">



						<tbody id="bpmAgentItem">
							

									<tr id="firstRow" height="40px">
										<th width="30%">
											表单概率 ：
										</th>
										
										<td>
											
												
														<input type="text" name="mes" id="mes" height="30px" 
						value="1.0">
												
											
										
				
			</td>
									</tr>
									<tr>
									<th width="30%">
											
										</th><td><div class="panel-toolbar">
					<div class="toolBar">
						<div class="group">
							<a class="link add" href="javascript:;"
								onclick="bangSave();dialog.close();"><span></span>确定</a>
								<a class="link reload" onclick="window.location.reload()"><span></span>刷新</a>
								<a class="link add" href="javascript:;"
								onclick="dialog.close();"><span></span>取消</a>

						</div>
						
					</div>
				</div></td></tr>
										
									
							
						</tbody>
					</table>
				
			</form>
		</div>

		<textarea id="tableRowTemplate" style="display: none;">

<tr id="def_#defKey" type="subdata">
	<th width="30%">流程名称：</th>
			<td>
			<input type="text" name="flowname" value="#subject">
			
            <input type="hidden" name="flowkey" value="#defKey">
			<input type="hidden" name="flowId" value="#defId">
			
			</td>
		<td>
					<div class="panel-toolbar">
						<div class="toolBar">
								<div class="group">
								<a class="link flowDesign"
									onclick="window.open('${ctx}/platform/bpm/bpmDefinition/designBtnx.ht?formDefId=${formDefId}&bangId=${bangId}')&flagflex=3"><span> </span>新建流程</a>
							</div>
								<div class="group">
								<a class="link add" href="javascript:;" onclick="addFlow();"><span></span>选择已有流程</a>
							</div>
							    <div class="group">
								<a class="link add" href="javascript:;" onclick="disbang()"><span></span>解除绑定</a>
							</div>
						</div>
					</div>	
					</td>	
			
	</tr>
	<tr id="def_#defKey" type="subdata">
<th width="30%">详细信息：</th>
<td>
					<a href="${ctx}/platform/bpm/bpmDefinition/design.ht?defId=#defId&flagflex=3"
						target="_blank">#subject</a>
				</td><td></td>
	</tr>
	
</textarea>

		<textarea id="agentConditionTableRowTemplate" style="display: none;">
	<tr type="subdata">
		<td>
			<input type="hidden" name="condition" value="#condition">
			<input type="hidden" name="memo" value="#memo">
			<input type="hidden" name="agentid" value="#agentid">
			<input type="hidden" name="agent" value="#agent">
		</td>
	</tr>
</textarea>
	
		

	</body>
</html>
