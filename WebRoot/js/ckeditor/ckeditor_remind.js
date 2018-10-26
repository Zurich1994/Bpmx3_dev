ckeditor = function(textareaid, ctx) {
	CKEDITOR.context = ctx;
	// 构造对像
	var editor = CKEDITOR.replace(
					textareaid,
					{
						skin : 'office2003',
						height : 160,
						toolbarCanCollapse : false,
						toolbarGroupCycling : true,
						extraPlugins : "subject,startUser,startDate,startTime,businessKey,vars,saveRule,jumpurl,readperson,sendperson,remaintime,taskName,flowvars",

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
									items : [ 'TextColor', 'BGColor','sendperson','readperson','jumpurl' ,'remaintime','taskName','flowvars']
								}

						]

					});
	return editor;
};
