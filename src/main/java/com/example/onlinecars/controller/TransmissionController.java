package com.example.onlinecars.controller;

import com.example.onlinecars.ApiResponse;
import com.example.onlinecars.model.Transmission;
import com.example.onlinecars.service.TransmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transmission")
@RequiredArgsConstructor
public class TransmissionController {
    private final TransmissionService service;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping
    public HttpEntity<?> getAll(){
        List<Transmission> transmissions  = service.getAll();
        return ResponseEntity.ok(new ApiResponse("List of transmission",true,transmissions));
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        Transmission transmissions  = service.getById(id);
        return ResponseEntity.ok(new ApiResponse("",true,transmissions));
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping
    public HttpEntity<?> addTransmission(@Valid @RequestBody Transmission transmission,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(bindingResult.getAllErrors());
        }
        service.saveTransmission(transmission);
        return ResponseEntity.ok(new ApiResponse("Successfully saved",true,null));
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping
    public HttpEntity<?> editTransmission(@Valid @RequestBody Transmission transmission, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(bindingResult.getAllErrors());
        }
        service.editTransmission(transmission);
        return ResponseEntity.ok(new ApiResponse("Successfully edited",true,null));
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTransmission(@PathVariable Integer id){
        service.deleteTransmission(id);
        return ResponseEntity.ok(new ApiResponse("Successfully deleted",true,null));
    }
}
