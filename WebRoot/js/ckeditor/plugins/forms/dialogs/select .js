/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.dialog
		.add(
				'select',
				function(a) {
					var script=new ScriptManage();
					return {
						title :'添加事件脚本',
						minWidth : 350,
						minHeight : 200,
						onShow : function(){
							delete this.select;
							var element=this.getParentEditor().getSelection().getSelectedElement();
							if(element&&element.is('select')){
								this.select=element;
								this.setupContent(element);
							}
						},
						onOk : function(){
							element=this.select;
							this.commitContent(element);
							
							var functions=script.getAryScripts();
							
							script.setScripts(functions);
							
							var name=this.getContentElement('event','name').getValue();
							var body=this.getContentElement('event','body').getValue();
							script.setName(name||'');
							script.setBody(body||'');
							
							var editor=CKEDITOR.instances.html;
							
							if(name&&body){
								script.addScript(name, body);
							}
							
							var text=script.getScriptText();
							var s='<script>'+text+'</script>';
							var data=editor.getData();
							var str=script.getHtml();
							var html=data.replace(s,'');
							html=html.trim();
							editor.setData( str+html);
							
						},
						contents : [{
							id : 'event',
							label :'事件脚本',
							title : '事件脚本',
							elements :
								[
								 {
									 id:'name',
									 type : 'text',
									 label : '函数名',
									 setup : function(element){
										 this.setValue(script.name||'');
									 },
									 commit : function(element){
										 var name=this.getValue()||'';
										 element.setAttribute('onchange',name);
									 }
								 },
								 {
									 id : 'body',
									 type : 'textarea',
									 label : '函数体',
									 setup : function(element){
										 this.setValue(script.body||'');
									 },
									 commit :null
								 }]
						}]
					}
				});
