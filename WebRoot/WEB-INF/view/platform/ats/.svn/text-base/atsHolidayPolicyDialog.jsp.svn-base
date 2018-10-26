<%@page pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title>选择考勤制度</title>
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
	});
	function selectMulti(obj) {
		if ($(obj).attr("checked") == "checked") {
			var data = $(obj).val();
			add(data);
		}
	};
	
	function add(data) {
		var aryTmp = data.split("#");
		var policyId = aryTmp[0];
		var len = $("#holidayPolicy_" + policyId).length;
		if (len > 0) return;
		var holidayPolicyTemplate = $("#holidayPolicyTemplate").val();
		var html = holidayPolicyTemplate.replace("#policyId", policyId)
			.replace("#data", data)
			.replace("#name", aryTmp[1]);
		$("#holidayPolicyList").append(html);
	};
	function del(obj) {
		var tr = $(obj).closest("tr");
		$(tr).remove();
	};
	function dellAll() {
		$("#holidayPolicyList").empty();
	};
	
	function selectData(){
		var chIds;
		if(isSingle=='true'){
			chIds =$('#listFrame').contents().find(":input[name='data'][checked]");
		}else{
			chIds = $("input[name='policy']", $("#holidayPolicyList"));
		}
		
		var ids =[],
		 	names =[];
		
		$.each(chIds,function(i,ch){
			var aryTmp=$(ch).val().split("#");
			ids.push(aryTmp[0]);
			names.push(aryTmp[1]);
		});
			
		var obj={id:ids.join(","),name:names.join(",")};
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
					src="${ctx}/platform/ats/atsHolidayPolicy/selector.ht?isSingle=${isSingle}"
				 ></iframe>
		</div>
		<c:if test="${param.isSingle==false}">
			   <div position="right" title="<span><a onclick='javascript:dellAll();' class='link del'>清空 </a><input type='text' class='quick-find' title='快速查询'/></span>" style="overflow: auto;height:95%;width:170px;">
          			<table width="145"   class="table-grid table-list"  cellpadding="1" cellspacing="1">
          				<tbody id="holidayPolicyList">
          					<tr class="hidden"></tr>
          				</tbody>
					</table>
			  </div>
			  <textarea style="display: none;" id="holidayPolicyTemplate">
				<tr id="holidayPolicy_#policyId">
			  			<td>
			  				<input type="hidden" name="policy" value="#data"><span>#name</span>
			  			</td>
			  			<td style="width: 30px;" nowrap="nowrap"><a onclick="javascript:del(this);" class="link del" title="删除">&nbsp;</a> </td>
			  	</tr>
			</textarea>
		</c:if>
	</div>
	<div position="bottom"  class="bottom" style="margin-top:10px;">
		<a href="javascript:;" class="button"  onclick="selectData()" style="margin-right:10px;" ><span class="icon ok"></span><span >选择</span></a>
		<a href="javascript:;" class="button"  onclick="clearData()"><span class="icon cancel" ></span><span class="chosen" >清空</span></a>
		<a href="javascript:;" class="button" style="margin-left:10px;"  onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a>
	</div>
</body>
</html>


