(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor)
        {
        	    var defId=$("#defId").val();
    			var taskNameRule=editor.getData();
    			var data="defId="+defId+"&taskNameRule="+taskNameRule;
    			$.post("saveTitleRule.ht",data,function(msg){
    				var obj=new com.hotent.form.ResultMessage(msg);
    				if(obj.isSuccess()){
    					$.ligerDialog.success(obj.getMessage(),"操作成功");
    				}else{
    					$.ligerDialog.error(obj.getMessage(),"操作失败");
    				}
    			});
    	
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='saveRule';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('saveRule',{
                label:'保存',
                icon: this.path + 'save.png',
                command:b
            });
        }
    });
    
})();
