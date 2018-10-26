/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

/**
 * @file Preview plugin.
 */

(function()
{
	
	var previewCmd =
	{
		modes : { wysiwyg:1, source:1 },
		canUndo : false,
		readOnly : 1,
		exec : function( editor )
		{
			
			var form = $('<form method="post" action="../bpmFormHandler/edit.ht" target="_blank"></form>');
			var input = $('<input type="hidden" name="html">').val(editor.getData());
			form.append(input);
			
			$('body').append(form);
			
			form.submit();
			form.remove();
			
		}
	};

	var pluginName = 'custPreview';

	// Register a plugin named "preview".
	CKEDITOR.plugins.add( pluginName,
	{
		init : function( editor )
		{
			editor.addCommand( pluginName, previewCmd );
			editor.ui.addButton( 'CustPreview',
				{
					label : editor.lang.preview,
					icon: 'custom/preview/cab.gif',
					command : pluginName
				});
		}
	});
})();
