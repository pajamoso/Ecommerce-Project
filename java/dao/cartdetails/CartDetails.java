package dao.cartdetails;

public class CartDetails {
	
	int cartdid;
	int cartid;
	int pid;
	int qty;
	String pname;
	int pprice;
	
	public int getCartdid() {
		return cartdid;
	}
	public void setCartdid(int cartdid) {
		this.cartdid = cartdid;
	}
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
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
	
}
