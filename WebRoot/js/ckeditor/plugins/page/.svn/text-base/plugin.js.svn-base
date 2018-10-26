(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor){
        	var imgHtml = '#tagpage#';
			editor.insertHtml(imgHtml);
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='page';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('page',{
                label:'插入分页',
                icon: this.path + 'pagebreak.gif',
                command:b
            });
        }
    });
})();
