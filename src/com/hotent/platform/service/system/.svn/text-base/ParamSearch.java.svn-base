package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.bpm.SysParamJsonStruct;
import com.hotent.platform.model.system.SysParam;


public abstract class ParamSearch<T> {
	/**
	 * 查询数据库的纯虚方法
	 * @param property
	 * @return
	 */
	public abstract List<T> getFromDataBase(Map<String,String> property);
	
	public List<T> getByParamCollect(String json) throws Exception{
		//将json字串符转为json对像。
		if(json==null||json.equals(""))return null;
		List<SysParamJsonStruct> struct= JSONArray.parseArray(json, SysParamJsonStruct.class);
		if(BeanUtils.isEmpty(struct))return null;
		//存储数数据的有序容器。
		java.util.LinkedList<ParamResult> resultList =new LinkedList<ParamResult>();
		getByParam(struct, resultList);
		//将数据空器中的数居返回
		List<T> returnList=new ArrayList<T>();
		if(resultList!=null&&resultList.size()>0){
			for(ParamResult res:resultList){
				if(res.getUserList()!=null)
				returnList.addAll(res.getUserList());
			}
		}
		return returnList;
	}

	/**
	 *	 将json数据:
	 * [{"branch":true,"sub":[{"paramKey":"hasChild","paramCondition":"=","paramValue":"否","conDesc":"hasChild = 否","ruleType":"3"},{"paramKey":"hunfou","paramCondition":"=","paramValue":"否","conDesc":"hunfou = 否","ruleType":"3","compType":"and"}]},
	 * {"paramKey":"age","paramCondition":"=","paramValue":"23","conDesc":"age = 23","ruleType":"3","compType":"or"}]
	 * 解析为sql参数查询数据，并根据集合关系合并最终结果。
	 * 算法解译：
	 * 1.取得集合关系：AND,OR,表达式。
	 * 		1.1如果为AND或OR
	 * 			1.1.1判断AND或OR节点下有没有表达式，即有没有branch(组合规则)节点。
	 *				如果有branch，即将sub下的表达式查询数据库并按照AND，OR关系合并数据，数据结果存入linkedlist里。
	 *				如果没有branch，即直接将AND，OR关系直接存入linkedlist中。
	 * 		1.2如果为表达式，直接将表达式数询数据库，并将结果存入linkedlist中。
	 * 
	 * 2.处理linklist中的结果。
	 * 	 按先后顺序将linkedlist 中的没有branch节点的AND,OR关系前后的表达式或有children节点的数据合并。
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public ParamResult getByParam(List<SysParamJsonStruct> ml, LinkedList<ParamResult> resultList) throws Exception{
		for(int i=0;i<ml.size();i++){
			SysParamJsonStruct m = ml.get(i);
			if(m.getBranch()){//组合规则
				java.util.LinkedList<ParamResult> resultListTemp =new LinkedList<ParamResult>();
				List<SysParamJsonStruct> sub = m.getSub();
				String compType = m.getCompType();
				if(!"".equals(compType) && compType!=null){
					int type = 3;
					if("or".equals(compType)) {//or
						type = 1;
					}
					else {//and
						type = 2;
					}
					ParamResult<T> res=new ParamResult<T>(type, compType);
					resultList.addLast(res);
				}
				ParamResult pr= getByParam(sub, resultListTemp);
				resultList.addLast(pr);
			}
			else {
				int type= 3;//expression
				String compType = m.getCompType();
				//集合运算类
				if(!"".equals(compType) && compType!=null){
					if("or".equals(compType)) {//or
						type = 1;
					}
					else {//and
						type = 2;
					}
					ParamResult<T> res1=new ParamResult<T>(type, compType);
					resultList.addLast(res1);
				}
				ParamResult<T> res2=new ParamResult<T>(type, new String());
				//判断集合关系
				String expression = m.getExpression();
				String dataType= m.getDataType();
				//将json属性转化为可用于sql查询是的参数
				Map<String,String> property=handlerParam(expression,dataType);
				//查询数据库
				List<T> ul=getFromDataBase(property);
				//将表达式下面的据库合部交结集合运算类处理
				res2.add(expression,ul);
				resultList.addLast(res2);
			}
		}
		//按先后顺序将linkedlist 中的没有children节点的AND,OR关系前后的表达式或有children节点的数据合并。
		if(BeanUtils.isEmpty(resultList))return null;
		if(resultList.size()%2==0)throw new IllegalArgumentException("表达式逻辑错误");
		
		if(resultList.size()>=3){
			while(resultList.size()>1){
				ParamResult cur=resultList.removeFirst();//数据集合
				ParamResult mid=resultList.removeFirst();//and,or关系
				ParamResult nex=resultList.removeFirst();//数据集合
				if(cur!=null&&mid!=null&&nex!=null&&mid.getType()!=3&&BeanUtils.isEmpty(mid.getUserList())){
					ParamResult count=new ParamResult(mid.getType(),mid.getTypeName());
					//根据集合关系，将集合交由集合运算类处理
					count.add("cur",cur.getUserList());
					count.add("nex",nex.getUserList());
					//加入数据容器
					resultList.addFirst(count);
				}else {
					throw new IllegalArgumentException("表达式逻辑错误");
				}
			}
		}
		return resultList.getFirst();
	}
	
	/**
	 * 将
	 * p>v将化为 aramKey=p and paramValue>v
	 * p=v将化为 aramKey=p and paramValue=v
	 * p<v将化为 aramKey=p and paramValue<v
	 * p!=v将化为 aramKey=p and paramValue!=v
	 * @param expression
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	protected  Map<String,String> handlerParam(String expression,String dataType) throws Exception{
		if(expression==null)return null;
		int m=-1;
		//查找关系式 "=","<",">","!=",">=","<=","like"
		String condition=null;
		for(Map.Entry<String, String> ent:SysParam.CONDITION_US.entrySet()){
			condition=ent.getKey();
			m=expression.indexOf(condition);
			if(m<0){
				condition=ent.getValue();
				m=expression.indexOf(condition);
			}
			if(m>-1)break;
		}
		if(m<0)return null;
		
		//查询paramKey,paramValue
		String tem[]=expression.split(condition);
		if(tem.length==2){
			String paramKey=tem[0].trim();
			String paramValue=tem[1].trim();
			String paramValueColumn=null;
			//根据不同的dataType，查询不同的字段
			paramValueColumn=SysParam.DATA_COLUMN_MAP.get(dataType);
			if(paramValueColumn==null)paramValueColumn="paramValue";
			Map<String,String> param=new HashMap<String,String>();
			param.put("paramKey", paramKey);
			param.put("condition", condition);
			param.put("paramValueColumn", paramValueColumn);
			if(condition=="like"||condition=="LIKE")
				param.put("paramValue", "%"+paramValue+"%");
			else
				param.put("paramValue", paramValue);
			System.out.print("[@_@]"+param.toString());
			return param;
		}else {
			throw new Exception("sql参数不是xxx"+condition+"x形式:"+expression);
		}
	}

	
	
}
