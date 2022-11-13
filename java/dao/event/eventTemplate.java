package dao.event;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;



public class eventTemplate {

	public JdbcTemplate getTemplate() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		JdbcTemplate temp = (JdbcTemplate) context.getBean("tmp");
		return temp;
	}

//	private int eID;
//	private String eName;
//	private String esDate;
//	private String edDate;
//	private String eDesc;
	
	public eventInfo searchEvent(int eID)
	{
		JdbcTemplate temp = getTemplate();	
		eventInfo event = temp.queryForObject("Select * from promo_event where proid = ?",
											new Object [] {eID},new eventMapper());
		return event;
	}
	
	public int insertNewEventID()
	{
		JdbcTemplate temp = getTemplate();	
		Integer neweID = temp.queryForObject("select max(proid) + 1 from promo_event",Integer.class);
		return neweID;
	}

	public void insertNewEvent(int eID,String eName,String esDate,String edDate, int ecID)
	{
		JdbcTemplate temp = getTemplate();	
		temp.update("insert into promo_event values(?,?,TO_DATE(?, 'YYYY-MM-DD'),TO_DATE(?, 'YYYY-MM-DD'),?)",new Object[] {eID,eName,esDate,edDate,ecID});
		System.out.println("----------row inserted");
	}
	
	public void updateEvent(String eName,String esDate,String edDate)
	{
		JdbcTemplate temp = getTemplate();	
		temp.update("update promo_event set prosd=TO_DATE(?, 'YYYY-MM-DD'), proed=TO_DATE(?, 'YYYY-MM-DD') where proname=?",new Object[] {esDate,edDate,eName});
	}
	
	public void deleteEvent(String eName)
	{
		JdbcTemplate temp = getTemplate();	
		temp.update("delete from promo_event where proname =?",new Object[] {eName});
	}
	
	public List<eventInfo> getAllEvents()
	{
		JdbcTemplate temp = getTemplate();
		List<eventInfo> list = temp.query("Select * from promo_event p inner join pcategory c on p.cid = c.cid",new eventMapper());
		
		return list;
		//for(CustomerInfo customer : list)
		//	customer.displayCustomerInfo();
	}
	
	


}
