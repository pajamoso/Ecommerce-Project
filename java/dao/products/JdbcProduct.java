package dao.products;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcProduct {
	public JdbcTemplate getTemplate() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		JdbcTemplate temp = (JdbcTemplate) context.getBean("tmp");
		return temp;
	}

	public List<Pproduct> getAllProduct() {
		JdbcTemplate temp = getTemplate();
		List<Pproduct> list = temp.query("Select * from pproduct p  inner join pcategory c on p.cid = c.cid", new ProductsMapper());
		return list;
	}
	
	public List<Pproduct> getAllProduct(String cname) {
		JdbcTemplate temp = getTemplate();
		List<Pproduct> list = temp.query("Select *  from pproduct p  inner join pcategory c on p.cid = c.cid where cname=?", new Object[] { cname },
				new ProductsMapper());
		return list;
	}
	
	public List<Pproduct> getAllCategory(String cname) {
		JdbcTemplate temp = getTemplate();
		List<Pproduct> list = temp.query("Select *  from pproduct p  inner join pcategory c on p.cid = c.cid where cname=?", new Object[] { cname },
				new ProductsMapper());
		return list;
	}
	
//	public List<Pproduct> getProductID(int pid) {
//		JdbcTemplate temp = getTemplate();
//		List<Pproduct> list = temp.query("Select *  from pproduct p  inner join pcategory c on p.cid = c.cid where pid=?", new Object[] { pid },
//				new ProductsMapper());
//		return list;
//	}
	
	public Pproduct getProductID(int pid) {
		JdbcTemplate temp = getTemplate();
		Pproduct product = temp.queryForObject("Select * from pproduct p  inner join pcategory c on p.cid = c.cid where pid = ?", new Object[] { pid },
				new ProductsMapper());
		return product;
	}

	public List<String> getAllUniqueCategory() {
		JdbcTemplate temp = getTemplate();
		List<String> list = temp.queryForList("Select unique(cname) from pproduct p  inner join pcategory c on p.cid = c.cid", String.class);
		return list;
	}
	
	public List<String> getSpecificProduct() {
		JdbcTemplate temp = getTemplate();
		List<String> list = temp.queryForList("Select unique(pname) from pproduct p  inner join pcategory c on p.cid = c.cid", String.class);
		return list;

	}
	
	public Pproduct searchProducts(String pname) {
		JdbcTemplate temp = getTemplate();
		Pproduct product = temp.queryForObject("Select * from pproduct where pname like '%or%' = ? ",
				new Object[] { pname }, new ProductsMapper());
		return product;
	}

	public static void main(String s[]) {
		JdbcProduct userproductTemplate = new JdbcProduct();

		// obj.insert(sc.nextInt(),sc.next(),sc.next(),sc.nextInt());
		// obj.update(sc.nextInt(), sc.nextInt());
		// obj.select();

		// obj.searchCustomer(sc.nextInt());

//		System.out.println(obj.getAllProduct().size());
		 
		System.out.println(userproductTemplate.getAllProduct());
		
	}
}
