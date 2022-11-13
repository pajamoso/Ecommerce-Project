package dao.coupons;

public class CouponInfo extends PromoEvents {
	private int couid;
	private String couname;
	private int discount;
	private String prosd;
	private String proed;
	private String proname;
	
	public void displayCouponInfo()
	{
		System.out.println(proname + "  " + couid + "  " + couname + " " + discount + " " + prosd + " " + proed);
	}
	
	public int getCouid() {
		return couid;
	}
	public void setCouid(int couid) {
		this.couid = couid;
	}
	public String getCouname() {
		return couname;
	}
	public void setCouname(String couname) {
		this.couname = couname;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getProsd() {
		return prosd;
	}
	public void setProsd(String prosd) {
		this.prosd = prosd;
	}
	public String getProed() {
		return proed;
	}
	public void setProed(String proed) {
		this.proed = proed;
	}
}