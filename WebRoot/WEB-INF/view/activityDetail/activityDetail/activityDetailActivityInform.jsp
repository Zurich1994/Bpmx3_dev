<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>任务信息编辑</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript">
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		$(function() {});
		
		function save() {		
			var url = __ctx + "/activityDetail/activityDetail/activityDetail/save.ht";
			var para = $('#activityDetailForm').serialize();
			$.post(url, para, showResult);
			
		}
	
		function showResult(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (!obj.isSuccess()) {
				$.ligerDialog.err('出错信息',"任务信息编辑失败",obj.getMessage());
				return;
			} else {
				$.ligerDialog.success('任务信息编辑成功!','提示信息',function() {
					dialog.close();
				});
			}
			//dialog.close();
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty activityDetailItem.id}">
			        <span class="tbar-label"><span></span>编辑任务信息表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加任务信息表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
					<div class="group"><a class="link save" onclick="save()"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  onclick="dialog.close()"><span></span>关闭</a></div>
			</div>
		</div>
	</div>
	<form id="activityDetailForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="4">活动信息表</td>
  </tr>
    <tr style="height:23px">
   	  <td style="width:13%" class="formTitle" nowrap="nowrap">资料来源:</td>
  	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="data_source" isflag="tableflag" validate="{maxlength:400}" lablename="资料来源" value="${activityDetail.data_source}" /></span></td>
  	<td style="width:13%" class="formTitle" nowrap="nowrap">活动来源:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="server_source" validate="{empty:false}" lablename="活动来源" controltype="select"><option selected="selected" value="本地服务" <c:if test='${activityDetail.server_source=="本地服务"}'>selected='selected'</c:if>>本地服务</option><option value="利用外部服务" <c:if test='${activityDetail.server_source=="利用外部服务"}'>selected='selected'</c:if>>利用外部服务</option><option value="外部利用服务" <c:if test='${activityDetail.server_source=="外部利用服务"}'>selected='selected'</c:if>>外部利用服务</option><option value="利用公共服务" <c:if test='${activityDetail.server_source=="利用公共服务"}'>selected='selected'</c:if>>利用公共服务</option><option value="公共利用服务" <c:if test='${activityDetail.server_source=="公共利用服务"}'>selected='selected'</c:if>>公共利用服务</option></select></span></td>
   </tr>
   <tr style="height:23px"> 
    <td style="width:13%" class="formTitle" nowrap="nowrap">节点编号:</td>
   	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="activity_num" isflag="tableflag" validate="{maxlength:400}" lablename="节点编号" value="${activityDetail.activity_num}" /></span></td>
    <td style="width:13%" class="formTitle" nowrap="nowrap">响应速度:</td>
   	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="res_speed" isflag="tableflag" validate="{maxlength:400}" lablename="响应速度" value="${activityDetail.res_speed}" /></span></td>
    </tr>
   <tr style="height:23px"> 
    <td style="width:13%" class="formTitle" nowrap="nowrap">作业号:</td>
   	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="work_id" isflag="tableflag" validate="{maxlength:400}" lablename="作业号" value="${activityDetail.work_id}" /></span></td>
    <td style="width:13%" class="formTitle" nowrap="nowrap">数据增量:</td>
   	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="data_incre" isflag="tableflag" validate="{maxlength:400}" lablename="数据增量" value="${activityDetail.data_incre}" /></span></td>
    </tr>
   <tr style="height:23px">
   	<td style="width:13%" class="formTitle" nowrap="nowrap">通信传输量:</td>
   	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="com_trans" isflag="tableflag" validate="{maxlength:400}" lablename="通信传输量" value="${activityDetail.com_trans}" /></span></td>
    </tr>
    <tr style="height:23px">
   	<td style="width:13%" class="formTitle" nowrap="nowrap" color="red">(数字)新增修改类操作元素个数:</td>
   	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="elem_sum" isflag="tableflag" validate="{maxlength:400}" lablename="新增修改类操作元素个数" value="${activityDetail.elem_sum}" /></span></td>
    </tr>
    <tr style="height:23px">
   	<td style="width:13%" class="formTitle" nowrap="nowrap">(数字)作业子系统:</td>
  	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="work_subsys" isflag="tableflag" validate="{maxlength:400}" lablename="作业子系统" value="${activityDetail.work_subsys}" /></span></td>
  	 <td style="width:13%" class="formTitle" nowrap="nowrap">(数字)新增修改类操作非规范元素个数:</td>
   	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="non_s_elem_num" isflag="tableflag" validate="{maxlength:400}" lablename="新增修改类操作非规范元素个数" value="${activityDetail.non_s_elem_num}" /></span></td>
    </tr>
   <tr style="height:23px">
   	 <td style="width:13%" class="formTitle" nowrap="nowrap">作业实例名:</td>
  	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="job_ins_name" isflag="tableflag" validate="{maxlength:400}" lablename="作业实例名" value="${activityDetail.job_ins_name}" /></span></td>
  	 <td style="width:13%" class="formTitle" nowrap="nowrap">上午高峰期发生频度:</td>
   	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="peak_freq" isflag="tableflag" validate="{maxlength:400}" lablename="上午高峰期发生频度" value="${activityDetail.peak_freq}" /></span></td>
    </tr>
    <tr style="height:23px">
   	 <td style="width:13%" class="formTitle" nowrap="nowrap">操作实例名:</td>
  	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="ope_ins_name" isflag="tableflag" validate="{maxlength:400}" lablename="操作实例名" value="${activityDetail.ope_ins_name}" /></span></td>
  	 <td style="width:13%" class="formTitle" nowrap="nowrap">平均发生频度:</td>
   	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="ave_freq_occur" isflag="tableflag" validate="{maxlength:400}" lablename="平均发生频度" value="${activityDetail.ave_freq_occur}" /></span></td>
    </tr>
    <tr style="height:23px">
   	 <td style="width:13%" class="formTitle" nowrap="nowrap">操作对象名:</td>
  	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="ope_obj_name" isflag="tableflag" validate="{maxlength:400}" lablename="操作对象名" value="${activityDetail.ope_obj_name}" /></span></td>
  	 <td style="width:13%" class="formTitle" nowrap="nowrap">操作过程:</td>
   	 <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="ope_process" isflag="tableflag" validate="{maxlength:400}" lablename="操作过程" value="${activityDetail.ope_process}" /></span></td>
    </tr>
   <tr style="height:23px">
     <td style="width:13%" class="formTitle" nowrap="nowrap">(数字)信息量:</td>
     <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="op_info_sum" isflag="tableflag" validate="{maxlength:400}" lablename="信息量" value="${activityDetail.op_info_sum}" /></td>
     <td style="width:13%" class="formTitle" nowrap="nowrap">活动类别:</td>
  	 <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="server_type" validate="{empty:false}" lablename="活动类别" controltype="select"><option selected="selected" value="事务服务" <c:if test='${activityDetail.server_type=="事务服务"}'>selected='selected'</c:if>>事务服务</option><option value="计算服务" <c:if test='${activityDetail.server_type=="计算服务"}'>selected='selected'</c:if>>计算服务</option></select></span></td>
   </tr> 
   <tr style="height:23px">
	 <td style="width:13%" class="formTitle" nowrap="nowrap">信息类型:</td>
	 <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="info_type" validate="{empty:false}" lablename="信息类型" controltype="select"><option selected="selected" value="本地信息" <c:if test='${activityDetail.info_type=="本地信息"}'>selected='selected'</c:if>>本地信息</option><option value="利用外部信息" <c:if test='${activityDetail.info_type=="利用外部信息"}'>selected='selected'</c:if>>利用外部信息</option><option value="外部利用信息" <c:if test='${activityDetail.info_type=="外部利用信息"}'>selected='selected'</c:if>>外部利用信息</option><option value="利用公共信息" <c:if test='${activityDetail.info_type=="利用公共信息"}'>selected='selected'</c:if>>利用公共信息</option><option value="公共利用信息" <c:if test='${activityDetail.info_type=="公共利用信息"}'>selected='selected'</c:if>>公共利用信息</option></select></span></td>
	 <td style="width:13%" class="formTitle" nowrap="nowrap">活动类型:</td>
	 <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="server_class" validate="{empty:false}" lablename="活动类型" controltype="select"><option selected="selected" value="自动检查服务" <c:if test='${activityDetail.server_class=="自动检查服务"}'>selected='selected'</c:if>>自动检查服务</option><option value="自动变换服务" <c:if test='${activityDetail.server_class=="自动变换服务"}'>selected='selected'</c:if>>自动变换服务</option><option value="自动判断服务" <c:if test='${activityDetail.server_class=="自动判断服务"}'>selected='selected'</c:if>>自动判断服务</option><option value="自动计算服务" <c:if test='${activityDetail.server_class=="自动计算服务"}'>selected='selected'</c:if>>自动计算服务</option><option value="N/A" <c:if test='${activityDetail.server_class=="N/A"}'>selected='selected'</c:if>>N/A</option></select></span></td>
   </tr>
   <tr style="height:23px">
     <td style="width:13%" class="formTitle" nowrap="nowrap">信息形态:</td>
     <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="info_shape" validate="{empty:false}" lablename="信息形态" controltype="select"><option selected="selected" value="静态信息" <c:if test='${activityDetail.info_shape=="静态信息"}'>selected='selected'</c:if>>静态信息</option><option value="动态信息" <c:if test='${activityDetail.info_shape=="动态信息"}'>selected='selected'</c:if>>动态信息</option></select></span></td>
     <td style="width:13%" class="formTitle" nowrap="nowrap">活动形态:</td>
     <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="server_shape" validate="{empty:false}" lablename="活动形态" controltype="select"><option selected="selected" value="离线服务" <c:if test='${activityDetail.server_shape=="离线服务"}'>selected='selected'</c:if>>离线服务</option><option value="在线服务" <c:if test='${activityDetail.server_shape=="在线服务"}'>selected='selected'</c:if>>在线服务</option></select></span></td>
   </tr>
   <tr style="height:23px">
   <td style="width:13%" class="formTitle" nowrap="nowrap">信息标准化:</td>
    <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="info_stand" validate="{empty:false}" lablename="信息标准化" controltype="select"><option selected="selected" value="标准信息" <c:if test='${activityDetail.info_stand=="标准信息"}'>selected='selected'</c:if>>标准信息</option><option value="非标准信息" <c:if test='${activityDetail.info_stand=="非标准信息"}'>selected='selected'</c:if>>非标准信息</option></select></span></td>
    <td style="width:13%" class="formTitle" nowrap="nowrap">活动性质:</td>
    <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="server_nature" validate="{empty:false}" lablename="活动性质" controltype="select"><option selected="selected" value="新增服务" <c:if test='${activityDetail.server_nature=="新增服务"}'>selected='selected'</c:if>>新增服务</option><option value="利旧服务" <c:if test='${activityDetail.server_nature=="利旧服务"}'>selected='selected'</c:if>>利旧服务</option></select></span></td>
   </tr>
   <tr style="height:23px">
   <td style="width:13%" class="formTitle" nowrap="nowrap">信息内容:</td>
  	<td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="info_content" isflag="tableflag" validate="{maxlength:400}" lablename="信息内容" value="${activityDetail.info_content}" /></span></td>
    <td style="width:13%" class="formTitle" nowrap="nowrap">活动方式:</td>
    <td style="width:20%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="server_way" validate="{empty:false}" lablename="活动方式" controltype="select"><option selected="selected" value="机器作业" <c:if test='${activityDetail.server_way=="机器作业"}'>selected='selected'</c:if>>机器作业</option><option value="人工作业" <c:if test='${activityDetail.server_way=="人工作业"}'>selected='selected'</c:if>>人工作业</option><option value="N/A" <c:if test='${activityDetail.server_way=="N/A"}'>selected='selected'</c:if>>N/A</option></select></span></td>
  </tr>
  <tr style="height:23px"> 
    <td style="width:13%" class="formTitle" nowrap="nowrap">服务周期:</td>
   	<td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="ser_cycle" isflag="tableflag" validate="{maxlength:400}" lablename="服务周期" value="${activityDetail.ser_cycle}" /></span></td>
    <td style="width:13%" class="formTitle" nowrap="nowrap">知识类操作:</td>
   	<td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="knowledge_flag" isflag="tableflag" validate="{maxlength:400}" lablename="知识类操作" value="${activityDetail.knowledge_flag}" /></span></td>
    </tr>
  <tr style="height:23px">
   <td style="width:13%" class="formTitle" nowrap="nowrap">(数字)活动成熟度:</td>
   <td style="width:20%" class="formInput"><input name="op_comp" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':1,'decimalValue':0}" value="${activityDetail.op_comp}" /></td>
   <td style="width:13%" class="formTitle" nowrap="nowrap">代码行数:</td>
   <td style="width:20%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="code_line_num" isflag="tableflag" validate="{maxlength:400}" lablename="代码行数" value="${activityDetail.code_line_num}" /></span></td>
  </tr>


   
  <input class="inputText" type="hidden" name="actDef_Id" isflag="tableflag" validate="{maxlength:50}" lablename="流程ID" value="${activityDetail.actDef_Id}" />

  <input class="inputText" type="hidden" name="activity_id" isflag="tableflag" validate="{maxlength:40}" lablename="活动ID" value="${activityDetail.activity_id}" />
   
  <input class="inputText" type="hidden" name="activity_name" isflag="tableflag" validate="{maxlength:200}" lablename="活动名称" value="${activityDetail.activity_name}" />
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${activityDetail.id}"/>
	</form>
	
</body>
</html>
