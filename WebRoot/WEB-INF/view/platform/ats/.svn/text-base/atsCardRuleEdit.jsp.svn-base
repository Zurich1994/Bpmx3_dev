<%--
	time:2015-05-18 16:16:16
	desc:edit the 取卡规则
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 取卡规则</title>
	<%@include file="/commons/include/form.jsp" %>
<f:link href="listEdit.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsCardRuleForm").attr("action","save.ht");
				submitForm();
			});
			changeSegmentNum();
			setSegFirAssignSegmentShow();
			$(".toggle").click();
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsCardRuleForm').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = window.location.href;
					}else{
						window.location.href = "${ctx}/platform/ats/atsCardRule/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		function changeSegmentNum(){
			var val = $("#segmentNum").val();
			if(val == 1){
				$('#segFirAssign').hide();
				$('#segSecAssign').hide();
			}else if(val == 2){
				$('#segFirAssign').show();
				$('#segSecAssign').hide();
			}else if(val == 3){
				$('#segFirAssign').show();
				$('#segSecAssign').show();
			}	
		}
		function setSegFirAssignSegmentShow(i){
			function a(){
				if($("#segFirAssignType").val()==2){
					$(".segFirAssignSegment").hide();
				}else{
					$(".segFirAssignSegment").show();
				}
			}
			function b(){
				if($("#segSecAssignType").val()==2){
					$(".segSecAssignSegment").hide();
				}else{
					$(".segSecAssignSegment").show();
				}
			}
			if(i == 0 ){
				a();
			}else if ( i == 1){
				b();
			}else{
				a();
				b();
			}
			
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${atsCardRule.id !=null}">
			        <span class="tbar-label"><span></span>编辑取卡规则</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加取卡规则</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javaScript:void(0)"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="atsCardRuleForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%">编码: </th>
					<td><input type="text" id="code" name="code" value="${atsCardRule.code}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
					<th width="20%">名称: </th>
					<td><input type="text" id="name" name="name" value="${atsCardRule.name}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
				</tr>
				<tr>
					<th width="20%">上班取卡提前(小时): </th>
					<td><input type="text" id="startNum" name="startNum" value="${atsCardRule.startNum}"  class="inputText" validate="{required:true,number:true,maxIntLen:10}"  /></td>
						<th width="20%">下班取卡延后(小时): </th>
					<td><input type="text" id="endNum" name="endNum" value="${atsCardRule.endNum}"  class="inputText" validate="{required:true,number:true,maxIntLen:10}"  /></td>
			
				</tr>
				<tr>
					<th width="20%">最短取卡间隔(分钟）: </th>
					<td><input type="text" id="timeInterval" name="timeInterval" value="${atsCardRule.timeInterval}"  class="inputText" validate="{required:true,number:true,maxIntLen:10}"  /></td>
					<th width="20%">适用段次: </th>
					<td>
						<select id="segmentNum" name="segmentNum" onchange="changeSegmentNum(this)">
							<option value="1" <c:if test="${atsCardRule.segmentNum == 1}">selected="selected"</c:if>>一段</option>
							<option value="2" <c:if test="${atsCardRule.segmentNum == 2}">selected="selected"</c:if>>二段</option>
							<option value="3" <c:if test="${atsCardRule.segmentNum == 3}">selected="selected"</c:if>>三段</option>
						</select>
					</td>
				</tr>
				<tr>
					<th width="20%">是否默认: </th>
					<td colspan="3">
						<input type="checkbox" id="isDefault" name="isDefault" value="1"   <c:if test="${atsCardRule.isDefault ==1}"> checked="checked"</c:if>  />
					</td>
				</tr>
			</table>
			<fieldset>
				<legend>上班取卡规则</legend>
				<fieldset>
					<legend>上班第一次取卡</legend>
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">取卡范围开始时数: </th>
							<td><input type="text" id="segBefFirStartNum" name="segBefFirStartNum" value="${atsCardRule.segBefFirStartNum}"  class="inputText" validate="{number:true,maxIntLen:10}"  /></td>
							<th width="20%">取卡范围结束时数: </th>
							<td><input type="text" id="segBefFirEndNum" name="segBefFirEndNum" value="${atsCardRule.segBefFirEndNum}"  class="inputText" validate="{number:true,maxIntLen:10}"  /></td>
					
						</tr>
						<tr>
							<th width="20%">取卡方式: </th>
							<td colspan="3">
								<select id="segBefFirTakeCardType"  name="segBefFirTakeCardType" >
									<option value="1" <c:if test="${atsCardRule.segBefFirTakeCardType == 1}">selected="selected"</c:if>>该段最早卡</option>
									<option value="2" <c:if test="${atsCardRule.segBefFirTakeCardType == 2}">selected="selected"</c:if>>该段最晚卡</option>
								</select>
							</td>
						</tr>
					</table>
				</fieldset>
				<a href="javascript:void(0);" onclick="$(this).next().toggle();$(this).text($(this).text()=='收起'?'展开':'收起')" class="toggle">收起</a>
				<fieldset>
					<legend >上班第二次取卡</legend>
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0" >	
							<tr>
								<th width="20%">取卡范围开始时数: </th>
								<td><input type="text" id="segBefSecStartNum" name="segBefSecStartNum" value="${atsCardRule.segBefSecStartNum}"  class="inputText" validate="{number:true,maxIntLen:10}"  /></td>
								<th width="20%">取卡范围结束时数: </th>
								<td ><input type="text" id="segBefSecEndNum" name="segBefSecEndNum" value="${atsCardRule.segBefSecEndNum}"  class="inputText" validate="{number:true,maxIntLen:10}"  /></td>
				
							</tr>
							<tr>
								<th width="20%">取卡方式: </th>
								<td colspan="3">
									<select id="segBefSecTakeCardType"  name="segBefSecTakeCardType" >
										<option value="1" <c:if test="${atsCardRule.segBefSecTakeCardType == 1}">selected="selected"</c:if>>该段最早卡</option>
										<option value="2" <c:if test="${atsCardRule.segBefSecTakeCardType == 2}">selected="selected"</c:if>>该段最晚卡</option>
									</select>
								</td>
							</tr>
						</table>
				</fieldset>	
			</fieldset>
			
			<!-- 上班end -->
			<!-- 下班start-->
			<fieldset>
				<legend>下班取卡规则</legend>
					<fieldset>
						<legend>下班第一次取卡</legend>
							<table class="table-detail" cellpadding="0" cellspacing="0" border="0">	
								<tr>
									<th width="20%">取卡范围开始时数: </th>
									<td><input type="text" id="segAftFirStartNum" name="segAftFirStartNum" value="${atsCardRule.segAftFirStartNum}"  class="inputText" validate="{number:true,maxIntLen:10}"  /></td>
									<th width="20%">取卡范围结束时数: </th>
									<td ><input type="text" id="segAftFirEndNum" name="segAftFirEndNum" value="${atsCardRule.segAftFirEndNum}"  class="inputText" validate="{number:true,maxIntLen:10}"  /></td>
						
								</tr>
								<tr>
									<th width="20%">取卡方式: </th>
									<td colspan="3">
										<select id="segAftFirTakeCardType"  name="segAftFirTakeCardType" >
											<option value="1" <c:if test="${atsCardRule.segAftFirTakeCardType == 1}">selected="selected"</c:if>>该段最早卡</option>
											<option value="2" <c:if test="${atsCardRule.segAftFirTakeCardType == 2}">selected="selected"</c:if>>该段最晚卡</option>
										</select>
									</td>
								</tr>
							</table>
					</fieldset>
					<a href="javascript:void(0);" onclick="$(this).next().toggle();$(this).text($(this).text()=='收起'?'展开':'收起')" class="toggle">收起</a>
					<fieldset>
						<legend>下班第二次取卡</legend>
							<table class="table-detail" cellpadding="0" cellspacing="0" border="0">		
								<tr>
									<th width="20%">取卡范围开始时数: </th>
									<td><input type="text" id="segAftSecStartNum" name="segAftSecStartNum" value="${atsCardRule.segAftSecStartNum}"  class="inputText" validate="{number:true,maxIntLen:10}"  /></td>
									<th width="20%">取卡范围结束时数: </th>
									<td><input type="text" id="segAftSecEndNum" name="segAftSecEndNum" value="${atsCardRule.segAftSecEndNum}"  class="inputText" validate="{number:true,maxIntLen:10}"  /></td>
								</tr>
								<tr>
									<th width="20%">取卡方式: </th>
									<td colspan="3">
										<select id="segAftSecTakeCardType"  name="segAftSecTakeCardType" >
											<option value="1" <c:if test="${atsCardRule.segAftSecTakeCardType == 1}">selected="selected"</c:if>>该段最早卡</option>
											<option value="2" <c:if test="${atsCardRule.segAftSecTakeCardType == 2}">selected="selected"</c:if>>该段最晚卡</option>
										</select>
									</td>
								</tr>
							</table>
					</fieldset>
			</fieldset>
			<fieldset id="segFirAssign" style="display: none">
				<legend>第一段间</legend>
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">	
					<tr>
						<th width="20%">分配类型: </th>
						<td>
							<select id="segFirAssignType"  name="segFirAssignType" onchange="setSegFirAssignSegmentShow(0)">
								<option value=""></option>
								<option value="1" <c:if test="${atsCardRule.segFirAssignType == 1}">selected="selected"</c:if>>手工分配</option>
								<option value="2" <c:if test="${atsCardRule.segFirAssignType == 2}">selected="selected"</c:if>>最近打卡点</option>
							</select>
						</td>
						<th width="20%" class="segFirAssignSegment">分配段次: </th>
						<td  class="segFirAssignSegment">
							<select id="segFirAssignSegment"  name="segFirAssignSegment" validate="{required:true,maxlength:384}" >
								<option value="1" <c:if test="${atsCardRule.segFirAssignSegment == 1}">selected="selected"</c:if>>第一段下班</option>
								<option value="2" <c:if test="${atsCardRule.segFirAssignSegment == 2}">selected="selected"</c:if>>第二段上班</option>
							</select>
						</td>
			
					</tr>
				</table>
			</fieldset>
			<fieldset id="segSecAssign" style="display: none">
				<legend>第二段间</legend>
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">	
					<tr>
						<th width="20%">分配类型: </th>
						<td>
							<select id="segSecAssignType"  name="segSecAssignType" onchange="setSegFirAssignSegmentShow(1)">
								<option value=""></option>
								<option value="1" <c:if test="${atsCardRule.segSecAssignType == 1}">selected="selected"</c:if>>手工分配</option>
								<option value="2" <c:if test="${atsCardRule.segSecAssignType == 2}">selected="selected"</c:if>>最近打卡点</option>
							</select>
						</td>
						<th width="20%" class="segSecAssignSegment">分配段次: </th>
						<td class="segSecAssignSegment">
							<select id="segSecAssignSegment"  name="segSecAssignSegment" validate="{required:true,maxlength:384}">
								<option value=""></option>
								<option value="1" <c:if test="${atsCardRule.segSecAssignSegment == 1}">selected="selected"</c:if>>第二段下班</option>
								<option value="2" <c:if test="${atsCardRule.segSecAssignSegment == 2}">selected="selected"</c:if>>第三段上班</option>
							</select>
						</td>
					</tr>
				</table>
			</fieldset>
			<input type="hidden" name="id" value="${atsCardRule.id}" />
		</form>
		
	</div>
</div>
</body>
</html>
