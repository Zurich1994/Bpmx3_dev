<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 流程模板</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/subform.js"></script>
	<script type="text/javascript"
			src="${ctx}/js/hotent/platform/system/BpmDefinitionDialog_new.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#codeTemplateForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				$("#saveData").val(1);
				if(frm.valid()){
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					if(OfficePlugin.officeObjs.length>0){
						OfficePlugin.submit(function(){
							frm.handleFieldName();
							$('#codeTemplateForm').submit();
						});
					}else{
						frm.handleFieldName();
						$('#codeTemplateForm').submit();
					}
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = window.location.href;
					}else{
					    window.location.href = window.location.href;
						//window.location.href = "${ctx}/CodeTemplate/CodeTemplate/codeTemplate/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
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
		};
		/**
		* 选择流程
		*/		
		function selectFlow(){
			BpmDefinitionDialog({isSingle:true,showAll:1,validStatus:2,callback:dlgCallBack,returnDefKey:true});
			
		};
		/**
		* 部分代理 构造一行流程(用于添加到表中)
		*/
		function getRow(defId,defKey,subject){
		
		    
			var template=$("#tableRowTemplate").val();
			
			return template.replaceAll("#defId",defId).replaceAll("#defKey",defKey).replaceAll("#subject",subject);
		};
		
		function saveTemplate(){
			var subjectName = $("subjectName").value;
			var defId = $("defid").val();
			alert("subjectName:"+subjectName);
			aelrt("defId:"+defId);
			document.getElementById("codeTemplateForm").action=save.ht;
		}
			
		
			
	</script>
</head>
<body>
<div class="panel" style="height:100%;overflow:auto;">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty codeTemplateItem.id}">
			        <span class="tbar-label"><span></span>编辑流程模板</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加流程模板</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave"  onclick="saveTemplate()"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="codeTemplateForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail" >
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">

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
  <tr>
   <td colspan="2" class="formHead">流程模板</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模版名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:templatename" lablename="模版名称" class="inputText" validate="{maxlength:50,required:true}" style="width: 180px; isflag="tableflag" type="text" value="${codeTemplate.templatename}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">别名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:bm" lablename="别名" class="inputText" validate="{maxlength:50,required:true}" style="width: 180px; isflag="tableflag" type="text" value="${codeTemplate.bm}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模板类别:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="m:code_template:templatetype" lablename="模板类别" controltype="select" validate="{empty:false,required:true}"  value="${codeTemplate.templatetype}"><option value="1">流程图</option><option value="2">操作图</option><option value="3">事务图</option><option value="4">word文档</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程类别:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="m:code_template:subjecttype" lablename="流程类别" controltype="select" validate="{empty:false}" value="${codeTemplate.subjecttype}"><option value="1">行政执法</option><option value="2">政务公开</option></select></span></td>
  
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input id="subjectName" name="m:code_template:subjectname" lablename="流程名称" class="inputText" validate="{maxlength:100,required:true}" style="width: 180px; isflag="tableflag" type="text" value="${codeTemplate.subjectname}" /></span>
        <a class="link add" href="javascript:;" onclick="addFlow();"><span></span>选择</a></td>
        <input type="hidden" id="defid" name="flowid" value="${codeTemplate.subjectname}" >
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">备注:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:bz" lablename="备注" class="inputText" validate="{maxlength:500}" style="width: 180px; isflag="tableflag" type="text" value="${codeTemplate.bz}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">是否标记为模板:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="m:code_template:issign" lablename="是否标记为模板" controltype="select" validate="{empty:false}"  value="${codeTemplate.issign}"><option value="1">是</option><option value="2">fou</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">创建时间:</td>
   <td class="formInput" style="width:80%;"><input name="m:code_template:createtime" class="Wdate" displaydate="1" datefmt="yyyy-MM-dd" validate="{empty:false}" style="width: 180px; type="text" value="${codeTemplate.createtime}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模板生成的文件路径:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:mbscdwjlj" lablename="模板生成的文件路径" class="inputText" validate="{maxlength:500}" isflag="tableflag" type="text" value="${codeTemplate.mbscdwjlj}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模板文件生成的文件名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:mbwjscdwjmc" lablename="模板文件生成的文件名称" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${codeTemplate.mbwjscdwjmc}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模板XML:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:templateXML" lablename="模板XML" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${codeTemplate.templateXML}" /></span></td>
  </tr>
  </c:otherwise>
</c:choose>
		
 </tbody>
</table>
			</div>
		</div>
		<textarea id="tableRowTemplate" style="display: none;">
	<tr>
   <td colspan="2" class="formHead">流程模板</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模版名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:templatename" lablename="模版名称" class="inputText" validate="{maxlength:50,required:true}" style="width: 180px; isflag="tableflag" type="text" value="${codeTemplate.templatename}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">别名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:bm" lablename="别名" class="inputText" validate="{maxlength:50,required:true}" style="width: 180px; isflag="tableflag" type="text" value="${codeTemplate.bm}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模板类别:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="m:code_template:templatetype" lablename="模板类别" controltype="select" validate="{empty:false,required:true}" value="${codeTemplate.templatetype}"><option value="1">流程图</option><option value="2">操作图</option><option value="3">事务图</option><option value="4">word文档</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程类别:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="m:code_template:subjecttype" lablename="流程类别" controltype="select" validate="{empty:false}" value="${codeTemplate.subjecttype}"><option value="1">行政执法</option><option value="2">政务公开</option></select></span></td>
  </tr>
   <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:subject" lablename="流程名称" class="inputText" validate="{maxlength:50}" style="width:180px; isflag="tableflag" type="text"  value="#subject" /></span>
   <a class="link add" href="javascript:;" onclick="addFlow();"><span></span>选择</a></td>
   <input type="hidden" id="defid" name="flowid" readonly="readonly" value="#defid" >
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">备注:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:bz" lablename="备注" class="inputText" validate="{maxlength:500}" style="width: 180px; isflag="tableflag" type="text" value="${codeTemplate.bz}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">是否标记为模板:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="m:code_template:issign" lablename="是否标记为模板" controltype="select" validate="{empty:false}" value="${codeTemplate.issign}"><option value="1">是</option><option value="2">否</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">创建时间:</td>
   <td class="formInput" style="width:80%;"><input name="m:code_template:createtime" class="Wdate" displaydate="1" datefmt="yyyy-MM-dd" validate="{empty:false}" style="width: 180px; type="text" value="${codeTemplate.createtime}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模板生成的文件路径:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:mbscdwjlj" lablename="模板生成的文件路径" class="inputText" validate="{maxlength:500}" isflag="tableflag" type="text" value="${codeTemplate.mbscdwjlj}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模板文件生成的文件名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:mbwjscdwjmc" lablename="模板文件生成的文件名称" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${codeTemplate.mbwjscdwjmc}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模板XML:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:templateXML" lablename="模板XML" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${codeTemplate.templateXML}" /></span></td>
  </tr>
  
   
		<input type="hidden" name="id" value="${codeTemplate.id}"/>
		<input type="hidden" id="saveData" name="saveData"/>
		<input type="hidden" name="executeType"  value="start" />
	</form>
</body>
</html>
