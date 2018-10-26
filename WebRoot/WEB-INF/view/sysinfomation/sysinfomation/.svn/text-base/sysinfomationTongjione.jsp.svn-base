<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>子系统内部信息统计管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">子系统内部信息统计管理列表</span>
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
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">子系统名称:</span><input type="text" name="Q_sysName_S"  class="inputText" />
						<span class="label">子系统id:</span><input type="text" name="Q_sysId_S"  class="inputText" />
						<span class="label">新增与修改类操作非规范元素个数:</span><input type="text" name="Q_nmopNonElemNum_S"  class="inputText" />
						<span class="label">新增与修改类操作元素总个数:</span><input type="text" name="Q_nmopElemTotal_S"  class="inputText" />
						<span class="label">业务成熟度:</span><input type="text" name="Q_workSubsysMaturity_S"  class="inputText" />
						<span class="label">信息规范程度:</span><input type="text" name="Q_infoStandGrade_S"  class="inputText" />
						<span class="label">知识型操作个数:</span><input type="text" name="Q_knowledgeOpnum_S"  class="inputText" />
						<span class="label">知识型操作自动实现的操作数量:</span><input type="text" name="Q_knowledgeAutoOpnum_S"  class="inputText" />
						<span class="label">知识结构化比例:</span><input type="text" name="Q_knowledStrucktGrade_S"  class="inputText" />
						<span class="label">本地信息量:</span><input type="text" name="Q_local_S"  class="inputText" />
						<span class="label">利用外部信息量:</span><input type="text" name="Q_outGov_S"  class="inputText" />
						<span class="label">外部利用信息量:</span><input type="text" name="Q_inGov_S"  class="inputText" />
						<span class="label">利用公共信息量:</span><input type="text" name="Q_outPub_S"  class="inputText" />
						<span class="label">公共利用信息量:</span><input type="text" name="Q_inPub_S"  class="inputText" />
						<span class="label">本地服务量:</span><input type="text" name="Q_flocal_S"  class="inputText" />
						<span class="label">利用外部服务量:</span><input type="text" name="Q_foutGov_S"  class="inputText" />
						<span class="label">外部利用服务量:</span><input type="text" name="Q_finGov_S"  class="inputText" />
						<span class="label">利用公共服务量:</span><input type="text" name="Q_foutPub_S"  class="inputText" />
						<span class="label">公共利用服务量:</span><input type="text" name="Q_finPub_S"  class="inputText" />
						<span class="label">业务架构开放程度:</span><input type="text" name="Q_busFrameOpenGrade_S"  class="inputText" />
						<span class="label">节点数量:</span><input type="text" name="Q_activityNum_S"  class="inputText" />
						<span class="label">马尔科夫节点数量:</span><input type="text" name="Q_markActivityNum_S"  class="inputText" />
						
						<span class="label">响应速度:</span><input type="text" name="Q_res_speed_S"  class="inputText" />
						<span class="label">服务周期:</span><input type="text" name="Q_ser_cycle_S"  class="inputText" />
						<span class="label">数据增量:</span><input type="text" name="Q_data_incre_S"  class="inputText" />
						<span class="label">通信传输量:</span><input type="text" name="Q_com_trans_S"  class="inputText" />
						<span class="label">平均发生频度:</span><input type="text" name="Q_ave_freq_occur_S"  class="inputText" />
						<span class="label">上午高峰期发生频度:</span><input type="text" name="Q_peak_freq_S"  class="inputText" />
						<span class="label">代码行数:</span><input type="text" name="Q_code_line_num_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysinfomationList" id="sysinfomationItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysinfomationItem.id}">
				</display:column>
				<display:column property="sysName" title="子系统名称" sortable="true" sortName="F_SYSNAME"></display:column>
				<display:column property="sysId" title="子系统id" sortable="true" sortName="F_SYSID"></display:column>
				<display:column property="nmopNonElemNum" title="新增与修改类操作非规范元素个数" sortable="true" sortName="F_NMOPNONELEMNUM"></display:column>
				<display:column property="nmopElemTotal" title="新增与修改类操作元素总个数" sortable="true" sortName="F_NMOPELEMTOTAL"></display:column>
				<display:column property="workSubsysMaturity" title="业务成熟度" sortable="true" sortName="F_WORKSUBSYSMATURITY"></display:column>
				<display:column property="infoStandGrade" title="信息规范程度" sortable="true" sortName="F_INFOSTANDGRADE"></display:column>
				<display:column property="knowledgeOpnum" title="知识型操作个数" sortable="true" sortName="F_KNOWLEDGEOPNUM"></display:column>
				<display:column property="knowledgeAutoOpnum" title="知识型操作自动实现的操作数量" sortable="true" sortName="F_KNOWLEDGEAUTOOPNUM"></display:column>
				<display:column property="knowledStrucktGrade" title="知识结构化比例" sortable="true" sortName="F_KNOWLEDSTRUCKTGRADE"></display:column>
				<display:column property="local" title="本地信息量" sortable="true" sortName="F_LOCAL"></display:column>
				<display:column property="outGov" title="利用外部信息量" sortable="true" sortName="F_OUTGOV"></display:column>
				<display:column property="inGov" title="外部利用信息量" sortable="true" sortName="F_INGOV"></display:column>
				<display:column property="outPub" title="利用公共信息量" sortable="true" sortName="F_OUTPUB"></display:column>
				<display:column property="inPub" title="公共利用信息量" sortable="true" sortName="F_INPUB"></display:column>
				<display:column property="flocal" title="本地服务量" sortable="true" sortName="F_FLOCAL"></display:column>
				<display:column property="foutGov" title="利用外部服务量" sortable="true" sortName="F_FOUTGOV"></display:column>
				<display:column property="finGov" title="外部利用服务量" sortable="true" sortName="F_FINGOV"></display:column>
				<display:column property="foutPub" title="利用公共服务量" sortable="true" sortName="F_FOUTPUB"></display:column>
				<display:column property="finPub" title="公共利用服务量" sortable="true" sortName="F_FINPUB"></display:column>
				<display:column property="busFrameOpenGrade" title="业务架构开放程度" sortable="true" sortName="F_BUSFRAMEOPENGRADE"></display:column>
				<display:column property="activityNum" title="节点数量" sortable="true" sortName="F_ACTIVITYNUM"></display:column>
				<display:column property="markActivityNum" title="马尔科夫节点数量" sortable="true" sortName="F_MARKACTIVITYNUM"></display:column>
				
				<display:column property="res_speed" title="响应速度" sortable="true" sortName="F_RESSPEED"></display:column>
				<display:column property="ser_cycle" title="服务周期" sortable="true" sortName="F_SERCYCLE"></display:column>
				<display:column property="data_incre" title="数据增量" sortable="true" sortName="F_DATA_NCRE"></display:column>
				<display:column property="com_trans" title="通信传输量" sortable="true" sortName="F_COMTRANS"></display:column>
				<display:column property="ave_freq_occur" title="平均发生频度" sortable="true" sortName="F_AVEFREQOCCUR"></display:column>
				<display:column property="peak_freq" title="上午高峰期发生频度" sortable="true" sortName="F_PEAKFREQ"></display:column>
				<display:column property="code_line_num" title="代码行数" sortable="true" sortName="F_CODELINENUM"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${sysinfomationItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${sysinfomationItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${sysinfomationItem.id}" class="link detail"><span></span>明细</a>
			</display:column>
			</display:table>
			<hotent:paging tableId="sysinfomationItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


