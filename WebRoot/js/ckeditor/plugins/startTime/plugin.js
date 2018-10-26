(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor)
        {
			editor.insertHtml("{开始时间:startTime}");
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='startTime';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('startTime',{
                label:'开始时间',
                icon: this.path + 'startTime.png',
                command:b
            });
        }
    });
    
})();
