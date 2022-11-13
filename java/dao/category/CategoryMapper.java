package dao.category;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CategoryMapper implements RowMapper<CategoryInfo> {
	
	@Override
	public CategoryInfo mapRow(ResultSet result, int arg1) throws SQLException{
		
		CategoryInfo obj = new CategoryInfo();
		
		obj.setCid(result.getInt(1));
		obj.setCname(result.getString(2));


		return obj;
		
	}

}
