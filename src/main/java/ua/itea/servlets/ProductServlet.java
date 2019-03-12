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

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get(ModelMap model, @RequestParam(value = "productSelected", required = false) String productSelected,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "page", required = false) String page) {
		ProductController pc = new ProductController();
		if (productSelected != null) {
			Product product = pc.getProduct(Integer.parseInt(productSelected));
			model.addAttribute("product", product);
			return "singleProduct";
		} else if (search != null) {
			List<Product> products = pc.search(search);
			double pages = products.size() / 12;
			model.addAttribute("pages", (int) Math.ceil(pages));
			model.addAttribute("products", products);
			return "productsView";
		} else {
			page = page == null ? "1" : page;
			List<Product> products = pc.getSelectedPageProducts(page);
			model.addAttribute("products", products);
			List<Product> allProducts = pc.getAllProducts();
			double pages = allProducts.size() / 12;
			model.addAttribute("pages", (int) Math.ceil(pages));
			HttpSession session = session();
			UserController.logout(session, logout); // make redirect to current URL
			model.addAttribute("session", session);
			return "productsView";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String post(ModelMap model,
			@RequestParam(value = "productSelected", required = false) String productSelected,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "phones", required = false) String phones,
			@RequestParam(value = "laptops", required = false) String laptops,
			@RequestParam(value = "watches", required = false) String watches,
			@RequestParam(value = "goToProducts", required = false) String goToProducts) {
		ProductController pc = new ProductController();
		HttpSession session = session();
		if (search != null) {
			List<Product> products;
			double pages;
			phones = phones == null ? "0" : phones;
			laptops = laptops == null ? "0" : laptops;
			watches = watches == null ? "0" : watches;
			if (phones != null && laptops != null && watches != null) {
				products = pc.getSelectedPageProducts("1");
				pages = products.size() / 12;
				model.addAttribute("products", products);
				model.addAttribute("pages", (int) Math.ceil(pages));
				UserController.logout(session, logout); // make redirect to current URL
				model.addAttribute("session", session);
				return "productsView";
			}
			int[] categoryID = { Integer.parseInt(phones), Integer.parseInt(laptops), Integer.parseInt(watches) };
			products = pc.getSelectedCategoryProducts(categoryID);
			pages = products.size() / 12;
			if (phones.equals("0") && laptops.equals("0") && watches.equals("0")) {
				model.addAttribute("phones", "checked");
				model.addAttribute("laptops", "checked");
				model.addAttribute("watches", "checked");
				products = pc.getAllProducts();
				pages = products.size() / 12;
				model.addAttribute("products", products);
				model.addAttribute("pages", (int) Math.ceil(pages));
				UserController.logout(session, logout); // make redirect to current URL
				model.addAttribute("session", session);
				return "productsView";
			} else if (goToProducts != null) {
				products = pc.getSelectedPageProducts("1");
				pages = products.size() / 12;
				session = session();
				UserController.logout(session, logout); // make redirect to current URL
				model.addAttribute("session", session);
				return "productsView";
			} else {
				model.addAttribute("phones", phones.equals("1") ? "checked" : "");
				model.addAttribute("laptops", laptops.equals("2") ? "checked" : "");
				model.addAttribute("watches", watches.equals("3") ? "checked" : "");
			}
			model.addAttribute("products", products);
			model.addAttribute("pages", (int) Math.ceil(pages));

		} else if (productSelected != null) {
			Product product = pc.getProduct(Integer.parseInt(productSelected));
			model.addAttribute("product", product);
			return "singleProduct";
		}
		UserController.logout(session, logout); // make redirect to current URL
		model.addAttribute("session", session);
		return "productsView";
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

}
