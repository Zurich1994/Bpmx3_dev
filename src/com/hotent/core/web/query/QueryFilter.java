package com.hotent.core.web.query;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.displaytag.util.ParamEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hotent.core.page.PageBean;
import com.hotent.core.page.PageUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.jsonobject.JSONObjectUtil;
import com.hotent.core.web.util.RequestUtil;

/**
 * 功能：对http请求进行封装，用于查询。<br>
 * 
 * 具体功能如下：<br>
 * 1.将查询条件封装成Map对象。<br>
 * 2.封装分页参数构建PageBean 。<br>
 * 3.封装排序条件。<br>
 * 
 * @author csx
 * 
 */
public class QueryFilter {
	private Logger logger = LoggerFactory.getLogger(QueryFilter.class);

	/**
	 * 过滤参数
	 */
	private Map<String, Object> filters = new HashMap<String, Object>();
	/**
	 * 排序列
	 */
	private String sortColumns = "";

	private ParamEncoder paramEncoder;

	public final static String ORDER_ASC = "1";
	public final static String ORDER_DESC = "2";

	/**
	 * Displaytag的tableId。 对应displaytag的id属性 通过tableId可以区分同一个页面有多个Display table
	 */
	private String tableId = "";

	private HttpServletRequest request;

	private PageBean pageBean = null;

	/**
	 * 构造QueryFilter，适用于使用displaytag列表的情况，默认为分页处理。
	 * 
	 * @param request
	 * @param tableId
	 *            displaytag的id
	 */
	public QueryFilter(HttpServletRequest request, String tableId) {
		this(request, tableId, true);
	}

	/**
	 * 构造QueryFilter，适用于使用displaytag列表的情况，默认为分页处理。
	 * 
	 * @param request
	 * @param tableId
	 *            displaytag的id
	 */
	public QueryFilter(HttpServletRequest request, Boolean isLg, String tableId) {

		this.tableId = tableId;
		this.request = request;

		this.paramEncoder = new ParamEncoder(tableId);
		try {
			// sortname和sortorder是ligerUI自带的前台返回的排序字段和方式名称
			String orderField = RequestUtil.getString(request, "sortname");
			String orderSeq = RequestUtil.getString(request, "sortorder");

			Map<String, Object> map = RequestUtil.getQueryMap(request);
			if (StringUtil.isNotEmpty(orderField)) {
				orderField = orderField.equals("id") ? orderField : "f_" + orderField;
				map.put("orderField", orderField);
				map.put("orderSeq", orderSeq);
			}
			this.filters = map;
			if (isLg) {
				// page和pagesize是ligerUI自带的前台返回的当前页和当前页大小的名称
				Integer page = RequestUtil.getInt(request, "page", 1);
				Integer pageSize = RequestUtil.getInt(request, "pagesize", PageBean.DEFAULT_PAGE_SIZE);
				this.pageBean = new PageBean(page, pageSize);
				this.pageBean.setShowTotal(true);
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

	}

	/**
	 * 构造普通请求的分页的QueryFilter（不适用displaytag列表），这个filter默认为分页。
	 * 
	 * @param request
	 */
	public QueryFilter(HttpServletRequest request) {
		this(request, true);
	}

	/**
	 * 这个QueryFilter构造方法用于使用displaytag的情况。<br>
	 * 如果需要碰到需要分页的情况， 请{@link com.hotent.core.web.util.RequestUtil#getQueryMap(HttpServletRequest) 参考}定义的参数规格。
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param tableId
	 *            tableId 对应diaplaytag的 id属性
	 * @param needPage
	 *            是否需要分页
	 */
	public QueryFilter(HttpServletRequest request, String tableId, boolean needPage) {
		this.tableId = tableId;
		this.request = request;

		this.paramEncoder = new ParamEncoder(tableId);
		String tableIdCode = this.paramEncoder.encodeParameterName("");
		try {
			// 排序字段
			String orderField = request.getParameter(tableIdCode + "s");
			String orderSeqNum = request.getParameter(tableIdCode + "o");

			String orderSeq = "desc";
			if (orderSeqNum != null && ORDER_ASC.equals(orderSeqNum)) {
				orderSeq = "asc";
			}
			Map<String, Object> map = RequestUtil.getQueryMap(request);
			if (orderField != null) {
				map.put("orderField", orderField);
				map.put("orderSeq", orderSeq);
			}
			this.filters = map;
			if (needPage) {
				int page = RequestUtil.getInt(request, tableIdCode + "p", 1);
				int pageSize = RequestUtil.getInt(request, tableIdCode + "z", PageBean.DEFAULT_PAGE_SIZE);
				int oldPageSize = RequestUtil.getInt(request, tableIdCode + "oz", PageBean.DEFAULT_PAGE_SIZE);
				if (pageSize != oldPageSize) {
					int first = PageUtils.getFirstNumber(page, oldPageSize);
					page = first / pageSize + 1;
				}
				this.pageBean = new PageBean(page, pageSize);
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	/**
	 * 
	 * @param request
	 *            请求ID
	 * @param tableId
	 *            表Id
	 * @param showTotal
	 *            是否统计总行数
	 */
	public QueryFilter(HttpServletRequest request, String tableId, String showTotal) {
		this(request, tableId, true);
		if ("false".equals(showTotal)) {
			this.pageBean.setShowTotal(false);
		}
	}

	/**
	 * 构建QueryFilter，可以根据needPage参数构建是否需要分页。<br>
	 * 如果需要分页。<br>
	 * 1.page 作为当前页参数。<br>
	 * 2.pageSize 作为获取分页大小的参数。
	 * 
	 * @param request
	 *            HttpServletRequest 对象
	 * @param needPage
	 *            是否需要分页
	 */
	public QueryFilter(HttpServletRequest request, boolean needPage) {
		this.request = request;
		try {
			if (needPage) {
				int page = RequestUtil.getInt(request, "page", 1);
				int pageSize = RequestUtil.getInt(request, "pageSize", 15);
				this.pageBean = new PageBean(page, pageSize);
			}
			Map<String, Object> map = RequestUtil.getQueryMap(request);
			filters = map;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	/**
	 * 构造方法
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param tableId
	 *            tableId 对应diaplaytag的 id属性
	 * @param pageSize
	 *            分页大小
	 */
	public QueryFilter(HttpServletRequest request, String tableId, int pageSize) {
		this(request, tableId, true, pageSize);
	}

	/**
	 * 这个QueryFilter构造方法用于使用displaytag的情况。<br>
	 * 如果需要碰到需要分页的情况， 请{@link com.hotent.core.web.util.RequestUtil#getQueryMap(HttpServletRequest) 参考}定义的参数规格。
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param tableId
	 *            tableId 对应diaplaytag的 id属性
	 * @param needPage
	 *            是否需要分页
	 * @param pageSize
	 *            分页大小
	 */
	public QueryFilter(HttpServletRequest request, String tableId, boolean needPage, int pageSize) {
		this.tableId = tableId;
		this.request = request;

		this.paramEncoder = new ParamEncoder(tableId);
		String tableIdCode = this.paramEncoder.encodeParameterName("");
		try {
			// 排序字段
			String orderField = request.getParameter(tableIdCode + "s");
			String orderSeqNum = request.getParameter(tableIdCode + "o");
			String orderSeq = "desc";
			if (orderSeqNum != null && ORDER_ASC.equals(orderSeqNum)) {
				orderSeq = "asc";
			}
			Map<String, Object> map = RequestUtil.getQueryMap(request);
			if (orderField != null) {
				map.put("orderField", orderField);
				map.put("orderSeq", orderSeq);
			}
			this.filters = map;
			if (needPage) {
				int page = RequestUtil.getInt(request, tableIdCode + "p", 1);
				if (page <= 0)
					page = 1;
				int size = RequestUtil.getInt(request, tableIdCode + "z", 0);
				if (size > 0)
					pageSize = size;
				int oldPageSize = RequestUtil.getInt(request, tableIdCode + "oz", PageBean.DEFAULT_PAGE_SIZE);
				if (pageSize != oldPageSize) {
					int first = PageUtils.getFirstNumber(page, oldPageSize);
					page = first / pageSize + 1;
				}
				this.pageBean = new PageBean(page, pageSize);
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * 创建一个新的实例 QueryFilter.
	 * @param jsonObject	:查询参数
	 * @param tableId	：表Id
	 * @param needPage	:是否分页
	 * @param pageSize	：分页大小
	 */
	public QueryFilter(JSONObject jsonObject, String tableId, boolean needPage, int pageSize) {
		this.tableId = tableId;

		this.paramEncoder = new ParamEncoder(tableId);
		String tableIdCode = this.paramEncoder.encodeParameterName("");
		try {
			// 排序字段
			String orderField = jsonObject.get(tableIdCode + "s")!=null?jsonObject.getString(tableIdCode + "s"):"";
			String orderSeqNum = jsonObject.get(tableIdCode + "o")!=null?jsonObject.getString(tableIdCode + "o"):"";
			String orderSeq = "desc";
			if (orderSeqNum != null && ORDER_ASC.equals(orderSeqNum)) {
				orderSeq = "asc";
			}
			Map<String, Object> map = JSONObjectUtil.getQueryMap(jsonObject);
			if (orderField != null) {
				map.put("orderField", orderField);
				map.put("orderSeq", orderSeq);
			}
			this.filters = map;
			if (needPage) {
				int page = jsonObject.get(tableIdCode + "p")!=null?jsonObject.getInt(tableIdCode + "p"):1;
				if (page <= 0)
					page = 1;
				int size = jsonObject.get(tableIdCode + "z")!=null?jsonObject.getInt(tableIdCode + "z"):0;
				if (size > 0)
					pageSize = size;
				int oldPageSize = jsonObject.get(tableIdCode + "oz")!=null?jsonObject.getInt(tableIdCode + "oz"):PageBean.DEFAULT_PAGE_SIZE;
				if (pageSize != oldPageSize) {
					int first = PageUtils.getFirstNumber(page, oldPageSize);
					page = first / pageSize + 1;
				}
				this.pageBean = new PageBean(page, pageSize);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	/**
	 * 设置当前分页路径，用于显示分页导航条。 <br>
	 * 适用于displaytag分页的情况。
	 * 
	 */
	public void setForWeb() {
		String pbName = "pageBean";
		String href = "requestURI";
		if (tableId != null) {
			pbName += tableId;
			href += tableId;
		}
		if (request == null)
			return;
		// 获取当前的url
		request.setAttribute(href, request.getRequestURI());
		request.setAttribute(pbName, pageBean);
	}

	/**
	 * 生成某个变量名以使唯一支持一个页面多个分页
	 * 
	 * @param parameterName
	 * @return
	 */
	public String encodeParameter(String parameterName) {
		if (this.paramEncoder == null) {
			// use the id attribute to get the unique identifier
			this.paramEncoder = new ParamEncoder(tableId);
		}

		return this.paramEncoder.encodeParameterName(parameterName);
	}

	/**
	 * 取得查询条件。
	 * 
	 * @return
	 */
	public Map<String, Object> getFilters() {
		return filters;
	}

	/**
	 * 添加过滤字段条件
	 * 
	 * @param filterName
	 *            参数名
	 * @param params
	 *            参数值
	 */
	public void addFilter(String filterName, Object params) {
		this.filters.put(filterName, params);
	}

	/**
	 * 设置查询条件。
	 * 
	 * @param filters
	 */
	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
	}

	/**
	 * 获取排序的sql。
	 * 
	 * @return
	 */
	public String getSortColumns() {
		return sortColumns;
	}

	/**
	 * 设置排序的SQL。<br>
	 * 
	 * <pre>
	 * 使用方法如下：
	 * QueryFilter q;
	 * q.setSortColumns("id des,age asc");
	 * </pre>
	 * 
	 * @param sortColumns
	 */
	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	/**
	 * 获取Displaytag的tableId
	 * 
	 * @return
	 */
	public String getTableId() {
		return tableId;
	}

	public ParamEncoder getParamEncoder() {
		return paramEncoder;
	}

}
