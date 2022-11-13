package dao.cartdetails;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CartDetailsMapper implements RowMapper<CartDetails>{

	public CartDetails mapRow(ResultSet result, int arg1)throws SQLException{
		
		CartDetails obj = new CartDetails();
		
		obj.setCartdid(result.getInt(1));
		obj.setCartid(result.getInt(2));
		obj.setPid(result.getInt(3));
		obj.setQty(result.getInt(4));
		obj.setPname(result.getString(6));
		obj.setPprice(result.getInt(7));
		
		
		return obj;
		
	}
}
