package com.hotent.platform.service.system;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * 根据集合关系合并数据
 *
 */

public class ParamResult<T> {

	
	private int type;//集合关系,1or,2and,3表达式
	private String typeName;
	private Collection<T> allList=null;//所有集全
	private Map<String,Collection<T>> conditionMap=null;//条件与集合的映谢
	/**
	 * 加入集合
	 * @param condition子条件
	 * @param ul子集合
	 */
	public void add(String condition,Collection<T> ul){
		if(allList==null)allList=new HashSet<T>();
		if(conditionMap==null)conditionMap=new HashMap<String,Collection<T>>();
		allList.addAll(ul);
		conditionMap.put(condition, ul);
	}
	public ParamResult(int t,String tn){
		type=t;typeName=tn;
	};
	

	public int getType() {
		return type;
	}
	public String getTypeName() {
		return typeName;
	}
	
	/**
	 * 返回合并后的数据
	 * @return	返回合并后的数据
	 */
	public Collection<T> getUserList() {
		if(type==1&&allList!=null&&allList.size()>0){//或.有数据
			return allList;
		}else if(type==1&&(allList==null||allList.size()<0)){////或.无数数据
			return null;
		}else if(type==2&&allList!=null&&allList.size()>0){//与.有数据
			return getAndUserList();
		}else if(type==2&&(allList==null||allList.size()<0)){//与.无数据
			return null;
		}else if(type==3){//表达式
			return allList;
		}else{
			return null;
		}
	}
	
	/**
	 * 返回与关系合并后的数据
	 * @return　返回与关系合并后的数据
	 */
	private Collection<T> getAndUserList(){
		
		if(allList==null)return null;
		Collection<T> resList=new ArrayList<T>();
		Collection<Collection<T>> values=conditionMap.values();
		
		for(T t:allList){
			boolean add=true;
			for(Collection<T> c:values){
				if(c==null||c.size()==0)add=false;
				if(!c.contains(t))add=false;
			}
			if(add){
				resList.add(t);
			}
		}
		return resList;
		
	}

}
