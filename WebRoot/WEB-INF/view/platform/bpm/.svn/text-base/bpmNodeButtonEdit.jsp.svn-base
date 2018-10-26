<%--
	time:2012-07-25 18:26:13
	desc:edit the 自定义工具条
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 自定义工具条</title>
	<%String formDefId =  request.getAttribute("formDefId").toString();%>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmNodeButton"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmNodeButton.js"></script>
	<!--zl引入 FormTableDialog.js -->
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormTableDialog.js"></script>
    <script type="text/javascript" src="${ctx}/js/hotent/ajaxgrid.js"></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/system/BpmDefinitionDialog_new.js"></script>
	<script type="text/javascript">
		var isStartForm=${bpmNodeButton.isstartform};
		var isSign=${bpmNodeButton.nodetype};
		var buttonStr = ${buttonStr};
		var bpmButtonList = eval(buttonStr);
		
		$(function() {
		
		
			function showRequest(formData, jqForm, options) { 
				var operatortype=$("#operatortype").val();
				var btnprobability=$("#btnprobability").val();
				if(operatortype=="0"){
					$.ligerDialog.warn("请选择操作类型",'提示信息');
					return false;
				}
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
			//获取操作类型。
			BpmNodeButton.getOperatorType(bpmButtonList,isStartForm,isSign);
			
			$("#operatortype").change(function(){
				var val=$(this).find("option:selected").text().trim();
				
				var script=$(this).find("option:selected").attr("script");
				if(val!=""){
					$("#btnname").val(val);
				}
				if(script==0){
					$("#trprevscript,#trafterscript").hide();
				}
				else{
					$("#trprevscript,#trafterscript").show();
				}
				if(val == "自定义"){
				   $("#sherlock,#sherlock1").show();
				}
				else{
				   $("#sherlock,#sherlock1").hide();
				}
			});
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
		}
		//zl在线流程设计跳转
       function design(formData, jqForm, options) { 
			var operatortype=$("#operatortype").val();
				if(operatortype=="0"){
					$.ligerDialog.warn("请选择操作类型",'提示信息');
					return false;
				}
				var nurl =__ctx + "/platform/bpm/bpmDefinition/designBtn.ht?buttonId=${buttonId}&sdefId=${defId}&flagflex=3";
						window.open(nurl);
		}
		
	   //zl限制输入小数
	   function checknum(obj)
		{   
			var reg = /^(0|0\.\d*|1(\.0*))$/;
		if(!reg.test(obj.value)){
    		  obj.value = "";
   			 }
	    }
      //zl选择事件类型
     /* function selectTable(){
		var callBack=function(tableId,tableName){	
			$("#tableId").val(tableId);
			$("#tableName").val(tableName);
		}
		FormTableDialogEvent({callBack:callBack});
	};
	//重选事件类型
	function resetTable(){
		$("#tableId").val('');
		$("#tableName").val('');
	};*/
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
					var defKey=newDefKeys[i];
					var defId=newDefIds[i];
					var subject=newSubjects[i];
					
					var row=$("#def_" + defKey);
					if(row.length>0) continue;
					var tr=getRow(defId,defKey,subject);
					$("#bpmAgentItem").empty();
					$("#bpmAgentItem").append(tr);
				}
			
				var url =__ctx + "/platform/bpm/bpmDefinition/sherlock.ht?";
					$.post(url,{buttonId:${buttonId},defId:defId},function(){
				
					});	
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
		function disbang(){
			var url =__ctx + "/platform/bpm/bpmDefinition/disbang.ht?";			  
					$.post(url,{buttonId:${buttonId}},function(){
					alert("已解綁。");
				});
		};
</script>
	<script language="javascript" type="text/javascript"> 
<!-- 
		function openwin() 
			{ 		
				window.open ("${ctx}/js/mydalog/sherlock.jsp", "newwindow", "height=500, width=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no") 
			}	 
--> 
    </script> 
    
    
</head>
<body>
  <c:if test="${buttonFlag}"> 
	<jsp:include page="incDefinitionHead.jsp">
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
					<div class="group"><a class="link reload"   onclick="window.location.reload()"><span></span>刷新</a></div>	
					<!--zl添加按钮，进入在线流程设计 -->
					             <div class="group"  id="sherlock" <c:if test="${bpmNodeButton.operatortype!=18}">style="display:none"</c:if> >
					              <a class="link flowDesign" onclick=design()><span> </span>新建事务图</a>
				                 </div>	
				           
				<!--  <div class="group"><a class="link flowDesign" href="${ctx}/js/ueditor2/dialogs/extend/transactiongraph/transactiongraph.jsp"><span> </span>绘图</a></div> -->	
					<!--<div class="group"><a class="link flowDesign" action="${ctx}/js/mydialog/definition_manager.jsp?defbId=${defbId}" onclick="openLinkDialog({scope:this,isFull:false})"><span> </span>绘图</a></div>
					--><div class="group" id= "sherlock1" <c:if test="${bpmNodeButton.operatortype!=18}">style="display:none"</c:if>><a class="link add"  href="javascript:;" onclick="addFlow();" ><span>
					 </span>选择已有流程</a></div>
					
					<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
				</c:set>
				
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="javascript:;"><span></span>返回</a></div>
					
				</div>
			</div>
		</div>
		<div class="panel-body">
	<form id="bpmNodeButtonForm" method="post" action="save.ht" name="form" >
					
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						   <tr id="table_tr">
						      <!--zl选择事件类型  -->
						      <!--<th width="20%">事件类型:</th>
						        <td style="padding-top: 5px;">
							
							       <input type="text" id="tableName" class="inputText" name="tableName" value="" readonly="readonly" >
							       <input type="hidden" id="tableId" name="tableId" value="">
							       <a href='#' class='link search'  onclick="selectTable()" ></a>
							       <a href='#' class='link redo' style='margin-left:10px;' onclick="resetTable()"><span>重选</span></a>
						       </td>
					       --></tr>
							<tr>
								<th width="20%">按钮名称: </th>
								<td><input type="text" id="btnname"  name="btnname" value="${bpmNodeButton.btnname}"  class="inputText"/></td>
							</tr>
							
							<tr>
							<th width="20%">操作类型: </th>
								<td>
									<select id="operatortype"  name="operatortype" operatortype="${bpmNodeButton.operatortype}" >
									</select>
								</td>
							</tr>
							<tr>
								<th width="20%">发生概率: </th>
								<td><input type="text" id="btnprobability"  name="btnprobability" value="${probability}"  class="inputText" onBlur="checknum(this)" style="ime-mode:disabled" />(以小数表示)
								<input type="hidden" id="bpmNodeButton.defId" name=""/></td>
							</tr>
							<c:choose>
								<c:when test="${item.isstartform==1 }">
									<tr id="trprevscript" <c:if test="${bpmNodeButton.operatortype>1&&bpmNodeButton.operatortype!=18}">style="display:none"</c:if> >
										<th width="20%">前置脚本: </th>
										<td><textarea  id="prevscript" name="prevscript" cols="50" rows="5"  class="inputText">${bpmNodeButton.prevscript}</textarea> </td>
									</tr>
									<tr id="trafterscript" <c:if test="${bpmNodeButton.operatortype>1&&bpmNodeButton.operatortype!=18}">style="display:none"</c:if> >
										<th width="20%">后置脚本: </th>
										<td><textarea  id="afterscript" name="afterscript" cols="50" rows="5"  class="inputText">${bpmNodeButton.afterscript}</textarea></td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr id="trprevscript" <c:if test="${bpmNodeButton.operatortype>8&&bpmNodeButton.operatortype!=18}">style="display:none"</c:if> >
										<th width="20%">前置脚本: </th>
										<td><textarea  id="prevscript" name="prevscript" cols="50" rows="5"  class="inputText">${bpmNodeButton.prevscript}</textarea>
											<div class="tipbox">
												<a href="javascript:;" class="tipinfo">
													<span>
														简单例子：<p>var btn=confirm("是否提交？");</p>
														<p>if(btn){</p>
															<p>&nbsp;&nbsp;alert("do something....");</p>
															<p>&nbsp;&nbsp;return true;</p>
														<p>}</p>
												   		<p>&nbsp;&nbsp;return false;</p>
													</span>
												</a>
											</div>
											<br> 
											<b>脚本为javascript，用于在提交前做些处理，需要返回true或false。返回false时不做提交动作。</b>
										</td>
									</tr>
									<tr id="trafterscript" <c:if test="${bpmNodeButton.operatortype>8&&bpmNodeButton.operatortype!=18}">style="display:none"</c:if> >
										<th width="20%">后置脚本: </th>
										<td><textarea  id="afterscript" name="afterscript" cols="50" rows="5"  class="inputText">${bpmNodeButton.afterscript}</textarea>
											<div class="tipbox">
												<a href="javascript:;" class="tipinfo">
													<span>
														简单例子：<p>var btn=confirm("是否提交？");</p>
														<p>if(btn){</p>
															<p>&nbsp;&nbsp;alert("do something....");</p>
															<p>&nbsp;&nbsp;return true;</p>
														<p>}</p>
												   		<p>&nbsp;&nbsp;return false;</p>
													</span>
												</a>
											</div>
											<br>
											<b>脚本为javascript，用于在提交后做些处理，需要返回true或false。返回false时可以控制不关闭当前窗口。</b>
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</table>
						
						<div class="panel-body" id= "sherlock" <c:if test="${bpmNodeButton.operatortype!=18}">style="display:none"</c:if>>		
					   	<table  class="table-grid table-list"  cellpadding="1" cellspacing="1" style="width:100%" >
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
							    		<tr id="def_${bpmDefinition1.defKey}" >
							    			<td  colspan="2" align="center">
							    			           已绑定的流程:
							    				流程key:<input type="text" name="flowkey" value="${bpmDefinition1.defKey}" />
							    				流程ID：<input type="text" name="flowId" value="${bpmDefinition1.defId}">
							    				流程名称：<input type="text" name="flowname" value="${bpmDefinition1.subject}">
							    				<a href="${ctx}/platform/bpm/bpmDefinition/design.ht?defId=#defId&flagflex=3" target="_blank">#subject</a>
							    			</td>
							    		
							    		</tr>
							    	</c:forEach>
					    		</c:when>
					    		 
					    		<c:otherwise> 
					    			<tr id="firstRow">
					    				<td colspan="2" align="center">
					    			已绑定的流程: 
									 流程key:<input type="text" id="defKey" name="flowkey" readonly="readonly" value="${bpmDefinition1.defKey}">
									流程ID：	<input type="text" id="defId" name="flowId"  readonly="readonly" value="${bpmDefinition1.defId}"   />
									流程名字：<input type="text" id="subject" name="flowname" readonly="readonly" value="${bpmDefinition1.subject}"   />
									<a href="${ctx}/platform/bpm/bpmDefinition/design.ht?defId=${bpmDefinition1.defId}&flagflex=3" target="_blank">${bpmDefinition1.subject}</a>
					    				</td>
					    			</tr>
					    		</c:otherwise>
					    	</c:choose>
					    	</tbody>
					    </table>
				</div>
					    			
					    			
				
			
			


<textarea id="tableRowTemplate" style="display: none;">
	<tr id="def_#defKey"}" type="subdata">
			<td colspan="2" align="center">
			已绑定的流程: 
			流程key:<input type="text" name="flowkey" value="#defKey">
			流程ID：<input type="text" name="flowId" value="#defId">
			流程名字：<input type="text" name="flowname" value="#subject">
			<a href="${ctx}/platform/bpm/bpmDefinition/design.ht?defId=#defId&flagflex=3" target="_blank">#subject</a>
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


						
						<input type="hidden" id="returnUrl" value="getByNode.ht?defId=${bpmAgentItem.flowid}&nodeId=${bpmNodeButton.nodeid}" />
						<input type="hidden" name="actdefid" value="${bpmNodeButton.actdefid}" />
						<input type="hidden" name="nodeid" value="${bpmNodeButton.nodeid}" />
						
						<input type="hidden" name="defbId" value="${bpmNodeButton.defId}" />
						<input type="hidden" name="defbId" value="#defId" />
						
						<input type="hidden" name="nodetype" value="${bpmNodeButton.nodetype}" />
						<input type="hidden" name="isstartform" value="${bpmNodeButton.isstartform}" />
						<input type="hidden" name="sn" value="${bpmNodeButton.sn}" />
						<input type="hidden" name="id" value="${bpmNodeButton.id}" />
						<input type="hidden" name="btnprobability" value="${btnprobability}" />
	</form>
	<tr>
					<td>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<a class="link add" id = "sherlock" <c:if test="${bpmNodeButton.operatortype!=18}">style="display:none"</c:if> href="javascript:;" onclick="disbang();window.location.reload();" ><span>
								</span>解除绑定</a>
							</div>
						</div>
					</div>	
					</td>
				</tr>		
			
</div>
</div>
</body>
</html>
