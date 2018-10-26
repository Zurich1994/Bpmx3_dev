<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>PageTest管理</title>
<%@include file="/commons/include/getLiger.jsp" %>
<script type="text/javascript">
$(function () {
    $("#maingrid4").ligerGrid({
        columns: [
        { display: '分类', name: 'category', minWidth: 60 ,frozen:true },
        { display: '分类', columns:[
        { display: '姓名', name: 'name', width: 50, align: 'left' ,isSort:true}, 
        { display: '人品', name: 'amount' ,
        	totalSummary:
            {
                render: function (val, column, cell)
                {
                    return '<div>最大值:' + val.max + ',和:'+ val.sum+'</div>';
                },
                align: 'left'
            },
            render:function(rowdata, index, value){
            	 if (value>10)
                 {
                     return "<div style='background:red;'>" +value+"</div>";
                 }
            	 else if(value>5){
            		 return "<div style='background:green;'>" +value+"</div>";
            	 }
            	 return value;
            }
		}]},
		{ display: '管理', minWidth: 140 ,render:mangerColRender }, 
        ],  pageSize:10,pagesizeParmName:"pageSize",
        title :"表格测试",
        showTableToggleBtn :true,
        groupRender: function (city,groupdata)
                        {
                        return '国家 [<b>' + city + '</b>]:(<b>' + groupdata.length + '</b>)';
                        },
                        /*
                        rowAttrRender: function (rowdata,rowid)
                         {
                                 if (rowdata.amount>5)
                                 {
                                     return "style='background:red;'";
                                 }
                                 return "";
                        },*/


        url:"listJson.ht", 
        async :true,
       
        
        root:"rows",
        record:"total",
        sortnameParmName:"orderField",
        sortorderParmName:"orderSeq",
        height:'100%',groupColumnName:'category',groupColumnDisplay:'分类'
    });
    $("#pageloading").hide(); 
    
    

    
});

function mangerColRender(rowdata, index, value){
	return '<a onclick="alert('+rowdata.id+')">删除</a>';
}

function f_search(){
	var grid=$("#maingrid4").ligerGetGridManager();
	var scope=$("#searchbar");
	var params={};
	$("input[id^='Q_']",scope).each(function(){
		var obj=$(this);
		var val=obj.val();
		if(val && val!=""){
			params[obj.attr("id")]=obj.val();
		}
	});
	grid.options.parms =params;
	grid.loadData();
	
}

</script>
</head>
<body>
	<div class="l-loading" style="display:block" id="pageloading"></div> 
	  <div id="searchbar">
    分类：<input id="Q_category_SL" type="text" />
    名字：<input id="Q_name_SL" type="text" />
    <input id="btnOK" type="button" value="button" onclick="f_search()" />
</div>
	  
	  <form>
	    <div id="maingrid4" style="margin:0; padding:0"></div>
	  </form>
	
</body>
</html>


