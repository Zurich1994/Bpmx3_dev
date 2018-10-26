/**
 * 关联关系操作。
 * @returns {com.hotent.platform.form.Relation}
 */
Namespace.register("com.hotent.platform.form");  

com.hotent.platform.form.Relation=function(){
	
	this.relations=new Array();
	this.pk="";
	
	/**
	* 添加关联关系。
	* 返回
	* 1，表示表关联关系已经添加。
	* 2，表示主键换了
	* 0，添加关系成功
	*/
	this.addRelation=function(pk,tableName,fk){
		var obj=new Object();
		obj.tableName=tableName;
		obj.fk=fk;
		var rtn=this.isExists(tableName);
		if(rtn) return 1;
		if(this.pk=="" || this.pk==pk){
			this.pk=pk;
			this.relations.push(obj);
			return 0;
		}
		return 2;
	};
	/**
	 * 判断表是否存在。
	 */
	this.isExists=function(tableName){
		for(var i=0;i<this.relations.length;i++){
			var obj=this.relations[i];
			if(obj.tableName==tableName)
				return true;
		}
		return false;
	};
	/**
	 * 移除表
	 */
	this.remove=function(tableName){
		for(var i=0;i<this.relations.length;i++){
			var obj=this.relations[i];
			if(obj.tableName==tableName){
				this.relations.splice(i,1);
			}
		}
		if(this.relations.length==0){
			this.pk="";
		}
	};
	/**
	 * 取得关联关系。
	 */
	this.getRelation=function(){
		var len=this.relations.length;
		if(len==0) return "";
		var sb=new StringBuffer();
		sb.append("<relation pk='"+this.pk+"'>");
		for(var i=0;i<len;i++){
			var tmp=this.relations[i];
			sb.append("<table name='"+tmp.tableName+"'  fk='"+tmp.fk+"' />");
		}
		sb.append("</relation>");
		return sb.toString();
	};
};