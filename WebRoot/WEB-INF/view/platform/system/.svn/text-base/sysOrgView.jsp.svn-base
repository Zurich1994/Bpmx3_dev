<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>组织架构右边列表</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerTab.js" ></script>
<script type="text/javascript">
	$(function (){
		var h=parent.divheight()-30;
        $("#tabMyInfo").ligerTab({         	
           	height:h
         });
        $("#A").height(h-30);
        //$("#B").height(h-30);
        $("#D").height(h-50);
        $("iframe[action]").each(function() {
        	$(this).attr('src', $(this).attr('action'));
        });
               
	});
		
    
</script>

<style type="text/css"> 
	html{height:100%}
    body {scroll:no;height:100%; padding:0px; margin:0;overflow:auto !important}
    </style>

</head>
<body>     
    <div id="tabMyInfo" style="overflow:hidden; border:1px solid #A3C0E8;position:relative;">                
        <div title="组织简介" tabid="orgmesg" icon="${ctx}/styles/default/images/icon/home.png">
	       <iframe id="A" action="get.ht?orgId=${orgId}&flag=" frameborder="0" style="width: 100%;position:relative;" scrolling="no" ></iframe>		              		 
        </div> 
       
         <div title="下级组织列表" tabid="orgmesgs" icon="${ctx}/styles/default/images/icon/home.png">
	       <iframe id="C" action="listById.ht?orgId=${orgId}&path=${path}" frameborder="0" style="width: 100%;position:relative;" scrolling="no" ></iframe>		              		 
        </div>            
       
        <div  title="组织人员" tabid="orgempl" icon="${ctx}/styles/default/images/icon/customer.png">
           <iframe id="D" action="userList.ht?orgId=${orgId}&path=${path}"  frameborder="0" style="width: 100%;" scrolling="auto"></iframe>	
        </div> 
    </div> 			
</body>
</html>