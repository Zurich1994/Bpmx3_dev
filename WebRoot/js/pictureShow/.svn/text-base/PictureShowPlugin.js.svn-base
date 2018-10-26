/**
 * 图片展示插件，用于自定义表单。
 * 
 * PictureShowPlugin.init();
 *  	加载控件。 
 *  注意图片控件一共由四部分组成大图 + 小图片集合（包括上、下张功能） + 打开图片原图的隐藏DIV + 上传功能（控件可以编辑和必填时才有）
 *  图片控件 每加载（上传或者点击小图片）一次就产生一个ZOOM容器（用于做扩大功能的），此时必需要在每次加载前把控件自己对应的前ZOOM容器删除，再加载产生一个新的（为了保持一致才产生新的）
 *  我们可以通过序号（图片控件和容器的控件要保持一致）对两者进行管理
 * 
 */
PictureShowPlugin={
		//图片信息对象数据
		fileObjs:null,		
		//图片信息对象数据
		pictureShowObjs:null,
		//可以展示控件下面多少个小图片
		showNum:null,
		//可以展示控件上面大图片的宽
		pictureWidth:null,
		//可以展示控件上面大图片的高
        pictureHeight:null,
        //是否初始化tab中的图片控件产生的ZOOM容器的隐藏问题。 在FormUtil用到 
        isTabItemZoom:true,
        //是否序号化容器
        isZoomNum:true,
        //最近修改的容器序号
        lastZoomNum:null,      
        //是什么浏览器  (小写)
        browserName:null,
        //最近点击过的图片控件对象名称
        lastPictureShowName:null,
		//初始化
		init:function(){
			this.pictureShowObjs = new Array();
			this.getBrowserName();  //初始化浏览器名称
			this.lastZoomNum=null,
			this.showNum = 5;           //可以展示控件下面多少个小图片 默认			
			this.pictureWidth = 320;         //可以展示控件 上面大图片的宽  默认			
			this.pictureHeight = (this.pictureWidth*3)/4;        //可以展示控件上面大图片的高  默认  4：3
			this.fileObjs=$("input[controltype='pictureShow']");
			$.ligerDialog.waitting('正在加载图片控件,请稍候...');
			for ( var i = 0; i < this.fileObjs.length; i++) {
				var fileObj = this.fileObjs.get(i);
				fileObj.setAttribute("myNum",i);                 //记录序号   一定要设置，后面的处理会用到
				var name=fileObj.getAttribute("name");				
				var jsonData = PictureShowPlugin.getData(name);    //获取隐藏域的JSON数据
				//整个容器DIV  容器样式修改 parseInt("123") = 123
				var divId="div_" + name.replaceAll(":","_");
				var div_rq = $("#" + divId);
				var right = fileObj.getAttribute("right");	  //处理权限的问题
				//没有权限，删除div容器。
				if(right=="no"){
					div_rq.remove();
				}else{
					//容器  宽度处理
					var  widthStr = div_rq.css("width");
					if(typeof(widthStr)==undefined||widthStr==null||widthStr==""){
						widthStr ="0px";
					}
					var dwidth = parseInt(widthStr.toLowerCase().replaceAll("px","")); 
					var lwidth = dwidth;                //大图片宽度
					if(dwidth < this.pictureWidth){                                 
						lwidth = this.pictureWidth;
					}
					var swidth = (lwidth - 60 - this.showNum * 12 ) / this.showNum ;   //  (lwidth-40 －this.showNum * 4 )/this.showNum结果是小图片的宽度，其过程 为减去两边按钮 20 + 10 + 20 +10 ，再减去 小图片之间的空格 为 10 * this.showNum 个  ，然后再除以this.showNum后得出每个小图片的宽度              	
					dwidth = lwidth + 20;  //大图片宽度 +20 误差 				
					div_rq.css("width", dwidth + "px");   //容器宽度
					lwidth = dwidth - 4 ;

					//容器 高度处理
					var  heightStr = div_rq.css("height");
					if(typeof(heightStr)==undefined||heightStr==null||heightStr==""){
						 heightStr ="0px";
					}
					var dheight = parseInt(heightStr.toLowerCase().replaceAll("px","")); 
					var lheight = dheight;         //大图片的高度
					if(dheight<this.pictureHeight){
						lheight = this.pictureHeight;   
					}
					var sheight = swidth * (lheight/lwidth);  //按照 大图片比例得出  小图片的比例 和高度
					dheight = lheight + 30 + sheight;  // 大图片高 + 中间两小区域的间隔 + 小图片的高度	＝ 容器高度

					if(right=="w"||right=="b"){
						//必填  已在其它处理
						if(right=="b"){
							var validRule = fileObj.getAttribute("validate");
							if ( validRule != null && 'undefined' != validRule.toLowerCase() && validRule.length>2 ){ 
								var json = eval('(' + validRule + ')');		
								if(json.required){
									//不做操作
								}else{
									var jsonStr = validRule.substring(0, validRule.lastIndexOf('}'));
									jsonStr +=",\'required\':true}";    //加上必填
									fileObj.setAttribute("validate",jsonStr);    
								}				
							}else{
								fileObj.setAttribute("validate","{\'required\':true}");     //必填
							}
						}	
						dheight = dheight + 20 + 20;               //因为可编辑情况下可以上传图片，所以加上上传栏的高度 和 两者的间隔
					}else{
						//处理超链接  把超链接禁止掉(没有编辑和上传图片功能时 只读)
						//$("#pictureShow_"+name.replaceAll(":","_")+"_Toolbar").remove();                 //只读时，删除(可能会有多个一样的，要删除所以不能用return false break)
						$("#"+divId).children("table").each(function(index){
							var me = $(this);
							var idStr = me.attr("id");
							if(idStr!=null&&idStr.indexOf("_Toolbar")>-1){
								me.remove();
								return false;
							}
						});
					}
					dheight = dheight + 20;  //最终容器高度(加个误差)
					div_rq.css("height",dheight + "px");				

					//图片DIV
					var pictureId="div_" + name.replaceAll(":","_") +"_container";
					//加载控件。
					var pictureObj= new PictureShowControl();
					pictureObj.initPicture(name,pictureId,{jsonData:jsonData,pictureWidth:lwidth,pictureHeight:lheight,swidth:swidth,sheight:sheight,showNum:this.showNum,myNum:i,sortType:"asc",browserName:this.browserName});
					this.pictureShowObjs.push(pictureObj);
					this.lastPictureShowName = name;
				}				
			}
			
			//js键盘事件监听键盘的删除按键：由最近激活的图片控件做相应的操作
			if(this.pictureShowObjs.length>0){
				this.deleteOnKeyUp();
			}
			
			$.ligerDialog.closeWaitting(); 
		},
		
		//如果有图片展示插件。则保存后将返回的附件id放到隐藏域。
		upLoadPictureFile:function(t){
			var inputObj = $(t);               //点击上传的按钮
			var fieldName = inputObj.attr("field");
			var fieldObj = $("input[name='"+fieldName+"']");           //JSON存放的隐藏对象，用于提交时保存到数据库中
			var myNum = fieldObj.attr("myNum");
			var myObj = this.pictureShowObjs[myNum];                 //实例化（ new PictureShowControl() ）时属于自己的对象（通过序号获取）
			var jsonStr = fieldObj.val();
			var jsonData = null;
			//是否序号化ZOOM容器  (注意：要放到上传再处理这个问题，否则失效)
			/*if(this.isZoomNum){   //第一次做上传修改时要ZOOM窗口序号化
				this.initZoomContainer();
				this.isZoomNum = false;
			}else{
				if(this.lastZoomNum!=null){        //不为空时，有最近修改过的情况
					this.setZoomContainer();
				}		
			}			*/
			

				//以下是用模态窗口的方式打开
				pictureShowUploadDialog({uploadType:"pictureShow",fileFormates:"*.jpg;*.jpeg;*.bmp;*.png;*.gif;*.tiff;*.pcx;*.fpx;*.svg;*.psd",callback:function (fileIds,fileNames,filePaths,extPaths){				
					//没有返回上传的图片相关信息时禁止执行
					
					if(typeof(fileIds)==undefined || fileIds==""){   //有上传图片时才执行下去 
						return false;
					}
					
					$.ligerDialog.waitting('正在渲染图片控件,请稍候...');
					
					//判断之前的JSON数据
					if(typeof(jsonStr)==undefined||jsonStr==null||jsonStr==""){   //没有数据时，制造一个空壳数据
						jsonData = { 
									  jsonArry:new Array(),
									  creator:"宏天软件",
									  createTime:new Date()	  
						 		   }; 
					}else{
						jsonData = eval('(' + jsonStr + ')');					
					}
					
					var aryFileId=fileIds.split(",");
					var aryName=fileNames.split(",");
					jsonData = PictureShowPlugin.setData(fieldName,aryFileId,aryName,jsonData); //图片展示成功后把JSON数据放入到保存的隐藏域 并返回设置的JSON值
				//	jsonData = PictureShowPlugin.getData(fieldName);    //获取隐藏域的JSON数据  设置时返回有值不用再拿			
					var pictureId="div_" + fieldName.replaceAll(":","_") +"_container";								
					myObj.initPicture(fieldName,pictureId,{jsonData:jsonData,pictureWidth:myObj.config.pictureWidth,pictureHeight:myObj.config.pictureHeight,swidth:myObj.swidth,sheight:myObj.sheight,showNum:myObj.config.showNum,myNum:myNum,sortType:"desc",browserName:PictureShowPlugin.browserName});
					PictureShowPlugin.lastZoomNum = myNum;         //因为ZOOM 和 图片对象的序号是一致的 最近修改的ZOOM容器				
					//下面是防止控件偶尔出现渲染过慢时导致其它的 图片控件出现小问题，所以在该图片控件上传后其它图片都要激活一次（补救）
					for ( var i = 0; i < PictureShowPlugin.pictureShowObjs.length; i++) {
						if(i==myNum){
							var obj = PictureShowPlugin.pictureShowObjs[i]; 
							$("a.gallery_active",$("#div_" + obj.newName + "_allImg")).each(function(){ 
								 $(this).trigger("click");  
							}); 
							break;
						}
					}
					
					$.ligerDialog.closeWaitting();
					
				}});				
			
			this.lastPictureShowName = fieldName;        //最新有激活过的控件标志	
			
		},
		
		
		/**
		 * PictureShow图片展示控件的小图片的删除功能事件－ 键盘监听DELETE 和 DEL
		 */
		deleteOnKeyUp:function(){
			document.onkeyup=function(event){ 									
				var e = event || window.event || arguments.callee.caller.arguments[0]; 
				if(e && (e.keyCode==46||e.keyCode==110) ){           // 按 Delete  keycode 46 = Delete  或者小键盘 Del = 110 
					//要做的事情 
					var lastPictureShowName = PictureShowPlugin.lastPictureShowName;
					if(typeof(lastPictureShowName)!=undefined&&lastPictureShowName!=null){
						$("a[field='"+lastPictureShowName+"']").each(function(index){
							var aObj = $(this);
							if(aObj.hasClass("del")){
								PictureShowPlugin.deletePictureFile(this);
								return false;
							}							
						});
					}	
				} 
			}	
		},
		

		/**
		 * PictureShow图片展示控件的图片的删除功能事件
		 */
		deletePictureFile:function(t){			
			var delObj = $(t);
			var fieldName = delObj.attr("field");
			var fieldObj = $("input[name='"+fieldName+"']");           //JSON存放的隐藏对象，用于提交时保存到数据库中
			var right = fieldObj.attr("right");         //权限			
			if(right=="w"||right=="b"){
				var myNum = fieldObj.attr("myNum");
				var myObj = this.pictureShowObjs[myNum];                 //实例化（ new PictureShowControl() ）时属于自己的对象（通过序号获取）
				var newName = myObj.newName;
				var myImg = 'my_'+ newName +'_images';             //大图片ID
				var imgObj = $("#"+ myImg);	
				var pictureId="div_" + fieldName.replaceAll(":","_") +"_container";
				//处理样式
				var arrys = $("a",$("#div_"+newName+"_allImg")); //本容器所有小图片集合
				var len = arrys.length;
				var delId = "";
				var delTitle = "";
				var delIndex = 0;
				if(len>1){
					arrys.each(function(index){
						var aObj = $(this);
						if(aObj.hasClass("gallery_active")){
							delIndex = index;
							if( index<len-1 ){
								arrys.each(function(num){
									if(num==index+1){
										PictureShowPlugin.onClikeImage(this,myNum);
									}
								});							
							}else{
								arrys.each(function(num){
									if(num==len-2){
										PictureShowPlugin.onClikeImage(this,myNum);;
									}
								});
							}
							delId = aObj.attr("id");
							delTitle = aObj.attr("title");
							if(typeof(delId)!=undefined&&delId!=null&&delId!=""){
								aObj.remove();
							}	
							return false;
						}
					});
					
					//删除JSON中的数据,并重新排序
					var jsonData = this.getData(fieldName);
					var jsonArry = jsonData.jsonArry;
					if(typeof(jsonArry)!=undefined&&jsonArry!=null&&jsonArry.length>0){
						var arry = new Array();
						for ( var i = 0; i < jsonArry.length; i++) {
							var obj = jsonArry[i];					
							if(delId!=obj.id){   //排除要删除的图片ID
								arry.push(obj)
							}
						}
						jsonData.jsonArry = arry;
						if(arry.length>0){
							arrys = $("a",$("#div_"+newName+"_allImg")); //重新获取 本容器所有小图片集合
							if(arry.length>myObj.config.showNum){
								//重新排序显示：后面的图片的补上显示
								if( myObj.globalNum<=delIndex && myObj.globalNum+myObj.config.showNum<len){    //后面有可以补上的图片            
									//myObj.globalNum保持不变
									if(myObj.globalNum<0){
										myObj.globalNum = 0;
									}
								}else{
									if(myObj.globalNum>0){   //删除的小图上原来是的，后面没有补上的，就向前借1
										myObj.globalNum = myObj.globalNum-1;     //向前借1
									} 
								}
								//在显示图片或者显示图片后面的隐藏图片 后面补上 globalNum保持不变								
								
								//排序
								arrys.each(function(index){
									var aobj = $(this);
									if( myObj.globalNum<=index && index< myObj.globalNum + myObj.config.showNum){
										aobj.show();
									}else{
										aobj.hide();
									}
								});
							    //处理上、下一张按钮样式
							    
								if(myObj.globalNum + myObj.config.showNum >= arry.length){
								    $("#a_"+newName+"_next").attr("class","pictureShow_n_next");
								    $("#a_"+newName+"_prev").attr("class","pictureShow_prev");
							    }else if(myObj.globalNum<=0){
							        $("#a_"+newName+"_next").attr("class","pictureShow_next");
									$("#a_"+newName+"_prev").attr("class","pictureShow_n_prev");
							    }else{
							    	$("#a_"+newName+"_next").attr("class","pictureShow_next");
									$("#a_"+newName+"_prev").attr("class","pictureShow_prev");
							    }
								
							}else{         //不需要重新排序
								myObj.globalNum=0;
								arrys.each(function(index){
									$(this).show();									
								});
								$("#a_"+newName+"_prev").attr("class","pictureShow_n_prev");
								$("#a_"+newName+"_next").attr("class","pictureShow_n_next");
							}
							//重新初始化大图片的点击事件
							myObj.initImageClick(imgObj,false);  //首先移除之前的点击事件
							myObj.initImageClick(imgObj,true);   //再建立新的点击事件
							//重新给值
							var valueStr = JSON2.stringify(jsonData).replace(/\"/g,"'");
							fieldObj.val(valueStr);
						}else{
							fieldObj.val("");
							myObj.initPicture(fieldName,pictureId,{jsonData:jsonData,pictureWidth:myObj.config.pictureWidth,pictureHeight:myObj.config.pictureHeight,swidth:myObj.swidth,sheight:myObj.sheight,showNum:myObj.config.showNum,myNum:myNum,sortType:"asc",browserName:PictureShowPlugin.browserName});
						}
					}
				}else{
					arrys.each(function(index){
						var aObj = $(this);
						delId = aObj.attr("id");
						delTitle = aObj.attr("title");
						if(typeof(delId)!=undefined&&delId!=null&&delId!=""){
							aObj.remove();
							return false;
						}	
					});
					fieldObj.val("");
					var data = this.getData(fieldName);
					myObj.initPicture(fieldName,pictureId,{jsonData:data,pictureWidth:myObj.config.pictureWidth,pictureHeight:myObj.config.pictureHeight,swidth:myObj.swidth,sheight:myObj.sheight,showNum:myObj.config.showNum,myNum:myNum,sortType:"asc",browserName:PictureShowPlugin.browserName});
				}	
				
				//删除数据库的数据
				if(typeof(delId)!=undefined&&delId!=null&&delId!=""){
					var path = __ctx +'/platform/system/sysFile/del.ht';
					$.post(path,{fileId:delId},function(){});
				}				
			}
			this.lastPictureShowName = fieldName;    //最新有激活过的控件标志
		},
		
		
		//如果有图片展示插件。上传图片后将返回的附件id和名称组成ITML写到页面上来展示图片。
		setHtml:function(me,myObj,newName){
			var url = me.attr("data-image"),
			    lurl = me.attr("data-zoom-image"),
			    id = me.attr("id");    
			    title = me.attr("title");
			var midImg = ImageQtip.getImage(url,{id:"my_"+newName+"_images",title:title,zoomUrl:lurl,maxWidth:myObj.config.pictureWidth,maxHeight:myObj.config.pictureHeight});
			$("a.gallery_active",$("#div_" + myObj.newName + "_allImg")).removeClass("gallery_active");
			me.addClass("gallery_active");			
			$("div.cnt_div",myObj.controlObj).empty().append(midImg);  //重新生成一个IMG
			
			//打开原图片设置
			var url=__ctx + '/platform/system/sysFile/pictureShow.ht?id='+id+'&type=large';
			url = url +'&title='+encodeURIComponent(title); 
			var html = '  <a id="grouped_'+newName+'_elements" title="'+title+'" href="'+url+'" ></a> ';
			$('#hidden_'+newName+'_ImageDiv').html(html);		
		},	
		
		
		//如果有图片展示插件。上传图片后设置好图片。
		resetEvent:function(myObj,newName){
			var myImg = 'my_'+ newName +'_images';             //大图片ID
			var div_allImg = "div_" + newName + "_allImg";           //所有小图片集合DIV
			var imgObj = $('#'+ myImg);	   
			
			//初始化放大图片功能 (myObj 为图片对象 ，div_allImg为小图片集合的ID)
		//	myObj.initMyImage(imgObj,div_allImg);
			
			//重新初始化大图片的点击事件
			myObj.initImageClick(imgObj,false);  //首先移除之前的点击事件
			myObj.initImageClick(imgObj,true);   //再建立新的点击事件
	        
			//生成了新的ZOMM后序号一定要修改最近修改的容器序号
			this.lastZoomNum = myObj.config.myNum;
		},

		
		//如果有图片展示插件。上传图片后将返回的附件id和名称放到隐藏域。
		/** jsonData 为图片组的相关信息 JSON 格式如下：
		 *  { 
		 *	  "jsonArry":"[{'id':'10000003990001','name':'QQ图片20130704170006.jpg'},{'id':'10000003990000','name':'QQ图片20130704170006.jpg'}]",
		 *	  "creator":"李四",
		 *	  "createTime":"2013-10-23"	  
		 *  } 
		 *  */
		setData:function(fieldName,aryFileId,aryName,jsonData){
			var fieldObj = $("input[name='"+fieldName+"']"); 
			for(var i=0;i<aryFileId.length;i++){
				var jsonObj = {
						      id:aryFileId[i],
						      name:aryName[i]
				         };				
				jsonData.jsonArry.push(jsonObj);
			}
			jsonData.creator = "宏天";
			jsonData.createTime = new Date();	
			var valueStr = JSON2.stringify(jsonData).replace(/\"/g,"'");
			fieldObj.val(valueStr);
			return jsonData;
		},
		
		//获取JSON值。
		/** jsonData 为图片组的相关信息 JSON 格式如下：
		 *  { 
		 *	  "jsonArry":"[{'id':'10000003990001','name':'QQ图片20130704170006.jpg'},{'id':'10000003990000','name':'QQ图片20130704170006.jpg'}]",
		 *	  "creator":"李四",
		 *	  "createTime":"2013-10-23"	  
		 *  } 
		 *  */
		getData:function(fieldName){
			var fieldObj = $("input[name='"+fieldName+"']"); 
			var jsonStr = fieldObj.val();
			var jsonData = null;
			if(typeof(jsonStr)==undefined||jsonStr==null||jsonStr==""){   //没有数据时，制造一个空壳数据
				jsonData = { 
							  jsonArry:new Array(),
							  creator:"宏天",
							  createTime:new Date()	  
				 		   } ;
			//	var valueStr = JSON2.stringify(jsonData).replace(/\"/g,"'");
			}else{
				jsonData = eval('(' + jsonStr + ')');
			}
			return jsonData;
		},
		
		/**
		 * 处理插件上在火有多个zoomContainer的问题 （加上序号方便管理，只需要一次初始化序号）
		 */
		initZoomContainer:function(){         	
			$('div.zoomContainer').each(function(index){
				$(this).attr("zoomContainerNum",index);   //按照顺序设置Zoom插件渲染容器 和  图片控件对象的myNum是序号是一样的，都是按页面中的顺序设置
			});
		},
		
		/**
		 * 设置最后一个zoomContainer是最近修改容器序号，所以把容器设置成自己的序号，用于 处理插件上在火狐上的问题（有多余的zoomContainer的对象 ）
		 */
		setZoomContainer:function(){         	
			var arrays = $('div.zoomContainer');
			if(arrays.length>0){
				arrays.get(arrays.length-1).setAttribute("zoomContainerNum",this.lastZoomNum);   //最后一个容器器为最近修改的
			}
		},
		
		/**
		 * PictureShow图片展示控件的小图片的触发事件
		 */
		onClikeImage:function(t,myNum){
			var me = $(t);
		///	if(me.hasClass("gallery_active"))return;
			var	myObj = this.pictureShowObjs[myNum],
				newName = myObj.newName;				
//			if(this.lastZoomNum!=null){        //不为空时，有最近修改过的情况
//				this.setZoomContainer();    //把原来最近修改上传图片后没有设置序号的重新设置			
//			}
		//	myObj.cleanMyZoomContainer(); //清空之前的ZOOM 重新生成一个ZOOM
			this.setHtml(me, myObj, newName);	//重新设置HTML
			this.resetEvent(myObj, newName);	//重新设置图片事件
			this.lastPictureShowName = myObj.fileName;       //最新有激活过的控件标志
		},
		
		/**
		 * 设置浏览器名称
		 */
		getBrowserName:function(){         	
			var ua=navigator.userAgent.toLowerCase();
		    if(ua.indexOf("msie")>=0){
		        type="MSIE";
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
		    this.browserName = type;
		    return type;
		},
		
		
		/**
		 * PictureShow图片展示控件的上、下张小图片的触发事件
		 */
		changeImageNum:function(num,type){				    
			var myObj = this.pictureShowObjs[num];
			var newName = myObj.newName;
			var arrys = $("a",$("#div_"+newName+"_allImg"));
			var aLength = arrys.length;
			var showNum = myObj.config.showNum;
			var globalNum = myObj.globalNum;
			if(aLength>showNum){
			   if(type=='next'){
				  if(globalNum<0){
				      globalNum = 0;
				  }else if(globalNum+showNum>=aLength){
				//    alert("已经是最后一张了！");
					  return false;     
				  }
				  arrys.each(function(index){						        
					  var obj = $(this);
					  if(index==globalNum){
						  obj.hide();
					  }else if(index==globalNum+showNum){
						  obj.show();
					  }
				  });
				  globalNum = globalNum + 1;
				  //处理上、下一张按钮样式
				  if(globalNum+showNum>=aLength){
					  $("#a_"+newName+"_next").attr("class","pictureShow_n_next");
				  }else{
					  $("#a_"+newName+"_next").attr("class","pictureShow_next");
				  }
				  $("#a_"+newName+"_prev").attr("class","pictureShow_prev");
			   }else{
				  if(globalNum<0){
				      globalNum = 0;
				  }else	if(globalNum+showNum<=showNum){
				//	  alert("已经是第一张了！");
					  return false;
				  }
				  globalNum = globalNum - 1;						  
				  arrys.each(function(index){						        
						var obj = $(this);
						if(index==globalNum+showNum){
						    obj.hide();
					    }else if(index==globalNum){
						    obj.show();
					    } 
				  });
				  //处理上、下一张按钮样式
				  if(globalNum+showNum<=showNum){
					  $("#a_"+newName+"_prev").attr("class","pictureShow_n_prev");
				  }else{
					  $("#a_"+newName+"_prev").attr("class","pictureShow_prev");
				  }
				  $("#a_"+newName+"_next").attr("class","pictureShow_next");
			   }
			   PictureShowPlugin.pictureShowObjs[num].globalNum = globalNum;
			}else{
			  //处理两个上、下张按键	
				$("#a_"+newName+"_prev").attr("class","pictureShow_n_prev");
				$("#a_"+newName+"_next").attr("class","pictureShow_n_next");
			}
			this.lastPictureShowName = myObj.fileName;       //最新有激活过的控件标志
		}
		
		
};



/**
 * 直接文件上传文本框。
 * 使用方法。
 * UploadDialog({callback:function(json){
 *			for(var i=0;i<json.length;i++){
 *				var obj=json[i];
 *				alert(obj.fileId +"," + obj.fileName);
 *			}
 *		}})	;
 * @param conf
 */
function pictureShowUploadDialog(conf) {
	if(!conf) conf={};
	var url=__ctx + "/platform/system/sysFile/uploadDialog.ht";
	var winArgs="dialogWidth:450px;dialogHeight:300px;help:0;status:0;scroll:1;center:1";
	url=url.getNewUrl();
	/*var rtn=window.showModalDialog(url,conf,winArgs);
	if(rtn!=undefined){
		if(conf.callback){
			var fileIds = new Array();
			var fileNames = new Array();
			var filePaths = new Array();
			var extPath = new Array();
	 		for(var i=0;i<rtn.length;i++){
	 			var fileObj=rtn[i];
	 			fileIds.push(fileObj.fileId);
	 			fileNames.push(fileObj.fileName);
				var ext=$.getFileExtName(fileObj.fileName);
		//		filePaths.push("/platform/system/sysFile/file_id" +fileObj.fileId +".ht");
				extPath.push(ext);
	 		}
			conf.callback.call(this,fileIds.join(','),fileNames.join(','),filePaths.join(','),extPath.join(','));
		}*/
	/*KILLDIALOG*/
	var that = this;
	DialogUtil.open({
		height:300,
		width: 450,
		title : '文件上传',
		url: url, 
		isResize: true,
		//自定义参数
		conf: conf,
		sucCall:function(rtn){
			if(conf.callback){
				var fileIds = new Array();
				var fileNames = new Array();
				var filePaths = new Array();
				var extPath = new Array();
		 		for(var i=0;i<rtn.length;i++){
		 			var fileObj=rtn[i];
		 			fileIds.push(fileObj.fileId);
		 			fileNames.push(fileObj.fileName);
					var ext=$.getFileExtName(fileObj.fileName);
					extPath.push(ext);
		 		}
				conf.callback.call(that,fileIds.join(','),fileNames.join(','),filePaths.join(','),extPath.join(','));
			}
		}
	});
}


