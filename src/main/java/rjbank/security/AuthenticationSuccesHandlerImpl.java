package rjbank.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import rjbank.repository.UserRepository;


@Component
public class AuthenticationSuccesHandlerImpl implements AuthenticationSuccessHandler {

	@Autowired
	UserRepository ur;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		rjbank.model.User myuser = new rjbank.model.User();
		myuser = ur.findByLogin(authUser.getUsername());
		
		session.setAttribute("username", myuser.getUserDetails().getFirstName());
		session.setAttribute("accountnumber", myuser.getUserDetails().getAccountNumber());
		session.setAttribute("login", myuser.getLogin());
		session.setAttribute("user", myuser); //to wszystko wyzej mozesz usunac i przerobic kod
		session.removeAttribute("error");
		response.sendRedirect("/home");
		
	}

}
