<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="hotent" uri="http://www.jee-soft.cn/paging" %>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
<head>
<title>考勤计算管理</title>
<f:link href="Aqua/css/ligerui-all.css"></f:link>
<script type="text/javascript" src="${ctx}/js/dynamic.jsp"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
<f:js pre="js/lang/common" ></f:js>
<f:js pre="js/lang/js" ></f:js>
<script type="text/javascript" src="${ctx}/js/util/util.js"></script>
<script type="text/javascript" src="${ctx}/js/util/json2.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/ligerui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/displaytag.js" ></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js" ></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerResizable.js" ></script>
<script type="text/javascript" src="${ctx}/js/lg/util/DialogUtil.js" ></script>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/font-awesome/font-awesome.min.css"></link>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/bootstrap/bootstrap.min.css"></link>
<f:link href="jqGrid/jquery-ui.css" ></f:link>
<f:link href="jqGrid/ui.jqgrid.css" ></f:link>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/fullcalendar/fullcalendar.min.css"></link>
<!--[if lte IE 8]>
	<script type="text/javascript" src="${ctx}/js/bootstrap/html5shiv.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/bootstrap/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${ctx}/js/fullcalendar/moment.min.js"></script>
<script type="text/javascript" src="${ctx}/js/fullcalendar/fullcalendar.min.js"></script>
<script type="text/javascript" src="${ctx}/js/lang/fullcalendar/zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/jqGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jqGrid/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsAttenceCalculate.js"></script>
<style type="text/css">
/*-----------------------------------------------------------------------------------*/
/*	toolbar-box 顶部按钮
/*-----------------------------------------------------------------------------------*/
.toolbar-box {
	clear: both;
	margin-top: 5px;
	margin-bottom: 5px;
	border: 1px solid #ddd;
	padding: 0px;
}

.toolbar-box .toolbar-head {
	/**margin-bottom: 10px;*/
	border-bottom: 1px solid #ddd;
	min-height: 45px;
	background-color: #f4f4f4;
}

.toolbar-box .toolbar-head .buttons {
	display: inline-block;
	padding: 0;
	margin-left: 10px;
	margin-bottom: 7px;
	float: left;
}

.toolbar-box .buttons>a,.toolbar-box .buttons .btn-group>a {
	margin-top: 7px !important;
}

.toolbar-box .toolbar-head .tools {
	display: inline-block;
	padding: 0;
	float: right;
	min-height: 45px;
}

.toolbar-box .toolbar-head .tools>a {
	display: inline-block;
	font-size: 14px;
	line-height: 24px;
	text-decoration: none;
	color: #c4c4c4;
	margin-right: 5px;
	margin-top: 5px;
}

.toolbar-box .toolbar-head .tools>a :hover {
	color: #ababab;
}

.toolbar-box .toolbar-head .tools .label {
	margin-right: 5px;
}

.toolbar-box .toolbar-body{
	margin-top: 10px;
}
.toolbar-box .toolbar-body.bg {
	border-radius: 0 0 4px 4px;
	padding: 10px;
	background-color: #FFFFFF;
	border-left: 1px solid #c4c4c4;
	border-right: 1px solid #c4c4c4;
	border-bottom: 1px solid #c4c4c4;
	margin-top: -15px;
}
.toolbar-border-bottom{
	border-bottom: 0  !important;
}

.toolbar-box:after,.toolbar-box:before {
	display: table;
	color: "";
}

.panel-title{
	margin: 5px 15px 0px 15px;
	border-bottom: 1px solid #e5e5e5;
	background-color: #f5f5f5;
	min-height: 41px;
	line-height: 40px;
	padding: 0px;
	padding-left: 5px;
}
/*-----------------------------------------------------------------------------------*/
/*	panel-toolbar
/*-----------------------------------------------------------------------------------*/

.panel-toolbar {
	clear: both;
	padding: 0px;
	margin:5px 15px 0px 15px;
	border: 1px solid #ddd;
	min-height: 45px;
	background-color: #f4f4f4;
}

.panel-toolbar .buttons {
	display: inline-block;
	padding: 0;
	margin: 5px 0 5px 10px;
}

.panel-toolbar.buttons>a {
	margin-top: 5px !important;
}

.panel-toolbar:after,.panel-toolbar:before {
	display: table;
	color: "";
}

.panel-toolbar.stuckMenu{
	z-index: 1000;
	width: 97.8%;
}
/*-----------------------------------------------------------------------------------*/
/*	panel-form
/*-----------------------------------------------------------------------------------*/
.panel-form{
	border: 1px solid #ddd;
	padding: 5px;
}
.panel-form:first-child{
	padding-top: 10px;
}

/*-----------------------------------------------------------------------------------*/
/*	search-form
/*-----------------------------------------------------------------------------------*/
.search-form {
	margin-left: 5px;
}

.search-form>ul {
	padding-left: 0;
	list-style: none;
}

.search-form ul>li {
	display: inline-block;
	padding: 3px 5px 0 5px ;
}

.search-form ul>li:first-child {
	padding-left: 5px;
}

.search-form li>span:not(.button-div) {
	display: inline-block;
	margin-bottom: -5px;
	margin-right: 5px;
	font-weight: bold;
	width: 80px;
	text-align: right;
	line-height: 20px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
<div class="panel">
	<div  class="panel-search">
			<div class="toolbar-box">
				<div class="toolbar-head clearfix">
					<!-- 顶部按钮 -->
					<div class="buttons"> 		
						<a class="btn btn-sm btn-primary fa fa-search" href="javascript:void(0);" ><span>查询</span></a>
						<a class="btn btn-sm btn-primary allCalculate"   href="javascript:void(0);" ><span>计算全部</span></a>
				        <a class="btn btn-sm btn-primary  calculateSelect"   href="javascript:void(0);"   ><span>计算选中行</span></a>	
				        <a class="btn btn-sm btn-primary summary "   href="javascript:void(0);"   ><span>汇总显示</span></a>	
				        <a class="btn btn-sm btn-primary detail"   href="javascript:void(0);"   ><span>明细显示</span></a>	
					</div>
				</div>
				<!-- #查询条件-->
				<div class="toolbar-body" >
					<form  id="searchForm" class="search-form" >
						<ul>
							<li>
								<span>组织:</span>
								<input id="orgId" type="hidden"/>
								<input  id="orgName"  class="inputText"  readonly="readonly" />
								<a class="btn btn-sm btn-primary fa fa-search-plus" href="javascript:selectOrg();" ><span></span></a>
							</li>
							<li>
								<span>姓名:</span>
								<input id="userId" type="hidden"/>
								<input  id="userName"  class="inputText"/>
								<a class="btn btn-sm btn-primary fa fa-search-plus" href="javascript:selectUser();" ><span></span></a>
							</li>
						</ul>
						<ul>
							<li>
								<span>考勤制度:</span>
									<input type="hidden" id="attendPolicy" class="inputText" value="${atsAttencePolicy.id }"/>
									<input id="attendPolicyName" class="inputText" readonly="readonly" value="${atsAttencePolicy.name }"/>
									<a class="btn btn-sm btn-primary fa fa-search-plus" href="javascript:selectAttendPolicy();" ><span></span></a>
							</li>
							<li>
								<span>考勤周期:</span>
								<select id="attenceCycle" class="inputText" >
									<c:forEach items="${cycleList}" var="cycle">
										<option startTime='<fmt:formatDate value="${cycle.startTime}" pattern="yyyy-MM-dd"/>'
												endTime='<fmt:formatDate value="${cycle.endTime}" pattern="yyyy-MM-dd"/>'  value="${cycle.id }">${cycle.memo }</option>
									</c:forEach>
								</select>
							</li>
						</ul>
					</form>
				</div><!--/ 查询条件-->
			</div>
		</div><!--/ 操作、查询-->
	
		<ul class="nav nav-tabs">
		  <li class="active" action="1"><a href="javascript:void(0);" >已计算人员</a></li>
		  <li  action="2"><a href="javascript:void(0);" >未计算人员</a></li>
		</ul>
		<div class="panel-body">
    				<div id="dataGrid" >请设置考勤制度</div>
    	</div>	
	
		</div><!-- end of panel-body -->
</div>		
</body>
</html>


