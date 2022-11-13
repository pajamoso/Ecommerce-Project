package pkg;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.admin.adminInfo;
import dao.admin.adminTemplate;
import dao.cart.CartInfo;
import dao.cart.JdbcTemplateCart;
import dao.cartdetails.CartDetails;
import dao.cartdetails.CartJdbc;
import dao.category.CategoryInfo;
import dao.category.JdbcTemplateC;
import dao.coupon.couponInfo;
import dao.coupon.couponTemplate;
import dao.coupons.CouponInfo;
import dao.coupons.JdbcTemplateExample;
import dao.event.eventInfo;
import dao.event.eventTemplate;
import dao.payment.JdbcTemplatePay;
import dao.product.JdbcTemplateP;
import dao.product.ProductInfo;
import dao.products.JdbcProduct;
import dao.products.Pproduct;
import dao.puser.JdbcTemplateU;
import dao.puser.UserInfo;

@Controller
public class oneControllerToRuleThemAll {

	// USER USER USER USER USER USER USER USER USER USER USER USER USER USER USER
	// USER USER USER USER USER USER USER

	JdbcTemplateP adminproductTemplate = new JdbcTemplateP();
	adminTemplate adminTemplate = new adminTemplate();
	couponTemplate couponTemplate = new couponTemplate();
	eventTemplate eventTemplate = new eventTemplate();
	JdbcTemplateCart cartTemplate = new JdbcTemplateCart();
	CartJdbc CartJdbc = new CartJdbc();
	JdbcTemplatePay paytemplate = new JdbcTemplatePay();
	
		JdbcTemplateU userTemplate = new JdbcTemplateU();
		private static final String Upload_Directory = "/resources/images";
		int userId;
		int cartId;
		String unameID;
		
		int total;
		int disc;
		int tdisc;
		double totaltotal;
		double discdisc;
		double totaldisc;
		double discounted;
		String cname;



		@RequestMapping("/loginhome")
		public String welcome() {

			return "login";
		}

		@RequestMapping("/testing")
		public String sucreg() {

			return "testing";
		}

//		@RequestMapping("/index")
//		public String index() {
//			
//			return "index";
//		}

		@RequestMapping("/fail")
		public String regsucc() {
			return "fail";
		}

		@RequestMapping("/register")
		public String registerUser(HttpServletRequest request, Model model) {
			// int cid = Integer.parseInt(request.getParameter("cid"));
						int userid = userTemplate.getUserID();
						String uname = request.getParameter("uname");
						String upass = request.getParameter("upass");
						String uadd = request.getParameter("uadd");
						int uphone = Integer.parseInt(request.getParameter("uphone"));

						userTemplate.insertNewUser(userid, uname, upass, uadd, uphone);
						
						int cartid = cartTemplate.getUserCartID();
						int useridc = userid;
						cartTemplate.insertNewUserCart(cartid,useridc);
						

						return "redirect:/testing";
		}

		@RequestMapping("/login")
		public String loginuser(HttpServletRequest request, Model model) {

			String uname = request.getParameter("uname");
			String upass = request.getParameter("upass");
		
			
			model.addAttribute("uname", uname);

			if (userValid(uname, upass)) {
				// System.out.println("from the uservalid: " + userID);
				return "redirect:/?login="+1;
			} else {
				return "redirect:/loginhome?error="+1;
			}
			
			
		}

		private boolean userValid(String uname, String upass) {

			List<UserInfo> list = userTemplate.getUser();
			List<CartInfo> list1 = cartTemplate.viewUsercart();
			

			boolean isValid = false;

			for (int i = 0; i < list.size(); i++) {
				String unamedb = list.get(i).getUname();
				String upassdb = list.get(i).getUpass();
				int userIDdb = list.get(i).getUserid();
					if (unamedb.equals(uname)) {
						if (upassdb.equals(upass)) {
							for(int l = 0; l<list1.size(); l++) {
								int cartIDdb = list1.get(l).getCartid();								
							userId = userIDdb;
							cartId = cartTemplate.selectCartId(userId);
							unameID = userTemplate.selectUname(userId);
							isValid = true;
							
						}
						break;
					}
				}
				
			}
			return isValid;

		}
		
		@RequestMapping("/user-about-us")
		public String aboutusPage() {
			
			return "user-about-us";
		}
		
		JdbcTemplateExample objtemplate = new JdbcTemplateExample();

		
		@RequestMapping("/user-promo-events")
		public String showAllCoupons(Model model)
		{
			List<String> uniqueEvents = objtemplate.getAllUniqueEvents();
			List<CouponInfo> list = objtemplate.getAllCoupons();
			 
			for(int i =0; i < list.size(); i++) {		
				
				int disc = list.get(i).getDiscount();
				int tdisc = disc/100;
				
				model.addAttribute("tdisc",tdisc);
				model.addAttribute("allcoupons",list);
				model.addAttribute("uc",uniqueEvents);
				model.addAttribute("tc",list.size());
				
			}
			
			if(checkCart(cartId)) {
				
				int cartT = CartJdbc.getCartTotal(cartId);
			
				model.addAttribute("username", userId);
				model.addAttribute("usercart", cartId);
				model.addAttribute("uname", unameID);
				model.addAttribute("cartT",cartT);
			
			} else {
				
				int cartT = 0;
				
				model.addAttribute("username", userId);
				model.addAttribute("usercart", cartId);
				model.addAttribute("uname", unameID);
				model.addAttribute("cartT",cartT);
			}
			
			return "user-promo-events";
		}
		
		@RequestMapping("/viewFilterCoupons")
		public String showFilterCustomerDetails(HttpServletRequest request, Model model)
		{
			List<String> uniqueEvents = objtemplate.getAllUniqueEvents();
			
			String filterevents = request.getParameter("filterevents");
			
			List<CouponInfo> list =null;
			
			if(!filterevents.equals("Show All"))
			{
				list = objtemplate.getAllCoupons(filterevents);
			}
			else
			{
				list = objtemplate.getAllCoupons();
			}
			
			model.addAttribute("bc",filterevents);
			model.addAttribute("allcoupons",list);
			model.addAttribute("uc",uniqueEvents);
			model.addAttribute("tc",list.size());
			
if(checkCart(cartId)) {
				
				int cartT = CartJdbc.getCartTotal(cartId);
			
				model.addAttribute("username", userId);
				model.addAttribute("usercart", cartId);
				model.addAttribute("uname", unameID);
				model.addAttribute("cartT",cartT);
			
			} else {
				
				int cartT = 0;
				
				model.addAttribute("username", userId);
				model.addAttribute("usercart", cartId);
				model.addAttribute("uname", unameID);
				model.addAttribute("cartT",cartT);
			}
				
			return "user-promo-events";
		}
		
		//CART CART CART CART CART
		@RequestMapping("/cart")
		 public String cartPage(Model model) {
			

	        if(checkCart(cartId)) {
	            List<CartDetails> list = CartJdbc.viewAllCart(cartId);
	            total = CartJdbc.getTotal(cartId);
	            int cartT = CartJdbc.getCartTotal(cartId);
	           
	            
	            if(totaldisc == 0) {
	            	
	            	totaldisc = total;
	            }
	          
	            model.addAttribute("total", total);
	            model.addAttribute("totaldisc", totaldisc);
	            model.addAttribute("discounted", discounted);
	            model.addAttribute("cart",list);
	            model.addAttribute("cartT",cartT);

	            return "cart";

	        } else if(userId == 0) {

	            return "redirect:/loginhome";
	        }

	        return "cart";
		}
		
		@RequestMapping("/discount")
        public String discount(HttpServletRequest request, Model model) {
            List<CartDetails> list = CartJdbc.viewAllCart(cartId);

            int total = CartJdbc.getTotal(cartId);
//
            totaltotal = total;
//
            
            model.addAttribute("cart",list);
            int discount = Integer.parseInt(request.getParameter("promo-checkbox"));
            
            if(discount != 0) {
            	
            	 discdisc = CartJdbc.getDiscount(discount);
                 discounted = totaltotal*(discdisc/100);
                 totaldisc=totaltotal-discounted;
                 model.addAttribute("totaldisc", totaldisc);
                 model.addAttribute("discounted", discounted);
     
             return "redirect:/cart";

            } else {
            	 
            	 model.addAttribute("total", total);
                 model.addAttribute("totaldisc", totaldisc);
                 model.addAttribute("discounted", discounted);
                 return "redirect:/cart";
            }
           
        
        }


		
		@RequestMapping("/deleteCart")
	    public String deleteCart(HttpServletRequest request, Model model) {

	        int pid = Integer.parseInt(request.getParameter("ppid"));
	        int cid = cartId;
	        CartJdbc.deleteCart(pid, cid);

	        return "redirect:/cart";
		}
		@RequestMapping("/addtocart")
		public String addToCart(HttpServletRequest request, Model model) {

	        if(userId == 0) {
	            return "redirect:/loginhome";
	        }

	        int cartdid = CartJdbc.getCartDetailsID();
	        //int cartId = cartTemplate.selectCartId(userId);
	        int pid = Integer.parseInt(request.getParameter("pid"));
	        int qty = 1;

	            //cartdTemplate.validateCart(cartId, pid);
	        CartJdbc.insertToCart(cartdid, cartId, pid, qty);


	        return "redirect:/products";	
			
		}
		
		@RequestMapping("/product-details/addtocart")
		public String addtocartP(HttpServletRequest request, @PathVariable int pid, Model model){
			
			   if(userId == 0) {
		            return "redirect:/loginhome";
		        }

		        int cartdid = CartJdbc.getCartDetailsID();
		        //int cartId = cartTemplate.selectCartId(userId);    
		        int qty = 1;

		            //cartdTemplate.validateCart(cartId, pid);
		        CartJdbc.insertToCart(cartdid, cartId, pid, qty);


		        return "redirect:/products";	
				
			}
		
		
		@RequestMapping("/updateQTY")
		public String updateQty(HttpServletRequest request, Model model) {
			
			int qty = Integer.parseInt(request.getParameter("qt"));
			int pid = Integer.parseInt(request.getParameter("pid"));
			
			CartJdbc.updateQty(qty,pid,cartId);
			
			return "redirect:/cart";
		}
		
		@RequestMapping("/user-payment")
		public String paymentPage(HttpServletRequest request, Model model) {
			
			  List<CartDetails> list = CartJdbc.viewAllCart(cartId);
	            int total = CartJdbc.getTotal(cartId);
	            int cartT = CartJdbc.getCartTotal(cartId);
	            model.addAttribute("total", total);
	            model.addAttribute("cart",list);
	            model.addAttribute("cartT",cartT);
	            model.addAttribute("totaldisc", totaldisc);
	            
				model.addAttribute("discounted", discounted);
				model.addAttribute("username", userId);
				model.addAttribute("usercart", cartId);
				
				model.addAttribute("uname", unameID);
	
			
			return "user-payment";
		}

		
		@RequestMapping("/pushPayment")
		public String pushPayment(HttpServletRequest request, Model model) {
			
			JdbcTemplatePay payTemplate = new JdbcTemplatePay();
			
			int payid = payTemplate.newPayId();
			
			int cartPid = Integer.parseInt(request.getParameter("cartid"));
			double ptotal = Double.parseDouble(request.getParameter("total"));
			double totald = Double.parseDouble(request.getParameter("totaldisc"));
			int userId = Integer.parseInt(request.getParameter("userId"));
			
			payTemplate.insertPayment(payid, cartPid, ptotal, totald);
			cartTemplate.deleteCart(cartPid);

			return "redirect:/";
			
			
		}

	
	// USER USER USER USER USER USER USER USER USER USER USER USER USER USER USER
	
	// USER PRODUCT USER PRODUCT USER PRODUCT USER PRODUCT USER PRODUCT USER PRODUCT USER PRODUCT
		
		private boolean checkCart(int cartId){

	        List<CartDetails> list = CartJdbc.viewAllCart(cartId);

	        boolean inCart = false;

	        for(int i = 0; i < list.size(); i++){

	            int cartIDb = list.get(i).getCartid();

	            if(cartId == cartIDb){

	                inCart = true;
	                break;
	            }


	        }
	            return inCart;

	    }
		
		
	@RequestMapping("/")
	public String indexPage(HttpServletRequest request, Model model) {

		
		
        if(checkCart(cartId)) {
			
			int cartT = CartJdbc.getCartTotal(cartId);
			
			model.addAttribute("username", userId);
			model.addAttribute("usercart", cartId);
			model.addAttribute("uname", unameID);
			model.addAttribute("cartT",cartT);
		
		} else {
			
			int cartT = 0;
			
			model.addAttribute("username", userId);
			model.addAttribute("usercart", cartId);
			model.addAttribute("uname", unameID);
			model.addAttribute("cartT",cartT);
		}
        
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		
		userId = 0;
		unameID = null;
		cartId = 0;
		
		return "redirect:/loginhome";
	}
	
	
	JdbcProduct userproductTemplate = new JdbcProduct();
	
	@RequestMapping("/products")
	public String productPage(HttpServletRequest request, Model model) {
		
		List<String> uniqueCategory = userproductTemplate.getAllUniqueCategory();
		model.addAttribute("uniqueCategory", uniqueCategory);

		List<Pproduct> list = userproductTemplate.getAllProduct();
		model.addAttribute("allproducts", list);
		
		String filtercategory = request.getParameter("filtercategory");
		model.addAttribute("aa", filtercategory);
		
		
		
		String puser = userTemplate.getEmail();
		model.addAttribute("puser", puser);
		
			if(checkCart(cartId)) {
			
			int cartT = CartJdbc.getCartTotal(cartId);
		
			model.addAttribute("username", userId);
			model.addAttribute("usercart", cartId);
			model.addAttribute("uname", unameID);
			model.addAttribute("cartT",cartT);
		
		} else {
			
			int cartT = 0;
			
			model.addAttribute("username", userId);
			model.addAttribute("usercart", cartId);
			model.addAttribute("uname", unameID);
			model.addAttribute("cartT",cartT);
		}
		return "products";
	}
	

	@RequestMapping("/filterCategory")
	public String showFilterCategoryDetails(HttpServletRequest request, Model model) {
		
		List<String> uniqueCategory = userproductTemplate.getAllUniqueCategory();
		String filtercategory = request.getParameter("filtercategory");

		List<Pproduct> list = null;

		if (!filtercategory.equals("All Brands")) {
			list = userproductTemplate.getAllCategory(filtercategory);
		} else {
			list = userproductTemplate.getAllProduct();
		}
		
		model.addAttribute("allproducts", list);
		model.addAttribute("uniqueCategory", uniqueCategory);
		
		model.addAttribute("aa", filtercategory);

		return "products";
	}
	

//	@RequestMapping("/product-details")
//	public String productdetails(Model model) {
//		
//		return "productdetails";
//	}
//	

	
	@RequestMapping("/product-details/{pid}")
	public String productdetails(HttpServletRequest request, @PathVariable int pid, Model model) {

		Pproduct details = userproductTemplate.getProductID(pid);
		model.addAttribute("details", details);
		
//		int cartT = CartJdbc.getCartTotal(cartId);
//		model.addAttribute("cartT",cartT);
		
		List<Pproduct> list = userproductTemplate.getAllProduct();
		model.addAttribute("userproducts", list);
		
if(checkCart(cartId)) {
			
			int cartT = CartJdbc.getCartTotal(cartId);
		
			model.addAttribute("username", userId);
			model.addAttribute("usercart", cartId);
			model.addAttribute("uname", unameID);
			model.addAttribute("cartT",cartT);
		
		} else {
			
			int cartT = 0;
			
			model.addAttribute("username", userId);
			model.addAttribute("usercart", cartId);
			model.addAttribute("uname", unameID);
			model.addAttribute("cartT",cartT);
		}

		return "productdetails";
	}
	
	
	
	// USER PRODUCT USER PRODUCT USER PRODUCT USER PRODUCT USER PRODUCT USER PRODUCT USER PRODUCT



	// ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN
	int adminID;

	public static void bruhBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}



	@RequestMapping("/Dashboard")
	public String showAllProductstest(Model model) {
		
		List<ProductInfo> list = adminproductTemplate.getProducts();
		List<couponInfo> clist = couponTemplate.getAllCoupons();
		List<eventInfo> elist = eventTemplate.getAllEvents();
		
		model.addAttribute("allproducts",list);
		model.addAttribute("count",list.size());
		model.addAttribute("cpcount",clist.size());
		model.addAttribute("evcount",elist.size());
		return "Dashboard";
	}
	
	@RequestMapping("/Product")
	public String showAllProductDetails(HttpServletRequest request, Model model)
	{
		List<String> uniqueID = adminproductTemplate.getAllUniqueCategoryName();
		List<ProductInfo> list = adminproductTemplate.getProducts();
		List<CategoryInfo> uniqueCID = adminproductTemplate.getAllUniqueCID();
		
		model.addAttribute("allproducts",list);
		model.addAttribute("ui",uniqueID);		
		model.addAttribute("ucid", uniqueCID);
		
		return "Product";
	}
	@RequestMapping("/preview")
	public String showPreview(HttpServletRequest request, Model model)
	{
		List<String> uniqueID = adminproductTemplate.getAllUniqueCategoryName();
		List<ProductInfo> list = adminproductTemplate.getProducts();
		List<CategoryInfo> uniqueCID = adminproductTemplate.getAllUniqueCID();
		String showImage = request.getParameter("ipname");
		String showFunc = adminproductTemplate.getImage(showImage);
		
		model.addAttribute("allproducts",list);
		model.addAttribute("ui",uniqueID);	
		model.addAttribute("ucid", uniqueCID);
		model.addAttribute("show", showFunc);
		
		return "Product";
	}
	@RequestMapping("/adminlog")
	public String adminlog() {
		return "Admin";
	}		
	@RequestMapping("/admin")
	public String loginadmin(HttpServletRequest request, Model model) {

		
		String aname = request.getParameter("aname");
		String apass = request.getParameter("apass");

		if (adminValid(aname, apass)) {
			// System.out.println("from the uservalid: " + userID);
			return "redirect:/Dashboard";
		} else {

			// adminController.infoBox("You have some error, my friend. Taking you back.",
			// "WEE OOH WEE OOH WEE OOH");
			return "redirect:/adminlog";
		}
	}

	private boolean adminValid(String aname, String apass) {

		List<adminInfo> list = adminTemplate.getAdmin();

		boolean isValid = false;

		for (int i = 0; i < list.size(); i++) {
			int adminIDdb = list.get(i).getaID();
			String anamedb = list.get(i).getaName();
			String apassdb = list.get(i).getaPass();
			if (anamedb.equals(aname)) {
				if (apassdb.equals(apass)) {
					adminID = adminIDdb;
					isValid = true;
					break;
				}
				break;
			}
		}
		return isValid;

	}
	
	// ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN ADMIN
	
	// ADMIN PRODUCT ADMIN PRODUCT ADMIN PRODUCT ADMIN PRODUCT ADMIN PRODUCT ADMIN PRODUCT
	
	
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
	@RequestMapping("/viewFilterProducts")
	public String showFilterProductDetails(HttpServletRequest request, Model model)
	{
		try {
		List<String> uniqueID = adminproductTemplate.getAllUniqueCategoryName();
		String filtercname = request.getParameter("filtercname");
		
		List<ProductInfo> list=null;
		
		if(!filtercname.equals("All Brands"))
		{
			list = adminproductTemplate.getCategory(filtercname);
		}
		else
		{
			list = adminproductTemplate.getProducts();
		}
		model.addAttribute("allproducts",list);
		model.addAttribute("ui",uniqueID);
		}catch(Exception e) {
			oneControllerToRuleThemAll.infoBox("You have some error, my friend. Taking you back.", "WEE OOH WEE OOH WEE OOH");
		}
		return "Product";
	}
	
	
	
	@RequestMapping("/insertproduct")
	public String insertNewProductForm(Model model)
	{
		
		return "redirect:/Product";
	}
	
	
	
	@RequestMapping(value="saveNewProduct", method=RequestMethod.POST)
	public String saveNewProduct( @RequestParam CommonsMultipartFile file,
			HttpSession session, HttpServletRequest request)
	{
		try {
		int pid = adminproductTemplate.getNewProductID();
		String pname = request.getParameter("pname");
		int pprice = Integer.parseInt(request.getParameter("pprice"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		String pimg = file.getOriginalFilename();
		
		ServletContext context = session.getServletContext();
		String path = context.getRealPath(Upload_Directory);
		String filename = file.getOriginalFilename();
		
		adminproductTemplate.insertNewProduct(pid, pname, pprice, cid, pimg);
		
		byte[] bytes = file.getBytes();
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path + File.separator + filename)));
		stream.write(bytes);
		stream.flush();
		stream.close();
		
		}catch(Exception e) {
			oneControllerToRuleThemAll.infoBox("You have some error, my friend. Taking you back.", "WEE OOH WEE OOH WEE OOH");
		}
		return "redirect:/Product";
	}
	

	
	@RequestMapping("/updateProductDetail")
	public String updateProductDetail(HttpServletRequest request)
	{
		try{
			String pname = request.getParameter("upname");
		int pprice = Integer.parseInt(request.getParameter("upprice"));

		adminproductTemplate.updateProduct(pname, pprice);
		}catch(Exception e) {
			oneControllerToRuleThemAll.infoBox("You have some error, my friend. Taking you back.", "WEE OOH WEE OOH WEE OOH");
		}
		return "redirect:/Product";  
	}
	
	@RequestMapping("/deleteProduct")
	public String deleteProduct(HttpServletRequest request)
	{
		try {
		String pname = request.getParameter("dpname");
		adminproductTemplate.deleteProduct(pname);
		}catch(Exception e) {
			oneControllerToRuleThemAll.infoBox("You have some error, my friend. Taking you back.", "WEE OOH WEE OOH WEE OOH");
		}
		return "redirect:/Product";
	}
	// ADMIN PRODUCT ADMIN PRODUCT ADMIN PRODUCT ADMIN PRODUCT ADMIN PRODUCT ADMIN PRODUCT
	
	//ADMIN EVENT ADMIN EVENT ADMIN EVENT ADMIN EVENT ADMIN EVENT
	
		
		
		@RequestMapping("/Event")
		public String eventDashboard(Model model) {	
			
	try {
			List<CategoryInfo> uniqueCID = adminproductTemplate.getAllUniqueCID();			
			List<eventInfo> list = eventTemplate.getAllEvents();
			
			
			model.addAttribute("ucid", uniqueCID);
			model.addAttribute("allEvents",list);
		}catch(Exception e) {
			oneControllerToRuleThemAll.infoBox("You have some error, my friend. Taking you back.", "WEE OOH WEE OOH WEE OOH");
		}
			return "Event";
		}
		
		public static Date convertUtilDateToSqlDate(java.util.Date date){
	        if(date != null) {
	            Date sqlDate = new Date(date.getTime());
	            return sqlDate;
	        }
	        return null;
	    }
		
		@RequestMapping("/saveNewEvent")
		public String saveNewEvent(HttpServletRequest request,Model model)
		{
			try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
			int eID = eventTemplate.insertNewEventID();
			String eName = request.getParameter("textName");
			String esDate = request.getParameter("dateStart");
			String edDate = request.getParameter("dateEnd");
			int ecID = Integer.parseInt(request.getParameter("textCatID"));
			
			
			eventTemplate.insertNewEvent(eID, eName, esDate, edDate, ecID);
			}catch(Exception e) {
				oneControllerToRuleThemAll.infoBox("Error occurred when saving an Event. Taking you back.", "Event Insert Error");
			}
			return "redirect:/Event";  
		}
		
		@RequestMapping("/updateEvent")
		public String updateEvent(HttpServletRequest request)
		{
		try {
			String ueName = request.getParameter("utextName");
			String uesDate = request.getParameter("udateStart");
			String uedDate = request.getParameter("udateEnd");
			
			eventTemplate.updateEvent(ueName, uesDate, uedDate);
		}catch(Exception e) {
			oneControllerToRuleThemAll.infoBox("Error occurred when updating an Event. Taking you back.", "Event Update Error");
		}
			return "redirect:/Event";  
		}
		
		@RequestMapping("/deleteEvent")
		public String deleteEvent(HttpServletRequest request)
		{
			try {
			String dename = request.getParameter("dename");
			eventTemplate.deleteEvent(dename);
			}catch(Exception e) {
				oneControllerToRuleThemAll.infoBox("Error occurred when deleting an Event. Taking you back.", "Event Delete Error");
			}
			return "redirect:/Event";
		}

	//ADMIN EVENT ADMIN EVENT ADMIN EVENT ADMIN EVENT ADMIN EVENT
	
	//ADMIN COUPON ADMIN COUPON ADMIN COUPON ADMIN COUPON ADMIN COUPON ADMIN COUPON
	public static void coupBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

	
	@RequestMapping("/Coupon")
	public String couponDashboard(Model model) {
		
		
		try {
			
			List<eventInfo> uniquePrid = couponTemplate.getAllUniquePrid();		
			List<couponInfo> list = couponTemplate.getAllCoupons();
			
			model.addAttribute("uprid", uniquePrid);
			model.addAttribute("allCoupons",list);
		}catch(Exception e) {
			oneControllerToRuleThemAll.infoBox("You have some error, my friend. Taking you back.", "WEE OOH WEE OOH WEE OOH");
		}
		return "Coupon";
	}
	
	@RequestMapping("/saveNewCoupon")
	public String saveNewCoupon(HttpServletRequest request,Model model)
	{
	try {
		int coID = couponTemplate.insertNewCouponID();
		String coName = request.getParameter("cotextName");
		int coDiscount = Integer.parseInt(request.getParameter("cotextDiscount"));
		int copID = Integer.parseInt(request.getParameter("cotextPromoID"));
		
		couponTemplate.insertNewCoupon(coID, coName, coDiscount, copID);
	}catch(Exception e) {
		oneControllerToRuleThemAll.infoBox("You have some error, my friend. Taking you back.", "WEE OOH WEE OOH WEE OOH");
	}
		return "redirect:/Coupon";  
	}
	
	@RequestMapping("/updateCoupon")
	public String updateCoupon(HttpServletRequest request)
	{
	
		try {
		String ucoName = request.getParameter("ucotextName");
		int ucoDiscount = Integer.parseInt(request.getParameter("ucotextDiscount"));
		int ucopID = Integer.parseInt(request.getParameter("ucotextPromoID"));
		
		couponTemplate.updateCoupon(ucoName, ucoDiscount, ucopID);
	}catch(Exception e) {
		oneControllerToRuleThemAll.infoBox("You have some error, my friend. Taking you back.", "WEE OOH WEE OOH WEE OOH");
	}
		return "redirect:/Coupon";  
	}
	
	@RequestMapping("/deleteCoupon")
	public String deleteCoupon(HttpServletRequest request)
	{
		try {
		String dcname = request.getParameter("dcname");
		couponTemplate.deleteCoupon(dcname);
	}catch(Exception e) {
		oneControllerToRuleThemAll.infoBox("You have some error, my friend. Taking you back.", "WEE OOH WEE OOH WEE OOH");
	}
		return "redirect:/Coupon";
	}
	


	
	//BRYCE BRYCE ADD TO CART ADD TO CART ADD TO CART ADD TO CART ADD TO CART ADD TO CART ADD TO CART
	
//	int userid;
//	int cartid;
//	int cartdid;
//	int pid;
// int qty;
	
	

		
	}
	
	

