package rjbank.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import rjbank.model.History;
import rjbank.model.User;
import rjbank.service.HistoryService;

@Controller
public class HomeController {
	
	private HistoryService hs;
	@Autowired
	private PasswordEncoder pe;
	
	@Autowired
	public void setHistoryService(HistoryService hs) {
		this.hs = hs;
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
	
	@GetMapping("/home")
	public String homeAfterLogin() {
		return "home";
	}
	
	@GetMapping("/history")
	public String getHistory(HttpSession session, Model model) {
		
		User user = (User)session.getAttribute("user");
		ArrayList<History> historyList = new ArrayList<>();
		historyList = hs.getUserHistory(user.getUserDetails().getAccountNumber());
		model.addAttribute("historyList", historyList);
		return "history";
	}
	
	@GetMapping("/info")
	public String info() {
		return "info";
	}
	
	@PostMapping("/pass")
	public String pass(HttpSession s, HttpServletRequest r) {
		User u = (User)s.getAttribute("user");
		String p2 = r.getParameter("password2");	
		String p = u.getPassword();
		System.out.println(p);
		System.out.println(p2);
		boolean wynik = pe.matches(p2, p);
		System.out.println(wynik);
		
		return "home";
				
	}
	
 }
