(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor)
        {
			editor.insertHtml("{业务主键:businessKey}");
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='businessKey';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('businessKey',{
                label:'业务主键',
                icon: this.path + 'bussinessKey.png',
                command:b
            });
        }
    });
})();
