ckeditor = function(textareaid,ctx) {
		CKEDITOR.context =ctx;
	//构造对像
	var editor = CKEDITOR.replace(textareaid, {
		skin : 'office2003',
	
		toolbarCanCollapse : false,
		toolbarGroupCycling : true,
		extraPlugins:"img,attchment,video,readperson,sendperson,jumpurl",
		
		
		
		toolbar : [
		           { name: 'document',    items : [ 'Source','-','NewPage','DocProps','Preview','Print','-','Templates' ] },
		           { name: 'clipboard',   items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
		           { name: 'editing',     items : [ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ] },
		          
		          
		           { name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','-','RemoveFormat' ] },
		           { name: 'paragraph',   items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
		           { name: 'links',       items : [ 'Link','Unlink','Anchor' ] },
		           { name: 'insert',      items : [ 'Flash','Table','HorizontalRule','Smiley','SpecialChar' ] },
		          
		           { name: 'styles',      items : [ 'Styles','Format','Font','FontSize' ] },
		           { name: 'colors',      items : [ 'TextColor','BGColor' ] },
		           { name: 'tools',       items : [ 'Maximize', 'ShowBlocks','-','img','attchment', 'video','readperson','sendperson','jumpurl'] }
		       ]
 
	});
	
	
	return editor;
};



