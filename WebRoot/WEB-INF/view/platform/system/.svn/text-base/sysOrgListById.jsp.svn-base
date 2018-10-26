<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title></title>
	<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript">
		$(function (){
		 	
			$("a.del").unbind("click" );
			 
			$("#btnDel").click(function(){	 		
				delList('');    
		  	}); 
			handlerDelOne();
		});
	
		function addWin(orgId,flag,path)
		{
			if(orgId=="0"){
				$.ligerDialog.warn("请选择左边组织节点!");
				return;
			}
			else
			{
				location.href="edit.ht?orgId="+orgId+"&flag=LA&path="+path;
			}
		}
		
		function delList(id)
		{
			var value;
			if(id=="")
			{
				var delId="";
				//提交到后台服务器进行日志删除批处理的日志编号字符串
				var $aryId = $("input[type='checkbox'][class='pk']:checked");
				var len=$aryId.length;
				if(len==0){
					$.ligerDialog.warn("还没有选择,请选择一项进行编辑!");
					return;
				}
				else if(len>0)
				{
					$aryId.each(function(i){
						var obj=$(this);
						if(i<len-1){
							delId+=obj.val() +",";
						}
						else
						{
							delId+=obj.val();
						}
					});
				}
			    value=delId;
			}
			else
			{
				value=id;
			}
			var url = "orgdel.ht?orgId="+value;
			$.ligerDialog.confirm('确认删除吗？','提示信息',function(rtn) {
				if(rtn) 
				{
					$.post(url,function(data)  { 
						var obj=new com.hotent.form.ResultMessage(data);
						if(obj.isSuccess()){
							 $.ligerDialog.success(obj.getMessage(),"提示信息",function(rtn){
								 var orgId = "${orgId}"
								 location.href="listById.ht?orgId=" + orgId;
								 var treeNode = parent.getSelectNode();
								 parent.loadTree(treeNode.demId);
							 });
						}else{
							$.ligerDialog.err('出错信息',obj.getMessage());
						}
					});
				}
			});
		}
		
		//处理删除一行
		function handlerDelOne()
		{
			$("table.table-grid td a.link.del").click(function(){
				if($(this).hasClass('disabled')) return false;
				var ele = this;
				var url=ele.href;
				$.ligerDialog.confirm('确认删除吗？','提示信息',function(rtn) {
					if(rtn) {
						$.post(url,function(data)  { 
							var obj=new com.hotent.form.ResultMessage(data);
							if(obj.isSuccess()){
								 var orgId = "${orgId}";
								 location.href="listById.ht?orgId=" + orgId;
								 var treeNode = parent.getSelectNode();
								 parent.loadTree(treeNode.demId);
							}else{
								$.ligerDialog.err('出错信息',obj.getMessage());
							}
						});
					}
				});
				return false;
			});
		}
		
		function goUrl(orgId)
		{
			parent.parent.$("#A").attr("src","get.ht?orgId="+orgId+"&flag=1");
		}
		//树列表的收展
		function treeClick(obj){
				var clazz=$(obj).attr("class");
				var id=$(obj).parents("tr").attr("id");
				if(clazz=="tree-list-minus"){
					toggleChild(id,"hide");
				}
				else if(clazz=="tree-list-plus"){
					toggleChild(id,"show");
				}
				//置换加减号
				$(obj).toggleClass("tree-list-minus");
				$(obj).toggleClass("tree-list-plus");
		};
		//子结点收展
		function toggleChild(parentId,type){
			var child=$("tr[parentId='"+parentId+"']");
			$.each( child, function(i, c){
				if(type=="hide"){
					$(c).hide();
				}
				else if(type=="show"){
					$(c).find("a[name='tree_a']").removeClass("tree-list-plus");
					$(c).find("a[name='tree_a']").addClass("tree-list-minus");
					$(c).show();
				}
				var id=$(c).attr("id");
				toggleChild(id,type);
			});
		};
	</script>
	
	 <style type="text/css"> 
		html{height:100%}
	    body {padding:0px; margin:0;}
	 </style>

</head>
<body>	
		  <c:choose>
		  	<c:when test="${action=='global' }">
		  		<f:tab curTab="type" tabName="sysOrg"/>
		  	</c:when>
		  	<c:otherwise>
		  		<f:tab curTab="type" tabName="sysOrgGrade"/>
		  	</c:otherwise>
		  </c:choose>
		  
		  <c:choose>
       		<c:when test="${empty sysOrg}">
					<div style="text-align: center;margin-top: 10%;">尚未指定具体组织!</div>
			</c:when>
       		<c:otherwise>
       		<div class="hide-panel">
       			<div class="panel-top">
	       		  <div class="tbar-title">
						<span class="tbar-label">下级组织列表</span>
				  	</div>	  
			      <div class="panel-toolbar">	       
					<div class="toolBar">
						<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
						<div class="l-bar-separator"></div>
						<!--  <div class="group"><a class="link add"  href="javascript:;" onclick="addWin('${orgId}','LA','${path}')">添加</a></div> 
						<div class="l-bar-separator"></div>-->
						<div class="group"><a class="link del" id="btnDel" href="javascript:;" ><span></span>删除</a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
					</div>	
				  </div>
				  <div class="panel-search">
		           <form id="searchForm" method="post" action="listById.ht">
					<ul class="row">
							<li><span class="label">组织名称:</span><input type="text" name="Q_orgName_SL"  class="inputText" value="${param['Q_orgName_SL']}"/>
							<input type="hidden" name="orgId"  class="inputText" value="${orgId}" readonly="readonly" value="${param['orgId']}"/>
							<input type="hidden" name="Q_path"  class="inputText" value="${path}" readonly="readonly" value="${param['Q_path']}"/></li>
					</ul>					
		            </form>
	   			</div>
			  </div>
			  </div>
	          <div class="panel-body">
	          	
			   	<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
				
	   				<table id="dicTable" class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">
			   			<thead>
				   			<th width="30px"><input type="checkbox" id="chkall"></th>
				    		<th>组织名称</th>
				    		<th>所属维度</th>
				    		<th>组织描述</th>
				    		<th>上级组织</th>
				    		<th>组织类型</th>				    		
				    		<th>管理</th>
			    		</thead>					    	
			    		<tbody>
			    		<c:forEach items="${sysOrgList}" var="sysOrgItem" varStatus="status">
			    			<tr id="${sysOrgItem.orgId }" parentId="${sysOrgItem.orgSupId }" class="${status.index%2==0?'odd':'even'}">
			    			<td  style="width:30px;text-align:center;">
				    			<input type="checkbox" class="pk" name="orgId" value="${sysOrgItem.orgId}">
				    		</td>
			    			<td nowrap="nowrap">${f:returnSpace(sysOrgItem.path)}
			    				<a name="tree_a" class="tree-list-minus" onclick="treeClick(this)"><span class="tree-list-span">${sysOrgItem.orgName }</span></a>
			    			</td>
			    			<td>${sysOrgItem.demName }</td> 
			    			<td>${sysOrgItem.orgDesc }</td>
			    			<td>${sysOrgItem.orgSupName }</td> 
				    		<td>
			    			<c:choose>
								<c:when test="${sysOrgItem.orgType==1}">
								        集团
							   	</c:when>
							   	<c:when test="${sysOrgItem.orgType==2}">
								        公司
							   	</c:when>
							   	<c:when test="${sysOrgItem.orgType==3}">
								       部门
							   	</c:when>
						       	<c:otherwise>
							                      其他组织      
							   	</c:otherwise>
						     </c:choose>
						     </td>
			   				 <td nowrap="nowrap" style="width:255px;">
			    			
			    			 <a href="orgdel.ht?orgId=${sysOrgItem.orgId}" class="link del">删除</a>
							 <a href="edit.ht?orgId=${sysOrgItem.orgId}&demId=${sysOrgItem.demId}&flag=LE&path=${path}" class="link edit">编辑</a>
							 <a href="edit.ht?orgId=${sysOrgItem.orgId}&demId=${sysOrgItem.demId}&action=add" class="link add">添加子组</a>
							 <a href="get.ht?orgId=${sysOrgItem.orgId}&flag=1&path=${path}" class="link detail">明细</a>
							 </td>
			    			 </tr>
			    		</c:forEach>					    						    					    	
			    	</tbody>					    	
			   	</table>
			   <hotent:paging tableId="sysOrgItem"/>
	   		
	    </div>
    </c:otherwise>
    </c:choose>
</body>
</html>