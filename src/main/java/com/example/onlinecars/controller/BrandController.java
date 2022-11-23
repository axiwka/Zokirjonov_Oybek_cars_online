package com.example.onlinecars.controller;

import com.example.onlinecars.ApiResponse;
import com.example.onlinecars.model.Brand;
import com.example.onlinecars.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Brand> list = brandService.getAll();
        return ResponseEntity.ok(new ApiResponse("Brand list ",true,list));
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        Brand brand = brandService.getById(id);
        return ResponseEntity.ok(new ApiResponse("Brand ",true,brand));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping
    public HttpEntity<?> addBrand(@Valid @RequestBody Brand brand, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(bindingResult.getAllErrors());
        }
        brandService.addBrand(brand);
        return ResponseEntity.ok(new ApiResponse("Successfully added ",true,null));
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping
    public HttpEntity<?> editBrand(@Valid @RequestBody Brand brand,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(bindingResult.getAllErrors());
        }
        brandService.editBrand(brand);
        return ResponseEntity.ok(new ApiResponse("Successfully edited ",true,null));
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteBrand(@PathVariable Integer id){
        brandService.deleteBrand(id);
        return ResponseEntity.ok(new ApiResponse("Successfully deleted ",true,null));
    }


}
