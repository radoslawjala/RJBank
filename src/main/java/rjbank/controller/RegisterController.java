package rjbank.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import rjbank.model.User;
import rjbank.model.UserDetails;
import rjbank.service.UserService;

@Controller
public class RegisterController {

	private UserService us;
	
	@Autowired
	public void setUserService(UserService us) {
		this.us = us;
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("userdetails", new UserDetails());
		return "register_form";
	}
	
	@PostMapping("/register")
	public String addUser(@Valid @ModelAttribute("user") User user, BindingResult r1,
			@Valid @ModelAttribute("userdetails") UserDetails userdetails, 
			BindingResult r2, Model model) {
		if(r1.hasErrors() || r2.hasErrors()) {
			return "register_form";
		}
		else if(us.incorrectEmail(userdetails.getEmail())) {
			model.addAttribute("incorrectEmail", "This email is already in use");
			return "register_form";
		}
		else if(!(user.getConfirmPassword().equals(user.getPassword()))) {
			model.addAttribute("incorrectPassword", "Passwords are not the same");
			return "register_form";
		}
		else {
			userdetails.setAccountNumber(us.generateAccountNumber());
			user.setUserDetails(userdetails);
			user.setLogin(us.generateLogin());
			us.addUser(user);
			model.addAttribute("user", user);
			model.addAttribute("userdetails", userdetails);
			return "register_success";
		}
	}

}
