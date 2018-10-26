package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 用户选择类型选择,使用策略模式。
 * 在计算用户的时候，根据类型获取一种计算类。使用相应的策略进行计算。
 * <pre>
 * &lt;bean id="bpmNodeUserCalculationSelector" class="com.sf.platform.service.bpm.BpmNodeUserCalculationSelector">
 * &lt;property name="bpmNodeUserCalculation">
 *		&lt;paraTypeMap>
 *			&lt;entry>
 *				&lt;key>&lt;value>0</value></key>
 *				&lt;ref bean="startUserCalculation" />
 *			&lt;/entry>
 *		&lt;/property>
 * &lt;/bean>
 * </pre>
 * 配置到app-beans.xml中。
 * 
 *
 */
public class  BpmNodeUserCalculationSelector {
	
	private Map<String,IBpmNodeUserCalculation > bpmNodeUserCalculation=new LinkedHashMap<String, IBpmNodeUserCalculation>();

	public Map<String, IBpmNodeUserCalculation> getBpmNodeUserCalculation() {
		return bpmNodeUserCalculation;
	}
	
	/**
	 * 取得算法列表。
	 * @return
	 */
	public  List<IBpmNodeUserCalculation> getBpmNodeUserCalculationList() {
		List<IBpmNodeUserCalculation> list=new ArrayList<IBpmNodeUserCalculation>();
		Set<Entry<String, IBpmNodeUserCalculation>>  entries = bpmNodeUserCalculation.entrySet();
		for(Entry<String, IBpmNodeUserCalculation> entry:entries){
			list.add(entry.getValue());
		}
		return list;
		
	}

	/**
	 * 设置用户计算类型。
	 * @param bpmNodeUserCalculation
	 */
	public void setBpmNodeUserCalculation(Map<String, IBpmNodeUserCalculation> bpmNodeUserCalculation) {
		this.bpmNodeUserCalculation = bpmNodeUserCalculation;
	}
	
	/**
	 * 获取用户接口
	 * @param i
	 * @return
	 */
	public IBpmNodeUserCalculation getByKey(String key){
		return this.bpmNodeUserCalculation.get(key);
	}
	

	
}
