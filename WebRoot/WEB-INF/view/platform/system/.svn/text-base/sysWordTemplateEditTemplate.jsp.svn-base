<%--
	time:2011-11-16 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>编辑WORD表单模板</title>
<%@include file="/commons/include/form.jsp"%>
<%@include file="/commons/include/customForm.jsp" %>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/OfficePlugin.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/OfficeControl.js"></script>
<style type="text/css">
	.panel-body{
		overflow:hidden;
	}
	.l-layout-left{
		overflow:auto;
	}
</style>
<script type="text/javascript">
	var controlObj;
	
	$(window).bind("load",function(){
		//Office控件初始化。
		OfficePlugin.init();
		controlObj = OfficePlugin.officeObjs[0].controlObj;
	});
	
	$(function() {
		//验证代码
		var winHeight = $(window).height()-120;
		$("#frmDefLayout").ligerLayout({leftWidth : 200,height:winHeight,onHeightChanged:function(layoutHeight, diffHeight, middleHeight){
		}});
		
   	 	$('#frmWorkFlow').ajaxForm({success:showResponse }); 
   	 	initZtree();
	});
	
	function showResponse(responseText){
		var obj=new com.hotent.form.ResultMessage(responseText);
		if(obj.isSuccess()){
			$.ligerDialog.confirm("保存成功,是否继续操作", "提示信息", function(rtn) {
				if (!rtn) {
					window.opener.location.reload();
					window.close();
				}
			});
		}else{
			$.ligerDialog.error(obj.getMessage(), '出错信息');
		}
		$(".l-dialog-frame").css("z-index","9000");
	}
	
	function submitData() {
		var uaName=navigator.userAgent.toLowerCase();	
		OfficePlugin.submit();//火狐和谷歌 的文档提交包括了  业务提交代码部分（完成  OfficePlugin.submit()后面的回调 函数 有 业务提交代码），所以 后面就不用加上业务提交代码
		if(uaName.indexOf("firefox")<0 && uaName.indexOf("chrome")<0){  //同步处理
			//当提交问题 等于 提交数量的变量 时 表示所有文档 都提交了  然后做 业务相关的提交
			if(OfficePlugin.submitNum == OfficePlugin.submitNewNum){    
				$('#frmWorkFlow').submit();
			}else{
				$.ligerDialog.error("提交失败,OFFICE控件没能正常使用，请重新安装 ！！！","提示");
				$(".l-dialog-frame").css("z-index","9000");
			}
		}
	}
	
	function initZtree(){
		var json = ${tableMap};
		var error = json.error;
		if(error) {
			$.ligerDialog.error(error,"出错啦！");
			json = {mainfields:[], subtables:[]};
		}
		var treeData = [];
		var iconFolder = __ctx + '/styles/tree/';
		
		for(var i=0,c;c=json.mainfields[i++];){
			if(c.isHidden == 0){
				c.name = c.fieldDesc;
				c.pId = 0;
				c.icon = iconFolder + c.fieldType + '.png';
				treeData.push(c);
			}
		}
		
		for(var i=0,c;c=json.subtables[i++];){
			c.icon = iconFolder + 'table.png';
			c.pId = 0;
			treeData.push(c);
			for(var j = 0,m;m=c.subfields[j++];){
				m.pId = c.id;
				m.name = m.fieldDesc;
				m.icon = iconFolder + m.fieldType + '.png';
				treeData.push(m);
			}
		}
	
		var setting = {
			data: {
				key : {
					name: "name"
				},
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			},
			
			callback : {
				beforeClick : beforeClick
			}
		};
		glTypeTree = $.fn.zTree.init($("#colstree"),setting, treeData);
	}
	
	var bookMarkIndex;//保存当前文档中书签的总数
	function beforeClick(treeId, treeNode, clickFlag){
		var fieldName = treeNode.fieldName;
		var fieldDesc = treeNode.fieldDesc;
		var doc = controlObj.ActiveDocument;
		var sel = doc.Application.Selection.Range;
		var isChecked = $('#insertDesc').is(':checked');
		var isParent = treeNode.isParent;
		if(isChecked){
			sel.Text = isParent ? treeNode.name : fieldDesc ;
		}else if(!isParent){
			if(!bookMarkIndex) {
				bookMarkIndex = doc.Bookmarks.Count;
			}
			var parent = treeNode.getParentNode();
			if(parent){
				sel.Text = parent.tablename +'_0_'+ fieldName;
			}else {
				fieldName = fieldName+'_1_'+bookMarkIndex;
				sel.Text = '\${' + fieldDesc + '}';
				doc.Bookmarks.Add(fieldName, sel);
				bookMarkIndex++;
			}
		}
		return false;
	}
	function developHelp(){
		var obj=$("#help_div");
		$.ligerDialog.open({ target:obj , height: 370,width:600, modal :true}).show();
		$(".l-dialog-frame").css("z-index","9000");
		return false;
	}
</script>
</head>
<body style="overflow:hidden">
	<div>
		<div class="tbar-title">
			<span class="tbar-label">word表单模板编辑</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="dataFormSave" href="javascript:;" onclick="submitData();"><span></span>保存</a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link preview" id="btnPreView" href="javascript:;" onclick="preview();"><span></span>预览</a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link  back" href="edit.ht?id=${sysWordTemplate.id}"><span></span>上一步</a>
				</div>
				<div class="group"><a onclick="developHelp()" class="link copyTo"><span></span>帮助</a></div>
			</div>
		</div>
	</div>
	<div  class="panel-body">
		<form id="frmWorkFlow" method="post" action="save.ht">
			<input type="hidden" name="tableId" value="${sysWordTemplate.tableId}" />
			<input type="hidden" name="id" value="${sysWordTemplate.id}" />
			<input type="hidden" name="name" value="${sysWordTemplate.name}" />
			<input type="hidden" name="alias" value="${sysWordTemplate.alias}"/>
			<input type="hidden" name="type" value="${sysWordTemplate.type}"/>
			<input type="hidden" name="dsAlias" value="${sysWordTemplate.dsAlias}">
			<input type="hidden" name="sql" value='${sysWordTemplate.sql}'>
			<input type="hidden" name="tableName" value='${sysWordTemplate.tableName}'>
			<div id="tab" class="panel-nav">
				<div id="frmDefLayout">
					<div position="left" title="表字段<input type='checkbox' id='insertDesc'>插入描述">
						<ul id="colstree" class="ztree"></ul>
					</div>
					<div id="wordEditor" position="center"  style="height:100%;">
						<input type="hidden" name="fileId" controltype="office"  value="${sysWordTemplate.fileId}" style="height:100%;" width="100%"  right="w" menuRight="{}"/>
					</div>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="sysWordTemplateInc.jsp"></jsp:include>
	<div id="help_div" class="hidden" style="font-size:13px;">
	<table id="callCodeTable" class="table-grid table-list">
		<tr>
			<th>主表字段：</th>
			<td style="text-align: left;">
				插入方式有以下2种：<br>
				1、点击左侧表字段列表中的主表字段<br>
				2、直接使用word书签功能，插入书签名为对应字段名的书签，若需要有多个相同字段的书签，请以_1_加数字进行区分，如：
				first_name， first_name_1_0，first_name_1_1等。<br><br>
				分隔符为：_1_
			</td>
		</tr>
		<tr>
			<th>子表字段：</th>
			<td style="text-align: left;">
				子表数据，需要放在table中，字段插入方式有以下2种：<br>
				1、点击左侧表字段列表中的对应的子表字段，插入到对应表格最后一行<br>
				2、直接在对应表格最后一行中填写，格式为：<br>
				自定义表：表名_0_字段名。<br>
				SQL：别名_0_字段名。<br><br>
				分隔符为：_0_
			</td>
		</tr>
		<tr>
			<th>复选框作用：</th>
			<td style="text-align: left;">
				1、复选框勾选：点击字段时，则只添加节点描述。<br>
				2、取消勾选：点击字段时，则按上述【主表字段】【子表字段】的规则进行添加<br>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>

