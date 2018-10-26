package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hotent.core.model.TaskExecutor;
import com.hotent.platform.model.bpm.BpmNodeUser;

public  class BpmUserCalc<T>{

	/**
	 * 计算两个集合的交集或合集或排除
	 * 
	 * @param computeType
	 * @param userIdSet
	 *            原集合
	 * @param newUserSet
	 *            新集合
	 * @return
	 */
	public   Set<T> computeUserSet(short computeType, Set<T> userIdSet, Set<T> newUserSet) {
		if (newUserSet == null) return userIdSet;
		if (BpmNodeUser.COMP_TYPE_AND==computeType) {// 交集
			Set<T> orLastSet = new LinkedHashSet<T>();
			Iterator<T> uIt = userIdSet.iterator();
			while (uIt.hasNext()) {
				T key = uIt.next();
				if (newUserSet.contains(key)) {
					orLastSet.add(key);
				}
			}
			return orLastSet;
		} else if (BpmNodeUser.COMP_TYPE_OR==computeType) {
			userIdSet.addAll(newUserSet);
		} else {// 排除
			for (T newUserId : newUserSet) {
				userIdSet.remove(newUserId);
			}
		}
		return userIdSet;
	}
	
	
}
