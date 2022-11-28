package com.example.onlinecars.controller;

import com.example.onlinecars.ApiResponse;
import com.example.onlinecars.dto.CarDto;
import com.example.onlinecars.dto.CarDtoById;
import com.example.onlinecars.model.Users;
import com.example.onlinecars.projection.CarProjection;
import com.example.onlinecars.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService service;

    @GetMapping()
    public HttpEntity<?> getAll(){
        System.out.println("Hello controller ");
        List<CarProjection> list = service.getAll();
        System.out.println("Car List -> " + list);
        return ResponseEntity.ok(new ApiResponse("Car List",true,list));
    }

    @GetMapping("/{id}")
    public  HttpEntity<?> getByID(@PathVariable Integer id ){
        CarProjection projection = service.getById(id);
        return ResponseEntity.ok(new ApiResponse("Car ",true,projection));
    }
    @GetMapping("/year/{year}")
    public HttpEntity<?> getByYear(@PathVariable Integer year){
        List<CarProjection> list=service.getCarByYear(year);
        return ResponseEntity.ok(new ApiResponse("",true,list));
    }



    @PostMapping
    public HttpEntity<?> addCar(@Valid @RequestBody CarDto carDto, BindingResult bindingResult,
                                @AuthenticationPrincipal Users users){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(bindingResult.getAllErrors());
        }
        service.addCar(carDto,users);
        return ResponseEntity.ok(new ApiResponse("Saved",true,null));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCar(@PathVariable Integer id){
        service.deleteCar(id);
        return ResponseEntity.ok(new ApiResponse("Car deleted",true,null));
    }

    @PutMapping()
    public HttpEntity<?> updateCar(@Valid @RequestBody CarDtoById carDtoById,BindingResult bindingResult,
                                   @AuthenticationPrincipal Users users){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(bindingResult.getAllErrors());
        }
        service.updateCar(carDtoById,users);
        return ResponseEntity.ok(new ApiResponse("Updated",true,null));
    }

    @GetMapping("/from/{year1}/to/{year2}")
    public HttpEntity<?> getCarsBetweenYears(@PathVariable Integer year1,
                                             @PathVariable Integer year2){
        List<CarProjection> projections = service.getCarsBetweenYears(year1,year2);
        return ResponseEntity.ok(new ApiResponse("Car list based on the year ",true,projections));
    }


//    @GetMapping("/page/{page}/size/{size}")
//    public HttpEntity<?> getAllByPagination(@PathVariable int page,@PathVariable int size){
//        System.out.println();
//        List<CarProjection> allUsersFromDb = service.pagination(page,size);
//        return ResponseEntity.ok(new ApiResponse("Pagination",true,allUsersFromDb));
//    }


}
