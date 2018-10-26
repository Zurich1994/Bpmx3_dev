(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor)
        {
			editor.insertHtml("{发起人:startUser}");
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='startUser';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('startUser',{
                label:'发起人',
                icon: this.path + 'startUser.png',
                command:b
            });
        }
    });
})();
