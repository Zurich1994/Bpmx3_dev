(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor)
        {
			editor.insertHtml("{流程标题:title}");
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='subject';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('subject',{
                label:'主题',
                icon: this.path + 'subject.png',
                command:b
            });
        }
    });
})();
