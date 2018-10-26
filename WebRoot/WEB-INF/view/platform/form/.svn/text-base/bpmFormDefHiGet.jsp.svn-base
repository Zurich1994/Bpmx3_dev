
<%--
	time:2011-11-16 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>表单设计</title>
<%@include file="/commons/include/form.jsp"%>
<%-- <link rel="stylesheet" href="${ctx}/styles/default/css/tab/tab.css" type="text/css" /> --%>
<f:link href="tab/tab.css"></f:link>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/PageTab.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormContainer.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDef.js"></script>
<!-- ueditor -->
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor2/design-setting/editor_config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor2/editor_api.js"></script>
<style type="text/css">
	.panel-body{
		overflow:hidden;
	}
</style>
<script type="text/javascript">
	var editor,
		tabTitle="${bpmFormDefHi.tabTitle}",
		formContainer,
		tabControl,
		locale='zh_cn';;
	$(function(){		
		var h = $(document.body).height();
		FormDef.getEditor({
			height:100,
			width:10,
			lang:locale
		});
		editor.addListener('ready',function(){
			initTab();
			editor.setDisabled();
		});
		editor.render("txtHtml");
		
		
		$(window).resize(function (){
       		window.location.href = window.location.href;
        });
		
	});
	
	//初始化TAB
	function initTab() {
		var formData = editor.getContent();
		if (tabTitle == "")
			tabTitle = "页面1";
		formContainer = new FormContainer();
		var aryTitle = tabTitle.split(formContainer.splitor);
		var aryForm = formData.split(formContainer.splitor);
		var aryLen = aryTitle.length;
		//初始化
		formContainer.init(tabTitle, formData);

		tabControl = new PageTab("pageTabContainer", aryLen, {
			addCallBack : addCallBack,
			beforeActive : beforeActive,
			activeCallBack : activeCallBack,
			beforeDell : beforeDell,
			delCallBack : delCallBack,
			txtCallBack : txtCallBack
		});
		tabControl.init(aryTitle);
		if (aryLen > 1) {
			editor.setContent(aryForm[0]);
		};
	};
	//添加tab页面的回调
	function addCallBack() {
		var curPage = tabControl.getCurrentIndex();
		var str = "新页面";
		var idx = curPage - 1;
		formContainer.insertForm(str, "", idx);
		saveTabChange(idx-1,idx);		
	};
	//切换tab之前，返回false即中止切换
	function beforeActive(prePage) {
		if (FormDef.isSourceMode) {
			$.ligerDialog.warn('不能在源代码模式下切换页面',"提示信息");
			return 0;
		}
		return 1;
	};
	//表单或者标题发生变化是保存数据。
	function saveChange() {
		var index = tabControl.getCurrentIndex() - 1;
		var title = tabControl.currentTab.text();
		var data = editor.getContent();
		formContainer.setForm(title, data, index);
	}
	//切换激活tab时执行。
	function activeCallBack(prePage, currentPage) {
		if (prePage == currentPage)
			return;
		//保存上一个tab的数据。
		saveTabChange(prePage - 1, currentPage - 1);
	};
	//在删除页面之前的事件，返回false即中止删除操作
	function beforeDell(curPage) {
		if (FormDef.isSourceMode) {
			$.ligerDialog.warn('不能在源代码模式下删除页面',"提示信息");
			return 0;
		}
		return 1;
	};
	//点击删除时回调执行。
	function delCallBack(curPage) {
		formContainer.removeForm(curPage - 1);
		var tabPage = tabControl.getCurrentIndex();
		setDataByIndex(tabPage - 1);
	};
	//文本返回
	function txtCallBack() {
		var curPage = tabControl.getCurrentIndex();
		var idx = curPage - 1;
		var title = tabControl.currentTab.text();
		//设置标题
		formContainer.setFormTitle(title, idx);
	};
	//根据索引设置数据
	function setDataByIndex(idx) {
		if (idx == undefined)
			return;
		var obj = formContainer.getForm(idx);
		editor.setContent(obj.form);
		$("b", tabControl.currentTab).text(obj.title);
	};
	//tab切换时保存数据
	function saveTabChange(index, curIndex){
		var data = editor.getContent();
		formContainer.setFormHtml(data, index);
		setDataByIndex(curIndex);
	}
</script>
</head>
<body style="overflow:hidden">
	<div  class="panel-body">
		<form id="bpmFormDefForm" method="post" name="bpmFormDefForm">
			<input id="tabTitle" type="hidden" name="tabTitle" value=""/>
			<table cellpadding="0" cellspacing="0" border="0" style=" margin-bottom:4px;"  class="table-detail">
				<tr style="height:25px;">
					<th>表单标题:</th>
					<td>&nbsp;<input id="subject" type="text" readonly="readonly" name="subject" value="${bpmFormDefHi.subject}" validate="{required:true}" class="inputText" style="width:80%" /></td>
					<th>表单描述:</th>
					<td>&nbsp;<input id="formDesc" type="text" readonly="readonly" name="formDesc"	value="${bpmFormDefHi.formDesc}" class="inputText" style="width:80%" /></td>
				</tr>
			</table>
		
			<div title="表单设计">
				<div id="editor" position="center"  style="overflow:hidden;height:100%;">
					<textarea id="txtHtml" name="html">${fn:escapeXml(bpmFormDefHi.html) }</textarea>
					<textarea id="content" name="content" style="display:none;"></textarea>
					<div id="pageTabContainer"></div>
				</div>
			</div>		
		</form>	
	</div>	
</body>
</html>

