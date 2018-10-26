/**
 * 加上此文件后，当前页面中所有的iframe的高度等于其加载的内容的高度，这样可以去掉iframe中的滚动条
 */
$(function(){
	$.each($('iframe'),function(){
		$(this).bind('load',function(){
			if(document.all){
				$(this).height(this.document.body.scrollHeight);
			}else{
				$(this).height(this.contentDocument.body.offsetHeight);
			}
		});
	});
});