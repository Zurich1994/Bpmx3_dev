<%@page pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title>选择考勤档案</title>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	var isSingle='${isSingle}';
	$(function(){
		//布局
		$("#listLayout").ligerLayout({
			 bottomHeight :40,
			 height: '90%',
			 allowBottomResize:false,
			 minLeftWidth:200,
			 allowLeftResize:false
		});
		initData();
	});
	
	function initData(){
		var obj=dialog.get("params");
		
		if(obj&&obj.length>0){
			for(var i=0,c;c=obj[i++];){
				var data = c.id+'#'+c.userName+'#'+c.account+'#'+c.orgName  ;
				add(data);
			}
		}
	}
	
	function selectMulti(obj) {
		if ($(obj).attr("checked") == "checked") {
			var data = $(obj).val();
			add(data);
		}
	};
	function add(data) {
		var aryTmp = data.split("#");
		var fileId = aryTmp[0];
		var len = $("#attendanceFile_" + fileId).length;
		if (len > 0) return;
		var attendanceFileTemplate = $("#attendanceFileTemplate").val();
		var html = attendanceFileTemplate.replace("#fileId", fileId)
			.replace("#data", data)
			.replace("#name", aryTmp[1]);
		$("#attendanceFileList").append(html);
	};
	function del(obj) {
		var tr = $(obj).closest("tr");
		$(tr).remove();
	};
	function dellAll() {
		$("#attendanceFileList").empty();
	};
	function selectData(){
		
		var chIds;
		if(isSingle=='true'){
			chIds =$('#listFrame').contents().find(":input[name='data'][checked]");
		}else{
			chIds = $("input[name='file']", $("#attendanceFileList"));
		}
		
		var ids =[],userNames =[],accounts=[],orgNames=[];
		
		$.each(chIds,function(i,ch){
			var aryTmp=$(ch).val().split("#");
			ids.push(aryTmp[0]);
			userNames.push(aryTmp[1]);
			accounts.push(aryTmp[2]);
			orgNames.push(aryTmp[3]);
		});
			
		var obj={id:ids.join(","),userName:userNames.join(","),account:accounts.join(","),orgName:orgNames.join(",")};
		dialog.get('sucCall')(obj);
		dialog.close();
	}
		
	function clearData(){
		var obj={id:'',name:''};
		dialog.get('sucCall')(obj);
		dialog.close();
	}
</script>
<style type="text/css">
	div.bottom{text-align: center;padding-top: 10px;}
	html,body{width:100%;height:100%;margin: 0 0 0 0;padding: 0 0 0 0 ;overflow: hidden;}
</style>
</head>
<body>
	<div id="listLayout" style="height:100%;">
		<div position="center">
				<iframe id="listFrame" name="listFrame" height="95%" width="100%" frameborder="0"
					src="${ctx}/platform/ats/atsAttendanceFile/selector.ht?isSingle=${isSingle}"
				 ></iframe>
		</div>
		<c:if test="${param.isSingle==false}">
			   <div position="right" title="<span><a onclick='javascript:dellAll();' class='link del'>清空 </a><input type='text' class='quick-find' title='快速查询'/></span>" style="overflow: auto;height:95%;width:170px;">
          			<table width="145"   class="table-grid table-list"  cellpadding="1" cellspacing="1">
          				<tbody id="attendanceFileList">
          					<tr class="hidden"></tr>
          				</tbody>
					</table>
			  </div>
		</c:if>
		<textarea style="display: none;" id="attendanceFileTemplate">
			<tr id="attendanceFile_#fileId">
		  			<td>
		  				<input type="hidden" name="file" value="#data"><span>#name</span>
		  			</td>
		  			<td style="width: 30px;" nowrap="nowrap"><a onclick="javascript:del(this);" class="link del" title="删除">&nbsp;</a> </td>
		  	</tr>
		</textarea>
	</div>
	
	<div position="bottom"  class="bottom" style="margin-top:10px;">
		<a href="javascript:;" class="button"  onclick="selectData()" style="margin-right:10px;" ><span class="icon ok"></span><span >选择</span></a>
		<a href="javascript:;" class="button"  onclick="clearData()"><span class="icon cancel" ></span><span class="chosen" >清空</span></a>
		<a href="javascript:;" class="button" style="margin-left:10px;"  onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a>
	</div>
</body>
</html>


