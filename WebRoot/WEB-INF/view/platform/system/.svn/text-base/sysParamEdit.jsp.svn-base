<%--
	time:2012-02-23 17:43:35
	desc:edit the 组织或人员参数属性
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 组织或人员参数属性</title>
	<%@include file="/commons/include/form.jsp" %>
	<f:link href="tree/zTreeStyle.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysParam"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/htCatCombo.js"></script>
	<script type="text/javascript">
		$(function() {
			var paramId = $("#paramId").val();
			if(paramId == null || paramId == ""){
				$("#paramName").blur(function(){
					var obj=$(this);
					autoPingin(obj);
				});
			}else{
				$("#paramKey").attr("disabled","disabled");
			}
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(paramId == ""){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			
			$("a.save").click(function() {
				var temp = $("[name='sourceKey']").val();
				$("[name='sourceKey']").val($("[name='dictType']").val());
				$('#sysParamForm').submit(); 
				$("[name='sourceKey']").val(temp);
			});

			if(${sysParam.effect == 2}){
				$('#selDem').show();
			}
			if(${isAdd==false}){	
				initData();
			}			
			
		});

		function initData(){
		    if(${sysParam.sourceType =='input'} ){
		    	$("#xuanzexiang").hide();
	    	}else if(${sysParam.sourceType =='dict'} ){
		    	$("#xuanzexiang").show();
				$("#dictionary").show();
				$("#selectItems").hide();
	    	}else{
	    		$("#xuanzexiang").show();
				$("#dictionary").hide();
				$("#selectItems").show();
		    	obj='${sysParam.sourceKey}';
		    	var json = eval('(' + obj+ ')');
	    	    $.each(json,function(key,value){
	    	    	initSelect(key,value);
	    		});
	    	}
		}
		
		function initSelect(key,value){
			var aryTr=[
				'<tr type="subdata" >',
				'<td style="text-align: left;">',
				'选项:<input class="inputText" type="text" value="'+key+'" name="key" validate="{required:true,maxlength:120}"	style="width: 50%;" >',
				'</td>',
				'<td style="text-align: left;">',
				'值:<input class="inputText" type="text" value="'+value+'" name="value" style="width: 50%;" >',
				'</td>',
				'<td style="text-align:center;">',
				'<a href="#" class="btn btn-info fa-delete " onclick="singleDell(this);">删除</a>',
				'</td>',
				'</tr>'];
				$("#selectItem").append(aryTr.join(""));
		}
		
		function showDem(){
			if($('option:selected').val()=='1'){
				$('#selDem').hide();
			}else{
				$('#selDem').show();
			}
		}
		function changeSelectFromType(){
			var type = $("#sourceType").val();
			if(type == 'input' || type == ''){
				$("#xuanzexiang").hide();
			}else if(type == 'dict'){
				$("#xuanzexiang").show();
				$("#selectItems").hide();
				$("#dictionary").show();
			}else{
				$("#xuanzexiang").show();
				$("#dictionary").hide();
				$("#selectItems").show();
			} 
		}
		
		function add(){
			var aryTr=[
				'<tr type="subdata" >',
				'<td style="text-align: left;">',
				'选项:<input class="inputText" type="text" name="key" validate="{required:true,maxlength:120}"	style="width: 50%;" >',
				'</td>',
				'<td style="text-align: left;">',
				'值:<input class="inputText" type="text" name="value" style="width: 50%;" >',
				'</td>',
				'<td style="text-align:center;">',
				'<a href="#" class="btn btn-info fa-delete " onclick="singleDell(this);">删除</a>',
				'</td>',
				'</tr>'];
			$("#selectItem").append(aryTr.join(""));
		}
		function singleDell(obj){
			$(obj).closest('tr').remove();
		};
		
		function selectCategory(obj) {
			var selObj = $(obj);
			$("#category").val(selObj.val());
		}

	</script>

</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${sysParam.paramId !=null}">
			            <span class="tbar-label">编辑组织或人员参数属性</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加组织或人员参数属性</span>
			        </c:otherwise>
			    </c:choose>
				
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="sysParamForm" method="post" action="save.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">参数名: </th>
							<td><input type="text" id="paramName" name="paramName" value="${sysParam.paramName}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">参数Key: </th>
							<td><input type="text" id="paramKey" name="paramKey" value="${sysParam.paramKey}"  class="inputText"/></td>
						</tr>
						
						<tr>
							<th width="20%">参数类型: </th>
							<td>
								<select id="effect" name="effect" onchange="showDem()">
									<option value="1" <c:if test="${sysParam.effect==1}">selected="selected"</c:if>>用户参数</option>
									<option value="2" <c:if test="${sysParam.effect==2}">selected="selected"</c:if>>组织参数</option>
								</select>
								<span id="selDem" hidden="true">
								   &ensp; 所属维度：
								   <select name="belongDem" class="inputText">
								     <c:forEach items="${demList}" var="d">
									     <option value="${d.demId}" <c:if test="${sysParam.belongDem==d.demId}">selected="selected"</c:if>>${d.demName}</option>
									  </c:forEach>
							     	</select>
								</span>
								
							</td>
						</tr>
						<tr>
							<th><span>参数分类:</span></th>
							<td>
								<input id="category" name="category" value="${sysParam.category}" class="inputText"/> 
									<select onchange="selectCategory(this)" class="inputText">
										<option value="">请选择</option>
										<c:forEach items="${categoryList}" var="catName">
											<option value="${catName}">${catName}</option>
										</c:forEach>
									</select>
							</td>
						</tr>
						<tr>
							<th>
							    <span class="required">*</span><span>数据来源:</span>
							</th>
							<td colspan="3">
								<select class="inputText" id="sourceType" name="sourceType" onchange="changeSelectFromType()" name="fromType">
									<option value="input" <c:if test="${sysParam.sourceType=='input'}">selected="selected"</c:if>>手动录入</option>
									<option value="dict" <c:if test="${sysParam.sourceType=='dict'}">selected="selected"</c:if>>数据字典</option>
									<option value="select" <c:if test="${sysParam.sourceType=='select'}">selected="selected"</c:if>>下拉框</option>
									<option value="checkbox" <c:if test="${sysParam.sourceType=='checkbox'}">selected="selected"</c:if>>复选框</option>
									<option value="radio" <c:if test="${sysParam.sourceType=='radio'}">selected="selected"</c:if>>单选按钮</option>
								</select>
							</td>								
						</tr>
						<tr id="xuanzexiang"  hidden="true">						
							<th><span>自定义选择项:</span></th>
							<td colspan="4">
							<div id="dictionary">
							  <input value="${sysParam.sourceKey}"  catValue="${sysParam.sourceKey}" id="dictTypeName" class="catComBo" catKey="DIC" valueField="dictType" isNodeKey="true" name="sourceKey" height="150" width="200"/>
							</div>
							<div id="selectItems">
								<table class="" type="sub" id="selectItem">  
								    <thead>
								    	 <tr>
								    		<th colspan="3"  class="add" style="width: 10%; text-align: left;">
								    	 		<a href="#" onclick="add()">添加项</a>
								    		</th>
								    	 </tr>
								    </thead>
								</table>
						   	</div>
							</td>								
						</tr>
						<tr>
						<th width="20%">数据类型: </th>
							<td>
								<select id="dataType" name="dataType" >
									<c:forEach items="${dataTypeMap}" var="t">
										<option value="${t.key }" <c:if test="${sysParam.dataType==t.key}">selected="selected"</c:if>>${t.value }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<th width="20%">参数描述:</th>
							<td>
								<textarea cols="10" rows="3" id="description" name="description"  class="inputText">${sysParam.description}</textarea>
							</td>
						</tr>
						<tr>
						<th width="20%">状态:</th>
						    <td>
						    <select id="status_" name="status_">
							   <option value="1"<c:if test="${sysParam.status_==1}">selected="selected"</c:if>>启用</option>
							   <option value="2"<c:if test="${sysParam.status_==2}">selected="selected"</c:if>>禁用</option>
						    </select>
						    </td>
					    </tr>
					</table>
					<input type="hidden" name="paramId" value="${sysParam.paramId}" />
				</form>
		</div>
</div>
</body>
</html>
