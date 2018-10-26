
<%--
	time:2011-11-16 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>表单子表数据授权</title>
<%@include file="/commons/include/form.jsp"%>
<f:link href="tree/zTreeStyle.css"></f:link>
<f:link href="codemirror/lib/codemirror.css"></f:link>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/SubRightsTree.js"></script>

<%-- <link  rel="stylesheet" type="text/css" href="${ctx}/js/codemirror/lib/codemirror.css" > --%>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js" ></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=BpmSubtableRights"></script>



<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

	var nodeId="${nodeId}";
	var actDefId="${actDefId}";
	var formKey="${formKey}";
	var tableId="${tableId}";
	
	$(function() {
		
		$('input[name="permissiontype"]').click(function(){
			changeUserScript(this.value);
		});

		changeUserScript($('input[name="permissiontype"]:checked').val());

		$("a.save").click(function() {

			if($('#subName').text()==''){
				$.ligerDialog.warn("请选取子表配置权限",'提示信息');
				return;
			}
			
			InitMirror.editor.save();
			
			var frm=$('#subAuthorityForm');
			var permissiontype = $('input[name="permissiontype"]:checked').val();
			if(permissiontype==2&&!frm.valid()) return ;
			var actdefid = $('#actdefid').val();
			var nodeid = $('#nodeid').val();
			var tableid = $('#tableid').val();
			var parentActDefId = $("#parentActDefId").val();
			var permissionseting = permissiontype==2?$('#permissionseting').val().trim():'';
		
			
			var url=__ctx+ "/platform/bpm/BpmSubtableRights/save.ht";
			
			 var data={};
			 data.actdefid=actdefid;
			 data.nodeid=nodeid;
			 data.tableid=tableid;
			 data.permissiontype=permissiontype;
			 data.permissionseting=permissionseting;
			 data.parentActDefId = parentActDefId;
			 
						
			$.post(url,data,function(res){
				var obj=eval("("+ res+")");
				if(obj.result==1){
					var subTree = $.fn.zTree.getZTreeObj("subTree");
					var node = subTree.getSelectedNodes()[0];
					changeShow(subTree,node,0);
					onClick(node.id,node);
					$.ligerDialog.success(obj.message,'提示信息');
				}else{
					$.ligerDialog.error(obj.message,'提示信息');
				}
			});
			
		});
		
		$("a.del").click(function() {
			if($('#rightid').val()!=0){
				var actdefid = $('#actdefid').val();
				var nodeid = $('#nodeid').val();
				var tableid = $('#tableid').val();
				
				 var url=__ctx+ "/platform/bpm/BpmSubtableRights/del.ht";
				
				 var data={};
				 data.id = $('#rightid').val();
				 data.actdefid=actdefid;
				 data.nodeid=nodeid;
				 data.tableid=tableid;
							
				$.post(url,data,function(res){
					var obj=eval("("+ res+")");
					if(obj.result==1){
						var subTree = $.fn.zTree.getZTreeObj("subTree");
						var node = subTree.getSelectedNodes()[0];
						changeShow(subTree,node,1);
						onClick(node.id,node);						
						$.ligerDialog.success(obj.message,'提示信息');
					}else{
						$.ligerDialog.error(obj.message,'提示信息');
					}
				});
			}else{
				$.ligerDialog.warn('权限不存在不能删除','提示信息');
			}
		});
		
		$("#btnScript").click(selectScript);
		
		$("a.close").click(function() {
			this.dialog("close");
		});
		
	});
	
	
	
	
	// 选择：当前人员，当前组织，脚本权限
	var changeUserScript = function(value){
		if(value==0||value==1){
			$('#scriptAuthority').hide();
		}
		if(value==2){
			$('#scriptAuthority').show();
		}
	};
	
	// 常用脚本回填
	function selectScript(){
		ScriptDialog({callback:function(script){
			InitMirror.editor.insertCode(script);
		}});
	}
	
</script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/SubRights.js"></script>

</head>
<body >
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">子表数据授权</span>
		</div>
	</div>
	<div id="layout" class="panel-body">
		<div position="left" title="子表"
				style="overflow: auto; float: left; width: 100%; height: 100%;">
			<div id="subLayout">
					<ul id="subTree" class="ztree" style="overflow: auto;"></ul>
			</div>
		</div>
		<div position="center" title="子表授权设置" style="overflow: auto;">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
						<a class="link del" id="dataFormDel" href="javascript:;"><span></span>删除权限</a>
						<a class="link cancel" id="dataFormClose" href="javascript:;" onclick="dialog.close()"><span></span>关闭</a>
					</div>
				</div>
			</div>
			<form id="subAuthorityForm" method="post" action="${ctx}/platform/bpm/BpmSubtableRights/save.ht" >
				
				<input type="hidden" id="rightid" name="id" value="0">
				<input type="hidden" id="actdefid" name="actdefid" value="${actDefId}">
				<input type="hidden" id="nodeid" name="nodeid" value="${nodeId}">
				<input type="hidden" id="mainTableid" name="mainTableid" value="${tableId}">
				<input type="hidden" id="tableid" name="tableid" value="0">
				<input type="hidden" id="parentActDefId" name="parentActDefId" value="${parentActDefId}">
				
				<div class="header">
					<div class="toolBar">
						<span class="difcolor" style="float: left;margin-left: 10px">条件类型：</span>
						<span class="difcolor" style="float: left;margin-left: 10px">
							<label for="condition-user">当前人员</label>
							<input id="condition-user" value="0" type="radio" name="permissiontype" checked="checked">
						</span>
						<span class="difcolor" style="float: left;margin-left: 20px">
							<label for="condition-org">当前组织</label>
							<input id="condition-org" value="1" type="radio" name="permissiontype" >
						</span>
						<span class="difcolor" style="float: left;margin-left: 20px">
							<label for="condition-script">脚本权限</label>
							<input id="condition-script" value="2" type="radio" name="permissiontype" >
						</span>
					</div>
				</div>
				
				<div class="panel-body">
					
					<div style="margin-top:5px;margin-bottom:5px;">
						<font color="red">单击子表父节点选取操作子表；</font>
					</div>
					
					<table border="0">
						<tr>
							<td><div style="margin-top:5px;margin-bottom:5px;">当前操作子表为：</div></td>
							<td><div id="subName" style="margin-top:5px;margin-bottom:5px;"></div></td>
						</tr>
					</table>

					<div id="scriptAuthority">
						<div class="condition-script-div-script">
							<table class="table-detail">
								<tr>
									<th width="70px" valign="top">
										<!--<div class="condition-script-div-script-operate">
											<a class="link next" onclick="validateConditionScript(this)">验证脚本</a>
										</div>-->
										<div style="margin-top: 80px">条件脚本</div>
									</th>
									<td>
										<div style="margin:8px 0;">
											<a href="javascript:;" id="btnScript" class="link var" title="常用脚本">常用脚本</a>
											<font color="red">双击选取子表字段编写脚本；</font>
											<div class="tipbox">
												<a href="javascript:;" class="tipinfo">
													<span>
														例1：<p>String sql="and a.f_spmc='"+scriptImpl.getCurrentName()+"'";</p>
														<p>return sql;</p>
														例2：<p>String sql="or a.f_spmc='"+scriptImpl.getCurrentName()+"'";</p>
														<p>return sql;</p>
													</span>
												</a>
											</div>
										</div>
										<textarea id="permissionseting" validate="{required:true,maxLength:2000}" codemirror="true" rows="12" cols="55" name="permissionseting" >return sql;</textarea>
									</td>
								</tr>
							</table>
						</div>
				 	</div>
				</div>
				
			</form>
		</div>
	</div>
</body>
</html>

