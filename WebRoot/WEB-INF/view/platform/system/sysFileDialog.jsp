<%@page import="com.hotent.platform.model.system.GlobalType"%>
<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%
	String basePath = request.getContextPath();
	String isSingle = request.getParameter("isSingle");
	request.setAttribute("isSingle", isSingle);
%>

<html>
<head>
<%@include file="/commons/include/form.jsp" %>

<title>附件选择</title>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMenu.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/GlobalType.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/FileMenu.js"></script>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
	var isSingle=${isSingle};
	
	var catKey="<%=GlobalType.CAT_FILE%>";
	
	var url="${ctx}/platform/system/globalType/getPersonType.ht";
	
	var fileMenu=new FileMenu();
	
	var curMenu;
	
	var conf={url:url,onClick:treeClick,onRightClick:zTreeOnRightClick,expandByDepth:1};
	
	var globalType=new GlobalType(catKey,"glTypeTree",conf);
	
	$(function() {
		$("#defLayout").ligerLayout({
			leftWidth : 195,
			height : '100%',
			allowLeftResize :false,
			bottomHeight:40
		});
		globalType.loadGlobalTree();
		
		var findStr = '';
		//快速查找
		$("input.quick-find").bind('keyup',function(){
			var str = $(this).val();
			if(!str)return;
			if(str==findStr)return;
			findStr = str;
			var  tbody = $("#sysFileList"), 	
				 firstTr = $('tr.hidden',tbody);
			$("tr",tbody).each(function(){
				var me = $(this),
					div = $('div',me),
					divStr = div.html();
				if(!divStr) return true;
				var fileStr = eval("("+divStr+")");
				var fileName = fileStr.fileName;
				if(!fileName)return true;						
				if(fileName.indexOf(findStr)>-1){
					$(this).insertAfter(firstTr);
				}
			});
		});
	});
	
	//展开收起
	function treeExpandAll(type) {
		globalType.treeExpandAll(type);
	};
	
	function treeClick(treeNode) {
		var typeId=treeNode.typeId;
		var url = "${ctx}/platform/system/sysFile/selector.ht?typeId=" + typeId +"&isSingle=${isSingle}";
		$("#fileFrame").attr("src", url);
	}
	
	/**
	 * 树右击事件
	 */
	function zTreeOnRightClick(event, treeId, treeNode) {
		
		if (treeNode) {
			globalType.currentNode=treeNode;
			globalType.glTypeTree.selectNode(treeNode);
			curMenu=fileMenu.getMenu(treeNode, handler);
			curMenu.show({ top: event.pageY, left: event.pageX });
		}
	};
	
	 function hiddenMenu(){
		if(curMenu){
			curMenu.hide();
		}
	 }
     
     function handler(item){
     	hiddenMenu();
     	var key=item.key;
     	switch(key){
     		case "add":
     			globalType.openGlobalTypeDlg(true);
     			break;
     		case "edit":
     			globalType.openGlobalTypeDlg(false);
     			break;
     		case "del":
     			globalType.delNode();
     			break;
     		case "addMy":
     			globalType.openGlobalTypeDlg(true,true);
     			break;
     	}
     }

	/**
	 * 添加选中的数据
	 */
	function add(ch){
		//所选
		var fileId = $(ch).val();
		var objData= $(ch).next();
		if($("#file_"+fileId).length>0) return;
		var fileObj=eval("("+ objData.val() +")");
		var tr=getRow(fileObj);
		$("#sysFileList").append(tr);
	};
	
	function addFileObj(fileObj){
		var fileId=fileObj.fileId;
		if($("#file_"+fileId).length>0) return;
		var tr=getRow(fileObj);
		$("#sysFileList").append(tr);
	}
	
	function getRow(fileObj){
		var template=$("#trTemplate").val();
		template=template.replaceAll("#fileId",fileObj.fileId).replaceAll("#fileName",fileObj.fileName);
		return template;
	}

	function delRow(obj){
		var tr=$(obj).parents("tr");
		$(tr).remove();
	};
	/**
	 * 清空所有的
	 */
	function dellAll(){
		$("#sysFileList").empty();
	};
	/**
	 * 选中所有
	 */
	function selectMulti(obj){
		if($(obj).attr("checked") == "checked"){
			add(obj);	
		}
		else{
			var fileId = $(obj).val();
			$("#file_" + fileId,"#sysFileList").remove();
		}
	};

	function selectAll(obj){
		var state = $(obj).attr("checked");
		if(state == undefined) {
			checkAll(false);		
		} else {		
			checkAll(true);
		}
	};

	function checkAll(checked){
		$("#fileFrame").contents().find("input[type='checkbox'][class='pk']").each(function(){
			$(this).attr("checked", checked);
			if(checked){
				add(this);
			}
		});
	};


	
	 /**
	 * 文件上传，窗口
	 */
	function fileUpload(){
		var url="${ctx}/platform/system/sysFile/uploadDialog.ht";
		var currentNode=globalType.currentNode;
		if(currentNode && currentNode.isRoot==undefined){
			url+="?typeId="+currentNode.typeId;
		}
	 	var winArgs="dialogWidth:500px;dialogHeight:300px";
	 	url=url.getNewUrl();
	 	/* var rtn=window.showModalDialog(url,"",winArgs);
	 	if(!rtn) return;
	 	if(!isSingle){
	 		for(var i=0;i<rtn.length;i++){
	 			var fileObj=rtn[i];
	 			addFileObj(fileObj);
	 		}
	 	}
 		var myiframe = document.getElementById("fileFrame");
	 	myiframe.src=myiframe.src.getNewUrl(); */
	 	
	 	var that = this;
	 	DialogUtil.open({
	 		height:300,
	 		width: 500,
	 		title : '文件上传',
	 		url: url, 
	 		isResize: true,
	 		//自定义参数
	 		sucCall:function(rtn){
	 			if(!isSingle){
	 		 		for(var i=0;i<rtn.length;i++){
	 		 			var fileObj=rtn[i];
	 		 			addFileObj(fileObj);
	 		 		}
	 		 	}
	 	 		var myiframe = document.getElementById("fileFrame");
	 		 	myiframe.src=myiframe.src.getNewUrl();
	 			
	 		 	try{
	 				conf.callback.call(that,userIds,fullnames,emails,mobiles);
	 		 	}catch(e){
	 		 		
	 		 	}
	 		}
	 	});
	}
	
	function selectFile(){
		var chIds;
		var aryFileData=null;
		if (isSingle==1) {
			chIds = $('#fileFrame').contents().find(":input[name='fileId'][checked]");
			aryFileData=chIds.next();
		} else {
			aryFileData = $("#sysFileList").find("div[name='fileData']");
		}
		
		var aryFileId = new Array();
		var aryFileName = new Array();
		var aryPath = new Array();
		var aryExt = new Array();
		$.each(aryFileData, function(i, ch) {
			var data=eval("(" + $(ch).text() +")");
			aryFileId.push(data.fileId);
			aryFileName.push(data.fileName);
			var ext=$.getFileExtName(data.fileName);
			aryPath.push("/platform/system/sysFile/file_id" +data.fileId +".ht");
			aryExt.push(ext);
			
		});
		var obj={fileIds:aryFileId.join(','),fileNames:aryFileName.join(','),filePaths:aryPath.join(','),extPath:aryExt.join(",")};
		//window.returnValue=obj;
		dialog.get("sucCall")(obj);
		dialog.close();
	}
	
</script>
<style type="text/css">
body{overflow: hidden;}
.l-layout-right{left:528px;}
.l-layout-left, .l-layout-center, .l-layout-right { height:90%;}
.l-accordion-content { height:324px;}
.l-accordion-content .ztree { height:285px;}
.quick-find {width:35px;}	
</style>
</head>
<body>
	<div id="defLayout" style="height:100%;">
		 <div position="left" title="附件分类" style="overflow: auto;float:left;width:210px;height:92%;">
            	<div class="tree-toolbar">
					<span class="toolBar">
						<div class="group"><a class="link reload" id="treeFresh" href="javascript:void(0);" onclick="globalType.loadGlobalTree();return false;" title="刷新" ></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link expand" id="treeExpandAll" href="javascript:void(0);" onclick="treeExpandAll(true);return false;" title="展开"></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link collapse" id="treeCollapseAll" href="javascript:void(0);" onclick="treeExpandAll(false);return false;" title="收起" ></a></div>	
					</span>
				</div>
				<ul id="glTypeTree" class="ztree"></ul>
        </div>
		
		<div position="center" style="height:100%;">
		<div class="l-layout-header">附件列表</div>			
			<iframe id="fileFrame" name="fileFrame"  height="92%"  width="100%" frameborder="0" src="${ctx}/platform/system/sysFile/selector.ht?isSingle=${isSingle}"></iframe>
		</div>
		<c:if test="${isSingle == 0}">
			<div position="right" title="<span><a onclick='javascript:dellAll();' class='link del'>清空</a><input type='text' class='quick-find' title='查找'/></span>" style="height:92%;overflow:auto;">
				<table width="145"  class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">
					<tbody id="sysFileList">
						<tr class="hidden"></tr>
					</tbody>
				</table>
			</div>
		</c:if>
		<div position="bottom"  class="bottom" >
				<a href='#' class='button'  onclick="fileUpload();" ><span class="icon upload"></span><span class="chosen">上传</span></a>
				<a href='#' class='button' style='margin-left:10px;'  onclick="selectFile()" ><span class="icon ok"></span><span class="chosen">选择</span></a>
				<a href='#' class='button' style='margin-left:10px;'  onclick="dialog.close()"><span class="icon cancel"></span><span class="chosen">取消</span></a>
		</div>
	</div>
	<textarea id="trTemplate" style="display: none;">
		<tr id="file_#fileId">
			<td>
				#fileName<div name="fileData" style="display: none;">{fileId:'#fileId',fileName:'#fileName'}</div>
			</td>
			<td>
				<a onclick="javascript:delRow(this);" class="link del">删除</a> 
			</td>
		</tr>
	</textarea>
</body>
</html>