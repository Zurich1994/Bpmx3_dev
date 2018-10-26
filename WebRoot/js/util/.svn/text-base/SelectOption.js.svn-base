/* SelectOption对象 */
//构造函数
var SelectOptionHelper = function() {}
//属性及函数
SelectOptionHelper.prototype =
{
	/* private function */
	//moveUp和moveDown方法中使用
	swapOptionProperties:function(option1, option2)
	{
		var tempStr = option1.value;
		option1.value = option2.value;
		option2.value = tempStr;
		tempStr = option1.text;
		option1.text = option2.text;
		option2.text = tempStr;
		tempStr = option1.selected;
		option1.selected = option2.selected;
		option2.selected = tempStr;
	},
	//move和moveAll方法中使用
	resetAutoWidth:function(obj)
	{
		try
		{
			var tempWidth = obj.style.getExpression("width");
			if(tempWidth != null)
			{
				obj.style.width = "auto";
				obj.style.setExpression("width", tempWidth);
				obj.style.width = null;
			}
		}
		catch(e)
		{
		}
	},
	/* public function */
	//添加一个项
	add:function(toObj, objText, objValue)
	{
		toObj.options[toObj.options.length] = new Option(objText, objValue);
	},
	//移动选中的项到目标中
	move:function(fromObj, toObj)
	{
		var fromObjOptions = fromObj.options;
		for( var i = 0;i < fromObjOptions.length;i++)
		{
			if(fromObjOptions[i].selected)
			{
				toObj.appendChild(fromObjOptions[i]);
				i--;
			}
		}
		this.resetAutoWidth(fromObj);
		this.resetAutoWidth(toObj);
	},
	//移动所有的项到目标中
	moveAll:function(fromObj, toObj)
	{
		var fromObjOptions = fromObj.options;
		if(fromObjOptions.length > 1000)
		{
			//if(!confirm("Are you sure to move options?")) return false;
		}
		for( var i = 0;i < fromObjOptions.length;i++)
		{
			fromObjOptions[0].selected = true;
			toObj.appendChild(fromObjOptions[i]);
			i--;
		}
		this.resetAutoWidth(fromObj);
		this.resetAutoWidth(toObj);
	},
	//移除相应列表的所选项目
	removeSelectOptions:function(obj)
	{
		if(obj.selectedIndex == -1)
		{
			alert("未选择任何选项!");
		}
		for( var i = obj.length - 1;i >= 0;i--)
		{
			if(obj.options[i].selected)
			{
				obj.remove(obj.selectedIndex);
				//obj.appendChild(obj.options[i]);//把当前选中的选项移到最后面
				//obj.options.length = obj.options.length - 1;//把移到最后面的选项移去
			}
		}
	},
	//移除相应列表中的所有项目
	removeAllOptions:function(obj)
	{
		obj.options.length = 0;
	},
	//添加左边列表中所选项目到右边列表中
	addSelectOptions:function(objSource, objDestination)
	{
		if(objSource.selectedIndex == -1)
		{
			alert("未选择任何选项!");
		}
		var sourceLen = objSource.options.length;
		for( var i = 0;i < sourceLen;i++)
		{
			if(objSource.options[i].selected)
			{
				var optionValue = objSource.options[i].value;
				if(!this.existOptionByValue(optionValue, objDestination))
				{
					var optOption = new Option(objSource.options[i].text, objSource.options[i].value);
					objDestination.options[objDestination.options.length] = optOption;
				}
			}
		}
	},
	//添加左边列表中的全部项目到右边列表
	addAllOptions:function(objSource, objDes)
	{
		if(objSource.options.length == 0)
		{
			alert("源列表中无选项可添加!");
			return;
		}
		//清空目标列表
		objDes.options.length = 0;
		//添加
		for( var i = 0;i < objSource.options.length;i++)
		{
			var optOption = new Option(objSource.options[i].text, objSource.options[i].value);
			objDes.options[objDes.options.length] = optOption;
		}
	},
	//判断相应的选项是否在列表中存在
	existOptionByValue:function(optionValue, obj)
	{
		for( var i = 0;i < obj.options.length;i++)
		{
			if(optionValue == obj.options[i].value)
				return true;
		}
		return false;
	},
	//选中相应列表中的全部项
	selectAll:function(selectObj)
	{
		var theObjOptions = selectObj.options;
		for( var i = 0;i < theObjOptions.length;i++)
		{
			theObjOptions[i].selected = true;
		}
	},
	//取消选中相应列表中的全部项
	unSelectAll:function(selectObj)
	{
		var theObjOptions = selectObj.options;
		for( var i = 0;i < theObjOptions.length;i++)
		{
			theObjOptions[i].selected = false;
		}
	},
	//将选中的项目向上移动若干格
	moveUp:function(selectObj, count)
	{
		var theObjOptions = selectObj.options;
		for( var c = 0;c < count;c++)
		{
			for( var i = 1;i < theObjOptions.length;i++)
			{
				if(theObjOptions[i].selected && !theObjOptions[i - 1].selected)
				{
					this.swapOptionProperties(theObjOptions[i], theObjOptions[i - 1]);
				}
			}
		}
	},
	//将选中的项目向下移动若干格
	moveDown:function(selectObj, count)
	{
		var theObjOptions = selectObj.options;
		for( var c = 0;c < count;c++)
		{
			for( var i = theObjOptions.length - 2;i > -1;i--)
			{
				if(theObjOptions[i].selected && !theObjOptions[i + 1].selected)
				{
					this.swapOptionProperties(theObjOptions[i], theObjOptions[i + 1]);
				}
			}
		}
	},
	//将选中的项目移至最前
	moveTop:function(selectObj)
	{
		var theObjOptions = selectObj.options;
		var oOption = null;
		for( var i = 0;i < theObjOptions.length;i++)
		{
			if(theObjOptions[i].selected && oOption)
			{
				selectObj.insertBefore(theObjOptions[i], oOption);
			}
			else if(!oOption && !theObjOptions[i].selected)
			{
				oOption = theObjOptions[i];
			}
		}
	},
	//将选中的项目移至最后
	moveBottom:function(selectObj)
	{
		var theObjOptions = selectObj.options;
		var oOption = null;
		for( var i = theObjOptions.length - 1;i > -1;i--)
		{
			if(theObjOptions[i].selected)
			{
				if(oOption)
				{
					oOption = selectObj.insertBefore(theObjOptions[i], oOption);
				}
				else
				{
					oOption = selectObj.appendChild(theObjOptions[i]);
				}
			}
		}
	}
}
var __SelectOption__ = new SelectOptionHelper();//默认生成一个对象
