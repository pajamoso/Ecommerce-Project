package dao.products;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductsMapper implements RowMapper<Pproduct>{

	@Override
	public Pproduct mapRow(ResultSet result, int arg1) throws SQLException {
		
		
		Pproduct obj = new Pproduct();
		
		obj.setPid(result.getInt(1));
		obj.setPname(result.getString(2));
		obj.setPprice(result.getInt(3));
		obj.setCid(result.getInt(4));
		obj.setCname(result.getString(7));
		
		
		return obj;
	}
	
	
}