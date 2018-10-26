<%--
	time:2013-04-29 11:15:10
	desc:edit the 代理设定
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title>编辑代理设定</title>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/BpmDefinitionDialog.js"></script>
	<script type="text/javascript">
		
		
		

		
		/**
		* 流程类型变更处理
		*/
		function handAgentType(){
			var val=parseInt( $(this).val());
			
			switch(val){
				case 0:
					$("#trDefinition,#bpmAgent,#linkAgentCondition").hide();
					$("#trAgent").show();
					break;
				case 1:
					$("#trDefinition,#linkAgentCondition").hide();
					$("#trAgent,#bpmAgent").show();
					
					break;
				case 2:
					$("#trDefinition,#linkAgentCondition").show();
					$("#trAgent,#bpmAgent").hide();
					break;
			}
		}
		/**
		* 选择流程代理人
		*/
		function selectAgent(){
			UserDialog({isSingle:true,
				callback:function(userIds, fullnames){
					$("#agentid").val(userIds);
					$("#agent").val(fullnames);
				}
			});
		};
		
		/**
		* 选择流程受权人
		*/
		function selectAuth(){
			UserDialog({isSingle:true,
				callback:function(userIds, fullnames){
					$("#authid").val(userIds);
					$("#authname").val(fullnames);
				}
			});
		};
		
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
			$("#flowkey").val(defKeys);
			$("#flowname").val(subjects);
		};
		
		/**
		* 部分代理 添加流程
		*/
		function addFlow(){
			BpmDefinitionDialog({isSingle:false,showAll:1,returnDefKey:true,validStatus:2,callback:function(defIds,subjects,defKeys){
				if(!subjects) return ;
				$('#firstRow').remove();
				var newSubjects=subjects.split(",");
				var newDefKeys=defKeys.split(",");
				for(var i=0,len=newDefKeys.length;i<len;i++){
					var defKey=newDefKeys[i];
					
					var subject=newSubjects[i];
					var row=$("#def_" + defKey);
					if(row.length>0) continue;
					var tr=getRow(defKey,subject);
					$("#bpmAgentItem").append(tr);
				}
			}});
		}
		
		/**
		* 部分代理 构造一行流程(用于添加到表中)
		*/
		function getRow(defKey,subject){
			var template=$("#tableRowTemplate").val();
			return template.replaceAll("#defKey",defKey).replaceAll("#subject",subject);
		}
		
		/**
		* 删除一行
		*/
		function singleDel(obj){
			var tr=$(obj).closest('tr');
			$(tr).remove();
		};
		
		
		
	</script>
</head>
<body>
<div class="panel">
	
	
			<div id="bpmAgent"  <c:if test="${agentSetting.authtype==0 or agentSetting.authtype==2}"> style="display: none"</c:if> >
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">授权流程</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<a class="link add" href="javascript:;" onclick="addFlow();"><span></span>添加流程定义</a>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-body" >		
					   	<table  class="table-grid table-list"  Id = "agentDef" cellpadding="1" cellspacing="1" style="width:100%" type="subtable">
					   		<thead>
					   			<tr>
					    			<th>流程名称</th>
					    			<th width="150px">管理</th>
					    		</tr>
					    	</thead>
					    	<tbody id="bpmAgentItem">
					    	<c:choose>
					    		<c:when test="${fn:length(agentSetting.agentDefList)>0}">
					    			<c:forEach items="${agentSetting.agentDefList}" var="bpmAgentItem">
							    		<tr id="def_${bpmAgentItem.flowkey}" type="subdata">
							    			<td>
							    				<input type="hidden" name="flowkey" value="${bpmAgentItem.flowkey}" />
							    				<input type="hidden" name="flowname" value="${bpmAgentItem.flowname}">
							    				<a href="${ctx}/platform/bpm/bpmDefinition/get.ht?defKey=${bpmAgentItem.flowkey}" target="_blank">${bpmAgentItem.flowname}</a>
							    			</td>
							    			<td>
							    				<a href="javascript:;" class="link del" onclick="singleDel(this);" class="link del">删除</a>
							    				
											</td>
							    		</tr>
							    	</c:forEach>
					    		</c:when>
					    		<c:otherwise>
					    			<tr id="firstRow">
					    				<td colspan="2" align="center">
					    					<font color='red'>还未选择流程</font>
					    				</td>
					    			</tr>
					    		</c:otherwise>
					    	</c:choose>
					    	</tbody>
					    </table>
				</div>
			</div>
			
			<div id="agentConditionDiv" style="display: none">
			<table id="agentCondition"  type="subtable">
				<c:forEach items="${agentSetting.agentConditionList}" var="conditionItem">
					<tr type="subdata">
						<input type="hidden" name="condition" value="${fn:escapeXml(conditionItem.condition)}">
						<input type="hidden" name="memo" value="${conditionItem.memo}">
						<input type="hidden" name="agentid" value="${conditionItem.agentid}">
						<input type="hidden" name="agent" value="${conditionItem.agent}">
					</tr>
				</c:forEach>
			</table>
			</div>
			
			<input type="hidden" id="id" name="id" value="${agentSetting.id}" />
			<input type="hidden" name="createtime" value="<fmt:formatDate value='${agentSetting.createtime}' pattern='yyyy-MM-dd'/>"/>
	</form>
</div>

<textarea id="tableRowTemplate" style="display: none;">
	<tr id="def_#defKey"}" type="subdata">
			<td>
			<input type="hidden" name="flowkey" value="#defKey">
			<input type="hidden" name="flowname" value="#subject">
			<a href="${ctx}/platform/bpm/bpmDefinition/get.ht?defKey=#defKey" target="_blank">#subject</a>
			</td>
			<td>
				<a href="javascript:;" class="link del" onclick="singleDel(this);">删除</a>
			</td>
	</tr>
</textarea>

<textarea id="agentConditionTableRowTemplate" style="display: none;">
	<tr type="subdata">
		<td>
			<input type="hidden" name="condition" value="#condition" >
			<input type="hidden" name="memo" value="#memo">
			<input type="hidden" name="agentid" value="#agentid">
			<input type="hidden" name="agent" value="#agent">
		</td>
	</tr>
</textarea>

</body>
</html>
