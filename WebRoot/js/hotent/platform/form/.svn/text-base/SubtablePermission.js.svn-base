/**
 * 子表的权限处理
 * @returns {SubtablePermission}
 */
if (typeof SubtablePermission == 'undefined') {
	SubtablePermission = {};
}

var selectValue=function(select){
	
		var html='';
		var query=select.attr("selectquery")
		var queryJson = "";
		if(query!=undefined && query!=null){
			query=query.replaceAll("'","\"");
			queryJson = JSON2.parse(query);
		}else{
			queryJson = "";
			query = "";
		}
		var sValue=select.attr("val");
		if(sValue==undefined || sValue==null && sValue.length<=0){
			sValue = "";
		}
		html="<span selectvalue="+sValue+" selectquery="+query+"><lable></lable></span>"
		return html;
	}

/**
 * 子表必填判断
 * 
 * @return {} Boolean 如果存在必填数据则返回true，否则则返回false
 */
SubtablePermission.subRequired = function() {
	var r = true;
	var subDiv = $("div[type='subtable']");
	if( $.isEmptyObject(subDiv)||subDiv.length == 0)
		return true;
	if ($.isEmpty(subDiv) )
		return true;

	// 子表的div
	subDiv.each(function() {	
		var visible = $(this).is(":visible");
		if(!visible)return true;
		var right = $(this).attr("right");
		if ($.isEmpty(right))
			right = "r";
		else
			right = right.toLowerCase();
		// 如果必填权限
		if (right != "b")
			return true;
		var tr = $(this).find('[formtype]:visible');
		if ($.isEmpty(tr) || $.isEmptyObject(tr) || tr.length == 0) {
			r = false;
			if(!($(this).hasClass('validError'))){		//去掉必填样式							
				$(this).addClass('validError');
			}
			return false;
		}
		
		//是不是每行都要有数据
		/*
		var t = false;
		// 判断里面的值是否为空，
		tr.each(function() {
			// 判断子表数据
			t = SubtablePermission.checkSubData($(this));
			if (t)//如果存在数据 则终止循环
				return false;
		});
		if (!t) {
			r = false;
			return false;
		}		
		*/
			
		});
	
  /*	if(!r) subDiv.addClass('validError');
    else subDiv.removeClass('validError');*/
	return r;
};

/**
 * 检查子表是否存在数据
 * 
 * @param {}
 *            objRow 子表的table
 * @return {} 如果存在数据则返回true，否则则返回false
 */
SubtablePermission.checkSubData = function(objRow) {
	var t = false;
	$("input:text[name^='s:'],input[type='hidden'][name^='s:'],textarea[name^='s:'],select[name^='s:']",
			objRow).each(function() {
				var value = $(this).val();
				// 如果存在值 则终止循环
				if (!$.isEmpty(value)) {
					t = true;
					return false;
				}
			});
	// 判断复选框按钮的数据是否有值
	$('input:checkbox:checked', objRow).each(function() {
				var value = $(this).val();
				// 如果存在值 则终止循环
				if (!$.isEmpty(value)) {
					t = true;
					return false;
				}
			});
	// 判断单选按钮的数据是否有值
	$('input:radio', objRow).each(function() {
				var value = $(this).val();
				// 如果存在值 则终止循环
				if ($(this).attr("checked") != undefined) {
					t = true;
					return false;
				}
			});
	return t;
};

