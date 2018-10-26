
<#assign className=model.variables.class>
<#assign classVarName=model.variables.classVar>

/**
* ${className}Detail
*/

Ext.define('mobile.${className}Detail', {
    extend: 'Ext.form.Panel',
    
    name: '${classVarName}Detail',

    constructor: function (config) {
    	
    	config = config || {};
    	
    	this.taskId = config.taskId;
    	
    	Ext.apply(config,{
    		title:'${className}',
    		items: [
    			{
	    			xtype: 'fieldset',
		    		items:[
		    			
		    		]
	    		}
    		]
    	});
    	
    	this.callParent([config]);
    }
    
});