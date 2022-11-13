package dao.product;

public class ProductInfo {

	private int pid;
	private String pname;
	private int pprice;
	private int cid;
	private String cname;
	private String pimg;
	


	public void displayProducts() {
		
		System.out.println(pid + "  " + pname + "  " + pprice + "  " + cid + " " + cname);
	}


	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}


	public int getPprice() {
		return pprice;
	}


	public void setPprice(int pprice) {
		this.pprice = pprice;
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


	public String getPimg() {
		return pimg;
	}


	public void setPimg(String pimg) {
		this.pimg = pimg;
	}

	
	
}
