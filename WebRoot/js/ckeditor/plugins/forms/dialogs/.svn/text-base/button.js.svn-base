/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.dialog
		.add(
				'button',
				function(a) {
					var script=new ScriptManage();
					function b(c) {
						var e = this;
						var d = e.getValue();
						if (d) {
							c.attributes[e.id] = d;
							if (e.id == 'name')
								c.attributes['data-cke-saved-name'] = d;
						} else {
							delete c.attributes[e.id];
							if (e.id == 'name')
								delete c.attributes['data-cke-saved-name'];
							
						}
					}
					;
					return {
						title : a.lang.button.title,
						minWidth : 350,
						minHeight : 150,
						onShow : function() {
							var e = this;
							delete e.button;
							var c = e.getParentEditor().getSelection()
									.getSelectedElement();
							if (c && c.is('input')) {
									e.button = c;
									e.setupContent(c);
							}
						},
						onOk : function() {
							var c = this.getParentEditor(), d = this.button, e = !d, f = d ? CKEDITOR.htmlParser.fragment
									.fromHtml(d.getOuterHtml()).children[0]
									: new CKEDITOR.htmlParser.element('input');
							this.commitContent(f);
							var g = new CKEDITOR.htmlParser.basicWriter();
							f.writeHtml(g);
							var h = CKEDITOR.dom.element.createFromHtml(g
									.getHtml(), c.document);
							if (e){
								c.insertElement(h);
						    }else {
								h.replace(d);
								c.getSelection().selectElement(h);
							}

							var functions=script.getAryScripts();
							
							script.setScripts(functions);
							
							var name=this.getContentElement('event','onclick').getValue();
							var body=this.getContentElement('event','funV').getValue();
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
						contents : [ {
							id : 'info',
							label : a.lang.button.title,
							title : a.lang.button.title,
							elements : [
									{
										id : 'name',
										type : 'text',
										label : a.lang.common.name,
										'default' : '',
										setup : function(c) {
											this.setValue(c
													.data('cke-saved-name')
													|| c.getAttribute('name')
													|| '');
										},
										commit :b
									},
									{
										id : 'value',
										type : 'text',
										label : a.lang.button.text,
										accessKey : 'V',
										'default' : '',
										setup : function(c) {
											this.setValue(c
													.getAttribute('value')
													|| '');
										},
										commit : b
									},
									 {
										id :'type',
										type : 'text',
										label : '类型',
										'default' : 'button',
										onLoad : function()
										{
											this.getInputElement().setAttribute( 'readOnly', true );
										},
										setup : function(c) {
											var value = c.hasAttribute('type') && c.getAttribute('type');
											this.setValue('button');
										},
										commit :b
									 }]
						} ,{
							id : 'event',
							label :'事件脚本',
							title : '事件脚本',
							elements :[{
								id :'onclick',
								type:'text',
								label:'函数名','default' : '',
								setup:function(c){
									this.setValue(script.name||'');
								},
								commit:b
							},
							{
								id : 'funV',
								type : 'textarea',
								label : '函数体','default' : '',
								setup : function(c){
									this.setValue(script.body||'');
								},
								commit :null
							}]
						}]
					};
				});
