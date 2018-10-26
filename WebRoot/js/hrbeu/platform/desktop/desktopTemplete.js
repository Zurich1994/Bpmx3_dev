Namespace.register("com.hrbeu.platform.desktop");
/**
 * 获取栏目模板
 */
com.hrbeu.platform.desktop.getColumnTemp= function(cols,width,showTable){
	    var ary=width.split(","); //字符分割
	    var str;
	    if(showTable==0)
			{str='<table width="100%"><tr>';}
		else
			{str='<table width="100%" class="wtMonth"><tr>';}
	    for(var i=1;i<=cols;i++){
	    	str+='<td width="'+ary[i-1]+'%" id='+i+' name="test" col='+i+' class="nonactivated" width="600" valign="top">';
			str+='<div id="columns"><ul id="column'+i+'" class="column" valign="top"><br></ul></div></td>';
	    }
	    str+='</tr></table>';
		return str;
};

com.hrbeu.platform.desktop.homeTemp= function(cols,width){	
  $.post(
	__ctx+"/platform/system/desktopMycolumn/getMycolumnData.ht",
	function(jsonData){
		var colshtml=com.hrbeu.platform.desktop.getColumnTemp(cols,width,0);		
		$("#colshtml").append(colshtml);
		$(jsonData).each(function (i) {  
            //同步处理布局中的数据链接  
            columnUrl=jsonData[i].columnUrl;
            $.post( 
                __ctx + "/platform/system/desktopColumn/getTemp.ht?id="+jsonData[i].columnId,
                function (jsonStr) { 
                    //创建布局窗格
                	var htt,htd;                	
                    if(columnUrl==null||columnUrl.trim()==''){
                        htt = '<li  class="widget color-blue" col="'+jsonData[i].col+'" sn="'+jsonData[i].sn+'"  id="'+jsonData[i].columnId+'"><div class="widget-head"><h3>'+jsonData[i].columnName+'</h3>'
                        +'<span id=more"'+jsonData[i].columnId+'" style="display:none"><a href="'+__ctx+columnUrl+'" class="more">更多</a>'+columnUrl+'</span>'
                        +'</div><div class="widget-content"><ul>';
                        htd = '</ul></div></li>'; 
                    }else{
                        htt = '<li  class="widget color-blue" col="'+jsonData[i].col+'" sn="'+jsonData[i].sn+'"  id="'+jsonData[i].columnId+'"><div class="widget-head"><h3>'+jsonData[i].columnName+'</h3>'
                        +'<span id=more"'+jsonData[i].columnId+'"><a href="'+__ctx+columnUrl+'" class="more">更多</a></span>'
                        +'</div><div class="widget-content"><ul>';
                        htd = '</ul></div></li>'; 
                    }
                    var items = "";  
                    //处理窗格连接数据  
                    var jsonList = jsonStr;
                    //创建链接数据项  
                        items += '<li>'+jsonList+'</li>';  
                    //开始创建布局  
                    $("#columns #" + "column"+jsonData[i].col).append(htt + items + htd);
                });            
        });
		$("#columns").append('<script type="text/javascript" src="'+__ctx+'/js/desktop/inettutshome.js"><\/script>');
	});
};
	
/**
 * 获取栏目数据的模板
 */
com.hrbeu.platform.desktop.myTemp= function(cols,columnId,width,showTable,desktop){
		    var columnUrl;
		    $('#news').attr('href',"news.ht?id="+columnId);
		    $('#change').attr('href',"change.ht?id="+columnId);
		    var colshtml;
	        $.ajax({  
	            type: "POST",  
	            url:__ctx + "/platform/system/desktopMycolumn/getMycolumnData.ht",
	            beforeSend: function (XMLHttpRequest) { $("#londing").show(); },  
	            success: function (jsonData) {  
	            	//加载布局模板
	                colshtml=com.hrbeu.platform.desktop.getColumnTemp(cols,width,showTable,desktop);
	                $("#colshtml").append(colshtml);
	                
	                $(jsonData).each(function (i) {  
	                    //同步处理布局中的数据链接  
	                    columnUrl=jsonData[i].columnUrl;
	                    $.ajax({  
	                        url: __ctx + "/platform/system/desktopColumn/getTemp.ht?id="+jsonData[i].columnId,  
	                        async: false,  
	                        success: function (jsonStr) { 
	                            //创建布局窗格  
	                            if(columnUrl==null||columnUrl.trim()==''){
		                            var htt = '<li  class="widget color-blue" col="'+jsonData[i].col+'" sn="'+jsonData[i].sn+'"  id="'+jsonData[i].columnId+'"><div class="widget-head"><h3>'+jsonData[i].columnName+'</h3>'
		                            +'<span id=more"'+jsonData[i].columnId+'" style="display:none"><a href="'+__ctx+columnUrl+'" class="more">更多</a>'+columnUrl+'</span>'
		                            +'</div><div class="widget-content"><ul>';
		                            var htd = '</ul></div></li>'; 
	                            }else{
		                            var htt = '<li  class="widget color-blue" col="'+jsonData[i].col+'" sn="'+jsonData[i].sn+'"  id="'+jsonData[i].columnId+'"><div class="widget-head"><h3>'+jsonData[i].columnName+'</h3>'
		                            +'<span id=more"'+jsonData[i].columnId+'"><a href="'+__ctx+columnUrl+'" class="more">更多</a></span>'
		                            +'</div><div class="widget-content"><ul>';
		                            var htd = '</ul></div></li>'; 
	                            }
	                            var items = "";  
	                            //处理窗格连接数据  
	                            var jsonList = jsonStr;
	                            //创建链接数据项  
	                                items += '<li>'+jsonList+'</li>';  
	                            //开始创建布局  
	                            $("#columns #" + "column"+jsonData[i].col).append(htt + items + htd);
	                        }  
	                    });  
	                });  
                    $("#columns").append('<script type="text/javascript" src="'+__ctx+'/js/desktop/jquery-ui-personalized-1.6rc2.min.js"><\/script>');
                    if(desktop=='home'){
	                $("#columns").append('<script type="text/javascript" src="'+__ctx+'/js/desktop/inettutshome.js"><\/script>');}
                    else if(desktop=='news'){
                    $("#columns").append('<script type="text/javascript" src="'+__ctx+'/js/desktop/inettutsnew.js"><\/script>');}
                    else if(desktop=='show'){
                    $("#columns").append('<script type="text/javascript" src="'+__ctx+'/js/desktop/inettutsshow.js"><\/script>');}
                    else if(desktop=='change'){
                    $("#columns").append('<script type="text/javascript" src="'+__ctx+'/js/desktop/inettutscg.js"><\/script>');
                    }
	            }
	        });
	};
	
/**
 * 保存布局
 */
com.hrbeu.platform.desktop.saveCol= function(layoutID,filename,layoutCols){
		var jsonSaveStr = "[";
		// 获取布局数据
		$("#columns .widget:not(#intro)").each(
				function() {
					var col="";
					var columnId = $(this).attr("id");
					var colS = $(this).parent("ul").attr("id"); 
					col=colS.substr(6, 6);
					var sn = $(this).attr("sn");
					jsonSaveStr += "{layoutId:'" + layoutID + "',columnId:'"+ columnId + "',col:'" + col + "',sn:'" + sn + "'},";
				});
		// 处理布局数据
		jsonSaveStr = jsonSaveStr.substring(0, jsonSaveStr.length - 1);
		jsonSaveStr += "]";
		// 异步保存布局
		$.ajax({
			type : "POST",
			url : __ctx + "/platform/system/"+filename+"/saveCol.ht",
			data : {
				layoutConfig : jsonSaveStr,
				id : layoutID,
				cols:layoutCols
			},
			success : function(result) {
				var msg = new com.hrbeu.form.ResultMessage(result);
				if (msg.isSuccess()) {
					ref = true;
					$.ligerDialog.success('保存布局成功！','提示信息');
				} else {
					$.ligerDialog.success('保存布局失败！','提示信息');
					ref = false;
				}
			}
		});
	};	
