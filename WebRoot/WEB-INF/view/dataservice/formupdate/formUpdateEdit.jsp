<%--
	time:2012-11-27 10:37:13
	desc:edit the 通用表单查询
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 通用表单查询</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript">
		$(function() {
			alert("function");
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#bpmFormQueryForm').form();
			$("a.save").click(function() {
				var alias=$("#alias").val();
				if(alias==""){
					$.ligerDialog.warn("别名不能为空");
				}else if(/.*[\u4e00-\u9fa5]+.*$/.test(alias)){
					$.ligerDialog.warn("别名不能为中文");
				}else{
					frm.setData();
					frm.ajaxForm(options);
					if(frm.valid()){
						form.submit();
					}
				}
			});
			$("#btnSearch").click(searchObjectList);
		});
		
		function searchObjectList(){
			
			alert("searchObjectList");
			var selList=$("#objname");
			var dsName=$("#dataSource").val();
			var objectname=$("#objectname").val();
			var istable=$("#istable").val();
			var url=__ctx +"/platform/form/bpmFormDialog/getByDsObjectName.ht";
		
			$.ligerDialog.waitting('正在查询，请等待...');
			$.post(url, { dsName:dsName, objectName: objectname,istable:istable },function(data) {
				$.ligerDialog.closeWaitting();
				selList.empty();
				var success=data.success;
				if(success=='false'){
					$.ligerDialog.error("出错了!");
					return;
				}
				//表的处理
				if(istable=="1"){
					var tables=data.tables;
					for(key in tables ){
						selList.append("<option title='"+tables[key]+"' value='"+ key+"'>"+ key +"("+tables[key] +")" +"</option>" );
					}
				}
				//视图的处理
				else{
					var aryView=data.views;
					for(var i=0;i<aryView.length;i++){
						var v=aryView[i];
						selList.append("<option value='"+ v+"'>"+v+"</option>" );
					}
				}
		    });
		}
		
		function selsize(){
			alert("selsize");
			var isneedPage=$("input:radio[name='needpage']:checked").val();
			if(isneedPage>0){
				$("#spanPageSize").show();
			}
			else{
				$("#spanPageSize").hide();
			}
		}
		
		function showResponse(responseText) {
			alert("showResponse");
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm( obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "${ctx}/dataservice/formupdate/formUpdate/list.ht";
					}
				});
			} else {
				$.ligerDialog.err("出错信息","编辑通用表单失败",obj.getMessage());
			}
		}
		
		function dialog(){
			alert("dialog");	
			$("#selInfo").text("");
			var id=$("#id").val();
			var istable=$("#istable").val();
			var objname=$("#objname").val();
			var dataSource=$("#dataSource").val();
			
			if(id==0){
				if(objname==null){
					$("#selInfo").text("请先选择数据库表");
					return ;
				}
			}
			showSettingDialog(dataSource,objname,istable,id);
		}
		
		function showSettingDialog(dsName,objectname,istable,id){
			alert("showSettingDialog");
			var settingobj=$("#settingobj").val(),
				fields={};
			
			if(settingobj==objectname){
				var conditionField=$("#conditionfield").val(),
					//alert(conditionField);
					updateField=$("#updatefield").val(),
					//alert(updateField);
					sortField=$("#sortfield").val();
					//alert(sortField);
				
				if(conditionField)
					fields.conditionField=conditionField;
				if(updateField)
					fields.updateField = updateField;
				if(sortField)
					fields.sortField=sortField;
			}
			
			var url=__ctx+"/dataservice/formupdate/formUpdate/setting.ht?dsName=" +dsName +"&objectName=" + objectname + "&istable=" + istable +"&id=" + id;
			url=url.getNewUrl();
			DialogUtil.open({
                height:600,
                width: 800,
                title : '设置列',
                url: url, 
                isResize: true,
                //自定义参数
                fields: fields,
                sucCall:function(rtn){
                	 $("#settingobj").val(objectname);
                	 //alert($("#settingobj").val(objectname));
               		 $("#conditionfield").val(rtn[1]);
               		// alert($("#conditionfield").val(rtn[1]));
               		 $("#updatefield").val(rtn[2]); 
               		 //alert($("#updatefield").val(rtn[2]););
               		 $("#sortfield").val(rtn[3]); 
               		// alert($("#sortfield").val(rtn[3]));
               		 $("#name").focus();
                }
            });
		}
		function getKeyName(obj){
			alert("getKeyName");
		    var value=$(obj).val();
		    if(!value)return false;
			Share.getPingyin({
				input:value,
				postCallback:function(data){
					var inputObj=	$("input[name='alias']");
					//当alias为空或者为中文时
					if(inputObj.val().length<1 || /.*[\u4e00-\u9fa5]+.*$/.test(inputObj.val())){
						inputObj.val(data.output);
					}
				}
			});
		}
		
		function formUpdateReturn(){
			alert("formUpdateReturn");
			var defId = $("#defId").val();
			window.location.href = "${ctx}/dataservice/formupdate/formUpdate/list.ht";
			//window.location.href = "${ctx}/platform/bpm/bpmDefinition/design.ht?defId="+defId;
		}
		
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${formUpdate.id !=null}">
			        <span class="tbar-label">编辑通用表单查询</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加通用表单查询</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<c:if test="${canReturn==0}">
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="#" onclick="formUpdateReturn()"><span></span>返回</a></div>
				<%-- 
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				--%>
			</div>
		</div>
		</c:if>
	</div>
	<div class="panel-body">
		<form id="bpmFormQueryForm" method="post" action="save.ht">
		<input type="text" id="defId" name="defId" value="<%=request.getParameter("defId") %>" />
		<input type="te" name="nodeId" value="<%=request.getParameter("nodeId") %>"/>
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						
						<tr>
							<th width="20%">更新名称: </th>
							<td><input type="text" id="name" name="name" value="${formUpdate.name}"  class="inputText" onblur="getKeyName(this)"/></td>
							<th width="20%">更新别名: </th>
							<td><input type="text" id="alias" name="alias" value="${formUpdate.alias}"  class="inputText"/><span id="aliasInfo" style="color:red"></span></td>
						</tr>
						<c:if test="${formUpdate.id==null}">
							<tr>
								<th width="20%">数据源: </th>
								<td>
								
									<select id="dataSource" name="dsalias">
										<option value="LOCAL">本地数据源 </option>
										<c:forEach items="${dsList}" var="ds">
											<option value="${ds.alias}">${ ds.name}</option>
										</c:forEach>
									</select>
									
								</td>
								<th width="20%">更新表(视图): </th>
								<td>
									<select name="isTable" id="istable">
										<option value="1">表</option>
										<option value="0">视图</option>
									</select>
									
									<input type="text" name="objectname" id="objectname">
									<a href="javascript:;" id="btnSearch" class="link search">查询</a>
								</td>
							</tr>
						</c:if>
						<tr>
							<th width="20%">更新字段设置: </th>
							<td colspan="3" valign="top">
								<a href="javascript:;" id="btnSetting" class="link setting" onclick="dialog()">设置列</a>
								<c:choose>
									<c:when test="${formUpdate.id==null}">
										<br>
										<select id="objname" name="objName" size="10" style="width:350px;">
										</select>
										<span id="selInfo" name="selInfo" style="color:red"></span>
									</c:when>
									<c:otherwise >
										<input type="hidden"  id="objname" name="objName" value="${formUpdate.objName}" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
			
			<input type="hidden" id="id" name="id" value="${formUpdate.id}" />
			<input type="hidden" id="settingobj" value="${formUpdate.objName}" />
			<textarea  id="conditionfield"  name="conditionfield" style="display: none;" >
				${formUpdate.conditionfield}
			</textarea>
			<textarea  id="updatefield"  name="updatefield" style="display: none;">
				${formUpdate.updatefield}
			</textarea>	
			<%-- 		
			<textarea  id="sortfield"  name="sortfield" >
				${formUpdate.sortfield}
			</textarea>
			--%>	
		</form>
		
	</div>
</div>
</body>
</html>
