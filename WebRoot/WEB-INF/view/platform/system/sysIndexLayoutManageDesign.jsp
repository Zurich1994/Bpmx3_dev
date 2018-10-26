
<%--
	time:2015-03-18 15:40:13
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>布局设计管理</title>
<%@include file="/commons/include/home.jsp"%>
	<f:link href="hotent/layoutit.css"></f:link>
	<script type="text/javascript" src="${ctx }/js/jquery/plugins/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery/plugins/jquery.htmlClean.js"></script>
	<script type="text/javascript" src="${ctx }/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${ctx }/js/ckeditor/ckeditor_indexconfig.js"></script>
	<script type="text/javascript" src="${ctx }/js/hotent/index/indexLayout.js"></script>
</head>
<body class="edit">

	<div class="navbar navbar-default navbar-fixed-top navbar-layoutit">
    <div class="navbar-header">
      <button data-target=".navbar-collapse" data-toggle="collapse" class="pull-right navbar-toggle  glyphicon glyphicon-align-justify" type="button" style="margin-top: 0;padding: 2px 10px;">
      </button>
      <a class="navbar-brand" href="javascripts:void(0)">首页可视化布局<span class="label label-default">BETA</span></a>
    </div>
    <div class="collapse navbar-collapse">
      <ul class="nav pull-right" id="menu-layoutit">
        <li>
          
          <div class="btn-group">	
            <button class="btn  btn-xs btn-primary" id="button-save-modal"  role="button" data-toggle="modal" d data-target="#saveModal"><i class="glyphicon-floppy-disk glyphicon"></i> 保存</button>
            <button class="btn  btn-xs btn-primary"   type="button" id="button-download-modal" data-target="#downloadModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-console"></i> 源码</button>
            <button class="btn  btn-xs btn-primary" href="#clear" id="clear" ><i class="glyphicon-trash glyphicon"></i> 清空</button>
          </div>
          <div class="btn-group">
				<button class="btn  btn-xs btn-primary" href="#undo" id="undo"><i class="glyphicon glyphicon-arrow-left"></i>撤销</button>
				<button class="btn  btn-xs btn-primary" href="#redo" id="redo"><i class="glyphicon glyphicon-arrow-right"></i>重做</button>
			</div>
		<div class="btn-group btn-donate pull-right" data-toggle="buttons-radio">
            <button type="button" id="edit" class="active btn  btn-xs btn-primary"><i class="glyphicon glyphicon-edit "></i> 编辑</button>
            <button type="button" class="btn btn-xs  btn-primary" id="devpreview"><i class="glyphicon-eye-close glyphicon"></i> 布局编辑</button>
            <button type="button" class="btn  btn-xs btn-primary" id="sourcepreview"><i class="glyphicon-eye-open glyphicon"></i> 预览</button>
          </div>
        </li>
      </ul>
    </div><!--/.navbar-collapse -->
	</div><!--/.navbar-fixed-top -->
	
	<div class="container">
		<div class="row">
	
		<div class="sidebar-nav" >
			<div class="sidebar-scroller">
			<!-- 左侧菜单 -->		
			<ul class="nav nav-list accordion-group">
				<li class="nav-header">
					<i class="glyphicon-plus glyphicon"></i>  布局设置
					 <div class="pull-right popover-info"><i class="glyphicon glyphicon-question-sign"></i> 
					 <div class="popover fade right"><div class="arrow"></div> 
					 	<h3 class="popover-title">提示：</h3> 
					 	<div class="popover-content">在这里设置首页的布局, 包含多种排版形式，可任意组合多种不同的排版布局风格。 </div></div> 
					 </div> 
				</li>
				<li class="rows" id="estRows">
				
					<c:forEach items="${layoutList}" var="layout">
							<div class="lyrow">
									<a href="#close" class="remove label label-danger"><i class="glyphicon-remove glyphicon"></i> 删除</a>
									<span class="drag label label-default"><i class="glyphicon glyphglyphicon glyphicon-move"></i> 拖动</span>
									<div class="preview"><input type="text" value="${layout.name }" readonly="readonly"  class="form-control"></div>
									<div class="view">
										<div class="row clearfix">
											${layout.templateHtml }
										</div>
									</div>
								</div>
					</c:forEach>
			
				</li>
			</ul>
			<!-- 布局end-->	

			<!-- //首页栏目-->	
			<c:forEach items="${columnMap}" var="colMap">
			
			<ul class="nav nav-list accordion-group">
				<li class="nav-header"><i class="glyphicon glyphicon-plus"></i>${colMap.key }
				 <div class="pull-right popover-info"><i class="glyphicon glyphicon-question-sign "></i> 
				 <div class="popover fade right"><div class="arrow"></div> 
					<h3 class="popover-title">提示：</h3> 
					<div class="popover-content">这里提供了首页的一些可用栏目，包含的待办事项、个人信息等组件，可任意组合搭配出您喜欢的排版形式。</div></div> 
				 </div>
				</li>
				<li class="boxes" id="elmBase">
					<c:forEach items="${colMap.value}" var="column">
						<div class="box box-element">
							<a href="#close" class="remove label label-danger"><i class="glyphicon glyphicon-remove"></i> 删除</a>
							<span class="drag label label-default"><i class="glyphicon glyphglyphicon glyphicon-move"></i> 拖动</span>
							<div class="preview" title="${column.name }">${column.name }</div>
							<div class="view">
								${column.templateHtml}
							
							</div>
						</div>
					</c:forEach>
				
				</li>
			</ul>
			</c:forEach>
			<!-- //首页栏目end -->	
			<!--  常用样式 -->
			<ul class="nav nav-list accordion-group">
				<li class="nav-header"><i class="glyphicon glyphicon-plus"></i> 常用样式
				 <div class="pull-right popover-info"><i class="glyphicon glyphicon-question-sign "></i> 
				 <div class="popover fade right"><div class="arrow"></div> 
					<h3 class="popover-title">提示：</h3> 
					<div class="popover-content">这里提供了首页的常用的样式。</div></div> 
				 </div>
				</li>
				<li class="boxes" id="commStyle">
						<div class="box box-element">
							<a href="#close" class="remove label label-danger"><i class="glyphicon glyphicon-remove"></i> 删除</a>
							<span class="drag label label-default"><i class="glyphicon glyphglyphicon glyphicon-move"></i> 拖动</span>
							 <span class="configuration">
							 	 <span class="btn-group"> 
						               <a class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" href="#">边框样式 <span class="caret"></span></a>
						              <ul class="dropdown-menu">
						                <li class="active"><a href="#" rel="hr-dotted">条纹</a></li>
						                <li class=""><a href="#" rel="hr-solid">无样式</a></li>
						              </ul>
					               </span>
							 	  <span class="btn-group"> 
					               <a class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" href="#">宽度<span class="caret"></span></a>
					              <ul class="dropdown-menu">
					                <li class="active"><a href="#" rel="hr10">10</a></li>
					                <li class=""><a href="#" rel="hr2">2</a></li>
					                <li class=""><a href="#" rel="hr4">4</a></li>
					                <li class=""><a href="#" rel="hr6">6</a></li>
					                <li class=""><a href="#" rel="hr8">8</a></li>
					                <li class=""><a href="#" rel="hr12">12</a></li>
					                <li class=""><a href="#" rel="hr14">14</a></li>
					                <li class=""><a href="#" rel="hr16">16</a></li>
					                <li class=""><a href="#" rel="hr18">18</a></li>
					                <li class=""><a href="#" rel="hr20">20</a></li>
					              </ul>
				             	 </span>
							 </span>
							<div class="preview">水平线</div>
							<div class="view">
								<div class="hr hr10 hr-dotted"></div>
							</div>
						</div>
				</li>
			</ul>
			<!--  常用样式 end-->
        <!-- 左侧菜单 end -->	
		</div>	
		
		</div>	
		
		<!--内容区域 开始-->
		<div class="index-layout"></div>
		<!--内容区域 结束-->
		<div id="download-layout">
			<!-- 可编辑内容区域 开始 -->
			<div class="container"></div>
			<!-- 可编辑内容区域 结束 -->
		</div>
	</div>
	<!--/row-->

</div><!--/.fluid-container-->
  	<textarea id= "html" rows="0" cols="0" style="display: none;">${fn:escapeXml(sysIndexLayoutManage.designHtml )}</textarea>

   <!-- /.modal-dialog -->
	<div class="modal fade" id="downloadModal" tabindex="-1" role="dialog" aria-labelledby="downloadModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="myModalLabel">查看/编辑源代码</h4>
	      </div>
	      <div class="modal-body">
					<p>如果您希望对布局进行二次编辑, 可以直接编辑对应的代码段: </p>
	        <textarea></textarea>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary">保存</button>
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
  	</div>
  	
<div class="modal  fade" role="dialog" id="editorModal" tabindex="-1" role="dialog" aria-labelledby="editorModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
			  <div class="modal-header">
				   <button type="button" class="close" data-dismiss="modal">
				   	<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				   </button>
			    <h4>编辑</h4>
			  </div>
			  <div class="modal-body">
			    <p>
			      <textarea id="contenteditor"></textarea>
			    </p>
			  </div>
			  <div class="modal-footer"> <a id="savecontent" class="btn btn-primary" data-dismiss="modal">保存</a> <a class="btn btn-danger" data-dismiss="modal">关闭</a> </div>

	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
</div>



   <!-- /.modal-dialog -->
	<div class="modal fade" id="saveModal" tabindex="-1" role="dialog" aria-labelledby="saveModal" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" >保存布局</h4>
	      </div>
	      <div class="modal-body">
	      			<input type="hidden" id="id" name="id" value="${sysIndexLayoutManage.id }"/>
				<table class="table table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%">布局名称: </th>
						<td>
						<input type="text" id="name" name="name" value="${sysIndexLayoutManage.name}"  class="inputText" style="width: 80%;" validate="{required:true,maxlength:100}"/>
						</td>
					</tr>
					<tr>
						<th width="20%">布局描述: </th>
						<td>
							<input type="text" id="memo" name="memo"  value="${sysIndexLayoutManage.memo}"  class="inputText" style="width: 80%;" validate="{required:true,maxlength:100}"/>
						</td>
					</tr>
					<tr style="display: none;">
						<th width="20%">所属组织: </th>
						<td>
							<input type="hidden" id="orgId" name="orgId" value="${sysIndexColumn.orgId}"  class="inputText"/>
							<input type="text" id="orgName"  value="${sysIndexColumn.orgName}">
							<a  href="javascript:;" onclick="selectOrg()"  class="btn  btn-xs btn-primary"><span>选 择...</span></a>
							
						</td>
					</tr>
					<tr>
						<th width="20%">是否是默认: </th>
						<td>
							<label><input type="radio"  name="isDef" value="1" <c:if test="${sysIndexLayoutManage.isDef==1}">checked="checked"</c:if>  />是</lable>
							<label><input type="radio"   name="isDef"  value="0" <c:if test="${sysIndexLayoutManage == null ||sysIndexLayoutManage.isDef==0}">checked="checked"</c:if>  />否</lable>	
						</td>
					</tr>
				</table>	

	      </div>
	      <div class="modal-footer">
	        <button type="button" id="saveRemoteLayout" class="btn btn-primary">保存</button>
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
  	</div>
  	

</body>
</html>

