<#--显示字段-->
<#assign fields=sysQueryView.fieldSettings>
<#--字段Map-->
<#assign metaMap=sysQueryView.fieldMap>
<#--是否显示行号-->
<#assign showRowsNum="false">
<#--是否初始化查询-->
<#assign initQuery="false">
<#--是否初始化查询-->
<#assign pageSize=sysQueryView.pageSize>
<#--是否分组-->
<#assign supportGroup="false">

<#if (sysQueryView.supportGroup==1)>
	<#assign supportGroup="true">
</#if>


<#if (sysQueryView.showRowsNum==1)>
	<#assign showRowsNum="true">
</#if>

<#if (sysQueryView.initQuery==1)>
	<#assign initQuery="true">
</#if>


<#--生成colModel对象-->
<#function getField field>
	<#assign meta=field.metaField>
	<#assign sort="false" >
	<#assign frozen="false" >
	<#assign align=field.align >
	<#assign alarmSetting=meta.alarmSetting >
	<#assign formater=meta.formater>
	<#assign url=meta.url >
	
	<#if (field.sortAble==1)>
		<#assign sort="true" >
	</#if>
	<#if (field.frozen==1)>
		<#assign frozen="true" >
	</#if>

	<#if ( align?exists && align=="" )>
		<#assign align="center" >
	</#if>

	<#assign rtn>{label:"${meta.fieldDesc}",name:"${meta.name}",index:'${meta.fieldName}',
		sortable:${sort},frozen:${frozen},align:"${align}"
		<#if (field.summaryType?exists && field.summaryType?trim!="") >
			,summaryType:"${field.summaryType}"
		</#if>
		<#if (field.summaryTemplate?exists && field.summaryTemplate?trim!="") >
			,summaryTemplate:"${field.summaryTemplate}"
		</#if>
		
		<#if (formater?exists && formater?trim!="" )>
			,formatter:${meta.name}_Formater
		<#elseif (alarmSetting?exists && alarmSetting?trim!="" )>
			,formatter:${meta.name}_AlarmFormater
	 	<#elseif ( url?exists && url?trim!="" )>
	 		,formatter:${meta.name}_UrlFormater
		</#if>
		}</#assign>
 	<#return rtn>
</#function>



<#--获取条件-->
<#function getCondition condition>
	<#assign rtn="">
	<#list condition as con>
		<#assign operate=con.op >
		<#if (operate=="=") >
			<#assign operate="==" >
		</#if>
		<#if con_index==0>
			<#assign rtn="cellvalue" + operate + con.val >
		<#else>
			<#assign rtn=rtn + " && cellvalue" + operate + con.val >
		</#if>
	</#list>
	<#return rtn>
</#function>
<#--生成格式化函数-->
<#macro genFormaterFunction>
	<#list fields as field>
		<#assign meta=field.metaField>
		<#assign alarmSetting=meta.alarmSetting >
		<#assign formater=meta.formater>
		<#assign url=meta.url >
		<#if (formater?exists && formater?trim!="") >
			function ${meta.name}_Formater(cellvalue, options, rowObject){
				 ${formater};
			}
		<#elseif (alarmSetting?exists && alarmSetting?trim!="") >
			<#assign alarm=alarmSetting?eval >
			function ${meta.name}_AlarmFormater(cellvalue, options, rowObject){
				if(options.rowId=="" || options.rowId.indexOf("gridList")>-1){
					return cellvalue;
				}
				<#list alarm as item>
					if(${getCondition(item.condition)}){
						return "<span style='color:${item.color};font-weight:bold;'>" + cellvalue +"</span>";
					}
				</#list>
				return cellvalue;
			}
		<#elseif (url?exists && url?trim!="") >
			function ${meta.name}_UrlFormater(cellvalue, options, rowObject){
				var url="${ctx}${url}";
				url=url.replace("{${meta.name}}",cellvalue);
				return "<a href='"+url+"' target='_blank'>${fieldDesc}</a>";
			}
		
		</#if>
	</#list>
</#macro>

<#--生成查询控件-->
<#macro getController field>
		<#assign fieldName=field.fieldName>
		<#assign dataType=field.dataType>
		<#assign content=field.controlContent>
		
		<#switch field.controlType>
			<#case "1">
				<#if (dataType=="varchar")>
					<input type="text" name="Q_${fieldName}" class="inputText"   />
				<#elseif (dataType=="number")>
					<input type="text" name="Q_${fieldName}" class="inputText"  validate="{number:true}"  />
				</#if>
			<#break>
			<#case "3">
				<input lablename="${fieldName}" class="dicComboTree" nodekey="${content}" validate="{empty:false}" name="${fieldName}" height="150" width="125" />
			<#break>
			
			<#case "4"><#--用户单选选择器 -->
				<input type="hidden" name="Q_${fieldName}" class="inputText">
				<input type="text" readonly="readonly" class="inputText" name="${fieldName}" >
				<input type="button" onclick="__Selector__.selectUser({self:this});" value="...">
			<#break>
			<#case "5"><#--角色多选选择器 -->
				<input  type="hidden" name="Q_${fieldName}" >
				<input type="text" style="width: 250px" readonly="readonly" class="inputText">
				<input type="button" onclick="__Selector__.selectRole({self:this,isSingle:false});" value="...">
			<#break>
			<#case "6"><#--组织多选选择器 -->
				<input  type="hidden" name="Q_${fieldName}" >
				<input type="text" style="width: 250px" readonly="readonly" class="inputText" name="${fieldName}" >
				<input type="button" onclick="__Selector__.selectOrg({self:this,isSingle:false});" value="...">
			<#break>
			<#case "7"><#--岗位多选选择器 -->
				<input  type="hidden" name="Q_${fieldName}" >
				<input type="text" style="width: 250px" readonly="readonly" class="inputText" name="${fieldName}">
				<input type="button" onclick="__Selector__.selectPos({self:this,isSingle:false});" value="...">
			<#break>
			<#case "8"><#--人员多选选择器 -->
				<input  type="hidden" name="Q_${fieldName}"  >
				<input type="text" style="width: 250px" readonly="readonly" class="inputText" name="${fieldName}"  >
				<input type="button" onclick="__Selector__.selectUser({self:this,isSingle:false});" value="...">
			<#break>
			<#case "17"><#--角色单选选择器 -->
				<input  type="hidden" name="Q_${fieldName}" >
				<input type="text" readonly="readonly" class="inputText" name="Q_${fieldName}" >
				<input type="button" onclick="__Selector__.selectRole({self:this});" value="...">
			<#break>
			<#case "18"><#--组织单选选择器 -->
				<input  type="hidden" name="Q_${fieldName}">
				<input type="text" readonly="readonly" class="inputText" name="${fieldName}">
				<input type="button" onclick="__Selector__.selectOrg({self:this});" value="...">
			<#break>
			<#case "19"><#--岗位单选选择器 -->
				<input  type="hidden" name="Q_${fieldName}" >
				<input type="text" readonly="readonly" class="inputText" name="${fieldName}" >
				<input type="button" onclick="__Selector__.selectPos({self:this});" value="...">
			<#break>
			
			<#case "11"><#--下拉列表框 -->
				<#assign options=content?eval>
				<select name="Q_${fieldName}" >
					<option value="">全部</option>
					<#list options as opt>
						<option value="${opt.key}">${opt.val}</option>
					</#list>
				</select>
			<#break>
			
			<#case "12"><#-- 自定对话框 -->
				<#assign dg=content?eval>
				<input type="text" id="Q_${fieldName}" name="Q_${fieldName}" class="inputText"   />
				<input type="button" onclick="showCustomDialog('${dg.dialog}','${dg.resultField}','Q_${fieldName}')" value="..." dialog="">
			<#break>
			
		</#switch>
</#macro>

<#--日期条件查询框-->
<#macro genDate con>
	<#assign fieldName=con.name>
	<#assign operate=con.operate>
	<#if (operate==7)>
		从:
		<input type="text" name="Q_begin${fieldName}" readonly="readonly" class="wdateTime inputText"  />
		</li><li>到:
		<input type="text" name="Q_end${fieldName}" readonly="readonly" class="wdateTime inputText"  />
	<#else>
		<input type="text" name="Q_${fieldName}" readonly="readonly"  class="wdateTime inputText"  />
	</#if>
</#macro>

<#--数字条件查询框-->
<#macro genNumber con>
	<#assign fieldName=con.name>
	<#assign operate=con.operate>
	<#assign field=metaMap[fieldName]>
	<#assign controlType=field.controlType>
	
	<#if (operate==7)>
		从:
		<input type="text" name="Q_begin${fieldName}" class="inputText"  validate="{number:true}"  />
		</li><li>到:
		<input type="text" name="Q_end${fieldName}"  class="inputText"  validate="{number:true}" />
	<#else>
		<@getController field />
	</#if>
	
</#macro>

<#macro genCondition con>
	<#assign fieldName=con.name>
	<#assign operate=con.operate>
	<#assign field=metaMap[fieldName]>
	<#assign controlType=field.controlType>
	<#assign dataType=field.dataType>
	<li>
	<span class="label">${field.fieldDesc}:</span>	
	<#switch dataType>
		<#case "varchar"><#--字符串-->
		<#case "text">
			<@getController field />
			<#break>
		<#--数字-->
		<#case "number">
			<@genNumber con/>
			<#break>
		<#--日期-->	
		<#case "date">
			<@genDate con/>
			<#break>
	</#switch>
	</li>
</#macro>

<script type="text/javascript">
<#--调用生成格式化函数体-->
<@genFormaterFunction/>

$(function () {
	
	$("#gridList").jqGrid({
		url:'${ctx}/platform/system/sysQueryView/data_${sysQueryView.sqlAlias}/${sysQueryView.alias}.ht',
		datatype: "json",
		mtype:"POST",//提交方式
		height: '100%',
		rowNum: ${pageSize},
		rowList: [5,10,20,30],
	   	autowidth:true,
	    jsonReader:{
        	root: "rows",// json中代表实际模型数据的入口
        	total: "total", // json中代表总页数的数据
        	page: "page", // json中代表当前页码的数据
        	records: "records",// json中代表数据行总数的数据
            repeatitems : false// 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。
        },
	   	colModel:[
	   		<#assign idx=0>
	  		<#list fields as field>
	  			<#--隐藏列不显示-->
	  			<#if (field.hidden==0)>
		  			<#if (idx >0) >,</#if>
		  			${getField(field)}	
		  			<#assign idx=idx +1>
	  			</#if>
	  		</#list>
	  		
	  		<#if (buttons?exists && buttons?size>0) >
	   			,{label:"管理", align:"center",formatter:managerFormatter}
	   		</#if>
	   	],
	   	//行号
	   	rownumbers:${showRowsNum},
	   	<#if (sortField?exists && sortField?trim!="") >
	   	sortname:"${sortField}",
	   	sortorder:"${sortSeq}",
	   	</#if>
	   	rownumWidth:20,
	   	viewrecords:true,
	   	recordtext:"总记录数:{2}",
	   	pager: "#pagerNav",
	   	//初始是否查询
	   	search:${initQuery},
	   	//上分页条
	   	//toppager :"#pagerNav",
	   	prmNames:{page:"page",rows:"pageSize",sort:"sortField",order:"orderSeq",search:"initSearch"},
	   	//列排序
	   	//direction : "rtl",
	   	/*
	 	grouping:true,
	   	groupingView : {
	   		groupField : ['category','amount'],
	   		//是否显示到列表
	   		groupColumnShow :[ true,true],
	   		groupText : ['<b> 国家: {0} {1} </b>','<b> 人品: {0}  </b>'],
	   		groupSummary : [false,true],
	   		groupOrder:['asc','desc']
	   	},*/
	   	<#if (supportGroup?exists && supportGroup=="true" )>
	   	grouping:${supportGroup},
	   	groupingView : ${sysQueryView.groupSetting},
	   	</#if>
	   	caption: "${sysQueryView.name}"
	});
	$("#gridList").jqGrid('setFrozenColumns');
	
	doResize(); 
    
	$("#btnSearch").click(function(){
		search();
	});
    
});

<#--管理列-->
<#if (rowButtons?exists && rowButtons?size>0) >
function managerFormatter(cellvalue, options, rowObject){
	<#--输出JSON数组-->
	<#assign idx=0>
	var aryJson=[<#list rowButtons as btn>
				<#if (btn.inRow) >
					<#if (idx>0)>
						{url:"${btn.urlPath}",name:"${btn.name}"}
					<#else>
						,{url:"${btn.urlPath}",name:"${btn.name}"}
					</#if>
					<#assign idx=idx+1>
				</#if>
			</#list>];
	var sb="";
	for(var i=0;i<aryJson.length;i++){
		var obj=aryJson[i];
		var url=obj.url;
		var name=obj.name;
		
		url=replaceUrl(url,rowObject);
		
		sb+= "<a href='"+url+"' target='_blank'>"+name+"</a>";
	}
	return sb;
}
</#if>


</script>

<div class="hide-panel" id="panelTop">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">${sysQueryView.name}</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
				<div class="l-bar-separator"></div>
				<#--导航栏工具条-->
				<#if (navButtons?exists && navButtons?size>0) >
				<#list navButtons as btn>
				<div class="group"><a class="link" href="${ctx}${btn.urlPath}"><span></span>${btn.name}</a></div>
				<div class="l-bar-separator"></div>
				</#list>
				</#if>
                <div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
			</div>	
		</div>
		<#--条件工具条-->	
		<#if (conditions?size > 0)>
			<div class="panel-search">
				<form id="searchForm" method="post" >
						<ul class="row">
							<#list conditions as con >
									<li><@genCondition con/></li>
							</#list>											
						</ul>
				</form>
			</div>
		</#if>
	</div>
</div>
<div class="panel-body">
	<table id="gridList" style="width:600px"></table>
	<div id="pagerNav"></div>
</div>				



