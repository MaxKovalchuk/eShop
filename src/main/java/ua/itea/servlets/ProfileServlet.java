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

import ua.itea.controllers.DBWorker;
import ua.itea.controllers.UserController;
import ua.itea.models.User;

@Controller
@RequestMapping("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfileServlet() {
	}

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get(RedirectAttributes redirectAttributes,
			@RequestParam(value = "search", required = false) String search, ModelMap model,
			@RequestParam(value = "logout", required = false) String logout) {
		HttpSession session = session();
		UserController.logout(session, logout); // make redirect to current URL
		model.addAttribute("session", session);
		if (search != null) {
			redirectAttributes.addAttribute("search", search);
			return new ModelAndView("redirect:/products").getViewName();
		}
		return "profile";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String post(ModelMap model, @RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "changeName", required = false) String changeName,
			@RequestParam(value = "changeAddress", required = false) String changeAddress) {
		HttpSession session = session();
		UserController uc = new UserController();
		System.out.println(changeName);
		System.out.println(changeAddress);
		if (changeName.length() > 0) {
			try {
				Integer.parseInt(changeName);
				model.addAttribute("result1", "Name must have letters");
			} catch (NumberFormatException e) {
				int userID = ((User) session.getAttribute("user")).getId();
				if (uc.changeName(userID, changeName)) {
					session.setAttribute("user", uc.getUser(userID));
					model.addAttribute("result1", "Name changed succesfully");
				} else {
					model.addAttribute("result1", "Something went wrong =(");
				}
			}
		} else if (changeAddress.length() > 0) {
			int userID = ((User) session.getAttribute("user")).getId();
			if (uc.changeAddress(userID, changeAddress)) {
				model.addAttribute("result2", "Address changed succesfully");
			} else {
				model.addAttribute("result2", "Something went wrong =(");
			}

		}
		UserController.logout(session, logout); // make redirect to current URL
		model.addAttribute("session", session);
		return "profile";
	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException {
//RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/profile.jsp");
//HttpSession session = request.getSession();
//UserController.logout(request, session, response);
//request.setAttribute("session", session);
//try {
//	rd.forward(request, response);
//} catch (IllegalStateException ex) {
//}
//}
//
//protected void doPost(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException {
//RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/profile.jsp");
//HttpSession session = request.getSession();
//UserController uc = new UserController(new DBWorker());
//if (request.getParameter("changeName") != null) {
//	int userID = ((User) session.getAttribute("user")).getId();
//	if(uc.changeName(userID, request.getParameter("changeName"))) {
//		session.setAttribute("user", uc.getUser(userID));
//		request.setAttribute("result1", "Name changed succesfully");
//	}else {
//		request.setAttribute("result1", "Something went wrong =(");
//	}
//} else if (request.getParameter("changeAddress") != null) {
//	int userID = ((User) session.getAttribute("user")).getId();
//	if(uc.changeAddress(userID, request.getParameter("changeAddress"))) {
//		request.setAttribute("result2", "Address changed succesfully");
//	}else {
//		request.setAttribute("result2", "Something went wrong =(");
//	}
//	
//}
//UserController.logout(request, session, response);
//request.setAttribute("session", session);
//try {
//	rd.forward(request, response);
//} catch (IllegalStateException ex) {
//}
//}
}
