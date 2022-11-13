package dao.product;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductMapper implements RowMapper<ProductInfo> {
	
	@Override
	public ProductInfo mapRow(ResultSet result, int arg1) throws SQLException{
		
		ProductInfo obj = new ProductInfo();
		
		obj.setPid(result.getInt(1));
		obj.setPname(result.getString(2));
		obj.setPprice(result.getInt(3));
		obj.setCid(result.getInt(4));
		obj.setCname(result.getString(7));
		obj.setPimg(result.getString(5));
		
		return obj;
	}

}
