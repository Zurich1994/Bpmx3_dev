<%--
	time:2012-02-20 09:25:49
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@include file="/commons/include/form.jsp"%>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerTab.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDef.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/PageTab.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormContainer.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<!-- ueditor -->
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor2/simple/editor_config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor2/editor_api.js"></script>
<html>
<head>
	<title>表单信息</title>
	<%@include file="/commons/include/getById.jsp" %>
	<script type="text/javascript">
	var locale='zh_cn';
	var tabTitle="${bpmFormDef.tabTitle}";
	var formKey=${bpmFormDef.formKey};
	var tableId=${bpmFormDef.tableId};
	var tab;
	var designType=${bpmFormDef.designType};

	
	$(function() {
		var winHeight = $(window).height()-120;
		$("#frmDefLayout").ligerLayout({leftWidth : 200,height:winHeight,onHeightChanged:function(layoutHeight, diffHeight, middleHeight){
		}});
		var height = $(".l-layout-center").height();
        $("#colstree").height(height-120);
     
		
		tab = $("#tab").ligerGetTabManager();
		FormDef.getEditor({
			height:240,
			width:227,
			lang:locale
		});
		editor.addListener('ready',function(){
			initTab();
		});
		//ueditor渲染textarea
		editor.render("html");
		editor.tableId = tableId;
		//获取字段显示到左边的字段树中
		getAllFields();
		$("#btnPreView").click(function(){
			preview();
		});
	
		
   	//	window.onbeforeunload = function() {				 											
   	//	  return '你确定吗？';
   	//	};
   	 	if(height)
	 		$('body').height(height);
	});
	
	//预览
	function preview(){
		//saveChange();
		window.onbeforeunload  =  null ;
		var objForm = formContainer.getResult();
		var frm=new com.hotent.form.Form();
		var url = "";
		if(designType==1){
			url = __ctx+"/platform/form/bpmFormDef/preview.ht";
		}else{
			url = __ctx+"/platform/form/bpmFormHandler/edit.ht";
		}
		frm.creatForm("bpmPreview",url);
		frm.addFormEl("name",$("#subject").val());
		frm.addFormEl("title",objForm.title);
		frm.addFormEl("html",objForm.form);
		frm.addFormEl("comment",$("#formDesc").val());
		frm.addFormEl("tableId",tableId);
		frm.setTarget("_blank");
		frm.setMethod("post");
		frm.submit();
	//	frm.clearFormEl();
	};
	


	function getAllFields() {
		FormDef.getFieldsByTableId(tableId);
	}

	//tab控件
	var tabControl;
	//存储数据控件。
	var formContainer;
	//添加tab页面
	function addCallBack() {
		var curPage = tabControl.getCurrentIndex();
		var str = "新页面";
		var idx = curPage - 1;
		formContainer.insertForm(str, "", idx);
		saveTabChange(idx-1,idx);		
	}
	//切换tab之前，返回false即中止切换
	function beforeActive(prePage) {
		if (FormDef.isSourceMode) {
			$.ligerDialog.warn('不能在源代码模式下切换页面',"提示信息");
			return 0;
		}
		return 1;
	}
	//点击激活tab时执行。
	function activeCallBack(prePage, currentPage) {
		if (prePage == currentPage)
			return;
		//保存上一个数据。
		saveTabChange(prePage - 1, currentPage - 1);
	}
	//根据索引设置数据
	function setDataByIndex(idx) {
		if (idx == undefined)
			return;
		var obj = formContainer.getForm(idx);
		editor.setContent(obj.form);
		$("b", tabControl.currentTab).text(obj.title);
	}
	//在删除页面之前的事件，返回false即中止删除操作
	function beforeDell(curPage) {
		if (FormDef.isSourceMode) {
			$.ligerDialog.warn('不能在源代码模式下删除页面',"提示信息");
			return 0;
		}
		return 1;
	}
	//点击删除时回调执行。
	function delCallBack(curPage) {
		formContainer.removeForm(curPage - 1);
		var tabPage = tabControl.getCurrentIndex();
		setDataByIndex(tabPage - 1);
	}
	//文本返回
	function txtCallBack() {
		var curPage = tabControl.getCurrentIndex();
		var idx = curPage - 1;
		var title = tabControl.currentTab.text();
		//设置标题
		formContainer.setFormTitle(title, idx);
	}
	//tab切换时保存数据
	function saveTabChange(index, curIndex){
		var data = editor.getContent();
		formContainer.setFormHtml(data, index);
		setDataByIndex(curIndex);
	}

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
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">表单信息</span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link preview" href="javascript:;" onclick="javascript:preview();"><span></span>预览</a></div>
					<div class="group"><a class="link back" href="${returnUrl}" onclick="javascript:stopPropagetion();"><span></span>返回</a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%">表单名:</th>
					<td>${bpmFormDef.subject}</td>
				</tr>
				<tr>
					<th width="20%">备注:</th>
					<td>${bpmFormDef.formDesc}</td>
				</tr>
				
				<tr>
					<th width="20%">默认版本:</th>
					<td> 
						<c:choose>
							<c:when test="${bpmFormDef.isDefault==1}">
								默认
							</c:when>
							<c:otherwise>
								非默认
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th width="20%">发布状态:</th>
					<td> 
						<c:choose>
							<c:when test="${bpmFormDef.isPublished==1}">
								发布
							</c:when>
							<c:otherwise>
								未发布
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<c:if test="${bpmFormDef.isPublished==1}">
					<tr>
						<th width="20%">发布人:</th>
						<td> 
							${bpmFormDef.publishedBy }
						</td>
					</tr>
					<tr>
						<th width="20%">发布时间:</th>
						<td> 
							<fmt:formatDate value="${bpmFormDef.publishTime}" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
				</c:if>
				<tr>
						<th width="20%">发布人:</th>
						<td> 
							${bpmFormDef.publishedBy }
						</td>
				</tr>
				<tr>
						<th width="20%">表单HTML:</th>
						<td> 
						<%-- 
							<textarea cols="100" rows="10">
								${fn:escapeXml(bpmFormDef.html)}
							</textarea>
						--%>	
					<!-- 	<div class="group">
								<a class="link preview" id="btnPreView" href="javascript:;"><span></span>预览</a>
				        </div>
				       <br/> -->
						<div id="frmDefLayout">
							<div  id="editor" position="center"  style="overflow:hidden;height:100%;width:100%;">
										<textarea id="html" name="html" width="100%">${fn:escapeXml(bpmFormDef.html) }</textarea>
							</div>
						</div>
						</td>
				</tr>
				
			</table>
		</div>
			
</div>

</body>
</html>