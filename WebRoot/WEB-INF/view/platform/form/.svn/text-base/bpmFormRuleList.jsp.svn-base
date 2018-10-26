
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>表单验证规则管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/htDicCombo.js"></script>
	<script type="text/javascript">
	$(function() {
		$("div.group > a.link.export").click(function()
		{	
			if($(this).hasClass('disabled')) return false;
			
			var action=$(this).attr("action");
			var $aryId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
			
			if($aryId.length == 0){
				$.ligerDialog.warn("请选择记录！");
				return false;
			}
			
			//提交到后台服务器进行日志删除批处理的日志编号字符串
			var delId="";
			var keyName="";
			var len=$aryId.length;
			$aryId.each(function(i){
				var obj=$(this);
				if(i<len-1){
					delId+=obj.val() +",";
				}
				else{
					keyName=obj.attr("name");
					delId+=obj.val();
				}
			});
			var url=action +"?" +keyName +"=" +delId ;
			
			$.ligerDialog.confirm('确认导出吗？','提示信息',function(rtn) {
				if(rtn) {
					var form=new com.hotent.form.Form();
					form.creatForm("form", action);
					form.addFormEl(keyName, delId);
					form.submit();
				}
			});
			return false;
		
		});
	});
	function ImportDefTableWindow(conf)
	{
		if(!conf) conf={};
		var url=__ctx + "/platform/form/bpmFormRule/import.ht";
		conf.url=url;
		var dialogWidth=550;
		var dialogHeight=250;
		conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);
		
		var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
			+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
		
		/* var rtn=window.showModalDialog(url,"",winArgs);
		if(rtn!=null)
			window.location.reload(true); */
		/*KILLDIALOG*/
		DialogUtil.open({
			height:conf.dialogHeight,
			width: conf.dialogWidth,
			title : '导入规则',
			url: url, 
			isResize: true,
			//自定义参数
			sucCall:function(rtn){
				window.location.reload(true);
			}
		});
	}
</script>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">表单验证规则管理列表</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link export"  action="export.ht"><span></span>导出</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link import"  href="javascript:;"  onclick="ImportDefTableWindow()"><span></span>导入</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label">规则名:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/></li>
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
					<c:set var="checkAll">
						<input type="checkbox" id="chkall"/>
					</c:set>
				    <display:table name="bpmFormRuleList" id="bpmFormRuleItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
						<display:column title="${checkAll}" media="html" style="width:30px;">
							  	<input type="checkbox" class="pk" name="id" value="${bpmFormRuleItem.id}">
						</display:column>
						<display:column property="name" title="规则名" style="text-align:left" ></display:column>
						<display:column property="rule" title="规则" style="text-align:left"></display:column>
						<display:column property="tipInfo" title="错误提示信息" style="text-align:left"></display:column>
						<display:column property="memo" title="描述" style="text-align:left"></display:column>
						<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">
							<a href="del.ht?id=${bpmFormRuleItem.id}" class="link del">删除</a>
							<a href="edit.ht?id=${bpmFormRuleItem.id}" class="link edit">编辑</a>
							<a href="get.ht?id=${bpmFormRuleItem.id}" class="link detail">明细</a>
						</display:column>
					</display:table>
					<hotent:paging tableId="bpmFormRuleItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


