package dao.coupons;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CouponMapper implements RowMapper<CouponInfo>{

	@Override
	public CouponInfo mapRow(ResultSet result, int arg1) throws SQLException {
		
		
		CouponInfo obj = new CouponInfo();
		
		obj.setProname(result.getString(1));
		obj.setCouid(result.getInt(2));
		obj.setCouname(result.getString(3));
		obj.setDiscount(result.getInt(4));
		obj.setProsd(result.getString(5));
		obj.setProed(result.getString(6));
		
		return obj;
	}

	
	
}