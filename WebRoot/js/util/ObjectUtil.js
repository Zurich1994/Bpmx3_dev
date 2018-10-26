/**
 * 定义一个基础的类对象，用于判定类是否相等。
 * 使用方法:
 * var obj=new BaseObject(1);
 * var obj2=new BaseObject(1);
 * alert(obj.equals(obj2));
 * @returns {BaseObject}
 */
function BaseObject()
{
	var id=arguments[0];
	this.id="";
	if(id!=undefined)
		this.id=id;
}

/**
 * 给BaseObject扩展一个equals方法。
 * @param obj
 * @returns {Boolean}
 */
BaseObject.prototype.equals=function(obj){
	return this.id==obj.id;	
}

/**
 * 扩展一个类继承BaseObject。
 * 增加：title和url两个属性。
 */
function DataRow(){
	this.title="";
	this.url="";
	var id=arguments[0];
	//调用构造函数
	if(id!=undefined)
		BaseObject.call(this , id);   
		
	var title=arguments[1];
	var url=arguments[2];
	
	if(title!=undefined)
		this.title=title;
	if(url!=undefined)
		this.url=url;
	
}
//建一个积累的对象作为子类原型的原型（原型继承）
//DataRow继承BaseObject  
DataRow.prototype=new BaseObject(); 

/**
 * 创建一个DataRowHelper类。
 * 定义:
 * add,remove,length,each四个方法。
 * @returns
 */
var DataRowHelper= function(){
	this.RowCollection=new Array();
};
//添加
DataRowHelper.prototype.add=function(row){
	var isExist=false;
	for(var i=0;i<this.RowCollection.length;i++)
	{
		var obj=this.RowCollection[i];
		if(row.equals(obj))
		{
			isExist=true;
		}
		
	}	
	if(!isExist)
			this.RowCollection.push(row);
}
//删除
DataRowHelper.prototype.remove=function(row){
	for(var i=this.RowCollection.length-1;i>=0;i--)
	{
		var obj=this.RowCollection[i];
		if(row.equals(obj))
		{
			this.RowCollection.splice(i,1);
			break;
		}
	}	
}

DataRowHelper.prototype.length=function(){
	return this.RowCollection.length;
}
//对象遍历
DataRowHelper.prototype.each=function(func){
	var len = this.RowCollection.length;
	for(var i=0;i<len;i++){
			func(this.RowCollection[i],i);
	}
}

/**
 * 测试用例。
 */
function test()
{
	var obj1=new DataRow(1,"aa","aaa");
	
	var obj2=new DataRow(3,"aasss","aaa");
	
	var obj3=new DataRow(3,"aasss","aaa");
	
	var h=new DataRowHelper();
	
	h.add(obj1);
	
	h.add(obj2);
	
	h.add(obj3);
	
	//h.remove(obj3);
	
	h.each(detail);
	
	alert(h.length());
}

/**
 * 扩展Object类，判断两个对象是否相同，在自定义对话框中使用到
 */
Object.prototype.equals = function(obj) {
	if (this == obj)
		return true;
	if (typeof (obj) == "undefined" || obj == null
			|| typeof (obj) != "object")
		return false;
	var length = 0;
	var length1 = 0;
	for ( var ele in this) {
		length++;
	}
	for ( var ele in obj) {
		length1++;
	}
	if (length != length1)
		return false;
	if (obj.constructor == this.constructor) {
		for ( var ele in this) {
			if (typeof (this[ele]) == "object") {
				if (!this[ele].equals(obj[ele]))
					return false;
			} else if (typeof (this[ele]) == "function") {
				if (!this[ele].toString().equals(obj[ele].toString()))
					return false;
			} else if (this[ele] != obj[ele])
				return false;
		}
		return true;
	}
	return false;
};