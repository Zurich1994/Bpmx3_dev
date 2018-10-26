/**
 * 数据字典分类下拉框。
 * 1.nodeKey:
 * 		对应数据分类表中的字典nodekey字段。
 * 
 * 2.下拉框类型：
 * 		1.dicComboBox：
 * 			表示普通的下拉框
 * 		2.dicCombo：
 * 			可为下拉框和树形下拉框。
 * 		3.dicComboTree：
 *	 		树形下拉框。
 * 3.valueFieldID
 *  	字典对应的值。
 * 4.isMultiSelect
 * 		是否允许多选。
 * 5.value
 * 		下拉框选择的值。
 * 6.treeLeafOnly
 * 		如果是树形下拉框，只选择叶子节点。
 * 
 * 7.height 下拉框的高度。
 * <input class="dicComboBox" nodeKey="xueli"  valueFieldID="BoxMultiId" name="BoxMultiName" isMultiSelect="true" width="200" value="博士"/>
 */
$(function() {
	var ctx = __ctx;
	/**
	 * 读取配置信息。
	 */
	function readProp(dicCombo) {
	
		//读取配置信息
		var prop = {
			//字典对应的分类key。
			nodeKey : $(dicCombo).attr("nodeKey"),
			width : $(dicCombo).attr("width"),
			height : $(dicCombo).attr("height"),
			//值字段。
			valueFieldID : $(dicCombo).attr("valueFieldID"),
			//允许多选
			isMultiSelect : $(dicCombo).attr("isMultiSelect"),
			//只选择页节点
			treeLeafOnly : $(dicCombo).attr("treeLeafOnly"),
			value : $(dicCombo).attr("value"),
			name : $(dicCombo).attr("name"),
			onSelected:$(dicCombo).attr("onSelected")
		};
		//nodekey 是必须的。
		if (isObjNull(prop.nodeKey)) {
			$.ligerDialog.warn('数据字典控件，nodeKey属性不能为空!');
			return;
		}
		//宽度
		if (isObjNull(prop.width)) prop.width = isObjNull($(dicCombo).width())?150:$(dicCombo).width();
		//高度
		if (isObjNull(prop.height)) prop.height = isObjNull($(dicCombo).height())&&$(dicCombo).height()<50?100:$(dicCombo).height();
		//字段值的ID
		if (isObjNull(prop.valueFieldID)){
			//表单字段命名为 m:表名:字段名称，在提交数据的时候程序会检查表单名称为m:开头的字段，这个id是不提交的，所以替换掉。
			prop.valueFieldID = $(dicCombo).attr("name").replaceAll(":","") +"_id";
		}
		//是否多选
		if (isObjNull(prop.isMultiSelect)) prop.isMultiSelect = false;

		//树形多选的配置。
		if (prop.isMultiSelect) {
			prop.check = {
				enable : true,
				chkboxType : {"Y" : "s","N" : "s"}
			};
		}
		//页节点选中
		if (isObjNull(prop.treeLeafOnly)){
			prop.treeLeafOnly = false;
		}
		return prop;
	}
	
	/**
	 * 判断是否为空。
	 */
	function isObjNull(v, allowBlank){
		return v === null || v === undefined || (!allowBlank ? v === '' : false);
	}
	/**
	 * 从服务端加载数据字典的数据。
	 */
	function process(dicCombo, prop) {
		//防止prop为空时JS报错
		if(typeof(prop) == undefined || prop == null || prop== ''){
			return;
		}
		if(typeof(prop.nodeKey) == undefined || prop.nodeKey == null || prop.nodeKey== ''){
			return;
		}
		var url=ctx + "/platform/system/dictionary/getMapByNodeKey.ht";
		var params={nodeKey:prop.nodeKey};
		$.post(url,params,function(data){
			//取得分类类型。
			var globalType=data.globalType;
			//类型(0平铺,1树形)
			var type=globalType.type;
			var dicList=data.dicList;
			//下拉框已有的值。
			var dictValue = $(dicCombo).val();
			
			var ligerComboObj;
			
			$.each(dicList, function(i, d) {
				d.id = d.itemName;
				d.text = d.itemName;
				
			});
			
			//平铺的情况。
			if ( type == 0) {
			
				// 平铺
				ligerComboObj = $(dicCombo).ligerComboBox({
					data : dicList,
					valueFieldID : prop.valueFieldID ,
					width : prop.width,
					isMultiSelect : prop.isMultiSelect ,
					slide:false,
					isShowCheckBox :prop.isMultiSelect,
					onSelected:function(newval){
						if(this._toggleSelectBox){
							this._toggleSelectBox(true);
						}
						else if(ligerComboObj && ligerComboObj._toggleSelectBox){
							ligerComboObj._toggleSelectBox(true);
						}
						if(prop.onSelected){
							eval(prop.onSelected+'.call(this,newval)');
						}
					}
				});
				$(dicCombo).trigger("blur");
			} else if ( type == 1) {
				// 树形
				$(dicCombo).ligerComboBox({
					slide:false,
					valueFieldID : prop.valueFieldID,
					width : prop.width,
					treeLeafOnly : prop.treeLeafOnly,
					tree : {
						nameKey:"itemName",
						data : {simpleData : {enable: true,idKey: 'dicId',pIdKey : "parentId"},
							key : {name : "itemName"},
							data : dicList
						},
						selectValue:dictValue,
						view : { selectedMulti : prop.isMultiSelect},
						check : prop.check
					},
					selectBoxWidth : prop.width,
					selectBoxHeight : prop.height,
					onSelected:function(newval){
						if(this._toggleSelectBox){
							this._toggleSelectBox(true);
						}
						else if(ligerComboObj && ligerComboObj._toggleSelectBox){
							ligerComboObj._toggleSelectBox(true);
						}
						if(prop.onSelected){
							eval(prop.onSelected+'.call(this,newval)');
						}
					}
				});
			}
			
			$.each(dicList, function(i, d) {
				if(isObjNull(dictValue)==false){
					if(dictValue==d.text){
						$("#" + prop.valueFieldID).val(d.id);
					}
				}
			});
		});
	}
	// htDicCombo
	$.fn.htDicCombo = function(option) {
		$(this).each(function() {
			var prop = readProp(this);
			process(this, prop);
		});
	};
	
	$('.dicComboBox,.dicComboTree,.dicCombo').each(function() {
		//子表的模板列不做下拉框变换
		if($(this).parents("[formtype='edit']").length>0)return;
		$(this).htDicCombo();
	});
});
