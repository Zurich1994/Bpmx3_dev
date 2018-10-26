<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/form.jsp" %>
<title>流程分管授权人员选择</title>
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
<script type="text/javascript" src="${ctx}/js/lg/plugins/htButtons.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmDefAuthorize.js"></script>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
    
	$(function() {
  	    	
	//	$("span.htbtn").htButtons();
		//初始化选择全部人员事件(以下要按顺序初始化)
	    selectAllHandler($("#selectAll"));
		
	    //人员查看详情事件
	    openDetailEvent();
		
		//确定返回选择对象事件
		initOwnerOk()
		
		//初始化数据库中的数据
		//var conf = window.dialogArguments;
		var conf =dialog.get('conf');
		if(conf!=undefined && conf!=null && conf!=""){      //模态窗口的接收方法
  			var myJson = $.parseJSON(conf.jsonStr);
  	    	initMyOwnerSpan(myJson);
  	    }
  	    
	});
	
	
	//确定返回选择对象事件
	function initOwnerOk(){
		$("a.ok").click(function() {
			var objJson = {};
			var checked=$("#selectAll").is(":checked");
			if(checked){
				objJson = {
	  	    			allJson:"Y",
	  	    			userJson:"[]",
	  	    			roleJson:"[]",
	  	    			orgJson:"[]",
	  	    			grantJson:"[]",
	  	    			positionJson:"[]"
	  	    	}
			}else{
				$("textarea[name='ownerName']").each(function(){
					var me = $(this),
						tr = me.parents("tr"),
						myJsonStr = $("textarea[name='ownerName']",tr).val();
						rightType = $("input[name='rightType']",tr).val();
						
					if(myJsonStr==undefined || myJsonStr==null || myJsonStr==""){
						myJsonStr = '[]';
					}
					json = $.parseJSON(myJsonStr);	
					if(rightType=='all'){
						if(checked){
							objJson.allJson='Y';
						}else{
							objJson.allJson='N';
						}
					}else if(rightType=='user'){
						objJson.userJson=json;
					}else if(rightType=='role'){
						objJson.roleJson=json;
					}else if(rightType=='org'){
						objJson.orgJson=json;
					}else if(rightType=='grant'){
						objJson.grantJson=json;
					}else if(rightType=='position'){
						objJson.positionJson=json;
					}
				});
			}
			var conf ={
    				objJson:objJson
    		}
			var jsonStr = JSON2.stringify(conf); 
			//window.returnValue = jsonStr;
			dialog.get('sucCall')(jsonStr);
			dialog.close();
		});
	};
	
	//初始化数据库中的数据
	function initMyOwnerSpan(myJson){
		if(objectIsEmptyByRep(myJson,'{}')){
			return false;
		}
		var objJson = myJson.objJson;
		if(objectIsEmptyByRep(objJson,'{}')){
			return false;
		}
		$("textarea[name='ownerName']").each(function(){
    		var me = $(this),
				tr = me.closest("tr"),
				owner = $("textarea[name='ownerName']",tr);
				rightType = $("input[name='rightType']",tr).val();
			if(rightType=='all'){
				if(objJson.allJson=="Y"){
					$("#selectAll").attr("checked","checked");
					selectAllHandler($("#selectAll"));
				}
			}else if(rightType=='user'){
				if(!objectIsEmptyByRep(objJson.userJson,'[]')){
					var href = __ctx+"/platform/system/sysUser/get.ht?openType=detail&userId=";
					setVal(me,objJson.userJson,href);
				}
			}else if(rightType=='role'){
				if(!objectIsEmptyByRep(objJson.roleJson,'[]')){
					setVal(me,objJson.roleJson);
				}
			}else if(rightType=='org'){
				if(!objectIsEmptyByRep(objJson.orgJson,'[]')){
					setVal(me,objJson.orgJson);
				}
			}else if(rightType=='grant'){
				if(!objectIsEmptyByRep(objJson.grantJson,'[]')){
					setVal(me,objJson.grantJson);
				}
			}else if(rightType=='position'){
				if(!objectIsEmptyByRep(objJson.positionJson,'[]')){
					setVal(me,objJson.positionJson);
				}
			}
		});
		
	}
	
	//所有人的选择事件
	function selectAllHandler(obj) {
		var _this = $(obj);
		if( _this.is(":checked")){
			$("a.current").hide();
			$("div.owner-div").hide();
			$("#chooseAll").trigger("click");
		}else{
			$("a.current").show();
			$("div.owner-div").show();
			$("#resetAll").trigger("click");
		}
	}
   
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link ok"><span></span>确定</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link del" onclick="dialog.close()" href="javascript:;"><span></span>关闭</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			
			<form id="bpmDefUserForm"  method="post">
				<input type="hidden"  name="id" value="${id}">
				
						<table id="bpmDefUserTable"  class="table-grid table-list" cellpadding="0" cellspacing="0" border="0">
							<thead>
								<tr>
									<th width="150px;" nowrap="nowrap">权限分类</th>
									<th style="text-align: center;">授权给</th>
									<th  width="180px;" nowrap="nowrap"  style="text-align: center;">操作</th>
								</tr> 
							</thead>
								<tr>
									<th colspan="3"> 
										<input id="selectAll" style="float:left" type="checkbox" onclick="selectAllHandler(this)"> <label style="float:left"  for="selectAll">允许所有人访问</label>
									</th>
								</tr>
								<tr style="display: none">
									<td>所有用户</td>
									<td>
										<div class="owner-div"></div>
										<textarea rightType="0" class="hidden" name="ownerName"></textarea>
										<input type="hidden" name="rightType" value="all">
									</td>
									<td>			
										<a id="chooseAll" onclick="chooseAllHandler(this);" ></a>
                                        <a id="resetAll" onclick="resetSelect(this);" ></a>
									</td>
								</tr>
								<tr>
									<td>用户授权</td>
									<td>
										<div class="owner-div"></div>
										<textarea class="hidden" name="ownerName"></textarea>
										<input type="hidden" name="rightType" value="user">
									</td>
									<td>
										<a class="current" onclick="chooseUser(this);" ><span></span>选择...</a>
										<a class="current" onclick="resetSelect(this);" ><span></span>重置</a>
									</td>
								</tr>
								<tr>
									<td>角色授权</td>
									<td>
										<div class="owner-div"></div>
										<textarea class="hidden" name="ownerName"></textarea>
										<input type="hidden" name="rightType" value="role">				
									</td>
									<td>
										<a class="current" onclick="chooseRole(this);" ><span></span>选择...</a>
										<a class="current" onclick="resetSelect(this);" ><span></span>重置</a>
									</td>
								</tr>
								<tr>
									<td>组织授权(本层级)</td>
									<td>
										<div class="owner-div"></div>
										<textarea class="hidden" name="ownerName"></textarea>
										<input type="hidden" name="rightType" value="org">
									</td>
									<td>
										<a class="current" onclick="chooseOrg(this);" ><span></span>选择...</a>
										<a class="current" onclick="resetSelect(this);" ><span></span>重置</a>
									</td>
								</tr>
								<tr>
									<td>组织授权(包含子组织)</td>
									<td>
										<div class="owner-div"></div>
										<textarea class="hidden" name="ownerName"></textarea>
										<input type="hidden" name="rightType" value="grant">
										</td>
									<td>
										<a class="current" onclick="chooseOrg(this);" ><span></span>选择...</a>
										<a class="current" onclick="resetSelect(this);" ><span></span>重置</a>
									</td>
								</tr>
								<tr>
									<td>岗位授权</td>
									<td>
										<div class="owner-div"></div>
										<textarea class="hidden" name="ownerName"></textarea>
										<input type="hidden" name="rightType" value="position">
									</td>
									<td>
										<a class="current" onclick="choosePosition(this);" ><span></span>选择...</a>
										<a class="current" onclick="resetSelect(this);" ><span></span>重置</a>
									</td>
								</tr>
						</table>
				</form>
		</div>
	</div>
</body>
</html>


