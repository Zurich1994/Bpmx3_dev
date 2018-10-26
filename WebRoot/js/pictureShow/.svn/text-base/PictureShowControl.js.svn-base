/**
 * PictureShow控件。
 * 使用方法：
 * var obj=new PictureShowControl();
 * initPicture("pictureContainer","divContainer",{fileId:123});
 * 	divContainer： 文档容器id
 * 	fileId：附件id，如果指定那么根据该文件id加载word文档。
 *  
 * @returns {PictureShowControl}
 */
PictureShowControl=function(){
	{		
		var me = this;
		this.controlId="";        //图片展示容器ID
		this.controlObj=null;       //图片展示容器对象
		this.fileName = "";        //存放JSON的隐藏域名称
		this.newName = "";           //新的名称
		this.swidth=52;   //小图片宽 （没有值时就默认这个）
		this.sheight=35;      //小图片高 （没有值时就默认这个）
		this.globalNum = 0;	    //全局参数，为于判断当前点击上、下张按键次数
		this.updateNum = 0;         //进入后做文件上传修改次数  从0开始，初始化是算0的，在第一次修改时算为1，再下次就为2...
		this.config={pictureWidth:'320',pictureHeight:'240',swidth:'52',sheight:'35',showNum:'5',myNum:'0',sortType:"asc",browserName:"MSIE"};
	};

	/**
	 * 将控件添加到div容器中。
	 * 第一个参数：
	 * JSON数据的隐藏域名称
	 * 第二个参数：
	 * 图片图示div的容器ID
	 * 第三个参数:
	 * conf:
	 * jsonData 为图片组的相关信息 JSON 格式如下：
	 *  { 
	 *	  "jsonArry":"[{'id':'10000003990001','name':'QQ图片20130704170006.jpg'},{'id':'10000003990000','name':'QQ图片20130704170006.jpg'}]",
	 *	  "creator":"李四",
	 *	  "createTime":"2013-10-23"	  
	 *  } 
	 * pictureWidth  大图片宽   
	 * pictureHeight 大图片高
	 * showNum:showNum  展示小图片个数
	 */
	this.initPicture=function(name,divContainerId,conf){	
		//先处理之前容器 的东西
		if(me.updateNum!=0){          //不是第一次进入
			var oname = name.replaceAll(":","_")+this.updateNum;
	   //   me.cleanMyZoomContainer();    //解决火狐存在多一个ZoomContainer问题（在初始化图片事件时，先去掉自己的ZOOM容器，再重新初始化自己的ZOOM容器）
            me.initImageClick($('#my_'+oname+'_images'), false);
    		this.updateNum = this.updateNum + 1;       //每初化一次，就相当修改一次
    	}
		
		var url=__ctx + '/platform/system/sysFile/getFileById.ht';
		this.config=$.extend({},this.config,conf);
		this.fileName = name;
		this.newName = name.replaceAll(":","_")+this.updateNum;
		if(typeof(this.config.swidth)!=undefined&&this.config.swidth!=null&&this.config.swidth!=""&&parseInt(this.config.swidth)>0){
			this.swidth = parseInt(this.config.swidth);  
		}
		if(typeof(this.config.sheight)!=undefined&&this.config.sheight!=null&&this.config.sheight!=""&&parseInt(this.config.sheight)>0){
			this.sheight = parseInt(this.config.sheight); 
		}
		this.controlObj = $('#'+divContainerId);
		this.controlId = divContainerId;
		var data = this.config.jsonData;
        var jsonArry = data.jsonArry;
        
        if( !(this.controlObj.hasClass("div_pictureShow")) ){
        	this.controlObj.addClass("div_pictureShow"); 
        }
        me.controlObj.empty().append(this.getImgDiv(jsonArry));
        me.initEvent();         //初始化图片事件
        //在TAB下面时IE有大图片偏移的样式问题处理(每一次由FormUtil.initTabStyle()方法实现了)
      //  var tabLength = $("#formTab").length
        if(this.config.browserName=="MSIE"){
    		$("div.wrap_div",me.controlObj).css("text-align","left"); 
    	}
      
    	//  me.globalNum = 0;	    //全局参数，为于判断当前点击上、下张按键次数  重新初始化时 归为 0 或者是最后一张 这要看 sortType的类型  放在initImages方法处理

    };
    
    this.getImgDiv = function(array){
    	if(!array||array.length==0){
    		array = [{name:'例示图片',url:__ctx+'/js/pictureShow/images/image1.jpg?1=1'}];
    	}
    	var url=__ctx + '/platform/system/sysFile/getFileById.ht';
    	var sNum = 0;
    	if(this.config.sortType=="desc"){  //反序显示
        	sNum = array.length-1;
    	}
    	var oneUrl = array[0].url ? array[0].url:(url + '?fileId=' + array[sNum].id);
    	//中等图片
    	var midImg = ImageQtip.getImage(oneUrl+'&type=small',{id:"my_"+me.newName+"_images",title:array[sNum].name,zoomUrl:oneUrl+'&type=large',maxWidth:me.config.pictureWidth,maxHeight:me.config.pictureHeight});
   //   var midImg = new Image();
   // 	$(midImg).attr("id","my_"+me.newName+"_images").attr("src",oneUrl).attr("data-zoom-image",oneUrl).css({"width":me.config.pictureWidth-2,"height":me.config.pictureHeight-2});
    	var	cnt = $('<div class="cnt_div"></div>').attr("id","div_"+me.newName+"_images").append(midImg);
    	var	hack = $('<div class="hack_div"></div>').append(cnt);
    	var	wrap = $('<div class="wrap_div"></div>').append(hack).css({"width":me.config.pictureWidth,"height":me.config.pictureHeight});
    //隐藏DIV 用于打开查看原图片	
    	var hiddenA = $('<a></a>').attr("id","grouped_"+this.newName+"_elements").attr("title",array[0].name).attr("href",oneUrl+'&type=large'),
			hiddenDiv = $('<div class="hidden"></div>').attr("id","hidden_"+this.newName+"_ImageDiv").append(hiddenA);
	
    	
    	//小图片
    	var allImg = $('<div></div>').attr("id","div_"+me.newName+"_allImg");
    	for ( var i = 0; i < array.length; i++) {
            var c = array[i];
    		oneUrl = c.url?c.url:(url + '?fileId=' + c.id); 
    		var thumb = $('<a class="thumbnail-a elevatezoom-gallery"></a>').attr("id",c.id)
    		                                                     .attr("title",c.name)
    															 .attr("data-image",oneUrl+'&type=small')
															     .attr("data-zoom-image",oneUrl+'&type=large')
															     .css({width:me.swidth + 2,height:me.sheight + 2}),
			    img = ImageQtip.getImage(oneUrl+'&type=small',{id:c.id,title:c.name,maxWidth:me.swidth,maxHeight:me.sheight});
    		// img = new Image();
    		//$(img).attr("src",oneUrl).css({"width":me.swidth,"height":me.sheight});
    		thumb.click(function(){
    			PictureShowPlugin.onClikeImage(this,me.config.myNum);
    		});
    		if(i==sNum){
    			thumb.addClass("gallery_active");
    		}
    		thumb.append(img);
    		allImg.append(thumb);
    	}
    	//小图片表格
    	var	prevA = $('<a class="pictureShow_n_prev" href="###"></a>').attr("id","a_"+me.newName+"_prev"),
    		nextA = $('<a class="pictureShow_n_next" href="###"></a>').attr("id","a_"+me.newName+"_next"),
    		preTd = $('<td></td>').css("width","21px").css("border-color","#CCCCCC").append(prevA),
    		td = $('<td></td>').css("border-color","#CCCCCC").append(allImg),
    		nextTd = $('<td></td>').css("width","21px").css("border-color","#CCCCCC").append(nextA),
    		tr = $('<tr></tr>').append(preTd).append(td).append(nextTd),
    		table = $('<table class="table_pictureShow" width="100%"></table>').append(tr);
    		
    	prevA.click(function(){
    		PictureShowPlugin.changeImageNum(me.config.myNum,'prev');
    	});
    	nextA.click(function(){
    		PictureShowPlugin.changeImageNum(me.config.myNum,'next');
    	});
    	
    	var div = $('<div></div>').append(wrap).append(table).append(hiddenDiv);
    	return div;
    };
    
    /**
	 * 初始化图片和触发事件
	 */
	this.initEvent=function(){
		var div_allImg = 'div_'+this.newName+'_allImg';
		var myImage = 'my_'+this.newName+'_images';
		var myDivImage = 'div_'+this.newName+'_images';
		var myImgObj = $("#"+myImage);
		var arrys = $("a",$("#"+div_allImg));		
		
        //初始化图片 (升序显示前this.config.showNum个小图)
		me.initImages(arrys,this.config.sortType);    
		
		//放大图片的功能
	//	me.initMyImage(myImgObj,div_allImg);
		
		//初始化大图片点击按钮 （myImgObj 图片对象  mark--建立：true,撤消：false   用于查看大图片）
		me.initImageClick(myImgObj,true);
		
		//初始化移到小图片事件按钮 (arrys 数组  ，mark决定－－ 建立:true,撤消：false)
	//	this.initImageHover(arrys,true)
		
	},
	
	
	//初始化放大图片功能 (myImgObj 为图片对象 ，div_allImg为小图片集合的ID)
	this.initMyImage=function(myImgObj,div_allImg){         
		var width = this.config.pictureWidth * 10/9
		var height = this.config.pictureHeight * 10/9
		myImgObj.elevateZoom({
			tint:true, 
			tintColour:'black',                  //遮挡大图片阴影颜色  red\green\black...
			tintOpacity:0.4,                    //遮挡大图片阴影透明度
			zoomWindowPosition: 1,                //放大局部图片的位置
			zoomWindowFadeIn: 500,                  //放大局部图片展示时间
			zoomWindowFadeOut: 500,            //放大局部图片展示时间
			scrollZoom : true,                       //放大局部图片的缩放功能
			zoomWindowWidth:width,                 // 放大局部图片显示区域的宽
            zoomWindowHeight:height,                 // 放大局部图片显示区域的高
			gallery : div_allImg,             //图片集合的DIV对象
			cursor : 'pointer',
			galleryActiveClass : "gallery_active",               //点击激活后的状态
			imageCrossfade : false,                               //按自己设定的大图片大小 展示大图
			loadingIcon : __ctx + "/js/pictureShow/images/spinner.gif"                 //加载显示图片					   
		});
	},
	
	
	//初始化图片 (数据，排序:降序 desc 其它为升序asc)
	this.initImages=function(arrys,type){         //初始化图片显示
		var showNum = this.config.showNum;
		if(arrys.length<=showNum){         //不够图片时直接不用做显示和隐藏
			//处理两个上、下张按键
			$("#a_"+this.newName+"_prev").attr("class","pictureShow_n_prev");
			$("#a_"+this.newName+"_next").attr("class","pictureShow_n_next");
		    return false;
		}else{
			type = type.replace(/(^\s*)|(\s*$)/g, "").toLowerCase();
			if(type=="desc"){
				//处理两个上、下张按键
				$("#a_"+this.newName+"_prev").attr("class","pictureShow_prev");
				$("#a_"+this.newName+"_next").attr("class","pictureShow_n_next");
				var lnum = arrys.length - showNum;
				arrys.each(function(index){       
					var aObj=$(this);
					if( index >= lnum){               //只显示后showNum个图片
						aObj.show();
					}else{
						aObj.hide();
					}
					if(index==arrys.length-1){
						if(!(aObj.hasClass("gallery_active"))){
							aObj.addClass("gallery_active");
						}						
					}
				});
				this.globalNum = lnum;            //相当于跳转了lnum次。作为跳转标识
			}else{
				//处理两个上、下张按键
				$("#a_"+this.newName+"_prev").attr("class","pictureShow_n_prev");
				$("#a_"+this.newName+"_next").attr("class","pictureShow_next");
				arrys.each(function(index){		     //显示隐藏图片				        
					var obj = $(this);
					if(index<showNum){         //只显示前showNum个图片
					    obj.show();
					}else{
						obj.hide();
					}
					if(index==0){
						if(!(obj.hasClass("gallery_active"))){
							obj.addClass("gallery_active");
						}						
					}
			    });
				this.globalNum = 0;            //相当于没有跳转了次数。作为跳转标识
			}			
		}
	},
	
	
	//初始化大图片点击按钮 (myImgObj 图片对象  mark--建立：true,撤消：false)
	this.initImageClick=function(myImgObj,mark){         //初始化图片显示
		//初始化大图片点击按钮触发事件
		if(!mark){
			myImgObj.unbind("click"); //移除click
		}else{
			//初始化隐藏DIV：hiddenImageDiv 的链接点击事件（用于在点击hiddenImageDiv内的链接时，打开图片明细用）
			$('#grouped_'+this.newName+'_elements').fancybox({
				'titleShow':true,
				'width':'100%',
				'height':'100%',
				'autoScale':true,
				'transitionIn':'none',
				'transitionOut':'none',
				'scrolling':'auto',
				'type':'iframe'
			});
			
			myImgObj.bind("click", function(e) {                //点击大图片展示原图事件			
				$('#grouped_'+me.newName+'_elements').trigger("click");          //模拟 隐藏 DIV：hiddenImageDiv 的链接点击事件
				PictureShowPlugin.lastPictureShowName = me.fileName;    //最新有激活过的控件标志
			});	
		}		
	},
	
	//初始化移到小图片事件按钮 (arrys 数组  ，mark决定－－ 建立:true,撤消：false)
	this.initImageHover=function(arrys,mark,myImgObj){         //初始化图片显示
		
		arrys.each(function(index){                                     //鼠标移过小图片事件    
			var aObj=$(this);
			if(!mark){
				aObj.unbind("mouseenter").unbind("mouseleave");           //移除之前的hover事件
			}else{
				var myclass = aObj.attr("class");						
				var ez = myImgObj.data('elevateZoom');	
				aObj.hover(                                           //建立hover事件
					 function() {
						 aObj.addClass("gallery_ahover"); 
						 if(myclass.indexOf("gallery_active") < 0){     //本身是没有被选中的就执行
						 	var smallImage = aObj.attr("data-image");
						 	var largeImage = aObj.attr("data-zoom-image");	
						 	ez.swaptheimage(smallImage, largeImage); 
						 }
					 },
					 function() {	
						 aObj.removeClass("gallery_ahover");
						 if(myclass.indexOf("gallery_active") < 0){     //本身是没有被选中的就执行
							 arrys.each(function(index){
								  var otherObj=$(this);
								  var otherClass = otherObj.attr("class");
								  if(otherClass.indexOf("gallery_active") > -1){
									  var small = otherObj.attr("data-image");
									  var large = otherObj.attr("data-zoom-image");	
									  ez.swaptheimage(small, large); 
									  return false;
								  }
							 });
						 }							 
					 }
				);	
			}
			PictureShowPlugin.lastPictureShowName = me.fileName;   //最新有激活过的控件标志
		});  
	},
	
	//处理插件上在的问题（有多余的zoomContainer的对象 ）
	this.cleanMyZoomContainer=function(){         	
		$('div.zoomContainer').each(function(index){
			var num = $(this).attr("zoomContainerNum");
			if(num==me.config.myNum){
				$(this).remove();
			}
		});

	}
	
};

