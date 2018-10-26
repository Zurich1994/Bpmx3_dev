<%--
	time:2012-02-20 14:57:31
	desc:edit the 班次设置
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 班次设置</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=workTimeSetting"></script>
	<script type="text/javascript">
		$(function() {
			initCalendar();
			
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${workTimeSetting.id ==null}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				
				var sflag = true;
				var idx = 0;
				$('input[name="startTime"]').each(function(){
					idx++;
					var v = $(this).attr('value');
					if(v.length!=5 || v.indexOf(':')==-1 || !isDigit(v.substring(0,2)) || !isDigit(v.substring(3,5))){
						$.ligerDialog.error('开始时间输入格式有误','错误',null);
						sflag = false;
					}
				});
				
				if(sflag){
					$('input[name="endTime"]').each(function(){
						var v = $(this).attr('value');
						if(v.length!=5 || v.indexOf(':')==-1 || !isDigit(v.substring(0,2)) || !isDigit(v.substring(3,5))){
							$.ligerDialog.error('结束时间输入格式有误','错误',null);
							sflag = false;
						}
					});
				}
				
				if(idx==0){
					$.ligerDialog.error('没有添加班次时间','错误',null);
				}
				
				if(sflag&&idx>0){
					$('#workTimeSettingForm').submit(); 
				}
			});
		});
		
		function initCalendar(){
			$("#workTimeItem").delegate("input.Wdate", "focus", function(){
				WdatePicker({el:this,dateFmt:"HH:mm"});
			});
		}
		
		function add(){
			var tr='<tr>';
			tr+='<td style="text-align: center;">';
			tr+='<input class="inputText Wdate" type="text" name="startTime" style="width: 95%;" value="" >';
			tr+='</td>';
			tr+='<td style="text-align: center;">';
			tr+='<input class="inputText Wdate" type="text" name="endTime" style="width: 95%;" value="" >';
			tr+='</td>';
			tr+='<td style="text-align: center;">';
			tr+='<input class="inputText" type="text" name="desc" style="width: 95%;" value="" >';
			tr+='</td>';
			tr+='<td style="text-align: center;">';
			tr+='<a class="link del" onclick="singleDel(this);">';
			tr+='删除</a>';
			tr+='</td>';
			tr+='</tr>';
			
			$("#workTimeItem").append(tr);
		};
		
		function singleDel(obj){
			var tr=$(obj).parents('tr');
			$(tr).remove();
		};
		
		function isDigit(s) 
		{ 
			var patrn=/^[0-9]{1,20}$/; 
			if (!patrn.exec(s)) return false;
			return true;
		}
		
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${workTimeSetting.id !=null}">
			            <span class="tbar-label">编辑班次设置</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加班次设置</span>
			        </c:otherwise>
			    </c:choose>
				
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
			<form id="workTimeSettingForm" method="post" action="save.ht">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%">班次名: </th>
						<td><input type="text" id="name" name="name" value="${workTimeSetting.name}" 
							class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">描述: </th>
						<td><input type="text" id="memo" name="memo" value="${workTimeSetting.memo}" 
							class="inputText"/></td>
					</tr>
				</table>
				<input type="hidden" name="id" value="${workTimeSetting.id}" />
				
				<br>
				
					
				<div class="tbar-title">
					<span class="tbar-label">班次时间设置</span>
				</div>
				
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group">
							<a class="link add" id="workTimeAdd" href="javascript:add()"><span></span>添加</a></div>
							<div class="l-bar-separator"></div>
							<div class="group">
							<span style="color: red;">时间输入格式，如9时30分，输入09:30</span></div>
					</div>
				</div>
				
				<table id="workTimeItem" class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">
					<thead>
							<th width="20%">开始时间</th>
 							<th width="20%">结束时间</th>
 							<th width="50%">描述</th>
 							<th width="10%" style="text-align: center;">管理</th>
					</thead>
					<tbody>
						<c:forEach items="${workTimelist}" var="workTimeItem">
							<tr>
								<td style="text-align: center;">
									<input class="inputText Wdate" type="text" name="startTime" style="width: 95%;" value="${workTimeItem.startTime}">
								</td>
								<td style="text-align: center;">
									<input class="inputText Wdate" type="text" name="endTime" style="width: 95%;" value="${workTimeItem.endTime}" >
								</td>
								<td style="text-align: center;">
									<input class="inputText" type="text" name="desc" 
										style="width: 95%;" value="${workTimeItem.memo}" >
								</td>
								<td style="text-align: center;">
									<a class="link del" onclick="singleDel(this);">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</form>
			</div>
		</div>
</div>
</body>
</html>
