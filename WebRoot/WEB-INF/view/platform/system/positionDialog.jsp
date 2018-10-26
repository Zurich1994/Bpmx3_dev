<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>岗位选择</title>
<f:link href="tree/zTreeStyle.css"></f:link>
<style type="text/css">
html {
	overflow: hidden;
}

.ztree {
	overflow: auto;
}

.label {
	color: #6F8DC6;
	text-align:left;
	padding-right: 6px;
	padding-left: 0px;
	font-weight: bold;
}
	.panel-search {
    min-width: 321px;
    padding-bottom: 3px;
    padding-left: 0px;
    padding-top: 3px;
}
.l-layout-right{left:578px;}
</style>
<script type="text/javascript"
	src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript">
		/*KILLDIALOG*/
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		var isSingle='${param.isSingle}';
		var scope =eval("("+dialog.get("scope")+")"); 
		var orgTree = null;
		findStr = '';
		//树展开层数
		var expandDepth = 2;
		var type ="";
		var typeVal="";
		if(scope){
			type = scope.type;
			typeVal = scope.value; 
		}
		forbidF5("Chrome");//禁止刷新页面
		$(function() {
			$("#defLayout").ligerLayout({
				leftWidth: 190,
				allowRightResize:false,
				allowLeftResize:false,
				allowTopResize:false,
				allowBottomResize:false,
				height: '90%',
				minLeftWidth:170,
				rightWidth:170
			});
			
			$('#demensionId').change(function(){
        		var demensionId=$(this).val();
        		loadTree(demensionId);
             });
			//首先加载行政维度的数据。
			loadTree(1);
			
			//快速查找
			handQuickFind();
			
			initData();
			
			var  src="${ctx}/platform/system/position/selector.ht?isSingle=${param.isSingle}&type="+type+"&typeVal="+typeVal;
			$("#posFrame").attr("src",src);

		});
		
		function handQuickFind(){
			$("input.quick-find").bind('keyup',function(){
				var str = $(this).val();
				if(!str)return;
				if(str==findStr)return;
				findStr = str;
				var  tbody = $("#orgList"), 	
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
		}
		
		


		//展开收起
		function treeExpandAll(type) {
			orgTree = $.fn.zTree.getZTreeObj("orgTree");
			orgTree.expandAll(type);
		};
		
		function treeExpand() {
			orgTree = $.fn.zTree.getZTreeObj("orgTree");
			var treeNodes = orgTree.transformToArray(orgTree.getNodes());
			for(var i=1;i<treeNodes.length;i++){
				if(treeNodes[i].children){
					orgTree.expandNode(treeNodes[i], true, false, false);
				}
			}
		};
		
		function treeFlesh(){
			var demId=$("#demensionId").val();
			loadTree(demId);
		}
		
		function loadTree(value) {
			var setting = {
					data: {
						key : {
							name: "orgName"
						},
					
						simpleData: {
							enable: true,
							idKey: "orgId",
							pIdKey: "orgSupId",
							rootPId: 0
						}
					},
					async: {
						enable: true,
						url:"${ctx}/platform/system/sysOrg/getTreeData.ht?demId="+value+"&type="+type+"&typeVal="+typeVal,
						autoParam:["orgId","orgSupId"]
					},
					callback:{
						onClick : treeClick,
						onAsyncSuccess: orgTreeOnAsyncSuccess
					}
					
				};
				orgTree=$.fn.zTree.init($("#orgTree"), setting);
		};
		//判断是否为子结点,以改变图标	
		function orgTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
			if(treeNode){
		  	 	var children=treeNode.children;
			  	 if(children.length==0){
			  		treeNode.isParent=true;
			  		orgTree = $.fn.zTree.getZTreeObj("orgTree");
			  		orgTree.updateNode(treeNode);
			  	 }
			}
		};
		//选择分类
		function getSelectNode() {
			orgTree = $.fn.zTree.getZTreeObj("orgTree");
			var nodes = orgTree.getSelectedNodes();
			var node = nodes[0];
			if (node == null || node.orgId == 0)
				return '';
			return node;
		}
	
		function treeClick(event, treeId, treeNode) {
			//取得组织id
			var orgId = '';
			var node = getSelectNode();
			if(node != ''){
				orgId = getSelectNode().orgId;
			}
			var demId = $("#demensionId").val();
			var url = "${ctx}/platform/system/position/selector.ht?orgId=" + orgId + "&demId=" + demId+"&isSingle=${param.isSingle}";
			$("#posFrame").attr("src", url);
			//setOrgId(orgId,demId);
		}
		
		function setOrgId(orgId,demId){
			$("#orgId").val(orgId);
			$("#demId").val(demId);
		}
		
		function dellAll() {
			$("#posList").empty();
		};
		function del(obj) {
			var tr = $(obj).closest("tr");
			$(tr).remove();
		};
		
		//初始化父级窗口传进来的数据
		function initData(){
			//var obj = window.dialogArguments;
			var obj =dialog.get("arrys");
			if(obj&&obj.length>0){
				for(var i=0,c;c=obj[i++];){
					var data = c.id+'#'+c.name;
					if(c.name!=undefined&&c.name!="undefined"&&c.name!=null&&c.name!=""){
						add(data);
					}
				}
			}
		};
		//list列表添加数据
		function add(data) {
			var aryTmp=data.split("#");
			var posId=aryTmp[0];
			var len= $("#pos_" + posId).length;
			if(len>0) return;
			var roleTemplate= $("#posTemplate").val();
			var html=roleTemplate.replace("#posId",posId)
					.replace("#data",data)
					.replace("#name",aryTmp[1]);
			$("#posList").append(html);
		};
	       //子窗口会调用
		function selectMulti(obj) {
			if ($(obj).attr("checked") == "checked"){
				var data = $(obj).val();
				add(data);
			}	
		};
		//选择按钮
		function  selectPosition(){
			var pleaseSelect= "请选择岗位!";
			var aryPos;
			if(isSingle=='true'){
				aryPos = $('#posFrame').contents().find(":input[name='posId'][checked]");
			}else{
				aryPos =$("input[name='pos']", $("#posList"));
			}
			if(aryPos.length==0){
				alert(pleaseSelect);
				return;
			}
			var aryId=[];
			var aryName=[];
			var aryJobName=[];
			var aryJobGradeName=[];
			var posJson = [];
			var node = getSelectNode();
			var orgId = '';
			var orgName = '';
			var node = getSelectNode();
			if(node != ''){
				orgId = getSelectNode().orgId;
				orgName = getSelectNode().orgName;
			}
			aryPos.each(function(){
				var data=$(this).val();
				var aryTmp=data.split("#");
				aryId.push(aryTmp[0]);
				aryName.push(aryTmp[1]);
				aryJobName.push(aryTmp[2]);
				aryJobGradeName.push(aryTmp[3]);
				posJson.push({id:aryTmp[0],name:aryTmp[1],jobName:aryTmp[2],jobGradeName:aryTmp[3],orgId:orgId,orgName:orgName});
			});
			var posIds=aryId.join(",");
			var posNames=aryName.join(",");
			var posJobName=aryJobName.join(",");
			var posJobGradeName=aryJobGradeName.join(",");
			
			var obj={};
			obj.posId=posIds;
			obj.posName=posNames;
			obj.posJobName=posJobName;
			obj.posJobGradeName=posJobGradeName;
			obj.posOrgId=orgId;
			obj.posOrgName=orgName;
			obj.posJson = posJson;
			//window.returnValue=obj;
			dialog.get("sucCall")(obj);
			dialog.close();
		}
		
		//清空岗位
		function clearPosition(){
			//window.returnValue={posId:'',posName:''};
			var rtn={posId:'',posName:''};
			dialog.get("sucCall")(rtn);
			dialog.close();
		}
		function treeFresh() {
			loadTree(1);
			setTimeout(function() {
				curStatus = true;
				treeExpandAll(false, true);
			}, 100);
		}

	</script>
	<style type="text/css">
		html { overflow-x: hidden; }
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
				.panel-search {
    min-width: 321px;
    padding-bottom: 3px;
    padding-left: 0px;
    padding-top: 3px;
}
.l-layout-right{left:578px;}
	</style>
</head>
<body>
	<div id="defLayout">
		<div  position="left" title="组织树" style="float: left;width: 100% !important;height:95%;;">
			 <div style="width:100%;">
		        <select id="demensionId"  style="width:99.8% !important;">  
		              <c:forEach var="dem" items="${demensionList}">  
		              	<option  value="${dem.demId}" <c:if test="${dem.demId==1 }">selected='selected'</c:if> >${dem.demName}</option>  
		              </c:forEach>  
		        </select>
	         </div>

			<div class="tree-toolbar">
				<span class="toolBar">
					<div class="group">
						<a class="link reload" id="treeFresh" href="javascript:treeFlesh();">刷新</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link expand" id="treeExpandAll"
							href="javascript:treeExpand()">展开</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link collapse" id="treeCollapseAll" href="javascript:treeExpandAll(false)">收起</a>
					</div>
				</span>
			</div>
			<ul id="orgTree" class="ztree" style="overflow:auto;height:89%;"></ul>
		</div>
		<div position="center">
			<div class="l-layout-header">岗位列表</div>
			<iframe id="posFrame" name="posFrame" height="95%" width="100%"  
			frameborder="0" ></iframe>
		</div>
		<c:if test="${param.isSingle==false}">
			   <div position="right" title="<span><a onclick='javascript:dellAll();' class='link del'>清空 </a><input type='text' class='quick-find' title='快速查询'/></span>" style="overflow: auto;height:95%;width:170px;">
          			<table width="145"   class="table-grid table-list"  cellpadding="1" cellspacing="1">
          				<tbody id="posList">
          					<tr class="hidden"></tr>
          				</tbody>
					</table>
			  </div>
		</c:if>
	</div>
	 <div position="bottom"  class="bottom" style='margin-top:10px;'>
		<a href='#' class='button'  onclick="selectPosition()" style="margin-right:10px;"><span class="icon ok"></span><span>选择</span></a>
		<a href="javascript:;" class="button"  onclick="clearPosition()"><span class="icon cancel" ></span><span class="cance" >清空</span></a>
		<a href='#' class='button' style='margin-left:10px;'  onclick="dialog.close()"><span class="icon cancel"></span><span>取消</span></a>
	</div>
	<textarea style="display: none;" id="posTemplate">
		<tr id="pos_#posId">
	  			<td>
	  				<input type="hidden" name="pos" value="#data"><span>#name</span>
	  			</td>
	  			<td style="width: 30px;" nowrap="nowrap"><a onclick="javascript:del(this);" class="link del" title="删除">&nbsp;</a> </td>
	  	</tr>
	</textarea>
</body>
</html>