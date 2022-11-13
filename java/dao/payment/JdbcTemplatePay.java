package dao.payment;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplatePay {

public JdbcTemplate getTemplate() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		JdbcTemplate temp = (JdbcTemplate)context.getBean("tmp");
		return temp;
	}


public int newPayId() {
	
	JdbcTemplate temp = getTemplate();
	
	Integer newPayid = temp.queryForObject("Select max(payid) + 1 from p_payment",Integer.class);
	return newPayid;
	
}

public void insertPayment(int payid,int cartId, double ptotal, double totaldisc) {
	
	JdbcTemplate temp = getTemplate();	
	
	temp.update("Insert into p_payment values(?,?,?,?)", new Object[] {payid, cartId, ptotal,totaldisc});
	
	System.out.println("Insert success");
}




	
}
