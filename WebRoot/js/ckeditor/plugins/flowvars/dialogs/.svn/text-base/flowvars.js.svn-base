CKEDITOR.dialog.add( 'flowvars', function( editor )
{
	
	var css=""+
	'<style type="text/css">' +
		'.flowvars-container{'+
			'width: 400px;'+
			'height: 400px;'+
			'border: solid 1px #817B7B;'+
			'overflow: auto;'+
			'padding:2px;'+
		'}'+
		'table.list{'+
			'width: 396px;'+
		'}'+
		'table.list tr:hover {'+
			'background-color: #FFFDD2;'+
		'}'+
	'}'+
	'</style>';
	var html="";
	var defId=$("#defId").val();
	
	var url=__ctx+"/platform/bpm/taskReminder/getFlowVars.ht?defId="+defId;
	$.ajax({
		  url: url,
		  async:false,
		  success:function(data){
			  var flowVars=data.flowVars;
			  html="<div class='flowvars-container'>"+
				'<table id="flowvar-table" class="table-grid list">'+
					'<tr>'+
						'<th>选择</th>'+
						'<th>变量名</th>'+
						'<th>注释</th>'+
						'<th>类型</th>'+
					'</tr>';
				for(var i=0;i<flowVars.length;i++){
					var css=i%2==1?"odd":"even";
					html+='<tr class="'+css+'" onclick="selectTr(this)">'+
						'<td> <input name="fieldName" class="pk" type="radio" value="'+flowVars[i].fieldName+'"/></td>'+
						'<td>'+flowVars[i].fieldName+'</td>'+
						'<td>'+flowVars[i].fieldDesc+'</td>'+
						'<td>'+flowVars[i].fieldType+'</td>'+
						'</tr>';
				}
				html+='</table></div>';
			  }
	});
	html=css+html;
	return {
		title : '插入流程变量',
		minWidth : 410,
		minHeight : 410,
		contents : [
			{
				id : 'flowvars',
				label : '',
				title : '',
				expand : true,
				padding : 0,
				elements :
				[
					{
						id : 'flowvars-list',
						type : 'html',
						html :html
					}
				]
			}
		],
		onOk : function(){
			// "this" is now a CKEDITOR.dialog object.
			var document = this.getElement().getDocument();
			// document = CKEDITOR.dom.document
			var doc = document.$;
			var fieldName=$(doc).find("input[name='fieldName']:[type='radio']:checked").val();
			if(!fieldName){
				return false;
			}
            editor.insertHtml("<span>${"+fieldName+"}</span>");
		},
		buttons : [CKEDITOR.dialog.okButton, CKEDITOR.dialog.cancelButton ]
	};
} );

function selectTr(obj){
	$(obj).find("input[type='radio']").attr("checked","checked");
};