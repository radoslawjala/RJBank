package rjbank.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rjbank.model.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

	public static final String FIND_ACCOUNT_NUMBERS = "SELECT account_number FROM user_details;";

	
	UserDetails findByAccountNumber(int accountNumber);
	UserDetails findByEmail(String email);
	
	@Query(value = FIND_ACCOUNT_NUMBERS, nativeQuery = true)
	public ArrayList<Integer> findAccountNumbers();
}
