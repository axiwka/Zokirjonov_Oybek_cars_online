package com.example.onlinecars.service;

import com.example.onlinecars.model.Brand;
import com.example.onlinecars.respository.BrandRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepo brandRepo;

    public List<Brand> getAll(){
        return brandRepo.findAll();
    }

    public Brand getById(Integer id ){
        Optional<Brand> exist = brandRepo.findById(id);
        if (exist.isPresent()) {
            return exist.get();
        }else throw new IllegalArgumentException("The brand with this " + id + "not found");
    }
    public void addBrand (Brand brand) {
        brandRepo.save(brand);
    }

    public void editBrand(Brand brand){
        brandRepo.save(brand);
    }

    public void deleteBrand(Integer id ){
        Optional<Brand> delete  = brandRepo.findById(id);
        if (delete.isPresent()) {
            brandRepo.deleteById(id);
        }else throw  new IllegalArgumentException("The brand with this "+ id +" not found ");
    }
}
