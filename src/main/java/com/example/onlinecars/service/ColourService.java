package com.example.onlinecars.service;

import com.example.onlinecars.model.Colour;
import com.example.onlinecars.respository.ColourRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColourService {
    private final ColourRepo colourRepo;

    public List<Colour> getAll(){
        return colourRepo.findAll();
    }

    public Colour getById(Integer id){
        Optional<Colour> exist = colourRepo.findById(id);
        if (exist.isPresent()) {
           return exist.get();
        }else throw  new IllegalArgumentException("Colour with  this " + id + "does not exist ");
    }

    public void addColour (Colour colour) {
        colourRepo.save(colour);
    }

    public void editColour(Colour colour){
        colourRepo.save(colour);
    }

    public void deleteColour(Integer id ) {
        Optional<Colour> delete = colourRepo.findById(id);
        if (delete.isPresent()) {
            colourRepo.deleteById(id);
        } else throw new IllegalArgumentException("Colour with  this " + id + " does not exist ");

    }
}