package ua.itea.servlets;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ua.itea.controllers.DBWorker;
import ua.itea.controllers.ProductController;
import ua.itea.controllers.UserController;
import ua.itea.models.Product;

@Controller
@RequestMapping("/products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductServlet() {
	}

//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		ProductController pc = new ProductController(new DBWorker());
//		RequestDispatcher rd = null;
//		if (request.getParameter("productSelected") != null) {
//			rd = request.getRequestDispatcher("WEB-INF/views/singleProduct.jsp");
//			Product product = pc.getProduct(Integer.parseInt(request.getParameter("productSelected")));
//			request.setAttribute("product", product);
//		}else if(request.getParameter("search") != null) {
//			System.out.println("we are searching" + request.getParameter("search"));
//			rd = request.getRequestDispatcher("WEB-INF/views/productsView.jsp");
//			List<Product> products = pc.search(request.getParameter("search"));
//			request.setAttribute("products", products);
//		} else {
//			rd = request.getRequestDispatcher("WEB-INF/views/productsView.jsp");
//			List<Product> products = pc.getAllProducts();
//			request.setAttribute("products", products);
//		}
//		HttpSession session = request.getSession();
//		UserController.logout(request, session, response);
//		request.setAttribute("session", session);
//		try {
//			rd.forward(request, response);
//		} catch (IllegalStateException ex) {
//		}
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		ProductController pc = new ProductController(new DBWorker());
//		HttpSession session = request.getSession();
//		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/productsView.jsp");
//		if (request.getParameter("search") != null) {
//			String phones = request.getParameter("phones") == null ? "0" : request.getParameter("phones");
//			String laptops = request.getParameter("laptops") == null ? "0" : request.getParameter("laptops");
//			String watches = request.getParameter("watches") == null ? "0" : request.getParameter("watches");
//			int[] categoryID = { Integer.parseInt(phones), Integer.parseInt(laptops), Integer.parseInt(watches) };
//			List<Product> products = pc.getSelectedProducts(categoryID);
//			request.setAttribute("products", products);
//			request.setAttribute("phones", phones.equals("1") ? "checked" : "");
//			request.setAttribute("laptops", laptops.equals("2") ? "checked" : "");
//			request.setAttribute("watches", watches.equals("3") ? "checked" : "");
//		} else if (request.getParameter("productSelected") != null) {
//			rd = request.getRequestDispatcher("WEB-INF/views/singleProduct.jsp");
//			Product product = pc.getProduct(Integer.parseInt(request.getParameter("productSelected")));
//			request.setAttribute("product", product);
//		}
//		UserController.logout(request, session, response);
//		request.setAttribute("session", session);
//		try {
//			rd.forward(request, response);
//		} catch (IllegalStateException ex) {
//		}
//	}

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}

	@RequestMapping(method = RequestMethod.GET, params = { "productSelected", "search", "logout" })
	public String get(ModelMap model, @RequestParam("productSelected") String productSelected,
			@RequestParam("search") String search, @RequestParam("logout") String logout) {
		ProductController pc = new ProductController(new DBWorker());
		if (productSelected != null) {
			Product product = pc.getProduct(Integer.parseInt(productSelected));
			model.addAttribute("product", product);
			return "singleProduct";
		} else if (search != null) {
			List<Product> products = pc.search(search);
			model.addAttribute("products", products);
			return "productsView";
		} else {
			List<Product> products = pc.getAllProducts();
			model.addAttribute("products", products);
			HttpSession session = session();
			UserController.logout(session, logout); // make redirect to current URL
			model.addAttribute("session", session);
			return "productsView";
		}
	}

	@RequestMapping(method = RequestMethod.POST, params = { "productSelected", "search", "logout", "phones", "laptops",
			"watches" })
	public String post(ModelMap model, @RequestParam("productSelected") String productSelected,
			@RequestParam("search") String search, @RequestParam("logout") String logout,
			@RequestParam("phones") String phones, @RequestParam("laptops") String laptops,
			@RequestParam("watches") String watches) {
		ProductController pc = new ProductController(new DBWorker());
		HttpSession session = session();
		if (search != null) {
			phones = phones == null ? "0" : phones;
			laptops = laptops == null ? "0" : laptops;
			watches = watches == null ? "0" : watches;
			int[] categoryID = { Integer.parseInt(phones), Integer.parseInt(laptops), Integer.parseInt(watches) };
			List<Product> products = pc.getSelectedProducts(categoryID);
			model.addAttribute("products", products);
			if (phones.equals("0") && laptops.equals("0") && watches.equals("0")) {
				model.addAttribute("phones", "checked");
				model.addAttribute("laptops", "checked");
				model.addAttribute("watches", "checked");
			} else {
				model.addAttribute("phones", phones.equals("1") ? "checked" : "");
				model.addAttribute("laptops", laptops.equals("2") ? "checked" : "");
				model.addAttribute("watches", watches.equals("3") ? "checked" : "");
			}
		} else if (productSelected != null) {
			Product product = pc.getProduct(Integer.parseInt(productSelected));
			model.addAttribute("product", product);
			return "singleProduct";
		}
		UserController.logout(session, logout); // make redirect to current URL
		model.addAttribute("session", session);
		return "productsView";
	}

}
