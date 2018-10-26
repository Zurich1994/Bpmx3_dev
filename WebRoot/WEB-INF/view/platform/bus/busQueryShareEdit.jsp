<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>共享</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript">
		/*KILLDIALOG*/
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		
		$(function() {

			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			$("a.save").click(function() {
				submitForm(options);
			});
			handChange("#shareTb");
			handClick("#shareTb");
			init()
			
		});
		//初始化
		function init(){
			var shareRightVal = $("#shareRight").val();
			if ($.isEmpty(shareRightVal))
				return;
			var shareRight = $.parseJSON(shareRightVal);
			if($.isEmpty(shareRight))
				return;
			var table=$('#shareTb');
			var type =$("[var='type']",table).val(shareRight.type);
			$("[var='sharerId']",table).val(shareRight.id);
			$("[var='sharerName']",table).val(shareRight.name);
			$("[var='shareScript']",table).val(shareRight.script);
			type.trigger("change");
		}
		
		
		function handChange(obj){
			$(obj).delegate("select.change_share", "change", function() {
				var me = $(this), spanObj = me.next(), nextSpanObj = spanObj.next();
				showSpan(me.val(), spanObj);
			 	$("textarea", spanObj).val(""),
				$("input:hidden",spanObj).val("");
				$("textarea", nextSpanObj).val("");
			});
		}
		function showSpan (permissionType, spanObj) {
			switch (permissionType) {
				case "user" :
				case "role" :
				case "org" :
				case "orgMgr" :
				case "pos" :
					spanObj.show();
					spanObj.next().hide();
					break;
				case "script" :
					spanObj.hide();
					spanObj.next().show();
					break;
				case "everyone" :
				case "none" :
					spanObj.hide();
					spanObj.next().hide();
					break;
			}
		}
		/**
		 * 处理选择
		 */
		function handClick(obj) {
			$(obj).delegate("a.link-get", "click", function() {
				var me = $(this), txtObj = me.prev(), idObj = txtObj.prev(), selObj = me
						.parent().prev(), selType = selObj.val();

				// 选择回调
				var callback = function(ids, names) {
					txtObj.val(names);
					idObj.val(ids);
				};

				switch (selType) {
					case "user" :
						UserDialog({
							callback : callback
						});
						break;
					case "role" :
						RoleDialog({
							callback : callback
						});
						break;
					case "org" :
					case "orgMgr" :
						OrgDialog({
							callback : callback
						});
						break;
					case "pos" :
						PosDialog({
							callback : callback
						});
						break;
				}
			});
		}
		
		/**
		 * 选择脚本
		 * 
		 * @param {}
		 *            obj
		 */
		 function selectScript (obj, isNext) {
			var linkObj = $(obj), txtObj = {};
			if (isNext)
				txtObj = linkObj.next()[0];
			else
				txtObj = linkObj.prev()[0];
			if (txtObj) {
				ScriptDialog({
					callback : function(script) {
						$.insertText(txtObj, script);
					}
				});
			}
		}
		
			//显示字段
			function getShareRight(){
				var obj={},table=$('#shareTb') ;
				obj.type =$("[var='type']",table).val();
				obj.id =$("[var='sharerId']",table).val();
				obj.name =$("[var='sharerName']",table).val();
				obj.script =$("[var='shareScript']",table).val();
				return obj;
			}	
		 
		//提交表单
		function submitForm(options){
			var shareRight = JSON2.stringify(getShareRight());
			
			var json={
					id:$('#id').val(),
					filterId:$('#filterId').val(),
					shareRight:shareRight
				};
				
				var form = $('<form method="post" action="save.ht"></form>');
				var input = $("<input type='hidden' name='json'/>");
				var jsonStr=JSON2.stringify(json);
				
				input.val(jsonStr);
				form.append(input);
				form.ajaxForm(options);
				form.submit();
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success(obj.getMessage(),"提示信息", function(rtn) {
					if(rtn){
						if(window.opener){
							window.opener.location.reload();
							dialog.close();
						}else{
							this.close();
							window.location.href="list.ht";
						}
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}

	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			   <span class="tbar-label">共享</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="list.ht"><span></span>关闭 </a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="busQueryShareForm" method="post" action="save.ht">
			<table class="table-detail" id="shareTb" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%" style="height: 100px;">共享: </th>
					<td>
						<select  class="change_share" var="type">
							<option selected="selected" value="none">无</option>
							<option value="everyone">所有人</option>
							<option value="user">用户</option>
							<option value="role">角色</option>
							<option value="org">组织</option>
							<option value="orgMgr">组织负责人</option>
							<option value="pos">岗位</option>
							<option value="script">脚本</option>
						</select>
						<span  style="display: none;" name="share_span">
							<input type="hidden" value="" var="sharerId">
							<textarea rows="3" cols="40" var="sharerName" readonly="readonly" style="width: 250px;"></textarea>
							<a href="javascript:;" class="link-get"><span class="link-btn">选择</span></a>
						</span>
						<span style="display: none;" class="share_script_span">
							<textarea var="shareScript" rows="3" cols="40" style="width: 250px;"></textarea>
							<a onclick="selectScript(this,false)" title="常用脚本" class="link var" name="btnScript" href="javascript:;">常用脚本</a>
						</span>
					</td>
				</tr>
			</table>
			<input type="hidden" id="id" name="id" value="${busQueryShare.id}" />
			<input type="hidden" id="filterId" name="filterId" value="${filterId}" />
			<textarea style="display: none;" id="shareRight" name="shareRight" >${fn:escapeXml(busQueryShare.shareRight)}</textarea>
		</form>
	</div>
</div>
</body>
</html>
