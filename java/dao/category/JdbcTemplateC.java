package dao.category;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateC {

	public JdbcTemplate getTemplate() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		JdbcTemplate temp = (JdbcTemplate)context.getBean("tmp");
		return temp;
	}
	
	public List<CategoryInfo> getCategory() {
		
		JdbcTemplate temp = getTemplate();
		
		List<CategoryInfo> list = temp.query("Select * from pcategory", new CategoryMapper());
		
		return list;
		
	}

	public static void main(String[] args) {
		
		JdbcTemplateC obj = new JdbcTemplateC();
		
		obj.getCategory();
		System.out.println(obj.getCategory().size());
	}
	
}
