package com.kashvillan.studentresult.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kashvillan.studentresult.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

}
 