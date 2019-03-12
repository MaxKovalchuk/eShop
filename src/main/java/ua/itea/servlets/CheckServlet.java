package ua.itea.servlets;

import java.util.HashMap;
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
@RequestMapping("/check")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckServlet() {
	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/check.jsp");
//		Cart cart = (Cart) session.getAttribute("cart");
//		System.out.println(cart.getProducts().toString());
//		Cart cartBuffer = new Cart();
//		cartBuffer.setProducts(new HashMap<Product, Integer>(cart.getProducts()));
//		System.out.println(cartBuffer.getProducts().toString());
//		cartBuffer.setSize(cart.getSize());
//		cartBuffer.setTotalCost(cart.getTotalCost());
//		request.setAttribute("cartBuffer", cartBuffer);
//		CartController.cleanCart(cart);
//		session.setAttribute("cart", cart);
//		request.setAttribute("session", session);
//		UserController.logout(request, session, response);
//		try {
//			rd.forward(request, response);
//		} catch (IllegalStateException ex) {
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		ProductController pc = new ProductController(new DBWorker());
//		Cart cart = (Cart) session.getAttribute("cart");
//		if(request.getParameter("goToProducts") != null) {
//			response.sendRedirect(request.getContextPath() + "/products");
//		}
//		session.setAttribute("cart", cart);
//		UserController.logout(request, session, response);
//	}

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get(RedirectAttributes redirectAttributes, @RequestParam(value = "search", required = false) String search, ModelMap model,
			@RequestParam(value = "logout", required = false) String logout) {
		HttpSession session = session();
		Cart cart = (Cart) session.getAttribute("cart");
		Cart cartBuffer = new Cart();
		cartBuffer.setProducts(new HashMap<Product, Integer>(cart.getProducts()));
		cartBuffer.setSize(cart.getSize());
		cartBuffer.setTotalCost(cart.getTotalCost());
		model.addAttribute("cartBuffer", cartBuffer);
		CartController.cleanCart(cart);
		session.setAttribute("cart", cart);
		UserController.logout(session, logout); // make redirect to current URL
		model.addAttribute("session", session);
		if(search != null) {
			redirectAttributes.addAttribute("search", search);
			return new ModelAndView("redirect:/products").getViewName();
		}
		return "check";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String post(ModelMap model, @RequestParam(value = "goToProducts", required = false) String goToProducts,
			@RequestParam(value = "logout", required = false) String logout) {
		HttpSession session = session();
		ProductController pc = new ProductController();
		Cart cart = (Cart) session.getAttribute("cart");
		if (goToProducts != null) {
			return new ModelAndView("redirect:/products").getViewName();
		}
		session.setAttribute("cart", cart);
		UserController.logout(session, logout); // make redirect to current URL
		model.addAttribute("session", session);
		return null;
	}

}
