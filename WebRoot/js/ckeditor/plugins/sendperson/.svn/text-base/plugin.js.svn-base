(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor)
        {
        	//addClick();
			editor.insertHtml("${发件人}");
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='sendperson';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('sendperson',{
                label:'插入发件人',
                icon: this.path + 'group.png',
                command:b
            });
        }
    });
       
})();
