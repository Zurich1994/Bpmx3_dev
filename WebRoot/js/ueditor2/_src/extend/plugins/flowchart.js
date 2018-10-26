UE.commands['flowchart'] = {
    execCommand:function (cmd) {
    	var me = this;
    	var html=' <div name="editable-input" class="flowchart"><input type="text" /></div>';
        me.execCommand('insertHtml',html);
    },
    queryCommandState:function () {
        return this.highlight ? -1 : 0;
    }
};

