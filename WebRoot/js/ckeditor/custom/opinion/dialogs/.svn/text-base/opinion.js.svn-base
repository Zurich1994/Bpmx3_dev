
CKEDITOR.dialog.add( 'opinion', function( editor )
{
	return {
		title : '意见属性',
		minWidth : 300,
		minHeight : 150,
		onShow : function()
		{
			delete this.opinion;
			delete this.originName;

			var element = this.getParentEditor().getSelection().getSelectedElement();
			if ( element && /^opinion:/.test(element.getNameAtt()))
			{
				this.opinion = element;
				this.originName=  element.getAttribute("name").replace(/^opinion:/, '');;
				this.setupContent( element );
			}
		},
		onOk : function()
		{
			var editor,
			element = this.opinion,
			isInsertMode = !element;
		

			if ( isInsertMode )
			{
				editor = this.getParentEditor();
				element = editor.document.createElement( 'textarea' );
			}
			this.commitContent( element );
			
		
	
			if ( isInsertMode ) {
			
				//添加意见选框。
				var formName = element.hasAttribute('name') && element.getAttribute('name');
				formName = formName.replace(/^opinion:/, '');
				
				var opinionName = element.hasAttribute('opinionName') && element.getAttribute('opinionName');
			
				//添加意见权限
				var rtn=__Permission__.addOpinion(formName,opinionName);
				
				if(rtn){
					editor.insertElement( element );
				}
				else{
					$.ligerDialog.warn('意见表单名称已经存在!','提示信息');
					
				}
			}
			else{
				var curName = element.hasAttribute('name') && element.getAttribute('name');
				var opinionName = element.hasAttribute('opinionName') && element.getAttribute('opinionName');
				curName = curName.replace(/^opinion:/, '');
				__Permission__.replaceOpinion(this.originName,curName,opinionName);
			}
			
		},
		contents : [
		{  
		    label : 'tab', 
		    title : 'tab',  
		    elements :  
		    [  
		        {  
		        	id: 'opinion',
		            type : 'text',  
		            label : '意见名称',
		            setup : function(element) {
						var value = element.hasAttribute('opinionName') && element.getAttribute('opinionName');
					
						this.setValue(value || '');
					},
					commit : function(element) {
						var name = this.getValue() || '';
						element.setAttribute('opinionName', name);
					}
		        },
		        {  
		        	id: 'opinionName',
		            type : 'text',  
		            label : '表单名称',
		            setup : function(element) {
		            
						var value = element.hasAttribute('name') && element.getAttribute('name');
						value = value.replace(/^opinion:/, '');
						this.setValue(value || '');
						
						
					},
					commit : function(element) {
						var name = this.getValue() || '';
						//alert("originName:" + originName);
						element.setAttribute('name', 'opinion:' + name);
					
					}
		        }  
		    ] 
		}  
		]
	};
});
