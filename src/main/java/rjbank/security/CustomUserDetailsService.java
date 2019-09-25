package rjbank.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import rjbank.model.User;
import rjbank.model.UserRole;
import rjbank.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository ur;
	
	@Autowired
	public void setUserRepository(UserRepository ur) {
		this.ur = ur;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = ur.findByLogin(username);
		if(user == null) 
			throw new UsernameNotFoundException("User not found");
		org.springframework.security.core.userdetails.User userDetails =
				new org.springframework.security.core.userdetails.User(
						user.getLogin(),
						user.getPassword(),
						convertAuthorities(user.getRoles()));
		return userDetails;
				
		
	}
	
	private Set<GrantedAuthority> convertAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		for(UserRole ur: userRoles) {
			authorities.add(new SimpleGrantedAuthority(ur.getRole()));
		}
		return authorities;
	}

}
