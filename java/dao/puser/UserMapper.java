package dao.puser;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<UserInfo> {
	
	public UserInfo mapRow(ResultSet result,int arg1) throws SQLException{
		
		UserInfo obj = new UserInfo();
		
		obj.setUserid(result.getInt(1));
		obj.setUname(result.getString(2));
		obj.setUpass(result.getString(3));
		obj.setUadd(result.getString(4));
		obj.setUphone(result.getInt(5));
		
		return obj;
	}

}
