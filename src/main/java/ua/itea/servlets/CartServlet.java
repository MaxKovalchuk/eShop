package ua.itea.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.itea.controllers.CartController;
import ua.itea.controllers.DBWorker;
import ua.itea.controllers.ProductController;
import ua.itea.controllers.UserController;
import ua.itea.models.Cart;
import ua.itea.models.Product;

public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ProductController pc = new ProductController(new DBWorker());
		Cart cart = (Cart) session.getAttribute("cart");
		if (request.getParameter("productID") != null) {
			if (cart == null) {
				cart = new Cart();
			}
			Product product = new Product();
			String[] param  = request.getParameter("productID").split("-");
			product = pc.getProduct(Integer.parseInt(param[0]));
			Integer qnt = Integer.parseInt(param[1]);
			CartController.addProduct(cart, product, qnt);
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath() + "/products");
		}else if(request.getParameter("bought") != null){
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath() + "/check");
		} else {
			if (request.getParameter("remove") != null) {
				Product product = new Product();
				String[] param  = request.getParameter("productID").split("-");
				product = pc.getProduct(Integer.parseInt(param[0]));
				Integer qnt = Integer.parseInt(param[1]);
				CartController.removeProduct(cart, pc.getProduct(Integer.parseInt(request.getParameter("remove"))));
			} else if (request.getParameter("add") != null) {
				Product product = new Product();
				String[] param  = request.getParameter("productID").split("-");
				product = pc.getProduct(Integer.parseInt(param[0]));
				Integer qnt = Integer.parseInt(param[1]);
				CartController.addProduct(cart, product, qnt);
			}
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/cart.jsp");
			session.setAttribute("cart", cart);
			UserController.logout(request, session, response);
			try {
				rd.forward(request, response);
			} catch (IllegalStateException ex) {
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
