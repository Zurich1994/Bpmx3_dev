<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>PageTest管理</title>
<%@include file="/commons/include/getJqGrid.jsp" %>
<script type="text/javascript">
$(function () {
	
	$("#list47").jqGrid({
		url:'listJsonJQ.ht',
		datatype: "json",
		mtype:"POST",//提交方式
		height: '100%',
		rowNum: 10,
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
	  
	   		{label:"国家",name:'category',index:'category', sortable:true ,frozen:true},
	   		{label:"姓名",name:'name',index:'name',  formatter: actionFormatter,sortable:false,width:600},
	   		{label:"人品",name:'amount',index:'amount',width:300,formatter: numberFormatter, align:"center",sortable:true,summaryType:"sum", summaryTpl : '小计:<span style="color:red;">{0}</span>'},
	   		{label:"管理", align:"center",formatter:managerFormatter}
	   	],
	   	//行号
	   	rownumbers:true,
	   	sortname:"category",
	   	sortorder:"desc",
	   	rownumWidth:20,
	   	viewrecords:true,
	   	recordtext:"总记录数:{2}",
	   	pager: "#pagerNav",
	   	//初始是否查询
	   	search:true,
	   	//上分页条
	   	//toppager :"#pagerNav",
	   	prmNames:{page:"page",rows:"pageSize",sort:"sortField",order:"orderSeq",search:"initSearch"},
	   	//列排序
	   	//direction : "rtl",
	   /*
	   	grouping:true,
	   	groupingView : {
	   		groupField : ['category'],
	   		//是否显示到列表
	   		groupColumnShow :[ true],
	   		groupText : ['<b> 国家: {0} 数量: {1}</b>'],
	   		groupSummary : [false],
	   	},
	   */
	   	grouping:true,
	   	groupingView : {
	   		groupField : ['category','amount'],
	   		//是否显示到列表
	   		groupColumnShow :[ true,true ],
	   		groupText : ['c{0} c d{1}d','<b> 人品: {0}  </b>'],
	   		groupSummary : [true,true]
	   	},
	  
	  
	   	caption: "分组测试1"
	});
	$("#list47").jqGrid('setFrozenColumns');
	
	

	

	doResize(); 
    
	$("#btnOK").click(function(){
		search();
	});
    
});

function managerFormatter(cellvalue, options, rowObject){
	return "<a href='#' onclick='alert("+rowObject.id+")'>管理</a>";
}

function actionFormatter(cellvalue, options, rowObject) {
    var retVal = cellvalue;
    return retVal;
}

function numberFormatter(cellvalue, options, rowObject){
	console.info(options);
	if(options.rowId=="" || options.rowId.indexOf("list47")>-1){
		return cellvalue;
	}
	if(cellvalue>10){
		return "<span style='color:red;font-weight:bold;'>" + cellvalue +"</span>";
	}
	else if(cellvalue>5 && cellvalue<=10){
		return "<span style='color:yellow;font-weight:bold;'>" + cellvalue +"</span>";
	}
	else {
		return "<span style='color:green;font-weight:bold;'>" + cellvalue +"</span>";
	}
}

function search(){
	
	var scope=$("#searchbar");
	var params={};
	var search = $("#list47").jqGrid("getGridParam", "search");
	console.info("search:"+search);
	var postData = $("#list47").jqGrid("getGridParam", "postData");
	$("input[id^='Q_']",scope).each(function(){
		var obj=$(this);
		var val=obj.val();
		var key=obj.attr("id");
		delete postData[key];
		if(val && val!=""){
			params[key]=obj.val();
		}
		
	});

	
    $("#list47").jqGrid('setGridParam',{postData:params,search:true}).trigger("reloadGrid"); //重新载入 
    
   // 
}


	var t=document.documentElement.clientWidth;  
	window.onresize = function(){  
		if(t != document.documentElement.clientWidth){ 
			t = document.documentElement.clientWidth; 
			doResize(); 
		} 
	} 

	function doResize() { 
		var ss = getPageSize(); 
		$("#list47").jqGrid('setGridWidth', ss.width-30).jqGrid('setGridHeight', ss.height-100); 
	} 

	function getPageSize() { 
		//http://www.blabla.cn/js_kb/javascript_pagesize_windowsize_scrollbar.html 
		var winW, winH; 
		if(window.innerHeight) {// all except IE 
			winW = window.innerWidth; 
			winH = window.innerHeight; 
		} else if (document.documentElement && document.documentElement.clientHeight) {// IE 6 Strict Mode 
			winW = document.documentElement.clientWidth; 
			winH = document.documentElement.clientHeight; 
		} else if (document.body) { // other 
			winW = document.body.clientWidth; 
			winH = document.body.clientHeight; 
		}  // for small pages with total size less then the viewport  
		return {width:winW, height:winH}; 
	} 


</script>
</head>
<body>
	
	  <div id="searchbar">
    分类：<input id="Q_category_SL" type="text" />
    名字：<input id="Q_name_SL" type="text" />
     
    <input id="btnOK" type="button" value="button" />
</div>
	  
	  <form>
	 
	    <table id="list47" style="width:600px"></table>
		 <div id="pagerNav"></div>
	  </form>
	
</body>
</html>


