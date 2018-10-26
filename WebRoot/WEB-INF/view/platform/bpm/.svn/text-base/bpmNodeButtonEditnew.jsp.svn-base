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
				var operatortype=$("#operatortype").val();
				if(operatortype=="18"){$("#btdes,#nnid").show();
				}else{$("#btdes,#nnid").hide();}
				if(val!=""){
					$("#btnname").val(val);
				}
				if(script==0){
					$("#trprevscript,#trafterscript").hide();
					
				}
				else if(script==18){
				
				$("#trprevscript,#trafterscript").show();
				}else{
					$("#trprevscript,#trafterscript").show();
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
		};
		
		//zl在线流程设计跳转
       function design(formData, jqForm, options) { 
			var operatortype=$("#operatortype").val();
				if(operatortype=="0"){
					$.ligerDialog.warn("请选择操作类型",'提示信息');
					return false;
				}
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
	--></script>
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
						   <tr id="table_tr">
						      <!--zl选择事件类型  -->
						      <!--<th width="20%">事类型:</th>
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
						<tr id="nnid">
							<th width="20%">
								绑定节点:
							</th>
							<td>
								<select id="nnid" name="nnid">								
									<c:forEach var="nnidMap" items="${nnidMap}">
									<option value=${nnidMap}>${nnidMap}</option> 
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr id="btdes">	<th width="20%">
								备注:
							</th>
							<td>
								<textarea  id="btdes" name="btdes" cols="50" rows="5"  class="inputText">${bpmNodeButton.btdes}</textarea>
							</td>
						</tr></tr>
						<tr>
								<th width="20%">发生概率: </th>
								<td><input type="text" id="btnprobability"  name="btnprobability" value="${probability}"  class="inputText" onBlur="checknum(this)" style="ime-mode:disabled" />(以小数表示)</td>
							</tr>
							<c:choose>
							
							<c:when test="${item.isstartform==1 }">
							     

								<tr id="trprevscript" <c:if test="${bpmNodeButton.operatortype>1}">style="display:none"</c:if> >
										<th width="20%">前置脚本: </th>
										<td><textarea  id="prevscript" name="prevscript" cols="50" rows="5"  class="inputText">${bpmNodeButton.prevscript}</textarea> </td>
									</tr>
									<tr id="trafterscript" <c:if test="${bpmNodeButton.operatortype>1}">style="display:none"</c:if> >
										<th width="20%">后置脚本: </th>
										<td><textarea  id="afterscript" name="afterscript" cols="50" rows="5"  class="inputText">${bpmNodeButton.afterscript}</textarea></td>
									</tr>
								</c:when>

								<c:otherwise>
									<tr id="trprevscript" <c:if test="${bpmNodeButton.operatortype>8}">style="display:none"</c:if> >
									
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
									<tr id="trafterscript" <c:if test="${bpmNodeButton.operatortype>8}">style="display:none"</c:if> >
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
						<input type="hidden" id="returnUrl" value="getByNode.ht?defId=${bpmNodeButton.defId}&nodeId=${bpmNodeButton.nodeid}" />
						<input type="hidden" name="actdefid" value="${bpmNodeButton.actdefid}" />
						<input type="hidden" name="nodeid" value="${bpmNodeButton.nodeid}" />
						<input type="hidden" name="defId" value="${bpmNodeButton.defId}" />
						<input type="hidden" name="nodetype" value="${bpmNodeButton.nodetype}" />
						<input type="hidden" name="isstartform" value="${bpmNodeButton.isstartform}" />
						<input type="hidden" name="sn" value="${bpmNodeButton.sn}" />
						<input type="hidden" name="id" value="${bpmNodeButton.id}" />
						<input type="hidden" name="btnprobability" value="${btnprobability}" />
						<!--  <input type="hidden" name="nnid" value="${bpmNodeButton.nnid}" />
						<input type="hidden" name="btdes" value="${bpmNodeButton.btdes}" />  -->
						
				</form>
		</div>
</div>
</body>
</html>
