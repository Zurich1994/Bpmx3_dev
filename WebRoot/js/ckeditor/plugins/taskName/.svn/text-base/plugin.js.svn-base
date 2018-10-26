(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor)
        {
			editor.insertHtml("${事项名称}");
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='taskName';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('taskName',{
                label:'插入事项名称',
                icon: this.path + 'document-attribute-n.png',
                command:b
            });
        }
    });
       
})();
