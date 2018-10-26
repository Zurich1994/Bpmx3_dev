<%--
	time:2011-11-16 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>表单字段预览</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
<script type="text/javascript">
	var valid;
	$(function() {
		valid=$("#bpmFormDefForm").form();
		$("#btnDeploy").click(function(){
			var valid=$("#bpmFormDefForm").form().valid();
			if(!valid){
				return;
			}
			$.ligerDialog.confirm("确认要发布表单吗?",function(rtn){
				if(rtn){
					deploy();
				}
			});
		});
   		window.onbeforeunload = function() {			    				    	
     		  return '你确定吗？';
    	 };
	});
	
	function deploy(){		
		var rtn=valid.valid();
		if(!rtn) return;
		var json=getJson();
		$("#json").val(json);

		var params={};
		var container=$("#bpmFormDefForm");
		$("input,textarea",container).each(function(){
			var obj=$(this);
			params[obj.attr("name")]=obj.val();
		})
		
		$.ligerDialog.waitting("正在发布表单，请稍后...");
		$.post("saveForm.ht",params,function(responseText){
			$.ligerDialog.closeWaitting();
			var obj=new com.hotent.form.ResultMessage(responseText);
			if(obj.isSuccess()){//成功
				$.ligerDialog.success(obj.getMessage(),'提示信息',function(){
					window.onbeforeunload =null;
					if(window.opener){
						try{
							window.opener.window.dialog.get('pwin').reload();
						}catch(e){
							
						}
					}
					window.close();
				});
			}
			else{
				$.ligerDialog.err('出错信息',"发布表单失败",obj.getMessage());
			}
		});
		
	}
	
	function getJson(){
		var mainJson={};
		
		var mainObj = $("tbody[type='mainTable']");
		var mainTableName = $("#tableName").val();
		var mainRows=[];
		$("tr[fieldName]",mainObj).each(function(){
			var obj={};
			obj.fieldName=$(this).attr("fieldName");
			obj.tableName=mainTableName;
			$("input:checkbox",$(this)).each(function(){
				var name=$(this).attr("name");
				var val=$(this).attr("checked")=='checked'?1:0;
				obj[name]=val;
			});
			mainRows.push(obj);
		});
		mainJson.rows=mainRows;
		
		//[{tableName:'',rows:[]}]
		var subTable=[];
		$("tbody[type='subTable']").each(function(){
			var tableObj=$(this);
			var tableJson={};
			tableJson.tableName=tableObj.attr("tablename");
			tableJson.rows=rows=[];
			$("tr[fieldName]",tableObj).each(function(){
				var trObj=$(this);
				var rowJson={};
				rowJson.fieldName=trObj.attr("fieldname");
				rowJson.tableName=tableJson.tableName;
				$("input:checkbox",trObj).each(function(){
					var name=$(this).attr("name");
					var val=$(this).attr("checked")=='checked'?1:0;
					rowJson[name]=val;
				});
				rows.push(rowJson);
			});
			subTable.push(tableJson);
		});
		mainJson.subTable=subTable;
		var str=JSON2.stringify(mainJson);
		return str;
	};
	//返回编辑界面
	function goBack(){
		window.onbeforeunload =null;
		$("#bpmFormDefForm")[0].submit();
	};
	
	//根据表描述 生成表名
	function getKeyName(obj){
		var tableName = $("#tableName");
		if(!tableName.val().length < 1)return;
	    var value=$(obj).val();
		Share.getPingyin({
				input:value,
				postCallback:function(data){
					tableName.val(data.output);
				}
		});
	}
</script>
<style type="text/css">
.table-grid td {
text-align: center;
}
</style>
</head>
<body >
<div class="panel">
	<div class="panel-top">	
		<div class="tbar-title">
			<span class="tbar-label">表单字段列表</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="btnDeploy" href="#"><span></span>发布表单</a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link back" href="javascript:;" onclick="goBack()"><span></span>返回</a>
				</div>
			</div>
		</div>
		<div class="panel-search">
				<form id="bpmFormDefForm" action="designEdit.ht" method="post">
					<table cellpadding="0" cellspacing="0" border="0" style=" margin-bottom:4px;" >
						<tr style="height:25px;">
							<td style="padding: 2px;">表单标题:&nbsp;<input id="subject" type="text" name="subject" value="${subject}" class="inputText" validate="{required:true}" /></td>
							<td style="padding: 2px;">表单别名:&nbsp;<input id="formKey" type="text" name="formKey" value="${formKey}" <c:if test="${formDefId>0 }">readonly='readonly'</c:if> class="inputText" validate="{required:true}" /></td>
							<td style="padding: 2px;">表单描述:&nbsp;<input id="formDesc" type="text" name="formDesc" value="${formDesc}" class="inputText" /></td>
							<td style="padding: 2px;">表注释:&nbsp;<input id="tableComment" type="text" name="tableComment" value="${tableComment}" class="inputText longInputText" validate="{required:true}"  onblur="getKeyName(this)"/></td>
							<td style="padding: 2px;">表名:&nbsp;<input id="tableName" type="text" <c:if test="${canEditTableName==false }">readonly='readonly'</c:if> name="tableName"  value="${tableName}" class="inputText longInputText" validate="{required:true,variable:true}" /></td>
							<textarea name="html" style="display:none;">${fn:escapeXml(content) }</textarea>
							<input id="tabTitle" type="hidden" name="tabTitle" value="${tabTitle}" />
							<input id="formDefId" type="hidden" name="formDefId" value="${formDefId}" />
							<input id="categoryId" type="hidden" name="categoryId" value="${categoryId}" />
							<input id="publish" type="hidden" name="publish" value="1" />
							<input id="isBack" type="hidden" name="isBack" value="1" />
							<textarea id="json" name="json" style="display:none">${json}</textarea>
						</tr>
					</table>
				</form>
		</div>
	</div>
	<div class="panel-body">
			<table cellpadding="1" cellspacing="1" class="table-grid" >
				<tr>
					<th>列名</th>
					<th>注释</th>
					<th>类型</th>
					<th>必填</th>
					<th>作为查询条件</th>
					<th>显示到列表</th>
					<th>流程变量</th>
					<th>支持Web印章验证</th>
				</tr>
				
				<tbody  type="mainTable">
					<c:forEach items="${result.bpmFormTable.fieldList}" var="field">
					<tr fieldName="${field.fieldName}">
						<td>${field.fieldName }</td>
						<td>${field.fieldDesc }</td>
						<td>${field.fieldTypeDisplay}</td>
						<td>
							<c:choose>
								<c:when test="${field.isRequired==1}">
									<input type="checkbox" name="isRequired" checked="checked" />
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="isRequired"  />
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${field.isQuery==1}">是</c:when>
								<c:otherwise>否</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${field.isList==1}">
									<input type="checkbox" name="isList" checked="checked" />
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="isList"  />
								</c:otherwise>
							</c:choose>
						
						</td>
						<td>
							<c:choose>
								<c:when test="${field.isFlowVar==1}">
									<input type="checkbox" name="isFlowVar" checked="checked" />
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="isFlowVar"  />
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${field.isWebSign==1}">
									<input type="checkbox" name="isWebSign" checked="checked" />
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="isWebSign"  />
								</c:otherwise>
							</c:choose>						
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
						
			<c:if test="${not empty result.bpmFormTable.subTableList}">
					<c:forEach items="${result.bpmFormTable.subTableList}" var="table">
						<table cellpadding="1" cellspacing="1" class="table-grid" style="margin-top: 5px;" >
							<tr>
								<td colspan="7">类型:子表, 表名:${table.tableName},表描述:${table.tableDesc }</td>
							</tr>
							<tr>
								<th >列名</th>
								<th >注释</th>
								<th >类型</th>
								<th >必填</th>
								<th>支持Web印章验证</th>
							</tr>
						
							<tbody type="subTable" tablename="${table.tableName}">
								<c:forEach items="${table.fieldList}" var="field">
								<tr fieldName="${field.fieldName}">
									<td>${field.fieldName}</td>
									<td>${field.fieldDesc}</td>
									<td>${field.fieldTypeDisplay}</td>
									<td>
										<c:choose>
											<c:when test="${field.isRequired==1}">
												<input type="checkbox" name="isRequired" checked="checked" />
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="isRequired"  />
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${field.isWebSign==1}">
												<input type="checkbox" name="isWebSign" checked="checked" />
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="isWebSign"  />
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:forEach>
			</c:if>
	</div>
</div>
</body>
</html>

