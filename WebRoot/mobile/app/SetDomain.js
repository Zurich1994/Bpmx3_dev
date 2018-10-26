
/**
 * 设置域名路径
 * by cjj
 */

Ext.define('mobile.SetDomain', {
    extend: 'Ext.form.Panel',
    
    name: 'domain',
    
    constructor: function (conf) {
    	conf = conf || {};
    	var me = this;
    	Ext.apply(conf,{
    		items : [
    			{
	        	    docked: 'top',
	        	    title: '设置域名路径',
	        	    xtype: 'titlebar',
	        	    items:[{
						xtype : 'button',
						id : 'btnCancel',
						text : '取消',
						align : 'left',
						handler : function() {
							me.destroy();
							mobileNavi.add(Ext.create('mobile.Login', {fullscreen: true}));
						}
	        	    },{
						xtype : 'button',
						id : 'btnset',
						text : '确定',
						align : 'right',
						handler : function() {
					        var domVal = me.getCmpByName('domPath').getValue();
					        __ctx = domVal;
					        localStorage.setItem("__ctx",domVal);
							me.destroy();
							mobileNavi.add(Ext.create('mobile.Login', {fullscreen: true}));
						}
	        	    }]
	        	},
    			{
	    			xtype: 'fieldset',
	    			defaults:{
	    				 labelWidth:document.body.clientWidth/3,
	    				 clearIcon : true
	    			},
	    			items:[
				        {
				            xtype: 'textareafield',
				            name: 'domPath',
				            label: '域名路径',
				            maxRows: 4,
				            value:__ctx
				        }
			        ]
		        }
	        ]
    	});
    	
    	this.callParent([conf]);
    }

});

