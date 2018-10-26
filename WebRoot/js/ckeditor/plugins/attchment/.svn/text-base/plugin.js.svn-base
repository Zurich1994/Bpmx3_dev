(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor){
        	
			editor.insertHtml("");
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='attchment';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('attchment',{
                label:'插入附件',
                icon: this.path + 'cab.gif',
                command:b
            });
        }
    });
})();
