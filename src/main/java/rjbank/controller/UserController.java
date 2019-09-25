package rjbank.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import rjbank.model.User;
import rjbank.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	PasswordEncoder pe;
	
	@Autowired
	UserRepository ur;

	@GetMapping("/changePassword")
	public String changePassword() {
		return "changePassword_form";
	}
	
	@PostMapping("/changePassword")
	public String changedPassword(HttpSession s, HttpServletRequest r) {
		String p0 = r.getParameter("oldPassword");
		String p1 = r.getParameter("newPassword1");
		String p2 = r.getParameter("newPassword2");
		User u = (User) s.getAttribute("user");
		
		if(!p1.equals(p2)) { 
			s.setAttribute("info", "Passwords are not the same!");
			return "changePassword_form";
		}
		else if(!pe.matches(p0, u.getPassword())) {
			s.setAttribute("info", "Current password is not valid!");
			return "changePassword_form";
		}
		else {
			s.setAttribute("info", "Password changed!");
			User user = ur.findByLogin(u.getLogin());
			user.setPassword(pe.encode(p1));
			user.setConfirmPassword(pe.encode(p1));
			ur.save(user);
			return "info";
		}
						
	}
	
	@GetMapping("/delete") 
	public String delete() {
		return "delete";
	}
	
	@PostMapping("/delete")
	public String deletePost(HttpSession s, HttpServletRequest r) {
		String login = (String) r.getParameter("username");
		String password = (String) r.getParameter("password");
		User u = (User) s.getAttribute("user");
		String loginfromsession = u.getLogin();
		if(!login.equals(loginfromsession)) {
			s.setAttribute("info", "Incorrect login");
			System.out.println("Incorrect login");

			return "delete";
		}
		else if(!pe.matches(password, u.getPassword())) {
			s.setAttribute("info", "Incorrect password");
			System.out.println("Incorrect pass");

			return "delete";
		}
		else {
			User user = ur.findByLogin(login);
			ur.delete(user);
			SecurityContextHolder.clearContext();
			s.invalidate();
			System.out.println("Usunieto");
			return "index"; //sendredirect
		}
	}
}
