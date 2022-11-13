package dao.cart;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CartMapper implements RowMapper<CartInfo> {


public CartInfo mapRow(ResultSet result,int arg1) throws SQLException{
		
		CartInfo obj = new CartInfo();
		
		obj.setCartid(result.getInt(1));
		obj.setUserid(result.getInt(2));
		
		return obj;
	}
}
