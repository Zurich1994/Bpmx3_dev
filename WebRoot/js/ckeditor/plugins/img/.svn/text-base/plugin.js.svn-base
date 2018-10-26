(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor){
        	var ctx=CKEDITOR.context;
        	if(ctx==null & ctx==undefined)
        		ctx="";
        	
        	var features = "dialogHeight:500px; dialogWidth:750px; status:no; scroll:auto; resizable:no; help:no; center:yes;";
			var url = jQuery.getContextPath()+ "/manage/file/getFile.ht?type=3";//type =3 为只选图片
			
			if(ctx!=="")
				url+="&ctx=" + ctx;
			
			var vReturnValue = window.showModalDialog(url, window,features);
			if (typeof (vReturnValue) == 'undefined'||vReturnValue==null) {
				vReturnValue = window.retureValue;
		    }
			
			if (vReturnValue == undefined|| vReturnValue == 'undefined')vReturnValue = "";
			if(vReturnValue!=""){
				var imgHtml = '<img  src= \"' + vReturnValue + '\" >';
				editor.insertHtml(imgHtml);
			}
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='img';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('img',{
                label:'插入图片',
                icon: this.path + 'image.gif',
                command:b
            });
        }
    });
})();
