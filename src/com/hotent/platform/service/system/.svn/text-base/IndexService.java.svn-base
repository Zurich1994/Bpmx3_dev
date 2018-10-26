package com.hotent.platform.service.system;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.page.PageBean;
import com.hotent.core.page.PageList;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.platform.dao.bpm.BpmProCopytoDao;
import com.hotent.platform.dao.bpm.BpmTaskExeDao;
import com.hotent.platform.dao.bpm.ProcessRunDao;
import com.hotent.platform.dao.bpm.TaskDao;
import com.hotent.platform.dao.mail.OutMailDao;
import com.hotent.platform.dao.system.MessageSendDao;
import com.hotent.platform.model.bpm.BpmDefAuthorizeType.BPMDEFAUTHORIZE_RIGHT_TYPE;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmTaskExe;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskAmount;
import com.hotent.platform.model.index.Infobox;
import com.hotent.platform.model.mail.OutMail;
import com.hotent.platform.model.system.MessageSend;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysBulletin;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.util.WarningSetting;
import com.hotent.platform.service.bpm.BpmDefAuthorizeService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.TaskReminderService;

@Service
public class IndexService {

	@Resource
	private TaskDao taskDao;
	@Resource
	private MessageSendDao messageSendDao;
	@Resource
	private ProcessRunDao processRunDao;
	@Resource
	private OutMailDao outMailDao;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmDefAuthorizeService bpmDefAuthorizeService;
	@Resource
	private SysBulletinService sysBulletinService;
	@Resource
	private BpmService bpmService;
	@Resource
	private BpmTaskExeDao bpmTaskExeDao;
	@Resource
	private TaskReminderService reminderService;
	@Resource
	private BpmProCopytoDao bpmProCopytoDao;

	public PageList<ProcessTask> pendingMatterPage(Integer curPage,
			Integer pageSize) {

		PageList<ProcessTask> list = new PageList<ProcessTask>();

		PageBean pageBean = new PageBean();

		try {
			pageBean.setCurrentPage(BeanUtils.isNotEmpty(curPage) ? curPage : 1);
			pageBean.setPagesize(BeanUtils.isNotEmpty(pageSize) ? pageSize : 10);
			list = (PageList<ProcessTask>) taskDao.getTasks(ContextUtil.getCurrentUserId(), null, null,
					null, null, "desc", pageBean);
			// 为待办添加上颜色用Description 字段
			Map<Integer, WarningSetting> waringSetMap = reminderService
					.getWaringSetMap();
			for (ProcessTask task : list) {
				int priority = task.getPriority();
				if (waringSetMap.containsKey(priority))
					task.setDescription(waringSetMap.get(priority).getColor());
				else
					task.setDescription("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ProcessTask> pendingMatters(Integer curPage, Integer pageSize) {

		List<ProcessTask> list = new ArrayList<ProcessTask>();

		PageBean pb = new PageBean();

		try {
			pb.setCurrentPage(BeanUtils.isNotEmpty(curPage) ? curPage : 1);
			pb.setPagesize(BeanUtils.isNotEmpty(pageSize) ? pageSize : 10);
			list = taskDao.getTasks(ContextUtil.getCurrentUserId(), null, null,
					null, null, "desc", pb);
			// 为待办添加上颜色用Description 字段
			Map<Integer, WarningSetting> waringSetMap = reminderService
					.getWaringSetMap();
			for (ProcessTask task : list) {
				int priority = task.getPriority();
				if (waringSetMap.containsKey(priority))
					task.setDescription(waringSetMap.get(priority).getColor());
				else
					task.setDescription("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 待办任务
	 * 
	 * @return
	 */
	public List<ProcessTask> pendingMatters() {
		return pendingMatters(1, 10);
	}

	/**
	 * 个人信息
	 * 
	 * @return
	 */
	public SysUser userInfo() {
		return ContextUtil.getCurrentUser();
	}

	/**
	 * 内部消息
	 * 
	 * @return
	 */
	public List<?> getMessage() {
		List<?> list = new ArrayList<MessageSend>();

		PageBean pb = new PageBean();
		pb.setCurrentPage(1);
		pb.setPagesize(10);
		try {
			list = messageSendDao.getNotReadMsgByUserId(
					ContextUtil.getCurrentUserId(), pb);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 我的审批的流程
	 * 
	 * @return
	 */
	public List<ProcessRun> myAttend() {
		List<ProcessRun> list = new ArrayList<ProcessRun>();
		PageBean pb = new PageBean();
		pb.setCurrentPage(1);
		pb.setPagesize(10);
		// 去掉进行分页的总记录数的查询
		pb.setShowTotal(false);
		try {
			list = processRunDao.getMyAttend(ContextUtil.getCurrentUserId(),
					null, pb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 我发起的流程
	 * 
	 * @return
	 */
	public List<ProcessRun> myStart() {
		List<ProcessRun> list = new ArrayList<ProcessRun>();
		PageBean pb = new PageBean();
		pb.setCurrentPage(1);
		pb.setPagesize(10);
		try {
			list = processRunDao.myStart(ContextUtil.getCurrentUserId(), pb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取用户未读邮件。 以时间降序排序，最多取10条。
	 * 
	 * @return 用户未读邮件对象列表
	 */
	public List<OutMail> myNewMail() {
		List<OutMail> list = new ArrayList<OutMail>();
		PageBean pb = new PageBean();
		pb.setCurrentPage(1);
		pb.setPagesize(10);
		try {
			list = outMailDao.getMailByUserId(ContextUtil.getCurrentUserId(),
					pb);
		} catch (Exception e) {
			e.getStackTrace();
		}

		return list;

	}

	/**
	 * 获取用户可以访问的流程定义
	 * 
	 * @return
	 */
	public List<BpmDefinition> myProcess() {
		List<BpmDefinition> list = new ArrayList<BpmDefinition>();
		Long curUserId = ContextUtil.getCurrentUserId();
		try {
			// 获得流程分管授权与用户相关的信息
			Map<String, Object> actRightMap = bpmDefAuthorizeService
					.getActRightByUserMap(curUserId,
							BPMDEFAUTHORIZE_RIGHT_TYPE.START, false, false);
			// 获得流程分管授权与用户相关的信息集合的流程KEY
			String actRights = (String) actRightMap.get("authorizeIds");
			list = bpmDefinitionService.getMyDefListForDesktop(actRights);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 我的办结
	 * 
	 * @return
	 */
	public List<ProcessRun> myCompleted() {
		List<ProcessRun> list = new ArrayList<ProcessRun>();
		long curUserId = ContextUtil.getCurrentUserId();
		PageBean pb = new PageBean();
		pb.setCurrentPage(1);
		pb.setPagesize(10);
		// 去掉进行分页的总记录数的查询
		pb.setShowTotal(false);
		try {
			list = processRunDao.getMyCompletedList(curUserId, pb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 已办事宜
	 * 
	 * @return
	 */
	public List<ProcessRun> alreadyMatters() {
		List<ProcessRun> list = new ArrayList<ProcessRun>();
		PageBean pb = new PageBean();
		pb.setCurrentPage(1);
		pb.setPagesize(10);
		// 去掉进行分页的总记录数的查询
		pb.setShowTotal(false);
		try {
			list = processRunDao.Myalready(ContextUtil.getCurrentUserId(), pb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 办结事宜
	 * 
	 * @return
	 */
	public List<ProcessRun> completedMatters() {
		List<ProcessRun> list = new ArrayList<ProcessRun>();
		PageBean pb = new PageBean();
		pb.setCurrentPage(1);
		pb.setPagesize(10);
		// 去掉进行分页的总记录数的查询
		pb.setShowTotal(false);
		try {
			list = processRunDao.completedMatters(
					ContextUtil.getCurrentUserId(), pb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 公告信息
	 * 
	 * @return
	 */
	public List<SysBulletin> getBulletin(String alias) {
		List<SysBulletin> list = sysBulletinService.getAllByAlias(alias);
		return list;
	}

	/**
	 * 个人信息
	 * 
	 * @return
	 */
	public Map<String, Object> getCurUser() {
		Map<String, Object> root = new HashMap<String, Object>();
		SysUser user = ContextUtil.getCurrentUser();
		root.put("user", user);
		SysOrg org = ContextUtil.getCurrentOrg();
		root.put("org", org);
		Position pos = ContextUtil.getCurrentPos();
		root.put("pos", pos);
		return root;
	}

	/**
	 * 信息盒子
	 * 
	 * @return
	 */
	public List<Infobox> getInfobox() {
		Long userId = ContextUtil.getCurrentUserId();

		List<Infobox> list = new ArrayList<Infobox>();
		try {

			List<TaskAmount> countlist = bpmService.getMyTasksCount(userId);
			int count = 0;
			int notRead = 0;
			for (TaskAmount taskAmount : countlist) {
				count += taskAmount.getTotal();
				notRead += taskAmount.getNotRead();
			}
			// ========== 待办事宜
			Infobox infobox1 = new Infobox();
			infobox1.setIcon("fa-comments");
			infobox1.setColor(Infobox.COLOR_BLUE);
			infobox1.setDataText("(" + notRead + "/" + count + ")");
			infobox1.setDataContent("待办事宜");
			infobox1.setUrl("/platform/bpm/task/pendingMatters.ht");
			list.add(infobox1);

			// ========== 查看内部消息
			Integer messCount = messageSendDao
					.getCountReceiverByUser(ContextUtil.getCurrentUserId());
			Integer noReadMessCount = messageSendDao
					.getCountNotReadMsg(ContextUtil.getCurrentUserId());

			Infobox infobox12 = new Infobox();
			infobox12.setIcon("fa-comments");
			infobox12.setColor(Infobox.COLOR_BLUE2);
			infobox12
					.setDataText("(" + noReadMessCount + "/" + messCount + ")");
			infobox12.setDataContent("内部消息");
			infobox12.setUrl("/platform/system/messageReceiver/list.ht");
			list.add(infobox12);

			// ========== 查看抄送转发
			Integer proCount = bpmProCopytoDao.getCountByUser(ContextUtil
					.getCurrentUserId());
			Integer noReadProCount = bpmProCopytoDao
					.getCountNotReadByUser(ContextUtil.getCurrentUserId());

			Infobox infobox13 = new Infobox();
			infobox13.setIcon("fa-comments");
			infobox13.setColor(Infobox.COLOR_BLUE3);
			infobox13.setDataText("(" + noReadProCount + "/" + proCount + ")");
			infobox13.setDataContent("抄送转发");
			infobox13.setUrl("/platform/system/messageReceiver/list.ht");
			list.add(infobox13);

			// ========== 已办事宜
			List<ProcessRun> list2 = processRunDao.Myalready(userId, null);

			Infobox infobox2 = new Infobox();
			infobox2.setIcon("fa-flag");
			infobox2.setColor(Infobox.COLOR_RED);
			infobox2.setDataText(list2.size() + "");
			infobox2.setDataContent("已办事宜");
			infobox2.setUrl("/platform/bpm/processRun/alreadyMatters.ht");
			list.add(infobox2);

			// ========== 办结事宜
			List<ProcessRun> list3 = processRunDao.completedMatters(userId,
					null);
			Infobox infobox3 = new Infobox();
			infobox3.setIcon("fa-check");
			infobox3.setColor(Infobox.COLOR_GREEN);
			infobox3.setDataText(list3.size() + "");
			infobox3.setDataContent("办结事宜");
			infobox3.setUrl("/platform/bpm/processRun/completedMatters.ht");
			list.add(infobox3);

			// ========== 转办代理事宜
			List<BpmTaskExe> list4 = bpmTaskExeDao.accordingMattersList(userId,
					null);
			Infobox infobox4 = new Infobox();
			infobox4.setIcon("fa-share");
			infobox4.setColor(Infobox.COLOR_PINK);
			infobox4.setDataText(list4.size() + "");
			infobox4.setDataContent("转办代理事宜");
			infobox4.setUrl("/platform/bpm/bpmTaskExe/accordingMatters.ht");
			list.add(infobox4);

			// ========== 新建流程
			// 获得流程分管授权与用户相关的信息
			/*
			 * Map<String, Object> actRightMap = bpmDefAuthorizeService
			 * .getActRightByUserMap(userId, BPMDEFAUTHORIZE_RIGHT_TYPE.START,
			 * false, false); // 获得流程分管授权与用户相关的信息集合的流程KEY String actRights =
			 * (String) actRightMap.get("authorizeIds"); List<BpmDefinition>
			 * list5 = bpmDefinitionService .getMyDefListForDesktop(actRights);
			 * Infobox infobox5 = new Infobox(); infobox5.setIcon("fa-file-o");
			 * infobox5.setColor(Infobox.COLOR_GREEN2);
			 * infobox5.setDataText(list5.size()+"");
			 * infobox5.setDataContent("新建流程");
			 * infobox5.setUrl("/platform/bpm/bpmDefinition/forMe.ht");
			 * list.add(infobox5);
			 */

			// ========== 我的请求
			List<ProcessRun> list6 = processRunDao.getMyRequestList(userId,
					null);
			Infobox infobox6 = new Infobox();
			infobox6.setIcon("fa-hand-o-up");
			infobox6.setColor(Infobox.COLOR_BLUE2);
			infobox6.setDataText(list6.size() + "");
			infobox6.setDataContent("我的请求");
			infobox6.setUrl("/platform/bpm/processRun/myRequest.ht");
			list.add(infobox6);

			// ========== 我的办结
			List<ProcessRun> list7 = processRunDao.getMyCompletedList(userId,
					null);
			Infobox infobox7 = new Infobox();
			infobox7.setIcon("fa-check-square-o");
			infobox7.setColor(Infobox.COLOR_BROWN);
			infobox7.setDataText(list7.size() + "");
			infobox7.setDataContent("我的办结");
			infobox7.setUrl("/platform/bpm/processRun/myCompleted.ht");
			list.add(infobox7);

			// ========== 我的草稿
			List<ProcessRun> list8 = processRunDao.getMyDraft(userId, null);
			Infobox infobox8 = new Infobox();
			infobox8.setIcon("fa-pencil-square-o");
			infobox8.setColor(Infobox.COLOR_WOOD);
			infobox8.setDataText(list8.size() + "");
			infobox8.setDataContent("我的草稿");
			infobox8.setUrl("/platform/bpm/processRun/myForm.ht");
			list.add(infobox8);

			/*
			 * Infobox infobox9 = new Infobox(); infobox9.setType(1);
			 * infobox9.setColor(Infobox.COLOR_BLUE3);
			 * infobox9.setDataText("任务完成率"); infobox9.setData((int)
			 * (Math.random() * 100)+""); list.add(infobox9);
			 */

			/*
			 * Infobox infobox10 = new Infobox(); infobox10.setType(2);
			 * infobox10.setColor(Infobox.COLOR_LIGHT_BROWN);
			 * infobox10.setDataText((int) (Math.random() * 1000)+"");
			 * infobox10.setDataContent("网站访问量");
			 * infobox10.setData("196,128,202,177,154,94,100,170,224");
			 * list.add(infobox10);
			 */

			// ========== 在线人数
			Infobox infobox11 = new Infobox();
			infobox11.setType(2);
			infobox11.setColor(Infobox.COLOR_LIGHT_BROWN);
			infobox11.setDataText(AppUtil.getOnlineUsers().size() + "");
			infobox11.setDataContent("在线人数");
			infobox11.setData("196,128,202,177,154,94,100,170,224");
			list.add(infobox11);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * 
	 * 我的日历例子 [{ title: '所有事件', start: new Date(y, m, 1), backgroundColor:
	 * Theme.colors.blue }, { title: '长事件', start: new Date(y, m, d-5), end: new
	 * Date(y, m, d-2), backgroundColor: Theme.colors.red }, { id: 999, title:
	 * '重复事件', start: new Date(y, m, d-3, 16, 0), allDay: false,
	 * backgroundColor: Theme.colors.yellow }, { id: 999, title: '重复事件', start:
	 * new Date(y, m, d+4, 16, 0), allDay: false, backgroundColor:
	 * Theme.colors.primary }, { title: '会议', start: new Date(y, m, d, 10, 30),
	 * allDay: false, backgroundColor: Theme.colors.green }, { title: '午餐',
	 * start: new Date(y, m, d, 12, 0), end: new Date(y, m, d, 14, 0), allDay:
	 * false, backgroundColor: Theme.colors.red }, { title: '生日聚会', start: new
	 * Date(y, m, d+1, 19, 0), end: new Date(y, m, d+1, 22, 30), allDay: false,
	 * backgroundColor: Theme.colors.gray }, { title: '链接到百度', start: new
	 * Date(y, m, 28), end: new Date(y, m, 29), url: 'http://www.baidu.com/',
	 * backgroundColor: Theme.colors.green }
	 * 
	 * 
	 * @return
	 */
	public String myCalendar() {

		JSONArray jsonAry = new JSONArray();

		// {title: '所有事件',start: new Date(y, m, 1),backgroundColor:
		// Theme.colors.blue

		for (int i = 0; i < 100; i++) {
			JSONObject json = new JSONObject();
			json.accumulate("title", "所有事件")
					.accumulate("start",
							DateFormatUtil.format(new Date(), "yyyy-MM-dd"))
					.accumulate("backgroundColor", "#70AFC4")
					.accumulate("eventClick",
							"function(calEvent){" + "alert(calEvent.title)}");
			jsonAry.add(json);

		}

		//
		JSONObject json2 = new JSONObject();
		Calendar ca = Calendar.getInstance();

		// {title: '所有事件',start: new Date(y, m, 1),backgroundColor:
		// Theme.colors.blue
		json2.accumulate("title", "长事件").accumulate("start",
				DateFormatUtil.format(ca.getTime(), "yyyy-MM-dd"));
		ca.add(Calendar.DAY_OF_MONTH, 3);
		json2.accumulate("end",
				DateFormatUtil.format(ca.getTime(), "yyyy-MM-dd")).accumulate(
				"backgroundColor", "#D9534F");
		jsonAry.add(json2);

		JSONObject json3 = new JSONObject();
		ca.add(Calendar.DAY_OF_MONTH, 6);

		// {title: '所有事件',start: new Date(y, m, 1),backgroundColor:
		// Theme.colors.blue
		json3.accumulate("title", "连接到公司网站")
				.accumulate("start",
						DateFormatUtil.format(ca.getTime(), "yyyy-MM-dd"))
				.accumulate("url", "http://www.jee-soft.cn/")
				.accumulate("backgroundColor", "#A8BC7B");
		jsonAry.add(json3);
		return jsonAry.toString();
	}

	/**
	 * 柱状图例子
	 * 
	 * @return
	 */
	public String barChart() {
		float data1[] = new float[12];
		float data2[] = new float[12];
		// 随机
		for (int i = 0; i <= 11; i++) {
			DecimalFormat dcmFmt = new DecimalFormat("0.0");
			float f1 = new Random().nextFloat() * 1000;
			float f2 = new Random().nextFloat() * 2000;
			data1[i] = Float.parseFloat(dcmFmt.format(f1));
			data2[i] = Float.parseFloat(dcmFmt.format(f2));
		}
		String d1 = JSONArray.fromObject(data1).toString();
		String d2 = JSONArray.fromObject(data2).toString();
		String data = "{title:{text:'',subtext:'纯属虚构'},tooltip:{trigger:'axis'},legend:{data:['蒸发量','降水量']},toolbox:{sho:true,feature:{mark:{sho:true},dataVie:{sho:true,readOnly:false},magicType:{sho:true,type:['line','bar']},restore:{sho:true},saveAsImage:{sho:true}}},calculable:true,xAxis:[{type:'category',data:['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']}],yAxis:[{type:'value'}],series:[{name:'蒸发量',type:'bar',"
				+ "data:"
				+ d1
				+ ",markPoint:{data:[{type:'max',name:'最大值'},{type:'min',name:'最小值'}]},markLine:{data:[{type:'average',name:'平均值'}]}},{name:'降水量',type:'bar',data:"
				+ d2
				+ ",markPoint:{data:[{name:'年最高',value:182.2,xAxis:7,yAxis:183,symbolSize:18},{name:'年最低',value:2.3,xAxis:11,yAxis:3}]},markLine:{data:[{type:'average',name:'平均值'}]}}]}";

		return data;
	}

	/**
	 * 折线图例子
	 * 
	 * @return
	 */
	public String lineChart() {
		int data1[] = new int[7];
		int data2[] = new int[7];
		// 随机
		for (int i = 0; i <= 6; i++) {
			data1[i] = (int) (Math.random() * 10) + 15;
			data2[i] = (int) (Math.random() * 10);
		}
		String d1 = JSONArray.fromObject(data1).toString();
		String d2 = JSONArray.fromObject(data2).toString();
		String data = "{" + "title:{" + "text:\"\"," + "subtext:\"纯属虚构\""
				+ "}," + "tooltip:{" + "trigger:\"axis\"" + "}," + "legend:{"
				+ "data:[\"最高气温\",\"最低气温\"]" + "}," + "calculable:\"true\","
				+ "xAxis:[" + "{" + "type:\"category\","
				+ "boundaryGap:\"false\","
				+ "data:[\"周一\",\"周二\",\"周三\",\"周四\",\"周五\",\"周六\",\"周日\"]"
				+ "}" + "]," + "yAxis:[" + "{" + "type:\"value\","
				+ "axisLabel:{" + "formatter:\"{value} °C\"" + "}" + "}" + "],"
				+ "series:[" + "{" + "name:\"最高气温\"," + "type:\"line\","
				+ "data:"
				+ d1
				+ ","
				+ "markPoint:{"
				+ "data:["
				+ "{type:\"max\",name:\"最大值\"},"
				+ "{type:\"min\",name:\"最小值\"}"
				+ "]"
				+ "},markLine:{"
				+ "data:["
				+ "{type:\"average\",name:\"平均值\"}"
				+ "]"
				+ "}"
				+ "},"
				+ "{name:\"最低气温\","
				+ "type:\"line\","
				+ "data:"
				+ d2
				+ ","
				+ "markPoint:{"
				+ "data:["
				+ "{name:\"周最低\",value:\"-2\",xAxis:\"1\",yAxis:\"-1.5\"}"
				+ "]"
				+ "},"
				+ "markLine:{"
				+ "data:["
				+ "{type:\"average\",name:\"平均值\"}" + "]" + "}" + "}" + "]}";
		return data;

	}

}
