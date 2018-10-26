Namespace.register("com.hotent.ui");  
var _divDialog=1;
com.hotent.ui.Dialog = function(title,width,height){     
	{
		this.okHandler=null;
		this.id="div_" +_divDialog;
		var x=($(document.body).width()-width) /2;
		
		this.witdh=width;
		this.height=height;
		this.divWidth=this.width -20;
		this.divHeight=this.height -40;
		
		this.containHeight=this.divHeight-30;
		var str="<div style='position: relative;height:"+this.divHeight+"px;width:100%;'>" +
				"<div  id='"+this.id+"' style=' height:"+this.containHeight+"px;width:100%;'></div>"+
				"<div class='dialogButton'>" +
					"<a href='#' id='btnOK_"+ _divDialog +"' class='button'><span class='left'></span><span class='right'>确定</span></a>&nbsp;&nbsp;" +
					"<a href='#' id='btnCancel_"+ _divDialog +"' class='button' style='margin-right:20px;'><span class='left'></span><span class='right'>取消</span></a>" +
				"</div>"+
				"</div>";
		this.dhxWins = new dhtmlXWindows();
		this.dhxWins.setSkin("dhx_skyblue");
	
		this.win = this.dhxWins.createWindow("win",x, 0, width, height);
		this.win.setText(title);
		this.win.denyResize();
		this.win.allowMove();
		
		this.win.button("minmax1").hide();
		this.win.attachHTMLString(str);
		
		var self=this;
		
		this.dhxWins.attachEvent("onClose", function(win){
			 self.close();
		});
	
		
		$("#btnCancel_" + _divDialog).click(function(){
			self.close();
		});
		
		$("#btnOK_" + _divDialog).click(function(){
			if(self.okHandler!=null){
				self.okHandler(self );
				self.close();
			}
		});
		
		_divDialog++;
	}
	//设置事件处理
	this.setHandler=function(hanler)
	{
		this.okHandler=hanler;
	},
	//隐藏对话框
	this.hide=function()
	{
		var isModal = this.win.isModal(); 
		if(isModal)
			this.win.setModal(false);
		this.win.hide();
	},	
	//显示对话框
    this.show=function()
    {
    	this.win.setModal(true);
    	this.win.show();
    },
    //关闭对话框
    this.close=function()
    {
    	this.win.setModal(false);
    	//this.win.close();
    	this.dhxWins.unload();
    },
    //显示一个页面的DIV
    this.attachObject=function(objId)
    {
    	var obj=$("#" + objId);
    	obj.show();
    	$("#" + this.id).append(obj);
    },
    //显示一个URL页面
    this.attachUrl=function(url)
    {
    	var frameId="_frame_" + _divDialog;
    	var url=url.getNewUrl();
    	var len=$("#" + frameId).length;
    	if(len==0)
    	{
    		var str="<iframe frameborder='0' id='"+frameId+"' src='"+url+"'  width='100%' height='"+(this.divHeight-45)+"' ></iframe>";
    		$("#" + this.id).append(str);
    	}
    	else{
    		$("#" + frameId).attr("src",url);
    	}
    },
    //获取IFRAME
    //IFRAME 中有个函数getFile
    //var iframe=winPic.getIframe();
	//var src=iframe.getFile();
    this.getIframe=function()
    {
    	var frameId="_frame_" + _divDialog;
    	return obj=document.frames[frameId];
    }
};
    
    
