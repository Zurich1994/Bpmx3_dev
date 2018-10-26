
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>表单模板管理</title>
	<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
    <script type="text/javascript" src="${ctx }/js/hotent/platform/form/copyTemplateDialog.js"></script> 
    <script type="text/javascript">
    	$(function(){//设置不能编辑的行不能选中
    		$("tr").live("click",function(event){
    			var checkbox = $(this).find("input.pk");
    			if(checkbox.attr("disabled")=="disabled"){
   				   checkbox.attr("checked",false);
    			}
    		});
    		
    		handlerInit();
    	});
    	
    	//处理初始化模板
	    function handlerInit()
	    {
	    	$("a.link.init").click(function(){
	    		var action=$(this).attr("action");
	    		if($(this).hasClass('disabled')) return false;
	    		
	    		$.ligerDialog.confirm('初始化表单模板将会导致自定义模板信息丢失，确定初始化吗？','提示信息',function(rtn){
	    			if(rtn){
	    				var form=new com.hotent.form.Form();
	    				form.creatForm('form', action);
	    				form.submit();
	    			}
	    		});
	    		
	    	});
	    }

    	function copyTemplate(templateId,templateName,alias){
    		CopyTemplateDialog({templateId:templateId,templateName:templateName,alias:alias});
    	}
    </script>
</head>
<body>
			<div class="panel">
				<div class="hide-panel">
					<div class="panel-top">
						<div class="tbar-title">
							<span class="tbar-label">表单模板管理列表</span>
						</div>
						<div class="panel-toolbar">
							<div class="toolBar">
								<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
								<div class="l-bar-separator"></div>
								<div class="group">
									<f:a alias="addTemplate" css="link add" href="edit.ht" showNoRight="false"><span></span>添加</f:a>
								</div>
								<div class="l-bar-separator"></div>
								<div class="group"><f:a alias="editTemplate" css="link update" id="btnUpd"  action="edit.ht" showNoRight="false"><span></span>修改</f:a></div>								
								<div class="l-bar-separator"></div>
								<div class="group"><f:a alias="delTemplateForm" css="link del" action="del.ht" showNoRight="false"><span></span>删除</f:a></div>								
								<div class="l-bar-separator"></div>
								<div class="group"><f:a alias="initTemplate" css="link init" id="bntInit" action="init.ht" showNoRight="false"><span></span>初始化模板</f:a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>								
							</div>	
						</div>
						
						<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
								<ul class="row">
									<li><span class="label">模板名:</span><input type="text" name="Q_templateName_SL"  class="inputText" value="${param['Q_templateName_SL']}"/></li>
									<li><span class="label">模板类型:</span>
									<select name="Q_templateType_S" value="${param['Q_templateType_S']}">
										<option value="">全部</option>
										<option value="main" <c:if test="${param['Q_templateType_S'] == 'main'}">selected</c:if>>主表模板</option>
										<option value="subTable" <c:if test="${param['Q_templateType_S'] == 'subTable'}">selected</c:if>>子表模板</option>
										<option value="macro" <c:if test="${param['Q_templateType_S'] == 'macro'}">selected</c:if>>宏模板</option>
										<option value="list" <c:if test="${param['Q_templateType_S'] == 'list'}">selected</c:if>>列表模板</option>
										<option value="detail" <c:if test="${param['Q_templateType_S'] == 'detail'}">selected</c:if>>明细模板</option>
										<option value="tableManage" <c:if test="${param['Q_templateType_S'] == 'tableManage'}">selected</c:if>>表管理模板</option>
										<option value="dataTemplate" <c:if test="${param['Q_templateType_S'] == 'dataTemplate'}">selected</c:if>>数据模板</option>
									</select></li>
								</ul>
							</form>
						</div>
					</div>
				</div>
				<div class="panel-body">
					
					
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="bpmFormTemplateList" id="bpmFormTemplateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk"  name="templateId" value="${bpmFormTemplateItem.templateId}"  <c:if test="${bpmFormTemplateItem.canEdit==0}">disabled="disabled"</c:if>  >
							</display:column>
							<display:column property="alias" title="别名" sortable="true" sortName="alias" style="text-align:left"></display:column>
							<display:column property="templateName" title="模板名" sortable="true" sortName="templateName" style="text-align:left"></display:column>
							<display:column title="模板类型" style="text-align:left">
								<c:if test="${bpmFormTemplateItem.templateType=='list'}">列表模板</c:if>
								<c:if test="${bpmFormTemplateItem.templateType=='detail'}">明细模板</c:if>
								<c:if test="${bpmFormTemplateItem.templateType=='subTable'}">子表模板</c:if>
								<c:if test="${bpmFormTemplateItem.templateType=='main'}">主表模板</c:if>
								<c:if test="${bpmFormTemplateItem.templateType=='macro'}">宏模板</c:if>
								<c:if test="${bpmFormTemplateItem.templateType=='tableManage'}">表管理模板</c:if>
								<c:if test="${bpmFormTemplateItem.templateType=='dataTemplate'}">数据模板</c:if>
								<c:if test="${bpmFormTemplateItem.templateType=='queryDataTemplate'}">自定义SQL查询模版</c:if>
							</display:column>
							<display:column title="模板来源" style="text-align:left">
								<c:choose>
									<c:when test="${bpmFormTemplateItem.canEdit==0}"><span class="red">系统模板</span></c:when>
									<c:when test="${bpmFormTemplateItem.canEdit==1}"><span class="green">自定义模板</span></c:when>
								</c:choose>
							</display:column>
							<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">
								<c:choose>
									<c:when test="${bpmFormTemplateItem.canEdit==0}">
										<a  class="link del disabled">删除</a>
										<a  class="link edit disabled">编辑</a>
										<a  class="link backUp disabled" >备份</a>
										
									</c:when>
									<c:otherwise >
										<f:a alias="delTemplateForm" href="del.ht?templateId=${bpmFormTemplateItem.templateId}" css="link del">删除</f:a>
										<f:a  alias="editTemplate" href="edit.ht?templateId=${bpmFormTemplateItem.templateId}" css="link edit">编辑</f:a>
										<a  href="backUp.ht?templateId=${bpmFormTemplateItem.templateId}" class="link backUp" >备份</a>
										
									</c:otherwise>
								</c:choose>
								<a href="get.ht?templateId=${bpmFormTemplateItem.templateId}" class="link detail">明细</a>
								<a href="javascript:;" onclick="copyTemplate('${bpmFormTemplateItem.templateId}','${bpmFormTemplateItem.templateName}','${bpmFormTemplateItem.alias}')"  class="link copy">复制</a>
							</display:column>
						</display:table>
						<hotent:paging tableId="bpmFormTemplateItem"/>
				
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


