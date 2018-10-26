<%@page pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html"%>
<html>
	<head>
		<title>选择角色</title>
		<%@include file="/commons/include/form.jsp" %>
		<f:link href="tree/zTreeStyle.css"></f:link>
	    <script type="text/javascript"	src="${ctx}/js/tree/jquery.ztree.js"></script>
	    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js" ></script>
		
 
		<script type="text/javascript">
			/*KILLDIALOG*/
			var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
 		    var systemTree=null; 
			var isSingle="${isSingle}";
			var isGrade="${isGrade}";
			var findStr = '';
			forbidF5("Chrome");//禁止刷新页面
			$(function(){
				$("#defLayout").ligerLayout({ leftWidth: 190,rightWidth: 170,allowRightResize:false,allowLeftResize:false,allowTopResize:false,allowBottomResize:false,height: '90%',minLeftWidth:170});
				//快速查找
				$("input.quick-find").bind('keyup',function(){
					var str = $(this).val();
					if(!str)return;
					if(str==findStr)return;
					findStr = str;
					var  tbody = $("#roleList"), 	
						 firstTr = $('tr.hidden',tbody);
					$("tr",tbody).each(function(){
						var me = $(this),
							span = $('span',me),
							spanStr = span.html();
						if(!spanStr)return true;						
						if(spanStr.indexOf(findStr)>-1){
							$(this).insertAfter(firstTr);
						}
					});
				});
				initData();
				loadTree();
				$("#roleFrame").attr("src","${ctx}/platform/system/sysRole/selector.ht?isSingle=${isSingle}&isGrade=${isGrade}");
			});
			//展开收起
			function treeExpandAll(type){
				systemTree = $.fn.zTree.getZTreeObj("systemTree");
				systemTree.expandAll(type);
			};
			
			function loadTree(){
				var setting = {
	    			data: {
		    				key : 
		    				{
		    					name: "sysName",
		    					title: "sysName"
		    				},
		    				simpleData: {
		    					enable: true,
		    					idKey: "systemId",
		    					pIdKey: "parentId",
		    					rootPId: 0
		    				}
	    				},
	    			callback:{onClick: treeClick}
	    		};
				var url="${ctx}/platform/system/subSystem/tree.ht";
				$.post(url,function(result){
					systemTree=$.fn.zTree.init($("#systemTree"), setting,eval(result));
		            systemTree.expandAll(true);
				});
		    		
			}
			//初始化父级窗口传进来的数据
			function initData(){
				//var obj = window.dialogArguments;
				var obj = dialog.get("arrys");
				if(obj&&obj.length>0){
					for(var i=0,c;c=obj[i++];){
						var data = c.id+'#'+c.name;
						if(c.name!=undefined&&c.name!="undefined"&&c.name!=null&&c.name!=""){
							add(data);
						}
					}
				}
			};
			//选择分类
         	function getSelectNode()
        	{
        		systemTree = $.fn.zTree.getZTreeObj("systemTree");
        		var nodes  = systemTree.getSelectedNodes();
        		var node   = nodes[0];
        		if(node.systemId==0) return '';
        		return node.systemId;
        	}
			
			function treeClick(event, treeId, treeNode){
        		var url="${ctx}/platform/system/sysRole/selector.ht?Q_systemId_L="+getSelectNode() +"&isSingle=${isSingle}&isGrade=${isGrade}";
        		$("#roleFrame").attr("src",url);
        	} 
			
			function selectRole(){
				var pleaseSelect= "请选择角色!";
				//单选
				if(isSingle=="true"){
					var chIds = $('#roleFrame').contents().find("input[name='roleId']:checked");
					if(chIds.length==0){
						alert("请选择");
						return;
					}
					var data=chIds.val();
					var aryRole=data.split("#");
					var obj={};
					obj.roleId=aryRole[0];
					obj.roleName=aryRole[1];
					//window.returnValue=obj;
					dialog.get("sucCall")(obj);
				}
				//复选
				else{
					var aryRoles =$("input[name='role']", $("#roleList"));
					if(aryRoles.length==0){
						alert(pleaseSelect);
						return;
					}
					var aryId=[];
					var aryName=[];
					var json = [];
					aryRoles.each(function(){
						var data=$(this).val();
						var aryRole=data.split("#");
						aryId.push(aryRole[0]);
						aryName.push(aryRole[1]);
						json.push({id:aryRole[0],name:aryRole[1]});
					});
					var roleIds=aryId.join(",");
					var roleNames=aryName.join(",");
					
					var obj={};
					obj.roleId=roleIds;
					obj.roleName=roleNames;
					obj.roleJson = json;
					//window.returnValue=obj;
					dialog.get("sucCall")(obj);
				}
				
				dialog.close();
			}
			function add(data) {
				var aryTmp=data.split("#");
				var roleId=aryTmp[0];
			
				var len= $("#role_" + roleId).length;
				if(len>0) return;
				var roleTemplate= $("#roleTemplate").val();
				
				var html=roleTemplate.replace("#roleId",roleId)
						.replace("#data",data)
						.replace("#name",aryTmp[1]);
				$("#roleList").append(html);
			};
		
			function selectMulti(obj) {
				if ($(obj).attr("checked") == "checked"){
					var data = $(obj).val();
					add(data);
				}	
			};
			
			function dellAll() {
				$("#roleList").empty();
			};
			function del(obj) {
				var tr = $(obj).closest("tr");
				$(tr).remove();
			};
			//清空角色
			function clearRole(){
				//window.returnValue={roleId:'',roleName:''};
				var rtn={roleId:'',roleName:''};
				dialog.get("sucCall")(rtn);
				dialog.close();
			}
	</script>
	<style type="text/css">
	.ztree {
		overflow: auto;
	}
	
	.label {
		color: #6F8DC6;
		text-align: right;
		padding-right: 6px;
		padding-left: 0px;
		font-weight: bold;
	}
	html { overflow-x: hidden;height:100%; }

	</style>	
	</head>
	<body >
		<div id="defLayout" >
				 <div position="center" >
	          		<iframe id="roleFrame" name="roleFrame" height="100%" width="100%" frameborder="0" ></iframe>
	             </div>
	          <c:if test="${isSingle=='false'}" >
		          <div position="right" title="<span><a onclick='javascript:dellAll();' class='link del'>清空</a><input type='text' class='quick-find' title='查找'/></span>" style="overflow: auto;height:95%;width:170px;">
	          			<table width="145"   class="table-grid table-list"  cellpadding="1" cellspacing="1">
	          				<tbody id="roleList">
	          					<tr class="hidden"></tr>
	          				</tbody>
						</table>
				  </div> 
       	  	  </c:if>
       	  	  <div position="left" title="子系统" style="height:95%;">
	            	<div class="tree-toolbar">
						<span class="toolBar">
							<div class="group"><a class="link reload" id="treeFresh" href="javascript:loadTree();">刷新</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link expand" id="treeExpandAll" href="javascript:treeExpandAll(true)">展开</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link collapse" id="treeCollapseAll" href="javascript:treeExpandAll(false)">收起</a></div>
						</span>
					</div>
					<ul id="systemTree" class="ztree" style="overflow:auto;height:93%;" ></ul>
			  </div>
		</div>
		<div position="bottom"  class="bottom" style="margin-top:10px;" >
		      	<a href="javascript:;" class="button"  onclick="selectRole()" style="margin-right:10px;" ><span class="icon ok"></span><span class="chosen">选择</span></a>
				<a href="javascript:;" class="button"  onclick="clearRole()"><span class="icon cancel" ></span><span class="chosen" >清空</span></a>
				<a href="javascript:;" class="button"  onclick="dialog.close()" style="margin-left:10px;" ><span class="icon cancel" ></span><span class="chosen" >取消</span></a>
		  </div>
	  <textarea id="roleTemplate" style="display: none;">
	  	<tr id="role_#roleId">
  			<td>
  				<input type="hidden" name="role" value="#data"><span>#name</span>
  			</td>
  			<td style="width: 30px;" nowrap="nowrap"><a onclick="javascript:del(this);" class="link del" title="删除" >&nbsp;</a></td>
		 </tr>
	  </textarea>
	</body>
</html>