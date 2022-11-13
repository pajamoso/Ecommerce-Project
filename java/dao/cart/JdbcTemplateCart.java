package dao.cart;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import dao.cartdetails.CartDetails;
import dao.cartdetails.CartDetailsMapper;

public class JdbcTemplateCart {

public JdbcTemplate getTemplate() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		JdbcTemplate temp = (JdbcTemplate)context.getBean("tmp");
		return temp;
	}

public void insertNewUserCart(int cartid,int userid)
{
	JdbcTemplate temp = getTemplate();	
	temp.update("Insert into p_cart values(?,?)",new Object[] {cartid,userid});
	System.out.println("----------row inserted");
}

public int getUserCartID()
{
	JdbcTemplate temp = getTemplate();	
	Integer newcartid = temp.queryForObject("select max(cartid) + 1 from p_cart",Integer.class);
	return newcartid;
}

public int selectCartId(int userId) {
	
	JdbcTemplate temp = getTemplate();	
	Integer sid = temp.queryForObject("Select cartid from p_cart where userid=?",new Object[] {userId},Integer.class);
	return sid;
}

public void updateCartId(int cartId,int userId) {
	
	JdbcTemplate temp = getTemplate();	
	temp.update("update p_cart set cartId=? where userId=?",new Object[] {cartId,userId},new CartDetailsMapper());
	
	
}


public void deleteCart(int cartId) {
	JdbcTemplate temp = getTemplate();	
	temp.update("delete from cart_detail where cartId=?",new Object[] {cartId});
}
public List<CartInfo> viewUsercart(){
	JdbcTemplate temp = getTemplate();	
	List <CartInfo> list = temp.query("Select * from p_cart", new CartMapper());
	return list;
}

public static void main(String[] args) {
	
	JdbcTemplateCart obj = new JdbcTemplateCart();
	
	int x = obj.getUserCartID();
	
	System.out.println("new CartID " + x);
	
	obj.updateCartId(x, 1);
	
	System.out.println("Update is working");
	
}


//public List<CartInfo> getCartId(int userid) {
//	
//	JdbcTemplate temp = getTemplate();	
//	List<CartInfo> cartId = temp.query("Select cartid from p_cart where userid=?", new Object[] {userid}, new CartMapper());
//	return cartId;
//}
	
}
