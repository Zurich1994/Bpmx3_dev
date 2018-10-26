(function()
{
	CKEDITOR.plugins.add( 'opinion',
	{
		
		init : function( editor )
		{
			CKEDITOR.dialog.add( 'opinion', 'custom/opinion/dialogs/opinion.js' );
			editor.addCommand( 'opinion', new CKEDITOR.dialogCommand( 'opinion' ) );
			editor.ui.addButton( 'opinion',
					{
						label : '添加意见',
						icon: 'custom/opinion/message.png',
						command : 'opinion'
					});

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
				editor.addMenuGroup('opinion');
				editor.addMenuItems(
					{
						opinion :
						{
							label : '意见属性',
							command : 'opinion',
							group : 'opinion'
						}
					});
			}

			if ( editor.contextMenu )
			{
				editor.contextMenu.addListener( function( element, selection )
					{
						if ( /^opinion:/.test(element.getNameAtt()) ) {
							return { opinion : CKEDITOR.TRISTATE_OFF };
						}
					});
			}
			editor.on( 'doubleclick', function( evt )
			{
				var element = evt.data.element;

				if ( /^opinion:/.test(element.getNameAtt()) )
					evt.data.dialog = 'opinion';
			});
		}
	});
})();

