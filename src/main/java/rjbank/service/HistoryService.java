package rjbank.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rjbank.model.History;
import rjbank.repository.HistoryRepository;
import rjbank.repository.UserDetailsRepository;

@Service
public class HistoryService {

	private HistoryRepository hr;
	private UserDetailsRepository udr;
	
	@Autowired
	public void setHistoryRepository(HistoryRepository hr) {
		this.hr = hr;
	}
	
	@Autowired
	public void setUserDetailsRepository(UserDetailsRepository udr) {
		this.udr = udr;
	}
	
	public void addTransaction(History history) {
		hr.save(history);
	}
	
	public ArrayList<History> getUserHistory(int accountNumber) {
		ArrayList<History> userHistory = new ArrayList<>();
		userHistory.addAll(hr.getByReceiver(accountNumber));
		userHistory.addAll(hr.getBySender(accountNumber));	
		return userHistory;
	}
	
	public boolean checkAccountNumber(int number) {
		ArrayList<Integer> accountNumbers = udr.findAccountNumbers();
		if(accountNumbers.contains(number)) {
			return false;
		}
		return true;
	}
}
