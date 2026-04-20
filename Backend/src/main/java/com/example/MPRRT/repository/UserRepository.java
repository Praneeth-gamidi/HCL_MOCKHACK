package com.example.MPRRT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MPRRT.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}