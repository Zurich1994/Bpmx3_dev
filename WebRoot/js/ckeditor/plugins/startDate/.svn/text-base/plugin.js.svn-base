(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor)
        {
			editor.insertHtml("{开始日期:startDate}");
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='startDate';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('startDate',{
                label:'开始日期',
                icon: this.path + 'startDate.png',
                command:b
            });
        }
    });
})();
