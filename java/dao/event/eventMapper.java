package dao.event;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class eventMapper implements RowMapper<eventInfo>{

	@Override
	public eventInfo mapRow(ResultSet result, int arg1) throws SQLException {
		
		eventInfo obj = new eventInfo();
		
		obj.seteID(result.getInt(1));
		obj.seteName(result.getString(2));
		obj.setEsDate(result.getString(3));
		obj.setEdDate(result.getString(4));
		obj.setEcID(result.getString(5));
		
		return obj;
	}

	
	
}
