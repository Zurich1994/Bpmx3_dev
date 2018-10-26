/**
 * 分类下拉框。
 * 1.catKey:
 * 		对应SYS_TYPE_KEY 表中的typeKey字段。
 * 2.catComBo：
 * 		分类的key值。	
 * 3.valueField
 *  	分类对应的字段（为隐藏字段)。
 * 4.isMultiSelect
 * 		是否允许多选。
 * 5.value
 * 		分类名称。
 * 6.typeId：分类id。
 * 7.treeLeafOnly
 * 		如果是树形下拉框，只选择叶子节点。
 * 8.height 下拉框的高度。
 * 9.typeName:  分类名
 * <input class="catComBo" catKey="xueli" valueField=""    name="BoxMultiName" isMultiSelect="true" width="200" typeId='分类值' value="博士"/>
 */
$(function() {
	var ctx = __ctx;
	/**
	 * 读取配置信息。
	 */
	function readCatProp(dicCombo) {
		//读取配置信息
		var prop = {
			//分类对应的分类key。
			typeName:$(dicCombo).attr("typeName"),
			width : $(dicCombo).attr("width"),
			height : $(dicCombo).attr("height"),
			isMultiSelect : $(dicCombo).attr("isMultiSelect"),
			treeLeafOnly : $(dicCombo).attr("treeLeafOnly"),
			value : $(dicCombo).attr("value"),
			catValue:$(dicCombo).attr("catValue"),
			name : $(dicCombo).attr("name"),
			valueField:$(dicCombo).attr("valueField"),
			isNodeKey : $(dicCombo).attr("isNodeKey"),
			onSelected:$(dicCombo).attr("onSelected")
		};
		//宽度
		if (isValueNull(prop.width)) {
			prop.width = $(dicCombo).width();
		}
		//高度
		if (isValueNull(prop.height)){
			prop.height = 150;
		}
	
		//是否多选
		if (isValueNull(prop.isMultiSelect)){
			prop.isMultiSelect = false;
		}
		//树形多选的配置。
		if (prop.isMultiSelect) {
			prop.check = {
				enable : true,
				chkboxType : {"Y" : "s","N" : "s"}
			};
		}
		//页节点选中
		if (isValueNull(prop.treeLeafOnly)){
			prop.treeLeafOnly = true;
		}
		
		return prop;
	}
	
	//判断是否为空。
	function isValueNull(obj){
		if(obj == 'undefined' || obj == null || obj == '')
			return true;
		return false;
	}
	//形成树。
	function processCat(catComBo, prop) {
		//值
		var catValue=prop.catValue;
		//var typeName=prop.typeName;
		if(catValue=="0") catValue="";
			//没有这个可以选择tree。
			var nameKey="typeId";
			if(!isValueNull(prop.isNodeKey)){
				nameKey="nodeKey";
			}
			var str = JSON.stringify(rtnData);
			$.each(rtnData, function(i, d) {
				if(!isValueNull(prop.isNodeKey)){
					d.id = d.nodeKey;
				}
				else{
					d.id=d.typeId;
				}
				d.text = d.typeName;
			});
			// 树形
			var comboBox=$(catComBo).ligerComboBox({
				valueFieldID:prop.valueField,
				width : prop.width,
				treeLeafOnly : prop.treeLeafOnly,
				tree : {
					nameKey:nameKey,
					data : {
							simpleData : {
								enable: true,
								idKey: 'domId',
								pIdKey : "parentId"
							},
							key : {name : "typeName",id:"typeName"},
							data : rtnData
					},
					selectValue:catValue,
					view : { selectedMulti : prop.isMultiSelect},
					check : prop.check
				},
				selectBoxWidth : prop.width,
				selectBoxHeight : prop.height,
				onSelected:function(newval){
					if(this._toggleSelectBox){
						this._toggleSelectBox(true);
					}
					else if(comboBox && comboBox._toggleSelectBox){
						comboBox._toggleSelectBox(true);
					}
					if(prop.onSelected){
						eval(prop.onSelected+'.call(this,newval)');
					}
				}
			});
			comboBox.ztree.expandAll(true);
			
			//判断是否有值字段
			if(!isValueNull(prop.valueField)){
				var hidCatField=$("#" + prop.valueField);
				//设置隐藏域的分类id
				if(hidCatField.length>0){
					hidCatField.val(catValue);
				}
			}
	}
	// htCatCombo
	$.fn.htCatCombo = function(option) {
		$(this).each(function() {
			var prop = readCatProp(this);
			processCat(this, prop);
		});
	};
	
	function initCatComboBox(){
		$('.catComBo').each(function() {
			$(this).htCatCombo();
		});
	}
	
	$.extend({initCatComboBox:initCatComboBox});
	
	$.initCatComboBox();
	
});
