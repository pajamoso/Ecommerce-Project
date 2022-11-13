package dao.puser;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateU {

public JdbcTemplate getTemplate() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		JdbcTemplate temp = (JdbcTemplate)context.getBean("tmp");
		return temp;
	}
	
	public List<UserInfo> getUser(){
		
		JdbcTemplate temp = getTemplate();
		List<UserInfo> list = temp.query("Select * from pUser", new UserMapper());
		
		return list;
	}
	
	
public List <String> getUname(){
		
		JdbcTemplate temp = getTemplate();
		List <String> list = temp.queryForList("Select uname from pUser", String.class);
		
		return list;
	}


public List<String> getUpass(){
	
	JdbcTemplate temp = getTemplate();
	List<String> list = temp.queryForList("Select upass from pUser", String.class);
	
	return list;
}

public List<Integer> getUid(){
	
	JdbcTemplate temp = getTemplate();
	List<Integer> list = temp.queryForList("Select userid from pUser", Integer.class);
	
	return list;
}

public String selectUname(int userId) {
	
	JdbcTemplate temp = getTemplate();
	String sname = temp.queryForObject("Select uname from puser where userid=?",new Object[] {userId}, String.class);
	return sname;
}

public String getEmail(){
	
	JdbcTemplate temp = getTemplate();
	String emailShow = temp.queryForObject("select uname from pUser where userid = 1",String.class);
	
	return emailShow;
}
	
	public int getUserID()
	{
	
		JdbcTemplate temp = getTemplate();	
		Integer newuserid = temp.queryForObject("select max(userid) + 1 from pUser",Integer.class);
		return newuserid;
	}
	
	
	
	public void insertNewUser(int userid,String uname,String upass,String uadd,int uphone)
	{
		JdbcTemplate temp = getTemplate();	
		temp.update("Insert into pUser values(?,?,?,?,?)",new Object[] {userid,uname,upass,uadd,uphone});
		System.out.println("----------row inserted");
	}
	
	public List<UserInfo> validateUser(String uname,String upass){
		
		JdbcTemplate temp = getTemplate();
		List<UserInfo> list = temp.query("Select uname,userpass from pUser where uname=? and userpass=?",new Object[] {uname,upass}, new UserMapper());
		return list;
	}
	public static void main(String[] args) {
		
		JdbcTemplateU obj = new JdbcTemplateU();
		

	}
}
