<%--
	time:2012-06-25 11:05:09
	desc:edit the 通用表单对话框
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 通用表单对话框</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmFormDialog"></script>
	<script type="text/javascript">
		var id=${bpmFormDialog.id==0};
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${bpmFormDialog.id==0}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				var alias=$("#alias").val(),
					objname=$("#objname").val(),
					settingobj=$("#settingobj").val(),
					style=$("input[name='style']:checked").val(),
					styletemp=$("#styletemp").val();
				if(alias==""){
					$.ligerDialog.warn("别名不能为空");
				}else if(/.*[\u4e00-\u9fa5]+.*$/.test(alias)){
					$.ligerDialog.warn("别名不能为中文");
				}else if(objname!=settingobj){
					$.ligerDialog.warn("当前选择的表(视图)未设置显示的列!");
				}else if(id && style!=styletemp){
					$.ligerDialog.warn("你配置的表(视图)的列类型与选择显示样式的类型不一致，请重设置列！");
				}
				else{
					$('#bpmFormDialogForm').submit();
				}
			});
			
			$("#btnSearch").click(searchObjectList);
			
			var needpage = ${bpmFormDialog.style==0} ;
			if(!needpage) {
				//树，隐藏分页TD以及合并TD
				$('#needpage').next('td').hide() ;
				$('#needpage').hide() ;
				$('#needpage').prev('td').attr('colspan',3) ;
			}
			$('#treeRadio').click(function(){
				$('#needpage').next('td').hide() ;
				$('#needpage').hide() ;
				$('#needpage').prev('td').attr('colspan',3) ;
			});
			$('#listRadio').click(function(){
				$('#needpage').show() ;
				$('#needpage').next('td').show() ;
				$('#needpage').prev('td').removeAttr('colspan') ;
			});
		});
		
		function searchObjectList(){
			
			var selList=$("#objname"); alert("selList="+selList);
			var dsName=$("#dataSource").val(); alert("dsName="+dsName);
			
			var objectname=$("#objectname").val(); alert("objectname="+objectname);
			var istable=$("#istable").val(); alert("istable="+istable);
			var url=__ctx +"/platform/form/bpmFormDialog/getByDsObjectName.ht";
			if(dsName==null || dsName==""){
				$.ligerDialog.error("请选择数据源!");
				return ;
			}
			
			
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
			var isneedPage=$("input:radio[name='needpage']:checked").val();
			if(isneedPage>0){
				$("#spanPageSize").show();
			}
			else{
				$("#spanPageSize").hide();
			}
		}
		
		
		function dialog(){
			$("#selInfo").text("");
			var id=$("#id").val();
			var istable=$("#istable").val();
			var objname=$("#objname").val();
			var dataSource=$("#dataSource").val();
			var style=$("input[name='style']:checked").val();
			
			if(id==0){
				if(objname==null){
					$("#selInfo").text("请先选择数据库表");
					return ;
				}
			}
			showSettingDialog(dataSource,objname,istable,style,id);
		}
		
		function showSettingDialog(dsName,objectname,istable,style,id){
			var settingobj=$("#settingobj").val(),
				fields={};
			
			if(settingobj==objectname){
				var displayField=$("#displayfield").val(),
					conditionField=$("#conditionfield").val(),
					sortField=$("#sortfield").val(),
					resultField=$("#resultfield").val();
				
				if(displayField)
					fields.displayField=displayField;
				if(conditionField)
					fields.conditionField=conditionField;
				if(resultField)
					fields.resultField=resultField;
				if(sortField)
					fields.sortField=sortField;
			}
			
			var url=__ctx+"/platform/form/bpmFormDialog/setting.ht?dsName=" +dsName +"&objectName=" + objectname +"&istable=" + istable +"&style=" +style +"&id=" + id;
			DialogUtil.open({
                height:560,
                width: 805,
                title : '设置列',
                url: url, 
                isResize: true,
                //自定义参数
                fields: fields,
                sucCall:function(rtn){
                	$("#settingobj").val(objectname);
           		 	$("#displayfield").val(rtn[0]);
           		 	$("#conditionfield").val(rtn[1]);
           		 	$("#resultfield").val(rtn[2]);
           		 	$("#sortfield").val(rtn[3]);
           		 	$("#styletemp").val(rtn[4]);
                }
            });
          	
		}
		function getKeyName(obj){
		    var value=$(obj).val();
		    if(!value)return false;
			Share.getPingyin({
				input:value,
				postCallback:function(data){
					var inputObj=	$("input[name='alias']");
					if(inputObj.val().length<1 || /.*[\u4e00-\u9fa5]+.*$/.test(inputObj.val())){
						inputObj.val(data.output);
					}			
				}
			});
		}
	</script>
</head>
<body>
<div class="panel">
<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">编辑通用表单对话框</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		</div>
		<div class="panel-body">
				<form id="bpmFormDialogForm" method="post" action="save.ht">
					
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">对话框名称: </th>
							<td><input type="text" id="name" name="name" value="${bpmFormDialog.name}"  class="inputText" onblur="getKeyName(this)"/></td>
							<th width="20%">对话框别名: </th>
							<td><input type="text" id="alias" name="alias" value="${bpmFormDialog.alias}"  class="inputText" /><span id="aliasInfo" style="color:red"></span></td>
						</tr>
						<tr>
							<th width="20%">显示样式： </th>
							<td colspan="3">
								<c:choose>
									<c:when test="${bpmFormDialog.id==0}">
										<input type="radio" name="style" value="0" id="listRadio" checked="checked"/>列表
										<input type="radio" name="style" value="1" id="treeRadio" />树形
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${bpmFormDialog.style==0}">列表</c:when>
											<c:otherwise >树形</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th width="20%">对话框宽度: </th>
							<td><input type="text" id="width" name="width" value="${bpmFormDialog.width}"  class="inputText"/></td>
							<th width="20%">高度: </th>
							<td><input type="text" id="height" name="height" value="${bpmFormDialog.height}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">是否单选
							 </th>
							<td>
								<label><input type="radio" name="issingle" value="1" <c:if test="${bpmFormDialog.issingle==1}">checked="checked"</c:if>>单选</label>
								<label><input type="radio" name="issingle" value="0" <c:if test="${bpmFormDialog.issingle==0}">checked="checked"</c:if>>多选</label>
								
							</td>
							<th width="20%" id="needpage">是否分页: </th>
							<td>
								<input type="radio" name="needpage" value="0"  onclick="selsize();" <c:if test="${bpmFormDialog.needpage==0}">checked="checked"</c:if> >不分页
								<input type="radio" name="needpage" value="1" onclick="selsize();" <c:if test="${bpmFormDialog.needpage==1}">checked="checked"</c:if>>分页
								<span style="color:red;<c:if test="${bpmFormDialog.needpage==0}">display:none;</c:if>" id="spanPageSize" name="spanPageSize">
									分页大小：
									  <select id="pagesize" name="pagesize" >
									  		<option value="5" <c:if test="${bpmFormDialog.pagesize==5}">selected="selected"</c:if> >5</option>
											<option value="10" <c:if test="${bpmFormDialog.pagesize==10}">selected="selected"</c:if>>10</option>
											<option value="15" <c:if test="${bpmFormDialog.pagesize==15}">selected="selected"</c:if> >15</option>
											<option value="20" <c:if test="${bpmFormDialog.pagesize==20}">selected="selected"</c:if>>20</option>
											<option value="50" <c:if test="${bpmFormDialog.pagesize==50}">selected="selected"</c:if>>50</option>						  
									  </select>
								 </span>
								
							</td>
						</tr>
						<c:if test="${bpmFormDialog.id==0}">
							<tr>
								<th width="20%">数据源: </th>
								<td>
									<select id="dataSource" name="dsalias">
										<option value="LOCAL">本地数据源 </option>
										<c:forEach items="${dsList}" var="ds">
											<option value="${ds.alias}">${ ds.name} </option>
										</c:forEach>
									</select>
								</td>
								<th width="20%">查询表(视图): </th>
								<td>
									<select name="istable" id="istable">
										<option value="1">表</option>
										<option value="0">视图</option>
									</select>
									<input type="text" name="objectname" id="objectname">
									<a href="javascript:;" id="btnSearch" class="link search">查询</a>
									
								</td>
							</tr>
						</c:if>
						<tr>
							<th width="20%">对话框字段设置: </th>
							<td colspan="3" valign="top">
								<a href="javascript:;" id="btnSetting" class="link setting" onclick="dialog()">设置列</a>
								<c:choose>
									<c:when test="${bpmFormDialog.id==0}">
										<br>
										<select id="objname" name="objname" size="10" style="width:350px;">
										</select>
										<span id="selInfo" name="selInfo" style="color:red"></span>
									</c:when>
									<c:otherwise >
										<input type="hidden"  id="objname" name="objname" value="${bpmFormDialog.objname}" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
					
					<input type="hidden" id="id" name="id" value="${bpmFormDialog.id}" />
					<input type="hidden" id="styletemp" name="styletemp" value="${bpmFormDialog.style}" />
					<input type="hidden" id="settingobj" value="${bpmFormDialog.objname}" />
					<textarea id="displayfield" name="displayfield"  style="display: none;">
						${bpmFormDialog.displayfield}
					</textarea>
					<textarea  id="conditionfield"  name="conditionfield" style="display: none;">
						${bpmFormDialog.conditionfield}
					</textarea>
					<textarea  id="resultfield"  name="resultfield" style="display: none;">
						${bpmFormDialog.resultfield}
					</textarea>
					<textarea  id="sortfield"  name="sortfield" style="display: none;">
						${bpmFormDialog.sortfield}
					</textarea>
					
				</form>
		</div>
</div>
</body>
</html>
