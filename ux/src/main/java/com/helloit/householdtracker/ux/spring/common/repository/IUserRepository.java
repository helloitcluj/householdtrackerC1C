package com.helloit.householdtracker.ux.spring.common.repository;

import com.helloit.householdtracker.ux.spring.common.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<User, Integer> {

}