
package com.hotent.banking.dao.banking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.AppUtil;
import com.hotent.banking.model.banking.BillPay;

@Repository
public class BillPayDao extends BaseDao<BillPay>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BillPay.class;
	}
	public List<BillPay> getUSERID(String USERID){
		return getBySqlKey("getUSERID", USERID);
	}

	public List<BillPay> getDate(String start_time_text,String end_time_text)
	{
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="SELECT * FROM W_BILL_PAY WHERE F_DATA between '"+start_time_text+"' and '"+end_time_text+"'";
		List<Map<String,Object>> list= template.queryForList(sql);
		List<BillPay> lis = new ArrayList<BillPay>();
		for(int i = 0; i < list.size(); i++){
			Map<String,Object> map = list.get(i);
			BillPay bill = new BillPay();
			bill.setDATA((java.util.Date) map.get("F_DATA"));
			bill.setUSERID((String) map.get("F_USERID"));
			bill.setPAYEEID((String) map.get("F_PAYEEID"));
			bill.setPAYMENT(((BigDecimal)map.get("F_PAYMENT")).doubleValue());
			lis.add(bill);
		}
//		BillPay billPay=billPayService.getBydateId(start_time_text, end_time_text);
		return lis;

		/*ModelAndView mv = new ModelAndView("/billPay/billPay/billPayList.jsp");{
		return (List<Map<String, Object>>) mv.addObject("billPayList", lis);*/
	}
}
