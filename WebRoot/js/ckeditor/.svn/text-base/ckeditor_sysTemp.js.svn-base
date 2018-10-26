ckeditor = function(textareaid, ctx) {
	CKEDITOR.context = ctx;
	// 系统模板编辑界面编辑器
	var editor = CKEDITOR.replace(
					textareaid,
					{
						skin : 'office2003',
						height : 100,
						toolbarCanCollapse : false,
						toolbarGroupCycling : true,
						extraPlugins : "jumpurl,readperson,sendperson,taskName,htmlDefForm,textDefForm",

						toolbar : [
								{
									name : 'basicstyles',
									items : [ 'Source', 'Bold', 'Italic',
											'Underline', 'Strike' ]
								}, {
									name : 'styles',
									items : [ 'Font', 'FontSize' ]
								}, {
									name : 'colors',
									items : [ 'TextColor', 'BGColor','sendperson','readperson','jumpurl','taskName','htmlDefForm','textDefForm']
								}
						]
					});
	return editor;
};
