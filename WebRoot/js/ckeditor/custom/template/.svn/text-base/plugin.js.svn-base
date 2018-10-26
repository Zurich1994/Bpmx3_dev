/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

/**
 * @file template plugin.
 */

(function()
{
	
	var templateCmd =
	{
		modes : { wysiwyg:1, source:1 },
		canUndo : false,
		readOnly : 1,
		exec : function( editor )
		{
			FormDef.showSelectTemplate('selectTemplate.ht?tableId=' + tableId +"&isSimple=1");
		}
	};

	var pluginName = 'template';

	// Register a plugin named "template".
	CKEDITOR.plugins.add( pluginName,
	{
		init : function( editor )
		{
			editor.addCommand( pluginName, templateCmd );
			editor.ui.addButton( 'Template',
				{
					label : '重选模板',
					icon: 'custom/template/cab.png',
					command : pluginName
				});
		}
	});
})();
