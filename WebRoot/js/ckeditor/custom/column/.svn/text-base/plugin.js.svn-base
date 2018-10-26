(function()
{
	CKEDITOR.plugins.add( 'column',
	{
		
		init : function( editor )
		{
			CKEDITOR.dialog.add( 'column', 'custom/column/dialogs/column.js' );
			editor.addCommand( 'column', new CKEDITOR.dialogCommand( 'column' ) );

			editor.addCss(
				'img.cke_flash' +
				'{' +
					'background-image: url(' + CKEDITOR.getUrl( this.path + 'images/placeholder.png' ) + ');' +
					'background-position: center center;' +
					'background-repeat: no-repeat;' +
					'border: 1px solid #a9a9a9;' +
					'width: 80px;' +
					'height: 80px;' +
				'}'
				);
			
			if ( editor.addMenuItems )
			{
				editor.addMenuGroup('column');
				editor.addMenuItems(
					{
						column :
						{
							label : '字段属性',
							command : 'column',
							group : 'column'
						}
					});
			}

			if ( editor.contextMenu )
			{
				editor.contextMenu.addListener( function( element, selection )
					{
						if ( /^[ms]:/.test(element.getNameAtt()) ) {
							return { column : CKEDITOR.TRISTATE_OFF };
						}
					});
			}
			editor.on( 'doubleclick', function( evt )
			{
				var element = evt.data.element;

				if ( /^[ms]:/.test(element.getNameAtt()) )
					evt.data.dialog = 'column';
			});
		}
	});
})();

