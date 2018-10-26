<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/commons/include/form.jsp" %>
	<link rel="stylesheet" type="text/css" href="../input.css">
	<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
	<script type="text/javascript">
		var curElement = null,
			dateCalculate = '',
			name = '',
			tableName = '',
			isMain =true;
		$(function(){
			//JSON 没有兼容IE时 就用到json2.js
			if(typeof JSON == 'undefined'){
		        $('head').append($("<script type='text/javascript' src='${ctx}/js/util/json2.js'>"));
		    }
			var tableId = editor.tableId;
			
			curElement = editor.selection.getStart();
			dateCalculate = curElement.getAttribute("dateCalculate");	
		
			if(!dateCalculate){
				dateCalculate = '{}';
			}
		
			dateCalculate = eval("("+dateCalculate+")");
			
			if(tableId){ //通过表生成
				name = curElement.getAttribute("name");	
				var names = name.split(':');
				
				isMain = names[0]=='s'?false:true;
				tableName =names[1];
				
				
				var url = __ctx
						+ '/platform/form/bpmFormTable/getTableById.ht?tableId='
						+ tableId;
				$.post(url, function(data) {
					initFieldsDiv(data,dateCalculate);
				});
			}
			else{	//编辑器设计表单时获取字段并验证字段
				var element = curElement.parentNode;
				var div = $(element).parents('[type="subtable"]');
				if(div){
					isMain = false;
					tableName = div.attr("tablename");
				}
					
				var html = editor.getContent();		
				var params={};
				params.html=html;
				params.formDefId=editor.formDefId;
				$.post(__ctx + '/platform/form/bpmFormDef/validDesign.ht', params, function(data){
					if(data.valid){
						initFieldsDiv(data,dateCalculate);
					}
					else{
						alert(data.errorMsg);
					}
				});
			}
		
		});
		function initFieldsDiv(data,dateCalculate){
			var  mainTable = data.table,pre='';
			var dateList = [];
			if(isMain){
				tableName = mainTable.tableName;
				var fieldList =  mainTable.fieldList;
				getDateList(fieldList,dateList);
				pre ="m:"+tableName+":";
			}else{
				var subTableList = mainTable.subTableList,
					subTable =  {};
				for (var i = 0, c; c = subTableList[i++];) {
					if(c.tableName == tableName){
						subTable = c;
						break;
					}
				}
				tableName = subTable.tableName;
				getDateList(subTable.fieldList,dateList)
				pre ="s:"+tableName+":";
			}
			initFields(dateList,pre,dateCalculate);
			
		}
		
		function getDateList(fieldList,dateList){
			for (var i = 0, c; c = fieldList[i++];) {
				if(c.controlType == 15 || c.fieldType == 'date')
					dateList.push(c);
			}
		}
		
		function initFields(data,pre,dateCalculate){
			var startdate = $('#start_date'),
				enddate = $('#end_date');
			for (var i = 0, c; c = data[i++];) {
				var opt = $('<option value="'+pre+c.fieldName+'"  >'+c.fieldDesc+'</option>');
				var opt1 = $('<option value="'+pre+c.fieldName+'"  >'+c.fieldDesc+'</option>');
				startdate.append(opt);
				enddate.append(opt1);
			}
			var dateCalculateStart =  dateCalculate.dateCalculateStart,
				dateCalculateEnd =  dateCalculate.dateCalculateEnd,
				diffType = dateCalculate.diffType;
				
			// 对日期计算处理
			if(dateCalculateStart){
				startdate.val(dateCalculateStart);
			}
			
			if(dateCalculateEnd){
				enddate.val(dateCalculateEnd);
			}
			
			if(diffType){
				$("[name='diffType']").each(function(){
					if($(this).val() == diffType){
						$(this).attr("checked","checked");
					}
				});
			}
		}
		
		
		//确定
		dialog.onok = function() {
			if(!curElement||!dateCalculate)return;
			
			var startdate = $("#start_date").val(),
				enddate = $("#end_date").val(),
				diffType = $("[name='diffType']:checked").val();
		
			delete dateCalculate['dateCalculateStart'];
			delete dateCalculate['dateCalculateEnd'];
			delete dateCalculate['diffType'];
			delete dateCalculate['isMain'];
		
			if(startdate){
				dateCalculate['dateCalculateStart'] = startdate;
			}
			
			if(enddate){
				dateCalculate['dateCalculateEnd'] = enddate;
			}
			if(diffType){
				dateCalculate['diffType'] = diffType;
			}
			dateCalculate['isMain'] = isMain;
			
			var str = '';
			if(typeof dateCalculate != 'undefined' && dateCalculate!=""){
				var str = JSON2.stringify(dateCalculate).replace(/\"/g,"'");    //JS 把所有的双引号 替换成单引号
			}
			$(curElement).attr("dateCalculate",str);
		}; 
		
		
	</script>
</head>
<body >
	<div id="inputPanel">
		<fieldset class="base">
			<legend>日期计算</legend>
			<table>
				<tr>
					<th>开始时间:</th>
					<td>
						<select id="start_date">
							<option value="">--请选择--</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>结束时间:</th>
					<td>
						<select id="end_date">
							<option value="">--请选择--</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>返回结果:</th>
					<td>
						<label><input type="radio" name="diffType" value="month" />月份</label>
						<label><input type="radio" name="diffType" value="day" />天数</label>
						<label><input type="radio" name="diffType" value="hour"/>小时</label>
						<label><input type="radio" name="diffType" value="minute"/>分钟</label>
						<label><input type="radio" name="diffType" value="second"/>秒数</label>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
</body>
</html>