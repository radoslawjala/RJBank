package rjbank.service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rjbank.model.User;
import rjbank.model.UserDetails;
import rjbank.model.UserRole;
import rjbank.repository.UserDetailsRepository;
import rjbank.repository.UserRepository;
import rjbank.repository.UserRoleRepository;

@Service
public class UserService {

	
	private static final String DEFAULT_ROLE = "ROLE_USER";
	private UserRepository ur;
	private UserRoleRepository urr;
	private UserDetailsRepository udr;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setUserRepository(UserRepository ur) {
		this.ur = ur;
	}
	
	@Autowired
	public void setRoleRepository(UserRoleRepository urr) {
		this.urr = urr;
	}
	
	@Autowired
	public void setUserDetailsRepository(UserDetailsRepository udr) {
		this.udr = udr;
	}
	
	public void addUser(User user) {
		UserRole defaultRole = urr.findByRole(DEFAULT_ROLE);
		user.getRoles().add(defaultRole);
		String passwordHash = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordHash);
		ur.save(user);
	}
	
	public String generateLogin() {
		Random r = new Random();
		ArrayList<String> logins = ur.findLogins();
		String result;
		while(true) {
			result = Integer.toString(100000 + r.nextInt(900000));
			if(!logins.contains(result)) {
				break;
			}
		}
		return result;	
	}

	public int generateAccountNumber() {
		Random r = new Random();
		ArrayList<Integer> accountNumbers = udr.findAccountNumbers(); 
		int result;
		while(true) {
			result = 10000000 + r.nextInt(90000000);
			if(!accountNumbers.contains(result)) {
				break;
			}
		}
		return result;
		
	}
	
	public void subtract(int amount, int accountNumber) {
		UserDetails ud = udr.findByAccountNumber(accountNumber);
		ud.setMoney(ud.getMoney() - amount);
		udr.save(ud);
	}
	
	public void add(int amount, int accountNumber) {
		UserDetails ud = udr.findByAccountNumber(accountNumber);
		ud.setMoney(ud.getMoney() + amount);
		udr.save(ud);
	}
	
	public boolean incorrectEmail(String email) {
		UserDetails ud = udr.findByEmail(email);
		if(ud == null) {
			return false;
		}
		return true;
	}
}
