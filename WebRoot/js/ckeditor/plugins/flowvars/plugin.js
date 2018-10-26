(function(){
    CKEDITOR.plugins.add("flowvars",{
        init:function(editor){
        	var pluginName = 'flowvars';
            CKEDITOR.dialog.add(pluginName, this.path + 'dialogs/flowvars.js');
            editor.addCommand(pluginName, new CKEDITOR.dialogCommand(pluginName));
            editor.ui.addButton(pluginName,{
                label:'流程变量',
                icon: this.path + 'images/flowvars.png',
                command:pluginName
            });
        }
    
    });
})();
