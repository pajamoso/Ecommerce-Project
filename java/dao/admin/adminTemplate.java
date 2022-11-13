package dao.admin;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import dao.admin.adminInfo;
import dao.admin.adminMapper;

public class adminTemplate {
	
public JdbcTemplate getTemplate() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		JdbcTemplate temp = (JdbcTemplate)context.getBean("tmp");
		return temp;

	}

public List<adminInfo> getAdmin(){
	
	JdbcTemplate temp = getTemplate();
	List<adminInfo> list = temp.query("Select * from pAdmin", new adminMapper());
	
	return list;
}


public List <String> getAname(){
	
	JdbcTemplate temp = getTemplate();
	List <String> list = temp.queryForList("Select aname from pAdmin", String.class);
	
	return list;
}

public List<String> getApass(){

JdbcTemplate temp = getTemplate();
List<String> list = temp.queryForList("Select apass from pAdmin", String.class);

return list;
}

public List<Integer> getAid(){

JdbcTemplate temp = getTemplate();
List<Integer> list = temp.queryForList("Select aid from pAdmin", Integer.class);

return list;
}

public List<adminInfo> validateAdmin(String aname,String apass){
	JdbcTemplate temp = getTemplate();
	List<adminInfo> list = temp.query("Select aname,apass from puser where aname=? and apass=?",new Object[] {aname,apass}, new adminMapper());
	return list;
}

}
