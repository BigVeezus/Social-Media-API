package com.springboot.blogAPI.repository;

import com.springboot.blogAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // This Extends every interface from JPARepository.
}
