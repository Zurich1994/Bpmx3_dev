(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor)
        {
        	//addClick();
			editor.insertHtml("${收件人}");
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='readperson';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('readperson',{
                label:'插入收件人',
                icon: this.path + 'users.png',
                command:b
            });
        }
    });
    
    function addClick(){
		var urDlg=new UserDialog(
			{
				callback:dlgCallBack,
				isSingle:false
			}
		);
		urDlg.show();
	};
	function dlgCallBack(userIds,fullnames)
	{
		editor.insertHtml(fullnames);		
	};
})();
