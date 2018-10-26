package com.hotent.platform.service.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.DateUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.mobile.model.form.PlatformType;
import com.hotent.platform.dao.form.BpmFormHandlerDao;
import com.hotent.platform.model.form.BpmFormData;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.PkValue;
import com.hotent.platform.model.form.SqlModel;
import com.hotent.platform.model.form.SubTable;
import com.hotent.platform.service.system.IdentityService;

/**
 * 将json数据转换为FormData对象数据。
 * @author ray
 *
 */
public class FormDataUtil {
	private static Log logger = LogFactory.getLog(FormDataUtil.class);
	
	/**
	 * 传入BpmFormData对象，解析成sqlmodel列表数据。
	 * @param formData
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public static List<SqlModel> parseSql(BpmFormData formData,String actDefId,String nodeId) throws Exception{
		PkValue pkValue=formData.getPkValue();
		boolean isAdd=pkValue.getIsAdd();
		List<SqlModel> list=new ArrayList<SqlModel>();
		
		BpmFormTable mainTalble= formData.getBpmFormTable();
		
		Map<String,BpmFormTable> subTableMap=convertTableMap(mainTalble.getSubTableList());
		
		if(isAdd){
			SqlModel sqlModel=getInsert(mainTalble, formData.getMainFields());
			sqlModel.setPk(pkValue.getValue().toString());
			sqlModel.setBpmFormTable(mainTalble);
			list.add(sqlModel);
			//插入子表数据
			for(SubTable subTable:formData.getSubTableList()){
				String tableName=subTable.getTableName().toLowerCase();
				BpmFormTable bpmFormTable=subTableMap.get(tableName);
				
				List<Map<String,Object>> dataList= subTable.getDataList();
				for(Map<String,Object> row:dataList){
					//获取主键。
					String pk=row.get(subTable.getPkName().toLowerCase()).toString();
					SqlModel subSqlModel=getInsert(bpmFormTable, row);
					subSqlModel.setPk(pk);
					subSqlModel.setBpmFormTable(bpmFormTable);
					list.add(subSqlModel);
				}
			}
			
		}else{
			//更新主表数据
			SqlModel sqlModel=getUpdate(formData);
			if(sqlModel!=null){
				list.add(sqlModel);
				sqlModel.setPk(formData.getPkValue().getValue().toString());
				sqlModel.setBpmFormTable(mainTalble);
			}
			//处理子表数据。
			BpmFormHandlerDao bpmFormHandlerDao=(BpmFormHandlerDao)AppUtil.getBean(BpmFormHandlerDao.class);
			BpmFormRightsService bpmFormRightsService = (BpmFormRightsService)AppUtil.getBean(BpmFormRightsService.class);
			List<JSONObject> subTableShowRights = bpmFormRightsService.getSubTableShowRights(actDefId, nodeId, PlatformType.PC);
			for(SubTable subTable:formData.getSubTableList()){
				String tableName=subTable.getTableName().toLowerCase();
				BpmFormTable table=subTableMap.get(tableName);
				
				//原来的数据。
				List<Map<String, Object>> subDataList= bpmFormHandlerDao.getByFk(table, pkValue.getValue().toString(), actDefId, nodeId,true);
				List<Map<String, Object>> curDataList=subTable.getDataList();
				if(BeanUtils.isEmpty(curDataList)){// 当前无数据，需要判断是子表隐藏还是数据的删除
					boolean isContinue = false ;
					for(JSONObject obj:subTableShowRights){
						String tempTableName = obj.getString("tableName");
						JSONObject showRights = obj.getJSONObject("show");
						if(tableName.equals(tempTableName) && "true".equalsIgnoreCase(showRights.getString("y"))){
							// 如果子表隐藏，则不处理
							isContinue = true;
							break;
						}
					}
					if(isContinue) continue;
				}
				//更新插入。
				List<SqlModel> updDelList= getUpdDelByList( table, curDataList,subDataList);
			
				list.addAll(updDelList);
			}
			
		}
		
		return list;
	}
	
	
	
	/**
	 * 获取更新或者删除的SqlModel列表。
	 * @param tableName
	 * @param curList
	 * @param originList
	 * @return
	 */
	private static List<SqlModel> getUpdDelByList(BpmFormTable bpmFormTable ,List<Map<String, Object>> curList,List<Map<String, Object>> originList){
		String tableName=bpmFormTable.getFactTableName();
		String pkField=bpmFormTable.getPkField();
	
		List<SqlModel> rtnList=new ArrayList<SqlModel>();
		Map<String,Map<String, Object>> curMap=convertMap(pkField,curList);
		Map<String,Map<String, Object>> originMap=convertMap(pkField,originList);
		Set<Entry<String, Map<String, Object>>> curSet= curMap.entrySet();
		//遍历当前数据
		//1.如果当前数据包含原来的数据，那么这个数据进行更新。
		//2.如果当前数据不包含原来的数据，那么添加这个数据。
		for(Iterator<Entry<String, Map<String, Object>>> it=curSet.iterator();it.hasNext();){
			Entry<String, Map<String, Object>> ent=it.next();
			Map<String, Object> map=ent.getValue();
			//原数据包含当前的数据，则更新。
			if(originMap.containsKey(ent.getKey())){
				SqlModel updSqlModel=getUpd(tableName,pkField, map);
				if(updSqlModel!=null){
					updSqlModel.setBpmFormTable(bpmFormTable);
					rtnList.add(updSqlModel);
				}
			}
			else{
				SqlModel model= getInsert(bpmFormTable, ent.getValue());
				
				model.setBpmFormTable(bpmFormTable);
				rtnList.add(model);
			}
		}
		//遍历原来的数据，当前数据不包含原来的数据，那么需要删除。
		Set<Entry<String, Map<String, Object>>> originSet= originMap.entrySet();
		for(Iterator<Entry<String, Map<String, Object>>> it=originSet.iterator();it.hasNext();){
			Entry<String, Map<String, Object>> ent=it.next();
			//当前数据不包含之前的数据，则需要删除
			if(!curMap.containsKey(ent.getKey())){
				String delSql="delete from " + tableName +" where "+pkField+ "=?";
				SqlModel sqlModel=new SqlModel(delSql, new Object[]{ent.getKey()} ,SqlModel.SQLTYPE_DEL);
				sqlModel.setPk(ent.getKey());
				sqlModel.setBpmFormTable(bpmFormTable);
				rtnList.add(sqlModel);	
			}
		}
		return rtnList;
	}
	
	/**
	 * 将列表转化为map对象。
	 * <pre>
	 * 主键和一行数据进行关联。
	 * </pre>
	 * @param list
	 * @return
	 */
	private static Map<String,Map<String, Object>> convertMap(String pkField, List<Map<String, Object>> list){
		pkField=pkField.toLowerCase();
		Map<String,Map<String, Object>> rtnMap=new HashMap<String, Map<String,Object>>();
		for(Map<String, Object> map:list){
			if(!map.containsKey(pkField)) continue;
			String value=map.get(pkField).toString();
			rtnMap.put(value, map);
		}
		return rtnMap;
	}
	
	
	/**
	 * 处理主表的数据
	 * @param jsonObj
	 * @param bpmFormData
	 * @throws Exception
	 */
	private static void handleMain(JSONObject jsonObj,BpmFormData bpmFormData) throws Exception{
		//主表 main
		JSONObject mainTable=jsonObj.getJSONObject("main");
		BpmFormTable mainTableDef=bpmFormData.getBpmFormTable();
		String colPrefix=mainTableDef.isExtTable()?"":TableModel.CUSTOMER_COLUMN_PREFIX;
		List<BpmFormField> mainFields=mainTableDef.getFieldList();
		Map<String, BpmFormField> mainFieldTypeMap= convertFieldToMap(mainFields);
		//主表字段
		JSONObject mainFieldJson=mainTable.getJSONObject("fields");
		//将主表JSON转换成map数据。
		Map<String, Object> mainFiledsData=handleRow(mainTableDef, mainFieldTypeMap, mainFieldJson);
		//添加主表数据
		bpmFormData.addMainFields(mainFiledsData);
		
		PkValue pkValue=bpmFormData.getPkValue();
		
		bpmFormData.addMainFields(pkValue.getName().toLowerCase(), pkValue.getValue());
		//只有在添加的时候才进行计算。
		if(pkValue.getIsAdd()){
			//获取需要通过脚本结算的字段。
			List<BpmFormField> mapFormField=getFieldsFromScript(mainFields);
			//通过脚本引擎计算字段。
			Map<String, Object> map= caculateField(colPrefix,mapFormField,bpmFormData.getMainFields(),mainFieldJson);
			
			bpmFormData.addMainFields(map);
			
			handIdentityField(mainTableDef,bpmFormData);
		}
	
		//设置流程变量
		Map<String, Object> variables=getVariables(mainFieldJson, mainFields);
		bpmFormData.setVariables(variables);
		
	}
	
	/**
	 * 处理后置的流水号。
	 */
	private static void handIdentityField(BpmFormTable mainTableDef,BpmFormData bpmFormData){
		List<BpmFormField> list =  mainTableDef.getFieldList();
		IdentityService identityService=AppUtil.getBean(IdentityService.class);
		String colPrefix=mainTableDef.isExtTable()?"":TableModel.CUSTOMER_COLUMN_PREFIX;
		for (BpmFormField field : list) {
			// 值来源为流水号。
			if (field.getValueFrom() != BpmFormField.VALUE_FROM_IDENTITY) continue;
			//处理启动流程时是否显示流水号的数据，判断依据来源于设计字段时，是否勾选了 启动时显示   isShowidentity=1  显示在启动页面, 为0则显示
			String prop=field.getCtlProperty();
			if (StringUtil.isEmpty(prop)) continue;
			JSONObject jsonObject=JSONObject.fromObject(prop);
			if(jsonObject.containsKey("isShowidentity")){
				String isShowidentity=jsonObject.getString("isShowidentity");
				if ("0".equals(isShowidentity)) {
					String idNo = identityService.nextId(field.getIdentity());
					String fieldName = field.getFieldName();
					String columnName = colPrefix+fieldName;
					bpmFormData.addMainFields(columnName.toLowerCase(),idNo);
				}
			}
		}
	}
	
	
	/**
	 * 获取流程变量。
	 * @param jsonObject
	 * @param list
	 * @return
	 */
	private static Map<String, Object> getVariables(JSONObject jsonObject,List<BpmFormField> list ){
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,BpmFormField> fieldsMap= convertFieldToMap(list);
		for(Iterator<String> it=jsonObject.keys();it.hasNext();){
			String key=it.next();
			String lowerKey=key.toLowerCase();
			BpmFormField field=fieldsMap.get(lowerKey);
			if(field!=null && field.getIsFlowVar()==1){
				Object value=jsonObject.get(key);
				if(value!=JSONNull.getInstance()){
					Object obj= convertType(value.toString(), field);
					map.put(key, obj);
				}
			}
		}
		return map;
	}
	
	/**
	 * 处理子表数据。
	 * @param jsonObj
	 * @param subTableMap
	 * @param bmpFormFieldDao
	 * @param mainTableDef
	 * @param bpmFormData
	 * @param pkValue
	 * @throws Exception 
	 */
	private static void handSub(JSONObject jsonObj,BpmFormData bpmFormData) throws Exception{
		BpmFormTable mainTable=bpmFormData.getBpmFormTable();
		List<BpmFormTable> listTable=mainTable.getSubTableList();
		//将表名消息并作为键和表对象进行关联。
		Map<String,BpmFormTable> formTableMap= convertTableMap(listTable);
		
		boolean isExternal=mainTable.isExtTable();
	
		String colPrefix=isExternal?"" : TableModel.CUSTOMER_COLUMN_PREFIX;
	
		//子表
		JSONArray arySub=jsonObj.getJSONArray("sub");
		//子表
		for(int i=0;i<arySub.size();i++){
			SubTable subTable=new SubTable();
			JSONObject subTableObj=arySub.getJSONObject(i);
			String tableName=subTableObj.getString("tableName").toLowerCase();
			//根据子表名称获取子表对象
			BpmFormTable subFormTable=formTableMap.get(tableName);
			//获取子表的列元数据。
			List<BpmFormField> subTableFields=subFormTable.getFieldList();
			//将子表的字段名称作为键，字段对象作为值放到map对象当中。
			Map<String,BpmFormField> subTableTypeMap= convertFieldToMap(subTableFields);
			//获取需要计算的脚本数据。
			List<BpmFormField> scriptFields=getFieldsFromScript(subTableFields);
			//设置子表名称
			subTable.setTableName(tableName);
			//设置子表的主键和外键名称。
			if(isExternal){
				String pk=subFormTable.getPkField();
				subTable.setPkName(pk);
				subTable.setFkName(subFormTable.getRelation());
			}
			else{
				subTable.setPkName(TableModel.PK_COLUMN_NAME);
				subTable.setFkName(TableModel.FK_COLUMN_NAME);
			}
			JSONArray arySubFields=subTableObj.getJSONArray("fields");
			for(int j=0;j<arySubFields.size();j++){
				JSONObject subFieldObj= arySubFields.getJSONObject(j);
				
				Map<String, Object> subRow = handleRow(subFormTable,subTableTypeMap, subFieldObj);
				//计算脚本字段。
				Map<String, Object> map= caculateField(colPrefix,scriptFields,subRow,null);
				
				subRow.putAll(map);
				//处理主键数据
				handFkRow(subFormTable,subRow,bpmFormData.getPkValue());
				//处理需要计算的数据。
				subTable.addRow(subRow);
			}
			bpmFormData.addSubTable(subTable);
		}
	}
	
	
	/**
	 * 处理意见数据。
	 * @param bpmFormData
	 * @param jsonObj
	 */
	private static void handOpinion(BpmFormData bpmFormData,  JSONObject jsonObj){
		//意见
		JSONArray aryOpinion=jsonObj.getJSONArray("opinion");
		//意见 opinion
		for(int i=0;i<aryOpinion.size();i++){
			JSONObject opinion=aryOpinion.getJSONObject(i);
			String formName=opinion.getString("name");
			String value=opinion.getString("value");
			bpmFormData.addOpinion(formName, value);
		}
	}
	
	/**
	 * 处理子表行数据的主键和外键。
	 * <pre>
	 * 添加子表的主键和外键。
	 * </pre>
	 * @param mainTabDef		主表定义。
	 * @param bpmFormTable		子表定义。
	 * @param rowData			子表一行数据。
	 * @param pkValue			主键数据。
	 * @throws Exception
	 */
	public static void handFkRow(BpmFormTable bpmFormTable, Map<String, Object> rowData,PkValue pkValue) throws Exception{
		boolean isExternal=bpmFormTable.isExtTable();
		//外部表数据
		if(isExternal){
			String pkField= bpmFormTable.getPkField().toLowerCase();
			if(!rowData.containsKey(pkField)){
				PkValue pk=generatePk(bpmFormTable);
				rowData.put(pk.getName(), pk.getValue());
			}
			else{
				Object obj=rowData.get(pkField);
				if(obj==null || "".equals(obj.toString().trim())){
					PkValue pk=generatePk(bpmFormTable);
					rowData.put(pk.getName(), pk.getValue());
				}
			}
			String fk=bpmFormTable.getRelation();
			rowData.put(fk, pkValue.getValue());
		}
		//本地表数据
		else{
			String pkField= bpmFormTable.getPkField().toLowerCase();
			//没有包含主键则添加一个。
			if(!rowData.containsKey(pkField)){
				Long pk=UniqueIdUtil.genId();
				rowData.put(TableModel.PK_COLUMN_NAME.toLowerCase() , pk);
			}
			
			rowData.put(TableModel.FK_COLUMN_NAME.toLowerCase(), pkValue.getValue());
		}
	
	}
	
	/**
	 * 取得值需要结算的字段。
	 * @param list
	 * @return
	 */
	private static List<BpmFormField> getFieldsFromScript(List<BpmFormField> list){
		List<BpmFormField> map=new ArrayList<BpmFormField>();
		for(BpmFormField field:list){
			//通过后台运算
			if(field.getValueFrom()==2)
				map.add(field);
		}
		return map;
	}
	
	
	
	/**
	 * 计算值从脚本的来的值。
	 * @param colPrefix		列前缀。
	 * @param fields		子表字段。
	 * @param data			子表的一行数据。
	 * @return
	 */
	private static Map<String, Object> caculateField(String colPrefix,  List<BpmFormField> fields,Map<String,Object> rowData, JSONObject mainFieldJson){
		
		Map<String, Object> result=new HashMap<String, Object>();
		for(BpmFormField field:fields){
			String fieldName = field.getFieldName().toLowerCase();
			//实际字段名称
			String name=colPrefix + field.getFieldName();
			//获取字段脚本。
			String script=field.getScript();
			Object value= FormUtil.calcuteField(script, rowData,colPrefix);
			//脚本不显示 ，用脚本计算的值覆盖新值
			if(mainFieldJson!=null){
				mainFieldJson.put(fieldName, value);
			}
			result.put(name.toLowerCase(), value);
		}
		return result;
	}

	
	/**
	 * 直接产生新的主键。
	 * @param bpmFormTable
	 * @return
	 * @throws Exception
	 */
	public static PkValue generatePk(BpmFormTable bpmFormTable) throws Exception{
		Object pkValue=null;
		String pkField=bpmFormTable.getPkField().toLowerCase();
		
		//外部表根据规则创建主键。
		if(bpmFormTable.isExtTable()){
			Short keyType=bpmFormTable.getKeyType();
			String keyValue=bpmFormTable.getKeyValue();
			pkValue=FormUtil.getKey(keyType, keyValue);
		}
		else{
			pkValue=UniqueIdUtil.genId();
		}
	
		PkValue pk=new PkValue();
		pk.setIsAdd(true);
		pk.setName(pkField);
		pk.setValue(pkValue);
		return pk;
	}

	/**
	 * 将json转换为Map对象。
	 * @param colPrefix			列前缀。
	 * @param subTableTypeMap	字段和类型映射。
	 * @param subFieldObj		json对象。
	 * @return
	 */
	private static Map<String, Object> handleRow(BpmFormTable bpmFormTable,Map<String, BpmFormField> fieldTypeMap, JSONObject fieldsJsonObj) {
		boolean isExternal=bpmFormTable.isExtTable();
		int keyType=  bpmFormTable.getKeyDataType();
		String colPrefix=(isExternal?"":TableModel.CUSTOMER_COLUMN_PREFIX).toLowerCase();
		String pkField=bpmFormTable.getPkField();
		Map<String, Object> row=new HashMap<String, Object>();
	
		//对字段名称进行遍历
		for(Iterator<String> it=fieldsJsonObj.keys();it.hasNext();){
			String key=it.next();
			Object obj=fieldsJsonObj.get(key);
			String value="";
			if(obj ==JSONNull.getInstance()){
				value="";
			}
			else if(obj instanceof JSONArray || obj instanceof JSONObject) {
				value=obj.toString();
			}
			else{
				value=obj + "";
			}
			if(pkField.equalsIgnoreCase(key) && StringUtil.isNotEmpty(value)){
				if(keyType==0){
					row.put(pkField.toLowerCase(), new Long(value));
				}
				else{
					row.put(pkField.toLowerCase(), value);
				}
				
			}
		
			BpmFormField bpmFormField=fieldTypeMap.get(key.toLowerCase());
			if(bpmFormField==null) continue;
			//转换数据类型
			Object convertValue=convertType(value, bpmFormField);
			String fieldName=key.toLowerCase();
			if(!isExternal && !fieldName.equalsIgnoreCase(TableModel.PK_COLUMN_NAME)){
				fieldName=(colPrefix +key).toLowerCase();
			}
			row.put(fieldName, convertValue);
		}
		return row;
	}
	
	/**
	 * 转换数据类型。
	 * @param strValue
	 * @param type
	 * @return
	 */
	public static Object convertType(String strValue, BpmFormField formField){
		
		String type=formField.getFieldType();
		if(StringUtil.isEmpty(strValue)) return null;
		Object value = strValue;
		if (ColumnModel.COLUMNTYPE_DATE.equals(type)){
			value = DateUtil.parseDate((String) strValue);
		}
		else if(ColumnModel.COLUMNTYPE_NUMBER.equals(type)) {
			String json = formField.getCtlProperty();
			JSONObject jsonObj = new JSONObject();
			if(StringUtil.isJson(json)){
				jsonObj = JSONObject.fromObject(json);
			}
			//替换货币符号
			if(BeanUtils.isNotEmpty(jsonObj.get("coinValue"))){
				strValue = StringUtil.trimPrefix(strValue, String.valueOf(jsonObj.get("coinValue")));
			}
			//替换千分位
			String isShowComdify=String.valueOf(jsonObj.get("isShowComdify"));
			if("1".equals(isShowComdify)|| "true".equals(isShowComdify)){
				strValue = strValue.replaceAll(",", "");
			}
			//属于小数类型。
			if(formField.getDecimalLen()>0){
				value=Double.parseDouble(strValue);
			}
			//整数型的处理。
			else{
				//判断是否包含小数点
				if(strValue.contains("."))
					strValue = StringUtils.substringBefore(strValue, ".");
				
				if(formField.getIntLen()<=10){
					value=Integer.parseInt(strValue);
				}
				else{
					value=Long.parseLong(strValue);
				}
			}
		} 
		
		return value;
	}
	
	
	
	/**
	 * 将字段列表转成字段map。
	 * @param list
	 * @return
	 */
	private static Map<String, BpmFormField> convertFieldToMap(List<BpmFormField>  list){
		Map<String, BpmFormField> map=new HashMap<String, BpmFormField>();
		for(Iterator<BpmFormField> it=list.iterator();it.hasNext();){
			BpmFormField field=it.next();
			map.put(field.getFieldName().toLowerCase(), field);
		}
		return map;
	}

	
	
	
	/**
	 * 将列表定义转换成Map对象。
	 * @param list
	 * @return
	 */
	private static Map<String, BpmFormTable> convertTableMap(List<BpmFormTable> list){
		Map<String,BpmFormTable> map=new HashMap<String, BpmFormTable>();
		for(BpmFormTable tb:list){
			map.put(tb.getTableName().toLowerCase(), tb);
		}
		return map;
	}
	
	/**
	 * 取得插入的数据的sqlmodel对象。
	 * @param tableName
	 * @param mapData
	 * @return
	 */
	private static SqlModel getInsert(BpmFormTable bpmFormTable,Map<String,Object> mapData){
		String tableName=bpmFormTable.getFactTableName();
		String pkField=bpmFormTable.getPkField().toLowerCase();
		
		StringBuffer fieldNames = new StringBuffer();
		StringBuffer params = new StringBuffer();
		final List<Object> values = new ArrayList<Object>();
		
		for (Map.Entry<String, Object> entry : mapData.entrySet()){
			fieldNames.append(entry.getKey()).append(",");
			params.append("?,");
			values.add(entry.getValue());
		}
		StringBuffer sql = new StringBuffer();
		
		sql.append(" INSERT INTO ");
		sql.append(tableName);
		sql.append("(");
		sql.append(fieldNames.substring(0, fieldNames.length() - 1));
		sql.append(")");
		sql.append(" VALUES (");
		sql.append(params.substring(0, params.length() - 1));
		sql.append(")");
	
		
		SqlModel sqlModel=new SqlModel(sql.toString(), values.toArray(),SqlModel.SQLTYPE_INSERT);
		
		Object obj=mapData.get(pkField);
		if(BeanUtils.isEmpty(obj)){
			obj=mapData.get(pkField.toUpperCase());
		}
		sqlModel.setPk(obj.toString());
		sqlModel.setBpmFormTable(bpmFormTable);
		
		return sqlModel;
	}
	
	/**
	 * 获取更新的数据model。
	 * @param tableName
	 * @param mapData
	 * @return
	 */
	private static SqlModel getUpd(String tableName,String pkField, Map<String,Object> mapData){
		final List<Object> values = new ArrayList<Object>();
		
		pkField=pkField.toLowerCase();
	
		
		String pkValue=mapData.get(pkField).toString();
		
		StringBuffer set = new StringBuffer();
		
		for (Map.Entry<String, Object> entry : mapData.entrySet()){
			if(!pkField.equals(entry.getKey())){
				set.append(entry.getKey()).append("=?,");
				values.add(entry.getValue());
			}
		}
		if(values.size()==0) return null;
		// sql
		StringBuffer sql = new StringBuffer();
		
		sql.append(" update ");
		sql.append(tableName);
		sql.append(" set ");
		sql.append(set.substring(0, set.length() - 1));
		sql.append(" where ");
		sql.append(pkField);
		sql.append("=?");
		values.add(pkValue);
		SqlModel sqlModel=new SqlModel(sql.toString(), values.toArray(),SqlModel.SQLTYPE_UPDATE);
		
		sqlModel.setPk(pkValue);
					
		return sqlModel;
	}
	
	
	/**
	 * 获取主表的更新的sqlmodel对象。
	 * <pre>
	 * 只是更新客户端提交的json数据，如果表单中没有提交任何数据则不更新。
	 * </pre>
	 * @param bpmFormData
	 * @return
	 */
	private static SqlModel getUpdate(BpmFormData bpmFormData){
		PkValue pk=bpmFormData.getPkValue();
		String tableName=bpmFormData.getBpmFormTable().getFactTableName();
		Map<String,Object> mapData=bpmFormData.getMainCommonFields();
		StringBuffer set = new StringBuffer();
	
		List<Object> values = new ArrayList<Object>();
		for (Map.Entry<String, Object> entry : mapData.entrySet()){
			set.append(entry.getKey()).append("=?,");
			values.add(entry.getValue());
		}
		if(values.size()==0) return null;
		
		StringBuffer sql = new StringBuffer();
		if(set.length() > 0) {
			// sql
			sql.append(" update ");
			sql.append(tableName);
			sql.append(" set ");
			sql.append(set.substring(0, set.length() - 1));
			sql.append(" where ");
			sql.append(pk.getName() );
			sql.append("=?");
			values.add(pk.getValue());
		}
		SqlModel sqlModel=new SqlModel(sql.toString(), values.toArray(),SqlModel.SQLTYPE_UPDATE);
		return sqlModel;
	}
	
	/**
	 * 将数据
	 * <pre>
	 * {
	 *		main: {
	 *			fields:{"字段1":"1","字段2":"2"}
	 *		},
	 *		sub: [
	 *			{
	 *				tableName: 'TB',
	 *				fields: [
	 *					{"字段1":"1","字段2":"2"},
	 *					{"字段1":"3","字段2":"4"}
	 *				]
	 *			},
	 *			{
	 *				tableName: 'TB',
	 *				fields: [
	 *					{"字段1":"1","字段2":"2"},
	 *					{"字段1":"3","字段2":"4"}
	 *				]
	 *			},
	 *		],
	 *		opinion: [
	 *			{name:"意见表单名1",value:"意见"},
	 *			{name:"意见表单名1",value:"意见"}
	 *		]
	 *	}
     *</pre>
	 * @param json
	 * @param mainTableDef
	 * @return
	 * @throws Exception
	 */
	public static  BpmFormData parseJson(String json,BpmFormTable mainTableDef) throws Exception{
		return parseJson( json,null, mainTableDef);
	}

	/**
	 * 将json数据解析为BpmFormData。
	 * @param json
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public static  BpmFormData parseJson(String json,PkValue pkValue,BpmFormTable mainTableDef) throws Exception{
		JSONObject jsonObj= JSONObject.fromObject(json);
		BpmFormData bpmFormData=new BpmFormData(mainTableDef);
		if(pkValue==null)
			pkValue=generatePk(mainTableDef);
		bpmFormData.setPkValue(pkValue);
		//处理主表
		handleMain(jsonObj,bpmFormData);
		//子表
		handSub(jsonObj,bpmFormData);
		//意见
		handOpinion(bpmFormData, jsonObj);
		
		
		return bpmFormData;
	}

	
}
