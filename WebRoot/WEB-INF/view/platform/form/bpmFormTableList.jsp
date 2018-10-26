
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<f:link href="tree/zTreeStyle.css"></f:link>
<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
<title>自定义表管理</title>
<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js"></script>
<script type="text/javascript" src="${ctx }/js/hotent/platform/bpm/SetRelationWindow.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ImportExportXmlUtil.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/htCatCombo.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<script type="text/javascript">
	var win;
	$(function(){
		$("table.table-grid td a.link.del").unbind("click");
		delFormTable();
		$("a.reset").click(function(){
			var me = $(this),
				url = me.attr("prehref"),
				message ='重置操作将删除物理表，并重置表的状态为未生成。是否重置该表?';
			$.ligerDialog.confirm(message,'提示',function(rtn) {
				if(rtn){
					if($.browser.msie){
						$.gotoDialogPage(url);
					}
					else{
						location.href=url;
					}
				}
			});
		});
	});
	
	
	function delFormTable(){	
		$("table.table-grid td a.link.del").click(function(){
			if($(this).hasClass('disabled')) return false;
			var ele=this,tableId = $(this).attr('action');
			$.post('isMain.ht', {'tableId': tableId}, function(data) {
				var message = data.isMain?"是否删除该表":"是否删除该表";
					$.ligerDialog.confirm(message,'提示',function(rtn) {
						if(rtn){
							if($.browser.msie){
								$.gotoDialogPage(ele.href);
							}
							else{ 
								location.href=ele.href;
							}
						}
					});
					return false;
			});
			return false;
		});
	}
	
	
	function showRelation(tableId,dsName) {
		var conf={tableId:tableId,dsName:dsName};
		SetRelationWindow(conf);
	}
	
	function assignMainTable(subTableId){
		var url=__ctx + "/platform/form/bpmFormTable/assignMainTable.ht?subTableId="+subTableId;
		var winArgs="dialogWidth=400px;dialogHeight=200px;help=0;status=0;scroll=0;center=1;resizable=1";
		url=url.getNewUrl();
		//var rtn=window.showModalDialog(url,"",winArgs);
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:200,
			width: 400,
			title : '',
			url: url, 
			isResize: true,
		});
	}
	
	//复制表
	function copyTable(tableId){
		var url= __ctx + "/platform/form/bpmFormTable/copyTable.ht?tableId="+tableId;
		ImportExportXml.showModalDialog({url:url,width:750,height:450,scroll:1});
	}
	
	function newTableTemp(e){		
		var url=__ctx + '/platform/form/bpmTableTemplate/edit.ht?tableId='+e;
		win= $.ligerDialog.open({ url: url, height: 400,width:600 ,isResize: false });		
	}
	function closeWin(){
		if(win){
			win.close();
		}		
	}	
	function generator(tableId) {
		$.ligerDialog.confirm('将连同子表一起生成,是否继续?','提示信息',function(rtn){
			if(!rtn) return;

			$.post('generateTable.ht', {'tableId': tableId}, function(data) {
				var obj=new com.hotent.form.ResultMessage(data);
				if(obj.isSuccess()){//成功
					$.ligerDialog.success("生成成功",'提示信息',function(){
						location.reload();
					});
				
			    }else{//失败
			    	$.ligerDialog.err('出错信息',"生成自定义表失败",obj.getMessage());
			    }
			});

		});
	}
	
	// 导出自定义表
	function ExportXml(){
		var tableId = "";
		$("input[type='checkbox'][class='pk']:checked").each(function(){ 
				var obj=$(this);
				if(obj.attr('id')==1)
					tableId+= obj.val()+",";
		
		});
		if(tableId.length==0){
			$.ligerDialog.warn("请选择主表进行导出!");
			return;
		}else{
			tableId=tableId.substring(0,tableId.length-1);
		}
		var url = __ctx + "/platform/form/bpmFormTable/export.ht?tableIds="+tableId;
		ImportExportXml.showModalDialog({url:url});
		
	}
	//导入自定义xml
	function ImportXml(){
		var url= __ctx + "/platform/form/bpmFormTable/import.ht";
		//ImportExportXml.showModalDialog({url:url});
		DialogUtil.open({
			height:250,
			width: 450,
			title : '导入自定义表',
			url: url, 
			isResize: true
		});
	}
	
	var dialog=null;
	
	// 设置分类
	function setCategory(){
		var tableIds = getTableIds();
		if(tableIds == ""){
			$.ligerDialog.warn('还没有选择,请选择一项记录!','提示');
			return;
		}
		$.initCatComboBox();
		if(dialog==null){
			dialog=$.ligerDialog.open({title:'设置分类',target:$("#dialogCategory"),width:400,height:250,buttons:
				[ {text : '确定',onclick: setCategoryOk},
				{text : '取消',onclick: function (item, dialog) {
						dialog.hide();
					}
				}]});	
		}
		dialog.show();
		justifyMargin(10,3,1000);
	}
	
	// 设置分类
	function setCategoryOk(item, dialog){
		var categoryId=$("#categoryId").val();
		if(categoryId==""){
			$.ligerDialog.warn('请选择分类','提示');
			return;
		}
		var tableIds = getTableIds();
		var params={tableIds:tableIds,categoryId:categoryId};
		var url="${ctx}/platform/form/bpmFormTable/setCategory.ht";
		$.post(url,params,function(responseText){
			var obj=new com.hotent.form.ResultMessage(responseText);
			if(obj.isSuccess()){
				$.ligerDialog.success('保存成功!','提示',function(){
					dialog.hide();
					var url=location.href.getNewUrl();
					location.href=url;
				});
			}
			else{
				$.ligerDialog.err('提示','保存失败!',obj.getMessage());
			}
		});
	}
	
	function getTableIds(){
		var aryChk=$("input:checkbox[name='tableId']:checked");
		if(aryChk.size()==0) return "";
		var aryTableId=[];
		aryChk.each(function(){
			aryTableId.push($(this).val());
		});
		return aryTableId.join(",");
	}
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">自定义表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link search" id="btnSearch"><span></span>查询</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<f:a alias="addForm" css="link add" href="edit.ht?categoryId=${param['categoryId']}" showNoRight="false" ><span></span>添加</f:a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group"><f:a alias="delForm" css="link del" action="delByTableId.ht" showNoRight="false" ><span></span>删除</f:a></div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link add" href="defExtTable1.ht" ><span></span>添加外部表</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a onclick="setCategory()"  class="link category"><span></span>设置分类</a>
					</div>
					
					<div class="l-bar-separator"></div>
				
					<div class="group">
						<a class="link export" href="javascript:;" onclick="ExportXml()"><span></span>导出</a>
					</div>
					
					<div class="l-bar-separator"></div>
				<div class="group">
					<a   class="link import"  href="javascript:;" onclick="ImportXml()"><span></span>导入</a>
				</div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
					</div>
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<input type="hidden" name="categoryId" value="${param['categoryId']}" title="表单分类ID"></input>
					<ul class="row">
						<li><span class="label">表名:</span>
							<input type="text" 
							name="Q_tableName_SL" class="inputText"
							value="${param['Q_tableName_SL']}" />
						</li>
						<li> <span class="label">注释:</span>
							<input type="text"
							 name="Q_tableDesc_SL" class="inputText"
							value="${param['Q_tableDesc_SL']}" /> 
						</li>
						<li>
							<span class="label">是否主表:</span>
							<select name="Q_isMain_SN" value="${param['Q_isMain_SN']}">
								<option value="" >全部</option>
								<option value="1"
									<c:if test="${param['Q_isMain_SN'] == '1'}">selected</c:if>>主表</option>
								<option value="0"
									<c:if test="${param['Q_isMain_SN'] == '0'}">selected</c:if>>子表</option>
							</select>
						</li>
					</ul>
					<ul class="row">
						<li> <span class="label">生成方式:</span> 
							<select name="Q_genByForm_SN" value="${param['Q_genByForm_SN']}">
							<option value="">全部</option>
							<option value="0" 
								<c:if test="${param['Q_genByForm_SN'] == '0'}">selected</c:if>>自定义表</option>
							<option value="1" 
								<c:if test="${param['Q_genByForm_SN'] == '1'}">selected</c:if>>由表单生成</option>
							</select>
						</li>
						<li> <span class="label">是否生成:</span> <select name="Q_isPublished_SN"
							value="${param['Q_isPublished_SN']}">
							<option value="">全部</option>
							<option value="0"
								<c:if test="${param['Q_isPublished_SN'] == '0'}">selected</c:if>>未生成</option>
							<option value="1"
								<c:if test="${param['Q_isPublished_SN'] == '1'}">selected</c:if>>已生成</option>
						</select>
						</li>
					</ul>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
			<c:set var="checkAll">
				<input type="checkbox" id="chkall" />
			</c:set>
			<display:table name="bpmFormTableList" id="bpmFormTableItem"
				requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"
				export="false" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
					<input type="checkbox" class="pk" name="tableId" id="${bpmFormTableItem.isMain}" value="${bpmFormTableItem.tableId}">
				</display:column>
				<display:column property="tableName" title="表名" sortable="true" sortName="tableName" style="text-align:left"></display:column>
				<display:column property="tableDesc" title="注释" sortable="true" sortName="tableDesc" style="text-align:left"></display:column>
				
				<display:column title="属性" style="text-align:left">
					<c:choose>
						<c:when test="${bpmFormTableItem.isMain == 1}">主表</c:when>
						<c:otherwise>子表</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="categoryName" title="业务数据分类" sortable="true" sortName="categoryId" style="width:80px;"></display:column>
				<display:column title="状态" style="text-align:center">
					<c:choose>
						<c:when test="${bpmFormTableItem.isPublished==0}">
							<span class="red">未生成</span>
						</c:when>
						<c:otherwise>
							<span class="green">已生成</span>
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="数据源别名">
						${bpmFormTableItem.dsAlias}
				</display:column>
				<display:column title="生成方式" style="text-align:left">
					<c:choose>
						<c:when test="${bpmFormTableItem.genByForm == 0}">
							<span class="red">自定义表</span>
						</c:when>
						<c:otherwise>
							<span class="green">由表单生成</span>
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="管理" media="html" style=" width:50px;text-align:center;" class="rowOps">
					<c:choose>
						<c:when test="${bpmFormTableItem.isExternal == 0 }">
							<c:if test="${bpmFormTableItem.genByForm==0}">
								<a href="edit.ht?tableId=${bpmFormTableItem.tableId}" class="link edit">编辑</a>
								<f:a alias="delTable" href="delByTableId.ht?tableId=${bpmFormTableItem.tableId}" action ="${bpmFormTableItem.tableId}" css="link del">删除</f:a>
								<a href="javascript:;" prehref="reset.ht?tableId=${bpmFormTableItem.tableId}" class="link reset" >重置</a>
								<a href="team.ht?tableId=${bpmFormTableItem.tableId}" class="link setting">分组</a>
								<c:if test="${bpmFormTableItem.isMain == 1  && bpmFormTableItem.isPublished==0}">
									<a href="javascript:;" class="link table"
										onclick="generator('${bpmFormTableItem.tableId}')">生成表</a>
								</c:if>
							</c:if>
						</c:when>
						<c:otherwise>
							<a href="defExtTable2.ht?tableId=${bpmFormTableItem.tableId}" class="link edit">编辑</a>
							<f:a alias="delTable" href="delExtTableById.ht?tableId=${bpmFormTableItem.tableId}" css="link del">删除</f:a>
							<a href="team.ht?tableId=${bpmFormTableItem.tableId}" class="link setting">分组</a>
						</c:otherwise>
					</c:choose>
					<a href="get.ht?tableId=${bpmFormTableItem.tableId}"
						class="link detail">明细</a>
						<c:if test="${bpmFormTableItem.isMain == 1 && bpmFormTableItem.genByForm==0 }">
							<a href="javascript:;" onclick="copyTable('${bpmFormTableItem.tableId}')" class="link copy">复制</a>
						</c:if>
					<a class="link search" href="${ctx}/dbFunc/dbFunc/dbFunc/getByTableName.ht?tableName=${bpmFormTableItem.tableName}">编辑方法</a>
				<a href="parcellist.ht?tableId=${bpmFormTableItem.tableId}" class="link edit">生成数据包</a>
					
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmFormTableItem" />
		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of panel -->
	<div id="dialogCategory" style="width: 380px;display: none;">
		<div class="panel">
			<div class="panel-body">
				<form id="frmDel">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th style="width:113px;text-align:center;">设置分类:</th>
							<td>
								<input class="catComBo" catKey="TABLE_TYPE" valueField="categoryId" catValue="" name="typeName" height="150" width="235"/>
							</td>
						</tr>
					</table>
				
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>


