<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title>流程分级授权管理详情</title>
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
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmDefAuthorize.js"></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>

<script type="text/javascript">
    
	 $(function() {
		 
		//人员查看详情事件
	    openDetailEvent();

	    //初始化数据库中的数据
	    var jsonStr = '${bpmDefAuthorize.ownerNameJson}';
	    var conf = {};
	    if(objectIsEmpty(jsonStr)){
	    	var objJson = {
  	    			allJson:"N",
  	    			userJson:"[]",
  	    			roleJson:"[]",
  	    			orgJson:"[]",
  	    			grantJson:"[]",
  	    			positionJson:"[]"
  	    	}
		    conf.objJson = objJson;
	    }else{
	    	conf = $.parseJSON(jsonStr);
	    }
	    initOwner(conf);
	    
	    //初始化授权流程数据
	    var djsonStr = '${bpmDefAuthorize.defNameJson}'
	    if(!objectIsEmpty(djsonStr)){
	    	var myConf = $.parseJSON(djsonStr);
	    	intDefAct(myConf);
	    }
	    
	 });
    
	
    
    //初始化数据库中的授权用户数据
	function initOwner(conf){
    	if(objectIsEmpty(conf)){
    		return false;
    	}
    	var jsonStr = JSON2.stringify(conf);
    	$("textarea[name='ownerNameJson']").val(jsonStr);
		var objJson = conf.objJson;
    	var emptyMark = true;
    	$("textarea[name='ownerName']",$("#ownerName_div")).each(function(){
    		var me = $(this),
				tr = me.closest("tr"),
				owner = $("textarea[name='ownerName']",tr);
				rightType = $("input[name='rightType']",tr).val();
			if(rightType=='all'){
			//	var allJson = JSON2.stringify(objJson.allJson);
				owner.val(objJson.allJson);
				if(objJson.allJson=="Y"){
					tr.show();
					emptyMark = false;
				}else{
					tr.hide();
				}
			}else if(rightType=='user'){
				if(objectIsEmptyByRep(objJson.userJson,'[]')){
					tr.hide();
				}else{
					tr.show();
				//	var userJson = $.parseJSON(objJson.userJson);
					var href = __ctx+"/platform/system/sysUser/get.ht?openType=detail&userId=";
					setVal(me,objJson.userJson,href);
					emptyMark = false;
				}
			}else if(rightType=='role'){
				if(objectIsEmptyByRep(objJson.roleJson,'[]')){
					tr.hide();
				}else{
					tr.show();
				//	var roleJson = $.parseJSON(objJson.roleJson);
					setVal(me,objJson.roleJson);
					emptyMark = false;
				}
			}else if(rightType=='org'){
				if(objectIsEmptyByRep(objJson.orgJson,'[]')){
					tr.hide();
				}else{
					tr.show();
				//	var orgJson = $.parseJSON(objJson.orgJson);
					setVal(me,objJson.orgJson);
					emptyMark = false;
				}
			}else if(rightType=='grant'){
				if(objectIsEmptyByRep(objJson.grantJson,'[]')){
					tr.hide();
				}else{
					tr.show();
				//	var grantJson = $.parseJSON(objJson.grantJson);
					setVal(me,objJson.grantJson);
					emptyMark = false;
				}
			}else if(rightType=='position'){
				if(objectIsEmptyByRep(objJson.positionJson,'[]')){
					tr.hide();
				}else{
					tr.show();
				//	var positionJson = $.parseJSON(objJson.positionJson);
					setVal(me,objJson.positionJson);
					emptyMark = false;
				}
			}
		});
    	
    	//是否没有授权对象内容
    	if(emptyMark){
    		$("tr.empty-div",$("#ownerName_div")).show();
    	}else{
    		$("tr.empty-div",$("#ownerName_div")).hide();
    	}

	}
	
	
    //初始化授权流程数据
    function intDefAct(conf){
    	if(objectIsEmpty(conf)){
			return false;
		}
    	if(objectIsEmptyByRep(conf.defArry,'[]')){
			return false;
		}
    	//拼装HTML
    	var template=$("#txtReceiveTemplate").val();
		var html=easyTemplate(template,conf.defArry).toString();
		$("#defName_div").html(html);
    }
    
   
</script>

</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">流程分管授权详情</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<!-- <div class="group">
						<a class="link save" ><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div> -->
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
					
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="bpmDefAuthorizeForm" >
				<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">				 
					
					<tr>
						<th width="20%">权限描述:</th>
						<td>
							${bpmDefAuthorize.authorizeDesc}
						</td>
					</tr>

					<tr>
						<th width="20%">权限类型:</th>
						<td>
							<c:if test="${fn:contains(bpmDefAuthorize.authorizeTypes,'management')}" >定义&nbsp;&nbsp;</c:if>
							<c:if test="${fn:contains(bpmDefAuthorize.authorizeTypes,'start')}" >启动&nbsp;&nbsp;</c:if>
							<c:if test="${fn:contains(bpmDefAuthorize.authorizeTypes,'task')}" >任务&nbsp;&nbsp;</c:if>
							<c:if test="${fn:contains(bpmDefAuthorize.authorizeTypes,'instance')}" >实例&nbsp;&nbsp;</c:if>
							<c:if test="${bpmDefAuthorize.authorizeTypes eq ''||bpmDefAuthorize.authorizeTypes == null}">暂时没有分配类型&nbsp;&nbsp;</c:if>
						</td>
					</tr>
					
					<tr>
						<th width="20%">授权人员名称:</th>
						<td>
							<br/>						
							
							<div id="ownerName_div">
								<table id="bpmDefUserTable"  class="table-grid table-list"  cellpadding="0" cellspacing="0" border="0">
									<thead>
										<tr>
											<th width="100px;" style="text-align: center;" >权限分类</th>
											<th style="text-align: center;">授权给</th>
										</tr> 
									</thead>
										
										<tr class="empty-div">
											<td colspan="2" style="text-align: center;"> 
												没有授权的人员
											</td>
										</tr>
																
										<tr class='all-div' >
											<td>所有用户</td>
											<td>
												<div class="owner-div"><label style="float:left"  for="selectAll">允许所有人访问</label></div>
												<textarea rightType="0" class="hidden" name="ownerName"></textarea>
												<input type="hidden" name="rightType" value="all">
											</td>
										</tr>
										<tr class='user-div' >
											<td>用户授权</td>
											<td>
												<div class="owner-div"></div>
												<textarea class="hidden" name="ownerName"></textarea>
												<input type="hidden" name="rightType" value="user">
											</td>
										</tr>
										<tr class='role-div' >
											<td>角色授权</td>
											<td>
												<div class="owner-div"></div>
												<textarea class="hidden" name="ownerName"></textarea>
												<input type="hidden" name="rightType" value="role">				
											</td>
										</tr>
										<tr class='org-div' >
											<td>组织授权(本层级)</td>
											<td>
												<div class="owner-div"></div>
												<textarea class="hidden" name="ownerName"></textarea>
												<input type="hidden" name="rightType" value="org">
											</td>
										</tr>
										<tr class='grant-div' >
											<td>组织授权(包含子组织)</td>
											<td>
												<div class="owner-div"></div>
												<textarea class="hidden" name="ownerName"></textarea>
												<input type="hidden" name="rightType" value="grant">
											</td>
										</tr>
										<tr class='position-div'>
											<td>岗位授权</td>
											<td>
												<div class="owner-div"></div>
												<textarea class="hidden" name="ownerName"></textarea>
												<input type="hidden" name="rightType" value="position">
											</td>
										</tr>
								</table>
							</div>
							
							<br/>
							
							<textarea class="hidden" name="ownerNameJson"></textarea>
							
						</td>
					</tr>
					
					<tr class="">
						<th width="20%">授权流程名称:</th>
						<td>
							<br/>
							
							<div id="defName_div">
								<table id="bpmDefActTable"  class="table-grid table-list"  cellpadding="0" cellspacing="0" border="0">
									<thead>
										<tr>
											<th style="text-align: center;">流程名称</th>
											<th width="450px;" style="text-align: center;">授权内容</th>
										</tr> 
									</thead>
										
									<tr class="empty-div">
										<td colspan="2" style="text-align: center;"> 
											没有授权的流程
										</td>
									</tr>
								</table>
							</div>
							
							<br/>

						</td>
					</tr>
					
				</table>
				</div>
			</form>
		</div>
	</div>
	
	<!-- HMTL模板拼写内容 -->
    <textarea id="txtReceiveTemplate"  style="display: none;">
    
		<table id="bpmDefActTable"  class="table-grid table-list"  cellpadding="0" cellspacing="0" border="0">
			<thead>
				<tr>
					<th style="text-align: center;">流程名称</th>
					<th width="450px;" style="text-align: center;">授权内容</th>
				</tr> 
			</thead>
			
			<tr class="empty-div" style="display: none;">
				<td colspan="2" style="text-align: center;"> 
					没有授权的流程
				</td>
			</tr>
				
			<#list data as obj>
				<tr class='def-div' >
					<td>
						<span class="owner-span">\${obj.defName}</span>
					</td>
					<td width="450px;" style="text-align: center;" >
					      <#if (obj.rightContent.m_edit=='Y'||obj.rightContent.m_del=='Y'||obj.rightContent.m_set=='Y'||obj.rightContent.m_clean=='Y')>          
							    &nbsp;&nbsp;&nbsp;&nbsp; 定义(&nbsp;&nbsp;
								     <#if (obj.rightContent.m_edit=='Y')> 设计&nbsp;&nbsp;</#if> 
								     <#if (obj.rightContent.m_del=='Y')> 删除&nbsp;&nbsp;</#if>  
								     <#if (obj.rightContent.m_start=='Y')> 启动&nbsp;&nbsp;</#if> 
								     <#if (obj.rightContent.m_set=='Y')>  设置&nbsp;&nbsp; </#if>
								     <!-- <#if (obj.rightContent.m_international=='Y')>  国际化&nbsp;&nbsp; </#if> -->
								     <#if (obj.rightContent.m_clean=='Y')> 清空数据&nbsp;&nbsp; </#if>
					             )
					      </#if>
					      
					      <#if (obj.rightContent.i_del=='Y'||obj.rightContent.i_log=='Y')>   
								&nbsp;&nbsp;&nbsp;&nbsp; 实例(&nbsp;&nbsp;
								     <#if (obj.rightContent.i_del=='Y')> 删除 &nbsp;&nbsp;</#if>  
								     <#if (obj.rightContent.i_log=='Y')> 日志&nbsp;&nbsp;</#if> 
					             )
					      </#if> 
					</td>
				</tr>
   			</#list>
	   			
		</table>
		<textarea class="hidden" name="defNameJson"></textarea>
	</textarea>
	
</body>
</html>

