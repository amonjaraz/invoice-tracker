package com.alexmonjaraz.customuserregistration.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alexmonjaraz.customuserregistration.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("SELECT u from User u where u.userName = ?1") //JPQL ?1 means first parameter in method.
	public User findByUserName(String username);

}
