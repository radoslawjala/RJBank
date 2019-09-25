package rjbank.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rjbank.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public static final String FIND_LOGINS = "SELECT login FROM user;";
	
	User findByLogin(String login);

	@Query(value = FIND_LOGINS, nativeQuery = true)
	public ArrayList<String> findLogins();
}
