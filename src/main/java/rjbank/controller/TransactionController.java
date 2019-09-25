package rjbank.controller;

import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import rjbank.model.History;
import rjbank.model.User;
import rjbank.service.HistoryService;
import rjbank.service.UserService;

@Controller
public class TransactionController {
	
	private HistoryService hs;
	private UserService us;
	
	@Autowired
	public void setHistoryService(HistoryService hs) {
		this.hs = hs;
	}
	
	@Autowired
	public void setUserService(UserService us) {
		this.us = us;
	}

	@GetMapping("/transaction")
	public String transaction(Model model) {
		model.addAttribute("history", new History());
		return "transaction_form";
	}
	
	@PostMapping("/transaction")
	public String sendTransaction(@ModelAttribute("history") @Valid History history,
			BindingResult bindResult, HttpSession session, Model model) {
		User u = (User)session.getAttribute("user");
		if(bindResult.hasErrors()) {
			return "transaction_form";
		}
		else if(hs.checkAccountNumber(history.getReceiver()) || history.getReceiver() == history.getSender()) {
			model.addAttribute("accountNumberError", "Account number is invalid");
			return "transaction_form";
		}
		else if(u.getUserDetails().getMoney() < history.getAmount()) {
			model.addAttribute("invalidAmount", "You try send more money than you actually have");
			return "transaction_form";	
		}
		else {
			history.setSender((int)session.getAttribute("accountnumber"));
			history.setDate(new Date().toString());
			hs.addTransaction(history);
			us.subtract(history.getAmount(), history.getSender());
			us.add(history.getAmount(), history.getReceiver());
			model.addAttribute("history", history);
			return "transaction_success";
		}
	}
	
	
}
