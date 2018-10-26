(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor)
        {
        	//addClick();
			editor.insertHtml("${跳转地址}");
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='jumpurl';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('jumpurl',{
                label:'插入跳转地址',
                icon: this.path + 'icon002a10.gif',
                command:b
            });
        }
    });
       
})();
