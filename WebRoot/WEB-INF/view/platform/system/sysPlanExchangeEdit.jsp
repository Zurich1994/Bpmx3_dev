<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>编辑日程交流信息</title>
<style type="text/css">
	.owner-span{
		font-size: 13px;
		background-color: #EFF2F7;
	    border: 1px solid #CCD5E4;
	    border-radius: 5px 5px 5px 5px;
	    cursor: default;
	    float: left;
	    height: auto !important;
	    margin: 3px;
	    overflow: hidden;
	    padding: 2px 4px;
	    white-space: nowrap;
	}
</style>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/FlexUploadDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript" src="${ctx}/codegen/js/AttachMent.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysPlanScript.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysPlan"></script>
<script type="text/javascript">
    
    var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

	$(function(){

		//初始化附件选择器
		AttachMent.init();
		
		//初始化保存交流信息按键
	    initExchangeSubmit(dialog);	
		
	    //初始化关闭事件
		$('#btnClose').click(function(){
			dialog.close();
		});

	});
    

</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">编辑日程交流信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" ><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a id="btnClose" class="link close" href="#"><span></span>关闭</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sysPlanExchangeEdit" method="post" action="${ctx}/platform/system/sysPlan/saveExchange.ht">
				<div class="panel-detail">
				<input type="hidden" name="id" value="${sysPlanExchange.id}"/>
				<input type="hidden" name="planId" value="${planId}"/>
				<input type="hidden" name="submitId" value="${sysPlanExchange.submitId}"/>
				<input type="hidden" name="submitor" value="${sysPlanExchange.submitor}"/>
				<input type="hidden" name="createTime" value="<fmt:formatDate value='${sysPlanExchange.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
				<input type="hidden" name="currentViweDate" value="${currentViweDate}"/>
				<input type="hidden" name="type" value="${type}"/>
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">				 

					<tr>
						<th width="20%">内容:</th>
						<td colspan="3">
							<textarea name="content" rows="10" cols="20" validate="{'required':true}" >${sysPlanExchange.content}</textarea>
						</td>

					</tr>
					
					<tr>
						<th width="20%">附件:</th>
						<td colspan="3">
							<div  name="div_attachment_container"  >
							<div  class="attachement" ></div>
							<textarea style="display:none" controltype="attachment" name="doc" lablename="相关文档">${sysPlanExchange.doc}</textarea>
							<a href="javascript:;" field="doc" class="link selectFile" atype="select"  onclick="AttachMent.directUpLoadFile(this);" >选择</a>
						</td>

					</tr>
					
					<c:if test="${sysPlanExchange.id!=null&&sysPlanExchange.id!=''}">
						<tr>
							<th width="20%">提交人:</th>
							<td width="30%">
								${sysPlanExchange.submitor}
							</td>
							<th width="20%">提交时间:</th>
							<td width="30%">
								<fmt:formatDate value='${sysPlanExchange.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
							</td>
						</tr>
					</c:if>		
					
				</table>
				</div>
			</form>
		</div>
	</div>
	
	
</body>
</html>

