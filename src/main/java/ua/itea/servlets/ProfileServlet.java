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

public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfileServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/profile.jsp");
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
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/profile.jsp");
		HttpSession session = request.getSession();
		UserController uc = new UserController(new DBWorker());
		if (request.getParameter("changeName") != null) {
			int userID = ((User) session.getAttribute("user")).getId();
			if(uc.changeName(userID, request.getParameter("changeName"))) {
				session.setAttribute("user", uc.getUser(userID));
				request.setAttribute("result1", "Name changed succesfully");
			}else {
				request.setAttribute("result1", "Something went wrong =(");
			}
		} else if (request.getParameter("changeAddress") != null) {
			int userID = ((User) session.getAttribute("user")).getId();
			if(uc.changeAddress(userID, request.getParameter("changeAddress"))) {
				request.setAttribute("result2", "Address changed succesfully");
			}else {
				request.setAttribute("result2", "Something went wrong =(");
			}
			
		}
		UserController.logout(request, session, response);
		request.setAttribute("session", session);
		try {
			rd.forward(request, response);
		} catch (IllegalStateException ex) {
		}
	}

}
