(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor)
        {
			editor.insertHtml("${text表单}");
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='textDefForm';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('textDefForm',{
                label:'插入text表单',
                icon: this.path + 'document-attribute-n.png',
                command:b
            });
        }
    });
       
})();
