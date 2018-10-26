//正则表达式
jQuery.validator.addMethod("regex",  
        function(value, element, params) {     
            var exp = new RegExp(params);     
            return exp.test(value);                     
        },
"格式错误");    

jQuery.validator.addMethod("date", function(value, element){
	var datetype = element.getAttribute('datetype');
	if(datetype=='datetime')
	{
		ereg = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\d):[0-5]?\d:[0-5]?\d$/;
	}
	else
	{
		ereg = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/;
	}
	result = (!ereg.exec(value))? false :true;

	return this.optional(element) || (result);
}, "请输入正确的日期");


//手机格式
jQuery.validator.addMethod("mobile", function(value, element) {
    var length = value.length;
    var mobile =  /^(((1[3456789][0-9]{1})|(15[0-9]{1}))+\d{8})$/
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "手机号码格式错误");   

//电话号码验证   
jQuery.validator.addMethod("phone", function(value, element) {
    var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
    return this.optional(element) || (tel.test(value));
}, "电话号码格式错误");

// 邮政编码验证   
jQuery.validator.addMethod("zipCode", function(value, element) {
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "邮政编码格式错误");

// QQ号码验证   
jQuery.validator.addMethod("qq", function(value, element) {
    var tel = /^[1-9]\d{4,9}$/;
    return this.optional(element) || (tel.test(value));
}, "qq号码格式错误");

// IP地址验证
jQuery.validator.addMethod("ip", function(value, element) {
    var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
    return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
}, "Ip地址格式错误");

// 字母和数字的验证
jQuery.validator.addMethod("chrnum", function(value, element) {
    var chrnum = /^([a-zA-Z0-9]+)$/;
    return this.optional(element) || (chrnum.test(value));
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");

// 中文的验证
jQuery.validator.addMethod("chinese", function(value, element) {
    var chinese = /^[\u4e00-\u9fa5]+$/;
    return this.optional(element) || (chinese.test(value));
}, "只能输入中文");

//一组数字之和下限
jQuery.validator.addMethod("digitsSum", function(value, element) {
	var sumRes = 0;
	var idx=0;
	$("input[name='"+element.name+"']").each(function(){
		idx++;
		$("label[for='"+element.name+idx+"']").remove();
		var obj=$(this);
		var v = obj.val();
		if(v!='')
		{
			sumRes += parseInt(obj.val());
		}
	});
    var result = sumRes<=100?true:false;
    return this.optional(element) || (result);
}, "它们之和必需小于等于100");
