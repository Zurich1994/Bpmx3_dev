if (typeof FormMath == 'undefined') {
	FormMath = {};
}

/****************数学统计的扩展方法********************/
FormMath.toNumber = function(x){
	if(x === null || x === undefined ||  x === '')
		return '';
	if(typeof x == "string"){
		x = x.replace(/,/g, "");
	}
	var match = x.toString().match(/([$|￥])\d+\.?\d*/);
	if(match){
		x = x.replace(match[1],'');
	}
	return Number(x);
};

/**
 * 返回x的绝对值
 * @param  {[type]} x [description]
 * @return {[type]}   [description]
 */
FormMath.abs = function(x){
    return Math.abs(x);
}

/**
 * 把x四舍五入为最接近的整数
 * @param  {[type]} x [description]
 * @return {[type]}   [description]
 */
FormMath.round = function(x){
	return Math.round(x);
}

/**
 * 对x进行上舍入，返回等于或者大于x,并且与x最接近的整数
 * @param  {[type]} x [description]
 * @return {[type]}   [description]
 */
FormMath.ceil = function(x){
	return Math.ceil(x);
}

/**
 * 对x进行下舍入，返回小于或者等于x，并且与x最接近的整数
 * @param  {[type]} x [description]
 * @return {[type]}   [description]
 */
FormMath.floor = function(x){
	return Math.floor(x);
}

/**
 * 返回集合ary中最大的数
 * @param  {[type]} ary [description]
 * @return {[type]}     [description]
 */
FormMath.max = function(ary){
	var tmp,
		x = 0,
		size = ary.length;
	for(var i=0;i<size;i++){
		x = FormMath.toNumber(ary[i]);
		if(isNaN(x))continue;
		if(tmp===undefined){
			tmp = x;
		}
		else{
			if(x>tmp)
				tmp = x;	
		}
	}
	tmp = FormMath.toNumber(tmp);
	return tmp;
}

/**
 * 返回集合ary中最小的数
 * @param  {[type]} ary [description]
 * @return {[type]}     [description]
 */
FormMath.min = function(ary){
	var tmp,
		x = 0,
		size = ary.length;
	for(var i=0;i<size;i++){
		x = FormMath.toNumber(ary[i]);
		if(isNaN(x))continue;
		if(tmp===undefined){
			tmp = x;
		}
		else{
			if(x<tmp)
				tmp = x;	
		}
	}
	tmp = FormMath.toNumber(tmp);
	return tmp;
}

/**
 * 返回x的平方根
 * @param  {[type]} x [description]
 * @return {[type]}   [description]
 */
FormMath.sqrt = function(x){
	return Math.sqrt(x);
}

/**
 * 获取ary的平均值
 * @param  {[type]} ary [description]
 * @return {[type]}     [description]
 */
FormMath.average = function(ary){
	var tmp,
		x = 0,
		size = ary.length;
	for(var i=0;i<size;i++){
		x = FormMath.toNumber(ary[i]);
		if(isNaN(x))continue;
		if(tmp===undefined){
			tmp = x;
		}
		else{
			tmp += x;
		}
	}
	tmp = FormMath.toNumber(tmp/size);
	return tmp;
};

/**
 * 求ary的和
 * @param  {[type]} ary [description]
 * @return {[type]}     [description]
 */
FormMath.sum = function(ary){
	var tmp,
		x = 0,
		size = ary.length;
	for(var i=0;i<size;i++){
		x = FormMath.toNumber(ary[i]);
		if(isNaN(x))continue;
		if(tmp===undefined){
			tmp = x;
		}
		else{
			tmp += x;
		}
	}
	tmp = FormMath.toNumber(tmp);
	return tmp;
};

/**
 * 返回保留小数点后b位的x的四舍五入值
 * @param  {[type]} x [description]
 * @param  {[type]} b [description]
 * @return {[type]}   [description]
 */
FormMath.tofixed = function(x,b){
	var tmp = FormMath.toNumber(x);
	b = FormMath.toNumber(b);
	if(isNaN(tmp)||isNaN(b))return x;
	return tmp.toFixed(b);
};

/**
 * 将数字转换为人民币大写
 * @param  {[type]} x [description]
 * @return {[type]}   [description]
 */
FormMath.convertCurrency = function(x){
	var tmp = FormMath.toNumber(x);
	if(isNaN(tmp))return x;
	return $.convertCurrency(tmp);
};


/****************数学统计的逻辑代码********************/
FormMath.doMath = function(){
	var valueRange="main";//这个值的范围，main代表主表,其他代表子表的名称
	var expRange="main";//运算符的范围，main代表主表,其他代表子表的名称
	var name = $(this).attr("name");
	if($(this).closest('div[tablename]').length>0){
		valueRange=$(this).closest('div[tablename]').attr('tablename');
	}
	
	
	// console.info("当前值改变的字段是："+name);
	$("input[funcexp]").each(function(){
		var me = $(this);
		
		if(me.closest('div[tablename]').length>0){
			expRange=$(this).closest('div[tablename]').attr('tablename');
		}
		
		curName = me.attr("name");
		exp = me.attr("funcexp");
		// console.info("当前检验的表达式是："+exp);
		if(curName&&curName==name)return true;

		if(exp.indexOf(name)==-1||valueRange!=expRange)//值跟exp的范围不一样就直接return
			return true;
		var value = FormMath.replaceSingleValue(exp,me);
		value = FormMath.replaceMultiValue(value);
		try{
			//计算表达式进行运算
			value = eval("("+value+")");
		}
		catch(e){
			return true;
		}
		if(/^(Infinity|NaN)$/i.test(value))return true;
		
		
		if(/^[0-9]*$/.test(value)){
			value = Number(value);
			//按字段的小数点设置处理小数点的问题（四舍五入）
			var jsonValidate = me.attr("validate"); 
			if(jsonValidate.length>0){
				var jsonObj = eval("("+jsonValidate+")");
				if(jsonObj.maxDecimalLen>=0){
					value = value.toFixed(jsonObj.maxDecimalLen);
				}
			}
		}
		
		me.val(value).trigger("change");
	});
};

FormMath.replaceSingleValue = function(exp,t){
	if(!exp)return 0;
	var reg = /\{.*?\((\w+)\)\}/g;//名称格式，就是name='fieldname'的格式，不再是name='m:tablename:fieldname'
	exp = exp.replace(reg,function(){
		var name = arguments[1],
			value = 0;
		var	object;
		//子表
		if(/^s\:.+$/.test(name)){
			var pTr = $(t).closest("[formtype]");
			object = $("[name='"+name+"']",pTr);
		}
		else{
			object = $("table.formTable [name='"+name+"']");
		}

		if(object){
			var val = object.val();
			val = FormMath.toNumber(val);
			if(!isNaN(val)&&val!="")
				value = val;
		}
		return value;
	});
	return exp;
};

FormMath.repMultiFn = function(){
	var name = arguments[1];
	var value = [],
		object = $("[name='"+name+"']");

	$("[name='"+name+"']").each(function(){
		var me = $(this);
		if(me){
			var val=FormMath.toNumber(me.val());
			if(!val=='')
			value.push(val);
		}
	});
	value = '[' + value.join(',') + ']'; 
	return value;
};

FormMath.replaceMultiValue = function(exp){
	if(!exp)return 0;
	var reg = /\[.*?\((\w+\:\w+\:\w+)\)\]/g;
	exp = exp.replace(reg,FormMath.repMultiFn);
	return exp;
};