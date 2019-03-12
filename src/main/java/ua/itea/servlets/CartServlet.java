package ua.itea.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.itea.controllers.CartController;
import ua.itea.controllers.DBWorker;
import ua.itea.controllers.ProductController;
import ua.itea.controllers.UserController;
import ua.itea.models.Cart;
import ua.itea.models.Product;

@Controller
@RequestMapping("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
	}

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get(RedirectAttributes redirectAttributes, @RequestParam(value = "search", required = false) String search, ModelMap model,
			@RequestParam(value = "productID", required = false) String productID,
			@RequestParam(value = "bought", required = false) String bought,
			@RequestParam(value = "remove", required = false) String remove,
			@RequestParam(value = "add", required = false) String add,
			@RequestParam(value = "logout", required = false) String logout) {
		HttpSession session = session();
		ProductController pc = new ProductController();
		Cart cart = (Cart) session.getAttribute("cart");
		if (productID != null) {
			if (cart == null) {
				cart = new Cart();
			}
			Product product = new Product();
			String[] param = productID.split(":");
			product = pc.getProduct(Integer.parseInt(param[0]));
			Integer qnt = Integer.parseInt(param[1]);
			if (qnt > 0) {
				CartController.addProduct(cart, product, qnt);
			} else if (qnt < 0) {
				CartController.removeProduct(cart, product, qnt);
			}
			session.setAttribute("cart", cart);
			model.addAttribute("session", session);
			return "productsView";
		} else if (bought != null) {
			session.setAttribute("cart", cart);
			model.addAttribute("session", session);
			return new ModelAndView("redirect:/check").getViewName();
		} else if (search != null) {
			redirectAttributes.addAttribute("search", search);
			return new ModelAndView("redirect:/products").getViewName();
		} else {
			if (remove != null) {
				Product product = new Product();
				String[] param = productID.split(":");
				product = pc.getProduct(Integer.parseInt(param[0]));
				Integer qnt = Integer.parseInt(param[1]);
				CartController.removeProduct(cart, pc.getProduct(Integer.parseInt(remove)), qnt);
			} else if (add != null) {
				Product product = new Product();
				String[] param = productID.split(":");
				product = pc.getProduct(Integer.parseInt(param[0]));
				Integer qnt = Integer.parseInt(param[1]);
				CartController.addProduct(cart, product, qnt);
			}
			session.setAttribute("cart", cart);
			UserController.logout(session, logout); // make redirect to current URL
			model.addAttribute("session", session);
			return "cart";
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(@RequestParam(value = "rmv", required = false) String rmv) {
		HttpSession session = session();
		ProductController pc = new ProductController();
		Cart cart = (Cart) session.getAttribute("cart");
		CartController.removeProductFully(cart, pc.getProduct(Integer.parseInt(rmv)));
		return "cart";
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException {
//HttpSession session = request.getSession();
//ProductController pc = new ProductController(new DBWorker());
//Cart cart = (Cart) session.getAttribute("cart");
//if (request.getParameter("productID") != null) {
//	if (cart == null) {
//		cart = new Cart();
//	}
//	Product product = new Product();
//	String[] param = request.getParameter("productID").split(":");
//	product = pc.getProduct(Integer.parseInt(param[0]));
//	Integer qnt = Integer.parseInt(param[1]);
//	if (qnt > 0) {
//		CartController.addProduct(cart, product, qnt);
//	} else if (qnt < 0) {
//		CartController.removeProduct(cart, product, qnt);
//	}
//	session.setAttribute("cart", cart);
//	response.sendRedirect(request.getContextPath() + "/products");
//} else if (request.getParameter("bought") != null) {
//	session.setAttribute("cart", cart);
//	response.sendRedirect(request.getContextPath() + "/check");
//} else {
//	if (request.getParameter("remove") != null) {
//		Product product = new Product();
//		String[] param = request.getParameter("productID").split(":");
//		product = pc.getProduct(Integer.parseInt(param[0]));
//		Integer qnt = Integer.parseInt(param[1]);
//		CartController.removeProduct(cart, pc.getProduct(Integer.parseInt(request.getParameter("remove"))),qnt);
//	} else if (request.getParameter("add") != null) {
//		Product product = new Product();
//		String[] param = request.getParameter("productID").split(":");
//		product = pc.getProduct(Integer.parseInt(param[0]));
//		Integer qnt = Integer.parseInt(param[1]);
//		CartController.addProduct(cart, product, qnt);
//	}
//	RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/cart.jsp");
//	session.setAttribute("cart", cart);
//	UserController.logout(request, session, response);
//	try {
//		rd.forward(request, response);
//	} catch (IllegalStateException ex) {
//	}
//}
//
//}
//
//protected void doPost(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException {
//doGet(request, response);
//}
}
