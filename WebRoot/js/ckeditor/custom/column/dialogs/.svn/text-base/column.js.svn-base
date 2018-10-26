
CKEDITOR.dialog.add( 'column', function( editor )
{
	return {
		title : '字段属性',
		minWidth : 300,
		minHeight : 150,
		onShow : function()
		{
			
			delete this.column;

			var element = this.getParentEditor().getSelection().getSelectedElement();
			if ( element && /^[ms]:/.test(element.getNameAtt()) )
			{
				this.column = element;
				this.setupContent( element );
			}
		},
		contents : [
		{
		    label : 'tab', 
		    title : 'tab',  
		    elements :  
		    [  
				{  
					id: 'table',
				    type : 'text',
				    label : '表名',
				    className : 'cke_disabled',
				    onLoad : function()
					{
						this.getInputElement().setAttribute( 'readOnly', true );
					},
				    setup : function(element) {
						var value = element.hasAttribute('name') && element.getAttribute('name');
						value = value.split(':')[1];
						this.setValue(value || '');
					}
				},
		        {  
		        	id: 'column',
		            type : 'text',  
		            label : '字段名',
		            className : 'cke_disabled',
		            onLoad : function()
					{
						this.getInputElement().setAttribute( 'readOnly', true );
					},
		            setup : function(element) {
						var value = element.hasAttribute('name') && element.getAttribute('name');
						value = value.split(':')[2];
						this.setValue(value || '');
					}
		        }  
		    ]  
		}  
		]
	};
});
