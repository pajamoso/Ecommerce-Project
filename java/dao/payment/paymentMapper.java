package dao.payment;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class paymentMapper implements RowMapper<paymentInfo> {
	
	public paymentInfo mapRow(ResultSet result, int arg1) throws SQLException{
		
		paymentInfo obj = new paymentInfo();
		
		obj.setPayid(result.getInt(1));
		obj.setCartId(result.getInt(2));
		obj.setTbilla(result.getDouble(3));
		obj.setTbilld(result.getDouble(4));
		
		return obj;
		
	}

}
