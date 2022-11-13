package dao.coupon;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class couponMapper implements RowMapper<couponInfo>{

	@Override
	public couponInfo mapRow(ResultSet result, int arg1) throws SQLException {
		
		couponInfo obj = new couponInfo();
		
		obj.setCoID(result.getInt(1));
		obj.setCoName(result.getString(2));
		obj.setCoDiscount(result.getInt(3));
		obj.setCopID(result.getInt(4));
		
		return obj;
	}

	
	
}
