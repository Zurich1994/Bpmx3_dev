<%--
	time:2011-12-05 17:00:54
	desc:edit the 子系统资源
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑子系统资源</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js" ></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/IconDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=resources"></script>
	<script type="text/javascript">
		var i=100;
		var ctx="${ctx}";
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			}
			
			if(${resources.alias==null||resources.alias==""}){
				valid(showRequest,showResponse,1);
				$("#resName").blur(function(){
					var obj=$(this);
					autoPingin(obj);
				});
			}else{
				valid(showRequest,showResponse);
			}
			
			$("a.save").click(function() {
				$('#resourcesForm').submit(); 
			});
		});
		
		function showResponse(responseText){
			var json=eval("("+responseText+")");
			if(json.result==1){
				var resName=$("#resName").val();
				var isFolder=$("#isFolder").val();
				var icon=$("#icon").val();
				if(json.operate=='add'){
					parent.addResource(json.resId,resName,icon,isFolder);
					$.ligerDialog.confirm('添加节点成功,继续添加吗?','提示信息',function(rtn){
						if(rtn){
							$("#resName,#alias,#defaultUrl").val("");
						}
						else{
							location.href=ctx +"/platform/system/resources/get.ht?resId="+ json.resId;
						}
					});
				}
				else{
					parent.editResource(resName,icon,isFolder);
					$.ligerDialog.success('编辑节点成功!','提示信息');
				}
			}
			else{
				$.ligerDialog.err('出错信息',"编辑节点失败",json.message);
			}
		}
		
		
		
		function selectIcon(){
			 IconDialog({callback:function(src){
				$("#icon").val(src);
				$("#iconImg").attr("src",src);
				$("#iconImg").show();
			}});
		};
		function add(){
			var aryTr=[
			'<tr>',
			'<td style="text-align: center;">',
			'<input   name="chDefaultUrl" type="radio"   onclick="setDefaultUrl(this);">', 
			'</td>',
			'<td style="text-align: center;">',
			'<input class="inputText" type="text" name="name"	style="width: 95%;" >',
			'</td>',
			'<td style="text-align: center;">',
			'<input class="inputText" type="text" name="url"	style="width: 95%;" >',
			'</td>',
			'<td style="text-align:center;">',
			'<a href="javascript:;" class="link del" onclick="singleDell(this);">删除</a>',
			'</td>',
			'</tr>'];
			
			$("#resourcesUrlItem").append(aryTr.join(""));
			$("#notSetURL").remove();
		};
		function checkDell(){
			var trCheckeds=$("#resourcesUrlItem").find(":checkbox[name='resUrlId'][checked]");
			$.each(trCheckeds,function(i,c){
				var tr=$(c).parents('tr');
				$(tr).remove();
			});
		};
		function singleDell(obj){
			var tr=$(obj).parents('tr');
			$(tr).remove();
		};
		function setDefaultUrl(obj){
			var tr=$(obj).parents('tr');
			$("#defaultUrl").val( tr.find(":input[name='url']").val());
		};
				
		function autoPingin(obj){
			var value=obj.val();
			Share.getPingyin({
				input:value,
				postCallback:function(data){
					$("#alias").val(data.output);
				}
			});
		}
		
	</script>
</head>
<body>
<form id="resourcesForm" method="post" action="save.ht">
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">
				<c:if test="${resources.resId==null }">添加子系统资源</c:if>
				<c:if test="${resources.resId!=null }">编辑子系统资源</c:if> 
				</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<table id="resourcesTable" class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">资源名称:  <span class="required">*</span></th>
							<td><input type="text" id="resName" name="resName" value="${resources.resName}"  class="inputText longInputText"/></td>
						</tr>
						<tr>
							<th width="20%">资源别名: </th>
							<td><input type="text" id="alias" name="alias" value="${resources.alias}"  class="inputText longInputText"/></td>
						</tr>
						
						<tr>
							<th width="20%">资源图标: </th>
							<td>
								<input type="hidden" id="icon" name="icon" value="${resources.icon}"  class="inputText"/>
								<img id="iconImg" alt="" src="${resources.icon}" <c:if test="${resources.icon==null}">style="display:none;"</c:if>>
								<a class="link detail" href="javascript:selectIcon();">选择</a>
							</td>
						</tr>
						<tr>
							<th width="20%">默认地址: </th>
							<td><input type="text" id="defaultUrl" name="defaultUrl" style="width:400px" value="${resources.defaultUrl}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">是否有子节点: </th>
							<td>
								<select id="isFolder" name="isFolder">
									<option value="0" <c:if test="${resources.isFolder==0}">selected="selected"</c:if>>否</option>
									<option value="1" <c:if test="${resources.isFolder==1}">selected="selected"</c:if>>是</option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%">显示到菜单: </th>
							<td>
								<select id="isDisplayInMenu" name="isDisplayInMenu">
									<option value="0" <c:if test="${resources.isDisplayInMenu==0}">selected="selected"</c:if>>否</option>
									<option value="1" <c:if test="${resources.isDisplayInMenu==1}">selected="selected"</c:if>>是</option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%">默认打开: </th>
							<td>
								<select id="isOpen" name="isOpen">
									<option value="0" <c:if test="${resources.isOpen==0}">selected="selected"</c:if>>否</option>
									<option value="1" <c:if test="${resources.isOpen==1}">selected="selected"</c:if>>是</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<th width="20%">是否打开新窗口: </th>
							<td>
								<select id="isNewOpen" name="isNewOpen">
									<option value="0" <c:if test="${resources.isNewOpen==0}">selected="selected"</c:if>>否</option>
									<option value="1" <c:if test="${resources.isNewOpen==1}">selected="selected"</c:if>>是</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<th width="20%">同级排序: </th>
							<td><input type="text" id="sn" name="sn" value="${resources.sn}"  class="inputText"/></td>
						</tr>
						
						
						<tr style="display: none;">
							<th width="20%">父ID: </th>
							<td><input type="text" id="parentId" name="parentId" value="${resources.parentId}"  class="inputText"/></td>
						</tr>
						<tr style="display: none;">
							<th width="20%">systemId: </th>
							<td><input type="text" id="systemId" name="systemId" value="${resources.systemId}"  class="inputText"/></td>
						</tr>
							
				</table>
					<input type="hidden" id="resId" name="resId" value="${resources.resId}" />
					<input type="hidden" id="returnUrl" value="${returnUrl}" />
					
		</div>
					
		<c:if test="${resources.isFolder==0 && resources.resId!=null}">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">资源URL</span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a onclick="add();" class="link add"><span></span>添加</a></div>
					</div>	
				</div>
			</div>
							
			<div class="panel-body">
			
					<table id="resourcesUrlItem" class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">
				   		<tr>
				   			<th width="10%">默认URL</th>
				   			<th width="30%">名称</th>
				    		<th width="50%">URL</th>
				    	
				    		<th width="10%" style="text-align: center;">管理</th>
				    	</tr>
				    	<tbody>
					    	<c:forEach items="${resourcesUrlList}" var="resourcesUrlItem">
					    		<tr>
					    			<td style="text-align: center;">
					    				<input name="chDefaultUrl" type="radio"  <c:if test="${resourcesUrlItem.url==resources.defaultUrl}">checked="checked"</c:if> onclick="setDefaultUrl(this);"> 
					    			</td>
						    		<td style="text-align: center;">
					    				<input class="inputText" type="text" name="name"	style="width: 95%;" value="${resourcesUrlItem.name}">
					    			</td>
					    			<td style="text-align: center;">
					    				<input class="inputText" type="text" name="url"	style="width: 95%;" value="${resourcesUrlItem.url}" >
					    			</td>
					    			<td style="text-align: center;">
					    				<a href="javascript:;" class="link del" onclick="singleDell(this);">删除</a>
									</td>
					    		</tr>
					    	</c:forEach>
				    	</tbody>
				   	 </table>
				   	 <c:if test="${resourcesUrlList=='[]'}">
				   	 	<div id="notSetURL"  width="90%">
					    	当前没有设置资源URL
					    </div>
					 </c:if>
					
			</div>
		</c:if>
					
		
</div>
</form>
</body>
</html>
