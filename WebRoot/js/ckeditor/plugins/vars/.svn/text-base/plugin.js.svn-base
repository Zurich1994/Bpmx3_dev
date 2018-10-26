(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor)
        {
        	var defId=$("#defId").val();
        	FlowVarWindow({defId:defId,callback:function(varKey,varName){
        		editor.insertHtml("{"+varName+":"+varKey+"}");
			}});
			
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='vars';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('vars',{
                label:'流程变量',
                icon: this.path + 'vars.png',
                command:b
            });
        }
    });
})();
