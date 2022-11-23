package com.example.onlinecars.respository;

import com.example.onlinecars.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepo extends JpaRepository<Brand,Integer> {

}
