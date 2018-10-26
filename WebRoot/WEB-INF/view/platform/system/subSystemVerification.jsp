
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>系统管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ImportExportXmlUtil.js"></script>
<script type="text/javascript">
	function ImportXml(systemId){
		var url=__ctx +"/platform/system/subSystem/import.ht";
		
		var conf=$.extend({},{dialogWidth:550 ,dialogHeight:200,help:0,status:0,scroll:0,center:1});
		var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
			+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
		
		url=url.getNewUrl();
		//var rtn=window.showModalDialog(url,{systemId:systemId},winArgs);
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:conf.dialogHeight,
			width: conf.dialogWidth,
			title : '子系统管理',
			url: url, 
			isResize: true,
			//自定义参数
			data: {systemId:systemId}
		});
		
	}
	
	
	
	
	function exportChart(){
		var bpmDefIds = ImportExportXml.getChkValue('pk');
		if (bpmDefIds ==''){
			$.ligerDialog.warn('还没有选择,请选择一项流程定义!','提示信息');
			return ;
		}
		var url=__ctx + "/platform/bpm/bpmDefinition/exportchart.ht?bpmDefIds="+bpmDefIds;
		window.open(url);
		
		alert("aaaa");
		
	}
	
	function box1(){
			
		//var id=$("#id").val();//有时候好事有时候不好使
		var ids=ImportExportXml.getChkValue('pk');
		if (ids=='')
		{
			$.ligerDialog.warn('还没有选择,请选择一项子系统!','提示信息');
			return ;
		}
		
		var url=__ctx + "/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/box1.ht?ids="+ids;
		window.open(url);//跳转到下一个页面
			//window.location.href = "${ctx}/wTbaSubsys/wTbaSubsys/wTbaSubsys/tongji.ht?id="+id;	//再笨页面跳转
		//window.location.href = "${ctx}/platform/system/subSystem/tongji.ht?id="+id;
		}
			
			
		function box(){
		var ids=ImportExportXml.getChkValue('pk');
		if (ids=='')
		{
			$.ligerDialog.warn('还没有选择,请选择一项子系统!','提示信息');
			return ;
		}
		    var url=__ctx + "/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/box.ht?ids="+ids;
		    window.open(url);//跳转到下一个页面
			//window.location.href = "${ctx}/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/box.ht?id="+id;	//再笨页面跳转
		
		}	
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">业务逻辑校验</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<c:if test="${status eq  0}">
					   <div class="group"><a class="link export" onclick="box1()"><span></span>进行校验</a></div>	
					</c:if>
					<c:if test="${status eq  1}">
					   <div class="group"><a class="link export" onclick="box()"><span></span>进行统计</a></div>		
					</c:if>
					<c:if test="${status eq  2}">
						<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
					</c:if>	
					<c:if test="${status eq  3}">
						<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
					</c:if>	
				             </div>	
				             
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="verfication.ht">
					<ul class="row">
						<li><span class="label">名称:</span>
						
						
						<input type="text" name="Q_sysName_SL"  class="inputText"  value="${param['Q_sysName_SL']}"/>
						
						
						</li>
						<li><span class="label">别名:</span><input type="text" name="Q_alias_SL"  class="inputText"  value="${param['Q_alias_SL']}"/></li>
	
						<div class="row_date">
						<li><span class="label">创建时间:</span><input  name="Q_begincreatetime_DL"  class="inputText date"  value="${param['Q_begincreatetime_DL']}"/></li>
						<li><span class="label">至</span><input  name="Q_endcreatetime_DG" class="inputText date"  value="${param['Q_endcreatetime_DG']}"/></li>
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
			    <display:table name="subSystemList" id="subSystemItem" requestURI="verification.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="id" value="${subSystemItem.systemId}">
					</display:column>
					<display:column property="sysName" title="名称" sortName="sysName" ></display:column>
					<display:column property="alias" title="别名" sortName="alias" ></display:column>
	
					<display:column  title="创建时间" sortName="createtime">
						<fmt:formatDate value="${subSystemItem.createtime}" pattern="yyyy-MM-dd"/>
					</display:column>
					
					<display:column title="本地系统" sortable="true" sortName="isLocal" style="text-align:center">
						<c:choose>
							<c:when test="${subSystemItem.isLocal eq 0}"><span class="red">否</span></c:when>
							<c:otherwise><span class="green">是</span></c:otherwise>
						</c:choose>
					</display:column>
					
					<display:column title="系统类别" sortable="true" sortName="systemType" style="text-align:center">
						<c:choose>
							<c:when test="${subSystemItem.systemType eq 0}"><span class="red">系统类</span></c:when>
							<c:otherwise><span class="green">业务类</span></c:otherwise>
						</c:choose>
					</display:column>
					
					<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">
						<c:choose>
							<c:when test="${subSystemItem.allowDel==1 }">
								<f:a alias="delSubsystem" href="del.ht?id=${subSystemItem.systemId}" css="link del">删除</f:a>
							</c:when>
							<c:otherwise>
								<a href="javascript:;" class="link del disabled">删除</a>
							</c:otherwise>
						</c:choose>
						
						<a href="edit.ht?id=${subSystemItem.systemId}" class="link edit">编辑</a>
						<a href="exportXml.ht?systemId=${subSystemItem.systemId}" class="link download">导出</a>
						<a href="javascript:;" onclick="ImportXml(${subSystemItem.systemId})" class="link upload">导入</a>
						
					</display:column>
				</display:table>
				<hotent:paging tableId="subSystemItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


