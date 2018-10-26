Namespace.register("com.hotent.platform.system");
/**
 * 表达式计算。
 * var con=com.hotent.platform.system.ConditionExpression();
 * 计算节点
 * con.evaluate(node);
 * 获取结果
 * con.getResult();
 * 清除计算
 * con.clear();
 * @returns {com.hotent.platform.system.ConditionExpression}
 */
com.hotent.platform.system.ConditionExpression = function(){
	{
		this.sb=new StringBuffer();
		this.hasError=false;
		this.index=0;
	}
	/**
	 * 清除重新计算
	 */
	this.reset=function(){
		this.sb=new StringBuffer();
		this.hasError=false;
		window.status="";
	};
	
	
	
	
	/**
	 * 获取结果
	 */
	this.getResult=function(){
		if(this.hasError){
			return "当前构建条件不符合规则,请仔细检查表达式!";
		}
		return this.sb.toString();
	};
	
//	this.getNextNode=function(node){
//		node.hasNextNode=false;
//		var nextNode=node.getNextNode();
//		if(!nextNode){
//			return;
//		}
//		if(nextNode.type=="3"){
//			node.hasNextNode= true;
//			return;
//		}
//		this.recurNode(node,nextNode);
//		
//	};
//	
//	
//	
//	this.recurNode=function(root,node){
//		var childs=node.childs;
//		if(!childs) return;
//		 
//		for(var i=0;i<childs.length;i++){
//			var tNode=childs[i];
//			if(tNode.type=="3"){
//				root.hasNextNode=true;
//				break;
//			}
//			else{
//				if(tNode.childs){
//					this.recurNode(root,tNode);
//				}
//			}
//		}
//	};
	
	
	
	this.validNode=function(node){
		
		var type=node.type;
		var childs=node.children;
		//当前节点的类型
		var parentNode=node.getParentNode();
		var preNode=node.getPreNode();
		var nextNode=node.getNextNode();
		
		//当前节点为条件表达式节点
		if(type=="3" ){
			if(parentNode){
				return;
			}
			//有下一个节点
			if(nextNode){
				//当前节点为条件节点，下一个节点是条件节点
				if(nextNode.type=="3"){
					this.hasError=true;
					window.status=("type 3 nextNode.type=='3'");
					return;
				}
				//下一个节点为条件节点
				else{
					//条件节点有子节点
					if(nextNode.children && nextNode.children.length>0)
					{
						window.status=("type 3 nextNode.children");
						this.hasError=true;
						return;
					}
				}
			}
			//有前一个节点
			if(preNode){
				if(preNode.type=="3"){
					this.hasError=true;
					window.status=("type 3 preNode.type=='3'");
					return;
				}
				//下一个节点为条件节点
				else{
					//条件节点有子节点
					if(preNode.children && preNode.children.length>0)
					{
						this.hasError=true;
						window.status=("type 3 preNode.children");
						return;
					}
				}
			}
		}
		//条件节点，有子节点
		else if(type!="3" &&  childs && childs.length>0){
			if(parentNode){
				return;
			}
			//有下一个节点
			if(nextNode){
				//当前节点为条件节点，下一个节点是条件表达式节点
				if(nextNode.type=="3"){
					this.hasError=true;
					window.status=("条件节点，有子节点  nextNode.type=='3'" +node.name +"," +node.id);
					return;
				}
				//下一个节点为条件节点
				else{
					//条件节点有子节点
					if(nextNode.children && nextNode.children.length>0)
					{
						window.status=(node.id +"," + node.name + "条件节点，有子节点  nextNode.children");
						this.hasError=true;
						return;
					}
				}
			}
			//有前一个节点
			if(preNode){
				if(preNode.type=="3"){
					this.hasError=true;
					window.status=("条件节点，有子节点  preNode.type=='3'");
					return;
				}
				//下一个节点为条件节点
				else{
					//条件节点有子节点
					if(preNode.children && preNode.children.length>0)
					{
						window.status=("条件节点，有子节点  preNode.children");
						this.hasError=true;
						return;
					}
				}
			}
		}
		//条件节点 ，当前节点无子节点
		else 
		{
			//没有下面的节点
			if(nextNode==null){
				window.status=("条件节点 ，当前节点无子节点 没有下面的节点," + childs + "," +node.name +","+ node.id);
				this.hasError=true;
				return;
			}
			//有下面的节点
			else{
				//没有子节点说明是一个条件节点
				if(nextNode.children==null && nextNode.type!='3'){
					window.status=("条件节点 ，nextNode.type!='3' childs:" + childs +"," + node.type +"," + node.name +"," + nextNode.type+","+nextNode.id);
					this.hasError=true;
					return;
				}
			}
			//没有前一个节点
			if(preNode==null){
				window.status=("条件节点 ，preNode==null" +node.id +"," +node.name);
				this.hasError=true;
				return;
			}
			//前面有的节点
			else{
				//没有子节点说明是一个条件节点
				if(preNode.children==null  && preNode.type!='3'){
					window.status=("条件节点 ，preNode.childs==null");
					this.hasError=true;
					return;
				}
			}
		}
	};
	
	
	/**
	 * 构建条件表达式。
	 */
	this.evaluate=function(ex){
		this.validNode(ex);
		var childLen=0;
		if(ex.children) 
			childLen=ex.children.length;
		if(ex.type=="3"){
			this.sb.append( " "+ ex.expression +" ");
		}
		else if((ex.type=="1"|| ex.type=="2") && childLen==0)
		{
//			this.getNextNode(ex);
//			var rtn=ex.hasNextNode;
//			if(rtn)
				this.sb.append(" "+ex.typeName+" ");	
		}
		else if((ex.type=="1" || ex.type=="2") && childLen>0)
		{
			this.sb.append("(");
			var childList=ex.children;
			var len=childList.length;
			
			for(var i=0;i<len;i++){
				var  child=childList[i];
				this.validNode(child);
				var tmpLen=0;
				if(child.children) 
					tmpLen=child.children.length;
				if(child.type!="3"){
					if((ex.type=="1" || ex.type=="2") && tmpLen==0){
						if(i>0 && i<len-1)
							this.sb.append(" " +child.typeName +" ");	
					}
					else{
						this.sb.append(" " +ex.typeName +" ");
						//递归
						this.evaluate(child);
					}
				}
				else{
					if(i>0 && i<len)
						this.sb.append(" " + ex.typeName +" " +" "+  child.expression +" " );
					else
						this.sb.append(" "+ child.expression +" " );
				}
			}
			
			this.sb.append(")");
		}
		
	};
	/**
	 * 生成节点。
	 */
	this.genNode=function(type,expression){
		this.index++;
	
		var ctx=__ctx;
		var type=$("input[name=action]:checked").val();
		var txt=expression;
		var node=new Object();
		node.expression="";
		node.id=this.index;
		var typeName="";
		//1 或2 并 3，条件
		switch(type){
			case "1":
				node.name="OR(或)";
				typeName="Or";
				node.icon=ctx+ "/themes/img/commons/or.gif";
				node.image="/themes/img/commons/or.gif";
				break;
			case "2":
				node.name="AND(和)";
				typeName="And";
				node.icon=ctx+ "/themes/img/commons/and.gif";
				node.image="/themes/img/commons/and.gif";
				break;
			case "3":
				node.name=txt;
				node.icon=ctx+ "/themes/img/commons/code.gif";
				node.expression=txt;
				node.image="/themes/img/commons/code.gif";
				break;
		}
		node.type=type;
		node.typeName=typeName;

		return node;
	};
};