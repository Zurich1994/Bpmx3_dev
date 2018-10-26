<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8" import="com.hotent.platform.model.system.Resources"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="skinStyle" value="${skinStyle}" />
<head>
    <title>电子政务建模仿真</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<f:link href="Aqua/css/ligerui-all.css"></f:link>
	<f:link href="index.css"></f:link>
	<f:link href="select.css"></f:link>
	<f:link href="tree/zTreeStyle.css"></f:link>
	<f:js pre="js/lang/common" ></f:js>
	<f:js pre="js/lang/js" ></f:js>
    <f:link href="greenMain.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/dynamic.jsp"></script>
   	<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
   	<script type="text/javascript" src="${ctx}/js/util/util.js"></script>
	<script type="text/javascript" src="${ctx}/js/util/form.js"></script>
    <script type="text/javascript" src="${ctx}/js/lg/base.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDrag.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMenu.js"></script>
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerTab.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerAccordion.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"  ></script>
    <script type="text/javascript">
		    if(top!=this){//当这个窗口出现在iframe里，表示其目前已经timeout，需要把外面的框架窗口也重定向登录页面
				  top.location='<%=request.getContextPath()%>/platform/console/main.ht';
			}
            var menus=[];
            var tab = null;

            var ctxPath=__ctx;
            var accordion = null;
          

            $(function (){
                //布局
                $("#layoutMain").ligerLayout({
                	topHeight :80,
                	leftWidth: 180, 
                	height: '98%',
                	onHeightChanged: heightChanged });
				//取得layout的高度
                var height = $(".l-layout-center").height();
                //Tab
                $("#framecenter").ligerTab({ height: height});

                //面板
               // $("#accordion1").ligerAccordion({ height: height, speed: null });
                

				//获取tab的引用
                tab = $("#framecenter").ligerGetTabManager();
               // accordion = $("#accordion1").ligerGetAccordionManager();
				//加载菜单
				loadMenu();
                //隐藏加载对话框
                $("#pageloading").hide();
                
                $('div.l-menu').live('mouseOut',function(){
                	for(var i=0;i<menus.length;i++){
    					menus[i].hide();
    				}
                })
 /*
                $("#menuPanel").delegate("a.menuItem", "click", function(){
                    var id=$(this).attr("id");
                    loadTree(id);
                    $(this).siblings().removeClass("menuItem_hover").end().addClass("menuItem_hover");
                    jQuery.setCookie("selectTab",id);
                });
  */              
                //更多操作的初始化
                var button = $('#loginButton');
        	    var box = $('#loginBox');
        	    var shade=$('#shadeEm');
        	    button.mouseover(function(login) {
        	        box.toggle();
        	        shade.toggle();
        	        button.toggleClass('active');   
        	    	t= setTimeout(function(){button_mouseout();},2000);
        		});
        		$(this).mouseup(function(login) {
        			if ($(login.target).hasClass("more")){
        				var prehref=$(login.target).attr('prehref');
        				if(prehref)
       						addToTab(prehref,$(login.target).text(),$(login.target).attr('resid'));
        				button.removeClass('active');
        				box.hide();
        				shade.hide();
        			}
        		});
        		var button_mouseout=function(){
    				box.hide();
    				shade.hide();
    				button.removeClass('active');	
    			}
        		box.mouseenter(function(){
        			clearTimeout(t);
        		});
        		box.mouseleave(function(){
        			box.hide();
        			shade.hide();
    				button.removeClass('active');
        		});
            });

			//布局大小改变的时候通知tab，面板改变大小
            function heightChanged(options){
            	$("iframe").each(function(){
            		if($(this).attr("name")!=undefined){
            			$(this).height(options.middleHeight-35);
            		}
            	});
				$("#leftTree").height(options.middleHeight-40);
                if (tab){
                	var tabContent =$(".l-tab-content"),
                		h =tabContent.height();
                	tabContent.height(h+options.diff+15);
                }
                //    tab.addHeight(options.diff);
                if (accordion && options.middleHeight - 25 > 0)
                    accordion.setHeight(options.middleHeight - 25);
            }
		      
            var aryTreeData=null;
            //返回根节点
            function getRootNodes(){
            	var nodes=new Array();
            	for(var i=0;i<aryTreeData.length;i++){
            		var node=aryTreeData[i];
            		if(node.parentId==0){
            			nodes.push(node);
            		}
            	}
            	return nodes;
            };
            //初始化菜单滚动按钮
            function initRollButton(){	
        		var pWidth = $("div.menuParent").width(),sWidth = $("div.menuPanel").width();
        		if(sWidth<=0)return;		
        		var left = pWidth - sWidth;
        		if (left <= 0) {
        			$(".nav_left").show();
        			$(".nav_right").show();
        		}
        	};
            //加载菜单面板
            function loadMenu(){
                $("#leftTree").empty();
            	//一次性加载
				$.post("${ctx}/platform/console/getSysRolResTreeData.ht",
					 function(result){
						aryTreeData=result;
						for(var i=0;i<result.length;i++){
            				var node=result[i];
            			}
						//获取根节点，加载顶部按钮菜单。
						var headers=getRootNodes();
						var len=headers.length;
						var menuContainer=$("#menuPanel");
						for(var i=0;i<len;i++){
	            			var head=headers[i];
	            			var menuItemHtml=getMenuItem(head);
	            			menuContainer.append($(menuItemHtml));
	            			loadTree(headers[i].resId);
	            		}
						initRollButton();
					});
            }
            
            function loadTree(resId){
            	var nodes=new Array();
    			getChildByParentId(resId,nodes);
    			var top=$('#'+resId).offset().top+35;
    			var left=$('#'+resId).offset().left;
    			var ary = new Array();  
    			for(var i=0;i<nodes.length;i++){
    				var node=nodes[i];
    				var nodeJson={
    						text:node.resName,
    						defaultUrl:node.defaultUrl,
    						resName:node.resName,
    						icon:node.icon,
    						resId:node.resId,
    						click:function(item, itemcount){
    							addToTab(item.defaultUrl,item.resName,item.resId,item.icon)
    						}
    				};
    				var child=addSubItems(node.resId);
    				if(child)
    					nodeJson.children=child;
    				ary.push(nodeJson);
    			}
    			var menu = $.ligerMenu({ top: top, left: left, width: 170, items:ary});    			
    			var currentShowObj=$(menu.element);
    			menus.push(menu);
    			$('#'+resId).hover(function(){
    				for(var i=0;i<menus.length;i++){
    					menus[i].hide();
    				}
    				menu.show();
    			},function(event){
    				   var x=event.clientX; 
    				   var y=event.clientY;    				  
    				   var divx1 = currentShowObj.offset().left;
    				   var divy1 = currentShowObj.offset().top-3; 
    				   var divx2 = currentShowObj.offset().left + currentShowObj.width(); 
    				   var divy2 = currentShowObj.offset().top + currentShowObj.height(); 
    				   if( x < divx1 || x > divx2 || y < divy1 || y > divy2){ 
    					   menu.hide();
    				   }     				
    			});
    			currentShowObj.hover(function(){},function(){
    				if(!menu.menu.showedSubMenu)
    				  menu.hide();
    			});
    			
            }
            
            function addSubItems(resId,json){
            	var nodes=new Array();
    			getChildByParentId(resId,nodes);
    			if(nodes.length<1) return null;
    			var ary = new Array(); 
    			
    			for(var i=0;i<nodes.length;i++){
    				var node=nodes[i];
    				var nodeJson={
    						text:node.resName,
    						defaultUrl:node.defaultUrl,
    						resName:node.resName,
    						icon:node.icon,
    						resId:node.resId,
    						click:function(item, itemcount){
    							addToTab(item.defaultUrl,item.resName,item.resId,item.icon)
    						}
    				};
    				var child=addSubItems(node.resId);
    				if(child)
    					nodeJson.children=child;
    				ary.push(nodeJson);
    			}
    			return ary;
            }
            
            //加载菜单项
            function getMenuItem(node){
            	var str='<li><a class="menuItem" id="'+node.resId+'">';        
           		str+=node.resName+'<div class="down_menu"></div> </a></li>';
           		return str;
			}
            
            function getChildByParentId(parentId,nodes){
            	for(var i=0;i<aryTreeData.length;i++){
            		var node=aryTreeData[i];
            		if(node.parentId==parentId){
            			nodes.push(node);
            			//getChildByParentId(node.resId,nodes);
            		}
            	}
            };      
            
            //添加到tab或者刷新
            function addToTab(url,txt,id,icon){
            	if(url!=null && url!='' && url!='null'){
	            	if(!url.startWith("http",false))  url=ctxPath +url;
	            	
	            	if(tab.isTabItemExist(id)){
	            		tab.selectTabItem(id);
	            		tab.reload(id);
	            	}
	            	else{
	            		tab.addTabItem({ tabid:id,text:txt,url:url,icon:icon});
	            	}
            	}  	
            };

          	//切换系统
          	function saveCurrentSys(){
            	var systemId=$("#setSubSystem").val();
        		var form=new com.hotent.form.Form();
        		form.creatForm("form", "${ctx}/platform/console/saveCurrSys.ht");
        		form.addFormEl("systemId", systemId);
        		form.submit();
			}
    		
          	// firefox下切换tab的高度处置

     </script> 
<style type="text/css"> 
    body,html{height:100%;}
    body{ padding:0px; margin:0;   overflow:hidden;}  
    #pageloading{position:absolute; left:0px; top:0px; background:white url('${ctx}/styles/default/images/loading.gif') no-repeat center; width:100%; height:100%; height:700px; z-index:99999;}
    #top{color:White;height: 80px;}
	#top a{color:white;}
 </style>
</head>
<body style="padding:0px;">  
<div id="pageloading"></div>
	<%@include file="main_top_extend.jspf" %>
  <div id="layoutMain" style="width:100%; margin:0 auto;margin-top:2px">
        <div position="center" id="framecenter"> 
            <div tabid="home" title="我的主页" style="height:300px" >
            	<c:if test="${not empty currentSystem.homePage }">
            		<c:choose>
            			<c:when test="${currentSystem.isLocal==1}">
            				<iframe frameborder="0" name="home" src="${ctx}${currentSystem.homePage}"></iframe>
            			</c:when>
            			<c:otherwise>
            				<iframe frameborder="0" name="home" src="${currentSystem.defaultUrl}${currentSystem.homePage}"></iframe>
            			</c:otherwise>
            		</c:choose>
            	</c:if>
            </div> 
        </div> 
    </div> 
    <div class="bottom">
    </div>   
</body>
</html>
