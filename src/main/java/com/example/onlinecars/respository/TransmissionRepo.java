package com.example.onlinecars.respository;

import com.example.onlinecars.model.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionRepo extends JpaRepository<Transmission,Integer> {

}
