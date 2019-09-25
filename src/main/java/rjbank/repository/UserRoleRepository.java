package rjbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rjbank.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	
	UserRole findByRole(String role);
}