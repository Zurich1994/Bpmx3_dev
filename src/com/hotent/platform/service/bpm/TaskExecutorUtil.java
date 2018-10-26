package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.StringUtil;

public class TaskExecutorUtil {
	
	
		/**
		 * 将数据 [{nodeId:"userTask1",executors:[{id:"",name:""},{id:"",name:""}]}],返回为
		 * 对象执行人。
		 * @param executors
		 * @return 
		 * Map<String,List<BpmIdentity>>
		 */
		public static Map<String,List<TaskExecutor>> getBpmIdentity(String executors){
			Map<String,List<TaskExecutor>> map=new HashMap<String, List<TaskExecutor>>();
			if(StringUtil.isEmpty(executors)) return map;
			JSONArray jsonArray=JSONArray.fromObject(executors);
			for(Object obj:jsonArray){
				JSONObject jsonNode=(JSONObject)obj;
				String nodeId=jsonNode.getString("nodeId");
				JSONArray users= jsonNode.getJSONArray("executors");
				List<TaskExecutor> userList=new ArrayList<TaskExecutor>();
				for(Object userObj:users){
					JSONObject user=(JSONObject)userObj;
					TaskExecutor bpmInentity=(TaskExecutor) TaskExecutor.getTaskUser(user.getString("id")
							, user.getString("name"));
					
					userList.add(bpmInentity);
				}
				map.put(nodeId, userList);
			}
			return map;
		}

}
