<#assign class=table.variable.class>
<#assign package=table.variable.package>
<#assign comment=table.tableDesc>
<#assign classVar=table.variable.classVar>
<#assign fieldList=table.fieldList>
<#function getJavaType dataType>
<#assign dbtype=dataType?lower_case>
<#assign rtn>
<#if  dbtype=="number" >
Long
<#elseif (dbtype=="varchar"||dbtype=="clob")  >
String
<#elseif (dbtype=="date")>
java.util.Date
</#if></#assign>
 <#return rtn?trim>
</#function>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>${comment }管理</title>
<%@include file="/js/lg/getLg.jsp"%>
<script type="text/javascript">
$(function(){
	//动态添加 columns
	var columns=[
		<#list table.fieldList as field>
		{
			display : '${field.fieldDesc}',
			name : '${field.fieldName}',
			<#if (field.controlType==15)||(field.fieldType=='date')>
			type : 'date',
			format : getFormat('${field.ctlProperty}'),
			isSort : true,
			editor: {
	            type: 'date'
	        } 
			<#elseif (field.controlType==4)&&(field.isHidden==0)>
			type : 'text',
			isSort : true,
			editor: {
	            type: 'selectUser',
	            multi: true
	        } 
			<#elseif (field.controlType==8)&&(field.isHidden==0)>
			type : 'text',
			isSort : true,
			editor: {
	            type: 'selectUser',
	            multi: false
	        }
			<#elseif (field.controlType==2||field.fieldType="clob")>
			 type : 'text',
	         isSort : false,
			 editor: {
	            type: 'textarea',
	            height:100
	         } 
			<#elseif (field.controlType==17)&&(field.isHidden==0)>
			type : 'text',
			isSort : true,
			editor: {
	            type: 'selectRole',
	            multi: false
	        }
			<#elseif (field.controlType==5)&&(field.isHidden==0)>
			type : 'text',
			isSort : true,
			editor: {
	            type: 'selectRole',
	            multi: true
	        }
			<#elseif (field.controlType==18)&&(field.isHidden==0)>
			type : 'text',
			isSort : true,
			editor: {
	            type: 'selectOrg',
	            multi: true
	        }
			<#elseif (field.controlType==6)&&(field.isHidden==0)>
			type : 'text',
			isSort : true,
			editor: {
	            type: 'selectOrg',
	            multi: false
	        }
	        
	        
	        
			<#elseif (field.controlType==19)&&(field.isHidden==0)>
			type : 'text',
			isSort : true,
			editor: {
	            type: 'selectPos',
	            multi: true
	        }
			<#elseif (field.controlType==7)&&(field.isHidden==0)>
			type : 'text',
			isSort : true,
			editor: {
	            type: 'selectPos',
	            multi: false
	        }
	        <#elseif (field.controlType==11)>
			type : 'text',
			isSort : true,
			editor: {
	            type: 'select',
	            data:getSelectData('${field.options}','${field.fieldName}'),
	            valueField : '${field.fieldName}'
	        },
            render: function (item){
            	var datas=getSelectData('${field.options}','${field.fieldName}');
            	for(var data in datas){
            		if(item.${field.fieldName}==datas[data]['${field.fieldName}']){
            			return datas[data]['text'];
            		}
            	}
            }
	        <#elseif field.fieldType="number">
	        type : 'int',
	        isSort : true,
			editor: {
	            type: 'int'
	        }
	        
	        <#elseif field.controlType=13>
	        type : 'text',
	        isSort : true,
            render: function (item){
            	return getComboboxValue('${field.options}',item.${field.fieldName});
            }
	        <#elseif field.controlType=14>
	        type : 'text',
	        isSort : true,
            render: function (item){
            	return getComboboxValue('${field.options}',item.${field.fieldName});
            }
	        <#elseif (field.isHidden=1)||(field.fieldType=="16")>
	        type : 'text',
	        isSort : false,
	        hide : true
	        
	        <#elseif (field.controlType==1)&&(field.options?length gt 0)>
	        type : 'text',
	        isSort : true,
	        editor: {
	            type: 'commonDialog',
	            options : ${field.options}
	        }
	        <#else>
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
	         </#if>
		}<#if field_has_next>,</#if>
		</#list>
	]
	initData({"columns":columns,"innerEdit":false,"needToolbar":true});

})
</script>
<#if flowKey?exists>
<script type="text/javascript">
	function startFlow(id){
		$.post("run.ht?isList=1&<#if table.isExternal==0>id<#else>${table.pkField}</#if>="+id,function(responseText){
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success("启动流程成功！", "成功", function(rtn) {
					if(rtn){
						this.close();
					}
					window.location.href = "<#noparse>${ctx}</#noparse>/${system}/${package}/${classVar}/list.ht";
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		});
	}
</script>
</#if>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">${comment }管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="groupUI"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="groupUI"><a class="link add" action="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="groupUI"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<#if !flowKey?exists>
					<div class="l-bar-separator"></div>
					<div class="groupUI"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					</#if>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="getList.ht">
					<div class="row">
						<#list fieldList as field>
						<#if field.isQuery==1>
						<#if (field.fieldType=="date")>
						<span class="label">${field.fieldDesc} 从:</span> <input  name="Q_begin${field.fieldName}_DL"  class="inputText date" />
						<span class="label">至: </span><input  name="Q_end${field.fieldName}_DG" class="inputText date" />
						<#elseif field.fieldType="number">
						<span class="label">${field.fieldDesc}:</span><input type="text" name="Q_${field.fieldName}_L"  class="inputText" />
						<#else>
						<span class="label">${field.fieldDesc}:</span><input type="text" name="Q_${field.fieldName}_S"  class="inputText" />
						</#if>
						</#if>
						</#list>
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<div id="grid" style="PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px"></div>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


