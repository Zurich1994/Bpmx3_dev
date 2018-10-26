<%--
	time:2012-03-16 10:53:20
	desc:edit the 常用语管理
--%>
<%@page language="java" pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	
	<title>编辑 常用语</title>
	
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=taskApprovalItems"></script>
	<script type="text/javascript">
		var isAdmin='${isAdmin}';
		$(function(){
			//点击返回
			$("a.back").click(function(){
				location.href='list.ht?isAdmin='+isAdmin;
			})
			
			//TaskReminder form Edit Layout
			tab=$("#reminder-div-tab").ligerTab({
			});
			
		})
		
		function save(){
			//获取那个是选择的tab
			var id=tab.getSelectedTabItemID();
			var selectDiv=$("div[tabid='"+id+"']");
			var type=$("#type",selectDiv).val();
			var approvalItem=$("#approvalItem",selectDiv).val();
			var flowTypeId,defKey;
			if(type==2){
				var aryTypeId = [];
				$("span[id^='type_']",selectDiv).each(function (i,ch){
					aryTypeId.push($(ch).attr("typeId"));
				});
				flowTypeId=aryTypeId.join(",");
			}else if(type==3){
				 var aryDefKey = [];
				$("span[id^='ref_']",selectDiv).each(function (i,ch){
					aryDefKey.push($(ch).attr("referKey"));
				});
				defKey=aryDefKey.join(",");
			}
			var param={type:type,approvalItem:approvalItem,flowTypeId:flowTypeId,defKey:defKey}
			var url=__ctx+"/platform/bpm/taskApprovalItems/save.ht";
			$.post(url,param,function(msg){
				var obj=new com.hotent.form.ResultMessage(msg);
				if(obj.isSuccess()){
					$.ligerDialog.confirm('操作成功,继续操作吗?','提示',function(rtn){
						if(!rtn){
							location.href='list.ht?isAdmin='+isAdmin;
						}
						else{
							location.href=location.href.getNewUrl();
						}
					});
				}else{
					$.ligerDialog.error(obj.getMessage(),'提示');
				}
			});
		}
			
		
		function referDefinition(){
			var url=__ctx +'/platform/bpm/bpmDefinition/defReferSelector.ht?defId=${bpmDefinition.defId}';
			referDef = $.ligerDialog.open({
				title:'选择流程',
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
				        	 var json={};
				        	 var defKeyTemp,subjectTmep,spanHtml='';
					     		$.each(chKeys,function(i,ch){
					     			aryDefKey.push($(ch).val());
					     			json[$(ch).val()]=$(ch).attr("defSubject");
					     			
					     		});
					     		var defKeyJson=$("input#refDefKey").val();
					     		if(defKeyJson){
					     			//处理先选择的数据和以前的数据是否有重复，去除重复
					     			var jsontemp=JSON2.parse(defKeyJson);
					     			for(var i in jsontemp){
					     				//console.info(jsontemp[i]);
					     				for(var j in json){
					     					if(i!=j){
					     						json[i]=jsontemp[i];
					     					}
					     				}
					     			} 
					     		}
					     		for(var k in json){
					     			spanHtml=spanHtml+"<span id='ref_"+k+"' referKey="+k+">"+json[k]+"<a href='javascript:void(0);' onclick='delRefer(\""+k+"\")'>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>"
					     		}
					     		if(aryDefKey.length > 0){
					    				$("span#refDefArray").html(spanHtml);
					    				$("input#refDefKey").val(JSON2.stringify(json));
					    				dialog.close();
					    		}else{
					    			$.ligerDialog.warn('请选择引用流程!','提示');
					    		}
								
						}
				         },
						{ text: '取消', onclick: function (item, dialog) { dialog.close(); } }
						]});
		};
		
		
		function delRefer(refKey){
			$('#ref_'+refKey).remove();
			var jsonTemp=$("input#refDefKey").val();
			var json=JSON2.parse(jsonTemp);
			//删除json对应的数据
			delete json[refKey];
			$("input#refDefKey").val(JSON2.stringify(json));
		}
		
		function flowTypeSelector(){
			var url=__ctx +'/platform/system/globalType/flowTypeSelector.ht';
			referDef = $.ligerDialog.open({
				title:'选择流程分类',
				mask:true,
				isResize:true,
				height: 500,
				url:url,
				width:700,
				buttons:[
				         { text: '确定', onclick: function (item, dialog) {
				        	 var contents=$("iframe",dialog.dialog).contents()
				        	 var chIds=contents.find("input.pk[name=typeId]:checked");
				        	 var arytypeId = [];
				        	 var json={};
				        	 var typeIdTemp,subjectTmep,spanHtml='';
					     		$.each(chIds,function(i,ch){
					     			arytypeId.push($(ch).val());
					     			json[$(ch).val()]=$(ch).attr("defSubject");
					     		});
					     		
					     		var flowJson=$("input#refFlowKey").val();
					     		if(flowJson){
					     			//处理先选择的数据和以前的数据是否有重复，去除重复
					     			var jsontemp=JSON2.parse(flowJson);
					     			for(var i in jsontemp){
					     				//console.info(jsontemp[i]);
					     				for(var j in json){
					     					if(i!=j){
					     						json[i]=jsontemp[i];
					     					}
					     				}
					     			} 
					     		}
					     		for(var k in json){
					     			spanHtml=spanHtml+"<span id='type_"+k+"' typeId="+k+">"+json[k]+"<a href='javascript:void(0);' onclick='delType(\""+k+"\")'>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>"
					     		}
					     		
					     		if(arytypeId.length > 0){
					    				$("span#flowTypeArray").html(spanHtml);
					    				$("input#refFlowKey").val(JSON2.stringify(json));
					    				dialog.close();
					    		}else{
					    			$.ligerDialog.warn('请选择流程分类!','提示');
					    		}
								
						}
				         },
						{ text: '取消', onclick: function (item, dialog) { dialog.close(); } }
						]});
		}
		//删除流程分类
		function delType(typeId){
			$('#type_'+typeId).remove();
			var jsonTemp=$("input#refFlowKey").val();
			var json=JSON2.parse(jsonTemp);
			//删除json对应的数据
			delete json[typeId];
			$("input#refFlowKey").val(JSON2.stringify(json));
			
		}
		
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">设置常用语</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" onclick="save()"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="javascript:;"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<div class="reminder-layout" id="reminder-layout">
			<div class="reminder-edit" position="center">
			<div style="height:570px;overflow: auto;">
				<form id="taskApprovalItems" method="post" action="save.ht">
					<div class="reminder-div" >
						<div class="reminder-div-tab" id="reminder-div-tab">
							<c:choose>
								<c:when test="${isAdmin==1}">
									<div class="reminder-div-all" title="全局设置" >
									<input type="hidden" name="type" id="type" value="1"/>
										<div class="panel-detail">
											<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
												<tr>
													<th width="20%">常用语: </th>
													<td>
														<textarea rows="5" cols="60" id="approvalItem" name="approvalItem" 
															style="margin-top: 5px;margin-bottom: 5px;">${taskApprovalItems.expression}</textarea>
														<div class="tipbox"><a class="tipinfo"><span>一行就是一条常用语</span></a></div>
													</td>
												</tr>
											</table>
										</div>
									</div>
									<div class="reminder-div-flow-type" title="根据流程分类设置" >
									<input type="hidden" name="type" id="type" value="2"/>
								    	<div class="panel-detail">
										    <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
											    <tr id="trFlowType">
													<th width="20%">流程分类: </th>
													<td>
														<div id="flowTypeDiv">
															<%-- <select id="flowType" name="flowType">
																<option value="">-请选择-</option>
																<c:forEach items="${globalTypeList}" var="globalType">
																	<c:choose>
																		<c:when test="${globalType.typeId==taskApprovalItems.typeId }">
																			<option value="${globalType.typeId}" selected="selected">${globalType.typeName }</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${globalType.typeId }">${globalType.typeName}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select> --%>
														<input type="hidden" id="refFlowKey" name="refFlowKey"/>
											    		<span id="flowTypeArray">
														</span>
														<a class="link search" href="javascript:void(0);" onclick="flowTypeSelector()">选择流程分类</a>
														</div>
													</td>
												</tr>
										    	<tr>
													<th width="20%">常用语: </th>
													<td>
														<textarea rows="5" cols="60" id="approvalItem" name="approvalItem" 
															style="margin-top: 5px;margin-bottom: 5px;">${taskApprovalItems.expression}</textarea>
													<div class="tipbox"><a class="tipinfo"><span>一行就是一条常用语</span></a></div>
													</td>
												</tr>
											</table>
										</div>
									</div>
									<div class="reminder-div-flow" title="根据流程设置" >
									<input type="hidden" name="type" id="type" value="3"/>
								    	<div class="panel-detail">
										    <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
										    	<tr>
										    		<th width="20%">流程定义:</th>
										    		<td >
										    			<input type="hidden" id="refDefKey" name="refDefKey"/>
											    		<span id="refDefArray">
														</span>
														<a class="link search" href="javascript:void(0);" onclick="referDefinition()">选择流程</a>
										    		</td>
										    	</tr>
										    	<tr>
													<th width="20%">常用语: </th>
													<td>
														<textarea rows="5" cols="60" id="approvalItem" name="approvalItem" 
															style="margin-top: 5px;margin-bottom: 5px;">${taskApprovalItems.expression}</textarea>
															<div class="tipbox"><a class="tipinfo"><span>一行就是一条常用语</span></a></div>
													</td>
												</tr>
											</table>
										</div>
								    </div>
						    	</c:when>
								<c:when test="${isAdmin==0}">
									<div class="reminder-div-all" title="个人常用语设置" >
									<input type="hidden" name="type" id="type" value="4"/>
										<div class="panel-detail">
											<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
												<tr>
													<th width="20%">常用语: </th>
													<td>
														<textarea rows="5" cols="60" id="approvalItem" name="approvalItem" 
															style="margin-top: 5px;margin-bottom: 5px;">${taskApprovalItems.expression}</textarea>
														<div class="tipbox"><a class="tipinfo"><span>一行就是一条常用语</span></a></div>
													</td>
												</tr>
											</table>
										</div>
									</div>
								</c:when>
							</c:choose>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
