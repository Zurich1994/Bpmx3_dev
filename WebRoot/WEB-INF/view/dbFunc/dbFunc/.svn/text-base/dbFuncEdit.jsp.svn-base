<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 数据库方法参数表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=dbFunc"></script>
	<script type="text/javascript">
	
		var id=${dbFunc.id==0};
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			
			var frm=$('#dbFuncForm').form();
			$("a.save").click(function() {
				
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#dbFuncForm').submit();
				}
				 
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "${ctx}/dbFunc/dbFunc/dbFunc/getByTableName.ht?tableName=${dbFunc.table_name}";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		//***************************************************************************************
		function searchObjectList(){
			
		//	alert("coming!!!!!!!!!!!!!!!!!!!");
			var selList=$("#objname");
			var dsName=$("#dataSource").val();
			
			
			var objectname=$("#objectname").val(); 
			var istable=$("#istable").val(); 
			var url=__ctx +"/dbFunc/dbFunc/dbFunc/getByDsObjectName.ht";
			if(dsName==null || dsName==""){
				$.ligerDialog.error("请选择数据源!");
				return ;
			}
		
		 	$.ligerDialog.waitting('正在查询，请等待...');
			
			$.post(url, { dsName:dsName, objectName: objectname,istable:istable },function(data) {
				$.ligerDialog.closeWaitting();
				selList.empty();
				var success=data.success;
				if(success=='false'){
					$.ligerDialog.error("出错了!");
					return;
				}
				//表的处理
				if(istable=="1"){
					var tables=data.tables;
					for(key in tables ){
						selList.append("<option title='"+tables[key]+"' value='"+ key+"'>"+ key +"("+tables[key] +")" +"</option>" );
					}
				}
				//视图的处理
				else{
					var aryView=data.views;
					for(var i=0;i<aryView.length;i++){
						var v=aryView[i];
						selList.append("<option value='"+ v+"'>"+v+"</option>" );
					}
				}
		    });
		}
		
		function dialog(){
			$("#selInfo").text("");
			var id  =$("#id").val();//0;//
			var istable=$("#istable").val();
			var objname=$("#objname").val();
			var dataSource=$("#dataSource").val();
		    
			var func_type=$("#func_type").val();
			
			var style=0;
			
			if(id==0){
				if(objname==null){
					$("#selInfo").text("请先选择数据库表");
					return ;
				}
			}
			showSettingDialog(dataSource, objname ,istable,style,id,func_type);
		}
		
		function showSettingDialog(dsName, objectname ,istable,style,id,func_type){
			var settingobj=$("#settingobj").val(),
				fields={};
			
			if(settingobj==objectname){
				var displayField=$("#displayField").val(),
					conditionField=$("#conditionField").val(),
					sortField=$("#sortField").val(),
					resultField=$("#resultField").val(),
					settingField=$("#settingField").val();
					
				
				if(displayField)
					fields.displayField=displayField;
				if(conditionField)
					fields.conditionField=conditionField;
				if(resultField)
					fields.resultField=resultField;
				if(sortField)
					fields.sortField=sortField;
				if(settingField)
					fields.settingField=settingField;
			}
			
			var url=__ctx+"/dbFunc/dbFunc/dbFunc/setting.ht?dsName=" +dsName +"&objectName=" + objectname +"&istable=" + istable +"&style=" +style +"&id=" + id+ "&func_type="+func_type;
			DialogUtil.open({
                height:700,
                width: 850,
                title : '设置列',
                url: url, 
                isResize: true,
                //自定义参数
                fields: fields,
                sucCall:function(rtn){
                	$("#settingobj").val(objectname);
           		 	$("#displayField").val(rtn[0]);
           		 	$("#conditionField").val(rtn[1]);
           		 	$("#resultField").val(rtn[2]);
           		 	$("#sortField").val(rtn[3]);
           		 	$("#settingField").val(rtn[4]);
           		 	$("#styletemp").val(rtn[5]);
                }
            });
}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty dbFuncItem.id}">
			        <span class="tbar-label"><span></span>编辑数据库方法参数表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加数据库方法参数表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="getByTableName.ht?tableName=${dbFunc.table_name}"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="dbFuncForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="8" class="formHead">数据库方法参数表</td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">表名:</td>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="table_name" id="table_name" lablename="表名" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${dbFunc.table_name}" /></span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">方法类型:</td>
   <td style="width:45%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select id="func_type"  name="func_type" lablename="方法类型" controltype="select" validate="{empty:false}"><option value="1" <c:if test='${dbFunc.func_type=="1"}'>selected='selected'</c:if>>添加</option><option value="2" <c:if test='${dbFunc.func_type=="2"}'>selected='selected'</c:if>>删除</option><option value="3" <c:if test='${dbFunc.func_type=="3"}'>selected='selected'</c:if>>修改</option><option value="4" <c:if test='${dbFunc.func_type=="4"}'>selected='selected'</c:if>>查询</option></select></span></td>
  </tr>
  <tr> 
   <td style="width:1%;" class="formTitle" align="right" nowrap="nowarp">方法名称:</td>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="func_name" id="func_name" lablename="方法名称" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${dbFunc.func_name}" /></span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">方法别名:</td>
   <td style="width:45%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="func_alias" lablename="方法别名" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${dbFunc.func_alias}" /></span></td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">备注:</td>
   <td style="width:25%;" class="formInput"><textarea name="remarks" validate="{empty:false}">${dbFunc.remarks}</textarea></td>
  </tr>
  <tr> 
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">传参类型:</td>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="parameterType" lablename="传参类型" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${dbFunc.parameterType}" /></span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">返回值类型:</td>
   <td style="width:45%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="resultType" lablename="返回值类型" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${dbFunc.resultType}" /></span></td>
    </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">是否正在使用:</td>
   <td style="width:25%;" class="formInput"><input name="is_Using" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:5,maxDecimalLen:0}" type="text" value="${dbFunc.is_Using}" /></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">是否为表 :</td>
   <td style="width:45%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="isTable" lablename="是否为表 " controltype="select" validate="{empty:false}"><option value="0" <c:if test='${dbFunc.isTable=="0"}'>selected='selected'</c:if>>视图</option><option value="1" <c:if test='${dbFunc.isTable=="1"}'>selected='selected'</c:if>>数据库表</option></select></span></td>
  </tr>  
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">数据源名称:</td>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select id="dataSource"  name="dsName" lablename="数据源名称" controltype="select" validate="{empty:false}"><option value="LOCAL" <c:if test='${dbFunc.dsName=="LOCAL"}'>selected='selected'</c:if>>本地数据源</option><option value="qq" <c:if test='${dbFunc.dsName=="qq"}'>selected='selected'</c:if>>qq</option></select></span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">数据源别名:</td>
   <td style="width:45%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ds_Alias" lablename="数据源别名" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${dbFunc.ds_Alias}" /></span></td>
  </tr>
  <tr>
   
  <c:if test="${dbFunc.id==0}">
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">查询表(视图) :</td>
	<th>
	    <select name="istable" id="istable">
		  <option value="1">表</option>
		  <option value="0">视图</option>
	    </select>
	    <input type="text" name="objectname" id="objectname">
		<a href="javascript:;" id="btnSearch" class="link search" onclick = "searchObjectList()">查询</a>
									
	</th>
  </c:if>						
	
  <c:if test="${dbFunc.id!=0}"> 
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">操作表的名称:</td>
  <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.objname}</td>
  </c:if> 
 
	</tr>
  <tr>					
   	
    <td  style="width:15%" class="formTitle" align="right" nowrap="nowarp" >对话框字段设置: </td>
	<th colspan="3" valign="top" style="width:25%">
	  <a href="javascript:;" id="btnSetting"  class="link setting" onclick="dialog()">设置列</a>
	  <c:choose>
		<c:when test="${dbFunc.id==0}">
			<br>
			<select id="objname" name="objname" size="10" style="width:350px;">
			</select>
			<span id="selInfo" name="selInfo" style="color:red"></span>
		</c:when>
	    <c:otherwise >
		  <input type="hidden"  id="objname" name="objname" value="${dbFunc.objname}" />
	    </c:otherwise>
	  </c:choose>
	</th>
    
</tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden"  name="id" id="id" value="${dbFunc.id}"/>
		
		<input type="hidden" id="settingobj" value="${dbFunc.objname}" />
		<textarea id="displayField" name="displayField" >
				${dbFunc.displayField}
		</textarea>
		<textarea  id="conditionField"  name="conditionField" >
				${dbFunc.conditionField}
		</textarea>
		<textarea  id="resultField"  name="resultField"  >
				${dbFunc.resultField}
		</textarea>
		<textarea  id="sortField"  name="sortField"  >
				${dbFunc.sortField}
		</textarea>
		<textarea  id="settingField"  name="settingField" >
				${dbFunc.settingField}
		</textarea>
	</form>
</body>
</html>
