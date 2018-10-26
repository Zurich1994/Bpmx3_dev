if (typeof OutMailDef == 'undefined') {
	OutMailDef = {};
}
var editor;
OutMailDef.getEditor=function(){
	editor = CKEDITOR.replace(
			'content',
			{
				height : $('body').height()-345,
				extraPlugins : 'tableresize',
				toolbar : [ ['Maximize'],[ 'Source', '-', 'NewPage', 'CustPreview' ], [ 'Undo', 'Redo' ],
						[ 'Bold', 'Italic', 'Underline', 'Strike' ], [ 'Table' ],
						[ 'TextColor', 'BGColor' ],
						[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ], [ 'FontSize','Styles' ]
				]
			});
};
