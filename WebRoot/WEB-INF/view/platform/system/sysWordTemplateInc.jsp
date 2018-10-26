<%@page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
	var previewWin;
	var alias = '';
	function preview(alias_){
		if(alias_) alias = alias_;
		var obj=$("#preview_key_div");
		previewWin= $.ligerDialog.open({ target:obj , height: 230,width:600, modal :true}); 
		previewWin.show();
		$(".l-dialog-frame").css("z-index","9000");
		return false;
	}
	function doPreview(){
		var valid = $("#pkForm").form().valid();
		if(!valid) return false;
		var pk = $('#pk').val();
		if(!alias) alias = '${sysWordTemplate.alias}';
		var previewUrl = 'preview_'+alias+'.ht?pk='+pk;
		$.openFullWindow(previewUrl.getNewUrl());
		previewWin.hide();
	}
</script>
<div id="preview_key_div" class="hidden" style="font-size:13px;">
	<div class="panel-toolbar">
		<div class="toolBar">
			<div class="group">
				<a class="link save" href="javascript:;" onclick="doPreview();"><span></span>确定</a>
			</div>
		</div>
	</div>
	<form id="pkForm">
	<table id="callCodeTable" class="table-grid table-list">
		<tr>
			<th>数据预览主键：</th>
			<td style="text-align: left;">
				<input type="text" id="pk" value="" validate="{required:true}">
			</td>
		</tr>
	</table>
	</form>
</div>

