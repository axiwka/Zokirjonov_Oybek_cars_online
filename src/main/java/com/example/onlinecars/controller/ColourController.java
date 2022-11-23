package com.example.onlinecars.controller;

import com.example.onlinecars.ApiResponse;
import com.example.onlinecars.model.Colour;
import com.example.onlinecars.service.ColourService;
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
@RequestMapping("/colour")
public class ColourController {
    private final ColourService colourService;


    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping
    public HttpEntity<?> getAll(){
        List<Colour> colours = colourService.getAll();
        return ResponseEntity.ok(new ApiResponse("List colours",true,colours));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByID(@PathVariable Integer id){
        Colour colour = colourService.getById(id);
        return ResponseEntity.ok(new ApiResponse("",true,colour));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping
    public HttpEntity<?> addColour(@Valid @RequestBody Colour colour,BindingResult bindingResult){
        colourService.addColour(colour);
        return ResponseEntity.ok(new ApiResponse("Successfully added ",true,null));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping
    public  HttpEntity<?> editColour(@Valid @RequestBody Colour colour, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(bindingResult.getAllErrors());
        }
        colourService.editColour(colour);
        return ResponseEntity.ok(new ApiResponse("Successfully edited",true,null));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteColour(@PathVariable Integer id ){
        colourService.deleteColour(id);
        return ResponseEntity.ok(new ApiResponse("Successfully deleted ",true,null));
    }


    
}
