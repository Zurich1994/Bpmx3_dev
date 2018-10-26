
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>子系统管理</title>
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
	/*function tongji(){
	//System.out.println("进入tongji函数");
		var action = "tongji.ht";
		var $aryId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
		
		//没有选择记录
		if($aryId.length == 0){
		System.out.println("进入tongji函数"+$aryId.length);
			$.ligerDialog.warn("请选择记录！");
			return false;
		}
		
		var tongjiId ="" ;
		$aryId.each(function(){
			var me = $(this)
			tongjiId += me.val()+",";
		});
		
		var keyName = "tongjiId"
		//var url=action +"?" +keyName +"=" +tongjiId ;
		
					var form=new com.hotent.form.Form();
					form.creatForm("form", action);
					form.addFormEl(keyName, tongjiId);
					form.submit(); 
	}*/
	
	
	
	
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
	/*function tongji()
	{
		var id=ImportExportXml.getChkValue('pk');
		if (id=='')
		{
			$.ligerDialog.warn('还没有选择,请选择一项流程定义!','提示信息');
			return ;
		}
		//var url=__ctx +"/platform/system/subSystem/tongji.ht?xuanzhongid="+xuanzhongId;
		//var url=__ctx + "/platform/system/subSystem/tongji.ht?id="+id;
		var url=__ctx + "/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/tongji.ht?id="+id;
		
		window.location.href = "${ctx}/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/tongji.ht";	
		
	

		
	}*/
	function tongji(){
			
		//var id=$("#id").val();//有时候好事有时候不好使
		var id=ImportExportXml.getChkValue('pk');
		if (id=='')
		{
			$.ligerDialog.warn('还没有选择,请选择一项流程定义!','提示信息');
			return ;
		}
		var url=__ctx + "/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/tongji.ht?id="+id;
		window.open(url);//跳转到下一个页面
			//window.location.href = "${ctx}/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/tongji.ht?id="+id;	//再笨页面跳转
		//window.location.href = "${ctx}/platform/system/subSystem/tongji.ht?id="+id;
		}
	
	
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">子系统管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link export" onclick="tongji()"><span></span>业务逻辑校验</a></div>		
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label">名称:</span><input type="text" name="Q_sysName_SL"  class="inputText"  value="${param['Q_sysName_SL']}"/></li>
						<li><span class="label">别名:</span><input type="text" name="Q_alias_SL"  class="inputText"  value="${param['Q_alias_SL']}"/></li>
						
						<li><span class="label">允许删除:</span>
						<select name="Q_allowDel_SN" class="select" >
							<option value="">全部</option>
							<option value="1" <c:if test="${param['Q_allowDel_SN'] == 1}">selected</c:if>>是</option>
							<option value="0" <c:if test="${param['Q_allowDel_SN'] == 0}">selected</c:if>>否</option>
						</select>
						</li>
						<li>
						<span class="label">是否激活:</span><select name="Q_isActive_SN" class="select" >
							<option value="">全部</option>
							<option value="1" <c:if test="${param['Q_isActive_SN'] == 1}">selected</c:if>>是</option>
							<option value="0" <c:if test="${param['Q_isActive_SN'] == 0}">selected</c:if>>否</option>
						</select></li>
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
			    <display:table name="subSystemList" id="subSystemItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="id" value="${subSystemItem.systemId}">
					</display:column>
					<display:column property="sysName" title="名称" sortable="true" sortName="sysName" ></display:column>
					<display:column property="alias" title="别名" sortable="true" sortName="alias" ></display:column>
					
					<display:column title="系统图标" sortName="logo">
						<img alt="" src="${ctx }/${subSystemItem.logo}" >
					</display:column>
					
					
					<display:column property="defaultUrl" title="首页地址" sortable="true" sortName="defaultUrl" ></display:column>
					<display:column  title="创建时间" sortable="true" sortName="createtime">
						<fmt:formatDate value="${subSystemItem.createtime}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column title="允许删除" sortable="true" sortName="allowDel" style="text-align:center">
						<c:choose>
							<c:when test="${subSystemItem.allowDel eq 1}"><span class="green">是</span></c:when>
							<c:when test="${subSystemItem.allowDel eq 0}"><span class="red">否</span></c:when>
							<c:otherwise><span class="red">未设定</span></c:otherwise>
						</c:choose>
					</display:column>
					<display:column title="是否激活" sortable="true" sortName="isActive" style="text-align:center">
						<c:choose>
							<c:when test="${subSystemItem.isActive eq 1}"><span class="green">是</span></c:when>
							<c:when test="${subSystemItem.isActive eq 0}"><span class="red">否</span></c:when>
							<c:otherwise><span class="red">未设定</span></c:otherwise>
						</c:choose>
					</display:column>
					<display:column title="本地系统" sortable="true" sortName="isLocal" style="text-align:center">
						<c:choose>
							<c:when test="${subSystemItem.isLocal eq 0}"><span class="red">否</span></c:when>
							<c:otherwise><span class="green">是</span></c:otherwise>
						</c:choose>
					</display:column>
					<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">
						<c:choose>
							<c:when test="${subSystemItem.allowDel==1 }">
								<f:a alias="delSubsystem" href="del.ht?id=${subSystemItem.systemId}" css="link del">删除</f:a>
							</c:when>
							<c:otherwise>
								<a href="javascript:;" class="link del">删除</a>
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


