package dao.coupon;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import dao.category.CategoryInfo;
import dao.category.CategoryMapper;
import dao.event.eventInfo;
import dao.event.eventMapper;
import dao.product.ProductInfo;
import dao.product.ProductMapper;



public class couponTemplate {

	public JdbcTemplate getTemplate() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		JdbcTemplate temp = (JdbcTemplate) context.getBean("tmp");
		return temp;
	}
	
	public List<eventInfo> getAllUniquePrid()
	{
		JdbcTemplate temp = getTemplate();
		List<eventInfo> list = temp.query("Select * from promo_event", new eventMapper());
		return list;
	}

//	private int coID;
//	private String coName;
//	private int coDiscount;
//	private int copID;
	
	public couponInfo searchCoupon(int coID)
	{
		JdbcTemplate temp = getTemplate();	
		couponInfo coupon = temp.queryForObject("Select * from p_coupon where couid = ?",
											new Object [] {coID},new couponMapper());
		return coupon;
	}
	
	public int insertNewCouponID()
	{
		JdbcTemplate temp = getTemplate();	
		Integer newcoID = temp.queryForObject("select max(couid) + 1 from p_coupon",Integer.class);
		return newcoID;
	}

	public void insertNewCoupon(int coID,String coName,int coDiscount,int copID)
	{
		JdbcTemplate temp = getTemplate();	
		temp.update("insert into p_coupon values(?,?,?,?)",new Object[] {coID,coName,coDiscount,copID});
		System.out.println("----------row inserted");
	}
	
	public void updateCoupon(String coName,int coDiscount,int copID)
	{
		JdbcTemplate temp = getTemplate();	
		temp.update("update p_coupon set discount=?, proid=? where couname =?",new Object[] {coDiscount,copID,coName});
	}
	
	public void deleteCoupon(String coName)
	{
		JdbcTemplate temp = getTemplate();	
		temp.update("delete from p_coupon where couname =?",new Object[] {coName});
	}
	
	public List<couponInfo> getAllCoupons()
	{
		JdbcTemplate temp = getTemplate();
		List<couponInfo> list = temp.query("SELECT * FROM p_coupon c INNER JOIN promo_event p ON c.proid = p.proid", new couponMapper());
		
		return list;
		//for(CustomerInfo customer : list)
		//	customer.displayCustomerInfo();
	}
	
	


}
