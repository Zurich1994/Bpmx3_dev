<%--
	time:2012-02-20 14:57:32
	desc:edit the 系统日历
--%>
<%@page import="com.hotent.core.util.StringUtil"%>
<%@page language="java" pageEncoding="UTF-8" import="java.util.*,com.hotent.platform.model.worktime.CalendarSetting;"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 系统日历</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysCalendar"></script>
	<f:link href="calendar.css" ></f:link>
	
	<script type="text/javascript">
		var calId=${sysCalendar.id};
		var year=${wtYear};
		var month=${wtMon};
		
		var hasModify=false;
		
	
		var restTemplate='<div name="divSetting" wtType="2"><span>休息</span></div>';
		var workdayTemplate='<div name="divSetting" worktimeid="#worktimeid"  wtType="1"><div>上班</div><div>班次：#wtName</div></div>';
		
		var previous = 'previous';
		var legal = 'legal';
		var weekend = 'weekend';
		var workday = 'workday';
		var active = 'active';
		var rest = 'rest';
		
		
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function(){
				var rtn=$("#sysCalendarForm").valid();
				if(rtn){
					saveCalendar(showResponse);
				}
			});
			$("#cal th").click(selAllCol);
			$("#cal td[column]").click(tdClickHandler);
		});
		
		//保存日历设置。
		function saveCalendar(response){
			var name=$("input[name='name']").val();
			var memo=$("input[name='memo']").val();
			var data={id:calId,name:name,memo:memo,year:year,month:month,days:[]};
			$("div[name='divDay']:has(div)").each(function(){
				var obj=$(this);
				var divSetting=$("div[name='divSetting']",obj);
				var day=obj.attr("day");
				var type=divSetting.attr("wtType");
				var dayObj={day:day,type:type};
				//上班
				if(type==1){
					var worktimeid=divSetting.attr("worktimeid");
					dayObj.worktimeid=worktimeid;
				}
				data.days.push(dayObj);
			});
			
			var json=JSON2.stringify (data);
			var params="calJson=" + json;
			
			$.post("save.ht",params,response);
		}
		
		//保存返回
		function showResponse(data){
			var obj=new com.hotent.form.ResultMessage(data);
			if(obj.isSuccess()){//成功
				$.ligerDialog.confirm('操作成功,继续操作吗?','提示信息',function(rtn){
					if(!rtn){
						var returnUrl=$("a.back").attr("href");
						location.href=returnUrl;
					}
				});
		    }else{//失败
		    	$.ligerDialog.err('出错信息',"保存系统日历失败",obj.getMessage());
		    }
		};
		//单击表格处理
		function tdClickHandler(){
			var tdObj=$(this);
			activeGrid(tdObj);
		}
		
		
		//表格激活
		function activeGrid(tdObj){
			var clsName=tdObj.attr("class");
			if(tdObj.data("class")==undefined){
				tdObj.data("class",clsName);
				tdObj.removeClass(clsName).addClass(active);
			}
			else{
				var className=tdObj.data("class");
				if(clsName==active){
					tdObj.removeClass(active).addClass(className);
				}
				else{
					tdObj.removeClass(className).addClass(active);
				}
			}
		}
		
		// 选中整列的项
		function selAllCol(){
			var objHeader=$(this);
			var col=objHeader.attr("column");
			$("td[column='"+col+"']").each(function(){
				var tdObj=$(this);
				activeGrid(tdObj);
			});
		}
		
		// 修改选中项班次
		function changeCboWt(obj){
			var value=$(obj).val();
			if(value==1){
				$('#spanWorkTime').show();
			}else{
				$('#spanWorkTime').hide();
			}
		}
		
		// 清除选中项
		function cleanSelItem(){
			$("#cal td[column]").each(function(){
				var tdObj=$(this)
				var itemClass =tdObj.attr('class');
				if(itemClass==active){
					var clsName=tdObj.data("class");
					tdObj.removeClass(active).addClass(clsName);
				}
			});
		}
		
		// 选中项班次修改
		function changeSelItemWt(){
			var activeAmount=$('#cal td[class="active"]').length;
			if(activeAmount==0){
				$.ligerDialog.warn("还没有任何选中项,请先选择日期!",'提示信息');
				return;
			}
			//班次类型
			var wtValue = $('#selWt').attr('value');
			//班次
			var wtSetValue = $('#selWtSet').attr('value');
			var wtSetText=$("#selWtSet").find("option:selected").text();
			//对日历进行了修改。
			hasModify=true;
			$('#cal div[name="divDay"]').each(function(){
				var objDiv=$(this);
				var objTd=objDiv.parent();
				var itemClass = objTd.attr('class');
				if(itemClass==active){
					var tmp="";
					//上班
					if(wtValue==1){
						tmp=workdayTemplate.replace("#worktimeid", wtSetValue).replace("#wtName", wtSetText);
						objTd.data("class",workday).attr("class",workday);
					}
					//休息
					else{
						tmp=restTemplate;
						objTd.data("class",rest).attr("class",rest);
					}
					objDiv.html(tmp);
				}
			});
		}
		
		// 提示是否保存该月数据
		function noticeSave(flag,year,month){
	   		var callback = function(rtn) {
	   			if(rtn){
	   				saveCalendar(function(data){
	   					var obj=new com.hotent.form.ResultMessage(data);
	   					if(obj.isSuccess()){
	   						jumpTo(flag,year,month);
	   					}
	   					else{
	   						$.ligerDialog.err('出错信息',"保存此月数据失败",obj.getMessage());
	   					}
		   			});
	   			}
	   			else{
	   				jumpTo(flag,year,month);
	   			}
 			};
 			if(hasModify){
 				$.ligerDialog.confirm('要保存此月数据吗？','提示信息',callback)	;
 			}
 			else{
 				jumpTo(flag,year,month);
 			}
		}
		//跳转
		function jumpTo(flag,year,month){
			var url = 'edit.ht?id=${sysCalendar.id}&wtYear='+year+'&wtMon='+month;
			if(flag=='pre'){
				location.href = url + '&flag=pre';
			}else if(flag=='next'){
				location.href = url + '&flag=next';
			}else{
				location.href = url;
			}
		}
		//跳转		
		function jump(){
			var year=$("#wtYear").val();
			var month=$("#wtMon").val();
			noticeSave("",year,month);
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
		        <c:when test="${sysCalendar.id !=0}">
		            <span class="tbar-label">编辑系统日历</span>
		        </c:when>
		        <c:otherwise>
		            <span class="tbar-label">添加系统日历</span>
		        </c:otherwise>
			</c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link prev" id="previousMonth"  href="javascript:noticeSave('pre',${wtYear},${wtMon})"><span></span>上月</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link next" id="nextMonth" href="javascript:noticeSave('next',${wtYear},${wtMon})"><span></span>下月</a></div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<form id="searchForm" method="post" action="edit.ht">
						<ul>
							<li style="float: left;"><span class="label" style="min-width:0px;">年份:</span></li>
							<li style="float: left;margin-top:2px;">
								<select id="wtYear" name="wtYear" >
									<c:forEach var="year" items="${yearlist}">
										<option value="${year}" <c:if test="${wtYear==year}">selected="selected"</c:if> >${year}年</option>
									</c:forEach>
								</select>
							</li>
							<li style="float: left;"><span class="label" style="min-width:0px;">月份:</span></li>
							<li style="float: left;margin-top:2px;">
								<select id="wtMon" name="wtMon" >
									<c:forEach var="i" begin="1" end="12" step="1">
								      <option value="${i}" <c:if test="${wtMon==i}">selected="selected"</c:if> >${i}月</option>
								    </c:forEach>
								</select>
							</li>
							<li style="float: left;">
								&nbsp;&nbsp;<input type="hidden" name="id" value="${sysCalendar.id}" />
							</li>
							<li style="float: left;">
								<a href="javascript:;" class="link search" onclick="jump()"><span></span>跳转</a>
							</li>
						</ul>
					</form>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	
	<div class="panel-body">
		<div class="panel-data" >
		<form id="sysCalendarForm" method="post" action="save.ht">
			<input type="hidden" name="id" value="${sysCalendar.id}" />
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th style="text-align: left;">
					<ul class="row">
						<li>名称: <input type="text" size="10" id="name" name="name" value="${sysCalendar.name}" class="inputText"/></li>
						<li>描述:<input type="text" id="memo" name="memo" value="${sysCalendar.memo}" class="inputText"/></li>
						</ul>
					</th>
					<th style="text-align: left;">
					<ul class="row">
						<li style="margin-left:5px;min-width:100px;">班次：
						<select id="selWt" name="selWt" onchange="changeCboWt(this)">
							<option value="1" selected="selected">上班</option>
							<option value="2">休息</option>
						</select></li>
						<li style="min-width:100px;"><span id="spanWorkTime">
							时间：
							<select id="selWtSet" name="selWtSet" >
								<c:forEach var="wtSettingItem" items="${wtSetting}">
									<option value="${wtSettingItem.id}">${wtSettingItem.name}</option>
								</c:forEach>
							</select>
						</span></li>
						
						<li><span style="color: red;font: 80%;margin-right:5px;line-height:24px;">点击日期选中进行修改</span></li>
						<li style="min-width:90px;"><a href='#' class='button'  onclick="changeSelItemWt()" ><span>修改选中项</span></a></li>
						<li><a href='#' class='button'  onclick="cleanSelItem()" ><span >清除选中项</span></a></li>
						</ul>
					</th>
				</tr>
			</table>
			
			<table id="cal" class="wtMonth" >
				<caption>
					<span class="wtDate">${wtYear}年${wtMon}月</span>
					<span class="monthFlag">
						<c:if test="${monthFlag==true}">
							此月份已排班
						</c:if>
					</span>
					<table  class="wtMonth" style="width: 250px;">
						<tr >
							
							<td class="workday common" >工作日</td>
							<td class="rest common">休息</td>
							<td class="active common" >激活</td>
							<td class="weekend common" >周末</td>
							<td class="legal common" >法定假日</td>
							<td class="noSetting common" >未设置</td>
						</tr>
					</table>
				</caption>
				<tr>
					<th column="1" ><span>星期日</span></th>
					<th column="2" ><span>星期一</span></th>
					<th column="3" ><span>星期二</span></th>
					<th column="4" ><span>星期三</span></th>
					<th column="5" ><span>星期四</span></th>
					<th column="6" ><span>星期五</span></th>
					<th column="7" ><span>星期六</span></th>
				</tr>
				<%
				//取得本月的天数。
				int mondDays=(Integer)request.getAttribute("mondDays");
				//取得当月第一天为星期几。
				int firstDay=(Integer)request.getAttribute("firstDay");
				int actGird=mondDays + firstDay-1;
				int rows=(actGird % 7==0) ? (actGird / 7) :(actGird / 7 +1);
				int totalGrid=rows *7;
				
				Map settingMap = (Map)request.getAttribute("settingMap");
				Map vacationMap = (Map)request.getAttribute("vacationMap");
				%>
				<tr>
				<%
					for(int i=1;i<=totalGrid;i++){
						if(i<firstDay){
							out.println("<td class='previous'></td>");
						}
						else if(i<=actGird){
							int	index=(i%7==0)?7:i%7;
							int curDay=i-firstDay+1;
							boolean hasSetting=false;
						
							CalendarSetting calSetting=null;
							int wtType=0;
							String wtName="";
							String vacationName="";
							String clsName="noSetting";
							
							if(index==1 || index==7){
								clsName="weekend";
							}
							if(vacationMap.containsKey(curDay)){
								clsName="legal";
								vacationName=(String)vacationMap.get(curDay);
							}
							if(settingMap.containsKey(curDay)){
								calSetting = (CalendarSetting)settingMap.get(curDay);
								
								wtType=calSetting.getType();
								wtName=calSetting.getWtName();
								clsName=(wtType==2)?"rest":"workday";
							}
						%>
							<td   column="<%=index%>"  class="<%=clsName %>" >
								<div>
									<%=curDay %>
								</div>
								<div name="divDay" day="<%=curDay%>" >
									<% 
										if(wtType==2){ 
									%>
										<div name="divSetting" wtType="2">
											<span>休息</span>
										</div>
									<% 
										}
										else if(wtType==1){ 
										%>
										<div name="divSetting" worktimeid="<%=calSetting.getWorkTimeId()%>"  wtType="1">
											<div>上班</div>
											<div>班次：<%if(wtName!=null){%><%=wtName %><%} %></div>
										</div>
									<%  } %>
									
								</div>
									<%
										if(!"".equals(vacationName)){
									%>
										<div><%=vacationName %></div>
									<%
										}
									%>
							</td>
						<%
						}
						else{
							out.println("<td class='previous'></td>");
						}
						if(i%7==0){
							out.println("</tr>");
						}
						if(i%7==0 && i<settingMap.size())
							out.println("<tr>");
					}
				%>
				</tr>
				
			</table>
			
		</form>
		</div>
		
	</div>
</div>
</body>
</html>


