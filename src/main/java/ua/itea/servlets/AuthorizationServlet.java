package ua.itea.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.itea.controllers.DBWorker;
import ua.itea.controllers.UserController;
import ua.itea.models.User;

public class AuthorizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AuthorizationServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/authorization.jsp");
		HttpSession session = request.getSession();
		request.setAttribute("session", session);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserController uc = new UserController(new DBWorker());
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/authorization.jsp");
		HttpSession session = null;
		if (uc.authorizate(login, password)) {
			session = request.getSession();
			session.setAttribute("user", uc.getUser(login));
			request.setAttribute("session", session);
			response.sendRedirect(request.getContextPath() + "/products");
		} else {
			if (request.getAttribute("logout") == null)
				request.setAttribute("errorText", "<font color = 'red'>Invalid login or password");
			request.setAttribute("login", login);
			UserController.logout(request, session);
			rd.forward(request, response);
		}
	}

}
