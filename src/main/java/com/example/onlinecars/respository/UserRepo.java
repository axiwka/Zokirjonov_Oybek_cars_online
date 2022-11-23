package com.example.onlinecars.respository;

import com.example.onlinecars.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Integer> {
    Optional<Users> findByPhoneNumber(String phoneNumber);
}
