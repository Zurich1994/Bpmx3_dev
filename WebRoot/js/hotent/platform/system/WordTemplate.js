var controlObj;

function initMainTable(main){
	controlObj = OfficePlugin.officeObjs[0].controlObj;
	var bookMarks = controlObj.ActiveDocument.BookMarks;
	var bookMarkCount = bookMarks.Count;
	for(var i=1;i<=bookMarks.Count;i++){
		var item = bookMarks.Item(i);
		var name = item.Name;
		if(name.indexOf('_0_')>0) continue;
		var currentRange = item.Range;
		name = name.split('_1_')[0];
		var nameValue = main[name];
		currentRange.Text = nameValue ? nameValue : '';
		i--;// 因替换了一个书签，所以需要减一，否则bookMarks.Item(i)会取不到后面的书签
	}
}
		
function fillSubtables(subTables){
	//读取表的元数据
	var aryTableMeta=getMetaData();
	var tables = controlObj.ActiveDocument.Tables; //得到tables
	for(var i=0;i<aryTableMeta.length;i++){
		var tableMeta=aryTableMeta[i];
		var idx=tableMeta.tableIndex;
		var tableName=tableMeta.tableName;
		var currentTable = tables.Item(idx);
		var rows=subTables[tableName].dataList;
		//遍历加载每一个子表的数据。
		fillTable(currentTable,rows,tableMeta);
	}
	
}
	
function fillTable(currentTable,rows,tableMeta){
	var tbColMap=tableMeta.rowMetaData;
	var rowCount = currentTable.rows.Count;
	//数据为空的情况
	if(rows.length==0){
		currentTable.rows(rowCount).Delete();
	}
	
	for(var i=0;i<rows.length;i++){
		var row=rows[i];
		for(fieldName in tbColMap ){
			var idx=tbColMap[fieldName];
			var cell = currentTable.Cell(rowCount, idx);
			console.info(row[fieldName]);
			cell.Range.Text=row[fieldName];
		}
		//遍历到最后一行 不加数据。
		if(i<rows.length-1){
			currentTable.rows.add();
			rowCount++;
		}
	}
}
	
/**
读取表的元数据。
*[
*	{
*		tableName:"",
*		tableIndex:1,
*		tbColMap:[{fieldName:"",colIdx:1},{fieldName:"",colIndex:1}]
*		rowMetaData:{field1:1,field2:2}
*	}
*]
*/
function getMetaData(){
	var aryTableMeta=[];
	
	var tables = controlObj.ActiveDocument.Tables; //得到tables
	var tableCount = tables.Count; //得到table的数量
	if(tableCount==0) return aryTableMeta;
	for(var i=1;i<=tableCount;i++){
		var currentTable = tables.Item(i);
		// 表格列数
		var columnCount = currentTable.Columns.Count;
		var rowCount = currentTable.rows.Count;
		var tableMeta={};
		//表元数据索引
		tableMeta.tableIndex=i;
		var aryRowMeta=[];
		
		
		//列元数据读取
		for(var c=1; c<=columnCount;c++){
			var cell = currentTable.Cell(rowCount, c);
			var val = cell.Range.Text;
			var rowMetaObj={};
			rowMetaObj.colIndex=c;
			rowMetaObj.val=val;
			aryRowMeta.push(rowMetaObj);
		}
		tableMeta.tbColMap=aryRowMeta;
		
		aryTableMeta.push(tableMeta);
	}
	handTableMeta(aryTableMeta);
	
	return aryTableMeta;
	
}
	
function handTableMeta(aryTableMeta){
	//splice
	for(var i=aryTableMeta.length-1;i>=0;i--){
		var tableMeta=aryTableMeta[i];
		setColName(tableMeta);
		var isValid=tableMeta.isValid;
		if(!isValid){
			//删除无效元数据
			aryTableMeta.splice(i,1);
		}
	}
}
	
function setColName(tableMeta){
	var aryCol=tableMeta.tbColMap;
	var rowMetaData={};
	
	var isValid=false;
	for(var i=0;i<aryCol.length;i++){
		var colObj=aryCol[i];
		var name=colObj.val;
		if(name.trim()=="") {
			colObj.fieldName="";
			continue;
		}
		var aryName=name.split('_0_');
		if(aryName.length!=2) {
			colObj.fieldName="";
			continue;
		}
		isValid=true;
		//设置表名和列名
		tableMeta.tableName=aryName[0];
		var fName=aryName[1];
		var fieldName=fName.substring(0, fName.length-2);
		
		rowMetaData[fieldName]=colObj.colIndex;
		
	}
	tableMeta.isValid=isValid;
	tableMeta.rowMetaData=rowMetaData;
}
	
	
function printWord(){
	var oldOption;	
	try
	{
		var objOptions =  controlObj.ActiveDocument.Application.Options;
		oldOption = objOptions.PrintBackground;
		objOptions.PrintBackground = false;
	}
	catch(err){};
	controlObj.printout(true);
	try
	{
		var objOptions =  controlObj.ActiveDocument.Application.Options;
		objOptions.PrintBackground = oldOption;
	}
	catch(err){};	
}
	