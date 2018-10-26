<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>文件上传管理</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript"
	src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<link href="${ctx}/styles/swfupload/form.upload.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="${ctx}/js/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${ctx}/js/swfupload/fileprogress.js"></script>
<script type="text/javascript" src="${ctx}/js/swfupload/handlers.form.upload.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/OfficeControl.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/OfficePlugin.js"></script>

<script type="text/javascript">
	
	$(function() {
		$("a.upload").click(function() {
			var form = $('#uploadFileForm');
			if (!form.valid())
				return;
			if (saveChange()) {
				var jsonParams = $("#jsonParams").val();
				swfu.addPostParam("jsonParams", jsonParams);
				// handlers.form.upload 中方法，用swfupload中的方法提交
				doSubmit();
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

		var userIds = $("#userIds").val();
		var userNames = $("#userNames").val();

		var posIds = $("#posIds").val();
		var posNames = $("#posNames").val();

		var roleIds = $("#roleIds").val();
		var roleNames = $("#roleNames").val();

		var remarks = $("#remarks").val();

		var fileManageJson = {
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

	var swfu;
	window.onload = function() {
		swfu = new SWFUpload({
			// Backend settings
			upload_url : "${ctx}/platform/file/fileManage/uploadFile.ht;jsessionid="+__jsessionId,
			file_post_name : "resume_file",

			// Flash file settings
			file_size_limit : "500 MB",
			file_types : "*.*", // or you could use something like: "*.doc;*.wpd;*.pdf",
			file_types_description : "All Files",
			file_upload_limit : 0,
			file_queue_limit : 1,

			// Event handler settings
			file_dialog_start_handler : fileDialogStart,
			file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,

			//upload_start_handler : uploadStart,	// I could do some client/JavaScript validation here, but I don't need to.
			swfupload_preload_handler : preLoad,
			swfupload_load_failed_handler : loadFailed,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			upload_complete_handler : uploadComplete,

			// Button Settings
			button_image_url : "${ctx}/styles/swfupload/XPButtonUpload.png",
			button_placeholder_id : "spanButtonPlaceholder",
			button_width : 61,
			button_height : 22,

			// Flash Settings
			flash_url : "${ctx}/js/swfupload/swfupload.swf",
			flash9_url : "${ctx}/js/swfupload/swfuploadbutton.swf",

			custom_settings : {
				progress_target : "fsUploadProgress",
				upload_successful : false
			},

			// Debug settings
			debug : false
		});
	};

	/* 
		上传完成后的回调
	 */
	function uploadComplete(file) {
		try {
			$.ligerDialog.confirm("上传成功,是否继续操作", "提示信息",
				function(rtn) {
					if (rtn) {
						// ....
						$("#txtFileName").val('');
					} else {
						closeWr();
					}
			});
		} catch (e) {
		}
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">文件上传</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link upload" id="btnSubmit" href="javascript:;"><span></span>上传</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link close" href="javascript:;" onclick="closeWr();"><span></span>关闭</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="uploadFileForm" method="post" action="uploadFile.ht"
				enctype="multipart/form-data">
				<input type="hidden" name="jsonParams" id="jsonParams" /> 
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<th width="20%">所有人：</th>
						<td><input type="checkbox" checked="checked" id="allPermis"
							name="allPermis" onclick="changePermis();" />
						</td>
					</tr>
					<tr>
						<th width="20%">分类：</th>
						<td><select id="categoryId" name="categoryId">
								<c:forEach items="${globalTypeList}" var="globalType">
									<option value="${globalType.typeId}"
										<c:if test="${globalType.typeId eq categoryId}">selected="selected"</c:if>>${globalType.typeName}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<th width="20%">备注：</th>
						<td><textarea id="remarks" name="remarks" cols="40" rows="3"></textarea>
						</td>
					</tr>
					<tr>
						<th width="20%">文件上传：</th>
						<td><input type="text" id="txtFileName" readonly="readonly"
							size="80" /> <span id="spanButtonPlaceholder"></span> (文件最大：500 MB)
							<div class="flash" id="fsUploadProgress"></div></td>
					</tr>

					<tr id="userPermis">
						<th width="20%">用户授权：</th>
						<td><input type="hidden" id="userIds" name="userIds">
							<textarea readonly="readonly" id="userNames" name="userNames"
								cols="40" rows="3"></textarea> <span class="bottom"
							style="margin-top: 10px;"> <a href="javascript:;" class="button"
								onclick="selectUsers()" style="margin-right: 10px;"><span
									class="icon ok"></span><span>选择</span>
							</a> <a href="javascript:;" class="button" onclick="resetSelectUsers()"><span
									class="icon cancel"></span><span class="chosen">清空</span>
							</a> </span></td>
					</tr>
					<tr id="rolePermis">
						<th width="20%">岗位授权：</th>
						<td><input type="hidden" id="posIds" name="posIds"> <textarea
								readonly="readonly" id="posNames" name="posNames" cols="40"
								rows="3"></textarea> <span class="bottom"
							style="margin-top: 10px;"> <a href="javascript:;" class="button"
								onclick="selectPos()" style="margin-right: 10px;"><span
									class="icon ok"></span><span>选择</span>
							</a> <a href="javascript:;" class="button" onclick="resetselectPos()"><span
									class="icon cancel"></span><span class="chosen">清空</span>
							</a> </span></td>
					</tr>
					<tr id="postPrmis">
						<th width="20%">角色授权：</th>
						<td><input type="hidden" id="roleIds" name="roleIds">
							<textarea readonly="readonly" id="roleNames" name="roleNames"
								cols="40" rows="3"></textarea> <span class="bottom"
							style="margin-top: 10px;"> <a href="javascript:;" class="button"
								onclick="selectRole()" style="margin-right: 10px;"><span
									class="icon ok"></span><span>选择</span>
							</a> <a href="javascript:;" class="button" onclick="resetSelectRole()"><span
									class="icon cancel"></span><span class="chosen">清空</span>
							</a> </span></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
