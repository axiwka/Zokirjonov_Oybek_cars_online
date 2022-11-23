package com.example.onlinecars.controller;

import com.example.onlinecars.ApiResponse;
import com.example.onlinecars.dto.CarsCommentDto;
import com.example.onlinecars.dto.UserDto;
import com.example.onlinecars.model.Users;
import com.example.onlinecars.projection.CarsCommandProjection;
import com.example.onlinecars.service.CarsCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CarsCommentController {
    private final CarsCommentService carsCommentService;

    @GetMapping("/carId/{carId}/page{page}/size/{size}")
    public HttpEntity<?> getAllByCarId(@PathVariable Integer carId, @PathVariable Integer page, @PathVariable Integer size){
        Page<CarsCommandProjection> carsCommandProjectionList = carsCommentService.pagination(carId,page,size);
        return ResponseEntity.ok(new ApiResponse("List",true,carsCommandProjectionList));
    }
    @GetMapping("/userId/{id}")
    public HttpEntity<?> getAllByUserId(@PathVariable Integer id){
        List<CarsCommandProjection> carsCommandProjectionList = carsCommentService.getAllByUserId(id);
        return ResponseEntity.ok(new ApiResponse("List",true,carsCommandProjectionList));
    }
    @PostMapping
    public HttpEntity<?> saveComment(@RequestBody CarsCommentDto carsCommentDto, BindingResult result, @AuthenticationPrincipal
                                     Users users){
        if (result.hasErrors()) {
            return ResponseEntity.ok(result.getAllErrors());
        }
        carsCommentService.saveComment(carsCommentDto,users);
        return ResponseEntity.ok(new ApiResponse("Saved",true,null));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Integer id, @AuthenticationPrincipal Users users){
        carsCommentService.deleteComment(id,users);
        return ResponseEntity.ok(new ApiResponse("Saved",true,null));
    }

}
