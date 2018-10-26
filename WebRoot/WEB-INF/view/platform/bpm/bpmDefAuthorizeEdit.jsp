<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title>流程分管授权编辑</title>
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
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/BpmDefinitionDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmDefAuthorize.js"></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmDefAuthorize"></script>

<script type="text/javascript">
    
	 $(function() {
        //初始化返回提示
		$('#bpmDefAuthorizeForm').ajaxForm({success:showResponse });
		 
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
		
	    //初始化授权类型的Checkbox选中内容
	    disableCheckbox();
	    
	    //提交事件
	    initSubmit();
	    
	 });
    
	 //提交后返回内容
	 function showResponse(r){
		var data = eval("("+r+")");
		if(data.result){
			$.ligerDialog.success(data.message,"消息提示",function(){
				window.location.href = 'list.ht';
			});
		}
		else{
			$.ligerDialog.warn(data.message,"消息提示");
		}
	}
	
	//初始化提交事件
	function initSubmit(){	
		$("a.save").click(function() {
			//验证
			var frm=$('#bpmDefAuthorizeForm');   
			if(!frm.valid()) return ;
			//获取并保存好流程信息
			var conf = getDefAct();
			if(!objectIsEmpty(conf)){
	    		var jsonStr = JSON2.stringify(conf);
	        	$("textarea[name='defNameJson']").val(jsonStr);
			}
			//提交保存内容
			frm.submit();
		});
		
	}
	
	 
    //打开对象选择窗口
    function selectBpmDefUser(id){
		var conf = $("textarea[name='ownerNameJson']").val();
    	/* var jsonStr = bpmDefAuthorizeUserDialog(id,conf);
    	if(objectIsEmpty(jsonStr)){
    		return;
    	}else{
    		var conf = $.parseJSON(jsonStr);
    		initOwner(conf);
    	} */
    	
    	var callBack = function(jsonStr){
    		if(objectIsEmpty(jsonStr)){
        		return;
        	}else{
        		var conf = $.parseJSON(jsonStr);
        		initOwner(conf);
        	}
    	}
    	bpmDefAuthorizeUserDialog(id,conf,callBack)
    }
    
    
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
					var href = __ctx+"/platform/system/sysUser/get.ht?openType=detail&userId=";
					setVal(me,objJson.userJson,href);
					emptyMark = false;
				}
			}else if(rightType=='role'){
				if(objectIsEmptyByRep(objJson.roleJson,'[]')){
					tr.hide();
				}else{
					tr.show();
					setVal(me,objJson.roleJson);
					emptyMark = false;
				}
			}else if(rightType=='org'){
				if(objectIsEmptyByRep(objJson.orgJson,'[]')){
					tr.hide();
				}else{
					tr.show();
					setVal(me,objJson.orgJson);
					emptyMark = false;
				}
			}else if(rightType=='grant'){
				if(objectIsEmptyByRep(objJson.grantJson,'[]')){
					tr.hide();
				}else{
					tr.show();
					setVal(me,objJson.grantJson);
					emptyMark = false;
				}
			}else if(rightType=='position'){
				if(objectIsEmptyByRep(objJson.positionJson,'[]')){
					tr.hide();
				}else{
					tr.show();
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
	
	//重置授权对象内容
    function resetOwnerName(){
    	$.ligerDialog.confirm("确认重置受权对象内容？","消息提示",function(rtn){
			if(rtn){
				$("textarea[name='ownerNameJson']").val("");   	
				$("textarea[name='ownerName']",$("#ownerName_div")).each(function(){
		    		$(this).val("");
		    		var tr = $(this).closest("tr");
		    		$("div.owner-div",tr).empty();
		    		tr.hide();
		    	});
		    	$("tr.empty-div",$("#ownerName_div")).show();
			}
		});
    }
	
	
    //重置授权流程内容
    function resetDefName(){
    	$.ligerDialog.confirm("确认重置授权流程内容？","消息提示",function(rtn){
			if(rtn){
				$("textarea[name='defNameJson']").remove();   	
				$("tr.def-div",$("#defName_div")).each(function(){
		    		$(this).remove();
		    	});
		    	$("tr.empty-div",$("#defName_div")).show();
			}
		});
    }
    
    //删除选中的授权流程
    function delDefName(){
    	$(".myDefKey",$("#bpmDefActTable")).each(function(index) {
    		var me = $(this);
    		var checked = me.is(":checked");
    		if(checked){
    			me.closest("tr").remove();
    		}
    	});
    	var trs = $("tr",$("#defName_div"));
    	if(trs.length<3){
    		$("tr.empty-div",$("#defName_div")).show();
    	}
    }
	
	//流程选择
    function selectBpmDefAct(){	
    	
    	var defArry = new Array();
    	var conf = getDefAct();
    	if(!objectIsEmpty(conf)){
    		var arry = conf.defArry;
    		for(i=0;i<arry.length;i++){
    			var obj = arry[i];
    			var defStr = obj.defId+"#"+obj.defName+"#"+obj.defKey;
    			defArry.push(defStr);
    		}
		}
    	
    	BpmDefinitionDialog({isSingle:false,showAll:1,returnDefKey:true,defArry:defArry,defMark:"key",callback:function(defIds,subjects,defKeys){
			if(!objectIsEmpty(defKeys)){
				var defIdArry = defIds.split(",");
				var defKeyArry = defKeys.split(",");
				var subjectArry = subjects.split(",");
				if(defKeyArry.length>0){
					var conf = {};
					var defArry= new Array();   
					for(i=0;i<defKeyArry.length;i++){
						var obj = {
								defId:defIdArry[i],
								defKey:defKeyArry[i],
								defName:subjectArry[i],
								rightContent:""
						};
						defArry.push(obj);
					}
					conf.defArry=defArry;
					intDefAct(conf);
				}
				
			}
		}});
	}
	
    //初始化授权流程数据
    function intDefAct(conf){
    	if(objectIsEmpty(conf)){
			return false;
		}
    	if(objectIsEmptyByRep(conf.defArry,'[]')){
			return false;
		}
    	
    	//把之前的流程和新增加的流程组合成一个流程
    	var newDefArry = conf.defArry;
    	var oldConf = getDefAct();
		var defArry = oldConf.defArry;
		if(newDefArry.length<=0){
			return false;
		}
		for(var i=0;i<newDefArry.length;i++){
			var id = newDefArry[i].defKey;
			var mark = true;
			for(var j=0;j<defArry.length;j++){
				var oldId = defArry[j].defKey;
				if(oldId==id){
					mark = false;
					break;
				}
			}
			if(mark){
				defArry.push(newDefArry[i]);
			}
		}
    	
    	//拼装HTML
    	var template=$("#txtReceiveTemplate").val();
		var html=easyTemplate(template,defArry).toString();
		$("#defName_div").html(html);
		disableCheckbox();
    }
    
    //初始化授权流程数据
    function getDefAct(){
    	var conf = {};
    	var defArry= new Array();
    	$(".myDefKey",$("#bpmDefActTable")).each(function(index) {
    		var me = $(this);
    		var tr = me.closest("tr");
    		
    		var right = {
					 
    		       };  //虚拟JSON，防止后台报错
    		       
    		$("input[name='authorizeTypes']").each(function(){
    			var authorizeTypeObj = $(this);
    			var authorizeTypeChecked = authorizeTypeObj.is(":checked");
    			if(authorizeTypeChecked){
    				authorizeType = authorizeTypeObj.val();
    				if(authorizeType=="management"){
		    			$("input[name='m_right']",tr).each(function(){
		        			var rightMe = $(this);
		            		var keyStr =  rightMe.val();
		        			var valueStr = "N";
		            		var checked = rightMe.is(":checked");
		            		if(checked){
		            			valueStr = "Y";
		            		}
		            		if(keyStr=="m_edit"){
		            			right.m_edit = valueStr;
		            		}else if(keyStr=="m_del"){
		            			right.m_del = valueStr;
		            		}else if(keyStr=="m_start"){
		            			right.m_start = valueStr;
		            		}else if(keyStr=="m_set"){
		            			right.m_set = valueStr;
		            		}else if(keyStr=="m_international"){
		            			right.m_international = valueStr;
		            		}else if(keyStr=="m_clean"){
		            			right.m_clean = valueStr;
		            		}
		        		});
		    		}else if(authorizeType=="instance"){
		    			$("input[name='i_right']",tr).each(function(){
		        			var rightMe = $(this);
		            		var keyStr =  rightMe.val();
		        			var valueStr = "N";
		            		var checked = rightMe.is(":checked");
		            		if(checked){
		            			valueStr = "Y";
		            		}
		            		if(keyStr=="i_del"){
		            			right.i_del = valueStr;
		            		}else if(keyStr=="i_log"){
		            			right.i_log = valueStr;
		            		}
		        		});
		    		}else{
			    					    		
		    		}
    			}
    		});
    		
    		var obj = {
    				defId:me.attr("defId"),
    				defKey:me.val(),
					defName:me.attr("defName"),
					rightContent:right
			};
    		defArry.push(obj);
    	});
    	conf.defArry = defArry;
    	return conf;
    }
    
    //按授权类型变化操作Checkbox内容
    function changeAuthorizeType(){
    	disableCheckbox();
    }
    
    //按授权类型 操作Checkbox内容
    function disableCheckbox(){
    	var bpmDefActTable = $("#bpmDefActTable");
    	$("input[name='authorizeTypes']").each(function(){
    		var me = $(this);
            var authorizeType = me.val();
            var myChecked = me.is(":checked");
            if(authorizeType=="management"){
	            if(myChecked){
	            	$("div[name='m_right_div']",bpmDefActTable).show();
				//	$("#am_right_div").show();
	            }else{
	            	$("div[name='m_right_div']",bpmDefActTable).hide();
				//	$("#am_right_div").hide();
	            }
				
			}else if(authorizeType=="instance"){
				if(myChecked){
					$("div[name='i_right_div']",bpmDefActTable).show();
				//	$("#ai_right_div").show();	            	
				}else{
					$("div[name='i_right_div']",bpmDefActTable).hide();
				//	$("#ai_right_div").hide();
				}
			}else{
				/* $("div[name='m_right_div']",bpmDefActTable).hide();
				$("#am_right_div").hide();
				$("div[name='i_right_div']",bpmDefActTable).hide();
				$("#ai_right_div").hide(); */
			}
        });

    }
   
</script>

</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">流程分管授权编辑</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" ><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
					
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="bpmDefAuthorizeForm" method="post" action="save.ht">
				<input type="hidden" name="id" value="${bpmDefAuthorize.id}">
				<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">				 
					
					<tr>
						<th width="20%">权限描述:</th>
						<td>
							<input type="text" name="authorizeDesc" size="100" value="${bpmDefAuthorize.authorizeDesc}" validate="{'required':true}" />
						</td>
					</tr>

					<tr>
						<th width="20%">权限类型:</th>
						<td>
							启动<input type="checkbox" name="authorizeTypes" value="start" onclick="javascript: changeAuthorizeType()" <c:if test="${fn:contains(bpmDefAuthorize.authorizeTypes,'start')}" >checked="checked"</c:if> />&nbsp;&nbsp;
							定义<input type="checkbox" name="authorizeTypes" value="management" onclick="javascript: changeAuthorizeType()" <c:if test="${fn:contains(bpmDefAuthorize.authorizeTypes,'management')}" >checked="checked"</c:if> />&nbsp;&nbsp;
							任务<input type="checkbox" name="authorizeTypes" value="task" onclick="javascript: changeAuthorizeType()" <c:if test="${fn:contains(bpmDefAuthorize.authorizeTypes,'task')}" >checked="checked"</c:if> />&nbsp;&nbsp;
							实例<input type="checkbox" name="authorizeTypes" value="instance" onclick="javascript: changeAuthorizeType()" <c:if test="${fn:contains(bpmDefAuthorize.authorizeTypes,'instance')}" >checked="checked"</c:if> />&nbsp;&nbsp;
						</td>
					</tr>
					
					<tr>
						<th width="20%">授权人员名称:</th>
						<td>
							<br/>
							
							<div class="group">
								<a class="link ok" href="javascript: selectBpmDefUser('${bpmDefAuthorize.id}');" ><span></span>选择</a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link reset" href="javascript: resetOwnerName();"><span></span>重置</a>
							</div>
							
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
							
							<div class="group">
								<a class="link ok" href="javascript: selectBpmDefAct('${bpmDefAuthorize.id}');" ><span></span>选择</a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link reset" href="javascript: resetDefName();"><span></span>重置</a>
							</div>
							
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link del" href="javascript: delDefName();"><span></span>删除</a>
							</div>
							
							<div class="l-bar-separator"></div>
							<div class="group">
							     <div id="am_right_div">
							     	 &nbsp;&nbsp;定义:(
								     	 <input type="checkbox" id="m_rightEdit" name="m_right" onclick="selectCheckbox('m_rightEdit')"  value="m_edit" >设计
									     <input type="checkbox" id="m_rightDel" name="m_right"  onclick="selectCheckbox('m_rightDel')"  value="m_del" >删除
									     <input type="checkbox" id="m_rightStart" name="m_right" onclick="selectCheckbox('m_rightStart')" value="m_start" >启动
									     <input type="checkbox" id="m_rightSet"  name="m_right" onclick="selectCheckbox('m_rightSet')"  value="m_set" >设置
	<!-- 								     <input type="checkbox" id="m_rightInternational"  name="m_international" onclick="selectCheckbox('m_rightInternational')"  value="m_international" >国际化 -->
									     <input type="checkbox" id="m_rightClean"  name="m_right" onclick="selectCheckbox('m_rightClean')"  value="m_clean" >清除数据
							     	 )
							     </div>
							     <div id="ai_right_div">
								     &nbsp;&nbsp;实例:(
									     <input type="checkbox" id="i_rightDela" name="i_right"  onclick="selectCheckbox('i_rightDela')"  value="i_del" >删除
									     <input type="checkbox" id="i_rightLoga" name="i_right" onclick="selectCheckbox('i_rightLoga')" value="i_log" >日志
							         )
							     </div>
							</div>
						
							<br/>
							
							<div id="defName_div" style="float: left; width: 100%;">
								<table id="bpmDefActTable" class="table-grid table-list"  cellpadding="0" cellspacing="0" border="0">
									<thead>
										<tr>
											<th width="100px;" style="text-align: center;"><input type="checkbox" id="all_key"/></th>
											<th style="text-align: center;">流程名称</th>
											<th width="350px;" style="text-align: center;">授权内容</th>
										</tr> 
									</thead>
										
									<tr class="empty-div">
										<td colspan="3" style="text-align: center;"> 
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
    
		<table id="bpmDefActTable" style="float: left;"  class="table-grid table-list"  cellpadding="0" cellspacing="0" border="0">
			<thead>
				<tr>
					<th width="100px;" style="text-align: center;"><input type="checkbox" id="all_myDefKey" onclick="selectCheckbox('all_myDefKey')"/></th>
					<th style="text-align: center;">流程名称</th>
					<th width="350px;" style="text-align: center;">授权内容</th>
				</tr> 
			</thead>
			
			<tr class="empty-div" style="display: none;">
				<td colspan="3" style="text-align: center;"> 
					没有授权的流程
				</td>
			</tr>
				
			<#list data as obj>
				<tr class='def-div' >
					<td width="100px;" style="text-align: center;">
					     <input type="checkbox" class="myDefKey" name="defKey" defId="\${obj.defId}"  value="\${obj.defKey}" defName="\${obj.defName}" >
					</td>
					<td>
						<span class="owner-span">\${obj.defName}</span>
					</td>
					<td width="350px;" style="text-align: center;" >
					     <div name="m_right_div">
						     &nbsp;&nbsp;定义:(
							     <input type="checkbox" class="rightEdit" name="m_right" <#if (obj.rightContent.m_edit=='Y')> checked="checked" </#if>  value="m_edit" >设计
							     <input type="checkbox" class="rightDel" name="m_right"  <#if (obj.rightContent.m_del=='Y')> checked="checked" </#if>  value="m_del" >删除
							     <input type="checkbox" class="rightStart"  name="m_right"  <#if (obj.rightContent.m_start=='Y')> checked="checked" </#if> value="m_start" >启动 
							     <input type="checkbox" class="rightSet" name="m_right"   <#if (obj.rightContent.m_set=='Y')> checked="checked" </#if> value="m_set" >设置
	<!-- 						     <input type="checkbox" class="rightInternational" name="m_right"   <#if (obj.rightContent.m_international=='Y')> checked="checked" </#if> value="m_international" >国际化 -->
							     <input type="checkbox" class="rightClean" name="m_right"   <#if (obj.rightContent.m_clean=='Y')> checked="checked" </#if> value="m_clean" >清除数据
					         )
					     </div>
					     <div name="i_right_div">
						     &nbsp;&nbsp; 实例(
						     	<input type="checkbox" class="rightDela" name="i_right"  <#if (obj.rightContent.i_del=='Y')> checked="checked" </#if>  value="i_del" >删除
						     	<input type="checkbox" class="rightLoga"  name="i_right"  <#if (obj.rightContent.i_log=='Y')> checked="checked" </#if> value="i_log" >日志
					         )
					     </div>
					</td>
				</tr>
   			</#list>
	   			
		</table>
		<textarea class="hidden" name="defNameJson"></textarea>
	</textarea>
	
</body>
</html>

