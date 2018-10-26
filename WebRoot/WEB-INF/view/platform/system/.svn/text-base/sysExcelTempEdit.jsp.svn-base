<%--
	time:2015-06-13 14:36:35
	desc:edit the Excel模板
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>编辑 Excel模板</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/subform.js"></script>
<script type="text/javascript">
	$(function() {
		$("a.save").click(function() {
			$("#sysExcelTempForm").attr("action", "save.ht");
			$("#saveData").val(1);
			submitForm();
		});
	});
	//提交表单
	function submitForm() {
		var options = {};
		if (showResponse) {
			options.success = showResponse;
		}
		var frm = $('#sysExcelTempForm').form();
		frm.ajaxForm(options);
		if (frm.valid() && !isRepeatShowIndex()) {
			frm.sortList();
			frm.submit();
		}
	}

	function showResponse(responseText) {
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (obj.isSuccess()) {
			$.ligerDialog
					.confirm(
							obj.getMessage() + ",是否继续操作",
							"提示信息",
							function(rtn) {
								if (rtn) {
									//window.location.href = window.location.href;
									window.location.reload();
								} else {
									window.location.href = "${ctx}/platform/system/sysExcelTemp/list.ht";
								}
							});
		} else {
			$.ligerDialog.error(obj.getMessage(), "提示信息");
		}
	}
	
	// 判断显示顺序是否重复
	function isRepeatShowIndex(){
		if($("[name='showIndex']:visible").size()==0){
			$.ligerDialog.warn("请填写明细！","提示");
		       return true;
		}
		var ary = new Array();
		$("[name='showIndex']:visible").each(function(i) {
			ary[i] = $(this).text();
		});
		var nary = ary.sort();
		for(var i = 0; i < nary.length - 1; i++){
		   if (nary[i] == nary[i+1]){
			   $.ligerDialog.warn("显示顺序重复！","提示");
		       return true;
		    }
		}
	}
	
	// 导入excel数据模板
	function importTempDataSample(){
		if($("#file").val()==""){
			$.ligerDialog.warn("请先选择模板Excel文件!","提示");
			return ;
		}
		if(isRepeatShowIndex()){
			return;
		}
		var url = "${ctx}/platform/system/sysExcelTemp/importTempDataSample.ht?id=${sysExcelTemp.id}";
		url=url.getNewUrl();
		$("#sysExcelTempForm").attr("enctype","multipart/form-data");
		$("#sysExcelTempForm").attr("action",url);
		var options={};
		if(showResponse){
			options.success=showResponse;
		}
		var frm=$('#sysExcelTempForm').form();
		frm.ajaxForm(options);
		frm.submit();
	}
	
	// 导出excel模板
	function exportTempDataSample(obj){
		var url = "${ctx}/platform/system/sysExcelTemp/exportTempDataSample.ht?id=${sysExcelTemp.id}";
		url=url.getNewUrl();
	  	$(obj).attr("href",url);
	}
	
	//根据字段描述生成字段名
	function autoGetKey(inputObj){
		var url=__ctx + '/platform/form/bpmFormTable/getFieldKey.ht' ;
		var subject=$(inputObj).val();
		if($.trim(subject).length<1) return;
		$.ajax({
			  url: url,
			  async:false,
			  type:'POST',
			  data: ({subject : subject}),
			  success: function(data){
				  var json=eval('('+data+')');
					if(json.result==1 && $.trim($('#fieldName').val()).length<1    ){			
						$('#tempCode').val(json.message);
						var valid=validateField();
						valid.form();
					
					}
			  }
		});
	}
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<c:choose>
					<c:when test="${sysExcelTemp.id !=null}">
						<span class="tbar-label"><span></span>编辑Excel模板</span>
					</c:when>
					<c:otherwise>
						<span class="tbar-label"><span></span>添加Excel模板</span>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="#"><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sysExcelTempForm" method="post" action="save.ht">
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0" type="main">
					<tr>
						<th width="20%">模板名称:</th>
						<td><input type="text" id="tempName" name="tempName"
							value="${sysExcelTemp.tempName}" class="inputText"
							validate="{required:true,maxlength:100}" style="width: 200px;"
							onblur="autoGetKey(this);"/>
						</td>
					</tr>
					<tr>
						<th width="20%">模板代码: <span class="required">*</span>
						</th>
						<td><input type="text" id="tempCode" name="tempCode"
							value="${sysExcelTemp.tempCode}" class="inputText"
							validate="{required:true,maxlength:100}" style="width: 200px;"/>
						</td>
					</tr>
					<tr>
						<th width="20%">填写说明行高:</th>
						<td ><input type="text" id="tempDesHeight"
							name="tempDesHeight" value="${sysExcelTemp.tempDesHeight}"
							class="inputText"
							validate="{required:false,number:true,maxIntLen:4}" style="width: 200px;"/>
						</td>
					</tr>
					<tr>
						<th width="20%">模板填写说明:</th>
						<td >
							<textarea rows="5" cols="20" name="tempDes" validate="{required:false,maxlength:500}" class="inputText">${sysExcelTemp.tempDes}</textarea>
						</td>
					</tr>
					<tr>
						<th width="20%">备注:</th>
						<td >
							<textarea rows="5" cols="20" name="memo" validate="{required:false,maxlength:500}" class="inputText">${sysExcelTemp.memo}</textarea>
						</td>
					</tr>
					<c:if test="${!empty sysExcelTemp.id }"> 
						<th width="20%">上传模板样例数据:</th>
						<td>
							<c:choose>
							    <c:when test="${!empty sysExcelTemp.tempDataSample}">
							       <span style="color:red;font-weight:bold;">已上传</span>
							    </c:when>
							    <c:otherwise>
							              未上传
							    </c:otherwise>							    
						    </c:choose>	
								请选择填写好的Excel模板<input type="file" name="file" id="file" class="inputText" style="width:250px;"/>
							<a class="link import"  href="javascript:;" onclick="importTempDataSample();" style="font-weight:bold;"><span></span>导入模板数据</a>
							<a class="link export"  href="javascript:;" onclick="exportTempDataSample(this);" style="font-weight:bold;"><span></span>导出模板数据</a>
						</td>
					</c:if>
					<%-- <tr>
						<th width="20%">模板样例数据:</th>
						<td><input type="text" id="tempDataSample"
							name="tempDataSample" value="${sysExcelTemp.tempDataSample}"
							class="inputText" validate="{required:false}" />
						</td>
					</tr> --%>
				</table>
				<table class="table-grid table-list" cellpadding="1" cellspacing="1"
					id="sysExcelTempDetail" formtype="form" type="subtable">
					<tr>
						<td colspan="5">
							<div class="group" align="left">
								<a id="btnAdd" class="link add">添加</a>
							</div>
							<div align="center">Excel模板明细</div></td>
					</tr>
					<tr>
						<th>列名称</th>
						<th>数据类型</th>
						<th>列长</th>
						<th>显示顺序</th>
					</tr>
					<c:forEach items="${sysExcelTempDetailList}"
						var="sysExcelTempDetailItem" varStatus="status">
						<tr type="subdata">
							<td style="text-align: center" name="columnName">${sysExcelTempDetailItem.columnName}</td>
							<td style="text-align: center" name="columnType">
								<c:choose>
									<c:when test="${sysExcelTempDetailItem.columnType eq 0}">文本</c:when>
								</c:choose>
							</td>
							<td style="text-align: center" name="columnLen">${sysExcelTempDetailItem.columnLen}</td>
							<td style="text-align: center" name="showIndex">${sysExcelTempDetailItem.showIndex}</td>
							<input type="hidden" name="columnName"
								value="${sysExcelTempDetailItem.columnName}" />
							<input type="hidden" name="columnType"
								value="${sysExcelTempDetailItem.columnType}" />
							<input type="hidden" name="columnLen"
								value="${sysExcelTempDetailItem.columnLen}" />
							<input type="hidden" name="showIndex"
								value="${sysExcelTempDetailItem.showIndex}" />
						</tr>
					</c:forEach>
					<tr type="append">
						<td style="text-align: center" name="columnName"></td>
						<td style="text-align: center" name="columnType"></td>
						<td style="text-align: center" name="columnLen"></td>
						<td style="text-align: center" name="showIndex"></td>
						<input type="hidden" name="columnName" value="" />
						<input type="hidden" name="columnType" value="" />
						<input type="hidden" name="columnLen" value="" />
						<input type="hidden" name="showIndex" value="" />
					</tr>
				</table>
				<input type="hidden" name="id" value="${sysExcelTemp.id}" /> 
				<input type="hidden" name="saveData" id="saveData" />
			</form>
		</div>
		<form id="sysExcelTempDetailForm" style="display: none"
			title="Excel模板明细" width="400" height="250">
			<table class="table-detail" cellpadding="0" cellspacing="0"
				border="0">
				<tr>
					<th width="20%">列名称:</th>
					<td><input type="text" name="columnName" value=""
						class="inputText" validate="{required:true,maxlength:100}" />
					</td>
				</tr>
				<tr>
					<th width="20%">数据类型:</th>
					<td>
						<select name="columnType" class="inputText">
							<option value="0">文本</option>
						</select>
					</td>
				</tr>
				<tr>
					<th width="20%">列长:</th>
					<td><input type="text" name="columnLen" value=""
						class="inputText"
						validate="{required:true,number:true,maxIntLen:4 }" />
					</td>
				</tr>
				<tr>
					<th width="20%">显示顺序:</th>
					<td><input type="text" name="showIndex" value=""
						class="inputText"
						validate="{required:true,number:true,maxIntLen:4 }" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<p>
		<br/>
	</p>
	<p>
		<br/>
	</p>
	<p>
		<br/>
	</p>
</body>
</html>
