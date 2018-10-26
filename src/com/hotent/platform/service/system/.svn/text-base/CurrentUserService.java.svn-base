package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hotent.core.model.CurrentUser;
import com.hotent.core.util.AppUtil;

import edu.emory.mathcs.backport.java.util.Collections;

@Service
public class CurrentUserService {

	/**
	 * 根据当前用户对象获取当前用户身份。
	 * 
	 * @param currentUser
	 * @return
	 */
	public Map<String, List<Long>> getUserRelation(CurrentUser currentUser) {
		Map<String, List<Long>> map = new HashMap<String, List<Long>>();
		try {
			List<ICurUserService> objectList = getUserRelation();
			for (ICurUserService curObj : objectList) {
				List<Long> list = curObj.getByCurUser(currentUser);
				map.put(curObj.getKey(), list);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 根据当前用户对象获取当前用户身份。
	 * 
	 * @return
	 */
	public List<String> getUserListKey() {
		List<String> list = new ArrayList<String>();
		try {
			List<ICurUserService> objectList = getUserRelation();
			for (ICurUserService curObj : objectList) {
				String key = curObj.getKey();
				list.add(key);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取权限的实现方法，这里返回算法的对应实现。
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	public List<ICurUserService> getUserRelation()
			throws ClassNotFoundException {
		Map<String, Object> instMap = AppUtil
				.getImplInstance(ICurUserService.class);
		Collection<Object> instCollect = instMap.values();
		List<ICurUserService> list = new ArrayList<ICurUserService>();
		for (Object obj : instCollect) {
			list.add((ICurUserService) obj);
		}
		
		return list;
	}

}
