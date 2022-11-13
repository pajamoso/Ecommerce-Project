package dao.category;

public class CategoryInfo {

	private int cid;
	private String cname;
	
	
	public void displayCategory() {
		
		System.out.println(cid + "  " + cname);
		
	}
	
	public int getCid() {
		return cid;
	}


	public void setCid(int cid) {
		this.cid = cid;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}




}
