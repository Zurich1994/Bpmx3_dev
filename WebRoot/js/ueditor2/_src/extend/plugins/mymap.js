UE.commands['mymap'] = {	
	execCommand : function(cmdName) {
		var me=this;
	},
	queryCommandState : function() {
		return this.highlight ? -1 : 0;;
	}
}