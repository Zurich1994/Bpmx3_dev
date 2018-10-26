UE.commands['addframelist'] = {	
	execCommand : function(cmdName) {
		var me=this;
	},
	queryCommandState : function() {
		return this.highlight ? -1 : 0;;
	}
}