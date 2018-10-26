<%@page pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html"%>
<html>
	<head>
		<title>选择职务</title>
		<%@include file="/commons/include/form.jsp" %>
		<f:link href="tree/zTreeStyle.css"></f:link>
	    <script type="text/javascript"	src="${ctx}/js/tree/jquery.ztree.js"></script>
	    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js" ></script>
		
 
		<script type="text/javascript">
 		    var systemTree=null, isSingle="${param.isSingle}",findStr = '';
			$(function(){
				$("#defLayout").ligerLayout({ leftWidth: 0,rightWidth: 170,allowRightResize:false,allowLeftResize:false,allowTopResize:false,allowBottomResize:false,height: '90%',minLeftWidth:170});
				
				initData();
				$("#jobFrame").attr("src","${ctx}/platform/system/job/selector.ht?isSingle=${param.isSingle}");
			});
		
			//初始化父级窗口传进来的数据
			function initData(){
				var obj = window.dialogArguments;
				if(obj&&obj.length>0){
					for(var i=0,c;c=obj[i++];){
						var data = c.id+'#'+c.name;
						if(c.name!=undefined&&c.name!="undefined"&&c.name!=null&&c.name!=""){
							add(data);
						}
					}
				}
			};
			
			function selectJob(){
				var pleaseSelect= "请选择角色!";
				//单选
				if(isSingle==true){
					var chIds = $('#jobFrame').contents().find("input[name='jobid']:checked");
					if(chIds.length==0){
						alert("请选择");
						return;
					}
					var data=chIds.val();
					var aryJob=data.split("#");
					var obj={};
					obj.jobId=aryJob[0];
					obj.jobName=aryJob[1];
					window.returnValue=obj;
				
				}
				//复选
				else{
					var aryJobs =$("input[name='job']", $("#jobList"));
					if(aryJobs.length==0){
						alert(pleaseSelect);
						return;
					}
					var aryId=[];
					var aryName=[];
					var json = [];
					aryJobs.each(function(){
						var data=$(this).val();
						var aryJob=data.split("#");
						aryId.push(aryJob[0]);
						aryName.push(aryJob[1]);
						json.push({id:aryJob[0],name:aryJob[1]});
					});
					var jobIds=aryId.join(",");
					var jobNames=aryName.join(",");
					
					var obj={};
					obj.jobId=jobIds;
					obj.jobName=jobNames;
					obj.jobJson = json;
					window.returnValue=obj;
				}
				
				window.close();
			};
			function add(data) {
				var aryTmp=data.split("#");
				var jobId=aryTmp[0];
				var len= $("#job_" + jobId).length;
				if(len>0) return;
				var jobTemplate= $("#jobTemplate").val();
				var html=jobTemplate.replace("#jobid",jobId)
						.replace("#data",data)
						.replace("#name",aryTmp[1]);
				$("#jobList").append(html);
			};
			function selectMulti(obj) {
				if ($(obj).attr("checked") == "checked"){
					var data = $(obj).val();
					add(data);
				}	
			};
			
			function dellAll() {
				$("#jobList").empty();
			};
			function del(obj) {
				var tr = $(obj).closest("tr");
				$(tr).remove();
			};
			//清空角色
			function clearJob(){
				window.returnValue={jobId:'',jobName:''};
				window.close();
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
		
				 <div position="center">
	          		<iframe id="jobFrame" name="jobFrame" height="100%" width="100%" frameborder="0" src="${ctx}/platform/system/job/selector.ht?isSingle=${param.isSingle}"></iframe>
	             </div>
	             
		          <div position="right" title="<span><a onclick='javascript:dellAll();' class='link del'>清空</a><input type='text' class='quick-find' title='查找'/></span>" style="overflow: auto;height:95%;width:170px;">
	          			<table width="145"   class="table-grid table-list"  cellpadding="1" cellspacing="1">
	          				<tbody id="jobList">
	          					<tr class="hidden"></tr>
	          				</tbody>
						</table>
				  </div> 
				 <%-- 
       	         <div position="left" title="子系统" style="height:95%;">
	            	<div class="tree-toolbar">
					</div>
					<ul id="systemTree" class="ztree" style="overflow:auto;height:93%;" ></ul>
			    </div>
			    --%>
		</div>
		<div position="bottom"  class="bottom" style="margin-top:10px;" >
		      	<a href="javascript:;" class="button"  onclick="selectJob()" style="margin-right:10px;" ><span class="icon ok"></span><span class="chosen">选择</span></a>
				<a href="javascript:;" class="button"  onclick="clearJob()"><span class="icon cancel" ></span><span class="chosen" >清空</span></a>
				<a href="javascript:;" class="button"  onclick="window.close()" style="margin-left:10px;" ><span class="icon cancel" ></span><span class="chosen" >取消</span></a>
				 
		  </div>
		  
	  <textarea id="jobTemplate" style="display: none;">
	  	<tr id="job_#jobid">
  			<td>
  				<input type="hidden" name="job" value="#data"><span>#name</span>
  			</td>
  			<td style="width: 30px;" nowrap="nowrap"><a onclick="javascript:del(this);" class="link del" title="删除" >&nbsp;</a></td>
		 </tr>
	  </textarea>
	  
	</body>
</html>