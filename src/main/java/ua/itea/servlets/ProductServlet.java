package ua.itea.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.itea.controllers.DBWorker;
import ua.itea.controllers.ProductController;
import ua.itea.controllers.UserController;
import ua.itea.models.Product;
import ua.itea.models.User;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductController pc = new ProductController(new DBWorker());
		RequestDispatcher rd = null;
		if (request.getParameter("productSelected") != null) {
			rd = request.getRequestDispatcher("WEB-INF/views/singleProduct.jsp");
			Product product = pc.getProduct(Integer.parseInt(request.getParameter("productSelected")));
			request.setAttribute("product", product);
		}else if(request.getParameter("search") != null) {
			System.out.println("we are searching" + request.getParameter("search"));
			rd = request.getRequestDispatcher("WEB-INF/views/productsView.jsp");
			List<Product> products = pc.search(request.getParameter("search"));
			request.setAttribute("products", products);
		} else {
			rd = request.getRequestDispatcher("WEB-INF/views/productsView.jsp");
			List<Product> products = pc.getAllProducts();
			request.setAttribute("products", products);
		}
		HttpSession session = request.getSession();
		UserController.logout(request, session, response);
		request.setAttribute("session", session);
		try {
			rd.forward(request, response);
		} catch (IllegalStateException ex) {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductController pc = new ProductController(new DBWorker());
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/productsView.jsp");
		if (request.getParameter("search") != null) {
			String phones = request.getParameter("phones") == null ? "0" : request.getParameter("phones");
			String laptops = request.getParameter("laptops") == null ? "0" : request.getParameter("laptops");
			String watches = request.getParameter("watches") == null ? "0" : request.getParameter("watches");
			int[] categoryID = { Integer.parseInt(phones), Integer.parseInt(laptops), Integer.parseInt(watches) };
			List<Product> products = pc.getSelectedProducts(categoryID);
			request.setAttribute("products", products);
			request.setAttribute("phones", phones.equals("1") ? "checked" : "");
			request.setAttribute("laptops", laptops.equals("2") ? "checked" : "");
			request.setAttribute("watches", watches.equals("3") ? "checked" : "");
		} else if (request.getParameter("productSelected") != null) {
			rd = request.getRequestDispatcher("WEB-INF/views/singleProduct.jsp");
			Product product = pc.getProduct(Integer.parseInt(request.getParameter("productSelected")));
			request.setAttribute("product", product);
		}
		UserController.logout(request, session, response);
		request.setAttribute("session", session);
		try {
			rd.forward(request, response);
		} catch (IllegalStateException ex) {
		}
	}

}
