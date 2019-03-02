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
@RequestMapping("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/registration.jsp");
//		boolean form = true;
//		request.setAttribute("form", form);
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
//		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/registration.jsp");
//		String name = request.getParameter("name");
//		String repassword = request.getParameter("repassword");
//		String age = request.getParameter("age");
//		String gender = request.getParameter("gender");
//		String address = request.getParameter("address");
//		String comment = request.getParameter("comment");
//		String agree = request.getParameter("agree");
//		boolean form = true;
//		boolean error = false;
//		StringBuilder errorText = new StringBuilder("<font color = 'red'> <ul>");
//		if (login != null) {
//			if (login.length() == 0) {
//				error = true;
//				errorText.append("<li>Login empty</li>");
//			}
//			if (name.length() == 0) {
//				error = true;
//				errorText.append("<li>Name empty</li>");
//			}
//			if (password.length() == 0) {
//				error = true;
//				errorText.append("<li>Password empty</li>");
//			}
//			if (repassword.length() == 0) {
//				error = true;
//				errorText.append("<li>Repassword empty</li>");
//			}
//			if (repassword.length() != 0 && !repassword.equals(password)) {
//				error = true;
//				errorText.append("<li>Passwords don't match</li>");
//			}
//			if (age.length() == 0) {
//				error = true;
//				errorText.append("<li>Age empty</li>");
//			}
//			if (age.length() != 0 && Integer.parseInt(age) < 0) {
//				error = true;
//				errorText.append("<li>Age invalid value</li>");
//			}
//			if (gender == null) {
//				error = true;
//				errorText.append("<li>Gender empty</li>");
//			}
//			if (gender != null && gender.length() == 0) {
//				error = true;
//				errorText.append("<li>Gender empty</li>");
//			}
//			if (address.length() == 0) {
//				error = true;
//				errorText.append("<li>Address empty</li>");
//			}
//			if (comment.length() < 1) {
//				error = true;
//				errorText.append("<li>Comment empty</li>");
//			}
//			if (agree == null) {
//				error = true;
//				errorText.append("<li>DOWNLOAD MY BROWSER !</li>");
//			}
//			if (!uc.validateLogin(login)) {
//				errorText.append("<li>Such email already exists</li>");
//				error = true;
//			}
//			if (error) {
//				form = true;
//				errorText.append("</ul>");
//				request.setAttribute("login", login);
//				request.setAttribute("name", name);
//				request.setAttribute("age", age);
//				request.setAttribute("gender", gender);
//				if (gender != null) {
//					request.setAttribute("genderM", gender.equals("M") ? "checked" : "");
//					request.setAttribute("genderF", gender.equals("F") ? "checked" : "");
//				}
//				request.setAttribute("address", address);
//				if(address != null) {
//					request.setAttribute("address1", (Integer.parseInt(address)) == 1 ? "selected" : "");
//					request.setAttribute("address2", (Integer.parseInt(address)) == 2 ? "selected" : "");
//					request.setAttribute("address3", (Integer.parseInt(address)) == 3 ? "selected" : "");
//				}
//				request.setAttribute("comment", comment);
//				request.setAttribute("agree", agree);
//				request.setAttribute("errorText", errorText.toString());
//				request.setAttribute("isError", error);
//				request.setAttribute("form", form);
//			} else {
//				form = false;
//				uc.insert(login, password, name, age, gender, address, comment);
//				response.sendRedirect(request.getContextPath() + "/authorization");
//			}
//		}
//
//		HttpSession session = request.getSession();
//		request.setAttribute("session", session);
//		try {
//			rd.forward(request, response);
//		} catch (IllegalStateException ex) {}
//	}

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get(ModelMap model) {
		boolean form = true;
		model.addAttribute("form", form);
		HttpSession session = session();
		model.addAttribute("session", session);
		return "registration";
	}

	@RequestMapping(method = RequestMethod.POST, params = { "login", "password", "name", "repassword", "age", "gender",
			"address", "comment", "agree" })
	public String post(ModelMap model, @RequestParam("login") String login, @RequestParam("password") String password,
			@RequestParam("name") String name, @RequestParam("repassword") String repassword,
			@RequestParam("age") String age, @RequestParam("gender") String gender,
			@RequestParam("address") String address, @RequestParam("comment") String comment,
			@RequestParam("agree") String agree) {
		UserController uc = new UserController(new DBWorker());
		boolean form = true;
		boolean error = false;
		StringBuilder errorText = new StringBuilder("<font color = 'red'> <ul>");
		if (login != null) {
			if (login.length() == 0) {
				error = true;
				errorText.append("<li>Login empty</li>");
			}
			if (name.length() == 0) {
				error = true;
				errorText.append("<li>Name empty</li>");
			}
			if (password.length() == 0) {
				error = true;
				errorText.append("<li>Password empty</li>");
			}
			if (repassword.length() == 0) {
				error = true;
				errorText.append("<li>Repassword empty</li>");
			}
			if (repassword.length() != 0 && !repassword.equals(password)) {
				error = true;
				errorText.append("<li>Passwords don't match</li>");
			}
			if (age.length() == 0) {
				error = true;
				errorText.append("<li>Age empty</li>");
			}
			if (age.length() != 0 && Integer.parseInt(age) < 0) {
				error = true;
				errorText.append("<li>Age invalid value</li>");
			}
			if (gender == null) {
				error = true;
				errorText.append("<li>Gender empty</li>");
			}
			if (gender != null && gender.length() == 0) {
				error = true;
				errorText.append("<li>Gender empty</li>");
			}
			if (address.length() == 0) {
				error = true;
				errorText.append("<li>Address empty</li>");
			}
			if (comment.length() < 1) {
				error = true;
				errorText.append("<li>Comment empty</li>");
			}
			if (agree == null) {
				error = true;
				errorText.append("<li>DOWNLOAD MY BROWSER !</li>");
			}
			if (!uc.validateLogin(login)) {
				errorText.append("<li>Such email already exists</li>");
				error = true;
			}
			if (error) {
				form = true;
				errorText.append("</ul>");
				model.addAttribute("login", login);
				model.addAttribute("name", name);
				model.addAttribute("age", age);
				model.addAttribute("gender", gender);
				if (gender != null) {
					model.addAttribute("genderM", gender.equals("M") ? "checked" : "");
					model.addAttribute("genderF", gender.equals("F") ? "checked" : "");
				}
				model.addAttribute("address", address);
				if (address != null) {
					model.addAttribute("address1", (Integer.parseInt(address)) == 1 ? "selected" : "");
					model.addAttribute("address2", (Integer.parseInt(address)) == 2 ? "selected" : "");
					model.addAttribute("address3", (Integer.parseInt(address)) == 3 ? "selected" : "");
				}
				model.addAttribute("comment", comment);
				model.addAttribute("agree", agree);
				model.addAttribute("errorText", errorText.toString());
				model.addAttribute("isError", error);
				model.addAttribute("form", form);
			} else {
				form = false;
				uc.insert(login, password, name, age, gender, address, comment);
				return "authorization";
			}
		}
		HttpSession session = session();
		model.addAttribute("session", session);
		return "registration";
	}

}
