(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor)
        {
        	//addClick();
			editor.insertHtml("${剩余时间}");
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='remaintime';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('remaintime',{
                label:'插入该节点剩余时间',
                icon: this.path + 'alarm-clock-blue.png',
                command:b
            });
        }
    });
       
})();
