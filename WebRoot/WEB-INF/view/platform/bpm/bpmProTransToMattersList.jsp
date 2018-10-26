<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title>加签(流转)事宜</title>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
</head>
	<script type="text/javascript">
	$(function(){
		$("a[parentTaskId]").each(showResult);	
	});

	function showResult(){
		var parentTaskId=$(this).attr("parentTaskId"),
			assignee=$(this).attr("assignee");
		var template=$("#txtReceiveTemplate").val();
		$(this).qtip({  
			content:{
				text:$lang.tip.loading,
				ajax:{
					url:__ctx +"/platform/bpm/bpmProTransTo/getByAssignee.ht",
					type:"GET",
					data:{parentTaskId:parentTaskId, assignee:assignee},
					success:function(data,status){
						var html=easyTemplate(template,data).toString();
						this.set("content.text",html);
					}
				},
				title:{
					text: '流转人'			
				}
			},
		        position: {
		        	at:'top left',
		        	target:'event',
		        	
					viewport:  $(window)
		        },
		        show:{
		        	event:"click",
   			     	solo:true
		        },   			     	
		        hide: {
		        	event:'unfocus',
		        	fixed:true
		        },  
		        style: {
		       	  classes:'ui-tooltip-light ui-tooltip-shadow'
		        } 			    
	 	});	
	};
	
	function cancel(taskId, userId) {
		var params = {taskId:taskId, userId:userId};
		var url=__ctx + '/platform/bpm/bpmProTransTo/cancelDialog.ht';
		var winArgs="dialogWidth=550px;dialogHeight=300px;help=0;status=0;scroll=0;center=1";
		url=url.getNewUrl();
		/* var rtn=window.showModalDialog(url,params,winArgs);
		if(rtn=="ok"){
			location.href=location.href.getNewUrl();
		} */
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:300,
			width: 550,
			title : '取消',
			url: url, 
			isResize: true,
			//自定义参数
			params: params,
			sucCall:function(rtn){
				if(rtn=="ok"){
					location.href=location.href.getNewUrl();
				}
			}
		});
	};
	
	function addTransTo(taskId){
		var url=__ctx + '/platform/bpm/bpmProTransTo/addDialog.ht?taskId='+taskId+'&curUserId=${curUserId}';
		var winArgs="dialogWidth=550px;dialogHeight=400px;help=0;status=0;scroll=0;center=1";
		url=url.getNewUrl();
		/* var rtn=window.showModalDialog(url,'',winArgs);
		if(rtn=="ok"){
			location.href=location.href.getNewUrl();
		} */
		
		DialogUtil.open({
			height:400,
			width: 550,
			title : '添加',
			url: url, 
			isResize: true,
			//自定义参数
			sucCall:function(rtn){
				if(rtn=="ok"){
					location.href=location.href.getNewUrl();
				}
			}
		});
	};

	function showDetail(obj){
		var url = $(obj).attr("action");
		jQuery.openFullWindow(url);
	};
	
	</script>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">加签(流转)事宜</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a href="javascript:;" class="link reset" onclick="$.clearQueryForm();"><span></span>重置</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="mattersList.ht">
					<ul class="row">
						<input type="hidden" name="nodePath" value="${param['nodePath']}" title="流程分类节点路径"></input>
						<li><span class="label">请求标题:</span><input type="text" name="Q_subject_SUPL"  class="inputText"   value="${param['Q_subject_SUPL']}"/></li>
						<li><span class="label">流程名称:</span><input type="text" name="Q_processName_SUPL"  class="inputText"   value="${param['Q_processName_SUPL']}"/></li>
						<div class="row_date">
						<li><span class="label">创建时间&nbsp;从:</span><input  name="Q_begincreatetime_DL"  class="inputText datePicker" datetype="1" value="${param['Q_begincreatetime_DL']}" /></li>
						<li><span class="label">至: </span><input  name="Q_endcreatetime_DG" class="inputText datePicker"  datetype="2"  value="${param['Q_endcreatetime_DG']}"/></li>
						</div>
					</ul>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="bpmProTransToList" id="bpmProTransToItem" requestURI="mattersList.ht" cellpadding="1" cellspacing="1"  class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${bpmProTransToItem.taskId}"/>
				</display:column>
				<display:column  titleKey="请求标题" sortable="true" sortName="subject">
					<a name="processDetail" onclick="showDetail(this)" href="#${bpmProTransToItem.id}"  action="${ctx}/platform/bpm/processRun/info.ht?link=1&runId=${bpmProTransToItem.runId}" title='${bpmProTransToItem.subject}' >${f:subString(bpmProTransToItem.subject)}</a>
				</display:column>
				<display:column property="processName" titleKey="流程名称" sortable="true" sortName="processName" style="text-align:center;"></display:column>
				<display:column titleKey="流转人" sortable="true" sortName="assigneeName" style="text-align:center;">
					<a href="javascript:;" parentTaskId="${bpmProTransToItem.taskId}" assignee="${bpmProTransToItem.assignee}">流转人</a>
				</display:column>
				<display:column  titleKey="创建时间" sortable="true" sortName="cratetime" style="text-align:center;">
					<fmt:formatDate value="${bpmProTransToItem.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>
				<display:column titleKey="管理" media="html" style="width:60px" class="rowOps">
					<a href="javascript:;" onclick="addTransTo(${bpmProTransToItem.taskId})" class="link add">添加流转人</a>
					<a href="showAssignee.ht?taskId=${bpmProTransToItem.taskId}" class="link detail">查看流转人</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmProTransToItem" />
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
	<textarea id="txtReceiveTemplate"  style="display: none;">
	    <div  style="height:200px;width:200px;overflow:auto">
	  		<#list data as obj>
		  		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			  		<tr>
			  			<th>流转人</th>
			  			<td>
			  				<img src="${ctx}/styles/default/images/bpm/user-16.png">&nbsp;
			  				<a target="_blank" href="${ctx}/platform/system/sysUser/get.ht?userId=\${obj.userId}&hasClose=true">\${obj.userName}</a>
		  				</td>
			  		</tr>
			  		<tr>
			  			<th>状态</th>
			  			<td>
			  				<#if (obj.status==1) >
			  					<span class="red">正在审批</span>
			  				<#else>
			  					<span class="green">已审批</span>	
			  				</#if>
			  			</td>
			  		</tr>
			  		<#if (obj.status==1) >
				  		<tr>
				  			<th>管理</th>
				  			<td><a href="javascript:;" class="link cancel" onclick="cancel('\${obj.parentTaskId}', '\${obj.userId}')">取消流转</a></td>
				  		</tr>
				  	</#if>
		  		</table>
		  		<br/>
	  		</#list>
	  	</div>
    </textarea>
</body>
</html>


