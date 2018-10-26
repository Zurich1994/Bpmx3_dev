<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<title>子表字段排序配置</title>
		<%@include file="/commons/include/form.jsp"%>
		<f:link href="tree/zTreeStyle.css"></f:link>
	    <script type="text/javascript"	src="${ctx}/js/tree/jquery.ztree.js"></script>

		<script type="text/javascript">
		    /*KILLDIALOG*/
		    var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		    var tableId = "${tableId}";
		    var sortTree = null;
		    $(function(){
		    	$("#layout").ligerLayout({
					leftWidth : 225,
					height : '100%',
					allowLeftResize : true
				});
		    	loadTree();
		    	initSortField();
		    	var options={};
				if(showResponse){
					options.success=showResponse;
				}	
		    	$("a.close").click(function(){close()});
		    	$("a.save").click(function(){saveData(options)});
		    	
		    })
		    
		    function loadTree() {
				var setting = {
						data: {
							key : {
								name: "sortDesc"
							},
						
							simpleData: {
								enable: true,
								idKey: "sortId",
								pIdKey: "supSortId",
								rootPId: 0
							}
						},
						
						view : {
							selectedMulti : false
						},
						async: {
							enable: true,
							url:"${ctx}/platform/form/subTableSort/getTreeData.ht?tableId="+tableId,
							autoParam:["sortId","supSortId"]
						},
						callback:{
							onClick : zTreeOnLeftClick
						}
						
					};
				sortTree = $.fn.zTree.init($("#sortTree"), setting);
				};
	
	//左击事件
	function zTreeOnLeftClick(event, treeId, treeNode) {
		if(treeNode.isField=='1'){
		 	var supId = treeNode.supSortId, name = treeNode.sortName, desc = treeNode.sortDesc;
		 	var parNode = sortTree.getNodeByParam( 'sortId',supId );
		 	var tableName = parNode.sortName;
		 	var isTrObj = $("tr[trTableName='"+tableName+"']",$("#sortTbl"));
		 	if(isTrObj.length==0){
		 		var tr = $($("#sortTemplate .table-detail tr")[0]).clone(true, true);
		 		tr.attr("trTableName",tableName);
		 		$("[var='tableName']", tr).html(tableName);
		 		$("[var='tableName']", tr).attr("tdTableName",tableName);
				$("[var='name']", tr).html(name);
				$("[var='desc']", tr).html(desc);
				$("#sortTbl tbody").append(tr);
		 	}else{
		 		if(isExistName(tableName,name)){
			 		var tdObj = $("td[tdTableName='"+tableName+"']",$("tr[trTableName='"+tableName+"']"));
			 		tdObj.attr("rowspan",isTrObj.length+1);
			 		var tr = $($("#sortTemplate .table-detail tr")[1]).clone(true, true);
			 		tr.attr("trTableName",tableName);
			 		$("[var='name']", tr).html(name);
					$("[var='desc']", tr).html(desc);
					isTrObj.last().after(tr); 
		 		}
		 	}
		}
	};
	
	/**
	 * 判断选择是否存在
	 */
	  function isExistName(tableName,name) {
		var rtn = true;
		$("#sortTbl").find("tr[trTableName='"+tableName+"']").each(function() {
			var me = $(this);
			var tn = me.find("td[var='name']").html();
			if(tn == name){
				rtn = false;
				return true;
			}
		});
		return rtn;
	};
		    
		    
	    function saveData(options){
	    	var json = JSON2.stringify(getJson());
	    	var actDefKey = $("#actDefKey").val();
	    	var form = $('<form method="post" action="save.ht"></form>');
			var inputJson = $("<input type='hidden' name='json'/>");
			var inputKey = $("<input type='hidden' name='actDefKey'/>");
			
			inputJson.val(json);
			form.append(inputJson);
			inputKey.val(actDefKey);
			form.append(inputKey);
			form.ajaxForm(options);
			form.submit();
	    }
	    //获取选择字段的信息转换为json对象
	    function getJson(){
	    	var json=new Array();
			$("#sortTbl tbody tr").each(function(){
				var me=$(this),obj={};
					obj.name =$("[var='name']",me).html();
					obj.desc =$("[var='desc']",me).html();
					obj.sort =$("[var='sort']",me).val();
				var tableName =me.attr("trtablename");
				var tableObj=getObject(json,tableName);
				if(tableObj){
					tableObj["field"].push(obj);
				}else{
					tableObj={};
					var fields= [];
					fields.push(obj);
					tableObj.tableName=tableName;
					tableObj.field=fields;
					json.push(tableObj);
				}
			});
			return json;
	    }
	    //根据表名获取在以保存的字段数组中已存在的对象
	    function getObject(fields,tableName){
	    	for(var i in fields){
				var obj = fields[i];
				if(obj["tableName"]==tableName){
					return obj;
				}
			}
	    	return null;
	    }
	    
	    
	    function showResponse(responseText) {
			$.ligerDialog.closeWaitting();
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success( obj.getMessage(),"提示信息", function(rtn) {
					window.close();
				});
				
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		    
		 
			//初始化数据
			 function initSortField() {
				var sortFieldVal = $("#sortField").val();
				if ($.isEmpty(sortFieldVal))
					return;
				var sortField = $.parseJSON(sortFieldVal);
				for (var i = 0, c; c = sortField[i++];) {
						var fields = c.field;
						for(var j = 0, d; d = fields[j++];){
							if(j==1){
								var tr = $($("#sortTemplate .table-detail tr")[0]).clone(true, true);
								tr.attr("trTableName",c.tableName);
								var tdTableName = $("[var='tableName']", tr);
								tdTableName.html(c.tableName);
								tdTableName.attr("tdtablename",c.tableName);
								tdTableName.attr("rowspan",fields.length);
								$("[var='name']", tr).html(d.name);
								$("[var='desc']", tr).html(d.desc);
								// 修复jquery clone的下拉赋值的bug。
								$("[var='sort']", tr).val(d.sort);
								$("#sortTbl tbody").append(tr);
							}else{
								var tr = $($("#sortTemplate .table-detail tr")[1]).clone(true, true);
								tr.attr("trTableName",c.tableName);
								$("[var='name']", tr).html(d.name);
								$("[var='desc']", tr).html(d.desc);
								// 修复jquery clone的下拉赋值的bug。
								$("[var='sort']", tr).val(d.sort);
								var TrObj = $("tr[trTableName='"+c.tableName+"']",$("#sortTbl"));
								TrObj.last().after(tr); 
							}
						}
				}
		
			}
			
			
			var tdCloneObj=$("<td var='tableName'></td>")
			function delTr(obj){
				var meTr = $(obj).closest("tr");
				var tableName = meTr.attr("trTableName");
				var nextTr = meTr.next("tr[trTableName='"+tableName+"']");
				var isFirstTr = $("td[tdTableName='"+tableName+"']",meTr);
				if(isFirstTr.length>0){
					//如果是同一个表名下的第一行，而且还有下一行的，则对下一行进行重构，否则直接移除
					if(nextTr.length>0){
						var length = isFirstTr.attr("rowspan");
						var tdObj = tdCloneObj.clone(true,true);
						tdObj.attr("var","tableName");
						tdObj.html(tableName);
						tdObj.attr("tdTableName",tableName);
						tdObj.attr("rowspan",length-1);
						$("[var='name']",nextTr).before(tdObj);
					}
				}else{
					//如果是同一个表名下的第一行以外的其他行，则修改第一行的跨行数
					var tdObj = $("td[tdTableName='"+tableName+"']",$("tr[trTableName='"+tableName+"']"));
			 		tdObj.attr("rowspan",tdObj.attr("rowspan")-1);
				}
				meTr.remove();
			}
			
			function moveTr(obj,isUp){
				var meTr = $(obj).closest("tr");
				var tableName = meTr.attr("trTableName");
				//判断当前是否为第一个
				var isFirstCurTr = meTr.find("td[tdTableName='"+tableName+"']");
				if(isUp){
					if(isFirstCurTr.length==0){
						var prevTr = meTr.prev("tr[trTableName='"+tableName+"']");
						var isFirstPreTr = $("td[tdTableName='"+tableName+"']",prevTr);
						if(isFirstPreTr.length!=0){
							var cloneTd = isFirstPreTr.clone(true,true);
							$("[var='name']",meTr).before(cloneTd);
							isFirstPreTr.remove();
						}
						meTr.insertBefore(prevTr);
					}
				}else{
					var nextTr = meTr.next("tr[trTableName='"+tableName+"']");
					if(nextTr.length!=0){
						if(isFirstCurTr!=0){
							var cloneTd = isFirstCurTr.clone(true,true);
							$("[var='name']",nextTr).before(cloneTd);
							isFirstCurTr.remove();
						}
						meTr.insertAfter(nextTr);
					}
				}
			}
			
			function close(){
				dialog.close();
			}
			
		</script>
	</head>
<body>
<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">子表字段排序配置</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
							</div>
							<div class="group">
								<a class="link close" ><span></span>关闭</a>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
	<div id="layout" style="bottom: 1; top: 1">
		<div position="left" title="表管理" id="rogTree"
			style="height: 100%; width: 100% !important;">
			<input type="hidden" id="actDefKey" value="${actDefKey}"/>
			<input type="hidden" id="sortField" value='${sortField }'/>
			<ul id="sortTree" class="ztree" style="overflow:auto;"></ul>
		</div>
		<div position="center" id="orgView" style="height: 100%;">
			<div class="l-layout-header">排序信息</div>
			<table id="sortTbl" cellpadding="0" cellspacing="0" border="0" class="table-detail">
				<thead>
					<tr class="leftHeader">
						<th nowrap="nowrap">表名</th>
						<th nowrap="nowrap">列名</th>
						<th nowrap="nowrap">注释</th>
						<th nowrap="nowrap">排序</th>
						<th nowrap="nowrap">管理</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	<div id="sortTemplate" style="display: none;">
		<table   class="table-detail">
			<tr var="sortTr">
				<td var="tableName">&nbsp;</td>
				<td var="name">&nbsp;</td>
				<td var="desc">&nbsp;</td>
				<td><select var="sort">
						<option value="ASC">升序</option>
						<option value="DESC">降序</option>
				</select></td>
				<td>
				 	<a class="link moveup" href="javascript:;" title="上移"
						onclick="moveTr(this,true)"></a> 
					<a class="link movedown" href="javascript:;" title="下移"
						onclick="moveTr(this,false)"></a>
					<a class="link del" href="javascript:;" title="删除"
					onclick="delTr(this)"></a>
					</td>
			</tr>
			<tr var="sortTr">
				<td var="name">&nbsp;</td>
				<td var="desc">&nbsp;</td>
				<td><select var="sort">
						<option value="ASC">升序</option>
						<option value="DESC">降序</option>
				</select></td>
				<td>
				 	<a class="link moveup" href="javascript:;" title="上移"
						onclick="moveTr(this,true)"></a> 
					<a class="link movedown" href="javascript:;" title="下移"
						onclick="moveTr(this,false)"></a>
					<a class="link del" href="javascript:;" title="删除"
					onclick="delTr(this)"></a>
					</td>
			</tr>
		</table>
	</div>
	</div>

</body>
<style type="text/css">
html {height: 100%}
body {padding: 0px;margin: 0;overflow: hidden;}
#layout {width: 99.5%;margin: 0;padding: 0;}
</style>
</html>