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

import ua.itea.controllers.DBWorker;
import ua.itea.controllers.UserController;

@Controller
@RequestMapping("/authorization")
public class AuthorizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AuthorizationServlet() {

	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/authorization.jsp");
//		HttpSession session = request.getSession();
//		request.setAttribute("session", session);
//		rd.forward(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		UserController uc = new UserController(new DBWorker());
//		String login = request.getParameter("login");
//		String password = request.getParameter("password");
//		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/authorization.jsp");
//		HttpSession session = null;
//		if (uc.authorizate(login, password)) {
//			session = request.getSession();
//			session.setAttribute("user", uc.getUser(login));
//			request.setAttribute("session", session);
//			response.sendRedirect(request.getContextPath() + "/products");
//		} else {
//			if (request.getAttribute("logout") == null)
//				request.setAttribute("errorText", "<font color = 'red'>Invalid login or password");
//			request.setAttribute("login", login);
//			rd.forward(request, response);
//		}
//	}

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get(ModelMap model) {
		HttpSession session = session();
		model.addAttribute("session", session);
		return "authorization";
	}

	@RequestMapping(method = RequestMethod.POST, params = { "login", "password" })
	public String post(ModelMap model, @RequestParam("login") String login, @RequestParam("password") String password,
			@RequestParam("logout") String logout) {
		UserController uc = new UserController(new DBWorker());
		HttpSession session = null;
		if (uc.authorizate(login, password)) {
			session = session();
			session.setAttribute("user", uc.getUser(login));
			model.addAttribute("session", session);
			return "productsView";
		} else {
			if (logout == null)
				model.addAttribute("errorText", "<font color = 'red'>Invalid login or password");
			model.addAttribute("login", login);
			return "authorization";
		}
	}

}
