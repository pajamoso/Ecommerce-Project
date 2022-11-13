package dao.cartdetails;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import dao.cart.CartMapper;
import dao.payment.JdbcTemplatePay;

public class CartJdbc {

public JdbcTemplate getTemplate() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		JdbcTemplate temp = (JdbcTemplate)context.getBean("tmp");
		return temp;
	}

public void insertToCart(int cartdid,int cartId,int pid,int qty)
{
	JdbcTemplate temp = getTemplate();	

	Integer x;
	try {
		x = temp.queryForObject("Select pid from cart_detail where cartId=? and pid=?",
				new Object[] {cartId,pid},Integer.class);
		 if(x != null){
			temp.update("update cart_detail set qty=qty+1 where pid=? and cartid=?",
					new Object[] {pid,cartId});
			System.out.println("----------row updated");
		}
		
	} catch (DataAccessException e) {
		// TODO Auto-generated catch block
		temp.update("Insert into cart_detail values(?,?,?,?)",new Object[] {cartdid,cartId,pid,qty});
		e.printStackTrace();
		
	} 
	
}


public void validateCart(int cartId, int pid) {
	
	JdbcTemplate temp = getTemplate();
	
}




public int getCartDetailsID()
{
	JdbcTemplate temp = getTemplate();	
	Integer newcartid = temp.queryForObject("select max(cartdid) + 1 from cart_detail",Integer.class);
	return newcartid;
}

public List<CartDetails> viewAllCart(int cartid) {
	
	JdbcTemplate temp = getTemplate();	
	List<CartDetails> list = temp.query("Select * from cart_detail c Inner Join pproduct p on c.pid=p.pid where cartid=?",
			new Object[] {cartid},new CartDetailsMapper());

	return list;
}

//public void incQty(int pid) {
//	
//	JdbcTemplate temp = getTemplate();	
//	temp.update("Update from Cart_detail Set qty=qty+1 where pid=?",new Object[] {pid});
//	
//}
//
//public void decQty(int pid) {
//	
//	JdbcTemplate temp = getTemplate();	
//	temp.update("Update from Cart_detail Set qty=qty-1 where pid=?",new Object[] {pid});
//	
//}

public void deleteCart(int pid, int cid) {
    JdbcTemplate temp = getTemplate();  
    temp.update("Delete from cart_detail where pid=? and cartid=?",
            new Object[] {pid, cid});
}

public void deleteAllCart(int cid) {
    JdbcTemplate temp = getTemplate();  
    temp.update("Delete from cart_detail where cartid=?",
            new Object[] {cid});
}

public void updateQty(int qty,int pid,int cartId) {
	JdbcTemplate temp = getTemplate();	
	temp.update("update cart_detail set qty=? where pid=? and cartid=?",
			new Object[] {qty,pid,cartId});
	System.out.println("------------------row updated-------------------");
}

public int getTotal(int cartId){
	JdbcTemplate temp = getTemplate();	
	int total = temp.queryForObject("select sum(pprice*qty) from cart_detail c inner join pproduct p on c.pid=p.pid "
			+ "where cartid=?",new Object[] {cartId}, Integer.class
			);
	return total;
}

public int getPtotal(int pid, int cartId) {
	JdbcTemplate temp = getTemplate();	
	int ptotal = temp.queryForObject("select sum(pprice*qty) from cart_detail c inner join pproduct p on c.pid=p.pid where pid=? and cartid=?",new Object[] {cartId}, Integer.class
			);
	return ptotal;
}



public int getCartTotal(int cartId) {
	JdbcTemplate temp = getTemplate();
	int cartT = temp.queryForObject("select sum(qty) from cart_detail where cartId=?", new Object[] {cartId}, Integer.class);
	return cartT;
}

public int getDiscount(int couid) {
    JdbcTemplate temp = getTemplate();
    int total = temp.queryForObject(
            "select discount from p_coupon where couid=?",
            new Object[] { couid }, Integer.class);
    return total;
}



}
