package ua.itea.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/check.jsp");
		Cart cart = (Cart) session.getAttribute("cart");
		System.out.println(cart.getProducts().toString());
		Cart cartBuffer = new Cart();
		cartBuffer.setProducts(new HashMap<Product, Integer>(cart.getProducts()));
		System.out.println(cartBuffer.getProducts().toString());
		cartBuffer.setSize(cart.getSize());
		cartBuffer.setTotalCost(cart.getTotalCost());
		request.setAttribute("cartBuffer", cartBuffer);
		CartController.cleanCart(cart);
		session.setAttribute("cart", cart);
		request.setAttribute("session", session);
		UserController.logout(request, session, response);
		try {
			rd.forward(request, response);
		} catch (IllegalStateException ex) {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ProductController pc = new ProductController(new DBWorker());
		Cart cart = (Cart) session.getAttribute("cart");
		if(request.getParameter("goToProducts") != null) {
			response.sendRedirect(request.getContextPath() + "/products");
		}
		session.setAttribute("cart", cart);
		UserController.logout(request, session, response);
	}

}
