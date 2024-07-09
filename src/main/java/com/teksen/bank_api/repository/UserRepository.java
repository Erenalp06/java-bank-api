package com.teksen.bank_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teksen.bank_api.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
