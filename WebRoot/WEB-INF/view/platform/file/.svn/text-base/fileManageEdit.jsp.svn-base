<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>文件上传管理</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript"
	src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>

<script type="text/javascript">
	$(function() {
		var options = {};
		if (showResponse) {
			options.success = showResponse;
		}
		$("a.save").click(function() {
			if (saveChange()) {
				$('#uploadFileForm').ajaxForm(options);
				$('#uploadFileForm').submit();
			}
		});
		// 权限
		changePermis();
	});
	// 
	function saveChange() {
		var categoryId = $("#categoryId").val();
		var allPermis = 1; // 1，表示默认所有人都有权限
		if (typeof $("#allPermis").attr("checked") == 'undefined') {
			allPermis = 0;
		}
		var fileId = $("#fileId").val();
		var userIds = $("#userIds").val();
		var userNames = $("#userNames").val();
		var posIds = $("#posIds").val();
		var posNames = $("#posNames").val();
		var roleIds = $("#roleIds").val();
		var roleNames = $("#roleNames").val();
		var remarks = $("#remarks").val();
		var fileManageJson = {
			fileId : fileId,
			categoryId : categoryId,	
			allPermis : allPermis,
			userPermis : userIds,
			userPermisName : userNames,
			postPermis : posIds,
			postpermisName : posNames,
			rolePermis : roleIds,
			rolePermisName : roleNames,
			remarks : remarks
		};
		var jsonStr = JSON2.stringify(fileManageJson);
		$("#jsonParams").val(jsonStr);
		return true;
	}

	// 选择用户
	function selectUsers() {
		UserDialog({
			isSingle : false,
			callback : function(userIds, fullnames, email, mobile, json) {
				$("#userIds").val(userIds);
				$("#userNames").val(fullnames);
			}
		});
	}
	// 选择岗位
	function selectPos() {
		PosDialog({
			isSingle : false,
			callback : function(roleIds, rolenames, json) {
				$("#posIds").val(roleIds);
				$("#posNames").val(rolenames);
			}
		});
	}
	// 选择角色
	function selectRole() {
		RoleDialog({
			isSingle : false,
			callback : function(roleIds, rolenames, json) {
				$("#roleIds").val(roleIds);
				$("#roleNames").val(rolenames);
			}
		});
	}
	// 重置用户
	function resetSelectUsers() {
		$("#userIds").val('');
		$("#userNames").val('');
	}
	// 重置岗位
	function resetselectPos() {
		$("#posIds").val('');
		$("#posNames").val('');
	}
	// 重置角色
	function resetSelectRole() {
		$("#roleIds").val('');
		$("#roleNames").val('');
	}

	// 提示信息
	function showResponse(responseText) {
		$.ligerDialog.closeWaitting();
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (obj.isSuccess()) {
			$.ligerDialog.confirm(obj.getMessage() + ",是否继续操作", "提示信息",
					function(rtn) {
						if (rtn) {
							//....
						} else {
							closeWr();
						}
					});
		} else {
			$.ligerDialog.err(obj.getMessage(), "提示信息");
		}
	}

	// 改变权限
	function changePermis() {
		if (typeof $("#allPermis").attr("checked") == 'undefined') {
			$("#userPermis").show();
			$("#rolePermis").show();
			$("#postPrmis").show();
		} else {
			$("#userPermis").hide();
			$("#rolePermis").hide();
			$("#postPrmis").hide();
		}
	}
	// 关闭窗口
	function closeWr() {
		parent.reload();
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">文件共享</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="fileFormSave" href="javascript:;"><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link close" href="javascript:;" onclick="closeWr();"><span></span>关闭</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="uploadFileForm" method="post" action="update.ht">
				<input type="hidden" name="jsonParams" id="jsonParams" /> <input
					type="hidden" name="fileId" id="fileId"
					value="${fileManage.fileId }" />
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<th width="20%">文件名：</th>
						<td>${fileManage.fileName }</td>
						<th width="20%">文件扩展名：</th>
						<td>${fileManage.ext }</td>
					</tr>
					<tr>
						<th width="20%">文件大小：</th>
						<td>${fileManage.fileSize }</td>
						<th width="20%">文件相对路径：</th>
						<td>${fileManage.filePath }</td>
					</tr>

					<tr>
						<th width="20%">创建时间：</th>
						<td><fmt:formatDate value="${fileManage.createTime }"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<th width="20%">创建者：</th>
						<td>${fileManage.creator }</td>
					</tr>
					<tr>
						<th width="20%">所有人：</th>
						<td><input type="checkbox"
							<c:if test="${fileManage.allPermis eq 1}">checked="checked" </c:if>
							id="allPermis" name="allPermis" onclick="changePermis();" /></td>
						<th width="20%">分类：</th>
						<td>
							<select id="categoryId" name="categoryId">
								<option value="">无</option>
								<c:forEach items="${globalTypeList}" var="globalType">
									<option value="${globalType.typeId}"
										<c:if test="${globalType.typeId eq fileManage.categoryId}">selected="selected"</c:if>>${globalType.typeName}</option>
								</c:forEach>
							</select></td>	
					</tr>
					<tr>
						<th width="20%">备注：</th>
						<td colspan="3"><textarea id="remarks" name="remarks"
								cols="40" rows="3">${fileManage.remarks }</textarea></td>
					</tr>
					<tr id="userPermis">
						<th width="20%">用户授权：</th>
						<td colspan="3"><input type="hidden" id="userIds" name="userIds"
							value="${fileManage.userPermis }"> <textarea
								readonly="readonly" id="userNames" name="userNames" cols="40"
								rows="3">${fileManage.userPermisName }</textarea>
							<span class="bottom" style="margin-top:10px;">
								<a href="javascript:;" class="button"  onclick="selectUsers()" style="margin-right:10px;" ><span class="icon ok"></span><span >选择</span></a>
								<a href="javascript:;" class="button"  onclick="resetSelectUsers()"><span class="icon cancel" ></span><span class="chosen" >清空</span></a>
							</span>
						</td>
					</tr>
					<tr id="rolePermis">
						<th width="20%">岗位授权：</th>
						<td colspan="3"><input type="hidden" id="posIds" name="posIds"
							value="${fileManage.postPermis }"> <textarea
								readonly="readonly" id="posNames" name="posNames" cols="40"
								rows="3">${fileManage.postpermisName }</textarea>
							<span class="bottom" style="margin-top:10px;">
								<a href="javascript:;" class="button"  onclick="selectPos()" style="margin-right:10px;" ><span class="icon ok"></span><span >选择</span></a>
								<a href="javascript:;" class="button"  onclick="resetselectPos()"><span class="icon cancel" ></span><span class="chosen" >清空</span></a>
							</span>	
						</td>
					</tr>
					<tr id="postPrmis">
						<th width="20%">角色授权：</th>
						<td colspan="3"><input type="hidden" id="roleIds" name="roleIds"
							value="${fileManage.rolePermis }"> <textarea
								readonly="readonly" id="roleNames" name="roleNames" cols="40"
								rows="3">${fileManage.rolePermisName }</textarea>
							<span class="bottom" style="margin-top:10px;">
								<a href="javascript:;" class="button"  onclick="selectRole()" style="margin-right:10px;" ><span class="icon ok"></span><span >选择</span></a>
								<a href="javascript:;" class="button"  onclick="resetSelectRole()"><span class="icon cancel" ></span><span class="chosen" >清空</span></a>
							</span>	
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
