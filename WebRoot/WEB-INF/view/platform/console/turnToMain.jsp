<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<script language="javascript">   
   function init(){
	   openScreenWin();
	   closewin();
   }
   
   function openScreenWin(){   
	    var url = '${pageContext.request.contextPath}/platform/console/main.ht';
        var newwin = window.open(url,"","left=0,top=0,width="+screen.availWidth+",height="+screen.availHeight+",toolbar=no,location=no,status=yes,menubar=no,scrollbars=yes,resizable=yes");  //第三个参数 加上fullscreen=yes 时页面会全屏打开，在IE10上页面上的标题栏会隐藏掉，所以不建议用全屏； 第二个参数（名称不能填写，否则当你在页面重新登录并关闭跳转页面时会按名称去关闭窗口的)
        if(newwin == null){
            alert("您的浏览器禁止了弹出窗口!请更改浏览器拦截弹出窗口的设置,才能正常访问该系统...");
        }
        /*  if (document.all){
	    	newwin.moveTo(0,0);
	    	newwin.resizeTo(screen.availWidth,screen.availHeight);
	    }    */
   }
   
	function closewin(){
		var type="";
	    var ua=navigator.userAgent.toLowerCase();
	    if(ua.indexOf("msie")>=0){
	        type="MSIE";
	        if(navigator.Actual_Version=="6.0"){
	        	type+="6";
	        }
	    }else if(ua.indexOf("firefox")>=0){
	    	type="Firefox";
	    }else if(ua.indexOf("chrome")>=0){
	    	type="Chrome";
	    }else if(ua.indexOf("opera")>=0){
	    	type="Opera";
	    }else if (ua.indexOf("netscape")>=0){ 
	    	type="Netscape";
	    }else{
	    	type="other";
	    } 

	    if(type=="MSIE6"){
	    	window.opener=null;
	    	window.close(); 
	    }else if(type=="MSIE"){     
	    	window.opener=null;
	    	window.open('','_self','');       
	    	window.close(); 
	    }else if(type=="Firefox"){
	    	window.location.href = 'about:blank';     //清空跳转页面  不是用OPEN打开的窗口在火狐默认是没有 window.close(); 所以把首页置空白
	    	window.open('','_parent','');
	        window.close();  
		}else if(type=="Chrome"){
			window.open('','_self','');
	    	window.close(); 
		}else if(type=="Opera"){
			window.location.href = 'about:blank';      //清空跳转页面  不是用OPEN打开的窗口在Opera默认是没有 window.close(); 所以把首页置空白
			window.open('','_parent','');
	    	window.close(); 
		}else if(type=="Netscape"){
			window.location.href = 'about:blank';
			window.open('','_self','');
	    	window.close(); 
		}else{
	    	window.location.href = 'about:blank'; 
	    	window.opener=null;
	    	window.open('','_parent','');
	    	window.close(); 
	    	window.open('','_self','');
	    	window.close(); 
	    }
	}
	     
</script>
</head>
<body onload="javascript:init();">  
         跳转借助页面，请关闭......
</body>
</html>
