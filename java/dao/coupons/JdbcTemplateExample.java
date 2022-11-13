package dao.coupons;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import dao.coupons.CouponInfo;
import dao.coupons.CouponMapper;

public class JdbcTemplateExample
{
	public JdbcTemplate getTemplate()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		JdbcTemplate temp = (JdbcTemplate)context.getBean("tmp");
		return temp;
	}

	public List<CouponInfo> getAllCoupons()
	{
		JdbcTemplate temp = getTemplate();
		List<CouponInfo> list = temp.query("SELECT promo_event.proname, p_coupon.couid,p_coupon.couname,P_Coupon.discount*100,CAST(Promo_Event.prosd AS varchar(20))As PromoSD,CAST(Promo_Event.proed AS varchar(20)) As PromoED FROM P_Coupon LEFT JOIN Promo_Event ON P_Coupon.Proid=Promo_Event.Proid",new CouponMapper());
		
		return list;
	}
	
	public List<String> getAllUniqueEvents()
	{
		JdbcTemplate temp = getTemplate();
		List<String> list = temp.queryForList("Select unique(proname) from promo_event", String.class);
		return list;
	}
	
	public List<CouponInfo> getAllCoupons(String proname)
	{
		JdbcTemplate temp = getTemplate();
		List<CouponInfo> list = temp.query("SELECT promo_event.proname, p_coupon.couid,p_coupon.couname,P_Coupon.discount*100,CAST(Promo_Event.prosd AS varchar(20))As PromoSD,CAST(Promo_Event.proed AS varchar(20)) As PromoED FROM P_Coupon LEFT JOIN Promo_Event ON P_Coupon.Proid=Promo_Event.Proid where proname=?",new Object[] {proname},new CouponMapper());
		return list;
		
	}
	
	/**public void countTotalCustomers()
	{
		JdbcTemplate temp = getTemplate();
		Integer totalcustomers = temp.queryForObject("select count(*) from customerinfo",Integer.class);
		
		System.out.println("Total customers : " + totalcustomers);
	}**/
	
	
	/**public static void main(String s[])
	{
		jdbcTemp obj = new jdbcTemp();
		
		//obj.insert(sc.nextInt(),sc.next(),sc.next(),sc.nextInt());
		//obj.update(sc.nextInt(), sc.nextInt());
		//obj.select();
		
		//obj.searchCustomer(sc.nextInt());
		
	//	obj.countTotalCustomers();
	
		System.out.print(obj.getAllCoupons().size());
	}**/
}



