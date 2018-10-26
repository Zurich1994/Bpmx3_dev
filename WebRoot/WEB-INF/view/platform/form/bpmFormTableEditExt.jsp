<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>添加外部表定义</title>
<%@include file="/commons/include/form.jsp" %>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerSpinner.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/formTableExtAdd.js" ></script>
<script type="text/javascript">
	var tableId="${tableId}";
	var tableManager;
	$(function ()
	{
		var layout = $("#layout1").ligerLayout({allowTopCollapse: false, topHeight:100, allowTopResize: false, bottomHeight: 170, isBottomCollapse: true});
		var pars={tableId:tableId};
		$.post('getExtTableByTableId.ht',pars, function(data) {
			tableManager = new com.hotent.platform.form.TableManager({
				id: {
					fieldListId: 'fieldList',
					fieldPropertyId: 'fieldProperty',
					tablePropertyId: 'table',
					backHistoryId: 'backHistory',
					forwardHistoryId: 'forwardHistory',
					moveupFieldId: 'moveupField',
					movedownFieldId: 'movedownField',
					delFieldId: 'delField',
					centerId: 'center',
				},
				data: data
			}, layout);
			
		});
	});
	
	function save(obj) {
		if(tableManager.validate()) {
			var url = $(obj).attr('url');
			var data = tableManager.getData();
			$.post(url, data, function(data) {
				var obj=new com.hotent.form.ResultMessage(data);
				if(obj.isSuccess()){//成功
					$.ligerDialog.confirm('操作成功,继续操作吗?','提示信息',function(rtn){
						if(!rtn){
							var returnUrl=$("a.back").attr("href");
							location.href=returnUrl;
						}
						else{
							location.reload();
						}
					});
			    }else{//失败
			    	$.ligerDialog.err('出错信息',"保存外部表定义失败",obj.getMessage());
			    }
			});
		} else {
			tableManager.showAllError();
		}
	}
	
	function selectScript(){
		var objConditionCode=$("#script")[0];
		var win=ScriptDialog({callback:function(script){
			jQuery.insertText(objConditionCode,script);
			$(objConditionCode).change();
		}});
	}
	
	function changeKeyType(obj){
		var objTmp=$("#tdKeyValue");
		if(obj.value==2){
			objTmp.css("display","");
		}
		else{
			objTmp.css("display","none");
		}
	}
</script>
<style type="text/css">
	body {
		overflow: hidden;
	}
	#fieldProperty table tr {
		height: 26px;
	}
</style>
</head>
<body>
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">添加外部表定义</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" url="save.ht" onclick="save(this)"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div id="layout1">
		<div position="top">
			<form id="table">
				<input id="tableId" type="hidden" name="tableId" value=""/>
				<input id="versionNo" type="hidden" name="versionNo" value=""/>
				<input id="isPublished" type="hidden" name="isPublished" value=""/>
				<table cellpadding="0" cellspacing="0" border="0" style="margin: 4px 30px;">
					<tr>
						<td>表名:&nbsp;<input id="tableName" type="text" name="tableName" value="" class="inputText" /></td>
						<td>&nbsp;&nbsp;&nbsp;描述:&nbsp;<input id="tableDesc" type="text" name="tableDesc" value="" class="inputText" /></td>
						<td>&nbsp;&nbsp;&nbsp;<input type="radio" name="isMain" value="1" checked/>&nbsp;主表&nbsp;</td>
						<td>&nbsp;&nbsp;&nbsp;<input type="radio" name="isMain" value="0" />&nbsp;子表</td>
						<td>
							&nbsp;&nbsp;&nbsp;主键:&nbsp;
							<select name="pkField">
							</select>
						</td>
						<td >
							&nbsp;&nbsp;&nbsp;主键规则:&nbsp;
							<select name="keyType" onchange="changeKeyType(this);">
							</select>
							
						</td>
						<td style="display:none;" id="tdKeyValue">&nbsp;&nbsp;&nbsp;流水号:
							<select name="keyValue">
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="center" position="center" title="字段列表" style="position:relative;overflow:auto;">
			<div class="panel-top" style="position: fixed;">
				<div class="panel-toolbar">
					<div class="toolBar">
						
						<div class="group"><a id="backHistory" class="link undo"><span></span>撤销<span style="font-weight:normal; color: gray; font-size: 10px;">(0)</span></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a id="forwardHistory" class="link redo"><span></span>重做<span style="font-weight:normal; color: gray; font-size: 10px;">(0)</span></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a id="moveupField" class="link moveup"><span></span>上移</a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a id="movedownField" class="link movedown"><span></span>下移</a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a id="delField" class=" link del"><span></span>删除</a></div>
					</div>
				</div>
			</div>
			<div class="panel-top">
				<div class="panel-toolbar">
					<div class="toolBar">
					</div>
				</div>
			</div>
			<div class="panel-body">
				<div class="panel-data">
				    <table id="fieldList" cellpadding="1" cellspacing="1" class="table-grid">
						<tr>
							<th width="5%" name="lineNO">序号</th>
							<th width="10%" name="fieldName">字段名称</th>
							<th width="10%" name="fieldDesc">字段描述</th>
							<th width="15%" name="fieldType">字段类型</th>
							<th width="10%" name="isRequired">必填</th>
							<th width="10%" name="isList">显示到列表</th>
							<th width="10%" name="isQuery">作为查询条件</th>
						</tr>
					</table>
				</div>
			</div><!-- end of panel-body -->			
		</div>
		<div position="bottom" title="字段属性" style="overflow: auto;">
			<form id="fieldProperty">
				<input type="hidden" id="fieldId" name="fieldId"/>
				<table cellpadding="0" cellspacing="0" border="0" style="margin: 4px 20px;">
					<tr>
						<td valign="top">
							<table cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td width=100>字段名称:</td>
									<td>
										<label name="fieldName"></label>
									</td>
								</tr>
								<tr>
									<td width=100>字段描述:</td>
									<td><input type="text" name="fieldDesc" value="" class="inputText"/></td>
								</tr>
								<tr>
									<td width=100>字段类型:</td>
									<td><select id="fieldType" name="fieldType"></select></td>
								</tr>
								<tr>
									<td width=100>文字长度:</td>
									<td><input type="text" id="charLen" name="charLen" value="20" class="inputText"/></td>
								</tr>
								<tr>
									<td width=100>整数长度:</td>
									<td>
										<input type="text" id="intLen" name="intLen" value="20" class="inputText" size=5/>
										&nbsp;&nbsp;&nbsp;小数长度:&nbsp;&nbsp;
										<input type="text" id="decimalLen" name="decimalLen" value="0" class="inputText" size=5/>
									</td>
								</tr>
								<tr>
									<td width=100>选项:</td>
									<td>
										<span style="display:inline-block"><input name="isRequired" type="checkbox" value="1"/>&nbsp;必填&nbsp;</span>
										<span style="display:inline-block"><input name="isList" type="checkbox" value="1"/>&nbsp;显示到列表&nbsp;</span>
										<span style="display:inline-block"><input name="isQuery" type="checkbox" value="1"/>&nbsp;作为查询条件&nbsp;</span>
									</td>
								</tr>
							</table>
						</td>
						<td valign="top">
							<table cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td width=100>值来源:</td>
									<td><select id="valueFrom" name="valueFrom"></select></td>
								</tr>
								<tr>
									<td width=100>控件类型:</td>
									<td><select id="controlType" name="controlType"></select></td>
								</tr>
								<tr>
									<td width=100>验证规则:</td>
									<td><select id="validRule" name="validRule"><option value="">无</option></select></td>
								</tr>
								<tr>
									<td width=100>数据字典类型:</td>
									<td><select id="dictType" name="dictType"></select></td>
								</tr>
								<tr>
									<td colspan="2" valign="top">
									<div style="width:96px;display:inline-block;vertical-align:top;">脚本:</div>
									<a href="javascript:;" onclick="selectScript()" id="btnScript" class="link var" title="常用脚本">常用脚本</a>
									<br/>脚本中要使用到其他字段参与运算， 请使用“[字段名]”方式引用。<br />
									<textarea id="script" name="script" rows="6" cols="70"></textarea>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>


