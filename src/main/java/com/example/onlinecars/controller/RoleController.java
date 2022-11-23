package com.example.onlinecars.controller;

import com.example.onlinecars.ApiResponse;
import com.example.onlinecars.model.Role;
import com.example.onlinecars.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")

public class RoleController {
    private final RoleService roleService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping
    public HttpEntity<?> getAllRole(){
        List<Role> roleList = roleService.getAllRoles();
        return ResponseEntity.ok(new ApiResponse("List of the roles",true,roleList));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getRoleById(@PathVariable Integer id ){
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(new ApiResponse("role",true,role));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping
    public HttpEntity<?> saveRole(@RequestBody Role role){
        roleService.saveRole(role);
        return ResponseEntity.ok(new ApiResponse("saved",true,null));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping
    public HttpEntity<?> editRole(@RequestBody Role role){
        roleService.editRole(role);
        return ResponseEntity.ok(new ApiResponse("edited",true,null));
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRole(@PathVariable Integer id){
        roleService.deleteRole(id);
        return ResponseEntity.ok(new ApiResponse("deleted",true,null));
    }
}
