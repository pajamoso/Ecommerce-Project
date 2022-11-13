package dao.admin;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class adminMapper implements RowMapper<adminInfo> {
	
	@Override
	public adminInfo mapRow(ResultSet result, int arg1) throws SQLException{
		
		adminInfo obj = new adminInfo();
		
		obj.setaID(result.getInt(1));
		obj.setaName(result.getString(2));
		obj.setaPass(result.getString(3));


		return obj;
		
	}

}
