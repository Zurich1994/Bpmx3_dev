Namespace.register("com.hotent.ui");  
com.hotent.ui.Window = function(title,width,height){     
	{
		var x=($(document.body).width()-width) /2;
		dhxWins = new dhtmlXWindows();
		dhxWins.setSkin("dhx_skyblue");
		dhxWins.attachEvent("onClose", function(win){
			 win.hide();
		});
		this.win = dhxWins.createWindow("win", x, 0, width, height);
		this.win.setText(title);
	}
	
    this.show=function()
    {
    	this.win.show();
    },
    this.hide=function()
    {
    	this.win.hide();
    },
    this.attachUrl=function(url)
    {
    	this.win.attachURL(url);
    };
    this.attachObject=function(url)
    {
    	this.win.attachObject(url);
    };
};
    
    
