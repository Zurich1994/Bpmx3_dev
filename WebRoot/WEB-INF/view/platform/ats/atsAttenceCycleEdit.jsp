<%--
	time:2015-05-17 15:46:54
	desc:edit the 考勤周期
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 考勤周期</title>
	<%@include file="/commons/include/form.jsp" %>
	<f:link href="listEdit.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsAttenceCycleForm").attr("action","save.ht");
				submitForm();
			});
			
			 $("#startDay").blur(function(){  
					var val = $(this).val();
					$("#endDaySpan").html(val-1);
					$("#endDay").val(val-1);
             });
			 initDetailList();
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsAttenceCycleForm').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				if($("[var='templTr']",$("#trContainer")).length==0){
					$.ligerDialog.warn("考勤周期信息至少要有一条！","提示信息");
					return;
				}
				$('#detailList').val(getDetailList());
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = window.location.href;
					}else{
						window.location.href = "${ctx}/platform/ats/atsAttenceCycle/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		
		function getDetailList(){
			var json =  [];
			$("#trContainer tr[var='templTr']").each(function(){
				var me = $(this),obj={};
				obj.name =$("[var='name']",me).html();
				obj.startTime =$("[var='startTime']",me).html();
				obj.endTime =$("[var='endTime']",me).html();
				json.push(obj);
			});
			return JSON2.stringify(json)
		}
		
		function initDetailList() {
			var detailList = $('#detailList').val();
			if ($.isEmpty(detailList))
				return;
			var tr = $($("#templ .table-detail tr")[0]).clone(true, true);
			var detailLists = $.parseJSON(detailList);
			for (var i = 0, c; c = detailLists[i++];) {
				$("[var='name']", tr).html(c.name);
				$("[var='startTime']", tr).html(c.startTime);
				$("[var='endTime']", tr).html(c.endTime);
				var tr1 = tr.clone(true, true);
				$("#trContainer").append(tr1);
			}
		}
		
		
		function changeType(obj){
			var val = $(obj).val();
			if(val ==1)
				$('#interval').hide();
			if(val ==2)
				$('#interval').show();
		}
		
		function changeStartMonth(obj){
			var val = $(obj).val();
			var endMonthSpan = '本',endMonth = 1;
			if(val ==1){
				endMonthSpan = '本';
			}else if(val ==2){
				endMonthSpan = '下'	;
				endMonth = 2
			}
			$("#endMonthSpan").html(endMonthSpan);
			$("#endMonth").val(endMonth);
		}
		function genCycle(){
			//自然月
			var type = $('#type').val(),
				year = parseInt($('#year').val(),10),
				month = parseInt($('#month').val(),10),
				tr = $($("#templ .table-detail tr")[0]).clone(true, true),
				startMonth = $('#startMonth').val(),
				startDay1 = $('#startDay').val();
				endDay1 = $('#endDay').val();		
				if(!year){
					$.ligerDialog.warn("请选择开始周期年！","提示信息");
					return;
				}
				if(!month){
					$.ligerDialog.warn("请选择开始周期月！","提示信息");
					return;
				}
				if(!type){
					$.ligerDialog.warn("请选择周期类型","提示信息");
					return;
				}else if(type == "2"){
					if(!startDay1){
						$.ligerDialog.warn("请填写周期开始日期！","提示信息");
						return;
					}
					if(startDay1 < 2 || startDay1 > 28){
						return;
					}
				}
			var hasCircles = $("[var='templTr']",$("#trContainer"));
			if(hasCircles.length!=0){
				year = parseInt($("[var='name']",hasCircles.last()).text());
				month = parseInt($("[var='startTime']",hasCircles.last()).text().split("-")[1])+1;
				startMonth = 0;
			}
			for(var i=month;i<month+12;i++){
				var m = i>12?i-12:i,
					y= i>12?(year+1):year,
					startDay = 1,endDay = 31;
				if(type ==1){
					startDay = '01';
					endDay =  new Date(y,m,0).getDate();
				}else{
					if(startMonth == 1){//上月
						m= m-1;
						if(m == 0){
							m= 12;
							y = y-1;
						}
							
					}
					startDay = startDay1;
					endDay = endDay1;
					if(startDay<10)
						startDay = "0"+startDay; 
					if(endDay<10)
						endDay = "0"+endDay; 
				}
				
				if(m<10)
					m = "0"+m;	
				$("[var='name']", tr).html(y+"-"+m);
				$("[var='startTime']", tr).html(y+"-"+m+"-"+startDay);
				if(startDay == "01"){
					$("[var='endTime']", tr).html(y+"-"+m+"-"+endDay)
				}else if(m<12)
					$("[var='endTime']", tr).html(y+"-"+(parseInt(m)+1)+"-"+endDay)
				else if(m==12){
					$("[var='endTime']", tr).html((y+1)+"-"+(1)+"-"+endDay)
				}
				var tr1 = tr.clone(true, true);
	
				$("#trContainer").append(tr1);
			}
		}
		
		function delRow(){
			$("#trContainer").empty();
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${atsAttenceCycle.id !=null}">
			        <span class="tbar-label"><span></span>编辑考勤周期</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加考勤周期</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javaScript:void(0)"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="atsAttenceCycleForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">编码: </th>
					<td><input type="text" id="code" name="code" value="${atsAttenceCycle.code}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
					<th width="20%">名称: </th>
					<td><input type="text" id="name" name="name" value="${atsAttenceCycle.name}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
				
				</tr>
				<tr>
					<th width="20%">周期类型: </th>
					<td>
						<select id="type" name="type" onchange="changeType(this)"> 
							<option>--请选择--</option>
							<option value="1"  <c:if test="${atsAttenceCycle.type ==1}">  selected="selected"</c:if> >自然月</option>
							<option value="2"  <c:if test="${atsAttenceCycle.type ==2}">  selected="selected"</c:if>>月（固定日期）</option>
						</select>
					</td>
					<th width="20%">是否默认: </th>
					<td><input type="checkbox" id="isDefault" name="isDefault" value="1"   <c:if test="${atsAttenceCycle.isDefault ==1}"> checked="checked"</c:if>  /></td>
				</tr>
				<tr>
					<th width="20%">开始周期: </th>
					<td><input type="text" id="year" name="year" value="${atsAttenceCycle.year}"  class="inputText wdateTime" datefmt="yyyy" style="width: 50px" validate="{required:true,number:true,maxIntLen:10}"  />年
						<input type="text" id="month" name="month" value="${atsAttenceCycle.month}"  class="inputText wdateTime" datefmt="M"  style="width: 50px" validate="{required:true,number:true,maxIntLen:10}"  />月
					</td>
					<th width="20%">周期区间: </th>
					<td>
						<div id="interval" <c:if test="${atsAttenceCycle.type !=2}">style="display: none;"</c:if>>
						<select id="startMonth" name="startMonth" onchange="changeStartMonth(this)"> 
							<option value="1"  <c:if test="${atsAttenceCycle.startMonth ==1}">  selected="selected"</c:if> >上月</option>
							<option value="2"  <c:if test="${atsAttenceCycle.startMonth ==2}">  selected="selected"</c:if>>本月</option>
						</select>
						<input type="text" id="startDay" name="startDay" value="${atsAttenceCycle.startDay}"   class="inputText wdateTime" datefmt="d"  style="width: 50px" validate="{required:true,number:true,maxIntLen:2}"  />日
						至 <span id="endMonthSpan">
						<c:choose>
							<c:when test="${atsAttenceCycle.endMonth==2}">下</c:when>
							<c:otherwise>本</c:otherwise>
						</c:choose>
						</span>月
						<span id="endDaySpan">${atsAttenceCycle.endDay}
						</span>日
						<input type="hidden" id="endMonth" name="endMonth" value="${atsAttenceCycle.endMonth}"    />
						<input type="hidden" id="endDay" name="endDay" value="${atsAttenceCycle.endDay}"  />
						</div>
					</td>
				</tr>
				<tr>
					<th width="20%">描述: </th>
					<td colspan="3">
						<textarea rows="3" cols="5" id="memo" name="memo"  class="inputText">${atsAttenceCycle.memo}</textarea>
					</td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${atsAttenceCycle.id}" />
				<textarea style="display: none" id="detailList" name="detailList">${fn:escapeXml(atsAttenceCycle.detailList)}</textarea>
		</form>
		<div class="panel-detail">
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a onclick="genCycle()" class="link add"><span></span>生成周期</a></div>
						<div class="group"><a onclick="delRow()" class="link del"><span></span>删除所有</a></div>
					</div>	
				</div>
			</div>
			<div class="panel-body">
					<table cellpadding="1" cellspacing="1"  class="table-grid" >
						<tr>
						  <th align="center" width="50px">选择</th>
						  <th align="center">周期</th>
						  <th align="center">开始时间</th>
						  <th align="center">结束时间</th>
						</tr>
						<tbody id="trContainer">
					    </tbody>
					</table>
			</div>
			
			<div  id="templ"  style="display: none;">
			<table cellpadding="1" cellspacing="1"  class="table-detail">
				<tbody>
				<tr var="templTr" onclick="checkTr(this)">
					<td ><input class="pk" type="checkbox" name="select" />
					<td var="name">
					</td>
					<td  var="startTime">
					</td>
					<td var="endTime">
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		</div>
	</div>
</div>
</body>
</html>
