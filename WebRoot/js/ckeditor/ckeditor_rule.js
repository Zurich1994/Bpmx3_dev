ckeditor = function(textareaid,ctx) {
		CKEDITOR.context =ctx;
	//构造对像
	var editor = CKEDITOR.replace(textareaid, {
		skin : 'office2003',
		height:60,
		toolbarCanCollapse : false,
		toolbarGroupCycling : true,
		extraPlugins:"subject,startUser,startDate,startTime,businessKey,vars",
		
		
		
		toolbar : [
		           { name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike'] },
		           { name: 'styles',      items : [ 'Font','FontSize' ] },
		           { name: 'colors',      items : [ 'TextColor','BGColor' ] },
		           { name: 'tools',       items : [ 'subject','startUser', 'startDate','startTime','businessKey','vars'] }
		       ]
 
	});
	
	
	return editor;
};



