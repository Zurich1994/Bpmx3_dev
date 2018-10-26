package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.DesktopColumnDao;
import com.hotent.platform.dao.system.DesktopLayoutDao;
import com.hotent.platform.dao.system.DesktopLayoutcolDao;
import com.hotent.platform.dao.system.DesktopMycolumnDao;
import com.hotent.platform.model.system.DesktopColumn;
import com.hotent.platform.model.system.DesktopLayout;
import com.hotent.platform.model.system.DesktopLayoutcol;
import com.hotent.platform.model.system.DesktopMycolumn;

/**
 * 对象功能:桌面个人栏目 Service类
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:ray 
 * 创建时间:2012-03-20 16:39:01
 */
@Service
public class DesktopMycolumnService extends BaseService<DesktopMycolumn> {
	@Resource
	private DesktopMycolumnDao dao;
	@Resource
	private DesktopLayoutDao desktopLayoutDao;
	@Resource
	private DesktopLayoutcolDao desktopLayoutcolDao;
	@Resource
	private DesktopColumnDao desktopColumnDao;
	@Resource
	private FreemarkEngine freemarkEngine;
	
	@Resource
	private DesktopColumnService desktopColumnService;

	public DesktopMycolumnService() {
	}

	@Override
	protected IEntityDao<DesktopMycolumn, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 根据用户ID获取用户桌面栏目列表。
	 * 
	 * @param userId 用户Id
	 * @return 用户桌面栏目列表
	 */
	public List<DesktopMycolumn> getByUserId(Long userId) {
		return dao.getByUserId(userId);
	}

	/**
	 * 根据布局ID，删除对应的桌面布局。
	 * 
	 * @param layoutId 桌面布局Id
	 */
	public void delByLayoutId(Long layoutId) {
		dao.delByLayoutId(layoutId);
	}

	/**
	 * 根据用户ID，删除对应的用户栏目。
	 * 
	 * @param layoutId 用户栏目Id
	 */
	public void delByUserId(Long userId) {
		dao.delByUserId(userId);
	}

	/**
	 * 根据用户Id和上下文目录，取得用户桌面栏目相关的数据。
	 * 如果找不到用户Id相应的用户桌面栏目，取默认值。
	 * 返回的桌面栏目相关的数据，用一个<code>Map</code>表示。Map中，key值为"layoutId"，表示用户桌面的布局的Id，
	 * "columns"，用String数据表示的用户栏目，"widthMap"，用Map表示的各个栏目的宽度。"datas"，用Map表示的各个栏目的子栏目。
	 * @param userId 用户Id
	 * @param ctxPath 上下文目录
	 * @return 桌面栏目相关的数据。
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getMyDeskData(Long userId, String ctxPath) throws Exception {
		//根据用户ID获取栏目。
		List<DesktopMycolumn> list = dao.getMyDeskData(userId);
		//获取栏目布局
		DesktopLayout layout = null;
		if (list.size() == 0) {
			list = dao.getDefaultDeskData();
			layout = desktopLayoutDao.getDefaultLayout();
		}else{
			layout = desktopLayoutDao.getLayoutByUserId(userId);
		}
		Map mapData = null;
		if(layout!=null){
			//获取栏目的宽度。
			String[] widths = layout.getWidth().split(",");			
			
			String[] columns = new String[layout.getCols()];
			Map map = new HashMap();
			Map widthMap = new HashMap();
			//获取到的列宽和列数不匹配时，以列数为准计算列宽（平均分配宽度）
			if(widths.length<layout.getCols()){
				int count=layout.getCols();
				double width=100/count;
				widths=new String[count];
				for(int x = 0; x < count; x++){
					widths[x]=String.format("%.2f", width);
				}
			}
			for (int i = 1; i <= layout.getCols(); i++) {
				columns[i - 1] = "" + i;
				widthMap.put("" + i, widths[i - 1]);
			}
			//遍历栏目。
			for (DesktopMycolumn desktopMycolumn : list) {
				//获取栏目的HTML。
				String html=getHtmlByMycolumn(desktopMycolumn, ctxPath);
				desktopMycolumn.setColumnHtml(html);
				if (!StringUtil.isEmpty(desktopMycolumn.getColumnUrl()))
					desktopMycolumn.setColumnUrl(desktopMycolumn.getColumnUrl());
				//将解析后数据添加到map中。
				List<DesktopMycolumn> subList = (List) map.get(desktopMycolumn.getCol().toString());
				if (subList == null) {
					subList = new ArrayList<DesktopMycolumn>();
					map.put(desktopMycolumn.getCol().toString(), subList);
				}
				subList.add(desktopMycolumn);
			}
			mapData = new HashMap();
			mapData.put("layoutId", layout.getId());
			mapData.put("columns", columns);
			mapData.put("widthMap", widthMap);
			mapData.put("datas", map);
		}
		return mapData;
	}
	
	/**
	 * 获取系统默认布局的已设置的栏目数据
	 * @param layoutId
	 * @param ctxPath
	 * @return
	 * @throws Exception
	 */
	public Map getDefaultDeskDataById(Long layoutId,String ctxPath) throws Exception{
		List<DesktopMycolumn> list = dao.getDefaultDeskDataById(layoutId);
		DesktopLayout layout = desktopLayoutDao.getById(layoutId);
		String[] widths = layout.getWidth().split(",");
		String[] columns = new String[layout.getCols()];
		Map map = new HashMap();
		Map widthMap = new HashMap();
		for (int i = 1; i <= layout.getCols(); i++) {
			columns[i - 1] = "" + i;
			widthMap.put("" + i, widths[i - 1]);
		}
		for (DesktopMycolumn desktopMycolumn : list) {
			desktopMycolumn.setColumnHtml(getHtmlByMycolumn(desktopMycolumn, ctxPath));
			//desktopMycolumn.setMethodType(methodType)
			if (!StringUtil.isEmpty(desktopMycolumn.getColumnUrl()))
				desktopMycolumn.setColumnUrl(ctxPath + desktopMycolumn.getColumnUrl());
			List<DesktopMycolumn> subList = (List) map.get(desktopMycolumn.getCol().toString());
			if (subList == null) {
				subList = new ArrayList<DesktopMycolumn>();
				map.put(desktopMycolumn.getCol().toString(), subList);
			}
			subList.add(desktopMycolumn);
		}
		Map mapData = new HashMap();
		mapData.put("layoutId", layout.getId());
		mapData.put("columns", columns);
		mapData.put("widthMap", widthMap);
		mapData.put("datas", map);
		return mapData;
	}

	/**
	 * 根据DesktopMyColumn的servicemethod(栏目获取数据的方法名)取得的栏目名称，上下文目录，使用FreeMarker模板引擎生成栏目数据html模板。
	 * @param desktopMycolumn 用户桌面栏目
	 * @param ctxPath 上下文目录
	 * @return 栏目数据html模板
	 * @throws Exception 
	 */
	private String getHtmlByMycolumn(DesktopMycolumn desktopMycolumn, String ctxPath) throws Exception {
		String html="";
		DesktopColumn desktopColumn=desktopColumnService.getById(desktopMycolumn.getColumnId());
		if(desktopColumn.getMethodType()==0){
			String handler=desktopMycolumn.getServicemethod();
			//获取具体栏目的数据。
			Object model = desktopColumnService.getModelByHandler(handler);
			if(model!=null){
				Map map = new HashMap();
				map.put("model", model);// 栏目名称
				map.put("ctxPath", ctxPath);// 上下文目录
				html = freemarkEngine.parseByStringTemplate(map, desktopMycolumn.getColumnHtml());
			}
		}else{
			html=desktopColumn.getHtml();
		}
		return html;
	}


	/**
	 * 获取用户桌面栏目。
	 * @param userId 用户Id
	 * @return 用户桌面栏目对象列表
	 * @throws Exception
	 */
	public List<DesktopMycolumn> getMyListData(long userId) throws Exception {
		List<DesktopMycolumn> listMy = new ArrayList<DesktopMycolumn>();
		DesktopMycolumn thisBean;
		// 获取 默认布局ID
		Long layoutId = desktopLayoutDao.getDefaultId();
		// 查找 默认布局栏目列表
		List<DesktopLayoutcol> listcol = desktopLayoutcolDao.getByLayoutId(layoutId);
		// 把默认布局栏目列表的数据保存到个人桌面中.
		for (DesktopLayoutcol bean : listcol) {
			thisBean = new DesktopMycolumn();
			thisBean.setId(UniqueIdUtil.genId());
			thisBean.setUserId(userId);
			thisBean.setCol(bean.getCol().shortValue());
			thisBean.setColumnId(bean.getColumnId());
			thisBean.setLayoutId(bean.getLayoutId());
			thisBean.setSn(bean.getSn());
			thisBean.setColumnName(bean.getColumnName());
			thisBean.setColumnUrl(bean.getColumnUrl());
			listMy.add(thisBean);
		}
		return listMy;
	}

	/**
	 * 保存个人栏目数据	
	 * @param list
	 * @param layoutId
	 * @param userId
	 * @throws Exception
	 */
	public void saveMycolumn(List<DesktopMycolumn> list, Long layoutId, Long userId) throws Exception {
		dao.delByUserId(userId);
		for (DesktopMycolumn bean : list) {
			dao.add(bean);
		}		
	}

	public DesktopLayoutcol newsData(Long id, Map<String, String> desktopLayoutAllmap, Map<String, String> desktopColumnmap,
			Map<String, String> desktopLayoutmap) {
		// 桌面所有布局
		List<DesktopLayout> desktopLayoutList = desktopLayoutDao.getAll();
		List<DesktopColumn> desktopColumnList = desktopColumnDao.getAll();
		DesktopLayout desktopLayout = desktopLayoutDao.getById(id);
		for (DesktopLayout dl : desktopLayoutList) {
			desktopLayoutAllmap.put("" + dl.getCols(), dl.getName());
		}
		// 桌面所有栏目
		for (DesktopColumn dc : desktopColumnList) {
			desktopColumnmap.put("" + dc.getId(), dc.getColumnName());
		}
		// 桌面当前布局
		desktopLayoutmap.put("cols", "" + desktopLayout.getCols());
		desktopLayoutmap.put("id", "" + desktopLayout.getId());
		DesktopLayoutcol desktopLayoutcol = desktopLayoutcolDao.getById(id);
		return desktopLayoutcol;
	}

	/**
	 * 获取个人桌面数据
	 * 
	 * @return
	 */
	public DesktopLayout getShowData(long userId) {
		List<DesktopMycolumn> list = dao.getByUserId(userId);
		if (list.size() == 0)
			return new DesktopLayout();
		DesktopLayout bean = desktopLayoutDao.getById(list.get(0).getLayoutId());
		return bean;
	}

	/**
	 * 显示个人桌面
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> desktopSync(long userId) throws Exception {
		List<DesktopMycolumn> list = dao.getByUserId(userId);
		Long layoutId = 0L;
		// 没有保存自己的个人桌面则取系统的默认桌面
		if (list.size() == 0) {
			DesktopLayout temp = desktopLayoutDao.getDefaultLayout();
			if (temp != null) {
				List<DesktopLayoutcol> collist = desktopLayoutcolDao.getByLayoutId(temp.getId());
				layoutId = collist.get(0).getLayoutId();
			}
		}
		// 有自己的桌面布局就取自己的桌面布局
		else {
			List<DesktopMycolumn> mylist = dao.getByUserId(userId);
			if (mylist.size() != 0) {
				layoutId = mylist.get(0).getLayoutId();
			}
		}
		DesktopLayout bean = desktopLayoutDao.getById(layoutId);
		Map<String, String> desktopLayoutmap = new HashMap<String, String>();
		desktopLayoutmap.put("cols", "" + bean.getCols());
		desktopLayoutmap.put("widths", bean.getWidth());
		// desktopLayoutmap.put("id", "" + bean.getId());
		return desktopLayoutmap;
	}

	public List<DesktopMycolumn> getByLayoutIdColNum(Long id, int colNum) {
		return	dao.getByLayoutIdColNum(id,colNum);
	}
}
