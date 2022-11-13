package dao.product;

import java.util.List; 

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import dao.category.CategoryInfo;
import dao.category.CategoryMapper;
import dao.product.ProductInfo;
import dao.product.ProductMapper;

public class JdbcTemplateP {
	
	public JdbcTemplate getTemplate() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		JdbcTemplate temp = (JdbcTemplate)context.getBean("tmp");
		return temp;
	}
	
	public int getNewProductID()
	{
	
		JdbcTemplate temp = getTemplate();	
		Integer newpid = temp.queryForObject("select max(pid) + 1 from pproduct",Integer.class);
		return newpid;
	}
	
	
	
	public List<ProductInfo> getProducts(){
		
		JdbcTemplate temp = getTemplate();
		List<ProductInfo> list = temp.query("select * from pproduct p inner join pcategory c on p.cid = c.cid", new ProductMapper());
		
		return list;
	}
	
	
	public List<ProductInfo> getProducts(String cname)
	{
		JdbcTemplate temp = getTemplate();
		List<ProductInfo> list = temp.query("Select * from pproduct p inner join pcategory c on p.cid = c.cid where cname = ?",new Object[] {cname},new ProductMapper());
		return list;
	}
	
	public List<ProductInfo> getCategory(String cname)
	{
		JdbcTemplate temp = getTemplate();
		List<ProductInfo> list = temp.query("Select * from pproduct p inner join pcategory c on p.cid = c.cid where cname = ?",new Object[] {cname},new ProductMapper());
		return list;
	}
	
	
	public List<String> getAllUniqueCategoryName()
	{
		JdbcTemplate temp = getTemplate();
		List<String> list = temp.queryForList("Select unique(cname) from pproduct p inner join pcategory c on p.cid = c.cid", String.class);
		return list;
	}
	
	public List<CategoryInfo> getAllUniqueCID()
	{
		JdbcTemplate temp = getTemplate();
		List<CategoryInfo> list = temp.query("Select * from pcategory", new CategoryMapper());
		return list;
	}
	
	
	public ProductInfo searchProduct(int pid)
	{
		JdbcTemplate temp = getTemplate();	
		ProductInfo product = temp.queryForObject("Select * from pproduct p inner join pcategory c on p.cid = c.cid where pid = ?",
											new Object [] {pid},new ProductMapper());
		return product;
				
	}
	
	
	public int insertNewProduct(int pid,String pname,int pprice,int cid,String pimg)
	{
		JdbcTemplate temp = getTemplate();	
		int tr = temp.update("insert into pproduct values(?,?,?,?,?)",new Object[] {pid,pname,pprice,cid,pimg});
		System.out.println("----------row inserted");
		return tr;
	}
	
		
	
	public void deleteProduct(String pname)
	{
		JdbcTemplate temp = getTemplate();	
		temp.update("delete from pproduct where pname =?",new Object[] {pname});

	}
	
	
	public void updateProduct(String pname, int pprice )
	{
		JdbcTemplate temp = getTemplate();	
		temp.update("update pproduct set pprice=?  where pname =?",new Object[] {pprice, pname});
	}
	
	public String getImage(String pname) {
		JdbcTemplate temp = getTemplate();
		String list = temp.queryForObject("Select pimg from pproduct where pname =?",new Object[] {pname}, String.class);	
		return list;
	}

	
	public static void main(String[] args) {
		
		JdbcTemplateP obj = new JdbcTemplateP();
		
		System.out.println(obj.getProducts());
	}
	
	
}
