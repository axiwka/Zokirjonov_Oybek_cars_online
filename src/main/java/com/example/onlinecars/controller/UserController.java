package com.example.onlinecars.controller;

import com.example.onlinecars.ApiResponse;
import com.example.onlinecars.dto.UserDto;
import com.example.onlinecars.model.Users;
import com.example.onlinecars.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @GetMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public HttpEntity<?> getAllUser(){
        List<Users> usersList = userService.getAll();
        return ResponseEntity.ok(new ApiResponse("List of the users",true,usersList));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getUserById(@PathVariable Integer id ){
        Users user = userService.getUSerById(id);
        return ResponseEntity.ok(new ApiResponse("User",true,user));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping
    public HttpEntity<?> saveUser(@RequestBody UserDto userDto){
        userService.save(userDto);
        return ResponseEntity.ok(new ApiResponse("saved",true,null));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping
    public HttpEntity<?> editUser(@RequestBody Users users){
        userService.editUser(users);
        return ResponseEntity.ok(new ApiResponse("edited",true,null));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("deleted",true,null));
    }

    @GetMapping("/page/{page}/size/{size}")
    public HttpEntity<?> getAllByPagination(@PathVariable int page,@PathVariable int size){
        System.out.println();
        List<Users> allUsersFromDb = userService.pagination(page,size);
        return ResponseEntity.ok(new ApiResponse("Pagination",true,allUsersFromDb));
    }
}
