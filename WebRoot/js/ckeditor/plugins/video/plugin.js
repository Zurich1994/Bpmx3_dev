(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor){
        	var ctx=CKEDITOR.context;
        	if(ctx==null & ctx==undefined)
        		ctx="";
        	var features = "dialogHeight:500px; dialogWidth:750px; status:no; scroll:auto; resizable:no; help:no; center:yes;";
			var url = jQuery.getContextPath()+ "/manage/file/getFile.ht?type=4";//type =4 为视频
			if(ctx!=="")
				url+="&ctx=" + ctx;
			var vReturnValue = window.showModalDialog(url, window,features);
			if (typeof (vReturnValue) == 'undefined'||vReturnValue==null) {
				vReturnValue = window.retureValue;
		    }
			
			if (vReturnValue == undefined|| vReturnValue == 'undefined')vReturnValue = "";
			if(vReturnValue!=""){
				setTimeout(function() 
						{
				editor.insertHtml(vReturnValue);
						}, 1);
			}
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='video';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
        	
            editor.addCommand(b,a);
            editor.ui.addButton('video',{
                label:'插入视频',
                icon: this.path + 'cab.png',
                command:b
            });
        }
    });
})();
